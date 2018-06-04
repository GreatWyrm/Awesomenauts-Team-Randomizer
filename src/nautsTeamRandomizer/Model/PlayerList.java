package nautsTeamRandomizer.Model;

import java.util.ArrayList;

public class PlayerList {
	private ArrayList<AwesomenautsPlayer> playerList;
	
	public PlayerList() {
		playerList = new ArrayList<AwesomenautsPlayer>();
	}
	
	public AwesomenautsPlayer removePlayer(int index) {
		return playerList.remove(index);
	}
	public void addPlayer(AwesomenautsPlayer player) {
		playerList.add(player);
	}
	public void addPlayer(AwesomenautsPlayer player, int index) {
		playerList.add(index, player);
	}
	public AwesomenautsPlayer getPlayer(int index) {
		return playerList.get(index);
	}
	public void overwritePlayer(AwesomenautsPlayer player, int index) {
		playerList.remove(index);
		playerList.add(index, player);
	}
	public AwesomenautsPlayer[] getPlayerList() {
		AwesomenautsPlayer[] temp = new AwesomenautsPlayer[playerList.size()];
		for(int i = 0; i < playerList.size(); i++) {
			temp[i] = playerList.get(i);
		}
		return temp;
	}
	public boolean hasNoPlayers() {
		return playerList.isEmpty();
	}
}
