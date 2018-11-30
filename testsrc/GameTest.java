import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	@DisplayName("Checking pieces are in correct placement.")
	void isCorrectPieces() {
		ChessBoard board = game.getBoard();
		
		//White Pieces
		assertTrue(board.getPiece(4, 7) instanceof King);
		assertTrue(board.getPiece(3, 7) instanceof Queen);
		assertTrue(board.getPiece(0, 7) instanceof Rook);
		assertTrue(board.getPiece(2, 7) instanceof Bishop);
		assertTrue(board.getPiece(1, 7) instanceof Knight);
		assertTrue(board.getPiece(7, 7) instanceof Rook);	
		assertTrue(board.getPiece(5, 7) instanceof Bishop);
		assertTrue(board.getPiece(6, 7) instanceof Knight);
		
		//Black Pieces
		assertTrue(board.getPiece(4, 0) instanceof King);
		assertTrue(board.getPiece(3, 0) instanceof Queen);
		assertTrue(board.getPiece(0, 0) instanceof Rook);
		assertTrue(board.getPiece(2, 0) instanceof Bishop);
		assertTrue(board.getPiece(1, 0) instanceof Knight);
		assertTrue(board.getPiece(7, 0) instanceof Rook);	
		assertTrue(board.getPiece(5, 0) instanceof Bishop);
		assertTrue(board.getPiece(6, 0) instanceof Knight);
		
		for(int i = 0; i < 8; i++) {
			assertTrue( board.getPiece(i, 6) instanceof Pawn);
			assertTrue( board.getPiece(i, 1) instanceof Pawn);
		}
	}
	
	@Test
	@DisplayName("Check if pawn can promote.")
	void validPromotion() {
		
		ChessBoard board = game.getBoard();
		Validator validator = new Validator(board);
		
		Piece white = board.getPiece(0, 6);
		Piece black = board.getPiece(0, 1);
		
		BoardCoordinate bottomEnd = new BoardCoordinate(0,7);
		BoardCoordinate topEnd = new BoardCoordinate(0,0);

		assertTrue(validator.legalPromotion((Pawn)white, topEnd));
		assertTrue(validator.legalPromotion((Pawn)black, bottomEnd));
		
		assertFalse(validator.legalPromotion((Pawn)white, new BoardCoordinate(5,5)));
		assertFalse(validator.legalPromotion((Pawn)white, new BoardCoordinate(5,5)));		
	}
	
	@Test
	@DisplayName("Check correct pawn movements.")
	void validPawnMoves() {
		ChessBoard board = game.getBoard();
		Validator validator = new Validator(board);
		
		Piece white = board.getPiece(0, 6);
		
		Pawn black = (Pawn) board.getPiece(0, 1);
		black.setFirstMove(false);
		
		List<BoardCoordinate> pawnFirstMoves = validator.calculateValidMoves((Pawn)white);
		List<BoardCoordinate> pawnMove = validator.calculateValidMoves(black);
		
		assertEquals(pawnFirstMoves.size(), 2);
		assertEquals(pawnMove.size(), 1);
	}

}