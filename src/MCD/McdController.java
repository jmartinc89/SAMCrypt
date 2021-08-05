package MCD;

import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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

public class McdController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqOp1;
    @FXML
    private TextField tfOp1;
    @FXML
    private Label etqDigitosOp1;
    @FXML
    private Label etqOp2;
    @FXML
    private TextField tfOp2;
    @FXML
    private Label etqDigitosOp2;
    @FXML
    private CheckBox etqOp3;
    @FXML
    private TextField tfOp3;
    @FXML
    private Label etqDigitosOp3;
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
    private Label etqDigitosResultado;

    private PrincipalController pc;
    private Validacion validar;
    private MCDLogica mcdl;
    private int base;
    private String resultado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfOp3.setDisable(true);
        etqDigitosOp3.setDisable(true);
        
        pc = fxmlLoader.getController();
        validar = new Validacion();
        mcdl = new MCDLogica();
        
        this.base = pc.getBase();
        mcdl.setBase(this.base);
        
        resultado = "";
        botonUniResul.setVisible(false);   
        
        switch(this.base)
        {
            case 10:
                validar.validacionEntradaEnteros(tfOp1);
                validar.validacionEntradaEnteros(tfOp2);
                validar.validacionEntradaEnteros(tfOp3);
                break;
            case 2:
                validar.validacionEntradaBinario(tfOp1);
                validar.validacionEntradaBinario(tfOp2);
                validar.validacionEntradaBinario(tfOp3);
                break;
            case 16:
                validar.validacionEntradaHexadecimal(tfOp1);
                validar.validacionEntradaHexadecimal(tfOp2);
                validar.validacionEntradaHexadecimal(tfOp3);
                break;
        }
    }    

    @FXML
    private void longitudOp1(KeyEvent event) {
        tfOp1.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResul.setVisible(false);
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
    }

    @FXML
    private void longitudOp2(KeyEvent event) {
        tfOp2.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResul.setVisible(false);
        etqDigitosResultado.setText("");
        
        String texto = tfOp2.getText();
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
        etqDigitosOp2.setText(resul);        
    }

    @FXML
    private void longitudOp3(KeyEvent event) {
        tfOp3.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        botonUniResul.setVisible(false);
        etqDigitosResultado.setText("");
        
        String texto = tfOp3.getText();
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
        etqDigitosOp3.setText(resul);        
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
        etqDigitosResultado.setText(resul);
        
        if(this.resultado.contains(",") | this.resultado.compareTo("0") < 0) 
        {
            resultadoBinario.setVisible(false);
            resultadoHexadecimal.setVisible(false);
        }
        
        botonUniResul.setVisible(true);
    }    
    
    private boolean auxOp3(){
        if(etqOp3.isSelected())
        {
            tfOp3.setDisable(false);
            etqDigitosOp3.setDisable(false);
            return (true);
        }
        else
        {
            tfOp3.setDisable(true);
            tfOp3.clear();
            tfOp3.setPromptText("");
            etqDigitosOp3.setDisable(true);
            etqDigitosOp3.setText("");
            return (false);
        }
    }

    @FXML
    private void habilitarOp3(ActionEvent event) {
        auxOp3();
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
        botonUniResul.setText("B");
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
        botonUniResul.setText("H");
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
        tfOp2.clear();
        tfOp2.setPromptText("");
        etqDigitosOp2.setText("");
        tfOp3.clear();
        tfOp3.setPromptText("");
        etqDigitosOp3.setText("");
        tfResultado.clear();
        etqDigitosResultado.setText("");
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
        String op2 = tfOp2.getText();
        String resul = tfResultado.getText();
        
        if(op2.contains("-"))
            op2 = ("(" + op2 + ")");
        
        String historial;
        if(tfOp3.getText().equals(""))
            historial = ("MCD( " + op1 + " , " + op2 + " ) = " + resul + "\n\n");
        else 
        {
            String op3 = tfOp3.getText();
            historial = ("MCD( " + op1 + " , " + op2 + " , " + op3 + " ) = " + resul + "\n\n");
        }
        
        pc.textoHistorial.setText(historial + pc.textoHistorial.getText()); 
    }
    
    private void establecerResultado(String op1, String op2, String resul){
        op1 = validar.separadorMiles(op1, this.base);
        tfOp1.setText(op1.toUpperCase());
        op2 = validar.separadorMiles(op2, base);
        tfOp2.setText(op2.toUpperCase());
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
                
        // Primero se comprueba si los operandos son nulos
        enc = (comprobacionNulos(tfOp1) & comprobacionNulos(tfOp2));
        if(enc)
        {
            // Si los operandos no son nulos, se valida el formato
            String op1 = tfOp1.getText();
            String op2 = tfOp2.getText();           
            op1 = validar.formato(op1);
            op2 = validar.formato(op2);
            
            // Comprobar si son números correctos
            enc = (comprobacionNumeros(tfOp1, op1) & comprobacionNumeros(tfOp2, op2));
            if(enc) // Valores correctos
            {
                if(this.base == 10)
                {
                    op1 = validar.comaAPunto(op1);
                    op2 = validar.comaAPunto(op2);
                }
                // Comprobar que son números enteros
                enc = (comprobacionDecimales(tfOp1, op1) & comprobacionDecimales(tfOp2, op2));
                if(enc)
                {
                    if(auxOp3()) // Op3 activo
                    {
                        // Comprobar si el op3 es nulo
                        enc = comprobacionNulos(tfOp3);
                        if(enc)
                        {
                            // Validar formato
                            String op3 = tfOp3.getText();
                            op3 = validar.formato(op3);
                            
                            // Comprobar si es un número correcto
                            enc = (comprobacionNumeros(tfOp3, op3));
                            if(enc)
                            {
                                if(this.base == 10)
                                    op3 = validar.comaAPunto(op3);
                                
                                // Comprobar que es un número entero
                                enc = comprobacionDecimales(tfOp3, op3);
                                if(enc) // Op3 correcto
                                {
                                    String r_aux = mcdl.mcd(op1, op2);
                                    resul = mcdl.mcd(r_aux, op3);
                                    op3 = validar.separadorMiles(op3, this.base);
                                    tfOp3.setText(op3.toUpperCase());
                                }
                                else
                                    error("Para calcular el MCD los operandos tienen que ser números enteros.");
                            }
                            else
                                error("Valor incorrecto.");
                        } 
                    }
                    else // Op3 inactivo
                        resul = mcdl.mcd(op1, op2);
                }
                else
                    error("Para calcular el MCD los operandos tienen que ser números enteros.");
            }
            else
                error("Valor incorrecto.");
            
            if(enc)
                establecerResultado(op1, op2, resul);
        }
    }  
}