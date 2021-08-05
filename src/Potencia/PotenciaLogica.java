package Potencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.application.Platform;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class PotenciaLogica {
    
    private final Validacion validar;
    private final PotenciaController powc;
    private final PrincipalController pc;
    private int base;
    
    private String resultado;
    public static boolean isCancelled;
    
    public PotenciaLogica(){
        validar = new Validacion();
        pc = fxmlLoader.getController();
        powc = pc.getFlPotencia().getController();
    }
  
    public void setBase(int base){
        this.base = base;
    }
    
    public void setIsCancelled(boolean value){
        PotenciaLogica.isCancelled = value;
    }
    
    
    // Exponenciación de números en decimal(enteros), binario y hexadecimal.
    public void potenciaEnteros(String b, String ex){
        BigInteger num1 = new BigInteger(b, this.base);
        BigInteger num2 = new BigInteger(ex, this.base);
        BigInteger resul = num1.pow(num2.intValue());
        // El exponente tiene que ser un número entero. 
        this.resultado = resul.toString(this.base);
       
        String bs = num1.toString(this.base);
        String expo = num2.toString(this.base);
        establecerResultado(bs, expo, this.resultado);
    }
    
    // Exponenciación de números en decimal(decimales).
    public void potenciaDecimal(String op1, String op2){
        BigDecimal num1 = new BigDecimal(op1);
        BigDecimal num2 = new BigDecimal(op2);
        BigDecimal resul = num1.pow(num2.intValue());
        this.resultado = resul.toString();
        this.resultado = validar.puntoAComa(this.resultado);
        
        String bs = num1.toString();
        bs = validar.puntoAComa(bs);
        String expo = num2.toString();
        establecerResultado(bs, expo, this.resultado);
    }
    
    // Exponenciación con módulo de números en decimal(enteros), binario y hexadecimal.
    public void potenciaModulo(String op1, String op2, String modulo){        
        BigInteger num1 = new BigInteger(op1, this.base);
        BigInteger num2 = new BigInteger(op2, this.base);
        BigInteger mod = new BigInteger(modulo, this.base);
                
        BigInteger resul = num1.modPow(num2, mod);
        this.resultado = resul.toString(this.base);

        String md = mod.toString(this.base);
        md = validar.separadorMiles(md, this.base);
        powc.getTfMod().setText(md);

        String bs = num1.toString(this.base);
        String expo = num2.toString(this.base);
        establecerResultado(bs, expo, this.resultado);
    }
    
    public void establecerResultado(String op1, String op2, String resul){
        op1 = validar.separadorMiles(op1, this.base);
        powc.getTfBase().setText(op1.toUpperCase());
        op2 = validar.separadorMiles(op2, base);
        powc.getTfExpo().setText(op2.toUpperCase());
        resul = validar.separadorMiles(resul, base);
        this.resultado = resul.toUpperCase();
        powc.setResultado(this.resultado);
        powc.getTfResultado().setText(this.resultado);
        Platform.runLater(() -> powc.longitudResultado(this.base)); 
        escribirHistorial();
    }
    
    public void escribirHistorial(){
        String op1 = powc.getTfBase().getText();
        String op2 = powc.getTfExpo().getText();
        String resul = powc.getTfResultado().getText();

        String historial;
        if(powc.getTfMod().getText().equals(""))
            historial = (op1 + " ^( " + op2 + " ) = " + resul + "\n\n");
        else 
        {
            String mod = powc.getTfMod().getText();
            historial = (op1 + " ^( " + op2 + " ) mod " + mod + " = " + resul + "\n\n");
        }
        
        pc.textoHistorial.setText(historial + pc.textoHistorial.getText()); 
    }
}