package MCD;

import java.math.BigInteger;

public class MCDLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }

    public String mcd(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, base);
        BigInteger num2 = new BigInteger(op2, base);
        BigInteger resul = num1.gcd(num2);
        return (resul.toString(base));
    }
}