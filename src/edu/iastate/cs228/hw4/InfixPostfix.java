package edu.iastate.cs228.hw4;

/**
 *  
 * @author Ian Jamieson
 *
 */

/**
 * 
 * This class evaluates input infix and postfix expressions. 
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 **/
	public static void main(String[] args) 
	{
		int trialNum = 1;
		boolean tf = true;
		HashMap<Character, Integer> newMap = new HashMap<Character, Integer>();
		
		//Base output, only used before the first trial.
		System.out.println("Evaluation of Infix and Postfix Expressions");
		System.out.println("1 (standard input)  2 (file input)  3 (exit)");
		System.out.println("(Enter I before an infix expression, P before a postfix expression)");
		System.out.println();
		System.out.println();
		//Scanner to figure out which input they would like to use.
		
		
		while(tf == true){
		
			Scanner s = new Scanner(System.in);
			int userNum = s.nextInt();
			
			
			
			//If the user selects a standard input via system.in
			if(userNum == 1){
				
				//local variables
				boolean isInfix = false;
				
				
				
				//trial number output line
				System.out.println("Trial " + trialNum + ":" + userNum);
				
				
				//expression output line 
				System.out.print("Expression: ");
				Scanner one = new Scanner(System.in);
				String exp = one.nextLine();
				
				if(exp.charAt(0) == 'I'){
					System.out.println("Hello");
					InfixExpression i = new InfixExpression(exp);
					System.out.println(i);
				}
				
				if(exp.charAt(0) == 'P'){
					PostfixExpression n = new PostfixExpression(exp);	
				}
				
				
				
				
				
				System.out.println("Infix");
				
				
				
				//System.out.println("Infix");
				
				
				
				
				trialNum = trialNum + 1;
				tf = true;
			}
			
			
			//If the user selects an input file
			if(userNum == 2){
				System.out.println("Trial " + trialNum + ":" + userNum);
				System.out.println("Hi");
				
				trialNum = trialNum + 1;
				tf = true;
			}
			
			//If the user is so done with this program they just really want to leave it.
			if(userNum == 3){
				System.out.println("Bye. ;) ");
				tf = false;
			}
		
		}
		
		// TODO  
	}
	
	// helper methods if needed
}
