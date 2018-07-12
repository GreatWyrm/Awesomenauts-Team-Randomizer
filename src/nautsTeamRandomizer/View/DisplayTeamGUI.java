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
	public DisplayTeamGUI(Awesomenaut[] nauts, AwesomenautsPlayer[] players, boolean useSkins) {
		super("Awesomenaut Team Randomizer");
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		for(int i = 0; i < nauts.length; i++) {
			if(!(nauts[i] == null)) {
				if(useSkins) {
					nautDisplayField[i] = new JTextField(players[i].getRandomSkin(nauts[i]));
					add(nautDisplayField[i]);
					nautDisplayField[i].setEditable(false);
				} else {
					nautDisplayField[i] = new JTextField(nauts[i].getNautName());
					add(nautDisplayField[i]);
					nautDisplayField[i].setEditable(false);
				}
				if(i == 0) {
					nautDisplayField[0].setBounds(20, 100, 150, 25);
				} else if(i == 1) {
					nautDisplayField[1].setBounds(180, 150, 150, 25);
				} else if(i == 2) {
					nautDisplayField[2].setBounds(340, 100, 150, 25);
				}
			}
			if(!(players[i] == null)) {
				playerDisplayField[i] = new JTextField(players[i].getPlayerName()); 
				add(playerDisplayField[i]);
				playerDisplayField[i].setEditable(false);
				if(i == 0) {
					playerDisplayField[0].setBounds(20, 140, 150, 25);
				} else if(i == 1) {
					playerDisplayField[1].setBounds(180, 190, 150, 25);
				} else if(i == 2) {
					playerDisplayField[2].setBounds(340, 140, 150, 25);
				}
			}
		}
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(510, 500);
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
