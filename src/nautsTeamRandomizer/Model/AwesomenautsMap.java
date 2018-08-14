package nautsTeamRandomizer.Model;

public class AwesomenautsMap {
	private String mapName;
	private boolean supportsNormalPlay;
	private boolean supportsTDMPlay;
	private boolean isCustomGamemode;
	private boolean isDefaultMap;
	
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
	
}
