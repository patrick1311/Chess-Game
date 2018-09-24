import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ChessBoard {
	
	public static final int TILE_SIZE = 80;
	public Piece p[][] = new Piece[8][8];
	
	public void draw(Graphics2D g2d) {
		
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
		
		placeStartingPieces(g2d);

	}
	
	public void placeStartingPieces(Graphics2D g2d) {
		
		g2d.drawImage(getRook("black"), 0, 0, null);
		g2d.drawImage(getKnight("black"), 80, 0, null);
		g2d.drawImage(getBishop("black"), 160, 0, null);
		g2d.drawImage(getQueen("black"), 240, 0, null);
		g2d.drawImage(getKing("black"), 320, 0, null);
		g2d.drawImage(getBishop("black"), 400, 0, null);
		g2d.drawImage(getKnight("black"), 480, 0, null);
		g2d.drawImage(getRook("black"), 560, 0, null);
		
		for(int i = 0; i <= 560; i += 80) {
			g2d.drawImage(getPawn("black"), i, 80, null);
		}
		
		g2d.drawImage(getRook("white"), 0, 560, null);
		g2d.drawImage(getKnight("white"), 80, 560, null);
		g2d.drawImage(getBishop("white"), 160, 560, null);
		g2d.drawImage(getQueen("white"), 240, 560, null);
		g2d.drawImage(getKing("white"), 320, 560, null);
		g2d.drawImage(getBishop("white"), 400, 560, null);
		g2d.drawImage(getKnight("white"), 480, 560, null);
		g2d.drawImage(getRook("white"), 560, 560, null);
		
		for(int i = 0; i <= 560; i += 80) {
			g2d.drawImage(getPawn("white"), i, 480, null);
		}
		
	}
	
	public Image getKing(String color){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_king.png"));
		return image.getImage();
	}
	
	public Image getQueen(String color){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_queen.png"));
		return image.getImage();
	}
	
	public Image getBishop(String color){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_bishop.png"));
		return image.getImage();
	}
	
	public Image getKnight(String color){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_knight.png"));
		return image.getImage();
	}	
	
	public Image getRook(String color){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_rook.png"));
		return image.getImage();
	}
	
	public Image getPawn(String color) {
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_pawn.png"));
		return image.getImage();
	}
}
