import static org.junit.Assert.*;

import org.junit.Test;


public class GameBoardTest {
	
	/*private GameBoard game = new GameBoard(450, 550);
	private Tile[][] board = game.getBoard();

	@Test
	public void test() {
		scoreZero();
		scoreTwo();
		mergeTwo();
		mergeFour();
		difMerge();
		scoreMore();
		twoRandomTiles();
		moveUp();
		moveDown();
		moveRight();
		moveLeft();
	}*/
	
	@Test
	public void mergeTwo(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(2, 1, 0);
		game.moveTiles(Direction.UP);
		assertEquals(board[0][0].getValue(), 4);
	}
	
	@Test
	public void mergeFour(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(4, 1, 0);
		game.spawn(2, 2, 0);
		game.spawn(2, 3, 0);
		game.moveTiles(Direction.UP);
		assertEquals(board[0][0].getValue(), 2);
		assertEquals(board[1][0].getValue(), 4);
		assertEquals(board[2][0].getValue(), 4);
	}
	
	@Test
	public void difMerge(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(2, 1, 0);
		game.spawn(2, 2, 0);
		game.spawn(2, 3, 0);
		game.moveTiles(Direction.UP);
		assertEquals(board[0][0].getValue(), 4);
		assertEquals(board[1][0].getValue(), 4);
	}
	
	@Test
	public void twoRandomTiles(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		int count = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(board[i][j] != null) count++;
			}
		}
		assertEquals(2, count);
	}
	
	@Test
	public void moveUp(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				//Set all to null
				board[i][j] = null;
			}
		}
		board[3][3] = new Tile(2,3,3);
		game.moveTiles(Direction.UP);
		Tile newTile = board[3][3];
		assertEquals(null, newTile);
		assertEquals(2, board[0][3].getValue());
	}
	
	@Test
	public void moveDown(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				//Set all to null
				board[i][j] = null;
			}
		}
		board[3][3] = new Tile(2,0,0);
		game.moveTiles(Direction.DOWN);
		Tile newTile = board[0][0];
		assertEquals(null, newTile);
	}
	
	@Test
	public void moveRight(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				//Set all to null
				board[i][j] = null;
			}
		}
		board[0][0] = new Tile(2,0,0);
		game.moveTiles(Direction.RIGHT);
		Tile newTile = board[0][0];
		assertEquals(null, newTile);
		assertEquals(2, board[0][3].getValue());
	}
	
	@Test
	public void moveLeft(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				//Set all to null
				board[i][j] = null;
			}
		}
		board[0][0] = new Tile(2,3,3);
		game.moveTiles(Direction.LEFT);
		assertEquals(2, board[3][3].getValue());
	}
	
	@Test
	public void scoreTwo(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(2, 1, 0);
		game.moveTiles(Direction.UP);
		int score = game.getScore();
		System.out.println(score);
		assertEquals(4, score);
	}
	
	@Test
	public void scoreMore(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(2, 1, 0);
		game.setScore();
		game.moveTiles(Direction.UP);
		game.spawn(4, 1, 0);
		game.moveTiles(Direction.UP);
		int score = game.getScore();
		assertEquals(12, score);
	}
	
	@Test
	public void scoreZero(){
		GameBoard game = new GameBoard(450, 550);
		Tile[][] board = game.getBoard();
		game.restart();
		game.spawn(2, 0, 0);
		game.spawn(4, 1, 0);
		game.setScore();
		game.moveTiles(Direction.UP);
		int score = game.getScore();
		assertEquals(0, score);
	}

}
