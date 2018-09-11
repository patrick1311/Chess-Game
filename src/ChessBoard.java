import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class ChessBoard extends JPanel {
	
	public static final int TILE_SIZE = 80;
	public Piece p[][] = new Piece[8][8];	
	
	public void paint(Graphics g) {
		
		//paint the board tiles
		for(int j = 0; j < p.length; j++) {
			for(int i = 0; i < p.length; i++) {
				if(j%2 == 0) {
					g.setColor(Color.WHITE);
					g.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
					i++;
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
				else {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
					i++;
					g.setColor(Color.WHITE);
					g.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
				
			}
		}
		
		
		
	}
}
