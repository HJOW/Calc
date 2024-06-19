package main_classes;

import java.util.Vector;

public class ThreadAccumulate
{
	private static Vector<ThreadControl> threads = new Vector<ThreadControl>();
	public static void add(ThreadControl accumulates)
	{
		threads.add(accumulates);
	}
	public static void exitAll()
	{
		for(ThreadControl t : threads)
		{
			try
			{
				t.exit();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
