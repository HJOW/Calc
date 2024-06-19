package reflex;

import java.util.StringTokenizer;

public class BReflexScenario extends XReflexScenario
{
	private static final long serialVersionUID = -3169853651272810510L;
	private Integer available_continues;
	private Integer continue_type;
	
	public static final int CONTINUE_WITH_ZERO_POINT = 0;
	public static final int CONTINUE_WITH_ZERO_ITEM = 1;
	
	public BReflexScenario()
	{
		super();
		available_continues = new Integer(2);
		continue_type = new Integer(0);
	}
	public BReflexScenario(String contents)
	{
		super(contents);
		
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
				
				
				if(key.equalsIgnoreCase("continues"))
				{
					setAvailable_continues(new Integer(target));
				}
				else if(key.equalsIgnoreCase("continue_type"))
				{
					setContinue_type(new Integer(target));
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
		if(getAvailable_continues() != null)
		{
			results = results + "\n";
			results = results + "# " + "함선 체력이 파괴되었을 때 점수를 희생하고 이어서 할 수 있는 횟수 제한을 입력합니다." + "\n";
			results = results + "continues" + Reflexioner.DELIM_SCEN + String.valueOf(getAvailable_continues()) + "\n";
		}
		if(getContinue_type() != null)
		{
			results = results + "\n";
			results = results + "# " + "함선 파괴 후 이어서 할 때 희생할 대상을 지정합니다. 아직은 0만 사용할 수 있습니다." + "\n";
			results = results + "continue_type" + Reflexioner.DELIM_SCEN + String.valueOf(getContinue_type()) + "\n";
		}
		return results;
	}
	@Override
	public long authorized(int password)
	{
		long results = super.authorized(password);
		
		if(getAvailable_continues() != null)
			results = results + getAvailable_continues().intValue();
		
		results = results - 951;
		results = results / 7;
		
		return results;
	}
	public Integer getAvailable_continues()
	{
		return available_continues;
	}
	public void setAvailable_continues(Integer available_continues)
	{
		this.available_continues = available_continues;
	}
	public Integer getContinue_type()
	{
		return continue_type;
	}
	public void setContinue_type(Integer continue_type)
	{
		this.continue_type = continue_type;
	}
}
