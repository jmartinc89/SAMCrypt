package Division;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionLogica {

    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    // División de números en decimal(enteros), binario y hexadecimal.
    public String divisionEnteros(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, this.base);
        BigInteger num2 = new BigInteger(op2, this.base);
        BigInteger resul = num1.divide(num2);
        return (resul.toString(this.base)); // El resultado es un número entero
    }
    
    // División de números en decimal(decimales).
    public String divisionDecimal(String op1, String op2){
        BigDecimal num1 = new BigDecimal(op1);
        BigDecimal num2 = new BigDecimal(op2);
        BigDecimal resul = num1.divide(num2, 12, RoundingMode.HALF_UP);
        return (resul.toString());
    }
}
