package nautsTeamRandomizer.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nautsTeamRandomizer.Model.AwesomenautsPlayer;

class TestAwesomenotsPlayer {

	@Test
	void testNewPlayerHasLessThanAllNauts() {
		AwesomenautsPlayer awesomenautsPlayer = new AwesomenautsPlayer("joe");
		
		assertFalse(awesomenautsPlayer.getHasAllNauts());
	}

	@Test
	void testNoNautsMeansReturnNullFromRandomFunction() {
		AwesomenautsPlayer awesomenautsPlayer = new AwesomenautsPlayer("joe");
		
		assertNull(awesomenautsPlayer.getRandomNaut());
	}
}
