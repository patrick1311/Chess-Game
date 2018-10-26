public class Chess {
	public static void main(String args[]) {
		Player white = new Player("White");
		Player black = new Player("Black");
		Game game = new Game(white, black);
		Display x = new Display(game.getBoard());
		Window window = new Window(x);
		
		game.run();
	}
}
