interface ValidMoveVisitor {
	List<BoardCoordinate> calculateValidMoves(King);
	List<BoardCoordinate> calculateValidMoves(Queen);
	List<BoardCoordinate> calculateValidMoves(Bishop);
	List<BoardCoordinate> calculateValidMoves(Knight);
	List<BoardCoordinate> calculateValidMoves(Rook);
	List<BoardCoordinate> calculateValidMoves(Pawn);
	List<BoardCoordinate> calculateValidMoves(Piece);
}

interface ValidMoveVisitorHost {
	List<BoardCoordinate> accept(ValidMoveVisitor visitor);
}

abstract public class Piece implements ValidMoveVisitorHost {
	protected Player owner;
	protected String color;
	private BoardCoordinate location;

	public Player getPlayer() {
		return owner;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setCoordinate(BoardCoordinate location) {
		this.location = location;
	}
	
	public BoardCoordinate getCoordinate() {
		return location;
	}

	public List<BoardCoordinate> accept(ValidMoveVisitor visitor) {
		return visitor.calculateValidMoves(this);
	}
}
