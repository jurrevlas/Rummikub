package unittest;

import static org.junit.Assert.*;
import game.Game;
import game.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	private Game game;

	
	
	@Before
	public void setUp(){
		game = new Game();
		game.addPlayer(new Player("Player1"));
		assertFalse(game.startGame());
		for(int i = 2; i<5;i++)
			game.addPlayer(new Player("Player"+i));
		assertTrue(game.startGame());
		
	}
	
	
	@Test
	public void testGame() {
		
		
	}
	
	@After
	public void tearDown(){
		
		
	}

}
