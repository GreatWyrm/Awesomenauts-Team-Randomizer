package nautsTeamRandomizer.Model;

import java.util.ArrayList;
import java.util.Random;

public class MapList {
	private ArrayList<AwesomenautsMap> mapList;

	public MapList() {
		mapList = new ArrayList<AwesomenautsMap>();
	}

	public AwesomenautsMap removePlayer(int index) {
		return mapList.remove(index);
	}

	public void addMap(AwesomenautsMap map) {
		assignIDToMap(map);
		mapList.add(map);

	}

	public void addMap(AwesomenautsMap map, int index) {
		assignIDToMap(map);
		mapList.add(index, map);
	}

	public AwesomenautsMap getMap(int index) {
		return mapList.get(index);
	}

	public void overwriteMap(AwesomenautsMap map) {
		int index = findMapByID(map.getMapID());
		mapList.remove(index);
		mapList.add(index, map);
	}

	public AwesomenautsMap[] getMapList() {
		AwesomenautsMap[] temp = new AwesomenautsMap[mapList.size()];
		for (int i = 0; i < mapList.size(); i++) {
			temp[i] = mapList.get(i);
		}
		return temp;
	}

	public boolean hasNoPlayers() {
		return mapList.isEmpty();
	}

	public String encodeAllMaps() {
		String encodedMaps = "";
		for (AwesomenautsMap map : mapList) {
			encodedMaps = map.encode() + "\n";
		}
		return encodedMaps;
	}

	public int findMapByID(String ID) {
		for(int i = 0; i < mapList.size(); i++) {
			if (mapList.get(i).getMapID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}

	private boolean noMatchingID(String ID) {
		if (hasNoPlayers()) {
			return true;
		}
		for (AwesomenautsMap map : mapList) {
			if (map.getMapID().equals(ID)) {
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

	private void assignIDToMap(AwesomenautsMap map) {
		if (map.getMapID() == null) {
			String ID = generateID();
			while (!noMatchingID(ID)) {
				ID = generateID();
			}
			map.setMapID(ID);
		}
	}
}
