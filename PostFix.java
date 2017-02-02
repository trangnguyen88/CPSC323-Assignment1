package homework1;
import java.util.Scanner;
import java.util.Stack;
public class PostFix {
	public static void main (String [] args)
	{
		Scanner scanString= new Scanner(System.in);
		String expression = "Y";
		while(expression.equals("Y")){
			System.out.println("Enter your expression: ");
		    expression=scanString.next();
			expression=expression.replaceAll("\\s", " ");// ignore the end of line
			Double finalResult=postfixEvaluate(expression);
			System.out.println(finalResult);
			System.out.println("Continue?(Y/N) ");
			expression=scanString.next();
		}
	}
	public static Double postfixEvaluate(String exp)
	{
		Stack <Operand> operandStack= new Stack<Operand>();
		Scanner scanner= new Scanner(System.in);
		for (int i=0;i<exp.length()-1;i++)// i put exp.length()-1 because the last character is $ is not part of the expression
		{
			char currChar=exp.charAt(i);
			if(!isOperator(currChar))
			{
				Operand op = new Operand();
				op.setOperand(currChar);
				Double val = isAlreadyRead(currChar,operandStack);
				if(val == Double.MIN_VALUE){
					System.out.println("Please enter value for " + currChar);// if it is MIN_VALUE, ask for the value
					double o = scanner.nextDouble();
					op.setOperandValue(o); 
				}else{
					op.setOperandValue(val);
				}
				
				operandStack.push(op);
			}
			else{
				Operand op1 = operandStack.pop(); // pop 2 values from stack
				Operand op2 = operandStack.pop();
				Operand result = evaluate(op1,op2,currChar);
				operandStack.push(result);
			}
			
		}
		return operandStack.pop().getOperandValue();
		
	}
	
	private static Double isAlreadyRead(Character operand,Stack <Operand> operandStack){
		for(Operand o : operandStack){
			if(o.getOperand().equals(operand))
				return o.getOperandValue();// if value is read, return the value
		}
		return Double.MIN_VALUE; // if not read, return MIN_VALUE
	}
	
	private static Operand evaluate(Operand op1,Operand op2,Character operator)
	{
		Operand numb= new Operand();
		if(operator.equals('+') )
		{			
			numb.setOperandValue(op2.getOperandValue() + op1.getOperandValue());
		}
		else if(operator.equals('-')){
			numb.setOperandValue(op2.getOperandValue() - op1.getOperandValue());
		}
		else if(operator.equals('*')){
			numb.setOperandValue(op2.getOperandValue() * op1.getOperandValue());
		}
		else if(operator.equals('/')) {
			try{
				numb.setOperandValue(op2.getOperandValue() / op1.getOperandValue());
			}catch(Exception ex){
				throw ex;// can not divide 0
			}
		}
		numb.setOperand(' ');
		return numb;
	}
	
	public static boolean isOperator(char op)
	{
		switch(op)
		{
		case'+':
			return true;
		case'-':
			return true;
		case'*':
			return true;
		case'/':
			return true;
		default:
			return false;
		
		}
		
	}
}
