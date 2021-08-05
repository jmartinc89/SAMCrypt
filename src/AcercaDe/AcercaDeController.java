package AcercaDe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AcercaDeController implements Initializable {

    @FXML
    private AnchorPane panelBase;
    @FXML
    private Button aceptar;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
    }    

    @FXML
    private void cerrar(ActionEvent event) {
        Stage stage = (Stage)panelBase.getScene().getWindow();
        stage.close();
    }
}