import javax.swing.JFrame;
import java.awt.Insets;

public class Window {
	public Window(Display gameDisplay) {
		final int WINDOW_SIZE = Display.TILE_SIZE * gameDisplay.getGame().getBoard().getBoard().length;
		JFrame frame = new JFrame("Chess");
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameDisplay);
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		frame.setSize(WINDOW_SIZE + insets.left + insets.right, 
			WINDOW_SIZE + insets.top + insets.bottom);
		frame.setLocationRelativeTo(null);
	}
}