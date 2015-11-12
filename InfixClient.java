
/**
 * The program should then evaluate the infix expression, display the final result, and keep allowing the user to enter expressions until s/he chooses to exit.
 * 
 * @author (Samuel Walton) 
 * @version (11/30/2013)
 * instructor Ernest McCracken 
 */

import java.util.*;
public class InfixClient
{ 
    public static void main (String [] args) 
    { 
        Scanner input = new Scanner (System.in);
        int answer =-1;
        do
        {
            System.out.println ("Enter an infix expression:"); 
            Operator token = new Operator(); 
            token.getOperator (); 
          Evaluate in = new Evaluate (token); 
            double result = in.expression ();  

            System.out.println ("= " + result); 
            System.out.println ("Enter [-1] to exit, or any other number to play again!" );  
            answer = input.nextInt();
        }while(answer!=-1);
        System.out.println ("Thank you for playing!" );  
    } // main method 
} 