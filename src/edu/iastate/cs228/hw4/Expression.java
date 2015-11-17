package edu.iastate.cs228.hw4;

/**
 * @author Ian Jamieson
 */

import java.util.HashMap;

public abstract class Expression 
{
	protected String postfixExpression; 		
	protected HashMap<Character, Integer> varTable; // hash map to store variables in the 

	
	protected Expression()
	{
		// no implementation needed 
		// removable when you are done
	}
	
	
	/**
	 * Initialization with a provided hash map. 
	 * 
	 * @param varTbl
	 */
	protected Expression(String st, HashMap<Character, Integer> varTbl)
	{
		postfixExpression = st;
		varTable = varTbl;
	}
	
	/**
	 * Initialization with a default hash map.
	 * 
	 * @param st
	 */
	protected Expression(String st) 
	{
		postfixExpression = st;
		HashMap<Character, Integer> h = new HashMap<Character, Integer>();
	
	}

	/**
	 * Useful with the d
	 * @param varTbl
	 */
	public void setVarTable(HashMap<Character, Integer> varTbl) 
	{
		// TODO 
	}
	
	/**
	 * Evaluates the infix or postfix expression. 
	 * 
	 * @return value of the expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public abstract int evaluate() throws ExpressionFormatException, UnassignedVariableException;  

	
	
	// --------------------------------------------------------
	// Helper methods for InfixExpression and PostfixExpression 
	// --------------------------------------------------------

	/** 
	 * Check if a string represents an integer.  You may call the static method 
	 * Integer.parseInt(). 
	 * 
	 * @param s
	 * @return
	 */
	protected static boolean isInt(String s) 
	{
		char c = s.charAt(0);
		
		if(Character.isDigit(c)){
			return true;
		}
		
		
		// TODO 
		return false; 
	}

	
	/**
	 * Check if a char represents an operator, i.e., one of '+', '-', '*', '/', '%', '^'. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isOperator(char c) 
	{
		if(c == '+'){
			return true;
		}
		
		if(c == '-'){
			return true;
		}
		
		if(c == '*'){
			return true;
		}
		
		if(c == '/'){
			return true;
		}
		
		if(c == '%'){
			return true;
		}
		
		if(c == '^'){
			return true;
		}
		
		else{
			return false; 
		}
		
	}

	/** 
	 * Check if a char is a variable, i.e., a lower case English letter. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isVariable(char c) 
	{
		if(c >= 'a' && c <= 'z'){
			return true;		
		}
		
		else{
			return false;
		}
		
	}
	
	 protected static String removeExtraSpaces(String s) {
	     return s.replaceAll("\\s+", " ").trim();
	 }
}
