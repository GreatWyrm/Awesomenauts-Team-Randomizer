package nautsTeamRandomizer.Model;

import nautsTeamRandomizer.AwesomenautData.Admiral_Swiggins;
import nautsTeamRandomizer.AwesomenautData.Awesomenaut;
import nautsTeamRandomizer.AwesomenautData.Ayla;
import nautsTeamRandomizer.AwesomenautData.Chucho_Krokk;
import nautsTeamRandomizer.AwesomenautData.Clunk;
import nautsTeamRandomizer.AwesomenautData.Coco_Nebulon;
import nautsTeamRandomizer.AwesomenautData.Commander_Rocket;
import nautsTeamRandomizer.AwesomenautData.Deadlift;
import nautsTeamRandomizer.AwesomenautData.Derpl_Zork;
import nautsTeamRandomizer.AwesomenautData.Dizzy;
import nautsTeamRandomizer.AwesomenautData.Froggy_G;
import nautsTeamRandomizer.AwesomenautData.Genji;
import nautsTeamRandomizer.AwesomenautData.Gnaw;
import nautsTeamRandomizer.AwesomenautData.Ix_the_Interloper;
import nautsTeamRandomizer.AwesomenautData.Jimmy_and_the_LUX5000;
import nautsTeamRandomizer.AwesomenautData.Ksenia;
import nautsTeamRandomizer.AwesomenautData.Leon_Chameleon;
import nautsTeamRandomizer.AwesomenautData.Max_Focus;
import nautsTeamRandomizer.AwesomenautData.Nibbs;
import nautsTeamRandomizer.AwesomenautData.Penny_Fox;
import nautsTeamRandomizer.AwesomenautData.Professor_Yoolip;
import nautsTeamRandomizer.AwesomenautData.QiTara;
import nautsTeamRandomizer.AwesomenautData.Raelynn;
import nautsTeamRandomizer.AwesomenautData.Rocco;
import nautsTeamRandomizer.AwesomenautData.Scoop;
import nautsTeamRandomizer.AwesomenautData.Sentry_X_58;
import nautsTeamRandomizer.AwesomenautData.Sheriff_Lonestar;
import nautsTeamRandomizer.AwesomenautData.Skree;
import nautsTeamRandomizer.AwesomenautData.Skolldir;
import nautsTeamRandomizer.AwesomenautData.Smiles;
import nautsTeamRandomizer.AwesomenautData.Snork_Gunk;
import nautsTeamRandomizer.AwesomenautData.Ted_McPain;
import nautsTeamRandomizer.AwesomenautData.Vinne_And_Spike;
import nautsTeamRandomizer.AwesomenautData.Voltar;
import nautsTeamRandomizer.AwesomenautData.Yuri;

public class AwesomenautsInfo {
	public static final Awesomenaut[] AWESOMENAUTS = {new Froggy_G(),
			new Sheriff_Lonestar(), new Leon_Chameleon(), new Scoop(), new Gnaw(), new Raelynn(),
			new Ayla(), new Clunk(), new Voltar(), new Coco_Nebulon(), new Skolldir(), new Yuri(),
			new Derpl_Zork(), new Vinne_And_Spike(), new Genji(), new Admiral_Swiggins(), new Rocco(),
			new Ksenia(), new Ix_the_Interloper(), new Ted_McPain(), new Penny_Fox(), new Sentry_X_58(),
			new Skree(), new Nibbs(), new Professor_Yoolip(), new Chucho_Krokk(), new Jimmy_and_the_LUX5000(),
			new Max_Focus(), new Deadlift(), new Dizzy(), new Smiles(), new Commander_Rocket(), new QiTara(),
			new Snork_Gunk()};
	public static final int NUM_OF_NAUTS = AWESOMENAUTS.length;
	
	static {
		for (int i = 0; i < NUM_OF_NAUTS; i++) {
			AWESOMENAUTS[i].setID(i);
		}
	}
}
