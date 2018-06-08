package nautsTeamRandomizer.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import nautsTeamRandomizer.AwesomenautData.Awesomenaut;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class DisplayTeamGUI extends JFrame {
	private JTextField[] nautDisplayField = new JTextField[3];
	private JTextField[] playerDisplayField = new JTextField[3];
	private JButton close = new JButton("Close");
	
	
	// nauts Array and players array are the same length
	public DisplayTeamGUI(Awesomenaut[] nauts, AwesomenautsPlayer[] players) {
		super("Awesomenaut Team Randomizer");
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		for(int i = 0; i < nauts.length; i++) {
			if(!(nauts[i] == null)) {
				nautDisplayField[i] = new JTextField(nauts[i].getNautName());
				add(nautDisplayField[i]);
				nautDisplayField[i].setEditable(false);
				if(i == 0) {
					nautDisplayField[0].setBounds(50, 100, 120, 25);
				} else if(i == 1) {
					nautDisplayField[1].setBounds(180, 150, 120, 25);
				} else if(i == 2) {
					nautDisplayField[2].setBounds(310, 100, 120, 25);
				}
			}
			if(!(players[i] == null)) {
				playerDisplayField[i] = new JTextField(players[i].getPlayerName()); 
				add(playerDisplayField[i]);
				playerDisplayField[i].setEditable(false);
				if(i == 0) {
					playerDisplayField[0].setBounds(50, 140, 120, 25);
				} else if(i == 1) {
					playerDisplayField[1].setBounds(180, 190, 120, 25);
				} else if(i == 2) {
					playerDisplayField[2].setBounds(310, 140, 120, 25);
				}
			}
		}
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
