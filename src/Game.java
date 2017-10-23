import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author fernandavargas
 */

public class Game extends JPanel implements KeyListener, Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 450;
	public static final int HEIGHT = 550;
	public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);
	
	private Thread game;
	//Keep track if the thread game is running
	private boolean running;
	//All the draws will be done here
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GameBoard board;
	
	/**
	 * Constructor method
	 * Create the GameBoard 
	 */
	public Game(){
		//Allows keyBoard
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		//Center the board
		board = new GameBoard(WIDTH/10, HEIGHT/3);
	}
	
	/**
	 * Render the board
	 * Create the Graphics to render
	 */
	private void render(){
		//Actual image to drawn to
		//This image has the board, pieces, etc
		//Keeps track of everything that is being drawn
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(new Color(0xfafae7));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Render Board
		board.render(g);
		g.dispose();
		
		//Actual JPanel graphics
		Graphics2D g2d = (Graphics2D) getGraphics();
		//Draw the image to the JPanel
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
	}
	
	//Will use the board class
	/**
	 * Call the update function in the class
	 * GameBoard to update the board image
	 */
	private void update(){
		board.update();
	}
	
	/**
	 * Create the main thread
	 * Make sure the entire method gets call and the thread manager wont 
	 * switch threads in the middles
	 */
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		game = new Thread(this,"game");
		game.start();
		
	}
	
	/**
	 * Restart the game 
	 */
	public void restart(){
		board.restart();
	}
	
	/**
	 * Stop and exit the game
	 */
	public synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		System.exit(0);
		
	}

	/**
	 * Run the main thread
	 * Render the images
	 */
	public void run() {
		double nPerUpdate = 1000000000.0 / 60;
		
		//Last update time in nanosecond
		double then = System.nanoTime();
		double unprocessed = 0;
		
		while(running){
			boolean shouldRender = false;
			double now = System.nanoTime();
			//Check how many times it should update
			unprocessed += (now - then)/ nPerUpdate;
			then = now;
			
			//update queue
			while(unprocessed >= 1){
				update();
				unprocessed--;
				shouldRender = true;
			}
			
			//Render
			if(shouldRender){
				render();
				shouldRender = false;
			}else{
				try{
					Thread.sleep(1);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Called just after the user releases a key 
	 * Handle 5 keys 
	 * Up, down, right, left , spacebar 
	 */
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	        	board.moveTiles(Direction.UP);
	            break;
	        case KeyEvent.VK_DOWN:
	        	board.moveTiles(Direction.DOWN);
	            break;
	        case KeyEvent.VK_LEFT:
	        	board.moveTiles(Direction.LEFT);
	            break;
	        case KeyEvent.VK_RIGHT :
	        	board.moveTiles(Direction.RIGHT);
	            break;
	        case KeyEvent.VK_SPACE:
	        	board.restart();
	     }
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
