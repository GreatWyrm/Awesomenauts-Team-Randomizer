package nautsTeamRandomizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.omg.CORBA.SystemException;

import nautsTeamRandomizer.AwesomenautData.Awesomenaut;
import nautsTeamRandomizer.Model.AwesomenautsInfo;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;
import nautsTeamRandomizer.Model.PlayerList;
import nautsTeamRandomizer.View.CreatePlayerGUI;
import nautsTeamRandomizer.View.DisplayTeamGUI;
import nautsTeamRandomizer.View.EditPlayerGUI;
import nautsTeamRandomizer.View.MainGUI;
import nautsTeamRandomizer.View.PlayerSelectionGUI;

public class TeamRandomizerController {
	private PlayerList playerList;
	private MainGUI mainGUI;
	private CreatePlayerGUI createPlayerGUI;
	private EditPlayerGUI editPlayerGUI;
	private PlayerSelectionGUI selectPlayerGUI;
	private Awesomenaut restrictedNaut0;
	private Awesomenaut restrictedNaut1;
	private DisplayTeamGUI displayTeamGUI;
	private final String FILE_NAME = "playerdata.txt";
	
	public TeamRandomizerController() {
		mainGUI = new MainGUI(this);
		playerList = new PlayerList();
	}
	
	public void createNewPlayerGUI() {
		createPlayerGUI = new CreatePlayerGUI(this);
	}
	public void addNewPlayer(AwesomenautsPlayer newPlayer) {
		playerList.addPlayer(newPlayer);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void deletePlayer(int index) {
		playerList.removePlayer(index);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void edit(int index) {
		editPlayerGUI = new EditPlayerGUI(this, playerList.getPlayer(index), index);
	}
	public AwesomenautsPlayer getPlayer(int index) {
		return playerList.getPlayer(index);
	}
	public void editPlayer(AwesomenautsPlayer player, int index) {
		playerList.overwritePlayer(player, index);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void selectPlayer(int playerNum) {
		if(playerList.hasNoPlayers()) {
			JOptionPane.showMessageDialog(null, "There are no players to select from.\nPlease create some players.", "No Players Available",
					JOptionPane.WARNING_MESSAGE);
		} else {
			selectPlayerGUI = new PlayerSelectionGUI(this, playerList.getPlayerList(), playerNum);
		}
	}
	public void select(int index, int playerNum) {
		mainGUI.playerSelect(playerList.getPlayer(index), playerNum);
	}
	public String addRestrictedNaut0(String nautName) {
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(AwesomenautsInfo.AWESOMENAUTS[i].matchesNautName(nautName)) {
				restrictedNaut0 = AwesomenautsInfo.AWESOMENAUTS[i];
				return restrictedNaut0.getNautName();
			}
		}
		JOptionPane.showMessageDialog(null, "No Awesomenaut matches the name you entered\nPlease try again", "No Matches Found",
				JOptionPane.WARNING_MESSAGE);
		return "";
	}
	public String addRestrictedNaut1(String nautName) {
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(AwesomenautsInfo.AWESOMENAUTS[i].matchesNautName(nautName)) {
				restrictedNaut1 = AwesomenautsInfo.AWESOMENAUTS[i];
				return restrictedNaut1.getNautName();
			}
		}
		JOptionPane.showMessageDialog(null, "No Awesomenaut matches the name you entered", "No Matches Found",
				JOptionPane.WARNING_MESSAGE);
		return "";
	}
	public void clearSelection() {
		restrictedNaut0 = null;
		restrictedNaut1 = null;
	}
	public void randomizeTeam(AwesomenautsPlayer[] players) {
		Awesomenaut[] nauts = new Awesomenaut[3];
		if(restrictedNaut0 == null && restrictedNaut1 == null) {
			if(!(players[0] == null)) {
				nauts[0] = players[0].getRandomNaut();
				if(!(players[1] == null)) {
					nauts[1] = players[1].getRandomNaut(nauts[0]);
					if(!(players[2] == null)) {
						nauts[2] = players[2].getRandomNaut(nauts[0], nauts[1]);
					}
				} else {
					if(!(players[2] == null)) {
						nauts[2] = players[2].getRandomNaut(nauts[0]);
					}
				}
			} else {
				if(!(players[1] == null)) {
					nauts[1] = players[1].getRandomNaut();
					if(!(players[2] == null)) {
						nauts[2] = players[2].getRandomNaut(nauts[1]);
					}
				} else {
					if(!(players[2] == null)) {
						nauts[2] = players[2].getRandomNaut();
					}
				}
			}
		} else if(restrictedNaut1 == null) {
			nauts[0] = restrictedNaut0;
			if(!(players[1] == null)) {
				nauts[1] = players[1].getRandomNaut(restrictedNaut0);
				if(!(players[2] == null)) {
					nauts[2] = players[2].getRandomNaut(restrictedNaut0, nauts[1]);
				}
			} else {
				if(!(players[2] == null)) {
					nauts[2] = players[2].getRandomNaut(restrictedNaut0);
				}
			}
		} else if(restrictedNaut0 == null) {
			nauts[2] = restrictedNaut1;
			if(!(players[0] == null)) {
				nauts[0] = players[0].getRandomNaut(restrictedNaut1);
				if(!(players[1] == null)) {
					nauts[1] = players[1].getRandomNaut(restrictedNaut1, nauts[0]);
				}
			} else {
				if(!(players[1] == null)) {
					nauts[1] = players[1].getRandomNaut(restrictedNaut1);
				}
			}
		} else {
			nauts[0] = restrictedNaut0;
			nauts[2] = restrictedNaut1;
			if(!(players[1] == null)) {
				nauts[1] = players[1].getRandomNaut(restrictedNaut0, restrictedNaut1);
			}
		}
		displayTeamGUI = new DisplayTeamGUI(nauts, players);
	}
	public void save(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			AwesomenautsPlayer[] players = playerList.getPlayerList();
			for(int i = 0; i < players.length; i++) {
				writer.write(players[i].encode() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
			AwesomenautsPlayer[] players = playerList.getPlayerList();
			for(int i = 0; i < players.length; i++) {
				writer.write(players[i].encode() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			String line = reader.readLine();
			while(line != null) {
				playerList.addPlayer(decodePlayer(line));
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public AwesomenautsPlayer decodePlayer(String player) {
		int split = player.lastIndexOf(':');
		String name = player.substring(0, split);
		String nauts = player.substring(split + 1);
		return new AwesomenautsPlayer(name, nauts);
	}
	public void displayMainScreen() {
		mainGUI.displayMainScren();
	}
	public static void main(String[] args) {
			TeamRandomizerController controller = new TeamRandomizerController();
			controller.load();
			controller.displayMainScreen();
	}
}
