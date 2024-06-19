package reflex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import setting.Difficulty;
import setting.Setting;
import lang.Korean;
import lang.Language;
import main_classes.CalcWindow;
import main_classes.MouseDragCatcher;
import main_classes.Openable;
import main_classes.RunManager;

public class ReflexReplayPlayer extends MouseDragCatcher implements ActionListener, Openable, Runnable
{
	private int progress = 0;
	private int speed = 1;
	private boolean playing = false;
	private boolean pause = false;
	private boolean threadSwitch = true;
	private boolean loadReplay = false;
	private Window window;
	private Setting sets;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private ReflexReplay playing_replay = null;
	private ReplayCanvas replayCanvas;
	private JFileChooser fileChooser;
	private FileFilter fileFilter;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem menu_file_load;
	private JMenuItem menu_file_exit;
	private JButton bt_play;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mouse_x;
	private int mouse_y;
	private JPanel statusPanel;
	private JLabel pointLabel;
	private JTextField pointField;
	private JLabel progressLabel;
	private JTextField progressField;
	private JLabel hpLabel;
	private JProgressBar hpBar;
	private JLabel energyLabel;
	private JProgressBar energyBar;
	private JCheckBox authCheck;
	private JMenuItem menu_file_play;
	private JButton bt_stop;
	private int sleepTime;
	private JTextField playSpeedField;
	private JMenuItem menu_file_speed;
	private JMenuItem menu_file_continue;
	private Reflexioner reflex;
	private ReflexSave nowDraw = null;
	private FileFilter fileFilter2;
	private JDialog statusDialog;
	private JPanel statusDialog_mainPanel;
	private JPanel status_upPanel;
	private JPanel status_downPanel;
	private JPanel status_centerPanel;
	private JPanel status_titlePanel;
	private int status_x;
	private int status_y;
	private JMenu menu_view;
	private JMenuItem menu_view_status;
	private boolean status_on;
	private JButton bt_status_close;
	private JTextArea statusArea;
	private JScrollPane statusScroll;
	private JLabel status_titleLabel;
	private JTextArea statusArea2;
	private JScrollPane statusScroll2;
	private JSplitPane status_spinPanel;
	private JDialog loadingDialog;
	private JPanel loadingPanel;
	private JPanel loading_centerPanel;
	private JPanel loading_eastPanel;
	private JLabel loading_centerLabel;
	private JButton bt_loading_cancel;
	private FileInputStream stream;
	private GZIPInputStream zips;
	private ObjectInputStream obj;
	private File loadFile;
	private JButton bt_detail;
	private JTextField status_authField;
	
	public ReflexReplayPlayer(JDialog dialog, Setting sets, Reflexioner refl)
	{
		window = new JDialog(dialog);
		this.sets = sets;
		this.reflex = refl;
		init();
	}
	public ReflexReplayPlayer(JFrame dialog, Setting sets, Reflexioner refl)
	{
		window = new JDialog(dialog);
		this.sets = sets;
		this.reflex = refl;
		init();
	}
	public void init()
	{
		window.setLayout(new BorderLayout());
		if(window instanceof JDialog)
		{
			((JDialog)window).setUndecorated(true);
		}
		else if(window instanceof JFrame)
		{
			((JFrame)window).setUndecorated(true);
		}
		window.setSize(Reflexioner.getSize_x() + 100, Reflexioner.getSize_y() + 50);
		window.setLocation((int)(sets.getScreenSize().getWidth()/2 - window.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - window.getHeight()/2));
		mainPanel = new JPanel();
		window.add(mainPanel, BorderLayout.CENTER);
		
		ImageCache.prepareImage(sets);
		SoundCache.prepareSound(sets);
		
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(sets.getSelected_back());
		
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerPanel = new JPanel();
		
		upPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		upPanel.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		upPanel.add(menuBar, BorderLayout.CENTER);		
		menuBar.setBackground(sets.getSelected_inside_back());
		
		titlePanel = new JPanel();
		upPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setBackground(sets.getSelected_inside_back());
		titlePanel.addMouseListener(this);
		titlePanel.addMouseMotionListener(this);
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(sets.getLang().getText(Language.REPLAY));
		titleLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			titleLabel.setFont(CalcWindow.usingFont);
		}
		titlePanel.add(titleLabel);
		
		menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		menu_file.setBackground(sets.getSelected_back());
		menu_file.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file.setFont(CalcWindow.usingFont);
		}
		menuBar.add(menu_file);
		
		menu_view = new JMenu(sets.getLang().getText(Language.VIEW));
		menu_view.setBackground(sets.getSelected_back());
		menu_view.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_view.setFont(CalcWindow.usingFont);
		}
		menuBar.add(menu_view);
		
		menu_file_play = new JMenuItem(sets.getLang().getText(Language.PLAY));
		menu_file_play.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));	
		menu_file_play.addActionListener(this);
		menu_file_play.setBackground(sets.getSelected_inside_back());
		menu_file_play.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_play.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_play);
		
		menu_file_speed = new JMenuItem();
		menu_file_speed.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_MASK));	
		menu_file_speed.addActionListener(this);
		menu_file_speed.setBackground(sets.getSelected_inside_back());
		menu_file_speed.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_speed.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_speed);
		
		if(sets.getLang() instanceof Korean)
		{
			menu_file_speed.setText("배속 설정");
		}
		else
		{
			menu_file_speed.setText("Speed");
		}
		
		menu_file_load = new JMenuItem(sets.getLang().getText(Language.LOAD));
		menu_file_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));	
		menu_file_load.addActionListener(this);
		menu_file_load.setBackground(sets.getSelected_inside_back());
		menu_file_load.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_load.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_load);
		
		menu_file_continue = new JMenuItem();
		menu_file_continue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, KeyEvent.CTRL_MASK));	
		menu_file_continue.addActionListener(this);
		menu_file_continue.setBackground(sets.getSelected_inside_back());
		menu_file_continue.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_continue.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_continue);
		
		if(sets.getLang() instanceof Korean)
		{
			menu_file_continue.setText("이어서 "+ sets.getLang().getText(Language.START));
		}
		else			
		{
			menu_file_continue.setText("Continue, "+ sets.getLang().getText(Language.START));
		}
		
		
		menu_file_exit = new JMenuItem(sets.getLang().getText(Language.CLOSE));
		menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		menu_file_exit.addActionListener(this);
		menu_file_exit.setBackground(sets.getSelected_inside_back());
		menu_file_exit.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_exit.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_exit);
		
		menu_view_status = new JMenuItem(sets.getLang().getText(Language.DETAIL));
		menu_view_status.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, KeyEvent.ALT_MASK));
		menu_view_status.addActionListener(this);
		menu_view_status.setBackground(sets.getSelected_inside_back());
		menu_view_status.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_view_status.setFont(CalcWindow.usingFont);
		}
		menu_view.add(menu_view_status);
		
		
		centerPanel.setLayout(new BorderLayout());
		
		replayCanvas = new ReplayCanvas();
		replayCanvas.loadBackground(sets.getDefault_path());
		replayCanvas.setSize(Reflexioner.getSize_x(), Reflexioner.getSize_y());
		centerPanel.add(replayCanvas);
		
		downPanel.setLayout(new FlowLayout());
		
		bt_play = new JButton("▶");
		bt_play.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_play.setBackground(sets.getSelected_button());
		bt_play.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_play.setFont(CalcWindow.usingFont);
		downPanel.add(bt_play);
					
		bt_stop = new JButton("||");
		bt_stop.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_stop.setBackground(sets.getSelected_button());
		bt_stop.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_stop.setFont(CalcWindow.usingFont);
		downPanel.add(bt_stop);
		
		bt_detail = new JButton(sets.getLang().getText(Language.DETAIL));
		bt_detail.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_detail.setBackground(sets.getSelected_button());
		bt_detail.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_detail.setFont(CalcWindow.usingFont);
		
		statusPanel = new JPanel();
		statusPanel.setBackground(sets.getSelected_back());
		
		upPanel.add(statusPanel, BorderLayout.SOUTH);
		
		statusPanel.setLayout(new FlowLayout());
		pointLabel = new JLabel(sets.getLang().getText(Language.POINT));
		pointField = new JTextField(20);
		progressLabel = new JLabel(sets.getLang().getText(Language.RUN));
		progressField = new JTextField(15);
		playSpeedField = new JTextField(2);
		pointField.setEditable(false);
		progressField.setEditable(false);
		playSpeedField.setEditable(false);
		pointField.setBorder(new EtchedBorder());
		playSpeedField.setBorder(new EtchedBorder());
		progressField.setBorder(new EtchedBorder());
		pointLabel.setForeground(sets.getSelected_fore());
		pointField.setForeground(sets.getSelected_fore());
		progressLabel.setForeground(sets.getSelected_fore());
		progressField.setForeground(sets.getSelected_fore());
		pointField.setBackground(sets.getSelected_inside_back());
		progressField.setBackground(sets.getSelected_inside_back());
		playSpeedField.setForeground(sets.getSelected_fore());
		playSpeedField.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
		{
			pointLabel.setFont(CalcWindow.usingFont);
			pointField.setFont(CalcWindow.usingFont);
			progressLabel.setFont(CalcWindow.usingFont);
			progressField.setFont(CalcWindow.usingFont);
			playSpeedField.setFont(CalcWindow.usingFont);
		}
		statusPanel.add(pointLabel);
		statusPanel.add(pointField);
		statusPanel.add(progressLabel);
		statusPanel.add(progressField);
		statusPanel.add(playSpeedField);
		
		hpLabel = new JLabel(sets.getLang().getText(Language.HP));		
		hpBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 1000);
		energyLabel = new JLabel("E");		
		energyBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 1000);
		authCheck = new JCheckBox(sets.getLang().getText(Language.AUTHORITY));
		authCheck.setEnabled(false);
		
		hpLabel.setForeground(sets.getSelected_fore());
		energyLabel.setForeground(sets.getSelected_fore());
		authCheck.setForeground(sets.getSelected_fore());
		authCheck.setBackground(sets.getSelected_back());
		if(CalcWindow.usingFont != null)
		{
			hpLabel.setFont(CalcWindow.usingFont);
			energyLabel.setFont(CalcWindow.usingFont);
			authCheck.setFont(CalcWindow.usingFont);
		}
		downPanel.add(hpLabel);
		downPanel.add(hpBar);
		downPanel.add(energyLabel);
		downPanel.add(energyBar);
		downPanel.add(authCheck);
		downPanel.add(bt_detail);
		
		
		statusDialog = new JDialog(window);
		statusDialog.setUndecorated(true);
		statusDialog.setSize(600, 200);
		statusDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - statusDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - statusDialog.getHeight()/2));
		statusDialog_mainPanel = new JPanel();
		statusDialog.setLayout(new BorderLayout());
		statusDialog.add(statusDialog_mainPanel);
		statusDialog_mainPanel.setLayout(new BorderLayout());
		statusDialog_mainPanel.setBackground(sets.getSelected_back());
		statusDialog_mainPanel.setBorder(new EtchedBorder());
		status_upPanel = new JPanel();
		status_downPanel = new JPanel();
		status_centerPanel = new JPanel();
		statusDialog_mainPanel.add(status_upPanel, BorderLayout.NORTH);
		statusDialog_mainPanel.add(status_downPanel, BorderLayout.SOUTH);
		statusDialog_mainPanel.add(status_centerPanel, BorderLayout.CENTER);
		status_upPanel.setBackground(sets.getSelected_back());
		status_downPanel.setBackground(sets.getSelected_back());
		status_centerPanel.setBackground(sets.getSelected_back());
		status_upPanel.setLayout(new BorderLayout());
		status_centerPanel.setLayout(new BorderLayout());
		status_spinPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		status_centerPanel.add(status_spinPanel, BorderLayout.CENTER);
		status_titleLabel = new JLabel(sets.getLang().getText(Language.DETAIL));
		status_titlePanel = new JPanel();
		status_titlePanel.setBorder(new EtchedBorder());
		status_titlePanel.setBackground(sets.getSelected_inside_back());
		status_titleLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			status_titleLabel.setFont(CalcWindow.usingFont);
		status_titlePanel.addMouseListener(this);
		status_titlePanel.addMouseMotionListener(this);
		status_titlePanel.setLayout(new FlowLayout());
		status_titlePanel.add(status_titleLabel);
		status_upPanel.add(status_titlePanel, BorderLayout.CENTER);
		bt_status_close = new JButton(sets.getLang().getText(Language.X));
		bt_status_close.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_status_close.setBackground(sets.getSelected_button());
		bt_status_close.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_status_close.setFont(CalcWindow.usingFont);
		status_upPanel.add(bt_status_close, BorderLayout.EAST);
		statusArea = new JTextArea();
		statusArea.setLineWrap(true);
		statusArea.setEditable(false);
		statusArea.setBackground(sets.getSelected_inside_back());
		statusArea.setForeground(sets.getSelected_fore());		
		if(CalcWindow.usingFont != null)
			statusArea.setFont(CalcWindow.usingFont);
		statusArea2 = new JTextArea();
		statusArea2.setEditable(false);
		statusArea2.setBackground(sets.getSelected_inside_back());
		statusArea2.setForeground(sets.getSelected_fore());		
		if(CalcWindow.usingFont != null)
			statusArea2.setFont(CalcWindow.usingFont);
		statusScroll = new JScrollPane(statusArea);
		statusScroll2 = new JScrollPane(statusArea2);
		status_downPanel.setLayout(new BorderLayout());
		status_authField = new JTextField();
		status_authField.setEditable(false);
		status_authField.setBorder(new EtchedBorder());
		status_authField.setBackground(sets.getSelected_inside_back());
		status_authField.setForeground(sets.getSelected_fore());		
		if(CalcWindow.usingFont != null)
			status_authField.setFont(CalcWindow.usingFont);
		status_downPanel.add(status_authField);
		
		//status_centerPanel.add(statusScroll);
		//status_centerPanel.add(statusScroll2);
		status_spinPanel.setBackground(sets.getSelected_back());
		status_spinPanel.setForeground(sets.getSelected_fore());		
		status_spinPanel.setLeftComponent(statusScroll);
		status_spinPanel.setRightComponent(statusScroll2);
		status_spinPanel.setDividerLocation(statusDialog.getWidth() / 3);
		
		loadingDialog = new JDialog(window);
		loadingDialog.setUndecorated(true);
		loadingDialog.setSize(150, 100);
		loadingDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - loadingDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - loadingDialog.getHeight()/2));
		loadingDialog.setLayout(new BorderLayout());
		loadingPanel = new JPanel();
		loadingDialog.add(loadingPanel, BorderLayout.CENTER);
		loadingPanel.setBorder(new EtchedBorder());
		loadingPanel.setLayout(new BorderLayout());
		loading_centerPanel = new JPanel();		
		loading_eastPanel = new JPanel();
		loadingPanel.add(loading_centerPanel, BorderLayout.CENTER);
		loadingPanel.add(loading_eastPanel, BorderLayout.SOUTH);
		loading_centerPanel.setBackground(sets.getSelected_back());
		loading_eastPanel.setBackground(sets.getSelected_back());
		loading_centerPanel.setLayout(new FlowLayout());
		loading_eastPanel.setLayout(new FlowLayout());
		loading_centerLabel = new JLabel(sets.getLang().getText(Language.LOAD) + "...");
		loading_centerLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			loading_centerLabel.setFont(CalcWindow.usingFont);
		loading_centerPanel.add(loading_centerLabel);
		bt_loading_cancel = new JButton(sets.getLang().getText(Language.X));
		bt_loading_cancel.addActionListener(this);
		bt_loading_cancel.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null) bt_loading_cancel.setBackground(sets.getSelected_button());
		if(CalcWindow.usingFont != null)
			bt_loading_cancel.setFont(CalcWindow.usingFont);
		loading_eastPanel.add(bt_loading_cancel);
		
		//loadingDialog.setVisible(true);
		
		
		new Thread(this).start();
		
		refresh();
	}
	public void refresh()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if(playing_replay == null) bt_play.setEnabled(false);
				else if(playing && (! pause)) bt_play.setEnabled(false);
				else bt_play.setEnabled(true);
				
				if(playing_replay == null) bt_stop.setEnabled(false);
				else if(playing && (! pause)) bt_stop.setEnabled(true);
				else bt_stop.setEnabled(false);
				
				menu_file_continue.setEnabled((pause && playing));
				if(playing_replay instanceof BReflexReplay)
				{
					if(((BReflexReplay) playing_replay).getContinue_abandoned() != null)
					{
						if(((BReflexReplay) playing_replay).getContinue_abandoned().booleanValue())
						{
							menu_file_continue.setEnabled(false);
						}
					}
				}
				
				menu_file_play.setEnabled(bt_play.isEnabled());
				if(playing_replay != null)
				{
					authCheck.setSelected(playing_replay.isAuthorized());
				}
				status_refresh();
			}			
		});	
		replayCanvas.repaint();
	}
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == menu_file_load)
		{
			loadReplay();
		}
		else if(ob == bt_loading_cancel)
		{			
			try
			{
				obj.close();
			} 
			catch (Exception e1)
			{
				
			}
			try
			{
				zips.close();
			} 
			catch (Exception e1)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (Exception e1)
			{
				
			}
			playing = false;
			pause = false;
			playing_replay = null;
			stream = null;
			loadingDialog.setVisible(false);
		}
		else if(ob == menu_file_continue)
		{
			if(nowDraw != null)
			{
				window.setVisible(false);
				reflex.start(playing_replay, progress);				
			}
		}
		else if(ob == menu_view_status || ob == bt_detail)
		{
			status_on = true;
			statusDialog.setVisible(true);
		}
		else if(ob == bt_status_close)
		{
			status_on = false;
			statusDialog.setVisible(false);
		}
		else if(ob == menu_file_speed)
		{
			String inputs, message;
			if(sets.getLang() instanceof Korean)
			{
				message = "재생 속도를 지정합니다.\n입력한 숫자 만큼 재생 속도가 빨라집니다.";
			}
			else
			{
				message = "Set playing speed.";
			}
			inputs = JOptionPane.showInputDialog(window, message, String.valueOf(speed));
			try
			{
				speed = Integer.parseInt(inputs);
			}
			catch(NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(window, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == bt_play || ob == menu_file_play)
		{
			if(pause && playing)
			{
				pause = false;
			}
			else
			{
				progress = 0;
				playing = true;				
			}
			refresh();
		}
		else if(ob == bt_stop)
		{
			pause = true;
			refresh();
		}
	}
	@Override
	public void open()
	{
		window.setVisible(true);
		
	}

	@Override
	public void exit()
	{
		try
		{
			obj.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			zips.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			stream.close();
		} 
		catch (Exception e1)
		{
			
		}
		playing = false;
		window.setVisible(false);
		
	}
	void off_thread()
	{
		threadSwitch = false;
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{
			try
			{
				if(playing && (! pause))
				{					
					try
					{
						replayCanvas.repaint();
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					if(speed >= 1)
					{
						progress++;
						if(playing_replay == null || (progress >= playing_replay.getScenes().size() ))
						{
							playing = false;
							refresh();
						}
					}
					else if(speed <= -1)
					{
						progress--;
						if(playing_replay == null || (progress <= 0 ))
						{
							playing = false;
							refresh();
						}
					}
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(loadReplay)
				{
					if(loadFile != null)
						loadCore(loadFile);
					loadReplay = false;
				}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				sleepTime = 37 + (int)(Math.random() * 5);
				if(playing_replay != null)
				{
					if(playing_replay.getReplay_interval() != null)
					{
						sleepTime = sleepTime * playing_replay.getReplay_interval().intValue();
					}
				}
				if(speed >= 1)
					sleepTime = (int)Math.round(sleepTime / (double)speed);
				if(sleepTime < 0) sleepTime = sleepTime * -1;
				if(sleepTime < 5) sleepTime = 5;
				Thread.sleep(sleepTime);
			}
			catch(Exception e)
			{
				
			}
		}		
	}
	class ReplayCanvas extends JPanel
	{
		private static final long serialVersionUID = -5183583381445319534L;
		private BufferedImage backgroundImage;
		private List<String> finishMessages;
		private String showReplayTitle;
		
		public ReplayCanvas()
		{
			finishMessages = new Vector<String>();
		}
		public void paint(Graphics g)
		{
			super.paint(g);
			if(playing && playing_replay != null)
			{
				nowDraw = playing_replay.getScenes().get(progress);
				
				try
				{
					g.setColor(nowDraw.getSpaceShip().getColor());				
					nowDraw.getSpaceShip().draw(g);
				}				
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getEnemies().size(); i++)
					{
						g.setColor(nowDraw.getEnemies().get(i).getColor());
						nowDraw.getEnemies().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getOurEnemy().size(); i++)
					{
						g.setColor(nowDraw.getOurEnemy().get(i).getColor());
						nowDraw.getOurEnemy().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getMissile().size(); i++)
					{
						//System.out.println("Missiles " + nowDraw.getMissile().get(i));
						nowDraw.getMissile().get(i).setImage(null);
						g.setColor(nowDraw.getMissile().get(i).getColor());
						nowDraw.getMissile().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getBooms().size(); i++)
					{
						g.setColor(nowDraw.getBooms().get(i).getColor());
						nowDraw.getBooms().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getItems().size(); i++)
					{
						g.setColor(nowDraw.getItems().get(i).getColor());
						nowDraw.getItems().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					for(int i=0; i<nowDraw.getDecorates().size(); i++)
					{
						g.setColor(nowDraw.getDecorates().get(i).getColor());
						nowDraw.getDecorates().get(i).draw(g);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				try
				{
					if(nowDraw.getAutoControl() != null && nowDraw.getAutoControl().booleanValue())
					{
						g.setColor(Color.MAGENTA);
						if(CalcWindow.usingFont != null) g.setFont(CalcWindow.usingFont);
						g.drawString(sets.getLang().getText(Language.AI), 5, 15);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						pointField.setText(nowDraw.getSpaceShip().getPoint().toString());
						progressField.setText(String.valueOf(progress) + " / " + String.valueOf(playing_replay.getScenes().size()));
						hpBar.setValue( (int) Math.round((nowDraw.getSpaceShip().getHp() / (double)nowDraw.getSpaceShip().getMax_hp()) * 1000.0) );
						energyBar.setValue( (int) Math.round((nowDraw.getSpaceShip().getEnergy() / (double)nowDraw.getSpaceShip().getMax_energy()) * 1000.0) );
						playSpeedField.setText("×" + String.valueOf(speed));
						if(status_on)
						{
							status_refresh();
						}
					}					
				});
			}
			if(playing_replay != null)
			{
				try
				{
					if(progress >= playing_replay.getScenes().size())
					{
						if(finishMessages == null) finishMessages = new Vector<String>();
						else finishMessages.clear();
						if(playing_replay.getName() != null) finishMessages.add(sets.getLang().getText(Language.NAME) + " : " + playing_replay.getName());
						if(playing_replay instanceof AReflexReplay)
						{
							AReflexReplay playing_ar = (AReflexReplay) playing_replay;
							if(playing_ar.getFinal_point() != null) finishMessages.add(sets.getLang().getText(Language.POINT) + " : " + playing_ar.getFinal_point().toString());
							
						}						
						try
						{
							finishMessages.add("");
							finishMessages.add(sets.getLang().getText(Language.REFLEX) + " v" + String.valueOf(playing_replay.getVer_m()) + "." + String.valueOf(playing_replay.getVer_s1() + String.valueOf(playing_replay.getVer_s2())
									+ String.valueOf(playing_replay.getVer_test().charValue()) + " n" + String.valueOf(playing_replay.getVer_nightly())));
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
						
						g.setColor(Color.MAGENTA);
						if(CalcWindow.usingFont != null) g.setFont(CalcWindow.usingFont2B);
						if(playing_replay instanceof BReflexReplay)
						{
							if(((BReflexReplay) playing_replay).getReplayTitle() != null)
							{
								showReplayTitle = ((BReflexReplay) playing_replay).getReplayTitle();
							}
							else showReplayTitle = sets.getLang().getText(Language.COMPLETE);
						}
						else showReplayTitle = sets.getLang().getText(Language.COMPLETE);
						g.drawString(showReplayTitle, (Reflexioner.getSize_x() / 2) - (showReplayTitle.length() * 9), Reflexioner.getSize_y() / 4);
						if(CalcWindow.usingFont != null) g.setFont(CalcWindow.usingFont);
						for(int s=0; s<finishMessages.size(); s++)
						{
							g.drawString(finishMessages.get(s), (Reflexioner.getSize_x() / 2) - (finishMessages.get(s).length() * 3), (Reflexioner.getSize_y() / 3) + (s * 30));
						}
					}
				} 
				catch (Exception e)
				{
					
				}
			}
		}
		public void loadBackground(String path)
		{
			File w;
			
			if(Reflexioner.image_allow)
			{
				try
				{
					w = new File(path + RunManager.r65279("background.png"));
					if(w.exists())
					{
						backgroundImage = ImageIO.read(w);
					}
					else
					{
						w = new File(path + RunManager.r65279("﻿background.jpg"));
						if(w.exists())
						{
							backgroundImage = ImageIO.read(w);
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(backgroundImage != null && Reflexioner.image_allow)
			{
				g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
			}
		}
	}
	
	private void prepareReplayFileChooser()
	{
		if(fileChooser == null)
		{
			fileChooser = new JFileChooser(new File(sets.getDefault_path()));
			if(CalcWindow.usingFont != null)
			{				
				CalcWindow.setFontRecursively(fileChooser, CalcWindow.usingFont);
			}
		}
		if(fileFilter == null)
		{
			fileFilter = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rxrp")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Save Replay (.rxrp)";
				}
			};
		}
		if(fileFilter2 == null)
		{
			fileFilter2 = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rxrz")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Compressed Save Replay (.rxrz)";
				}
			};
		}
		
		fileChooser.setFileFilter(fileFilter);
		fileChooser.addChoosableFileFilter(fileFilter2);
	}
	private void loadCore(File file) throws Exception
	{
		File target = file;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				loadingDialog.setVisible(true);
			}					
		});
		
		try
		{
			obj.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			zips.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			stream.close();
		} 
		catch (Exception e1)
		{
			
		}
		
		
		if(! (target.getAbsolutePath().endsWith(".rxrp") || target.getAbsolutePath().endsWith(".RXRP") || target.getAbsolutePath().endsWith(".Rxrp")
				|| target.getAbsolutePath().endsWith(".rxrz") || target.getAbsolutePath().endsWith(".RXRZ") || target.getAbsolutePath().endsWith(".Rxrz")))
		{
			target = new File(target.getAbsolutePath() + ".rxrp");
		}
						
		
		stream = new FileInputStream(target);
		
		if(target.getAbsolutePath().endsWith(".rxrz") || target.getAbsolutePath().endsWith(".RXRZ") || target.getAbsolutePath().endsWith(".Rxrz"))
		{
			zips = new GZIPInputStream(stream);
		}				
		if(zips == null)
		{
			obj = new ObjectInputStream(stream);
		}
		else
		{
			obj = new ObjectInputStream(zips);
		}
		
		playing_replay = (ReflexReplay) obj.readObject();
		
		progress = 0;
		playing = false;
		loadFile = null;
		nowDraw = playing_replay.getScenes().get(0);
		
		if(playing_replay instanceof AReflexReplay)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						status_authField.setText(((AReflexReplay) playing_replay).getAuthority_code());
					} 
					catch (Exception e)
					{
						
					}									
				}								
			});
		}
		try
		{
			for(ReflexSave s : playing_replay.getScenes())
			{
				s.loadImages(sets.getDefault_path());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				loadingDialog.setVisible(false);
			}					
		});
		try
		{
			obj.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			zips.close();
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			stream.close();
		} 
		catch (Exception e1)
		{
			
		}
		if(playing_replay instanceof CReflexReplay)
		{
			if(((CReflexReplay) playing_replay).getPassword() != null)
			{
				String passwd = JOptionPane.showInputDialog(window, ((CReflexReplay) playing_replay).getPassword_hint());
				if(! (((CReflexReplay) playing_replay).getPassword().equals(passwd)))
				{
					playing_replay = null;
					playing = false;
					progress = 0;
				}
			}
		}
		refresh();
	}
	private void loadReplay()
	{
		if(fileChooser == null)
			prepareReplayFileChooser();
		
		playing = false;
		pause = false;
		zips = null;
		stream = null;
		obj = null;
		try
		{
			if(fileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION)
			{
				loadFile = fileChooser.getSelectedFile();
				loadReplay = true;
				
			}
			else
			{
				
			}
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(window, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				obj.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				zips.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (Exception e)
			{
				
			}
			obj = null;
			zips = null;
			stream = null;
		}
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			mouse_x = e.getX();
			mouse_y = e.getY();
		}
		else if(ob == status_titlePanel)
		{
			status_x = e.getX();
			status_y = e.getY();
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			window.setLocation(e.getXOnScreen() - mouse_x, e.getYOnScreen() - mouse_y);
		}
		else if(ob == status_titlePanel)
		{
			statusDialog.setLocation(e.getXOnScreen() - status_x, e.getYOnScreen() - status_y);
		}
	}
	private void status_refresh()
	{
		StringBuffer results = new StringBuffer("");
		StringBuffer results2 = new StringBuffer("");
		
		if(playing_replay == null || nowDraw == null)
		{
			statusArea.setText("");
			statusArea2.setText("");
			statusArea.setCaretPosition(0);
			statusArea2.setCaretPosition(0);
			return;
		}
		
		try
		{
			results = results.append("Ship \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getKeyName())); 
			if(nowDraw.getAutoControl() != null && nowDraw.getAutoControl().booleanValue())
			{
				results = results.append(" (auto)"); results = results.append("\n");
			}
			else results = results.append("\n");
			
			results = results.append("Damage \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getDamage())); results = results.append("\n");
			results = results.append("Missiles \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getMissiles())); results = results.append("\n");
			results = results.append("" + sets.getLang().getText(Language.HP) + " \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getHp())); results = results.append(" / ");
			results = results.append(String.valueOf(nowDraw.getSpaceShip().getMax_hp())); results = results.append("\n");
			results = results.append("E \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getEnergy())); results = results.append(" / ");
			results = results.append(String.valueOf(nowDraw.getSpaceShip().getMax_energy())); results = results.append("\n");
			results = results.append("Speed \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getAccel())); results = results.append("\n");
			results = results.append(sets.getLang().getText(Language.HP) + " Heal \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getHp_heal())); results = results.append("\n");
			results = results.append("E Heal \t: "); results = results.append(String.valueOf(nowDraw.getSpaceShip().getEnergy_heal())); results = results.append("\n\n");
			results = results.append("Life \t: "); results = results.append(String.valueOf(nowDraw.getContinue_left())); results = results.append("\n");
		}
		catch (NullPointerException e)
		{
			
		}
		try
		{
			if(progress >= playing_replay.getScenes().size() || (! playing))
			{
				try
				{
					if(playing_replay instanceof BReflexReplay)
					{
						if(((BReflexReplay) playing_replay).getReplayTitle() != null)
						{						
							if(! (((BReflexReplay) playing_replay).getReplayTitle().trim().equals("")))
							{
								results2 = results2.append(((BReflexReplay) playing_replay).getReplayTitle());
								results2 = results2.append("\n\n");
							}
						}
						if(((BReflexReplay) playing_replay).getDescription() != null)
						{
							if(! (((BReflexReplay) playing_replay).getDescription().trim().equals("")))
							{
								results2 = results2.append(((BReflexReplay) playing_replay).getDescription());
								results2 = results2.append("\n\n");
							}
						}
					}
				} 
				catch (Exception e)
				{
					results2 = new StringBuffer("");
				}
				if(nowDraw.getScenario() != null)
				{
					results2 = results2.append("Scenario : ");
					results2 = results2.append(nowDraw.getScenario().getName());
					results2 = results2.append("\n");					
					results2 = results2.append("Scenario Difficulty : ");
					results2 = results2.append(Difficulty.intToString(nowDraw.getScenario().getDifficulty().intValue(), 3.3));
					results2 = results2.append("\n");
					if(sets.getLang() instanceof Korean)
					{
						results2 = results2.append(nowDraw.getScenario().getKoreanDescription());
					}
					else
					{
						results2 = results2.append(nowDraw.getScenario().getDescription());
					}
					
					results2 = results2.append("\n\n");
				}
			}
		} 
		catch (Exception e1)
		{
			
		}
		
		try
		{
			results2 = results2.append("Difficulty : ");
			results2 = results2.append(String.valueOf(nowDraw.getDifficulty()));
			results2 = results2.append("\nEnemies\nType\t");
			results2 = results2.append(sets.getLang().getText(Language.HP));
			results2 = results2.append("\tE\tE2\tUnique\n");
			for(Enemy e : nowDraw.getEnemies())				
			{
				results2 = results2.append(String.valueOf(e.getEnemyName()));
				results2 = results2.append("\t");				
				results2 = results2.append(String.valueOf(e.getHp()));
				results2 = results2.append("\t");
				results2 = results2.append(String.valueOf(e.getEnergy()));
				if(e instanceof Boss)
				{					
					results2 = results2.append("\t");
					results2 = results2.append(String.valueOf(((Boss) e).getBeam_energy()));
					results2 = results2.append(" / ");
					results2 = results2.append(String.valueOf(((Boss) e).getBeam_std()));
					results2 = results2.append("\t");
					if(((Boss) e).isUnique())
						results2 = results2.append("O");
					else
						results2 = results2.append("X");
				}
				else
				{
					results2 = results2.append("\t0\tX");
				}
				results2 = results2.append("\n");
				//results2 = results2.append("\n");
			}
			results2 = results2.append("\nAttacks\n");
			results2 = results2.append("\nType\t\tDamage\tOwner\n");
			for(Missile m : nowDraw.getMissile())
			{
				results2 = results2.append(String.valueOf(m.getMissileName()));
				results2 = results2.append("\t\t");
				results2 = results2.append(String.valueOf(m.getDamage()));
				results2 = results2.append("\t");
				results2 = results2.append(String.valueOf(m.getOwner()));
				results2 = results2.append("\n");
			}
		}
		catch (NullPointerException e)
		{
			
		}
		
		statusArea.setText(results.toString());
		statusArea2.setText(results2.toString());
		statusArea.setCaretPosition(0);
		statusArea2.setCaretPosition(0);
	}
	public Window getWindow()
	{
		return window;
	}
}

