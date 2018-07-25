package nautsTeamRandomizer.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

@SuppressWarnings({"serial", "unchecked"})
public class CreatePlayerGUI extends JFrame{
	private JTextField playerNameField = new JTextField("Name");
	private JCheckBox hasAllNautsBox = new JCheckBox("I own all of the 'Nauts");
	private JCheckBox[] hasNautsBoxes = new JCheckBox[AwesomenautsInfo.NUM_OF_NAUTS];
	private JList<String>[] skinLists = (JList<String>[]) new JList[AwesomenautsInfo.NUM_OF_NAUTS];
	private JPanel[] hasNautsPanels = new JPanel[AwesomenautsInfo.NUM_OF_NAUTS];
	private JButton createPlayer = new JButton("Create Player");
	private JPanel nautsPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	GridLayout layout = new GridLayout(1, 2);
	GridLayout layout2 = new GridLayout(2, 1);
	GridLayout nautsPanelLayout = new GridLayout(4, 11);
	
	public CreatePlayerGUI(TeamRandomizerController parent) {
		super("Create New Player");
		setLayout(layout2);
		add(otherPanel, BorderLayout.NORTH);
		add(nautsPanel);
        otherPanel.add(playerNameField);
		otherPanel.add(hasAllNautsBox);
		otherPanel.add(createPlayer);
		otherPanel.setLayout(null);
		otherPanel.setPreferredSize(new Dimension(1600, 100));
		otherPanel.setMaximumSize(new Dimension(1600, 50));
		otherPanel.setMinimumSize(new Dimension(1600, 250)); 
		playerNameField.setBounds(100, 100, 300, 25);
		hasAllNautsBox.setBounds(700, 100, 160, 25);
		createPlayer.setBounds(1200, 100, 140, 25);
		// Set up JLists for skins
		for(int i = 0; i < skinLists.length; i++) {
			skinLists[i] = new JList<String>( AwesomenautsInfo.AWESOMENAUTS[i].getAllSkins());
			skinLists[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			skinLists[i].setVisible(false);
		}
		hasAllNautsBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < hasNautsBoxes.length; i++) {
					hasNautsBoxes[i].setSelected(true);
					skinLists[i].setVisible(true);
				}
				
			}
		});
		createPlayer.addActionListener(new ActionListener() {
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
					parent.addNewPlayer(player);
					closeWindow();
				}
			}
		});
		nautsPanel.setLayout(nautsPanelLayout);
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNautsBoxes[i] = new JCheckBox(AwesomenautsInfo.AWESOMENAUTS[i].getNautName());
			hasNautsPanels[i] = new JPanel(layout2); 
			nautsPanel.add(hasNautsPanels[i]);
			hasNautsPanels[i].add(hasNautsBoxes[i]);
			hasNautsPanels[i].add(skinLists[i]);
			final JCheckBox currentBox = hasNautsBoxes[i];
			final Integer index = i;
			currentBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(currentBox.isSelected()) {
						skinLists[index].setVisible(true);
					} else if(!currentBox.isSelected()) {
						skinLists[index].setVisible(false);
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
