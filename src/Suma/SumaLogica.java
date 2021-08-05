package Suma;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SumaLogica {
    
    private int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    // Suma lógica de números en decimal(enteros), binario y hexadecimal.
    public String sumaEnteros(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, base);
        BigInteger num2 = new BigInteger(op2, base);
        BigInteger resul = num1.add(num2);
        return (resul.toString(base));
    }
    
    // Suma lógica de números en decimal(decimales).
    public String sumaDecimal(String op1, String op2){
        BigDecimal num1 = new BigDecimal(op1);
        BigDecimal num2 = new BigDecimal(op2);
        BigDecimal resul = num1.add(num2);
        return (resul.toString());
    }
    
    // Suma lógica con módulo de números en decimal(enteros), binario y hexadecimal.
    public String sumaModulo(String op1, String op2, String modulo){        
        BigInteger mod = new BigInteger(modulo, base);
        String aux = sumaEnteros(op1, op2);
        BigInteger suma = new BigInteger(aux, base);
        BigInteger resul = suma.mod(mod);
        return (resul.toString(base));
    }
}