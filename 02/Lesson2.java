/* 
 * Tasks for second lesson
 * @author Dmitry Kartsev
 * @version 27/08/2016
*/

import java.util.Arrays; // we need it to work with arrays (arr to String)
import java.util.Random; // we need it to create random array

public class Lesson2 {
	static int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 }; //declare array
	static int[] arr2 = new int[8]; //declare new array for genMatrix2()
	static int[] mas = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 }; //declare array for calcMatrix()
	static int[] arr3 = new int[20]; //declare array for minmaxArrayElements()
	static int min, max = 0; //here we will put min and max elements of random array
	
	
	public static void genMatrix() { // let's regenerate elemnts in array
		for(int i = 0; i < 10; i++) {
			arr[i] = Math.abs(arr[i] - 1);
		}
	}
	
	public static void genMatrix2() { // let's generate new array
		arr2[0] = 1; // setting first element to 1
		for(int i = 1; i < 8; i++) {
			arr2[i] = arr2[i-1] + 3;
		}
	}
	
	public static void calcMatrix() { // let's some calc with array
		for(int i = 0; i < 12; i++) {
			if(mas[i] < 6)
				mas[i] = mas[i] * 2;			
		}
	}
	
	public static void minmaxArrayElements() { // let's some calc with array
		Random rand = new Random(); //init Random
		
		//we will generate array and search fo min / max elemnts if one cycle 
		for(int i = 0; i < 20; i++) {
			arr3[i] = rand.nextInt(100);
			if ( arr3[min] > arr3[i] ) min = i;
            if ( arr3[max] < arr3[i] ) max = i;
		}
	}
	
	public static void main(String args[]) { // here we will call our methods to get results
		// we need to replace values in array
		System.out.println("--------------   Task #1   --------------");
		System.out.print("Original array: ");
		System.out.println(Arrays.toString(arr));
		genMatrix();
		System.out.print("After inversion: ");
		System.out.println(Arrays.toString(arr));
		for(int i=0; i<4; i++) {
			System.out.println(""); 
		}
		
		// let's generate values in new array
		System.out.println("--------------   Task #2   --------------");
		genMatrix2();
		System.out.print("Generating new array: ");
		System.out.println(Arrays.toString(arr2));
		for(int i=0; i<4; i++) {
			System.out.println(""); 
		}
		
		// let's replace multiply values < 6
		System.out.println("--------------   Task #3   --------------");
		System.out.print("Original array: ");
		System.out.println(Arrays.toString(mas));
		calcMatrix();
		System.out.print("If elemt < 6, then multiply it for 2: ");
		System.out.println(Arrays.toString(mas));
		for(int i=0; i<4; i++) {
			System.out.println(""); 
		}
		
		minmaxArrayElements();
		System.out.println("--------------   Task #4   --------------");
		System.out.print("Array with random elements: ");
		System.out.println(Arrays.toString(arr3));
		System.out.println("And MIN & MAX elements here:");
		System.out.println("MIN:" + arr3[min]);
		System.out.println("MAX:" + arr3[max]);
		
	}
}