abstract public class Piece {
	protected Player owner;
	protected String color;
	private BoardCoordinate location;
	
	public Player getOwner() {
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
}
