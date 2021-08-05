package RaizPrimitiva;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import samcript.PrincipalController;
import static samcript.SamCript.fxmlLoader;
import validacion.Validacion;

public class RaizPrimitivaLogica {    
    
    private final Validacion validar;
    private final RaizPrimitivaController rpc;
    private final PrincipalController pc;   
    int base;
    private long tiempoIni;
    private long tiempoFin;
    
    // Para ContinuarRP
    BigInteger mod;
    BigInteger p; 
    BigInteger divisor;
    BigInteger c;
    BigInteger x;
    BigInteger xx;
    BigInteger num; 
    public static boolean isCancelled;
    public boolean finFact;
    
    // RAÍZ PRIMITIVA
    private final ArrayList<BigInteger> raices = new ArrayList<>();
    private final ArrayList<String> raicesH = new ArrayList<>();
    private static BigInteger g;
    
    // POLLARD RHO
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();
    private final ArrayList<BigInteger> factores = new ArrayList<>();
            
    
    public RaizPrimitivaLogica(){
        validar = new Validacion();
        pc = fxmlLoader.getController();
        rpc = pc.getFlRaizP().getController();
    }
      
    public void setBase(int base){
        this.base = base;
    } 
    
    public void setIsCancelled(boolean value){
        RaizPrimitivaLogica.isCancelled = value;
    }
  
    
    public void start(String op){
        BigInteger n = new BigInteger(op, this.base);
        this.factores.clear();
        this.raices.clear();
        this.raicesH.clear();
        this.tiempoIni = System.currentTimeMillis();
        this.raizPrimitiva_start(n);
        if(!isCancelled)
        {    
            establecerResultado();
            escribirHistorial();
        }
    }
       
    public void factor_start(BigInteger N) {
        factores.clear();
        factorAux_start(N);
    }
    
    private void factorAux_start(BigInteger N) {
        if (N.compareTo(ONE) == 0)
            return;
        if (N.isProbablePrime(100)) 
        {
            factores.add(N);
            return; 
        }
        BigInteger div = rho(N);
        this.num = (N.divide(div));
        factorAux_start(div);
        factorAux_start(N.divide(div));
    }
      
    public void raizPrimitiva_start(BigInteger m){ 
        boolean enc;
        this.mod = m;
        this.p = this.mod.subtract(ONE);
        
        // Factorización
        this.finFact = false;
        this.factor_start(this.p);        
        // Eliminar valores repetidos
        HashSet hs = new HashSet();
        hs.addAll(this.factores);
        this.factores.clear();
        this.factores.addAll(hs);
        this.finFact = true;
              
        // Cálculo raíces 
        g = new BigInteger("2"); // primer CCR
        while((g.compareTo(mod) < 0) && (!RaizPrimitivaLogica.isCancelled)) // Recorre valores para g (CCR)
        {
            enc = true;
            int i=0;
            while((i<this.factores.size()) && enc && (!RaizPrimitivaLogica.isCancelled)) // Realiza la expoMod por cada factor del ArrayList
            {
                BigInteger factor = this.factores.get(i);
                BigInteger ex = p.divide(factor);
                BigInteger resultado = g.modPow(ex, mod);
                if(resultado.compareTo(ONE) == 0)
                    enc = false;

                i++;
            }
            if(enc)
            {
                raices.add(g);
                raicesH.add(g.toString(this.base).toUpperCase());
                rpc.getGenerador().setText(g.toString(this.base).toUpperCase() + ", " + rpc.getGenerador().getText());
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RaizPrimitivaLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            g = g.add(ONE);
        }
    }

    
    public void continueRP(){
        this.raizPrimitiva_cont();
        establecerResultado();
        escribirHistorial();
    }
   
    public void factor_cont(BigInteger N) {
        factorAux_cont(N);
    }
    
    private void factorAux_cont(BigInteger N) {
        if (N.compareTo(ONE) == 0)
            return;
        if (N.isProbablePrime(100)) 
        {
            factores.add(N);
            return; 
        }
        BigInteger div = rho(N);
        factorAux_cont(div);
        factorAux_cont(N.divide(div));
    }
    
    public void raizPrimitiva_cont() {
        boolean enc;
        if(!this.finFact) // No se ha terminado de factorizar
        {
            this.factor_start(this.p);        
            // Eliminar valores repetidos
            HashSet hs = new HashSet();
            hs.addAll(this.factores);
            this.factores.clear();
            this.factores.addAll(hs);
            this.finFact = true;
        }
        
        // Cálculo raíces 
        if(!RaizPrimitivaLogica.isCancelled)
        {
            while((g.compareTo(mod) < 0) && (!RaizPrimitivaLogica.isCancelled)) // Recorre valores para g (CCR)
            {
                enc = true;
                int i=0;
                while((i<this.factores.size()) && enc && (!RaizPrimitivaLogica.isCancelled)) // Realiza la expoMod por cada factor del ArrayList
                {
                    BigInteger factor = this.factores.get(i);
                    BigInteger ex = p.divide(factor);
                    BigInteger resultado = g.modPow(ex, mod);
                    if(resultado.compareTo(ONE) == 0)
                        enc = false;

                    i++;
                }
                if(enc)
                {
                    raices.add(g);
                    raicesH.add(g.toString(this.base).toUpperCase());
                    rpc.getGenerador().setText(g.toString(this.base).toUpperCase() + ", " + rpc.getGenerador().getText());
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RaizPrimitivaLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                g = g.add(ONE);
            }
        }
        if(RaizPrimitivaLogica.isCancelled)
            raizPrimitiva_cont();
    }

    
    private BigInteger rho(BigInteger N) {   
        this.c  = new BigInteger(N.bitLength(), random);
        this.x  = new BigInteger(N.bitLength(), random);
        this.xx = x;

        // Comprueba divisibilidad por 2
        if (N.mod(TWO).compareTo(ZERO) == 0)
            return TWO;

        if(!RaizPrimitivaLogica.isCancelled)
        {
            do
            {
                this.x = this.x.multiply(this.x).mod(N).add(this.c).mod(N);
                this.xx = this.xx.multiply(this.xx).mod(N).add(this.c).mod(N);
                this.xx = this.xx.multiply(this.xx).mod(N).add(this.c).mod(N);
                this.divisor = this.x.subtract(this.xx).gcd(N);
            } while(((this.divisor.compareTo(ONE)) == 0) && (!RaizPrimitivaLogica.isCancelled));
        }
        return divisor;
    }
    
    
    public void establecerResultado(){
        String op = rpc.getTfNumero().getText();
        op = validar.separadorMiles(op, this.base);
        rpc.getTfNumero().setText(op.toUpperCase());
    }
    
    public void imprimirResultado(ArrayList<BigInteger> resul, int b){
        String r = "";
        String n;
        for(int j=0; j<resul.size(); j++)
        {
            n = resul.get(j).toString(b).toUpperCase();
            if(b == 10) // Si es decimal realizar separación miles
                n = validar.separadorMiles(n, b);
            r = (n + ", " + r);
        }
        rpc.getGenerador().setText(r);
    }
    
    private void escribirHistorial(){   
        String op = rpc.getTfNumero().getText();
        this.tiempoFin = System.currentTimeMillis();
        String historial = op + " = " + this.raicesH + " (" +
                validar.millisToTime(this.tiempoFin - this.tiempoIni) + ") " +
                "|α|=" + this.raices.size();
        
        historial = historial.replace("\n", ", ");
        pc.textoHistorial.setText(historial + "\n\n" + pc.textoHistorial.getText()); 
    }
    
    public ArrayList<BigInteger> getResul(){
        return(this.raices);
    }
}