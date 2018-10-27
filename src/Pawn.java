public class Pawn extends Piece {
	protected Player owner;
	protected String color;
	private boolean firstMove;
	
	public Pawn(Player owner, String color) {
		this.owner = owner;
		this.color = color;
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}
