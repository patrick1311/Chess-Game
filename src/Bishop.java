import java.util.List;

public class Bishop extends Piece {
	public Bishop(Player owner, String color) {
		this.owner = owner;
		this.color = color;
	}

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.calculateValidMoves(this);
	}
}