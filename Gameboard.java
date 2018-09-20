import java.util.Random;

public class Gameboard {
	
	int[][] answerBoard;
	String[][] playBoard;
	
	public Gameboard() {
		this.answerBoard = new int[4][4];
		this.playBoard = new String[4][4];
		Random rand = new Random();           // populates the answer board and display board arrays for the start of a game
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				answerBoard[j][i] = rand.nextInt(8)+1;
				playBoard[j][i] = "-";
			}
		}
	}
	
	
	public boolean guess(String playArray[][], int answerArray[][], int guessX1, int guessY1, int guessX2, int guessY2) {
		if(answerArray[guessX1][guessY1] == answerArray[guessX2][guessY2]) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean isGuessed(int guessX, int guessY, String[][] array) {
		if (array[guessX][guessY] != "-") {
			return true;
		}  else {return false;}
	}
	public String[][] updateBoard(String playArray[][], int answerArray[][], int x, int y) {
		playArray[x][y] = Integer.toString(answerArray[x][y]);
		return playArray;
	}
	public String toString(String[][] array) {
		String stringBoard = "";
		for(int i = 0; i < 4; i++) {
			stringBoard = stringBoard + System.lineSeparator() + "|";
			for(int j = 0; j < 4; j++) {
				stringBoard =  stringBoard + array[j][i] + "|";
			}
		}
		return stringBoard;
	}
	
	
	
	
}
