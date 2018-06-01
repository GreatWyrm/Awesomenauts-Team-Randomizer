package nautsTeamRandomizer.AwesomenautData;

public class Awesomenaut {
	private String nautName;
	private String[] alternateNames;
	private String nautClass;
	
	// Regular Constructor
	public Awesomenaut(String name, String[] altNames, String nautClass) {
		nautName = name;
		alternateNames = altNames;
		this.nautClass = nautClass;
	}
	public String getNautClass() {
		return nautClass;
	}
	public String getNautName() {
		return nautName;
	}
	public boolean matchesNautName(String name) {
		if(name.toLowerCase().equals(nautName.toLowerCase())) {
			return true;
		}
		for(int i = 0; i < alternateNames.length; i++) {
			if(name.toLowerCase().equals(alternateNames[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
