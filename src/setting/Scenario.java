package setting;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import main_classes.StandardCard;
import ai_algorithm.AI_Algorithm;

public class Scenario implements Serializable
{
	private static final long serialVersionUID = -8990924988531459266L;
	private String name = "";
	private String description = "";
	private Integer difficulty = new Integer(1);
	private Integer bonuses = new Integer(5);
	private Integer multiply_card_ratio = new Integer(4);
	private Integer plusminus_card_ratio = new Integer(4);
	private Integer change_card_count = new Integer(1);	
	private Integer player_cards_count = new Integer(10);
	private Integer ai_cards_count = new Integer(10);
	private Integer timelimit = new Integer(20);
	private Long authorize_code = new Long(0);
	private Boolean trump_mode = new Boolean(false);
	private AI_Algorithm ai = null;
	private Vector<StandardCard> deck_additionals = new Vector<StandardCard>();
	private Vector<StandardCard> player_cards = new Vector<StandardCard>();
	private Vector<StandardCard> ai_cards = new Vector<StandardCard>();
	private String turn_script = "";

	public static String scenarioToString(ExtendedScenario scen)
	{
		String newOne = "scenario\n";
		
		newOne = newOne + "name|" + scen.getName() + "\n";
		newOne = newOne + "name_kor|" + scen.getKoreanName() + "\n";
		
		StringTokenizer stoken;
		int countToken;
		try
		{
			stoken = new StringTokenizer(scen.getDescription(), "\n");
			countToken = stoken.countTokens();		
			for(int i=0; i<countToken; i++)
			{			
				newOne = newOne + "description|" + stoken.nextToken() + "\n";
			}
		} 
		catch (Exception e2)
		{
			
		}
		try
		{
			stoken = new StringTokenizer(scen.getKoreanDescription(), "\n");
			countToken = stoken.countTokens();
			for(int i=0; i<countToken; i++)
			{			
				newOne = newOne + "description_kor|" + stoken.nextToken() + "\n";
			}
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			stoken = new StringTokenizer(scen.getTurn_script(), "\n");
			countToken = stoken.countTokens();
			for(int i=0; i<countToken; i++)
			{			
				newOne = newOne + "script|" + stoken.nextToken() + "\n";
			}
		} 
		catch (Exception e)
		{
			
		}		
		
		newOne = newOne + "difficulty|" + String.valueOf(scen.getDifficulty()) + "\n";
		newOne = newOne + "bonus|" + String.valueOf(scen.getBonuses()) + "\n";
		newOne = newOne + "multiply_card_ratio|" + String.valueOf(scen.getMultiply_card_ratio()) + "\n";
		newOne = newOne + "plus_card_ratio|" + String.valueOf(scen.getPlusminus_card_ratio()) + "\n";
		newOne = newOne + "change_card_count|" + String.valueOf(scen.getChange_card_count()) + "\n";
		newOne = newOne + "player_card_count|" + String.valueOf(scen.getPlayer_cards_count()) + "\n";
		newOne = newOne + "ai_card_count|" + String.valueOf(scen.getAi_cards_count()) + "\n";
		newOne = newOne + "timelimit|" + String.valueOf(scen.getTimelimit()) + "\n";
		newOne = newOne + "authorize|" + String.valueOf(scen.getAuthorize_code()) + "\n";
		if(scen.getWeb_url() != null)
			newOne = newOne + "web_url|" + String.valueOf(scen.getWeb_url()) + "\n";
		if(scen.getEvent_deadline() != null)
			newOne = newOne + "event_deadline|" + String.valueOf(scen.getEvent_deadline().getYear()) + 
			"," + String.valueOf(scen.getEvent_deadline().getMonth())
			+ "," + String.valueOf(scen.getEvent_deadline().getDay())
			+ "," + String.valueOf(scen.getEvent_deadline().getHour())
			+ "," + String.valueOf(scen.getEvent_deadline().getMinute())
			+ "," + String.valueOf(scen.getEvent_deadline().getSecond())+ "\n";
		
		if(scen.getEvent_onlyWin() != null)
		{
			if(scen.getEvent_onlyWin().booleanValue())
			{
				newOne = newOne + "event_onlywin|true" + "\n";
			}
			else
			{
				newOne = newOne + "event_onlywin|false" + "\n";
			}
		}
		
		newOne = newOne + "random_player_min|" + String.valueOf(scen.getRandom_player_cards_min()) + "\n";
		newOne = newOne + "random_player_max|" + String.valueOf(scen.getRandom_player_cards_max()) + "\n";
		newOne = newOne + "random_plus_min|" + String.valueOf(scen.getRandom_plus_min()) + "\n";
		newOne = newOne + "random_plus_max|" + String.valueOf(scen.getRandom_plus_max()) + "\n";
		newOne = newOne + "random_multiply_min|" + String.valueOf(scen.getRandom_multiply_min()) + "\n";
		newOne = newOne + "random_multiply_max|" + String.valueOf(scen.getRandom_multiply_max()) + "\n";
		newOne = newOne + "random_deck_min|" + String.valueOf(scen.getRandom_deck_min()) + "\n";
		newOne = newOne + "random_deck_max|" + String.valueOf(scen.getRandom_deck_max()) + "\n";
		
		
		
		if(scen.getRandom_plus_use().booleanValue())
		{
			newOne = newOne + "random_plus|true" + "\n";
		}
		else
		{
			newOne = newOne + "random_plus|false" + "\n";
		}
		if(scen.getRandom_multiply_use().booleanValue())
		{
			newOne = newOne + "random_multiply|true" + "\n";
		}
		else
		{
			newOne = newOne + "random_multiply|false" + "\n";
		}
		if(scen.getRandom_player_cards_use().booleanValue())
		{
			newOne = newOne + "random_player|true" + "\n";
		}
		else
		{
			newOne = newOne + "random_player|false" + "\n";
		}
		if(scen.getRandom_deck_use().booleanValue())
		{
			newOne = newOne + "random_deck|true" + "\n";
		}
		else
		{
			newOne = newOne + "random_deck|false" + "\n";
		}
		
		for(int i=0; i<scen.getPlayer_cards().size(); i++)
		{
			newOne = newOne + "player_card|" + String.valueOf(scen.getPlayer_cards().get(i).getOp()) + "," + String.valueOf(scen.getPlayer_cards().get(i).getNum()) + "\n";
		}
		for(int i=0; i<scen.getAi_cards().size(); i++)
		{
			newOne = newOne + "ai_card|" + String.valueOf(scen.getAi_cards().get(i).getOp()) + "," + String.valueOf(scen.getAi_cards().get(i).getNum()) + "\n";
		}
		for(int i=0; i<scen.getDeck_additionals().size(); i++)
		{
			newOne = newOne + "deck|" + String.valueOf(scen.getDeck_additionals().get(i).getOp()) + "," + String.valueOf(scen.getDeck_additionals().get(i).getNum()) + "\n";
		}
		if(scen.getStar_line() != null)
		{
			if(scen.getStar_line().length >= 1)
			{
				try
				{
					for(int i=0; i<scen.getStar_line().length; i++)
					{
						if(scen.getStar_line()[i] != null)
						{
							newOne = newOne + "star_object|" + String.valueOf(scen.getStar_line()[i].longValue()) + "\n";
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
		if(scen instanceof LankableScenario)
		{
			LankableScenario lks = (LankableScenario) scen;
			newOne = newOne + "lank_separator|" + lks.getLank_separator() + "\n";
			newOne = newOne + "limit_turn|" + lks.getLimit_turn().toString() + "\n";
			for(int i=0; i<lks.getLank().size(); i++)
			{
				newOne = newOne + "lank|" + lks.getLank().get(i) + "\n";
			}
			try
			{
				stoken = new StringTokenizer(lks.getEndMessage(), "\n");
				countToken = stoken.countTokens();
				for(int i=0; i<countToken; i++)
				{			
					newOne = newOne + "end_message|" + stoken.nextToken() + "\n";
				}
			} 
			catch (Exception e)
			{
				
			}	
			try
			{
				stoken = new StringTokenizer(lks.getKorean_endMessage(), "\n");
				countToken = stoken.countTokens();
				for(int i=0; i<countToken; i++)
				{			
					newOne = newOne + "kor_end_message|" + stoken.nextToken() + "\n";
				}
			} 
			catch (Exception e)
			{
				
			}	
			try
			{
				stoken = new StringTokenizer(lks.getFail_endMessage(), "\n");
				countToken = stoken.countTokens();
				for(int i=0; i<countToken; i++)
				{			
					newOne = newOne + "fail_end_message|" + stoken.nextToken() + "\n";
				}
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				stoken = new StringTokenizer(lks.getFail_korean_endMessage(), "\n");
				countToken = stoken.countTokens();
				for(int i=0; i<countToken; i++)
				{			
					newOne = newOne + "fail_kor_end_message|" + stoken.nextToken() + "\n";
				}
			} 
			catch (Exception e)
			{
				
			}
		}
		
		return newOne;
	}
	public static LankableScenario stringToScenario(String str)
	{
		LankableScenario newOne = null;
		
		StringTokenizer linetoken = new StringTokenizer(str, "\n");
		StringTokenizer delimToken = null, cardToken = null;
		int lineCounts = linetoken.countTokens();
		String dirc = "", content = "", card1 = "", card2 = "";
		StandardCard newCard = null;
		long changeInt = 0;
		
		try
		{
			String titles = linetoken.nextToken();
			if(titles.equalsIgnoreCase("scenario"))
				newOne = new LankableScenario();
			else if(titles.equalsIgnoreCase("detailed_scenario"))
			{
				newOne = new DetailedScenario();
			}
			else return null;
		} 
		catch (Exception e1)
		{
			return null;
		}
		
		newOne.setDescription("");
		newOne.setKoreanDescription("");
		newOne.setTurn_script("");
		
		for(int i=1; i<lineCounts; i++)
		{
			try
			{
				delimToken = new StringTokenizer(linetoken.nextToken(), "|");
				dirc = delimToken.nextToken();
				content = delimToken.nextToken();
				if(dirc.equalsIgnoreCase("lank_separator"))
				{
					newOne.setLank_separator(content);
				}
				else if(dirc.equalsIgnoreCase("limit_turn"))
				{
					newOne.setLimit_turn(new BigInteger(content));
				}
				else if(dirc.equalsIgnoreCase("lank"))
				{
					newOne.getLank().add(content);
				}
				else if(dirc.equalsIgnoreCase("end_message"))
				{
					if(newOne.getEndMessage() == null) newOne.setEndMessage("");
					newOne.setEndMessage(newOne.getEndMessage() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("kor_end_message"))
				{
					if(newOne.getKorean_endMessage() == null) newOne.setKorean_endMessage("");
					newOne.setKorean_endMessage(newOne.getKorean_endMessage() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("fail_end_message"))
				{
					if(newOne.getFail_endMessage() == null) newOne.setFail_endMessage("");
					newOne.setFail_endMessage(newOne.getFail_endMessage() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("fail_kor_end_message"))
				{
					if(newOne.getFail_korean_endMessage() == null) newOne.setFail_korean_endMessage("");
					newOne.setFail_korean_endMessage(newOne.getFail_korean_endMessage() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("web_url"))
				{
					newOne.setWeb_url(content.trim());
				}
				else if(dirc.equalsIgnoreCase("event_deadline"))
				{
					try
					{
						cardToken = new StringTokenizer(content, ",");
						Date newDate = new Date();
						newDate.setYear(Integer.parseInt(cardToken.nextToken()));
						try
						{
							newDate.setMonth(Integer.parseInt(cardToken.nextToken()));
						} 
						catch (Exception e)
						{
							newDate.setMonth(0);
							newDate.setDay(0);
							newDate.setHour(0);
							newDate.setMinute(0);
							newDate.setSecond(0);
						}
						try
						{
							newDate.setDay(Integer.parseInt(cardToken.nextToken()));
						} 
						catch (Exception e)
						{
							newDate.setDay(0);
							newDate.setHour(0);
							newDate.setMinute(0);
							newDate.setSecond(0);
						}
						try
						{
							newDate.setHour(Integer.parseInt(cardToken.nextToken()));
						} 
						catch (Exception e)
						{
							newDate.setHour(0);
							newDate.setMinute(0);
							newDate.setSecond(0);
						}
						try
						{
							newDate.setMinute(Integer.parseInt(cardToken.nextToken()));
						} 
						catch (Exception e)
						{
							newDate.setMinute(0);
							newDate.setSecond(0);
						}
						try
						{
							newDate.setSecond(Integer.parseInt(cardToken.nextToken()));
						} 
						catch (Exception e)
						{
							newDate.setSecond(0);
						}
						
						newOne.setEvent_deadline(newDate);
					} 
					catch (Exception e)
					{
						
					}
				}
				else if(dirc.equalsIgnoreCase("description"))
				{
					newOne.setDescription(newOne.getDescription() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("description_kor"))
				{
					newOne.setKoreanDescription(newOne.getKoreanDescription() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("script"))
				{
					newOne.setTurn_script(newOne.getTurn_script() + content + "\n");
				}
				else if(dirc.equalsIgnoreCase("star"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					Long[] starLines = newOne.getStar_line();
					if(starLines == null || starLines.length <= 0)
					{
						starLines = new Long[1];
					}
					else
					{
						starLines = new Long[starLines.length + 1];
						for(int j=0; j<newOne.getStar_line().length; j++)
						{
							starLines[j] = new Long(newOne.getStar_line()[i].longValue());
						}
					}
					starLines[starLines.length - 1] = new Long(content);
					newOne.setStar_line(starLines);
				}
				else if(dirc.equalsIgnoreCase("player_card"))
				{
					cardToken = new StringTokenizer(content, ",");
					card1 = cardToken.nextToken();
					card2 = cardToken.nextToken();
					card1 = card1.trim();
					card2 = card2.trim();
					newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(card1.toCharArray()[0]);
					changeInt = Integer.parseInt(card2);
					newCard.setNum((int) changeInt);
					newOne.getPlayer_cards().add(newCard);
				}
				else if(dirc.equalsIgnoreCase("deck"))
				{
					cardToken = new StringTokenizer(content, ",");
					card1 = cardToken.nextToken();
					card2 = cardToken.nextToken();
					card1 = card1.trim();
					card2 = card2.trim();
					newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(card1.toCharArray()[0]);
					changeInt = Integer.parseInt(card2);
					newCard.setNum((int) changeInt);
					newOne.getDeck_additionals().add(newCard);
				}
				else if(dirc.equalsIgnoreCase("ai_card"))
				{
					cardToken = new StringTokenizer(content, ",");
					card1 = cardToken.nextToken();
					card2 = cardToken.nextToken();
					card1 = card1.trim();
					card2 = card2.trim();
					newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(card1.toCharArray()[0]);
					changeInt = Integer.parseInt(card2);
					newCard.setNum((int) changeInt);
					newOne.getAi_cards().add(newCard);
				}
				else if(dirc.equalsIgnoreCase("name"))
				{
					content = content.trim();
					newOne.setName(content);
				}
				else if(dirc.equalsIgnoreCase("name_kor"))
				{
					content = content.trim();
					newOne.setKoreanName(content);
				}
				else if(dirc.equalsIgnoreCase("difficulty"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setDifficulty(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("bonus"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setBonuses(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("multiply_card_ratio"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setMultiply_card_ratio(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("plus_card_ratio"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setPlusminus_card_ratio(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("change_card_count"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setChange_card_count(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("timelimit"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setTimelimit(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("player_card_count"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setPlayer_cards_count(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("authorize"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setAuthorize_code(new Long(changeInt));
				}
				else if(dirc.equalsIgnoreCase("ai_card_count"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setAi_cards_count(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_player_min"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_player_cards_min(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_player_max"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_player_cards_max(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_plus_max"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_plus_max(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_plus_min"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_plus_min(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_multiply_min"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_multiply_min(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_multiply_max"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_multiply_max(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_deck_min"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_deck_min(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_deck_max"))
				{
					content = content.trim();
					changeInt = Long.parseLong(content);
					newOne.setRandom_deck_max(new Integer((int) changeInt));
				}
				else if(dirc.equalsIgnoreCase("random_player"))
				{
					if(content.equalsIgnoreCase("true") || content.equalsIgnoreCase("t"))
					{
						newOne.setRandom_player_cards_use(new Boolean(true));
					}
					else if(content.equalsIgnoreCase("false") || content.equalsIgnoreCase("f"))
					{
						newOne.setRandom_player_cards_use(new Boolean(false));
					}
				}
				else if(dirc.equalsIgnoreCase("random_plus"))
				{
					if(content.equalsIgnoreCase("true") || content.equalsIgnoreCase("t"))
					{
						newOne.setRandom_plus_use(new Boolean(true));
					}
					else if(content.equalsIgnoreCase("false") || content.equalsIgnoreCase("f"))
					{
						newOne.setRandom_plus_use(new Boolean(false));
					}
				}
				else if(dirc.equalsIgnoreCase("event_onlywin"))
				{
					if(content.equalsIgnoreCase("true") || content.equalsIgnoreCase("t"))
					{
						newOne.setEvent_onlyWin(new Boolean(true));
					}
					else if(content.equalsIgnoreCase("false") || content.equalsIgnoreCase("f"))
					{
						newOne.setEvent_onlyWin(new Boolean(false));
					}
				}
				else if(dirc.equalsIgnoreCase("random_multiply"))
				{
					if(content.equalsIgnoreCase("true") || content.equalsIgnoreCase("t"))
					{
						newOne.setRandom_multiply_use(new Boolean(true));
					}
					else if(content.equalsIgnoreCase("false") || content.equalsIgnoreCase("f"))
					{
						newOne.setRandom_multiply_use(new Boolean(false));
					}
				}
				else if(dirc.equalsIgnoreCase("random_deck"))
				{
					if(content.equalsIgnoreCase("true") || content.equalsIgnoreCase("t"))
					{
						newOne.setRandom_deck_use(new Boolean(true));
					}
					else if(content.equalsIgnoreCase("false") || content.equalsIgnoreCase("f"))
					{
						newOne.setRandom_deck_use(new Boolean(false));
					}
				}
				else if(dirc.equalsIgnoreCase("star_object"))
				{										
					if(newOne.getStar_line() == null || newOne.getStar_line().length <= 0)
					{
						Long[] newArr = new Long[1];
						newArr[0] = new Long(Long.parseLong(content));
						newOne.setStar_line(newArr);						
					}
					else
					{
						Long[] newArr2 = new Long[newOne.getStar_line().length + 1];
						int forleng = newOne.getStar_line().length;
						for(int k=0; k<forleng; k++)
						{
							newArr2[k] = new Long(newOne.getStar_line()[k].longValue());
						}
						newArr2[newArr2.length - 1] = new Long(Long.parseLong(content));	
						newOne.setStar_line(newArr2);
					}
				}
			} 
			catch (NoSuchElementException e)
			{
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return newOne;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getMultiply_card_ratio()
	{
		return multiply_card_ratio;
	}

	public void setMultiply_card_ratio(Integer multiply_card_ratio)
	{
		this.multiply_card_ratio = multiply_card_ratio;
	}

	public Integer getPlusminus_card_ratio()
	{
		return plusminus_card_ratio;
	}

	public void setPlusminus_card_ratio(Integer plusminus_card_ratio)
	{
		this.plusminus_card_ratio = plusminus_card_ratio;
	}

	public Long getAuthorize_code()
	{
		return authorize_code;
	}

	public void setAuthorize_code(Long authorize_code)
	{
		this.authorize_code = authorize_code;
	}

	public Integer getChange_card_count()
	{
		return change_card_count;
	}

	public void setChange_card_count(Integer change_card_count)
	{
		this.change_card_count = change_card_count;
	}

	public Boolean getTrump_mode()
	{
		return trump_mode;
	}

	public void setTrump_mode(Boolean trump_mode)
	{
		this.trump_mode = trump_mode;
	}

	public Integer getBonuses()
	{
		return bonuses;
	}

	public void setBonuses(Integer bonuses)
	{
		this.bonuses = bonuses;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	

	public Integer getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(Integer difficulty)
	{
		this.difficulty = difficulty;
	}

	public AI_Algorithm getAi()
	{
		return ai;
	}

	public void setAi(AI_Algorithm ai)
	{
		this.ai = ai;
	}

	public Integer getPlayer_cards_count()
	{
		return player_cards_count;
	}

	public void setPlayer_cards_count(Integer player_cards_count)
	{
		this.player_cards_count = player_cards_count;
	}

	public Integer getAi_cards_count()
	{
		return ai_cards_count;
	}

	public void setAi_cards_count(Integer ai_cards_count)
	{
		this.ai_cards_count = ai_cards_count;
	}

	public Vector<StandardCard> getPlayer_cards()
	{
		return player_cards;
	}

	public void setPlayer_cards(Vector<StandardCard> player_cards)
	{
		this.player_cards = player_cards;
	}

	public Vector<StandardCard> getAi_cards()
	{
		return ai_cards;
	}

	public void setAi_cards(Vector<StandardCard> ai_cards)
	{
		this.ai_cards = ai_cards;
	}

	public Vector<StandardCard> getDeck_additionals()
	{
		return deck_additionals;
	}

	public void setDeck_additionals(Vector<StandardCard> deck_additionals)
	{
		this.deck_additionals = deck_additionals;
	}

	public String getTurn_script()
	{
		return turn_script;
	}

	public void setTurn_script(String turn_script)
	{
		this.turn_script = turn_script;
	}

	public Integer getTimelimit()
	{
		return timelimit;
	}

	public void setTimelimit(Integer timelimit)
	{
		this.timelimit = timelimit;
	}

	public Scenario clone()
	{
		Scenario newOne = new Scenario();
		newOne.name = new String(name);
		newOne.multiply_card_ratio = new Integer(multiply_card_ratio.intValue());
		newOne.plusminus_card_ratio = new Integer(plusminus_card_ratio.intValue());
		newOne.change_card_count = new Integer(change_card_count.intValue());
		newOne.difficulty = new Integer(difficulty.intValue());
		newOne.player_cards_count = new Integer(player_cards_count.intValue());
		newOne.ai_cards_count = new Integer(ai_cards_count.intValue());
		newOne.bonuses = new Integer(bonuses.intValue());
		newOne.timelimit = new Integer(timelimit.intValue());
		newOne.authorize_code = new Long(authorize_code.longValue());
		newOne.trump_mode = new Boolean(trump_mode.booleanValue());
		newOne.description = new String(description);
		newOne.turn_script = new String(turn_script);
		for(int i=0; i<player_cards.size(); i++)
		{
			newOne.player_cards.add((StandardCard) player_cards.get(i).clone());
		}
		for(int i=0; i<ai_cards.size(); i++)
		{
			newOne.ai_cards.add((StandardCard) ai_cards.get(i).clone());
		}
		for(int i=0; i<deck_additionals.size(); i++)
		{
			newOne.deck_additionals.add((StandardCard) deck_additionals.get(i).clone());
		}
		if(ai != null)
		{
			try
			{
				newOne.ai = ai.clone();
			} 
			catch (Exception e)
			{
				newOne.ai = null;
			}
		}
		return newOne;
	}
	
}
