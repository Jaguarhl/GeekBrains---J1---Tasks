/*
 * Input Output from / to file task 1 & 2
 * @author Dmitry Kartsev
 * @version 10/09/2016
*/

import java.io.*; 
import java.util.*; 
 
public class FindStringInFile {
	public static void main(String args[]) {
        if(args.length != 0) {
            if((args.length == 2) && (args[0] != "") && (args[1] != "")) {
                int res = searchInFile(args[0], args[1]);
                if(res > 0) System.out.println("Слово " + args[0] + " найдено в файле " + args[1] + ", и оно повторяется " + res + " раз(а).");
                else System.out.println("Слово " + args[0] + " в файле " + args[1] + " не найдено.");
            }
            else System.out.println("Для поиска передайте программе параметры: СЛОВО_ДЛЯ_ПОИСКА ИМЯ_ФАЙЛА");
		}
		else System.out.println("Необходимо передать параметры: СЛОВО_ДЛЯ_ПОИСКА_ПОИСКА ФАЙЛ_ДЛЯ_ПОИСКА");
	}
	
	// search for a word in specified file
    public static int searchInFile(String s_word, String fname) {
        int count = 0;
        File file = new File(fname);
                      
        try {
            // open file for reading
            BufferedReader bufRead = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = bufRead.readLine()) != null) {
                int index = -1;
                while((index = line.indexOf(s_word,index+1)) != -1){
                    count++;
                }
            }
            bufRead.close();  // close connection
            return count;                    
        }
        catch (IOException е) {
            System.out.println("Произошла ошибка при работе с файлами");
            return -1;        
        }
    }
}