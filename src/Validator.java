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

		if(color.equals("White")) {
			direction = UP;
		}
		else {
			direction = DOWN;
		}

		if(board.getPiece(x, y + direction) == null) {
			if(!moveStillUnderCheck(source, x, y + direction)) {
				coordinates.add(new BoardCoordinate(x, y + direction));
			}

			if(board.getPiece(x, y + (direction * 2)) == null &&
					pawn.getFirstMove() &&
					!moveStillUnderCheck(source, x, y + (direction * 2))
					) {
				coordinates.add(new BoardCoordinate(x, y + (direction * 2)));
			}
		}

		if(board.getPiece(x + RIGHT, y + direction) != null && 
				!isSameColor(source, board.getPiece(x + RIGHT, y + direction)) &&
				!moveStillUnderCheck(source, x + RIGHT, y + direction)
				) {
			coordinates.add(board.getPiece(x + RIGHT, y + direction).getCoordinate());
		}
		if(board.getPiece(x + LEFT, y + direction) != null &&
				!isSameColor(source, board.getPiece(x + LEFT, y + direction)) &&
				!moveStillUnderCheck(source, x + LEFT, y + direction)
				) {
			coordinates.add(board.getPiece(x + LEFT, y + direction).getCoordinate());
		}
		legalEnPassant(pawn, coordinates, direction, x, y);

		return coordinates;
	}

	/*
	 * EnPassant Rules
	 * 1) Capturing pawn must be on the fifth rank. Fifth row of its respective color.
	 * 2) Captured piece must be a pawn and have just performed its first move as a two step.
	 * 3) Must be done immediately after captured pawn move or else it cannot be done again. 
	 * 
	 */

	private boolean legalEnPassant(Pawn pawn, List<BoardCoordinate> coordinates, int direction, int x, int y) {
		String color = pawn.getColor();
		MoveHistory capture = board.getPreviousMove();
		int fifthRank = 3;

		if(color.equals("Black")) {
			fifthRank = 4;
		}
		if(y == fifthRank && 
				capture.getPiece() instanceof Pawn && 
				capture.getPiece().getCoordinate().getY() == y &&
				capture.getMove().getY() == fifthRank + direction*2 &&
				(capture.getMove().getX() == x+1 || capture.getMove().getX() == x-1)
				) {
			coordinates.add(new BoardCoordinate(capture.getPiece().getCoordinate().getX(), capture.getPiece().getCoordinate().getY() + direction));
			return true;	
		}
		return false;
	}

	/*
	 * After move if at promotion edge
	 * If pawn promote
	 */

	public boolean legalPromotion(Pawn pawn, BoardCoordinate tile) {
		String color = pawn.getColor(); 
		int pos = 0, y = tile.getY();

		if(color.equals("Black"))
			pos = 7;

		return y == pos;
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
		if((x >= 0 && x <= 7 && y >= 0 && y <= 7) &&
				(destination == null || !isSameColor(source, destination)) &&
				!moveStillUnderCheck(source, x, y)
				) {
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

	public List<BoardCoordinate> calculateValidMoves(final Bishop bishop) {
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

	public List<BoardCoordinate> calculateValidMoves(final Queen queen) {
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
				if((i == 0 && j == 0) || 
						(x + i < 0 || x + i > 7) || 
						(y + j < 0 || y + j > 7)
						) {
					continue;
				}
				Piece source = board.getPiece(x, y);
				Piece destination = board.getPiece(x + i, y + j);
				if((destination == null || !isSameColor(source, destination)) &&
						!moveStillUnderCheck(king, x + i, y + j)
						) {
					coordinates.add(new BoardCoordinate(x + i, y + j));
				}
			}
		}

		legalCastling(king, coordinates, x, y);

		return coordinates;
	}

	//Used for castling checking between rook position and king position
	private boolean emptyBetweenRow(Piece p1, Piece p2) {
		BoardCoordinate piece1 = p1.getCoordinate();
		BoardCoordinate piece2 = p2.getCoordinate();

		int start = piece1.getX(), end = piece2.getX();

		if(start > end) {
			int temp = end;
			end = start;
			start = temp;
		}

		for(; start < end - 1; start++ ) {
			if(board.getPiece(start + 1, piece1.getY()) != null) {
				return false;
			}
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

	private boolean legalCastling(final King king, List<BoardCoordinate> coordinates, int x, int y) {
		String color = king.getColor();
		int row = 0;

		if(color.equals("White")) {
			row = 7;
		}

		// Determine if board for current player is in check
		// If current player is in check and king has moved
		if(!moveStillUnderCheck(king, x, y) && !king.getHasMoved()) {
			Piece leftRook = board.getPiece(0, row);
			Piece rightRook = board.getPiece(7, row);

			if(leftRook instanceof Rook 
					&& isSameColor(king, leftRook) 
					&& (!((Rook) leftRook).getHasMoved() && !king.getHasMoved()) 
					&& emptyBetweenRow(leftRook, king) 
					&& !moveStillUnderCheck(king, x - 1, y) 
					&& !moveStillUnderCheck(king, x - 2, y)) {
				coordinates.add(new BoardCoordinate(x - 2, y));
			}

			if(rightRook instanceof Rook 
					&& isSameColor(king, rightRook) 
					&& (!((Rook) rightRook).getHasMoved() && !king.getHasMoved())
					&& emptyBetweenRow(king, rightRook)
					&& !moveStillUnderCheck(king, x + 1, y) 
					&& !moveStillUnderCheck(king, x + 2, y)) {
				coordinates.add(new BoardCoordinate(x + 2, y));
			}
			//Check if moving king will create a check 
			//This needs to be done after checking whether castling is done
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
			if((board.getPiece(x, y) == null || !isSameColor(board.getPiece(x, y), board.getPiece(xPos, yPos))) &&
					!moveStillUnderCheck(board.getPiece(xPos, yPos), x, y)
					) {
				coordinates.add(new BoardCoordinate(x, y));
			}

			if(board.getPiece(x, y) != null) {
				break;            
			}
		}
	}

	public List<BoardCoordinate> filterForEnemyHighlights(List<BoardCoordinate> moves, Piece piece) {
		List<BoardCoordinate> enemyHighlights = new LinkedList<BoardCoordinate>();
		int x, y;
		for(BoardCoordinate move: moves) {
			x = move.getX();
			y = move.getY();
			if(board.getPiece(x, y) != null) {
				enemyHighlights.add(move);
			}
			else if(board.getPiece(x, y) == null && 
					piece instanceof Pawn && 
					x != piece.getCoordinate().getX()
					) {
				if(move.getY() == 2 && piece.getColor().equals("White"))
					enemyHighlights.add(new BoardCoordinate(x, y + 1));
				else if(move.getY() == 5 && piece.getColor().equals("Black")) {
					enemyHighlights.add(new BoardCoordinate(x, y - 1));
				}
				else {
					enemyHighlights.add(new BoardCoordinate(x, y));
				}
			}
		}
		return enemyHighlights;
	}

	private Piece[][] copyBoard() {
		Piece[][] board = this.board.getBoard();
		Piece[][] copy = new Piece[board.length][board[1].length];

		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < copy.length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}

	private boolean moveStillUnderCheck(Piece piece, int moveX, int moveY) {
		Piece[][] lookahead = copyBoard();

		lookahead[piece.getCoordinate().getX()][piece.getCoordinate().getY()] = null;
		lookahead[moveX][moveY] = piece; //Have new lookahead board state after move is made

		int x = 0, y = 0;
		for(int i = 0; i < lookahead.length; i++) {
			for(int j = lookahead[0].length - 1; j >= 0; j--) {
				if(lookahead[i][j] instanceof King && lookahead[i][j].getPlayer() == piece.getPlayer()) {
					x = i;
					y = j;
					break;
				}
			}
		}
		//Have location of king on lookahead board
		//Now calculate if king is still in check

		return underCheck(lookahead, x, y);
	}

	public boolean underCheck(Player currentPlayer) {
		List<Piece> pieces = currentPlayer.getPieceList();
		int x = 0, y = 0;
		for(Piece piece: pieces) {
			if(piece instanceof King) {
				x = piece.getCoordinate().getX();
				y = piece.getCoordinate().getY();
				return underCheck(board.getBoard(), x, y);
			}
		}
		return false;
	}

	private boolean underCheck(Piece[][] board, int x, int y) {
		return kingAttackedDiagonally(board, x, y) ||
				kingAttackedOrthogonally(board, x, y) ||
				kingAttackedByPawn(board, x, y) ||
				kingAttackedByKnight(board, x, y) ||
				kingAttackedByKing(board, x, y);
	}

	private boolean kingAttackedDiagonally(Piece[][] lookahead, int xPos, int yPos) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		int horizontal, vertical, x, y;
		for(int a = 0; a < 4; a++) {
			horizontal = (a / 2 == 0) ? LEFT: RIGHT;
			vertical = (a % 2 == 0) ? UP: DOWN;

			for(
					x = xPos + horizontal, y = yPos + vertical;
					(x >= 0 && x <= 7) && (y >= 0 && y <= 7);
					x += horizontal, y += vertical
					) {
				if(lookahead[x][y] != null) {
					if(!isSameColor(lookahead[x][y], lookahead[xPos][yPos]) &&
							(lookahead[x][y] instanceof Bishop || lookahead[x][y] instanceof Queen)
							) {
						return true;
					}
					else {
						break;
					}
				}
			}
		}
		return false;
	}

	private boolean kingAttackedOrthogonally(Piece[][] lookahead, int xPos, int yPos) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		int horizontal, vertical, x, y;
		for(int a = 0; a < 4; a++) {
			if(a / 2 == 0) {
				horizontal = (a % 2 == 0) ? LEFT: RIGHT;
				vertical = NOOP;
			}
			else {
				horizontal = NOOP;
				vertical = (a % 2 == 0) ? UP: DOWN;
			}

			for(
					x = xPos + horizontal, y = yPos + vertical;
					(x >= 0 && x <= 7) && (y >= 0 && y <= 7);
					x += horizontal, y += vertical
					) {
				if(lookahead[x][y] != null) {
					if(!isSameColor(lookahead[x][y], lookahead[xPos][yPos]) &&
							(lookahead[x][y] instanceof Rook || lookahead[x][y] instanceof Queen)
							) {
						return true;
					}
					else {
						break;
					}
				}
			}
		}
		return false;
	}

	private boolean kingAttackedByKnight(Piece[][] lookahead, int xPos, int yPos) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		int horizontal, vertical, x, y;
		for(int a = 0; a < 8; a++) {
			horizontal = ((a / 2) % 2 == 0) ? LEFT: RIGHT;
			vertical = (a % 2 == 0) ? UP: DOWN;
			if(a / 4 == 0) {
				horizontal *= 2;
			}
			else {
				vertical *= 2;
			}

			x = xPos + horizontal;
			y = yPos + vertical;

			if(((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) &&
					lookahead[x][y] != null &&
					!isSameColor(lookahead[x][y], lookahead[xPos][yPos]) &&
					lookahead[x][y] instanceof Knight
					) {
				return true;
			}
		}
		return false;
	}

	private boolean kingAttackedByKing(Piece[][] lookahead, int xPos, int yPos) {
		int x, y;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i == 0 && j == 0) {
					continue;
				}
				x = xPos + i;
				y = yPos + j;

				if(((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) &&
						lookahead[x][y] != null &&
						!isSameColor(lookahead[x][y], lookahead[xPos][yPos]) &&
						lookahead[x][y] instanceof King
						) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean kingAttackedByPawn(Piece[][] lookahead, int xPos, int yPos) {
		assert xPos >= 0 && xPos <= 7 && yPos >= 0 && yPos <= 7;

		int horizontal, x, y;
		if(lookahead[xPos][yPos].getColor().equals("White") ) {
			y = yPos + UP;
		}
		else {
			y = yPos + DOWN;
		}
		for(int a = 0; a < 2; a++) {
			horizontal = (a % 2 == 0) ? LEFT: RIGHT;
			x = xPos + horizontal;
			if(((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) &&
					lookahead[x][y] != null &&
					!isSameColor(lookahead[x][y], lookahead[xPos][yPos]) &&
					lookahead[x][y] instanceof Pawn
					) {
				return true;
			}
		}
		return false;
	}

	private boolean hasValidMoves(Player player) {
		List<BoardCoordinate> validMoves;
		for(Piece piece: player.getPieceList()) {
			validMoves = piece.accept(this);
			if(validMoves.size() > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean underCheckmate(Player waitingPlayer) {
		if(underCheck(waitingPlayer)) {
			return !hasValidMoves(waitingPlayer);
		}
		return false;
	}

	public boolean isStalemate(Player waitingPlayer) { //private?
		return !hasValidMoves(waitingPlayer);
	}

	public boolean isFiftyMove(int turn, int lastCapture, int lastPawnMove) { //private?
		return turn >= lastCapture + 50 && turn >= lastPawnMove + 50;
	}
	/*
	public boolean isDraw(Player waitingPlayer) {
		return isStalemate(waitingPlayer);//stalemate
		//three-fold repetition
		//fifty-move rule
		//dead position? no sequence of legal moves can lead to checkmate, 
		// most commonly when neither player has sufficient 
		// material to checkmate the opponent.
	}*/
}
