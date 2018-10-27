public class King extends Piece {
	public King(Player owner, String color) {
		this.owner = owner;
		this.color = color;
	}

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.calculateValidMoves(this);
	}
}
