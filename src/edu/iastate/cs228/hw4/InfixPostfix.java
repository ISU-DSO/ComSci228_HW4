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
import java.util.Iterator;
import java.util.Map;
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
				String test = one.nextLine();
				
				for(int i = 1; i < test.length(); i++){
					char c = test.charAt(i);
					if(c >= 'a' && c <= 'z'){
						newMap.put(c, 0);
					}
					
				}
				
				//checking if the given expression is infix or postfix
				if(exp.charAt(0) == 'I'){
					String infix;
					infix = exp.substring(1, exp.length());
					InfixExpression i = new InfixExpression(infix);
					System.out.println("Infix: " + i.toString());
					System.out.println("Postfix: " + i.postfixString());
				}
				
				if(exp.charAt(0) == 'P'){
					String post;
					post = exp.substring(1, exp.length());
					PostfixExpression n = new PostfixExpression(post);
					System.out.println("Postfix: " + n.toString());
				}
				
				//where are the variables?
				System.out.println("where");
				
				//Scanning for the variables
				Scanner w = new Scanner(System.in);
				Iterator i = newMap.entrySet().iterator();
				while(i.hasNext()){
					char c = ((Map.Entry<Character, Integer>) i.next()).getKey();
					System.out.println(c + " = ");
					newMap.put(c, w.nextInt());
					
				}
				
				
				
				
				
				
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
