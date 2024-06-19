package replay;

import java.util.Vector;

public class ControlCollector
{

	public Vector<Control> controls;
	public Replay replay;
	public ControlCollector()
	{
		controls = new Vector<Control>();
	}
	public Control[] toControlArray()
	{
		return (Control[]) controls.toArray();
	}
	public void clear()
	{
		controls.clear();
	}
}
