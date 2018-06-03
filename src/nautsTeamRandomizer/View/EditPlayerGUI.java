package nautsTeamRandomizer.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsInfo;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class EditPlayerGUI extends JFrame{
	private TeamRandomizerController parentController;
	private int playerIndex;
	private JTextField playerNameField = new JTextField();
	private JCheckBox hasAllNautsBox = new JCheckBox("I own all of the 'Nauts");
	private JCheckBox[] hasNautsBoxes = new JCheckBox[AwesomenautsInfo.NUM_OF_NAUTS];
	private JButton savePlayer = new JButton("Save");
	private JPanel nautsPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	GridLayout layout = new GridLayout(1, 2);
	GridLayout otherPanelLayout = new GridLayout(3, 1, 0, 300);
	GridLayout nautsPanelLayout = new GridLayout(11, 3);
	
	public EditPlayerGUI(TeamRandomizerController parent, AwesomenautsPlayer player, int index) {
		super("Edit Player");
		parentController = parent;
		playerIndex = index;
		setLayout(layout);
		add(otherPanel);
		add(nautsPanel);
        otherPanel.add(playerNameField);
        playerNameField.setText(player.getPlayerName());
		otherPanel.add(hasAllNautsBox);
		hasAllNautsBox.setSelected(player.getHasAllNauts());
		otherPanel.add(savePlayer);
		savePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(playerNameField, "Please enter in a name", "No Name Entered", JOptionPane.INFORMATION_MESSAGE);
				} else {
					boolean[] hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS];
					for(int i = 0; i < hasNauts.length; i++) {
						hasNauts[i] = hasNautsBoxes[i].isSelected(); 
					}
					AwesomenautsPlayer player = new AwesomenautsPlayer(playerNameField.getText(), hasAllNautsBox.isSelected(), hasNauts);
					parent.editPlayer(player, playerIndex);
					closeWindow();
				}
			}
		});
		otherPanel.setLayout(otherPanelLayout);
		nautsPanel.setLayout(nautsPanelLayout);
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNautsBoxes[i] = new JCheckBox(AwesomenautsInfo.AWESOMENAUTS[i].getNautName());
			hasNautsBoxes[i].setSelected(player.getHasNauts(i));
			nautsPanel.add(hasNautsBoxes[i]);
		}
		setSize(800, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
