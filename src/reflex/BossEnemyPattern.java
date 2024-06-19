package reflex;

import java.util.StringTokenizer;

public class BossEnemyPattern extends EnemyPattern
{
	private static final long serialVersionUID = 901997232138099521L;
	private Boolean unique = new Boolean(false);
	public BossEnemyPattern()
	{
		super();
		setEnemy(null);
	}
	public BossEnemyPattern(long min, long max, double ratio, boolean unique)
	{
		setEnemy(null);
		setMin_delay(new Long(min));
		setMax_delay(new Long(max));
		ratio = new Double(ratio);
		setAddDamageRatio(new Double(0.0));
		setAddHPRatio(new Double(0.0));
		setUnique(new Boolean(unique));
	}
	public BossEnemyPattern(String str)
	{
		StringTokenizer lineToken = new StringTokenizer(str, "\n");
		StringTokenizer splToken;
		String lines, key, target;
		while(lineToken.hasMoreTokens())
		{
			lines = lineToken.nextToken();
			splToken = new StringTokenizer(lines, Reflexioner.DELIM_ENEMY_PATTERN);
			try
			{
				key = splToken.nextToken();
				target = splToken.nextToken();
				if(key.equalsIgnoreCase("start_appear"))
				{					
					setMin_delay(new Long(target));
				}
				else if(key.equalsIgnoreCase("finish_appear"))
				{
					setMax_delay(new Long(target));
				}
				else if(key.equalsIgnoreCase("appear_ratio"))
				{
					setRatio(new Double(target));
				}
				else if(key.equalsIgnoreCase("hp_ratio"))
				{
					setAddHPRatio(new Double(target));
				}
				else if(key.equalsIgnoreCase("damage_ratio"))
				{
					setAddDamageRatio(new Double(target));
				}
				else if(key.equalsIgnoreCase("unique"))
				{
					setUnique(new Boolean(target));
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	public String convertStr()
	{
		String results = "";
		
		results = results + "boss_pattern" + Reflexioner.DELIM_ENEMY_PATTERN + "\n";
		results = results + "unique" + Reflexioner.DELIM_ENEMY_PATTERN + getUnique().toString() + "\n";
		results = results + "start_appear" + Reflexioner.DELIM_ENEMY_PATTERN + getMin_delay().toString() + "\n";
		results = results + "finish_appear" + Reflexioner.DELIM_ENEMY_PATTERN + getMax_delay().toString() + "\n";
		results = results + "start_appear" + Reflexioner.DELIM_ENEMY_PATTERN + getMin_delay().toString() + "\n";
		results = results + "appear_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + getRatio().toString() + "\n";
		results = results + "hp_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + getAddHPRatio().toString() + "\n";
		results = results + "damage_ratio" + Reflexioner.DELIM_ENEMY_PATTERN + getAddDamageRatio().toString() + "\n";
		
		return results;
	}
	@Override
	public Enemy createEnemy(String path, long difficulty)
	{
		return Boss.getNewInstance(path, difficulty, unique.booleanValue());
	}
	public Boolean getUnique()
	{
		return unique;
	}
	public void setUnique(Boolean unique)
	{
		this.unique = unique;
	}
	@Override
	public Enemy getEnemy()
	{
		return Boss.getNewInstance(Reflexioner.getFile_path(), 5000, unique.booleanValue());
	}
}
