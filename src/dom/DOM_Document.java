package dom;

import java.awt.Component;
import java.util.Date;

import scripting.ScriptActor;

public class DOM_Document
{
	public String title = "";
	public String location = "";
	public String referer = "";
	public String cookie = "";
	public Object bgColor;
	public Object fgColor;
	public Object alinkColor;
	public Object vlinkColor;
	public Object linkColor;
	public Object[] anchors;
	public Object[] forms;
	public Object[] links;
	public String domain = "";
	public Object[] applets;
	public Object area;
	public Object[] embeds;
	public Object[] images;
	public Object[] layers;
	
	public Date lastModified;
	public String URL = "";
	
	
	private ScriptActor actor;
	public DOM_Document(ScriptActor actor, Component dialog)
	{
		this.actor = actor;
	}
	public void write(String str)
	{
		actor.message(str);
	}
	public void writeln(String str)
	{
		actor.message(str);
	}
	public void open()
	{
		
	}
	public void close()
	{
		
	}
	public void clear()
	{
		
	}
	public double eval(String str)
	{
		return Double.parseDouble(str);
	}
	public String toString()
	{
		return "";
	}
	public String valueOf()
	{
		return "";
	}
	public String getSelection(double x, double y, double w, double h)
	{
		return "";
	}
	public void captureEvent()
	{
		
	}
	public void releaseEvent()
	{
		
	}
	public Object routeEvent()
	{
		return null;
	}
	public String toSource()
	{
		return "";
	}
}
