package setting;

import main_classes.StandardCard;
import ai_algorithm.AI_Scripted_Algorithm;

public class AuthorizedScenario extends Scenario
{
	private static final long serialVersionUID = 5856550973449668890L;
	
	
	private String koreanName = "";
	private String koreanDescription = "";
	
	public AuthorizedScenario()
	{
		super();
	}
	
	public String getKoreanName()
	{
		return koreanName;
	}

	public void setKoreanName(String koreanName)
	{
		this.koreanName = koreanName;
	}

	public String getKoreanDescription()
	{
		return koreanDescription;
	}

	public void setKoreanDescription(String koreanDescription)
	{
		this.koreanDescription = koreanDescription;
	}
	public AuthorizedScenario clone()
	{
		AuthorizedScenario newOne = new AuthorizedScenario();
		newOne.setName(new String(getName()));
		newOne.setMultiply_card_ratio(new Integer(getMultiply_card_ratio().intValue()));
		newOne.setPlusminus_card_ratio(new Integer(getPlusminus_card_ratio().intValue()));
		newOne.setChange_card_count(new Integer(getChange_card_count().intValue()));
		newOne.setDifficulty(new Integer(getDifficulty().intValue()));
		newOne.setPlayer_cards_count(new Integer(getPlayer_cards_count().intValue()));
		newOne.setAi_cards_count(new Integer(getAi_cards_count().intValue()));
		newOne.setBonuses(new Integer(getBonuses().intValue()));
		newOne.setTimelimit(new Integer(getTimelimit().intValue()));
		newOne.setAuthorize_code(new Long(getAuthorize_code().longValue()));
		newOne.setTrump_mode(new Boolean(getTrump_mode().booleanValue()));
		newOne.setDescription(new String(getDescription()));
		newOne.setTurn_script(new String(getTurn_script()));
		newOne.setKoreanDescription(new String(getKoreanDescription()));
		newOne.setKoreanName(new String(getKoreanName()));
		for(int i=0; i<getPlayer_cards().size(); i++)
		{
			newOne.getPlayer_cards().add((StandardCard) getPlayer_cards().get(i).clone());
		}
		for(int i=0; i<getAi_cards().size(); i++)
		{
			newOne.getAi_cards().add((StandardCard) getAi_cards().get(i).clone());
		}
		for(int i=0; i<getDeck_additionals().size(); i++)
		{
			newOne.getDeck_additionals().add((StandardCard) getDeck_additionals().get(i).clone());
		}
		if(getAi() != null)
		{
			try
			{
				newOne.setAi(getAi().clone());
			} 
			catch (Exception e)
			{
				newOne.setAi(null);
			}
		}
		return newOne;
	}
	public void authorize(Setting sets)
	{		
		Setting setting = sets;
		if(setting == null)
			setting = Setting.getNewInstance();
		
		long results = 0;
		char[] names = getName().toCharArray();
		char[] korNames = getKoreanName().toCharArray();
		char[] descriptions = getDescription().toCharArray();
		char[] korDesc = getKoreanDescription().toCharArray();
		char[] scripts = getTurn_script().toCharArray();
		for(int i=0; i<names.length; i++)
		{
			results += (long) names[i];
		}
		for(int i=0; i<korNames.length; i++)
		{
			results += (long) korNames[i];
		}
		results = results * 3;
		results += getDifficulty().longValue();
		results += getBonuses().longValue();
		results *= 2;
		
		for(int i=0; i<descriptions.length; i++)
		{
			results += (long) descriptions[i];
		}
		for(int i=0; i<korDesc.length; i++)
		{
			results += (long) korDesc[i];
		}
		results = results * 4;
		results += getMultiply_card_ratio();
		results += getPlayer_cards_count();
		results += getChange_card_count();
		results += getPlayer_cards_count();
		results += getAi_cards_count();
		results += getTimelimit();
		results += 5;
		
		for(int i=0; i<scripts.length; i++)
		{
			results += (long) scripts[i];
		}
		results = results * 2;
		if(getAi() instanceof AI_Scripted_Algorithm)
			results = results * 2;
		for(int i=0; i<getDeck_additionals().size(); i++)
		{
			int values = (int) getDeck_additionals().get(i).getOp();
			values = values * getDeck_additionals().get(i).getNum();
			results += values;
		}
		for(int i=0; i<getAi_cards().size(); i++)
		{
			int values = (int) getAi_cards().get(i).getOp();
			values = values * getAi_cards().get(i).getNum();
			results += values;
		}
		for(int i=0; i<getPlayer_cards().size(); i++)
		{
			int values = (int) getPlayer_cards().get(i).getOp();
			values = values * getPlayer_cards().get(i).getNum();
			results += values;
		}
		results = results * 3;
		
		if(getTrump_mode().booleanValue()) results = results / 2;
		
		setAuthorize_code(new Long(results));
	}
	public boolean isAuthorized(Setting sets)
	{
		Setting setting = sets;
		if(setting == null)
			setting = Setting.getNewInstance();
		
		long results = 0;
		char[] names = getName().toCharArray();
		char[] korNames = getKoreanName().toCharArray();
		char[] descriptions = getDescription().toCharArray();
		char[] korDesc = getKoreanDescription().toCharArray();
		char[] scripts = getTurn_script().toCharArray();
		for(int i=0; i<names.length; i++)
		{
			results += (long) names[i];
		}
		for(int i=0; i<korNames.length; i++)
		{
			results += (long) korNames[i];
		}
		results = results * 3;
		results += getDifficulty().longValue();
		results += getBonuses().longValue();
		results *= 2;
		
		for(int i=0; i<descriptions.length; i++)
		{
			results += (long) descriptions[i];
		}
		for(int i=0; i<korDesc.length; i++)
		{
			results += (long) korDesc[i];
		}
		results = results * 4;
		results += getMultiply_card_ratio();
		results += getPlayer_cards_count();
		results += getChange_card_count();
		results += getPlayer_cards_count();
		results += getAi_cards_count();
		results += getTimelimit();
		results += 5;
		
		for(int i=0; i<scripts.length; i++)
		{
			results += (long) scripts[i];
		}
		results = results * 2;
		if(getAi() instanceof AI_Scripted_Algorithm)
			results = results * 2;
		for(int i=0; i<getDeck_additionals().size(); i++)
		{
			int values = (int) getDeck_additionals().get(i).getOp();
			values = values * getDeck_additionals().get(i).getNum();
			results += values;
		}
		for(int i=0; i<getAi_cards().size(); i++)
		{
			int values = (int) getAi_cards().get(i).getOp();
			values = values * getAi_cards().get(i).getNum();
			results += values;
		}
		for(int i=0; i<getPlayer_cards().size(); i++)
		{
			int values = (int) getPlayer_cards().get(i).getOp();
			values = values * getPlayer_cards().get(i).getNum();
			results += values;
		}
		results = results * 3;
		
		if(getTrump_mode().booleanValue()) results = results / 2;
		
		return (results == getAuthorize_code().longValue());
	}
}
