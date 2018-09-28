
public class Validator {
	
	public boolean isSameColor(Piece p1, Piece p2) {
		
		if(p1.getColor().equals(p2.getColor()))
			return true;

		return false;
	}
	
	
	
}
