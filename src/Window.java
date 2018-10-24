
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
	}
	
	public static void main(String args[]) {
		
		Display x = new Display();
		Window window = new Window(x);
		
	}
	
	
}
