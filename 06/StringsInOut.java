/*
 * Input Output from / to file task 1 & 2
 * @author Dmitry Kartsev
 * @version 10/09/2016
*/

import java.io.*; 
import java.util.*; 
 
public class StringsInOut {
	public static void main(String args[]) {
        if(args.length != 0) {
            if((args.length == 3) && (args[0] != "") && (args[1] != "") && (args[2] != "")) {
                if(splitFiles(args[0], args[1], args[2])) System.out.println("Операция успешно выполнена. Результат записан в файл " + args[2]);
            }
            else if((args.length == 2) && (args[0] != "") && (args[1] != "")) {
                if(splitFilesAndWriteToFirst(args[0], args[1])) System.out.println("Операция успешно выполнена. Результат записан в файл " + args[0]);
            }
            else System.out.println("Для объединения 2-х файлов в третий, надо передать программе в командной строке следующие параметры: ИМЯ_ФАЙЛА_1 ИМЯ_ФАЙЛА_2 ИМЯ_ФАЙЛА_ЗАПИСИ\n---------------------\nЛибо для присоединения к 1-му файлу второго используйте параметры: ИМЯ_ЦЕЛЕВОГО_ФАЙЛА ИМЯ_ПРИСОЕДИНЯЕМОГО_ФАЙЛА");
		}
		else {
            // writing 4 different files by 2 different methods
            if((generateFile(75, 'X', "out1.txt", false)) && (generateFile(100, 'Y', "out2.txt", false)) && (generateFile(95, 'Z', "out3.txt", true)) && (generateFile(87, 'Q', "out4.txt", true))) System.out.println("Файлы успешно сформированы и записаны");
            else System.out.println("Что-то пошло не так (");
        }
	}
	
	// let's generate new file with defined size in characters, fill it with needed character and write to a file with specified filename (with serialize or standrt text methods)
	public static boolean generateFile(int size, char symbol, String filename, boolean serialize) {
		StringBuffer a = new StringBuffer(""); // let's create StringBuffer to fill it with chars and write it to disk later
        for(int i =0; i < size; i++) {
            a.append(symbol);
        }

        try {		
            if(serialize) {
                FileOutputStream f_out =  new FileOutputStream(filename); // file I/O object
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
            return false;
        }
        
        return true;
	}
    
    public static boolean splitFiles(String fin1, String fin2, String fout) { // filename 1, filename 2, and filename to create
        int i;
        FileInputStream fn1 = null;
        FileInputStream fn2 = null;
        FileOutputStream f_out = null;
        try {
            fn1 = new FileInputStream(fin1);
            fn2 = new FileInputStream(fin2);
            f_out = new FileOutputStream(fout) ; // creating new file to write
            do { // coping data from first file
                i = fn1.read();
                if (i != -1) f_out.write(i) ;
            } while (i != -1);
            if (fn1 != null) fn1.close();
            do { // and do the same for second file
                i = fn2.read();
                if (i != -1) f_out.write(i) ;
            } while (i != -1);
            if (fn2 != null) fn2.close();
            if (f_out != null) f_out.close();
        }
        catch (IOException е) {
            System.out.println("Произошла ошибка при работе с файлами");
            return false;        
        }
        return true;        
    }
    
    public static boolean splitFilesAndWriteToFirst(String fin1, String fin2) {
        File file1 = new File(fin1);
        File file2 = new File(fin2);
        
        try {
            // open first file for writing
            BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(file1, true)); // true - attaching to the end of file
            
            // open second file for reading
            BufferedInputStream bufRead = new BufferedInputStream(new FileInputStream(file2));
            int n;
            while((n = bufRead.read()) != -1) {
                bufOut.write(n);
            }
            
            bufOut.flush();      // flush data from buffer
            bufOut.close();     // close connection
            bufRead.close();  // close connection
        }
        catch (IOException е) {
            System.out.println("Произошла ошибка при работе с файлами");
            return false;        
        }
        
        return true;
    }
}