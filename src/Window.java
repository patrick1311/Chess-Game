import javax.swing.JFrame;

public class Window {
	public static final int TOPBORDER = 22;
	
	public Window(Display game) {
		JFrame frame = new JFrame("Chess");
		frame.setSize(ChessBoard.TILE_SIZE * 8, ChessBoard.TILE_SIZE * 8 + TOPBORDER);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
		
		new MouseInput(frame);
	}
	
	public static void main(String args[]) {
		Player white = new Player("White");
		Player black = new Player("Black");
		Game game = new Game(white, black);
		Display x = new Display(game.getBoard());
		Window window = new Window(x);
		
		game.run();
	}
}