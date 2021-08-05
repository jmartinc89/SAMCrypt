package samcript;

import PFE.FactorizacionLogica;
import PLD.LogaritmoLogica;
import Potencia.PotenciaLogica;
import RaizPrimitiva.RaizPrimitivaLogica;
import java.io.IOException;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SamCript extends Application {
    
    public static FXMLLoader fxmlLoader; // Resultado de las operaciones en el historial
    private Stage preStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preStage = primaryStage;
        // Interfaz de carga
        FXMLLoader fxmlCarga = new FXMLLoader();
        fxmlCarga.setLocation(getClass().getResource("/Carga/Carga.fxml"));
        Parent rootPre = fxmlCarga.load();
        
        //Establece dimensiones, fondo transparentes sin marcos
        preStage.initStyle(StageStyle.UNDECORATED);
        preStage.getIcons().add(new Image("/Imagenes/Logo.PNG"));   //Icono
        Scene scenePre = new Scene(rootPre);
        scenePre.setFill(Color.WHITESMOKE);

        preStage.setScene(scenePre);   //Establece el contenido
        preStage.setResizable(false);  //No dimensionable
        preStage.show();               //Muestra la Ventana
        
        //Concurrencia para Mostrar la pantalla de carga y esperar 1200 ms hasta que aparezca el panel principal
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        
        //Cuando termina Thread.sleep() establece la nueva ventana
        sleeper.setOnSucceeded((WorkerStateEvent event) -> {
            try{
                preStage.hide();
                
                // Carga la interfaz de inicio
                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Principal.fxml"));
                Parent root = fxmlLoader.load();
                
                Stage stage = new Stage();
                Scene scene = new Scene(root, 1000, 600);
                stage.setScene(scene);
                stage.setResizable(true);
                stage.setTitle("SAMCript - Software de Aritmética Modular para Criptografía");
                stage.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
                stage.show();
                
                // Cierra un Thread al cerrar la ventana
                stage.setOnCloseRequest(closeEvent -> {
                    FactorizacionLogica.isCancelled = true;
                    RaizPrimitivaLogica.isCancelled = true;
                    LogaritmoLogica.isCancelled = true;
                    PotenciaLogica.isCancelled = true;
                });
                
            } catch(IOException e){}
        });
        new Thread(sleeper).start();
    }

    public static void main(String[] args){
        launch(args);
    }
}