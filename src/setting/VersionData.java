package setting;

import java.io.Serializable;

import main_classes.CalcWindow;

public class VersionData implements Serializable
{
	private static final long serialVersionUID = -144348653263971863L;
	private Integer v_m, v_1, v_2;
	private Character v_t;
	public VersionData()
	{
		v_m = new Integer(CalcWindow.version_main);
		v_1 = new Integer(CalcWindow.version_sub_1);
		v_2 = new Integer(CalcWindow.version_sub_2);
		v_t = new Character(CalcWindow.version_test);
	}
	public VersionData(int v_m, int v_1, int v_2, char v_t)
	{
		this.v_m = new Integer(v_m);
		this.v_1 = new Integer(v_1);
		this.v_2 = new Integer(v_2);
		this.v_t = new Character(v_t);
	}
	public VersionData clone()
	{
		VersionData newOne = new VersionData(v_m.intValue(), v_1.intValue(), v_2.intValue(), v_t.charValue());
		return newOne;
	}
	public int v_m()
	{
		return v_m.intValue();
	}
	public int v_1()
	{
		return v_1.intValue();
	}
	public int v_2()
	{
		return v_2.intValue();
	}
	public char v_t()
	{
		return v_t.charValue();
	}
	public Integer getV_m()
	{
		return v_m;
	}
	public void setV_m(Integer v_m)
	{
		this.v_m = v_m;
	}
	public Integer getV_1()
	{
		return v_1;
	}
	public void setV_1(Integer v_1)
	{
		this.v_1 = v_1;
	}
	public Integer getV_2()
	{
		return v_2;
	}
	public void setV_2(Integer v_2)
	{
		this.v_2 = v_2;
	}
	public Character getV_t()
	{
		return v_t;
	}
	public void setV_t(Character v_t)
	{
		this.v_t = v_t;
	}
}
