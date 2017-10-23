import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * @author fernandavargas
 */


public class GameBoard{
	
		
	public static final int ROWS = 4;
	public static final int COLS = 4;
	public static final int STARTINGTILE = 2;
	
	private Tile[][] board;
	private boolean dead = false;
	private boolean won = false;
	private int score = 0;
	private static final Font title = new Font("Bebas Neue Regular", Font.BOLD, 28);
	
	//Background of the game board
	private BufferedImage gameBoard;
	//Final image with tiles
	private BufferedImage finalBoard;

	private int x; 
	private int y;
	
	private static final int SPACING = 5;
	public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * Tile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Tile.HEIGHT;
	
	/**
	 * Constructor
	 * Initialize board, create imaged for board. 
	 * @param x
	 * @param y
	 */
	public GameBoard(int x, int y){
		this.x = x;
		this.y = y;
		board = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		createBoardImage();
		start();
		
	}
	
	/**
	 * Create board image
	 * Each image "tile" 
	 */
	private void createBoardImage(){
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,  0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				int x = SPACING + SPACING * col + Tile.WIDTH * col;
				int y = SPACING + SPACING * row + Tile.HEIGHT * row;
				g.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
			}
		}
	}
	
	/**
	 * Start method
	 * put in a random place two tiles with random number
	 */
	private void start(){
		for(int i = 0; i < STARTINGTILE; i++){
			spawnRandom();
		}
	}
	
	/**
	 * Uses Random
	 * Boolean notValid to check if we found a valid place to put in 
	 * Choose a random tile and a random number, if valid then set. 
	 */
	public void spawnRandom(){
		Random random = new Random();
		boolean notValid = true;
		while(notValid && emptyPlace()){
			int location = random.nextInt(ROWS*COLS); 
			int row = location / ROWS;
			int col = location % COLS;
			Tile current = board[row][col];
			if(current == null){
				int value = random.nextInt(10) < 9 ? 2 : 4;
				Tile tile = new Tile(value, getTileX(col), getTileY(row));
				board[row][col] = tile;
				notValid = false;
			}
		}
		//Check if the game is lost
		if(notValid){
			/*int response = JOptionPane.showConfirmDialog(null, "You lost, do you want to play again? ", "Warning!", JOptionPane.YES_NO_OPTION);
			if(response == 0){
				restart();
			}else{
				System.exit(0);
			}*/
			dead = true;
			
		}
	}
	
	/**
	 * Put a value in x and y Tile
	 * @param value --> tile's vale
	 * @param x --> x's position
	 * @param y --> y's position
	 */
	public void spawn(int value, int x, int y){
		Tile tile = new Tile(value, getTileX(x), getTileY(y));
		board[x][y] = tile;
	}
	
	/**
	 * Obtain the correct X position of the tile 
	 * @param col
	 * @return X position
	 */
	public int getTileX(int col) {
		return SPACING + col * Tile.WIDTH + col * SPACING;
	}

	/**
	 * Obtain the correct Y position of the tile 
	 * @param row
	 * @return Y position
	 */
	public int getTileY(int row) {
		return SPACING + row * Tile.HEIGHT + row * SPACING;
	}

	/**
	 * Render the board
	 * Including: game's name, best score, and game's score. 
	 * @param g
	 */
	public void render(Graphics2D g ){
		
		Graphics2D gd2 = (Graphics2D)finalBoard.getGraphics();
		gd2.drawImage(gameBoard, 0,0, null);
		
		//Draw tiles
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				Tile current = board[row][col];
				//No need to render as is empty
				if(current == null) continue;
				current.render(gd2);
			}
		}
		
		g.drawImage(finalBoard, x, y, null);

		g.setColor(new Color(0x796e64));
		g.setFont(title.deriveFont(48f));
		g.drawString("2048" , 80, 85);
		
		g.setColor(new Color(0xbdad9f));
		g.fillRoundRect(240, 45, 100, 55, 10, 10);
		
		g.setColor(new Color(0xf0e2d0));
		g.setFont(title.deriveFont(18f));
		g.drawString("Score ", 264, 67);
		
		g.setColor(Color.white);
		g.setFont(title.deriveFont(20f));
		if(score > 10){
			g.drawString(" " + score , 265, 92);
		}else{
			g.drawString(" " + score , 277, 92);
		}
	
		g.setColor(new Color(0xd68910));
		g.setFont(title.deriveFont(15f));
		g.drawString("Press SPACE KEY to restart" , 120, 135);
		
		if(dead){
			g.setColor(Color.black);
			g.setFont(title.deriveFont(50f));
			g.drawString("You lost" , 110, 300);
		
			g.setColor(Color.black);
			g.setFont(title.deriveFont(30f));
			g.drawString("Press SPACE KEY to restart" , 10, 350);
		}
		
		g.setColor(new Color(0x796e64));
		g.setFont(title.deriveFont(25f));
		if(won) g.drawString("Winner!!" , 170, 170);
			
		gd2.dispose();
		
		
	}
	
	/**
	 * Set all tiles to null
	 * Initialize the first two tiles
	 * Set score to 0 
	 */
	public void restart(){
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				board[row][col] = null;
			}
		}
		for(int i = 0; i < STARTINGTILE; i++){
			spawnRandom();
		}
		dead = false;
		score = 0;
	}
	
	/**
	 * CHeck if the user won
	 * If a tile has the number 2048 then set won global value to true
	 */
	private void won(){
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				if(board[row][col] == null) continue;
				if(board[row][col].getValue() == 2048){
					won = true;
				}
			}
		}
	}
	
	/**
	 * Check if the user lost
	 * CHeck if there is an empty tile
	 * If no empty tile then check possible movements
	 * @return true is he lost, false it the play keeps going
	 */
	private boolean emptyPlace(){
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				if(board[row][col] == null) return true;
			}
		}
		return false;
	}
	
	/**
	 * Update tile position in the board
	 * Includes the slideSpeed for the "animation"
	 * @param tile
	 * @param row
	 * @param col
	 */
	private void resetPosition(Tile tile, int row, int col){
		if(tile == null) return;
		
		int x = getTileX(col);
		int y = getTileY(row);
		
		int distX = tile.getX() - x;
		int distY = tile.getY() - y;

		if (Math.abs(distX) < Tile.SLIDE_SPEED) {
			tile.setX(tile.getX() - distX);
		}

		if (Math.abs(distY) < Tile.SLIDE_SPEED) {
			tile.setY(tile.getY() - distY);
		}

		//Moving left
		if (distX < 0) {
			tile.setX(tile.getX() + Tile.SLIDE_SPEED);
		}
		//Moving up
		if (distY < 0) {
			tile.setY(tile.getY() + Tile.SLIDE_SPEED);
		}//Moving right
		if (distX > 0) {
			tile.setX(tile.getX() - Tile.SLIDE_SPEED);
		}//Moving down
		if (distY > 0) {
			tile.setY(tile.getY() - Tile.SLIDE_SPEED);
		}
		
	}
	
	/**
	 * Check if a key was pressed. 
	 * If so reset position
	 */
	public void update(){
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				Tile current = board[row][col];
				if(current == null) continue;
				//Reset position
				resetPosition(current, row, col);
				//Check if you won the game
				if(current.getValue() == 2048){
					won = true;
				}
			}
		}
	}
	
	/**
	 * Check if the tile can move in a specific direction
	 * @param dir --> direction to move
	 * @param row 
	 * @param col
	 * @return
	 */
	private boolean checkOutOfBound(Direction dir, int row, int col){
		if(dir == Direction.LEFT){
			return col < 0;
		}else if(dir == Direction.RIGHT){
			return col >COLS -1; 
		}else if (dir == Direction.UP){
			return row < 0;
		}else if (dir == Direction.DOWN){
			return row > ROWS -1;
		}
		return false;
	}
	
	/**
	 * Move the tile 
	 * Check if it can merge, move or not move. 
	 * @param row
	 * @param col
	 * @param d
	 * @param horizontal
	 * @param vertical
	 * @return
	 */
	private boolean move(int row, int col, Direction d, int horizontal, int vertical){
		Tile currentTile = board[row][col];
		boolean moving = false;
		boolean canMove = true;
		if(board[row][col] == null) return false;
		while(canMove){
			col += horizontal;
			row += vertical;
			if(checkOutOfBound(d, row, col)) break;
			if(board[row][col] == null ){
				board[row][col] = currentTile;
				board[row - vertical][col - horizontal] = null;
				moving = true;
			}else if(currentTile.getValue() == board[row][col].getValue() && board[row][col].isCanCombined()){
				board[row][col].setValue(board[row][col].getValue() * 2);
				board[row - vertical][col - horizontal] = null;
				score += (currentTile.getValue() * 2);
				moving = true;
				won();
			}else{
				canMove = false;
			}
		}
		return moving;
	}
	
	/**
	 * Move the tile to the given direction 
	 * Check valid moves and do it 
	 * Condition for each movement depending on horizontal and vertical values
	 * Any movement causes a spawnRandom in the board
	 * Check if the game still playable –
	 */
	public void moveTiles(Direction d){
		int horizontalDir = 0;
		int verticalDir = 0;
		boolean moving = false;
		
		if(d == Direction.RIGHT){
			horizontalDir = 1;
			for(int row = 0; row < ROWS; row++){
				for(int col = COLS - 1; col >= 0; col--){
					moving = move(row, col, Direction.RIGHT, horizontalDir, verticalDir);
				}
			}
		}else if(d == Direction.LEFT){
			horizontalDir = -1;
			for(int row = 0; row < ROWS; row++){
				for(int col = 0 ; col < COLS; col++){
					moving = move(row, col, Direction.LEFT, horizontalDir, verticalDir);
				}
			}
		}else if(d == Direction.DOWN){
			verticalDir = 1;
			for(int row = ROWS - 1; row >= 0; row--){
				for(int col = 0 ; col < COLS; col++){
					moving = move(row, col, Direction.DOWN, horizontalDir, verticalDir);
				}
			}
		}else if(d == Direction.UP){
			verticalDir = -1;
			for(int row = 0; row < ROWS; row++){
				for(int col = 0 ; col < COLS; col++){
					moving = move(row, col, Direction.UP, horizontalDir, verticalDir);
				}
			}
		}else{
			System.out.println("Not valid direction");
		}
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				Tile current = board[row][col];
				if(current == null) continue;
				current.setCanCombined(true);
			}
		}
		spawnRandom();
		dead = checkDead();
		
	}
	
	/**
	 * Check if a tile can merge with another
	 * by checking the value of the tile surrounding
	 * @param row
	 * @param col
	 * @param tile
	 * @return boolean
	 */
	private boolean checkSurroundingTiles(int row, int col, Tile tile) {
		if (row > 0) {
			Tile check = board[row - 1][col];
			if (check == null) return true;
			if (tile.getValue() == check.getValue()) return true;
		}
		if (row < ROWS - 1) {
			Tile check = board[row + 1][col];
			if (check == null) return true;
			if (tile.getValue() == check.getValue()) return true;
		}
		if (col > 0) {
			Tile check = board[row][col - 1];
			if (check == null) return true;
			if (tile.getValue() == check.getValue()) return true;
		}
		if (col < COLS - 1) {
			Tile check = board[row][col + 1];
			if (check == null) return true;
			if (tile.getValue() == check.getValue()) return true;
		}
		return false;
	}


	/**
	 * Check if the board has no free spaces
	 * And no more movements can be done
	 * @return boolean
	 */
	private boolean checkDead() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == null) return false;
				boolean canCombine = checkSurroundingTiles(row, col, board[row][col]);
				if (canCombine) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*Testing issues*/
	public Tile[][] getBoard(){
		return board;
	}
	
	/*Testing issues*/
	public int getScore(){
		return score;
	}
	
	/*Testing issues*/
	public void setScore(){
		this.score = 0;
	}
}
