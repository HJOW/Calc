package accumulate;

import java.io.Serializable;

public class Section implements Serializable
{
	private static final long serialVersionUID = 1742088582852663312L;
	public transient boolean worked = false;
	private String name = "";
	public Section()
	{
		
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int power()
	{
		return 0;
	}
}
