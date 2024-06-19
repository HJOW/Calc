package network;

import java.math.BigInteger;

import setting.Lint;
import setting.Scenario;

public class AuthorizedScenarioPackage extends ScenarioPackage
{
	private static final long serialVersionUID = -3066673192294684590L;
	private String name = "";
	private String description = "";
	private BigInteger authorizes = new BigInteger("0");
	private Integer edition = new Integer(0);
	public AuthorizedScenarioPackage()
	{
		super();
	}
	public void authorize()
	{
		authorizes = new BigInteger("0");
		char[] name_to = name.toCharArray();
		char[] description_to = description.toCharArray();
		
		for(int i=0; i<name_to.length; i++)
		{
			authorizes = authorizes.add(Lint.big((long) (name_to[i])));
		}
		authorizes = authorizes.add(Lint.big(521));
		authorizes = authorizes.multiply(Lint.big( (edition.intValue() + 956) ));
		
		for(int i=0; i<description_to.length; i++)
		{
			authorizes = authorizes.add(Lint.big((long) (description_to[i])));
		}
		authorizes = authorizes.add(Lint.big(627));
		authorizes = authorizes.multiply(Lint.big( (edition.intValue() + 162) ));
		
		
		for(int i=0; i<getScenarios().size(); i++)
		{
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getAuthorize_code().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getTurn_script().length()));
			char[] scripts = getScenarios().get(i).getTurn_script().toCharArray();
			for(int j=0; j<scripts.length; j++)
			{
				authorizes = authorizes.add(Lint.big((long) scripts[j]));
			}
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getAi_cards_count().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getPlayer_cards_count().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getChange_card_count().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getPlusminus_card_ratio().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getTimelimit().longValue()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getAi_cards().size()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getDeck_additionals().size()));
			authorizes = authorizes.add(Lint.big(getScenarios().get(i).getPlayer_cards().size()));
			authorizes = authorizes.multiply(Lint.big( getScenarios().get(i).getDifficulty().longValue() + 10000 ));
		}
		authorizes = authorizes.multiply(Lint.big(getScenarios().size()));
		
	}
	public boolean isAuthorized()
	{
		BigInteger authorizes2 = new BigInteger("0");
		char[] name_to = name.toCharArray();
		char[] description_to = description.toCharArray();
		
		for(int i=0; i<name_to.length; i++)
		{
			authorizes2 = authorizes2.add(Lint.big((long) (name_to[i])));
		}
		authorizes2 = authorizes2.add(Lint.big(521));
		authorizes2 = authorizes2.multiply(Lint.big( (edition.intValue() + 956) ));
		
		for(int i=0; i<description_to.length; i++)
		{
			authorizes2 = authorizes2.add(Lint.big((long) (description_to[i])));
		}
		authorizes2 = authorizes2.add(Lint.big(627));
		authorizes2 = authorizes2.multiply(Lint.big( (edition.intValue() + 162) ));
		
		for(int i=0; i<getScenarios().size(); i++)
		{
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getAuthorize_code().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getTurn_script().length()));
			char[] scripts = getScenarios().get(i).getTurn_script().toCharArray();
			for(int j=0; j<scripts.length; j++)
			{
				authorizes2 = authorizes2.add(Lint.big((long) scripts[j]));
			}
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getAi_cards_count().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getPlayer_cards_count().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getChange_card_count().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getPlusminus_card_ratio().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getTimelimit().longValue()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getAi_cards().size()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getDeck_additionals().size()));
			authorizes2 = authorizes2.add(Lint.big(getScenarios().get(i).getPlayer_cards().size()));
			authorizes2 = authorizes2.multiply(Lint.big( getScenarios().get(i).getDifficulty().longValue() + 10000 ));
		}
		authorizes2 = authorizes2.multiply(Lint.big(getScenarios().size()));
		
		if(authorizes2.compareTo(authorizes) == 0) return true;
		else return false;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public BigInteger getAuthorizes()
	{
		return authorizes;
	}
	public void setAuthorizes(BigInteger authorizes)
	{
		this.authorizes = authorizes;
	}
	public Integer getEdition()
	{
		return edition;
	}
	public void setEdition(Integer edition)
	{
		this.edition = edition;
	}
	public static ScenarioPackage getDefaultScenarioPack()
	{
		AuthorizedScenarioPackage newOne = new AuthorizedScenarioPackage();
		
		Scenario scenario01 = new Scenario();
		scenario01.setName("Do you have enough time?");
		scenario01.setDescription("이 게임이 끝날 때까지는 엄청난 시간이 걸릴 것입니다.");
		scenario01.setPlusminus_card_ratio(new Integer(16));
		scenario01.setMultiply_card_ratio(new Integer(16));
		scenario01.setAi_cards_count(new Integer(100));
		scenario01.setPlayer_cards_count(new Integer(100));
		scenario01.setDifficulty(new Integer(90));
		newOne.getScenarios().add(scenario01);
		
		return newOne;
	}
}
