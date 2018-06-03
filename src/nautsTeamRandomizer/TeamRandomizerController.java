package nautsTeamRandomizer;

import nautsTeamRandomizer.Model.AwesomenautsPlayer;
import nautsTeamRandomizer.Model.PlayerList;
import nautsTeamRandomizer.View.CreatePlayerGUI;
import nautsTeamRandomizer.View.EditPlayerGUI;
import nautsTeamRandomizer.View.MainGUI;

public class TeamRandomizerController {
	private PlayerList playerList;
	private MainGUI mainGUI;
	private CreatePlayerGUI createPlayerGUI;
	private EditPlayerGUI editPlayerGUI;
	
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
	
	
	
	
	
	
	public static void main(String[] args) {
		TeamRandomizerController controller = new TeamRandomizerController();
	}
}
