package reflex;

import java.math.BigInteger;
import java.util.StringTokenizer;

import setting.Lint;

public class DReflexScenario extends CReflexScenario
{
	private static final long serialVersionUID = -3403626213822326233L;
	private Long star1_least = new Long(0);
	private BigInteger deadline;
	public DReflexScenario()
	{
		super();
	}
	public DReflexScenario(String contents)
	{
		super(contents);
		
		this.setSpaceShip_selectable(new Boolean(false));
		
		StringTokenizer stoken = new StringTokenizer(contents, "\n");
		StringTokenizer keyToken;
		String lines = "", key = "", target = "", additional = null, swap = null;
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
				
				
				if(key.equalsIgnoreCase("least_time"))
				{
					setStar1_least(new Long(target));
				}
				else if(key.equalsIgnoreCase("deadline"))
				{
					setDeadline(new BigInteger(target));
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public String stringData()
	{
		String results = super.stringData();
		if(getSpaceShip_selectable() != null)
		{
			results = results + "\n";
			results = results + "# " + "별을 획득하기 위해서는 얼마나 프레임이 지나야 하는지를 지정합니다." + "\n";
			results = results + "least_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStar1_least()) + "\n";
			if(getDeadline() != null)
			{
				results = results + "# " + "양수로 지정하면, 이 만큼의 프레임이 지나는 순간 게임이 끝납니다." + "\n";
				results = results + "deadline" + Reflexioner.DELIM_SCEN + String.valueOf(getDeadline()) + "\n";
			}
		}
		return results;
	}
	@Override
	public long authorized(int password)
	{
		long results = super.authorized(password);
		
		if(getStar1_least() != null)
		{
			results = results + (getStar1_least().longValue() / 1000);
		}
		if(getDeadline() != null)
		{
			results = results + (getDeadline().divide(Lint.big(1000)).longValue());
		}
		
		results = results - 7295;
		results = results / 7;
		
		return results;
	}
	public Long getStar1_least()
	{
		return star1_least;
	}
	public void setStar1_least(Long star1_least)
	{
		this.star1_least = star1_least;
	}
	public BigInteger getDeadline()
	{
		return deadline;
	}
	public void setDeadline(BigInteger deadline)
	{
		this.deadline = deadline;
	}
}
