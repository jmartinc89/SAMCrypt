package PFE;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import javafx.application.Platform;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class FactorizacionLogica{
    
    private final Validacion validar;
    private final FactorizacionController fc;
    private final PrincipalController pc;
    private int base;
    private long tiempoIni;
    private long tiempoFin;
    
    // Para ContinuarPFE
    BigInteger divisor;
    BigInteger c;
    BigInteger x;
    BigInteger xx;
    BigInteger num;
    private ArrayList<BigInteger> resultado;
    public static boolean isCancelled;
    
    // POLLARD RHO
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();
    private final ArrayList<BigInteger> resul = new ArrayList<>();
    private final ArrayList<String> resulH = new ArrayList<>();
    
    
    public FactorizacionLogica(){
        validar = new Validacion();
        pc = fxmlLoader.getController();
        fc = pc.getFlFact().getController();
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    public void setIsCancelled(boolean value){
        FactorizacionLogica.isCancelled = value;
    }
    
        
    public void start(String op){
        BigInteger n = new BigInteger(op, this.base);
        this.resul.clear();
        this.resulH.clear();
        this.tiempoIni = System.currentTimeMillis();
        this.resultado = this.factor_start(n);
        establecerResultado();
        escribirHistorial();
    }
    
    public ArrayList<BigInteger> factor_start(BigInteger N) {
        resul.clear();
        resulH.clear();
        factorAux_start(N);
        return (resul);
    } 
    
    private void factorAux_start(BigInteger N) {
        if (N.compareTo(ONE) == 0)
            return;
        if (N.isProbablePrime(100)) 
        {
            resul.add(N);
            resulH.add(N.toString(this.base));
            Platform.runLater(() -> this.imprimirResultado(resul, this.base));
            return; 
        }
        BigInteger div = rho(N);
        this.num = (N.divide(div));
        factorAux_start(div);
        factorAux_start(N.divide(div));
    }
    
    
    public void continuePFE(){
        this.resultado = this.factor_cont(this.num);
        establecerResultado();
        escribirHistorial();
    }
     
    public ArrayList<BigInteger> factor_cont(BigInteger N) {
        factorAux_cont(N);
        return (resul);
    } 
    
    private void factorAux_cont(BigInteger N) {
        if (N.compareTo(ONE) == 0) 
            return;
        if (N.isProbablePrime(100)) 
        {
            resul.add(N);
            resulH.add(N.toString(this.base));
            Platform.runLater(() -> this.imprimirResultado(resul, this.base));
            return; 
        }
        BigInteger div = rho(N);
        factorAux_cont(div);
        factorAux_cont(N.divide(div));
    }
      
   
    private BigInteger rho(BigInteger N) {        
        this.c  = new BigInteger(N.bitLength(), random);
        this.x  = new BigInteger(N.bitLength(), random);
        this.xx = x;

        // Comprueba divisibilidad por 2
        if (N.mod(TWO).compareTo(ZERO) == 0)
            return TWO;

        if(!FactorizacionLogica.isCancelled)
        {
            do
            {
                this.x = this.x.multiply(this.x).mod(N).add(this.c).mod(N);
                this.xx = this.xx.multiply(this.xx).mod(N).add(this.c).mod(N);
                this.xx = this.xx.multiply(this.xx).mod(N).add(this.c).mod(N);
                this.divisor = this.x.subtract(this.xx).gcd(N);
            } while(((this.divisor.compareTo(ONE)) == 0) && (!FactorizacionLogica.isCancelled));
        }
        return divisor;
    }
    
    
    public void establecerResultado(){
        String op = fc.getTfNumero().getText();
        op = validar.separadorMiles(op, this.base);
        fc.getTfNumero().setText(op.toUpperCase()); 
    }
    
    public void imprimirResultado(ArrayList<BigInteger> resul, int b){
        String r = "";
        String n;
        for(int i=0; i<resul.size(); i++)
        {
            n = resul.get(i).toString(b).toUpperCase();
            if(b == 10) // Si es decimal realizar separación miles
                n = validar.separadorMiles(n, b);
            
            r = (r + "\n" + n);
        }
        r = r.substring(1); // Quita el salto de línea que hay al principio
        fc.getTfFactor().setText(r);
    }
    
    private void escribirHistorial(){  
        String op = fc.getTfNumero().getText();
        this.tiempoFin = System.currentTimeMillis();  
        String historial = (op + " = " + this.resulH + " (" +
                validar.millisToTime(this.tiempoFin - this.tiempoIni) + ")\n\n");   
        pc.textoHistorial.setText(historial + pc.textoHistorial.getText());}
    
    public ArrayList<BigInteger> getResul(){
        return(this.resul);
    }
}