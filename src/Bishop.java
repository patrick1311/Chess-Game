import java.util.ArrayList;

public class Bishop extends Piece{
	
	private String color;

	public Bishop(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public ArrayList<BoardCoordinate> moves(int x, int y){
		
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

}
