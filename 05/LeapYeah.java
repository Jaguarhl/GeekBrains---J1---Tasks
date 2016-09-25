/*
 * OOP level 2 example (with ArrayList implementation for child classes)
 * @author Dmitry Kartsev
 * @version 08/09/2016
*/

import java.text.*;
import java.util.ArrayList; // plug ArrayList

class LeapYeah {
	public static void main(String args[]) {
		
		System.out.println(leapYearCount(100));
	}
	
	public static int leapYearCount(int year) {
		int ly = 0;
		for(int i = 1; i <= year; i++) {
			if(((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) ly++;
		}
		return ly;
	}
}