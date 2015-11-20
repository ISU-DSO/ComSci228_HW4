package edu.iastate.cs228.hw4;

/**
 *  
 * @author Ian Jamieson
 *
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * This class represents an infix expression. It implements infix to postfix conversion using 
 * one stack, and evaluates the converted postfix expression.    
 *
 */

public class InfixExpression extends Expression 
{
	private String infixExpression;   		// the infix expression to convert		
	private boolean postfixReady = false;   // postfix already generated if true
	private PureStack<Operator> operatorStack; 	  // stack of operators 

	
	/**
	 * Constructor stores the input infix string, and initializes the operand stack and 
	 * the hash map.
	 * 
	 * @param st  input infix string. 
	 * @param varTbl  hash map storing all variables in the infix expression and their values. 
	 */
	public InfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		infixExpression = st;
		varTable = varTbl;
		operatorStack =  new ArrayBasedStack<Operator>();
	}
	

	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public InfixExpression (String s)
	{
		infixExpression = s;
		operatorStack = new ArrayBasedStack<Operator>();
		varTable = new HashMap<Character, Integer>();
		
	}
	

	/**
	 * Outputs the infix expression according to the format in the project description.
	 * It first calls the method toStringHelper() from the class Expression.  
	 */
	@Override
	public String toString()
	{
		String s = "";
		for(int i = 0; i < infixExpression.length(); i++){
			if(isVariable(infixExpression.charAt(i))|| isOperator(infixExpression.charAt(i))){
				s = s + infixExpression.charAt(i) + " ";
			}
			else{
				s = s + infixExpression.charAt(i);
				if(i == infixExpression.length()-1){
					break;
				}
				String t = infixExpression.substring(i+1, i+2);
				if(!isInt(t)){
					s = s + " ";
				}
			}
		}
		s = removeExtraSpaces(s);
		return s.replace("( ", "(").replace(" )", ")");
	}
	
	
	/** 
	 * @return equivalent postfix expression, or  
	 * 
	 *         a null string if a call to postfix() inside the body (when postfixReady 
	 * 		   == false) throws an exception.
	 */
	public String postfixString() 
	{
		String s = "";	
		
		if(postfixReady == true){
			s = postfixExpression.toString();
		}
		
		if(postfixReady == false){
			try {
				postfix();
			} catch (ExpressionFormatException e) {
				return null;
			}
			s = postfixExpression.toString();
		}
		
		return s; 
	}


	/**
	 * Resets the infix expression. 
	 * 
	 * @param st
	 */
	public void resetInfix (String st)
	{
		infixExpression = st; 
	}


	/**
	 * Converts infixexpression to an equivalent postfix string stored at postfixExpression.
	 * If postfixReady == false, the method scans the infixExpression, and does the following
	 * (for algorithm details refer to the relevant PowerPoint slides): 
	 * 
	 *     1. Skips a whitespace character.
	 *     2. Writes a scanned operand to postfixExpression.
	 *     3. If a scanned operator has a higher input precedence than the stack precedence of 
	 *        the top operator on the operatorStack, push it onto the stack.   
	 *     4. Otherwise, first calls outputHigherOrEqual() before pushing the scanned operator 
	 *        onto the stack. No push if the scanned operator is ). 
     *     5. Keeps track of the cumulative rank of the infix expression. 
     *     
     *  During the conversion, catches errors in the infixExpression by throwing 
     *  ExpressionFormatException with one of the following messages:
     *  
     *      -- "Operator expected" if the cumulative rank goes above 1;
     *      -- "Operand expected" if the rank goes below 0; 
     *      -- "Missing '('" if scanning a �)� results in popping the stack empty with no '(';
     *      -- "Missing ')'" if a '(' is left unmatched on the stack at the end of the scan; 
     *      -- "Invalid character" if a scanned char is neither a digit nor an operator; 
     *   
     *  If an error is not one of the above types, throw the exception with a message you define.
     *      
     *  Sets postfixReady to true.  
	 */
	public void postfix() throws ExpressionFormatException
	{
		int cr = 0;
		

			postfixExpression = "";
			for(int i = 0; i < infixExpression.length(); i++){
				char c = infixExpression.charAt(i);
				
				//if the value is an operator
				if(isOperator(c)){
					if(operatorStack.isEmpty()){
						Operator n = new Operator(c);
						operatorStack.push(n);
						cr = cr + n.rank;
						if(cr > 1){
							throw new ExpressionFormatException("Operator expected");
						}
					}
					
					else{
						Operator m = new Operator(c);
						
						if(operatorStack.peek().compareTo(m) >= 0){
							outputHigherOrEqual(m);
							cr = cr + m.rank;
							
						}
						
						else{
							operatorStack.push(m);
							cr = cr + m.rank;
							if(cr > 1){
								throw new ExpressionFormatException("Operator expected");
							}
						}
					}
				}
				
//				else if(!isInt(Character.toString(infixExpression.charAt(i))) && !isOperator(infixExpression.charAt(i)) && c != ' '){
//					throw new ExpressionFormatException("Invalid character");
//				}
				
				//if the value is anything but an operator
				else{
					if(c == ' '){
						postfixExpression = postfixExpression + "";
					}
					else{
						postfixExpression = postfixExpression + c + " ";
						cr++;
						
					}
					
				}
				
				
				
				
			}
			
			int count = 0;
			while(!operatorStack.isEmpty()){
				if(count == 0){
					//System.out.println(operatorStack.peek().getOp());
					postfixExpression = postfixExpression + operatorStack.pop().operator;
					count++;
				}
				else if(count > 0){
					postfixExpression = postfixExpression + " " + operatorStack.pop().operator;
				}
			
				
			}
			postfixReady = true;
			
		}
		
		
		 
	
	
	
	/**
	 * This function first calls postfix() to convert infixExpression into postfixExpression. Then 
	 * it creates a PostfixExpression object and calls its evaluate() method (which may throw  
	 * an exception).  It also passes any exception thrown by the evaluate() method of the 
	 * PostfixExpression object upward the chain. 
	 * 
	 * @return value of the infix expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 * @throws UnassignedVariableException 
	 */
	public int evaluate() throws ExpressionFormatException, UnassignedVariableException  
    {
		postfix();
		
		PostfixExpression n = new PostfixExpression(this.postfixExpression);
		n.setVarTable(this.varTable);
		int i = n.evaluate();
 
		return i;  
    }


	/**
	 * Pops the operator stack and output as long as the operator on the top of the stack has a 
	 * stack precedence greater than or equal to the input precedence of the current operator op.  
	 * Writes the popped operators to the string postfixExpression. 
	 * 
	 * If op is a ')', and the top of the stack is a '(', also pops '(' from the stack but does 
	 * not write it to postfixExpression. 
	 * 
	 * @param op  current operator
	 * @throws ExpressionFormatException 
	 */
	private void outputHigherOrEqual(Operator op) throws ExpressionFormatException
	{

		
		
		while(!operatorStack.isEmpty() && operatorStack.peek().compareTo(op) >= 0){
			if(operatorStack.peek().getOp() != '('){
				postfixExpression = postfixExpression + operatorStack.pop().operator + " ";
			}
			
		}
		
		if(op.getOp() != ')'){
			operatorStack.push(op);
			
		}
		
		else if(operatorStack.isEmpty() || operatorStack.pop().operator != '('){
			throw new ExpressionFormatException("Missing '(");
		}
		
	}	
	
	
}

