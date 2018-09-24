import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Display extends JPanel{

	//Parameter board, 
	public Display() {
		
		// Display the frame
		//draw homescreen
		
		//when click play 
		

	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		drawBoard(g2d);
	}

	public void drawBoard(Graphics2D g2d) {
		//paint the board tiles
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 8; i++) {
				if(j%2 == 0) {
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i*ChessBoard.TILE_SIZE, j*ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
					i++;
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.fillRect(i*ChessBoard.TILE_SIZE, j*ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
				}
				else {
					g2d.setColor(Color.LIGHT_GRAY);
					g2d.fillRect(i*ChessBoard.TILE_SIZE, j*ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
					i++;
					g2d.setColor(Color.WHITE);
					g2d.fillRect(i*ChessBoard.TILE_SIZE, j*ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
				}
			}
		}

		placeStartingPieces(g2d);

	}
	
	
	public void drawPieces(ChessBoard chessBoard) {
		
		Piece[][] board = chessBoard.getP();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != null) {
					drawPiece(board[i][j]);
				}
				
			}
		}
	}
	
	public void drawPiece(Piece piece) {
		
	}

	public void placeStartingPieces(Graphics2D g2d) {

		g2d.drawImage(getRook("black"), 0, 0, null);
		g2d.drawImage(getKnight("black"), ChessBoard.TILE_SIZE, 0, null);
		g2d.drawImage(getBishop("black"), ChessBoard.TILE_SIZE * 2, 0, null);
		g2d.drawImage(getQueen("black"), ChessBoard.TILE_SIZE * 3, 0, null);
		g2d.drawImage(getKing("black"), 320, 0, null);
		g2d.drawImage(getBishop("black"), 400, 0, null);
		g2d.drawImage(getKnight("black"), 480, 0, null);
		g2d.drawImage(getRook("black"), 560, 0, null);

		for(int i = 0; i <= 560; i += 80) {
			g2d.drawImage(getPawn("black"), i, ChessBoard.TILE_SIZE, null);
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
