package main_classes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.DetailedScenario;
import setting.Difficulty;
import setting.Lint;
import setting.SaveBoolean;
import setting.SaveInt;
import setting.Setting;
import tracking.TrackStorage;

public class MathConquer extends JDialog implements Openable, ActionListener, WindowListener, Runnable, MouseListener, MouseMotionListener, ItemListener, MessageShowable
{
	private static final long serialVersionUID = 7427909645623293970L;

	private Setting sets;
	private boolean independence = false;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private CardLayout centerLayout;
	private JPanel selectPanel;
	private JPanel gamePanel;
	private JPanel select_upPanel;
	private JPanel select_downPanel;
	private JPanel select_centerPanel;
	private JButton bt_start;
	private JButton bt_exit;
	private Thread thread;
	private boolean threadSwitch = true;
	private int state_now = 0, state_limit = 10000, state_speed = 1, combo = 1;
	private String name = "";
	private StringBuffer code;
	private boolean active = false;
	private volatile boolean pause = false;
	private JProgressBar stateGage;
	private JPanel game_centerPanel;
	private JPanel game_upPanel;
	private JPanel game_downPanel;
	private JPanel game_hintPanel;
	private JScrollPane game_hintScroll;
	private JLabel game_answerLabel;
	private JTextField game_answerField;
	private JButton bt_answer;
	private double ratio;
	private int hintCount;
	private volatile String[] hints;
	private MathAnswer target;
	private BigInteger clearCount;
	private BigInteger point;
	private JLabel[] game_hintLabel;
	private JLabel game_pointLabel;
	private JTextField game_pointField;
	private JTextField game_nowField;
	private JPanel[] game_hintLabelPanel;
	private JLabel game_nowLabel;
	private JPanel finish_mainPanel;
	private JPanel finish_upPanel;
	private JPanel finish_downPanel;
	private JPanel finish_centerPanel;
	private JPanel[] finish_pns;
	private JLabel[] finish_lbs;
	private JTextField[] finish_fds;
	private JLabel select_nameLabel;
	private JTextField select_nameField;
	private JScrollPane finish_scroll;
	private JComboBox cb_difficulty;
	private int difficulty;
	private JButton bt_stop;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mousex;
	private int mousey;
	private JButton bt_calc;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenu menu_another;
	private JMenuItem menu_file_exit;
	private JCheckBoxMenuItem menu_another_calc;
	private JMenuItem menu_another_warn;
	private JCheckBoxMenuItem menu_another_onecard;
	private JCheckBoxMenuItem menu_another_dir;
	private Vector<String> messages;
	private JTextField messageField;
	private int messageDelay = 0;
	private JPanel finishPanel;
	private JButton bt_fin;
	private JPanel finish_buttonPanel;
	private JPanel finishInsidePanel;
	private JPanel finish_labelPanel;
	private JLabel finish_label;
	private JTextArea select_noticePanel;
	private JScrollPane select_noticeScroll;
	private JMenuItem menu_file_code;
	private JFrame mainFrame = null;
	
	public MathConquer(boolean independence, Setting s)
	{
		sets = s;
		mainFrame = new JFrame();
		this.independence = independence;
		init();
	}
	public MathConquer(boolean independence, Setting s, JFrame sp)
	{
		super(sp);
		sets = s;
		this.independence = independence;	
		init();
	}
	public MathConquer(boolean independence, Setting s, JDialog sp)
	{
		super(sp);
		sets = s;
		this.independence = independence;	
		init();
	}
	private void init()
	{
		if(sets == null) sets = Setting.getNewInstance();
		if(CalcWindow.usingFont == null) 
		{
			CalcWindow.prepareFont();
		}
		
		messages = new Vector<String>();
		mainPanel = new JPanel();
		
		if(mainFrame != null)
		{
			mainFrame.setUndecorated(sets.isUse_own_titleBar());
			mainFrame.setSize(sets.getWidth(), sets.getHeight());
			mainFrame.setLocation((int)(sets.getScreenSize().getWidth()/2 - mainFrame.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - mainFrame.getHeight()/2));
			mainFrame.addWindowListener(this);
			mainFrame.getContentPane().setLayout(new BorderLayout());
			
			mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			
			try
			{
				mainFrame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")).getImage());
			}
			catch(Exception e)
			{
				if(sets.isError_printDetail()) e.printStackTrace();
			}
			
			this.getContentPane().setBackground(sets.getSelected_back());
		}
		else
		{
			this.setUndecorated(sets.isUse_own_titleBar());
			this.setSize(sets.getWidth(), sets.getHeight());
			this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
			this.addWindowListener(this);
			this.getContentPane().setLayout(new BorderLayout());
			this.getContentPane().add(mainPanel, BorderLayout.CENTER);
			
			this.getContentPane().setBackground(sets.getSelected_back());
		}
		
		
		mainPanel.setBackground(sets.getSelected_back());
		
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setLayout(new BorderLayout());
				
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		
		upPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		centerLayout = new CardLayout();
		centerPanel.setLayout(centerLayout);
		
		selectPanel = new JPanel();
		finishPanel = new JPanel();
		centerPanel.add("select", selectPanel);
		centerPanel.add("finish", finishPanel);
		
		selectPanel.setLayout(new BorderLayout());
		select_upPanel = new JPanel();
		select_downPanel = new JPanel();
		select_centerPanel = new JPanel();
		selectPanel.add(select_upPanel, BorderLayout.NORTH);
		selectPanel.add(select_downPanel, BorderLayout.SOUTH);
		selectPanel.add(select_centerPanel, BorderLayout.CENTER);
		select_downPanel.setLayout(new FlowLayout());
		
		selectPanel.setBackground(sets.getSelected_back());
		select_upPanel.setBackground(sets.getSelected_back());
		select_downPanel.setBackground(sets.getSelected_back());
		select_centerPanel.setBackground(sets.getSelected_back());
		
		String[] diflist = new String[3];
		diflist[0] = Difficulty.intToString(-1, 5.0);
		diflist[1] = Difficulty.intToString(1000, 5.0);
		diflist[2] = Difficulty.intToString(10000, 5.0);
		cb_difficulty = new JComboBox(diflist);
		cb_difficulty.setSelectedIndex(0);
		cb_difficulty.setBackground(sets.getSelected_inside_back());
		cb_difficulty.setForeground(sets.getSelected_fore());
		select_downPanel.add(cb_difficulty);
		bt_start = new JButton(sets.getLang().getText(Language.START));
		bt_start.addActionListener(this);
		select_downPanel.add(bt_start);
		bt_exit = new JButton(sets.getLang().getText(Language.EXIT));
		bt_exit.addActionListener(this);
		bt_calc = new JButton(sets.getLang().getText(Language.TITLE));
		bt_calc.addActionListener(this);
		select_downPanel.add(bt_exit);
		//select_downPanel.add(bt_calc);
		if(CalcWindow.usingFont != null)
		{
			cb_difficulty.setFont(CalcWindow.usingFont);
			bt_start.setFont(CalcWindow.usingFont);
			bt_calc.setFont(CalcWindow.usingFont);
			bt_exit.setFont(CalcWindow.usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_start.setBackground(sets.getSelected_button());
			bt_exit.setBackground(sets.getSelected_button());
			bt_calc.setBackground(sets.getSelected_button());
		}
		bt_start.setForeground(sets.getSelected_fore());
		bt_exit.setForeground(sets.getSelected_fore());
		bt_calc.setForeground(sets.getSelected_fore());
		
		select_upPanel.setLayout(new FlowLayout());
		select_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		select_nameField = new JTextField(10);
		select_nameField.setText(sets.getLang().getText(Language.USER));
		select_upPanel.add(select_nameLabel);
		select_upPanel.add(select_nameField);
		
		try
		{
			select_nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		
		select_nameLabel.setForeground(sets.getSelected_fore());
		select_nameField.setForeground(sets.getSelected_fore());
		select_nameField.setBackground(sets.getSelected_inside_back());
		
		if(CalcWindow.usingFont != null)
		{
			select_nameLabel.setFont(CalcWindow.usingFont);
			select_nameField.setFont(CalcWindow.usingFont);
		}
		
		upPanel.setLayout(new BorderLayout());					
		titlePanel = new JPanel();	
		titlePanel.setBackground(sets.getSelected_inside_back());
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBorder(new EtchedBorder());
		titleLabel = new JLabel(sets.getLang().getText(Language.MATHCONQ));
		titleLabel.setForeground(sets.getSelected_fore());
		titlePanel.add(titleLabel);
		if(sets.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		if(mainFrame != null)
		{
			mainFrame.setTitle(sets.getLang().getText(Language.MATHCONQ));
		}
		
		if(CalcWindow.usingFont != null)
		{
			titleLabel.setFont(CalcWindow.usingFont);
		}
		
		menuBar = new JMenuBar();
		upPanel.add(menuBar, BorderLayout.CENTER);
		menuBar.setBackground(sets.getSelected_inside_back());
		menuBar.setForeground(sets.getSelected_fore());
		
		if(CalcWindow.usingFont != null)
		{
			menuBar.setFont(CalcWindow.usingFont);
		}
		
		menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		menu_another = new JMenu(sets.getLang().getText(Language.ANOTHER_GAMES));
		menuBar.add(menu_file);
		menuBar.add(menu_another);
		menu_file.setBackground(sets.getSelected_back());
		menu_file.setForeground(sets.getSelected_fore());
		menu_another.setBackground(sets.getSelected_back());
		menu_another.setForeground(sets.getSelected_fore());
		
		if(RunManager.STANDALONE_MODE >= 0)
			menu_another.setVisible(false);
		
		if(CalcWindow.usingFont != null)
		{
			menu_file.setFont(CalcWindow.usingFont);
			menu_another.setFont(CalcWindow.usingFont);
		}
		
		menu_file_code = new JMenuItem(sets.getLang().getText(Language.CODE_CHECKER));
		menu_file_code.addActionListener(this);
		menu_file_code.setBackground(sets.getSelected_back());
		menu_file_code.setForeground(sets.getSelected_fore());
		menu_file.add(menu_file_code);
		
		menu_file_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		menu_file_exit.addActionListener(this);
		menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		menu_file_exit.setBackground(sets.getSelected_back());
		menu_file_exit.setForeground(sets.getSelected_fore());
		menu_file.add(menu_file_exit);
		
		
		
		if(CalcWindow.usingFont != null)
		{
			menu_file_exit.setFont(CalcWindow.usingFont);
			menu_file_code.setFont(CalcWindow.usingFont);
		}
		
		menu_another_warn = new JMenuItem(sets.getLang().getText(Language.NEED_RESTART));
		menu_another_warn.setBackground(sets.getUnselected_back());
		menu_another_warn.setForeground(sets.getUnselected_fore());
		menu_another_warn.setEnabled(false);
		
		if(CalcWindow.usingFont != null)
		{
			menu_another_warn.setFont(CalcWindow.usingFont);
		}
		
		menu_another_calc = new JCheckBoxMenuItem(sets.getLang().getText(Language.TITLE));
		menu_another_calc.addItemListener(this);
		menu_another_calc.setBackground(sets.getSelected_back());
		menu_another_calc.setForeground(sets.getSelected_fore());
		
		if(CalcWindow.usingFont != null)
		{
			menu_another_calc.setFont(CalcWindow.usingFont);
		}
		
		menu_another_onecard = new JCheckBoxMenuItem(sets.getLang().getText(Language.ONECARD));
		menu_another_onecard.addItemListener(this);
		menu_another_onecard.setBackground(sets.getSelected_back());
		menu_another_onecard.setForeground(sets.getSelected_fore());
		
		if(CalcWindow.usingFont != null)
		{
			menu_another_onecard.setFont(CalcWindow.usingFont);
		}
		
		menu_another_dir = new JCheckBoxMenuItem(sets.getLang().getText(Language.CONQUER));
		menu_another_dir.addItemListener(this);
		menu_another_dir.setBackground(sets.getSelected_back());
		menu_another_dir.setForeground(sets.getSelected_fore());
		
		if(CalcWindow.usingFont != null)
		{
			menu_another_dir.setFont(CalcWindow.usingFont);
		}
		
		menu_another.add(menu_another_warn);
		menu_another.add(menu_another_calc);
		menu_another.add(menu_another_onecard);
		menu_another.add(menu_another_dir);
		
		select_centerPanel.setLayout(new BorderLayout());
		
		select_noticePanel = new JTextArea();
		select_noticePanel.setLineWrap(true);
		select_noticePanel.setEditable(false);
		select_noticePanel.setBorder(new EtchedBorder());
		select_noticePanel.setBackground(sets.getSelected_inside_back());
		select_noticePanel.setForeground(sets.getSelected_fore());
		select_noticeScroll = new JScrollPane(select_noticePanel);
		select_noticePanel.setText(sets.getLang().getText(Language.MATHQ_HELP) + "\n\n");
		
		
		
		select_centerPanel.add(select_noticeScroll, BorderLayout.CENTER);
		
		gamePanel = new JPanel();
		gamePanel.setBackground(sets.getSelected_back());
		centerPanel.add("game", gamePanel);
		
		game_centerPanel = new JPanel();
		game_upPanel = new JPanel();
		game_downPanel = new JPanel();
		game_centerPanel.setBackground(sets.getSelected_back());
		game_upPanel.setBackground(sets.getSelected_back());
		game_downPanel.setBackground(sets.getSelected_back());
		
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(game_centerPanel, BorderLayout.CENTER);
		gamePanel.add(game_upPanel, BorderLayout.NORTH);
		gamePanel.add(game_downPanel, BorderLayout.SOUTH);
		
		game_centerPanel.setLayout(new BorderLayout());
		game_hintPanel = new JPanel();		
		game_hintPanel.setBackground(sets.getSelected_back());
		game_hintScroll = new JScrollPane(game_hintPanel);
		game_hintLabel = new JLabel[10];
		game_hintLabelPanel = new JPanel[game_hintLabel.length];
		game_hintPanel.setLayout(new GridLayout(game_hintLabel.length, 1));
		for(int i=0; i<game_hintLabel.length; i++)
		{
			game_hintLabel[i] = new JLabel("");
			game_hintLabelPanel[i] = new JPanel();	
			if(CalcWindow.usingFont != null)
			{
				game_hintLabel[i].setFont(CalcWindow.usingFont);
			}
			game_hintLabel[i].setForeground(sets.getSelected_fore());
			game_hintLabelPanel[i].setBackground(sets.getSelected_back());
			game_hintLabelPanel[i].setLayout(new FlowLayout());
			game_hintLabelPanel[i].add(game_hintLabel[i]);
			game_hintPanel.add(game_hintLabelPanel[i]);
		}
		game_centerPanel.add(game_hintScroll, BorderLayout.CENTER);
		
		game_upPanel.setLayout(new FlowLayout());
		game_pointLabel = new JLabel(sets.getLang().getText(Language.POINT));
		game_pointField = new JTextField(20);
		game_pointField.setBorder(new EtchedBorder());
		game_pointField.setEditable(false);		
		game_nowLabel = new JLabel(", ");
		game_nowField = new JTextField(6);
		game_nowField.setBorder(new EtchedBorder());
		game_nowField.setEditable(false);
		game_upPanel.add(game_pointLabel);
		game_upPanel.add(game_pointField);
		game_upPanel.add(game_nowLabel);
		game_upPanel.add(game_nowField);
		game_pointLabel.setForeground(sets.getSelected_fore());
		game_pointField.setForeground(sets.getSelected_fore());
		game_nowLabel.setForeground(sets.getSelected_fore());
		game_nowField.setForeground(sets.getSelected_fore());
		game_pointField.setBackground(sets.getSelected_inside_back());
		game_nowField.setBackground(sets.getSelected_inside_back());
		
		if(CalcWindow.usingFont != null)
		{
			game_pointLabel.setFont(CalcWindow.usingFont);
			game_pointField.setFont(CalcWindow.usingFont);
			game_nowLabel.setFont(CalcWindow.usingFont);
			game_nowField.setFont(CalcWindow.usingFont);
		}
		
		game_downPanel.setLayout(new FlowLayout());
		game_answerLabel = new JLabel(sets.getLang().getText(Language.ANSWER));
		game_answerField = new JTextField(15);
		game_answerField.setBorder(new EtchedBorder());
		game_answerField.addActionListener(this);
		bt_answer = new JButton(sets.getLang().getText(Language.ACCEPT));
		bt_answer.addActionListener(this);
		bt_stop = new JButton(sets.getLang().getText(Language.GAME_STOP));
		bt_stop.addActionListener(this);
		game_downPanel.add(game_answerLabel);
		game_downPanel.add(game_answerField);
		game_downPanel.add(bt_answer);
		game_downPanel.add(bt_stop);
		game_answerLabel.setForeground(sets.getSelected_fore());
		game_answerField.setBackground(sets.getSelected_inside_back());
		game_answerField.setForeground(sets.getSelected_fore());
		bt_answer.setForeground(sets.getSelected_fore());
		bt_stop.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_answer.setBackground(sets.getSelected_button());
			bt_stop.setBackground(sets.getSelected_button());
		}
		if(CalcWindow.usingFont != null)
		{
			game_answerLabel.setFont(CalcWindow.usingFont);
			game_answerField.setFont(CalcWindow.usingFont);
			bt_answer.setFont(CalcWindow.usingFont);
			bt_stop.setFont(CalcWindow.usingFont);			
		}
		
		downPanel.setLayout(new BorderLayout());
		stateGage = new JProgressBar(JProgressBar.HORIZONTAL, 0, state_limit);
		downPanel.add(stateGage, BorderLayout.CENTER);
		
		messageField = new JTextField();
		messageField.setEditable(false);
		messageField.setBorder(new EtchedBorder());
		messageField.setBackground(sets.getSelected_inside_back());
		messageField.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			messageField.setFont(CalcWindow.usingFont);
		}
		downPanel.add(messageField, BorderLayout.SOUTH);
				
		finishPanel.setBackground(sets.getSelected_back());
		finishPanel.setLayout(new BorderLayout());
		finish_buttonPanel = new JPanel();
		finish_buttonPanel.setBackground(sets.getSelected_back());
		finishPanel.add(finish_buttonPanel, BorderLayout.SOUTH);
		
		finishInsidePanel = new JPanel();
		finishInsidePanel.setBackground(sets.getSelected_back());
		finishPanel.add(finishInsidePanel, BorderLayout.CENTER);
		finishInsidePanel.setLayout(new FlowLayout());
		
		finish_mainPanel = new JPanel();
		finish_mainPanel.setBackground(sets.getSelected_back());
		finish_mainPanel.setLayout(new BorderLayout());
		finish_mainPanel.setBorder(new EtchedBorder());
		finish_scroll = new JScrollPane(finish_mainPanel);
		finishInsidePanel.add(finish_scroll);
		
		finish_upPanel = new JPanel();
		finish_downPanel = new JPanel();
		finish_centerPanel = new JPanel();
		finish_upPanel.setBackground(sets.getSelected_back());
		finish_downPanel.setBackground(sets.getSelected_back());
		finish_centerPanel.setBackground(sets.getSelected_back());
		
		finish_mainPanel.add(finish_upPanel, BorderLayout.NORTH);
		finish_mainPanel.add(finish_downPanel, BorderLayout.SOUTH);
		finish_mainPanel.add(finish_centerPanel, BorderLayout.CENTER);
		
		finish_pns = new JPanel[3];
		finish_lbs = new JLabel[finish_pns.length];
		finish_fds = new JTextField[finish_pns.length];
		finish_centerPanel.setLayout(new GridLayout(finish_pns.length + 1, 1));
		
		finish_labelPanel = new JPanel();
		finish_labelPanel.setBackground(sets.getSelected_back());
		finish_centerPanel.add(finish_labelPanel);
		
		finish_label = new JLabel();
		finish_label.setForeground(sets.getSelected_fore());
		finish_labelPanel.add(finish_label);
		
		if(CalcWindow.usingFont != null)
			finish_label.setFont(CalcWindow.usingFont);
		
		for(int i=0; i<finish_pns.length; i++)
		{
			finish_pns[i] = new JPanel();
			finish_pns[i].setBackground(sets.getSelected_back());
			finish_lbs[i] = new JLabel();
			finish_lbs[i].setForeground(sets.getSelected_fore());
			finish_fds[i] = new JTextField(30);
			finish_fds[i].setBorder(new EtchedBorder());
			finish_fds[i].setForeground(sets.getSelected_fore());
			finish_fds[i].setBackground(sets.getSelected_inside_back());
			finish_fds[i].setEditable(false);
			finish_pns[i].setLayout(new FlowLayout());
			finish_pns[i].add(finish_lbs[i]);
			finish_pns[i].add(finish_fds[i]);
			if(CalcWindow.usingFont != null)
			{
				finish_lbs[i].setFont(CalcWindow.usingFont);
				finish_fds[i].setFont(CalcWindow.usingFont);
			}
			finish_centerPanel.add(finish_pns[i]);
		}
		finish_downPanel.setLayout(new FlowLayout());
		bt_fin = new JButton(sets.getLang().getText(Language.ACCEPT));
		bt_fin.addActionListener(this);
		bt_fin.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_fin.setBackground(sets.getSelected_button());
		}
		if(CalcWindow.usingFont != null)
			bt_fin.setFont(CalcWindow.usingFont);
		
		finish_buttonPanel.add(bt_fin);
		
		centerLayout.show(centerPanel, "select");
		
		thread = new Thread(this);
	}
	@Override
	public void open()
	{
		if(mainFrame != null)
			mainFrame.setVisible(true);
		else
			this.setVisible(true);
		thread.start();
	}

	@Override
	public void exit()
	{
		
		if(independence) System.exit(0);
		else
		{
			this.setVisible(false);
			if(mainFrame != null)
			{
				mainFrame.setVisible(false);
			}
		}
	}
	public void game_start()
	{
		state_speed = 1;
		state_now = state_limit;
		difficulty = cb_difficulty.getSelectedIndex() + 1;
		state_speed = difficulty;		
		name = select_nameField.getText();
		point = Lint.big(0);		
		clearCount = Lint.big(0);
		combo = (int) Math.pow(3, difficulty - 1);
		centerLayout.show(centerPanel, "game");
		readyQuest();
		
		menu_another.setEnabled(false);
		message(sets.getLang().getText(Language.MATHQ_SIMPLEHELP));
		
		active = true;
		pause = false;
		
		game_answerField.requestFocus();
	}
	private void readyQuest()
	{
		pause = true;
		if(active)
		{
			clearCount = clearCount.add(Lint.big(1));
			BigInteger addV = Lint.big(1);
			addV = addV.multiply(Lint.big(combo));
			point = point.add(addV);
			if(combo < 100)
				combo++;
		}
		int random1 = (int) (Math.random() * 10);
		long random2 = (long) (Math.random() * (10.0 * (clearCount.doubleValue() + 1)));
		double random3 = (double) Math.random() * (10.0 * (clearCount.doubleValue() + 1));
		random3 = random3 - (random3 / 3.0);
		random3 = random3 * Math.pow(10.0, clearCount.doubleValue() + 1.0);
		random3 = Math.round(random3);
		random3 = random3 / Math.pow(10.0, clearCount.doubleValue() + 1.0);
		
		
		int sectionCount = (int) (clearCount.longValue() + 3);
		String newHint = "";
		DecimalSection[] decSections;
		IntSection[] intSections;
		
		long float_or_int_std = 10 - clearCount.longValue();
		if(float_or_int_std < 2) float_or_int_std = 2;
		
		if(random1 >= float_or_int_std)
		{
			target = new FloatAnswer();
			((FloatAnswer) target).setAnswer(random3);
			for(int i=0; i<target.hintCount(); i++)
			{
				newHint = "";
				decSections = new DecimalSection[sectionCount];
				for(int j=0; j<decSections.length - 1; j++)
				{
					decSections[j] = new DecimalSection();
					random3 = (Math.random() * (5.0 + clearCount.doubleValue() * 2));	
					random3 = random3 - (random3 / 3.0);
					decSections[j].setCoe(new BigDecimal(String.valueOf(random3)));
					if(decSections[j].getCoe().scale() >= 1 + clearCount.intValue())
						decSections[j].setCoe(decSections[j].getCoe().setScale(1 + clearCount.intValue(), BigDecimal.ROUND_HALF_UP));
					random1 = (int) (Math.random() * (3 + difficulty + (clearCount.intValue() / 2)));
					random1 = random1 - (random1 / 3);
					random1++;
					decSections[j].setExp(random1);
				}
				decSections[decSections.length - 1] = new DecimalSection();
				BigDecimal testValue;
				while(true)
				{					
					decSections[decSections.length - 1].setExp(0);
					
					testValue = new BigDecimal(String.valueOf(0.0));
					
					for(int j=0; j<decSections.length - 1; j++)
					{
						testValue = testValue.add(decSections[j].puts(((FloatAnswer) target).getAnswer()));		
					}					
					decSections[decSections.length - 1].setCoe(new BigDecimal(testValue.toString()).multiply(new BigDecimal(-1.0)));
					testValue = testValue.add(decSections[decSections.length - 1].puts(((FloatAnswer) target).getAnswer()));
					
					
					if(testValue.compareTo(new BigDecimal(String.valueOf(0.0))) == 0) break;			
					
				}
				
				for(int j=0; j<decSections.length - 1; j++)
				{					
					newHint = newHint + decSections[j].toBasicString() + " + ";
				}
				newHint = newHint + decSections[decSections.length - 1].toBasicString() + " = 0";
				//System.out.println(newHint + ", " + i);
				target.setHint(newHint, i);
			}
		}
		else
		{
			target = new IntAnswer();
			((IntAnswer) target).setAnswer(random2);
			for(int i=0; i<target.hintCount(); i++)
			{
				newHint = "";
				intSections = new IntSection[sectionCount];
				for(int j=0; j<intSections.length - 1; j++)
				{
					intSections[j] = new IntSection();
					random2 = (long) (Math.random() * (8.0 + clearCount.doubleValue() * 3));
					random2 = random2 - (random2 / 3);
					intSections[j].setCoe(Lint.big(random2));
					random1 = (int) (Math.random() * (4 + clearCount.intValue()));
					intSections[j].setExp(random1);
				}
				intSections[intSections.length - 1] = new IntSection();
				BigInteger testValue;
				while(true)
				{					
					intSections[intSections.length - 1].setExp(0);
					
					testValue = Lint.big(0);
					//System.out.println("start " + testValue);
					for(int j=0; j<intSections.length - 1; j++)
					{
						testValue = testValue.add(intSections[j].puts(((IntAnswer) target).getAnswer()));		
						//System.out.println(j + "th " + testValue);
					}					
					intSections[intSections.length - 1].setCoe(Lint.big(testValue.toString()).multiply(Lint.big(-1)));
					testValue = testValue.add(intSections[intSections.length - 1].puts(((IntAnswer) target).getAnswer()));
					//System.out.println("last " + testValue);
					
					/*
					for(int j=0; j<decSections.length; j++)
					{
						System.out.print(decSections[j].toBasicString());
						if(j < decSections.length - 1) System.out.print(" + ");
					}
					System.out.println(" = 0, " + ((FloatAnswer) target).getAnswer());
					*/
					
					if(testValue.compareTo(Lint.big(0)) == 0) break;			
					
				}
				
				for(int j=0; j<intSections.length - 1; j++)
				{					
					newHint = newHint + intSections[j].toBasicString() + " + ";
				}
				newHint = newHint + intSections[intSections.length - 1].toBasicString() + " = 0";
				//System.out.println(newHint);
				target.setHint(newHint, i);
			}
		}
		
		updateState();
		pause = false;
	}
	public void game_finish()
	{
		active = false;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{				
				menu_another.setEnabled(true);		
				centerLayout.show(centerPanel, "select");	
			}			
		});		
			
	}
	public void game_finish(boolean won)
	{		
		active = false;
				
		StringBuffer auth_code = new StringBuffer("");
		String[] auth_codes = new String[17];
		auth_codes[0] = String.valueOf(point.toString());
		auth_codes[1] = name;
		auth_codes[2] = String.valueOf(1);
		auth_codes[3] = String.valueOf(clearCount);
		auth_codes[4] = String.valueOf(CalcWindow.version_main);
		auth_codes[5] = String.valueOf(CalcWindow.version_sub_1);
		auth_codes[6] = String.valueOf(CalcWindow.version_sub_2);
		BigInteger secret_code = new BigInteger("0");
		BigInteger secret_nameCode = new BigInteger("0");
		
		secret_code = secret_code.add(new BigInteger(String.valueOf((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2)));
		secret_code = secret_code.multiply(clearCount);
		secret_code = secret_code.add(new BigInteger(String.valueOf(point.toString())));
		// authority_code used
		long authority_code = CalcWindow.getAuthorizedCode(2938291);
		secret_code = secret_code.add(Lint.big(authority_code + (1 * ((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2))));
								
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
		auth_codes[14] = "MathConquer";
		auth_codes[15] = "Calc";
		
		
		secret_nameCode = Lint.copy(secret_code);
		secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(name) / 100.0) + 5)));
		//System.out.println("5 : " + secret_code);
		secret_code = secret_code.add(Lint.newBigInteger((clearCount.longValue() + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
		//System.out.println("6 : " + secret_code);
		
		secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
		auth_codes[7] = secret_code.toString();			
		auth_codes[16] = secret_nameCode.toString();			
		
		for(int i=0; i<auth_codes.length; i++)
		{
			auth_code.append(auth_codes[i]);
			if(i < auth_codes.length - 1) auth_code.append("||");
		}
		
		code = auth_code;
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				finish_label.setText(sets.getLang().getText(Language.CANCEL));
				finish_lbs[0].setText(sets.getLang().getText(Language.POINT));
				finish_lbs[1].setText(sets.getLang().getText(Language.ACCEPT) + " " + sets.getLang().getText(Language.COUNT));
				finish_lbs[2].setText(sets.getLang().getText(Language.AUTHORITY));
				finish_fds[0].setText(point.toString());
				finish_fds[1].setText(clearCount.toString());
				if(code == null)
					finish_fds[2].setText("");
				else
					finish_fds[2].setText(code.toString());
				
			}			
		}
		);
		if(won)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					finish_label.setText(sets.getLang().getText(Language.RESULT));
					centerLayout.show(centerPanel, "finish");	
				}
			});
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					finish_label.setText(sets.getLang().getText(Language.RESULT));
					centerLayout.show(centerPanel, "select");	
				}
			});
		}
		active = false;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{				
				menu_another.setEnabled(true);				
			}			
		});
	}
	public void updateState()
	{
		ratio = (double) state_now / (double) state_limit;
		hintCount = (int) ((1.1 - ratio) * 10.0);
		if(hintCount > target.hintCount()) hintCount = target.hintCount();		//System.out.println(target.hintCount());
			
		hints = new String[hintCount];
		
		
		if(target != null)
		{					
			for(int i=0; i<hintCount; i++)
			{
				hints[i] = target.hint(i);
			}
		}
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				stateGage.setValue(state_now);	
				for(int i=0; i<game_hintLabel.length; i++)
				{
					if(i < hintCount)
						game_hintLabel[i].setText(hints[i]);
					else
						game_hintLabel[i].setText("");
				}
				game_pointField.setText(point.toString());
				game_nowField.setText("X " + String.valueOf(combo));
			}
		});
	}
	public void run()
	{
		while(threadSwitch)
		{
			try
			{
				if(active)
				{
					
					if(! pause)
					{
						state_now = state_now - state_speed;
						if(state_now <= 0) game_finish(true);
						else updateState();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				//System.out.println(messages.size() + ", " + messageDelay);
				if(messageDelay <= 0 && messages.size() >= 1)
				{
					messageField.setText(messages.get(0));
					System.out.println(messages.get(0));
					messages.remove(0);
					messageDelay = 60;
				}
				else if(messageDelay <= 0)
				{
					messageField.setText("");
					messageDelay = 60;
				}
				else messageDelay--;
			}
			catch(Exception e)
			{
				
			}
			try
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e)
			{
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == bt_exit || ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == menu_file_code)
		{
			new Code_Checker(false, this, sets).open();
		}
		else if(ob == bt_fin)
		{
			centerLayout.show(centerPanel, "select");
		}
		else if(ob == bt_calc)
		{
			sets.setNext_execute(new SaveInt(0));
			sets.setNext_execute_saved(new SaveBoolean(true));
			save_setting(null);
			JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.NEED_RESTART));
		}
		else if(ob == bt_start)
		{
			game_start();
		}
		else if(ob == bt_answer || ob == game_answerField)
		{
			if(active)
			{
				try
				{
					if(target.isCorrect(game_answerField.getText()))
					{
						readyQuest();
					}
					else
					{
						state_now = state_now - (state_speed * 100);
						combo = (int) Math.pow(3, difficulty - 1);
						if(combo >= 2) combo--;
					}
				}
				catch(NumberFormatException e1)
				{
					state_now = state_now - (state_speed * 100);
					combo = (int) Math.pow(3, difficulty - 1);
					if(combo >= 2) combo--;
				}
				game_answerField.setText("");
				game_answerField.requestFocus();
			}
			else
			{
				game_finish();
			}
		}
		else if(ob == bt_stop)
		{
			game_finish();
		}
		
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		
	}
	@Override
	public void windowClosed(WindowEvent e)
	{
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		Object ob = e.getSource();
		if(ob == this) exit();
		else if(mainFrame != null && ob == mainFrame) exit();
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{		
		
	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		
	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(e.getSource() == titlePanel) 
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		
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
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		
	}
	public void save_setting(Setting sets)
	{
		boolean default_xmlMode = false;
		try
		{
			File file = new File(sets.getDefault_path() + "setting.xml");
			if(file.exists()) default_xmlMode = true;
			else default_xmlMode = false;
		} 
		catch (Exception e)
		{
			default_xmlMode = false;
		}
		
		//System.out.println("설정 저장 실행 : " + String.valueOf(default_xmlMode));
		save_setting(sets, default_xmlMode);
	}
	public void save_setting(Setting sets, boolean xml)
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_setting() started", false);
		
		//System.out.println("설정 저장 : " + String.valueOf(xml));
		//ProgressDialog.show(setting);
		if(sets == null)
		{
			sets = this.sets;
		}
		FileOutputStream fout = null;
		try
		{
			File file = new File(sets.getDefault_path());
			sets.setVer_main(CalcWindow.version_main);
			sets.setVer_sub1(CalcWindow.version_sub_1);
			sets.setVer_sub2(CalcWindow.version_sub_2);
			if(! file.exists())
			{
				file.mkdir();
			}
			
			for(int i=0; i<sets.getScenarios().size(); i++)
			{
				if(sets.getScenarios().get(i) instanceof DetailedScenario)
				{
					sets.getScenarios().remove(i);
					i = 0;
				}
			}
			
			if(xml)
			{
				try
				{
					File xmlFile = new File(sets.getDefault_path() + "setting.xml");
					if(xmlFile.exists()) xmlFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(sets.getDefault_path() + "setting.xml");
				XMLEncoder encoder = new XMLEncoder(fout);
				sets.wrapperToObjects();
				encoder.writeObject(sets);
				encoder.close();
				//ProgressDialog.progress(90);
				
			}		
			else
			{
				try
				{
					File objectFile = new File(sets.getDefault_path() + "setting.clst");
					if(objectFile.exists()) objectFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(sets.getDefault_path() + "setting.clst");
				
				ObjectOutputStream obout = new ObjectOutputStream(fout);
				obout.writeObject(sets);
				obout.close();
				
				//ProgressDialog.progress(90);
				
			}
			
			try
			{
				fout.close();
			} 
			catch (Exception e)
			{
				
			}
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
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_another_calc || ob == menu_another_dir || ob == menu_another_onecard)
		{
			if(ob == menu_another_calc && menu_another_calc.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_calc.setSelected(true);
						menu_another_dir.setSelected(false);
						menu_another_onecard.setSelected(false);						
					}					
				});
				
				sets.setNext_execute(new SaveInt(0));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(ob == menu_another_dir && menu_another_dir.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_dir.setSelected(true);
						menu_another_calc.setSelected(false);
						menu_another_onecard.setSelected(false);						
					}					
				});
				
				sets.setNext_execute(new SaveInt(2));
				sets.setNext_execute_saved(new SaveBoolean(true));
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
						menu_another_dir.setSelected(false);						
					}					
				});
				
				sets.setNext_execute(new SaveInt(1));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(! (menu_another_calc.isSelected() || menu_another_dir.isSelected() || menu_another_onecard.isSelected()))
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_calc.setSelected(false);
						menu_another_dir.setSelected(false);
						menu_another_onecard.setSelected(false);						
					}					
				});				
				sets.setNext_execute(new SaveInt(5));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			save_setting(null);
		}
		
	}
	@Override
	public void message()
	{
		
		
	}
	@Override
	public void message_bar()
	{
		
		
	}
	@Override
	public void message(String str)
	{
		if(str == null) return;
		StringTokenizer stoken = new StringTokenizer(str, "\n");
		int countTok = stoken.countTokens();
		//System.out.println("Tokens : " + stoken.countTokens());
		for(int i=0; i<countTok; i++)
		{
			messages.add(stoken.nextToken());
			//System.out.println("Add : " + stoken.nextToken());
		}
		
	}
	@Override
	public void alert(String str)
	{
		JOptionPane.showMessageDialog(this, str);		
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}
}
abstract class Section implements Serializable
{
	private static final long serialVersionUID = 4135841175014102726L;
	private int type = 0;
	private int exp = 1;
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getExp()
	{
		return exp;
	}
	public void setExp(int exp)
	{
		this.exp = exp;
	}
	public abstract String toBasicString();
}
class DecimalSection extends Section
{
	private static final long serialVersionUID = -8105475242253430462L;
	private BigDecimal coe = new BigDecimal(1.0);
	public DecimalSection()
	{
		coe = new BigDecimal(1.0);
		setExp(1);
	}
	public BigDecimal getCoe()
	{
		return coe;
	}
	public void setCoe(BigDecimal coe)
	{
		this.coe = coe;
	}
	public BigDecimal puts(BigDecimal value)
	{
		BigDecimal results = new BigDecimal(String.valueOf(value.doubleValue()));
		switch(getType())
		{
			case 0:
				results = results.pow(getExp());
				results = results.multiply(coe);
				return results;
		}
		return results;
	}
	@Override
	public String toBasicString()
	{
		switch(getType())
		{
			case 0:
				if(getExp() == 0)
				{
					if(coe.compareTo(new BigDecimal("0.0")) == 0) 
						return "0";					
					else
						return coe.toString();
				}
				else if(getExp() == 1)
				{
					if(coe.compareTo(new BigDecimal("0.0")) == 0) 
						return "0";
					else if(coe.compareTo(new BigDecimal("1.0")) == 0) 
						return "x";
					else
						return coe.toString() + "x";
				}
				else
				{
					if(coe.compareTo(new BigDecimal("0.0")) == 0)
						return "0";
					else if(coe.compareTo(new BigDecimal("1.0")) == 0)
						return "x^" + String.valueOf(getExp());
					else
						return coe.toString() + "x^" + String.valueOf(getExp());
				}
				
		}
		return null;
	}
}
class IntSection extends Section
{
	private static final long serialVersionUID = -1883645547958792440L;
	private BigInteger coe = Lint.big(1);
	public IntSection()
	{
		coe = Lint.big(1);
		setExp(1);
	}
	public BigInteger getCoe()
	{
		return coe;
	}
	public void setCoe(BigInteger coe)
	{
		this.coe = coe;
	}	
	public BigInteger puts(BigInteger value)
	{
		BigInteger results = new BigInteger(value.toString());
		switch(getType())
		{
			case 0:
				results = results.pow(getExp());
				results = results.multiply(coe);
				return results;
		}
		return results;
	}
	@Override
	public String toBasicString()
	{
		switch(getType())
		{
			case 0:
				if(getExp() == 0)
				{
					if(coe.compareTo(Lint.big(0)) == 0) 
						return "0";					
					else
						return coe.toString();
				}
				else if(getExp() == 1)
				{
					if(coe.compareTo(Lint.big(0)) == 0) 
						return "0";
					else if(coe.compareTo(Lint.big(1)) == 0) 
						return "x";
					else
						return coe.toString() + "x";
				}
				else
				{
					if(coe.compareTo(Lint.big(0)) == 0)
						return "0";
					else if(coe.compareTo(Lint.big(1)) == 0)
						return "x^" + String.valueOf(getExp());
					else
						return coe.toString() + "x^" + String.valueOf(getExp());
				}
				
		}
		return null;
	}
}
