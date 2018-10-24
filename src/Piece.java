import java.util.ArrayList;

abstract public class Piece {
		
	public abstract ArrayList<BoardCoordinate> moves(int x, int y);
	public abstract String getColor();
	public abstract String getName();

}
