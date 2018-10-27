import java.util.List;

public class Game {
	private Player white;
	private Player black;
	private Player currentPlayer;
	private ChessBoard board;
	private Validator validator;
	private Piece selectedPiece;
	
	public Game(Player p1, Player p2) {
		p1.initialize("white");
		p2.initialize("black");
		this.white = p1;
		this.black = p2;
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
		board.getBoard()[2][7] = white.getPieceList().get(3);	//Bishop
		board.getBoard()[1][7] = white.getPieceList().get(4);	//Knight
		board.getBoard()[7][7] = white.getPieceList().get(5);	//Rook
		board.getBoard()[5][7] = white.getPieceList().get(6);	//Bishop
		board.getBoard()[6][7] = white.getPieceList().get(7);	//Knight
		for(int i = 0; i < 8; i++) {
			board.getBoard()[i][6] = white.getPieceList().get(8 + i);	//Pawn
		}
		
		board.getBoard()[4][0] = black.getPieceList().get(0);	//King
		board.getBoard()[3][0] = black.getPieceList().get(1);	//Queen
		board.getBoard()[0][0] = black.getPieceList().get(2);	//Rook  
		board.getBoard()[2][0] = black.getPieceList().get(3);	//Bishop
		board.getBoard()[1][0] = black.getPieceList().get(4);	//Knight
		board.getBoard()[7][0] = black.getPieceList().get(5);	//Rook  
		board.getBoard()[5][0] = black.getPieceList().get(6);	//Bishop
		board.getBoard()[6][0] = black.getPieceList().get(7);	//Knight
		for(int i = 0; i < 8; i++) {
			board.getBoard()[i][1] = black.getPieceList().get(8 + i);	//Pawn
		}
		
		board.getBoard()[4][7].setCoordinate(new BoardCoordinate(4, 7));
		board.getBoard()[3][7].setCoordinate(new BoardCoordinate(3, 7));
		board.getBoard()[0][7].setCoordinate(new BoardCoordinate(0, 7));
		board.getBoard()[2][7].setCoordinate(new BoardCoordinate(2, 7));
		board.getBoard()[1][7].setCoordinate(new BoardCoordinate(1, 7));
		board.getBoard()[7][7].setCoordinate(new BoardCoordinate(7, 7));
		board.getBoard()[5][7].setCoordinate(new BoardCoordinate(5, 7));
		board.getBoard()[6][7].setCoordinate(new BoardCoordinate(6, 7));
		board.getBoard()[4][0].setCoordinate(new BoardCoordinate(4, 0));
		board.getBoard()[3][0].setCoordinate(new BoardCoordinate(3, 0));
		board.getBoard()[0][0].setCoordinate(new BoardCoordinate(0, 0));
		board.getBoard()[2][0].setCoordinate(new BoardCoordinate(2, 0));
		board.getBoard()[1][0].setCoordinate(new BoardCoordinate(1, 0));
		board.getBoard()[7][0].setCoordinate(new BoardCoordinate(7, 0));
		board.getBoard()[5][0].setCoordinate(new BoardCoordinate(5, 0));
		board.getBoard()[6][0].setCoordinate(new BoardCoordinate(6, 0));
		for(int i = 0; i < 8; i++) {
			board.getBoard()[i][6].setCoordinate(new BoardCoordinate(i, 6));
			board.getBoard()[i][1].setCoordinate(new BoardCoordinate(i, 1));
		}
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public void run() {/*
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
		//display options*/
	}
	
	public void selectTile(BoardCoordinate tile, Display display) {
		List<BoardCoordinate> moves;
		display.clearHighlights();
		Piece currentPiece = board.getPiece(tile.getX(), tile.getY());
		if(currentPiece != null 
				&& currentPlayer.equals(currentPiece.getPlayer())
				&& !currentPiece.getCoordinate().equals(tile)) { //Don't select same piece
			selectedPiece = currentPiece;
			moves = selectedPiece.accept(validator);
			display.highlightTiles(moves);
		}
		else {
			moves = selectedPiece.accept(validator); //moves == class variable?
			if(selectedPiece != null && validator.isValidMove(moves, tile)) {
				move(selectedPiece, tile);
				display.drawMove(selectedPiece, tile);
			}
			selectedPiece = null;
		}
	}
	
	private void move(Piece piece, BoardCoordinate tile) {
		board.move(piece, tile);
		checkGameStatus();
		changeTurn();
	}
	
	private void checkGameStatus() {
		if(validator.underCheckmate(currentPlayer)) {
			
		}
		else if(validator.isDraw()) {
			
		}
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
