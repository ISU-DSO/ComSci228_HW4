package edu.iastate.cs228.hw4;

/**
 *  
 * @author Ian Jamieson
 *
 */

/**
 * 
 * This class evaluates a postfix expression using one stack.    
 *
 */

import java.util.HashMap;
import java.util.NoSuchElementException; 
import java.util.Scanner;
import java.util.Stack;

public class PostfixExpression extends Expression 
{
	private int leftOperand;                  // left operand for the current evaluation step             
	private int rightOperand;                 // right operand for the current evaluation step	

	private PureStack<Integer> operandStack;  // stack of operands
	

	/**
	 * Constructor stores the input postfix string and initializes the operand stack.
	 * 
	 * @param st  input postfix string. 
	 * @param varTbl  hash map that stores variables from the postfix string and their values.
	 */
	public PostfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		postfixExpression = st;
		operandStack = (PureStack<Integer>) new Stack<Integer>();
	}
	
	
	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public PostfixExpression (String s)
	{
		postfixExpression = s;
		HashMap<Character, Integer> newMap = new HashMap<Character, Integer>();
	}

	
	/**
	 * Outputs the postfix expression according to the format in the project description.
	 * Needs to first call the method toStringHelper() from the class Expression.  
	 */
	@Override 
	public String toString()
	{
		return postfixExpression.replace("( ", "(").replace(" )", ")");
	}
	

	/**
	 * Resets the postfix expression. 
	 * @param st
	 */
	public void resetPostfix (String st)
	{
		postfixExpression = st; 
	}


	/**
     * Scan the postfixExpression and carry out the following:  
     * 
     *    1. Whenever an integer is encountered, push it onto operandStack.
     *    2. Whenever an operator is encountered, invoke it on the two elements popped from  
     *       operandStack,  and push the result back onto the stack.  
     *    3. On encountering a character that is not a digit, an operator, or a blank space, stop 
     *       the evaluation. 
     *       
     * @return value of the postfix expression 
     * @throws ExpressionFormatException with one of the messages below: 
     *  
     *           -- "Invalid character" if encountering a character that is not a digit, an operator
     *              or a whitespace (blank, tab); 
     *           --	"Too many operands" if operandStack is non-empty at the end of evaluation; 
     *           -- "Too many operators" if getOperands() throws NoSuchElementException; 
     *           -- "Divide by zero" if division or modulo is the current operation and rightOperand == 0;
     *           -- "0^0" if the current operation is "^" and leftOperand == 0 and rightOperand == 0;
     *           -- self-defined message if the error is not one of the above.
     *           
     *         UnassignedVariableException if the operand as a variable does not have a value stored
     *            in the hash map.  In this case, the exception is thrown with the message
     *            
     *           -- "Variable <name> was not assigned a value", where <name> is the name of the variable.  
     *           
     */
	public int evaluate() throws ExpressionFormatException 
    {
		
		Scanner s = new Scanner(postfixExpression);
		int j = postfixExpression.length();	
		
		for(int i = 0; i < j; i++){
			
			char c = postfixExpression.charAt(i);
			if(isInt(s.next())){
				int l = Integer.parseInt(s.next());
				operandStack.push(l);
				
			}
			
			else if(isOperator(c)){
				getOperands();
				int m = compute(c);
				operandStack.push(m);
				
			}
			
			else{
				s.close();
				throw new ExpressionFormatException("Invalid character");
			}
			
		}
		
		if(!operandStack.isEmpty()){
			s.close();
			throw new ExpressionFormatException("Too many operands");
		}
		s.close();
		int eval2 = operandStack.pop();
	
		return eval2;  
    }
	

    /** 
     * Pops the right and left operands from operandStack, and assign them to rightOperand 
     * and leftOperand, respectively. The stack must have at least two entries.  Otherwise, 
     * throws NoSuchElementException.  
     */
	private void getOperands() throws NoSuchElementException 
	{
		if(operandStack.size() >= 2){
			rightOperand = operandStack.pop();
			leftOperand = operandStack.pop();
		}
		
		else{
			throw new NoSuchElementException();
		}
		// TODO 
	}


	/**
	 * Computes "leftOperand op rightOprand". 
	 * 
	 * @param op operator that acts on leftOperand and rightOperand. 
	 * @return
	 */
	private int compute(char op)  
	{
		
		int comp = 0;
		
		if(op == '+'){
			comp = leftOperand + rightOperand;
		}
		
		if(op == '-'){
			comp = leftOperand - rightOperand;
		}
		
		if(op == '*'){
			comp = leftOperand * rightOperand;
		}
		
		if(op == '/'){
			comp = leftOperand / rightOperand;
		}
		
		if(op == '%'){
			comp = leftOperand % rightOperand;
		}
		
		if(op == '^'){
			comp = leftOperand ^ rightOperand;
		}
		
		// TODO 
		return comp;  // TO MODIFY 
	}
}
