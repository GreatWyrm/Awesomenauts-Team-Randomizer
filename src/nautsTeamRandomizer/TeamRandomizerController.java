package nautsTeamRandomizer;

import javax.swing.JOptionPane;

import nautsTeamRandomizer.Model.AwesomenautsPlayer;
import nautsTeamRandomizer.Model.PlayerList;
import nautsTeamRandomizer.View.CreatePlayerGUI;
import nautsTeamRandomizer.View.EditPlayerGUI;
import nautsTeamRandomizer.View.MainGUI;
import nautsTeamRandomizer.View.PlayerSelectionGUI;

public class TeamRandomizerController {
	private PlayerList playerList;
	private MainGUI mainGUI;
	private CreatePlayerGUI createPlayerGUI;
	private EditPlayerGUI editPlayerGUI;
	private PlayerSelectionGUI selectPlayerGUI;
	
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
	public void editPlayer(AwesomenautsPlayer player, int index) {
		playerList.overwritePlayer(player, index);
		mainGUI.updatePlayerList(playerList.getPlayerList());
	}
	public void selectPlayer(int playerNum) {
		if(playerList.hasNoPlayers()) {
			JOptionPane.showMessageDialog(null, "There are no players to select from.", "No Players Available",
					JOptionPane.WARNING_MESSAGE);
		} else {
			selectPlayerGUI = new PlayerSelectionGUI(this, playerList.getPlayerList(), playerNum);
		}
	}
	public void select(int index, int playerNum) {
		mainGUI.playerSelect(playerList.getPlayer(index), playerNum);
	}
	
	
	
	
	
	public static void main(String[] args) {
		TeamRandomizerController controller = new TeamRandomizerController();
	}
}
