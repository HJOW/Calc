package reflex;

import java.util.StringTokenizer;

import main_classes.RunManager;

public class IReflexScenario extends BReflexScenario
{
	private static final long serialVersionUID = 50766445335031441L;
	private Long start_time;
	private Integer start_item_m, start_item_d, start_item_s, start_item_a, start_item_k, start_item_e, start_item_g;
	private String start_script = "", finish_script = "";
	public IReflexScenario()
	{
		super();
	}
	public IReflexScenario(String contents)
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
				
				
				if(key.equalsIgnoreCase("start_time"))
				{
					setStart_time(new Long(target));
				}
				else if(key.equalsIgnoreCase("start_a"))
				{
					setStart_item_a(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_d"))
				{
					setStart_item_d(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_e"))
				{
					setStart_item_e(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_g"))
				{
					setStart_item_g(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_k"))
				{
					setStart_item_k(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_m"))
				{
					setStart_item_m(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_s"))
				{
					setStart_item_s(new Integer(target));
				}
				else if(key.equalsIgnoreCase("start_script"))
				{
					if(getStart_script() == null) setStart_script("");
					setStart_script(getStart_script() + target + "\n");
				}
				else if(key.equalsIgnoreCase("finish_script"))
				{
					if(getFinish_script() == null) setFinish_script("");
					setFinish_script(getFinish_script() + target + "\n");
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
		StringTokenizer lineToken;
		String lines;
		if(getStart_time() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 얼마만큼의 프레임이 지나간 것 처럼 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_time()) + "\n";
		}
		if(getStart_item_a() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 A 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_a()) + "\n";
		}
		if(getStart_item_d() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 D 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_d()) + "\n";
		}
		if(getStart_item_e() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 E 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_e()) + "\n";
		}
		if(getStart_item_g() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 G 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_g()) + "\n";
		}
		if(getStart_item_k() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 K 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_k()) + "\n";
		}
		if(getStart_item_m() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 M 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_m()) + "\n";
		}
		if(getStart_item_s() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음부터 S 아이템을 얼마나 획득한 채로 시작할 지 지정합니다." + "\n";
			results = results + "start_time" + Reflexioner.DELIM_SCEN + String.valueOf(getStart_item_s()) + "\n";
		}
		if(getStart_script() != null)
		{
			results = results + "\n";
			results = results + "# " + "처음 시작 시 실행할 스크립트를 입력합니다." + "\n";
			results = results + "start_script" + Reflexioner.DELIM_SCEN + "" + "\n";
			lineToken = new StringTokenizer(getStart_script(), "\n");
			while(lineToken.hasMoreTokens())
			{
				lines = lineToken.nextToken();
				results = results + "start_script" + Reflexioner.DELIM_SCEN + lines + "\n";
			}
		}
		if(getFinish_script() != null)
		{
			results = results + "\n";
			results = results + "# " + "게임이 끝났을 때 실행할 스크립트를 입력합니다." + "\n";
			results = results + "finish_script" + Reflexioner.DELIM_SCEN + "" + "\n";
			lineToken = new StringTokenizer(getFinish_script(), "\n");
			while(lineToken.hasMoreTokens())
			{
				lines = lineToken.nextToken();
				results = results + "finish_script" + Reflexioner.DELIM_SCEN + lines + "\n";
			}
		}
		return results;
	}
	@Override
	public long authorized(int password)
	{
		long results = super.authorized(password);
		
		if(getAvailable_continues() != null)
			results = results + getAvailable_continues().intValue();
		
		if(getStart_item_a() != null)
			results = results + getStart_item_a().intValue();
		if(getStart_item_d() != null)
			results = results + getStart_item_d().intValue();
		if(getStart_item_e() != null)
			results = results + getStart_item_e().intValue();
		if(getStart_item_g() != null)
			results = results + getStart_item_g().intValue();
		if(getStart_item_k() != null)
			results = results + getStart_item_k().intValue();
		if(getStart_item_m() != null)
			results = results + getStart_item_m().intValue();
		if(getStart_item_s() != null)
			results = results + getStart_item_s().intValue();
		
		if(getStart_time() != null)
			results = results + (getStart_time().longValue() / 10);
		
		if(getStart_script() != null)
			results = results + RunManager.getNameCode(getStart_script());
		if(getFinish_script() != null)
			results = results + RunManager.getNameCode(getFinish_script());
		
		if(getScript() != null)
			results = results + RunManager.getNameCode(getScript());
		
		results = results - 9511;
		results = results / 7;
		
		return results;
	}
	public Long getStart_time()
	{
		return start_time;
	}
	public void setStart_time(Long start_time)
	{
		this.start_time = start_time;
	}
	public Integer getStart_item_m()
	{
		return start_item_m;
	}
	public void setStart_item_m(Integer start_item_m)
	{
		this.start_item_m = start_item_m;
	}
	public Integer getStart_item_d()
	{
		return start_item_d;
	}
	public void setStart_item_d(Integer start_item_d)
	{
		this.start_item_d = start_item_d;
	}
	public Integer getStart_item_s()
	{
		return start_item_s;
	}
	public void setStart_item_s(Integer start_item_s)
	{
		this.start_item_s = start_item_s;
	}
	public Integer getStart_item_a()
	{
		return start_item_a;
	}
	public void setStart_item_a(Integer start_item_a)
	{
		this.start_item_a = start_item_a;
	}
	public Integer getStart_item_k()
	{
		return start_item_k;
	}
	public void setStart_item_k(Integer start_item_k)
	{
		this.start_item_k = start_item_k;
	}
	public Integer getStart_item_e()
	{
		return start_item_e;
	}
	public void setStart_item_e(Integer start_item_e)
	{
		this.start_item_e = start_item_e;
	}
	public Integer getStart_item_g()
	{
		return start_item_g;
	}
	public void setStart_item_g(Integer start_item_g)
	{
		this.start_item_g = start_item_g;
	}
	public String getStart_script()
	{
		return start_script;
	}
	public void setStart_script(String start_script)
	{
		this.start_script = start_script;
	}
	public String getFinish_script()
	{
		return finish_script;
	}
	public void setFinish_script(String finish_script)
	{
		this.finish_script = finish_script;
	}
	
}
