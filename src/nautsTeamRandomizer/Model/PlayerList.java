package nautsTeamRandomizer.Model;

import java.util.ArrayList;
import java.util.Random;

public class PlayerList {
	private ArrayList<AwesomenautsPlayer> playerList;

	public PlayerList() {
		playerList = new ArrayList<AwesomenautsPlayer>();
	}

	public AwesomenautsPlayer removePlayer(AwesomenautsPlayer player) {
		int index = findPlayerByID(player.getPlayerID());
		return playerList.remove(index);
	}

	public void addPlayer(AwesomenautsPlayer player) {
		assignIDToPlayer(player);
		playerList.add(player);

	}

	public void addPlayer(AwesomenautsPlayer player, int index) {
		assignIDToPlayer(player);
		playerList.add(index, player);
	}

	public AwesomenautsPlayer getPlayer(int index) {
		return playerList.get(index);
	}

	public void overwritePlayer(AwesomenautsPlayer player) {
		int index = findPlayerByID(player.getPlayerID());
		playerList.remove(index);
		playerList.add(index, player);
	}

	public AwesomenautsPlayer[] getPlayerList() {
		AwesomenautsPlayer[] temp = new AwesomenautsPlayer[playerList.size()];
		for (int i = 0; i < playerList.size(); i++) {
			temp[i] = playerList.get(i);
		}
		return temp;
	}

	public boolean hasNoPlayers() {
		return playerList.isEmpty();
	}

	public String encodeAllPlayers() {
		String encodedPlayers = "";
		for (AwesomenautsPlayer player : playerList) {
			encodedPlayers = player.encode() + "\n";
		}
		return encodedPlayers;
	}

	public int findPlayerByID(String ID) {
		for(int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getPlayerID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}

	private boolean noMatchingID(String ID) {
		if (hasNoPlayers()) {
			return true;
		}
		for (AwesomenautsPlayer player : playerList) {
			if (player.getPlayerID().equals(ID)) {
				return false;
			}
		}
		return true;
	}

	private String generateID() {
		final int ID_LENGTH = 6;
		String ID = "";
		Random random = new Random();
		for (int i = 0; i < ID_LENGTH; i++) {
			ID += random.nextInt(10);
		}
		return ID;
	}

	private void assignIDToPlayer(AwesomenautsPlayer player) {
		if (player.getPlayerID() == null) {
			String ID = generateID();
			while (!noMatchingID(ID)) {
				ID = generateID();
			}
			player.setPlayerID(ID);
		}
	}
}
