package samcript;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import validacion.Validacion;

public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane panelAnchorBase;
    @FXML
    public CheckMenuItem unidadesDecimal;
    @FXML
    public CheckMenuItem unidadesBinario;
    @FXML
    public CheckMenuItem unidadesHexadecimal;
    @FXML
    public TextArea textoHistorial;
    @FXML
    public StackPane panelStack;
    
    public int base; 
    private Validacion validar;    
    private int operacion;   
    //Suma=1; Resta=2; Multipliacion=3; Division=4; Raiz cuadrada=5;
    //Raiz primitiva=6; XOR=7; Inverso=8; Potencia=9; Modul0=10;
    //MCM=11; mcm=12; Primalidad=13; Factorizacion=14; Logaritmo=15;

    public static FXMLLoader flFact;
    public static FXMLLoader flRaizP;
    public static FXMLLoader flPotencia;
    public static FXMLLoader flLog;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operacion = 0;
        textoHistorial.setText("");
        unidadesDecimal.setSelected(true);
        this.base = 10;
        validar = new Validacion();
    }    

    // Menú Archivo
    @FXML
    private void cerrarKey(ActionEvent event) {
        Stage stage = (Stage)panelAnchorBase.getScene().getWindow();
        stage.close();
    }

    // Menú Unidades
    private void cambioUnidad(ActionEvent event){
        switch(operacion)
        {
            case 1: sumar(event);
                break;
            case 2: restar(event);
                break;
            case 3: multiplicar(event);
                break;
            case 4: dividir(event);
                break;
            case 5: raiz(event);
                break;
            case 6: raizPrimitiva(event);
                break;
            case 7: XOR(event);
                break;
            case 8: inverso(event);
                break;
            case 9: potencia(event);
                break;
            case 10: modulo(event);
                break;
            case 11: mcd(event);
                break;
            case 12: mcm(event);
                break;
            case 13: primalidad(event);
                break;
            case 14: factorizacion(event);
                break;
            case 15: logaritmo(event);
                break;
        }
    }
    
    @FXML
    private void decimalKey(ActionEvent event) {
        unidadesDecimal.setSelected(true);
        unidadesBinario.setSelected(false);
        unidadesHexadecimal.setSelected(false);
        this.base = 10;
        cambioUnidad(event);
    }

    @FXML
    private void binarioKey(ActionEvent event) {
        unidadesBinario.setSelected(true);
        unidadesDecimal.setSelected(false);
        unidadesHexadecimal.setSelected(false);
        this.base = 2;
        cambioUnidad(event);
    }

    @FXML
    private void hexadecimalKey(ActionEvent event) {
        unidadesHexadecimal.setSelected(true);
        unidadesDecimal.setSelected(false);
        unidadesBinario.setSelected(false);
        this.base = 16;
        cambioUnidad(event);
    }

    // Menú Operaciones
    @FXML
    private void sumaKey(ActionEvent event) {
        sumar(event);
    }

    @FXML
    private void restaKey(ActionEvent event) {
        restar(event);
    }

    @FXML
    private void multiplicacionKey(ActionEvent event) {
        multiplicar(event);
    }

    @FXML
    private void divisionKey(ActionEvent event) {
        dividir(event);
    }
    
    @FXML
    private void raizCuadradaKey(ActionEvent event) {
        raiz(event);
    }

    @FXML
    private void raizPrimitivaKey(ActionEvent event) {
        raizPrimitiva(event);
    }    
    
    @FXML
    private void XORKey(ActionEvent event) {
        XOR(event);
    }
    
    @FXML
    private void inversoKey(ActionEvent event) {
        inverso(event);
    }
    
    @FXML
    private void potenciaKey(ActionEvent event) {
        potencia(event);
    }

    @FXML
    private void moduloKey(ActionEvent event) {
        modulo(event);
    }

    @FXML
    private void mcdKey(ActionEvent event) {
        mcd(event);
    }

    @FXML
    private void mcmKey(ActionEvent event) {
        mcm(event);
    }

    @FXML
    private void primalidadKey(ActionEvent event) {
        primalidad(event);
    }

    @FXML
    private void pfeKey(ActionEvent event) {
        factorizacion(event);
    }

    @FXML
    private void pldKey(ActionEvent event) {
        logaritmo(event);
    }

    // Menu Tablas
    @FXML
    private void tablaPrimos(ActionEvent event) {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Tablas/TablaPrimos.fxml"));
            root = fxmlLoader.load();
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setTitle("Tabla de números primos");
            stg.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
            stg.setScene(new Scene(root, 500, 370));
            stg.show();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tablaPrimosSeguros(ActionEvent event) {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Tablas/TablaPrimosSeguros.fxml"));
            root = fxmlLoader.load();
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setTitle("Tabla de números primos seguros");
            stg.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
            stg.setScene(new Scene(root, 500, 370));
            stg.show();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void tablaASCII(ActionEvent event) {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Tablas/TablaASCII.fxml"));
            root = fxmlLoader.load();
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setTitle("Tabla ASCII");
            stg.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
            stg.setScene(new Scene(root, 500, 370));
            stg.show();
 
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Menú Conversor
    @FXML
    private void conversor(ActionEvent event) {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Conversor/Conversor.fxml"));
            root = fxmlLoader.load();
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setTitle("Conversor");
            stg.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
            stg.setScene(new Scene(root, 500, 370));
            stg.show();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Menú Ayuda
    @FXML
    private void manualUsuarioKey(ActionEvent event) {
        try
        {
            File path = new File("Manual de usuario.pdf");
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {}
    }

    @FXML
    private void bancoPruebasKey(ActionEvent event) {
        try
        {
            File path = new File("Banco de pruebas.pdf");
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {}        
    }

    @FXML
    private void acercaDeKey(ActionEvent event) {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/AcercaDe/AcercaDe.fxml"));
            root = fxmlLoader.load();
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setTitle("Acerca de");
            stg.getIcons().add(new Image("/Imagenes/Logo1.PNG"));
            stg.setScene(new Scene(root, 500, 370));
            stg.show();
             
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    // Historial    
    @FXML
    private void borrarHistorial(ActionEvent event) {
        textoHistorial.clear();
    }

    // Operaciones
    @FXML
    private void sumar(ActionEvent event) {
        try{
            operacion = 1;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flSuma = new FXMLLoader();
            flSuma.setLocation(getClass().getResource("/Suma/Suma.fxml"));
            
            GridPane panel = flSuma.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());        
            
            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }
    
    @FXML
    private void restar(ActionEvent event) {
        try{
            operacion = 2;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flResta = new FXMLLoader();
            flResta.setLocation(getClass().getResource("/Resta/Resta.fxml"));
            
            GridPane panel = flResta.load();
            // Asignar las mismas medidas al nuevo panel
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void multiplicar(ActionEvent event) {
        try{
            operacion = 3;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flMultip = new FXMLLoader();
            flMultip.setLocation(getClass().getResource("/Multiplicacion/Multiplicacion.fxml"));
            
            GridPane panel = flMultip.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void dividir(ActionEvent event) {
        try{
            operacion = 4;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flDivision = new FXMLLoader();
            flDivision.setLocation(getClass().getResource("/Division/Division.fxml"));
            
            GridPane panel = flDivision.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          
            
            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }
    
    @FXML
    private void raiz(ActionEvent event) {
        try{
            operacion = 5;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flRaizC = new FXMLLoader();
            flRaizC.setLocation(getClass().getResource("/RaizCuadrada/RaizCuadrada.fxml"));
            
            GridPane panel = flRaizC.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          
            
            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
            
        }catch(IOException e){}
    }
     
    @FXML
    private void raizPrimitiva(ActionEvent event) {
        try{
            operacion = 6;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            flRaizP = new FXMLLoader();
            flRaizP.setLocation(getClass().getResource("/RaizPrimitiva/RaizPrimitiva.fxml"));

            GridPane panel = flRaizP.load();        
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          
            
            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}        
    }

    @FXML
    private void XOR(ActionEvent event) {
        try{
            operacion = 7;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flXOR = new FXMLLoader();
            flXOR.setLocation(getClass().getResource("/XOR/XOR.fxml"));
            
            GridPane panel = flXOR.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          
            
            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}        
    }
   
    @FXML
    private void inverso(ActionEvent event) {
        try{
            operacion = 8;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flInverso = new FXMLLoader();
            flInverso.setLocation(getClass().getResource("/Inverso/Inverso.fxml"));
            
            GridPane panel = flInverso.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void potencia(ActionEvent event) {
        try{
            operacion = 9;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            flPotencia = new FXMLLoader();
            flPotencia.setLocation(getClass().getResource("/Potencia/Potencia.fxml"));
            
            GridPane panel = flPotencia.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
            
        }catch(IOException e){}
    }
    
    @FXML
    private void modulo(ActionEvent event) {
        try{
            operacion = 10;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flModulo = new FXMLLoader();
            flModulo.setLocation(getClass().getResource("/Modulo/Modulo.fxml"));
 
            GridPane panel = flModulo.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void mcd(ActionEvent event) {
        try{
            operacion = 11;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flMcd = new FXMLLoader();
            flMcd.setLocation(getClass().getResource("/MCD/Mcd.fxml"));
            
            GridPane panel = flMcd.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }
        
    @FXML
    private void mcm(ActionEvent event) {
        try{
            operacion = 12;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flMcm = new FXMLLoader();
            flMcm.setLocation(getClass().getResource("/Mcm/Mcm.fxml"));
            
            GridPane panel = flMcm.load();
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}        
    }
    
    @FXML
    private void primalidad(ActionEvent event) {
        try{
            operacion = 13;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            FXMLLoader flPrimalidad = new FXMLLoader();
            flPrimalidad.setLocation(getClass().getResource("/TestPrimalidad/Primalidad.fxml"));
            
            GridPane panel = flPrimalidad.load();        
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void factorizacion(ActionEvent event) {
        try{
            operacion = 14;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            flFact = new FXMLLoader();
            flFact.setLocation(getClass().getResource("/PFE/Factorizacion.fxml"));

            GridPane panel = flFact.load();        
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }

    @FXML
    private void logaritmo(ActionEvent event) {
        try{
            operacion = 15;
            
            // Limpiar el panel
            panelStack.getChildren().clear();
           
            // Cargar la nueva interfaz
            flLog = new FXMLLoader();
            flLog.setLocation(getClass().getResource("/PLD/Logaritmo.fxml"));

            GridPane panel = flLog.load();        
            panel.setBorder(panelStack.getBorder());
            panel.setMaxHeight(panelStack.getMaxHeight());
            panel.setMaxWidth(panelStack.getMaxWidth());
            panel.setMinHeight(panelStack.getMinHeight());
            panel.setMinWidth(panelStack.getMinWidth());
            panel.setPrefHeight(panelStack.getPrefHeight());
            panel.setPrefWidth(panelStack.getPrefWidth());          

            // Establecer el nuevo contenido
            panelStack.getChildren().setAll(panel);
        
        }catch(IOException e){}
    }
    
    public int getBase(){
        return(this.base);
    }
    
    public FXMLLoader getFlFact(){
        return(PrincipalController.flFact);
    }
    
    public FXMLLoader getFlRaizP(){
        return(PrincipalController.flRaizP);
    }
    
    public FXMLLoader getFlPotencia(){
        return(PrincipalController.flPotencia);
    }
    
    public FXMLLoader getFlLogaritmo(){
        return(PrincipalController.flLog);
    }
}