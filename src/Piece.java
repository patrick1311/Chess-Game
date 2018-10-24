import java.util.List;

abstract public class Piece {
		
	public abstract List<BoardCoordinate> moves(int x, int y);
	public abstract String getColor();
	public abstract String getName();

}
