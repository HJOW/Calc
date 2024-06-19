package scripting;

import java.io.Serializable;

public class ScriptModule implements Serializable, Cloneable
{
	private static final long serialVersionUID = 3740441405545151824L;
	private String name = "";
	private String description = "";
	private String scripts = "";
	private Long authorized;
	public String getScripts()
	{
		return scripts;
	}
	public void setScripts(String scripts)
	{
		this.scripts = scripts;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Long getAuthorized()
	{
		return authorized;
	}
	public void setAuthorized(Long authorized)
	{
		this.authorized = authorized;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public ScriptModule clone()
	{
		ScriptModule newOne = new ScriptModule();
		newOne.name = new String(name);
		newOne.description = new String(description);
		newOne.scripts = new String(scripts);
		newOne.authorized = new Long(authorized.longValue());
		return newOne;
	}
}
