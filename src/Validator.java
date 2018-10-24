import java.util.List;

public class Validator {
	
	public boolean isSameColor(Piece p1, Piece p2) {
		
		if(p1.getColor().equals(p2.getColor()))
			return true;

		return false;
	}
	
	public List<BoardCoordinate> validMoves(List<BoardCoordinate> moves){
		
		for(int i = 0; i < moves.size(); i++) {
			BoardCoordinate move = moves.get(i);
			
			if(move.getX() > 8 || move.getY() > 8)
				moves.remove(move);
			
			
			
		}
		
		return moves;
	}
	
	
	
}
