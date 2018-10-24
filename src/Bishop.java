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
		
		return coordinates;
 
		
	}

	@Override
	public String getName() {
		return "bishop";
	}

}
