package first_run;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import main_classes.CalcWindow;

public class RunCalc 
{	
	private static SettingForRun sets;
	private static String separator;
	private static int downloaded;
	private static int contentLength;
	private static int size = 1;
	private static int status = 5;
	public static boolean always_download = false;
	private static String ver_info;
	public static final int DOWNLOADING = 0;
	public static final int PAUSED = 1;
	public static final int COMPLETE = 2;
	public static final int CANCELLED = 3;
	public static final int ERROR = 4;
	public static final int CHECKING_VER = 5;	
	
	public static final int BUFFER_SIZE = 1024;
	
	public static String getFileName(URL url)
	{
		String fileName = url.getFile();
		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}
	public static int start(int get_buffer_size, String[] args, boolean get_always_download)
	{
		boolean error_occured = false;
		boolean downloaded_file_exist = false;
		int buffer_size = get_buffer_size;
		
		if(buffer_size == 0) buffer_size = BUFFER_SIZE;
		
		String default_path = System.getProperty("user.home");
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + "calc" + separator;		
		
		try
		{
			FileInputStream fin = new FileInputStream(default_path + "run.crst");
			ObjectInputStream obin = new ObjectInputStream(fin);
			sets = (SettingForRun) obin.readObject();
			obin.close();
			fin.close();
			
			if(! sets.isAuthorized())
			{
				System.out.println(sets.getLang().getFailed() + sets.getLang().getAuthorizing());
				sets = new SettingForRun();
			}
		} 
		catch (FileNotFoundException e)
		{
			sets = new SettingForRun();
		} 
		catch (ClassNotFoundException e)
		{
			sets = new SettingForRun();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sets = new SettingForRun();
		} 
		always_download = sets.getAlways_download();
		if(get_always_download) always_download = true;
		
		boolean changed = false;
		boolean run_downloaded_file = true;
		stateChanged();
		try
		{
			URL notice_ver = new URL(sets.getBasic_url() + "notice_ver.txt");
			InputStreamReader inread = new InputStreamReader(notice_ver.openStream());
			BufferedReader bfread = new BufferedReader(inread);
			ver_info = bfread.readLine();
			bfread.close();
			inread.close();
			char[] arraies = ver_info.toCharArray();
			
			SettingForRun newRun = new SettingForRun();
			newRun.setVer_main(new Integer(arraies[0] - '0'));
			newRun.setVer_sub1(new Integer(arraies[1] - '0'));
			newRun.setVer_sub2(new Integer(arraies[2] - '0'));	
			
			if(CalcWindow.version_main < newRun.getVer_main()
					|| (CalcWindow.version_main <= newRun.getVer_main() && CalcWindow.version_sub_1 < newRun.getVer_sub1())
					|| (CalcWindow.version_main <= newRun.getVer_main() && CalcWindow.version_sub_1 <= newRun.getVer_sub1() && CalcWindow.version_sub_2 < newRun.getVer_sub2()))
			{
				
			}
			else
			{
				if(! always_download)
				{
					System.out.println(sets.getLang().getAlready_newVersion());
					return 1;
				}
			}
			
			try
			{
				File downloadedFile = new File(sets.getExec_path());
				downloaded_file_exist = downloadedFile.exists();
			} 
			catch (Exception e)
			{
				downloaded_file_exist = false;
			}
			
			String already_message = "";
			if(sets.getVer_main() < newRun.getVer_main()
					|| (sets.getVer_main() <= newRun.getVer_main() && sets.getVer_sub1() < newRun.getVer_sub1())
					|| (sets.getVer_main() <= newRun.getVer_main() && sets.getVer_sub1() <= newRun.getVer_sub1() && sets.getVer_sub2() < newRun.getVer_sub2())
					|| always_download)
			{
				String newVer_message = sets.getLang().getNew_version_exist() + String.valueOf(newRun.getVer_main())
						+ "." + String.valueOf(newRun.getVer_sub1()) + String.valueOf(newRun.getVer_sub2()) + ")\n" + sets.getLang().getGet_location() + sets.getBasic_url();
				int getSelection = JOptionPane.showOptionDialog(null, newVer_message, newVer_message, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(getSelection == JOptionPane.YES_OPTION)
				{
					changed = true;
					downloaded_file_exist = true;
					sets.setVer_main(new Integer(arraies[0] - '0'));
					sets.setVer_sub1(new Integer(arraies[1] - '0'));
					sets.setVer_sub2(new Integer(arraies[2] - '0'));
					sets.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
				}
				else
				{					
					changed = false;	
					try
					{
						File downloadedFile = new File(sets.getExec_path());
						downloaded_file_exist = downloadedFile.exists();
						
					} 
					catch (Exception e)
					{
						downloaded_file_exist = false;
					}
					if(downloaded_file_exist)
					{
						already_message = sets.getLang().getAsk_run_download();
						int select_already_message_download = JOptionPane.showOptionDialog(null, already_message, already_message, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
						if(select_already_message_download == JOptionPane.YES_OPTION)
						{
							run_downloaded_file = true;
						}
						else run_downloaded_file = false;
					}
					else run_downloaded_file = false;
				}
			}			
			else
			{
				try
				{
					File downloadedFile = new File(sets.getExec_path());
					downloaded_file_exist = downloadedFile.exists();
				} 
				catch (Exception e)
				{
					downloaded_file_exist = false;
				}
				if(downloaded_file_exist)
				{
					System.out.println(sets.getLang().getAlready_newVersion());
					changed = false;
					already_message = sets.getLang().getAlready_newVersion() + "\n" + sets.getLang().getAsk_run_download();
					int select_already_message_download = JOptionPane.showOptionDialog(null, already_message, already_message, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(select_already_message_download == JOptionPane.YES_OPTION)
					{
						run_downloaded_file = true;
					}
					else run_downloaded_file = false;
				}
				else
				{
					run_downloaded_file = false;
				}
			}
		}
		catch (Exception e1)
		{
			changed = false;
			return 1;
		}
		
		RandomAccessFile file = null;
		InputStream stream = null;
		downloaded = 0; 
		contentLength = 0;
		size = -1;
		
		if(changed)
		{
			try
			{
				boolean need_retry = false;
				URL url = null;		
				HttpURLConnection connection = null;
				try
				{
					url = new URL(sets.getBasic_url() + "calc_game.jar");	
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
					connection.connect();
				} 
				catch (Exception e)
				{
					need_retry = true;
					try
					{
						connection.disconnect();
					} 
					catch (Exception e1)
					{
						
					}
				}
				if(need_retry)
				{
					url = new URL(sets.getBasic_url2() + "calc_game.jar");	
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
					connection.connect();
				}
				if((connection.getResponseCode() / 100) != 2)
				{
					status = ERROR;
					stateChanged();
					return 1;
				}
				else
				{
					contentLength = connection.getContentLength();
					if(contentLength < 1)
					{
						status = ERROR;
						stateChanged();
						return 1;
					}
					else
					{
						if(size == -1)
						{
							size = contentLength;						
						}
						File check_dir = new File(sets.getDefault_path());
						if(! check_dir.exists()) check_dir.mkdir();
						file = new RandomAccessFile(sets.getExec_path(), "rw");	
						file.seek(downloaded);
						
						stream = connection.getInputStream();
						status = DOWNLOADING;
						stateChanged();
						System.out.println(sets.getLang().getDownloading());
						while(status == DOWNLOADING)
						{
							byte[] buffer;
							if(size - downloaded > buffer_size)
							{
								buffer = new byte[buffer_size];
							}
							else
							{
								buffer = new byte[size - downloaded];
							}
							int read = stream.read(buffer);
							if(read == -1)
							{
								break;
							}
							
							file.write(buffer, 0, read);
							downloaded += read;
							stateChanged();
						}
						System.out.println();
						if(status == DOWNLOADING)
						{
							status = COMPLETE;
							System.out.println(sets.getLang().getCompleted() + sets.getExec_path());
							String finish_message = sets.getLang().getCompleted() + sets.getExec_path() + "\n" + sets.getLang().getAsk_run_download();
							int select_finish_download = JOptionPane.showOptionDialog(null, finish_message, finish_message, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
							if(select_finish_download == JOptionPane.YES_OPTION)
							{
								run_downloaded_file = true;
								downloaded_file_exist = true;
							}
							else run_downloaded_file = false;
							stateChanged();
						}
						
					}
				}
			} /*
			catch (FileNotFoundException e)
			{
				//e.printStackTrace();
				return 1;
			}*/
			catch (MalformedURLException e)
			{
				e.printStackTrace();
				status = ERROR;
				stateChanged();
				error_occured = true;
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				status = ERROR;
				stateChanged();
				error_occured = true;
			}
			finally
			{
				if(file != null)
				{
					try
					{
						file.close();
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				if(stream != null)
				{
					try
					{
						stream.close();
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		//else return 1;
		
		if(error_occured)
		{
			return 1;
		}
		if(! run_downloaded_file)
		{
			return 1;
		}
		
		Runtime runtime = Runtime.getRuntime();
		try
		{
			sets.refresh_authorize();
			File save_target = new File(default_path);
			if(! save_target.exists()) save_target.mkdir();
			FileOutputStream newOut = new FileOutputStream(default_path + "run.crst");
			ObjectOutputStream obout = new ObjectOutputStream(newOut);
			obout.writeObject(sets);
			obout.close();
			newOut.close();
			
			StringBuffer include_arguments = new StringBuffer(" ");
			if(args != null)
			{
				for(int i=0; i<args.length; i++)
				{
					include_arguments.append(args[i]);
					include_arguments.append(" ");
				}
			}
			File runFile = new File(sets.getExec_path());
			if(runFile.exists())
				runtime.exec("java -jar " + sets.getExec_path() + include_arguments.toString());	
			else return 1;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 1;
		}
		System.exit(0);
		return 0;
	}
	public static void stateChanged()
	{
		String message = "";
		switch(status)
		{
			case DOWNLOADING:
				message = sets.getLang().getDownloading();
				System.out.print(".");
				break;
			case CHECKING_VER:
				message = sets.getLang().getCheck_version();
				System.out.println(message);
				break;
			case ERROR:
				message = sets.getLang().getFailed();
				System.out.println(message);
				break;
				
		}		
	}
}
