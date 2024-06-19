package main_classes;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import ai_algorithm.VirtualBlock;
import lang.Language;
import scripting.ScriptManager;
import setting.Lint;
import setting.Setting;
import setting.SettingManager;
import tracking.TrackStorage;

public class ClassicCalc extends Frame implements ActionListener, WindowListener, Runnable, ItemListener, MouseListener, MainGame
{
	private static final long serialVersionUID = -1687141534939104257L;
	
	private boolean independence = false;
	private RunManager manager;
	private Setting setting;
	private Vector<ClassicBlock> blocks;
	private Vector<StandardCard> deck;
	private int turn = 0;
	public int time_limit_number1 = 100;
	public int time_limit_number2 = 20;
	public int time_limit_number3 = 20;
	public Language lang;
	private Panel mainPanel;
	private Panel centerPanel;
	private Panel upPanel;
	private Panel downPanel;
	private Panel deckPanel;
	private Label[] deckLabels;
	private Button take;
	private Panel contentPanel;
	public boolean active = false;
	private Panel[] contentPns;
	private Frame selectDialog;
	private Panel selectDialog_mainPanel;
	private Panel selectDialog_centerPanel;
	private Panel selectDialog_downPanel;
	private Button start;
	private Button exit;
	private Panel selectDialog_contentPanel;
	private Panel[] selectDialog_pns;
	private TextField[] selectDialog_names;
	private CheckboxGroup[] selectDialog_group;
	private Checkbox[][] selectDialog_radios;
	private Vector<VirtualBlock> virtual_blocks;
	private Vector<VirtualBlock> virtual_block2;
	private Vector<StandardCard> virtual_deck;
	private Dialog endDialog;
	private Panel endDialog_mainPanel;
	private Button endDialog_ok;
	private boolean game_pause = false;
	private TextField deckField;
	private Dialog finishDialog;
	private Panel finishDialog_mainPanel;
	private Panel finishDialog_upPanel;
	private Panel finishDialog_centerPanel;
	private Panel finishDialog_downPanel;
	private Label finishDialog_winLabel;
	private TextField finishDialog_whoField;
	private Panel finishDialog_controlPanel;
	private Button finishDialog_close;
	private Panel[] finishDialog_pns;
	private Label[] finishDialog_nameLabels;
	private TextField[] finishDialog_pointFields;
	private Thread thread;
	private boolean threadRun;
	private JProgressBar time_limit;
	private Panel progressPanel;
	private Panel textPanel;
	private TextArea textArea;
	private Panel selectDialog_upPanel;
	private Button set_setting;
	private Panel controlPanel;
	private Button stop;
	private Panel selectDialog_buttonPanel;
	private Panel selectDialog_controlPanel;
	private Checkbox check_authorized;
	private boolean authorized;
	private TextField finishDialog_code;
	private Panel finishDialog_codePanel;
	private Button code_check;
	private Code_Checker code_checker;
	private Dialog ver;
	private Button about;
	private Panel ver_mainPanel;
	private Panel ver_centerPanel;
	private Panel ver_downPanel;
	private Panel ver_controlPanel;
	private Panel ver_versionPanel;
	private Button ver_close;
	private Label ver_version;
	private Panel ver_titlePanel;
	private Panel ver_serialPanel;
	private Label ver_title;
	private Label ver_serial;
	private int grade_mode = 0;
	private String[] arguments;

	private MenuBar selectDialog_menubar;

	private Menu selectDialog_menu_file;

	private MenuItem selectDialog_menu_file_set;

	private MenuItem selectDialog_menu_file_exit;

	private Menu selectDialog_menu_view;

	private MenuItem selectDialog_menu_view_codeCheck;

	private MenuItem selectDialog_menu_view_about;

	private Panel textControlPanel;

	private TextField textConsole;

	private Button textConsoleAct;

	private ScriptManager scriptEngine = null;

	private Dialog serialDialog;

	private Panel serialDialog_mainPanel;

	private Panel serialDialog_downPanel;

	private Panel serialDialog_centerPanel;

	private Panel serialDialog_serialPanel;

	private Panel serialDialog_bottomPanel;

	private JEditorPane serialDialog_webPanel;

	private JScrollPane serialDialog_webScroll;

	private Button serialDialog_ok;

	private Button serialDialog_close;

	private TextField[] serialDialog_keys;

	private Label[] serialDialog_bars;

	private Checkbox selectDialog_scriptUse;

	private Dialog themeDialog;

	private Panel themeDialog_mainPanel;

	private Panel themeDialog_centerPanel;

	private Panel themeDialog_downPanel;

	private Panel[] themeDialog_pns;

	private MenuItem selectDialog_menu_file_theme;

	private Button themeDialog_close;

	private Checkbox themeDialog_classic;

	private Label themeDialog_warn;
	public ClassicCalc(boolean independence, RunManager manager, Setting sets, String[] args) throws HeadlessException, UnsupportedEncodingException
	{
		this.independence = independence;
		this.manager = manager;
		this.setting = sets.clone();
		this.lang = sets.getLang();
		blocks = new Vector<ClassicBlock>();
		deck = new Vector<StandardCard>();
		arguments = args;
		init();
		
	}
	public void init() throws HeadlessException, UnsupportedEncodingException
	{
		this.setSize(setting.getWidth(), setting.getHeight());
		this.setLocation((int)(setting.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - (this.getHeight() + 40)/2 - 10));
		this.addWindowListener(this);
		this.setLayout(new BorderLayout());
		mainPanel = new Panel();
		this.setTitle(lang.getText(Language.TITLE));
		this.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		upPanel = new Panel();
		centerPanel = new Panel();
		contentPanel = new Panel();
		downPanel = new Panel();
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout());
		upPanel.setLayout(new BorderLayout());
		deckPanel = new Panel();
		upPanel.add(deckPanel, BorderLayout.CENTER);
		deckPanel.setLayout(new FlowLayout());
		deckLabels = new Label[2];
		deckField = new TextField(4);
		deckField.setEditable(false);
		for(int i=0; i<deckLabels.length; i++)
		{
			deckLabels[i] = new Label();
			//deckPanel.add(deckLabels[i]);
		}
		deckPanel.add(deckLabels[0]);
		deckPanel.add(deckField);
		deckPanel.add(deckLabels[1]);
		take = new Button(lang.getText(Language.GET));
		take.addActionListener(this);
		deckPanel.add(take);
		
		downPanel.setLayout(new BorderLayout());
		progressPanel = new Panel();		
		textPanel = new Panel();
		time_limit = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		downPanel.add(progressPanel, BorderLayout.NORTH);
		downPanel.add(textPanel, BorderLayout.CENTER);
		progressPanel.setLayout(new BorderLayout());
		progressPanel.add(time_limit, BorderLayout.CENTER);
		controlPanel = new Panel();
		progressPanel.add(controlPanel, BorderLayout.EAST);
		controlPanel.setLayout(new BorderLayout());
		stop = new Button(lang.getText(Language.GAME_STOP));
		stop.addActionListener(this);
		controlPanel.add(stop);
		
		textArea = new TextArea("", 10, 10, TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea.setEditable(false);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textArea, BorderLayout.CENTER);
		textControlPanel = new Panel();
		textPanel.add(textControlPanel, BorderLayout.SOUTH);
		textControlPanel.setLayout(new BorderLayout());
		textConsole = new TextField();
		textConsoleAct = new Button(lang.getText(Language.RUN));
		textConsole.addActionListener(this);
		textConsoleAct.addActionListener(this);
		textControlPanel.add(textConsole, BorderLayout.CENTER);
		textControlPanel.add(textConsoleAct, BorderLayout.EAST);
		
		selectDialog = new Frame();
		selectDialog.setTitle(lang.getText(Language.TITLE));
		selectDialog.setSize(400, 300);
		selectDialog.setLocation((int)(setting.getScreenSize().getWidth()/2 - selectDialog.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - selectDialog.getHeight()));
		selectDialog.addWindowListener(this);
		selectDialog.setLayout(new BorderLayout());
		selectDialog_mainPanel = new Panel();
		selectDialog.add(selectDialog_mainPanel);
		selectDialog_mainPanel.setLayout(new BorderLayout());
		selectDialog_centerPanel = new Panel();
		selectDialog_downPanel = new Panel();
		selectDialog_upPanel = new Panel();
		selectDialog_mainPanel.add(selectDialog_centerPanel, BorderLayout.CENTER);
		selectDialog_mainPanel.add(selectDialog_downPanel, BorderLayout.SOUTH);
		selectDialog_mainPanel.add(selectDialog_upPanel, BorderLayout.NORTH);
		selectDialog_downPanel.setLayout(new BorderLayout());
		start = new Button(lang.getText(Language.START));
		exit = new Button(lang.getText(Language.EXIT));
		start.addActionListener(this);
		exit.addActionListener(this);
		selectDialog_buttonPanel = new Panel();
		selectDialog_controlPanel = new Panel();
		selectDialog_downPanel.add(selectDialog_buttonPanel, BorderLayout.CENTER);
		selectDialog_downPanel.add(selectDialog_controlPanel, BorderLayout.NORTH);
		selectDialog_buttonPanel.setLayout(new FlowLayout());
		selectDialog_controlPanel.setLayout(new FlowLayout());
		selectDialog_buttonPanel.add(start);
		selectDialog_buttonPanel.add(exit);
		check_authorized = new Checkbox(lang.getText(Language.AUTHORITY));
		check_authorized.addItemListener(this);
		selectDialog_scriptUse = new Checkbox(lang.getText(Language.INPUT) + " " + lang.getText(Language.SCRIPT) + " " + lang.getText(Language.USE));
		selectDialog_scriptUse.setState(true);
		selectDialog_controlPanel.add(check_authorized);
		selectDialog_controlPanel.add(selectDialog_scriptUse);
		selectDialog_centerPanel.setLayout(new BorderLayout());
		selectDialog_contentPanel = new Panel();
		selectDialog_refresh();
		selectDialog_radios[0][1].setState(true);
		for(int i=1; i<selectDialog_group.length; i++)
		{
			selectDialog_radios[i][2].setState(true);
		}
		
		selectDialog_upPanel.setLayout(new FlowLayout());
		set_setting = new Button(lang.getText(Language.SETTING));
		set_setting.addActionListener(this);
		//selectDialog_upPanel.add(set_setting);
		code_check = new Button(lang.getText(Language.CODE_CHECKER));
		code_check.addActionListener(this);
		//selectDialog_upPanel.add(code_check);
		about = new Button(lang.getText(Language.ABOUT));
		about.addActionListener(this);
		//selectDialog_upPanel.add(about);
		selectDialog_menubar = new MenuBar();
		selectDialog_menu_file = new Menu(lang.getText(Language.MENU_FILE));
		selectDialog_menubar.add(selectDialog_menu_file);
		selectDialog_menu_file_theme = new MenuItem(lang.getText(Language.THEME));
		selectDialog_menu_file_set = new MenuItem(lang.getText(Language.SETTING));		
		selectDialog_menu_file_exit = new MenuItem(lang.getText(Language.EXIT));
		selectDialog_menu_file_theme.addActionListener(this);
		selectDialog_menu_file_set.addActionListener(this);
		selectDialog_menu_file_exit.addActionListener(this);
		selectDialog_menu_file.add(selectDialog_menu_file_set);		
		selectDialog_menu_file.add(selectDialog_menu_file_exit);
		selectDialog_menu_view = new Menu(lang.getText(Language.VIEW));
		selectDialog_menubar.add(selectDialog_menu_view);
		selectDialog_menu_view_codeCheck = new MenuItem(lang.getText(Language.CODE_CHECKER));
		selectDialog_menu_view_about = new MenuItem(lang.getText(Language.ABOUT));
		selectDialog_menu_view_codeCheck.addActionListener(this);
		selectDialog_menu_view_about.addActionListener(this);
		selectDialog_menu_view.add(selectDialog_menu_view_codeCheck);
		selectDialog_menu_view.add(selectDialog_menu_file_theme);
		selectDialog_menu_view.add(selectDialog_menu_view_about);
		selectDialog.setMenuBar(selectDialog_menubar);
		
		endDialog = new Dialog(this, false);
		endDialog.addWindowListener(this);
		endDialog.setLayout(new BorderLayout());
		endDialog_mainPanel = new Panel();
		endDialog.add(endDialog_mainPanel);
		endDialog_mainPanel.setLayout(new BorderLayout());
		endDialog_ok = new Button(lang.getText(Language.FINISH));
		endDialog_ok.addActionListener(this);
		endDialog_mainPanel.add(endDialog_ok);
		
		finishDialog = new Dialog(this, true);
		finishDialog.addWindowListener(this);
		finishDialog.setSize(400, 300);
		finishDialog.setLocation((int)(setting.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - finishDialog.getHeight()));
		finishDialog.setLayout(new BorderLayout());
		finishDialog_mainPanel = new Panel();
		finishDialog.add(finishDialog_mainPanel);
		finishDialog_mainPanel.setLayout(new BorderLayout());
		finishDialog_upPanel = new Panel();
		finishDialog_centerPanel = new Panel();
		finishDialog_downPanel = new Panel();
		finishDialog_mainPanel.add(finishDialog_centerPanel, BorderLayout.CENTER);
		finishDialog_mainPanel.add(finishDialog_upPanel, BorderLayout.NORTH);
		finishDialog_mainPanel.add(finishDialog_downPanel, BorderLayout.SOUTH);
		finishDialog_upPanel.setLayout(new FlowLayout());
		finishDialog_winLabel = new Label(lang.getText(Language.RESULT) + " : ");
		finishDialog_whoField = new TextField(10);
		finishDialog_whoField.setEditable(false);
		finishDialog_upPanel.add(finishDialog_winLabel);
		finishDialog_upPanel.add(finishDialog_whoField);
		finishDialog_downPanel.setLayout(new BorderLayout());
		finishDialog_controlPanel = new Panel();
		finishDialog_downPanel.add(finishDialog_controlPanel, BorderLayout.CENTER);
		finishDialog_codePanel = new Panel();
		finishDialog_downPanel.add(finishDialog_codePanel, BorderLayout.SOUTH);
		finishDialog_controlPanel.setLayout(new FlowLayout());
		finishDialog_close = new Button(lang.getText(Language.CLOSE));
		finishDialog_close.addActionListener(this);
		finishDialog_controlPanel.add(finishDialog_close);
		finishDialog_code = new TextField(30);
		finishDialog_code.setEditable(false);
		finishDialog_codePanel.setLayout(new FlowLayout());
		finishDialog_codePanel.add(finishDialog_code);
		
		ver = new Dialog(selectDialog);
		ver.setSize(300, 200);
		ver.setLocation((int)(setting.getScreenSize().getWidth()/2 - ver.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - ver.getHeight()));
		ver.setTitle(lang.getText(Language.TITLE));
		ver.addWindowListener(this);
		ver.setLayout(new BorderLayout());
		ver_mainPanel = new Panel();
		ver.add(ver_mainPanel);
		ver_centerPanel = new Panel();
		ver_downPanel = new Panel();
		ver_mainPanel.setLayout(new BorderLayout());
		ver_mainPanel.add(ver_centerPanel, BorderLayout.CENTER);
		ver_mainPanel.add(ver_downPanel, BorderLayout.SOUTH);
		ver_centerPanel.setLayout(new BorderLayout());
		ver_downPanel.setLayout(new BorderLayout());
		ver_controlPanel = new Panel();
		ver_versionPanel = new Panel();
		ver_downPanel.add(ver_controlPanel, BorderLayout.CENTER);
		ver_downPanel.add(ver_versionPanel, BorderLayout.NORTH);
		ver_controlPanel.setLayout(new FlowLayout());
		ver_versionPanel.setLayout(new FlowLayout());
		ver_close = new Button(lang.getText(Language.CLOSE));
		ver_close.addActionListener(this);
		ver_controlPanel.add(ver_close);
		ver_version = new Label(lang.getText(Language.MAKER) + " v" + CalcWindow.version_main + "." + CalcWindow.version_sub_1 + "" + CalcWindow.version_sub_2);
		ver_versionPanel.add(ver_version);
		ver_centerPanel.setLayout(new BorderLayout());
		ver_titlePanel = new Panel();
		ver_serialPanel = new Panel();
		ver_centerPanel.add(ver_titlePanel, BorderLayout.CENTER);
		ver_centerPanel.add(ver_serialPanel, BorderLayout.SOUTH);
		ver_titlePanel.setLayout(new FlowLayout());
		ver_title = new Label(lang.getText(Language.TITLE));
		ver_title.setFont(new Font(null, Font.BOLD, 55));
		ver_titlePanel.setBackground(Color.DARK_GRAY);
		ver_title.setBackground(Color.DARK_GRAY);
		ver_title.setForeground(Color.LIGHT_GRAY);
		ver_titlePanel.add(ver_title);
		ver_serial = new Label(lang.getText(Language.BASIC_EDITION));
		ver_serialPanel.setLayout(new FlowLayout());
		ver_serialPanel.add(ver_serial);
		ver_serialPanel.setBackground(Color.DARK_GRAY);
		ver_serial.setBackground(Color.DARK_GRAY);
		ver_serial.setForeground(Color.WHITE);
		ver_serial.addMouseListener(this);
		
		serialDialog = new Dialog(ver);
		serialDialog.addWindowListener(this);
		serialDialog.setSize(450, 225);
		serialDialog.setLocation((int)(setting.getScreenSize().getWidth()/2 - serialDialog.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - serialDialog.getHeight()/2));
		serialDialog.setLayout(new BorderLayout());
		serialDialog_mainPanel = new Panel();
		serialDialog.add(serialDialog_mainPanel, BorderLayout.CENTER);
		serialDialog_mainPanel.setLayout(new BorderLayout());
		serialDialog_downPanel = new Panel();
		serialDialog_centerPanel = new Panel();
		serialDialog_mainPanel.add(serialDialog_downPanel, BorderLayout.SOUTH);
		serialDialog_mainPanel.add(serialDialog_centerPanel, BorderLayout.CENTER);
		serialDialog_downPanel.setLayout(new BorderLayout());
		serialDialog_serialPanel = new Panel();
		serialDialog_bottomPanel = new Panel();
		serialDialog_downPanel.add(serialDialog_serialPanel, BorderLayout.CENTER);
		serialDialog_downPanel.add(serialDialog_bottomPanel, BorderLayout.SOUTH);
		serialDialog_serialPanel.setLayout(new FlowLayout());
		serialDialog_bottomPanel.setLayout(new FlowLayout());
		serialDialog_centerPanel.setLayout(new BorderLayout());
		serialDialog_webPanel = new JEditorPane();
		serialDialog_webPanel.setEditable(false);
		serialDialog_webScroll = new JScrollPane(serialDialog_webPanel);
		try
		{
			serialDialog_webPanel.setPage(setting.getNotice_url() + "/free_serial.html");
		}
		catch(Exception e)
		{
			serialDialog_webPanel.setText(lang.getText(Language.INPUT_SERIAL_AGREEMENT));
		}
		serialDialog_centerPanel.add(serialDialog_webScroll, BorderLayout.CENTER);
		serialDialog_ok = new Button(lang.getText(Language.OK));
		serialDialog_close = new Button(lang.getText(Language.CLOSE));
		serialDialog_ok.addActionListener(this);
		serialDialog_close.addActionListener(this);
		serialDialog_bottomPanel.add(serialDialog_ok);
		serialDialog_bottomPanel.add(serialDialog_close);
		
		serialDialog_keys = new TextField[5];
		serialDialog_bars = new Label[4];
		for(int i=0; i<serialDialog_keys.length; i++)
		{
			serialDialog_keys[i] = new TextField(5);
			serialDialog_serialPanel.add(serialDialog_keys[i]);
			if(i < serialDialog_keys.length - 1)
			{
				serialDialog_bars[i] = new Label("-");
				serialDialog_serialPanel.add(serialDialog_bars[i]);
			}
		}
		
		themeDialog = new Dialog(selectDialog, true);
		themeDialog.setSize(300, 200);
		themeDialog.setLocation((int)(setting.getScreenSize().getWidth()/2 - themeDialog.getWidth()/2), (int)(setting.getScreenSize().getHeight()/2 - themeDialog.getHeight()/2));
		themeDialog.setLayout(new BorderLayout());
		themeDialog_mainPanel = new Panel();
		themeDialog.add(themeDialog_mainPanel, BorderLayout.CENTER);
		themeDialog_mainPanel.setLayout(new BorderLayout());
		themeDialog_centerPanel = new Panel();
		themeDialog_downPanel = new Panel();
		themeDialog_mainPanel.add(themeDialog_centerPanel, BorderLayout.CENTER);
		themeDialog_mainPanel.add(themeDialog_downPanel, BorderLayout.SOUTH);
		themeDialog_pns = new Panel[2];
		themeDialog_centerPanel.setLayout(new GridLayout(themeDialog_pns.length, 1));
		for(int i=0; i<themeDialog_pns.length; i++)
		{
			themeDialog_pns[i] = new Panel();
			themeDialog_pns[i].setLayout(new FlowLayout());
			themeDialog_centerPanel.add(themeDialog_pns[i]);
		}
		themeDialog_downPanel.setLayout(new FlowLayout());
		themeDialog_close = new Button(lang.getText(Language.CLOSE));
		themeDialog_close.addActionListener(this);
		themeDialog_downPanel.add(themeDialog_close);
		themeDialog_classic = new Checkbox(lang.getText(Language.CLASSIC));
		themeDialog_classic.addItemListener(this);
		themeDialog_classic.setState(true);
		themeDialog_pns[0].add(themeDialog_classic);
		themeDialog_warn = new Label(lang.getText(Language.NEED_RESTART));
		themeDialog_pns[themeDialog_pns.length - 1].add(themeDialog_warn);
		
		deck_refresh();
		center_refresh();
		
		thread = new Thread(this);
		threadRun = true;
		thread.start();
	}
	public void deck_refresh()
	{
		deckLabels[0].setText(lang.getText(Language.DECK_LABEL1));
		deckField.setText(String.valueOf(deck.size()));
		deckLabels[1].setText(lang.getText(Language.DECK_LABEL4));
	}
	public void selectDialog_refresh()
	{
		selectDialog_centerPanel.removeAll();
		selectDialog_contentPanel = new Panel();
		selectDialog_pns = new Panel[setting.getSlots()];
		selectDialog_names = new TextField[setting.getSlots()];
		selectDialog_group = new CheckboxGroup[setting.getSlots()];
		selectDialog_radios = new Checkbox[setting.getSlots()][3];
		selectDialog_contentPanel.setLayout(new GridLayout(setting.getSlots(), 1));
		for(int i=0; i<setting.getSlots(); i++)
		{
			selectDialog_pns[i] = new Panel();
			selectDialog_names[i] = new TextField(10);
			selectDialog_names[i].setText(lang.getText(Language.PLAYER) + " " + String.valueOf((i + 1)));
			selectDialog_pns[i].setLayout(new FlowLayout());
			selectDialog_pns[i].add(selectDialog_names[i]);
			selectDialog_group[i] = new CheckboxGroup();
			for(int j=0; j<3; j++)
			{
				selectDialog_radios[i][j] = new Checkbox("", false, selectDialog_group[i]);
				selectDialog_pns[i].add(selectDialog_radios[i][j]);
			}
			selectDialog_radios[i][0].setLabel(lang.getText(Language.NONE));
			selectDialog_radios[i][1].setLabel(lang.getText(Language.PLAYER));
			selectDialog_radios[i][2].setLabel(lang.getText(Language.AI));
			selectDialog_contentPanel.add(selectDialog_pns[i]);
		}
		
		selectDialog_centerPanel.add(selectDialog_contentPanel);		
	}
	public void center_refresh()
	{
		centerPanel.removeAll();		
		contentPanel = new Panel();
		int h_blocks = setting.getSlots();
		contentPns = new Panel[((int) Math.ceil((double)h_blocks / 2)) * 2];
		contentPanel.setLayout(new GridLayout(2, contentPns.length / 2));
		for(int i=0; i<contentPns.length; i++)
		{
			if(i < blocks.size())
			{
				contentPns[i] = blocks.get(i);
			}
			else
			{
				contentPns[i] = new Panel();
			}
			contentPanel.add(contentPns[i]);
		}
		centerPanel.add(contentPanel);		
	}
	public void start()
	{
		authorized = check_authorized.getState();
		int block_count = setting.getSlots();
		if(authorized) 
			block_count = 4;
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).pay.removeActionListener(this);
		}
		blocks.clear();
		if(authorized)
		{
			ClassicBlock[] newBlock = new ClassicBlock[block_count];
			newBlock[0] = new ClassicBlock(setting);
			newBlock[1] = new ClassicBlock(setting);
			newBlock[2] = new ClassicBlock(setting);
			newBlock[3] = new ClassicBlock(setting);
			//System.out.println(selectDialog_names[0]);
			newBlock[0].inside.setName(selectDialog_names[0].getText());
			newBlock[1].inside.setName(lang.getText(Language.AI) + " 1");
			newBlock[2].inside.setName(lang.getText(Language.AI) + " 2");
			newBlock[3].inside.setName(lang.getText(Language.AI) + " 3");
			newBlock[0].inside.setAi(false);
			newBlock[0].ai = false;
			newBlock[1].inside.setAi(true);
			newBlock[1].ai = true;
			newBlock[2].inside.setAi(true);
			newBlock[2].ai = true;
			newBlock[3].inside.setAi(true);
			newBlock[3].ai = true;
			for(int i=0; i<block_count; i++)
			{
				blocks.add(newBlock[i]);
			}
		}
		else
		{
			for(int i=0; i<block_count; i++)
			{
				ClassicBlock newBlock = new ClassicBlock(setting.clone());
				newBlock.inside.setName(selectDialog_names[i].getText());
				if(selectDialog_radios[i][0].getState())
				{
					
				}
				else if(selectDialog_radios[i][1].getState())
				{
					blocks.add(newBlock);
				}
				else if(selectDialog_radios[i][2].getState())
				{
					newBlock.ai = true;
					newBlock.inside.setAi(true);				
					blocks.add(newBlock);
				}				
			}
		}
		turn = (int) (Math.random() * blocks.size());
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).pay.addActionListener(this);
		}
		deck.clear();
		StandardCard newCard = null;
		char newOp;
		int newNum = 0;
		for(int i=0; i<3; i++)
		{
			for(int j=-1; j<10; j++)
			{
				switch(i)
				{
					case 0:
						newOp = setting.getOp_plus();
						break;
					case 1:
						newOp = setting.getOp_minus();
						break;
					case 2:
						newOp = setting.getOp_multiply();
						break;	
					default:
						newOp = setting.getOp_plus();
						break;
				}
				newNum = j;
				newCard = new StandardCard(newOp, newNum);
				for(int k=0; k<4; k++)
				{
					deck.add(newCard);
				}
				
			}
		}
		for(int i=0; i<setting.getChange_card_count(); i++)
		{
			deck.add(new StandardCard(setting.getOp_change(), 1));
		}
		Vector<StandardCard> newDeck = new Vector<StandardCard>();
		int random_deck = 0;
		while(deck.size() >= 1)
		{
			random_deck = (int) (Math.random() * deck.size());
			newDeck.add((StandardCard) deck.get(random_deck).clone());
			deck.remove(random_deck);
		}
		deck = newDeck;
		for(int i=0; i<setting.getStart_givenCards(); i++)
		{
			for(int j=0; j<blocks.size(); j++)
			{				
				blocks.get(j).inside.getOwns().add(deck.get(0));
				deck.remove(0);
			}
		}
		
		selectDialog.setVisible(false);
		this.setVisible(false);
		center_refresh();
		this.setVisible(true);		
		//System.out.println("turn : " + turn + ", block : " + blocks.size());
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).turnFinish();
		}
		blocks.get(turn).setTurn();
		deck_refresh();
		message(lang.getText(Language.DESCRIPTIONS + 11));
		active = true;
		time_limit_number1 = 100;
		time_limit_number2 = time_limit_number3;
		time_limit.setValue(time_limit_number1);
		if(blocks.get(turn).ai)
		{
			runAI();
		}
	}
	public void finish()
	{
		active = false;
		int min_cards = blocks.get(0).inside.getOwns().size();
		int bonus_getter = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).inside.getOwns().size() < min_cards)
			{
				min_cards = blocks.get(i).inside.getOwns().size();
				bonus_getter = i;
			}
		}
		message(lang.getText(Language.BONUS_TARGET) + " : " + blocks.get(bonus_getter).inside.getName());
		for(int i=0; i<blocks.size(); i++)
		{
			if(i == bonus_getter) blocks.get(i).inside.calculate(false, 5);
			else blocks.get(i).inside.calculate(false);
		}		
		
		BigInteger max_point = blocks.get(0).inside.getPoint();
		int winner = 0;
		
		for(int i=0; i<blocks.size(); i++)
		{
			//if(blocks.get(i).inside.getPoint() > max_point)
			if(blocks.get(i).inside.getPoint().compareTo(max_point) > 0)
			{
				max_point = blocks.get(i).inside.getPoint();
				winner = i;
			}
		}
		
		finishDialog_centerPanel.removeAll();
		finishDialog_centerPanel.setLayout(new GridLayout(blocks.size(), 1));
		finishDialog_pns = new Panel[blocks.size()];
		finishDialog_nameLabels = new Label[blocks.size()];
		finishDialog_pointFields = new TextField[blocks.size()];
		for(int i=0; i<blocks.size(); i++)
		{
			finishDialog_pns[i] = new Panel();
			finishDialog_pns[i].setLayout(new FlowLayout());
			finishDialog_nameLabels[i] = new Label(blocks.get(i).inside.getName());
			finishDialog_pointFields[i] = new TextField(10);
			finishDialog_pointFields[i].setEditable(false);
			finishDialog_pointFields[i].setText(String.valueOf(blocks.get(i).inside.getPoint()));
			finishDialog_pns[i].add(finishDialog_nameLabels[i]);
			finishDialog_pns[i].add(finishDialog_pointFields[i]);
			finishDialog_centerPanel.add(finishDialog_pns[i]);
		}
		finishDialog_whoField.setText(blocks.get(winner).inside.getName());
		finishDialog.setTitle(lang.getText(Language.RESULT) + " : " + blocks.get(winner).inside.getName() + " " + lang.getText(Language.WON));
		
		if((authorized && (! blocks.get(0).ai)) && 0 == winner)
		{
			int version_main = CalcWindow.version_main;
			int version_sub_1 = CalcWindow.version_sub_1;
			int version_sub_2 = CalcWindow.version_sub_2;
			StringBuffer auth_code = new StringBuffer("");
			String[] auth_codes = new String[17];
			auth_codes[0] = String.valueOf(blocks.get(winner).inside.getPoint());
			auth_codes[1] = new String(blocks.get(0).inside.getName());
			auth_codes[2] = String.valueOf(blocks.size());
			auth_codes[3] = String.valueOf(blocks.get(0).inside.getOwns().size());
			auth_codes[4] = String.valueOf(version_main);
			auth_codes[5] = String.valueOf(version_sub_1);
			auth_codes[6] = String.valueOf(version_sub_2);
			BigInteger secret_code = Lint.newBigInteger(0);
			secret_code = secret_code.add(Lint.big((version_main * 100) + (version_sub_1 * 10) + version_sub_2));
			secret_code = secret_code.multiply(Lint.big(blocks.get(0).inside.getOwns().size()));
			secret_code = secret_code.add(blocks.get(winner).inside.getPoint());
			// authority_code used
			long authority_code = CalcWindow.getAuthorizedCode(2938291);
			secret_code = secret_code.add(Lint.big(authority_code + (blocks.size() * ((version_main * 100) + (version_sub_1 * 10) + version_sub_2))));
			BigInteger secret_nameCode = new BigInteger("0");
									
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
			auth_codes[14] = "calc_classic";
			auth_codes[15] = "Calc";
			
			secret_nameCode = Lint.copy(secret_code);
			secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(blocks.get(0).getName()) / 100.0) + 5)));
			secret_code = secret_code.add(Lint.newBigInteger((blocks.get(0).inside.getOwns().size() + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
			secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
			
			auth_codes[7] = String.valueOf(secret_code);
			auth_codes[16] = String.valueOf(secret_nameCode.toString());	
			
			for(int i=0; i<auth_codes.length; i++)
			{
				auth_code.append(auth_codes[i]);
				if(i < auth_codes.length - 1) auth_code.append("||");
			}			
			finishDialog_code.setVisible(true);
			finishDialog_code.setText(auth_code.toString());
		}
		else if(authorized && (! blocks.get(0).ai)) finishDialog_code.setText(lang.getText(Language.DESCRIPTIONS + 21));
		else finishDialog_code.setText(lang.getText(Language.DESCRIPTIONS + 18));
		
		finishDialog.setVisible(true);
		//selectDialog.setVisible(true);
	}
	public void runAI()
	{
		BigInteger calc_values = new BigInteger(String.valueOf(Long.MIN_VALUE));
		BigInteger max_value = new BigInteger(String.valueOf(Long.MIN_VALUE));
		int promising_target = 0;
		int promising_card_index = 0;
		boolean take_or_pay = false; // false : take, true : pay
		if(virtual_blocks == null) virtual_blocks = new Vector<VirtualBlock>();
		if(virtual_block2 == null) virtual_block2 = new Vector<VirtualBlock>();
		if(virtual_deck == null) virtual_deck = new Vector<StandardCard>();
		virtual_block2.clear();
		for(int i=0; i<blocks.size(); i++)
		{
			virtual_block2.add(blocks.get(i).inside.clone());
		}
		virtual_reset();
		
		max_value = calc_virtual_value();
		
		// Pay
		StandardCard pay_card, last_card;
		char pay_op, last_op;
		int pay_num, last_num;
		boolean pay_available = false;
		for(int i=0; i<virtual_block2.size(); i++)
		{			
			for(int j=0; j<virtual_block2.get(turn).getOwns().size(); j++)
			{
				virtual_reset();
				pay_card = virtual_blocks.get(turn).getOwns().get(j);
				//System.out.println(pay_card);
				pay_op = pay_card.getOp();
				pay_num = pay_card.getNum();
				if(virtual_blocks.get(i).getPaids().size() >= 1)
				{
					last_card = virtual_blocks.get(i).getPaids().lastElement();
					last_op = last_card.getOp();
					last_num = last_card.getNum();
					
					if(pay_num == 1)
					{
						if(last_num == 7)
						{
							if(turn == i)
							{
								pay_available = true;
							}
							else
							{
								pay_available = false;
							}
						}
						else
						{
							pay_available = true;
						}
					}
					else if(pay_num == last_num || pay_op == last_op)
					{
						if(last_num == 7)
						{
							if(turn == i)
							{
								pay_available = true;
							}
							else
							{
								pay_available = false;
							}
						}
						else
						{
							pay_available = true;
						}
					}
					else pay_available = false;
				}
				else
				{
					pay_available = true;
				}		
				
				
				
				if(pay_available)
				{
					virtual_blocks.get(turn).getOwns().remove(j);
					virtual_blocks.get(i).getPaids().add(pay_card);
					calc_values = calc_virtual_value();					
					
					virtual_blocks.get(0).calculate(false);
					int winner = 0;
					BigInteger max_now_points = virtual_blocks.get(0).getPoint();
					BigInteger calc_now_points = virtual_blocks.get(0).getPoint();
					for(int k=0; k<virtual_blocks.size(); k++)
					{
						virtual_blocks.get(i).calculate(false);
						calc_now_points = virtual_blocks.get(i).getPoint();
						//if(calc_now_points > max_now_points)
						if(calc_now_points.compareTo(max_now_points) > 0)
						{
							winner = i;
							max_now_points = calc_now_points;
						}
					}
					if(virtual_blocks.get(turn).getOwns().size() <= 0)
					{
						if(winner == turn)
						{
							//if(calc_values >= 0) calc_values = calc_values * 4;
							//else  calc_values = calc_values / 4;
							if(calc_values.compareTo(new BigInteger("0")) >= 0) calc_values = calc_values.multiply(new BigInteger("4"));
							else calc_values = calc_values.divide(new BigInteger("4"));
						}
						else
						{
							//if(calc_values >= 0) calc_values = calc_values / 4;
							//else  calc_values = calc_values * 4;
							if(calc_values.compareTo(new BigInteger("0")) >= 0) calc_values = calc_values.divide(new BigInteger("4"));
							else calc_values = calc_values.multiply(new BigInteger("4"));
						}
					}
					
					//if(calc_values > max_value)
					if(calc_values.compareTo(max_value) > 0)
					{
						take_or_pay = true;
						promising_target = i;
						promising_card_index = j;
					}
				}				
			}
		}
		if(take_or_pay) // pay
		{
			StandardCard pays = blocks.get(turn).inside.getOwns().get(promising_card_index);
			blocks.get(turn).inside.getOwns().remove(promising_card_index);
			blocks.get(promising_target).inside.getPaids().add(pays);
			message(blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(promising_target).inside.getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + pays);
			blocks.get(turn).refresh();
			blocks.get(promising_target).refresh();
			if(pays.getOp() == setting.getOp_change())
			{
				Vector<StandardCard> temps = blocks.get(turn).inside.getOwns();
				blocks.get(turn).inside.setOwns(blocks.get(promising_target).inside.getOwns());
				blocks.get(promising_target).inside.setOwns(temps);
				message(lang.getText(Language.DESCRIPTIONS + 26) + blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 27) + blocks.get(promising_target).inside.getName() + lang.getText(Language.DESCRIPTIONS + 28));
			}
		}
		else // take
		{
			StandardCard takes = deck.get(0);
			deck.remove(0);
			blocks.get(turn).inside.getOwns().add(takes);
			blocks.get(turn).refresh();
			message(blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 0));
		}
		
		controlled();
	}
	private BigInteger calc_virtual_value()
	{
		BigInteger result = new BigInteger("0");
			
		for(int i=0; i<virtual_blocks.size(); i++)
		{
			virtual_blocks.get(i).calculate(false);	
			//if(i == turn) result += virtual_blocks.get(i).getPoint();
			//else  result -= virtual_blocks.get(i).getPoint();
			if(i == turn) result = result.add(virtual_blocks.get(i).getPoint());
			else result = result.subtract(virtual_blocks.get(i).getPoint());
		}
		
		return result;
	}
	private void virtual_reset()
	{
		virtual_blocks.clear();
		for(int i=0; i<virtual_block2.size(); i++)
		{
			virtual_blocks.add(virtual_block2.get(i).clone());
		}
		virtual_deck.clear();
		for(int i=0; i<deck.size(); i++)
		{
			virtual_deck.add((StandardCard) deck.get(i).clone());
		}
	}
	
	public void takeCard()
	{
		StandardCard target_card = deck.get(0);
		deck.remove(0);
		blocks.get(turn).inside.getOwns().add(target_card);
		blocks.get(turn).refresh();
		
		message(blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 0));
		controlled();
	}
	public void payCard(int target)
	{
		boolean pay_available = false;
		boolean change_card = false;
		
		StandardCard target_card = null, last_card = null;
		int select_index = blocks.get(turn).ownList.getSelectedIndex();
		char pay_op, last_op;
		int pay_num, last_num;
		if(select_index >= 0)
		{
			target_card = blocks.get(turn).inside.getOwns().get(select_index);
			pay_op = target_card.getOp();
			pay_num = target_card.getNum();
			if(blocks.get(target).inside.getPaids().size() >= 1)
			{
				last_card = blocks.get(target).inside.getPaids().lastElement();
				last_op = last_card.getOp();
				last_num = last_card.getNum();
				if(last_op == setting.getOp_change())
				{
					change_card = true;
				}
				if(pay_num == 1 && last_num != 7)
				{
					pay_available = true;
				}
				else if(pay_num == 1 && last_num == 7)
				{
					if(turn == target)
					{
						pay_available = true;
					}
					else
					{
						alert(lang.getText(Language.DESCRIPTIONS + 6));
						pay_available = false;
					}
				}
				else if(pay_op == last_op || pay_num == last_num)
				{
					if(last_num == 7)
					{
						if(turn == target)
						{
							pay_available = true;
						}
						else
						{
							alert(lang.getText(Language.DESCRIPTIONS + 6));
							pay_available = false;
						}
					}
					else
					{
						pay_available = true;
					}
					
				}
				else
				{
					alert(last_card + lang.getText(Language.DESCRIPTIONS + 7));
				}
			}
			else // No cards in pay slot
			{
				pay_available = true;
			}
		}
		else
		{
			alert(last_card + lang.getText(Language.DESCRIPTIONS + 7));
		}		
		
		if(pay_available)
		{			
			blocks.get(turn).inside.getOwns().remove(select_index);
			blocks.get(target).inside.getPaids().add(target_card);
			message(blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(target).inside.getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + target_card.toBasicString(setting.getCard_separator()));
			if(change_card)
			{
				Vector<StandardCard> temps = blocks.get(turn).inside.getOwns();
				blocks.get(turn).inside.setOwns(blocks.get(target).inside.getOwns());
				blocks.get(target).inside.setOwns(temps);
				message(lang.getText(Language.DESCRIPTIONS + 26) + blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 27) + blocks.get(target).inside.getName() + lang.getText(Language.DESCRIPTIONS + 28));
			}			
			blocks.get(turn).refresh();
			blocks.get(target).refresh();			
			controlled();
		}
	}
	public void controlled()
	{		
		deck_refresh();
		boolean need_finish = false;
		if(deck.size() <= 0)
		{
			need_finish = true;
		}		
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).inside.getOwns().size() <= 0)
			{
				need_finish = true;
				break;
			}
		}
		if(need_finish) finish();
		else
		{			
			time_limit_number1 = 100;
			time_limit_number2 = time_limit_number3;
			time_limit.setValue(time_limit_number1);
			if(! blocks.get(turn).ai)
			{
				endDialog.setSize(this.getWidth(), this.getHeight() - deckPanel.getHeight());
				endDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + deckPanel.getHeight()));
				game_pause = true;
				endDialog.setVisible(true);
			}			
			turn++;
			if(turn >= blocks.size())
			{
				turn = 0;
			}			
			for(int i=0; i<blocks.size(); i++)
			{
				blocks.get(i).turnFinish();			
			}
			blocks.get(turn).setTurn();
			if(blocks.get(turn).ai) runAI();
		}		
	}
	public void act_console(String orders) throws Exception
	{
		if(orders == null || orders.equals(""))
			return;
		
		String beforeData = "";
		String define_int = "", define_string = "";
		if(scriptEngine.getEngine().equalsIgnoreCase("JavaScript"))
		{
			define_int = "";
			define_string = "";
		}
		beforeData = beforeData + define_int + " deck_size = " + deck.size() + ";\n";
		beforeData = beforeData + define_int + " turn = " + turn + ";\n";
		beforeData = beforeData + define_int + " players" + " = " + blocks.size() + ";\n";
		
		beforeData = beforeData + define_string + " player_myself" + " = " + "\"" + blocks.get(turn).getName() + "\"" + ";\n";
		beforeData = beforeData + define_int + " player_myself" + "_owns" + " = " + blocks.get(turn).inside.getOwns().size() + ";\n";
		beforeData = beforeData + define_int + " point_myself" + " = " + blocks.get(turn).inside.getOwns().size() + ";\n";
		for(int a=0; a<blocks.size(); a++)
		{
			beforeData = beforeData + define_string + " player_" + String.valueOf(a) + " = " + "\"" + blocks.get(a).getName() + "\"" + ";\n";
			beforeData = beforeData + define_int + " player_" + String.valueOf(a) + "_owns" + " = " + blocks.get(a).inside.getOwns().size() + ";\n";
			beforeData = beforeData + define_int + " point_" + String.valueOf(a) + " = " + blocks.get(a).inside.getOwns().size() + ";\n";
		}
		
		
		if(blocks.get(turn).inside.getPaids().size() >= 1) 
		{
			beforeData = beforeData + define_string + " last_myturn" + " = " + "\"" + blocks.get(turn).inside.getPaids().lastElement().toBasicString() + "\"" + ";\n";
			beforeData = beforeData + define_string + " last_myturn" + "_op" + " = " + "\"" + String.valueOf(blocks.get(turn).inside.getPaids().lastElement().getOp()) + "\"" + ";\n";
			beforeData = beforeData + define_int + " last_myturn" + "_num" + " = " + String.valueOf(blocks.get(turn).inside.getPaids().lastElement().getNum()) + ";\n";
		}
		else
		{
			beforeData = beforeData + define_string + " last_myturn" + " = " + "\"empty\"" + ";\n";
			beforeData = beforeData + define_string + " last_myturn" + "_op" + " = " + "\"empty\"" + ";\n";
			beforeData = beforeData + define_int + " last_myturn" + "_num" + " = " + "0" + ";\n";
		}
		for(int b=0; b<blocks.size(); b++)
		{
			if(blocks.get(b).inside.getPaids().size() >= 1) 
			{
				beforeData = beforeData + define_string + " last_" + String.valueOf(b) + " = " + "\"" + blocks.get(b).inside.getPaids().lastElement().toBasicString() + "\"" + ";\n";
				beforeData = beforeData + define_string + " last_" + String.valueOf(b) + "_op" + " = " + "\"" + String.valueOf(blocks.get(b).inside.getPaids().lastElement().getOp()) + "\"" + ";\n";
				beforeData = beforeData + define_int + " last_" + String.valueOf(b) + "_num" + " = " + String.valueOf(blocks.get(b).inside.getPaids().lastElement().getNum()) + ";\n";
			}
			else
			{
				beforeData = beforeData + define_string + " last_" + String.valueOf(b) + " = " + "\"empty\"" + ";\n";
				beforeData = beforeData + define_string + " last_" + String.valueOf(b) + "_op" + " = " + "\"empty\"" + ";\n";
				beforeData = beforeData + define_int + " last_" + String.valueOf(b) + "_num" + " = " + "0" + ";\n";
			}
		}
		
		if((! authorized) && (setting.getKey().accepted(setting)) && selectDialog_scriptUse.getState())
			scriptEngine.actOnly(orders);
	}
	@Override
	public void open()
	{
		this.setVisible(true);
		boolean accept_net = false;
		boolean net_mastered = false;
		try
		{
			accept_net = setting.accept_net();
			net_mastered = setting.accept_mastered();
		} 
		catch (Exception e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
			accept_net = false;
		}
		if(net_mastered)
		{
			ver_serial.setText(lang.getText(Language.MASTER));				
			if(CalcWindow.usingFont != null)
				ver_serial.setFont(CalcWindow.usingFont.deriveFont(Font.BOLD, ver_serial.getFont().getSize()));
			else
				ver_serial.setFont(new Font(CalcWindow.usingFontName, Font.BOLD, ver_serial.getFont().getSize()));
			int r, g, b;
			Color base_color = Color.CYAN;
			r = (int) ((double) base_color.getRed() - (((double) base_color.getRed()) * 0.25));
			g = (int) ((double) base_color.getGreen() - (((double) base_color.getGreen()) * 0.25));
			b = (int) ((double) base_color.getBlue() - (((double) base_color.getBlue()) * 0.25));
			if(r < 0) r = 0;
			if(g < 0) g = 0;
			if(b < 0) b = 0;
			ver_serial.setForeground(new Color(r, g, b));
			grade_mode = 3;
		}
		else if(accept_net)
		{
			ver_serial.setText(lang.getText(Language.ULTIMATE));
			ver_serial.setFont(new Font(null, Font.BOLD, ver_serial.getFont().getSize()));
			ver_serial.setForeground(Color.YELLOW);
			grade_mode = 2;
		}
		else if(setting.accepted())
		{
			ver_serial.setText(lang.getText(Language.PROFESSIONAL));
			ver_serial.setForeground(Color.RED);
			grade_mode = 1;
		}
		else
		{
			ver_serial.setText(lang.getText(Language.BASIC_EDITION));
			ver_serial.setForeground(Color.WHITE);
			grade_mode = 0;
		}
		if(accept_net || setting.accepted()) check_authorized.setEnabled(true);
		else  check_authorized.setEnabled(false);
		selectDialog.setVisible(true);
		scriptEngine = new ScriptManager(setting, this, this, setting.getScript_engine());
	}

	@Override
	public void exit()
	{
		if(independence) System.exit(0);
		else this.setVisible(false);
		
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
		else if(ob == selectDialog)
		{
			exit();
		}	
		else if(ob == endDialog)
		{
			game_pause = false;
			endDialog.setVisible(false);
		}
		else if(ob == finishDialog)
		{
			finishDialog.setVisible(false);
			selectDialog.setVisible(true);
		}
		else if(ob == ver)
		{
			ver.setVisible(false);
		}
		else if(ob == serialDialog)
		{
			serialDialog.setVisible(false);
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
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == exit || ob == selectDialog_menu_file_exit)
		{
			exit();
		}
		else if(ob == start)
		{
			start();
		}
		else if(ob == stop)
		{
			active = false;
			selectDialog.setVisible(true);
		}
		else if(ob == take)
		{
			if(active) takeCard();
		}
		else if(ob == about || ob == selectDialog_menu_view_about)
		{
			ver.setVisible(true);
		}
		else if(ob == ver_close)
		{
			ver.setVisible(false);
		}
		else if(ob == selectDialog_menu_file_theme)
		{
			themeDialog.setVisible(true);
		}
		else if(ob == themeDialog_close)
		{
			if(themeDialog_classic.getState())
			{
				
			}
			else
			{
				setting.setClassic_mode(false);
				save_setting(setting);
			}
			themeDialog.setVisible(false);
		}
		else if(ob == endDialog_ok)
		{
			game_pause = false;
			endDialog.setVisible(false);
		}
		else if(ob == finishDialog_close)
		{
			finishDialog.setVisible(false);
			selectDialog.setVisible(true);
		}
		else if(ob == set_setting || ob == selectDialog_menu_file_set)
		{
			selectDialog.setVisible(false);
			this.setVisible(false);	
			new SettingManager(setting.clone(), setting.getScreenSize(), CalcWindow.version_main, CalcWindow.version_sub_1, CalcWindow.version_sub_2, true, manager, false, arguments).open();
					
		}
		else if(ob == code_check || ob == selectDialog_menu_view_codeCheck)
		{
			if(code_checker == null) code_checker = new Code_Checker(false, selectDialog, setting);
			code_checker.open();
		}
		else if(ob == textConsole || ob == textConsoleAct)
		{
			try
			{
				act_console(textConsole.getText());
			} 
			catch (Exception e1)
			{
				
			}
			textConsole.setText("");
		}
		else if(ob == serialDialog_ok)
		{
			String[] getKeys = new String[serialDialog_keys.length];
			for(int i=0; i<getKeys.length; i++)
			{
				getKeys[i] = serialDialog_keys[i].getText().toUpperCase(Locale.ENGLISH);
			}
			if(setting.input(getKeys))
			{
				boolean net_accepted = false;
				boolean abandoned = false;
				boolean net_mastered = false;
				try
				{
					net_accepted = setting.accept_net();
					abandoned = setting.abandoned_key();
					net_mastered = setting.accept_mastered();
				} 
				catch (Exception e1)
				{
					if(setting.isError_printDetail()) e1.printStackTrace();
					TrackStorage.addTrack(this.getClass().getName(), e1);
					net_accepted = false;
					abandoned = false;
				}
				if(net_mastered && (! abandoned))
				{
					ver_serial.setText(lang.getText(Language.MASTER));				
					if(CalcWindow.usingFont != null)
						ver_serial.setFont(CalcWindow.usingFont.deriveFont(Font.BOLD, ver_serial.getFont().getSize()));
					else
						ver_serial.setFont(new Font(CalcWindow.usingFontName, Font.BOLD, ver_serial.getFont().getSize()));
					int r, g, b;
					Color base_color = Color.CYAN;
					r = (int) ((double) base_color.getRed() - (((double) base_color.getRed()) * 0.25));
					g = (int) ((double) base_color.getGreen() - (((double) base_color.getGreen()) * 0.25));
					b = (int) ((double) base_color.getBlue() - (((double) base_color.getBlue()) * 0.25));
					if(r < 0) r = 0;
					if(g < 0) g = 0;
					if(b < 0) b = 0;
					ver_serial.setForeground(new Color(r, g, b));
					grade_mode = 3;
				}
				else if(net_accepted && (! abandoned))
				{
					ver_serial.setText(lang.getText(Language.ULTIMATE));
					ver_serial.setFont(new Font(null, Font.BOLD, ver_serial.getFont().getSize()));
					ver_serial.setForeground(Color.YELLOW);
					grade_mode = 2;
					check_authorized.setEnabled(true);
				}
				else if(setting.accepted() && (! abandoned))
				{
					ver_serial.setText(lang.getText(Language.PROFESSIONAL));
					ver_serial.setFont(new Font(null, Font.PLAIN, ver_serial.getFont().getSize()));
					ver_serial.setForeground(Color.RED);
					grade_mode = 1;
					check_authorized.setEnabled(true);
				}
				else
				{
					ver_serial.setText(lang.getText(Language.BASIC_EDITION));
					ver_serial.setFont(new Font(null, Font.PLAIN, ver_serial.getFont().getSize()));
					ver_serial.setForeground(Color.WHITE);
					grade_mode = 0;
					check_authorized.setEnabled(false);
				}
				save_setting(null);
				serialDialog.setVisible(false);	
			}
			else
			{
				JOptionPane.showMessageDialog(serialDialog, lang.getText(Language.DESCRIPTIONS + 20));
			}
		}
		else if(ob == serialDialog_close)
		{
			serialDialog.setVisible(false);
		}
		else
		{
			int target = 0;
			boolean accept = false;
			for(int i=0; i<blocks.size(); i++)
			{
				if(ob == blocks.get(i).pay)
				{
					target = i;
					accept = true;
					break;
				}
			}
			if(accept)
			{
				if(active) payCard(target);
			}
		}
	}
	public void save_setting(Setting sets)
	{
		boolean default_xmlMode = false;
		try
		{
			File file = new File(setting.getDefault_path() + "setting.xml");
			if(file.exists()) default_xmlMode = true;
			else default_xmlMode = false;
		} 
		catch (Exception e)
		{
			default_xmlMode = false;
		}
		
		save_setting(sets, default_xmlMode);
	}
	public void save_setting(Setting sets, boolean xml)
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_setting() started", false);
		if(sets == null)
		{
			sets = setting;
		}
		try
		{
			File file = new File(setting.getDefault_path());
			sets.setVer_main(CalcWindow.version_main);
			sets.setVer_sub1(CalcWindow.version_sub_1);
			sets.setVer_sub2(CalcWindow.version_sub_2);
			if(! file.exists())
			{
				file.mkdir();
			}
			FileOutputStream fout = null;
			if(xml)
			{
				fout = new FileOutputStream(setting.getDefault_path() + "setting.xml");
				XMLEncoder encoder = new XMLEncoder(fout);
				sets.wrapperToObjects();
				encoder.writeObject(sets);
				encoder.close();
			}		
			else
			{
				fout = new FileOutputStream(setting.getDefault_path() + "setting.clst");
				ObjectOutputStream obout = new ObjectOutputStream(fout);
				obout.writeObject(sets);
				obout.close();
			}
			
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
	public void run()
	{
		while(threadRun)
		{
			try
			{
				if(active)
				{
					stop.setEnabled(true);
					take.setEnabled(true);
					try
					{
						for(int i=0; i<blocks.size(); i++)
						{
							blocks.get(i).pay.setEnabled(true);
						}
					} 
					catch (Exception e)
					{
						
					}
					
					
					if((! blocks.get(turn).ai) && (! game_pause))
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
							takeCard();
							time_limit_number1 = 100;
							time_limit.setValue(time_limit_number1);
						}
					}
				}			
				else
				{
					stop.setEnabled(false);
					take.setEnabled(false);
					try
					{
						for(int i=0; i<blocks.size(); i++)
						{
							blocks.get(i).pay.setEnabled(false);
						}
					} 
					catch (Exception e)
					{
						
					}
				}
				
				Thread.sleep(20);
			}
			catch(Exception e)
			{
				
			}			
		}		
	}
	@Override
	public void message()
	{
		textArea.append("\n");
		System.out.println();
	}
	@Override
	public void message_bar()
	{
		textArea.append("--------------------------------------------------\n");
		System.out.println("--------------------------------------------------");
	}
	@Override
	public void message(String str)
	{
		textArea.append(str + "\n");
		System.out.println(str);
	}
	@Override
	public void alert(String str)
	{
		message(str);
		JOptionPane.showMessageDialog(this, str);
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == check_authorized)
		{
			if(check_authorized.getState())
			{
				for(int i=0; i<selectDialog_pns.length; i++)
				{
					for(int j=0; j<3; j++)
					{
						selectDialog_radios[i][j].setEnabled(false);						
					}
					selectDialog_radios[i][0].setState(true);
				}
				selectDialog_radios[0][1].setState(true);
				selectDialog_radios[1][2].setState(true);
				selectDialog_radios[2][2].setState(true);
				selectDialog_radios[3][2].setState(true);
			}
			else
			{
				for(int i=0; i<selectDialog_pns.length; i++)
				{
					for(int j=0; j<3; j++)
					{
						selectDialog_radios[i][j].setEnabled(true);
					}
				}
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == ver_serial)
		{
			serialDialog.setVisible(true);
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	public int getGrade_mode()
	{
		return grade_mode;
	}
	public void setGrade_mode(int grade_mode)
	{
		this.grade_mode = grade_mode;
	}
	@Override
	public void stop_game()
	{
		active = false;
		selectDialog.setVisible(true);
		
	}
	@Override
	public void start_game()
	{
		start();
		
	}
	@Override
	public void payCard(int blockNumber, int pay_card_index)
	{
		boolean pay_available = false;
		boolean change_card = false;
		
		StandardCard target_card = null, last_card = null;
		int select_index = pay_card_index;
		char pay_op, last_op;
		int pay_num, last_num;
		if(select_index >= 0)
		{
			target_card = blocks.get(turn).inside.getOwns().get(select_index);
			pay_op = target_card.getOp();
			pay_num = target_card.getNum();
			if(blocks.get(blockNumber).inside.getPaids().size() >= 1)
			{
				last_card = blocks.get(blockNumber).inside.getPaids().lastElement();
				last_op = last_card.getOp();
				last_num = last_card.getNum();
				if(last_op == setting.getOp_change())
				{
					change_card = true;
				}
				if(pay_num == 1 && last_num != 7)
				{
					pay_available = true;
				}
				else if(pay_num == 1 && last_num == 7)
				{
					if(turn == blockNumber)
					{
						pay_available = true;
					}
					else
					{
						alert(lang.getText(Language.DESCRIPTIONS + 6));
						pay_available = false;
					}
				}
				else if(pay_op == last_op || pay_num == last_num)
				{
					if(last_num == 7)
					{
						if(turn == blockNumber)
						{
							pay_available = true;
						}
						else
						{
							alert(lang.getText(Language.DESCRIPTIONS + 6));
							pay_available = false;
						}
					}
					else
					{
						pay_available = true;
					}
					
				}
				else
				{
					alert(last_card + lang.getText(Language.DESCRIPTIONS + 7));
				}
			}
			else // No cards in pay slot
			{
				pay_available = true;
			}
		}
		else
		{
			alert(last_card + lang.getText(Language.DESCRIPTIONS + 7));
		}		
		
		if(pay_available)
		{			
			blocks.get(turn).inside.getOwns().remove(select_index);
			blocks.get(blockNumber).inside.getPaids().add(target_card);
			message(blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(blockNumber).inside.getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + target_card.toBasicString(setting.getCard_separator()));
			if(change_card)
			{
				Vector<StandardCard> temps = blocks.get(turn).inside.getOwns();
				blocks.get(turn).inside.setOwns(blocks.get(blockNumber).inside.getOwns());
				blocks.get(blockNumber).inside.setOwns(temps);
				message(lang.getText(Language.DESCRIPTIONS + 26) + blocks.get(turn).inside.getName() + lang.getText(Language.DESCRIPTIONS + 27) + blocks.get(blockNumber).inside.getName() + lang.getText(Language.DESCRIPTIONS + 28));
			}			
			blocks.get(turn).refresh();
			blocks.get(blockNumber).refresh();			
			controlled();
		}
		
	}
	@Override
	public void selectAuthorizeCheckbox()
	{
		if(selectDialog.isVisible()) check_authorized.setState(! check_authorized.getState());
		
	}
	@Override
	public void selectPlayerSetting(int index, int value)
	{		
		if(selectDialog.isVisible())
		{
			selectDialog_radios[index][value].setState(true);
		}
		
	}
	@Override
	public ScriptManager getScriptEngine()
	{
		if(scriptEngine == null) scriptEngine = new ScriptManager(setting, this, this, setting.getScript_engine());	
		return scriptEngine;
	}
	@Override
	public Setting getSetting()
	{
		return setting.clone();
	}
	@Override
	public void openConsole()
	{
		
		
	}
}
class ClassicBlock extends Panel implements Serializable
{
	private static final long serialVersionUID = -9212112619553936377L;
	public boolean ai = false;
	public boolean turn = false;
	private Panel upPanel;
	private Panel centerPanel;
	private Panel downPanel;
	public VirtualBlock inside;
	public List ownList;
	private List paidList;
	private Label nameLabel;
	private TextField nameField;
	private Panel leftPanel;
	private Panel rightPanel;
	private TextField lastCardField;
	private Panel lastCardPanel;
	public Checkbox thisTurn;
	private Label pointLabel;
	private TextField pointField;
	private Label cardLabel;
	private TextField cardField;
	public Button pay;
	public Setting set;
	private Panel[] upPns;
	private Panel cardFieldPanel;
	private Panel pointFieldPanel;

	public ClassicBlock()
	{
		
	}
	public ClassicBlock(Setting set)
	{
		this.setLayout(new BorderLayout());
		this.set = set.clone();
		upPanel = new Panel();
		centerPanel = new Panel();
		downPanel = new Panel();
		inside = new VirtualBlock(set);
		this.add(upPanel, BorderLayout.NORTH);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
		ownList = new List();
		paidList = new List();
		ownList.setMultipleMode(false);
		paidList.setEnabled(false);
		centerPanel.setLayout(new GridLayout(1, 2));
		leftPanel = new Panel();
		rightPanel = new Panel();
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		leftPanel.add(ownList, BorderLayout.CENTER);
		rightPanel.add(paidList, BorderLayout.CENTER);
		lastCardPanel = new Panel();
		lastCardField = new TextField();
		lastCardField.setEditable(false);
		rightPanel.add(lastCardPanel, BorderLayout.SOUTH);
		lastCardPanel.setLayout(new BorderLayout());
		lastCardPanel.add(lastCardField, BorderLayout.NORTH);
		pay = new Button(set.getLang().getText(Language.PAY));
		lastCardPanel.add(pay, BorderLayout.CENTER);
		upPns = new Panel[2];
		//upPanel.setLayout(new GridLayout(upPns.length, 1));
		upPanel.setLayout(new BorderLayout());
		for(int i=0; i<upPns.length; i++)
		{
			upPns[i] = new Panel();
			upPns[i].setLayout(new FlowLayout());
			//upPanel.add(upPns[i]);
		}
		upPanel.add(upPns[0], BorderLayout.CENTER);
		upPanel.add(upPns[1], BorderLayout.SOUTH);
		nameLabel = new Label();
		nameField = new TextField(7);
		thisTurn = new Checkbox();
		thisTurn.setEnabled(false);
		nameField.setEditable(false);
		upPns[0].add(nameLabel);
		upPns[0].add(nameField);
		upPns[0].add(thisTurn);
		downPanel.setLayout(new FlowLayout());
		pointLabel = new Label(set.getLang().getText(Language.POINT));
		pointField = new TextField(10);
		cardLabel = new Label(set.getLang().getText(Language.CARD) + " " + set.getLang().getText(Language.COUNT));
		cardField = new TextField(3);
		//downPanel.add(pointLabel);
		//downPanel.add(pointField);
		//downPanel.add(cardLabel);
		//downPanel.add(cardField);		
		cardFieldPanel = new Panel();
		pointFieldPanel = new Panel();
		cardFieldPanel.setLayout(new BorderLayout());
		pointFieldPanel.setLayout(new BorderLayout());
		cardFieldPanel.add(cardLabel, BorderLayout.WEST);
		cardFieldPanel.add(cardField, BorderLayout.CENTER);
		pointFieldPanel.add(pointLabel, BorderLayout.WEST);
		pointFieldPanel.add(pointField, BorderLayout.CENTER);	
		upPns[1].setLayout(new BorderLayout());
		upPns[1].add(cardFieldPanel, BorderLayout.WEST);
		upPns[1].add(pointFieldPanel, BorderLayout.CENTER);
		inside.setAi(this.ai);
		theme_refresh();
	}
	public void theme_refresh()
	{
		if(turn)
		{
			this.setBackground(Color.GRAY);
			upPanel.setBackground(Color.GRAY);
			downPanel.setBackground(Color.GRAY);
			centerPanel.setBackground(Color.GRAY);
			leftPanel.setBackground(Color.GRAY);
			rightPanel.setBackground(Color.GRAY);
			cardFieldPanel.setBackground(Color.GRAY);
			pointFieldPanel.setBackground(Color.GRAY);
			thisTurn.setBackground(Color.GRAY);
			nameLabel.setBackground(Color.GRAY);
			cardLabel.setBackground(Color.GRAY);
			pointLabel.setBackground(Color.GRAY);
			for(int i=0; i<upPns.length; i++)
			{
				upPns[i].setBackground(Color.GRAY);
			}
		}
		else
		{
			this.setBackground(Color.LIGHT_GRAY);
			upPanel.setBackground(Color.LIGHT_GRAY);
			downPanel.setBackground(Color.LIGHT_GRAY);
			centerPanel.setBackground(Color.LIGHT_GRAY);
			leftPanel.setBackground(Color.LIGHT_GRAY);
			rightPanel.setBackground(Color.LIGHT_GRAY);
			cardFieldPanel.setBackground(Color.LIGHT_GRAY);
			pointFieldPanel.setBackground(Color.LIGHT_GRAY);
			thisTurn.setBackground(Color.LIGHT_GRAY);
			nameLabel.setBackground(Color.LIGHT_GRAY);
			cardLabel.setBackground(Color.LIGHT_GRAY);
			pointLabel.setBackground(Color.LIGHT_GRAY);
			for(int i=0; i<upPns.length; i++)
			{
				upPns[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	public void refresh()
	{
		String[] ownArray = new String[inside.getOwns().size()];
		String[] paidArray = new String[inside.getPaids().size()];
		ownList.removeAll();
		paidList.removeAll();
		StringTokenizer stoken;
		for(int i=0; i<ownArray.length; i++)
		{			
			ownArray[i] = inside.getOwns().get(i).toBasicString();
			stoken = new StringTokenizer(ownArray[i], "|");
			if(turn) ownList.add(stoken.nextToken().toCharArray()[0] + set.getCard_separator() + stoken.nextToken());
		}
		for(int i=0; i<paidArray.length; i++)
		{
			paidArray[i] = inside.getPaids().get(i).toBasicString();
			stoken = new StringTokenizer(paidArray[i], "|");
			paidList.add(stoken.nextToken().toCharArray()[0] + set.getCard_separator() + stoken.nextToken());
		}
		if(inside.getPaids().size() >= 1)
		{
			stoken = new StringTokenizer(inside.getPaids().lastElement().toBasicString(), "|");
			lastCardField.setText(stoken.nextToken().toCharArray()[0] + set.getCard_separator() + stoken.nextToken());
		}
		inside.calculate(false);
		nameField.setText(inside.getName());
		pointField.setText(String.valueOf(inside.getPoint()));
		cardField.setText(String.valueOf(inside.getOwns().size()));
		thisTurn.setState(turn);
		theme_refresh();
	}
	public void setTurn()
	{
		turn = true;
		ownList.setEnabled(true);
		refresh();
	}
	public void turnFinish()
	{
		turn = false;
		ownList.setEnabled(false);
		refresh();
	}
	public ClassicBlock clone()
	{
		ClassicBlock newOne = new ClassicBlock(set.clone());
		newOne.inside = this.inside.clone();
		return newOne;
	}
}
