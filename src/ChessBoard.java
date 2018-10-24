import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ChessBoard {
	
	public static final int TILE_SIZE = 80;
	
	
	public void draw(Graphics2D g2d) {

		drawTiles(g2d);
		//drawPieces(g2d);
	}
	
	public void drawPieces(Graphics2D g2d) {
		
		String pieceName;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				
			}
		}
	}
	
	public void drawTiles(Graphics2D g2d) {
		//paint the board tiles
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 8; i++) {
				if(j%2 == 0) {
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
					i++;
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
				else {
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
					i++;
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}	
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(3*TILE_SIZE, 0*TILE_SIZE, TILE_SIZE, TILE_SIZE);
		g2d.drawImage(getKing(), 240, 0, null);
	}
	
	public Image getKing(){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/white_king.png"));
		return image.getImage();
	}
}
