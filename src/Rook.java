import java.util.List;

public class Rook extends Piece {
	private boolean hasMoved;
	
	public Rook(Player owner, String color) {
		this.owner = owner;
		this.color = color;
		this.setHasMoved(false);
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