package scripting;

import java.io.Serializable;

public class HandleResult implements Serializable
{
	private static final long serialVersionUID = -5011721133841782396L;
	private Object returns = null;
	private boolean isError = false;
	private String messages = null;
	public HandleResult()
	{
		
	}
	public HandleResult(Object returns, boolean isError, String messages)
	{
		this.returns = returns;
		this.isError = isError;
		this.messages = messages;
	}
	public Object getReturns()
	{
		return returns;
	}
	public void setReturns(Object returns)
	{
		this.returns = returns;
	}
	public boolean isError()
	{
		return isError;
	}
	public void setError(boolean isError)
	{
		this.isError = isError;
	}
	public String getMessages()
	{
		return messages;
	}
	public void setMessages(String messages)
	{
		this.messages = messages;
	}
}
