import java.util.ArrayList;
import java.util.HashMap;

public class Queen extends Piece{
	
	private String color;
	
	public Queen(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public ArrayList<BoardCoordinate> moves(int x, int y){
		
		ArrayList<BoardCoordinate> coordinates = new ArrayList<>();
		
		return coordinates;
	}

	@Override
	public String getName() {
		return "queen";
	}

}
