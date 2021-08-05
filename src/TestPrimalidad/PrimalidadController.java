package TestPrimalidad;

import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class PrimalidadController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqOp1;
    @FXML
    private TextField tfOp1;
    @FXML
    private Label etqResul;
    @FXML
    private TextField tfResultado;
    @FXML
    private MenuButton botonUniResul;
    @FXML
    private MenuItem resultadoDecimal;
    @FXML
    private MenuItem resultadoBinario;
    @FXML
    private MenuItem resultadoHexadecimal;
    @FXML
    private Label etqDigitosOp1;

    private PrincipalController pc;
    private Validacion validar;
    private PrimalidadLogica pl;
    private int base;
    private String resultado;    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = fxmlLoader.getController();
        validar = new Validacion();
        pl = new PrimalidadLogica();
        
        this.base = pc.getBase();
        pl.setBase(this.base);
        
        resultado = "";
        botonUniResul.setVisible(false);
        
                switch(this.base)
        {
            case 10:
                validar.validacionEntradaEnteros(tfOp1);
                break;
            case 2:
                validar.validacionEntradaBinario(tfOp1);
                break;
            case 16:
                validar.validacionEntradaHexadecimal(tfOp1);
                break;
        }
    }    

    @FXML
    private void longitudOp1(KeyEvent event) {
        tfOp1.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResul.setVisible(false);
        
        String texto = tfOp1.getText();
        String resul = "";
        
        switch (this.base)
        {
            case 10:
                resul = validar.longitudDecimal(texto);
                break;
            case 2:
                resul = validar.longitudBinario(texto);
                break;
            case 16:
                resul = validar.longitudHexadecimal(texto);
                break;
        }
        etqDigitosOp1.setText(resul);
    }

    private void longitudResultado(int b) {
        String texto = tfOp1.getText();
        String resul = "";
                
        resultadoBinario.setVisible(true);
        resultadoHexadecimal.setVisible(true);
        
        switch (b)
        {
            case 10:
                resul = validar.longitudDecimal(texto);
                botonUniResul.setText("D");
                break;
            case 2:
                resul = validar.longitudBinario(texto);
                botonUniResul.setText("B");
                break;
            case 16:
                resul = validar.longitudHexadecimal(texto);
                botonUniResul.setText("H");
                break;
        }
        etqDigitosOp1.setText(resul);
        
        botonUniResul.setVisible(true);
    }    
    
    @FXML
    private void resultadoDecimal(ActionEvent event) {
        botonUniResul.setText("D");
        // Transforma el resultado en decimal.
        if(this.base != 10)
        {
            BigInteger b = new BigInteger(this.resultado, this.base); // Lo pasa a decimal
            String r = b.toString(10);
            r = validar.separadorMiles(r, 10);
            tfOp1.setText(r.toUpperCase());
            longitudResultado(10);
        }
        else
        {
            tfOp1.setText(this.resultado);
            String resul = validar.longitudDecimal(this.resultado);
            etqDigitosOp1.setText(resul);
        }
    }

    @FXML
    private void resultadoBinario(ActionEvent event) {
        botonUniResul.setText("B");
        // Transforma el resultado en binario.
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b = new BigInteger(aux, this.base); // Lo pasa a binario
        String r = b.toString(2);
        
        tfOp1.setText(r.toUpperCase());
        longitudResultado(2);  
    }

    @FXML
    private void resultadoHexadecimal(ActionEvent event) {
        botonUniResul.setText("H");
        // Transforma el resultado en hexadecimal.
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b = new BigInteger(aux, this.base); // Lo pasa a hexadecimal
        String r = b.toString(16);
        
        tfOp1.setText(r.toUpperCase());
        longitudResultado(16);
    }
    

    @FXML
    private void borrar(ActionEvent event) {
        tfOp1.clear();
        tfOp1.setPromptText("");
        etqDigitosOp1.setText("");
        tfResultado.clear();
        botonUniResul.setVisible(false);         
    }

    
    private boolean comprobacionNulos(TextField tf){
        boolean enc = true;
        if(tf.getText().equals(""))
        {
            enc = false;
            tf.setPromptText("Introduce un valor");
            tfResultado.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionNumeros(TextField tf, String num){
        boolean enc = true;
        if(!validar.isNumber(num, this.base))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfResultado.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionDecimales(TextField tf, String num){
        boolean enc = true;
        if(num.contains("."))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfResultado.setText("");
        }
        return(enc);
    }

    public void escribirHistorial(){
        String op1 = tfOp1.getText();
        String resul = tfResultado.getText();
        
        String historial = (op1 + " " + resul.toLowerCase() + "\n\n");
        
        pc.textoHistorial.setText(historial + pc.textoHistorial.getText()); 
    }    
    
    private void establecerResultado(String op, String resul){
        op = validar.separadorMiles(op, this.base);
        tfOp1.setText(op.toUpperCase());
        this.resultado = op;
        tfResultado.setText(resul);
        longitudResultado(this.base);
        
        escribirHistorial();
    }
        
    private void error(String t){
        Alert datos = new Alert(Alert.AlertType.INFORMATION);
        datos.setTitle("Caracter inválido");
        datos.setHeaderText(null);
        datos.setContentText(t);
        ((Stage) datos.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Imagenes/Logo1.PNG"));
        Optional<ButtonType> ventana = datos.showAndWait();
    }   
    
    
    @FXML
    private void resultado(ActionEvent event) {
        boolean enc;
        String resul = "";
        
        // Comrpobar si el operando es nulo
        enc = comprobacionNulos(tfOp1);
        if(enc)
        {
            // Si no es nulo, validar formato
            String op = tfOp1.getText();
            op = validar.formato(op);
            
            // Comprobar si es un número correcto
            enc = comprobacionNumeros(tfOp1, op);
            if(enc) // Valores correctos
            {
                if(this.base == 10) // Si son números en Decimal, sustituir las comas por puntos
                    op = validar.comaAPunto(op);
                
                // Los números tienen que ser enteros y positivos
                enc = (comprobacionDecimales(tfOp1, op) & (op.compareTo("0") > 0));
                if(enc) // Números enteros y positivos
                    resul = pl.primalidad(op);
                else
                {
                    tfOp1.setStyle("-fx-text-inner-color: red;");
                    error("El operando tiene que ser un número entero positivo.");
                    tfResultado.setText("");
                }
            }
            else
                error("Valor incorrecto.");
            
            if(enc)
                establecerResultado(op, resul);
        }
    }   
}