import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;


public class TileTest {
	
	private GameBoard game = new GameBoard(450, 550);
	private Tile[][] board = game.getBoard();


	@Test
	public void test() {
		colorTwo();
		colorEight();
		colorSixteen();
		colortwohundred();
		colorthousand();
	}
	
	public void colorTwo(){
		Tile tile = new Tile(2,0,0);
		Color color = tile.getColor();
		assertEquals(new Color(0xe9e9e9), color);
	}
	
	public void colorEight(){
		Tile tile = new Tile(8,0,0);
		Color color = tile.getColor();
		assertEquals(new Color(0xf79d3d), color);
	}
	
	public void colorSixteen(){
		Tile tile = new Tile(16,0,0);
		Color color = tile.getColor();
		assertEquals(new Color(0xf28007), color);
	}
	
	public void colortwohundred(){
		Tile tile = new Tile(256,0,0);
		Color color = tile.getColor();
		assertEquals(new Color(0xf6e873), color);
	}
	
	public void colorthousand(){
		Tile tile = new Tile(2048,0,0);
		Color color = tile.getColor();
		assertEquals(new Color(0xffe400), color);
	}

}
