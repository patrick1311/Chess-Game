import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Display extends JPanel {
	private Game game;
	private ChessBoard board;
	//Parameter board,
	public Display(Game game) {
		this.game = game;
		this.board = game.getBoard();
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawBoard(g2d);
		drawPieces(g2d);
	}

	public void drawBoard(Graphics2D g2d) {
	    //paint the board tiles
	    for(int j = 0; j < 8; j++) {
	        for(int i = 0; i < 8; i++) {
	            if(
	                (j % 2 == 0 && i % 2 == 0) || // Both are even
	                (j % 2 == 1 && i % 2 == 1)    // Both are odd
	            ) {
	                g2d.setColor(Color.WHITE);
	            } else {
	                g2d.setColor(Color.LIGHT_GRAY);
	            }
	            g2d.fillRect(i * ChessBoard.TILE_SIZE, j * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
	        }
	    }
	}
	
	public void drawPieces(Graphics2D g2d) {
		Piece[][] board = this.board.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != null) {
					drawPiece(g2d, board[i][j], i, j);
				}
			}
		}
	}
	
	public void drawPiece(Graphics2D g2d, Piece piece, int x, int y) {
		String color = piece.getColor();
		
		if(Pawn.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "pawn"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);	
		else if(Rook.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "rook"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);	
		else if(Knight.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "knight"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);	
		else if(Bishop.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "bishop"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);	
		else if(Queen.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "queen"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);	
		else if(King.class.isInstance(piece))
			g2d.drawImage(getPiece(color, "king"), ChessBoard.TILE_SIZE * x, ChessBoard.TILE_SIZE * y, null);		
	}

	public Image getPiece(String color, String piece){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_" + piece + ".png"));
		return image.getImage();
	}
	
	public Game getGame() {
		return game;
	}
	
	public void clearHighlights() {
		
	}
	
	public void highlightTiles(List<BoardCoordinate> moves) {
		
	}
	
	public void drawMove(Piece piece, BoardCoordinate tile) { //Animation
		
	}
}
