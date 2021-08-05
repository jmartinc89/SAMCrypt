package XOR;

import java.math.BigInteger;

public class XORLogica {
    
    int base;
    
    public int getBase(){
        return(this.base);
    }
    
    public void setBase(int base){
        this.base = base;
    }
    
    public String xor(String op1, String op2){
        BigInteger num1 = new BigInteger(op1, this.base);
        BigInteger num2 = new BigInteger(op2, this.base);
        BigInteger resul = num1.xor(num2);
        
        int longitud = op1.length();
        String resultado = resul.toString(this.base);
        for(int i=resultado.length(); i<longitud; i++)
        {
            resultado = "0" + resultado;
        }
        
        return(resultado);
    }    
}