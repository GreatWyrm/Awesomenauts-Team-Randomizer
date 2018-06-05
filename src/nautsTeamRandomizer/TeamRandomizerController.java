package nautsTeamRandomizer;

import javax.swing.JOptionPane;

import nautsTeamRandomizer.AwesomenautData.Awesomenaut;
import nautsTeamRandomizer.Model.AwesomenautsInfo;
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
	private Awesomenaut restrictedNaut0;
	private Awesomenaut restrictedNaut1;
	
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
	public void randomizeTeam(AwesomenautsPlayer player0) {
		if(restrictedNaut0 == null && restrictedNaut1 == null) {
			Awesomenaut nautPlayer0 = player0.getRandomNaut();
		} else if(restrictedNaut0 == null) {
			Awesomenaut nautPlayer0 = player0.getRandomNaut(restrictedNaut1);
		} else if(restrictedNaut1 == null) {
			Awesomenaut nautPlayer0 = player0.getRandomNaut(restrictedNaut0);
		} else {
			Awesomenaut nautPlayer0 = player0.getRandomNaut(restrictedNaut0, restrictedNaut1);
		}
	}
	public void randomizeTeam(AwesomenautsPlayer player0, AwesomenautsPlayer player1) {
		if(restrictedNaut0 == null && restrictedNaut1 == null) {
			Awesomenaut nautPlayer0 = player0.getRandomNaut();
			Awesomenaut nautPlayer1 = player1.getRandomNaut(nautPlayer0);
		} else if(restrictedNaut1 == null) {
			Awesomenaut nautPlayer0 = player0.getRandomNaut(restrictedNaut0);
			Awesomenaut nautPlayer1 = player1.getRandomNaut(nautPlayer0, restrictedNaut0);
		} else {
			Awesomenaut nautPlayer0 = player0.getRandomNaut(restrictedNaut1);
			Awesomenaut nautPlayer1 = player1.getRandomNaut(nautPlayer0, restrictedNaut1);
		}
	}
	public void randomizeTeam(AwesomenautsPlayer player0, AwesomenautsPlayer player1, AwesomenautsPlayer player2) {
		Awesomenaut nautPlayer0 = player0.getRandomNaut();
		Awesomenaut nautPlayer1 = player1.getRandomNaut(nautPlayer0);
		Awesomenaut nautPlayer2 = player2.getRandomNaut(nautPlayer0, nautPlayer1);
	}
	public static void main(String[] args) {
		TeamRandomizerController controller = new TeamRandomizerController();
	}
}
