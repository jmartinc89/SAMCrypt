package TestPrimalidad;

import java.math.BigInteger;

public class PrimalidadLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
   
    public String primalidad(String op){
        BigInteger num = new BigInteger(op, this.base);
        boolean resul = num.isProbablePrime(100);
        if(resul)
            return ("Es un número primo");
        else
            return ("No es un número primo");
    }
}