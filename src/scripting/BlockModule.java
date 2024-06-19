package scripting;

public class BlockModule extends ScriptModule
{
	private static final long serialVersionUID = 8942733421979483250L;
	private String turnScript = "";
	private String actScript = "";
	private Boolean useActScript = new Boolean(false);
	private Boolean actAfterDefault = new Boolean(true);
	private String labelText = "";
	public String getTurnScript()
	{
		return turnScript;
	}
	public void setTurnScript(String turnScript)
	{
		this.turnScript = turnScript;
	}
	public String getActScript()
	{
		return actScript;
	}
	public void setActScript(String actScript)
	{
		this.actScript = actScript;
	}
	public String getLabelText()
	{
		return labelText;
	}
	public void setLabelText(String labelText)
	{
		this.labelText = labelText;
	}
	public void setUseActScript(Boolean useActScript)
	{
		this.useActScript = useActScript;
	}
	public Boolean getActAfterDefault()
	{
		return actAfterDefault;
	}
	public void setActAfterDefault(Boolean actAfterDefault)
	{
		this.actAfterDefault = actAfterDefault;
	}
	public Boolean getUseActScript()
	{
		return useActScript;
	}
	@Override
	public ScriptModule clone()
	{
		BlockModule newOne = new BlockModule();
		newOne.setName(new String(getName()));
		newOne.setDescription(new String(getDescription()));
		newOne.setScripts(new String(getScripts()));
		newOne.setAuthorized(new Long(getAuthorized().longValue()));
		newOne.turnScript = new String(turnScript);
		newOne.actScript = new String(actScript);
		newOne.labelText = new String(labelText);
		newOne.useActScript = new Boolean(useActScript.booleanValue());
		newOne.actAfterDefault = new Boolean(actAfterDefault.booleanValue());
		return newOne;
	}
}
