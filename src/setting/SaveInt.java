package setting;

public class SaveInt extends SaveData
{
	private static final long serialVersionUID = -5365454060693891813L;
	public SaveInt()
	{
		
	}
	public SaveInt(int value)
	{
		this.value = value;
	}
	public int intValue()
	{
		return (int)value;
	}
	@Override
	public SaveData clone()
	{
		SaveInt newOne = new SaveInt((int)value);
		return newOne;
	}
	
}
