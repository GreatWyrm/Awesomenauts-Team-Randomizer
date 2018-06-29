package nautsTeamRandomizer.Tests;

import org.junit.jupiter.api.Test;

import nautsTeamRandomizer.TeamRandomizerController;

class TestLoadingAndSaving {

	@Test
	void test() {
		TeamRandomizerController trc = new TeamRandomizerController();
		
		trc.load("my_data");
	}

}
