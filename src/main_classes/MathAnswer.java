package main_classes;

import java.io.Serializable;

public abstract class MathAnswer implements Serializable
{
	private static final long serialVersionUID = 5269116551440323769L;
	private String[] hints;

	public MathAnswer()
	{
		init();
	}
	protected void init()
	{
		hints = new String[10];
		for(int i=0; i<hints.length; i++)
		{
			hints[i] = "";
		}
	}
	public abstract String stringValue();
	public abstract boolean isCorrect(Object ob);
	public String hint(int i)
	{
		if(hints == null) return null;
		else if(i >= hints.length) return null;
		else return hints[i];
	}
	public int hintCount()
	{
		if(hints == null) return 0;
		else return hints.length;
	}
	public String[] getHints()
	{
		return hints;
	}
	public void setHints(String[] hints)
	{
		this.hints = hints;
	}
	public void setHint(String str, int i)
	{
		hints[i] = str;
	}
}
