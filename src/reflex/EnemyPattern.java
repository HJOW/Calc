package reflex;

import java.io.Serializable;
import java.util.StringTokenizer;

public class EnemyPattern implements Serializable
{
	private static final long serialVersionUID = 685579182641435599L;
	private Enemy enemy;
	private Long min_delay = new Long(0), max_delay = new Long(-1);
	private Double ratio = new Double(0.01), addHPRatio = new Double(0.001), addDamageRatio = new Double(0.001);
	
	
	public EnemyPattern()
	{
		
	}
	public EnemyPattern(Enemy enemy, long min, long max, double ratio)
	{
		this.enemy = enemy;
		this.min_delay = new Long(min);
		this.max_delay = new Long(max);
		this.ratio = new Double(ratio);
		this.addDamageRatio = new Double(0.0);
		this.addHPRatio = new Double(0.0);
	}
	public EnemyPattern(String str)
	{
		StringTokenizer lineToken = new StringTokenizer(str, "\n");
		StringTokenizer splToken;
		String lines, key, target;
		String enemy = "";
		while(lineToken.hasMoreTokens())
		{
			lines = lineToken.nextToken();
			splToken = new StringTokenizer(lines, Reflexioner.DELIM_ENEMY_PATTERN);
			try
			{
				key = splToken.nextToken();
				target = splToken.nextToken();
				if(key.equalsIgnoreCase("enemy"))
				{
					enemy = enemy + target + "\n";
				}
				else if(key.equalsIgnoreCase("start_appear"))
				{					
					min_delay = new Long(target);
				}
				else if(key.equalsIgnoreCase("finish_appear"))
				{
					max_delay = new Long(target);
				}
				else if(key.equalsIgnoreCase("appear_ratio"))
				{
					ratio = new Double(target);
				}
				else if(key.equalsIgnoreCase("hp_ratio"))
				{
					addHPRatio = new Double(target);
				}
				else if(key.equalsIgnoreCase("damage_ratio"))
				{
					addDamageRatio = new Double(target);
				}
			}
			catch(Exception e)
			{
				
			}
		}
		enemy = enemy.trim();
		this.enemy = AReflexScenario.stringToEnemy(enemy);
	}
	public static EnemyPattern convertPattern(String str)
	{
		if(str.startsWith("boss_pattern"))
		{
			return new BossEnemyPattern(str);
		}
		else
		{
			return new EnemyPattern(str);
		}
	}
	public String convertStr()
	{
		String results = "";
		StringTokenizer lineToken = new StringTokenizer(AReflexScenario.enemyToString(getEnemy()), "\n");
		while(lineToken.hasMoreTokens())
		{
			results = results + "enemy" + Reflexioner.DELIM_ENEMY_PATTERN + lineToken.nextToken() + "\n";
		}
		results = results + "start_appear" + Reflexioner.DELIM_ENEMY_PATTERN + min_delay.toString() + "\n";
		results = results + "finish_appear" + Reflexioner.DELIM_ENEMY_PATTERN + max_delay.toString() + "\n";
		results = results + "start_appear" + Reflexioner.DELIM_ENEMY_PATTERN + min_delay.toString() + "\n";
		results = results + "appear_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + ratio.toString() + "\n";
		results = results + "hp_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + addHPRatio.toString() + "\n";
		results = results + "damage_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + addDamageRatio.toString() + "\n";
		
		return results;
	}
	public Enemy createEnemy(String path, long difficulty)
	{
		return enemy.clone();
	}
	public Enemy getEnemy()
	{
		return enemy;
	}
	public void setEnemy(Enemy enemy)
	{
		this.enemy = enemy;
	}
	public Long getMin_delay()
	{
		return min_delay;
	}
	public void setMin_delay(Long min_delay)
	{
		this.min_delay = min_delay;
	}
	public Long getMax_delay()
	{
		return max_delay;
	}
	public void setMax_delay(Long max_delay)
	{
		this.max_delay = max_delay;
	}
	public Double getRatio()
	{
		return ratio;
	}
	public void setRatio(Double ratio)
	{
		this.ratio = ratio;
	}
	public Double getAddHPRatio()
	{
		return addHPRatio;
	}
	public void setAddHPRatio(Double addHPRatio)
	{
		this.addHPRatio = addHPRatio;
	}
	public Double getAddDamageRatio()
	{
		return addDamageRatio;
	}
	public void setAddDamageRatio(Double addDamageRatio)
	{
		this.addDamageRatio = addDamageRatio;
	}
}
