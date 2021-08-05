package RaizPrimitiva;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class RaizPrimitivaController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqNumero;
    @FXML
    private TextField tfNumero;
    @FXML
    private Label etqDigitosNum;
    @FXML
    private Label etqGenerador;
    @FXML
    private TextArea tfGenerador;
    @FXML
    private ProgressIndicator indProgreso;
    @FXML
    private MenuButton botonUniResultado;
    @FXML
    private MenuItem resultadoDecimal;
    @FXML
    private MenuItem resultadoBinario;
    @FXML
    private MenuItem resultadoHexadecimal;
    @FXML
    private Button botonBorrar;
    @FXML
    private Button botonIgual;
    @FXML
    private Button botonStopCont;

    private PrincipalController pc;
    private RaizPrimitivaLogica rpl;
    private Validacion validar;  
    private int base;
    private boolean stopCont;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pc = fxmlLoader.getController();
        rpl = new RaizPrimitivaLogica();
        validar = new Validacion();
        
        this.base = pc.getBase();
        rpl.setBase(this.base);
        stopCont = true;
        
        indProgreso.setVisible(false);
        botonStopCont.setVisible(false);
        
        botonUniResultado.setVisible(false);
        establecerBotonUniResultado();
    }    

    @FXML
    private void longitudOp1(KeyEvent event) {
        tfNumero.setStyle("-fx-text-inner-color: black;");
        tfGenerador.setText("");
        
        botonUniResultado.setVisible(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        
        String texto = tfNumero.getText();
        String resul = "";
        
        switch (this.base)
        {
            case 10:
                resul = validar.longitudDecimal(texto);
                validar.validacionEntradaEnteros(tfNumero);
                break;
            case 2:
                resul = validar.longitudBinario(texto);
                validar.validacionEntradaBinario(tfNumero);
                break;
            case 16:
                resul = validar.longitudHexadecimal(texto);
                validar.validacionEntradaHexadecimal(tfNumero);
                break;
        }
        etqDigitosNum.setText(resul);      
    }

    @FXML
    private void resultadoDecimal(ActionEvent event) {
        Task t = new Task(){
            @Override
            protected Object call(){
                Platform.runLater(() -> {
                    indProgreso.setVisible(true);
                    botonUniResultado.setText("Decimal");
                });
                rpl.imprimirResultado(rpl.getResul(), 10);
                Platform.runLater(() -> indProgreso.setVisible(false));
                
                return(null);
            }
        }; new Thread(t).start();     
    }

    @FXML
    private void resultadoBinario(ActionEvent event) {        
        Task t = new Task(){
            @Override
            protected Object call(){
                Platform.runLater(() -> {
                    indProgreso.setVisible(true);
                    botonUniResultado.setText("Binario");
                });
                rpl.imprimirResultado(rpl.getResul(), 2);
                Platform.runLater(() -> indProgreso.setVisible(false));
                
                return(null);
            }
        }; new Thread(t).start();        
    }

    @FXML
    private void resultadoHexadecimal(ActionEvent event) {
        Task t = new Task(){
            @Override
            protected Object call(){
                Platform.runLater(() -> {
                    indProgreso.setVisible(true);
                    botonUniResultado.setText("Hexadecimal");
                });
                rpl.imprimirResultado(rpl.getResul(), 16);
                Platform.runLater(() -> indProgreso.setVisible(false));
                
                return(null);
            }
        }; new Thread(t).start();  
    }

    @FXML
    private void borrar(ActionEvent event) {
        tfNumero.clear();
        tfNumero.setPromptText("");
        etqDigitosNum.setText("");
        tfGenerador.clear();
        botonUniResultado.setVisible(false);  
        botonIgual.setDisable(false);
        botonStopCont.setVisible(false);
        indProgreso.setVisible(false);
        rpl.setIsCancelled(true);
        stopCont = true;
    }
    
    @FXML
    private void stopCont(ActionEvent event) throws InterruptedException {
        establecerBotonUniResultado();
        if(stopCont) // boton Detener
        {
            Task ThStop = new Task(){
                @Override
                protected Object call() throws Exception{
                    stopCont = false;
                    Platform.runLater(() -> {
                        botonIgual.setDisable(true);
                        botonStopCont.setText("Continuar");
                        botonBorrar.setDisable(false);
                        indProgreso.setVisible(false);
                    });
                    rpl.setIsCancelled(true);
                    return (null);
                }
            };
            new Thread(ThStop).start();
        }
        else // boton Continuar
        {
            Task ThCont = new Task(){
                @Override
                protected Object call() throws Exception{
                    stopCont = true;
                    Platform.runLater(() -> {
                        botonIgual.setDisable(true);
                        botonStopCont.setText("Detener");
                        botonBorrar.setDisable(true);
                        indProgreso.setVisible(true);
                        botonUniResultado.setVisible(false);
                    });
                    rpl.setIsCancelled(false);
                    rpl.continueRP();
                    // Si termina por éxito
                    Platform.runLater(() -> {
                        botonIgual.setDisable(false);
                        botonStopCont.setVisible(false);
                        botonBorrar.setDisable(false);
                        indProgreso.setVisible(false);
                        botonUniResultado.setVisible(true);
                    });           
                    return (null);
                }
            };
            new Thread(ThCont).start();
        }
    }

    @FXML
    private void resultado(ActionEvent event){
        boolean enc;
        establecerBotonUniResultado();
        tfGenerador.setText("");
            
        // Comrpobar si el operando es nulo
        enc = comprobacionNulos(tfNumero);
        if(enc)
        {
            // Si no es nulo, validar el formato
            String op = tfNumero.getText();
            op = validar.formato(op);
            
            // Comprobar si es un número correcto
            enc = comprobacionNumeros(tfNumero, op);
            if(enc) // Valor correcto
            {
                // El número tiene que ser un número primo          
                BigInteger primo = new BigInteger(op, this.base);
                if(primo.isProbablePrime(100))
                {
                    tfNumero.setText(op);
                    Task ThStart = new Task(){
                        @Override
                        protected Object call() throws Exception{
                            stopCont = true;
                            rpl.setIsCancelled(false);
                            Platform.runLater(() -> {
                                botonIgual.setDisable(true);
                                botonStopCont.setVisible(true);
                                botonStopCont.setText("Detener");
                                botonStopCont.setDisable(false);
                                botonBorrar.setDisable(true);
                                indProgreso.setVisible(true);
                            });                     
                            rpl.start(tfNumero.getText());
                            if(!RaizPrimitivaLogica.isCancelled)
                            {
                                // Si termina por éxito
                                Platform.runLater(() -> {
                                    botonIgual.setDisable(false);
                                    botonStopCont.setVisible(false);
                                    botonStopCont.setText("Detener");
                                    botonBorrar.setDisable(false);
                                    indProgreso.setVisible(false);
                                    botonUniResultado.setVisible(true);
                                });   
                            }
                            return (null);
                        }
                    };
                    new Thread(ThStart).start();
                }
                else
                {
                    tfNumero.setStyle("-fx-text-inner-color: red;");
                    error("El operando tiene que ser un número primo.");
                    tfGenerador.setText("");
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
            tfGenerador.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionNumeros(TextField tf, String num){
        boolean enc = true;
        if(!validar.isNumber(num, this.base))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfGenerador.setText("");
        }
        return(enc);        
    }
    
    private boolean comprobacionDecimales(TextField tf, String num){
        boolean enc = true;
        if(num.contains(","))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfGenerador.setText("");
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
       
    private void establecerBotonUniResultado(){
        switch (this.base)
        {
            case 10:
                botonUniResultado.setText("Decimal");
                break;
            case 2:
                botonUniResultado.setText("Binario");
                break;
            default:
                botonUniResultado.setText("Hexadecimal");
                break;
        }
    }    
    
    public TextField getTfNumero(){
        return(this.tfNumero);
    }
     
    public TextArea getGenerador(){
        return(this.tfGenerador);
    }
}