package nautsTeamRandomizer.Tests;

import nautsTeamRandomizer.Model.PlayerList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayerList {

	@Test
	void testCanDetectLackOfPlayers() {
		PlayerList playerList = new PlayerList();
		
		assertTrue(playerList.hasNoPlayers());
	}

//	@Test
//	void testCanInsertAPlayer() {
//		PlayerList playerList = new PlayerList();
//		
//		playerList.addPlayer(player);
//	}
}
