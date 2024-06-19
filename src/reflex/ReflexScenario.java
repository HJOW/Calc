package reflex;

import java.io.Serializable;

public class ReflexScenario implements Serializable, Comparable<ReflexScenario>
{
	private static final long serialVersionUID = 872068919420124788L;
	private String name, description, koreanDescription;
	private String spaceShip;
	private Integer difficulty, enemy_limit, grade_limit;
	private Long diff_delay, auth;
	private EnemyPattern[] patterns;
	private EnemyPattern defaultPattern;
	public ReflexScenario()
	{
		name = "";
		description = "";
		koreanDescription = "";
		spaceShip = "flex";
		difficulty = new Integer(1);
		enemy_limit = new Integer(20);
		grade_limit = new Integer(0);
		diff_delay = new Long(5000);
		auth = new Long(0);		
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSpaceShip()
	{
		return spaceShip;
	}
	public void setSpaceShip(String spaceShip)
	{
		this.spaceShip = spaceShip;
	}
	public Integer getDifficulty()
	{
		return difficulty;
	}
	public void setDifficulty(Integer difficulty)
	{
		this.difficulty = difficulty;
	}
	public Long getDiff_delay()
	{
		return diff_delay;
	}
	public void setDiff_delay(Long diff_delay)
	{
		this.diff_delay = diff_delay;
	}
	public Long getAuth()
	{
		return auth;
	}
	public void setAuth(Long auth)
	{
		this.auth = auth;
	}

	public EnemyPattern[] getPatterns()
	{
		return patterns;
	}

	public void setPatterns(EnemyPattern[] patterns)
	{
		this.patterns = patterns;
	}
	
	public long authorized(int password)
	{
		long results = 0;
		for(char c : name.toCharArray())
		{
			results = results + (long) c;
		}
		for(char c : description.toCharArray())
		{
			results = results + (long) c;
		}
		for(char c : koreanDescription.toCharArray())
		{
			results = results + (long) c;
		}
		for(char c : spaceShip.toCharArray())
		{
			results = results + (long) c;
		}
		results = results + difficulty.intValue();
		results = results - diff_delay.longValue();
		results = results * enemy_limit.intValue();
		results = results / (grade_limit.intValue() + 6);
		results = (long) (results + (defaultPattern.getAddDamageRatio().longValue() * 1026));
		results = (long) (results + (defaultPattern.getAddHPRatio().longValue() * 2732));
		results = (long) (results + (defaultPattern.getAddHPRatio() * 2183));
		results = (long) (results + (long) ((defaultPattern.getMax_delay().longValue() + defaultPattern.getMin_delay().longValue()) * defaultPattern.getRatio().doubleValue()));
		results = results + defaultPattern.getEnemy().getDamage();
		results = results + defaultPattern.getEnemy().getMissiles();
		results = results + defaultPattern.getEnemy().getMax_energy();
		results = results + defaultPattern.getEnemy().getMax_hp();
		if(defaultPattern.getEnemy() instanceof Boss)
		{
			results = results + 5124;			
		}
		if(defaultPattern.getEnemy() instanceof BigEnemy)
		{
			results = results + 8362;
			results = results + defaultPattern.getEnemy().getMissiles();
			results = results + defaultPattern.getEnemy().getR();			
		}
		else results = results + 2615;
		results = results + defaultPattern.getEnemy().getDy();
		results = results + defaultPattern.getEnemy().getDx();
		results = results + defaultPattern.getEnemy().getX();
		results = results + defaultPattern.getEnemy().getY();
		
		results = results / 183;		
		
		return results;
	}
	public boolean isAuthorized()
	{
		if(auth == null) return false;
		try
		{
			return (auth.longValue() == authorized(1937283 + 1001008));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}		
	}

	public EnemyPattern getDefaultPattern()
	{
		return defaultPattern;
	}

	public void setDefaultPattern(EnemyPattern defaultPattern)
	{
		this.defaultPattern = defaultPattern;
	}

	public Integer getEnemy_limit()
	{
		return enemy_limit;
	}

	public void setEnemy_limit(Integer enemy_limit)
	{
		this.enemy_limit = enemy_limit;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getKoreanDescription()
	{
		return koreanDescription;
	}
	public void setKoreanDescription(String koreanDescription)
	{
		this.koreanDescription = koreanDescription;
	}
	public Integer getGrade_limit()
	{
		return grade_limit;
	}
	public void setGrade_limit(Integer grade_limit)
	{
		this.grade_limit = grade_limit;
	}
	public int compareTo(ReflexScenario scen)
	{
		return getName().compareTo(scen.getName());
	}
}
