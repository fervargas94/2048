import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author fernandavargas
 *
 */

public class Tile {
	
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	public static final int SLIDE_SPEED = 25;
	public static final int ARC_WIDTH = 15;
	public static final int ARC_HEIGHT = 15;
	
	private int value;
	private BufferedImage tileImage;
	private Color background;
	private Color text;
	private int x;
	private int y;
	private Font font;
	private boolean canCombined = true;
	
	/**
	 * Constructor
	 * @param value
	 * @param x
	 * @param y
	 */
	public Tile(int value, int x, int y){
		this.value = value;
		this.x = x;
		this.y = y;
		//Transparent image
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}
	
	/**
	 * Define tile color 
	 * Draw tile with it's specific color and letter size
	 * Calculate the value position in the tile
	 */
	private void drawImage(){
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		if (value == 2) {
			background = new Color(0xe9e9e9);
			text = new Color(0x000000);
		}
		else if (value == 4) {
			background = new Color(0xe6daab);
			text = new Color(0x000000);
		}
		else if (value == 8) {
			background = new Color(0xf79d3d);
			text = new Color(0xffffff);
		}
		else if (value == 16) {
			background = new Color(0xf28007);
			text = new Color(0xffffff);
		}
		else if (value == 32) {
			background = new Color(0xf55e3b);
			text = new Color(0xffffff);
		}
		else if (value == 64) {
			background = new Color(0xff0000);
			text = new Color(0xffffff);
		}
		else if (value == 128) {
			background = new Color(0xe9de84);
			text = new Color(0xffffff);
		}
		else if (value == 256) {
			background = new Color(0xf6e873);
			text = new Color(0xffffff);
		}
		else if (value == 512) {
			background = new Color(0xf5e455);
			text = new Color(0xffffff);
		}
		else if (value == 1024) {
			background = new Color(0xf7e12c);
			text = new Color(0xffffff);
		}
		else if (value == 2048) {
			background = new Color(0xffe400);
			text = new Color(0xffffff);
		}
		else if(value == 0){
			background = Color.lightGray;
			text = Color.black;
		}
		else{
			background = new Color(0x000000);
			text = new Color(0xffffff);
		}
		
		//Transparent color
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Number tile
		g.setColor(background);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		g.setColor(text);
		
		if(value <= 64){
			font = Game.main.deriveFont(36f);
		}else{
			font = Game.main;
		}
		g.setFont(font);
		
		//Check x position
		g.setFont(font);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(" " + value, g);
		
		int drawX = WIDTH / 3  - (int)bounds.getWidth() / 3;
		
		//Check y position
		g.setFont(font);
		TextLayout tl = new TextLayout(" " + value, font, g.getFontRenderContext());
		
		int drawY = HEIGHT / 2 + (int)tl.getBounds().getHeight() / 2;
		g.drawString(" "+ value, drawX, drawY);
		g.dispose();
	}
	
	
	/**
	 * Render the tile in the board
	 * @param g
	 */
	public void render(Graphics2D g){
		g.drawImage(tileImage, x, y, null);
	}
	
	/**
	 * Get tile's value
	 * @return
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Set tile's value
	 * @param value
	 */
	public void setValue(int value){
		this.value = value;
		drawImage();
	}

	/**
	 * Check if the tile can combine 
	 * Depends on previous status
	 * @return
	 */
	public boolean isCanCombined() {
		return canCombined;
	}

	/**
	 * Set canCombined variable to the given value
	 * @param canCombined
	 */
	public void setCanCombined(boolean canCombined) {
		this.canCombined = canCombined;
	}
	
	/**
	 * Obtain X position
	 * @return int
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Set X position
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Get Y position
	 * @return int
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Set Y position
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Get tile's color 
	 * @return Color 
	 */
	public Color getColor(){
		return background;
	}
	
	
}
