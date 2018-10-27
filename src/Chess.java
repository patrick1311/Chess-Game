public class Chess {
	public static void main(String args[]) {
		Player p1 = new Player();
		Player p2 = new Player();
		Game game = new Game(p1, p2);
		Display display = new Display(game);
		Window window = new Window(display);
		MouseInput mouseListener = new MouseInput(display);
	}
}