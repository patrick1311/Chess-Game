public class Pawn extends Piece {
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

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.visit(this);
	}
}
