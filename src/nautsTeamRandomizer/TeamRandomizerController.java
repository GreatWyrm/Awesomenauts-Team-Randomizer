package nautsTeamRandomizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

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
	private Awesomenaut[] awesomenauts;
	private DisplayTeamGUI displayTeamGUI;
	private final String FILE_NAME = "playerdata.txt";
	
	public TeamRandomizerController() {
		mainGUI = new MainGUI(this);
		playerList = new PlayerList();
		awesomenauts = new Awesomenaut[3];
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
	public AwesomenautsPlayer[] getPlayerList() {
		return playerList.getPlayerList();
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
		if(nautName.toLowerCase().equals("bas the angry cheese farmer")) {
			JOptionPane.showMessageDialog(null, "Seriously?", "Easter Egg Found", JOptionPane.WARNING_MESSAGE);
			return "";
		}
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(AwesomenautsInfo.AWESOMENAUTS[i].matchesNautName(nautName)) {
				awesomenauts[0] = AwesomenautsInfo.AWESOMENAUTS[i];
				return awesomenauts[0].getNautName();
			}
		}
		JOptionPane.showMessageDialog(null, "No Awesomenaut matches the name you entered\nPlease try again", "No Matches Found",
				JOptionPane.WARNING_MESSAGE);
		return "";
	}
	public String addRestrictedNaut1(String nautName) {
		if(nautName.toLowerCase().equals("bas the angry cheese farmer")) {
			JOptionPane.showMessageDialog(null, "Seriously?", "Easter Egg Found", JOptionPane.WARNING_MESSAGE);
			return "";
		}
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(AwesomenautsInfo.AWESOMENAUTS[i].matchesNautName(nautName)) {
				awesomenauts[2] = AwesomenautsInfo.AWESOMENAUTS[i];
				return awesomenauts[2].getNautName();
			}
		}
		JOptionPane.showMessageDialog(null, "No Awesomenaut matches the name you entered", "No Matches Found",
				JOptionPane.WARNING_MESSAGE);
		return "";
	}
	public void clearSelection() {
		for(int i = 0; i < awesomenauts.length; i++) {
			awesomenauts[i] = null; 
		}
	}
	public void randomizeTeam(AwesomenautsPlayer[] players, boolean useSkins) {
		for(int i = 0; i < players.length; i++) {
			if(players[i] != null) {
				awesomenauts[i] = generateNaut(players[i], i);
			}
		}
		displayTeamGUI = new DisplayTeamGUI(awesomenauts, players, useSkins);
	}
	private Awesomenaut generateNaut(AwesomenautsPlayer player, int targetIndex) {
		Awesomenaut[] restrictedNauts = new Awesomenaut[awesomenauts.length - 1];
		int numOfNauts = 0;
		for(int i = 0; i < awesomenauts.length; i++) {
			if(i != targetIndex) {
				if(awesomenauts[i] != null) {
					restrictedNauts[numOfNauts] = awesomenauts[i];
					numOfNauts++;
				}
			}
		}
		if(numOfNauts == 0) {
			return player.getRandomNaut();
		} else if(numOfNauts == 1) {
			return player.getRandomNaut(restrictedNauts[0]);
		} else {
			return player.getRandomNaut(restrictedNauts[0], restrictedNauts[1]);
		}
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
		save(FILE_NAME);
	}
	public void load() {
		load(FILE_NAME);
	}
	public void load(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				playerList.addPlayer(decodePlayer(line));
				line = reader.readLine();
			}
			reader.close();
			mainGUI.updatePlayerList(playerList.getPlayerList());
		} catch(Exception e) {

		}
	}
	public void deleteFile() {
		deleteFile(FILE_NAME);
	}
	public void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();
	}
	public AwesomenautsPlayer decodePlayer(String player) {
		int split = player.lastIndexOf(':');
		String name = player.substring(0, split);
		String nauts = player.substring(split + 1);
		return new AwesomenautsPlayer(name, nauts);
	}
	public void displayMainScreen() {
		mainGUI.displayMainScreen();
	}
	public static void main(String[] args) {
			TeamRandomizerController controller = new TeamRandomizerController();
			controller.load();
			controller.displayMainScreen();
	}
}
