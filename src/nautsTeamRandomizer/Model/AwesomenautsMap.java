package nautsTeamRandomizer.Model;

public class AwesomenautsMap {
	private String mapName;
	private boolean supportsNormalPlay;
	private boolean supportsTDMPlay;
	private boolean isCustomGamemode;
	private String mapID;
	
	public AwesomenautsMap(String name, boolean supportsNormal, boolean supportsTDM, boolean isCustom) {
		mapName = name;
		supportsNormalPlay = supportsNormal;
		supportsTDMPlay = supportsTDM;
		isCustomGamemode = isCustom;
	}
	public AwesomenautsMap(String encodedMap) {
		mapName = encodedMap.substring(0, encodedMap.indexOf(':'));
		String mapdata = encodedMap.substring(encodedMap.indexOf(':') + 1);
		int booleanCount = 0;
		while(mapdata.length() != 0) {
			String current = mapdata.substring(0, mapdata.indexOf(' '));
			mapdata = mapdata.substring(mapdata.indexOf(' ') + 1);
			if (current.equals(" ") || current.equals("")) {
				continue;
			}
			if(current.equals("true")) {
				if(booleanCount == 0) {
					supportsNormalPlay = true;
					booleanCount++;
				} else if(booleanCount == 1) {
					supportsTDMPlay = true;
					booleanCount++;
				} else if(booleanCount == 2) {
					isCustomGamemode = true;
					booleanCount++;
				} else {
					throw new IllegalStateException("Error when loading the maps, too many booleans for map: " + mapName);
				}
			} else if(current.equals("false")) {
				if(booleanCount == 0) {
					supportsNormalPlay = false;
					booleanCount++;
				} else if(booleanCount == 1) {
					supportsTDMPlay = false;
					booleanCount++;
				} else if(booleanCount == 2) {
					isCustomGamemode = false;
					booleanCount++;
				} else {
					throw new IllegalStateException("Error when loading the maps, too many booleans for map: " + mapName);
				}
			} else {
				throw new IllegalStateException("Error when loading the maps, illegal argument for map: " + mapName);
			}
		}
	}
	public String getMapID() {
		return mapID;
	}
	public void setMapID(String mapID) {
		this.mapID = mapID;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public boolean isSupportsNormalPlay() {
		return supportsNormalPlay;
	}
	public void setSupportsNormalPlay(boolean supportsNormalPlay) {
		this.supportsNormalPlay = supportsNormalPlay;
	}
	public boolean isSupportsTDMPlay() {
		return supportsTDMPlay;
	}
	public void setSupportsTDMPlay(boolean supportsTDMPlay) {
		this.supportsTDMPlay = supportsTDMPlay;
	}
	public boolean isCustomGamemode() {
		return isCustomGamemode;
	}
	public void setCustomGamemode(boolean isCustomGamemode) {
		this.isCustomGamemode = isCustomGamemode;
	}
	public String encode() {
		String result = mapName + ":";
		result += supportsNormalPlay + " ";
		result += supportsTDMPlay + " ";
		result += isCustomGamemode + " ";
		return result;
	}
	public String toString() {
		return mapName;
	}
}
