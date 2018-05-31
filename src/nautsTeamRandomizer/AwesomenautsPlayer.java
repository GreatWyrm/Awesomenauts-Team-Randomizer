package nautsTeamRandomizer;

public class AwesomenautsPlayer {
	private String playerName;
	private boolean hasAllNauts;
	private boolean[] hasNauts;
	
	public AwesomenautsPlayer(String name) {
		playerName = name;
		hasAllNauts = false;
		hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS];
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public boolean isHasAllNauts() {
		return hasAllNauts;
	}
	public void setHasAllNauts(boolean hasAllNauts) {
		this.hasAllNauts = hasAllNauts;
	}
	public boolean[] getHasNauts() {
		return hasNauts;
	}
	public void setHasNauts(boolean[] hasNauts) {
		this.hasNauts = hasNauts;
	}
}
