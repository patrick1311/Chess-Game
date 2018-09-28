
public class Bishop extends Piece{
	
	private String color;

	public Bishop(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	@Override
	public void move() {
		// Move in diagonal 
		
	}

	@Override
	public String getName() {
		return "bishop";
	}

}
