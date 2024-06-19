package setting;

public class SaveBoolean extends SaveData
{	
	private static final long serialVersionUID = 556533616875066522L;
	public SaveBoolean()
	{
		
	}
	public SaveBoolean(boolean value)
	{
		if(value) this.value = 1;
		else this.value = 0;
	}
	public void setValue(boolean value)
	{
		if(value) this.value = 1;
		else this.value = 0;
	}
	public SaveBoolean(int value)
	{
		this.value = value;
	}
	public boolean booleanValue()
	{
		if(value == 0) return false;
		else return true;
	}
	public SaveData clone()
	{
		SaveBoolean newOne = new SaveBoolean((int)value);
		return newOne;
	}
}
