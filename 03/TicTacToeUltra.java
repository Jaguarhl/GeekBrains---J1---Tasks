/* 
 * Console TicTacToe Ultra Game
 * @author Dmitry Kartsev
 * @version 01/09/2016
*/

import java.util.*;

public class TicTacToeUltra {
	
	final int FIELD_SIZE = 5; // size of field
	final int neededPoints = 4; // how much points in a line needed for win
	char[][] field = new char [FIELD_SIZE][FIELD_SIZE]; // an array for game field
	final char PLAYER_DOT = 'X'; // sign for empty field square
	final char AI_DOT = 'O'; // sign for player 2 turns
	final char EMPTY_DOT = '.'; // sign for player 1 turns
	Scanner sc = new Scanner(System.in); // init Scanner for player input
	Random rand = new Random(); // init Random utility

	public static void main(String args[]) {
		new TicTacToeUltra().go();
	}
	
	public void go() {
		initField();
		
		while (true) {
            playerTurn();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("¬€ ¬€»√–¿À»!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("”ÔÒ, ÌË˜¸ˇ...");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("¬ Õ≈–¿¬ÕŒ… ¡Œ–‹¡≈ œŒ¡≈ƒ»À »— ”—“¬≈ÕÕ€… »Õ“≈ÀÀ≈ “!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("”ÔÒ, ÌË˜¸ˇ...");
                break;
            }
        }
	}
	
	public void initField() { // init game field
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
			{
				field[i][j] = EMPTY_DOT;
			}
		}
	}
	
	public void printField() { // display game field 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
			{
				System.out.print(field[j][i]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void playerTurn() {
		int x, y;
        do {
            System.out.println("¬‚Â‰ËÚÂ ÍÓÓ‰ËÌ‡Ú˚ X Ë Y (1-5):");
            x = sc.nextInt();
            y = sc.nextInt();
        } while (!isCellEmpty(x - 1, y - 1));
        field[x - 1][y - 1] = PLAYER_DOT;
	}
	
	void aiTurn() {
		int x, y;
		// let's check, if AI can win
		int[] coords = checkLastTurn(AI_DOT);
		
		if (coords[0]!=-1 && coords[1] !=-1) field[coords[0]][coords[1]] = AI_DOT;
		else {
			// let's check, if player can win next turn
			coords = checkLastTurn(PLAYER_DOT);
			
			if (coords[0]!=-1 && coords[1] !=-1) field[coords[0]][coords[1]] = AI_DOT; // ha-ha, you will not win, player ^_^
			else {
				do {
					x = rand.nextInt(5);
					y = rand.nextInt(5);
				} while (!isCellEmpty(x, y));

				field[x][y] = AI_DOT;
			}
		}
	}
	
	boolean isCellEmpty(int x, int y) {
		if (x < 0 || y < 0 || x > 4 || y > 4) return false;
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
	}
	
	boolean isFieldFull() {
		for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;		
	}
	
	boolean checkHorizontal(char dot){
		int points = 0;
		
		for (int i = 0; i < field.length; i++)
		{	
			for (int j = 0; j < field.length; j++)	{
				if (field[i][j] == dot) points++;
				else points = 0;
				
				if(points == neededPoints) return true;
			}
			points = 0; // we need to do it for ich row
		}
		
		return false;
	}
	
	boolean checkVertical(char dot){
		int points = 0;
		
		for (int j = 0; j < field.length; j++)
		{	
			for (int i = 0; i < field.length; i++)	{
				if (field[i][j] == dot) points++;
				else points = 0;
				
				if(points == neededPoints) return true;
			}
			points = 0;	// we need to do it for ich column
		}
		
		return false;
	}
	
	boolean checkDiagonal1(char dot){
		int[] points = new int [3]; // an array for different diagonals elements calc
		for (int i= 0; i < 3; i++) points[i] = 0;		
		
		for (int i = 0; i < field.length; i++)
		{	
			if (field[i][i] == dot) points[1]++; // checking first diagonal (1,1; 2,2; 3,3..)
			else points[1] = 0;
			
			if (i + 1 < field.length) if (field[i][i+1] == dot) points[0]++; // checking second diagonal (1,2; 2,3; 3,4..)
			else points[0] = 0;
			
			if (i - 1 >= 0) if (field[i][i-1] == dot) points[2]++; // checking third diagonal (1,0; 2,1; 3,2..)
			else points[2] = 0;
			
			if(points[1] == neededPoints || points[0] == neededPoints || points[2] == neededPoints) return true;
		}		
		
		return false;
	}
	
	boolean checkDiagonal2(char dot){
		int[] points = new int [3]; // an array for different diagonals elements calc
		for (int i= 0; i < 3; i++) points[i] = 0;
		
		for (int i = 0; i < field.length; i++)
		{	
			if (field[field.length - 1 - i][i] == dot) points[1]++; // checking first diagonal (5,1; 4,2; 3,3..)
			else points[1] = 0;
			
			if (i + 1 < field.length) if (field[field.length - i - 1][i+1] == dot) points[0]++; // checking first diagonal (5,2; 4,3; 3,4..)
			else points[0] = 0;
			
			if (i - 1 >= 0) if (field[field.length - i - 1][i-1] == dot) points[2]++; // checking first diagonal (4,1; 3,2; 2,3..)
			else points[2] = 0;
			
			if(points[1] == neededPoints || points[0] == neededPoints || points[2] == neededPoints) return true;
		}
		
		return false;
	}
	
	boolean checkWin(char dot) {
		if(checkHorizontal(dot) || checkVertical(dot) || checkDiagonal1(dot) || checkDiagonal2(dot)) return true;
		else return false;
	}
	
	int[] checkLastTurn(char dot) {
		int x, y; // what coords we need to win
		for (x = 0; x < field.length; x++)
		{	
			for (y = 0; y < field.length; y++)	{
				if (isCellEmpty(x, y)) {
					field[x][y] = dot;
					if (checkWin(dot)) {
						field[x][y] = EMPTY_DOT;
						return new int[] {x, y};
					}
					else {
						field[x][y] = EMPTY_DOT;
					}
				}
			}			
		}
		
		return new int[] {-1, -1};
	}
}