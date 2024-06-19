package main_classes;

public class SpecialCard extends StandardCard
{
	private static final long serialVersionUID = 5644142109426017937L;
	private String script = "";
	public SpecialCard()
	{
		
	}
	public SpecialCard(String script)
	{
		this.script = new String(script);
	}
	
	public String getScript()
	{
		return script;
	}

	public void setScript(String script)
	{
		this.script = script;
	}

	@Override
	public StandardCard clone()
	{
		SpecialCard newOne = (SpecialCard) super.clone();
		
		return newOne;
	}
}
