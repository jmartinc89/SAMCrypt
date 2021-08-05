package Multiplicacion;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MultiplicacionLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    // Multiplicación logica de números en decimal(enteros), binario y hexadecimal.
    public String multiplicacionEnteros(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, base);
        BigInteger num2 = new BigInteger(op2, base);
        BigInteger resul = num1.multiply(num2);
        return (resul.toString(base));
    }
    
    // Multiplicación logica de números en decimal(decimales).
    public String multiplicacionDecimal(String op1, String op2){
        BigDecimal num1 = new BigDecimal(op1);
        BigDecimal num2 = new BigDecimal(op2);
        BigDecimal resul = num1.multiply(num2);
        return (resul.toString());
    }
    
    // Multiplicación logica con módulo de números en decimal(enteros), binario y hexadecimal.
    public String multiplicacionModulo(String op1, String op2, String modulo){        
        BigInteger mod = new BigInteger(modulo, base);
        String aux = multiplicacionEnteros(op1, op2);
        BigInteger multi = new BigInteger(aux, base);
        BigInteger resul = multi.mod(mod);
        return (resul.toString(base));
    }
}