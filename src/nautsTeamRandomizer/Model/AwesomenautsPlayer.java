package nautsTeamRandomizer.Model;

import java.util.ArrayList;
import java.util.Random;

import nautsTeamRandomizer.AwesomenautData.Awesomenaut;

public class AwesomenautsPlayer {
	private String playerName;
	private boolean hasAllNauts;
	private boolean[][] hasNauts;
	private String playerID;

	public AwesomenautsPlayer(String name) {
		playerName = name;
		hasAllNauts = false;
		hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS][];
		for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNauts[i] = new boolean[AwesomenautsInfo.AWESOMENAUTS[i].getNumOfSkins() + 1];
		}
		playerID = null;
	}

	// Constructor to decode player
	public AwesomenautsPlayer(String name, String nautsOwned) {
		playerName = name;
		hasNauts = new boolean[AwesomenautsInfo.NUM_OF_NAUTS][];
		for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			hasNauts[i] = new boolean[AwesomenautsInfo.AWESOMENAUTS[i].getNumOfSkins() + 1];
		}
		if (nautsOwned.charAt(0) == 'A') {
			hasAllNauts = true;
			for (int i = 0; i < hasNauts.length; i++) {
				hasNauts[i][0] = true;
			}
		} else {
			hasAllNauts = false;
		}
		int i = 0;
		while (i != -1) {
			String naut = nautsOwned.substring(0, i);
			nautsOwned = nautsOwned.substring(i + 1);
			i = nautsOwned.indexOf(" ");
			if (naut.equals(" ") || naut.equals("")) {
				continue;
			}
			if (naut.indexOf('.') != -1) {
				String index = naut.substring(0, naut.indexOf('.'));
				String index2 = naut.substring(naut.indexOf('.') + 1);
				int intIndex = Integer.parseInt(index);
				int intIndex2 = Integer.parseInt(index2);
				hasNauts[intIndex][intIndex2] = true;
			} else {
				int intIndex = Integer.parseInt(naut);
				hasNauts[intIndex][0] = true;
			}
		}
		if (nautsOwned.indexOf('.') != -1) {
			String index = nautsOwned.substring(0, nautsOwned.indexOf('.'));
			String index2 = nautsOwned.substring(nautsOwned.indexOf('.') + 1);
			int intIndex = Integer.parseInt(index);
			int intIndex2 = Integer.parseInt(index2);
			hasNauts[intIndex][intIndex2] = true;
		} else {
			int intIndex = Integer.parseInt(nautsOwned);
			hasNauts[intIndex][0] = true;
		}
	}

	public AwesomenautsPlayer(String name, boolean hasAllNauts, boolean[][] hasNauts) {
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

	public boolean[][] getHasNauts() {
		return hasNauts;
	}

	public boolean[] getHasNauts(int index) {
		return hasNauts[index];
	}

	public boolean getHasNauts(int index, int index2) {
		return hasNauts[index][index2];
	}

	public void setHasNauts(boolean[][] hasNauts) {
		this.hasNauts = hasNauts;
	}

	public Awesomenaut getRandomNaut() {
		Random random = new Random();
		if (hasAllNauts) {
			return AwesomenautsInfo.AWESOMENAUTS[random.nextInt(AwesomenautsInfo.NUM_OF_NAUTS)];
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if (hasNauts[i][0]) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		if (nautsOwned.isEmpty()) {
			return null;
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}

	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut) {
		Random random = new Random();
		if (hasAllNauts) {
			ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
			for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
				if (!(AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut))) {
					nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
				}
			}
			return nautsOwned.get(random.nextInt(nautsOwned.size()));
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if (hasNauts[i][0] && !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut)) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		if (nautsOwned.isEmpty()) {
			return null;
		}
		return nautsOwned.get(random.nextInt(nautsOwned.size()));
	}

	public Awesomenaut getRandomNaut(Awesomenaut restrictedNaut0, Awesomenaut restrictedNaut1) {
		Random random = new Random();
		if (hasAllNauts) {
			ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
			for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
				if (!(AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut0)
						|| (AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut1)))) {
					nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
				}
			}
			return nautsOwned.get(random.nextInt(nautsOwned.size()));
		}
		ArrayList<Awesomenaut> nautsOwned = new ArrayList<Awesomenaut>();
		for (int i = 0; i < AwesomenautsInfo.NUM_OF_NAUTS; i++) {
			if (hasNauts[i][0] && (!AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut0)
					&& !AwesomenautsInfo.AWESOMENAUTS[i].equals(restrictedNaut1))) {
				nautsOwned.add(AwesomenautsInfo.AWESOMENAUTS[i]);
			}
		}
		if (nautsOwned.isEmpty()) {
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
		for (int i = 0; i < hasNauts.length; i++) {
			string += AwesomenautsInfo.AWESOMENAUTS[i].getNautName() + ": " + hasNauts[i] + "\n";
		}
		return string;
	}

	public String getRandomSkin(Awesomenaut naut) {
		Random random = new Random();
		ArrayList<String> availableSkins = new ArrayList<String>();
		int nautIndex = naut.getID();
		availableSkins.add(naut.getNautName());
		for (int i = 1; i < hasNauts[nautIndex].length; i++) {
			if (hasNauts[nautIndex][i]) {
				availableSkins.add(naut.getSkinName(i - 1));
			}
		}
		if (availableSkins.isEmpty()) {
			return null;
		}
		return availableSkins.get(random.nextInt(availableSkins.size()));
	}

	public String encode() {
		String result = this.playerName + ":";
		if (this.hasAllNauts) {
			result += "A";
		} else {
			for (int i = 0; i < this.hasNauts.length; i++) {
				if (this.hasNauts[i][0]) {
					result += " " + i + ".0";
				}
			}
		}
		for (int i = 0; i < hasNauts.length; i++) {
			for (int j = 1; j < hasNauts[i].length; j++) {
				if (hasNauts[i][j]) {
					result += " " + i + "." + j;
				}
			}
		}
		return result;
	}

	public void printNautAndSkins(int index) {
		Awesomenaut awesomenaut = AwesomenautsInfo.AWESOMENAUTS[index];
		for (int i = 0; i < hasNauts[index].length; i++) {
			if (i == 0) {
				System.out.println(awesomenaut.getNautName() + ": " + hasNauts[index][i]);
			} else {
				System.out.println(awesomenaut.getSkinName(i - 1) + ": " + hasNauts[index][i]);
			}
		}
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
}
