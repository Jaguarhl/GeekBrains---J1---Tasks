/* 
 * Console calclator Scanner Input
 * @author Dmitry Kartsev
 * @version 28/08/2016
*/

import java.util.Scanner;

public class Calculator2 {
		
	// calculate here and return result
	public static int doCalc(int a, String op, int b) { // let's calculate our simple math
		int result = 0;
		
		switch(op) {
			case "+": result = a + b;
			break;
			case "-": result = a - b;
			break;
			// we can't use * in command-line because of it's a reserved sybmbol for OS
			case "*": result = a * b;
			break;
			case "/": result = a / b;
			break;
		}
		return result;		
	}
	
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in); // creating scanner
		System.out.println("¬ведите первое число:");
		int a = sc.nextInt(); 
		String operation;
		boolean math_input = false;
		// getting first number(int)
		do { 
			System.out.println("¬ведите операцию (+ - / *):");
			operation = sc.next();                         // getting operation
			if ((operation.contains("*")) || (operation.contains("+")) || (operation.contains("-")) || (operation.contains("/")))
				math_input = true;
		} while (math_input == false);
		System.out.println("¬ведите второе число:");
		int b = sc.nextInt(); // getting second number(int)
		
		// let's do a work
		System.out.println("–≈«”Ћ№“ј“: " + doCalc(a, operation, b));
	}
}