import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece{
	public Rook(Player owner, String color) {
		this.owner = owner;
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public List<BoardCoordinate>moves(int x, int y){
		
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		
		int xPos = x;
		int yPos = y;
		
		for(int i = 0; i < ChessBoard.BOARD_LENGTH;i++) {
			coordinates.add(new BoardCoordinate(xPos, i));
			coordinates.add(new BoardCoordinate(i, yPos));
		}
		
		return coordinates;		
	}

}
