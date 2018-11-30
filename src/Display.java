import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
	public static final int TILE_SIZE = 80;
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
	private MovingPiece currentMovingPiece;
	private Image images[][];
	private String message1;
	private String message2;
	
	public Display(Game game) {
		this.highlights = new LinkedList<BoardCoordinate>();
		this.enemyHighlights = new LinkedList<BoardCoordinate>();
		this.game = game;
		this.board = game.getBoard();
		inAnimation = false;
		images = new Image[6][6];
		images[0][0] = getPiece("white", "king");
		images[0][1] = getPiece("white", "queen");
		images[0][2] = getPiece("white", "rook");
		images[0][3] = getPiece("white", "bishop");
		images[0][4] = getPiece("white", "knight");
		images[0][5] = getPiece("white", "pawn");
		images[1][0] = getPiece("black", "king");
		images[1][1] = getPiece("black", "queen");
		images[1][2] = getPiece("black", "rook");
		images[1][3] = getPiece("black", "bishop");
		images[1][4] = getPiece("black", "knight");
		images[1][5] = getPiece("black", "pawn");
	}
	
	public boolean isAnimating() {
		return inAnimation;
	}
	
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		drawBoard();
		highlightTiles(sourceHighlight, enemyHighlights, highlights);
		drawPieces();
		drawMovingPiece();
		drawMessage();
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
	            g2d.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	        }
	    }
	}
	
	private void drawPieces() {
		Piece[][] board = this.board.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != null &&
					(currentMovingPiece == null ||
					currentMovingPiece.getPiece() != board[i][j])
				) {	//if board location is not null and there is not a piece moving or if this is not a moving piece
					drawPiece(board[i][j], i, j);
				}
			}
		}
	}
	
	private void drawPiece(Piece piece, int x, int y) {
		if(piece.getColor().equals("White")) {
			if(Pawn.class.isInstance(piece))
				g2d.drawImage(images[0][5], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Rook.class.isInstance(piece))
				g2d.drawImage(images[0][2], TILE_SIZE * x, TILE_SIZE * y, null);		
			else if(Knight.class.isInstance(piece))
				g2d.drawImage(images[0][4], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Bishop.class.isInstance(piece))
				g2d.drawImage(images[0][3], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Queen.class.isInstance(piece))
				g2d.drawImage(images[0][1], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(King.class.isInstance(piece))
				g2d.drawImage(images[0][0], TILE_SIZE * x, TILE_SIZE * y, null);	
		}
		else {
			if(Pawn.class.isInstance(piece))
				g2d.drawImage(images[1][5], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Rook.class.isInstance(piece))
				g2d.drawImage(images[1][2], TILE_SIZE * x, TILE_SIZE * y, null);		
			else if(Knight.class.isInstance(piece))
				g2d.drawImage(images[1][4], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Bishop.class.isInstance(piece))
				g2d.drawImage(images[1][3], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(Queen.class.isInstance(piece))
				g2d.drawImage(images[1][1], TILE_SIZE * x, TILE_SIZE * y, null);	
			else if(King.class.isInstance(piece))
				g2d.drawImage(images[1][0], TILE_SIZE * x, TILE_SIZE * y, null);	
		}
	}

	private Image getPiece(String color, String piece){
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + color + "_" + piece + ".png"));
		Image image = icon.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image, icon.getDescription());
		return icon.getImage();
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
			g2d.fillRect(source.getX() * TILE_SIZE, source.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
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
			g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
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
			g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
	}
	
	public void drawMove(Piece piece, BoardCoordinate tile) { //Animation
		final double srcX = piece.getCoordinate().getX() * TILE_SIZE;
		final double srcY = piece.getCoordinate().getY() * TILE_SIZE;
		final double desX = tile.getX() * TILE_SIZE;
		final double desY = tile.getY() * TILE_SIZE;
		currentMovingPiece = new MovingPiece(piece);
		final int totalAnimationTime = 250; // 250 milliseconds
		final int FPS = 60;	
		int frameRate = totalAnimationTime / FPS;	//each 10ms will fire an action
		double deltaX = desX - srcX;	//total displacement of
		double deltaY = desY - srcY;	//x and y in pixels
		final double incrementX = deltaX / FPS;
		final double incrementY = deltaY / FPS;
		//System.out.println("delta: " + deltaX + ", " + deltaY);
		//System.out.println("increment per frame: " + incrementX + " " + incrementY);
		//System.out.println("src[][]: " + piece.getCoordinate().getX() + ", " + piece.getCoordinate().getY() 
		//					+ "\tsrc: " + srcX + ", " + srcY);
		//System.out.println("des[][]: " + tile.getX() + ", " + tile.getY() + "\tdes: " + desX + ", " + desY);
		
		timer = new Timer(frameRate, new ActionListener() {
			private int remainingFrame = FPS;
			//initial location
			private double x = srcX;
			private double y = srcY;
			
            public void actionPerformed(ActionEvent e) {
            	if(remainingFrame == 0) {
            		inAnimation = false;
            		currentMovingPiece = null;
            		timer.stop();
            	}
            	else {
            		inAnimation = true;
            		remainingFrame--;
            		x = x + incrementX;
            		y = y + incrementY;
            		currentMovingPiece.update(x, y);
            	}
            	repaint();
            }
        });
		timer.restart();
	}
	
	private void drawMovingPiece() {
		if(currentMovingPiece == null) return;
		String color = currentMovingPiece.getPiece().getColor();
		
		if(color.equals("White")) {
			if(Pawn.class.isInstance(currentMovingPiece.getPiece())) 
				g2d.drawImage(images[0][5], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);	
			else if(Rook.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[0][2], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Knight.class.isInstance(currentMovingPiece.getPiece())) 
				g2d.drawImage(images[0][4], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Bishop.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[0][3], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Queen.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[0][1], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(King.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[0][0], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
		}
		else {
			if(Pawn.class.isInstance(currentMovingPiece.getPiece())) 
				g2d.drawImage(images[1][5], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);	
			else if(Rook.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[1][2], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Knight.class.isInstance(currentMovingPiece.getPiece())) 
				g2d.drawImage(images[1][4], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Bishop.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[1][3], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(Queen.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[1][1], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
			else if(King.class.isInstance(currentMovingPiece.getPiece()))
				g2d.drawImage(images[1][0], (int) currentMovingPiece.getX(), (int) currentMovingPiece.getY(), null);
		}
	}
	
	public void setMessages(String message1, String message2) {
		this.message1 = message1;
		this.message2 = message2;
	}
	
	public void drawMessage() {
		if(message1 != null && message2 != null) {
			int fontSize = (int)(TILE_SIZE * 8.0 / 7);
			int startingX, startingY;
			g2d.setFont(new Font("Copperplate", Font.PLAIN, fontSize));
			g2d.setColor(Color.BLACK);
			FontMetrics fm = getFontMetrics(g2d.getFont());
			
			startingX = ((TILE_SIZE * 8) - (fm.stringWidth(message1))) / 2;
			startingY = ((TILE_SIZE * 8) - (fm.getHeight() / 2)) / 2;
			g2d.drawString(message1, startingX, startingY);
			startingX = ((TILE_SIZE * 8) - (fm.stringWidth(message2))) / 2;
			startingY += fm.getHeight();
			g2d.drawString(message2, startingX, startingY);
		}
	}
}