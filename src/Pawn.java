import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece{

	private String color;
	private boolean firstMove;
	
	public Pawn(String color) {
		this.color = color;
		this.firstMove = true;
	}
	
	public String getColor() {
		return color;
	}
	
	public List<BoardCoordinate> moves(int x, int y){
		
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		
		int xPos = x;
		int yPos = y;
		
		int[] X = {0, 0, 1, -1};
		int[] Y = {2, 1, 1, 1};
		
		if(firstMove) {
			firstMove = false;
			
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

	@Override
	public String getName() {
		return "pawn";
	}

}
