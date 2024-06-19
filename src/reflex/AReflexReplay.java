package reflex;

import java.math.BigInteger;

import main_classes.CalcWindow;
import main_classes.RunManager;
import setting.Lint;

public class AReflexReplay extends ReflexReplay
{
	private static final long serialVersionUID = 8079446348890025626L; 
	private String authority_code = "";
	private BigInteger final_point;
	private Boolean authority_game;
	public AReflexReplay()
	{
		super();
	}
	public boolean isAuthorized()
	{
		boolean results = super.isAuthorized();
		if(! results) return false;
		if(getAuths().compareTo(auth(1937283 + 1001008)) == 0) return true;
		return false;
	}	
	public void applyAuth(long password)
	{
		setAuths(auth(password));
	}
	protected BigInteger auth(long password)
	{
		if(CalcWindow.getAuthorizedCode(password) < 1000) return Lint.big(0);
		BigInteger results = Lint.big(2603);
		if(getYear() != null) results = results.add(Lint.big(getYear().intValue()));
		if(getMonth() != null) results = results.add(Lint.big(getMonth().intValue()));
		if(getDay() != null) results = results.add(Lint.big(getDay().intValue()));
		if(getHour() != null) results = results.add(Lint.big(getHour().intValue()));
		if(getMin() != null) results = results.add(Lint.big(getMin().intValue()));
		if(getSec() != null) results = results.add(Lint.big(getSec().intValue()));
		if(getVer_m() != null) results = results.add(Lint.big(getVer_m().intValue()));
		if(getVer_s1() != null) results = results.add(Lint.big(getVer_s1().intValue()));
		if(getVer_s2() != null) results = results.add(Lint.big(getVer_s2().intValue()));
		if(getReplay_interval() != null) results = results.add(Lint.big(getReplay_interval().intValue()));
		if(getVer_nightly() != null) results = results.add(Lint.big(getVer_nightly().longValue()));
		if(getVer_test() != null) results = results.add(Lint.big((long) getVer_test().charValue()));
		if(getName() != null) results = results.add(Lint.big(RunManager.getNameCode(getName())));
		if(getScenarioName() != null) results = results.add(Lint.big(RunManager.getNameCode(getScenarioName())));
		if(getScenarioData() != null) results = results.add(Lint.big(RunManager.getNameCode(getScenarioData())));
		if(getScenes() != null)
		{
			for(ReflexSave s : getScenes())
			{
				if(s != null) results = results.add(s.getAuthority());
			}
		}
		if(getAuthority_code() != null) results = results.add(Lint.big(RunManager.getNameCode(getAuthority_code())));
		if(getFinal_point() != null) results = results.add(getFinal_point());
		if(getAuthority_game() != null) results = results.multiply(Lint.big(2));
		
		results = results.add(Lint.big(5673));
		results = results.multiply(Lint.big(623));
		return results;
	}
	public String getAuthority_code()
	{
		return authority_code;
	}
	public void setAuthority_code(String authority_code)
	{
		this.authority_code = authority_code;
	}
	public BigInteger getFinal_point()
	{
		return final_point;
	}
	public void setFinal_point(BigInteger final_point)
	{
		this.final_point = final_point;
	}
	public Boolean getAuthority_game()
	{
		return authority_game;
	}
	public void setAuthority_game(Boolean authority_game)
	{
		this.authority_game = authority_game;
	}
}
