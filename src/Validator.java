import java.util.ArrayList;

public class Validator {
	
	public boolean isSameColor(Piece p1, Piece p2) {
		
		if(p1.getColor().equals(p2.getColor()))
			return true;

		return false;
	}
	
	public ArrayList<BoardCoordinate> validMoves(ArrayList<BoardCoordinate> moves){
		
		for(int i = 0; i < moves.size(); i++) {
			BoardCoordinate move = moves.get(i);
			
			
		}
		
		return moves;
	}
	
	
	
}
