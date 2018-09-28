
public class Queen extends Piece{
	
	private String color;
	
	public Queen(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	@Override
	public void move() {
		// Move all around		
	}

	@Override
	public String getName() {
		return "queen";
	}

}
