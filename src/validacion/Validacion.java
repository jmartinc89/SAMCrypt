package validacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class Validacion {
    
    private String formatoTiempo;
    
    // Método para "limpiar" número (quita puntos, espacios y tabuladores).
    public String formato(String num){          
        num = num.replaceAll("[. \t]","");  // Permite la coma para los decimales.
        return num;
    }
    
    public String comaAPunto(String num){
        num = num.replaceAll(",","."); // Internamente los decimales se escriben con punto.
                                       // Trabajar con punto para decimales.
        return num;
    }
    
    public String puntoAComa(String num){
        num = num.replace(".",","); // Internamente los decimales se escriben con punto.
                                    // Mostrar con coma los decimales.
        return num;
    }
    
    // Método que comprueba si el número es un correcto.
    public boolean isNumber(String num, int base){
        boolean enc = true;
        switch (base) {
            case 10:
                if(!num.matches("-?[0-9]*,?[0-9]+"))
                    enc = false;
                break;
            case 2:
                if(!num.matches("[0-1]+"))
                    enc = false;
                break;
            case 16:
                if(!num.matches("[0-9a-fA-F]+"))
                    enc= false;
                break;
            default:
                enc = false;
                break;
        }
        return(enc);
    }
       
    // Método que cuenta el número de dígitos (para números en decimal).
    public String longitudDecimal (String num){       
        int numDigitos;
        String resul = "";
        // BigInteger utiliza el punto como separador de decimales --> transformar
        num = this.formato(num);
        
        if(this.isNumber(num, 10))
        {
            num = this.comaAPunto(num);
            numDigitos = num.length();
        
            if(num.contains(",")) // Si el número tiene decimales no se cuenta la coma.
                numDigitos = numDigitos-1;
            if(num.contains("."))
                numDigitos = numDigitos-1;
            if(num.contains("-")) // Si el número tiene signo, no contarlo.
                numDigitos = numDigitos-1;

            resul = String.valueOf(numDigitos);
            if(numDigitos == 1)
                resul = (resul + " dígito");
            else
                resul = (resul + " dígitos");
        }
        return(resul);
    }
    
    // Método que cuenta el número de bits (para números en binario).
    public String longitudBinario (String num){
        int numBits;
        String resul = "";
        num = this.formato(num);
        
        if(this.isNumber(num, 2))
        {
            BigInteger n = new BigInteger(num, 2);
            numBits = n.bitLength();

            resul = String.valueOf(numBits);
            if(numBits == 1)
                resul = (resul + " bit");
            else
                resul = (resul + " bits");
        }
        return(resul);
    }
    
    // Método que cuenta el número de caracteres (para números en hexadecimal).
    public String longitudHexadecimal (String num){
        int numCaracteres;
        String resul = "";
        num = this.formato(num);
        
        if(this.isNumber(num, 16))
        {
            numCaracteres = num.length();
            resul = String.valueOf(numCaracteres);
            
            if(numCaracteres == 1)
                resul = (resul + " carácter");
            else
                resul = (resul + " caracteres");
        } 
        return(resul);
    }
  
    public String longitudBinarioXOR (String num){
        int numBits;
        String resul = "";
        num = this.formato(num);
        
        if(this.isNumber(num, 2))
        {
            numBits = num.length();
            resul = String.valueOf(numBits);
            
            if(numBits == 1)
                resul = (resul + " bit");
            else
                resul = (resul + " bits");            
        }
        return(resul);
    }
    
    // Método que pone puntos a los números en decimal (separador de miles).
    public String separadorMiles(String num, int base){  
       if (base == 10) // Número en decimal.
       {
            // Si el número tiene signo, quitárselo y añadirlo al final.
            boolean enc = false;
            if(num.compareTo("0") < 0)
            {
                num = num.substring(1);
                enc = true;
            }
            // Si el número tiene decimales, tratar parte entera y decimal por separado.
            if(num.contains(",")) // El número tiene decimales.
            {
                String[]numDiv = num.split(",");         // Dividir en parte entera y decimal.
                String pEntera = numDiv[0];              // Parte entera del número.
                String pDecimal = numDiv[1];             // Parte decimal del número.
                int numeroEntero = pEntera.length()-3;   // Separador de miles.
                
                pEntera = auxSeparador(pEntera, numeroEntero);
                
                pEntera = pEntera + ","; // Añadir la coma para la parte decimal.

                num = pEntera.concat(pDecimal);
            }
            else
            {
                int numero = num.length()-3; // Separador de miles.
                num = auxSeparador(num, numero);
            }
            // Añade el signo que se quitó arriba si el número era decimal.
            if(enc)
                num = ("-" + num);
       }           
       return num;
   }
    
   private String auxSeparador(String num, int numero){
       int longitud = num.length();
       while(numero>0)
       {
           num = num.substring(0,numero ) + "." + num.substring(numero, longitud);
           numero = numero-3;
           longitud++;
        }
       return(num);       
   }
   
    public String decimales(String resul){
        if(resul.contains(","))
        {   
            String[]partes = resul.split(",");
            String enteros = partes[0];
            String decimales = partes[1];
            BigDecimal d = new BigDecimal(decimales);
            if(d.equals(BigDecimal.ZERO))
                return(enteros);
            else
            {
                int l = decimales.length()-1; // Se resta uno para quedarse con el último dígito
                int cont = l;
                for(int i=0; i<cont; i++)
                {
                    if(decimales.substring(l).equals("0"))
                        decimales = decimales.substring(0, l);
                    l--;
                }
                enteros = enteros + ",";
                resul = enteros.concat(decimales);
                return(resul);
            }
        }
        else
            return(resul);
    }
    
    public String millisToTime(long runningTime) {
       String time;
       int numChars;
       
       runningTime = formatoTiempo(runningTime);
       time = String.valueOf(runningTime);
       numChars = time.length();
       
       if (numChars > 3){
           time = time.substring(0, numChars-3) + "," + time.substring(numChars-3, numChars);
       } else if (numChars == 3){
           time = "0," + time;
       } else if (numChars == 2){
           time = "0,0" + time;
       } else {
           time ="0,00" + time;
       }
       this.formatoTiempo = this.formatoTiempo + time;           
       return this.formatoTiempo;
    }
    
    private Long formatoTiempo(long tiempo){
        int h = 0, m = 0;
        while(tiempo >= 3600000) // Horas 
        {
            tiempo = tiempo - 3600000;
            h = h+1;
        }
        while(tiempo >= 60000) // Minutos 
        {
            tiempo = tiempo - 60000;
            m = m+1;
        }
        this.formatoTiempo = (h + ":" + m + ":");
        return(tiempo);
    }
    
    public void validacionEntradaDecimal(TextField tf){
            UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));         

                if (t.isAdded())
                {
                    if ((t.getControlText().startsWith("-")) && (t.getControlText().contains(",")))
                    {
                        if (t.getText().matches("[^0-9]"))
                        {
                            t.setText("");
                        }
                    }
                    else if(t.getControlText().startsWith("-"))
                    {
                        if (t.getText().matches("[^0-9,]"))
                        {
                            t.setText("");
                        }
                    }
                    else if(t.getControlText().contains(","))
                    {
                        if (t.getText().matches("[^0-9]"))
                        {
                            t.setText("");
                        }
                    }
                    else if (t.getText().matches("[^-0-9,]"))
                    {
                        t.setText("");
                    }
                }
                return t;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(filter));
    }
    
    public void validacionEntradaBinario(TextField tf){
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-1]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));         

                if (t.isAdded())
                {
                    if (t.getText().matches("[^0-1]"))
                    {
                        t.setText("");
                    }
                }
                return t;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(filter));
    }
    
    public void validacionEntradaHexadecimal(TextField tf){
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9a-fA-F]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));         

                if (t.isAdded())
                {
                    if (t.getText().matches("[^0-9a-fA-F]"))
                    {
                        t.setText("");
                    }
                }
                return t;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(filter));
    }
    
    public void validacionEntradaEnteros(TextField tf){
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));         

                if (t.isAdded())
                {
                    if (t.getText().matches("[^0-9]"))
                    {
                        t.setText("");
                    }
                }
                return t;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(filter));
    }
}