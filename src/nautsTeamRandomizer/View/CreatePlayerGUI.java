package nautsTeamRandomizer.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private final int NUM_OF_COLUMNS = 11;
	private JTextField playerNameField = new JTextField("Name");
	private JCheckBox hasAllNautsBox = new JCheckBox("I own all of the 'Nauts");
	private JCheckBox[] hasNautsBoxes = new JCheckBox[AwesomenautsInfo.NUM_OF_NAUTS];
	private JList<String>[] skinLists = (JList<String>[]) new JList[AwesomenautsInfo.NUM_OF_NAUTS];
	private JPanel[] hasNautsPanels = new JPanel[AwesomenautsInfo.NUM_OF_NAUTS];
	private JButton createPlayer = new JButton("Create Player");
	private JLabel creationInfo0 = new JLabel("-To create a player, enter in a name, select the Awesomenauts and skins you own, and then press Create Player");
	private JPanel[] nautsPanel = new JPanel[NUM_OF_COLUMNS];
	GridLayout layout = new GridLayout(2, 1);
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	GridLayout nautsPanelLayout = new GridLayout(4, 1);
	
	public CreatePlayerGUI(TeamRandomizerController parent) {
		super("Create New Player");
		setLayout(gridBagLayout);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(playerNameField, constraints);
		constraints.gridx = 6;
		constraints.gridy = 0;
		add(hasAllNautsBox, constraints);
		constraints.gridx = 10;
		constraints.gridy = 0;
		add(createPlayer, constraints);
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		add(creationInfo0, constraints);
		constraints.gridwidth = 1;
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		for(int i = 0; i < nautsPanel.length; i++) {
			nautsPanel[i]= new JPanel(); 
			add(nautsPanel[i], constraints);
			nautsPanel[i].setLayout(nautsPanelLayout);
			constraints.gridx++;
		}
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
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNautsBoxes[i] = new JCheckBox(AwesomenautsInfo.AWESOMENAUTS[i].getNautName());
			hasNautsPanels[i] = new JPanel(layout);
			nautsPanel[i % NUM_OF_COLUMNS].add(hasNautsPanels[i]);
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
