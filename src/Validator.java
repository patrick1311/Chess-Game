import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Validator {
	
	ChessBoard board;

	public Validator(ChessBoard board) {
		this.board = board;
	}
	
	public boolean isSameColor(Piece p1, Piece p2) {

		if(p1.getColor().equals(p2.getColor()))
			return true;

		return false;
	}

	public List<BoardCoordinate> calculateValidMoves(Pawn pawn){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		int xPos = x;
		int yPos = y;

		int[] X = {0, 0, 1, -1};
		int[] Y = {2, 1, 1, 1};

		if(pawn.getFirstMove()) {
			pawn.setFirstMove(false);

			for(int i = 0; i < X.length; i++) {
				coordinates.add(new BoardCoordinate(xPos+=X[i], yPos+=Y[i]));
			}

			return coordinates;	
		}
		else {

			for(int i = 1; i < X.length; i++) {
				coordinates.add(new BoardCoordinate(xPos+=X[i], yPos+=Y[i]));
			}

			return coordinates;
		}

	}

	public List<BoardCoordinate> calculateValidMoves(Rook rook){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		int xPos = x;
		int yPos = y;

		for(int i = 0; i < ChessBoard.BOARD_LENGTH;i++) {
			coordinates.add(new BoardCoordinate(xPos, i));
			coordinates.add(new BoardCoordinate(i, yPos));
		}

		return coordinates;	

	}

	public List<BoardCoordinate> calculateValidMoves(Knight knight){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		int xPos = x;
		int yPos = y;

		int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
		int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

		for(int i = 0; i < ChessBoard.BOARD_LENGTH; i++) {
			coordinates.add(new BoardCoordinate(xPos+=X[i],yPos+=Y[i]));
		}


		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Bishop bishop){
		ArrayList<BoardCoordinate>coordinates = new ArrayList<BoardCoordinate>();

		//Diagonal to the bottom right
		for(int xPos = x, yPos = y; xPos < ChessBoard.BOARD_LENGTH;) {
			coordinates.add(new BoardCoordinate(xPos+=1, yPos+=1));
		}

		//Diagonal to the top right
		for(int xPos = x, yPos = y; xPos <= 0;) {
			coordinates.add(new BoardCoordinate(xPos-=1, yPos+=1));
		}

		//Diagonal to the bottom left
		for(int xPos = x, yPos = y; xPos <= ChessBoard.BOARD_LENGTH;) {
			coordinates.add(new BoardCoordinate(xPos+=1, yPos-=1));
		}

		//Diagonal to the top left
		for(int xPos = x, yPos = y; xPos < 0; xPos++) {
			coordinates.add(new BoardCoordinate(xPos-=1, yPos-=1));
		}

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Queen queen){

		List<BoardCoordinate> coordinates = new LinkedList<>();

		//Diagonal to the bottom right
		for(int xPos = x, yPos = y; xPos < ChessBoard.BOARD_LENGTH;) {
			coordinates.add(new BoardCoordinate(xPos+=1, yPos+=1));
		}

		//Diagonal to the top right
		for(int xPos = x, yPos = y; xPos <= 0;) {
			coordinates.add(new BoardCoordinate(xPos-=1, yPos+=1));
		}

		//Diagonal to the bottom left
		for(int xPos = x, yPos = y; xPos <= ChessBoard.BOARD_LENGTH;) {
			coordinates.add(new BoardCoordinate(xPos+=1, yPos-=1));
		}

		//Diagonal to the top left
		for(int xPos = x, yPos = y; xPos < 0; xPos++) {
			coordinates.add(new BoardCoordinate(xPos-=1, yPos-=1));
		}

		for(int i = 0; i < ChessBoard.BOARD_LENGTH;i++) {
			coordinates.add(new BoardCoordinate(x, i));
			coordinates.add(new BoardCoordinate(i, y));
		}

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(King king){

		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		int[] X = { 0, 0, 1, -1, 1, -1, 1, -1};
		int[] Y = { 1, -1, 0, 0, 1, 1, -1, -1};

		int xPos = x;
		int yPos = y;

		for(int i = 0; i < 8; i++) {
			coordinates.add(new BoardCoordinate(xPos+=X[i],yPos+=Y[i]));
		}


		return coordinates ;	
	}





}
