import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel {

	public boolean inGame = false;
	public Player p1;
	public Player p2;
	public ChessBoard board;
	public String currentPlayer;
	
	public Game() {
		p1 = new Player("WHITE");
		p2 = new Player("BLACK");
		board = new ChessBoard();
		
	}
	
	public void gameLoop() {
		while(inGame) {
			//player.move() and repaint() is called after event happen
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		board.draw(g2d);
	}
	
	
}
