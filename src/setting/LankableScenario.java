package setting;
import java.math.BigInteger;
import java.util.Vector;

import ai_algorithm.AI_Scripted_Algorithm;
import main_classes.StandardCard;

public class LankableScenario extends ExtendedScenario
{
	private static final long serialVersionUID = -4568507813181837904L;
	private Vector<String> lank;
	private String lank_separator;
	private String endMessage, korean_endMessage;
	private String fail_endMessage, fail_korean_endMessage;
	private BigInteger limit_turn;

	public LankableScenario()
	{
		super();
		lank = new Vector<String>();
		lank_separator = "::";
		endMessage = null;
		korean_endMessage = null;
		fail_endMessage = null;
		fail_korean_endMessage = null;
		limit_turn = Lint.big(0);
	}
	
	public Vector<String> getLank()
	{
		return lank;
	}

	public void setLank(Vector<String> lank)
	{
		this.lank = lank;
	}

	public String getLank_separator()
	{
		return lank_separator;
	}

	public void setLank_separator(String lank_separator)
	{
		this.lank_separator = lank_separator;
	}

	public String getEndMessage()
	{
		return endMessage;
	}

	public void setEndMessage(String endMessage)
	{
		this.endMessage = endMessage;
	}

	public String getKorean_endMessage()
	{
		return korean_endMessage;
	}

	public void setKorean_endMessage(String korean_endMessage)
	{
		this.korean_endMessage = korean_endMessage;
	}

	public BigInteger getLimit_turn()
	{
		return limit_turn;
	}

	public void setLimit_turn(BigInteger limit_turn)
	{
		this.limit_turn = limit_turn;
	}

	public String getFail_endMessage()
	{
		return fail_endMessage;
	}

	public void setFail_endMessage(String fail_endMessage)
	{
		this.fail_endMessage = fail_endMessage;
	}

	public String getFail_korean_endMessage()
	{
		return fail_korean_endMessage;
	}

	public void setFail_korean_endMessage(String fail_korean_endMessage)
	{
		this.fail_korean_endMessage = fail_korean_endMessage;
	}

	public LankableScenario clone()
	{
		LankableScenario newOne = new LankableScenario();
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
		newOne.setStars(new Long(getStars().longValue()));
		newOne.setRandom_deck_use(new Boolean(getRandom_deck_use().booleanValue()));
		newOne.setRandom_multiply_use(new Boolean(getRandom_multiply_use().booleanValue()));
		newOne.setRandom_player_cards_use(new Boolean(getRandom_player_cards_use().booleanValue()));
		newOne.setRandom_plus_use(new Boolean(getRandom_plus_use().booleanValue()));
		newOne.setRandom_multiply_max(new Integer(getRandom_multiply_max().intValue()));
		newOne.setRandom_multiply_min(new Integer(getRandom_multiply_min().intValue()));
		newOne.setRandom_plus_max(new Integer(getRandom_plus_max().intValue()));
		newOne.setRandom_plus_min(new Integer(getRandom_plus_min().intValue()));
		newOne.setRandom_deck_max(new Integer(getRandom_deck_max().intValue()));
		newOne.setRandom_deck_min(new Integer(getRandom_deck_min().intValue()));
		newOne.setRandom_plus_max(new Integer(getRandom_plus_max().intValue()));
		newOne.setRandom_plus_min(new Integer(getRandom_plus_min().intValue()));
		newOne.setRandom_player_cards_max(new Integer(getRandom_player_cards_max().intValue()));
		newOne.setRandom_player_cards_min(new Integer(getRandom_player_cards_min().intValue()));
		for(int i=0; i<getLank().size(); i++)
		{
			newOne.getLank().add(new String(getLank().get(i)));
		}
		newOne.setLank_separator(new String(getLank_separator()));
		if(getEndMessage() != null)
			newOne.setEndMessage(new String(getEndMessage()));
		if(getKorean_endMessage() != null)
			newOne.setKorean_endMessage(new String(getKorean_endMessage()));
		if(getFail_endMessage() != null)
			newOne.setFail_endMessage(new String(getFail_endMessage()));
		if(getFail_korean_endMessage() != null)
			newOne.setFail_korean_endMessage(new String(getFail_korean_endMessage()));
		if(getWeb_url() != null)
		{
			newOne.setWeb_url(new String(getWeb_url()));
		}
		if(getEvent_deadline() != null)
		{
			newOne.setEvent_deadline(getEvent_deadline());
		}
		if(getEvent_onlyWin() != null)
		{
			newOne.setEvent_onlyWin(new Boolean(getEvent_onlyWin().booleanValue()));
		}
		newOne.setLimit_turn(new BigInteger(getLimit_turn().toString()));
		if(getStar_line() != null)
		{
			newOne.setStar_line(new Long[getStar_line().length]);
			for(int i=0; i<getStar_line().length; i++)
			{
				if(getStar_line()[i] != null)
				{
					newOne.getStar_line()[i] = new Long(getStar_line()[i].longValue());
				}
				else
				{
					newOne.getStar_line()[i] = new Long(0);
				}
			}
		}
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
		BigInteger results2 = new BigInteger("0");
		
		long results = 0;
		char[] names = getName().toCharArray();
		char[] korNames = getKoreanName().toCharArray();
		char[] descriptions = getDescription().toCharArray();
		char[] korDesc = getKoreanDescription().toCharArray();
		char[] scripts = getTurn_script().toCharArray();
		for(int i=0; i<names.length; i++)
		{
			results += (long) names[i];
			results2 = results2.add(Lint.big( ((long) names[i])));
		}
		for(int i=0; i<korNames.length; i++)
		{
			results += (long) korNames[i];
			results2 = results2.add(Lint.big( ((long) korNames[i])));
		}
		results = results * 3;
		results2 = results2.multiply(Lint.big(3));
		results += getDifficulty().longValue();
		results2 = results2.add(Lint.big(getDifficulty().longValue()));
		results += getBonuses().longValue();
		results2 = results2.add(Lint.big(getBonuses().longValue()));
		results *= 2;
		results2 = results2.multiply(Lint.big(2));
		
		for(int i=0; i<descriptions.length; i++)
		{
			results += (long) descriptions[i];
			results2 = results2.add(Lint.big( ((long) descriptions[i])));
		}
		for(int i=0; i<korDesc.length; i++)
		{
			results += (long) korDesc[i];
			results2 = results2.add(Lint.big( ((long) korDesc[i])));
		}
		results = results * 4;
		results2 = results2.multiply(Lint.big(4));
		results += getMultiply_card_ratio().longValue();
		results2 = results2.add(Lint.big(getMultiply_card_ratio().longValue()));
		results += getPlayer_cards_count().longValue();
		results2 = results2.add(Lint.big(getPlayer_cards_count().longValue()));
		results += getChange_card_count().longValue();
		results2 = results2.add(Lint.big(getChange_card_count().longValue()));
		results += getPlayer_cards_count().longValue();
		results2 = results2.add(Lint.big(getPlayer_cards_count().longValue()));
		results += getAi_cards_count().longValue();
		results2 = results2.add(Lint.big(getAi_cards_count().longValue()));
		results += getTimelimit().longValue();
		results2 = results2.add(Lint.big(getTimelimit().longValue()));
		results += 5;
		results2 = results2.add(Lint.big(5));
		
		for(int i=0; i<scripts.length; i++)
		{
			results += (long) scripts[i];
			results2 = results2.add(Lint.big( ((long) scripts[i])));
		}
		results = results * 2;
		results2 = results2.multiply(Lint.big(2));
		if(getAi() instanceof AI_Scripted_Algorithm)
		{
			results = results * 2;
			results2 = results2.multiply(Lint.big(2));
		}
		for(int i=0; i<getDeck_additionals().size(); i++)
		{
			int values = (int) getDeck_additionals().get(i).getOp();
			values = values * getDeck_additionals().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}
		for(int i=0; i<getAi_cards().size(); i++)
		{
			int values = (int) getAi_cards().get(i).getOp();
			values = values * getAi_cards().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}
		for(int i=0; i<getPlayer_cards().size(); i++)
		{
			int values = (int) getPlayer_cards().get(i).getOp();
			values = values * getPlayer_cards().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}		
		
		
		results = results * 3;
		results2 = results2.multiply(Lint.big(3));
		
		if(getTrump_mode().booleanValue())
		{
			results = results / 2;	
			results2 = results2.divide(Lint.big(2));
		}
		if(getWeb_url() != null)
		{
			for(int i=0; i<getWeb_url().toCharArray().length; i++)
			{
				results = results + (long) getWeb_url().toCharArray()[i];
				results2 = results2.add(Lint.big((long) getWeb_url().toCharArray()[i]));
			}
		}
		if(getEvent_deadline() != null)
		{
			results = results + getEvent_deadline().getYear();
			results = results + getEvent_deadline().getMonth();
			results = results + getEvent_deadline().getDay();
			results = results + getEvent_deadline().getHour();
			results = results + getEvent_deadline().getMinute();
			results = results + getEvent_deadline().getSecond();
			results2 = results2.add(Lint.big(getEvent_deadline().getYear()));
			results2 = results2.add(Lint.big(getEvent_deadline().getMonth()));
			results2 = results2.add(Lint.big(getEvent_deadline().getDay()));
			results2 = results2.add(Lint.big(getEvent_deadline().getHour()));
			results2 = results2.add(Lint.big(getEvent_deadline().getMinute()));
			results2 = results2.add(Lint.big(getEvent_deadline().getSecond()));
		}
		if(getLimit_turn() != null)
		{
			results = results + getLimit_turn().longValue();
			results2 = results2.add(getLimit_turn());
		}
		if(getEvent_onlyWin() != null)
		{
			if(getEvent_onlyWin().booleanValue())
			{
				results = results * 3;
				results2 = results2.multiply(Lint.big(3));
			}
		}
		results = results * 2;
		results2 = results2.multiply(Lint.big(2));
		
		
		setAuthorize_code(new Long(results));
	}
	public boolean isAuthorized(Setting sets)
	{
		Setting setting = sets;
		if(setting == null)
			setting = Setting.getNewInstance();
		BigInteger results2 = new BigInteger("0");
		
		long results = 0;
		char[] names = getName().toCharArray();
		char[] korNames = getKoreanName().toCharArray();
		char[] descriptions = getDescription().toCharArray();
		char[] korDesc = getKoreanDescription().toCharArray();
		char[] scripts = getTurn_script().toCharArray();
		for(int i=0; i<names.length; i++)
		{
			results += (long) names[i];
			results2 = results2.add(Lint.big( ((long) names[i])));
		}
		for(int i=0; i<korNames.length; i++)
		{
			results += (long) korNames[i];
			results2 = results2.add(Lint.big( ((long) korNames[i])));
		}
		results = results * 3;
		results2 = results2.multiply(Lint.big(3));
		results += getDifficulty().longValue();
		results2 = results2.add(Lint.big(getDifficulty().longValue()));
		results += getBonuses().longValue();
		results2 = results2.add(Lint.big(getBonuses().longValue()));
		results *= 2;
		results2 = results2.multiply(Lint.big(2));
		
		for(int i=0; i<descriptions.length; i++)
		{
			results += (long) descriptions[i];
			results2 = results2.add(Lint.big( ((long) descriptions[i])));
		}
		for(int i=0; i<korDesc.length; i++)
		{
			results += (long) korDesc[i];
			results2 = results2.add(Lint.big( ((long) korDesc[i])));
		}
		results = results * 4;
		results2 = results2.multiply(Lint.big(4));
		results += getMultiply_card_ratio().longValue();
		results2 = results2.add(Lint.big(getMultiply_card_ratio().longValue()));
		results += getPlayer_cards_count().longValue();
		results2 = results2.add(Lint.big(getPlayer_cards_count().longValue()));
		results += getChange_card_count().longValue();
		results2 = results2.add(Lint.big(getChange_card_count().longValue()));
		results += getPlayer_cards_count().longValue();
		results2 = results2.add(Lint.big(getPlayer_cards_count().longValue()));
		results += getAi_cards_count().longValue();
		results2 = results2.add(Lint.big(getAi_cards_count().longValue()));
		results += getTimelimit().longValue();
		results2 = results2.add(Lint.big(getTimelimit().longValue()));
		results += 5;
		results2 = results2.add(Lint.big(5));
		
		for(int i=0; i<scripts.length; i++)
		{
			results += (long) scripts[i];
			results2 = results2.add(Lint.big( ((long) scripts[i])));
		}
		results = results * 2;
		results2 = results2.multiply(Lint.big(2));
		if(getAi() instanceof AI_Scripted_Algorithm)
		{
			results = results * 2;
			results2 = results2.multiply(Lint.big(2));
		}
		for(int i=0; i<getDeck_additionals().size(); i++)
		{
			int values = (int) getDeck_additionals().get(i).getOp();
			values = values * getDeck_additionals().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}
		for(int i=0; i<getAi_cards().size(); i++)
		{
			int values = (int) getAi_cards().get(i).getOp();
			values = values * getAi_cards().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}
		for(int i=0; i<getPlayer_cards().size(); i++)
		{
			int values = (int) getPlayer_cards().get(i).getOp();
			values = values * getPlayer_cards().get(i).getNum();
			results += values;
			results2 = results2.add(Lint.big(values));
		}		
		
		
		results = results * 3;
		results2 = results2.multiply(Lint.big(3));
		
		if(getTrump_mode().booleanValue())
		{
			results = results / 2;	
			results2 = results2.divide(Lint.big(2));
		}
		if(getWeb_url() != null)
		{
			for(int i=0; i<getWeb_url().toCharArray().length; i++)
			{
				results = results + (long) getWeb_url().toCharArray()[i];
				results2 = results2.add(Lint.big((long) getWeb_url().toCharArray()[i]));
			}
		}
		if(getEvent_deadline() != null)
		{
			results = results + getEvent_deadline().getYear();
			results = results + getEvent_deadline().getMonth();
			results = results + getEvent_deadline().getDay();
			results = results + getEvent_deadline().getHour();
			results = results + getEvent_deadline().getMinute();
			results = results + getEvent_deadline().getSecond();
			results2 = results2.add(Lint.big(getEvent_deadline().getYear()));
			results2 = results2.add(Lint.big(getEvent_deadline().getMonth()));
			results2 = results2.add(Lint.big(getEvent_deadline().getDay()));
			results2 = results2.add(Lint.big(getEvent_deadline().getHour()));
			results2 = results2.add(Lint.big(getEvent_deadline().getMinute()));
			results2 = results2.add(Lint.big(getEvent_deadline().getSecond()));
		}
		if(getLimit_turn() != null)
		{
			results = results + getLimit_turn().longValue();
			results2 = results2.add(getLimit_turn());
		}
		if(getEvent_onlyWin() != null)
		{
			if(getEvent_onlyWin().booleanValue())
			{
				results = results * 3;
				results2 = results2.multiply(Lint.big(3));
			}
		}
		results = results * 2;
		results2 = results2.multiply(Lint.big(2));
		
		return (results == getAuthorize_code().longValue());
	}
}
