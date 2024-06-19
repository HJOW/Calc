package setting;

public class SaveChar extends SaveData
{
	private static final long serialVersionUID = -8569376022895086528L;
	public SaveChar()
	{
		
	}
	public SaveChar(int value)
	{
		this.value = value;
	}
	public SaveChar(char value)
	{
		this.value = (int) value;
	}
	public void setValue(char value)
	{
		this.value = (int) value;
	}
	public char charValue()
	{
		return (char) value;
	}
	@Override
	public SaveData clone()
	{
		SaveChar newOne = new SaveChar((int)value);
		return newOne;
	}

}
