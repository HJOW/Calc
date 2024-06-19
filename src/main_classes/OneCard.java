package main_classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import accumulate.CityManager;
import lang.Language;
import setting.Lint;
import setting.SaveBoolean;
import setting.SaveInt;
import setting.Setting;
import setting.SettingManager;

public class OneCard extends JFrame implements Openable, ActionListener, WindowListener, MouseListener, MouseMotionListener, MessageShowable, Runnable, ItemListener
{
	private static final long serialVersionUID = -5984077951086512585L;
	private boolean independence = true;
	public int time_limit_number1 = 100;
	public int time_limit_number2 = 20;
	public int time_limit_number3 = 20;
	public int seven_limit_number1 = 100;
	public int seven_limit_number2 = 20;
	public int seven_limit_number3 = 20;
	private Setting sets;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JPanel deckPanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JLabel deckLabel1;
	private JLabel deckLabel2;
	private JTextField deckField;
	private Vector<OneCardBlock> blocks;
	private Vector<Card> deck, center_deck, given_queue;
	private Card given_lasts = null;
	private JPanel contentPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel playerListPanel;
	private JPanel playerCountPanel;
	private JPanel deckListPanel;
	private JPanel deckCountPanel;
	private JList playerList;
	private JList deckList;
	private JScrollPane playerListScroll;
	private JScrollPane deckListScroll;
	private JLabel playerCountLabel;
	private JLabel deckCountLabel;
	private JTextField playerCountField;
	private JTextField deckCountField;
	private JPanel deckLastPanel;
	private JLabel deckLastLabel;
	private JTextField deckLastField;
	private JButton take;
	private JButton pay;
	private boolean active = false;
	private boolean order_round = false;
	private JDialog selectDialog;
	private JPanel selectDialog_mainPanel;
	private JPanel selectDialog_titlePanel;
	private JPanel selectDialog_upPanel;
	private JPanel selectDialog_centerPanel;
	private JPanel selectDialog_downPanel;
	private JScrollPane selectDialog_centerScroll;
	private JTabbedPane selectDialog_tab;
	private JPanel selectDialog_bottomPanel;
	private JPanel selectDialog_controlPanel;
	private JPanel[] selectDialog_pns;
	private ButtonGroup[] selectDialog_select;
	private JRadioButton[] selectDialog_select_none;
	private JRadioButton[] selectDialog_select_player;
	private JRadioButton[] selectDialog_select_ai;
	private JTextField[] selectDialog_name;
	private JButton start;
	private JButton exit;
	private JLabel accumulateLabel;
	private JTextField accumulateCardCount;
	private boolean threadRun = true;
	private Thread thread;
	private JPanel bottomPanel;
	private JProgressBar time_limit;
	private JPanel controlPanel;
	private JButton stop;
	private boolean runAINeed = false;
	private Vector<OneCardBlock> virtual_blocks;
	private JDialog finishDialog;
	private JPanel finishDialog_mainPanel;
	private JPanel finishDialog_upPanel;
	private JPanel finishDialog_centerPanel;
	private JPanel finishDialog_downPanel;
	private JPanel finishDialog_controlPanel;
	private JButton finishDialog_close;
	private JPanel finishDialog_titlePanel;
	private JLabel finishDialog_title;
	private JPanel[] finishDialog_pns;
	private JLabel[] finishDialog_names;
	private JLabel[] finishDialog_cardLabels;
	private JTextField[] finishDialog_cardFields;
	private JPanel finishDialog_contentPanel;
	private int sel_mousex = 0;
	private int sel_mousey = 0;
	private int fin_mousex = 0;
	private int fin_mousey = 0;
	private JDialog endDialog;
	private JButton endDialog_button;
	private boolean paused = false;
	private JDialog messageDialog;
	private JPanel messagePanel;
	private JTextArea messages;
	private JScrollPane messages_scroll;
	private JLabel selectDialog_title;
	private JDialog sevenDialog;
	private JPanel sevenDialog_mainPanel;
	private JPanel sevenDialog_centerPanel;
	private JPanel sevenDialog_downPanel;
	private JButton[] sevenDialog_buttons;
	private boolean seven_payed;
	private JPanel sevenDialog_upPanel;
	private JLabel sevenDialog_title;
	private int sev_mousex = 0;
	private int sev_mousey = 0;
	private JMenuBar selectDialog_menu;
	private JMenu selectDialog_menu_file;
	private JMenuItem selectDialog_menu_exit;
	private JMenuItem selectDialog_menu_set;
	private JMenu selectDialog_menu_anothers;
	private JMenuItem selectDialog_menu_anothers_warn;
	private JCheckBoxMenuItem selectDialog_menu_anothers_calc;
	private String[] arguments;
	private JProgressBar sevenDialog_progress;
	private boolean seven_paused;
	private JCheckBoxMenuItem selectDialog_menu_anothers_conquer;
	private JCheckBox selectDialog_authorityMode;
	private boolean authorize_mode;
	private JPanel finishDialog_codePanel;
	private JLabel finishDialog_codeLabel;
	private JTextField finishDialog_codeField;
	private JButton finishDialog_copy;
	private JCheckBoxMenuItem selectDialog_menu_anothers_math;
	private JCheckBox selectDialog_scriptUse;
	private JMenuItem selectDialog_menu_anothers_city;
	private CityManager city;
	
	public OneCard(boolean independence, Setting set, String[] args)
	{
		this.independence = independence;
		this.sets = set.clone();
		this.arguments = args;
		
		//System.out.println(arguments);
		init();
	}
	
	public void init()
	{
		CalcWindow.prepareFont();
		this.setSize(sets.getWidth(), sets.getHeight());
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - (this.getHeight())/2));
		this.addWindowListener(this);
		this.setTitle(sets.getLang().getText(Language.ONECARD));
		blocks = new Vector<OneCardBlock>();
		deck = new Vector<Card>();
		center_deck = new Vector<Card>();
		given_queue = new Vector<Card>();
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		upPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new BorderLayout());
		downPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		deckPanel = new JPanel();
		title = new JLabel(sets.getLang().getText(Language.ONECARD));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		
		try
		{
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")).getImage());
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		
		upPanel.add(deckPanel, BorderLayout.CENTER);
		if(sets.isUse_own_titleBar())
		{
			this.setUndecorated(true);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.setLayout(new FlowLayout());
			titlePanel.add(title);
			titlePanel.setBorder(new EtchedBorder());
		}
		
		deckLabel1 = new JLabel(sets.getLang().getText(Language.DECK_LABEL1));
		deckLabel2 = new JLabel(sets.getLang().getText(Language.DECK_LABEL4));
		if(CalcWindow.usingFont != null)
			deckLabel1.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			deckLabel2.setFont(CalcWindow.usingFont);
		deckField = new JTextField(5);
		if(CalcWindow.usingFont != null)
			deckField.setFont(CalcWindow.usingFont);
		deckField.setEditable(false);
		take = new JButton(sets.getLang().getText(Language.GET));
		if(CalcWindow.usingFont != null)
			take.setFont(CalcWindow.usingFont);
		take.addActionListener(this);
		accumulateLabel = new JLabel(sets.getLang().getText(Language.ACCUMULATED) + " " + sets.getLang().getText(Language.CARD) + " " + sets.getLang().getText(Language.COUNT));
		if(CalcWindow.usingFont != null)
			accumulateLabel.setFont(CalcWindow.usingFont);
		accumulateCardCount = new JTextField(3);
		if(CalcWindow.usingFont != null)
			accumulateCardCount.setFont(CalcWindow.usingFont);
		accumulateCardCount.setEditable(false);
		accumulateCardCount.setBorder(new EtchedBorder());
		deckPanel.setLayout(new FlowLayout());
		deckPanel.add(deckLabel1);
		deckPanel.add(deckField);
		deckPanel.add(deckLabel2);
		deckPanel.add(take);
		deckPanel.add(accumulateLabel);
		deckPanel.add(accumulateCardCount);
		leftPanel = new JPanel();
		contentPanel = new JPanel();
		rightPanel = new JPanel();
		centerPanel.add(contentPanel, BorderLayout.CENTER);
		centerPanel.add(rightPanel, BorderLayout.EAST);
		centerPanel.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		playerListPanel = new JPanel();
		playerCountPanel = new JPanel();
		deckListPanel = new JPanel();
		deckCountPanel = new JPanel();
		deckLastPanel = new JPanel();
		playerListPanel.setLayout(new BorderLayout());
		deckListPanel.setLayout(new BorderLayout());
		playerCountPanel.setLayout(new FlowLayout());
		deckCountPanel.setLayout(new FlowLayout());
		deckLastPanel.setLayout(new BorderLayout());
		leftPanel.add(playerListPanel, BorderLayout.CENTER);
		leftPanel.add(playerCountPanel, BorderLayout.NORTH);
		rightPanel.add(deckListPanel, BorderLayout.CENTER);
		rightPanel.add(deckCountPanel, BorderLayout.NORTH);
		playerList = new JList();
		if(CalcWindow.usingFont != null)
			playerList.setFont(CalcWindow.usingFont);
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deckList = new JList();
		if(CalcWindow.usingFont != null)
			deckList.setFont(CalcWindow.usingFont);
		deckList.setEnabled(false);
		playerListScroll = new JScrollPane(playerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		deckListScroll = new JScrollPane(deckList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		playerListPanel.add(playerListScroll, BorderLayout.CENTER);
		deckListPanel.add(deckListScroll, BorderLayout.CENTER);
		pay = new JButton(sets.getLang().getText(Language.PAY));
		if(CalcWindow.usingFont != null)
			pay.setFont(CalcWindow.usingFont);
		pay.addActionListener(this);
		deckListPanel.add(deckLastPanel, BorderLayout.SOUTH);
		rightPanel.add(pay, BorderLayout.SOUTH);
		playerCountLabel = new JLabel(sets.getLang().getText(Language.PLAYER) + " " + sets.getLang().getText(Language.CARD) + " " + sets.getLang().getText(Language.COUNT));
		if(CalcWindow.usingFont != null)
			playerCountLabel.setFont(CalcWindow.usingFont);
		deckCountLabel = new JLabel(sets.getLang().getText(Language.DIR_CENTER) + " " + sets.getLang().getText(Language.CARD) + " " + sets.getLang().getText(Language.COUNT));
		if(CalcWindow.usingFont != null)
			deckCountLabel.setFont(CalcWindow.usingFont);
		playerCountField = new JTextField(5);
		if(CalcWindow.usingFont != null)
			playerCountField.setFont(CalcWindow.usingFont);
		deckCountField = new JTextField(5);
		if(CalcWindow.usingFont != null)
			deckCountField.setFont(CalcWindow.usingFont);
		playerCountField.setBorder(new EtchedBorder());
		deckCountField.setBorder(new EtchedBorder());
		playerCountField.setEditable(false);
		deckCountField.setEditable(false);
		playerCountPanel.add(playerCountLabel);
		deckCountPanel.add(deckCountLabel);
		playerCountPanel.add(playerCountField);
		deckCountPanel.add(deckCountField);
		deckLastLabel = new JLabel();
		if(CalcWindow.usingFont != null)
			deckLastLabel.setFont(CalcWindow.usingFont);
		deckLastField = new JTextField(5);
		if(CalcWindow.usingFont != null)
			deckLastField.setFont(CalcWindow.usingFont);
		deckLastField.setEditable(false);
		deckLastPanel.add(deckLastLabel, BorderLayout.WEST);
		deckLastPanel.add(deckLastField, BorderLayout.CENTER);
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		time_limit = new JProgressBar();
		controlPanel = new JPanel();		
		bottomPanel.add(time_limit, BorderLayout.CENTER);
		bottomPanel.add(controlPanel, BorderLayout.EAST);
		controlPanel.setLayout(new FlowLayout());
		stop = new JButton(sets.getLang().getText(Language.GAME_STOP));
		if(CalcWindow.usingFont != null)
			stop.setFont(CalcWindow.usingFont);
		stop.addActionListener(this);
		controlPanel.add(stop);
		downPanel.add(bottomPanel, BorderLayout.CENTER);
		
		selectDialog = new JDialog(this, false);
		selectDialog.setSize(400, 350);
		selectDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - selectDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - selectDialog.getHeight()/2));
		if(this.sets.isUse_own_titleBar()) selectDialog.setUndecorated(true);
		else
		{
			selectDialog.addWindowListener(this);
			selectDialog.setTitle(sets.getLang().getText(Language.TITLE));
		}
		selectDialog_mainPanel = new JPanel();
		selectDialog_mainPanel.setBorder(new EtchedBorder());
		selectDialog.setLayout(new BorderLayout());
		selectDialog.getContentPane().add(selectDialog_mainPanel);		
		selectDialog_mainPanel.setLayout(new BorderLayout());
		selectDialog_titlePanel = new JPanel();
		selectDialog_upPanel = new JPanel();
		selectDialog_centerPanel = new JPanel();
		selectDialog_downPanel = new JPanel();
		selectDialog_titlePanel.setLayout(new FlowLayout());
		selectDialog_centerPanel.setLayout(new GridLayout(sets.getSlots(), 1));
		selectDialog_centerScroll = new JScrollPane(selectDialog_centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		selectDialog_downPanel.setLayout(new BorderLayout());
		selectDialog_tab = new JTabbedPane(JTabbedPane.NORTH);
		if(CalcWindow.usingFont != null)
			selectDialog_tab.setFont(CalcWindow.usingFont);
		selectDialog_tab.add(sets.getLang().getText(Language.SINGLE) + " " + sets.getLang().getText(Language.PLAY), selectDialog_centerScroll);
		//selectDialog_tab.addChangeListener(this);
		selectDialog_mainPanel.add(selectDialog_tab, BorderLayout.CENTER);
		selectDialog_mainPanel.add(selectDialog_upPanel, BorderLayout.NORTH);
		selectDialog_upPanel.setLayout(new BorderLayout());
		if(sets.isUse_own_titleBar()) selectDialog_upPanel.add(selectDialog_titlePanel, BorderLayout.NORTH);
		selectDialog_menu = new JMenuBar();
		if(CalcWindow.usingFont != null)
			selectDialog_menu.setFont(CalcWindow.usingFont);
		selectDialog_upPanel.add(selectDialog_menu, BorderLayout.CENTER);
		selectDialog_mainPanel.add(selectDialog_downPanel, BorderLayout.SOUTH);
		selectDialog_bottomPanel = new JPanel();
		selectDialog_controlPanel = new JPanel();
		selectDialog_downPanel.add(selectDialog_bottomPanel, BorderLayout.SOUTH);
		selectDialog_downPanel.add(selectDialog_controlPanel, BorderLayout.CENTER);
		selectDialog_title = new JLabel(sets.getLang().getText(Language.ONECARD));
		if(CalcWindow.usingFont != null)
			selectDialog_title.setFont(CalcWindow.usingFont);
		if(this.sets.isUse_own_titleBar())
		{
			selectDialog_titlePanel.setBorder(new EtchedBorder());
			selectDialog_titlePanel.add(selectDialog_title);
			selectDialog_titlePanel.addMouseListener(this);
			selectDialog_titlePanel.addMouseMotionListener(this);
		}
		selectDialog_pns = new JPanel[sets.getSlots()];
		selectDialog_select = new ButtonGroup[selectDialog_pns.length];
		selectDialog_select_none = new JRadioButton[selectDialog_pns.length];
		selectDialog_select_player = new JRadioButton[selectDialog_pns.length];
		selectDialog_select_ai = new JRadioButton[selectDialog_pns.length];
		selectDialog_name = new JTextField[selectDialog_pns.length];
		for(int i=0; i<selectDialog_pns.length; i++)
		{
			selectDialog_pns[i] = new JPanel();
			selectDialog_pns[i].setLayout(new FlowLayout());
			selectDialog_centerPanel.add(selectDialog_pns[i]);
			selectDialog_select[i] = new ButtonGroup();
			selectDialog_select_none[i] = new JRadioButton(sets.getLang().getText(Language.NONE));
			selectDialog_select_player[i] = new JRadioButton(sets.getLang().getText(Language.PLAYER));
			selectDialog_select_ai[i] = new JRadioButton(sets.getLang().getText(Language.AI));
			if(CalcWindow.usingFont != null)
				selectDialog_select_none[i].setFont(CalcWindow.usingFont);
			if(CalcWindow.usingFont != null)
				selectDialog_select_player[i].setFont(CalcWindow.usingFont);
			if(CalcWindow.usingFont != null)
				selectDialog_select_ai[i].setFont(CalcWindow.usingFont);
			selectDialog_select_ai[i].setSelected(true);
			selectDialog_select[i].add(selectDialog_select_player[i]);
			selectDialog_select[i].add(selectDialog_select_ai[i]);
			selectDialog_select[i].add(selectDialog_select_none[i]);
			selectDialog_name[i] = new JTextField(8);
			if(CalcWindow.usingFont != null)
				selectDialog_name[i].setFont(CalcWindow.usingFont);
			selectDialog_name[i].setText(sets.getLang().getText(Language.PLAYER) + " " + String.valueOf((i+1)));
			selectDialog_pns[i].add(selectDialog_name[i]);
			selectDialog_pns[i].add(selectDialog_select_none[i]);
			selectDialog_pns[i].add(selectDialog_select_player[i]);
			selectDialog_pns[i].add(selectDialog_select_ai[i]);
			selectDialog_pns[i].setBorder(new EtchedBorder());
		}
		selectDialog_select_player[0].setSelected(true);
		for(int i=2; i<sets.getSlots(); i++)
		{
			selectDialog_select_ai[i].setSelected(true);
		}
		try
		{
			selectDialog_name[0].setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		start = new JButton(sets.getLang().getText(Language.START));
		start.addActionListener(this);
		if(CalcWindow.usingFont != null)
			start.setFont(CalcWindow.usingFont);
		exit = new JButton(sets.getLang().getText(Language.EXIT));
		exit.addActionListener(this);	
		if(CalcWindow.usingFont != null)
			exit.setFont(CalcWindow.usingFont);
		selectDialog_authorityMode = new JCheckBox(sets.getLang().getText(Language.AUTHORITY));
		if(CalcWindow.usingFont != null)
			selectDialog_authorityMode.setFont(CalcWindow.usingFont);
		selectDialog_authorityMode.addItemListener(this);		
		selectDialog_scriptUse = new JCheckBox(sets.getLang().getText(Language.INPUT) + " " + sets.getLang().getText(Language.SCRIPT) + " " + sets.getLang().getText(Language.USE));
		if(CalcWindow.usingFont != null)
			selectDialog_scriptUse.setFont(CalcWindow.usingFont);
		selectDialog_scriptUse.setSelected(true);
		selectDialog_scriptUse.setVisible(false);
		
		selectDialog_controlPanel.setLayout(new FlowLayout());
		selectDialog_controlPanel.add(selectDialog_authorityMode);
		selectDialog_controlPanel.add(selectDialog_scriptUse);
		selectDialog_bottomPanel.setLayout(new FlowLayout());
		selectDialog_bottomPanel.add(start);
		selectDialog_bottomPanel.add(exit);
		
		selectDialog_menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_file.setFont(CalcWindow.usingFont);
		selectDialog_menu_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_exit.setFont(CalcWindow.usingFont);
		selectDialog_menu_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		selectDialog_menu_exit.addActionListener(this);
		selectDialog_menu_set = new JMenuItem(sets.getLang().getText(Language.SETTING));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_set.setFont(CalcWindow.usingFont);
		selectDialog_menu_set.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_MASK));
		selectDialog_menu_set.addActionListener(this);
		
		selectDialog_menu_anothers = new JMenu(sets.getLang().getText(Language.ANOTHER_GAMES));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers.setFont(CalcWindow.usingFont);
		selectDialog_menu_anothers_warn = new JMenuItem(sets.getLang().getText(Language.NEED_RESTART));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers_warn.setFont(CalcWindow.usingFont);
		selectDialog_menu_anothers_warn.setEnabled(false);
		selectDialog_menu_anothers_calc = new JCheckBoxMenuItem(sets.getLang().getText(Language.TITLE));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers_calc.setFont(CalcWindow.usingFont);
		selectDialog_menu_anothers_calc.addItemListener(this);
		selectDialog_menu_anothers_conquer = new JCheckBoxMenuItem(sets.getLang().getText(Language.CONQUER));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers_conquer.setFont(CalcWindow.usingFont);
		selectDialog_menu_anothers_conquer.addItemListener(this);
		selectDialog_menu_anothers_math = new JCheckBoxMenuItem(sets.getLang().getText(Language.MATHCONQ));
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers_math.setFont(CalcWindow.usingFont);
		selectDialog_menu_anothers_math.addItemListener(this);
		
		selectDialog_menu_anothers_city = new JMenuItem(sets.getLang().getText(Language.SHELTER_CITY));
		selectDialog_menu_anothers_city.addActionListener(this);
		if(CalcWindow.usingFont != null)
			selectDialog_menu_anothers_city.setFont(CalcWindow.usingFont);
		
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_warn);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_calc);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_conquer);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_math);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_city);
		
		boolean city_available = false;
		try
		{
			if(sets.accepted())
			{
				city_available = true;
			}
			else if(sets.accept_net() && (! sets.abandoned_key()))
			{
				city_available = true;
			}
			else if(sets.accept_mastered() && (! sets.abandoned_key()))
			{
				city_available = true;
			}
		} 
		catch (Exception e)
		{
			city_available = false;
		}
		selectDialog_menu_anothers_city.setVisible(city_available);
		
		selectDialog_menu_file.add(selectDialog_menu_set);
		//selectDialog_menu_file.add(selectDialog_menu_anothers);
		selectDialog_menu_file.add(selectDialog_menu_exit);		
		
		selectDialog_menu.add(selectDialog_menu_file);
		selectDialog_menu.add(selectDialog_menu_anothers);
		
		if(RunManager.STANDALONE_MODE >= 0)
			selectDialog_menu_anothers.setVisible(false);
		
		finishDialog = new JDialog(this, true);
		finishDialog.setSize(400, 340);
		finishDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
		if(this.sets.isUse_own_titleBar()) finishDialog.setUndecorated(true);
		else
		{
			finishDialog.addWindowListener(this);
			finishDialog.setTitle(sets.getLang().getText(Language.RESULT));
		}
		finishDialog_mainPanel = new JPanel();
		finishDialog.getContentPane().setLayout(new BorderLayout());
		finishDialog.getContentPane().add(finishDialog_mainPanel);
		finishDialog_mainPanel.setBorder(new EtchedBorder());
		finishDialog_mainPanel.setLayout(new BorderLayout());
		finishDialog_upPanel = new JPanel();
		finishDialog_centerPanel = new JPanel();
		finishDialog_downPanel = new JPanel();
		finishDialog_mainPanel.add(finishDialog_centerPanel, BorderLayout.CENTER);
		finishDialog_mainPanel.add(finishDialog_upPanel, BorderLayout.NORTH);
		finishDialog_mainPanel.add(finishDialog_downPanel, BorderLayout.SOUTH);
		finishDialog_downPanel.setLayout(new BorderLayout());
		finishDialog_controlPanel = new JPanel();
		finishDialog_codePanel = new JPanel();
		finishDialog_downPanel.add(finishDialog_controlPanel, BorderLayout.CENTER);
		finishDialog_downPanel.add(finishDialog_codePanel, BorderLayout.NORTH);
		finishDialog_codePanel.setLayout(new FlowLayout());
		finishDialog_codeLabel = new JLabel(sets.getLang().getText(Language.POINT_CODE));
		finishDialog_codeField = new JTextField(20);
		if(CalcWindow.usingFont != null)
			finishDialog_codeField.setFont(CalcWindow.usingFont);
		finishDialog_codeField.setEditable(false);
		finishDialog_codeField.setBorder(new EtchedBorder());
		finishDialog_copy = new JButton(sets.getLang().getText(Language.COPY_CLIPBOARD));
		if(CalcWindow.usingFont != null)
			finishDialog_copy.setFont(CalcWindow.usingFont);
		finishDialog_copy.addActionListener(this);
		finishDialog_codePanel.add(finishDialog_codeLabel);
		finishDialog_codePanel.add(finishDialog_codeField);
		finishDialog_codePanel.add(finishDialog_copy);
		finishDialog_controlPanel.setLayout(new FlowLayout());
		finishDialog_close = new JButton(sets.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			finishDialog_close.setFont(CalcWindow.usingFont);
		finishDialog_close.addActionListener(this);
		finishDialog_controlPanel.add(finishDialog_close);
		finishDialog_centerPanel.setLayout(new BorderLayout());
		finishDialog_upPanel.setLayout(new BorderLayout());
		finishDialog_titlePanel = new JPanel();		
		finishDialog_titlePanel.setBorder(new EtchedBorder());
		finishDialog_titlePanel.setLayout(new FlowLayout());
		finishDialog_title = new JLabel(sets.getLang().getText(Language.RESULT));
		if(CalcWindow.usingFont != null)
			finishDialog_title.setFont(CalcWindow.usingFont);
		if(this.sets.isUse_own_titleBar())
		{
			finishDialog_upPanel.add(finishDialog_titlePanel, BorderLayout.CENTER);
			finishDialog_titlePanel.addMouseListener(this);
			finishDialog_titlePanel.addMouseMotionListener(this);
			finishDialog_titlePanel.add(finishDialog_title);
		}
		finishDialog_contentPanel = new JPanel();
		finishDialog_centerPanel.add(finishDialog_contentPanel);
		finishDialog_pns = new JPanel[blocks.size()];
		finishDialog_names = new JLabel[blocks.size()];
		finishDialog_cardLabels = new JLabel[blocks.size()];
		finishDialog_cardFields = new JTextField[blocks.size()];
		
		endDialog = new JDialog(this, false);
		endDialog.setUndecorated(true);
		endDialog.setSize(400, 300);
		endDialog_button = new JButton(sets.getLang().getText(Language.FINISH));
		endDialog_button.addActionListener(this);
		endDialog.getContentPane().setLayout(new BorderLayout());
		endDialog.getContentPane().add(endDialog_button);
		
		sevenDialog = new JDialog(this, false);
		sevenDialog.setUndecorated(true);		
		sevenDialog.setSize(500, 200);		
		sevenDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - sevenDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - sevenDialog.getHeight()/2));
		sevenDialog.getContentPane().setLayout(new BorderLayout());
		sevenDialog_mainPanel = new JPanel();
		sevenDialog.getContentPane().add(sevenDialog_mainPanel);
		sevenDialog_mainPanel.setLayout(new BorderLayout());
		sevenDialog_upPanel = new JPanel();
		sevenDialog_centerPanel = new JPanel();
		sevenDialog_downPanel = new JPanel();
		sevenDialog_mainPanel.add(sevenDialog_upPanel, BorderLayout.NORTH);
		sevenDialog_mainPanel.add(sevenDialog_centerPanel, BorderLayout.CENTER);
		sevenDialog_mainPanel.add(sevenDialog_downPanel, BorderLayout.SOUTH);		
		sevenDialog_buttons = new JButton[4];
		sevenDialog_centerPanel.setLayout(new GridLayout(1, sevenDialog_buttons.length));
		for(int i=0; i<sevenDialog_buttons.length; i++)
		{
			sevenDialog_buttons[i] = new JButton();
			if(CalcWindow.usingFont != null)
				sevenDialog_buttons[i].setFont(CalcWindow.usingFont);
			sevenDialog_centerPanel.add(sevenDialog_buttons[i]);
			sevenDialog_buttons[i].addActionListener(this);
		}
		sevenDialog_buttons[0].setText(String.valueOf(sets.getOp_spade()));
		sevenDialog_buttons[1].setText(String.valueOf(sets.getOp_clover()));
		sevenDialog_buttons[2].setText(String.valueOf(sets.getOp_diamond()));
		sevenDialog_buttons[3].setText(String.valueOf(sets.getOp_heart()));
		sevenDialog_buttons[0].setForeground(Color.BLACK);
		sevenDialog_buttons[1].setForeground(Color.BLACK);
		sevenDialog_buttons[2].setForeground(Color.RED);
		sevenDialog_buttons[3].setForeground(Color.RED);
		for(int i=0; i<sevenDialog_buttons.length; i++)
		{
			sevenDialog_buttons[i].setFont(new Font(null, Font.BOLD, 55));
		}
		sevenDialog_upPanel.setLayout(new FlowLayout());
		sevenDialog_upPanel.setBorder(new EtchedBorder());
		sevenDialog_title = new JLabel(sets.getLang().getText(Language.SELECT));
		sevenDialog_upPanel.add(sevenDialog_title);
		if(sets.isUse_own_titleBar())
		{
			sevenDialog_upPanel.addMouseListener(this);
			sevenDialog_upPanel.addMouseMotionListener(this);
		}
		sevenDialog_progress = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		sevenDialog_downPanel.setLayout(new BorderLayout());
		sevenDialog_downPanel.add(sevenDialog_progress);
		
		messageDialog = new JDialog(this, false);
		messageDialog.setSize(this.getWidth(), this.getHeight() / 6);		
		messageDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + (this.getHeight())));
		messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EtchedBorder());		
		messageDialog.setLayout(new BorderLayout());
		if(this.sets.isUse_own_titleBar()) messageDialog.setUndecorated(true);
		else messageDialog.addWindowListener(this);
		messageDialog.getContentPane().add(messagePanel);		
		messages = new JTextArea();	
		messages.setText("\n\n");
		messages.setLineWrap(true);
		messages.setEditable(false);
		messages_scroll = new JScrollPane(messages, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messages.setMaximumSize(new Dimension(sets.getWidth(), sets.getHeight() / 8));
		messagePanel.add(messages_scroll);	
		
		thread = new Thread(this);
		
		theme_refresh();
	}
	public void theme_refresh()
	{
		titlePanel.setBackground(sets.getSelected_inside_back());
		title.setForeground(sets.getSelected_fore());
		//title.setForeground(Color.RED);
		accumulateCardCount.setBackground(sets.getSelected_inside_back());
		accumulateCardCount.setForeground(sets.getSelected_fore());
		accumulateLabel.setForeground(sets.getSelected_fore());
		upPanel.setBackground(sets.getSelected_back());
		deckPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		mainPanel.setBackground(sets.getSelected_back());
		deckLabel1.setForeground(sets.getSelected_fore());
		deckLabel2.setForeground(sets.getSelected_fore());
		deckField.setForeground(sets.getSelected_fore());
		deckField.setBackground(sets.getSelected_inside_back());
		contentPanel.setBackground(sets.getSelected_back());
		rightPanel.setBackground(sets.getSelected_back());
		leftPanel.setBackground(sets.getSelected_back());
		deckCountPanel.setBackground(sets.getSelected_back());
		deckLastPanel.setBackground(sets.getSelected_back());
		deckListPanel.setBackground(sets.getSelected_back());
		deckList.setBackground(sets.getUnselected_inside_back());
		deckList.setForeground(sets.getUnselected_fore());
		deckCountLabel.setForeground(sets.getUnselected_fore());
		deckCountField.setForeground(sets.getUnselected_fore());
		deckCountField.setBackground(sets.getUnselected_inside_back());
		deckLastLabel.setForeground(sets.getUnselected_fore());
		deckLastField.setForeground(sets.getUnselected_fore());
		deckLastField.setBackground(sets.getUnselected_inside_back());
		playerCountPanel.setBackground(sets.getSelected_back());
		playerListPanel.setBackground(sets.getSelected_back());
		playerList.setBackground(sets.getSelected_inside_back());
		playerList.setForeground(sets.getSelected_fore());
		playerCountLabel.setForeground(sets.getSelected_fore());
		playerCountField.setForeground(sets.getSelected_fore());
		playerCountField.setBackground(sets.getSelected_inside_back());
		controlPanel.setBackground(sets.getSelected_back());
		bottomPanel.setBackground(sets.getSelected_back());
		messages.setBackground(sets.getSelected_inside_back());
		messages.setForeground(sets.getSelected_fore());
		start.setForeground(sets.getSelected_fore());
		stop.setForeground(sets.getSelected_fore());
		exit.setForeground(sets.getSelected_fore());
		take.setForeground(sets.getSelected_fore());
		pay.setForeground(sets.getSelected_fore());
		selectDialog_title.setForeground(sets.getSelected_fore());
		selectDialog_mainPanel.setBackground(sets.getSelected_back());
		selectDialog_centerPanel.setBackground(sets.getSelected_back());
		selectDialog_titlePanel.setBackground(sets.getSelected_inside_back());
		selectDialog_downPanel.setBackground(sets.getSelected_back());
		selectDialog_bottomPanel.setBackground(sets.getSelected_back());
		selectDialog_controlPanel.setBackground(sets.getSelected_back());
		selectDialog_tab.setBackground(sets.getSelected_back());
		selectDialog_tab.setForeground(sets.getSelected_fore());
		for(int i=0; i<selectDialog_pns.length; i++)
		{
			selectDialog_pns[i].setBackground(sets.getSelected_back());
			selectDialog_name[i].setBackground(sets.getSelected_inside_back());
			selectDialog_name[i].setForeground(sets.getSelected_fore());
			selectDialog_select_ai[i].setBackground(sets.getSelected_back());
			selectDialog_select_none[i].setBackground(sets.getSelected_back());
			selectDialog_select_player[i].setBackground(sets.getSelected_back());
			selectDialog_select_ai[i].setForeground(sets.getSelected_fore());
			selectDialog_select_none[i].setForeground(sets.getSelected_fore());
			selectDialog_select_player[i].setForeground(sets.getSelected_fore());
		}
		selectDialog_menu.setBackground(sets.getSelected_back());
		selectDialog_menu.setForeground(sets.getSelected_fore());
		selectDialog_menu_anothers.setBackground(sets.getSelected_back());
		selectDialog_menu_anothers.setForeground(sets.getSelected_fore());
		selectDialog_menu_anothers_calc.setBackground(sets.getSelected_back());
		selectDialog_menu_anothers_calc.setForeground(sets.getSelected_fore());
		selectDialog_menu_anothers_conquer.setBackground(sets.getSelected_back());
		selectDialog_menu_anothers_conquer.setForeground(sets.getSelected_fore());
		selectDialog_menu_anothers_math.setBackground(sets.getSelected_back());
		selectDialog_menu_anothers_math.setForeground(sets.getSelected_fore());
		selectDialog_menu_anothers_warn.setBackground(sets.getUnselected_back());
		selectDialog_menu_anothers_warn.setForeground(sets.getUnselected_fore());
		selectDialog_menu_anothers_city.setBackground(sets.getSelected_back());
		selectDialog_menu_anothers_city.setForeground(sets.getSelected_fore());
		selectDialog_menu_exit.setBackground(sets.getSelected_back());
		selectDialog_menu_exit.setForeground(sets.getSelected_fore());
		selectDialog_menu_set.setBackground(sets.getSelected_back());
		selectDialog_menu_set.setForeground(sets.getSelected_fore());
		selectDialog_menu_file.setBackground(sets.getSelected_back());
		selectDialog_menu_file.setForeground(sets.getSelected_fore());
		selectDialog_authorityMode.setBackground(sets.getSelected_back());
		selectDialog_authorityMode.setForeground(sets.getSelected_fore());
		selectDialog_scriptUse.setBackground(sets.getSelected_back());
		selectDialog_scriptUse.setForeground(sets.getSelected_fore());
		finishDialog_mainPanel.setBackground(sets.getSelected_back());
		finishDialog_centerPanel.setBackground(sets.getSelected_back());
		finishDialog_downPanel.setBackground(sets.getSelected_back());
		finishDialog_upPanel.setBackground(sets.getSelected_back());
		finishDialog_controlPanel.setBackground(sets.getSelected_back());
		finishDialog_contentPanel.setBackground(sets.getSelected_back());
		finishDialog_titlePanel.setBackground(sets.getSelected_inside_back());
		finishDialog_title.setForeground(sets.getSelected_fore());
		finishDialog_codePanel.setBackground(sets.getSelected_back());
		finishDialog_codeField.setBackground(sets.getSelected_inside_back());
		finishDialog_codeField.setForeground(sets.getSelected_fore());
		finishDialog_codeLabel.setForeground(sets.getSelected_fore());
		finishDialog_close.setForeground(sets.getSelected_fore());
		finishDialog_copy.setForeground(sets.getSelected_fore());
		for(int i=0; i<finishDialog_pns.length; i++)
		{
			if(finishDialog_pns[i] != null) finishDialog_pns[i].setBackground(sets.getSelected_back());
			if(finishDialog_names[i] != null) finishDialog_names[i].setForeground(sets.getSelected_fore());
			if(finishDialog_cardLabels[i] != null) finishDialog_cardLabels[i].setForeground(sets.getSelected_fore());
			if(finishDialog_cardFields[i] != null) finishDialog_cardFields[i].setForeground(sets.getSelected_fore());
			if(finishDialog_cardFields[i] != null) finishDialog_cardFields[i].setBackground(sets.getSelected_inside_back());
		}
		sevenDialog_title.setForeground(sets.getSelected_fore());
		sevenDialog_mainPanel.setBackground(sets.getSelected_back());
		sevenDialog_upPanel.setBackground(sets.getSelected_inside_back());
	}
	public void center_refresh()
	{
		contentPanel.removeAll();
		contentPanel.setLayout(new GridLayout(blocks.size(), 1));
		for(int i=0; i<blocks.size(); i++)
		{
			contentPanel.add(blocks.get(i));
			blocks.get(i).refresh();
		}
	}
	public void deck_refresh()
	{
		deckField.setText(String.valueOf(deck.size()));
		String[] listData = new String[center_deck.size()];
		for(int i=0; i<listData.length; i++)
		{
			listData[i] = new String(center_deck.get(i).toBasicString(sets.getCard_separator()));
		}
		deckList.setListData(listData);
		if(center_deck.size() >= 1) deckLastField.setText(center_deck.lastElement().toBasicString(sets.getCard_separator()));
		else deckLastField.setText("");
		accumulateCardCount.setText(String.valueOf(given_queue.size()));
		deckCountField.setText(String.valueOf(center_deck.size()));
	}
	public void start()
	{
		selectDialog.setVisible(false);
		this.setVisible(false);
		endDialog.setVisible(false);
		sevenDialog.setVisible(false);
		pay.setEnabled(true);
		take.setEnabled(true);
		stop.setEnabled(true);
		deck.clear();
		blocks.clear();
		center_deck.clear();
		given_queue.clear();
		given_lasts = null;
		paused = false;
		seven_paused = false;
		seven_payed = false;
		endDialog.setVisible(false);
		
		authorize_mode = selectDialog_authorityMode.isSelected();
	
		Vector<OneCardBlock> newBlocks = new Vector<OneCardBlock>();
		if(authorize_mode)
		{
			OneCardBlock newBlock = new OneCardBlock(sets.clone());
			newBlock.ai = false;
			newBlock.setName(selectDialog_name[0].getText());
			newBlocks.add(newBlock);
			newBlock = new OneCardBlock(sets.clone());
			newBlock.ai = true;
			newBlock.setName(sets.getLang().getText(Language.AI) + " 1");
			newBlocks.add(newBlock);
			newBlock = newBlock.clone();
			newBlock.setName(sets.getLang().getText(Language.AI) + " 2");
			newBlocks.add(newBlock);
			newBlock = newBlock.clone();
			newBlock.setName(sets.getLang().getText(Language.AI) + " 3");
			newBlocks.add(newBlock);
		}
		else
		{
			for(int i=0; i<sets.getSlots(); i++)
			{
				OneCardBlock newBlock = new OneCardBlock(sets.clone());
				if(selectDialog_select_none[i].isSelected())
				{
					
				}
				else if(selectDialog_select_player[i].isSelected())
				{
					newBlock.ai = false;	
					newBlock.setName(selectDialog_name[i].getText());
					newBlocks.add(newBlock);
				}
				else if(selectDialog_select_ai[i].isSelected())
				{
					newBlock.ai = true;	
					newBlock.setName(selectDialog_name[i].getText());
					newBlocks.add(newBlock);
				}
			}
		}
		int random_block = 0;
		while(newBlocks.size() >= 1)
		{
			random_block = (int) (Math.random() * newBlocks.size());
			blocks.add(newBlocks.get(random_block));
			newBlocks.remove(random_block);
		}
		int repeat_count = 2;
		repeat_count = blocks.size() / 2;
		Vector<Card> newDeck = new Vector<Card>();
		Card newCard;
		char op = '◆';
		for(int i=1; i<=13; i++)
		{
			for(int j=0; j<4; j++)
			{
				switch(j)
				{
					case 0:
						op = sets.getOp_clover();
						break;
					case 1:
						op = sets.getOp_spade();
						break;
					case 2:
						op = sets.getOp_diamond();
						break;
					case 3:
						op = sets.getOp_heart();
						break;
				}				
				for(int k=0; k<repeat_count; k++)
				{
					newCard = new StandardCard(op, i);
					newDeck.add(newCard);
				}
			}
		}	
		newCard = new Joker(false);
		newDeck.add(newCard);
		newCard = new Joker(true);
		newDeck.add(newCard);
		while(newDeck.size() >= 1)
		{
			random_block = (int) (Math.random() * newDeck.size());
			deck.add(newDeck.get(random_block));
			newDeck.remove(random_block);
		}		
		center_deck.add(deck.get(0));
		deck.remove(0);		
				
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<blocks.size(); j++)
			{
				blocks.get(j).owns.add(deck.get(0));
				deck.remove(0);
			}
		}
		deck_refresh();
		
		setTurn(0, true);
		
		center_refresh();
		theme_refresh();
		message(sets.getLang().getText(Language.DESCRIPTIONS + 11));
		
		this.setVisible(true);
		active = true;
		if(blocks.get(getTurn()).isAi())
		{
			runAINeed  = true;
		}
	}
	public void finish()
	{
		int min_card_count = blocks.get(0).owns.size();
		int winner = 0, not_ai = 0;
		
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).owns.size() < min_card_count)
			{
				min_card_count = blocks.get(i).owns.size();
				winner = i;
			}
			if(! blocks.get(i).ai) not_ai = i;
		}
				
		finishDialog_contentPanel.removeAll();
		finishDialog_pns = new JPanel[blocks.size()];
		finishDialog_names = new JLabel[blocks.size()];
		finishDialog_cardLabels = new JLabel[blocks.size()];
		finishDialog_cardFields = new JTextField[blocks.size()];
		finishDialog_contentPanel.setLayout(new GridLayout(blocks.size(), 1));
		for(int i=0; i<finishDialog_pns.length; i++)
		{
			finishDialog_pns[i] = new JPanel();
			finishDialog_pns[i].setBorder(new EtchedBorder());
			finishDialog_pns[i].setLayout(new FlowLayout());
			finishDialog_names[i] = new JLabel(blocks.get(i).getName());
			if(CalcWindow.usingFont != null)
				finishDialog_names[i].setFont(CalcWindow.usingFont);
			finishDialog_cardLabels[i] = new JLabel(sets.getLang().getText(Language.CARD) + " " + sets.getLang().getText(Language.COUNT));
			if(CalcWindow.usingFont != null)
				finishDialog_cardLabels[i].setFont(CalcWindow.usingFont);
			finishDialog_cardFields[i] = new JTextField(5);
			if(CalcWindow.usingFont != null)
				finishDialog_cardFields[i].setFont(CalcWindow.usingFont);
			finishDialog_cardFields[i].setText(String.valueOf(blocks.get(i).owns.size()));
			finishDialog_cardFields[i].setEditable(false);
			finishDialog_pns[i].add(finishDialog_names[i]);
			finishDialog_pns[i].add(finishDialog_cardLabels[i]);
			finishDialog_pns[i].add(finishDialog_cardFields[i]);
			finishDialog_contentPanel.add(finishDialog_pns[i]);
		}
		finishDialog.setTitle(sets.getLang().getText(Language.RESULT) + " : " + blocks.get(winner).getName() + " " + sets.getLang().getText(Language.WON));
		if(sets.isUse_own_titleBar()) finishDialog_title.setText(sets.getLang().getText(Language.RESULT) + " : " + blocks.get(winner).getName() + " " + sets.getLang().getText(Language.WON));
				
		if(authorize_mode && winner == not_ai)
		{
			int result_point = 0;
			for(int i=0; i<blocks.size(); i++)
			{
				if(i != not_ai) result_point += blocks.get(i).owns.size();
			}
			result_point -= blocks.get(winner).owns.size();
			
			StringBuffer auth_code = new StringBuffer("");
			String[] auth_codes = new String[17];
			auth_codes[0] = String.valueOf(result_point);
			auth_codes[1] = blocks.get(not_ai).name + " (＠)";
			auth_codes[2] = String.valueOf(blocks.size());
			auth_codes[3] = String.valueOf(blocks.get(not_ai).owns.size());
			auth_codes[4] = String.valueOf(CalcWindow.version_main);
			auth_codes[5] = String.valueOf(CalcWindow.version_sub_1);
			auth_codes[6] = String.valueOf(CalcWindow.version_sub_2);
			BigInteger secret_code = new BigInteger("0");
			BigInteger secret_nameCode = new BigInteger("0");
			secret_code = secret_code.add(new BigInteger(String.valueOf((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2)));
			secret_code = secret_code.multiply(new BigInteger(String.valueOf(blocks.get(not_ai).owns.size())));
			secret_code = secret_code.add(new BigInteger(String.valueOf(result_point)));
			// authority_code used
			long authority_code = CalcWindow.getAuthorizedCode(2938291);
			secret_code = secret_code.add(Lint.big(authority_code + (blocks.size() * ((CalcWindow.version_main * 100) + (CalcWindow.version_sub_1 * 10) + CalcWindow.version_sub_2))));
									
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
			auth_codes[14] = "onecard";
			auth_codes[15] = "Calc";
			
			secret_nameCode = Lint.copy(secret_code);
			secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(blocks.get(not_ai).name + " (＠)") / 100.0) + 5)));
			secret_code = secret_code.add(Lint.newBigInteger((blocks.get(not_ai).owns.size() + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
			secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
			auth_codes[7] = secret_code.toString();			
			auth_codes[16] = secret_nameCode.toString();			
			
			for(int i=0; i<auth_codes.length; i++)
			{
				auth_code.append(auth_codes[i]);
				if(i < auth_codes.length - 1) auth_code.append("||");
			}			
			finishDialog_codeField.setText(auth_code.toString());
		}
		else if(authorize_mode)
		{
			finishDialog_codeField.setText(sets.getLang().getText(Language.DESCRIPTIONS + 21));
		}
		else
		{
			finishDialog_codeField.setText(sets.getLang().getText(Language.DESCRIPTIONS + 18));
		}
		
		active = false;
		theme_refresh();
		finishDialog.setVisible(true);
	}
	public void setTurn(int turn, boolean refresh_list)
	{
		setTurn(turn);
		if(refresh_list)
		{
			String[] listData = new String[blocks.get(getTurn()).owns.size()];
			for(int i=0; i<listData.length; i++)
			{
				listData[i] = new String(blocks.get(getTurn()).owns.get(i).toBasicString(sets.getCard_separator()));
			}
			playerList.setListData(listData);
			playerCountField.setText(String.valueOf(listData.length));
		}
	}
	public void setTurn(int turn)
	{
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).turn = false;
		}
		blocks.get(turn).turn = true;
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).refresh();
		}		
	}
	public int getTurn()
	{
		int result = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).turn)
			{
				result = i;
				break;
			}
		}
		return result;
	}
	public int getNextTurn()
	{
		int result = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).turn)
			{
				result = i;
				break;
			}
		}
		if(order_round)
		{
			result--;
			if(result <= -1)
			{
				result = blocks.size() - 1;
			}
		}
		else
		{
			result++;
			if(result >= blocks.size())
			{
				result = 0;
			}
		}
		return result;
	}
	public void give(int howMany)
	{		
		boolean left_empty = false;
		for(int i=0; i<howMany; i++)
		{
			if(deck.size() <= 0)
			{
				left_empty = true;
				break;
			}
			given_queue.add(deck.get(0));
			deck.remove(0);
		}
		if(left_empty)
		{
			finish();
		}
	}
	public void takeLeft()
	{
		if(given_queue.size() >= 1) message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.ONECARD_PANELTY) + String.valueOf(given_queue.size()));
		while(given_queue.size() >= 1)
		{
			blocks.get(getTurn()).owns.add(given_queue.get(0));
			given_queue.remove(0);
		}		
	}
	public void runAI()
	{
		try
		{
			if(virtual_blocks == null) virtual_blocks = new Vector<OneCardBlock>();
			virtualBlockReset();
			int cards = virtual_blocks.get(getTurn()).owns.size();
			int promising_card = 0;
			int calc_level = 0;
			int calc_selected = 0;
			boolean pay_available = false;
			boolean skip_given_available = false;
			Card lasts = null;
			Card nowCard = null;
			if(center_deck.size() >= 1) lasts = center_deck.lastElement();
			for(int i=0; i<cards; i++)
			{
				virtualBlockReset();
				nowCard = virtual_blocks.get(getTurn()).owns.get(i);
				if(lasts == null)
				{
					promising_card = i;
					pay_available = true;
					if(given_lasts != null && given_lasts.grade(sets) >= 1)
					{
						calc_level = nowCard.grade(sets);
						if(calc_level >= given_lasts.grade(sets))
						{
							calc_selected = i;
							skip_given_available = true;
						}
					}
					else break;
				}
				else if(lasts.getType() == Card.STANDARD)
				{
					StandardCard stCard = (StandardCard) lasts;
					if(nowCard.getType() == Card.STANDARD)
					{
						StandardCard nowStCard = (StandardCard) nowCard;
						if(stCard.getOp() == nowStCard.getOp() || stCard.getNum() == nowStCard.getNum())
						{
							/*
							if(given_lasts != null)
							{
								if(given_lasts.grade(set) > nowCard.grade(set))
								{
									takeLeft();
								}
							}*/
							promising_card = i;
							pay_available = true;
							/*
							if(nowStCard.getNum() == 2)
							{
								give(2);
								given_lasts = nowCard.clone();
							}
							else if(nowStCard.getNum() == 1)
							{
								if(nowStCard.getOp() == set.op_spade) give(5);
								else give(3);
								given_lasts = nowCard.clone();
							}*/
							if(given_lasts != null && given_lasts.grade(sets) >= 1)
							{
								calc_level = nowCard.grade(sets);
								if(calc_level >= given_lasts.grade(sets))
								{
									calc_selected = i;
									skip_given_available = true;
								}
							}
							else break;
						}
						else
						{
							pay_available = false;
						}					
					}
					else if(nowCard.getType() == Card.JOKER)
					{
						/*
						if(given_lasts != null)
						{
							if(given_lasts.grade(set) > nowCard.grade(set))
							{
								takeLeft();
							}
						}*/
						promising_card = i;
						pay_available = true;
						/*
						if(((Joker) nowCard).isColored())
						{
							give(10);
						}
						else give(5);
						given_lasts = nowCard.clone();*/
						if(given_lasts != null && given_lasts.grade(sets) >= 1)
						{
							calc_level = nowCard.grade(sets);
							if(calc_level >= given_lasts.grade(sets))
							{
								calc_selected = i;
								skip_given_available = true;
							}
						}
						else break;
					}
					else
					{
						pay_available = false;	
					}				
				}
				else if(lasts.getType() == Card.JOKER)
				{
					/*
					if(given_lasts != null)
					{
						if(given_lasts.grade(set) > nowCard.grade(set))
						{
							takeLeft();
						}
					}*/
					promising_card = i;
					pay_available = true;
					/*
					if(nowCard.getType() == Card.JOKER)
					{
						if(((Joker) nowCard).isColored())
						{
							give(10);
						}
						else give(5);
						given_lasts = nowCard.clone();
					}
					else if(((StandardCard) nowCard).getNum() == 2)
					{
						give(2);
						given_lasts = nowCard.clone();
					}
					else if(((StandardCard) nowCard).getNum() == 1)
					{
						if(((StandardCard) nowCard).getOp() == set.op_spade) give(5);
						else give(3);
						given_lasts = nowCard.clone();
					}*/
					if(given_lasts != null && given_lasts.grade(sets) >= 1)
					{
						calc_level = nowCard.grade(sets);
						if(calc_level >= given_lasts.grade(sets))
						{
							calc_selected = i;
							skip_given_available = true;
						}
					}
					else break;				
				}
				else
				{
					pay_available = false;				
				}
			}
			if(pay_available)
			{
				if(given_lasts != null && given_lasts.grade(sets) >= 1) 
				{
					if(skip_given_available)
					{
						pay(calc_selected, true);
					}
					else
					{
						pay(promising_card, true);
					}
				}
				else
				{
					pay(promising_card, true);
				}
			}
			else
			{
				take(true);
			}
		} 
		catch (Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
			take();
		}
	}
	private void virtualBlockReset()
	{
		virtual_blocks.clear();
		for(int i=0; i<blocks.size(); i++)
		{
			virtual_blocks.add(blocks.get(i).clone());
		}
	}
	public void take()
	{
		take(false);
	}
	public void take(boolean ai)
	{
		if(active)
		{
			if(given_queue.size() >= 1) takeLeft();
			if(deck.size() <= 0)
			{
				finish();
				return;
			}
			blocks.get(getTurn()).owns.add(deck.get(0));
			deck.remove(0);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 0));
			controlled(true, false, ai);
		}
	}
	public void pay(int select_index, boolean ai)
	{
		
		if (active)
		{
			if (select_index >= 0)
			{
				Card takeCard = blocks.get(getTurn()).owns.get(select_index);
				//Card lastCard = center_deck.lastElement();
				int takeCard_type = takeCard.getType();
				int given_grade = 0;
				boolean pay_available = false;
				boolean given_take = false;
				boolean jump = false;
				boolean oneMore = false;
				seven_payed = false;

				if (given_queue.size() >= 1)
					given_take = true;
				if (given_lasts != null)
					given_grade = given_lasts.grade(sets);
				else
					given_grade = 0;
				if (takeCard_type == Card.STANDARD)
				{
					StandardCard stCard = (StandardCard) takeCard;
					pay_available = true;
					if (center_deck.lastElement().getType() == Card.JOKER
							|| (center_deck.lastElement().getType() == Card.STANDARD && (((StandardCard) center_deck
									.lastElement()).getOp() == stCard.getOp() || ((StandardCard) center_deck
									.lastElement()).getNum() == stCard.getNum())))
					{
						if (given_take)
						{
							if (given_grade <= stCard.grade(sets))
							{

							} else
							{
								takeLeft();
							}
						}
						given_lasts = null;
						if (stCard.getNum() == 1)
						{
							if (stCard.getOp() == sets.getOp_spade())
							{
								give(5);
								given_lasts = stCard.clone();
							} else
							{
								give(3);
								given_lasts = stCard.clone();
							}
						} 
						else if (stCard.getNum() == 2)
						{
							give(2);
							given_lasts = stCard.clone();
						} 
						else if(stCard.getNum() == 7)
						{
							seven_payed = true;
						}
						else if (stCard.getNum() == 11)
						{
							jump = true;
						} 
						else if (stCard.getNum() == 12)
						{
							order_round = (!order_round);
						} 
						else if (stCard.getNum() == 13)
						{
							oneMore = true;
						}
					} else
					{
						pay_available = false;
					}
				} 
				else if (takeCard_type == Card.JOKER)
				{
					Joker jk = (Joker) takeCard;

					if (jk.isColored())
					{
						give(10);
						given_lasts = jk.clone();
						pay_available = true;
					} 
					else
					{
						if (given_lasts == null)
						{
							give(5);
							given_lasts = jk.clone();
							pay_available = true;
						}
						else if (given_lasts.getType() == Card.JOKER)
						{
							if (((Joker) given_lasts).colored)
							{
								takeLeft();
								give(5);
								given_lasts = jk.clone();
								pay_available = true;
							} 
							else
							{
								give(5);
								given_lasts = jk.clone();
								pay_available = true;
							}
						}
						else
						{
							give(5);
							given_lasts = jk.clone();
							pay_available = true;
						}
					}
				}

				if (pay_available)
				{
					blocks.get(getTurn()).owns.remove(select_index);
					if(seven_payed)
					{
						getSeven(ai);						
					}
					else
					{						
						center_deck.add(takeCard);
						message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
						deck_refresh();
						if (active)
							controlled((!oneMore), jump, ai);
					}
				}
				else
				{
					alert(center_deck.lastElement().toBasicString(sets.getCard_separator()) + sets.getLang().getText(Language.DESCRIPTIONS + 7));
				}
			} 
			else
			{
				// Don't choosed
				alert(sets.getLang().getText(Language.DESCRIPTIONS + 8));
			}
		}
	}
	private void getSeven(boolean ai)
	{
		if(ai)
		{
			char exist_op = sets.getOp_spade();
			boolean exist_check = false;
			int randoms;
			Card cards;
			for(int i=0; i<blocks.get(getTurn()).owns.size(); i++)
			{
				cards = blocks.get(getTurn()).owns.get(i);
				if(cards.getType() == Card.STANDARD)
				{
					StandardCard stCard = (StandardCard) cards;
					if(stCard.getOp() == sets.getOp_spade())
					{
						exist_op = sets.getOp_spade();
						exist_check = true;
					}
					else if(stCard.getOp() == sets.getOp_clover())
					{
						exist_op = sets.getOp_clover();
						exist_check = true;
					}
					else if(stCard.getOp() == sets.getOp_diamond())
					{
						exist_op = sets.getOp_diamond();
						exist_check = true;
					}
					else if(stCard.getOp() == sets.getOp_heart())
					{
						exist_op = sets.getOp_heart();
						exist_check = true;
					}
				}
				else if(cards.getType() == Card.JOKER)
				{
					exist_check = false;
				}
			}
			StandardCard takeCard = new StandardCard();
			takeCard.setNum(7);
			if(exist_check)
			{
				takeCard.setOp(exist_op);
			}
			else
			{				
				randoms = (int) Math.random() * 4;
				switch(randoms)
				{
					case 0: 
						takeCard.setOp(sets.getOp_spade());
						break;
					case 1:
						takeCard.setOp(sets.getOp_clover());
						break;
					case 2:
						takeCard.setOp(sets.getOp_diamond());
						break;
					case 3:
						takeCard.setOp(sets.getOp_heart());
						break;
					default:
						takeCard.setOp(sets.getOp_spade());
				}					
				
			}
			boolean oneMore = false;
			boolean jump = false;
			// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
			center_deck.add(takeCard);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
			deck_refresh();
			if (active)
				controlled((!oneMore), jump, ai);
			paused = false;
		}
		else
		{
			paused = true;
			seven_payed = false;
			seven_paused = true;
			sevenDialog.setSize(this.getWidth(), this.getHeight() - upPanel.getHeight());
			sevenDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + upPanel.getHeight()));
			pay.setEnabled(false);
			take.setEnabled(false);
			sevenDialog.setVisible(true);
		}
	}
	public void pay()
	{
		int select_index = playerList.getSelectedIndex();
		pay(select_index, false);
	}
	public void controlled(boolean next, boolean jump, boolean ai)
	{
		if (active)
		{
			for(int i=0; i<blocks.size(); i++)
			{
				if(blocks.get(i).owns.size() == 1)
				{
					message(sets.getLang().getText(Language.DESCRIPTIONS + 10) + blocks.get(i).getName());
				}
			}
			if(! ai)
			{
				if(! blocks.get(getTurn()).ai)
				{
					paused = true;
					time_limit_number1 = 100;
					endDialog.setSize(this.getWidth(), this.getHeight() - upPanel.getHeight());
					endDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + upPanel.getHeight()));
					//endDialog.setLocation((int) centerPanel.getLocation().getX(), (int) centerPanel.getLocation().getY());
					if(! active) return;
					endDialog.setVisible(true);
				}
			}
			deck_refresh();
			if (deck.size() <= 0)
			{
				if(center_deck.size() >= 2)
				{
					Card lastOne = center_deck.lastElement();
					center_deck.remove(center_deck.size() - 1);
					Vector<Card> appendDeck = new Vector<Card>();
					int random_number = 0;
					while(center_deck.size() >= 1)
					{
						random_number = (int) (Math.random() * center_deck.size());
						appendDeck.add(center_deck.get(random_number));
						center_deck.remove(random_number);
					}
					for(int k=0; k<appendDeck.size(); k++)
					{
						deck.add(appendDeck.get(k));
					}
					appendDeck.clear();
					center_deck.add(lastOne);
				}
				else 
				{
					finish();
					return;
				}
			} 
			else
			{
				boolean whoIsFinished = false;
				for (int i = 0; i < blocks.size(); i++)
				{
					if (blocks.get(i).owns.size() <= 0)
					{
						whoIsFinished = true;
						break;
					}
				}
				if (whoIsFinished)
				{
					finish();
					return;
				}
			}
			if (next)
			{
				if (order_round)
				{
					if (jump)
					{
						if (getTurn() <= 0)
						{
							setTurn(blocks.size() - 2, true);
						}
						if (getTurn() == 1)
						{
							setTurn(blocks.size() - 1, true);
						} 
						else
						{
							setTurn(getTurn() - 2, true);
						}
					} 
					else
					{
						if (getTurn() <= 0)
						{
							setTurn(blocks.size() - 1, true);
						} 
						else
						{
							setTurn(getTurn() - 1, true);
						}
					}
					if (blocks.get(getTurn()).isAi())
					{
						runAI();
					}
				} 
				else
				{
					if (jump)
					{
						if (getTurn() >= blocks.size() - 1)
						{
							setTurn(1, true);
						} else if (getTurn() == blocks.size() - 2)
						{
							setTurn(0, true);
						} else
						{
							setTurn(getTurn() + 2, true);
						}
					} 
					else
					{
						if (getTurn() >= blocks.size() - 1)
						{
							setTurn(0, true);
						} else
						{
							setTurn(getTurn() + 1, true);
						}
					}
				}
				if (blocks.get(getTurn()).isAi())
				{
					runAI();
				}
			} 
			else
			{
				setTurn(getTurn(), true);
				if (blocks.get(getTurn()).isAi())
				{
					runAI();
				}
			}
		}
	}
	public void controlled()
	{		
		controlled(true, false, false);
	}
	@Override
	public void open()
	{
		this.setVisible(true);
		messageDialog.setVisible(true);
		pay.setEnabled(false);
		take.setEnabled(false);
		stop.setEnabled(false);
		selectDialog.setVisible(true);
		thread.run();
	}

	@Override
	public void exit()
	{
		threadRun = false;
		if(independence) System.exit(0);
		else this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == exit)
		{
			exit();
		}
		else if(ob == selectDialog_menu_exit)
		{
			exit();
		}
		else if(ob == selectDialog_menu_set)
		{
			this.setVisible(false);
			threadRun = false;
			active = false;		
			selectDialog.setVisible(false);
			messageDialog.setVisible(false);
			endDialog.setVisible(false);
			new SettingManager(sets.clone(), sets.getScreenSize(), CalcWindow.version_main, CalcWindow.version_sub_1, CalcWindow.version_sub_2, true, RunManager.getInstance(), false, arguments).open();
		}
		else if(ob == start)
		{
			if(selectDialog_authorityMode.isSelected())
			{
				if(! selectDialog_name[0].getText().equals("")) start();
			}
			else
			{
				start();
			}			
		}
		else if(ob == take)
		{
			take();
		}
		else if(ob == pay)
		{
			pay();
		}
		else if(ob == stop)
		{
			active = false;
			endDialog.setVisible(false);
			sevenDialog.setVisible(false);
			finishDialog.setVisible(false);
			pay.setEnabled(false);
			take.setEnabled(false);
			stop.setEnabled(false);
			selectDialog.setVisible(true);
		}
		else if(ob == finishDialog_close)
		{
			finishDialog.setVisible(false);
			pay.setEnabled(false);
			take.setEnabled(false);
			stop.setEnabled(false);
			selectDialog.setVisible(true);
		}
		else if(ob == endDialog_button)
		{
			endDialog.setVisible(false);
			paused = false;
		}
		else if(ob == finishDialog_copy)
		{
			try
			{
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finishDialog_codeField.getText()), null);
				alert(sets.getLang().getText(Language.DESC_COPY_CLIPBOARD));
			} 
			catch (Exception e1)
			{
				if(sets.isError_printDetail()) e1.printStackTrace();
				alert(sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == sevenDialog_buttons[0])
		{
			StandardCard takeCard = new StandardCard();
			takeCard.setNum(7);
			takeCard.setOp(sets.getOp_spade());
			boolean oneMore = false;
			boolean jump = false;
			boolean ai = false;
			// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
			center_deck.add(takeCard);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
			deck_refresh();			
			paused = false;
			seven_paused = false;
			pay.setEnabled(true);
			take.setEnabled(true);
			sevenDialog.setVisible(false);
			if (active)
				controlled((!oneMore), jump, ai);
		}
		else if(ob == sevenDialog_buttons[1])
		{
			StandardCard takeCard = new StandardCard();
			takeCard.setNum(7);
			takeCard.setOp(sets.getOp_clover());
			boolean oneMore = false;
			boolean jump = false;
			boolean ai = false;
			// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
			center_deck.add(takeCard);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
			deck_refresh();			
			paused = false;
			seven_paused = false;
			pay.setEnabled(true);
			take.setEnabled(true);
			sevenDialog.setVisible(false);
			if (active)
				controlled((!oneMore), jump, ai);
		}
		else if(ob == sevenDialog_buttons[2])
		{
			StandardCard takeCard = new StandardCard();
			takeCard.setNum(7);
			takeCard.setOp(sets.getOp_diamond());
			boolean oneMore = false;
			boolean jump = false;
			boolean ai = false;
			// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
			center_deck.add(takeCard);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
			deck_refresh();			
			paused = false;
			seven_paused = false;
			pay.setEnabled(true);
			take.setEnabled(true);
			sevenDialog.setVisible(false);
			if (active)
				controlled((!oneMore), jump, ai);
		}
		else if(ob == sevenDialog_buttons[3])
		{
			StandardCard takeCard = new StandardCard();
			takeCard.setNum(7);
			takeCard.setOp(sets.getOp_heart());
			boolean oneMore = false;
			boolean jump = false;
			boolean ai = false;
			// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
			center_deck.add(takeCard);
			message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
			deck_refresh();			
			paused = false;
			seven_paused = false;
			pay.setEnabled(true);
			take.setEnabled(true);
			sevenDialog.setVisible(false);
			if (active)
				controlled((!oneMore), jump, ai);
		}
		else if(ob == selectDialog_menu_anothers_city)
		{
			if(city == null)
				city = new CityManager(selectDialog, false, sets);
			city.open();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(e.getSource() == titlePanel) 
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
			messageDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + (this.getHeight())));
		}
		else if(e.getSource() == selectDialog_titlePanel)
		{
			selectDialog.setLocation(e.getXOnScreen() - sel_mousex, e.getYOnScreen() - sel_mousey);	
		}
		else if(e.getSource() == finishDialog_titlePanel)
		{
			finishDialog.setLocation(e.getXOnScreen() - fin_mousex, e.getYOnScreen() - fin_mousey);	
		}
		else if(e.getSource() == sevenDialog_upPanel)
		{			
			sevenDialog.setLocation(e.getXOnScreen() - sev_mousex, e.getYOnScreen() - sev_mousey);	
			//this.setLocation(e.getXOnScreen() - sev_mousex, e.getYOnScreen() - sev_mousey);
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
		else if(ob == selectDialog_titlePanel)
		{
			sel_mousex = e.getX();
			sel_mousey = e.getY();
		}
		else if(ob == finishDialog_titlePanel)
		{
			fin_mousex = e.getX();
			fin_mousey = e.getY();
		}
		else if(ob == sevenDialog_upPanel)
		{
			sev_mousex = e.getX();
			sev_mousey = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		
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
		if(ob == this)
		{
			exit();
		}
		
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

	public void message_bar()
	{
		if((! messageDialog.isVisible()) && this.isVisible()) messageDialog.setVisible(true);
		messages.append("-----------------------------------------------------------\n");
		messages.setCaretPosition(messages.getDocument().getLength() - 1);
		System.out.println("-----------------------------------------------------------");
	}
	public void message()
	{
		if((! messageDialog.isVisible()) && this.isVisible()) messageDialog.setVisible(true);
		messages.append("\n");
		messages.setCaretPosition(messages.getDocument().getLength() - 1);
		System.out.println();
	}
	public void message(String str)
	{
		if((! messageDialog.isVisible()) && this.isVisible()) messageDialog.setVisible(true);
		messages.append(str + "\n");
		messages.setCaretPosition(messages.getDocument().getLength() - 1);
		System.out.println(str);
	}
	public void alert(String str)
	{
		if(sets.isUseAlertWindow()) JOptionPane.showMessageDialog(this, str);
		messages.append(str + "\n");
		messages.setCaretPosition(messages.getDocument().getLength() - 1);
		System.out.println(str);
	}

	@Override
	public void run()
	{
		while(threadRun)
		{
			if(active)
			{
				if(runAINeed)
				{
					runAI();
					runAINeed = false;
				}
				if(! paused)
				{
					if(! blocks.get(getTurn()).ai)
					{
						time_limit_number2--;
						if(time_limit_number2 <= 0)
						{
							time_limit_number2 = time_limit_number3;
							time_limit_number1--;
							time_limit.setValue(time_limit_number1);
						}				
						if(time_limit_number1 <= 0)
						{
							take();
							time_limit_number1 = 100;
							time_limit.setValue(time_limit_number1);
						}
					}
				}	
				if(seven_paused)
				{
					seven_limit_number2--;
					if(seven_limit_number2 <= 0)
					{
						seven_limit_number2 = time_limit_number3;
						seven_limit_number1--;
						sevenDialog_progress.setValue(seven_limit_number1);
					}				
					if(seven_limit_number1 <= 0)
					{
						seven_limit_number1 = 100;
						sevenDialog_progress.setValue(seven_limit_number1);
						StandardCard takeCard = new StandardCard();
						takeCard.setNum(7);
						takeCard.setOp(sets.getOp_spade());
						boolean oneMore = false;
						boolean jump = false;
						boolean ai = false;
						// blocks.get(getTurn()).owns.remove(seven_saved_selected_index);
						center_deck.add(takeCard);
						message(blocks.get(getTurn()).getName() + sets.getLang().getText(Language.DESCRIPTIONS + 1) + sets.getLang().getText(Language.DIR_CENTER) + ", " + takeCard.toBasicString(sets.getCard_separator()));
						deck_refresh();
						paused = false;
						seven_paused = false;
						pay.setEnabled(true);
						take.setEnabled(true);
						sevenDialog.setVisible(false);
						if (active)
							controlled((!oneMore), jump, ai);												
					}
				}
			}
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				if(sets.isError_printDetail()) e.printStackTrace();
			}
		}		
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == selectDialog_menu_anothers_calc || ob == selectDialog_menu_anothers_conquer || ob == selectDialog_menu_anothers_math)
		{
			if(ob == selectDialog_menu_anothers_calc && selectDialog_menu_anothers_calc.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_conquer.setSelected(false);
						selectDialog_menu_anothers_calc.setSelected(true);
						selectDialog_menu_anothers_math.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(0));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(ob == selectDialog_menu_anothers_conquer && selectDialog_menu_anothers_conquer.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_conquer.setSelected(true);
						selectDialog_menu_anothers_calc.setSelected(false);
						selectDialog_menu_anothers_math.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(2));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(ob == selectDialog_menu_anothers_math && selectDialog_menu_anothers_math.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_conquer.setSelected(false);
						selectDialog_menu_anothers_calc.setSelected(false);
						selectDialog_menu_anothers_math.setSelected(true);
					}
				});
				
				sets.setNext_execute(new SaveInt(5));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(! (selectDialog_menu_anothers_calc.isSelected() || selectDialog_menu_anothers_conquer.isSelected() 
					|| selectDialog_menu_anothers_math.isSelected()))
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_conquer.setSelected(false);
						selectDialog_menu_anothers_calc.setSelected(false);
						selectDialog_menu_anothers_math.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(1));
				sets.setNext_execute_saved(new SaveBoolean(true));
			}
			save_setting();
		}
		else if(ob == selectDialog_authorityMode)
		{
			if(selectDialog_authorityMode.isSelected())
			{
				if(selectDialog_pns.length >= 4)
				{
					for(int i=0; i<selectDialog_pns.length; i++)
					{
						selectDialog_select_ai[i].setEnabled(false);
						selectDialog_select_none[i].setEnabled(false);
						selectDialog_select_player[i].setEnabled(false);
					}
					selectDialog_select_player[0].setSelected(true);
					selectDialog_select_ai[1].setSelected(true);
					selectDialog_select_ai[2].setSelected(true);
					selectDialog_select_ai[3].setSelected(true);
					//selectDialog_scriptUse.setSelected(false);
					//selectDialog_scriptUse.setEnabled(false);
				}
				else
				{
					selectDialog_authorityMode.setSelected(false);
				}
			}
			else
			{
				for(int i=0; i<selectDialog_pns.length; i++)
				{
					selectDialog_select_ai[i].setEnabled(true);
					selectDialog_select_none[i].setEnabled(true);
					selectDialog_select_player[i].setEnabled(true);
				}
				//selectDialog_scriptUse.setSelected(true);
				//selectDialog_scriptUse.setEnabled(true);
			}			
		}
	}
	public void save_setting()
	{
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
			FileOutputStream fout = new FileOutputStream(sets.getDefault_path() + "setting.clst");
			/*
			XMLEncoder encoder = new XMLEncoder(fout);
			set.wrapperToObjects();
			encoder.writeObject(set);
			encoder.close();
			*/
			
			ObjectOutputStream obout = new ObjectOutputStream(fout);
			sets.wrapperToObjects();
			obout.writeObject(sets);
			obout.close();
			
			fout.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void openConsole()
	{
		messageDialog.setVisible(true);
		
	}
	
}

class OneCardBlock extends JPanel implements Serializable, Cloneable
{
	private static final long serialVersionUID = -4822023096704025274L;
	public JPanel mainPanel;
	public JPanel[] pns;
	public Vector<Card> owns;
	public JLabel nameLabel;
	public JLabel cardLabel;
	public JTextField cardField;
	public boolean ai = false;
	public boolean turn = false;
	public String name = "";
	public Color selected_back, selected_fore, selected_inside_back, unselected_back, unselected_fore, unselected_inside_back;
	public Setting set;
	public OneCardBlock()
	{
		
	}
	public OneCardBlock(Setting set)
	{
		this.setLayout(new BorderLayout());
		this.set = set.clone();
		mainPanel = new JPanel();
		this.add(mainPanel);
		mainPanel.setBorder(new EtchedBorder());
		pns = new JPanel[1];
		mainPanel.setLayout(new GridLayout(pns.length, 1));
		for(int i=0; i<pns.length; i++)
		{
			pns[i] = new JPanel();
			pns[i].setLayout(new FlowLayout());
			mainPanel.add(pns[i]);
		}
		owns = new Vector<Card>();
		nameLabel = new JLabel();
		cardLabel = new JLabel(set.getLang().getText(Language.CARD) + " " + set.getLang().getText(Language.COUNT));
		cardField = new JTextField(5);
		pns[0].add(nameLabel);
		pns[0].add(cardLabel);
		pns[0].add(cardField);
		refresh();
		selected_back = set.getSelected_back();
		selected_fore = set.getSelected_fore();
		selected_inside_back = set.getSelected_inside_back();
		unselected_back = set.getUnselected_back();
		unselected_fore = set.getUnselected_fore();
		unselected_inside_back = set.getUnselected_inside_back();
	}
	public void refresh()
	{
		nameLabel.setText(name);
		cardField.setText(String.valueOf(owns.size()));
		if(turn)
		{
			mainPanel.setBackground(selected_back);
			for(int i=0; i<pns.length; i++)
			{
				pns[i].setBackground(selected_back);
			}
			nameLabel.setForeground(selected_fore);
			cardLabel.setForeground(selected_fore);
			cardField.setForeground(selected_fore);
			cardField.setBackground(selected_inside_back);
		}
		else
		{
			mainPanel.setBackground(unselected_back);
			for(int i=0; i<pns.length; i++)
			{
				pns[i].setBackground(unselected_back);
			}
			nameLabel.setForeground(unselected_fore);
			cardLabel.setForeground(unselected_fore);
			cardField.setForeground(unselected_fore);
			cardField.setBackground(unselected_inside_back);
		}
	}
	public OneCardBlock clone()
	{
		OneCardBlock newOne = new OneCardBlock(set);
		for(int i=0; i<owns.size(); i++)
		{
			newOne.owns.add(owns.get(i));
		}
		newOne.ai = ai;
		newOne.turn = turn;
		newOne.name = new String(name);
		return newOne;
	}
	public JPanel getMainPanel()
	{
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel)
	{
		this.mainPanel = mainPanel;
	}
	public JPanel[] getPns()
	{
		return pns;
	}
	public void setPns(JPanel[] pns)
	{
		this.pns = pns;
	}
	public Vector<Card> getOwns()
	{
		return owns;
	}
	public void setOwns(Vector<Card> owns)
	{
		this.owns = owns;
	}
	public JLabel getNameLabel()
	{
		return nameLabel;
	}
	public void setNameLabel(JLabel nameLabel)
	{
		this.nameLabel = nameLabel;
	}
	public JLabel getCardLabel()
	{
		return cardLabel;
	}
	public void setCardLabel(JLabel cardLabel)
	{
		this.cardLabel = cardLabel;
	}
	public JTextField getCardField()
	{
		return cardField;
	}
	public void setCardField(JTextField cardField)
	{
		this.cardField = cardField;
	}
	public boolean isAi()
	{
		return ai;
	}
	public void setAi(boolean ai)
	{
		this.ai = ai;
	}
	public boolean isTurn()
	{
		return turn;
	}
	public void setTurn(boolean turn)
	{
		this.turn = turn;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Color getSelected_back()
	{
		return selected_back;
	}
	public void setSelected_back(Color selected_back)
	{
		this.selected_back = selected_back;
	}
	public Color getSelected_fore()
	{
		return selected_fore;
	}
	public void setSelected_fore(Color selected_fore)
	{
		this.selected_fore = selected_fore;
	}
	public Color getSelected_inside_back()
	{
		return selected_inside_back;
	}
	public void setSelected_inside_back(Color selected_inside_back)
	{
		this.selected_inside_back = selected_inside_back;
	}
	public Color getUnselected_back()
	{
		return unselected_back;
	}
	public void setUnselected_back(Color unselected_back)
	{
		this.unselected_back = unselected_back;
	}
	public Color getUnselected_fore()
	{
		return unselected_fore;
	}
	public void setUnselected_fore(Color unselected_fore)
	{
		this.unselected_fore = unselected_fore;
	}
	public Color getUnselected_inside_back()
	{
		return unselected_inside_back;
	}
	public void setUnselected_inside_back(Color unselected_inside_back)
	{
		this.unselected_inside_back = unselected_inside_back;
	}
}
