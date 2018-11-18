import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
	
	private static Game game;
	
	
	@BeforeAll
	static void initAll() {
		Player p1 = new Player();
		Player p2 = new Player();
		game = new Game(p1,p2);
	}
	
	@Test
	@DisplayName("Checking if game is not null.")
	void isGameInstanciated() {
		assertNotNull(game);
	}
	
	@Test
	@DisplayName("Checking if pawn has correct behavior at first move.")
	void testPawnFirstMove() {
	}

}
