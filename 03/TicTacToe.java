/* 
 * Console calclator Scanner Input
 * @author Dmitry Kartsev
 * @version 28/08/2016
*/

import java.util.*;

public class TicTacToe {
	
	char[][] field = new char [3][3];
	final char PLAYER_DOT = 'x';
	final char AI_DOT = 'o';
	final char EMPTY_DOT = '.';
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
		
	public static void main(String args[]) {
		new TicTacToe().go();
	}
	
	void go() {
		initField();
        while (true) {
            playerTurn();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry, draft...");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("COMPUTER WON!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry, draft...");
                break;
            }
        }
	}
	
	void initField() {
		for (int i = 0; i < 3; i++) {
			for (int j=0; j < 3; j++) {
				field[i][j] = EMPTY_DOT;
			}
		}
	}
	
	void printField() {
		for (int i = 0; i < 3; i++) {
			for (int j=0; j < 3; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void playerTurn() {
		int x, y;
        do {
            System.out.println("Enter X and Y (1-3):");
            x = sc.nextInt();
            y = sc.nextInt();
        } while (!isCellEmpty(x - 1, y - 1));
        field[x - 1][y - 1] = PLAYER_DOT;
	}
	
	void aiTurn() {
		int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!isCellEmpty(x, y));
        field[x][y] = AI_DOT;		
	}
	
	boolean isCellEmpty(int x, int y) {
		if (x < 0 || y < 0 || x > 2 || y > 2) return false;
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
	}
	
	boolean isFieldFull() {
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;		
	}
	
	boolean checkWin(char dot) {
		// check horizontals
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        // check verticals
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
        // check diagonals
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[2][0] == dot && field[1][1] == dot && field[0][2] == dot) return true;
        return false;
	}
}