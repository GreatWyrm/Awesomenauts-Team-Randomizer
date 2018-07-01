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
	// Constructor to decode player
	public AwesomenautsPlayer(String name, String nautsOwned) {
		playerName = name;
		hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS];
		if (nautsOwned.equals("")) {
			hasAllNauts = false;
		} else {
			if (nautsOwned.equals("A")) {
				hasAllNauts = true;
			} else {
				hasAllNauts = false;
				// nautsOwned = nautsOwned.substring(1);
				int i = 0;
				while (i != -1) {
					String naut = nautsOwned.substring(0, i);
					nautsOwned = nautsOwned.substring(i + 1);
					i = nautsOwned.indexOf(" ");
					if (naut.equals(" ") || naut.equals("")) {
						continue;
					}
					int index = Integer.parseInt(naut);
					hasNauts[index] = true;
				}
				int index = Integer.parseInt(nautsOwned);
				hasNauts[index] = true;
			}
		}
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
		if(nautsOwned.isEmpty()) {
			return null;
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}
	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut) {
		Random random = new Random();
		if(hasAllNauts) {
			ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
			for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
				if(!(AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut))) {
					nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
				}
			}
			return nautsOwned.get(random.nextInt(nautsOwned.size()));
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(hasNauts[i] && !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut)) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		if(nautsOwned.isEmpty()) {
			return null;
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}
	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut0, Awesomenaut restrictedNaut1) {
		Random random = new Random();
		if(hasAllNauts) {
			ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
			for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
				if(!(AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut0) || (AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut1)))) {
					nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
				}
			}
			return nautsOwned.get(random.nextInt(nautsOwned.size()));
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for(int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if(hasNauts[i] && (!AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut0) && !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut1))) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		if(nautsOwned.isEmpty()) {
			return null;
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
	
	public String encode() {
		String result = this.playerName + ":";
		if (this.hasAllNauts) {
			return result + "A";
		}
		for (int i = 0; i < this.hasNauts.length; i++) {
			if (this.hasNauts[i]) {
				result += " " + i;
			}
		}
		return result;
	}
}
