/*
 * Input Output from / to file task 1 & 2
 * @author Dmitry Kartsev
 * @version 10/09/2016
*/

import java.io.*; 
import java.util.*; 
 
public class StringsInOut {
	public static void main(String args[]) {
        // writing 2 different files
        if((generateFile(75, 'X', "out1.txt", false)) && (generateFile(100, 'Y', "out2.txt", false)) && (generateFile(95, 'Z', "out3.txt", true)) && (generateFile(87, 'Q', "out4.txt", true))) System.out.println("Файлы успешно сформированы и записаны");
        else System.out.println("Что-то пошло не так (");
	}
	
	// let's generate new file with defined size in characters, fill it with needed character and write to a file with specified filename (with serialize or standrt text methods)
	public static boolean generateFile(int size, char symbol, String filename, boolean serialize) {
		boolean result = true;
        StringBuffer a = new StringBuffer(""); // let's create StringBuffer to fill it with chars and write it to disk later
        for(int i =0; i < size; i++) {
            a.append(symbol);
        }

        try {		
            FileOutputStream f_out =  new FileOutputStream(filename); // file I/O object
            if(serialize) {
                ObjectOutputStream os = new ObjectOutputStream(f_out); // serialization object
                os.writeObject(a);
                os.close();
            }
            else {
                // using text utility object (filename, append)
                FileWriter ow = new FileWriter(filename, false);
                ow.write(a.toString());
                ow.flush();
            }
        }
        catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлами");
            result = false;
        }
        
        return result;
	}
}