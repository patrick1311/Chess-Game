
public class Pawn extends Piece{

	private String color;
	
	public Pawn(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	@Override
	public void move() {
		
		

	}

	@Override
	public String getName() {
		return "pawn";
	}

}
