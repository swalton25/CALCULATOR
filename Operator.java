
/**
 *write a SUPER DUPER JAVA CALCULATORTM. Your program should allow the user to
 *enter an infix expression involving any combination of non-negative real operands (including decimals), the six math
 *operators + - * / % ^ (where % and ^ mean mod and exponentiation, respectively – remember that mod’s precedence is the
 *same as multiplication/division, while exponentiation is higher), and parentheses/brackets/braces () [] {}.
 * @author (Samuel Walton) 
 * @version (11/30/2013)
 *
 */
import java.io.*; 
import java.util.*; 

public class Operator
{ 
    private double theValue = 0; 
    private String name, tokenString; 
    BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in)); 
    StringTokenizer mathSymbols; 

    public Operator() 
    { 
        try 
        { 
            tokenString = stdin.readLine (); 
            mathSymbols= new StringTokenizer (tokenString, "+-*/^()[]{} ", true); 
        } catch (IOException e) {System.out.println ("Input error");} 
    } 

    public String getOperator () 
    { 
        char ch = ' '; 
        if (mathSymbols.hasMoreTokens ()) 
        { 
            String token = mathSymbols.nextToken (); 
            ch = token.charAt (0); 
            while (ch == ' ') 
            { 
                token = mathSymbols.nextToken (); 
                ch = token.charAt (0); 
            } 
            if ((('0' <= ch) && (ch <= '9'))||ch=='.') 
            { 
                name = "digit"; 
                try 
                { theValue = Double.parseDouble (token); 
                } catch (NumberFormatException e) {theValue= 0;} 
            } 
            else 
            {    switch (ch) 
                { 
                    case '+': name = "add"; break; 
                    case '-': name = "subtract"; break; 
                    case '*': name = "times"; break; 
                    case '/': name = "divide"; break; 
                    case '^': name = "exponent"; break; 
                    case '(': name = "lparen"; break; 
                    case ')': name = "rparen"; break; 
                    case '{': name = "lcurley"; break; 
                    case '}': name = "rcurley"; break; 
                    case '[': name = "lbracket"; break; 
                    case ']': name = "rbracket"; break; 
                    default: name = "other"; 
                } //switch 
            } // else 
        } 
        else name = "other"; 
        return name;
    } //method getToken 

    public String getName () { return name; } 

    public double getValue () { return theValue; } 

} 

class Evaluate

{ 
    private Operator token; 

    Evaluate(Operator token) { 
        this.token = token; 
    } // constructor 

    public double expression () 
    { 
        String oper; 
        double result; 
        result =myTerm (); 
        while ((token.getName ().equals ("add")) || (token.getName ().equals ("subtract"))||(token.getName ().equals ("exponent"))) 
        { 
            oper = token.getName (); 
            token.getOperator(); 
            if (oper.equals ("add")) 
            { 
                result = result + myTerm (); 
                System.out.print ("+ "); 
            } 
            else if(oper.equals ("exponent"))
            {
                double d=myTerm();
                result = Math.pow(result,d);
                System.out.print ("^"); 

            }
            else 
            { 
                result = result - myTerm(); 
                System.out.print ("- "); 
            } 
        } 
        return result; 
    } // method expression 

    public double myTerm () 
    { 
        String oper; 
        double result; 
        result = theFactor (); 
        while ((token.getName ().equals ("times")) || (token.getName ().equals ("divide"))) 
        { 
            oper = token.getName (); 
            token.getOperator(); 
            if (oper.equals ("times")) 
            { 
                result = result * theFactor (); 
                System.out.print ("* "); 
            } 
            else 
            { 
                result = result / theFactor (); 
                System.out.print ("/ "); 
            } 
        } 
        return result; 
    } // method term 

    public double theFactor () 
    { 
        double result = 0; 

        if (token.getName ().equals ("digit")) 
        { 
            result = token.getValue (); 
            System.out.print ("" + token.getValue () + " "); 
            token.getOperator(); 
        } 

        else
        if (token.getName ().equals ("lparen")||token.getName ().equals ("lbracket")||token.getName ().equals ("lcurley")) 

        { 
            token.getOperator(); 
            result = expression();
           
            
            if (token.getName ().equals ("rparen")||token.getName ().equals ("rbracket")||token.getName ().equals ("rcurley")) 

                token.getOperator(); 
            else 
                System.out.print ("Error - missing symbol"); 

        } 

        else 
            System.out.print ("Error - invalid operator symbol"); 
        return result; 
    } // method factor 
} // class Express 
