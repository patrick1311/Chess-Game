import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel {
	private static final Color GREEN = new Color(0x96, 0xFF, 0x96);
	private static final Color LIGHT_BLUE = new Color(0xC8, 0xE6, 0xFF);
	private static final Color DARK_BLUE = new Color(0x7D, 0xC8, 0xFF);
	private static final Color LIGHT_RED = new Color(0xFF, 0x50, 0x50);
	private static final Color DARK_RED = new Color(0xC8, 0x00, 0x00);
	private Graphics2D g2d;
	private Game game;
	private ChessBoard board;
	private List<BoardCoordinate> highlights;
	private List<BoardCoordinate> enemyHighlights;
	private BoardCoordinate sourceHighlight;
	private Timer timer;
	private boolean inAnimation;
	private Piece currentMovingPiece;
	
	public Display(Game game) {
		this.highlights = new LinkedList<BoardCoordinate>();
		this.enemyHighlights = new LinkedList<BoardCoordinate>();
		this.game = game;
		this.board = game.getBoard();
		inAnimation = false;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		drawBoard();
		highlightTiles(sourceHighlight, enemyHighlights, highlights);
		drawPieces();
	}

	private void drawBoard() {
	    //paint the board tiles
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
	            if(
	                (i % 2 == 0 && j % 2 == 0) || // Both are even
	                (i % 2 == 1 && j % 2 == 1)    // Both are odd
	            ) {
	                g2d.setColor(Color.WHITE);
	            } else {
	                g2d.setColor(Color.LIGHT_GRAY);
	            }
	            g2d.fillRect(i * ChessBoard.TILE_SIZE, j * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
	        }
	    }
	}
	
	private void drawPieces() {
		Piece[][] board = this.board.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != null) {
					drawPiece(board[i][j], i, j);
				}
			}
		}
	}
	
	private void drawPiece(Piece piece, int x, int y) {
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

	private Image getPiece(String color, String piece){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/" + color + "_" + piece + ".png"));
		return image.getImage();
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setSourceHighlight(BoardCoordinate sourceHighlight) {
		this.sourceHighlight = sourceHighlight;
	}
	
	public void setHighlights(List<BoardCoordinate> highlights) {
		this.highlights = highlights;
	}
	
	public void setEnemyHighlights(List<BoardCoordinate> enemyHighlights) {
		this.enemyHighlights = enemyHighlights;
	}
	
	public void clearHighlights() {
		this.sourceHighlight = null;
		this.highlights.clear();
		this.enemyHighlights.clear();
	}
	
	public void highlightTiles(BoardCoordinate source, List<BoardCoordinate> enemyMoves, List<BoardCoordinate> moves) {
		int x, y;
		
		if(source != null) {
			g2d.setColor(GREEN);
			g2d.fillRect(source.getX() * ChessBoard.TILE_SIZE, source.getY() * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
		}
		for(BoardCoordinate move: moves) {
			x = move.getX();
			y = move.getY();
			
			if(
				(x % 2 == 0 && y % 2 == 0) || // Both are even
				(x % 2 == 1 && y % 2 == 1)    // Both are odd
			) {
				g2d.setColor(LIGHT_BLUE);
			} else {
				g2d.setColor(DARK_BLUE);
			}
			g2d.fillRect(x * ChessBoard.TILE_SIZE, y * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
		}
		for(BoardCoordinate move: enemyMoves) {
			x = move.getX();
			y = move.getY();
			
			if(
				(x % 2 == 0 && y % 2 == 0) || // Both are even
				(x % 2 == 1 && y % 2 == 1)    // Both are odd
			) {
				g2d.setColor(LIGHT_RED);
			} else {
				g2d.setColor(DARK_RED);
			}
			g2d.fillRect(x * ChessBoard.TILE_SIZE, y * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
		}
	}
	
	public void drawMove(Piece piece, BoardCoordinate tile) { //Animation
		currentMovingPiece = piece;
		int totalAnimationTime = 1000; //1 second
		int FPS = 60;
		int frameRate = (int) totalAnimationTime / FPS;	//16.6667
		int delx = tile.getX() - piece.getCoordinate().getX();
		int dely = tile.getY() - piece.getCoordinate().getY();
		int increaseX = delx / FPS;
		int increaseY = dely / FPS;
		System.out.println("src: " + piece.getCoordinate().getX() + "," + piece.getCoordinate().getY());
		System.out.println("des: " + tile.getX() + "," + tile.getY());
		System.out.println("delta: " + delx + "," + dely);

		timer = new Timer(frameRate, new ActionListener() {
			private int remainingFrame = 60;
			
            public void actionPerformed(ActionEvent e) {
            	if(remainingFrame == 0) {
            		inAnimation = false;
            		timer.stop();
            	}
            	else {
            		inAnimation = true;
            		
            		repaint();
            		remainingFrame--;
            	}
            }
        });
		timer.restart();
	}
}