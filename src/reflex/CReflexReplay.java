package reflex;

import java.math.BigInteger;

import main_classes.CalcWindow;
import main_classes.RunManager;
import setting.Lint;

public class CReflexReplay extends BReflexReplay
{
	private static final long serialVersionUID = -2274768625890121283L;
	private String password = null, password_hint = "";
	
	public CReflexReplay()
	{
		super();
	}
	public CReflexReplay(ReflexReplay rep)
	{
		super();
		setScenes(rep.getScenes());
		setYear(new Integer(rep.getYear().intValue()));
		setMonth(new Integer(rep.getMonth().intValue()));
		setDay(new Integer(rep.getDay().intValue()));
		setHour(new Integer(rep.getHour().intValue()));
		setMin(new Integer(rep.getMin().intValue()));
		setSec(new Integer(rep.getSec().intValue()));
		setVer_m(new Integer(rep.getVer_m().intValue()));
		setVer_s1(new Integer(rep.getVer_s1().intValue()));
		setVer_s2(new Integer(rep.getVer_s2().intValue()));
		setVer_nightly(new Long(rep.getVer_nightly().longValue()));
		setVer_test(new Character(rep.getVer_test().charValue()));
		setAuths(Lint.copy(rep.getAuths()));
		if(rep.getName() != null) setName(new String(rep.getName()));
		if(rep.getScenarioName() != null) setScenarioName(new String(rep.getScenarioName()));
		if(rep.getScenarioData() != null) setScenarioData(new String(rep.getScenarioData()));
		if(rep.getReplay_interval() != null) setReplay_interval(new Integer(rep.getReplay_interval().intValue()));
		if(rep instanceof AReflexReplay)
		{
			if(((AReflexReplay) rep).getAuthority_code() != null) setAuthority_code(new String(((AReflexReplay) rep).getAuthority_code()));
			if(((AReflexReplay) rep).getFinal_point() != null) setFinal_point(Lint.copy(((AReflexReplay) rep).getFinal_point()));
			if(((AReflexReplay) rep).getAuthority_game() != null) setAuthority_game(new Boolean(((AReflexReplay) rep).getAuthority_game()));
		}
		if(rep instanceof BReflexReplay)
		{
			if(((BReflexReplay) rep).getContinue_abandoned() != null) setContinue_abandoned(new Boolean(((BReflexReplay) rep).getContinue_abandoned().booleanValue()));
			if(((BReflexReplay) rep).getDescription() != null) setDescription(new String(((BReflexReplay) rep).getDescription())); 
			if(((BReflexReplay) rep).getReplayTitle() != null) setReplayTitle(new String(((BReflexReplay) rep).getReplayTitle()));
		}
		if(rep instanceof CReflexReplay)
		{
			if(((CReflexReplay) rep).getPassword() != null) setPassword(new String(((CReflexReplay) rep).getPassword()));
			if(((CReflexReplay) rep).getPassword_hint() != null) setPassword(new String(((CReflexReplay) rep).getPassword_hint()));			
		}
	}
	protected BigInteger auth(long password)
	{
		if(CalcWindow.getAuthorizedCode(password) < 1000) return Lint.big(0);
		BigInteger results = super.auth(password);
		if(getPassword_hint() != null)
		{
			results = results.add(Lint.big(RunManager.getNameCode(getPassword_hint())));
		}
		if(getPassword() != null)
		{
			results = results.add(Lint.big(RunManager.getNameCode(getPassword())));
		}
		results = results.add(Lint.big(6271));
		results = results.divide(Lint.big(17));
		return results;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword_hint()
	{
		return password_hint;
	}
	public void setPassword_hint(String password_hint)
	{
		this.password_hint = password_hint;
	}
}
