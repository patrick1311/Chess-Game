import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Piece{

	private String color;
	
	public Rook(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public ArrayList<BoardCoordinate>moves(int x, int y){
		
		ArrayList<BoardCoordinate>coordinates = new ArrayList<BoardCoordinate>();
		
		int xPos = x;
		int yPos = y;
		
		for(int i = 0; i < 8;i++) {
			coordinates.add(new BoardCoordinate(xPos, i));
			coordinates.add(new BoardCoordinate(i, yPos));
		}
		
		return coordinates;		
	}

	@Override
	public String getName() {
		return "rook";
	}

}
