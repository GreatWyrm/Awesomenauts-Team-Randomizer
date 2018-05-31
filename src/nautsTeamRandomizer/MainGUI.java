package nautsTeamRandomizer;

import java.util.ArrayList;

import javax.swing.JFrame;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6530073372542178903L;
	private ArrayList<AwesomenautsPlayer> playerList;
	public MainGUI() {
		super("Awesomenaut Team Randomizer");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainGUI gui = new MainGUI();
	}
}
