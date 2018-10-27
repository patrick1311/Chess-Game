public class Rook extends Piece {
	public Rook(Player owner, String color) {
		this.owner = owner;
		this.color = color;
	}

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.visit(this);
	}
}
