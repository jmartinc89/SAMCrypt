package Resta;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RestaLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    // Resta de números en decimal(enteros), binario y hexadecimal.
    public String restaEnteros(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, this.base);
        BigInteger num2 = new BigInteger(op2, this.base);
        BigInteger resul = num1.subtract(num2);
        return (resul.toString(this.base));
    }
    
    // Resta de números en decimal(decimales).
    public String restaDecimal(String op1, String op2){
        BigDecimal num1 = new BigDecimal(op1);
        BigDecimal num2 = new BigDecimal(op2);
        BigDecimal resul = num1.subtract(num2);
        return (resul.toString());
    }
    
    // Resta con módulo de números en decimal(enteros), binario y hexadecimal.
    public String restaModulo(String op1, String op2, String modulo){        
        BigInteger mod = new BigInteger(modulo, this.base);
        String aux = restaEnteros(op1, op2);
        BigInteger resta = new BigInteger(aux, this.base);
        BigInteger resul = resta.mod(mod);
        return (resul.toString(this.base));
    }
}