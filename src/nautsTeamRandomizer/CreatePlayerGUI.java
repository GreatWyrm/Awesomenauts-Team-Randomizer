package nautsTeamRandomizer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreatePlayerGUI extends JFrame{
	private JTextField playerNameField = new JTextField("Name");
	private JCheckBox hasAllNautsBox = new JCheckBox("I own all of the 'Nauts");
	private JCheckBox[] hasNautsBoxes = new JCheckBox[AwesomenautsInfo.NUM_OF_NAUTS];
	private JButton createPlayer = new JButton("Create Player");
	private JPanel nautsPanel = new JPanel();
	private JPanel otherPanel = new JPanel();
	GridLayout layout = new GridLayout(1, 2);
	GridLayout otherPanelLayout = new GridLayout(3, 1, 0, 300);
	GridLayout nautsPanelLayout = new GridLayout(11, 3);
	
	public CreatePlayerGUI() {
		super("Create New Player");
		setLayout(layout);
		add(otherPanel);
		add(nautsPanel);
        otherPanel.add(playerNameField);
		otherPanel.add(hasAllNautsBox);
		otherPanel.add(createPlayer);
		createPlayer.addActionListener(new ActionListener() {
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
					// Add the player to the list
				}
			}
		});
		otherPanel.setLayout(otherPanelLayout);
		nautsPanel.setLayout(nautsPanelLayout);
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNautsBoxes[i] = new JCheckBox(AwesomenautsInfo.AWESOMENAUTS[i].getNautName());
			nautsPanel.add(hasNautsBoxes[i]);
		}
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		CreatePlayerGUI gui = new CreatePlayerGUI();
	}
}
