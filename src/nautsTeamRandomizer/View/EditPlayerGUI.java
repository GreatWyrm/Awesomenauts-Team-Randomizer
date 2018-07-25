package nautsTeamRandomizer.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsInfo;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class EditPlayerGUI extends JFrame{
	private JButton savePlayer = new JButton("Save");
	private JTextField playerNameField = new JTextField("Name");
	private JCheckBox hasAllNautsBox = new JCheckBox("I own all of the 'Nauts");
	private JCheckBox[] hasNautsBoxes = new JCheckBox[AwesomenautsInfo.NUM_OF_NAUTS];
	private JList<String>[] skinLists = (JList<String>[]) new JList[AwesomenautsInfo.NUM_OF_NAUTS];
	private JPanel[] hasNautsPanels = new JPanel[AwesomenautsInfo.NUM_OF_NAUTS];
	private JPanel nautsPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	GridLayout layout = new GridLayout(1, 2);
	GridLayout layout2 = new GridLayout(2, 1);
	GridLayout nautsPanelLayout = new GridLayout(4, 11);
	
	public EditPlayerGUI(TeamRandomizerController parent, AwesomenautsPlayer player, int index) {
		super("Edit Player");
		setLayout(layout2);
		add(otherPanel);
		add(nautsPanel);
        otherPanel.add(playerNameField);
        playerNameField.setText(player.getPlayerName());
		otherPanel.add(hasAllNautsBox);
		hasAllNautsBox.setSelected(player.getHasAllNauts());
		otherPanel.add(savePlayer);
		otherPanel.setLayout(null);
		playerNameField.setBounds(100, 100, 300, 25);
		hasAllNautsBox.setBounds(700, 100, 160, 25);
		savePlayer.setBounds(1200, 100, 140, 25);
		savePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(playerNameField, "Please enter in a name", "No Name Entered", JOptionPane.INFORMATION_MESSAGE);
				} else {
					boolean[][] hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS][];
					for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
						hasNauts[i] = new boolean[AwesomenautsInfo.AWESOMENAUTS[i].getNumOfSkins() + 1];
					}
					for(int i = 0; i < hasNauts.length; i++) {
						hasNauts[i][0] = hasNautsBoxes[i].isSelected();
						int[] skins = skinLists[i].getSelectedIndices();
						for(int j = 0; j < skins.length; j++) {
							hasNauts[i][skins[j] + 1] = true;
						}
					}
					AwesomenautsPlayer player = new AwesomenautsPlayer(playerNameField.getText(), hasAllNautsBox.isSelected(), hasNauts);
					parent.editPlayer(player, index);
					closeWindow();
				}
			}
		});
		for(int i = 0; i < skinLists.length; i++) {
			skinLists[i] = new JList<String>( AwesomenautsInfo.AWESOMENAUTS[i].getAllSkins());
			skinLists[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			skinLists[i].setVisible(false);
		}
		nautsPanel.setLayout(nautsPanelLayout);
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNautsBoxes[i] = new JCheckBox(AwesomenautsInfo.AWESOMENAUTS[i].getNautName());
			hasNautsPanels[i] = new JPanel(layout2); 
			nautsPanel.add(hasNautsPanels[i]);
			hasNautsPanels[i].add(hasNautsBoxes[i]);
			hasNautsPanels[i].add(skinLists[i]);
			if(player.getHasNauts(i, 0)) {
				hasNautsBoxes[i].setSelected(true);
				skinLists[i].setVisible(true);
			}
			int[] indices = new int[player.getHasNauts(i).length - 1];
			for(int j = 0; j < player.getHasNauts(i).length - 1; j++) {
				if(player.getHasNauts(i, j + 1)) {
					indices[j] = j;
				} else {
					// setSelectedIndices - Indices greater than or equal to the model size are ignored
					indices[j] = player.getHasNauts(i).length + 1;
				}
				skinLists[i].setSelectedIndices(indices);
			}
			final JCheckBox currentBox = hasNautsBoxes[i];
			final Integer skinIndex = i;
			currentBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(currentBox.isSelected()) {
						skinLists[skinIndex].setVisible(true);
					} else if(!currentBox.isSelected()) {
						skinLists[skinIndex].setVisible(false);
					}
				}
			});
		}
		playerNameField.requestFocus();
		playerNameField.selectAll();
		setSize(1600, 900);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
