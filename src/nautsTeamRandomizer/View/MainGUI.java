package nautsTeamRandomizer.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 6530073372542178903L;
	private JButton createPlayer = new JButton("Create Player");
	private JButton deletePlayer = new JButton("Delete Player");
	private JButton editPlayer = new JButton("Edit Player");
	private DefaultListModel<AwesomenautsPlayer> playerListModel = new DefaultListModel<AwesomenautsPlayer>();
	private JList<AwesomenautsPlayer> playerList = new JList<AwesomenautsPlayer>(playerListModel);
	private JScrollPane listScroller = new JScrollPane(playerList);
	private JPanel playerPanel = new JPanel();
	private JPanel generationPanel = new JPanel();
	private TeamRandomizerController parentController;
	private JLabel teamLabel = new JLabel("Awesomenaut Team Randomizer");
	private JLabel playerLabel = new JLabel("Awesomenaut Player List");
	private JLabel optionsLabel = new JLabel("Team Selection Options:");
	private JLabel optionsLabel0 = new JLabel("-Select a player that is on the Player List for a random 'Naut");
	private JLabel optionsLabel1 = new JLabel("-Enter in the name of an Awesomenaut that is already on your team");
	private JLabel optionsLabel2 = new JLabel("-Leave the box blank and it will ignore it");
	private JButton playerSelector0 = new JButton("Select Player");
	private JButton playerSelector1 = new JButton("Select Player");
	private JButton playerSelector2 = new JButton("Select Player");
	private JTextField player0Field = new JTextField("");
	private JTextField player1Field = new JTextField("");
	private JTextField player2Field = new JTextField("");
	private AwesomenautsPlayer player0;
	private AwesomenautsPlayer player1;
	private AwesomenautsPlayer player2;
	private JButton nautName0 = new JButton("Enter 'Naut Name");
	private JButton nautName1 = new JButton("Enter 'Naut Name");
	private JButton generateTeam = new JButton("Randomize!");
	
	public MainGUI(TeamRandomizerController parent) {
		super("Awesomenaut Team Randomizer");
		parentController = parent;

		add(playerPanel);
		playerPanel.add(playerList);
		playerPanel.add(createPlayer);
		playerPanel.add(deletePlayer);
		playerPanel.add(editPlayer);
		playerPanel.add(playerLabel);
		playerPanel.setLayout(null);
		createPlayer.setBounds(35, 670, 120, 25);
		editPlayer.setBounds(165, 670, 120, 25);
		deletePlayer.setBounds(105, 700, 120, 25);
		playerList.setBounds(10, 60, 300, 550);
		playerLabel.setBounds(80, 0, 300, 50);
		
		add(generationPanel);
		generationPanel.add(teamLabel);
		generationPanel.add(optionsLabel);
		generationPanel.add(optionsLabel0);
		generationPanel.add(optionsLabel1);
		generationPanel.add(optionsLabel2);
		generationPanel.add(playerSelector0);
		generationPanel.add(playerSelector1);
		generationPanel.add(playerSelector2);
		generationPanel.add(player0Field);
		generationPanel.add(player1Field);
		generationPanel.add(player2Field);
		generationPanel.add(nautName0);
		generationPanel.add(nautName1);
		generationPanel.add(generateTeam);
		generationPanel.setLayout(null);
		teamLabel.setBounds(110, 0, 200, 50);
		optionsLabel.setBounds(0, 100, 200, 50);
		optionsLabel0.setBounds(50, 120, 500, 50);
		optionsLabel1.setBounds(50, 140, 400, 50);
		optionsLabel2.setBounds(50, 160, 300, 50);
		playerSelector0.setBounds(30, 400, 140, 25);
		playerSelector1.setBounds(180, 520, 140, 25);
		playerSelector2.setBounds(330, 400, 140, 25);
		player0Field.setBounds(30, 430, 140, 25);
		player1Field.setBounds(180, 550, 140, 25);
		player2Field.setBounds(330, 430, 140, 25);
		nautName0.setBounds(30, 460, 140, 25);
		nautName1.setBounds(330, 460, 140, 25);
		generateTeam.setBounds(180, 700, 160, 40);

		// Set the List options
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setLayoutOrientation(JList.VERTICAL);
		listScroller.setPreferredSize(new Dimension(250, 80));

		// Add the action listeners. Hey! Listen!
		createPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.createNewPlayerGUI();
			}
		});
		editPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = playerList.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerList, "A player has not been selected", "No Selection Made",
							JOptionPane.WARNING_MESSAGE);
				} else {
					parentController.edit(index);
				}
			}
		});
		deletePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = playerList.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(playerList, "A player has not been selected", "No Selection Made",
							JOptionPane.WARNING_MESSAGE);
				} else {
					parentController.deletePlayer(index);
				}

			}
		});
		playerSelector0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.selectPlayer(0);
			}
		});
		playerSelector1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.selectPlayer(1);
			}
		});
		playerSelector2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.selectPlayer(2);
			}
		});
		nautName0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = parentController.addRestrictedNaut1(player0Field.getText());
				if(!result.equals("")) {
					player0Field.setText(result);
					player0Field.setEditable(false);
				}
			}
		});
		nautName1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = parentController.addRestrictedNaut1(player2Field.getText());
				if(!result.equals("")) {
					player2Field.setText(result);
					player2Field.setEditable(false);
				}
			}
		});
		generateTeam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(player0 == null && player1 == null && player2 == null) {
					JOptionPane.showMessageDialog(null, "You have not made any selections!", "Cannot Generate Team",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if(player0 == null) {
						if(player1 == null) {
							// randomize player2
						} else if(player2 == null) {
							// Randomzie player1
						} else {
							// Randomize player1 and player2
						}
					} else if(player1 == null) {
						if(player0 == null) {
							// randomize player2
						} else if(player2 == null) {
							// Randomzie player0
						} else {
							// Randomize player0 and player2
						}
					} else if(player2 == null) {
						if(player0 == null) {
							// randomize player1
						} else if(player1 == null) {
							// Randomzie player0
						} else {
							// Randomize player0 and player1
						}
					} else {
						// Randomzie all three players
					}
					
				}
				
			}
		});
		setLayout(new GridLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
		setVisible(true);
	}

	public void updatePlayerList(AwesomenautsPlayer[] list) {
		playerListModel.clear();
		for (AwesomenautsPlayer player : list) {
			playerListModel.addElement(player);
		}
	}
	public void playerSelect(AwesomenautsPlayer player, int index) {
		if(index == 0) {
			player0 = player;
			player0Field.setText(player0.getPlayerName());
			player0Field.setEditable(false);
		} else if(index == 1) {
			player1 = player;
			player1Field.setText(player1.getPlayerName());
			player1Field.setEditable(false);
		} else if(index == 2) {
			player2 = player;
			player2Field.setText(player2.getPlayerName());
			player2Field.setEditable(false);
		}
	}
	private void reset() {
		player0 = null;
		player1 = null;
		player2 = null;
		player0Field.setEditable(true);
		player1Field.setEditable(true);
		player2Field.setEditable(true);
		player0Field.setText("");
		player1Field.setText("");
		player2Field.setText("");
		parentController.clearSelection();
	}
}
