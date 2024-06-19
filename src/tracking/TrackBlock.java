package tracking;

import java.io.Serializable;

public class TrackBlock implements Serializable, Cloneable
{
	private static final long serialVersionUID = 49091903159636194L;
	private String className = "";
	private int line_number = 0;
	private String message = "";
	private boolean problem = false;

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}
	public TrackBlock clone()
	{
		TrackBlock newOne = new TrackBlock();
		newOne.className = new String(className);
		newOne.line_number = line_number;
		newOne.message = new String(message);
		newOne.problem = this.problem;
		return newOne;
	}

	public int getLine_number()
	{
		return line_number;
	}

	public void setLine_number(int line_number)
	{
		this.line_number = line_number;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public boolean isProblem()
	{
		return problem;
	}
	
	public boolean getProblem()
	{
		return problem;
	}
	public void setProblem(boolean problem)
	{
		this.problem = problem;
	}
}
