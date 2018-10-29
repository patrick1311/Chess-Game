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
		int x = coor.getX(), y = coor.getY(), direction; 
		String color = pawn.getColor();
		Piece source = board.getPiece(x, y);

		if(color.equals("white")) 
			direction = UP;
		else
			direction = DOWN;

		if(board.getPiece(x, y + direction) == null) {
			coordinates.add(new BoardCoordinate(x, y + direction));

			if(board.getPiece(x, y + (direction * 2)) == null && pawn.getFirstMove()) {
				coordinates.add(new BoardCoordinate(x, y + (direction * 2)));
			}
		}

		if(board.getPiece(x + RIGHT, y + direction) != null && !isSameColor(source, board.getPiece(x + RIGHT, y + direction) )) 
			coordinates.add(board.getPiece(x + RIGHT, y + direction).getCoordinate());

		if(board.getPiece(x + LEFT, y + direction) != null && !isSameColor(source, board.getPiece(x + LEFT, y + direction) )) 
			coordinates.add(board.getPiece(x + LEFT, y + direction).getCoordinate());

		legalEnPassant(pawn, coordinates, direction, x, y);
		legalPromotion(pawn, x, y);
		return coordinates;
	}

	/*
	 * EnPassant Rules
	 * 1) Capturing pawn must be on the fifth rank. Fifth row of its respective color.
	 * 2) Captured piece must be a pawn and have just performed its first move as a two step.
	 * 3) Must be done immediately after captured pawn move or else it cannot be done again. 
	 * 
	 */

	public boolean legalEnPassant(Pawn pawn, List<BoardCoordinate> coordinates, int direction, int x, int y) {
		String color = pawn.getColor();

		int fifthRank = 3;

		if(color.equals("black"))
			fifthRank += 1;

		MoveHistory capture = board.getPreviousMove();
		
		if(y == fifthRank && capture.getPiece() instanceof Pawn) {

			if(capture.getMove().getY() == y && capture.getMove().getX() == x+1 || capture.getMove().getX() == x-1) {

				Piece toCapture = capture.getPiece();

				if(toCapture != null && !isSameColor(pawn, toCapture) && !((Pawn) toCapture).getFirstMove()) 
					coordinates.add(new BoardCoordinate(toCapture.getCoordinate().getX(), toCapture.getCoordinate().getY() + direction));

			}

			return true;	
		}

		return false;
	}

	public boolean legalPromotion(Pawn pawn, int x, int y) {
		String color = pawn.getColor(); 

		int pos = 0;

		if(color.equals("Black"))
			pos = 7;

		if(x == pos)
			return true;

		return false;
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

		legalCastling(king, coordinates, x, y);

		return coordinates;
	}

	//Used for castling checking between rook position and king position
	public boolean emptyBetweenRow(Piece p1, Piece p2) {

		BoardCoordinate piece1 = p1.getCoordinate();
		BoardCoordinate piece2 = p2.getCoordinate();

		int start = piece1.getY(), end = piece2.getY();

		if(start > end) {
			int temp = end;
			end = start;
			start = temp;
		}

		for(; start < end; start++ ) {
			if(board.getPiece(piece1.getX(), start) != null)
				return false;
		}

		return true;
	}

	/*
	 * Castling Rules
	 * 1) Cannot castle when king or castling rook has been moved.
	 * 2) Cannot castle when king is in check.
	 * 3) Cannot castle through a check. 
	 * 4) Cannot have pieces between castling rook and king.
	 * 
	 */

	public boolean legalCastling(final King king, List<BoardCoordinate> coordinates, int x, int y) {

		String color = king.getColor();

		int row = 0;

		if(color.equals("Black")) {
			row = 7;
		}

		// Determine if board for current player is in check
		// If current player is in check and king has moved
		if(true && !king.getHasMoved()) {

			Piece leftRook = board.getPiece(row, 0);
			Piece rightRook = board.getPiece(row, 7);

			if(leftRook instanceof Rook && isSameColor(king, board.getPiece(row, 0)) && ((Rook) leftRook).getHasMoved() && emptyBetweenRow(leftRook, king)) {
				coordinates.add(new BoardCoordinate(x, y - 2));

				//Check if moving king will create a check 
				//This needs to be done after checking whether castling is done
				BoardCoordinate rook = leftRook.getCoordinate();
				rook.setY(y + 3);
			}

			if(rightRook instanceof Rook && isSameColor(king, board.getPiece(row, 7)) && ((Rook) rightRook).getHasMoved() && emptyBetweenRow(king, rightRook)) {	
				coordinates.add(new BoardCoordinate(x, y + 2));

				//Check if moving king will create a check 
				//This needs to be done after checking whether castling is done
				BoardCoordinate rook = leftRook.getCoordinate();
				rook.setY(y - 2);

			}
			return true;

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