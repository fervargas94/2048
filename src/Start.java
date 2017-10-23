import javax.swing.JFrame;

/**
 * 
 * @author fernandavargas
 */

public class Start extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public Game game = new Game();
	public JFrame window = new JFrame("2048");
	
	/**
	 * Constructor
	 * Creates JFrame 
	 */
	public Start(){
		//Game is a JPanel
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(game);
		window.pack();
		//Center the screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		game.start();
	}
	
	public static void main(String[] args){
		Start play = new Start();
	}

}
