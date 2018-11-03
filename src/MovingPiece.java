public class MovingPiece {
	private Piece piece;
	private double currentX;
	private double currentY;
	
	public MovingPiece(Piece piece) {
		this.piece = piece;
		this.currentX = piece.getCoordinate().getX() * Display.TILE_SIZE;
		this.currentY = piece.getCoordinate().getY() * Display.TILE_SIZE;
	}
	
	public void update(double x, double y) {
		currentX = x;
		currentY = y;
	}
	
	public double getX() {
		return currentX;
	}
	
	public double getY() {
		return currentY;
	}
	
	public Piece getPiece() {
		return piece;
	}
}