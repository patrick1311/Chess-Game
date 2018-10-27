import java.util.LinkedList;
import java.util.List;

public class Validator implements ValidMoveVisitor {
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int NOOP = 0;
	ChessBoard board;

	public Validator(ChessBoard board) {
		this.board = board;
	}

	private static boolean isSameColor(Piece p1, Piece p2) {
		return p1.getColor().equals(p2.getColor());
	}

	public List<BoardCoordinate> calculateValidMoves(final Piece piece) {
		return null;
	}

	public List<BoardCoordinate> calculateValidMoves(final Pawn pawn) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coor = pawn.getCoordinate();
		int x = coor.getX();
		int y = coor.getY();
		int direction; 
		String color = pawn.getColor();
		Piece source = board.getPiece(x, y);
		
		if(color.equals("white")) {
			direction = UP;
		}
		else {
			direction = DOWN;
		}
		if(board.getPiece(x, y + direction) == null) {
			coordinates.add(new BoardCoordinate(x, y + direction));
			
			if(board.getPiece(x, y + (direction * 2)) == null && pawn.getFirstMove()) {
				coordinates.add(new BoardCoordinate(x, y + (direction * 2)));
			}
		}
		
		if(board.getPiece(x + RIGHT, y + direction) != null && !isSameColor(source, board.getPiece(x + RIGHT, y + direction) )) {
			coordinates.add(board.getPiece(x + RIGHT, y + direction).getCoordinate());
		}
		if(board.getPiece(x + LEFT, y + direction) != null && !isSameColor(source, board.getPiece(x + LEFT, y + direction) )) {
			coordinates.add(board.getPiece(x + LEFT, y + direction).getCoordinate());
		}
		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(final Rook rook) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coor = rook.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();

		getValidMoves(coordinates, x, y, RIGHT, NOOP);
		getValidMoves(coordinates, x, y, LEFT, NOOP);
		getValidMoves(coordinates, x, y, NOOP, UP);
		getValidMoves(coordinates, x, y, NOOP, DOWN);

		return coordinates;	
	}

	private void addValid(List<BoardCoordinate> coordinates, Piece source, int x, int y) {
		Piece destination = board.getPiece(x, y);
		if(destination == null || !isSameColor(source, destination)) {
			coordinates.add(new BoardCoordinate(x, y));
		}
	}

	public List<BoardCoordinate> calculateValidMoves(final Knight knight) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coordinate = knight.getCoordinate();
		int x = coordinate.getX();
		int y = coordinate.getY();
		Piece source = board.getPiece(x, y);

		addValid(coordinates, source, x + 2, y - 1);
		addValid(coordinates, source, x + 2, y + 1);
		addValid(coordinates, source, x - 2, y - 1);
		addValid(coordinates, source, x - 2, y + 1);
		addValid(coordinates, source, x + 1, y - 2);
		addValid(coordinates, source, x + 1, y + 2);
		addValid(coordinates, source, x - 1, y - 2);
		addValid(coordinates, source, x - 1, y + 2);

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(final Bishop bishop){
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coor = bishop.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();

		getValidMoves(coordinates, x, y, RIGHT, UP); 
		getValidMoves(coordinates, x, y, LEFT, DOWN); 
		getValidMoves(coordinates, x, y, LEFT, UP);
		getValidMoves(coordinates, x, y, RIGHT, DOWN);

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(final Queen queen){
		List<BoardCoordinate> coordinates = new LinkedList<>();
		BoardCoordinate coor = queen.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();


		getValidMoves(coordinates, x, y, RIGHT, UP); 
		getValidMoves(coordinates, x, y, LEFT, DOWN); 
		getValidMoves(coordinates, x, y, LEFT, UP);
		getValidMoves(coordinates, x, y, RIGHT, DOWN);
		getValidMoves(coordinates, x, y, RIGHT, NOOP);
		getValidMoves(coordinates, x, y, LEFT, NOOP);
		getValidMoves(coordinates, x, y, NOOP, UP);
		getValidMoves(coordinates, x, y, NOOP, DOWN);

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(final King king) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coordinate = king.getCoordinate();
		int x = coordinate.getX(), y = coordinate.getY();
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i == 0 && j == 0 || (x + i < 0 || x + i > 7) || (y + j < 0 || y + j > 7)) {
					continue;
				}
				Piece source = board.getPiece(x, y);
				Piece destination = board.getPiece(x + i, y + j);
				if(destination == null || !isSameColor(source, destination)) {
					coordinates.add(new BoardCoordinate(x + i, y + j));
				}
			}
		}
		return coordinates;
	}

	private void getValidMoves(List<BoardCoordinate> coordinates, int xPos, int yPos, int horizontal, int vertical) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		for(
			int x = xPos + horizontal, y = yPos + vertical;
			(x >= 0 && x <= 7) && (y >= 0 && y <= 7);
			x += horizontal, y += vertical
		) {
			if(board.getPiece(x, y) == null || !isSameColor(board.getPiece(x, y), board.getPiece(xPos, yPos))){
				coordinates.add(new BoardCoordinate(x, y));
			}

			if(board.getPiece(x, y) != null) {
			    break;            
			}
		}
	}
	
	public List<BoardCoordinate> filterForEnemyHighlights(List<BoardCoordinate> moves) {
		List<BoardCoordinate> enemyHighlights = new LinkedList<BoardCoordinate>();
		int x, y;
		for(BoardCoordinate move: moves) {
			x = move.getX();
			y = move.getY();
			if(board.getPiece(x, y) != null) {
				enemyHighlights.add(move);
			}
		}
		return enemyHighlights;
	}

	public boolean underCheck(Player currentPlayer) {
		return false;//King is in validMove of enemy piece?
	}

	public boolean underCheckmate(Player currentPlayer) {
		return false;//If currentPlayer's King is under check & no validMoves 
		//for any of player's pieces
	}

	public boolean isStalemate() { //private?
		return false; //If no valid moves but not under check
	}

	public boolean isDraw() {
		return false;//stalemate
		//fifty-move rule
		/*dead position? no sequence of legal moves can lead to checkmate, 
		 * most commonly when neither player has sufficient 
		 * material to checkmate the opponent.
		 */
	}
}