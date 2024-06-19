
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import main_classes.MessageShowable;
import main_classes.Openable;
import main_classes.RunManager;
import network.Downloader;
import scripting.NormalScriptManager;
import setting.Setting;
import first_run.RunCalc;
import first_run.RunSettingManager;
public class Index 
{		
	public static final boolean TRY_CHECKVER = false;
	public static final boolean UPDATE_ONLY = false;
	
	public static void main(String[] args)
	{		
		int try_state = 0;
		boolean found_update_arg = false;
		String[] ags = args;
		if(UPDATE_ONLY)
		{
			ags = new String[1];
			ags[0] = "update_now";
		}
		
				
		if(ags != null || UPDATE_ONLY)
		{
			if(ags.length >= 1)
			{
				for(int i=0; i<ags.length; i++)
				{
					if(ags[i].endsWith(".js") || ags[i].endsWith(".JS") || ags[i].endsWith(".Js")
							|| ags[i].endsWith(".script") || ags[i].endsWith(".SCRIPT") || ags[i].endsWith(".Script"))
					{
						
						new NormalScriptManager(new ConsoleShowable(), Setting.getNewInstance()).actFile(ags[i]);
						return;
					}
					else if(args[i].equalsIgnoreCase("script"))
					{
						new NormalScriptManager(new ConsoleShowable(), Setting.getNewInstance()).open();
						return;
					}
					else if(ags[i].equalsIgnoreCase("update_now") || UPDATE_ONLY)
					{						
						Setting setting = Setting.getNewInstance();
						FileInputStream fin = null;
						ObjectInputStream obin = null;
						XMLEncoder encoder = null;
						XMLDecoder decoder = null;
						FileOutputStream fout = null;
						ObjectOutputStream obout = null;
						boolean xmled = false;
						if(UPDATE_ONLY)
						{
							try
							{
								try
								{
									UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
								} 
								catch (ClassNotFoundException e)
								{
									UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
								}
							}
							catch(Exception e)
							{
								
							}
						}
						try
						{
							File savingFile = new File(System.getProperty("user.home") + "/calc/setting.clst");
							xmled = false;
							if(! savingFile.exists())
							{
								savingFile = new File(Setting.getNewInstance().getDefault_path() + "setting.clst");
								xmled = false;
								
							}
							if(! savingFile.exists())
							{
								savingFile = new File(Setting.getNewInstance().getDefault_path() + "setting.xml");								
								if(savingFile.exists())
									xmled = true;
								
							}
							if(! savingFile.exists())
							{
								savingFile = new File(System.getProperty("user.home") + "/calc/setting.xml");
								if(savingFile.exists())
									xmled = true;								
							}
							if(! savingFile.exists())
							{
								savingFile = new File(System.getProperty("user.home") + "/calc/setting.clst");
								xmled = false;
							}
							if(savingFile.exists())
							{
								if(xmled)
								{
									fin = new FileInputStream(savingFile);
									decoder = new XMLDecoder(fin);
									setting = (Setting) decoder.readObject();	
								}
								else
								{
									fin = new FileInputStream(savingFile);
									obin = new ObjectInputStream(fin);
									setting = (Setting) obin.readObject();	
								}															
							}
							else setting = Setting.getNewInstance();
							String[] fileNames = null;
							if(UPDATE_ONLY)
							{
								fileNames = new String[1];
								fileNames[0] = "calc_game.jar";
							}
							else if(ags[i].equalsIgnoreCase("update_now"))
							{
								fileNames = new String[1];
								fileNames[0] = "calc_downloader.jar";
							}
							Downloader.downloads(setting, fileNames, true);
							if(UPDATE_ONLY)
							{
								setting.setDownloaded_path(setting.getDefault_path() + "calc_game.jar");
								setting.setDownloaded_ver(Downloader.getNewVersionNumber(setting));
								try
								{
									obin.close();
								}
								catch(Exception e)
								{
									
								}
								try
								{
									decoder.close();
								}
								catch(Exception e)
								{
									
								}
								try
								{
									fin.close();
								}
								catch(Exception e)
								{
									
								}	
								obin = null;
								decoder = null;
								fin = null;
								fout = new FileOutputStream(savingFile);
								if(xmled)
								{
									encoder = new XMLEncoder(fout);
									encoder.writeObject(fout);
								}
								else
								{
									obout = new ObjectOutputStream(fout);
									obout.writeObject(setting);
								}
							}
							File resultFile;
							if(UPDATE_ONLY)
							{
								resultFile = new File(System.getProperty("user.home") + "/calc/calc_game.jar");	
								if(! resultFile.exists())
								{
									resultFile = new File(setting.getDefault_path() + "calc_game.jar");	
								}
								try
								{									
									try
									{
										if(System.getProperty("os.name").startsWith("ubuntu") || System.getProperty("os.name").startsWith("Ubuntu")
												|| System.getProperty("os.name").startsWith("UBUNTU"))
										{
											if(System.getProperty("user.language").equalsIgnoreCase("ko") || System.getProperty("user.language").equalsIgnoreCase("kr")
													|| System.getProperty("user.language").equalsIgnoreCase("kor") || System.getProperty("user.language").equalsIgnoreCase("korean"))
											{
												JOptionPane.showMessageDialog(null, "우분투 환경에서는 보안 정책 때문에 다운로드된 새 버전이 자동으로 실행되지 않을 수 있습니다.\n다운로드된 실행 파일은 \n" + resultFile.getAbsolutePath() + "\n에 있습니다.");
											}
											else
											{
												JOptionPane.showMessageDialog(null, "You are using Ubuntu.\nDonwloaded file may not run automatically because of security issue.\nDownloaded file is in\n" + resultFile.getAbsolutePath() + "\nRun this file to play the game.");
											}
										}
									} 
									catch (Exception e)
									{
										
									}
									Runtime.getRuntime().exec("java -jar " + resultFile.getAbsolutePath());
								} 
								catch (Exception e)
								{
									if(System.getProperty("os.name").startsWith("ubuntu") || System.getProperty("os.name").startsWith("Ubuntu")
											|| System.getProperty("os.name").startsWith("UBUNTU"))
									{
										JOptionPane.showMessageDialog(null, "보안 문제로 인하여 새 버전을 실행하지 못했습니다.\n다운로드된 파일은\n" + resultFile.getAbsolutePath() + "\n에 있습니다.");
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Run failed because of security issue.\nDownloaded file is in\n" + resultFile.getAbsolutePath() + "\nRun this file to play the game.");
									}
								}
								System.exit(0);
							}
							else if(ags[i].equalsIgnoreCase("update_now"))
							{
								resultFile = new File(System.getProperty("user.home") + "/calc/calc_downloader.jar");
								Runtime.getRuntime().exec("java -jar " + resultFile.getAbsolutePath());								
								System.exit(0);
							}
						}
						catch(Exception e)
						{
							
						}		
						finally
						{
							try
							{
								obin.close();
							}
							catch(Exception e)
							{
								
							}
							try
							{
								obout.close();
							}
							catch(Exception e)
							{
								
							}
							try
							{
								decoder.close();
							}
							catch(Exception e)
							{
								
							}
							try
							{
								encoder.close();
							}
							catch(Exception e)
							{
								
							}
							try
							{
								fin.close();
							}
							catch(Exception e)
							{
								
							}
							try
							{
								fout.close();
							}
							catch(Exception e)
							{
								
							}
						}
					}
					else if(args[i].startsWith("update:") || args[i].startsWith("UPDATE:") || args[i].startsWith("Update:"))
					{
						found_update_arg = true;
						if(ags[i].endsWith("as_needed") || ags[i].endsWith("AS_NEEDED") || ags[i].endsWith("As_needed"))
						{
							try_state = RunCalc.start(102400, args, false);
						}
						else if(ags[i].endsWith("never") || ags[i].endsWith("NEVER") || ags[i].endsWith("Never"))
						{
							try_state = 1;
						}
						else if(ags[i].endsWith("always") || ags[i].endsWith("ALWAYS") || ags[i].endsWith("Always"))
						{
							try_state = RunCalc.start(102400, ags, true);
						}	
						else
						{
							found_update_arg = false;
						}
						break;
					}
					else if(ags[i].equalsIgnoreCase("run_mode_editor"))
					{
						try_state = 0;
						found_update_arg = true;
						Openable window = new RunSettingManager(true);
						window.open();
						break;
					}						
				}
				if(! found_update_arg)
				{
					if(TRY_CHECKVER) try_state = RunCalc.start(102400, args, false);
					else try_state = 1;
				}
			}
			else
			{
				if(TRY_CHECKVER) try_state = RunCalc.start(102400, args, false);
				else try_state = 1;
			}
		}
		else
		{
			if(TRY_CHECKVER) try_state = RunCalc.start(102400, args, false);
			else try_state = 1;
		}
		
		if(try_state == 1)
		{		
			Index ind = new Index();
			try
			{
				String path = ind.getClass().getClassLoader().getResource("./").getPath();
				RunManager.run(args, null, path);
			} 
			catch (Exception e)
			{
				try
				{
					RunManager.run(args, null);
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					System.exit(0);
				}
			}
		}
	}	
}
class ConsoleShowable implements MessageShowable
{
	@Override
	public void message()
	{
		System.out.println();
		
	}

	@Override
	public void message_bar()
	{
		System.out.println("----------------------------------------------");
		
	}

	@Override
	public void message(String str)
	{
		System.out.println(str);
		
	}

	@Override
	public void alert(String str)
	{
		JOptionPane.showMessageDialog(null, str);
		
	}

	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}	
}
