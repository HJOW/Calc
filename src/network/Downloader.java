package network;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

import lang.Language;
import main_classes.CalcWindow;
import main_classes.RunManager;
import setting.Setting;
import setting.VersionData;

public class Downloader implements TimerTaker
{
	private static int downloaded;
	private static int contentLength;
	private static int size = 1;
	private static int status = 5;
	public static boolean always_download = false;
	private static volatile boolean busyWait = true;
	public static final int DOWNLOADING = 0;
	public static final int PAUSED = 1;
	public static final int COMPLETE = 2;
	public static final int CANCELLED = 3;
	public static final int ERROR = 4;
	public static final int CHECKING_VER = 5;	
	public static final int BUFFER_SIZE = 1024;
	private static Downloader insts;
	private static boolean showDialog = false;
	private static JDialog dialog = null;
	private static int progressNow, progressEnd, now_download_index;
	private static JLabel progressLabel = null;
	private static JProgressBar progressBar = null;
	private static String[] fileList;
	private static JPanel titlePanel;
	private static JPanel upSepPanel;
	private static JLabel titleLabel;
	private static JButton xbutton;
	public static void downloads(Setting sets, String[] fileNames, boolean _showDialog, JDialog spComp) throws Exception
	{
		showDialog = _showDialog;
		fileList = fileNames;
		if(insts == null) insts = new Downloader();
		if(showDialog)
		{
			if(spComp == null) dialog = new JDialog();
			else dialog = new JDialog(spComp, false);
		}
		downloads(sets, fileNames, _showDialog);
	}
	public static void downloads(Setting sets, String[] fileNames, boolean _showDialog, JFrame spComp) throws Exception
	{
		showDialog = _showDialog;
		fileList = fileNames;
		if(insts == null) insts = new Downloader();
		if(showDialog)
		{
			if(spComp == null) dialog = new JDialog();
			else dialog = new JDialog(spComp, false);
		}
		downloads(sets, fileNames, _showDialog);
	}
	public static void downloads(Setting sets, String[] fileNames, boolean _showDialog) throws Exception
	{
		showDialog = _showDialog;
		fileList = fileNames;
		if(insts == null) insts = new Downloader();
		if(showDialog)
		{
			if(dialog == null) dialog = new JDialog();
			dialog.setSize(200, 100);
			dialog.setUndecorated(true);
			dialog.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - dialog.getWidth()/2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - dialog.getHeight()/2) );
			JPanel mainPanel = new JPanel();
			dialog.getContentPane().setLayout(new BorderLayout());
			dialog.getContentPane().add(mainPanel);
			mainPanel.setLayout(new BorderLayout());
			mainPanel.setBorder(new EtchedBorder());
			mainPanel.setBackground(sets.getSelected_back());
			JPanel upPanel, downPanel, rightPanel, leftPanel, centerPanel;
			upPanel = new JPanel();
			downPanel = new JPanel();
			rightPanel = new JPanel();
			leftPanel = new JPanel();
			centerPanel = new JPanel();			
			upPanel.setBackground(sets.getSelected_back());
			downPanel.setBackground(sets.getSelected_back());
			rightPanel.setBackground(sets.getSelected_back());
			leftPanel.setBackground(sets.getSelected_back());
			centerPanel.setBackground(sets.getSelected_back());
			mainPanel.add(upPanel, BorderLayout.NORTH);
			mainPanel.add(downPanel, BorderLayout.SOUTH);
			mainPanel.add(centerPanel, BorderLayout.CENTER);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			mainPanel.add(rightPanel, BorderLayout.EAST);
			
			if(CalcWindow.usingFont == null)
				CalcWindow.prepareFont();
			
			upPanel.setLayout(new BorderLayout());
			titlePanel = new JPanel();
			upSepPanel = new JPanel();
			upPanel.add(titlePanel, BorderLayout.CENTER);
			upPanel.add(upSepPanel, BorderLayout.SOUTH);
			titlePanel.setBackground(sets.getSelected_inside_back());
			upSepPanel.setBackground(sets.getSelected_back());
			titlePanel.setLayout(new FlowLayout());
			titleLabel = new JLabel(sets.getLang().getText(Language.UPDATE));
			if(CalcWindow.usingFont != null)
				titleLabel.setFont(CalcWindow.usingFont);
			titleLabel.setForeground(sets.getSelected_fore());
			titlePanel.add(titleLabel);
			
			centerPanel.setLayout(new BorderLayout());
			JPanel progressPanel = new JPanel();
			centerPanel.add(progressPanel, BorderLayout.CENTER);
			
			progressNow = 0;
			progressEnd = 100 * fileNames.length;
			
			progressPanel.setLayout(new FlowLayout());
			progressPanel.setBackground(sets.getSelected_back());
			progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, progressEnd);
			progressPanel.add(progressBar);
			xbutton = new JButton("X");
			if(CalcWindow.usingFont != null)
				xbutton.setFont(CalcWindow.usingFont);
			xbutton.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					status = CANCELLED;
					
				}
			});
			xbutton.setForeground(sets.getSelected_fore());
			if(sets.getSelected_button() != null)
				xbutton.setBackground(sets.getSelected_button());
			progressPanel.add(xbutton);
			JPanel labelPanel = new JPanel();
			labelPanel.setBackground(sets.getSelected_back());
			centerPanel.add(labelPanel, BorderLayout.SOUTH);
			labelPanel.setLayout(new FlowLayout());
			progressLabel = new JLabel();
			if(CalcWindow.usingFont != null)
				progressLabel.setFont(CalcWindow.usingFont);
			progressLabel.setForeground(sets.getSelected_fore());
			labelPanel.add(progressLabel);
			
			
			dialog.setVisible(true);
		}
		for(now_download_index=0; now_download_index<fileNames.length; now_download_index++)
		{
			if(fileNames[now_download_index] != null)
			{
				download(sets, fileNames[now_download_index]);
			}
		}
		if(showDialog)
		{
			dialog.setVisible(false);
			dialog = null;
		}
	}
	public static void download(Setting sets, String fileName) throws Exception
	{	
		int buffer_size = BUFFER_SIZE;
		RandomAccessFile file = null;
		InputStream stream = null;
		downloaded = 0; 
		contentLength = 0;
		size = -1;		
		long not_infinite_download = 0;
		if(insts == null) insts = new Downloader();
		
		try
		{
			boolean need_retry = false;
			URL url = null;		
			HttpURLConnection connection = null;
			try
			{
				
				url = new URL(RunManager.r65279(sets.getNotice_url() + fileName));	
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
				url = new URL(RunManager.r65279(sets.getNotice_url2() + fileName));	
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
				connection.connect();
			}
			if((connection.getResponseCode() / 100) != 2)
			{
				status = ERROR;
				stateChanged();
				if(showDialog)
				{
					dialog.setVisible(false);
				}
				return;
			}
			else
			{
				contentLength = connection.getContentLength();
				not_infinite_download = contentLength + 1000;
				if(contentLength < 1)
				{
					status = ERROR;
					stateChanged();
					if(showDialog)
					{
						dialog.setVisible(false);
					}
					return;
				}
				else
				{
					if(size == -1)
					{
						size = contentLength;						
					}
					File check_dir = new File(sets.getDefault_path());
					if(! check_dir.exists()) check_dir.mkdir();
					file = new RandomAccessFile(sets.getDefault_path() + fileName, "rw");
					file.seek(downloaded);
					
					stream = connection.getInputStream();
					status = DOWNLOADING;
					stateChanged();
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
						not_infinite_download--;
						if(not_infinite_download < -1) break;
						stateChanged();
						if(status == CANCELLED) break;
					}
					if(status == DOWNLOADING)
						status = COMPLETE;
					if(status == COMPLETE)
					{						
						stateChanged();						
						TimerThread thr = new TimerThread(insts, 2);
						thr.start();
						busyWait = true;
						long prevent_infiniteLoop = 0;
						while(busyWait)
						{
							prevent_infiniteLoop++;
							if(prevent_infiniteLoop >= 10000) break;
							try
							{
								Thread.sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
					if(status == CANCELLED)
					{
						stateChanged();
						if(file != null)
						{
							try
							{
								file.close();
							} 
							catch (Exception e)
							{
								
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
								
							}
						}
						try
						{
							File removeFile = new File(sets.getDefault_path() + fileName);
							removeFile.delete();
						} 
						catch (Exception e)
						{
							
						}
						System.exit(0);
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
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			status = ERROR;
			stateChanged();
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
					
				}
			}
		}
		//else return 1;
		
	}
	private static void stateChanged()
	{
		if(showDialog)
		{
			if(status == DOWNLOADING)
			{
				progressNow = (int) (((double) downloaded / (double) contentLength) * 100.0) + (100 * now_download_index);
				progressBar.setValue(progressNow);
				progressLabel.setText(fileList[now_download_index]);
			}
			else if(status == COMPLETE)
			{
				progressLabel.setText("Complete");
			}
			else
			{
				progressLabel.setText("");
			}
		}		
	}
	public static void closeDialog()
	{
		showDialog = false;
		dialog.setVisible(false);
	}
	public static void update(Setting sets)
	{
		try
		{
			String[] fileNames = new String[1];
			fileNames[0] = "calc_downloader.jar";
			
			Downloader.downloads(sets, fileNames, true);
			File resultFile;
			resultFile = new File(System.getProperty("user.home") + "/calc/calc_downloader.jar");
			Runtime.getRuntime().exec("java -jar " + resultFile.getAbsolutePath());
			System.exit(0);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
		}
	}
	@Override
	public void timerFinish()
	{
		if(showDialog)
		{
			dialog.setVisible(false);
		}
		busyWait = false;
	}
	public static VersionData getNewVersionNumber(Setting sets) throws Exception
	{
		Setting s = sets;
		if(s == null) s = Setting.getNewInstance();
		InputStream in = null;
		InputStreamReader reader = null;
		BufferedReader buffered = null;
		VersionData nd = null;
		try
		{
			in = new URL(s.getNotice_url() + "notice_ver.txt").openStream();
		} 
		catch (MalformedURLException e)
		{
			in = new URL(s.getNotice_url2() + "notice_ver.txt").openStream();
		}
		catch (Exception e)
		{
			throw e;
		}
		try
		{
			if(in != null)
			{
				reader = new InputStreamReader(in);
				buffered = new BufferedReader(reader);
				String read = buffered.readLine();
				if(read != null)
				{
					char[] getChar = read.toCharArray();
					if(getChar.length >= 4)
						nd = new VersionData(getChar[0] - '0', getChar[1] - '0', getChar[2] - '0', getChar[3]);
					else
						nd = new VersionData(getChar[0], getChar[1], getChar[2], ' ');
				}
			}
		} 
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				buffered.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				reader.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				in.close();
			}
			catch (Exception e)
			{
				
			}
		}
		return nd;
	}
}