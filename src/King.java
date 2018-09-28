
public class King extends Piece{
	
	private String color;
	
	public King(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public void move() {
		// Move 1 tile all around 
		
	}

	@Override
	public String getName() {
		return "king";
	}

}
