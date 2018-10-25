import javax.swing.JPanel;

public class Game extends JPanel {

	public boolean inGame = false;
	public Player p1;
	public Player p2;
	public ChessBoard board;
	public Piece p[][]; 
	public String currentPlayer;

	public Display display;
	
	public Game() {
		p1 = new Player("white");
		p2 = new Player("black");
		board = new ChessBoard();
		setupBoard();
	}
	
	public void setupBoard() {
		Piece[][] b = board.getBoard();
		
		//White
		b[4][7] = p1.getPieceList().get(0);	//King
		b[3][7] = p1.getPieceList().get(1);	//Queen
		b[0][7] = p1.getPieceList().get(2);	//Rook
		b[7][7] = p1.getPieceList().get(3);	//Rook
		b[2][7] = p1.getPieceList().get(4);	//Bishop
		b[5][7] = p1.getPieceList().get(5);	//Bishop
		b[1][7] = p1.getPieceList().get(6);	//Knight
		b[6][7] = p1.getPieceList().get(7);	//Knight
		
		for(int i = 0; i < 8; i++) {
			b[i][6] = p1.getPieceList().get(8 + i);	//Pawn
		}
		
		//Black
		b[4][0] = p2.getPieceList().get(0);	//King
		b[3][0] = p2.getPieceList().get(1);	//Queen
		b[0][0] = p2.getPieceList().get(2);	//Rook
		b[7][0] = p2.getPieceList().get(3);	//Rook
		b[2][0] = p2.getPieceList().get(4);	//Bishop
		b[5][0] = p2.getPieceList().get(5);	//Bishop
		b[1][0] = p2.getPieceList().get(6);	//Knight
		b[6][0] = p2.getPieceList().get(7);	//Knight
		
		for(int i = 0; i < 8; i++) {
			b[i][1] = p2.getPieceList().get(8 + i);	//Pawn
		}
		
		board.setBoard(b);
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public void gameLoop() {
		while(inGame) {
			//display.drawboard
			
			//check player click or not
				//update board, p1, p2, currentplayer
				//then draw the rest

		}
		//display options
	}
}
