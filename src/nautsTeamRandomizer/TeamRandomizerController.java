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
import nautsTeamRandomizer.Model.AwesomenautsMap;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;
import nautsTeamRandomizer.Model.MapList;
import nautsTeamRandomizer.Model.PlayerList;
import nautsTeamRandomizer.View.CreateEditPlayerGUI;
import nautsTeamRandomizer.View.DisplayTeamGUI;
import nautsTeamRandomizer.View.MainGUI;

public class TeamRandomizerController {
	private PlayerList playerList;
	private MapList mapList;
	private MainGUI mainGUI;
	private Awesomenaut[] awesomenauts;
	private DisplayTeamGUI displayTeamGUI;
	private final String DEFAULT_PLAYERS_FILE_NAME = "playerdata.txt";
	private final String DEFAULT_MAPS_FILE_NAME = "mapdata.txt";
	
	public TeamRandomizerController() {
		mainGUI = new MainGUI(this);
		playerList = new PlayerList();
		mapList = new MapList();
		awesomenauts = new Awesomenaut[3];
	}
	
	public void createNewPlayerGUI() {
		CreateEditPlayerGUI createEditPlayerGUI = new CreateEditPlayerGUI(this);
	}
	public void addNewPlayer(AwesomenautsPlayer newPlayer) {
		playerList.addPlayer(newPlayer);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void deletePlayer(int index) {
		playerList.removePlayer(index);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void edit(AwesomenautsPlayer player) {
		CreateEditPlayerGUI createEditPlayerGUI = new CreateEditPlayerGUI(this, player);
	}
	public AwesomenautsPlayer getPlayer(int index) {
		return playerList.getPlayer(index);
	}
	public AwesomenautsPlayer[] getPlayerList() {
		return playerList.getPlayerList();
	}
	public void editPlayer(AwesomenautsPlayer player) {
		playerList.overwritePlayer(player);
		mainGUI.updatePlayerList(playerList.getPlayerList());
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
	public void savePlayers(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(playerList.encodeAllPlayers());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void savePlayers() {
		savePlayers(DEFAULT_PLAYERS_FILE_NAME);
	}
	public void loadPlayers() {
		loadPlayers(DEFAULT_PLAYERS_FILE_NAME);
	}
	public void loadPlayers(String filename) {
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
	public void loadMaps() {
		loadMaps(DEFAULT_MAPS_FILE_NAME);
	}
	public void loadMaps(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				mapList.addMap(new AwesomenautsMap(line));
				line = reader.readLine();
			}
			reader.close();
			mainGUI.updatePlayerList(playerList.getPlayerList());
		} catch(Exception e) {

		}
	}
	public void deleteFile() {
		deleteFile(DEFAULT_PLAYERS_FILE_NAME);
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
			controller.loadPlayers();
			controller.loadMaps();
			controller.displayMainScreen();
	}
}
