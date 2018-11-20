import java.util.List;
import javax.swing.JOptionPane;

public class Game {
	private Player white;
	private Player black;
	private Player currentPlayer;
	private Player waitingPlayer;
	private ChessBoard board;
	private Validator validator;
	private Piece selectedPiece;
	private int turn;
	private int lastCapture;
	private int lastPawnMove;
	private boolean isRunning;
	
	public Game(Player p1, Player p2) {
		p1.initialize("White");
		p2.initialize("Black");
		this.white = p1;
		this.black = p2;
		currentPlayer = white;
		waitingPlayer = black;
		board = new ChessBoard();
		setupBoard();
		validator = new Validator(board);
		selectedPiece = null;
		turn = 1;
		isRunning = true;
	}
	
	private void setupBoard() {
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
	
	public void selectTile(BoardCoordinate tile, Display display) {
		List<BoardCoordinate> moves;
		display.clearHighlights();
		Piece tilePiece = board.getPiece(tile.getX(), tile.getY());
		if(tilePiece != null &&
			currentPlayer.equals(tilePiece.getPlayer()) &&
			selectedPiece != tilePiece //Don't select same piece
		) {
			selectedPiece = tilePiece;
			moves = tilePiece.accept(validator);
			display.setHighlights(moves);
			display.setSourceHighlight(tilePiece.getCoordinate());
			display.setEnemyHighlights(validator.filterForEnemyHighlights(moves, selectedPiece));
		}
		else if(selectedPiece != null) {
			moves = selectedPiece.accept(validator);
			if(moves.contains(tile)) {
				display.drawMove(selectedPiece, tile);
				move(selectedPiece, tile, display);
			}
			selectedPiece = null;
		}
	}
	
	private void move(Piece piece, BoardCoordinate tile, Display display) {
		int graveyardSize = waitingPlayer.getGraveyard().size();
		board.move(piece, tile);
		
		if(piece instanceof Pawn) {
			lastPawnMove = turn;
			if(validator.legalPromotion((Pawn) piece, tile)) {
				promote(piece);
			}
		}
		
		if(waitingPlayer.getGraveyard().size() > graveyardSize) {
			lastCapture = turn;
		}
		
		checkGameStatus(display);
		changeTurn();
	}
	
	private void checkGameStatus(Display display) {
		String message1 = null;
		String message2 = null;
		if(validator.underCheckmate(waitingPlayer)) {
			message1 = "Checkmate!";
			message2 = currentPlayer.getColor() + " wins!";
		}
		else if(validator.isStalemate(waitingPlayer)) {
			message1 = "Stalemate!";
			message2 = "Draw!";
		}
		else if(validator.isFiftyMove(turn, lastCapture, lastPawnMove)) {
			message1 = "Fifty-move rule!";
			message2 = "Draw!";
		}
		else {
			return;
		}
		isRunning = false;
		display.setMessages(message1, message2);
		display.repaint();
	}
	
	private void changeTurn() {
		if(currentPlayer == white) {
			currentPlayer = black;
			waitingPlayer = white;
		}
		else {
			currentPlayer = white;
			waitingPlayer = black;
			turn++;
		}
	}
	
	private void promote(Piece piece) {
		BoardCoordinate coor = piece.getCoordinate();
		int x = coor.getX(), y = coor.getY();
		
		String[] promote = {"Queen", "Knight", "Rook", "Bishop"};
		
		int n = JOptionPane.showOptionDialog(null,
			    "What do you want to promote to?",
			    "Choose a piece:",
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null, promote, promote[0]);
		
		if(n == 0) {
			board.getBoard()[x][y] = new Queen(piece.getPlayer(),piece.getColor());
		}
		else if(n == 1) {
			board.getBoard()[x][y] = new Knight(piece.getPlayer(),piece.getColor());
		}
		else if(n == 2) {
			board.getBoard()[x][y] = new Rook(piece.getPlayer(),piece.getColor());
		}
		else if(n == 3) {
			board.getBoard()[x][y] = new Bishop(piece.getPlayer(),piece.getColor());
		}
		currentPlayer.removePiece(piece);
		board.getBoard()[x][y].setCoordinate(new BoardCoordinate(x,y));
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
}