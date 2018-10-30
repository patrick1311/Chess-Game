import java.util.Stack;

public class ChessBoard {
	public static final int TILE_SIZE = 80;
	public static final int BOARD_LENGTH = 8;
	
	private Piece board[][] = new Piece[8][8];
	private Stack<MoveHistory> previousMoves = new Stack<>();

	
	public Piece[][] getBoard() {
		return board;
	}
	
	public Piece getPiece(int x, int y) {
		if(x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
			return board[x][y];
		}
		return null;
	}
	
	public Stack<MoveHistory> getPreviousMoves(){
		return previousMoves;
	}
	
	public MoveHistory getPreviousMove() {
		if(previousMoves.isEmpty())
			return null;
		
		return previousMoves.peek();
	}
	
	public void move(Piece piece, BoardCoordinate tile) {
		int sourceX = piece.getCoordinate().getX();
		int sourceY = piece.getCoordinate().getY();
		int destX = tile.getX();
		int destY = tile.getY();
		int direction = 0; 
		Piece tilePiece = getPiece(destX, destY);
		
		if(tilePiece != null) {
			tilePiece.getPlayer().addToGraveyard(tilePiece);
		}
		
		//System.out.println(destX + " " + destY + " " + sourceX + " " + sourceY);
		
		this.previousMoves.push(new MoveHistory(piece,new BoardCoordinate(sourceX, sourceY)));
		
		if(piece instanceof Pawn) {
			((Pawn)piece).setFirstMove(false);	
			
			String color = piece.getColor(); 
			
			if(destX < sourceX || destX > sourceX) {
				
				if(tilePiece == null) {
					
					if(color.equals("White")) {
						direction = Validator.UP;
					}
					else {
						direction = Validator.DOWN;
					}
					
					board[sourceX][sourceY] = null;
					tilePiece = getPiece(destX, destY-direction);
					tilePiece.getPlayer().addToGraveyard(tilePiece);

					sourceX = destX;
					sourceY = destY-direction;
					
				}
			}	
			
		}
		
		//System.out.println(destX + " " + destY + " " + sourceX + " " + (sourceY - direction));
		
		board[sourceX][sourceY] = null;
		board[destX][destY] = piece;
		piece.setCoordinate(tile);
	}
}