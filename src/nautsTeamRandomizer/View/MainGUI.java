package nautsTeamRandomizer.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsMap;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

public class MainGUI extends JFrame {

	// UID - Unused
	private static final long serialVersionUID = 6530073372542178903L;
	// CreatePlayer - button that allows creation of AwesomenautsPlayer objects
	private JButton createPlayer = new JButton("Create Player");
	// DeletePlayer - button that allows deletion of players
	private JButton deletePlayer = new JButton("Delete Player");
	// editPlayer - button that allows the editing of AwesomenautsPlayer objects
	private JButton editPlayer = new JButton("Edit Player");
	// playerListModel - Listmodel to contain the awesomenautsPlayer objects
	private DefaultListModel<AwesomenautsPlayer> playerListModel = new DefaultListModel<AwesomenautsPlayer>();
	//  playerList - list to display the AwesomenautsPlayer objects
	private JList<AwesomenautsPlayer> playerList = new JList<AwesomenautsPlayer>(playerListModel);
	private JButton createMap = new JButton("Create Map");
	private JButton deleteMap = new JButton("Delete Map");
	private JButton editMap = new JButton("Edit Map");
	private DefaultListModel<AwesomenautsMap> mapListModel = new DefaultListModel<AwesomenautsMap>();
	private JList<AwesomenautsMap> mapList = new JList<AwesomenautsMap>(mapListModel);
	// playerPanel - panel to hold all of the components related to players
	private JPanel playerPanel = new JPanel();
	// generationPanel - panel to hold all of the components related to team randomization 
	private JPanel generationPanel = new JPanel();
	private JPanel mapPanel = new JPanel();
	// parentController - reference to the parent controller to call methods it in
	private TeamRandomizerController parentController;
	// labels - display static text describing the function of the program
	private JLabel teamLabel = new JLabel("Awesomenaut Team Randomizer");
	private JLabel playerLabel = new JLabel("Awesomenaut Player List");
	private JLabel optionsLabel = new JLabel("Team Selection Options:");
	private JLabel optionsLabel0 = new JLabel("-Select a player that is on the Player List for a random 'Naut");
	private JLabel optionsLabel1 = new JLabel("-Enter in the name of an Awesomenaut that is already on your team");
	private JLabel optionsLabel2 = new JLabel("-Leave the box blank and it will ignore it");
	// player Selector buttons - allows user to select a player to fill in an empty space - move to an array eventually
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
	private JMenu menuGeneration = new JMenu("Generation Settings");
	private JMenuItem menuRefreshPlayers = new JMenuItem("Refresh Player List");
	private JMenuItem menuSavePlayers = new JMenuItem("Save Player List");
	private JMenu menuSortPlayers = new JMenu("Sort Players");
	private JMenuItem menuSortAscending = new JMenuItem("Sort Players Alphabetically Ascending");
	private JMenuItem menuSortDescending = new JMenuItem("Sort Players Alphabetically Descending");
	private JCheckBoxMenuItem menuUseSkins = new JCheckBoxMenuItem("Use Skins");
	
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
		
		add(mapPanel);
		mapPanel.add(mapList);
		mapPanel.add(createMap);
		mapPanel.add(deleteMap);
		mapPanel.add(editMap);
		mapPanel.setLayout(null);
		createMap.setBounds(35, 670, 120, 25);
		editMap.setBounds(165, 670, 120, 25);
		deleteMap.setBounds(105, 700, 120, 25);
		mapList.setBounds(10, 60, 300, 550);
		
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

		// adding the menuBar and it's components to the window
		setJMenuBar(menuBar);
		menuBar.add(menuPlayer);
		menuBar.add(menuGeneration);
		menuPlayer.add(menuSavePlayers);
		menuPlayer.add(menuRefreshPlayers);
		menuPlayer.add(menuSortPlayers);
		menuSortPlayers.add(menuSortAscending);
		menuSortPlayers.add(menuSortDescending);
		menuGeneration.add(menuUseSkins);
		
		// Set the List options
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerList.setLayoutOrientation(JList.VERTICAL);

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
					parentController.edit(playerListModel.get(index));
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
					parentController.deletePlayer(playerListModel.getElementAt(index));
				}

			}
		});
		createMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		editMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		deleteMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = mapList.getSelectedIndex();
				if (index == -1) {
					JOptionPane.showMessageDialog(mapList, "A map has not been selected", "No Selection Made",
							JOptionPane.WARNING_MESSAGE);
				} else {
					//parentController.deleteMap(index);
				}

				
			}
		});
		playerSelector0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSelectionGUI(0);
			}
		});
		playerSelector1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSelectionGUI(1);
			}
		});
		playerSelector2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSelectionGUI(2);
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
					parentController.randomizeTeam(players, menuUseSkins.isSelected());	
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
				parentController.savePlayers();
				System.exit(0);		
			}
		});
		menuSavePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentController.savePlayers();
			}
		});
		menuSavePlayers.setToolTipText("Save the current player list");
		menuRefreshPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePlayerList(parentController.getPlayerList());
			}
		});
		menuSortAscending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AwesomenautsPlayer[] array = new AwesomenautsPlayer[playerListModel.getSize()];
				for(int i = 0; i < array.length; i++) {
					array[i] = playerListModel.getElementAt(i);
				}
				selectionSort(array);
				for(int i = 0; i < array.length; i++) {
					playerListModel.setElementAt(array[i], i);
				}
			}
		});
		menuSortDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AwesomenautsPlayer[] array = new AwesomenautsPlayer[playerListModel.getSize()];
				for(int i = 0; i < array.length; i++) {
					array[i] = playerListModel.getElementAt(i);
				}
				selectionSort(array);
				for(int i = 0; i < array.length; i++) {
					playerListModel.setElementAt(array[array.length - 1 - i], i);
				}
			}
		});
		setLayout(new GridLayout());
		setSize(1600, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void displayMainScreen() {
		setVisible(true);
	}

	public void updatePlayerList(AwesomenautsPlayer[] list) {
		playerListModel.clear();
		for (AwesomenautsPlayer player : list) {
			playerListModel.addElement(player);
		}
		reset();
	}
	public void updateMapList(AwesomenautsMap[] list) {
		mapListModel.clear();
		for(AwesomenautsMap map : list) {
			mapListModel.addElement(map);
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
    private void selectionSort(AwesomenautsPlayer array[])
    {
        for (int i = 0; i < array.length-1; i++)
        {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j].getPlayerName().compareTo(array[min].getPlayerName()) < 0) {
                	min = j;
                }
            }
            AwesomenautsPlayer temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }
    private void createSelectionGUI(int num) {
    	PlayerSelectionGUI gui = new PlayerSelectionGUI(this, playerListModel, num);
    }
}
