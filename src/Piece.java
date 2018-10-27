import java.util.List;

interface ValidMoveVisitor {
	List<BoardCoordinate> calculateValidMoves(Piece piece);
	List<BoardCoordinate> calculateValidMoves(King piece);
	List<BoardCoordinate> calculateValidMoves(Queen piece);
	List<BoardCoordinate> calculateValidMoves(Rook piece);
	List<BoardCoordinate> calculateValidMoves(Bishop piece);
	List<BoardCoordinate> calculateValidMoves(Knight piece);
	List<BoardCoordinate> calculateValidMoves(Pawn piece);
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