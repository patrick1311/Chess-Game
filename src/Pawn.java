public class Pawn extends Piece{

	protected Player owner;
	protected String color;
	private boolean firstMove;
	
	public Pawn(String color) {
		this.color = color;
	}

	
	public String getColor() {
		return color;
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

}
