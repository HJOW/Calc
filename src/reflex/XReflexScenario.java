package reflex;

import java.math.BigInteger;
import java.util.StringTokenizer;

import setting.Lint;

public class XReflexScenario extends AReflexScenario
{
	private static final long serialVersionUID = -7896142909608969625L;
	private String script = "";
	private BigInteger star4 = null, star5 = null;
	public XReflexScenario()
	{
		super();
		star4 = Lint.big(getStar3().longValue()).multiply(Lint.big(100));
		star5 = star4.multiply(Lint.big(100));
	}
	public XReflexScenario(String contents)
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
				
				
				if(key.equalsIgnoreCase("script"))
				{
					setScript(getScript() + target + "\n");
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
					else if(Integer.parseInt(additional) == 4)
					{
						setStar4(Lint.big(target));
					}
					else if(Integer.parseInt(additional) == 5)
					{
						setStar5(Lint.big(target));
					}
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
		if(getScript() != null)
		{
			results = results + "\n";
			results = results + "# " + "매 프레임마다 실행될 스크립트를 입력합니다." + "\n";
			results = results + "script" + Reflexioner.DELIM_SCEN + "" + "\n";
			StringTokenizer lineToken = new StringTokenizer(getScript(), "\n");
			while(lineToken.hasMoreTokens())
			{
				results = results + "script" + Reflexioner.DELIM_SCEN + lineToken.nextToken() + "\n";
			}
		}
		return results;
	}
	@Override
	public long authorized(int password)
	{
		long results = super.authorized(password);
		char[] scriptChar = script.toCharArray();
		for(int i=0; i<scriptChar.length; i++)
		{
			results = results + (int) scriptChar[i];
		}
		results = results + 395;
		results = results / 5;
		results = results + getRandomCode().longValue() / 20;
		results = results * 3;
		return results;
	}
	public String getScript()
	{
		return script;
	}
	public void setScript(String script)
	{
		this.script = script;
	}
	public BigInteger getStar4()
	{
		return star4;
	}
	public void setStar4(BigInteger star4)
	{
		this.star4 = star4;
	}
	public BigInteger getStar5()
	{
		return star5;
	}
	public void setStar5(BigInteger star5)
	{
		this.star5 = star5;
	}
}
