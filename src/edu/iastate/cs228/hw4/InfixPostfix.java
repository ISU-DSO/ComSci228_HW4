package edu.iastate.cs228.hw4;

/**
 *  
 * @author
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
		
		System.out.println("Evaluation of Infix and Postfix Expressions");
		System.out.println("1 (standard input)  2 (file input)  3 (exit)");
		System.out.println("(Enter I before an infix expression, P before a postfix expression)");
		Scanner s = new Scanner(System.in);
		System.out.print("Trial " + trialNum + ":" +s.nextInt());
		
		
		
		// TODO  
	}
	
	// helper methods if needed
}
