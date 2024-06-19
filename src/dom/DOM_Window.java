package dom;

import java.awt.Component;
import javax.swing.JOptionPane;

import reflex.Reflexioner;
import scripting.ScriptActor;

public class DOM_Window
{
	public String status = "";
	public String defaultStatus = "";
	public int length = 1;
	public String name = "";
	public DOM_Window self = this;
	public DOM_Window window = this;
	public DOM_Window parent = this;
	public DOM_Window top = this;
	public DOM_Window opener = null;
	public DOM_Document document = null;
	public Object[] frames = null;
	public DOM_History[] history = null;
	public DOM_Location location = null;
	public boolean closed = false;
	public Object menubar = null;
	public int innerHeight = Reflexioner.getSize_y(), innerWidth = Reflexioner.getSize_x();
	public int outerHeight = Reflexioner.getSize_y(), outerWidth = Reflexioner.getSize_x();
	public int pageXOffset = 0, pageYOffset = 0;
	public Object scrollbar = null;
	public Object statusbar = null;
	public Object toolbar = null;
		
	protected ScriptActor actor;
	protected Component dialog;
	protected long timeouts;
	protected boolean timeout_working = false;
	public DOM_Window(ScriptActor actor, Component dialog)
	{
		this.actor = actor;
		this.dialog = dialog;
		document = new DOM_Document(actor, dialog);
		location = new DOM_Location(actor, dialog);
	}
	public void alert(Object ob)
	{
		JOptionPane.showMessageDialog(dialog, ob);
	}
	public String prompt(Object ob)
	{
		return JOptionPane.showInputDialog(dialog, ob);
	}
	public boolean confirm(Object ob)
	{
		return JOptionPane.showConfirmDialog(dialog, ob, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
	public double eval(String str)
	{
		return Double.parseDouble(str);
	}
	public void blur()
	{
		
	}
	public void focus()
	{
		
	}
	public void scroll()
	{
		
	}
	public Object valueOf()
	{
		return null;
	}
	public void back()
	{
		
	}
	public void forward()
	{
		
	}
	public void home()
	{
		
	}
	public void moveby()
	{
		
	}
	public boolean find(String str)
	{
		return false;
	}	
	public void open(String str)
	{
		
	}
	public void stop()
	{
		
	}
	public void captureEvents()
	{
		
	}
	public void setInterval()
	{
		
	}
	public void clearInterval()
	{
		
	}
	public void handleEvent()
	{
		
	}
	public void releaseEvent()
	{
		
	}
	public void routeEvent()
	{
		
	}	
	public void print()
	{
		
	}
	public void moveto(double x, double y)
	{
		dialog.setLocation((int)x, (int)y);
	}
	public void resizeby(double w, double h)
	{
		dialog.setSize((int)w, (int)h);
	}
	public void resizeto(double w, double h)
	{
		dialog.setSize((int)w, (int)h);
	}
	public void scrollby(double w, double h)
	{
		
	}
	public void scrollto(double w, double h)
	{
		
	}
	public void setTimeout(double t)
	{
		timeouts = (int)(t);
		timeout_working = true;
	}
	public void clearTimeout()
	{
		timeout_working = false;
	}
	public String toSource()
	{
		return "";
	}
}
