package nautsTeamRandomizer.Model;

public class AwesomenautsMap {
	private String mapName;
	private boolean supportsNormalPlay;
	private boolean supportsTDMPlay;
	private boolean isCustomGamemode;
	private boolean isDefaultMap;
	private String mapID;
	
	public AwesomenautsMap(String name, boolean supportsNormal, boolean supportsTDM, boolean isCustom, boolean isDefault) {
		mapName = name;
		supportsNormalPlay = supportsNormal;
		supportsTDMPlay = supportsTDM;
		isCustomGamemode = isCustom;
		isDefaultMap = isDefault;
	}
	public AwesomenautsMap(String name, boolean supportsNormal, boolean supportsTDM, boolean isCustom) {
		mapName = name;
		supportsNormalPlay = supportsNormal;
		supportsTDMPlay = supportsTDM;
		isCustomGamemode = isCustom;
		isDefaultMap = false;
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
				} else if(booleanCount == 3) {
					isDefaultMap = true;
					booleanCount++;
				} else {
					
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
				} else if(booleanCount == 3) {
					isDefaultMap = false;
					booleanCount++;
				} else {
					
				}
			} else {
				
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
	public boolean isDefaultMap() {
		return isDefaultMap;
	}
	public void setDefaultMap(boolean isDefaultMap) {
		this.isDefaultMap = isDefaultMap;
	}
	public String encode() {
		String result = mapName + ":";
		result += supportsNormalPlay + " ";
		result += supportsTDMPlay + " ";
		result += isCustomGamemode + " ";
		result += isDefaultMap;
		System.out.print(result);
		return result;
	}
	public String toString() {
		return mapName;
	}
}
