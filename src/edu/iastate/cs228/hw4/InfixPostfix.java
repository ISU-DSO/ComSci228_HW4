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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws UnassignedVariableException 
	 * @throws ExpressionFormatException 
	 **/
	public static void main(String[] args) throws FileNotFoundException, ExpressionFormatException, UnassignedVariableException 
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
				boolean hasV = false;
				int eval = 0;
				InfixExpression in = null;
				PostfixExpression p;
				
				
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
						hasV = true;
					}
					
				}
				one.close();
				//checking if the given expression is infix or postfix
				if(exp.charAt(0) == 'I'){
					String infix;
					infix = exp.substring(1, exp.length());
					in = new InfixExpression(infix);
					System.out.println("Infix form: " + in.toString());
					System.out.println("Postfix form: " + in.postfixString());
					
				}
				
				if(exp.charAt(0) == 'P'){
					String post;
					post = exp.substring(1, exp.length());
					PostfixExpression n = new PostfixExpression(post);
					System.out.println("Postfix form: " + n.toString());
				}
				
				//where are the variables?
				if(hasV == true){
					System.out.println("where");
					
					//Scanning for the variables
					Scanner w = new Scanner(System.in);
					Iterator<Entry<Character, Integer>> n = newMap.entrySet().iterator();
					while(n.hasNext()){
						char c = ((Map.Entry<Character, Integer>) n.next()).getKey();
						System.out.println(c + " = ");
						newMap.put(c, w.nextInt());
						
					}
					w.close();
				}
				
				
				
				
				//evaluating the expression
				in.setVarTable(newMap);
				eval = in.evaluate();
				System.out.println("Evaluation: " + eval);
				
				
				
				trialNum = trialNum + 1;
				tf = true;
			}
			
			
			//If the user selects an input file
			if(userNum == 2){
				//trial number and import from file line
				System.out.println("Trial " + trialNum + ":" + userNum);
				System.out.println("Input from a file");
				
				//importing from a file
				System.out.println("Enter file name: ");
				Scanner t = new Scanner(System.in);
				String fileName = t.nextLine();
				File f = new File(fileName);
				
				try{
					Scanner x = new Scanner(f);
					while(x.hasNextLine()){
						String y = x.nextLine();
						if(y.charAt(0) == 'I'){
							String infix;
							infix = y.substring(1, y.length());
							InfixExpression i = new InfixExpression(infix);
							System.out.println("Infix form: " + i.toString());
							System.out.println("Postfix form: " + i.postfixString());
						}
						if(y.charAt(0) == 'P'){
							String post;
							post = y.substring(1, y.length());
							PostfixExpression n = new PostfixExpression(post);
							System.out.println("Postfix form: " + n.toString());
							
						}
					}
					x.close();
				}
				
				catch(FileNotFoundException e){
					System.out.println(e);
				}
				
				
				
				
				trialNum = trialNum + 1;
				tf = true;
			}
			
			//If the user is so done with this program they just really want to leave it.
			if(userNum == 3){
				System.out.println("Closed.");
				tf = false;
			}
		
		}
		
		 
	}
	
	// helper methods if needed
}
