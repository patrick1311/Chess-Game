import java.util.ArrayList;

public class Pawn extends Piece{

	private String color;
	private boolean firstMove = true;
	
	public Pawn(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public ArrayList<BoardCoordinate> moves(int x, int y){
		
		ArrayList<BoardCoordinate>coordinates = new ArrayList<BoardCoordinate>();
		
		for(int i = 0; i < ChessBoard.BOARD_LENGTH; i++) {
			
		}
		

		return coordinates;
		
	}

	@Override
	public String getName() {
		return "pawn";
	}

}
