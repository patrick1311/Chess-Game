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
		p1 = new Player("WHITE");
		p2 = new Player("BLACK");
		board = new ChessBoard();
		p = new Piece[8][8];

	}
	
	public void gameLoop() {
		while(inGame) {
			//if(mouse click)m
			//player.move() and repaint() is called after event happen
			repaint();
		}
		
		Graphics2D g2d = (Graphics2D) g;
		board.draw(g2d);

	}

	
	
}
