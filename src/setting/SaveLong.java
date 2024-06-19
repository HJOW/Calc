package setting;

public class SaveLong extends SaveData
{
	private static final long serialVersionUID = -5365454060693891813L;
	public SaveLong()
	{
		
	}
	public SaveLong(int value)
	{
		this.value = value;
	}
	public SaveLong(long value)
	{
		this.value = value;
	}
	public long longValue()
	{
		return value;
	}
	@Override
	public SaveData clone()
	{
		SaveLong newOne = new SaveLong(value);
		return newOne;
	}
	
}
