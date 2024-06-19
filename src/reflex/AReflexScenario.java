package reflex;

import java.io.Serializable;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class AReflexScenario extends ReflexScenario
{
	private static final long serialVersionUID = -6099788907065693378L;
	private Long star1, star2, star3;
	private Long randomCode;
	private Boolean event_exist;
	private String event_url;
	private Integer event_year, event_month, event_day, event_hour, event_minute, event_second;
	private String trig = "";
	public AReflexScenario()
	{
		super();
		randomCode = new Long(new Random().nextLong());
		star1 = new Long(1000000);
		star2 = new Long(100000000);
		star3 = new Long(10000000000L);
	}
	
	public AReflexScenario(String contents)
	{
		super();
		
		StringTokenizer stoken = new StringTokenizer(contents, "\n");
		StringTokenizer keyToken;		
		String lines = "", key = "", target = "", additional = null, swap = null;
		String spaceShip = null, defaultPattern = null;
		Vector<EnemyPatternWithIndex> enemyPatterns = new Vector<EnemyPatternWithIndex>();
		while(stoken.hasMoreTokens())
		{
			lines = stoken.nextToken().trim();
			if(lines.startsWith("#")) continue;
			if(lines.equalsIgnoreCase("")) continue;
			additional = null;
			keyToken = new StringTokenizer(lines, Reflexioner.DELIM_SCEN);
			
			try
			{
				key = keyToken.nextToken().trim();	
				if(! keyToken.hasMoreTokens()) continue;
				target = keyToken.nextToken().trim();				
				
				if(keyToken.hasMoreTokens())
				{
					additional = keyToken.nextToken().trim();
					swap = target;
					target = additional;
					additional = swap;
				}	
				
				if(key.equalsIgnoreCase("name"))
				{					
					setName(target);
				}
				else if(key.equalsIgnoreCase("description"))
				{
					if(getDescription() == null) setDescription("");
					setDescription(getDescription() + target + "\n");
				}
				else if(key.equalsIgnoreCase("korean_description"))
				{
					if(getDescription() == null) setKoreanDescription("");
					setKoreanDescription(getKoreanDescription() + target + "\n");
				}
				else if(key.equalsIgnoreCase("spaceship"))
				{
					if(spaceShip == null) spaceShip = target;
					else spaceShip = spaceShip + "\n" + target;
				}
				else if(key.equalsIgnoreCase("difficulty"))
				{
					setDifficulty(new Integer(target));
				}
				else if(key.equalsIgnoreCase("enemylimit"))
				{
					setEnemy_limit(new Integer(target));
				}
				else if(key.equalsIgnoreCase("needgrade"))
				{
					setGrade_limit(new Integer(target));
				}
				else if(key.equalsIgnoreCase("timeunit"))
				{
					setDiff_delay(new Long(target));
				}
				else if(key.equalsIgnoreCase("auth"))
				{
					setAuth(new Long(target));
				}
				else if(key.equalsIgnoreCase("star"))
				{
					if(Integer.parseInt(additional) == 1)
					{
						setStar1(Long.parseLong(target));
					}
					else if(Integer.parseInt(additional) == 2)
					{
						setStar2(Long.parseLong(target));
					}
					else if(Integer.parseInt(additional) == 3)
					{
						setStar3(Long.parseLong(target));
					}
				}
				else if(key.equalsIgnoreCase("pattern"))
				{
					if(additional == null) continue;
					int index = Integer.parseInt(additional);
					int nth = 0;
					boolean exists = false;
					String changes = null;
					for(int i=0; i<enemyPatterns.size(); i++)
					{
						if(enemyPatterns.get(i) != null)
						{
							if(enemyPatterns.get(i).getId() == index)
							{
								exists = true;
								changes = enemyPatterns.get(i).getPattern();		
								nth = i;
							}
						}
					}
					if(! exists)
					{
						changes = "";
						nth = enemyPatterns.size();
						index = enemyPatterns.size();
						enemyPatterns.add(new EnemyPatternWithIndex(enemyPatterns.size(), changes));
					}
					enemyPatterns.set(nth, new EnemyPatternWithIndex(index, enemyPatterns.get(nth).getPattern() + target + "\n"));					
				}
				else if(key.equalsIgnoreCase("default_pattern"))
				{
					if(defaultPattern == null) defaultPattern = target;
					else defaultPattern = defaultPattern + "\n" + target;
				}
				else if(key.equalsIgnoreCase("event"))
				{
					setEvent_exist(Boolean.parseBoolean(target));
				}
				else if(key.equalsIgnoreCase("event_url"))
				{
					setEvent_url(target);
				}
				else if(key.equalsIgnoreCase("event_year"))
				{
					setEvent_year(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("event_month"))
				{
					setEvent_month(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("event_day"))
				{
					setEvent_day(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("event_time"))
				{
					setEvent_hour(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("event_minute"))
				{
					setEvent_minute(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("event_second"))
				{
					setEvent_second(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("trigger"))
				{
					setTrig(getTrig() + target + "\n");
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(spaceShip != null)
		{
			spaceShip = spaceShip.trim();
			setSpaceShip(spaceShip);
		}
		if(defaultPattern != null)
		{
			defaultPattern = defaultPattern.trim();
			setDefaultPattern(EnemyPattern.convertPattern(defaultPattern));			
		}
		EnemyPattern[] arr = new EnemyPattern[enemyPatterns.size()];
		for(int i=0; i<enemyPatterns.size(); i++)
		{
			arr[i] = EnemyPattern.convertPattern(enemyPatterns.get(i).getPattern());
		}
		setPatterns(arr);
		randomCode = new Long(new Random().nextLong());
	}
	public String stringData()
	{
		String results = "";
		results = results + "# " + "\"#\" 으로 시작하는 줄은 데이터에 포함되지 않습니다." + "\n";
		results = results + "" + "" + "\n";
		results = results + "# " + "시나리오의 이름을 지정합니다." + "\n";
		results = results + "name" + Reflexioner.DELIM_SCEN + getName() + "\n\n";	
		results = results + "# " + "표시되는 난이도를 지정합니다." + "\n";
		results = results + "difficulty" + Reflexioner.DELIM_SCEN + getDifficulty().toString() + "\n\n";
		results = results + "# " + "0 : 모든 에디션, 1 : Professioner 또는 그 상위, 2 : Ultimate 또는 그 상위, 3 : Master 에디션에서만" + "\n";
		results = results + "needgrade" + Reflexioner.DELIM_SCEN + getGrade_limit().toString() + "\n\n";
		results = results + "# " + "시간 단위를 지정합니다. 5000 이 보통이며, 작을 수록 어려워집니다." + "\n";
		results = results + "timeunit" + Reflexioner.DELIM_SCEN + getDiff_delay().toString() + "\n\n";
		results = results + "# " + "적의 수 제한을 지정합니다. 너무 높게 하면 난이도도 어려워지지만 게임이 느려질 수 있습니다." + "\n";
		results = results + "enemylimit" + Reflexioner.DELIM_SCEN + getEnemy_limit().toString() + "\n\n";
		results = results + "# " + "인증 번호를 지정합니다. 인증된 시나리오에서만 사용합니다." + "\n";
		results = results + "auth" + Reflexioner.DELIM_SCEN + getAuth().toString() + "\n\n";
		results = results + "# " + "별을 얻는 데 필요한 점수의 양을 지정합니다." + "\n";
		results = results + "star" + Reflexioner.DELIM_SCEN + "1" + Reflexioner.DELIM_SCEN + getStar1().toString() + "\n";
		results = results + "star" + Reflexioner.DELIM_SCEN + "2" + Reflexioner.DELIM_SCEN + getStar2().toString() + "\n";
		results = results + "star" + Reflexioner.DELIM_SCEN + "3" + Reflexioner.DELIM_SCEN + getStar3().toString() + "\n\n";
		
		results = results + "# " + "사용할 함선 종류를 지정합니다. 함선 스크립트를 사용할 수도 있습니다." + "\n";
		StringTokenizer lineToken;
		lineToken = new StringTokenizer(getSpaceShip(), "\n");
		while(lineToken.hasMoreTokens())
		{
			results = results + "spaceship" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
		}
		results = results + "\n";
		results = results + "# " + "시나리오 설명을 입력합니다." + "\n";
		if(getDescription() != null)
		{
			lineToken = new StringTokenizer(getDescription(), "\n");
			while(lineToken.hasMoreTokens())
			{
				results = results + "description" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
			}
		}
		results = results + "\n";
		results = results + "# " + "시나리오 한글 설명을 입력합니다." + "\n";
		if(getKoreanDescription() != null)
		{
			lineToken = new StringTokenizer(getKoreanDescription(), "\n");
			while(lineToken.hasMoreTokens())
			{
				results = results + "korean_description" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
			}
		}
		results = results + "\n";
		results = results + "# " + "적이 생성되는 패턴을 입력합니다." + "\n";
		results = results + "# " + "pattern 뒤에 " + Reflexioner.DELIM_SCEN + " 기호를 쓰고, 몇 번째 패턴인지 숫자를 쓴 후 다시 " + Reflexioner.DELIM_SCEN + " 기호를 씁니다." + "\n";
		results = results + "# " + "이후 패턴 정보를 넣습니다. 구분자는 " + Reflexioner.DELIM_ENEMY_PATTERN + " 를 사용합니다." + "\n";
		results = results + "# " + "enemy 속성에는 하위 속성이 여러 개 있으며, 이를 " + Reflexioner.DELIM_ENEMY + " 구분자를 사용합니다." + "\n";
		results = results + "# " + "예제를 참고해 보세요." + "\n";
		
		if(getPatterns() != null)
		{
			for(int i=0; i<getPatterns().length; i++)
			{
				if(getPatterns()[i] != null)
				{
					try
					{
						lineToken = new StringTokenizer(getPatterns()[i].convertStr(), "\n");
						while(lineToken.hasMoreTokens())
						{
							results = results + "pattern" + Reflexioner.DELIM_SCEN + String.valueOf(i) + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
						}
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
			}
		}
		lineToken = new StringTokenizer(getDefaultPattern().convertStr(), "\n");
		while(lineToken.hasMoreTokens())
		{
			try
			{
				results = results + "default_pattern" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(getEvent_exist() != null && getEvent_exist().booleanValue())
		{
			results = results + "event" + Reflexioner.DELIM_SCEN + getEvent_exist().toString() + "\n";
			results = results + "event_url" + Reflexioner.DELIM_SCEN + getEvent_url() + "\n";
			results = results + "event_year" + Reflexioner.DELIM_SCEN + getEvent_year().toString() + "\n";
			results = results + "event_month" + Reflexioner.DELIM_SCEN + getEvent_month().toString() + "\n";
			results = results + "event_day" + Reflexioner.DELIM_SCEN + getEvent_day().toString() + "\n";
			results = results + "event_time" + Reflexioner.DELIM_SCEN + getEvent_hour().toString() + "\n";
			results = results + "event_minute" + Reflexioner.DELIM_SCEN + getEvent_minute().toString() + "\n";
			results = results + "event_second" + Reflexioner.DELIM_SCEN + getEvent_second().toString() + "\n";
			
		}
		if(getTrig() != null)
		{
			lineToken = new StringTokenizer(getTrig(), "\n");
			while(lineToken.hasMoreTokens())
			{
				results = results + "trigger" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
			}
		}
		return results;
	}
	public Long getRandomCode()
	{
		return randomCode;
	}
	public void setRandomCode(Long randomCode)
	{
		this.randomCode = randomCode;
	}
	public Long getStar1()
	{
		return star1;
	}
	public void setStar1(Long star1)
	{
		this.star1 = star1;
	}
	public Long getStar2()
	{
		return star2;
	}
	public void setStar2(Long star2)
	{
		this.star2 = star2;
	}
	public Long getStar3()
	{
		return star3;
	}
	public void setStar3(Long star3)
	{
		this.star3 = star3;
	}
	public long authorized(int password)
	{
		long results = 0;
		try
		{
			for(char c : getName().toCharArray())
			{
				results = results + (long) c;
			}
			for(char c : getDescription().toCharArray())
			{
				results = results + (long) c;
			}
			for(char c : getKoreanDescription().toCharArray())
			{
				results = results + (long) c;
			}
			for(char c : getSpaceShip().toCharArray())
			{
				results = results + (long) c;
			}
			results = results + getDifficulty().intValue();
			results = results - getDiff_delay().longValue();
			results = results * getEnemy_limit().intValue();
			results = results / (getGrade_limit().intValue() + 6);
			results = (long) (results + (getDefaultPattern().getAddDamageRatio().doubleValue() * 1026));
			results = (long) (results + (getDefaultPattern().getAddHPRatio().doubleValue() * 2732));
			results = (long) (results + (getDefaultPattern().getAddHPRatio() * 2183));
			results = (long) (results + (long) ((getDefaultPattern().getMax_delay().longValue() + getDefaultPattern().getMin_delay().longValue()) * getDefaultPattern().getRatio().doubleValue()));
			results = results + getDefaultPattern().getEnemy().getDamage();
			results = results + getDefaultPattern().getEnemy().getMissiles();
			results = results + getDefaultPattern().getEnemy().getMax_energy();
			results = results + getDefaultPattern().getEnemy().getMax_hp();
			if(getDefaultPattern().getEnemy() instanceof Boss)
			{
				results = results + 5124;			
			}
			if(getDefaultPattern().getEnemy() instanceof BigEnemy)
			{
				results = results + 8362;
				results = results + getDefaultPattern().getEnemy().getMissiles();
				results = results + getDefaultPattern().getEnemy().getR();			
			}
			else results = results + 2615;
			results = results + getDefaultPattern().getEnemy().getDy();
			results = results + getDefaultPattern().getEnemy().getDx();
			results = results + getDefaultPattern().getEnemy().getX();
			results = results + getDefaultPattern().getEnemy().getY();
			if(getEvent_exist() != null)
			{
				if(getEvent_exist().booleanValue())
				{
					results = results + 10;
					if(getEvent_url() != null)
					{
						char[] vals = getEvent_url().toCharArray();
						for(int i=0; i<vals.length; i++)
						{
							results = results + (long) vals[i];
						}
					}
					if(getEvent_year() != null)
					{
						results = results + (getEvent_year().intValue() * 2);
					}
					if(getEvent_month() != null)
					{
						results = results + getEvent_month().intValue();
					}
					if(getEvent_day() != null)
					{
						results = results + getEvent_day().intValue();
					}
					if(getEvent_hour() != null)
					{
						results = results + getEvent_hour().intValue();
					}
					if(getEvent_minute() != null)
					{
						results = results + getEvent_minute().intValue();
					}
					if(getEvent_second() != null)
					{
						results = results + getEvent_second().intValue();
					}
				}
			}
			if(getTrig() != null)
			{
				char[] vals = getTrig().toCharArray();
				for(int i=0; i<vals.length; i++)
				{
					results = results + (long) vals[i];
				}
			}
			results = results / 2;
			if(getPatterns() != null)
			{
				for(int i=0; i<getPatterns().length; i++)
				{
					if(getPatterns()[i] != null)
					{
						if(getPatterns()[i] instanceof BossEnemyPattern)
						{
							
						}
						else
						{
							EnemyPattern pattern = getPatterns()[i];
							results = (long) (results + (pattern.getAddDamageRatio().longValue() * 1026));
							results = (long) (results + (pattern.getAddHPRatio().longValue() * 2732));
							results = (long) (results + (pattern.getAddHPRatio() * 2183));
							results = (long) (results + (long) ((pattern.getMax_delay().longValue() + pattern.getMin_delay().longValue()) * pattern.getRatio().doubleValue()));
							results = results + pattern.getEnemy().getDamage();
							results = results + pattern.getEnemy().getMissiles();
							results = results + pattern.getEnemy().getMax_energy();
							results = results + pattern.getEnemy().getMax_hp();
							if(pattern.getEnemy() instanceof Boss)
							{
								results = results + 5124;			
							}
							if(pattern.getEnemy() instanceof BigEnemy)
							{
								results = results + 8362;
								results = results + pattern.getEnemy().getMissiles();
								results = results + pattern.getEnemy().getR();			
							}
							else results = results + 2615;
							results = results + pattern.getEnemy().getDy();
							results = results + pattern.getEnemy().getDx();
							results = results + pattern.getEnemy().getX();
							results = results + pattern.getEnemy().getY();
						}						
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		results = results / 183;		
		
		return results;
	}
	public static String enemyToString(Enemy enemy)
	{
		String results = "";
		//if(enemy == null) return "";
		if(enemy instanceof HyperBoss)
		{
			results = results + "type" + Reflexioner.DELIM_ENEMY + "boss_3" + "\n";
			results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
			results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
			results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
			results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
			results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
		}
		else if(enemy instanceof ExtremeBoss)
		{
			results = results + "type" + Reflexioner.DELIM_ENEMY + "boss_2" + "\n";
			results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
			results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
			results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
			results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
			results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
		}
		else if(enemy instanceof Boss)
		{
			results = results + "type" + Reflexioner.DELIM_ENEMY + "boss" + "\n";
			results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
			results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
			results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
			results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
			results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
		}
		else if(enemy instanceof BigEnemy)
		{
			results = results + "type" + Reflexioner.DELIM_ENEMY + "bigenemy" + "\n";
			results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((BigEnemy) enemy).isGuide()) + "\n";
			results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((BigEnemy) enemy).getMissiles()) + "\n";
		}
		results = results + "hp" + Reflexioner.DELIM_ENEMY + enemy.getMax_hp() + "\n";
		results = results + "size" + Reflexioner.DELIM_ENEMY + enemy.getR() + "\n";
		results = results + "speed_x" + Reflexioner.DELIM_ENEMY + enemy.getDx() + "\n";
		results = results + "speed_y" + Reflexioner.DELIM_ENEMY + enemy.getDy() + "\n";
		results = results + "firedelay" + Reflexioner.DELIM_ENEMY + enemy.getMax_energy() + "\n";
		results = results + "damage" + Reflexioner.DELIM_ENEMY + enemy.getDamage() + "\n";		
		
		return results;
	}
	public static Enemy stringToEnemy(String str)
	{
		Enemy enemy = new Enemy();
		enemy.setColor(Reflexioner.color_enemy);
		enemy.setX(Reflexioner.getSize_x());
		enemy.setY(10);
		enemy.setR(Reflexioner.getEnemy_r());
		enemy.setColor(Reflexioner.color_enemy);
		enemy.setHp(500);
		enemy.setMax_hp(500);
		enemy.setDamage((enemy.getHp() / 10));
		
		StringTokenizer stoken = new StringTokenizer(str, "\n");
		StringTokenizer keyToken;
		String lines = "", key = "", target = "";
		while(stoken.hasMoreTokens())
		{
			lines = stoken.nextToken().trim();
			if(lines.startsWith("#")) continue;
			keyToken = new StringTokenizer(lines, Reflexioner.DELIM_ENEMY);
			try
			{
				key = keyToken.nextToken();				
				target = keyToken.nextToken();
				if(key.equalsIgnoreCase("type"))
				{
					if(target.equalsIgnoreCase("boss_3"))
					{
						enemy = new HyperBoss();
						enemy.setColor(Reflexioner.color_bigenemy);
						enemy.setX(Reflexioner.getSize_x());
						enemy.setY(10);
						enemy.setR(Reflexioner.getEnemy_r() * 2);
						enemy.setColor(Reflexioner.color_enemy);
						enemy.setHp(5000);
						enemy.setMax_hp(5000);
						enemy.setDamage((enemy.getHp() / 10));
						((Boss) enemy).setBeam_energy(300);
						((Boss) enemy).setRatio(0.9);
					}
					else if(target.equalsIgnoreCase("boss_2"))
					{
						enemy = new ExtremeBoss();
						enemy.setColor(Reflexioner.color_bigenemy);
						enemy.setX(Reflexioner.getSize_x());
						enemy.setY(10);
						enemy.setR(Reflexioner.getEnemy_r() * 2);
						enemy.setColor(Reflexioner.color_enemy);
						enemy.setHp(5000);
						enemy.setMax_hp(5000);
						enemy.setDamage((enemy.getHp() / 10));
						((Boss) enemy).setBeam_energy(300);
						((Boss) enemy).setRatio(0.9);
					}
					else if(target.equalsIgnoreCase("boss"))
					{
						enemy = new Boss();
						enemy.setColor(Reflexioner.color_bigenemy);
						enemy.setX(Reflexioner.getSize_x());
						enemy.setY(10);
						enemy.setR(Reflexioner.getEnemy_r() * 2);
						enemy.setColor(Reflexioner.color_enemy);
						enemy.setHp(5000);
						enemy.setMax_hp(5000);
						enemy.setDamage((enemy.getHp() / 10));
						((Boss) enemy).setBeam_energy(300);
						((Boss) enemy).setRatio(0.9);
					}
					else if(target.equalsIgnoreCase("bigenemy"))
					{
						enemy = new BigEnemy();
						enemy.setColor(Reflexioner.color_bigenemy);
						enemy.setX(Reflexioner.getSize_x());
						enemy.setY(10);
						enemy.setR(Reflexioner.getEnemy_r());
						enemy.setColor(Reflexioner.color_enemy);
						enemy.setHp(500);
						enemy.setMax_hp(500);
						enemy.setDamage((enemy.getHp() / 10));
					}
				}
				else if(key.equalsIgnoreCase("hp"))
				{
					enemy.setMax_hp(Long.parseLong(target));
					enemy.setHp(Long.parseLong(target));
				}
				else if(key.equalsIgnoreCase("size"))
				{
					enemy.setR(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("speed_x"))
				{
					enemy.setDx(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("speed_y"))
				{
					enemy.setDy(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("firedelay"))
				{
					enemy.setMax_energy(Long.parseLong(target));
					enemy.setEnergy(Long.parseLong(target));
				}
				else if(key.equalsIgnoreCase("damage"))
				{
					enemy.setDamage(Long.parseLong(target));
				}
				else if(key.equalsIgnoreCase("guide"))
				{
					((BigEnemy) enemy).setGuide(Boolean.parseBoolean(target));
				}
				else if(key.equalsIgnoreCase("missiles"))
				{
					((BigEnemy) enemy).setMissiles(Integer.parseInt(target));
				}
				else if(key.equalsIgnoreCase("beamdelay"))
				{
					((Boss) enemy).setBeam_std(Integer.parseInt(target));
					((Boss) enemy).setBeam_energy(0);
				}
				else if(key.equalsIgnoreCase("attack_ratio"))
				{
					((Boss) enemy).setRatio(Double.parseDouble(target));
				}
				else if(key.equalsIgnoreCase("unique"))
				{
					((Boss) enemy).setUnique(Boolean.parseBoolean(target));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		return enemy;
	}
	public Boolean getEvent_exist()
	{
		return event_exist;
	}
	public void setEvent_exist(Boolean event_exist)
	{
		this.event_exist = event_exist;
	}
	public String getEvent_url()
	{
		return event_url;
	}
	public void setEvent_url(String event_url)
	{
		this.event_url = event_url;
	}
	public Integer getEvent_year()
	{
		return event_year;
	}
	public void setEvent_year(Integer event_year)
	{
		this.event_year = event_year;
	}
	public Integer getEvent_month()
	{
		return event_month;
	}
	public void setEvent_month(Integer event_month)
	{
		this.event_month = event_month;
	}
	public Integer getEvent_hour()
	{
		return event_hour;
	}
	public void setEvent_hour(Integer event_hour)
	{
		this.event_hour = event_hour;
	}
	public Integer getEvent_minute()
	{
		return event_minute;
	}
	public void setEvent_minute(Integer event_minute)
	{
		this.event_minute = event_minute;
	}
	public Integer getEvent_second()
	{
		return event_second;
	}
	public void setEvent_second(Integer event_second)
	{
		this.event_second = event_second;
	}
	public Integer getEvent_day()
	{
		return event_day;
	}
	public void setEvent_day(Integer event_day)
	{
		this.event_day = event_day;
	}
	public String getTrig()
	{
		return trig;
	}
	public void setTrig(String trig)
	{
		this.trig = trig;
	}
}

class EnemyPatternWithIndex implements Comparable<EnemyPatternWithIndex>, Serializable
{
	private static final long serialVersionUID = -7710486471500359792L;
	private String pattern;
	private int id;
	public EnemyPatternWithIndex()
	{
		
	}
	public EnemyPatternWithIndex(int id, String pat)
	{
		this.id = id;
		this.pattern = pat;
	}
	@Override
	public int compareTo(EnemyPatternWithIndex o)
	{
		return new Integer(this.id).compareTo(new Integer(o.id));
	}
	public String getPattern()
	{
		return pattern;
	}
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}