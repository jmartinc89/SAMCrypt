package Tablas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TablaASCIIController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button aceptar;
    @FXML
    private AnchorPane panelBase;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            ArrayList<String> ascii = this.tablaASCII();
            for(int i=0; i<ascii.size(); i++)
                textArea.setText(textArea.getText() + ascii.get(i) + "\n");
        } catch (IOException ex) {
            Logger.getLogger(TablaPrimosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<String> tablaASCII() throws IOException{
        File archivo = new File("ListaASCII.txt");   
        BufferedReader br = null;
        ArrayList<String> resul = new ArrayList<>();                      
        
        try {
            //BufferedReader con Formato de Lectura UTF-8 (Evita incompatibilidades)
            br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));
            
            String numero;   //String Auxiliar para leer los números
            while ((numero = br.readLine()) != null)
                    resul.add(numero);
        }
        catch(IOException e){}
        br.close();
                
        return(resul);
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Stage stage = (Stage)panelBase.getScene().getWindow();
        stage.close();         
    }
}