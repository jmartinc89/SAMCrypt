package RaizCuadrada;

import java.math.BigInteger;

public class RaizCuadradaLogica {
    int base;
    
    public int getBase(){
        return(this.base);
    }
      
    public void setBase(int base){
        this.base = base;
    }
    
    public String raiz(String op){
        BigInteger a = new BigInteger("1", this.base);
        BigInteger uno = new BigInteger("1", this.base);
        BigInteger number = new BigInteger(op, this.base);
	BigInteger b = number.shiftRight(5).add(BigInteger.valueOf(8));
        BigInteger mid ;
        
        while (b.compareTo(a) >= 0)
        {
            mid = a.add(b).shiftRight(1);
            if (mid.multiply(mid).compareTo(number) > 0)
                b = mid.subtract(uno);
            else
                a = mid.add(uno);
	}
        return(a.subtract(uno).toString(this.base).toUpperCase());
    }
}