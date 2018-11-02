import javax.swing.JFrame;

public class Window {
	//default border size for all operating systems
	private static int TOPBORDER = 50;
	private static int BOTTOMBORDER = 50;
	private static int LEFTBORDER = 50;
	private static int RIGHTBORDER = 50;
	private static String OS = System.getProperty("os.name").toLowerCase();

	public Window(Display game) {
		if(isWindows()) {
			TOPBORDER = 26;
			BOTTOMBORDER = 3;
			LEFTBORDER = 3;
			RIGHTBORDER = 3;
		}
		else if(isMac()) {
			TOPBORDER = 22;
			BOTTOMBORDER = 0;
			LEFTBORDER = 0;
			RIGHTBORDER = 0;
		}
		//System.out.println(OS);
		JFrame frame = new JFrame("Chess");
		frame.setSize(Display.TILE_SIZE*8 + LEFTBORDER + RIGHTBORDER
				,Display.TILE_SIZE*8 + TOPBORDER + BOTTOMBORDER);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
	}

	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	private boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

}