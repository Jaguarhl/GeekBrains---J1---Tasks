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
                if(res > 0) System.out.println("����� " + args[0] + " ������� � ����� " + args[1] + ", � ��� ����������� " + res + " ���(�).");
                else System.out.println("����� " + args[0] + " � ����� " + args[1] + " �� �������.");
            }
            else System.out.println("��� ������ ��������� ��������� ���������: �����_���_������ ���_�����");
		}
		else System.out.println("���������� �������� ���������: �����_���_������_������ ����_���_������");
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
        catch (IOException �) {
            System.out.println("��������� ������ ��� ������ � �������");
            return -1;        
        }
    }
}