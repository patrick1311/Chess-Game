import javax.swing.JPanel;

public class Game extends JPanel {

	public boolean inGame = false;
	public Player p1;
	public Player p2;
	public ChessBoard board;
	public String currentPlayer;

	public Display display;
	
	public Game() {
		p1 = new Player("WHITE");
		p2 = new Player("BLACK");
		board = new ChessBoard();	
		
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
