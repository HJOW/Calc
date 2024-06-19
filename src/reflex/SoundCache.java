package reflex;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

import setting.Setting;
import main_classes.RunManager;

public class SoundCache
{
	public static AudioClip fireMissile = null, fireBeam = null, fireMultiplyMissiles = null, hit = null, boom = null, takeItem = null, clicks = null;
	public static AudioClip bgm = null;
	private static int channels = 16;
	private static SoundThread[] playThreads = null;
	private static int nowState = 0;
	
	public static void clear()
	{
		try
		{
			if(playThreads != null)
			{
				for(int i=0; i<playThreads.length; i++)
				{
					try
					{
						if(playThreads[i] != null) playThreads[i].stopThread();
						playThreads[i] = null;
					} 
					catch (Exception e)
					{
						
					}
				}
				playThreads = null;
			}
		} 
		catch (Exception e)
		{
			
		}
	}
	public static void prepareSound(Setting sets)
	{
		prepareSound(sets, channels);
	}
	public static void prepareSound(Setting sets, int channels)
	{
		File file = null;
		String path = sets.getDefault_path();
		SoundCache.channels = channels;
				
		clear();
		
		playThreads = new SoundThread[channels];
		for(int i=0; i<channels; i++)
		{
			playThreads[i] = new SoundThread();
			playThreads[i].start();
		}
		
		file = new File(RunManager.r65279(path + "fire_missile.wav"));
		
		if(file.exists())
		{
			try
			{
				fireMissile = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				fireMissile = null;
			}
		}
		file = new File(RunManager.r65279(path + "fire_beam.wav"));
		if(file.exists())
		{
			try
			{
				fireBeam = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				fireBeam = null;
			}
		}
		file = new File(RunManager.r65279(path + "fire_multiple_missiles.wav"));
		if(file.exists())
		{
			try
			{
				fireMultiplyMissiles = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				fireMultiplyMissiles = null;
			}
		}
		file = new File(RunManager.r65279(path + "hit.wav"));
		if(file.exists())
		{
			try
			{
				hit = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				hit = null;
			}
		}
		file = new File(RunManager.r65279(path + "boom.wav"));
		if(file.exists())
		{
			try
			{
				boom = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				boom = null;
			}
		}
		file = new File(RunManager.r65279(path + "clicks.wav"));
		if(file.exists())
		{
			try
			{
				clicks = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				clicks = null;
			}
		}
		file = new File(RunManager.r65279(path + "bgm.wav"));
		if(file.exists())
		{
			try
			{
				bgm = Applet.newAudioClip(file.toURI().toURL());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				bgm = null;
			}
		}
	}
	public static void play(String commands)
	{
		if(! Reflexioner.sound_allow) return;
		if(playThreads == null) return;
		if(playThreads.length <= 0) return;
		boolean allEquals = true;
		try
		{
			int allEqualChecker = playThreads[0].nowSize();
			
			for(int i=0; i<playThreads.length; i++)
			{
				if(allEqualChecker != playThreads[i].nowSize())
				{
					allEquals = false;
					break;
				}
			}
			if(allEquals)
			{
				if(nowState >= playThreads.length) nowState = 0;
				playThreads[nowState].addWork(commands);
				nowState++;				
			}
			else
			{
				Arrays.sort(playThreads);
				playThreads[0].addWork(commands);
			}
		} 
		catch (Exception e)
		{
			
		}		
	}
}
class SoundThread extends Thread implements Comparable<SoundThread>
{
	private boolean threadSwitch = true;
	private Vector<String> needToPlay = new Vector<String>();
	private String target = "";
	private transient long sleeping = 0;
	private transient AudioClip playing = null;
	private transient long calc_sleeps = 0;
	public SoundThread()
	{
		
	}
	public int nowSize()
	{
		return needToPlay.size();
	}
	
	public void addWork(String commands)
	{
		needToPlay.add(commands);
	}
	public void stopThread()
	{
		threadSwitch = false;
	}
	public boolean playing()
	{
		return (sleeping >= 1);
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{
			try
			{
				if(needToPlay.size() >= 1 && sleeping <= 0)
				{
					target = needToPlay.get(0);
					if(target.equalsIgnoreCase("fire_missile") && SoundCache.fireMissile != null)
					{
						playing = SoundCache.fireMissile;						
					}
					else if(target.equalsIgnoreCase("fire_beam") && SoundCache.fireBeam != null)
					{
						playing = SoundCache.fireBeam;
					}
					else if(target.equalsIgnoreCase("fire_multiple_missiles") && SoundCache.fireMultiplyMissiles != null)
					{
						playing = SoundCache.fireMultiplyMissiles;
					}
					else if(target.equalsIgnoreCase("hit") && SoundCache.hit != null)
					{
						playing = SoundCache.hit;
					}
					else if(target.equalsIgnoreCase("boom") && SoundCache.boom != null)
					{
						playing = SoundCache.boom;
					}
					else if(target.equalsIgnoreCase("takeItem") && SoundCache.takeItem != null)
					{
						playing = SoundCache.takeItem;
					}
					else if(target.equalsIgnoreCase("click") && SoundCache.clicks != null)
					{
						playing = SoundCache.clicks;
					}
					if(playing != null)
					{
						playing.play();
						sleeping = 10;
					}
					needToPlay.remove(0);
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				calc_sleeps = 100 + (int)(Math.random() * 5);
				if(sleeping >= 1) sleeping--;
				Thread.sleep(calc_sleeps);				
			}
			catch(Exception e)
			{
				
			}
		}
	}
	@Override
	public int compareTo(SoundThread o)
	{
		return new Integer(nowSize()).compareTo(new Integer(o.nowSize()));
	}
}
