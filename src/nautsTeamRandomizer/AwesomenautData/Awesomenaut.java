package nautsTeamRandomizer.AwesomenautData;

public class Awesomenaut {
	private String nautName;
	private String[] alternateNames;
	private String[] skinNames;
	private String nautClass;
	private int nautID;
	
	// Regular Constructor
	public Awesomenaut(String name, String[] altNames, String nautClass) {
		nautName = name;
		alternateNames = altNames;
		skinNames = new String[]{};
		this.nautClass = nautClass;
	}
	// Regular Constructor
	public Awesomenaut(String name, String[] altNames, String[] skinNames,String nautClass) {
		nautName = name;
		alternateNames = altNames;
		this.skinNames = skinNames;
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
	public String toString() {
		return getNautName();
	}
	public void setID(int i) {
		this.nautID = i;
	}
	public int getID() {
		return this.nautID;
	}
}
