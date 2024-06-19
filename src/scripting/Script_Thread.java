package scripting;

import main_classes.ThreadControl;

public class Script_Thread implements ThreadControl
{
	private boolean threadSwitch = true;
	private String script = "";
	private ScriptActor manager;
	private Thread thread;
	public Script_Thread(ScriptActor manager, String script)
	{
		this.manager = manager;
		this.script = script;
		thread = new Thread(this);
	}
	public void start()
	{
		thread.start();
	}
	public void offThread()
	{
		threadSwitch = false;
		thread = null;
		manager = null;
	}

	@Override
	public void run()
	{
		while(threadSwitch)
		{
			try
			{
				manager.act(script);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e)
			{
				
			}
		}
		
	}
	@Override
	public void exit()
	{
		threadSwitch = false;
		
	}
}
