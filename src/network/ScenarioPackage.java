package network;

import java.io.Serializable;
import java.util.Vector;

import setting.AuthorizedScenario;
import setting.Scenario;

public class ScenarioPackage implements Serializable
{
	private static final long serialVersionUID = 5065044744033529287L;
	private Vector<Scenario> scenarios;
	public ScenarioPackage()
	{
		scenarios = new Vector<Scenario>();
	}
	public Vector<Scenario> getScenarios()
	{
		return scenarios;
	}
	public void setScenarios(Vector<Scenario> scenarios)
	{
		this.scenarios = scenarios;
	}
	public static ScenarioPackage getDefaultScenarioPack()
	{
		ScenarioPackage newOne = new ScenarioPackage();
		
		AuthorizedScenario scenario01 = new AuthorizedScenario();
		scenario01.setName("Do you have enough time?");
		scenario01.setKoreanName("시간 있어요?");
		scenario01.setDescription("It take lots of time to finish this game.");
		scenario01.setKoreanDescription("이 게임이 끝날 때까지는 엄청난 시간이 걸릴 것입니다.");
		scenario01.setPlusminus_card_ratio(new Integer(16));
		scenario01.setMultiply_card_ratio(new Integer(16));
		scenario01.setAi_cards_count(new Integer(100));
		scenario01.setPlayer_cards_count(new Integer(100));
		scenario01.setDifficulty(new Integer(900));		
		newOne.scenarios.add(scenario01);
		
		
		return newOne;
	}
}
