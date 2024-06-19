package main_classes;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.Setting;

public class Uninstaller
{
	private static JDialog dialog;
	private static JLabel progressLabel = null;
	private static JProgressBar progressBar = null;
	private static JPanel titlePanel;
	private static JPanel upSepPanel;
	private static JLabel titleLabel;
	private static JButton xbutton;
	private static int progressNow = 0;
	private static int progressEnd = 1000;
	
	public static void uninstall_progress_show(Setting sets, JDialog spComp)
	{
		if(spComp == null)
		{
			try
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			}
			catch(Exception e)
			{
				
			}
			dialog = new JDialog();
		}
		else dialog = new JDialog(spComp, false);
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
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		upSepPanel = new JPanel();
		upPanel.add(titlePanel, BorderLayout.CENTER);
		upPanel.add(upSepPanel, BorderLayout.SOUTH);
		titlePanel.setBackground(sets.getSelected_inside_back());
		upSepPanel.setBackground(sets.getSelected_back());
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(sets.getLang().getText(Language.UPDATE));
		titleLabel.setForeground(sets.getSelected_fore());
		titlePanel.add(titleLabel);
		
		centerPanel.setLayout(new BorderLayout());
		JPanel progressPanel = new JPanel();
		centerPanel.add(progressPanel, BorderLayout.CENTER);
		
		progressNow = 0;
		progressEnd = 1000;
		
		progressPanel.setLayout(new FlowLayout());
		progressPanel.setBackground(sets.getSelected_back());
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, progressEnd);
		progressPanel.add(progressBar);
		xbutton = new JButton("X");
		xbutton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
				
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
		progressLabel.setForeground(sets.getSelected_fore());
		labelPanel.add(progressLabel);
				
		dialog.setVisible(true);
	}
	public static void uninstall(MessageShowable cons, Setting sets, boolean gui)
	{
		uninstall(cons, sets, gui, false, false, true, null);
	}
	public static void uninstall(MessageShowable cons, Setting sets, boolean gui, boolean afterDelete, boolean afterExit, boolean askAgain, JDialog spComp)
	{
		MessageShowable console = cons;
		Setting st = sets;
		if(cons == null)
		{
			console = new MessageShowable()
			{
				
				@Override
				public void message_bar()
				{
					System.out.println("--------------------------------------------------");
					
				}
				
				@Override
				public void message(String str)
				{
					System.out.println(str);
					
				}
				
				@Override
				public void message()
				{
					System.out.println();
					
				}
				
				@Override
				public void alert(String str)
				{	
					System.out.println(str);
					if(Desktop.isDesktopSupported()) JOptionPane.showMessageDialog(null, str);
				}

				@Override
				public void openConsole()
				{
					
				}
			};
		}
		if(sets == null)
		{
			st = Setting.getNewInstance();
		}
		try
		{
			String askMessage = "";
			String deleted = "";
			String completed = "";
			String error_messages = "";
			boolean errored = false;
			if(afterDelete)
			{
				askMessage = sets.getDefault_path() + sets.getLang().getText(Language.UNINSTALL_AFTER_ASK);
				deleted = sets.getLang().getText(Language.UNINSTALL_AFTER_DELETED);
				completed = sets.getLang().getText(Language.UNINSTALL_AFTER_COMPLETED);
				error_messages = sets.getLang().getText(Language.UNINSTALL_AFTER_ERROR);				
			}
			else
			{
				askMessage = sets.getDefault_path() + sets.getLang().getText(Language.UNINSTALL_ASK);
				deleted = sets.getLang().getText(Language.UNINSTALL_DELETED);
				completed = sets.getLang().getText(Language.UNINSTALL_COMPLETED);
				error_messages = sets.getLang().getText(Language.UNINSTALL_ERROR);			
			}
			File dirs = new File(sets.getDefault_path());
			if(dirs.exists())
			{
				if(gui)
				{
					int selectGetter = JOptionPane.YES_OPTION;
					if(askAgain)
						selectGetter = JOptionPane.showConfirmDialog(null, askMessage, "Uninstall", JOptionPane.YES_NO_OPTION);
					if(selectGetter == JOptionPane.YES_OPTION)
					{						
						File[] lists = dirs.listFiles();
						uninstall_progress_show(sets, spComp);
						dialog.setVisible(true);
						progressBar.setValue(progressNow);						
						progressBar.setMaximum(progressEnd);
						for(int i=0; i<lists.length; i++)
						{
							try
							{
								console.message(deleted + lists[i].getAbsolutePath());
								if(afterDelete) lists[i].deleteOnExit();
								else lists[i].delete();
								progressNow = (int) (((double) i  / (double) lists.length)  * 980);
								progressBar.setValue(progressNow);
							} 
							catch (Exception e)
							{
								console.message(st.getLang().getText(Language.ERROR) + " : " + e.getMessage());
								errored = true;
								error_messages += e.getMessage() + "\n";
							}
						}
						try
						{
							console.message(deleted + dirs.getAbsolutePath());
							if(afterDelete) dirs.deleteOnExit();
							else dirs.delete();
						}
						catch (Exception e)
						{
							console.message(st.getLang().getText(Language.ERROR) + " : " + e.getMessage());
							errored = true;
							error_messages += e.getMessage() + "\n";
						}
						finally
						{
							if(dialog != null)
								dialog.setVisible(false);
						}
						
						if(errored)
						{
							JOptionPane.showMessageDialog(null, error_messages);
						}
						else
						{
							JOptionPane.showMessageDialog(null, completed);
						}
					}
				}
				else
				{
					while(true)
					{
						System.out.print(askMessage + " (y/n)");
						InputStreamReader inp = new InputStreamReader(System.in);
						BufferedReader bfr = new BufferedReader(inp);
						String readed = bfr.readLine();
						if(readed.equalsIgnoreCase("y"))
						{
							File[] lists = dirs.listFiles();
							for(int i=0; i<lists.length; i++)
							{
								try
								{
									console.message(deleted + lists[i].getAbsolutePath());
									if(afterDelete) lists[i].deleteOnExit();
									else lists[i].delete();
								} 
								catch (Exception e)
								{
									console.message(st.getLang().getText(Language.ERROR) + " : " + e.getMessage());
									errored = true;
									error_messages += e.getMessage() + "\n";
								}
							}
							try
							{
								console.message(deleted + dirs.getAbsolutePath());
								if(afterDelete) dirs.deleteOnExit();
								else dirs.delete();
							}
							catch (Exception e)
							{
								console.message(st.getLang().getText(Language.ERROR) + " : " + e.getMessage());
								errored = true;
								error_messages += e.getMessage() + "\n";
							}
							
							if(errored)
							{
								System.out.println(error_messages);
							}
							else
							{
								System.out.println(completed);
							}
							break;
						}
						else if(readed.equalsIgnoreCase("n"))
						{
							break;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			console.message(st.getLang().getText(Language.ERROR) + " : " + e.getMessage());
		}
		if(afterExit)
		{
			System.exit(0);
		}
	}
}
