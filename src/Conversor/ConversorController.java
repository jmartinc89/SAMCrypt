package Conversor;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import validacion.Validacion;

public class ConversorController implements Initializable {

    public Button aceptar;
    @FXML
    private TextField tfDecimal;
    @FXML
    private TextField tfBinario;
    @FXML
    private TextField tfHexadecimal;
    
    private Validacion validar;
    @FXML
    private Button borrar;
    @FXML
    private AnchorPane panelBase;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validar = new Validacion();
        
        validar.validacionEntradaDecimal(tfDecimal);
        validar.validacionEntradaBinario(tfBinario);
        validar.validacionEntradaHexadecimal(tfHexadecimal);
    }        

    @FXML
    private void decimal(KeyEvent event) {
        
        if(validar.isNumber(tfDecimal.getText(), 10))
        {
            String num = validar.formato(tfDecimal.getText());
            if(num.contains(",") || num.compareTo("0") < 0) // Sólo convierte a b/h si el número es entero positivo
            {
                tfBinario.setText("");
                tfHexadecimal.setText("");
            }
            else
            {
                tfBinario.setText("");
                tfHexadecimal.setText("");
                            
                BigInteger bin = new BigInteger(num, 10);
                tfBinario.setText(bin.toString(2)); // Lo pasa a binario
                
                BigInteger hex = new BigInteger(num, 10);
                tfHexadecimal.setText(hex.toString(16).toUpperCase()); // Lo pasa a hexadecimal
            }
        }
        else
        {
            tfBinario.setText("");
            tfHexadecimal.setText("");
        }
    }

    @FXML
    private void binario(KeyEvent event) {
        
        if(validar.isNumber(tfBinario.getText(), 2))
        {
            String num = validar.formato(tfBinario.getText());
            
            BigInteger dec = new BigInteger(num, 2);
            tfDecimal.setText(dec.toString(10)); // Lo pasa a decimal
            
            BigInteger hex = new BigInteger(num, 2);
            tfHexadecimal.setText(hex.toString(16).toUpperCase()); // Lo pasa a hexadecimal
        }
        else
        {
            tfDecimal.setText("");
            tfHexadecimal.setText("");
        }        
    }

    @FXML
    private void hexadecimal(KeyEvent event) {
        if(validar.isNumber(tfHexadecimal.getText(), 16))
        {
            String num = validar.formato(tfHexadecimal.getText());
            
            BigInteger dec = new BigInteger(num, 16);
            tfDecimal.setText(dec.toString(10)); // Lo pasa a decimal
            
            BigInteger bin = new BigInteger(num, 16);
            tfBinario.setText(bin.toString(2).toUpperCase()); // Lo pasa a binario
        }
        else
        {
            tfDecimal.setText("");
            tfBinario.setText("");
        }         
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Stage stage = (Stage)panelBase.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void borrar(ActionEvent event) {
        tfDecimal.clear();
        tfBinario.clear();
        tfHexadecimal.clear();
    }
}