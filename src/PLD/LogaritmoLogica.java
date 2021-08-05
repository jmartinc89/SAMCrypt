package PLD;

import PollardRho.PollardRhoPLD;
import java.math.BigInteger;
import javafx.application.Platform;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class LogaritmoLogica {
    
    private final Validacion validar;
    private final LogaritmoController lc;
    private final PrincipalController pc;
    private int base;
    private long tiempoIni;
    private long tiempoFin;
    
    // Para Continuar
    private String resultado;
    public static boolean isCancelled;

    
    public LogaritmoLogica(){
        validar = new Validacion();
        pc = fxmlLoader.getController();
        lc = pc.getFlLogaritmo().getController();
    }
    
    public void setBase(int base){
        this.base = base;
    }
      
    public void setIsCancelled(boolean value){
        LogaritmoLogica.isCancelled = value;
    }
    
    
    public void start(String g, String y, String m){
        resultado = "";
        this.tiempoIni = System.currentTimeMillis();
        this.logaritmo(g, y, m);
        establecerResultado(g, y, m, this.resultado);        
        escribirHistorial();
    }

    // Logarimto discreto.
    public void logaritmo(String op1, String op2, String modulo){
        PollardRhoPLD pf = new PollardRhoPLD();
        BigInteger a = new BigInteger(op1, this.base);
        BigInteger b = new BigInteger(op2, this.base);
        BigInteger p = new BigInteger(modulo, this.base);
        
        BigInteger resul = pf.logarithm(a, b, p);
        BigInteger error = new BigInteger("-1");
        if(resul.equals(error))
        {
            Platform.runLater(() -> {
            LogaritmoLogica.isCancelled = true;
            lc.getBotonBorrar().setDisable(false);
            lc.getBotonIgual().setDisable(false);
            lc.getBotonDetener().setVisible(false);
            lc.getTfX().setText("No se ha encontrado solución");
            lc.getIndProgreso().setVisible(false);
            });
        }
        else
            this.resultado = resul.toString(this.base).toUpperCase();
    }
    
    public void establecerResultado(String g, String y, String m, String resul){
        g = validar.separadorMiles(g, this.base);
        lc.getTfBase().setText(g.toUpperCase());
        y = validar.separadorMiles(y, base);
        lc.getTfY().setText(y.toUpperCase());
        m = validar.separadorMiles(m, base);
        lc.getTfMod().setText(m.toUpperCase());
        resul = validar.separadorMiles(resul, base);
        this.resultado = resul.toUpperCase();
        lc.setResultado(this.resultado);
        lc.getTfX().setText(this.resultado);
        Platform.runLater(() -> lc.longitudResultado(this.base));
    }
    
    public void escribirHistorial(){
        String g = lc.getTfBase().getText();
        String y = lc.getTfY().getText();
        String m = lc.getTfMod().getText();
        String x = lc.getTfX().getText();

        this.tiempoFin = System.currentTimeMillis();
        String historial = (g + " ^( " + x + " ) = " + y + " mod " + m + " (" +
                validar.millisToTime(this.tiempoFin - this.tiempoIni) +")\n\n");
        
        pc.textoHistorial.setText(historial + pc.textoHistorial.getText()); 
    }
}