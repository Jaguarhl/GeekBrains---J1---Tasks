/* 
 * Console calclator
 * @author Dmitry Kartsev
 * @version 28/08/2016
*/

public class Calculator {
		
	// calculate here and return result
	public static int doCalc(int a, String op, int b) { // let's calculate our simple math
		int result = 0;
		
		switch(op) {
			case "+": result = a + b;
			break;
			case "-": result = a - b;
			break;
			// we can't use * in command-line because of it's a reserved sybmbol for OS
			case "X":
			case "x": result = a * b;
			break;
			case "/": result = a / b;
			break;
		}
		return result;		
	}
	
	public static boolean checkString(String string) { // check, if this string is integer
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
	public static void main(String args[]) {

		// let's see, if there was params in command line
		if(args.length != 0) {
			if((checkString(args[0])) && (checkString(args[2]))) {
				System.out.println("Result: " + doCalc(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2])));
			}
			else
				System.out.println("Please enter integer!");
		}
		else { // display some help to user
			System.out.println("USAGE: calculator NUMBER1 OPERATION NUMBER2");
			System.out.println("OPERATIONS:");
			System.out.println("+ (addition)");
			System.out.println("- (subtraction)");
			System.out.println("/ (division)");
			System.out.println("x (multiply)");
		}
	}
}