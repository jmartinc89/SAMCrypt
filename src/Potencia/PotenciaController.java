package Potencia;

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
import javafx.scene.control.CheckBox;
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

public class PotenciaController implements Initializable {

    @FXML
    private Label etqTitulo;
    @FXML
    private Label etqBase;
    @FXML
    private TextField tfBase;
    @FXML
    private Label etqDigitosBase;
    @FXML
    private Label etqExpo;
    @FXML
    private TextField tfExpo;
    @FXML
    private Label etqDigitosExpo;
    @FXML
    private CheckBox etqModulo;
    @FXML
    private TextField tfModulo;
    @FXML
    private Label etqDigitosMod;
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
    @FXML
    private ProgressIndicator indProgreso;
    @FXML
    private Button botonBorrar;
    @FXML
    private Button botonIgual;
    @FXML
    private Button botonDetener;

    private PrincipalController pc;
    private PotenciaLogica pl;
    private Validacion validar;
    private int base;
    private String resultado;
    
    private int tipo;
    private Thread thStart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfModulo.setDisable(true);
        etqDigitosMod.setDisable(true);
        
        pc = fxmlLoader.getController();
        validar = new Validacion();
        pl = new PotenciaLogica();
        
        this.base = pc.getBase();
        pl.setBase(this.base);
        
        botonUniResul.setVisible(false);
        indProgreso.setVisible(false);
        botonDetener.setVisible(false);
        
        switch(this.base)
        {
            case 10:
                validar.validacionEntradaDecimal(tfBase);
                validar.validacionEntradaEnteros(tfExpo);
                validar.validacionEntradaEnteros(tfModulo);
                break;
            case 2:
                validar.validacionEntradaBinario(tfBase);
                validar.validacionEntradaBinario(tfExpo);
                validar.validacionEntradaBinario(tfModulo);
                break;
            case 16:
                validar.validacionEntradaHexadecimal(tfBase);
                validar.validacionEntradaHexadecimal(tfExpo);
                validar.validacionEntradaHexadecimal(tfModulo);
                break;
        }
    }    

    private boolean auxModulo(){
        if(etqModulo.isSelected())
        {
            tfModulo.setDisable(false);
            etqDigitosMod.setDisable(false);
            return (true);
        }
        else
        {
            tfModulo.setDisable(true);
            tfModulo.clear();
            tfModulo.setPromptText("");
            etqDigitosMod.setDisable(true);
            etqDigitosMod.setText("");
            return (false);
        }
    }

    @FXML
    private void habilitarModulo(ActionEvent event) {
        auxModulo();
    }
    
    @FXML
    private void longitudOp1(KeyEvent event) {
        tfBase.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        etqDigitosResultado.setText("");
        
        botonUniResul.setVisible(false);
        botonDetener.setVisible(false);
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
        tfExpo.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        etqDigitosResultado.setText("");
        
        botonUniResul.setVisible(false);
        botonDetener.setVisible(false);
        indProgreso.setVisible(false);
        
        String texto = tfExpo.getText();
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
        etqDigitosExpo.setText(resul);
    }
    
    @FXML
    private void longitudMod(KeyEvent event) {
        tfModulo.setStyle("-fx-text-inner-color: black;");
        tfResultado.clear();
        etqDigitosResultado.setText("");
        
        botonUniResul.setVisible(false);
        botonDetener.setVisible(false);
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
        
        if(this.resultado.contains(",") || this.resultado.compareTo("0") < 0) 
        {
            resultadoBinario.setVisible(false);
            resultadoHexadecimal.setVisible(false);
        }        
        botonUniResul.setVisible(true);
    }

    
    @FXML
    private void resultadoDecimal(ActionEvent event) {
        botonUniResul.setText("D");
        //indProgreso.setVisible(true);
        // Transforma el resultado en decimal.
        if(this.base != 10)
        {
            BigInteger b1 = new BigInteger(this.resultado, this.base); // Lo pasa a decimal
            String r = b1.toString(10);
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
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b1 = new BigInteger(aux, this.base); // Lo pasa a binario
        String r = b1.toString(2);
        
        tfResultado.setText(r.toUpperCase());
        longitudResultado(2);
    }

    @FXML
    private void resultadoHexadecimal(ActionEvent event) {
        botonUniResul.setText("H");
        String aux;
        if(this.base == 10)
            aux = validar.formato(resultado);
        else
            aux = this.resultado;
        
        BigInteger b1 = new BigInteger(aux, this.base); // Lo pasa a hexadecimal
        String r = b1.toString(16);
        
        tfResultado.setText(r.toUpperCase());
        longitudResultado(16);
    }
    
    @FXML
    private void borrar(ActionEvent event) {
        tfBase.clear();
        tfBase.setPromptText("");
        etqDigitosBase.setText("");
        tfExpo.clear();
        tfExpo.setPromptText("");
        etqDigitosExpo.setText("");
        tfModulo.clear();
        tfModulo.setPromptText("");
        etqDigitosMod.setText("");
        tfResultado.clear();
        etqDigitosResultado.setText("");
        
        botonUniResul.setVisible(false);
        botonIgual.setDisable(false);
        botonDetener.setVisible(false);
        indProgreso.setVisible(false);
        pl.setIsCancelled(true);
    }

    @FXML
    private void detener(ActionEvent event) {
        thStart.stop();
        botonIgual.setDisable(false);
        botonDetener.setVisible(false);
        botonBorrar.setDisable(false);
        indProgreso.setVisible(false);
    }    

    @FXML
    private void resultado(ActionEvent event) {
        boolean enc;

        // Comprobar si los operando son nulos
        enc = (comprobacionNulos(tfBase) & comprobacionNulos(tfExpo));
        if(enc)
        {
            // Si la base y el exponente son correcto, se valida el formato
            String bs = tfBase.getText();
            String expo = tfExpo.getText();           
            bs = validar.formato(bs);
            expo = validar.formato(expo);
            
            // Comprobar si son números correctos
            enc = (comprobacionNumeros(tfBase, bs) & comprobacionNumeros(tfExpo, expo));
            if(enc) // Valores correctos
            {
                if(this.base == 10) // Si son números en Decimal, sustituir las comas por puntos
                {
                    bs = validar.comaAPunto(bs);
                    expo = validar.comaAPunto(expo);
                }
                
                // El exponente tiene que ser un número entero positivo
                enc = (comprobacionDecimales(tfExpo, expo) & (expo.compareTo("0")>=0));
                if(enc)
                {
                    if(auxModulo())
                    {
                        // Comprobar que la base es un entero
                        enc = comprobacionDecimales(tfBase, bs);
                        if(enc) //Son enteros
                        {
                            // Comprobar si el módulo es nulo
                            enc = comprobacionNulos(tfModulo);
                            if(enc)
                            {
                                String mod = tfModulo.getText();
                                mod = validar.formato(mod);
                                
                                // Comprobar que es un número
                                enc = comprobacionNumeros(tfModulo, mod);
                                if(enc)
                                {
                                    mod = validar.comaAPunto(mod);
                                    enc = (comprobacionDecimales(tfModulo, mod) & // No tiene decimales
                                            mod.compareTo("0")>0); // Es mayor que cero
                                    if(enc) // Módulo correcto
                                    {
                                        tipo = 1;
                                        tfBase.setText(bs);
                                        tfExpo.setText(expo);
                                        tfModulo.setText(mod);
                                        crearHilo();
                                    }
                                    else
                                        error("El módulo tiene que ser un número entero positivo mayor que 0.");
                                }
                            }
                        }
                        else
                            error("No se puede realizar el módulo de números decimales.");
                    }
                    else if(this.base == 10) // Módulo inactivo
                    {
                        tipo = 2;
                        tfBase.setText(bs);
                        tfExpo.setText(expo);
                        crearHilo();
                    }
                    else // Módulo inactivo
                    {
                        tipo = 3;
                        tfBase.setText(bs);
                        tfExpo.setText(expo);
                        crearHilo();
                    }
                }
                else
                    error("El exponente tiene que ser un número entero positivo.");
            }
            else
                error("Valor incorrecto.");
        }
    } 
     
    private void crearHilo(){    
        Task task = new Task(){
            @Override
            protected Object call() throws Exception{
                pl.setIsCancelled(false);
                Platform.runLater(() -> {
                    botonIgual.setDisable(true);
                    botonDetener.setVisible(true);
                    indProgreso.setVisible(true);
                    botonBorrar.setDisable(true);
                });
                
                switch (tipo) {
                    case 1:
                        pl.potenciaModulo(tfBase.getText(), tfExpo.getText(), tfModulo.getText());
                        break;
                    case 2:
                        pl.potenciaDecimal(tfBase.getText(), tfExpo.getText());
                        break;
                    default:
                        pl.potenciaEnteros(tfBase.getText(), tfExpo.getText());
                        break;
                }
                Platform.runLater(() -> {
                    botonIgual.setDisable(false);
                    botonDetener.setVisible(false);
                    botonBorrar.setDisable(false);
                    indProgreso.setVisible(false);
                    botonUniResul.setVisible(true);
                });
                return(null);
            }
        };
        thStart = new Thread(task);
        thStart.start();
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
        if(num.contains(","))
        {
            enc = false;
            tf.setStyle("-fx-text-inner-color: red;");
            tfResultado.setText("");
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
    
    public TextField getTfExpo(){
        return(this.tfExpo);
    }
    
    public TextField getTfMod(){
        return(this.tfModulo);
    }
    
    public TextField getTfResultado(){
        return(this.tfResultado);
    }
    
    public Button getBotonBorrar(){
        return(this.botonBorrar);
    }
    
    public Button getBotonIgual(){
        return(this.botonIgual);
    }
    
    public Button getBotonDetener(){
        return(this.botonDetener);
    }
    
    public ProgressIndicator getIndProgreso(){
        return(this.indProgreso);
    }

    public void setResultado(String resultado){
        this.resultado = resultado;
    }
}