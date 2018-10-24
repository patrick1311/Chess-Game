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
	}
	
	public void setupBoard() {
		
		Piece[][] b = board.getBoard();
		
		b[0][0] = new Rook("black");
		b[1][0] = new Knight("black");
		b[2][0] = new Bishop("black");
		b[3][0] = new Queen("black");
		b[4][0] = new King("black");
		b[5][0] = new Bishop("black");
		b[6][0] = new Knight("black");
		b[7][0] = new Rook("black");
		
		for(int i = 0; i < 8; i++) {
			b[i][1] = new Pawn("black");
		}
		
		b[0][7] = new Rook("white");
		b[1][7] = new Knight("white");
		b[2][7] = new Bishop("white");
		b[3][7] = new Queen("white");
		b[4][7] = new King("white");
		b[5][7] = new Bishop("white");
		b[6][7] = new Knight("white");
		b[7][7] = new Rook("white");
		
		for(int i = 0; i < 8; i++) {
			b[i][6] = new Pawn("white");
		}
		
		board.setBoard(b);
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
