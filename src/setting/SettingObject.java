package setting;

import java.io.Serializable;

public class SettingObject implements Serializable, CanBeClone
{
	private static final long serialVersionUID = -4955166665698999041L;
	private String type = "";
	private String separator = "|";
	private String data = "";

	public SettingObject()
	{
		
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getSeparator()
	{
		return separator;
	}
	public void setSeparator(String separator)
	{
		this.separator = separator;
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public SettingObject clone()
	{
		SettingObject newOne = new SettingObject();
		newOne.type = new String(type);
		newOne.separator = new String(separator);
		newOne.data = new String(data);
		return newOne;
	}
}
