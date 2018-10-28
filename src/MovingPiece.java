
public class MovingPiece {
	private Piece piece;
	private double srcX;
	private double srcY;
	private double desX;
	private double desY;
	private double currentX;
	private double currentY;
	
	public MovingPiece(Piece piece, double srcX, double srcY, double desX, double desY) {
		this.piece = piece;
		this.srcX = srcX;
		this.srcY = srcY;
		this.desX = desX;
		this.desY = desY;
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
