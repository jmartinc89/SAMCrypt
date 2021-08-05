package Modulo;

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

public class ModuloController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqOp1;
    @FXML
    private TextField tfOp1;
    @FXML
    private Label etqDigitosOp1;
    @FXML
    private Label etqModulo;
    @FXML
    private TextField tfModulo;
    @FXML
    private Label etqDigitosOp21;
    @FXML
    private Label etqResul;
    @FXML
    private TextField tfResultado;
    @FXML
    private MenuButton botonUniResultado;
    @FXML
    private MenuItem resultadoDecimal;
    @FXML
    private MenuItem resultadoBinario;
    @FXML
    private MenuItem resultadoHexadecimal;
    @FXML
    private Label etqDigitosResultado;

    private PrincipalController pc;
    private Validacion validar;
    private ModuloLogica ml;
    private int base;
    private String resultado;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = fxmlLoader.getController();
        validar = new Validacion();
        ml = new ModuloLogica();
        
        this.base = pc.getBase();
        ml.setBase(this.base);
        
        resultado = "";
        botonUniResultado.setVisible(false);
        
        switch(this.base)
        {
            case 10:
                validar.validacionEntradaEnteros(tfOp1);
                validar.validacionEntradaEnteros(tfModulo);
                break;
            case 2:
                validar.validacionEntradaBinario(tfOp1);
                validar.validacionEntradaBinario(tfModulo);
                break;
            case 16:
                validar.validacionEntradaHexadecimal(tfOp1);
                validar.validacionEntradaHexadecimal(tfModulo);
                break;
        }
    }    


    @FXML
    private void longitudOp1(KeyEvent event) {
        tfOp1.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResultado.setVisible(false);
        etqDigitosResultado.setText("");
        
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
        botonUniResultado.setVisible(false);
        etqDigitosResultado.setText("");        
    }

    @FXML
    private void longitudMod(KeyEvent event) {
        tfModulo.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResultado.setVisible(false);
        etqDigitosResultado.setText("");
        
        String texto = tfModulo.getText();
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
        etqDigitosOp21.setText(resul);        
    }

    private void longitudResultado(int b) {
        String texto = tfResultado.getText();
        String resul = "";
        
        resultadoBinario.setVisible(true);
        resultadoHexadecimal.setVisible(true);
        
        switch (b)
        {
            case 10:
                resul = validar.longitudDecimal(texto);
                botonUniResultado.setText("D");
                break;
            case 2:
                resul = validar.longitudBinario(texto);
                botonUniResultado.setText("B");
                break;
            case 16:
                resul = validar.longitudHexadecimal(texto);
                botonUniResultado.setText("H");
                break;
        }
        etqDigitosResultado.setText(resul);
        
        if(this.resultado.contains(",") | this.resultado.compareTo("0") < 0) 
        {
            resultadoBinario.setVisible(false);
            resultadoHexadecimal.setVisible(false);
        }
        
        botonUniResultado.setVisible(true);
    }
  
    @FXML
    private void resultadoDecimal(ActionEvent event) {
        botonUniResultado.setText("D");
        // Transforma el resultado en decimal.
        if(this.base != 10)
        {
            BigInteger b = new BigInteger(this.resultado, this.base); // Lo pasa a decimal
            String r = b.toString(10);
            r = validar.separadorMiles(r, 10);
            tfResultado.setText(r.toUpperCase());
            longitudResultado(10);
        }
        else
        {
            tfResultado.setText(this.resultado);
            String resul = validar.longitudDecimal(this.resultado);
            etqDigitosResultado.setText(resul);
        }        
    }

    @FXML
    private void resultadoBinario(ActionEvent event) {
        botonUniResultado.setText("B");
        // Transforma el resultado en binario.
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b = new BigInteger(aux, this.base); // Lo pasa a binario
        String r = b.toString(2);
        
        tfResultado.setText(r.toUpperCase());
        longitudResultado(2);        
    }

    @FXML
    private void resultadoHexadecimal(ActionEvent event) {
        botonUniResultado.setText("H");
        // Transforma el resultado en hexadecimal.
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b = new BigInteger(aux, this.base); // Lo pasa a hexadecimal
        String r = b.toString(16);
        
        tfResultado.setText(r.toUpperCase());
        longitudResultado(16);        
    }


    @FXML
    private void borrar(ActionEvent event) {
        tfOp1.clear();
        tfOp1.setPromptText("");
        etqDigitosOp1.setText("");
        tfModulo.clear();
        tfModulo.setPromptText("");
        etqDigitosOp21.setText("");
        tfResultado.clear();
        etqDigitosResultado.setText("");
        botonUniResultado.setVisible(false); 
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
        String op = tfOp1.getText();
        String mod = tfModulo.getText();
        String resul = tfResultado.getText();

        String historial = (op + " ( mod " + mod + " ) = " + resul + "\n\n");

        pc.textoHistorial.setText(historial + pc.textoHistorial.getText()); 
    }

    private void establecerResultado(String op1, String mod, String resul){
        op1 = validar.separadorMiles(op1, this.base);
        tfOp1.setText(op1.toUpperCase());
        mod = validar.separadorMiles(mod, base);
        tfModulo.setText(mod.toUpperCase());
        resul = validar.separadorMiles(resul, base);
        this.resultado = resul.toUpperCase();
        tfResultado.setText(resultado);
        longitudResultado(this.base);
        
        escribirHistorial();
    }
    
    private void error(String t){
        Alert datos = new Alert(Alert.AlertType.ERROR);
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
        
        // Primero se comprueba si el operando y el módulo son nulos
        enc = (comprobacionNulos(tfOp1) & comprobacionNulos(tfModulo));
        if(enc)
        {
            // Si no son nulos, se valida el formato
            String op = tfOp1.getText();
            String mod = tfModulo.getText();           
            op = validar.formato(op);
            mod = validar.formato(mod);
            
            // Comprobar si son números correctos
            enc = (comprobacionNumeros(tfOp1, op) & comprobacionNumeros(tfModulo, mod));
            if(enc) // Valores correctos
            {
                if(this.base == 10) // Si son números en Decimal, sustituir las comas por puntos
                {
                    op = validar.comaAPunto(op);
                    mod = validar.comaAPunto(mod);
                }
                // Los números tienen que ser enteros para porder realizar el módulo
                enc = (comprobacionDecimales(tfOp1, op) & comprobacionDecimales(tfModulo, mod));
                if(enc) // Números enteros
                {
                    // El módulo tiene que ser un número positivo mayor que 0
                    enc = (mod.compareTo("0") > 0);
                    if(enc)
                    {
                        resul = ml.modulo(op, mod);
                        tfModulo.setText(mod);
                    }
                    else
                        error("El módulo tiene que ser un número entero positivo mayor que 0.");
                }
                else
                    error("No se puede realizar el módulo de números decimales.");
            }
            else
                error("Valor incorrecto.");
            
            if(enc)
                establecerResultado(op, mod, resul);
        }      
    }   
}