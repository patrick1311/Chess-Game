public class Game {
	private Player white;
	private Player black;
	private Player currentPlayer;
	private ChessBoard board;
	private Validator validator;
	
	public Game(Player white, Player black) {
		this.white = white;
		this.black = black;
		currentPlayer = white;
		board = new ChessBoard();
		setupBoard();
		validator = new Validator();
	}
	
	public void setupBoard() {
		//White
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
		
		//Black
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
		Piece selectedPiece;
		BoardCoordinate validCoordinate;
		do {
			selectedPiece = null;
			validCoordinate = null;
			//BoardCoordinate selectedTile = actionListener input
			do {
			selectedPiece = currentPlayer.selectTile();
			validCoordinate = currentPlayer.selectTile();
			currentPlayer.move(selectedPiece, validCoordinate);
			} while(selectedPiece != null && validCoordinate != null);
			//display.drawboard
			
			if(validator.isValidMove(validMoves, move)) {
				
			}
			
			//check player click or not
				//update board, p1, p2, currentplayer
				//then draw the rest
			if(validator.underCheckmate(currentPlayer)) {
				//display victory
				break;
			}
			else if(validator.isDraw()) {
				//Draw
				break;
			}
		} while(true);
		//display options
	}
}