package PLD;

import java.math.BigInteger;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class LogaritmoController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqBase;
    @FXML
    private TextField tfBase;
    @FXML
    private Label etqDigitosBase;
    @FXML
    private Label etqY;
    @FXML
    private TextField tfY;
    @FXML
    private Label etqDigitosY;
    @FXML
    private Label etqModulo;
    @FXML
    private TextField tfModulo;
    @FXML
    private Label etqDigitosMod;
    @FXML
    private Label etqResul;
    @FXML
    private TextField tfX;
    @FXML
    private MenuButton botonUniResul;
    @FXML
    private MenuItem resultadoDecimal;
    @FXML
    private MenuItem resultadoBinario;
    @FXML
    private MenuItem resultadoHexadecimal;
    @FXML
    private Label etqDigitosX;
    @FXML
    private Button botonBorrar;
    @FXML
    private Button botonIgual;
    @FXML
    private ProgressIndicator indProgreso;
    @FXML
    private Button botonStopCont;
    
    private PrincipalController pc;
    private LogaritmoLogica ll;
    private Validacion validar;
    private int base;
    private String resultado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = fxmlLoader.getController();
        ll = new LogaritmoLogica();
        validar = new Validacion();
                
        this.base = pc.getBase();
        ll.setBase(this.base);  
        
        indProgreso.setVisible(false);
        botonUniResul.setVisible(false);
        botonStopCont.setVisible(false);        
        botonStopCont.setText("Detener");
        
        switch(this.base)
        {
            case 10:
                validar.validacionEntradaEnteros(tfBase);
                validar.validacionEntradaEnteros(tfY);
                validar.validacionEntradaEnteros(tfModulo);
                break;
            case 2:
                validar.validacionEntradaBinario(tfBase);
                validar.validacionEntradaBinario(tfY);
                validar.validacionEntradaBinario(tfModulo);
                break;
            case 16:
                validar.validacionEntradaHexadecimal(tfBase);
                validar.validacionEntradaHexadecimal(tfY);
                validar.validacionEntradaHexadecimal(tfModulo);
                break;
        }
    }    

    @FXML
    private void longitudOp1(KeyEvent event) {
        tfBase.setStyle("-fx-text-inner-color: black;");
        tfX.clear();
        etqDigitosX.setText("");
        
        botonUniResul.setVisible(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        
        String texto = tfBase.getText();
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
        etqDigitosBase.setText(resul); 
    }

    @FXML
    private void longitudOp2(KeyEvent event) {
        tfY.setStyle("-fx-text-inner-color: black;");
        tfX.clear();
        etqDigitosX.setText("");
        
        botonUniResul.setVisible(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        
        String texto = tfY.getText();
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
        etqDigitosY.setText(resul); 
    }

    @FXML
    private void longitudMod(KeyEvent event) {
        tfModulo.setStyle("-fx-text-inner-color: black;");
        tfX.clear();
        etqDigitosX.setText("");
        
        botonUniResul.setVisible(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        
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
        etqDigitosMod.setText(resul); 
    }

    public void longitudResultado(int b) {
        String texto = tfX.getText();
        String resul = "";
        
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
        etqDigitosX.setText(resul);
        
        botonUniResul.setVisible(true);
    }    
    
    
    @FXML
    private void resultadoDecimal(ActionEvent event) {
        //digitos = true;
        botonUniResul.setText("D");
        // Transforma el resultado en decimal.
        if(base != 10)
        {
            BigInteger b1 = new BigInteger(resultado, base); // Lo pasa a decimal
            String r = b1.toString(10);
            r = validar.separadorMiles(r, 10);
            tfX.setText(r.toUpperCase());
            longitudResultado(10);
        }
        else
        {
            tfX.setText(resultado);
            String resul = validar.longitudDecimal(resultado);
            etqDigitosX.setText(resul);
        }
    }

    @FXML
    private void resultadoBinario(ActionEvent event) {
        //digitos = true;
        botonUniResul.setText("B");
        // Transforma el resultado en binario.
        String aux;
        if(base == 10)
            aux = validar.formato(resultado);
        else
            aux = resultado;

        BigInteger b1 = new BigInteger(aux, base); // Lo pasa a binario
        String r = b1.toString(2);

        tfX.setText(r.toUpperCase());
        longitudResultado(2);
    }

    @FXML
    private void resultadoHexadecimal(ActionEvent event) {
        //digitos = true;
        botonUniResul.setText("H");
        // Transforma el resultado en hexadecimal.
        String aux;
        if(base == 10)
            aux = validar.formato(resultado);
        else
            aux = resultado;

        BigInteger b1 = new BigInteger(aux, base); // Lo pasa a hexadecimal
        String r = b1.toString(16);

        tfX.setText(r.toUpperCase());
        longitudResultado(16);
    }

    @FXML
    private void borrar(ActionEvent event) {
        tfBase.clear();
        tfBase.setPromptText("");
        etqDigitosBase.setText("");
        tfY.clear();
        tfY.setPromptText("");
        etqDigitosY.setText("");
        tfModulo.clear();
        tfModulo.setPromptText("");
        etqDigitosMod.setText("");
        tfX.clear();
        etqDigitosX.setText("");
        
        botonUniResul.setVisible(false);  
        botonIgual.setDisable(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        ll.setIsCancelled(true);
        //stopCont = true; 
    }
    
    @FXML
    private void stopCont(ActionEvent event) {
        Task ThStop = new Task(){
            @Override
            protected Object call() throws Exception{
                Platform.runLater(() -> {
                    botonIgual.setDisable(false);
                    botonStopCont.setVisible(false);
                    botonBorrar.setDisable(false);
                    indProgreso.setVisible(false);
                });
                ll.setIsCancelled(true);
                return (null);
            }
        };
        new Thread(ThStop).start();     
    }
    
    @FXML
    private void resultado(ActionEvent event) {
        boolean enc;
        
        // Comrpobar si los operandos son nulos
        enc = (comprobacionNulos(tfBase) && comprobacionNulos(tfY)
               && comprobacionNulos(tfModulo));
        if(enc)
        {
            // Si no son nulos, validar el formato
            String G = tfBase.getText();
            G = validar.formato(G);
            String Y = tfY.getText();
            Y = validar.formato(Y);
            String M = tfModulo.getText();
            M = validar.formato(M);
            
            // Comprobar si son números correctos
            enc = (comprobacionNumeros(tfBase, G) && comprobacionNumeros(tfY, Y)
                    && comprobacionNumeros(tfModulo, M));
            if(enc) // Valor correcto
            {
                // G, Y, M tienes que ser enteros y positivos
                enc = (comprobacionDecimales(tfBase, G) && comprobacionPositivos(tfBase, G)
                        && comprobacionDecimales(tfY, Y) && comprobacionPositivos(tfY, Y)
                        && comprobacionDecimales(tfModulo, M) && comprobacionPositivos(tfModulo, M));
                if(enc) // Números enteros
                {
                    BigInteger modulo = new BigInteger(M, this.base);
                    if(modulo.isProbablePrime(100))
                    {
                        tfBase.setText(G);
                        tfY.setText(Y);
                        tfModulo.setText(M);
                        Task ThStart = new Task(){
                            @Override
                            protected Object call() throws Exception{
                                //stopCont = true;
                                ll.setIsCancelled(false);
                                Platform.runLater(() -> {
                                    botonIgual.setDisable(true);
                                    botonStopCont.setVisible(true);
                                    botonStopCont.setDisable(false);
                                    botonBorrar.setDisable(true);
                                    indProgreso.setVisible(true);
                                });                     
                                ll.start(tfBase.getText(), tfY.getText(), tfModulo.getText());
                                // Si termina por éxito
                                Platform.runLater(() -> {
                                    botonIgual.setDisable(false);
                                    botonStopCont.setVisible(false);
                                    botonBorrar.setDisable(false);
                                    indProgreso.setVisible(false);
                                    botonUniResul.setVisible(true);
                                });                
                                return (null);
                            }
                        };
                        new Thread(ThStart).start();
                    }
                    else
                    {
                        tfModulo.setStyle("-fx-text-inner-color: red;");
                        error("El módulo tiene que ser un número primo");
                        tfX.setText("");
                    }
                }
                else
                {
                    //tfNumero.setStyle("-fx-text-inner-color: red;");
                    error("Los operandos tienen que ser números enteros y positivos.");
                    tfX.setText("");
                }
            }
            else
                error("Valor incorrecto.");
        }       
    }
    
    
    private boolean comprobacionNulos(TextField tf){
        boolean enc = true;
        if(tf.getText().equals(""))
        {
            enc = false;
            tf.setPromptText("Introduce un valor");
            tfX.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionNumeros(TextField tf, String num){
        boolean enc = true;
        if(!validar.isNumber(num, this.base))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfX.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionDecimales(TextField tf, String num){
        boolean enc = true;
        if(num.contains(","))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfX.setText("");
        }
        return(enc);
    }
    
    private boolean comprobacionPositivos(TextField tf, String num){
        boolean enc = true;
        if(num.compareTo("0") <= 0)
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfX.setText("");
        }
        return(enc);
    }
    
    private void error(String t){
        Alert datos = new Alert(Alert.AlertType.ERROR);
        datos.setTitle("Caracter inválido");
        datos.setHeaderText(null);
        datos.setContentText(t);
        ((Stage) datos.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Imagenes/Logo1.PNG"));
        Optional<ButtonType> ventana = datos.showAndWait();      
    }  
    
    
    public TextField getTfBase(){
        return(this.tfBase);
    }
    
    public TextField getTfY(){
        return(this.tfY);
    }
    
    public TextField getTfMod(){
        return(this.tfModulo);
    }
    
    public TextField getTfX(){
        return(this.tfX);
    }
    
    public Button getBotonBorrar(){
        return(this.botonBorrar);
    }
    
    public Button getBotonIgual(){
        return(this.botonIgual);
    }
    
    public Button getBotonDetener(){
        return(this.botonStopCont);
    }
    
    public ProgressIndicator getIndProgreso(){
        return(this.indProgreso);
    }
    
    public void setResultado(String resultado){
        this.resultado = resultado;
    }
}