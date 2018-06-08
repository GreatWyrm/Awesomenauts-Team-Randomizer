package nautsTeamRandomizer.Model;

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
	public AwesomenautsPlayer(String name, boolean hasAllNauts, boolean[] hasNauts) {
		playerName = name;
		this.hasAllNauts = hasAllNauts;
		this.hasNauts = hasNauts;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public boolean getHasAllNauts() {
		return hasAllNauts;
	}
	public void setHasAllNauts(boolean hasAllNauts) {
		this.hasAllNauts = hasAllNauts;
	}
	public boolean[] getHasNauts() {
		return hasNauts;
	}
	public boolean getHasNauts(int index) {
		return hasNauts[index];
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
	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut) {
		Random random = new Random();
		if(hasAllNauts) {
			Awesomenaut chosen = AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
			while(chosen.equals(restrictedNaut) ) {
				chosen = AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
			}
			return chosen;
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(hasNauts[i] && !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut)) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}
	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut0, Awesomenaut restrictedNaut1) {
		Random random = new Random();
		if(hasAllNauts) {
			Awesomenaut chosen = AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
			while(chosen.equals(restrictedNaut0) || chosen.equals(restrictedNaut1) ) {
				chosen = AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
			}
			return chosen;
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(hasNauts[i] && (!AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut0) && !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut1))) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}
	public String toString() {
		return playerName;
	}
	public String printAllData() {
		String string = playerName + "\n";
		string += "Has all Nauts: " + hasAllNauts + "\n";
		for(int i = 0; i < hasNauts.length; i++) {
			string += AwesomenautsInfo.AWESOMENAUTS[i].getNautName() + ": " + hasNauts[i] + "\n";
		}
		return string;
	}
}
