package main_classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import accumulate.CityManager;
import lang.Language;
import setting.Lint;
import setting.SaveBoolean;
import setting.SaveInt;
import setting.Setting;
import tracking.TrackStorage;

public class CalcConqueror extends JFrame implements Openable, MessageShowable, ActionListener, WindowListener, MouseListener, MouseMotionListener, Runnable, ItemListener
{
	private static final long serialVersionUID = 7464108897871040738L;
	private Setting setting;
	//private String[] arguments;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel contentPanel;
	private ConquerBlock[][] pns;
	private JTextArea message;
	private JScrollPane messageSc;
	private JPanel[] leftPns;
	private JProgressBar timeLimitBar;
	private JButton start_stop;
	private Thread thread;
	private boolean threadSwitch = true;
	private boolean active = false;
	private boolean active_checking = false;
	private int time_limit = 0, time_delay = 20, check_delay = 10;
	private int check_point_i = 0, check_point_j = 0;
	private int success_counts = 0;
	private int size_i = 12, size_j = 7;
	private JDialog finishDialog;
	private JPanel finishDialog_mainPanel;
	private JPanel finishDialog_centerPanel;
	private JPanel finishDialog_downPanel;
	private JButton finishDialog_close;
	private JPanel finishDialog_successPanel;
	private JPanel finishDialog_timePanel;
	private JPanel finishDialog_pointPanel;
	private JLabel finishDialog_successLabel;
	private JTextField finishDialog_successField;
	private JLabel finishDialog_timeLabel;
	private JTextField finishDialog_timeField;
	private JLabel finishDialog_pointLabel;
	private JTextField finishDialog_pointField;
	private ButtonGroup difficulty_group;
	private JRadioButton[] difficulty;
	private JButton exit_button;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenu menu_another;
	private JMenuItem menu_file_exit;
	private JMenuItem menu_another_warn;
	private JMenuItem menu_another_calc;
	private JMenuItem menu_another_onecard;
	private JPanel titlePanel;
	private JLabel title;
	private JPanel finishDialog_upPanel;
	private JLabel finishDialog_title;
	private int mousex;
	private int mousey;
	private int finish_mousex;
	private int finish_mousey;
	private JSpinner checkingDelay;
	private JScrollPane contentScroll;
	private JTextField successCountField;
	private JPanel finishDialog_controlPanel;
	private JPanel finishDialog_codePanel;
	private JLabel finishDialog_codeLabel;
	private JTextField finishDialog_codeField;
	private JTextField nameField;
	private String name;
	private JButton finishDialog_copy;
	private JCheckBoxMenuItem menu_another_math;
	private JMenuItem menu_another_city;
	private CityManager city;
	
	public CalcConqueror(Setting setting, String[] args)
	{
		this.setting = setting.clone();
		//this.arguments = args;
		init();
	}
	public void init()
	{
		int width, height;
		width = 610;
		height = 460;
		if(setting.getHeight() >= 540) height = 500;
		this.setSize(width, height);
		this.setLocation((int)(setting.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - this.getHeight()/2));
		if(setting.isUse_own_titleBar())
		{
			this.setUndecorated(true);
		}
		else
		{
			this.addWindowListener(this);
		}
		CalcWindow.prepareFont();
		
		try
		{
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")).getImage());
		}
		catch(Exception e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
		}
		
		this.setTitle(setting.getLang().getText(Language.CONQUER));
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EtchedBorder());
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		contentPanel = new JPanel();
		contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(size_i, size_j));
		pns = new ConquerBlock[size_i][size_j];
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				pns[i][j] = new ConquerBlock();
				pns[i][j].close();
				if(CalcWindow.usingFont != null)
					pns[i][j].setFont(CalcWindow.usingFont);
				pns[i][j].button.addActionListener(this);
				pns[i][j].button.addMouseListener(this);
				//pns[i][j].button.setText(String.valueOf(i) + ", " + String.valueOf(j));
				contentPanel.add(pns[i][j]);
			}
		}
		message = new JTextArea();
		if(CalcWindow.usingFont != null)
			message.setFont(CalcWindow.usingFont);
		message.setLineWrap(true);
		messageSc = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		message.setEditable(false);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(messageSc);
		
		leftPns = new JPanel[10];
		leftPanel.setLayout(new GridLayout(leftPns.length, 1));
		for(int i=0; i<leftPns.length; i++)
		{
			leftPns[i] = new JPanel();
			leftPns[i].setLayout(new FlowLayout());
			leftPanel.add(leftPns[i]);
		}
		start_stop = new JButton(setting.getLang().getText(Language.START));
		if(CalcWindow.usingFont != null)
			start_stop.setFont(CalcWindow.usingFont);
		start_stop.addActionListener(this);
		
		exit_button = new JButton(setting.getLang().getText(Language.EXIT));
		if(CalcWindow.usingFont != null)
			exit_button.setFont(CalcWindow.usingFont);
		exit_button.addActionListener(this);
		
		nameField = new JTextField(5);
		if(CalcWindow.usingFont != null)
			nameField.setFont(CalcWindow.usingFont);
		nameField.setText(setting.getLang().getText(Language.PLAYER));
		leftPns[0].setLayout(new FlowLayout());
		leftPns[0].add(nameField);
		
		try
		{
			nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		
		difficulty_group = new ButtonGroup();
		difficulty = new JRadioButton[3];
		for(int i=0; i<difficulty.length; i++)
		{
			difficulty[i] = new JRadioButton();
			if(CalcWindow.usingFont != null)
				difficulty[i].setFont(CalcWindow.usingFont);
			difficulty_group.add(difficulty[i]);
			leftPns[i+1].add(difficulty[i]);
		}
		difficulty[0].setText(setting.getLang().getText(Language.EASY));
		difficulty[1].setText(setting.getLang().getText(Language.NORMAL));
		difficulty[2].setText(setting.getLang().getText(Language.HARD));
		difficulty[1].setSelected(true);
		
		leftPns[difficulty.length + 1].setLayout(new FlowLayout());
		leftPns[difficulty.length + 1].add(start_stop);
		
		
		checkingDelay = new JSpinner();
		if(CalcWindow.usingFont != null)
			checkingDelay.setFont(CalcWindow.usingFont);
		checkingDelay.setValue(new Integer(check_delay));		
		leftPns[difficulty.length + 3].add(checkingDelay);
		
		successCountField = new JTextField(3);
		if(CalcWindow.usingFont != null)
			successCountField.setFont(CalcWindow.usingFont);
		successCountField.setEditable(false);
		leftPns[leftPns.length - 2].add(successCountField);
		leftPns[leftPns.length - 1].add(exit_button);
		
				
		timeLimitBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		timeLimitBar.setStringPainted(true);
		timeLimitBar.setString(String.valueOf(0) + " / 100");
		if(CalcWindow.usingFont != null)
			timeLimitBar.setFont(CalcWindow.usingFont);
		downPanel.setLayout(new BorderLayout());
		downPanel.add(timeLimitBar);
		
		menuBar = new JMenuBar();
		if(CalcWindow.usingFont != null)
			menuBar.setFont(CalcWindow.usingFont);
		
		titlePanel = new JPanel();
		title = new JLabel(setting.getLang().getText(Language.CONQUER));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(title);
		upPanel.setLayout(new BorderLayout());
		if(setting.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.CENTER);
			upPanel.add(menuBar, BorderLayout.SOUTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			titlePanel.setBorder(new EtchedBorder());			
		}
		else
		{
			this.setJMenuBar(menuBar);
		}
		
		menu_file = new JMenu(setting.getLang().getText(Language.MENU_FILE));
		menu_another = new JMenu(setting.getLang().getText(Language.ANOTHER_GAMES));
		if(CalcWindow.usingFont != null)
			menu_file.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			menu_another.setFont(CalcWindow.usingFont);
		menuBar.add(menu_file);
		menuBar.add(menu_another);
		
		menu_file_exit = new JMenuItem(setting.getLang().getText(Language.EXIT));
		if(CalcWindow.usingFont != null)
			menu_file_exit.setFont(CalcWindow.usingFont);
		menu_file_exit.addActionListener(this);
		menu_file.add(menu_file_exit);
		
		menu_another_warn = new JMenuItem(setting.getLang().getText(Language.NEED_RESTART));
		if(CalcWindow.usingFont != null)
			menu_another_warn.setFont(CalcWindow.usingFont);
		menu_another_warn.addActionListener(this);
		menu_another_warn.setEnabled(false);
		menu_another.add(menu_another_warn);
		
		menu_another_calc = new JCheckBoxMenuItem(setting.getLang().getText(Language.TITLE));
		if(CalcWindow.usingFont != null)
			menu_another_calc.setFont(CalcWindow.usingFont);
		menu_another_calc.addItemListener(this);
		menu_another.add(menu_another_calc);
		
		menu_another_onecard = new JCheckBoxMenuItem(setting.getLang().getText(Language.ONECARD));
		if(CalcWindow.usingFont != null)
			menu_another_onecard.setFont(CalcWindow.usingFont);
		menu_another_onecard.addItemListener(this);
		menu_another.add(menu_another_onecard);	
		
		menu_another_math = new JCheckBoxMenuItem(setting.getLang().getText(Language.MATHCONQ));
		if(CalcWindow.usingFont != null)
			menu_another_math.setFont(CalcWindow.usingFont);
		menu_another_math.addItemListener(this);
		menu_another.add(menu_another_math);	
		
		menu_another_city = new JMenuItem(setting.getLang().getText(Language.SHELTER_CITY));
		if(CalcWindow.usingFont != null)
			menu_another_city.setFont(CalcWindow.usingFont);
		menu_another_city.addActionListener(this);
		menu_another.add(menu_another_city);	
		
		boolean city_avail = false;
		try
		{
			if(setting.accepted())
			{
				city_avail = true;
			}
			else if(setting.accept_net() && (! setting.abandoned_key()))
			{
				city_avail = true;
			}
			else if(setting.accept_mastered())
			{
				city_avail = true;
			}
		} 
		catch (Exception e)
		{
			city_avail = false;
		}
		menu_another_city.setVisible(city_avail);
		
		if(RunManager.STANDALONE_MODE >= 0)
			menu_another.setVisible(false);
				
		finishDialog = new JDialog(this, false);
		finishDialog.setSize(300, 200);
		finishDialog.setLocation((int)(setting.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
		finishDialog_mainPanel = new JPanel();
		finishDialog_mainPanel.setBorder(new EtchedBorder());
		finishDialog.getContentPane().setLayout(new BorderLayout());
		finishDialog.getContentPane().add(finishDialog_mainPanel);
		finishDialog_upPanel = new JPanel();
		finishDialog_centerPanel = new JPanel();
		finishDialog_downPanel = new JPanel();
		finishDialog_title = new JLabel();
		if(CalcWindow.usingFont != null)
			finishDialog_title.setFont(CalcWindow.usingFont);		
		finishDialog_mainPanel.setLayout(new BorderLayout());
		finishDialog_mainPanel.add(finishDialog_centerPanel, BorderLayout.CENTER);
		finishDialog_mainPanel.add(finishDialog_downPanel, BorderLayout.SOUTH);
		finishDialog_close = new JButton(setting.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			finishDialog_close.setFont(CalcWindow.usingFont);
		finishDialog_close.addActionListener(this);
		finishDialog_downPanel.setLayout(new BorderLayout());
		finishDialog_controlPanel = new JPanel();
		finishDialog_codePanel = new JPanel();
		finishDialog_downPanel.add(finishDialog_controlPanel, BorderLayout.CENTER);
		finishDialog_downPanel.add(finishDialog_codePanel, BorderLayout.NORTH);
		finishDialog_controlPanel.setLayout(new FlowLayout());
		finishDialog_controlPanel.add(finishDialog_close);
		finishDialog_codePanel.setLayout(new FlowLayout());
		finishDialog_codeLabel = new JLabel(setting.getLang().getText(Language.POINT_CODE));
		finishDialog_codeField = new JTextField(10);
		if(CalcWindow.usingFont != null)
			finishDialog_codeLabel.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			finishDialog_codeField.setFont(CalcWindow.usingFont);
		finishDialog_codeField.setEditable(false);
		finishDialog_codeField.setBorder(new EtchedBorder());
		finishDialog_copy = new JButton(setting.getLang().getText(Language.COPY_CLIPBOARD));
		if(CalcWindow.usingFont != null)
			finishDialog_copy.setFont(CalcWindow.usingFont);
		finishDialog_copy.addActionListener(this);
		finishDialog_codePanel.add(finishDialog_codeLabel);
		finishDialog_codePanel.add(finishDialog_codeField);
		finishDialog_codePanel.add(finishDialog_copy);
		finishDialog_successPanel = new JPanel();
		finishDialog_timePanel = new JPanel();
		finishDialog_pointPanel = new JPanel();
		finishDialog_centerPanel.setLayout(new GridLayout(3, 1));
		finishDialog_centerPanel.add(finishDialog_successPanel);
		finishDialog_centerPanel.add(finishDialog_timePanel);
		finishDialog_centerPanel.add(finishDialog_pointPanel);
		finishDialog_successPanel.setBorder(new EtchedBorder());
		finishDialog_timePanel.setBorder(new EtchedBorder());
		finishDialog_pointPanel.setBorder(new EtchedBorder());
		finishDialog_successPanel.setLayout(new FlowLayout());
		finishDialog_timePanel.setLayout(new FlowLayout());
		finishDialog_pointPanel.setLayout(new FlowLayout());
		finishDialog_successLabel = new JLabel(setting.getLang().getText(Language.COMPLETE));
		if(CalcWindow.usingFont != null)
			finishDialog_successLabel.setFont(CalcWindow.usingFont);
		finishDialog_successField = new JTextField(15);
		if(CalcWindow.usingFont != null)
			finishDialog_successField.setFont(CalcWindow.usingFont);
		finishDialog_successField.setEditable(false);
		finishDialog_successPanel.add(finishDialog_successLabel);
		finishDialog_successPanel.add(finishDialog_successField);
		finishDialog_timeLabel = new JLabel(setting.getLang().getText(Language.TIME));
		if(CalcWindow.usingFont != null)
			finishDialog_timeLabel.setFont(CalcWindow.usingFont);
		finishDialog_timeField = new JTextField(15);
		if(CalcWindow.usingFont != null)
			finishDialog_timeField.setFont(CalcWindow.usingFont);
		finishDialog_timeField.setEditable(false);
		finishDialog_timePanel.add(finishDialog_timeLabel);
		finishDialog_timePanel.add(finishDialog_timeField);
		finishDialog_pointLabel = new JLabel(setting.getLang().getText(Language.POINT));
		if(CalcWindow.usingFont != null)
			finishDialog_pointLabel.setFont(CalcWindow.usingFont);
		finishDialog_pointField = new JTextField(15);
		if(CalcWindow.usingFont != null)
			finishDialog_pointField.setFont(CalcWindow.usingFont);
		finishDialog_pointField.setEditable(false);
		finishDialog_pointPanel.add(finishDialog_pointLabel);
		finishDialog_pointPanel.add(finishDialog_pointField);
		if(setting.isUse_own_titleBar())
		{
			finishDialog.setUndecorated(true);
			finishDialog_mainPanel.add(finishDialog_upPanel, BorderLayout.NORTH);
			finishDialog_upPanel.addMouseListener(this);
			finishDialog_upPanel.addMouseMotionListener(this);
			finishDialog_upPanel.setLayout(new FlowLayout());
			finishDialog_upPanel.setBorder(new EtchedBorder());
			finishDialog_upPanel.add(finishDialog_title);
		}
		theme_refresh();
		
		thread = new Thread(this);
	}
	public void theme_refresh()
	{
		mainPanel.setBackground(setting.getSelected_back());
		upPanel.setBackground(setting.getSelected_back());
		titlePanel.setBackground(setting.getSelected_inside_back());
		title.setForeground(setting.getSelected_fore());
		downPanel.setBackground(setting.getSelected_back());
		centerPanel.setBackground(setting.getSelected_back());
		leftPanel.setBackground(setting.getSelected_back());
		for(int i=0; i<leftPns.length; i++)
		{
			leftPns[i].setBackground(setting.getSelected_back());
		}
		for(int i=0; i<difficulty.length; i++)
		{
			difficulty[i].setBackground(setting.getSelected_back());
			difficulty[i].setForeground(setting.getSelected_fore());
		}
		nameField.setBackground(setting.getSelected_inside_back());
		nameField.setForeground(setting.getSelected_fore());
		rightPanel.setBackground(setting.getSelected_back());
		checkingDelay.setBackground(setting.getSelected_inside_back());
		checkingDelay.setForeground(setting.getSelected_fore());
		successCountField.setBackground(setting.getSelected_inside_back());
		successCountField.setForeground(setting.getSelected_fore());
		start_stop.setForeground(setting.getSelected_fore());
		exit_button.setForeground(setting.getSelected_fore());
		message.setBackground(setting.getSelected_inside_back());
		message.setForeground(setting.getSelected_fore());
		menuBar.setBackground(setting.getSelected_inside_back());
		menuBar.setForeground(setting.getSelected_fore());
		menu_file.setBackground(setting.getSelected_back());
		menu_file.setForeground(setting.getSelected_fore());
		menu_file_exit.setBackground(setting.getSelected_back());		
		menu_file_exit.setForeground(setting.getSelected_fore());
		menu_another.setBackground(setting.getSelected_back());
		menu_another.setForeground(setting.getSelected_fore());
		menu_another_warn.setBackground(setting.getUnselected_back());
		menu_another_warn.setForeground(setting.getUnselected_fore());
		menu_another_calc.setBackground(setting.getSelected_back());
		menu_another_calc.setForeground(setting.getSelected_fore());
		menu_another_onecard.setBackground(setting.getSelected_back());
		menu_another_onecard.setForeground(setting.getSelected_fore());
		menu_another_math.setBackground(setting.getSelected_back());
		menu_another_math.setForeground(setting.getSelected_fore());
		menu_another_city.setBackground(setting.getSelected_back());
		menu_another_city.setForeground(setting.getSelected_fore());
		finishDialog_mainPanel.setBackground(setting.getSelected_back());
		finishDialog_centerPanel.setBackground(setting.getSelected_back());
		finishDialog_downPanel.setBackground(setting.getSelected_back());
		finishDialog_upPanel.setBackground(setting.getSelected_inside_back());
		finishDialog_title.setForeground(setting.getSelected_fore());
		finishDialog_pointPanel.setBackground(setting.getSelected_back());
		finishDialog_successPanel.setBackground(setting.getSelected_back());
		finishDialog_timePanel.setBackground(setting.getSelected_back());
		finishDialog_pointField.setBackground(setting.getSelected_inside_back());
		finishDialog_pointField.setForeground(setting.getSelected_fore());
		finishDialog_successField.setBackground(setting.getSelected_inside_back());
		finishDialog_successField.setForeground(setting.getSelected_fore());
		finishDialog_timeField.setBackground(setting.getSelected_inside_back());
		finishDialog_timeField.setForeground(setting.getSelected_fore());
		finishDialog_pointLabel.setForeground(setting.getSelected_fore());
		finishDialog_successLabel.setForeground(setting.getSelected_fore());
		finishDialog_timeLabel.setForeground(setting.getSelected_fore());
		finishDialog_controlPanel.setBackground(setting.getSelected_back());
		finishDialog_codePanel.setBackground(setting.getSelected_back());
		finishDialog_codeLabel.setForeground(setting.getSelected_fore());
		finishDialog_codeField.setForeground(setting.getSelected_fore());
		finishDialog_codeField.setBackground(setting.getSelected_inside_back());
		finishDialog_close.setForeground(setting.getSelected_fore());
		finishDialog_copy.setForeground(setting.getSelected_fore());
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				pns[i][j].button.setForeground(setting.getSelected_fore());
			}
		}
	}
	public void start()
	{						
		int random_i = 0, random_j = 0, random_dir = 0;
		name = nameField.getText();
		nameField.setEditable(false);
		if(name.equals("")) name = setting.getLang().getText(Language.PLAYER);
		success_counts = 0;
		successCountField.setText(String.valueOf(success_counts));
		message(setting.getLang().getText(Language.DESCRIPTIONS + 11));
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				pns[i][j].open();				
				pns[i][j].start_point = false;
				pns[i][j].check(false);
				random_dir = (int) (Math.random() * 4);
				for(int k=0; k<random_dir; k++)
				{
					pns[i][j].next();
				}				
			}
		}
		random_i = (int) (Math.random() * 10 + 1);
		random_j = (int) (Math.random() * 4 + 1);
		random_dir = (int) (Math.random() * 4);
		pns[random_i][random_j].close();
		pns[random_i][random_j].start_point = true;
		pns[random_i][random_j].check(true);
		for(int i=0; i<random_dir; i++)
		{
			pns[random_i][random_j].next();
		}
		time_limit = 100;
		timeLimitBar.setValue(time_limit);
		timeLimitBar.setString(String.valueOf(time_limit) + " / 100");
		try
		{
			check_delay = ((Integer) checkingDelay.getValue()).intValue();
		} 
		catch (Exception e)
		{
			check_delay = 10;
		}
		time_delay = 50;
		if(difficulty[0].isSelected())
		{
			time_delay = 50;
		}
		else if(difficulty[1].isSelected())
		{
			time_delay = 35;
		}
		else if(difficulty[2].isSelected())
		{
			time_delay = 20;
		}
		else
		{
			time_delay = 12;
		}
		start_stop.setText(setting.getLang().getText(Language.COMPLETE));
		start_stop.setEnabled(true);
		checkingDelay.setEnabled(false);
		active_checking = false;
		active = true;		
	}
	public void check_turn_start()
	{
		//System.out.println("Check Turn Start");
		message(setting.getLang().getText(Language.DESCRIPTIONS + 30));
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				if(pns[i][j].start_point)
				{
					check_point_i = i;
					check_point_j = j;
					break;
				}
			}
		}
		start_stop.setEnabled(false);
		active_checking = true;
		active = true;	
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				pns[i][j].close();
			}
		}
	}
	public void finish(int success_count)
	{
		//System.out.println("Finish");
		message(setting.getLang().getText(Language.DESCRIPTIONS + 29));
		active = false;
		active_checking = false;
		for(int i=0; i<size_i; i++)
		{
			for(int j=0; j<size_j; j++)
			{
				pns[i][j].close();
			}
		}
		//start_stop.setEnabled(true);
		finishDialog_successField.setText(String.valueOf(success_count));
		finishDialog_timeField.setText(String.valueOf(time_limit));
		if(time_limit < 0) time_limit = 0;
		long result_point = ((success_count * 10) + (time_limit + 10) * 2);
		finishDialog_pointField.setText(String.valueOf(result_point));
		finishDialog.setTitle(setting.getLang().getText(Language.RESULT) + " : " + String.valueOf(result_point));
		finishDialog_title.setText(setting.getLang().getText(Language.RESULT) + " : " + String.valueOf(result_point));
				
		StringBuffer auth_code = new StringBuffer("");
		String[] auth_codes = new String[17];
		auth_codes[0] = String.valueOf(result_point);
		auth_codes[1] = new String(name  + " (＃)");
		auth_codes[2] = String.valueOf((size_i * size_j));
		auth_codes[3] = String.valueOf(success_count);
		auth_codes[4] = String.valueOf(CalcWindow.version_main);
		auth_codes[5] = String.valueOf(CalcWindow.version_sub_1);
		auth_codes[6] = String.valueOf(CalcWindow.version_sub_2);
		BigInteger secret_code = new BigInteger("0");
		BigInteger secret_nameCode = new BigInteger("0");
		secret_code = secret_code.add(new BigInteger(String.valueOf((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2)));
		secret_code = secret_code.multiply(new BigInteger(String.valueOf(success_count)));
		secret_code = secret_code.add(new BigInteger(String.valueOf(result_point)));
		// authority_code used
		long authority_code = CalcWindow.getAuthorizedCode(2938291);
		secret_code = secret_code.add(new BigInteger(String.valueOf(authority_code + ((size_i * size_j) * ((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2)))));
								
		Calendar calendar_inst = Calendar.getInstance();
		int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
		aut_year = calendar_inst.get(Calendar.YEAR);
		aut_month = calendar_inst.get(Calendar.MONTH);
		aut_day = calendar_inst.get(Calendar.DATE);
		aut_hour = calendar_inst.get(Calendar.HOUR);
		aut_min = calendar_inst.get(Calendar.MINUTE);
		aut_sec = calendar_inst.get(Calendar.SECOND);
		
		auth_codes[8] = String.valueOf(aut_year);
		auth_codes[9] = String.valueOf(aut_month);
		auth_codes[10] = String.valueOf(aut_day);
		auth_codes[11] = String.valueOf(aut_hour);
		auth_codes[12] = String.valueOf(aut_min);
		auth_codes[13] = String.valueOf(aut_sec);
		auth_codes[14] = "conquer";
		auth_codes[15] = "Calc";
		
		secret_nameCode = Lint.copy(secret_code);
		secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(name  + " (＃)") / 100.0) + 5)));
		secret_code = secret_code.add(new BigInteger(String.valueOf((success_count + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec))));
		secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
		auth_codes[7] = secret_code.toString();			
		auth_codes[16] = secret_nameCode.toString();			
		
		for(int i=0; i<auth_codes.length; i++)
		{
			auth_code.append(auth_codes[i]);
			if(i < auth_codes.length - 1) auth_code.append("||");
		}			
		finishDialog_codeField.setText(auth_code.toString());
		
		nameField.setEditable(true);
		start_stop.setEnabled(false);		
		finishDialog.setVisible(true);
	}
	public void save_setting()
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_setting() started", false);
		try
		{
			File file = new File(setting.getDefault_path());
			setting.setVer_main(CalcWindow.version_main);
			setting.setVer_sub1(CalcWindow.version_sub_1);
			setting.setVer_sub2(CalcWindow.version_sub_2);
			if(! file.exists())
			{
				file.mkdir();
			}
			FileOutputStream fout = new FileOutputStream(setting.getDefault_path() + "setting.clst");
			/*
			XMLEncoder encoder = new XMLEncoder(fout);
			setting.wrapperToObjects();
			encoder.writeObject(setting);
			encoder.close();
			*/
			
			ObjectOutputStream obout = new ObjectOutputStream(fout);
			setting.wrapperToObjects();
			obout.writeObject(setting);
			obout.close();
			
			fout.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
		}
	}
	@Override
	public void open()
	{
		this.setVisible(true);
		thread.start();
	}
	@Override
	public void exit()
	{
		threadSwitch = false;
		System.exit(0);
	}
	@Override
	public void message()
	{
		message.append("\n");
	}
	@Override
	public void message_bar()
	{
		message.append("==========================================================\n");
	}
	@Override
	public void message(String str)
	{
		message.append(str + "\n");
	}
	@Override
	public void alert(String str)
	{
		message.append(str + "\n");
		JOptionPane.showMessageDialog(this, str);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == start_stop)
		{
			if(! active) start();
			else if(active && (! active_checking))
			{
				check_turn_start();
			}
			else 
			{
				active = false;
				active_checking = false;
				for(int i=0; i<size_i; i++)
				{
					for(int j=0; j<size_j; j++)
					{
						pns[i][j].check(false);
						pns[i][j].start_point = false;
						pns[i][j].close();
					}
				}
				start_stop.setText(setting.getLang().getText(Language.START));
				start_stop.setEnabled(true);				
			}			
		}
		else if(ob == finishDialog_close)
		{
			finishDialog.setVisible(false);
			start_stop.setText(setting.getLang().getText(Language.START));
			start_stop.setEnabled(true);
			checkingDelay.setEnabled(true);
		}
		else if(ob == exit_button)
		{
			exit();
		}
		else if(ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == finishDialog_copy)
		{
			try
			{
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finishDialog_codeField.getText()), null);
				alert(setting.getLang().getText(Language.DESC_COPY_CLIPBOARD));
			} 
			catch (Exception e1)
			{
				if(setting.isError_printDetail()) e1.printStackTrace();
				alert(setting.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == menu_another_city)
		{
			if(city == null)
				city = new CityManager(this, false, setting);
			city.open();
		}
		else
		{
			int select_index_i = 0, select_index_j = 0;
			boolean accept = false;
			for(int i=0; i<size_i; i++)
			{
				for(int j=0; j<size_j; j++)
				{
					if(ob == pns[i][j].button)
					{
						select_index_i = i;
						select_index_j = j;
						accept = true;
					}
				}
			}
			if(accept)
			{
				pns[select_index_i][select_index_j].next();
			}
		}		
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		Object ob = e.getSource();
		if(ob == this)
		{
			exit();
		}
		
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);
		}
		else if(ob == finishDialog_upPanel)
		{
			finishDialog.setLocation(e.getXOnScreen() - finish_mousex, e.getYOnScreen() - finish_mousey);	
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			mousex = e.getX();
			mousey = e.getY();
		}
		else if(ob == finishDialog_upPanel)
		{
			finish_mousex = e.getX();
			finish_mousey = e.getY();
		}
		else if(active && (! active_checking) && e.getButton() == MouseEvent.BUTTON3)
		{
			int select_index_i = 0, select_index_j = 0;
			boolean accept = false;
			for(int i=0; i<size_i; i++)
			{
				for(int j=0; j<size_j; j++)
				{
					if(ob == pns[i][j].button)
					{
						select_index_i = i;
						select_index_j = j;
						accept = true;
					}
				}
			}
			if(accept)
			{
				pns[select_index_i][select_index_j].prev();
			}			
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run()
	{
		int time_addit = 10;
		
		while(threadSwitch)
		{
			if(active && (! active_checking))
			{
				time_addit--;
				if(time_addit <= 0)
				{
					time_limit--;
					timeLimitBar.setValue(time_limit);
					timeLimitBar.setString(String.valueOf(time_limit) + "/ 100");
					time_addit = time_delay;
				}
				if(time_limit <= 0)
				{
					check_turn_start();
				}
			}
			else if(active && active_checking)
			{
				time_addit--;
				if(time_addit <= 0)
				{
					success_counts++;
					successCountField.setText(String.valueOf(success_counts));
					pns[check_point_i][check_point_j].check(true);
					if(pns[check_point_i][check_point_j].button.getText().equals("↑") || pns[check_point_i][check_point_j].button.getText().equals("▲") || pns[check_point_i][check_point_j].button.getText().equals("△"))
					{
						check_point_i--;
					}
					else if(pns[check_point_i][check_point_j].button.getText().equals("←") || pns[check_point_i][check_point_j].button.getText().equals("◀") || pns[check_point_i][check_point_j].button.getText().equals("◁"))
					{
						check_point_j--;
					}
					else if(pns[check_point_i][check_point_j].button.getText().equals("↓") || pns[check_point_i][check_point_j].button.getText().equals("▼") || pns[check_point_i][check_point_j].button.getText().equals("▽"))
					{
						check_point_i++;
					}
					else if(pns[check_point_i][check_point_j].button.getText().equals("→") || pns[check_point_i][check_point_j].button.getText().equals("▶") || pns[check_point_i][check_point_j].button.getText().equals("▷"))
					{
						check_point_j++;
					}
					boolean need_finish = true;
					int check_count = 0;
					for(int i=0; i<size_i; i++)
					{
						for(int j=0; j<size_j; j++)
						{
							if(pns[i][j].checked) check_count++;
							else need_finish = false;
						}
					}
					if(check_point_i < 0 || check_point_j < 0 || check_point_i >= size_i || check_point_j >= size_j)
					{					
						finish(check_count);
					}
					else if(pns[check_point_i][check_point_j].checked)
					{
						finish(check_count);
					}
					else
					{						
						if(need_finish) finish(check_count);
						else time_addit = check_delay;
					}
				}
			}
			
			
			try
			{
				Thread.sleep(20);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_another_calc || ob == menu_another_onecard || ob == menu_another_math)
		{
			if(ob == menu_another_calc && menu_another_calc.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(false);
						menu_another_calc.setSelected(true);
						menu_another_math.setSelected(false);
					}
				});				
				setting.setNext_execute(new SaveInt(0));
				setting.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
			else if(ob == menu_another_onecard && menu_another_onecard.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(true);
						menu_another_calc.setSelected(false);
						menu_another_math.setSelected(false);
					}
				});
				
				setting.setNext_execute(new SaveInt(1));
				setting.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
			else if(ob == menu_another_math && menu_another_math.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(false);
						menu_another_calc.setSelected(false);
						menu_another_math.setSelected(true);
					}
				});
				
				setting.setNext_execute(new SaveInt(5));
				setting.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
			else if(! (menu_another_calc.isSelected() || menu_another_onecard.isSelected() || menu_another_math.isSelected()))
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(false);
						menu_another_calc.setSelected(false);
						menu_another_math.setSelected(false);
					}
				});
				
				setting.setNext_execute(new SaveInt(2));
				setting.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
		}
		
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}

}
class ConquerBlock extends JPanel
{
	private static final long serialVersionUID = 4608151087045250880L;
	//private JPanel downPanel;
	private JPanel upPanel;
	public JButton button;
	public boolean start_point = false;
	public boolean checked = false;
	//public JTextField field;

	public ConquerBlock()
	{
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder());
		//downPanel = new JPanel();
		upPanel = new JPanel();
		//this.add(downPanel, BorderLayout.SOUTH);
		this.add(upPanel, BorderLayout.CENTER);
		//downPanel.setLayout(new BorderLayout());
		upPanel.setLayout(new BorderLayout());
		button = new JButton("→");
		upPanel.add(button);
	}
	public void check(boolean check)
	{
		checked = check;
		if(button.getText().equals("↑") || button.getText().equals("▲") || button.getText().equals("△"))
		{
			if(check)
			{
				if(start_point) button.setText("△");
				else button.setText("▲");
			}
			else
			{
				if(start_point) button.setText("△");
				else button.setText("↑");
			}
		}
		else if(button.getText().equals("→") || button.getText().equals("▶") || button.getText().equals("▷"))
		{
			if(check)
			{
				if(start_point) button.setText("▷");
				else button.setText("▶");
			}
			else
			{
				if(start_point) button.setText("▷");
				else button.setText("→");
			}
		}
		else if(button.getText().equals("↓") || button.getText().equals("▼") || button.getText().equals("▽"))
		{
			if(check)
			{
				if(start_point) button.setText("▽");
				else button.setText("▼");
			}
			else
			{
				if(start_point) button.setText("▽");
				else button.setText("↓");
			}
		}
		else if(button.getText().equals("←") || button.getText().equals("◀") || button.getText().equals("◁"))
		{
			if(check)
			{
				if(start_point) button.setText("◁");
				else button.setText("◀");
			}
			else
			{
				if(start_point) button.setText("◁");
				else button.setText("←");
			}
		}
	}
	public void prev()
	{
		if(start_point)
		{
			if(button.getText().equals("△"))
			{
				button.setText("◁");
			}
			else if(button.getText().equals("◁"))
			{
				button.setText("▽");
			}
			else if(button.getText().equals("▽"))
			{
				button.setText("▷");
			}
			else if(button.getText().equals("▷"))
			{
				button.setText("△");
			}
		}
		else if(checked)
		{
			if(button.getText().equals("▲"))
			{
				button.setText("◀");
			}
			else if(button.getText().equals("◀"))
			{
				button.setText("▼");
			}
			else if(button.getText().equals("▼"))
			{
				button.setText("▶");
			}
			else if(button.getText().equals("▶"))
			{
				button.setText("▲");
			}
		}
		else
		{
			if(button.getText().equals("↑"))
			{
				button.setText("←");
			}
			else if(button.getText().equals("←"))
			{
				button.setText("↓");
			}
			else if(button.getText().equals("↓"))
			{
				button.setText("→");
			}
			else if(button.getText().equals("→"))
			{
				button.setText("↑");
			}
		}
	}
	public void next()
	{
		if(start_point)
		{
			if(button.getText().equals("△"))
			{
				button.setText("▷");
			}
			else if(button.getText().equals("▷"))
			{
				button.setText("▽");
			}
			else if(button.getText().equals("▽"))
			{
				button.setText("◁");
			}
			else if(button.getText().equals("◁"))
			{
				button.setText("△");
			}
		}
		else if(checked)
		{
			if(button.getText().equals("▲"))
			{
				button.setText("▶");
			}
			else if(button.getText().equals("▶"))
			{
				button.setText("▼");
			}
			else if(button.getText().equals("▼"))
			{
				button.setText("◀");
			}
			else if(button.getText().equals("◀"))
			{
				button.setText("▲");
			}
		}
		else
		{
			if(button.getText().equals("↑"))
			{
				button.setText("→");
			}
			else if(button.getText().equals("→"))
			{
				button.setText("↓");
			}
			else if(button.getText().equals("↓"))
			{
				button.setText("←");
			}
			else if(button.getText().equals("←"))
			{
				button.setText("↑");
			}
		}
	}
	public void close()
	{
		button.setEnabled(false);
	}
	public void open()
	{
		button.setEnabled(true);		
	}
}