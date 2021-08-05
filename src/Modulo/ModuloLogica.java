package Modulo;

import java.math.BigInteger;

public class ModuloLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    public String modulo(String op, String mod){
        BigInteger num1 = new BigInteger(op, base);
        BigInteger num2 = new BigInteger(mod, base);
        BigInteger resul = num1.mod(num2);
        return (resul.toString(base));
    }
}