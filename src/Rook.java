
public class Rook extends Piece{

	private String color;
	
	public Rook(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	@Override
	public void move() {
		// Move vertical and horizontal 
		
	}

	@Override
	public String getName() {
		return "rook";
	}

}
