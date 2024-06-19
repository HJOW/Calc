package ai_algorithm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

import main_classes.Openable;
import setting.Setting;

public class AIActedDialog extends JDialog implements Runnable, Openable
{
	private static final long serialVersionUID = 8849324653379013767L;
	private JPanel mainPanel;
	private boolean threadSwitch = true;
	private Thread thread;
	private int value = 0, maximum = 100;
	private JProgressBar progressBar;
	private JLabel centerLabel;
	private boolean active = false;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JLabel upLabel;
	private JLabel downLabel;
	private JPanel downLabelPanel;
	
	public AIActedDialog(Setting set, JFrame upper)
	{
		super(upper, false);
		this.setUndecorated(true);
		this.setSize(400, 300);
		center();
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setLayout(new BorderLayout());
		centerPanel = new JPanel();		
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerLabel = new JLabel();
		upLabel = new JLabel();
		downLabel = new JLabel();
		centerLabel.setFont(new Font(null, Font.BOLD, 40));
		
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		//mainPanel.add(progressBar, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new FlowLayout());
		downPanel.setLayout(new BorderLayout());
		
		upPanel.add(upLabel);
		downLabelPanel = new JPanel();
		downPanel.add(downLabelPanel, BorderLayout.CENTER);		
		downPanel.add(progressBar, BorderLayout.SOUTH);
		downLabelPanel.setLayout(new FlowLayout());
		downLabelPanel.add(downLabel);
		
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(centerLabel);
		
		mainPanel.setBackground(set.getSelected_back());
		centerPanel.setBackground(set.getSelected_back());
		upPanel.setBackground(set.getSelected_back());
		downPanel.setBackground(set.getSelected_back());
		centerLabel.setForeground(set.getSelected_fore());
		upLabel.setForeground(set.getSelected_fore());
		downLabel.setForeground(set.getSelected_fore());
		downLabelPanel.setBackground(set.getSelected_back());
		
		thread = new Thread(this);
		thread.start();
	}
	public boolean isThreadSwitch()
	{
		return threadSwitch;
	}
	public void setThreadSwitch(boolean threadSwitch)
	{
		this.threadSwitch = threadSwitch;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public int getMaximum()
	{
		return maximum;
	}
	public void setMaximum(int maximum)
	{
		this.maximum = maximum;
	}
	public boolean isActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	public void center()
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screen.getWidth()/2 - this.getWidth()/2), (int)(screen.getHeight()/2 - this.getHeight()/2));
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{			
			if(active)
			{
				if(value >= maximum)
				{
					exit();
				}
				else
				{
					value++;
					progressBar.setValue((int)(((double)value / (double)maximum) * 100));
				}
				try
				{
					Thread.sleep(20);
				}
				catch(Exception e)
				{
					
				}
			}
		}		
	}
	public void open(String message)
	{
		centerLabel.setText(message);
		open();
	}
	public void open(String upMessage, String centerMessage, String downMessage)
	{
		upLabel.setText(upMessage);
		centerLabel.setText(centerMessage);
		downLabel.setText(downMessage);
		open();
	}
	@Override
	public void open()
	{			
		value = 0;
		this.setVisible(true);	
		active = true;
	}
	@Override
	public void exit()
	{
		active = false;
		this.setVisible(false);		
	}
}
