package accumulate;

import java.io.Serializable;

public class Goods implements Serializable
{
	private static final long serialVersionUID = 152217867086327052L;
	private Integer quality = null;
	private Integer old = null;
	private Integer type = null;
	
	public transient static final int BOOK = 0;
	public transient static final int MUSIC = 1;
	public transient static final int PC = 2;
	
	public Goods()
	{
		quality = new Integer(1);
		old = new Integer(1);
	}
	public long price()
	{
		long results = 0;
		results = results + quality.intValue();
		results = results * 10;
		switch(type.intValue())
		{
			case BOOK:
				results = results * 1;
				break;
			case MUSIC:
				results = results * 1;
				break;
			case PC:
				results = results * 100;
				break;
		}
		results = (long) Math.round((double) results / (old.intValue() / 2));
		
		return results;
	}
	public int type()
	{
		return type.intValue();
	}
	public Integer getQuality()
	{
		return quality;
	}
	public void setQuality(Integer quality)
	{
		this.quality = quality;
	}
	public Integer getOld()
	{
		return old;
	}
	public void setOld(Integer old)
	{
		this.old = old;
	}
	
	public Integer getType()
	{
		return type;
	}
	public void setType(Integer type)
	{
		this.type = type;
	}
}
