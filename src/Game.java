import java.util.LinkedList;

public class Game {
	private Player white;
	private Player black;
	private Player currentPlayer;
	private ChessBoard board;
	private Validator validator;
	private Piece selectedPiece;
	
	public Game(Player white, Player black) {
		this.white = white;
		this.black = black;
		currentPlayer = white;
		board = new ChessBoard();
		setupBoard();
		validator = new Validator(board);
		selectedPiece = null;
	}
	
	public void setupBoard() {
		board.getBoard()[4][7] = white.getPieceList().get(0);	//King
		board.getBoard()[3][7] = white.getPieceList().get(1);	//Queen
		board.getBoard()[0][7] = white.getPieceList().get(2);	//Rook
		board.getBoard()[7][7] = white.getPieceList().get(3);	//Rook
		board.getBoard()[2][7] = white.getPieceList().get(4);	//Bishop
		board.getBoard()[5][7] = white.getPieceList().get(5);	//Bishop
		board.getBoard()[1][7] = white.getPieceList().get(6);	//Knight
		board.getBoard()[6][7] = white.getPieceList().get(7);	//Knight
		for(int i = 0; i < 8; i++) {
			board.getBoard()[i][6] = white.getPieceList().get(8 + i);	//Pawn
		}
		
		board.getBoard()[4][0] = black.getPieceList().get(0);	//King
		board.getBoard()[3][0] = black.getPieceList().get(1);	//Queen
		board.getBoard()[0][0] = black.getPieceList().get(2);	//Rook
		board.getBoard()[7][0] = black.getPieceList().get(3);	//Rook
		board.getBoard()[2][0] = black.getPieceList().get(4);	//Bishop
		board.getBoard()[5][0] = black.getPieceList().get(5);	//Bishop
		board.getBoard()[1][0] = black.getPieceList().get(6);	//Knight
		board.getBoard()[6][0] = black.getPieceList().get(7);	//Knight
		for(int i = 0; i < 8; i++) {
			board.getBoard()[i][1] = black.getPieceList().get(8 + i);	//Pawn
		}
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public void run() {
		do {
			selectTile();
			if(validator.underCheckmate(currentPlayer)) {
				//display victory
				break;
			}
			else if(validator.isDraw()) {
				//display draw
				break;
			}
			changeTurn();
		} while(true);
		//display options
	}
	
	private void selectTile() {
		BoardCoordinate tile = null;//Listener
		//display.clearHighlights();
		Piece currentPiece = board.getPiece(tile.getX(), tile.getY());
		if(currentPiece != null 
				&& currentPlayer.equals(currentPiece.getPlayer())
				&& !currentPiece.getCoordinate().equals(tile)) {//Unsure if this works
			selectedPiece = currentPiece;
			//LinkedList<BoardCoordinates> moves = validator.calculateValidMoves(selectedPiece);
			//display.highlightTiles(moves);
			selectTile();
		}
		else {
			LinkedList<BoardCoordinate> moves = null;//validator.calculateValidMoves(selectedPiece);//moves == class variable?
			if(selectedPiece != null && validator.isValidMove(moves, tile)) {
				move(selectedPiece, tile);
				//display.drawMove(selectedPiece, tile);
			}
			else {
				selectTile();
			}
			selectedPiece = null;
		}
	}
	
	private void move(Piece piece, BoardCoordinate tile) {
		
	}
	
	private void changeTurn() {
		if(currentPlayer == white) {
			currentPlayer = black;
		}
		else {
			currentPlayer = white;
		}
	}
}