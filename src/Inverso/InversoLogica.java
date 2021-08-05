package Inverso;

import java.math.BigInteger;

public class InversoLogica {
        
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }

    // op1 y op2 tienen que ser primos primos relativos, es decir, op1 mod op2 = 1 == mcd(op1, op2) = 1    
    public String inverso(String op, String mod){
        BigInteger num1 = new BigInteger(op, base);
        BigInteger num2 = new BigInteger(mod, base);
        BigInteger resul = num1.modInverse(num2);
        return (resul.toString(base));
    }
}