package Mcm;

import java.math.BigInteger;

public class McmLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
    
    public void setBase(int base){
        this.base = base;
    }
    
    public String mcm(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, this.base);
        BigInteger num2 = new BigInteger(op2, this.base);
        BigInteger resul = (num1.divide(num1.gcd(num2))).multiply(num2); // (a/mcd(a,b))*b
        return(resul.toString(this.base));
    }   
}