import java.util.LinkedList;
import java.util.List;

public class Validator {
	public static final int LEFT = -1;
	public static final int DOWN = -1;
	public static final int UP = 1;
	public static final int RIGHT = 1;
	public static final int NOOP = 0;
	ChessBoard board;

	public Validator(ChessBoard board) {
		this.board = board;
	}

	private static boolean isSameColor(Piece p1, Piece p2) {
		return p1.getColor().equals(p2.getColor());
	}

	public List<BoardCoordinate> calculateValidMoves(Piece piece) {
		return null;
	}

	public List<BoardCoordinate> calculateValidMoves(Pawn pawn) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();

		BoardCoordinate coor = pawn.getCoordinate();

		int x = coor.getX();
		int y = coor.getY();

		int[] X = {0, 0, 1, -1};
		int[] Y = {2, 1, 1, 1};

		if(pawn.getFirstMove()) {
			
			pawn.setFirstMove(false);

			getValidMoves(coordinates, x, y, NOOP, UP);
			getValidMoves(coordinates, x, y, NOOP, UP+1);

		}
		else 
			getValidMoves(coordinates, x, y, NOOP, UP);



		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Rook rook) {
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

	private void addValid(List<BoardCoordinate> coordinates, Piece source, Piece destination) {
		if(destination == null || !isSameColor(source, destination)) {
			coordinates.add(destination.getCoordinate());
		}
	}

	public List<BoardCoordinate> calculateValidMoves(Knight knight) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coordinate = knight.getCoordinate();
		int x = coordinate.getX();
		int y = coordinate.getY();
		Piece source = board.getPiece(x, y);

		addValid(coordinates, source, board.getPiece(x + 2, y - 1));
		addValid(coordinates, source, board.getPiece(x + 2, y + 1));
		addValid(coordinates, source, board.getPiece(x - 2, y - 1));
		addValid(coordinates, source, board.getPiece(x - 2, y + 1));
		addValid(coordinates, source, board.getPiece(x + 1, y - 2));
		addValid(coordinates, source, board.getPiece(x + 1, y + 2));
		addValid(coordinates, source, board.getPiece(x - 1, y - 2));
		addValid(coordinates, source, board.getPiece(x - 1, y + 2));

		return coordinates;
	}

	public List<BoardCoordinate> calculateValidMoves(Bishop bishop){

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

	public List<BoardCoordinate> calculateValidMoves(Queen queen){

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

	public List<BoardCoordinate> calculateValidMoves(King king) {
		List<BoardCoordinate>coordinates = new LinkedList<BoardCoordinate>();
		BoardCoordinate coordinate = king.getCoordinate();

		for(
				int x = coordinate.getX(), y = coordinate.getY(), i = -1, j = -1;
				i < 2 && j < 2;
				i++, j++
				) {
			Piece source = board.getPiece(x, y);
			Piece destination = board.getPiece(x + i, y + j);

			if(
					(i != 0 || j != 0) && 
					(destination == null || !isSameColor(source, destination))
					) {
				coordinates.add(destination.getCoordinate());
			}
		}
		return coordinates;
	}

	public boolean isValidMove(List<BoardCoordinate> validMoves, BoardCoordinate move) {
		for(BoardCoordinate validMove: validMoves) {
			if(move.getX() == validMove.getX() && move.getY() == validMove.getY()) {
				return true;
			}
		}
		return false;
	}

	private void getValidMoves(List<BoardCoordinate> coordinates, int xPos, int yPos, int horizontal, int vertical) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		for(
				int x = xPos + horizontal, y = yPos + vertical; 
				(x >= 0 && x <= 7) && (y >= 0 && y <= 7); 
				x += horizontal, y += vertical
				) {
			if(board.getPiece(x,y) == null || !isSameColor(board.getPiece(x,y),board.getPiece(x,y))) {
				coordinates.add(new BoardCoordinate(x,y));
			}

			if(board.getPiece(x, y) != null) {
				break;
			}
		}
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
