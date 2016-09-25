/*
 * OOP level 2 example (with ArrayList implementation for child classes)
 * @author Dmitry Kartsev
 * @version 08/09/2016
*/

import java.text.*;
import java.util.ArrayList; // plug ArrayList

class Math {
	public static void main(String args[]) {
		
		System.out.println(doubleExpression(0.1, 0.2, 0.3));
	}
	
	public static boolean doubleExpression(double a, double b, double c) {
		return a*1000 + b*1000 == c*1000;
	}
}