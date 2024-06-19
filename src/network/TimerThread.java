package network;

import main_classes.ThreadControl;

public class TimerThread implements ThreadControl
{
	private Thread thread;
	private boolean thswt = true;
	private int value = 0;
	private int waitTime = 3;
	private TimerTaker taker = null;
	public TimerThread(TimerTaker taker, int waitTime)
	{
		thread = new Thread(this);
		this.waitTime = waitTime;
		this.taker = taker;
	}
	public void start()
	{
		thread.start();
	}
	@Override
	public void run()
	{
		while(thswt)
		{
			try
			{
				value++;
				if(value >= waitTime)
				{
					taker.timerFinish();
					taker = null;
					thswt = false;
				}
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
		}		
	}
	@Override
	public void exit()
	{
		thswt = false;
		
	}	
}
