package nautsTeamRandomizer.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import nautsTeamRandomizer.TeamRandomizerController;
import nautsTeamRandomizer.Model.AwesomenautsPlayer;

class TestLoadingAndSaving {

	@Test
	void testSingleNaut() {
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
		assertArrayEquals(b, trc2.getPlayer(0).getHasNauts());
	}
	
	@Test
	void testMultipleNauts() {
		Random r = new Random();
		TeamRandomizerController x = new TeamRandomizerController();
		
		AwesomenautsPlayer ap1 = new AwesomenautsPlayer("ap1");
		AwesomenautsPlayer ap2 = new AwesomenautsPlayer("ap2");
		AwesomenautsPlayer ap_all = new AwesomenautsPlayer("ap_all");
		
		boolean b1[] = new boolean[34];
		boolean b2[] = new boolean[34];
		
		for (int i = 0; i < b1.length; i++) {
			b1[i]= r.nextBoolean();
			b2[i]= r.nextBoolean();
		}
		
		ap1.setHasNauts(b1);
		ap2.setHasNauts(b2);
		ap_all.setHasAllNauts(true);
		
		x.addNewPlayer(ap1);
		x.addNewPlayer(ap2);
		x.addNewPlayer(ap_all);
		
		x.save("random_and_all");
		
		TeamRandomizerController y = new TeamRandomizerController();
		y.load("random_and_all");
		
		assertArrayEquals(b1, y.getPlayer(0).getHasNauts());
		assertFalse(y.getPlayer(0).getHasAllNauts());
		assertFalse(y.getPlayer(1).getHasAllNauts());
		assertArrayEquals(b2, y.getPlayer(1).getHasNauts());
		assertTrue(y.getPlayer(2).getHasAllNauts());
		
	}
}
