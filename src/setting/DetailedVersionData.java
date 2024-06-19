package setting;

import java.util.StringTokenizer;

import main_classes.CalcWindow;

public class DetailedVersionData extends VersionData
{
	private static final long serialVersionUID = -3806159464521969479L;
	private Long nightly;
	public DetailedVersionData()
	{
		setV_m(new Integer(CalcWindow.version_main));
		setV_1(new Integer(CalcWindow.version_sub_1));
		setV_2(new Integer(CalcWindow.version_sub_2));
		setV_t(new Character(CalcWindow.version_test));
		setNightly(new Long(CalcWindow.version_nightly));
	}
	public DetailedVersionData(int v_m, int v_1, int v_2, char v_t)
	{
		setV_m(new Integer(v_m));
		setV_1(new Integer(v_1));
		setV_2(new Integer(v_2));
		setV_t(new Character(v_t));
		setNightly(new Long(CalcWindow.version_nightly));
	}
	public DetailedVersionData(int v_m, int v_1, int v_2, char v_t, long v_n)
	{
		setV_m(new Integer(v_m));
		setV_1(new Integer(v_1));
		setV_2(new Integer(v_2));
		setV_t(new Character(v_t));
		setNightly(new Long(v_n));
	}
	public DetailedVersionData(String str)
	{
		try
		{
			StringTokenizer numberToken = new StringTokenizer(str.trim(), " ");
			String mainVer = numberToken.nextToken();
			String nightly = numberToken.nextToken();
			String test = " ";
			if(numberToken.hasMoreTokens())
				test = numberToken.nextToken();
			setV_m(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[0]))));
			setV_1(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[1]))));
			setV_2(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[2]))));
			setNightly(new Long(nightly));
			setV_t(new Character(test.toCharArray()[0]));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			setV_m(new Integer(CalcWindow.version_main));
			setV_1(new Integer(CalcWindow.version_sub_1));
			setV_2(new Integer(CalcWindow.version_sub_2));
			setV_t(new Character(CalcWindow.version_test));
			setNightly(new Long(CalcWindow.version_nightly));
		}
		
	}
	public DetailedVersionData clone()
	{
		DetailedVersionData newOne = new DetailedVersionData(getV_m().intValue(), getV_1().intValue(), getV_2().intValue(), getV_t().charValue(), getNightly().longValue());
		return newOne;
	}
	public Long getNightly()
	{
		return nightly;
	}
	public void setNightly(Long nightly)
	{
		this.nightly = nightly;
	}
}
