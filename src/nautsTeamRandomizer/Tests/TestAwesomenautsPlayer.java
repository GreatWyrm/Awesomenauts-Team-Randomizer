package nautsTeamRandomizer.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

class TestAwesomenautsPlayer {

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
	
	@Test
	void testNoNautsCanBenEncoded() {
		AwesomenautsPlayer ap = new AwesomenautsPlayer("bob");
		assertEquals("bob:", ap.encode());
	}
	
	@Test
	void testHasAllNautsCanBeEncoded() {
		AwesomenautsPlayer ap = new AwesomenautsPlayer("bob");
		ap.setHasAllNauts(true);
		assertEquals("bob:A", ap.encode());		
	}
	
	@Test
	void testCanHaveNibbs() {
		AwesomenautsPlayer ap = new AwesomenautsPlayer("bob");
		boolean b[] = {false, false, true, false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false, false, false};
		ap.setHasNauts(b);
		assertEquals("bob: 2", ap.encode());
	}
	@Test
	void testCanDecodeNameFromString() {
		TeamRandomizerController controller = new TeamRandomizerController();
		AwesomenautsPlayer ap = new AwesomenautsPlayer("bob");
		boolean b[] = {false, false, false, false, false, false, false, false, false, false, false, false,
				false, false, false, true, false, false, false, false, false, false, false, false, false,
				false, false, false, false, true, false, false, false, false};
		ap.setHasNauts(b);
		controller.decodePlayer("bob: 15 29");
		assertEquals(ap.getPlayerName(), controller.getPlayer(0).getPlayerName());
	}
	@Test
	void testCanDecodeNautsFromString() {
		TeamRandomizerController controller = new TeamRandomizerController();
		AwesomenautsPlayer ap = new AwesomenautsPlayer("bob");
		boolean b[] = {false, false, false, false, false, false, false, false, false, false, false, false,
				false, false, false, true, false, false, false, false, false, false, false, false, false,
				false, false, false, false, true, false, false, false, false};
		ap.setHasNauts(b);
		controller.decodePlayer("bob: 15 29");
		assertArrayEquals(ap.getHasNauts(), controller.getPlayer(0).getHasNauts());
	}
}
