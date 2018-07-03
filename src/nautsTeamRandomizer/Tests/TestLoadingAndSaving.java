package nautsTeamRandomizer.Tests;

import org.junit.jupiter.api.Test;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

class TestLoadingAndSaving {

	@Test
	void test() {
		TeamRandomizerController trc1 = new TeamRandomizerController();
		
		AwesomenautsPlayer ap = new AwesomenautsPlayer("jerry");
		boolean b[] = {true, false, true, false, false, false, false, false, true, false, false, false,
				false, false, false, false, false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false, true, false};
		ap.setHasNauts(b);
		
		trc1.addNewPlayer(ap);
		trc1.save("my_data");
		
		TeamRandomizerController trc2 = new TeamRandomizerController();
		
		trc2.load("my_data");

	}

}
