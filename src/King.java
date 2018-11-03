import java.util.List;

public class King extends Piece {
	private boolean hasMoved;
	
	public King(Player owner, String color) {
		this.owner = owner;
		this.color = color;
		this.hasMoved = false;
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.calculateValidMoves(this);
	}
}