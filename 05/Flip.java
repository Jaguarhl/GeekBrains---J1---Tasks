/*
 * OOP level 2 example (with ArrayList implementation for child classes)
 * @author Dmitry Kartsev
 * @version 08/09/2016
*/

import java.text.*;
import java.util.ArrayList; // plug ArrayList

class Flip {
	public static void main(String args[]) {
		
		System.out.println(flipBit(-2, 1));
	}
	
	/**
	* Flips one bit of the given <code>value</code>.
	*
	* @param value     any number
	* @param bitIndex  index of the bit to flip, 1 <= bitIndex <= 32
	* @return new value with one bit flipped
	*/
	public static int flipBit(int value, int bitIndex) {
		/*String access = Integer.toString(value);
		
		char[] buf = new char[access.length()];
		access.getChars(0, access.length(), buf, 0);		
		if(buf[bitIndex-1] == '0') buf[bitIndex-1] = '1';
		else buf[bitIndex-1] = '0';
		String str = new String(buf);	*/		
		
		/*return Integer.parseInt(str, 2)*/ // put your implementation here
		int bitMask = 1;
		bitMask <<= bitIndex - 1;
		return value ^ bitMask;
	}
}