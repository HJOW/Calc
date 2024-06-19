package setting;

import java.io.Serializable;

public class StringWithInt implements Serializable
{
	private static final long serialVersionUID = 3511665895428776256L;
	private String str;
	private int num;
	public StringWithInt()
	{
		
	}
	public StringWithInt(String str, int num)
	{
		this.str = str;
		this.num = num;
	}
	public String getStr()
	{
		return str;
	}
	public void setStr(String str)
	{
		this.str = str;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
}
