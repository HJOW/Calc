package reflex;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import setting.Lint;
import main_classes.CalcWindow;

public class ReflexReplay implements Serializable
{
	private static final long serialVersionUID = 2379574302155122416L;
	private List<ReflexSave> scenes;
	private Integer year, month, day, hour, min, sec;
	private Integer ver_m, ver_s1, ver_s2;
	private Long ver_nightly;
	private Character ver_test;
	private BigInteger auths;
	private String name = "";
	private String scenarioName = null;
	private String scenarioData = null;
	private Integer replay_interval;
	public ReflexReplay()
	{
		scenes = new Vector<ReflexSave>();
		Calendar cal = Calendar.getInstance();
		year = new Integer(cal.get(Calendar.YEAR));
		month = new Integer(cal.get(Calendar.MONTH) + 1);
		day = new Integer(cal.get(Calendar.DAY_OF_MONTH));
		hour = new Integer(cal.get(Calendar.HOUR_OF_DAY));
		min = new Integer(cal.get(Calendar.MINUTE));
		sec = new Integer(cal.get(Calendar.SECOND));
		ver_m = new Integer(CalcWindow.version_main);
		ver_s1 = new Integer(CalcWindow.version_sub_1);
		ver_s2 = new Integer(CalcWindow.version_sub_2);
		ver_test = new Character(CalcWindow.version_test);
		ver_nightly = new Long(CalcWindow.version_nightly);
		auths = Lint.big(0);
	}
	public boolean isAuthorized()
	{
		for(int i=0; i<getScenes().size(); i++)
		{
			if(! getScenes().get(i).accept())
			{
				return false;
			}
		}
		return true;
		//return (auth(2938291).compareTo(auths) == 0);
	}
	public void applyAuth(long password)
	{
		setAuths(auth(password));
	}
	protected BigInteger auth(long password)
	{
		if(CalcWindow.getAuthorizedCode(password) < 1000) return Lint.big(0);
		
		return Lint.big(1);
	}
	
	public void addScene(ReflexSave scene)
	{
		scenes.add(scene);
	}
	public List<ReflexSave> getScenes()
	{
		return scenes;
	}
	public void setScenes(List<ReflexSave> scenes)
	{
		this.scenes = scenes;
	}
	public Integer getYear()
	{
		return year;
	}
	public void setYear(Integer year)
	{
		this.year = year;
	}
	public Integer getMonth()
	{
		return month;
	}
	public void setMonth(Integer month)
	{
		this.month = month;
	}
	public Integer getDay()
	{
		return day;
	}
	public void setDay(Integer day)
	{
		this.day = day;
	}
	public Integer getHour()
	{
		return hour;
	}
	public void setHour(Integer hour)
	{
		this.hour = hour;
	}
	public Integer getMin()
	{
		return min;
	}
	public void setMin(Integer min)
	{
		this.min = min;
	}
	public Integer getSec()
	{
		return sec;
	}
	public void setSec(Integer sec)
	{
		this.sec = sec;
	}
	public Integer getVer_m()
	{
		return ver_m;
	}
	public void setVer_m(Integer ver_m)
	{
		this.ver_m = ver_m;
	}
	public Integer getVer_s1()
	{
		return ver_s1;
	}
	public void setVer_s1(Integer ver_s1)
	{
		this.ver_s1 = ver_s1;
	}
	public Integer getVer_s2()
	{
		return ver_s2;
	}
	public void setVer_s2(Integer ver_s2)
	{
		this.ver_s2 = ver_s2;
	}
	public Long getVer_nightly()
	{
		return ver_nightly;
	}
	public void setVer_nightly(Long ver_nightly)
	{
		this.ver_nightly = ver_nightly;
	}
	public Character getVer_test()
	{
		return ver_test;
	}
	public void setVer_test(Character ver_test)
	{
		this.ver_test = ver_test;
	}
	public BigInteger getAuths()
	{
		return auths;
	}
	public void setAuths(BigInteger auths)
	{
		this.auths = auths;
	}
	public String getScenarioName()
	{
		return scenarioName;
	}
	public void setScenarioName(String scenarioName)
	{
		this.scenarioName = scenarioName;
	}
	public String getScenarioData()
	{
		return scenarioData;
	}
	public void setScenarioData(String scenarioData)
	{
		this.scenarioData = scenarioData;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getReplay_interval()
	{
		return replay_interval;
	}
	public void setReplay_interval(Integer replay_interval)
	{
		this.replay_interval = replay_interval;
	}
}
