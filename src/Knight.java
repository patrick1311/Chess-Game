import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece{
	
	private String color;
		
	public Knight(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	//Make HashMap of all possibles that a piece can make at current position.
	//A validator will check if they can move there if not remove from possible moves.

	public List<BoardCoordinate> moves(int x, int y){
		
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

}
