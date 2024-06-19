package sound;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import setting.Setting;

public class Player
{
	private static Player player = new Player();
	private Setting sets = null;
	private AudioInputStream audioIn1 = null, audioIn2 = null, audioIn3 = null, audioIn4 = null;
	private File a1 = null, a2 = null, a3 = null, s = null;
	private Clip clip = null;
	private boolean prepared = false;
	private Player()
	{
		sets = Setting.getNewInstance();		
	}
	
	public static void setSetting(Setting sets)
	{
		player.sets = sets;
		Player.prepare();
	}
	public static boolean isPrepared()
	{
		return player.prepared;
	}
	public static void prepare()
	{
		closeAll();
		try
		{
			player.a1 = new File(player.sets.getDefault_path() + "a1.wav");
			player.a2 = new File(player.sets.getDefault_path() + "a2.wav");
			player.a3 = new File(player.sets.getDefault_path() + "a3.wav");
			player.s = new File(player.sets.getDefault_path() + "s.wav");
			if(player.a1.exists())
			{
				player.audioIn1 = AudioSystem.getAudioInputStream(player.a1);
			}
			if(player.a2.exists())
			{
				player.audioIn2 = AudioSystem.getAudioInputStream(player.a2);
			}
			if(player.a3.exists())
			{
				player.audioIn3 = AudioSystem.getAudioInputStream(player.a3);
			}
			if(player.s.exists())
			{
				player.audioIn4 = AudioSystem.getAudioInputStream(player.s);
			}
			player.clip = AudioSystem.getClip();
			player.prepared = true;
		} 
		catch (Exception e)
		{
			player.prepared = false;
		}
	}
	public static void closeAll()
	{
		try
		{
			player.clip.close();
		}
		catch (Exception e)
		{
			
		}
		try
		{
			player.audioIn1.close();
			player.audioIn1 = null;
		}
		catch (Exception e)
		{
			
		}
		try
		{
			player.audioIn2.close();
			player.audioIn2 = null;
		}
		catch (Exception e)
		{
			
		}
		try
		{
			player.audioIn3.close();
			player.audioIn3 = null;
		}
		catch (Exception e)
		{
			
		}
		try
		{
			player.audioIn4.close();
			player.audioIn4 = null;
		}
		catch (Exception e)
		{
			
		}
		try
		{
			player.prepared = false;
		} 
		catch (Exception e)
		{
			
		}
	}
	public static void play(int i) throws Exception
	{
		if(! player.prepared) return;
		if(! player.clip.isRunning())
		{
			switch(i)
			{
				case 0:
					if(player.audioIn1 != null)
						player.clip.open(player.audioIn1);
					break;
				case 1:
					if(player.audioIn2 != null)
						player.clip.open(player.audioIn2);
					break;
				case 2:
					if(player.audioIn3 != null)
						player.clip.open(player.audioIn3);
					break;
				case 3:
					if(player.audioIn4 != null)
						player.clip.open(player.audioIn4);
					break;
			}
			try
			{
				player.clip.start();
			} 
			catch (Exception e)
			{
				
			}
		}
		else
		{
			player.clip.close();
		}
	}
}
