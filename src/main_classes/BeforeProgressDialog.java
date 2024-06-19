package main_classes;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import lang.Language;
import setting.Setting;

public class BeforeProgressDialog extends Frame implements ActionListener, Openable, Runnable
{
	private static final long serialVersionUID = 2438713500715589367L;
	private static BeforeProgressDialog progress = null;
	private Panel mainPanel;
	private Panel titlePanel;
	private Panel controlPanel;
	private Label title;
	private Label progressBar;
	private Button close;
	//private Thread thread;
	private volatile boolean threadSwitch = true;
	private Panel centerPanel;
	private long progresses = 0;
	public static BeforeProgressDialog getInstance()
	{
		if(progress == null)
		{
			progress = new BeforeProgressDialog();
			progress.init();
		}		
		return progress;
	}
	private BeforeProgressDialog()
	{
		super();
	}
	private void init()
	{
		Setting sets = Setting.getNewInstance();
		this.setUndecorated(true);
		this.setSize(200, 80);
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - this.getHeight()/2));
		mainPanel = new Panel();
		this.setLayout(new BorderLayout());
		this.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		titlePanel = new Panel();
		controlPanel = new Panel();
		centerPanel = new Panel();
		mainPanel.add(titlePanel, BorderLayout.CENTER);
		mainPanel.add(centerPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(controlPanel, BorderLayout.CENTER);		
		title = new Label(sets.getLang().getText(Language.LOAD));
		title.setFont(new Font(null, Font.BOLD, 25));
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(title);
		progressBar = new Label();
		//progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		//progressBar.setStringPainted(true);
		//progressBar.setString(String.valueOf(0));
		//centerPanel.add(progressBar2, BorderLayout.SOUTH);
		close = new Button(sets.getLang().getText(Language.X));
		close.addActionListener(this);
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(progressBar, BorderLayout.CENTER);
		controlPanel.add(close, BorderLayout.EAST);
		titlePanel.setBackground(Color.DARK_GRAY);
		title.setForeground(Color.GRAY);
		progresses = 0;
		//thread = new Thread(this);
	}
	public void setValue(int value)
	{		
		String str = "";
		for(int i=0; i<value; i++)
			str = str + "l";
		progressBar.setText(str);
	}
	public void setValue(double value)
	{
		String str = "";
		for(int i=0; i<(int)(value * 100); i++)
		{
			str = str + "l";
		}
		
		progressBar.setText(str);
	}
	public long getProgresses()
	{
		return progresses;
	}
	public void setProgresses(long progresses)
	{
		this.progresses = progresses;
	}
	public static void progress(int value)
	{
		progress.setValue(value);
	}
	public static void progress(long value)
	{
		try
		{
			progress.setValue(value);
		
		}
		catch(Exception e)
		{
			
		}
	}
	public static void progress(double value)
	{
		try
		{
			progress.setValue(value);
		}
		catch(Exception e)
		{
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{
			System.exit(0);
		}
	}

	@Override
	public void open()
	{
		this.setVisible(true);
		setValue(0);
		threadSwitch = true;
		//thread.start();
	}
	
	@Override
	public void exit()
	{
		threadSwitch = false;
		this.setVisible(false);		
	}
	public void closeThis()
	{
		threadSwitch = false;
		this.setVisible(false);
	}
	public static void close()
	{
		progress.closeThis();
	}
	public static long getProgress()
	{
		return progress.getProgresses();
		
	}
	public static void showNow()
	{
		BeforeProgressDialog.getInstance().open();
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{
			
		}		
	}
}
