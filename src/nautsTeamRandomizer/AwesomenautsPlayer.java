package nautsTeamRandomizer;

import java.util.ArrayList;
import java.util.Random;

import nautsTeamRandomizer.AwesomenautData.Awesomenaut;

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
	public Awesomenaut getRandomNaut() {
		Random random = new Random();
		if(hasAllNauts) {
			return AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(hasNauts[i]) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}
}
