package reflex;

import java.util.StringTokenizer;

public class CReflexScenario extends IReflexScenario
{
	private static final long serialVersionUID = 4976140325318089810L;
	private Boolean spaceShip_selectable;
	public CReflexScenario()
	{
		super();
		this.setSpaceShip_selectable(new Boolean(false));
	}
	public CReflexScenario(String contents)
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
				
				
				if(key.equalsIgnoreCase("ship_selectable"))
				{
					setSpaceShip_selectable(new Boolean(target));
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
			results = results + "# " + "게임 시작 전 함선을 선택할 수 있게 합니다." + "\n";
			results = results + "ship_selectable" + Reflexioner.DELIM_SCEN + String.valueOf(getSpaceShip_selectable()) + "\n";
		}
		return results;
	}
	@Override
	public long authorized(int password)
	{
		long results = super.authorized(password);
		
		if(getSpaceShip_selectable() != null)
		{
			if(getSpaceShip_selectable().booleanValue())
				results = results + 14;
			else
				results = results + 11;
		}
		
		
		
		results = results - 6934;
		results = results / 7;
		
		return results;
	}
	public Boolean getSpaceShip_selectable()
	{
		return spaceShip_selectable;
	}
	public void setSpaceShip_selectable(Boolean spaceShip_selectable)
	{
		this.spaceShip_selectable = spaceShip_selectable;
	}
}
