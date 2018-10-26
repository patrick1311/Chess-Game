import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece{
	
	private String color;
	
	public Queen(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public List<BoardCoordinate> moves(int x, int y){
		
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


}
