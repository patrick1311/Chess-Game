
public class Knight extends Piece{
	
	private String color;
		
	public Knight(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public void move() {
		// Move in L shape
		
	}

	@Override
	public String getName() {
		return "knight";
	}

}
