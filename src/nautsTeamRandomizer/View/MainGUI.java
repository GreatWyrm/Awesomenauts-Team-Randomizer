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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	private JTextField[] playerFields = new JTextField[3];
	private AwesomenautsPlayer[] players = new AwesomenautsPlayer[3];
	private JButton nautName0 = new JButton("Enter 'Naut Name");
	private JButton nautName2 = new JButton("Enter 'Naut Name");
	private JButton generateTeam = new JButton("Randomize!");
	private JButton clear = new JButton("Clear Selection");
	private JButton saveExit = new JButton("Save Players and Exit");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuPlayer = new JMenu("Players");
	private JMenuItem menuRefreshPlayers = new JMenuItem("Refresh Player List");
	private JMenuItem menuSavePlayers = new JMenuItem("Save Player List");
	
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
		for(int i = 0; i < playerFields.length; i++) {
			playerFields[i] = new JTextField("");
			generationPanel.add(playerFields[i]);
		}
		generationPanel.add(nautName0);
		generationPanel.add(nautName2);
		generationPanel.add(generateTeam);
		generationPanel.add(clear);
		generationPanel.add(saveExit);	
		generationPanel.setLayout(null);
		teamLabel.setBounds(110, 0, 200, 50);
		optionsLabel.setBounds(0, 100, 200, 50);
		optionsLabel0.setBounds(50, 120, 500, 50);
		optionsLabel1.setBounds(50, 140, 400, 50);
		optionsLabel2.setBounds(50, 160, 300, 50);
		playerSelector0.setBounds(30, 400, 140, 25);
		playerSelector1.setBounds(180, 520, 140, 25);
		playerSelector2.setBounds(330, 400, 140, 25);
		playerFields[0].setBounds(30, 430, 140, 25);
		playerFields[1].setBounds(180, 550, 140, 25);
		playerFields[2].setBounds(330, 430, 140, 25);
		nautName0.setBounds(30, 460, 140, 25);
		nautName2.setBounds(330, 460, 140, 25);
		generateTeam.setBounds(180, 700, 160, 40);
		clear.setBounds(30, 700, 140, 25);
		saveExit.setBounds(350, 700, 160, 25);

		setJMenuBar(menuBar);
		menuBar.add(menuPlayer);
		menuPlayer.add(menuSavePlayers);
		menuPlayer.add(menuRefreshPlayers);
		
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
				String result = parentController.addRestrictedNaut0(playerFields[0].getText());
				if(!result.equals("")) {
					playerFields[0].setText(result);
					playerFields[0].setEditable(false);
				}
			}
		});
		nautName2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = parentController.addRestrictedNaut1(playerFields[2].getText());
				if(!result.equals("")) {
					playerFields[2].setText(result);
					playerFields[2].setEditable(false);
				}
			}
		});
		generateTeam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					parentController.randomizeTeam(players);	
			}
		});
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		saveExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.save();
				System.exit(0);		
			}
		});
		menuSavePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.save();
			}
		});
		menuRefreshPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePlayerList(parentController.getPlayerList());
			}
		});
		setLayout(new GridLayout());
		setSize(1200, 850);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void displayMainScren() {
		setVisible(true);
	}

	public void updatePlayerList(AwesomenautsPlayer[] list) {
		playerListModel.clear();
		for (AwesomenautsPlayer player : list) {
			playerListModel.addElement(player);
		}
		reset();
	}
	public void playerSelect(AwesomenautsPlayer player, int index) {
		players[index] = player;
		playerFields[index].setText(players[index].getPlayerName());
		playerFields[index].setEditable(false);
	}
	private void reset() {
		for(int i = 0; i < players.length; i++) {
			players[i] = null;
			playerFields[i].setEditable(true);
			playerFields[i].setText("");
		}
		parentController.clearSelection();
	}
}
