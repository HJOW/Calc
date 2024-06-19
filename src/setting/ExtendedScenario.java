package setting;

import java.math.BigInteger;

import ai_algorithm.AI_Scripted_Algorithm;
import main_classes.StandardCard;

public class ExtendedScenario extends AuthorizedScenario
{
	private static final long serialVersionUID = -7032163972761029351L;
	private Boolean random_plus_use;
	private Integer random_plus_min;
	private Integer random_plus_max;
	private Boolean random_multiply_use;
	private Integer random_multiply_min;
	private Integer random_multiply_max;
	private Boolean random_deck_use;
	private Integer random_deck_min;
	private Integer random_deck_max;
	private Boolean random_player_cards_use;
	private Integer random_player_cards_max;
	private Integer random_player_cards_min;
	private Long stars;
	private Long[] star_line = null;
	private String web_url = null;
	private Date event_deadline = null;
	private Boolean event_onlyWin = null;
	
	public ExtendedScenario()
	{
		super();
		random_plus_use = new Boolean(false);
		random_plus_min = new Integer(-1);
		random_plus_max = new Integer(10);
		random_multiply_use = new Boolean(false);
		random_multiply_min = new Integer(-1);
		random_multiply_max = new Integer(10);
		random_deck_use = new Boolean(false);
		random_deck_max = new Integer(1000);
		random_deck_min = new Integer(200);
		random_player_cards_use = new Boolean(false);
		random_player_cards_max = new Integer(11);
		random_player_cards_min = new Integer(10);
		stars = new Long(0);
	}
	
	public Integer getRandom_plus_min()
	{
		return random_plus_min;
	}

	public void setRandom_plus_min(Integer random_plus_min)
	{
		this.random_plus_min = random_plus_min;
	}

	public Integer getRandom_plus_max()
	{
		return random_plus_max;
	}

	public void setRandom_plus_max(Integer random_plus_max)
	{
		this.random_plus_max = random_plus_max;
	}



	public Integer getRandom_multiply_min()
	{
		return random_multiply_min;
	}

	public void setRandom_multiply_min(Integer random_multiply_min)
	{
		this.random_multiply_min = random_multiply_min;
	}

	public Integer getRandom_multiply_max()
	{
		return random_multiply_max;
	}

	public void setRandom_multiply_max(Integer random_multiply_max)
	{
		this.random_multiply_max = random_multiply_max;
	}

	public Integer getRandom_deck_min()
	{
		return random_deck_min;
	}

	public void setRandom_deck_min(Integer random_deck_min)
	{
		this.random_deck_min = random_deck_min;
	}

	public Integer getRandom_deck_max()
	{
		return random_deck_max;
	}

	public void setRandom_deck_max(Integer random_deck_max)
	{
		this.random_deck_max = random_deck_max;
	}

	public Integer getRandom_player_cards_max()
	{
		return random_player_cards_max;
	}

	public void setRandom_player_cards_max(Integer random_player_cards_max)
	{
		this.random_player_cards_max = random_player_cards_max;
	}

	public Integer getRandom_player_cards_min()
	{
		return random_player_cards_min;
	}

	public void setRandom_player_cards_min(Integer random_player_cards_min)
	{
		this.random_player_cards_min = random_player_cards_min;
	}

	public Boolean getRandom_plus_use()
	{
		return random_plus_use;
	}

	public void setRandom_plus_use(Boolean random_plus_use)
	{
		this.random_plus_use = random_plus_use;
	}

	public Boolean getRandom_multiply_use()
	{
		return random_multiply_use;
	}

	public void setRandom_multiply_use(Boolean random_multiply_use)
	{
		this.random_multiply_use = random_multiply_use;
	}

	public Boolean getRandom_deck_use()
	{
		return random_deck_use;
	}

	public void setRandom_deck_use(Boolean random_deck_use)
	{
		this.random_deck_use = random_deck_use;
	}

	public Boolean getRandom_player_cards_use()
	{
		return random_player_cards_use;
	}

	public void setRandom_player_cards_use(Boolean random_player_cards_use)
	{
		this.random_player_cards_use = random_player_cards_use;
	}

	public Long getStars()
	{
		return stars;
	}

	public void setStars(Long stars)
	{
		this.stars = stars;
	}

	public Long[] getStar_line()
	{
		return star_line;
	}

	public void setStar_line(Long[] star_line)
	{
		this.star_line = star_line;
	}

	public String getWeb_url()
	{
		return web_url;
	}

	public void setWeb_url(String web_url)
	{
		this.web_url = web_url;
	}

	public ExtendedScenario clone()
	{
		ExtendedScenario newOne = new ExtendedScenario();
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
		newOne.stars = new Long(stars.longValue());
		newOne.random_deck_use = new Boolean(random_deck_use.booleanValue());
		newOne.random_multiply_use = new Boolean(random_multiply_use.booleanValue());
		newOne.random_player_cards_use = new Boolean(random_player_cards_use.booleanValue());
		newOne.random_plus_use = new Boolean(random_plus_use.booleanValue());
		newOne.random_multiply_max = new Integer(random_multiply_max.intValue());
		newOne.random_multiply_min = new Integer(random_multiply_min.intValue());
		newOne.random_plus_max = new Integer(random_plus_max.intValue());
		newOne.random_plus_min = new Integer(random_plus_min.intValue());
		newOne.random_deck_max = new Integer(random_deck_max.intValue());
		newOne.random_deck_min = new Integer(random_deck_min.intValue());
		newOne.random_plus_max = new Integer(random_plus_max.intValue());
		newOne.random_plus_min = new Integer(random_plus_min.intValue());
		newOne.random_player_cards_max = new Integer(random_player_cards_max.intValue());
		newOne.random_player_cards_min = new Integer(random_player_cards_min.intValue());
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
		if(star_line != null)
		{
			newOne.star_line = new Long[star_line.length];
			for(int i=0; i<star_line.length; i++)
			{
				if(star_line[i] != null)
				{
					newOne.star_line[i] = new Long(star_line[i].longValue());
				}
				else
				{
					newOne.star_line[i] = new Long(0);
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
		if(getEvent_onlyWin() != null)
		{
			if(getEvent_onlyWin().booleanValue())
			{
				results = results * 3;
				results2 = results2.multiply(Lint.big(3));
			}
		}
		results = results / 2;	
		results2 = results2.divide(Lint.big(2));
		
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
		if(getEvent_onlyWin() != null)
		{
			if(getEvent_onlyWin().booleanValue())
			{
				results = results * 3;
				results2 = results2.multiply(Lint.big(3));
			}
		}
		results = results / 2;	
		results2 = results2.divide(Lint.big(2));
		
		return results == getAuthorize_code().longValue();
	}

	public Date getEvent_deadline()
	{
		return event_deadline;
	}

	public void setEvent_deadline(Date event_deadline)
	{
		this.event_deadline = event_deadline;
	}

	public Boolean getEvent_onlyWin()
	{
		return event_onlyWin;
	}

	public void setEvent_onlyWin(Boolean event_onlyWin)
	{
		this.event_onlyWin = event_onlyWin;
	}
}
