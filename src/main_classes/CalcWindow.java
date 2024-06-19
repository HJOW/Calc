package main_classes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
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
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FontUIResource;

import first_run.SettingForRun;
import lang.English;
import lang.Korean;
import lang.Language;
import network.AuthorizedScenarioPackage;
import network.ChatManager;
import network.Downloader;
import network.NetplayMessage;
import network.NetworkManager;
import network.OnlineContentURLList;
import network.ScenarioPackage;
import ranking.Rank;
import ranking.RankViewer;
import reflex.Reflexioner;
import replay.Control;
import replay.ControlCollector;
import replay.Replay;
import replay.ReplayPlayer;
import scripting.BlockModule;
import scripting.Cheatable;
import scripting.ScriptManager;
import scripting.Script_Chat;
import scripting.Script_Load;
import scripting.SpecialCardModule;
import setting.AuthorizedScenario;
import setting.DetailedScenario;
import setting.Difficulty;
import setting.ExtendedScenario;
import setting.Key;
import setting.LankableScenario;
import setting.Lint;
import setting.MessageOnTurn;
import setting.SaveBoolean;
import setting.SaveInt;
import setting.Scenario;
import setting.ScenarioEditor;
import setting.Setting;
import setting.SettingManager;
import setting.StringWithInt;
import setting.ThemeSet;
import sound.Player;
import tracking.TrackStorage;
import accumulate.CityManager;
import ai_algorithm.AIActedDialog;
import ai_algorithm.AI_Algorithm;
import ai_algorithm.AI_Algorithm_Result;
import ai_algorithm.AI_Script_Data;
import ai_algorithm.VirtualBlock;

public class CalcWindow extends JFrame implements ActionListener, ItemListener, Runnable, MouseListener, MouseMotionListener, WindowListener, ChangeListener, MainGame, ListSelectionListener, Cheatable
{
	private static final long serialVersionUID = 658800441111434264L;
	public JFrame mainFrame;
	public static final long version_nightly = 25;
	public static final int version_main = 0;
	public static final int version_sub_1 = 9;
	public static final int version_sub_2 = 0;
	public static final char version_test = ' '; // Complete : ' ', Test : 'a' 'b' 'c'
	public int start_givenCards = 10;
	public int ai_start_givenCards = 10;
	public boolean authority_mode = false;
	public int scenario_mode = 0; // 0 : Normal
	public Language lang;
	public int basic_width = 600, basic_height = 400;
	public int slots = 4;
	public int time_limit_number1 = 100;
	public int time_limit_number2 = 20;
	public int time_limit_number3 = 20;
	public boolean printDescriptDetail = true;
	public boolean ai_enabled = true;
	public Dimension screenSize;
	public static String usingFontName = null;
	public static Font usingFont = null;
	public static Font usingFontB = null;
	public static Font usingFont2 = null;
	public static Font usingFont2B = null;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JScrollPane centerScroll;
	public Color unselected_back, selected_back, unselected_inside_back, selected_inside_back, unselected_fore, selected_fore, selected_button, unselected_button;
	private boolean color_reverse = false;
	private Vector<Block> blocks;
	private Vector<JButton> listener_added, centerSecondButtons;
	public boolean threadRun = true;
	public boolean active = false;
	public boolean deck_changed = false;
	private JLabel deckLabel1;
	private JLabel deckLabel2;
	private JButton takeButton;
	private Vector<StandardCard> deck;
	private JDialog selectDialog;
	private JLabel title;
	private JPanel selectDialog_mainPanel;
	private JPanel selectDialog_titlePanel;
	private JPanel selectDialog_centerPanel;
	private JPanel selectDialog_downPanel;
	private JPanel[] selectDialog_pns;
	private ButtonGroup[] selectDialog_select;
	private JRadioButton[] selectDialog_select_none;
	private JRadioButton[] selectDialog_select_player;
	private JRadioButton[] selectDialog_select_ai;
	private JButton start;
	private JButton exit;
	private Thread thread;
	private JDialog finishDialog;
	private JPanel finishDialog_mainPanel;
	private JPanel finishDialog_centerPanel;
	private JPanel finishDialog_downPanel;
	private JScrollPane selectDialog_centerScroll;
	private JScrollPane finishDialog_centerScroll;
	private JPanel[] finishDialog_pns;
	private JLabel[] finishDialog_labels;
	private JTextField[] finishDialog_fields;
	private JButton finishDialog_close;
	private int turn;
	private JPanel centerBasePanel;
	private JTextField[] selectDialog_name;
	private boolean printThreadMessageOnce = false;
	private JPanel finishDialog_upPanel;
	private JLabel finishDialog_label;
	private String finishLabelText;
	private JPanel basePanel;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel titleBar;
	private JButton title_exit;
	private JPanel title_buttonPanel;
	private JDialog endDialog;
	private JPanel endDialog_mainPanel;
	private JButton endDialog_end;
	private JTextArea messages;
	private JScrollPane messages_scroll;
	private JPanel messagePanel;
	public Setting setting;
	private int mousex;
	private int mousey;
	private int selectDialog_mousex;
	private int selectDialog_mousey;
	private int finishDialog_mousex;
	private int finishDialog_mousey;
	private Vector<VirtualBlock> virtual_blocks;
	private Vector<VirtualBlock> virtual_blocks2;
	private Vector<StandardCard> virtual_deck;
	private JDialog messageDialog;
	private JButton stop_game;
	private JPanel title_iconPanel;
	private JButton settingButton;
	private JButton help;
	private Help helpDialog;
	private JButton help2;
	public JProgressBar time_limit;
	private JPanel controlPanel;
	private JPanel barPanel;
	private JPanel selectDialog_bottomPanel;
	private JPanel selectDialog_controlPanel;
	private JCheckBox selectDialog_authorityMode;
	public ControlCollector control_collector;
	public Replay replay;
	private JButton finishDialog_replay;
	private FileFilter ff1;
	private JFileChooser cfd;
	private AI_Algorithm algorithm;
	private boolean algorithm_ready = false;
	private AI_Algorithm_Result algorithm_result;
	private JPanel selectDialog_upPanel;
	private JMenuBar selectDialog_menuBar;
	private JMenu selectDialog_menu_file;
	private JMenuItem selectDialog_menu_exit;
	private JMenu selectDialog_menu_view;
	private JMenu selectDialog_menu_help;
	private JMenuItem selectDialog_menu_help_view;
	private JMenuItem selectDialog_menu_view_replay;
	private JMenuItem selectDialog_menu_set;
	private boolean save_replay = false;
	private JCheckBox selectDialog_replayMode;
	private JDialog ver;
	private JButton ver_close;
	private JMenuItem selectDialog_menu_help_ver;
	private boolean deck_empty;
	private JTabbedPane selectDialog_tab;
	private JPanel selectDialog_multiPanel;
	private JPanel selectDialog_multi_controlPanel;
	private JPanel selectDialog_multi_playerPanel;
	private JScrollPane selectDialog_multi_playerScroll;
	private JButton selectDialog_multi_host;
	private JButton selectDialog_multi_join;
	private JTextField selectDialog_multi_ip;
	private JTextField selectDialog_multi_port;
	private JLabel selectDialog_multi_ip_label;
	private JLabel selectDialog_multi_port_label;
	private JPanel[] selectDialog_multi_controlPns;
	private JLabel selectDialog_multi_nowip_label;
	public String ip_address;
	public boolean multiplay = false;
	private JButton selectDialog_multi_disconnect;
	private NetworkManager networkManager;
	private JPanel[] selectDialog_multi_playerPns;
	private JLabel[] selectDialog_multi_playerIp;
	private JCheckBox[] selectDialog_multi_playerReadied;
	private JMenuItem selectDialog_menu_view_rank;
	private boolean single_start_problem_occur;
	private boolean random_order;
	private JCheckBox selectDialog_randomOrder;
	private JMenuItem selectDialog_menu_saveLog;
	private FileFilter ff2;
	private JFileChooser cfd2;
	private JTabbedPane centerBaseTab;
	private JPanel centerSecondTab;
	private JPanel centerSecondCenter;
	private JPanel[] centerSecondPns;
	private JScrollPane centerSecondScroll;
	private JLabel[] centerSecondLabels;
	private JTextField[] centerSecondPoint;
	private JTextField[] centerSecondCards;
	private JTextField[] centerSecondCounts;
	private JLabel[] centerSecondPointLabel;
	private JLabel[] centerSecondCountsLabel;
	private JPanel centerSecondLeft;
	private JList centerSecondList;
	private JScrollPane centerSecondListScroll;
	private JButton[] centerSecondPay;
	private boolean center_tab_used = false;
	private boolean center_tab_saved = false;
	private String readed_notice;
	private boolean notice_read_success = false;	
	private FileFilter ff3;
	private JLabel centerSecondListLabel;
	private JLabel[] finishDialog_labels2;
	private JTextField[] finishDialog_fields2;
	private boolean independence = false;
	private JMenuItem selectDialog_menu_view_user;
	private User_Selector user_selector;
	private long betted = 0;
	private boolean isBetted = false;
	private JTextField[] finishDialog_ranks;
	private JPanel finishDialog_bottomPanel;
	private JPanel finishDialog_codePanel;
	private JTextField finishDialog_code;
	private JLabel finishDialog_codeLabel;
	private JMenuItem selectDialog_menu_view_checker;
	private Code_Checker code_checker;
	private JMenuItem start2;
	private int change_card_used;
	private boolean change_card_saved;
	private JPanel ver_titlePanel;
	private JPanel ver_makerPanel;
	private JPanel ver_bottomPanel;
	private JPanel ver_upPanel;
	private JPanel ver_keyPanel;
	private JLabel ver_serial;
	private JDialog serialDialog;
	private JPanel serialDialog_mainPanel;
	private JPanel serialDialog_upPanel;
	private JPanel serialDialog_downPanel;
	private JPanel serialDialog_centerPanel;
	private JPanel serialDialog_titlePanel;
	private JLabel serialDialog_title;
	private JTextField[] serialDialog_keys;
	private JPanel serialDialog_contentPanel;
	private JLabel[] serialDialog_bars;
	private JButton serialDialog_close;
	private JButton serialDialog_ok;
	private int serialDialog_mousex;
	private int serialDialog_mousey;
	public static int grade_mode = 0;
	public static int default_fontSize = 12;
	private RunManager manager = null;
	private boolean finished;
	private boolean continue_ord;
	private boolean runAIInThread = false;
	private String[] arguments;
	private JPanel ver_downPanel;
	private JMenu selectDialog_menu_anothers;
	private JCheckBoxMenuItem selectDialog_menu_anothers_oneCard;
	private JMenuItem selectDialog_menu_anothers_warn;
	private JEditorPane serialDialog_message;
	private JScrollPane serialDialog_messageSc;
	private JPanel message_consolePanel;
	private JPanel message_controlPanel;
	private JTextField message_console;
	private JButton message_act;
	private JButton finishDialog_copy;
	private int winner;
	private int secondBlock_index;
	private JLabel title_iconLabel;
	private JPanel selectDialog_noticePanel;
	private JEditorPane selectDialog_noticeArea;
	private JScrollPane selectDialog_noticeScroll;
	private JButton selectDialog_noticeOK;
	private JDialog warnDialog;
	private JPanel warnDialog_mainPanel;
	private JPanel warnDialog_upPanel;
	private JPanel warnDialog_centerPanel;
	private JPanel warnDialog_downPanel;
	private JPanel warnDialog_titlePanel;
	private JLabel warnDialog_title;
	private JLabel warnDialog_message;
	private JButton warnDialog_close;
	private int warn_mousex;
	private int warn_mousey;
	private JPanel selectDialog_noticeControlPanel;
	private JButton selectDialog_noticeRetry;
	private int notice_read_count = 0;
	private JLabel selectDialog_noticeVersionLabel;
	private boolean auto_play_available_player = true;
	private JCheckBoxMenuItem selectDialog_menu_anothers_conquer;
	private JPanel selectDialog_miniSetting;
	private JPanel selectDialog_miniSetting_mainPanel;
	private JScrollPane selectDialog_miniSetting_mainScroll;
	private JPanel[] selectDialog_miniSetting_pns;
	private JButton miniSetting_accept;
	private JLabel miniSetting_needRestartLabel;
	private JCheckBox miniSetting_useTab;
	private JSpinner miniSetting_difficulty;
	private JLabel miniSetting_difficultyLabel;
	private JLabel miniSetting_cardCountLabel;
	private JSpinner miniSetting_cardCount;
	private JLabel miniSetting_changeCountLabel;
	private JSpinner miniSetting_changeCount;
	private boolean open_serialDialog;
	private AIActedDialog aiActedDialog;
	private volatile boolean endDialog_paused = false;
	private volatile boolean paused = false;
	private JTextField realTimeField;
	private JPanel selectDialog_bottomControlPanel;
	private Vector<String> realTimeStore;
	private int realTimeIndex = 0;
	private int realTimeDelay = Integer.MAX_VALUE;
	private JPanel barCenterPanel;
	private JLabel miniSetting_multiplyCountLabel;
	private JSpinner miniSetting_multiplyCount;
	private ScriptManager scriptFactory;
	private JDialog scriptDialog;
	private JPanel scriptDialog_mainPanel;
	private JPanel scriptDialog_titlePanel;
	private JPanel scriptDialog_centerPanel;
	private JPanel scriptDialog_downPanel;
	private JLabel scriptDialog_title;
	private JTextArea scriptDialog_text;
	private JScrollPane scriptDialog_scroll;
	private JButton scriptDialog_close;
	private JButton scriptDialog_run;
	private JMenuItem selectDialog_menu_view_script;
	private int script_mousex;
	private int script_mousey;
	private Vector<JRadioButtonWithModule> selectDialog_select_modules;
	private JDialog scenarioDialog;
	private JPanel scenarioDialog_mainPanel;
	private JPanel scenarioDialog_centerPanel;
	private JPanel scenarioDialog_upPanel;
	private JPanel scenarioDialog_downPanel;
	private JList scenarioDialog_list;
	private JScrollPane scenarioDialog_scroll;
	private JButton scenarioDialog_close;
	private JPanel scenarioDialog_titlePanel;
	private JLabel scenarioDialog_titleLabel;
	private JMenuItem selectDialog_menu_view_scenario;
	private int scenario_mousex;
	private int scenario_mousey;
	private JTextArea scenarioDialog_descriptionPanel;
	private JScrollPane scenarioDialog_descriptionScroll;
	private JProgressBar aliveBar;
	private JPanel deckPanel;
	private JPanel alivePanel;
	private volatile int alives = 0;
	private JPanel message_modePanel;
	private JList message_mode;
	private ChatManager chats;
	private volatile boolean chatMode = false;
	private JPanel chatPanel;
	private JButton chatOk;
	private Vector<String> consoleMode;
	private JScrollPane message_mode_scroll;
	private JButton chatRemove;
	private JButton chatSet;
	private JPanel chatMainPanel;
	private int bonus_point = 5;
	private JButton miniSetting_changeTheme;
	private JDialog themeDialog;
	private JPanel themeDialog_mainPanel;
	private JPanel themeDialog_centerPanel;
	private JPanel themeDialog_downPanel;
	private JPanel[] themeDialog_pns;
	private JButton themeDialog_accept;
	private JButton themeDialog_close;
	private JComboBox themeDialog_combo;
	private JComboBox themeDialog_colorCombo;
	private JPanel themeDialog_upPanel;
	private JLabel themeDialog_title;
	private JPanel themeDialog_titlePanel;
	private int theme_mousex;
	private int theme_mousey;
	private BigInteger turn_passed;
	private JPanel scenarioDialog_buttonPanel;
	private JButton scenarioDialog_new;
	private ScenarioEditor scenarioEditor;
	private JCheckBox selectDialog_scriptUse;
	private boolean scriptAllow;
	private JMenuItem selectDialog_menu_update;
	private JMenu selectDialog_menu_run;
	private JMenuItem selectDialog_menu_uninstall;
	private JButton finishDialog_web;
	private String scenario_web;
	private int fin_slots = 4;
	private int[] centerSecondThemeIndex;
	private Point thisLocation;
	private Dimension thisSize;
	private int centerSecondThemeNow = 0;
	private int centerSecondThemeSetNow;
	private int[] centerSecondThemeSetIndex;
	private JLabel ver_titleLabel;
	private Component ver_makerLabel;
	private JCheckBox themeDialog_classic;
	private boolean din_use = false;
	private JCheckBoxMenuItem selectDialog_menu_anothers_math;
	private JMenuItem selectDialog_menu_anothers_city;
	private CityManager citym = null;
	private JMenuItem selectDialog_menu_anothers_reflex;
	private Reflexioner reflex;
	private Color gradeColor;
	private static transient int calc_grade = 0;
	public static transient String calc_grade_str = "Lite"; 
	
	public CalcWindow()
	{
		init(null, new Dimension(1024, 768), null);
	}
	public CalcWindow(Setting setting, Dimension screenSize, String[] args, boolean independence, RunManager manager)
	{
		this.manager = manager;
		init(setting, screenSize, args);
		this.independence = independence;
	}
	public void init(Setting setting, Dimension screenSize, String[] args)
	{
		System.out.print(".");
		this.screenSize = screenSize;		
		this.setting = setting;
		this.arguments = args;
		
					
		if(setting == null)
		{
			this.setting = Setting.getNewInstance();
		}
				
		centerSecondThemeIndex = new int[200];
		centerSecondThemeSetIndex = new int[200];
		if(this.setting.getLang() == null) lang = new English();
		else lang = this.setting.getLang();
		
		prepareFont();
		
		System.out.print(".");
		
		realTimeStore = new Vector<String>();
		
		ChatManager.ready(this);
		chats = ChatManager.getInstance();
		
		slots = this.setting.getSlots();
		blocks = new Vector<Block>();
		blocks.add(new Block(this));
		listener_added = new Vector<JButton>();
		centerSecondButtons = new Vector<JButton>();
		deck = new Vector<StandardCard>();
		control_collector = new ControlCollector();
		try
		{
			InetAddress inet = InetAddress.getLocalHost();
			ip_address = inet.getHostAddress();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
		}
		
		System.out.print(".");
		
		selected_back = this.setting.getSelected_back();
		selected_inside_back = this.setting.getSelected_inside_back();
		selected_fore = this.setting.getSelected_fore();
		selected_button = this.setting.getSelected_button();
		unselected_button = this.setting.getUnselected_button();
		unselected_back = this.setting.getUnselected_back();
		unselected_inside_back = this.setting.getUnselected_inside_back();
		unselected_fore = this.setting.getUnselected_fore();
		if(this.setting.getColor_reverse() != null)
			color_reverse = this.setting.getColor_reverse().booleanValue();
		
		basic_width = this.setting.getWidth();
		basic_height = this.setting.getHeight();
		start_givenCards = this.setting.getStart_givenCards();
		
		if(setting.isAuto_scrollBar())
		{
			int scrollCount = setting.getSlots() * 300;
			if(setting.getWidth() > scrollCount) setting.setScrollBar(false);
			else setting.setScrollBar(true);
		}
		this.setSize(basic_width, basic_height);
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - (this.getHeight() + 60)/2 - 30));
		if(this.setting.isUse_own_titleBar()) this.setUndecorated(true);
		else this.addWindowListener(this);
		this.setTitle(lang.getText(Language.TITLE));
		mainFrame = this;
		
		System.out.print(".");
		
		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(basePanel);
		
		mainPanel = new JPanel();
		titleBar = new JPanel();
		basePanel.add(mainPanel, BorderLayout.CENTER);
		if(this.setting.isUse_own_titleBar()) basePanel.add(titleBar, BorderLayout.NORTH);
		mainPanel.setBorder(new EtchedBorder());
		
		titleBar.setLayout(new BorderLayout());
		titleBar.setBorder(new EtchedBorder());
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());		
		titleLabel = new JLabel(lang.getText(Language.TITLE));
		if(usingFont != null)
			titleLabel.setFont(usingFont.deriveFont(Font.BOLD, 20));
		else
			titleLabel.setFont(new Font(usingFontName, Font.BOLD, 20));
		titlePanel.add(titleLabel);	
		
		titleBar.add(titlePanel, BorderLayout.CENTER);
		title_exit = new JButton(lang.getText(Language.X));
		if(usingFont != null)
			title_exit.setFont(usingFont);
		title_exit.addActionListener(this);
		title_buttonPanel = new JPanel();
		title_iconPanel = new JPanel();
		title_iconLabel = new JLabel();
		title_iconPanel.setLayout(new FlowLayout());
		title_iconPanel.add(title_iconLabel);
		title_buttonPanel.setLayout(new FlowLayout());
		titleBar.add(title_buttonPanel, BorderLayout.EAST);
		titleBar.add(title_iconPanel, BorderLayout.WEST);	
		title_buttonPanel.add(title_exit);
		
		if(setting.isUse_own_titleBar()) 
		{
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			
			try
			{
				ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("calc_ico_24.png"));				
				title_iconLabel.setIcon(imageIcon);
				title_iconLabel.setSize(20, 20);
			}
			catch(Exception e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
			}
			/*
			try
			{
				titleLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")));
			}
			catch(Exception e)
			{
				if(setting.error_printDetail) e.printStackTrace();
			}*/
		}
		try
		{
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")).getImage());
		}
		catch(Exception e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
		}
		
		stop_game = new JButton(lang.getText(Language.GAME_STOP));
		if(usingFont != null)
			stop_game.setFont(usingFont);
		stop_game.addActionListener(this);
		help2 = new JButton(lang.getText(Language.HELP));
		if(usingFont != null)
			help2.setFont(usingFont);
		help2.addActionListener(this);
		time_limit = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		
		System.out.print(".");
		
		upPanel = new JPanel();
		centerBasePanel = new JPanel();
		centerBaseTab = new JTabbedPane();
		if(usingFont != null)
			centerBaseTab.setFont(usingFont);
		centerPanel = new JPanel();
		centerSecondTab = new JPanel();
		centerScroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		downPanel = new JPanel();
		centerBasePanel.setLayout(new BorderLayout());
		centerBasePanel.add(centerScroll);
		centerSecondCenter = new JPanel();
		centerSecondLeft = new JPanel();
		centerSecondListLabel = new JLabel();
		centerSecondList = new JList();
		if(usingFont != null)
			centerSecondList.setFont(usingFont);
		centerSecondPns = new JPanel[blocks.size()];
		centerSecondLabels = new JLabel[centerSecondPns.length];
		centerSecondPoint = new JTextField[centerSecondPns.length];
		centerSecondCards = new JTextField[centerSecondPns.length];
		centerSecondCounts = new JTextField[centerSecondPns.length];
		centerSecondCountsLabel = new JLabel[centerSecondPns.length];
		centerSecondPointLabel = new JLabel[centerSecondPns.length];
		for(int i=0; i<centerSecondPns.length; i++)
		{
			centerSecondPns[i] = new JPanel();
			centerSecondLabels[i] = new JLabel();
			centerSecondPoint[i] = new JTextField(15);
			centerSecondCards[i] = new JTextField(5);
			centerSecondCounts[i] = new JTextField(5);
			centerSecondCountsLabel[i] = new JLabel();
			centerSecondPointLabel[i] = new JLabel();
		}
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		if(setting.isCenter_tab())
		{
			mainPanel.add(centerBaseTab, BorderLayout.CENTER);
			centerBaseTab.add(centerBasePanel, lang.getText(Language.DETAIL));
			centerBaseTab.add(centerSecondTab, lang.getText(Language.SUMMARY));
		}
		else mainPanel.add(centerBasePanel, BorderLayout.CENTER);
		
		System.out.print(".");
		
		
		upPanel.setLayout(new BorderLayout());		
		deckPanel = new JPanel();
		upPanel.add(deckPanel, BorderLayout.CENTER);
		deckPanel.setLayout(new FlowLayout());
		deckLabel1 = new JLabel(lang.getText(Language.DECK_LABEL1));
		if(usingFont != null)
			deckLabel1.setFont(usingFont);
		deckLabel2 = new JLabel(lang.getText(Language.DECK_LABEL2));
		if(usingFont != null)
			deckLabel2.setFont(usingFont);
		takeButton = new JButton(lang.getText(Language.GET));
		if(usingFont != null)
			takeButton.setFont(usingFont);
		takeButton.addActionListener(this);
		deckPanel.add(deckLabel1);
		deckPanel.add(deckLabel2);
		deckPanel.add(takeButton);
		
		downPanel.setLayout(new BorderLayout());
		controlPanel = new JPanel();
		barPanel = new JPanel();
		//downPanel.add(controlPanel, BorderLayout.CENTER);
		downPanel.add(barPanel, BorderLayout.CENTER);
		controlPanel.setLayout(new FlowLayout());
		
		controlPanel.add(stop_game);
		controlPanel.add(help2);
		
		realTimeField = new JTextField(5);
		if(usingFont != null)
			realTimeField.setFont(usingFont);
		realTimeField.setEditable(false);
		realTimeField.setBorder(new EtchedBorder());		
		barPanel.setLayout(new BorderLayout());
		barCenterPanel = new JPanel();
		barCenterPanel.setLayout(new BorderLayout());
		barPanel.add(barCenterPanel, BorderLayout.CENTER);
		barPanel.add(controlPanel, BorderLayout.EAST);
		barCenterPanel.add(time_limit, BorderLayout.CENTER);
		barCenterPanel.add(realTimeField, BorderLayout.SOUTH);
		if(! (setting.getLookAndFeel().equals(UIManager.getSystemLookAndFeelClassName()) && (setting.getOs().startsWith("Window") || setting.getOs().startsWith("window"))))
		{
			time_limit.setStringPainted(true);
			time_limit.setString(lang.getText(Language.TITLE));
			if(usingFont != null)
				time_limit.setFont(usingFont);
		}
		
		aliveBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		alivePanel = new JPanel();
		//upPanel.add(alivePanel, BorderLayout.EAST);
		alivePanel.setLayout(new FlowLayout());
		//alivePanel.add(aliveBar);
		
		messageDialog = new JDialog(this, false);
		messageDialog.setSize(this.getWidth(), 150);		
		messageDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + (this.getHeight())));
		messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new EtchedBorder());		
		messageDialog.setLayout(new BorderLayout());
		if(this.setting.isUse_own_titleBar()) messageDialog.setUndecorated(true);
		else messageDialog.addWindowListener(this);
		messageDialog.getContentPane().add(messagePanel);		
		messages = new JTextArea();	
		if(usingFont != null)
			messages.setFont(usingFont);
		messages.setText("\n\n");
		messages.setLineWrap(true);
		messages.setEditable(false);
		messages_scroll = new JScrollPane(messages, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messages.setMaximumSize(new Dimension(setting.getWidth(), setting.getHeight() / 8));
		messagePanel.add(messages_scroll, BorderLayout.CENTER);
		message_consolePanel = new JPanel();
		messagePanel.add(message_consolePanel, BorderLayout.SOUTH);
		message_modePanel = new JPanel();
		messagePanel.add(message_modePanel, BorderLayout.EAST);
		message_modePanel.setLayout(new BorderLayout());
		message_mode = new JList();
		if(usingFont != null)
			message_mode.setFont(usingFont);
		message_mode_scroll = new JScrollPane(message_mode, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		message_modePanel.add(message_mode_scroll, BorderLayout.CENTER);
		message_mode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		message_mode.addListSelectionListener(this);
		consoleMode = new Vector<String>();
		consoleMode.add(lang.getText(Language.ORDER) + ", " + lang.getText(Language.SCRIPT));
		consoleMode.add(lang.getText(Language.ORDER));
		consoleMode.add(lang.getText(Language.SCRIPT));
		message_mode.setListData(consoleMode);
		message_mode.setSelectedIndex(0);
		message_consolePanel.setLayout(new BorderLayout());
		message_controlPanel = new JPanel();
		message_console = new JTextField(10);
		if(usingFont != null)
			message_console.setFont(usingFont);
		message_console.addActionListener(this);
		//message_consolePanel.add(aliveBar, BorderLayout.NORTH);
		message_consolePanel.add(message_console, BorderLayout.CENTER);
		message_consolePanel.add(message_controlPanel, BorderLayout.EAST);
		message_act = new JButton(lang.getText(Language.RUN));
		if(usingFont != null)
			message_act.setFont(usingFont);
		message_act.addActionListener(this);
		message_controlPanel.setLayout(new BorderLayout());
		message_controlPanel.add(message_act, BorderLayout.CENTER);
		
		chatPanel = new JPanel();
		chatMainPanel = new JPanel();
		message_modePanel.add(chatMainPanel, BorderLayout.SOUTH);
		chatMainPanel.setLayout(new BorderLayout());
		chatMainPanel.add(chatPanel, BorderLayout.CENTER);
		chatMainPanel.add(aliveBar, BorderLayout.SOUTH);
		chatPanel.setLayout(new GridLayout(1, 3));
		chatOk = new JButton(setting.getLang().getText(Language.ADD));
		if(usingFont != null)
			chatOk.setFont(usingFont);
		chatOk.addActionListener(this);
		chatRemove = new JButton(setting.getLang().getText(Language.REMOVE));
		if(usingFont != null)
			chatRemove.setFont(usingFont);
		chatRemove.addActionListener(this);
		chatRemove.setEnabled(false);
		chatSet = new JButton(lang.getText(Language.PORT));
		if(usingFont != null)
			chatSet.setFont(usingFont);
		chatSet.addActionListener(this);
		chatPanel.add(chatOk);
		chatPanel.add(chatRemove);
		chatPanel.add(chatSet);
		
		
		System.out.print(".");
		
		warnDialog = new JDialog(this, false);
		warnDialog.getContentPane().setLayout(new BorderLayout());
		warnDialog_mainPanel = new JPanel();
		warnDialog.getContentPane().add(warnDialog_mainPanel);
		warnDialog_mainPanel.setLayout(new BorderLayout());
		warnDialog_mainPanel.setBorder(new EtchedBorder());
		warnDialog_upPanel = new JPanel();
		warnDialog_centerPanel = new JPanel();
		warnDialog_downPanel = new JPanel();
		warnDialog_mainPanel.add(warnDialog_centerPanel, BorderLayout.CENTER);
		warnDialog_mainPanel.add(warnDialog_downPanel, BorderLayout.SOUTH);
		warnDialog_mainPanel.add(warnDialog_upPanel, BorderLayout.NORTH);
		warnDialog_centerPanel.setLayout(new FlowLayout());
		warnDialog_downPanel.setLayout(new FlowLayout());
		warnDialog_upPanel.setLayout(new BorderLayout());
		warnDialog_titlePanel = new JPanel();
		warnDialog_titlePanel.setBorder(new EtchedBorder());
		warnDialog_titlePanel.setLayout(new FlowLayout());
		warnDialog_title = new JLabel(lang.getText(Language.FAIL));
		if(usingFont != null)
			warnDialog_title.setFont(usingFont);
		warnDialog_titlePanel.add(warnDialog_title);
		if(setting.isUse_own_titleBar())
		{
			warnDialog.setUndecorated(true);
			warnDialog_upPanel.add(warnDialog_titlePanel, BorderLayout.CENTER);
			warnDialog_titlePanel.addMouseListener(this);
			warnDialog_titlePanel.addMouseMotionListener(this);
			warnDialog.setSize(450, 120);
		}
		else
		{
			warnDialog.addWindowListener(this);
			warnDialog.setTitle(lang.getText(Language.FAIL));
			warnDialog.setSize(450, 150);
		}
		
		warnDialog.setLocation((int)(screenSize.getWidth()/2 - warnDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - warnDialog.getHeight()/2));
		warnDialog_message = new JLabel();
		if(usingFont != null)
			warnDialog_message.setFont(usingFont);
		warnDialog_centerPanel.add(warnDialog_message);
		warnDialog_close = new JButton(lang.getText(Language.CLOSE));
		warnDialog_close.addActionListener(this);
		warnDialog_downPanel.add(warnDialog_close);
		
		selectDialog = new JDialog(this, false);
		selectDialog.setSize(400, 340);
		selectDialog.setLocation((int)(screenSize.getWidth()/2 - selectDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - selectDialog.getHeight()/2));
		if(this.setting.isUse_own_titleBar()) selectDialog.setUndecorated(true);
		else
		{
			selectDialog.addWindowListener(this);
			selectDialog.setTitle(lang.getText(Language.TITLE));
		}
		selectDialog_mainPanel = new JPanel();
		selectDialog_mainPanel.setBorder(new EtchedBorder());
		selectDialog.setLayout(new BorderLayout());
		selectDialog.getContentPane().add(selectDialog_mainPanel);
		title = new JLabel(lang.getText(Language.TITLE));
		if(usingFont != null)
			title.setFont(usingFont);
		selectDialog_mainPanel.setLayout(new BorderLayout());
		selectDialog_titlePanel = new JPanel();
		selectDialog_upPanel = new JPanel();
		selectDialog_menuBar = new JMenuBar();
		if(usingFont != null)
			selectDialog_menuBar.setFont(usingFont);
		selectDialog_centerPanel = new JPanel();
		selectDialog_downPanel = new JPanel();
		selectDialog_titlePanel.setLayout(new FlowLayout());
		selectDialog_centerPanel.setLayout(new GridLayout(slots, 1));
		selectDialog_centerScroll = new JScrollPane(selectDialog_centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		selectDialog_downPanel.setLayout(new BorderLayout());
		selectDialog_tab = new JTabbedPane(JTabbedPane.NORTH);
		if(usingFont != null)
			selectDialog_tab.setFont(usingFont);
		selectDialog_noticePanel = new JPanel();
		selectDialog_noticeArea = new JEditorPane();
		selectDialog_noticeArea.setEditable(false);
		selectDialog_noticeArea.setText(lang.getText(Language.NOTICE_NULL));
		selectDialog_noticeScroll = new JScrollPane(selectDialog_noticeArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		selectDialog_noticePanel.setLayout(new BorderLayout());
		selectDialog_noticePanel.add(selectDialog_noticeScroll, BorderLayout.CENTER);
		selectDialog_noticeOK = new JButton(lang.getText(Language.ACCEPT));
		if(usingFont != null)
			selectDialog_noticeOK.setFont(usingFont);
		selectDialog_noticeOK.addActionListener(this);
		selectDialog_noticeRetry = new JButton(lang.getText(Language.REFRESH));
		if(usingFont != null)
			selectDialog_noticeRetry.setFont(usingFont);
		selectDialog_noticeRetry.addActionListener(this);
		selectDialog_noticeVersionLabel = new JLabel(getVersionString());
		if(usingFont != null)
			selectDialog_noticeVersionLabel.setFont(usingFont);
		selectDialog_noticeControlPanel = new JPanel();
		selectDialog_noticeControlPanel.setLayout(new BorderLayout());
		selectDialog_noticeControlPanel.add(selectDialog_noticeVersionLabel, BorderLayout.WEST);
		selectDialog_noticeControlPanel.add(selectDialog_noticeOK, BorderLayout.CENTER);
		selectDialog_noticeControlPanel.add(selectDialog_noticeRetry, BorderLayout.EAST);
		selectDialog_noticePanel.add(selectDialog_noticeControlPanel, BorderLayout.SOUTH);
		
		selectDialog_miniSetting = new JPanel();
		selectDialog_miniSetting.setLayout(new BorderLayout());
		selectDialog_miniSetting_mainPanel = new JPanel();
		selectDialog_miniSetting_mainScroll = new JScrollPane(selectDialog_miniSetting_mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		selectDialog_miniSetting.add(selectDialog_miniSetting_mainScroll);
		selectDialog_miniSetting_pns = new JPanel[8];
		selectDialog_miniSetting_mainPanel.setLayout(new GridLayout(selectDialog_miniSetting_pns.length, 1));
		for(int i=0; i<selectDialog_miniSetting_pns.length; i++)
		{
			selectDialog_miniSetting_pns[i] = new JPanel();
			selectDialog_miniSetting_pns[i].setLayout(new FlowLayout());
			selectDialog_miniSetting_mainPanel.add(selectDialog_miniSetting_pns[i]);
		}
		miniSetting_accept = new JButton(lang.getText(Language.ACCEPT));
		if(usingFont != null)
			miniSetting_accept.setFont(usingFont);
		miniSetting_accept.setEnabled(false);
		miniSetting_accept.addActionListener(this);		
		miniSetting_needRestartLabel = new JLabel(lang.getText(Language.NEED_RESTART));
		if(usingFont != null)
			miniSetting_needRestartLabel.setFont(usingFont);
		selectDialog_miniSetting_pns[0].add(miniSetting_needRestartLabel);
		selectDialog_miniSetting_pns[0].add(miniSetting_accept);
		
		miniSetting_useTab = new JCheckBox(lang.getText(Language.SUMMARY_USE));
		if(usingFont != null)
			miniSetting_useTab.setFont(usingFont);
		miniSetting_useTab.addItemListener(this);
		selectDialog_miniSetting_pns[2].add(miniSetting_useTab);
		
		miniSetting_difficultyLabel = new JLabel(lang.getText(Language.DIFFICULTY));
		if(usingFont != null)
			miniSetting_difficultyLabel.setFont(usingFont);
		miniSetting_difficulty = new JSpinner();
		if(usingFont != null)
			miniSetting_difficulty.setFont(usingFont);
		miniSetting_difficulty.addChangeListener(this);
		selectDialog_miniSetting_pns[3].add(miniSetting_difficultyLabel);
		selectDialog_miniSetting_pns[3].add(miniSetting_difficulty);
		
		miniSetting_cardCountLabel = new JLabel(lang.getText(Language.ON_START_CARDS));
		if(usingFont != null)
			miniSetting_cardCountLabel.setFont(usingFont);
		miniSetting_cardCount = new JSpinner();
		if(usingFont != null)
			miniSetting_cardCount.setFont(usingFont);
		miniSetting_cardCount.addChangeListener(this);
		selectDialog_miniSetting_pns[4].add(miniSetting_cardCountLabel);
		selectDialog_miniSetting_pns[4].add(miniSetting_cardCount);
		
		miniSetting_changeCountLabel = new JLabel(lang.getText(Language.CHANGE_CARD) + " " + lang.getText(Language.COUNT));
		if(usingFont != null)
			miniSetting_changeCountLabel.setFont(usingFont);
		miniSetting_changeCount = new JSpinner();
		if(usingFont != null)
			miniSetting_changeCount.setFont(usingFont);
		miniSetting_changeCount.addChangeListener(this);
		selectDialog_miniSetting_pns[6].add(miniSetting_changeCountLabel);
		selectDialog_miniSetting_pns[6].add(miniSetting_changeCount);
		
		miniSetting_multiplyCountLabel = new JLabel(String.valueOf(setting.getOp_multiply()) + " " + lang.getText(Language.COUNT));
		if(usingFont != null)
			miniSetting_multiplyCountLabel.setFont(usingFont);
		miniSetting_multiplyCount = new JSpinner();
		if(usingFont != null)
			miniSetting_multiplyCount.setFont(usingFont);
		miniSetting_multiplyCount.addChangeListener(this);
		selectDialog_miniSetting_pns[5].add(miniSetting_multiplyCountLabel);
		selectDialog_miniSetting_pns[5].add(miniSetting_multiplyCount);
		
		miniSetting_changeTheme = new JButton(lang.getText(Language.THEME));
		if(usingFont != null)
			miniSetting_changeTheme.setFont(usingFont);
		miniSetting_changeTheme.addActionListener(this);
		selectDialog_miniSetting_pns[1].add(miniSetting_changeTheme);
		
		selectDialog_tab.add(lang.getText(Language.NOTICE), selectDialog_noticePanel);
		selectDialog_tab.add(lang.getText(Language.SINGLE) + " " + lang.getText(Language.PLAY), selectDialog_centerScroll);
		selectDialog_tab.add(lang.getText(Language.SETTING), selectDialog_miniSetting);
		selectDialog_tab.addChangeListener(this);
		selectDialog_mainPanel.add(selectDialog_tab, BorderLayout.CENTER);
		selectDialog_mainPanel.add(selectDialog_upPanel, BorderLayout.NORTH);
		selectDialog_upPanel.setLayout(new BorderLayout());
		if(setting.isUse_own_titleBar()) selectDialog_upPanel.add(selectDialog_titlePanel, BorderLayout.NORTH);
		selectDialog_upPanel.add(selectDialog_menuBar, BorderLayout.CENTER);
		selectDialog_mainPanel.add(selectDialog_downPanel, BorderLayout.SOUTH);
		selectDialog_bottomPanel = new JPanel();
		selectDialog_controlPanel = new JPanel();
		selectDialog_downPanel.add(selectDialog_bottomPanel, BorderLayout.SOUTH);
		selectDialog_downPanel.add(selectDialog_controlPanel, BorderLayout.CENTER);
		if(this.setting.isUse_own_titleBar())
		{
			selectDialog_titlePanel.setBorder(new EtchedBorder());
			selectDialog_titlePanel.add(title);
			selectDialog_titlePanel.addMouseListener(this);
			selectDialog_titlePanel.addMouseMotionListener(this);
		}
		selectDialog_pns = new JPanel[slots];
		selectDialog_select = new ButtonGroup[selectDialog_pns.length];
		selectDialog_select_none = new JRadioButton[selectDialog_pns.length];
		selectDialog_select_player = new JRadioButton[selectDialog_pns.length];
		selectDialog_select_ai = new JRadioButton[selectDialog_pns.length];
		selectDialog_name = new JTextField[selectDialog_pns.length];		
		
		selectDialog_select_modules = new Vector<JRadioButtonWithModule>();				
		
		for(int i=0; i<selectDialog_pns.length; i++)
		{
			selectDialog_pns[i] = new JPanel();
			selectDialog_pns[i].setLayout(new FlowLayout());
			selectDialog_centerPanel.add(selectDialog_pns[i]);
			selectDialog_select[i] = new ButtonGroup();
			selectDialog_select_none[i] = new JRadioButton(lang.getText(Language.NONE));
			selectDialog_select_player[i] = new JRadioButton(lang.getText(Language.PLAYER));
			selectDialog_select_ai[i] = new JRadioButton(lang.getText(Language.AI));
			if(usingFont != null)
			{
				selectDialog_select_none[i].setFont(usingFont);
				selectDialog_select_player[i].setFont(usingFont);
				selectDialog_select_ai[i].setFont(usingFont);
			}
			if(! ai_enabled) selectDialog_select_ai[i].setEnabled(false);
			selectDialog_select[i].add(selectDialog_select_player[i]);
			selectDialog_select[i].add(selectDialog_select_ai[i]);
			selectDialog_select[i].add(selectDialog_select_none[i]);
			selectDialog_name[i] = new JTextField(8);
			selectDialog_name[i].setText(lang.getText(Language.PLAYER) + " " + String.valueOf((i+1)));
			if(usingFont != null)
				selectDialog_name[i].setFont(usingFont);
			selectDialog_pns[i].add(selectDialog_name[i]);
			selectDialog_pns[i].add(selectDialog_select_none[i]);
			selectDialog_pns[i].add(selectDialog_select_player[i]);
			selectDialog_pns[i].add(selectDialog_select_ai[i]);
			selectDialog_pns[i].setBorder(new EtchedBorder());
		}
		
		try
		{
			selectDialog_name[0].setText(System.getProperty("user.name"));
			if(setting.isUser_selected())
			{
				selectDialog_name[0].setText(setting.getUsers()[setting.getNow_user_index()].getName());
			}
		}
		catch(Exception e)
		{
			
		}
		
		for(int i=0; i<setting.getModules().size(); i++)
		{
			if(setting.getModules().get(i) instanceof BlockModule)
			{
				BlockModule blockModule = (BlockModule) setting.getModules().get(i).clone();
				JRadioButton[] arraies = new JRadioButton[selectDialog_pns.length];
				for(int j=0; j<selectDialog_pns.length; j++)
				{
					arraies[j] = new JRadioButton(blockModule.getLabelText());
					if(usingFont != null)
						arraies[j].setFont(usingFont);
					selectDialog_select[j].add(arraies[j]);
					selectDialog_pns[j].add(arraies[j]);					
				}
				JRadioButtonWithModule newSet = new JRadioButtonWithModule();
				newSet.radioButton = arraies;
				newSet.module = blockModule;
				selectDialog_select_modules.add(newSet);
			}
		}
		
		selectDialog_select_player[0].setSelected(true);
		selectDialog_select_ai[1].setSelected(true);
		if(! ai_enabled) selectDialog_select_player[1].setSelected(true);
		for(int i=2; i<slots; i++)
		{
			selectDialog_select_ai[i].setSelected(true);
		}
		start = new JButton(lang.getText(Language.START));
		if(usingFont != null)
			start.setFont(usingFont);
		start.addActionListener(this);
		exit = new JButton(lang.getText(Language.EXIT));
		if(usingFont != null)
			exit.setFont(usingFont);
		exit.addActionListener(this);
		settingButton = new JButton(lang.getText(Language.SETTING));
		if(usingFont != null)
			settingButton.setFont(usingFont);
		settingButton.addActionListener(this);
		help = new JButton(lang.getText(Language.HELP));
		if(usingFont != null)
			help.setFont(usingFont);
		help.addActionListener(this);
		
		start2 = new JMenuItem(lang.getText(Language.START));
		if(usingFont != null)
			start2.setFont(usingFont);
		start2.addActionListener(this);
		start2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK));	
		
				
		selectDialog_controlPanel.setLayout(new FlowLayout());
		selectDialog_randomOrder = new JCheckBox(lang.getText(Language.RANDOM_ORDER));
		selectDialog_authorityMode = new JCheckBox(lang.getText(Language.AUTHORITY));
		if(usingFont != null)
		{
			selectDialog_randomOrder.setFont(usingFont);
			selectDialog_authorityMode.setFont(usingFont);
		}
		selectDialog_authorityMode.addItemListener(this);
		selectDialog_replayMode = new JCheckBox(lang.getText(Language.REPLAY));
		selectDialog_scriptUse = new JCheckBox(lang.getText(Language.INPUT) + " " + lang.getText(Language.SCRIPT) + " " + lang.getText(Language.USE));
		if(usingFont != null)
		{
			selectDialog_replayMode.setFont(usingFont);
			selectDialog_scriptUse.setFont(usingFont);
		}
		selectDialog_controlPanel.add(selectDialog_randomOrder);
		selectDialog_controlPanel.add(selectDialog_authorityMode);
		selectDialog_controlPanel.add(selectDialog_replayMode);
		selectDialog_controlPanel.add(selectDialog_scriptUse);
		selectDialog_randomOrder.setSelected(true);
		selectDialog_bottomPanel.setLayout(new BorderLayout());
		selectDialog_bottomControlPanel = new JPanel();
		selectDialog_bottomControlPanel.setLayout(new FlowLayout());		
		selectDialog_bottomPanel.add(selectDialog_bottomControlPanel, BorderLayout.CENTER);		
		selectDialog_bottomControlPanel.add(start);
		selectDialog_bottomControlPanel.add(help);
		selectDialog_bottomControlPanel.add(settingButton);
		selectDialog_bottomControlPanel.add(exit);
		
		if((! this.setting.isActive_authority_mode()) || (! ai_enabled)) selectDialog_authorityMode.setVisible(false);
		
		selectDialog_multiPanel = new JPanel();
		if(this.setting.isNetworkEnabled()) selectDialog_tab.add(lang.getText(Language.MULTI) + " " + lang.getText(Language.PLAY), selectDialog_multiPanel);
		selectDialog_multiPanel.setLayout(new BorderLayout());
		selectDialog_multi_controlPanel = new JPanel();
		selectDialog_multi_playerPanel = new JPanel();
		selectDialog_multi_playerScroll = new JScrollPane(selectDialog_multi_playerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		selectDialog_multiPanel.add(selectDialog_multi_controlPanel, BorderLayout.NORTH);
		selectDialog_multiPanel.add(selectDialog_multi_playerScroll, BorderLayout.CENTER);
		selectDialog_multi_host = new JButton(lang.getText(Language.HOST));
		selectDialog_multi_host.addActionListener(this);
		if(usingFont != null)
			selectDialog_multi_host.setFont(usingFont);
		selectDialog_multi_join = new JButton(lang.getText(Language.JOIN));
		selectDialog_multi_join.addActionListener(this);
		if(usingFont != null)
			selectDialog_multi_join.setFont(usingFont);
		selectDialog_multi_disconnect = new JButton(lang.getText(Language.DISCONNECT));
		selectDialog_multi_disconnect.addActionListener(this);
		if(usingFont != null)
			selectDialog_multi_disconnect.setFont(usingFont);
		selectDialog_multi_ip_label = new JLabel(lang.getText(Language.IP));
		selectDialog_multi_ip = new JTextField(15);
		if(usingFont != null)
		{
			selectDialog_multi_ip_label.setFont(usingFont);
			selectDialog_multi_ip.setFont(usingFont);
		}
		selectDialog_multi_port_label = new JLabel(lang.getText(Language.PORT));
		if(usingFont != null)
			selectDialog_multi_port_label.setFont(usingFont);
		selectDialog_multi_port = new JTextField(4);
		if(usingFont != null)
			selectDialog_multi_port.setFont(usingFont);
		selectDialog_multi_nowip_label = new JLabel();
		if(usingFont != null)
			selectDialog_multi_nowip_label.setFont(usingFont);
		selectDialog_multi_controlPns = new JPanel[2];
		selectDialog_multi_controlPanel.setLayout(new GridLayout(2, 1));
		for(int i=0; i<selectDialog_multi_controlPns.length; i++)
		{
			selectDialog_multi_controlPns[i] = new JPanel();
			selectDialog_multi_controlPns[i].setLayout(new FlowLayout());
			selectDialog_multi_controlPanel.add(selectDialog_multi_controlPns[i]);
		}
		selectDialog_multi_controlPns[0].add(selectDialog_multi_nowip_label);
		selectDialog_multi_controlPns[0].add(selectDialog_multi_host);
		selectDialog_multi_controlPns[0].add(selectDialog_multi_disconnect);		
		selectDialog_multi_controlPns[1].add(selectDialog_multi_ip_label);
		selectDialog_multi_controlPns[1].add(selectDialog_multi_ip);
		selectDialog_multi_controlPns[1].add(selectDialog_multi_port_label);
		selectDialog_multi_controlPns[1].add(selectDialog_multi_port);
		selectDialog_multi_controlPns[1].add(selectDialog_multi_join);		
		selectDialog_multi_nowip_label.setText(ip_address);
		selectDialog_multi_host.setEnabled(setting.isNetworkEnabled());
		selectDialog_multi_join.setEnabled(setting.isNetworkEnabled());
		selectDialog_multi_ip.setEditable(setting.isNetworkEnabled());
		selectDialog_multi_port.setEditable(setting.isNetworkEnabled());
		selectDialog_multi_disconnect.setEnabled(false);
		
		selectDialog_menu_file = new JMenu(lang.getText(Language.MENU_FILE));
		if(usingFont != null)
			selectDialog_menu_file.setFont(usingFont);
		selectDialog_menu_exit = new JMenuItem(lang.getText(Language.EXIT));
		if(usingFont != null)
			selectDialog_menu_exit.setFont(usingFont);
		selectDialog_menu_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		selectDialog_menu_exit.addActionListener(this);
		selectDialog_menu_saveLog = new JMenuItem(lang.getText(Language.LOG) + " " + lang.getText(Language.SAVE));
		if(usingFont != null)
			selectDialog_menu_saveLog.setFont(usingFont);
		selectDialog_menu_saveLog.addActionListener(this);
		selectDialog_menu_set = new JMenuItem(lang.getText(Language.DETAIL) + " " + lang.getText(Language.SETTING));
		if(usingFont != null)
			selectDialog_menu_set.setFont(usingFont);
		selectDialog_menu_set.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_MASK));
		selectDialog_menu_set.addActionListener(this);
		selectDialog_menu_update = new JMenuItem(lang.getText(Language.UPDATE));
		if(usingFont != null)
			selectDialog_menu_update.setFont(usingFont);
		selectDialog_menu_update.addActionListener(this);
		selectDialog_menu_run = new JMenu(lang.getText(Language.MANAGEMENT));
		if(usingFont != null)
			selectDialog_menu_run.setFont(usingFont);
		selectDialog_menu_uninstall = new JMenuItem(lang.getText(Language.TITLE) + " " + lang.getText(Language.UNINSTALL));
		if(usingFont != null)
			selectDialog_menu_uninstall.setFont(usingFont);
		selectDialog_menu_uninstall.addActionListener(this);
		selectDialog_menu_view = new JMenu(lang.getText(Language.VIEW));
		if(usingFont != null)
			selectDialog_menu_view.setFont(usingFont);
		selectDialog_menu_help = new JMenu(lang.getText(Language.HELP));
		if(usingFont != null)
			selectDialog_menu_help.setFont(usingFont);
		selectDialog_menu_help_view = new JMenuItem(lang.getText(Language.HELP) + " "  + lang.getText(Language.VIEW));
		if(usingFont != null)
			selectDialog_menu_help_view.setFont(usingFont);
		selectDialog_menu_help_view.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
		selectDialog_menu_help_view.addActionListener(this);
		selectDialog_menu_help_ver = new JMenuItem(lang.getText(Language.ABOUT));
		if(usingFont != null)
			selectDialog_menu_help_ver.setFont(usingFont);
		selectDialog_menu_help_ver.addActionListener(this);
		selectDialog_menu_view_replay = new JMenuItem(lang.getText(Language.REPLAY) + " " + lang.getText(Language.VIEW));
		if(usingFont != null)
			selectDialog_menu_view_replay.setFont(usingFont);
		selectDialog_menu_view_replay.addActionListener(this);
		selectDialog_menu_view_replay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));
		selectDialog_menu_view_replay.addActionListener(this);
		selectDialog_menu_view_rank = new JMenuItem(lang.getText(Language.RANKING) + " " + lang.getText(Language.VIEW));
		if(usingFont != null)
			selectDialog_menu_view_rank.setFont(usingFont);
		selectDialog_menu_view_rank.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));
		selectDialog_menu_view_rank.addActionListener(this);
		selectDialog_menu_view_user = new JMenuItem(lang.getText(Language.USER) + " " + lang.getText(Language.SELECT));
		if(usingFont != null)
			selectDialog_menu_view_user.setFont(usingFont);
		selectDialog_menu_view_user.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, KeyEvent.CTRL_MASK));
		selectDialog_menu_view_user.addActionListener(this);
		selectDialog_menu_view_checker = new JMenuItem(lang.getText(Language.CODE_CHECKER));
		if(usingFont != null)
			selectDialog_menu_view_checker.setFont(usingFont);
		selectDialog_menu_view_checker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, KeyEvent.CTRL_MASK));
		selectDialog_menu_view_checker.addActionListener(this);
		selectDialog_menu_view_script = new JMenuItem(lang.getText(Language.SCRIPT));
		if(usingFont != null)
			selectDialog_menu_view_script.setFont(usingFont);
		selectDialog_menu_view_script.addActionListener(this);
		selectDialog_menu_view_script.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, KeyEvent.CTRL_MASK));
		selectDialog_menu_view_scenario = new JMenuItem(lang.getText(Language.SCENARIO));
		if(usingFont != null)
			selectDialog_menu_view_scenario.setFont(usingFont);
		selectDialog_menu_view_scenario.addActionListener(this);
		
		selectDialog_menu_anothers = new JMenu(lang.getText(Language.ANOTHER_GAMES));
		if(usingFont != null)
			selectDialog_menu_anothers.setFont(usingFont);
		selectDialog_menu_anothers_warn = new JMenuItem(lang.getText(Language.NEED_RESTART));
		if(usingFont != null)
			selectDialog_menu_anothers_warn.setFont(usingFont);
		selectDialog_menu_anothers_warn.setEnabled(false);
		selectDialog_menu_anothers_oneCard = new JCheckBoxMenuItem(lang.getText(Language.ONECARD));
		if(usingFont != null)
			selectDialog_menu_anothers_oneCard.setFont(usingFont);
		selectDialog_menu_anothers_oneCard.addItemListener(this);
		selectDialog_menu_anothers_conquer = new JCheckBoxMenuItem(lang.getText(Language.CONQUER));
		if(usingFont != null)
			selectDialog_menu_anothers_conquer.setFont(usingFont);
		selectDialog_menu_anothers_conquer.addItemListener(this);
		selectDialog_menu_anothers_math = new JCheckBoxMenuItem(lang.getText(Language.MATHCONQ));
		if(usingFont != null)
			selectDialog_menu_anothers_math.setFont(usingFont);
		selectDialog_menu_anothers_math.addItemListener(this);
		
		selectDialog_menu_anothers_city = new JMenuItem(lang.getText(Language.SHELTER_CITY));
		if(usingFont != null)
			selectDialog_menu_anothers_city.setFont(usingFont);
		selectDialog_menu_anothers_city.addActionListener(this);
		
		selectDialog_menu_anothers_reflex = new JMenuItem(lang.getText(Language.REFLEX));
		if(usingFont != null)
			selectDialog_menu_anothers_reflex.setFont(usingFont);
		selectDialog_menu_anothers_reflex.addActionListener(this);
		
		selectDialog_menu_file.add(start2);
		selectDialog_menu_file.add(selectDialog_menu_set);
		selectDialog_menu_file.add(selectDialog_menu_saveLog);
		//selectDialog_menu_file.addSeparator();
		//selectDialog_menu_file.add(selectDialog_menu_anothers);
		selectDialog_menu_file.add(selectDialog_menu_run);
		selectDialog_menu_file.add(selectDialog_menu_exit);
		selectDialog_menu_help.add(selectDialog_menu_help_view);
		selectDialog_menu_help.add(selectDialog_menu_help_ver);
		
		selectDialog_menu_run.add(selectDialog_menu_update);
		selectDialog_menu_run.add(selectDialog_menu_uninstall);
		
		selectDialog_menu_view.add(selectDialog_menu_view_rank);
		selectDialog_menu_view.add(selectDialog_menu_view_replay);	
		//selectDialog_menu_view.addSeparator();
		selectDialog_menu_view.add(selectDialog_menu_view_user);
		selectDialog_menu_view.add(selectDialog_menu_view_checker);
		selectDialog_menu_view.add(selectDialog_menu_view_checker);
		selectDialog_menu_view.add(selectDialog_menu_view_script);
		selectDialog_menu_view.add(selectDialog_menu_view_scenario);
		
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_warn);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_oneCard);	
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_conquer);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_math);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_reflex);
		selectDialog_menu_anothers.add(selectDialog_menu_anothers_city);
		
		selectDialog_menuBar.add(selectDialog_menu_file);
		//selectDialog_menuBar.add(selectDialog_menu_run);
		selectDialog_menuBar.add(selectDialog_menu_view);		
		selectDialog_menuBar.add(selectDialog_menu_anothers);
		selectDialog_menuBar.add(selectDialog_menu_help);
		
		if(RunManager.STANDALONE_MODE >= 0)
			selectDialog_menu_anothers.setVisible(false);
		
		selectDialog_menu_anothers.setVisible(true);
		selectDialog_menu_view_script.setVisible(false);
		
		//if(grade_mode >= 1) selectDialog_menu_anothers.setVisible(true);
		//else  selectDialog_menu_anothers.setVisible(false);
						
		
		System.out.print(".");
		
		help.setVisible(false);
		settingButton.setVisible(false);
		
		finishDialog = new JDialog(this, true);
		finishDialog.setUndecorated(true);
		finishDialog.setSize(470, 320);
		finishDialog.setLocation((int)(screenSize.getWidth()/2 - finishDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - finishDialog.getHeight()/2));
		finishDialog.setLayout(new BorderLayout());
		finishDialog_mainPanel = new JPanel();
		finishDialog_mainPanel.setLayout(new BorderLayout());
		finishDialog.getContentPane().add(finishDialog_mainPanel);
		finishDialog_mainPanel.setBorder(new EtchedBorder());
		finishDialog_centerPanel = new JPanel();
		finishDialog_upPanel = new JPanel();
		finishDialog_centerScroll = new JScrollPane(finishDialog_centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		finishDialog_downPanel = new JPanel();
		finishDialog_mainPanel.add(finishDialog_centerScroll, BorderLayout.CENTER);
		finishDialog_mainPanel.add(finishDialog_downPanel, BorderLayout.SOUTH);
		finishDialog_mainPanel.add(finishDialog_upPanel, BorderLayout.NORTH);
		finishDialog_downPanel.setLayout(new GridLayout(2, 1));
		finishDialog_bottomPanel = new JPanel();
		finishDialog_codePanel = new JPanel();
		finishDialog_downPanel.add(finishDialog_codePanel);
		finishDialog_downPanel.add(finishDialog_bottomPanel);
		finishDialog_bottomPanel.setLayout(new FlowLayout());
		finishDialog_codePanel.setLayout(new FlowLayout());
		finishDialog_codeLabel = new JLabel(lang.getText(Language.POINT_CODE));
		if(usingFont != null)
			finishDialog_codeLabel.setFont(usingFont);
		finishDialog_code = new JTextField(25);
		finishDialog_code.setFont(usingFont);
		finishDialog_code.setBorder(new EtchedBorder());
		finishDialog_code.setEditable(false);
		finishDialog_code.addMouseListener(this);
		finishDialog_copy = new JButton(lang.getText(Language.COPY_CLIPBOARD));
		if(usingFont != null)
			finishDialog_copy.setFont(usingFont);
		finishDialog_copy.addActionListener(this);
		finishDialog_codePanel.add(finishDialog_codeLabel);
		finishDialog_codePanel.add(finishDialog_code);
		finishDialog_codePanel.add(finishDialog_copy);
		finishDialog_replay = new JButton(lang.getText(Language.REPLAY) + " " + lang.getText(Language.SAVE));
		if(usingFont != null)
			finishDialog_replay.setFont(usingFont);
		finishDialog_replay.addActionListener(this);
		finishDialog_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			finishDialog_close.setFont(usingFont);
		finishDialog_close.addActionListener(this);
		finishDialog_web = new JButton(lang.getText(Language.EVENT) + " " + lang.getText(Language.HOMEPAGE));
		if(usingFont != null)
			finishDialog_web.setFont(usingFont);
		finishDialog_web.addActionListener(this);
		finishDialog_web.setVisible(false);
		finishDialog_bottomPanel.add(finishDialog_replay);
		finishDialog_bottomPanel.add(finishDialog_web);
		finishDialog_bottomPanel.add(finishDialog_close);
		
		if(! authority_mode) finishDialog_replay.setEnabled(false);
		finishDialog_upPanel.setLayout(new FlowLayout());
		finishDialog_label = new JLabel();
		if(usingFont != null)
			finishDialog_label.setFont(usingFont);
		finishLabelText = lang.getText(Language.RESULT) + " : ";
		finishDialog_label.setText(finishLabelText);
		finishDialog_upPanel.add(finishDialog_label);
		finishDialog_upPanel.addMouseListener(this);
		finishDialog_upPanel.addMouseMotionListener(this);
		finishDialog_upPanel.setBorder(new EtchedBorder());
		
		System.out.print(".");
		
		endDialog = new JDialog(this, false);
		endDialog.setUndecorated(true);
		endDialog.setSize(this.getWidth(), this.getHeight());
		endDialog.setLocation((int)(screenSize.getWidth()/2 - endDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - endDialog.getHeight()/2));
		endDialog.setLayout(new BorderLayout());
		endDialog_mainPanel = new JPanel();
		endDialog_mainPanel.setLayout(new BorderLayout());
		endDialog.getContentPane().add(endDialog_mainPanel);
		endDialog_mainPanel.setBorder(new EtchedBorder());
		endDialog_end = new JButton(lang.getText(Language.FINISH));
		//endDialog_end.setFont(usingFont);
		if(usingFont != null)
			endDialog_end.setFont(usingFont.deriveFont(Font.BOLD, 40));
		else
			endDialog_end.setFont(new Font(usingFontName, Font.BOLD, 40));
		endDialog_end.addActionListener(this);
		endDialog_mainPanel.add(endDialog_end);
		
		ver = new JDialog(selectDialog, false);
		
		ver.setLayout(new BorderLayout());
		ver.setUndecorated(true);
		ver.setSize(300, 180);
		ver.setLocation((int)(screenSize.getWidth()/2 - ver.getWidth()/2), (int)(screenSize.getHeight()/2 - ver.getHeight()/2));
		JPanel ver_mainPanel = new JPanel();
		ver.add(ver_mainPanel);
		JPanel ver_centerPanel = new JPanel();
		ver_mainPanel.setBorder(new EtchedBorder());
		ver_mainPanel.setLayout(new BorderLayout());
		ver_mainPanel.add(ver_centerPanel, BorderLayout.CENTER);
		ver_centerPanel.setLayout(new BorderLayout());		
		ver_titlePanel = new JPanel();
		ver_makerPanel = new JPanel();
		ver_bottomPanel = new JPanel();
		ver_upPanel = new JPanel();
		ver_downPanel = new JPanel();
		ver_keyPanel = new JPanel();
		ver_centerPanel.add(ver_upPanel, BorderLayout.CENTER);
		ver_centerPanel.add(ver_downPanel, BorderLayout.SOUTH);
		ver_downPanel.setLayout(new BorderLayout());
		ver_downPanel.add(ver_bottomPanel, BorderLayout.CENTER);
		ver_bottomPanel.setLayout(new FlowLayout());
		ver_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			ver_close.setFont(usingFont);
		ver_close.addActionListener(this);
		ver_close.setForeground(selected_fore);
		if(selected_button != null)
			ver_close.setBackground(selected_button);
		ver_bottomPanel.add(ver_close);
		ver_upPanel.setLayout(new BorderLayout());
		ver_titlePanel.setLayout(new FlowLayout());
		ver_makerPanel.setLayout(new FlowLayout());
		ver_upPanel.setBackground(new Color(76, 76, 76));
		ver_titlePanel.setBackground(new Color(76, 76, 76));
		ver_keyPanel.setBackground(new Color(76, 76, 76));
		ver_titleLabel = new JLabel(lang.getText(Language.TITLE));
		if(usingFont != null)
			ver_titleLabel.setFont(usingFont);
		ver_makerLabel = new JLabel(lang.getText(Language.MAKER) + " " + getVersionString(true));
		if(usingFont != null)
			ver_makerLabel.setFont(usingFont);
		ver_titleLabel.setBackground(new Color(76, 76, 76));
		ver_titleLabel.setForeground(new Color(150, 150, 150));
		if(usingFont != null)
			ver_titleLabel.setFont(usingFont.deriveFont(Font.BOLD, 70));
		else
			ver_titleLabel.setFont(new Font(usingFontName, Font.BOLD, 70));
		ver_titlePanel.add(ver_titleLabel);
		ver_makerPanel.add(ver_makerLabel);
		ver_downPanel.add(ver_makerPanel, BorderLayout.NORTH);
		ver_upPanel.add(ver_titlePanel, BorderLayout.CENTER);
		ver_upPanel.add(ver_keyPanel, BorderLayout.SOUTH);
		ver.setTitle(lang.getText(Language.TITLE));
		ver_makerPanel.setBackground(new Color(150, 150, 150));
		ver_bottomPanel.setBackground(new Color(150, 150, 150));
		ver_keyPanel.setLayout(new FlowLayout());
		ver_serial = new JLabel(lang.getText(Language.BASIC_EDITION));
		if(usingFont != null)
			ver_serial.setFont(usingFont);
		ver_serial.addMouseListener(this);
		ver_serial.setForeground(Color.WHITE);
		gradeColor = Color.WHITE;
		ver_keyPanel.add(ver_serial);
		
		serialDialog = new JDialog(ver, true);		
		if(setting.isUse_own_titleBar()) serialDialog.setSize(450, 220);
		else  serialDialog.setSize(450, 250);
		serialDialog.setTitle(lang.getText(Language.AUTHORITY));
		serialDialog.setLocation((int)(screenSize.getWidth()/2 - serialDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - serialDialog.getHeight()/2));
		serialDialog.getContentPane().setLayout(new BorderLayout());
		serialDialog_mainPanel = new JPanel();
		serialDialog.getContentPane().add(serialDialog_mainPanel);
		serialDialog_mainPanel.setLayout(new BorderLayout());
		serialDialog_mainPanel.setBorder(new EtchedBorder());
		serialDialog_upPanel = new JPanel();
		serialDialog_downPanel = new JPanel();
		serialDialog_centerPanel = new JPanel();
		serialDialog_mainPanel.add(serialDialog_upPanel, BorderLayout.NORTH);
		serialDialog_mainPanel.add(serialDialog_centerPanel, BorderLayout.CENTER);		
		serialDialog_mainPanel.add(serialDialog_downPanel, BorderLayout.SOUTH);
		serialDialog_upPanel.setLayout(new BorderLayout());
		serialDialog_centerPanel.setLayout(new BorderLayout());
		serialDialog_downPanel.setLayout(new FlowLayout());
		serialDialog_titlePanel = new JPanel();
		serialDialog_upPanel.add(serialDialog_titlePanel, BorderLayout.NORTH);
		serialDialog_titlePanel.setBorder(new EtchedBorder());
		serialDialog_titlePanel.setLayout(new FlowLayout());
		serialDialog_title = new JLabel(lang.getText(Language.AUTHORITY));
		if(usingFont != null)
			serialDialog_title.setFont(usingFont);
		serialDialog_titlePanel.add(serialDialog_title);
		if(setting.isUse_own_titleBar())
		{
			serialDialog.setUndecorated(true);
			serialDialog_titlePanel.addMouseListener(this);
			serialDialog_titlePanel.addMouseMotionListener(this);
		}
		serialDialog_contentPanel = new JPanel();
		serialDialog_centerPanel.add(serialDialog_contentPanel, BorderLayout.SOUTH);
		serialDialog_message = new JEditorPane();
		serialDialog_message.setEditable(false);		
		//serialDialog_message.setLineWrap(true);
		serialDialog_messageSc = new JScrollPane(serialDialog_message, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		serialDialog_centerPanel.add(serialDialog_messageSc, BorderLayout.CENTER);
		try
		{
			serialDialog_message.setPage(setting.getNotice_url() + "/free_serial.html");
		}
		catch(Exception e)
		{
			serialDialog_message.setText(lang.getText(Language.INPUT_SERIAL_AGREEMENT));
		}
		
		serialDialog_contentPanel.setLayout(new FlowLayout());
		serialDialog_keys = new JTextField[5];
		serialDialog_bars = new JLabel[4];
		for(int i=0; i<serialDialog_keys.length; i++)
		{
			serialDialog_keys[i] = new JTextField(5);	
			if(usingFont != null)
				serialDialog_keys[i].setFont(usingFont);
			serialDialog_contentPanel.add(serialDialog_keys[i]);
			if(i < serialDialog_keys.length - 1)
			{
				serialDialog_bars[i] = new JLabel("-");
				if(usingFont != null)
					serialDialog_bars[i].setFont(usingFont);
				serialDialog_contentPanel.add(serialDialog_bars[i]);
			}			
		}
		serialDialog_keys[serialDialog_keys.length - 1].addActionListener(this);
		serialDialog_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			serialDialog_close.setFont(usingFont);
		serialDialog_ok = new JButton(lang.getText(Language.OK));
		if(usingFont != null)
			serialDialog_ok.setFont(usingFont);
		serialDialog_close.addActionListener(this);
		serialDialog_ok.addActionListener(this);
		serialDialog_downPanel.add(serialDialog_ok);
		serialDialog_downPanel.add(serialDialog_close);
		
		scriptDialog = new JDialog(this, false);
		if(setting.isUse_own_titleBar())
		{
			scriptDialog.setUndecorated(true);
		}
		scriptDialog.setSize(400, 300);
		scriptDialog.setLocation((int)(screenSize.getWidth()/2 - scriptDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - scriptDialog.getHeight()/2));
		scriptDialog.getContentPane().setLayout(new BorderLayout());
		scriptDialog_mainPanel = new JPanel();
		scriptDialog.getContentPane().add(scriptDialog_mainPanel);
		scriptDialog_mainPanel.setBorder(new EtchedBorder());
		scriptDialog_mainPanel.setLayout(new BorderLayout());
		scriptDialog_titlePanel = new JPanel();
		scriptDialog_centerPanel = new JPanel();
		scriptDialog_downPanel = new JPanel();
		scriptDialog_mainPanel.add(scriptDialog_centerPanel, BorderLayout.CENTER);
		scriptDialog_mainPanel.add(scriptDialog_downPanel, BorderLayout.SOUTH);
		scriptDialog_title = new JLabel(lang.getText(Language.SCRIPT));
		if(usingFont != null)
			scriptDialog_title.setFont(usingFont);
		if(setting.isUse_own_titleBar())
		{
			scriptDialog_mainPanel.add(scriptDialog_titlePanel, BorderLayout.NORTH);
			scriptDialog_titlePanel.setBorder(new EtchedBorder());
			scriptDialog_titlePanel.addMouseListener(this);
			scriptDialog_titlePanel.addMouseMotionListener(this);
			scriptDialog_titlePanel.setLayout(new FlowLayout());
			scriptDialog_titlePanel.add(scriptDialog_title);
		}
		scriptDialog_centerPanel.setLayout(new BorderLayout());
		scriptDialog_text = new JTextArea();
		if(usingFont != null)
			scriptDialog_text.setFont(usingFont);
		scriptDialog_scroll = new JScrollPane(scriptDialog_text);
		scriptDialog_centerPanel.add(scriptDialog_scroll, BorderLayout.CENTER);
		scriptDialog_downPanel.setLayout(new FlowLayout());
		scriptDialog_run = new JButton(lang.getText(Language.RUN));
		if(usingFont != null)
			scriptDialog_run.setFont(usingFont);
		scriptDialog_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			scriptDialog_close.setFont(usingFont);
		scriptDialog_run.addActionListener(this);
		scriptDialog_close.addActionListener(this);
		scriptDialog_downPanel.add(scriptDialog_run);
		scriptDialog_downPanel.add(scriptDialog_close);
		
		scenarioDialog = new JDialog(selectDialog, false);
		scenarioDialog.setSize(300, selectDialog.getHeight());
		if(screenSize.getWidth() >= 1024)
			scenarioDialog.setLocation((int)(selectDialog.getLocation().getX() + selectDialog.getWidth()), (int)(selectDialog.getLocation().getY()));
		else
			scenarioDialog.setLocation((int)(screenSize.getWidth()/2 - scenarioDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - scenarioDialog.getHeight()/2));
		scenarioDialog.setUndecorated(setting.isUse_own_titleBar());
		scenarioDialog.getContentPane().setLayout(new BorderLayout());
		scenarioDialog_mainPanel = new JPanel();
		scenarioDialog_mainPanel.setBorder(new EtchedBorder());
		scenarioDialog.getContentPane().add(scenarioDialog_mainPanel, BorderLayout.CENTER);
		scenarioDialog_mainPanel.setLayout(new BorderLayout());
		scenarioDialog_centerPanel = new JPanel();
		scenarioDialog_upPanel = new JPanel();
		scenarioDialog_downPanel = new JPanel();
		scenarioDialog_mainPanel.add(scenarioDialog_centerPanel, BorderLayout.CENTER);
		scenarioDialog_mainPanel.add(scenarioDialog_upPanel, BorderLayout.NORTH);
		scenarioDialog_mainPanel.add(scenarioDialog_downPanel, BorderLayout.SOUTH);
		scenarioDialog_centerPanel.setLayout(new BorderLayout());
		scenarioDialog_list = new JList();
		if(usingFont != null)
			scenarioDialog_list.setFont(usingFont);
		scenarioDialog_list.addListSelectionListener(this);
		String[] scenarios = new String[setting.getScenarios().size()];
		for(int i=0; i<scenarios.length; i++)
		{
			scenarios[i] = setting.getScenarios().get(i).getName();
		}
		scenarioDialog_list.setListData(scenarios);
		scenarioDialog_scroll = new JScrollPane(scenarioDialog_list);
		scenarioDialog_centerPanel.add(scenarioDialog_scroll, BorderLayout.NORTH);
		scenarioDialog_descriptionPanel = new JTextArea();
		if(usingFont != null)
			scenarioDialog_descriptionPanel.setFont(usingFont);
		scenarioDialog_descriptionPanel.setLineWrap(true);
		scenarioDialog_descriptionPanel.setEditable(false);
		scenarioDialog_descriptionScroll = new JScrollPane(scenarioDialog_descriptionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scenarioDialog_centerPanel.add(scenarioDialog_descriptionScroll, BorderLayout.CENTER);
		
		scenarioDialog_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scenarioDialog_downPanel.setLayout(new BorderLayout());
		scenarioDialog_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			scenarioDialog_close.setFont(usingFont);
		scenarioDialog_close.addActionListener(this);
		scenarioDialog_buttonPanel = new JPanel();
		scenarioDialog_buttonPanel.setLayout(new BorderLayout());
		scenarioDialog_buttonPanel.add(scenarioDialog_close, BorderLayout.CENTER);
		//scenarioDialog_new = new JButton(String.valueOf(setting.getOp_plus()));
		scenarioDialog_new = new JButton(lang.getText(Language.MAKE));
		if(usingFont != null)
			scenarioDialog_new.setFont(usingFont);
		scenarioDialog_new.addActionListener(this);
		if(grade_mode <= 0)
			scenarioDialog_new.setVisible(false);
		else
			scenarioDialog_new.setVisible(true);
		scenarioDialog_buttonPanel.add(scenarioDialog_new, BorderLayout.EAST);
		scenarioDialog_downPanel.add(scenarioDialog_buttonPanel, BorderLayout.CENTER);
		scenarioDialog_upPanel.setLayout(new BorderLayout());
		scenarioDialog_titlePanel = new JPanel();
		scenarioDialog_titleLabel = new JLabel(lang.getText(Language.SCENARIO));
		if(usingFont != null)
			scenarioDialog_titleLabel.setFont(usingFont);
		if(setting.isUse_own_titleBar())
		{
			scenarioDialog_upPanel.add(scenarioDialog_titlePanel, BorderLayout.CENTER);
			scenarioDialog_titlePanel.setBorder(new EtchedBorder());
			scenarioDialog_titlePanel.setLayout(new FlowLayout());
			scenarioDialog_titlePanel.addMouseListener(this);
			scenarioDialog_titlePanel.addMouseMotionListener(this);
			scenarioDialog_titlePanel.add(scenarioDialog_titleLabel);
		}
		scenarioDialog_list.setSelectedIndex(0);
		scenarioDialog_list.setEnabled(false);
		start.setEnabled(false);
		start2.setEnabled(false);
		selectDialog_authorityMode.setEnabled(false);
		selectDialog_replayMode.setEnabled(false);
		selectDialog_authorityMode.setSelected(false);
		selectDialog_replayMode.setSelected(false);
		selectDialog_randomOrder.setEnabled(false);
		selectDialog_scriptUse.setEnabled(false);
		selectDialog_scriptUse.setSelected(true);
		
		themeDialog = new JDialog(this, true);
		themeDialog.setUndecorated(true);
		themeDialog.setSize(300, 150);
		themeDialog.setLocation((int)(screenSize.getWidth()/2 - themeDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - themeDialog.getHeight()/2));
		themeDialog_mainPanel = new JPanel();
		themeDialog.getContentPane().setLayout(new BorderLayout());
		themeDialog.getContentPane().add(themeDialog_mainPanel);
		themeDialog_mainPanel.setBorder(new EtchedBorder());
		themeDialog_mainPanel.setLayout(new BorderLayout());
		
		themeDialog_upPanel = new JPanel();
		themeDialog_centerPanel = new JPanel();
		themeDialog_downPanel = new JPanel();
		themeDialog_mainPanel.add(themeDialog_upPanel, BorderLayout.NORTH);
		themeDialog_mainPanel.add(themeDialog_centerPanel, BorderLayout.CENTER);
		themeDialog_mainPanel.add(themeDialog_downPanel, BorderLayout.SOUTH);
		themeDialog_pns = new JPanel[3];
		themeDialog_centerPanel.setLayout(new GridLayout(themeDialog_pns.length, 1));
		for(int i=0; i<themeDialog_pns.length; i++)
		{
			themeDialog_pns[i] = new JPanel();
			themeDialog_pns[i].setLayout(new FlowLayout());			
			themeDialog_centerPanel.add(themeDialog_pns[i]);
		}
		
		themeDialog_classic = new JCheckBox(lang.getText(Language.CLASSIC));
		themeDialog_classic.addItemListener(this);
		if(usingFont != null)
			themeDialog_classic.setFont(usingFont);
		themeDialog_pns[0].add(themeDialog_classic);
		
		String[] themeList = null;
		
		boolean themeSelection_old = false;
		if(setting.getThemeSelection_new() != null && setting.getThemeSelection_new())
		{
			LookAndFeelInfo[] themeListObj = UIManager.getInstalledLookAndFeels();
			if(themeListObj == null || themeListObj.length <= 0)
			{
				themeSelection_old = true;
			}
			else
			{
				themeList = new String[themeListObj.length];
				for(int i=0; i<themeList.length; i++)
				{
					themeList[i] = themeListObj[i].getName();
				}
				themeSelection_old = false;
			}
		}
		else
		{
			themeSelection_old = true;
		}
		if(themeSelection_old)
		{
			themeList = new String[3];
			themeList[0] = "Metal";
			themeList[1] = "Nimbus";
			themeList[2] = "OS Based";
		}
		
		
		themeDialog_combo = new JComboBox(themeList);
		if(usingFont != null)
			themeDialog_combo.setFont(usingFont);
		themeDialog_pns[1].add(themeDialog_combo);
		
		StringWithInt[] availableThemes = ThemeSet.list();
		String[] colorList = new String[availableThemes.length];
		for(int i=0; i<colorList.length; i++)
		{
			colorList[i] = availableThemes[i].getStr();
		}		
		//colorList[4] = "Normal";
		themeDialog_colorCombo = new JComboBox(colorList);
		if(usingFont != null)
			themeDialog_colorCombo.setFont(usingFont);
		themeDialog_pns[2].add(themeDialog_colorCombo);
		
		themeDialog_upPanel.setLayout(new BorderLayout());
		themeDialog_titlePanel = new JPanel();
		themeDialog_upPanel.add(themeDialog_titlePanel, BorderLayout.CENTER);
		themeDialog_titlePanel.setLayout(new FlowLayout());
		themeDialog_title = new JLabel(lang.getText(Language.THEME));
		if(usingFont != null)
			themeDialog_title.setFont(usingFont);
		themeDialog_titlePanel.add(themeDialog_title);
		themeDialog_titlePanel.setBorder(new EtchedBorder());
		themeDialog_titlePanel.addMouseListener(this);
		themeDialog_titlePanel.addMouseMotionListener(this);
		
		themeDialog_downPanel.setLayout(new FlowLayout());
		themeDialog_accept = new JButton(lang.getText(Language.ACCEPT));
		if(usingFont != null)
			themeDialog_accept.setFont(usingFont);
		themeDialog_close = new JButton(lang.getText(Language.CLOSE));
		if(usingFont != null)
			themeDialog_close.setFont(usingFont);
		themeDialog_accept.addActionListener(this);
		themeDialog_close.addActionListener(this);
		themeDialog_downPanel.add(themeDialog_accept);
		themeDialog_downPanel.add(themeDialog_close);
		
				
		takeButton.setEnabled(false);
		stop_game.setEnabled(false);
		help2.setEnabled(false);
		//title_exit.setEnabled(false);
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(false);
		}		
		System.out.print(".");
		
		finish_refresh(true);
		
		center_refresh(false);	
		thread = new Thread(this);
		if(scriptFactory == null)
		{
			scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
		}
		System.out.print(".");
		
		if(setting.getKind_ai().booleanValue())
			aiActedDialog = new AIActedDialog(setting, this);
	}
	public static void prepareFont()
	{
		boolean font_loaded = false;
		String osName = System.getProperty("os.name");
		String locale = System.getProperty("user.language");
		
		Setting sets = Setting.getNewInstance();
		InputStream infs = null;
		FileInputStream finfs = null;
		ObjectInputStream objs = null;
		if(osName.startsWith("Windows") || osName.startsWith("windows") || osName.startsWith("WINDOWS"))
		{
			try
			{
				File fontFile = new File(sets.getDefault_path() + "basic_font.ttf");
				if(fontFile.exists())
				{
					finfs = new FileInputStream(fontFile);
					infs = new BufferedInputStream(finfs);
					usingFont = Font.createFont(Font.TRUETYPE_FONT, infs);
					usingFont = usingFont.deriveFont(Font.PLAIN, default_fontSize);
					usingFont2 = usingFont.deriveFont(Font.PLAIN, default_fontSize * 2);
					usingFontB = usingFont.deriveFont(Font.BOLD, default_fontSize);
					usingFont2B = usingFont.deriveFont(Font.BOLD, default_fontSize * 2);
					font_loaded = true;
				}
				else font_loaded = false;
			}
			catch(Exception e)
			{
				if(sets != null && sets.isError_printDetail())
					e.printStackTrace();
				font_loaded = false;
			}
			finally
			{
				try
				{
					infs.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					finfs.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		String truetype = "돋움";
		try
		{
			GraphicsEnvironment gr = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] fontList = gr.getAvailableFontFamilyNames();
			for(int i=0; i<fontList.length; i++)
			{
				if(fontList[i].equals("나눔고딕") || fontList[i].equals("나눔고딕코딩") || fontList[i].equalsIgnoreCase("NanumGothic") || fontList[i].equalsIgnoreCase("NanumGothicCoding"))
				{
					truetype = fontList[i];
					break;
				}
			}
		} 
		catch (Exception e1)
		{
			truetype = "돋움";
		}
		
		if(! font_loaded)
		{
			try
			{
				finfs = new FileInputStream(sets.getDefault_path() + "setting.clst");
				objs = new ObjectInputStream(finfs);
				usingFont = (Font) objs.readObject();
				usingFont2 = usingFont.deriveFont(Font.PLAIN, usingFont.getSize() * 2);
				usingFontB = usingFont.deriveFont(Font.BOLD, usingFont.getSize());
				usingFont2B = usingFont.deriveFont(Font.BOLD, usingFont.getSize() * 2);
			} 
			catch (Exception e)
			{
				if(sets != null && sets.isError_printDetail())
					e.printStackTrace();
				font_loaded = false;
			}
			finally
			{
				try
				{
					objs.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					finfs.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		
		if(! font_loaded)
		{
			if(osName.equalsIgnoreCase("Windows Vista") || osName.equalsIgnoreCase("Windows 7")
					|| osName.equalsIgnoreCase("Windows 8")|| osName.equalsIgnoreCase("Windows 8.1"))
			{
				if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
					usingFontName = truetype;
				else
					usingFontName = "Arial";
			}
			else if(osName.startsWith("Windows") || osName.startsWith("windows") || osName.startsWith("WINDOWS"))
			{
				if(osName.endsWith("95") || osName.endsWith("98") || osName.endsWith("me") || osName.endsWith("ME") || osName.endsWith("Me") || osName.endsWith("2000"))
				{
					if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
						usingFontName = "돋움";
					else
						usingFontName = "Dialog";
				}
				else
				{
					if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
						usingFontName = truetype;
					else
						usingFontName = "Arial";
				}
				
			}
			try
			{
				usingFont = new Font(usingFontName, Font.PLAIN, default_fontSize);
				usingFontB = new Font(usingFontName, Font.BOLD, default_fontSize);
				usingFont2 = new Font(usingFontName, Font.PLAIN, default_fontSize * 2);
				usingFont2B = new Font(usingFontName, Font.BOLD, default_fontSize * 2);
				font_loaded = true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				font_loaded = false;
				usingFont = null;
			}
		}
		if(font_loaded)
		{
			try
			{
				UIManager.put("OptionPane.messageFont", new FontUIResource(usingFont));
				UIManager.put("OptionPane.font", new FontUIResource(usingFont));
				UIManager.put("OptionPane.buttonFont", new FontUIResource(usingFont));
				UIManager.put("JOptionPane.font", new FontUIResource(usingFont));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void setFontRecursively(Component comp, Font font)
	{
		try
		{
			if(font == null) return;
			comp.setFont(font);
			int max_limit = 100;
			if(comp instanceof Container)
			{
				Container cont = (Container) comp;
				int ub = cont.getComponentCount();
				for(int  j=0; j<ub; j++)
				{
					ub = cont.getComponentCount();
					if(ub > max_limit) ub = max_limit;
					setFontRecursively(cont.getComponent(j), font, max_limit);
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void setFontRecursively(Component comp, Font font, int prevent_infiniteLoop)
	{
		try
		{
			if(font == null) return;
			comp.setFont(font);
			int max_limits = prevent_infiniteLoop;
			if(comp instanceof Container)
			{
				Container cont = (Container) comp;
				int ub = cont.getComponentCount();
				for(int  j=0; j<ub; j++)
				{
					ub = cont.getComponentCount();
					if(ub > max_limits) ub = max_limits;
					max_limits--;
					if(max_limits <= 0) break;
					setFontRecursively(cont.getComponent(j), font, max_limits);					
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void open()
	{		
		TrackStorage.addTrack(this.getClass().getName(), "open() started", false);
		ProgressDialog.progress(0.30);		
		this.setVisible(true);
		for(int i=0; i<1000; i++)
			messages.append(String.valueOf(i) + "\n");
		messages.setText("");		
		notice_read_success = false;
		ProgressDialog.progress(0.39);	
		
		File additionalSetitngFile = null;
		FileInputStream fin = null;
		InputStreamReader inr = null;
		BufferedReader buf = null;
		String buf_takes = null;
		String buf_one = null;
		StringTokenizer buf_token = null;		
		try
		{
			additionalSetitngFile = new File(setting.getDefault_path() + "config.cfg");
			if(additionalSetitngFile.exists())
			{
				fin = new FileInputStream(additionalSetitngFile);
				inr = new InputStreamReader(fin);
				buf = new BufferedReader(inr);
				while(true)
				{
					buf_takes = buf.readLine();
					if(buf_takes == null) break;
					if(buf_takes.startsWith("#")) continue;
					try
					{
						buf_token = new StringTokenizer(buf_takes, "=");
						if(buf_token.countTokens() == 2)
						{
							buf_one = buf_token.nextToken().trim();
							if(buf_one.equalsIgnoreCase("datainputstream"))
							{
								buf_one = buf_token.nextToken().trim();
								din_use = Boolean.parseBoolean(buf_one);
							}
						}
					} 
					catch (Exception e)
					{
						if(setting.isError_printDetail())
						{
							e.printStackTrace();
							message(setting.getLang().getText(Language.ERROR) + " : " + e.getMessage());
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				buf.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				inr.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				fin.close();
			} 
			catch (Exception e)
			{
				
			}
		}
		
		if(! setting.isNotice_not_view())
		{
			readed_notice = getNotice("notice.txt", null);
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_utf.txt", null);
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice.txt", "EUC-KR");
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice.txt", "MS949");
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_utf.txt", "UTF-8");
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_uni.txt", null);
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_uni.txt", "UTF-8");
			}
		}
		
		ProgressDialog.progress(0.40);
					
		messageDialog.setVisible(true);		
		if(notice_read_success)
		{
			message(readed_notice);
		}
		message(lang.getText(Language.TITLE) + " " + getVersionString(true));
			
		boolean net_accepted = false;
		boolean abandoned_key = false;
		boolean net_mastered = false;
		try
		{
			net_accepted = setting.accept_net();
			abandoned_key = setting.abandoned_key();
			net_mastered = setting.accept_mastered();
			if(abandoned_key) net_accepted = false;
		} 
		catch (Exception e1)
		{
			if(setting.isError_printDetail()) e1.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e1);
		}
		ProgressDialog.progress(0.60);
		if(net_accepted || setting.accepted() || net_mastered)
		{
			OnlineContentURLList onlineConts = null, onlineConts2 = null;
			URL onlineURL = null;
			XMLDecoder oin = null;
			DataInputStream din = null;
			try
			{
				onlineURL = new URL(setting.getNotice_url() + "urls.curl");
				if(din_use)
				{
					din = new DataInputStream(onlineURL.openStream());
					oin = new XMLDecoder(din);
				}
				else
				{
					oin = new XMLDecoder(onlineURL.openStream());
				}
				onlineConts = (OnlineContentURLList) oin.readObject();
				try
				{
					oin.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					din.close();
				}
				catch(Exception e)
				{
					
				}
			} 
			catch (Exception e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
				onlineConts = null;
				try
				{
					oin.close();
				}
				catch(Exception e1)
				{
					
				}
				try
				{
					din.close();
				}
				catch(Exception e1)
				{
					
				}
			}
			try
			{
				onlineURL = new URL(setting.getNotice_url2() + "urls.curl");
				if(din_use)
				{
					din = new DataInputStream(onlineURL.openStream());
					oin = new XMLDecoder(din);
				}
				else
				{
					oin = new XMLDecoder(onlineURL.openStream());
				}
				
				onlineConts2 = (OnlineContentURLList) oin.readObject();				
				try
				{
					oin.close();
				}
				catch(Exception e2)
				{
					
				}
				try
				{
					din.close();
				}
				catch(Exception e2)
				{
					
				}
			}				
			catch (Exception e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
				onlineConts2 = null;
				try
				{
					oin.close();
				}
				catch(Exception e2)
				{
					
				}
				try
				{
					din.close();
				}
				catch(Exception e2)
				{
					
				}
			}
			try
			{
				if(onlineConts == null && onlineConts2 != null) onlineConts = onlineConts2;
				else if(onlineConts != null && onlineConts2 != null)
				{
					if(onlineConts2.getVer_main().intValue() > onlineConts.getVer_main().intValue())
					{
						onlineConts = onlineConts2;
					}
					else if(onlineConts2.getVer_main().intValue() == onlineConts.getVer_main().intValue())
					{
						if(onlineConts2.getVer_sub1().intValue() > onlineConts.getVer_sub1().intValue())
						{
							onlineConts = onlineConts2;
						}
						else if(onlineConts2.getVer_sub1().intValue() == onlineConts.getVer_sub1().intValue())
						{
							if(onlineConts.getVer_sub2().intValue() > onlineConts.getVer_sub2().intValue())
							{
								onlineConts = onlineConts2;
							}
						}
					}					
				}
			}
			catch(Exception e)
			{
				
			}
			ProgressDialog.progress(0.65);
			if(onlineConts != null)
			{
				//System.out.println(onlineConts);
				Vector<AI_Algorithm> ai_saves = new Vector<AI_Algorithm>();
				//boolean ai_sync = false;
				if(setting.getAi_algorithms() == null)
				{
					setting.setProfessional_contents_loaded(false);
					setting.setAi_algorithms(new AI_Algorithm[0]);
				}
				URL getsURL;
				boolean check_url = true;
				int ai_index = 0;
				int ai_equals = 0;
				AI_Algorithm ai_temp = null;
				while(check_url)
				{
					din = null;
					XMLDecoder dec = null;
					try
					{
						getsURL = new URL(onlineConts.getAi_contents().get(ai_index));
						if(din_use)
						{
							din = new DataInputStream(getsURL.openStream());
							dec = new XMLDecoder(din);
						}
						else
						{
							dec = new XMLDecoder(getsURL.openStream());
						}
						ai_temp = (AI_Algorithm) dec.readObject();
						ai_saves.add(ai_temp);
						//System.out.println(ai_temp);
						
						for(int i=0; i<setting.getAi_algorithms().length; i++)
						{
							if(ai_temp.getRandoms().longValue() == setting.getAi_algorithms()[i].getRandoms().longValue())
							{
								ai_equals++;
								break;
							}
						}
					} 
					catch (Exception e)
					{
						if(setting.isError_printDetail()) e.printStackTrace();
						System.out.println(setting.isError_printDetail());
						TrackStorage.addTrack(this.getClass().getName(), e);
					}						
					finally
					{
						try
						{
							dec.close();
						} 
						catch (Exception e)
						{
							
						}
						try
						{
							din.close();
						} 
						catch (Exception e)
						{
							
						}
					}
					
					ai_index++;
					if(ai_index >= setting.getAi_algorithms().length || ai_index >= onlineConts.getAi_contents().size())
					{
						check_url = false;
						break;
					}
				}
				ProgressDialog.progress(0.68);
				if(! (ai_equals >= onlineConts.getAi_contents().size()))
				{
					setting.setProfessional_contents_loaded(false);
				}
				else
				{
					setting.setProfessional_contents_loaded(true);
				}
				
				
				if(! setting.isProfessional_contents_loaded())
				{					
					for(int i=0; i<ai_saves.size(); i++)
					{
						setting.input(ai_saves.get(i));
					}
					
					setting.setProfessional_contents_loaded(true);
				}
			}
			ProgressDialog.progress(0.70);
			grade_mode = getGrade(setting);
			ver_serial.setForeground(getGradeColor());
			gradeColor = ver_serial.getForeground();
			ver_serial.setText(getGradeString(setting));
			ver_serial.setFont(getGradeFont());
			
			if(grade_mode >= 3)
			{				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_city.setVisible(true);
						selectDialog_menu_view_script.setVisible(true);
						scenarioDialog_new.setVisible(true);
					}					
				});				
			}
			else if(grade_mode >= 2)
			{				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_city.setVisible(true);
						selectDialog_menu_view_script.setVisible(true);
						scenarioDialog_new.setVisible(true);
					}					
				});
			}
			else if(grade_mode >= 1)
			{				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						selectDialog_menu_anothers_city.setVisible(true);
						selectDialog_menu_view_script.setVisible(true);
						scenarioDialog_new.setVisible(true);
					}					
				});
			}
		}
		else
		{			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					selectDialog_menu_anothers_city.setVisible(false);
					selectDialog_menu_view_script.setVisible(false);
					scenarioDialog_new.setVisible(false);
				}					
			});
		}
		
		miniSetting_useTab.setSelected(setting.isCenter_tab());
		miniSetting_difficulty.setValue(new Integer(setting.getAi_difficulty()));
		miniSetting_cardCount.setValue(new Integer(setting.getStart_givenCards()));
		miniSetting_changeCount.setValue(new Integer(setting.getChange_card_count()));
		miniSetting_multiplyCount.setValue(new Integer(setting.getMultiply_card_count()));
		
		default_scenario();
		
		ProgressDialog.close();
		if((! setting.isNotice_not_view()) && notice_read_success)
		{				
			selectDialog_noticeArea.setText(readed_notice);			
		}			
		
		messageDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + (this.getHeight())));		
		selectDialog.setVisible(true);
		if(setting.isScenario_open())
		{
			scenarioDialog.setVisible(true);
		}
		if(messageDialog.isVisible()) message_console.requestFocus();
		readed_notice = null;	
		tryUpdate(false);
		if(scriptFactory == null)
		{
			scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
		}		
		try
		{
			scriptFactory.putObject("chat", new Script_Chat(this));
		}
		catch(Exception e2)
		{
			
		}
		try
		{
			scriptFactory.putObject("loader", new Script_Load(setting, this));
		}
		catch(Exception e2)
		{
			
		}
		thread.start();	
		//this.pack();
		
	}
	public String getNotice(String fileName, String offset)
	{
		TrackStorage.addTrack(this.getClass().getName(), "getNotice(" + fileName + ", " + offset + ") started", false);
		String readed_notice = "";
		try
		{
			URL notice_url = null;
			InputStream inputStream = null;
			boolean need_retry = false;
			try
			{
				//notice_url = new URL("http://hjow.iptime.org/calc/" + fileName);
				notice_url = new URL(setting.getNotice_url() + fileName);
				inputStream = notice_url.openStream();
			} 
			catch (Exception e)
			{
				need_retry = true;
				try
				{
					inputStream.close();
				} 
				catch (Exception e1)
				{
					
				}
			}
			if(need_retry)
			{
				try
				{
					notice_url = new URL(setting.getNotice_url2() + fileName);
					inputStream = notice_url.openStream();
				} 
				catch (Exception e)
				{
					need_retry = true;
					try
					{
						inputStream.close();
					} 
					catch (Exception e1)
					{
						
					}
				}
			}
			if(need_retry)
			{
				notice_url = new URL("http://hjow.iptime.org/calc/" + fileName);
				inputStream = notice_url.openStream();
			}
			
			InputStreamReader inputStreamReader = new InputStreamReader(notice_url.openStream());
			BufferedReader notice_reader = new BufferedReader(inputStreamReader);
			String readed = "";
			StringBuffer readNotice = new StringBuffer("");
			readed_notice = "";
			int prevent_infinite = 0;
			while(prevent_infinite < 100)
			{
				readed = notice_reader.readLine();
				if(offset != null) 
				{
					readed = new String(readed.getBytes(offset));
				}
				//readed = readed.trim();
				if(prevent_infinite >= 1)
				{
					if(readed == null) break;
					//message(readed);
					readNotice.append(readed);
					readNotice.append("\n");
				}
				else
				{
					if(! readed.equals("=== 공지사항 Notice ==="))
					{
						notice_read_success = false;
						return "";
					}
				}
				prevent_infinite++;
			}
			notice_reader.close();
			inputStreamReader.close();
			inputStream.close();
			readed_notice = readNotice.toString();
			notice_read_success = true;
		}
		catch(IOException e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
			notice_read_success = false;
		}
		catch(Exception e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
			notice_read_success = false;
		}	
		return readed_notice;
	}
	public void theme_refresh()
	{
		TrackStorage.addTrack(this.getClass().getName(), "theme_refresh() started", false);
		//System.out.println("Theme Refreshs");
		if(setting.isUse_color())
		{					
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{					
					try
					{
						mainPanel.setBackground(selected_back);
						upPanel.setBackground(selected_back);
						downPanel.setBackground(selected_back);
						titlePanel.setBackground(selected_inside_back);
						title_buttonPanel.setBackground(selected_inside_back);
						title_iconPanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
							title_buttonPanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
							title_iconPanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
						titleLabel.setForeground(selected_fore);
						messages.setBackground(selected_inside_back);
						messages.setForeground(selected_fore);
						messagePanel.setBackground(selected_back);
						message_consolePanel.setBackground(selected_back);
						message_controlPanel.setBackground(selected_back);
						message_console.setBackground(selected_inside_back);
						message_console.setForeground(selected_fore);
						message_modePanel.setBackground(selected_back);
						message_mode.setBackground(selected_inside_back);
						message_mode.setForeground(selected_fore);
						message_act.setForeground(selected_fore);
						chatOk.setForeground(selected_fore);
						chatRemove.setForeground(selected_fore);
						chatSet.setForeground(selected_fore);
						chatPanel.setBackground(selected_back);
						chatMainPanel.setBackground(selected_back);					
						deckLabel1.setForeground(selected_fore);
						deckLabel2.setForeground(selected_fore);
						controlPanel.setBackground(selected_back);
						titleBar.setBackground(selected_inside_back);
						takeButton.setForeground(selected_fore);
						stop_game.setForeground(selected_fore);
						help.setForeground(selected_fore);
						help2.setForeground(selected_fore);
						if(selected_button != null)
						{
							chatOk.setBackground(selected_button);
							chatRemove.setBackground(selected_button);
							chatSet.setBackground(selected_button);
							stop_game.setBackground(selected_button);
							help.setBackground(selected_button);
							help2.setBackground(selected_button);
							takeButton.setBackground(selected_button);
							message_act.setBackground(selected_button);
						}
						if(centerSecondButtons != null || centerSecondButtons.size() >= 1)
						{
							for(int i=0; i<centerSecondButtons.size(); i++)
							{
								centerSecondButtons.get(i).setForeground(selected_fore);
								if(selected_button != null)
								{
									centerSecondButtons.get(i).setBackground(selected_button);
								}
							}
						}
						deckPanel.setBackground(selected_back);
						alivePanel.setBackground(selected_back);
						barPanel.setBackground(selected_back);
						barCenterPanel.setBackground(selected_back);
						warnDialog_centerPanel.setBackground(selected_back);
						warnDialog_downPanel.setBackground(selected_back);
						warnDialog_mainPanel.setBackground(selected_back);
						warnDialog_upPanel.setBackground(selected_back);
						warnDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							warnDialog_titlePanel.setBackground(selected_back);
						}
						warnDialog_message.setForeground(selected_fore);
						warnDialog_title.setForeground(selected_fore);
						selectDialog_noticePanel.setBackground(selected_back);
						selectDialog_noticeArea.setBackground(selected_inside_back);
						selectDialog_noticeArea.setForeground(selected_fore);
						selectDialog_noticeControlPanel.setBackground(selected_back);
						selectDialog_noticeVersionLabel.setForeground(selected_fore);
						selectDialog_miniSetting.setBackground(selected_back);
						selectDialog_miniSetting_mainPanel.setBackground(selected_back);
						for(int i=0; i<selectDialog_miniSetting_pns.length; i++)
						{
							selectDialog_miniSetting_pns[i].setBackground(selected_back);
						}
						miniSetting_needRestartLabel.setForeground(selected_fore);
						miniSetting_useTab.setForeground(selected_fore);
						miniSetting_useTab.setBackground(selected_back);
						miniSetting_difficulty.setBackground(selected_inside_back);
						miniSetting_difficulty.setForeground(selected_fore);
						miniSetting_difficultyLabel.setForeground(selected_fore);
						miniSetting_cardCount.setBackground(selected_inside_back);
						miniSetting_cardCount.setForeground(selected_fore);
						miniSetting_cardCountLabel.setForeground(selected_fore);
						miniSetting_changeCount.setBackground(selected_inside_back);
						miniSetting_changeCount.setForeground(selected_fore);
						miniSetting_changeCountLabel.setForeground(selected_fore);
						miniSetting_multiplyCountLabel.setForeground(selected_fore);
						miniSetting_multiplyCount.setForeground(selected_fore);
						miniSetting_multiplyCount.setBackground(selected_inside_back);
						selectDialog_mainPanel.setBackground(selected_back);
						selectDialog_downPanel.setBackground(selected_back);
						selectDialog_centerPanel.setBackground(unselected_back);
						selectDialog_bottomPanel.setBackground(selected_back);
						selectDialog_bottomControlPanel.setBackground(selected_back);
						realTimeField.setBackground(selected_inside_back);
						realTimeField.setForeground(selected_fore);
						selectDialog_controlPanel.setBackground(selected_back);
						selectDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							selectDialog_titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
						selectDialog_authorityMode.setBackground(selected_back);
						selectDialog_authorityMode.setForeground(selected_fore);
						selectDialog_replayMode.setBackground(selected_back);
						selectDialog_replayMode.setForeground(selected_fore);
						selectDialog_randomOrder.setBackground(selected_back);
						selectDialog_randomOrder.setForeground(selected_fore);
						selectDialog_scriptUse.setBackground(selected_back);
						selectDialog_scriptUse.setForeground(selected_fore);
						title.setForeground(selected_fore);
						for(int i=0; i<slots; i++)
						{
							selectDialog_pns[i].setBackground(selected_back);
							selectDialog_select_ai[i].setBackground(selected_back);
							selectDialog_select_player[i].setBackground(selected_back);
							selectDialog_select_none[i].setBackground(selected_back);
							selectDialog_select_ai[i].setForeground(selected_fore);
							selectDialog_select_player[i].setForeground(selected_fore);
							selectDialog_select_none[i].setForeground(selected_fore);
							selectDialog_name[i].setBackground(selected_inside_back);
							selectDialog_name[i].setForeground(selected_fore);
						}
						for(int i=0; i<selectDialog_select_modules.size(); i++)
						{
							for(int j=0; j<selectDialog_select_modules.get(i).radioButton.length; j++)
							{
								selectDialog_select_modules.get(i).radioButton[j].setBackground(selected_back);
								selectDialog_select_modules.get(i).radioButton[j].setForeground(selected_fore);
							}
						}
						selectDialog_menuBar.setBackground(selected_inside_back);
						selectDialog_menuBar.setForeground(selected_fore);
						selectDialog_menu_file.setBackground(selected_inside_back);
						selectDialog_menu_view.setBackground(selected_inside_back);
						selectDialog_menu_help.setBackground(selected_inside_back);
						selectDialog_menu_file.setForeground(selected_fore);
						selectDialog_menu_view.setForeground(selected_fore);
						selectDialog_menu_help.setForeground(selected_fore);
						selectDialog_menu_exit.setBackground(selected_back);
						selectDialog_menu_exit.setForeground(selected_fore);
						selectDialog_menu_set.setBackground(selected_back);
						selectDialog_menu_set.setForeground(selected_fore);
						selectDialog_menu_update.setBackground(selected_back);
						selectDialog_menu_update.setForeground(selected_fore);
						selectDialog_menu_uninstall.setBackground(selected_back);
						selectDialog_menu_uninstall.setForeground(selected_fore);
						selectDialog_menu_run.setBackground(selected_back);
						selectDialog_menu_run.setForeground(selected_fore);
						start2.setBackground(selected_back);
						start2.setForeground(selected_fore);
						selectDialog_menu_saveLog.setBackground(selected_back);
						selectDialog_menu_saveLog.setForeground(selected_fore);
						selectDialog_menu_help_ver.setBackground(selected_back);
						selectDialog_menu_help_ver.setForeground(selected_fore);
						selectDialog_menu_help_view.setBackground(selected_back);
						selectDialog_menu_help_view.setForeground(selected_fore);
						selectDialog_menu_view_replay.setBackground(selected_back);
						selectDialog_menu_view_replay.setForeground(selected_fore);	
						selectDialog_menu_view_rank.setBackground(selected_back);
						selectDialog_menu_view_rank.setForeground(selected_fore);
						selectDialog_menu_view_user.setBackground(selected_back);
						selectDialog_menu_view_user.setForeground(selected_fore);
						selectDialog_menu_view_checker.setBackground(selected_back);
						selectDialog_menu_view_checker.setForeground(selected_fore);
						selectDialog_menu_view_script.setBackground(selected_back);
						selectDialog_menu_view_script.setForeground(selected_fore);
						selectDialog_menu_view_scenario.setBackground(selected_back);
						selectDialog_menu_view_scenario.setForeground(selected_fore);
						selectDialog_menu_anothers.setBackground(selected_back);
						selectDialog_menu_anothers.setForeground(selected_fore);
						selectDialog_menu_anothers_oneCard.setBackground(selected_back);
						selectDialog_menu_anothers_oneCard.setForeground(selected_fore);
						selectDialog_menu_anothers_conquer.setBackground(selected_back);
						selectDialog_menu_anothers_conquer.setForeground(selected_fore);
						selectDialog_menu_anothers_math.setBackground(selected_back);
						selectDialog_menu_anothers_math.setForeground(selected_fore);
						selectDialog_menu_anothers_city.setBackground(selected_back);
						selectDialog_menu_anothers_city.setForeground(selected_fore);
						selectDialog_menu_anothers_reflex.setBackground(selected_back);
						selectDialog_menu_anothers_reflex.setForeground(selected_fore);
						selectDialog_menu_anothers_warn.setBackground(unselected_back);
						selectDialog_menu_anothers_warn.setForeground(selected_fore);
						selectDialog_tab.setBackground(selected_back);
						selectDialog_tab.setForeground(selected_fore);
						selectDialog_multiPanel.setBackground(selected_back);
						selectDialog_multi_playerPanel.setBackground(selected_back);
						selectDialog_multi_controlPanel.setBackground(selected_back);
						for(int i=0; i<selectDialog_multi_controlPns.length; i++)
						{
							selectDialog_multi_controlPns[i].setBackground(selected_back);
						}
						selectDialog_multi_nowip_label.setForeground(selected_fore);
						selectDialog_multi_ip_label.setForeground(selected_fore);
						selectDialog_multi_port_label.setForeground(selected_fore);
						selectDialog_multi_ip.setForeground(selected_fore);
						selectDialog_multi_port.setForeground(selected_fore);
						selectDialog_multi_ip.setBackground(selected_inside_back);
						selectDialog_multi_port.setBackground(selected_inside_back);
						start.setForeground(selected_fore);
						exit.setForeground(selected_fore);
						selectDialog_noticeOK.setForeground(selected_fore);
						selectDialog_noticeRetry.setForeground(selected_fore);
						miniSetting_accept.setForeground(selected_fore);
						miniSetting_changeTheme.setForeground(selected_fore);
						if(title_exit != null)
							title_exit.setForeground(selected_fore);
						if(selected_button != null)
						{
							start.setBackground(selected_button);
							exit.setBackground(selected_button);
							selectDialog_noticeOK.setBackground(selected_button);
							selectDialog_noticeRetry.setBackground(selected_button);
							miniSetting_accept.setBackground(selected_button);
							miniSetting_changeTheme.setBackground(selected_button);
							if(title_exit != null)
								title_exit.setBackground(selected_button);
						}
						
						scriptDialog_mainPanel.setBackground(selected_back);
						scriptDialog_centerPanel.setBackground(selected_back);
						scriptDialog_downPanel.setBackground(selected_back);
						scriptDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							scriptDialog_titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
						scriptDialog_text.setBackground(selected_inside_back);
						scriptDialog_text.setForeground(selected_fore);
						scriptDialog_close.setForeground(selected_fore);
						scriptDialog_run.setForeground(selected_fore);
						scriptDialog_title.setForeground(selected_fore);
						
						if(selected_button != null)
						{
							scriptDialog_close.setBackground(selected_button);
							scriptDialog_run.setBackground(selected_button);
							endDialog_end.setBackground(selected_button);
						}
						endDialog_end.setForeground(selected_fore);
						endDialog_mainPanel.setBackground(selected_back);
						
						scenarioDialog_mainPanel.setBackground(selected_back);
						scenarioDialog_centerPanel.setBackground(selected_back);
						scenarioDialog_upPanel.setBackground(selected_back);
						scenarioDialog_downPanel.setBackground(selected_back);
						scenarioDialog_list.setBackground(selected_inside_back);
						scenarioDialog_list.setForeground(selected_fore);
						scenarioDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							scenarioDialog_titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
						scenarioDialog_titleLabel.setForeground(selected_fore);
						scenarioDialog_buttonPanel.setBackground(selected_back);
						scenarioDialog_close.setForeground(selected_fore);
						scenarioDialog_new.setForeground(selected_fore);
						scenarioDialog_descriptionPanel.setBackground(selected_back);
						scenarioDialog_descriptionPanel.setForeground(selected_fore);
						
						if(selected_button != null)
						{
							scenarioDialog_close.setBackground(selected_button);
							scenarioDialog_new.setBackground(selected_button);
						}
						
						finishDialog_mainPanel.setBackground(selected_back);
						finishDialog_downPanel.setBackground(selected_back);
						finishDialog_centerPanel.setBackground(selected_back);
						finishDialog_bottomPanel.setBackground(selected_back);
						finishDialog_codePanel.setBackground(selected_back);
						finishDialog_code.setBackground(selected_inside_back);
						finishDialog_code.setForeground(selected_fore);
						finishDialog_codeLabel.setForeground(selected_fore);
						finishDialog_upPanel.setBackground(selected_inside_back);
						finishDialog_label.setForeground(selected_fore);
						finishDialog_close.setForeground(selected_fore);
						finishDialog_web.setForeground(selected_fore);
						finishDialog_copy.setForeground(selected_fore);
						finishDialog_replay.setForeground(selected_fore);
						if(selected_button != null)
						{
							finishDialog_close.setBackground(selected_button);
							finishDialog_copy.setBackground(selected_button);
							finishDialog_replay.setBackground(selected_button);
							finishDialog_web.setBackground(selected_button);
						}
						for(int i=0; i<slots; i++)
						{
							if(finishDialog_pns[i] != null) finishDialog_pns[i].setBackground(selected_back);
							if(finishDialog_labels[i] != null) finishDialog_labels[i].setForeground(selected_fore);
							if(finishDialog_labels2[i] != null) finishDialog_labels2[i].setForeground(selected_fore);
							if(finishDialog_ranks[i] != null) 
							{
								finishDialog_ranks[i].setBackground(selected_inside_back);
								finishDialog_ranks[i].setForeground(selected_fore);
							}
							if(finishDialog_fields[i] != null) 
							{
								finishDialog_fields[i].setBackground(selected_inside_back);
								finishDialog_fields[i].setForeground(selected_fore);
							}
							if(finishDialog_fields2[i] != null) 
							{
								finishDialog_fields2[i].setBackground(selected_inside_back);
								finishDialog_fields2[i].setForeground(selected_fore);
							}
						}
						serialDialog_mainPanel.setBackground(selected_back);
						serialDialog_centerPanel.setBackground(selected_back);
						serialDialog_contentPanel.setBackground(selected_back);
						serialDialog_downPanel.setBackground(selected_back);
						serialDialog_upPanel.setBackground(selected_back);
						serialDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							serialDialog_titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
						serialDialog_title.setForeground(selected_fore);
						serialDialog_message.setForeground(selected_fore);
						serialDialog_message.setBackground(unselected_inside_back);
						serialDialog_close.setForeground(selected_fore);
						serialDialog_ok.setForeground(selected_fore);
						if(selected_button != null)
						{
							serialDialog_ok.setBackground(selected_button);
							serialDialog_close.setBackground(selected_button);
						}
						for(int i=0; i<serialDialog_keys.length; i++)
						{
							serialDialog_keys[i].setBackground(selected_inside_back);
							serialDialog_keys[i].setForeground(selected_fore);
							if(i < serialDialog_keys.length - 1) serialDialog_bars[i].setForeground(selected_fore);
						}
						
						themeDialog_mainPanel.setBackground(selected_back);
						themeDialog_centerPanel.setBackground(selected_back);
						themeDialog_downPanel.setBackground(selected_back);
						for(int i=0; i<themeDialog_pns.length; i++)
						{
							themeDialog_pns[i].setBackground(selected_back);
						}
						themeDialog_upPanel.setBackground(selected_back);
						themeDialog_title.setForeground(selected_fore);
						themeDialog_classic.setForeground(selected_fore);
						themeDialog_classic.setBackground(selected_back);
						themeDialog_combo.setForeground(selected_fore);
						themeDialog_colorCombo.setForeground(selected_fore);
						themeDialog_combo.setBackground(selected_inside_back);
						themeDialog_colorCombo.setBackground(selected_inside_back);
						themeDialog_accept.setForeground(selected_fore);
						themeDialog_close.setForeground(selected_fore);					
						if(selected_button != null)
						{
							themeDialog_accept.setBackground(selected_button);
							themeDialog_close.setBackground(selected_button);
						}
						themeDialog_titlePanel.setBackground(selected_inside_back);
						if(color_reverse)
						{
							themeDialog_titlePanel.setBackground(new Color(selected_back.getRed() - 10, selected_back.getGreen() - 10, selected_back.getBlue() - 10));
						}
					} 
					catch (Exception e)
					{
						if(setting.isError_printDetail())
							e.printStackTrace();
					}
				}
			}
			);
			TrackStorage.addTrack(this.getClass().getName(), "theme_refresh(), almost finished", false);
			if(setting.isCenter_tab())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{				
						try
						{
							centerBaseTab.setBackground(selected_back);						
							centerBaseTab.setForeground(selected_fore);
							centerSecondLeft.setBackground(selected_back);
							centerSecondListLabel.setForeground(selected_fore);
							centerSecondList.setBackground(selected_inside_back);
							centerSecondList.setForeground(selected_fore);
							if(centerSecondPns != null)
							{
								for(int i=0; i<centerSecondPns.length; i++)
								{
									if(turn == i)
									{
										centerSecondPns[i].setBackground(selected_back);
										centerSecondLabels[i].setForeground(selected_fore);
										centerSecondPoint[i].setBackground(selected_inside_back);
										centerSecondCards[i].setBackground(selected_inside_back);
										centerSecondCounts[i].setBackground(selected_inside_back);
										centerSecondPoint[i].setForeground(selected_fore);
										centerSecondCards[i].setForeground(selected_fore);
										centerSecondCounts[i].setForeground(selected_fore);
										centerSecondPointLabel[i].setForeground(selected_fore);
										centerSecondCountsLabel[i].setForeground(selected_fore);
									}
									else 
									{
										centerSecondPns[i].setBackground(unselected_back);
										centerSecondLabels[i].setForeground(unselected_fore);
										centerSecondPoint[i].setBackground(unselected_inside_back);
										centerSecondCards[i].setBackground(unselected_inside_back);
										centerSecondCounts[i].setBackground(unselected_inside_back);
										centerSecondPoint[i].setForeground(unselected_fore);
										centerSecondCards[i].setForeground(unselected_fore);
										centerSecondCounts[i].setForeground(unselected_fore);
										centerSecondPointLabel[i].setForeground(unselected_fore);
										centerSecondCountsLabel[i].setForeground(unselected_fore);
									}
								}
							}
						} 
						catch (Exception e)
						{
							if(setting.isError_printDetail())
								e.printStackTrace();
						}
					}
				}
				);
			}			
			TrackStorage.addTrack(this.getClass().getName(), "theme_refresh(), second tab finished", false);
			for(int i=0; i<blocks.size(); i++)
			{
				blocks.get(i).theme_refresh();
			}
			TrackStorage.addTrack(this.getClass().getName(), "theme_refresh(), All finished", false);
		}
	}
	public void realTime()
	{
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader bufferedReader = null;
		String readed = "1";	
		boolean need_retry = false;
		try
		{
			try
			{
				inputStream = new URL(setting.getNotice_url() + "notice_realtime.txt").openStream();
			} 
			catch (Exception e)
			{
				need_retry = true;
				try
				{
					inputStream.close();
				} 
				catch (Exception e1)
				{
					
				}
			}
			if(need_retry)
			{
				inputStream = new URL(setting.getNotice_url2() + "notice_realtime.txt").openStream();
			}
			inputReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputReader);
			realTimeStore.clear();
			while(readed != null)
			{
				readed = bufferedReader.readLine();
				if(readed == null) break;
				if(readed.equals("")) break;
				realTimeStore.add(readed);
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				if(bufferedReader != null) bufferedReader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(inputReader != null) inputReader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				if(inputStream != null) inputStream.close();
			}
			catch(Exception e)
			{
				
			}
			if(realTimeIndex >= realTimeStore.size())
			{
				realTimeIndex = 0;
			}
			if(realTimeIndex < realTimeStore.size())
			{
				SwingUtilities.invokeLater(new Runnable()
				{				
					@Override
					public void run()
					{
						try
						{
							realTimeField.setText(realTimeStore.get(realTimeIndex));
						} 
						catch (Exception e)
						{
							
						}
					}
				});
				realTimeIndex++;
			}
		}
	}
	public synchronized void center_refresh(boolean open)
	{
		TrackStorage.addTrack(this.getClass().getName(), "center_refresh() started", false);
		this.setVisible(false);	
		
		
		
		for(int i=0; i<listener_added.size(); i++)
		{
			removeActionListenerOnThis(listener_added.get(i));
		}
		listener_added.clear();
		centerPanel.removeAll();
		centerPanel.setLayout(new GridLayout(1, blocks.size()));
		for(int i=0; i<blocks.size(); i++)
		{
			centerPanel.add(blocks.get(i));
			addActionListenerOnThis(blocks.get(i).getPay());
			//listener_added.add(blocks.get(i).pay);
			//blocks.get(i).theme_refresh();
		}		
		centerScroll.removeAll();
		centerScroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		centerBasePanel.removeAll();
		
		if(setting.isAuto_scrollBar())
		{
			int scrollCount = slots * 300;
			if(this.getWidth() > scrollCount) setting.setScrollBar(false);
			else setting.setScrollBar(true);
		}
		
		if(setting.isScrollBar())
		{
			centerBasePanel.add(centerScroll);
		}
		else
		{
			centerBasePanel.add(centerPanel);
		}
		if(multiplay)
		{
			selectDialog_multi_playerPanel.removeAll();
			
			selectDialog_multi_playerPanel.setLayout(new GridLayout());
		}
		
		if(setting.isCenter_tab())
		{			
			centerSecondTab.removeAll();			
			centerSecondTab.setLayout(new BorderLayout());
			if(centerSecondLeft != null) centerSecondLeft.removeAll(); 
			if(centerSecondList != null) centerSecondList.removeAll();
			if(centerSecondListScroll != null) centerSecondListScroll.removeAll();
			centerSecondLeft = new JPanel();			
			centerSecondTab.add(centerSecondLeft, BorderLayout.WEST);
			centerSecondListLabel = new JLabel(lang.getText(Language.NOW_PLAYER_CARD));
			if(usingFont != null)
				centerSecondListLabel.setFont(usingFont);
			centerSecondList = new JList();
			if(usingFont != null)
				centerSecondList.setFont(usingFont);
			centerSecondListScroll = new JScrollPane(centerSecondList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			centerSecondLeft.setLayout(new BorderLayout());
			centerSecondLeft.add(centerSecondListScroll, BorderLayout.CENTER);
			centerSecondLeft.add(centerSecondListLabel, BorderLayout.NORTH);
			centerSecondList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			if(centerSecondCenter != null) centerSecondCenter.removeAll();
			centerSecondCenter = new JPanel();
			if(centerSecondScroll != null) centerSecondScroll.removeAll();
			centerSecondScroll = new JScrollPane(centerSecondCenter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			centerSecondTab.add(centerSecondScroll, BorderLayout.CENTER);	
			centerSecondPns = new JPanel[blocks.size()];
			centerSecondLabels = new JLabel[centerSecondPns.length];
			centerSecondPointLabel = new JLabel[centerSecondPns.length];
			centerSecondCountsLabel = new JLabel[centerSecondPns.length];
			centerSecondPoint = new JTextField[centerSecondPns.length];
			centerSecondCards = new JTextField[centerSecondPns.length];
			centerSecondCounts = new JTextField[centerSecondPns.length];
			centerSecondCenter.setLayout(new GridLayout(centerSecondPns.length, 1));
			centerSecondPay = new JButton[centerSecondPns.length];
			for(int i=0; i<centerSecondButtons.size(); i++)
			{
				centerSecondButtons.get(i).removeActionListener(this);
			}				
			centerSecondButtons.clear();			
			for(int i=0; i<centerSecondPns.length; i++)
			{
				centerSecondPns[i] = new JPanel();
				
				
				centerSecondPns[i].setBorder(new EtchedBorder());
				centerSecondPns[i].setLayout(new FlowLayout());
				centerSecondLabels[i] = new JLabel(blocks.get(i).getName());
				centerSecondPointLabel[i] = new JLabel(lang.getText(Language.POINT));
				centerSecondCountsLabel[i] = new JLabel(lang.getText(Language.CARD) + " " + lang.getText(Language.COUNT));
				if(usingFont != null)
				{
					centerSecondLabels[i].setFont(usingFont);
					centerSecondPointLabel[i].setFont(usingFont);
					centerSecondCountsLabel[i].setFont(usingFont);
				}
				centerSecondPoint[i] = new JTextField(15);
				centerSecondCards[i] = new JTextField(5);
				centerSecondCounts[i] = new JTextField(3);
				if(usingFont != null)
				{
					centerSecondPoint[i].setFont(usingFont);
					centerSecondCards[i].setFont(usingFont);
					centerSecondCounts[i].setFont(usingFont);
				}
				centerSecondPay[i] = new JButton(lang.getText(Language.PAY));
				if(usingFont != null)
					centerSecondPay[i].setFont(usingFont);
				centerSecondPay[i].addActionListener(this);
				centerSecondButtons.add(centerSecondPay[i]);
				centerSecondPoint[i].setEditable(false);
				centerSecondCards[i].setEditable(false);
				centerSecondCounts[i].setEditable(false);
				centerSecondPns[i].add(centerSecondLabels[i]);				
				centerSecondPns[i].add(centerSecondCards[i]);
				centerSecondPns[i].add(centerSecondPointLabel[i]);
				centerSecondPns[i].add(centerSecondPoint[i]);
				centerSecondPns[i].add(centerSecondCountsLabel[i]);
				centerSecondPns[i].add(centerSecondCounts[i]);
				centerSecondPns[i].add(centerSecondPay[i]);
				centerSecondCenter.add(centerSecondPns[i]);
			}
			
		}	
		
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).theme_refresh();
			blocks.get(i).refresh();
		}
		
		theme_refresh();
		if(open) this.setVisible(true);
	}
	public synchronized void deck_refresh()
	{
		TrackStorage.addTrack(this.getClass().getName(), "deck_refresh() started", false);
		if(deck.size() == 0) deckLabel2.setText(lang.getText(Language.DECK_LABEL2));
		else if(deck.size() == 1) deckLabel2.setText(lang.getText(Language.DECK_LABEL3));
		else deckLabel2.setText(lang.getText(Language.ARE) + " " + deck.size() + lang.getText(Language.DECK_LABEL4));
		takeButton.setEnabled(false);
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).isAi() == false && blocks.get(i).isTurn() == true)
			{
				takeButton.setEnabled(true);
				break;
			}
		}
	}
	public synchronized void finish_refresh(boolean isFirst)
	{
		TrackStorage.addTrack(this.getClass().getName(), "finish_refresh(" + isFirst + ") started", false);
		finishDialog_centerPanel.removeAll();
		finishDialog_pns = new JPanel[fin_slots];
		finishDialog_ranks = new JTextField[fin_slots];
		finishDialog_labels = new JLabel[fin_slots];
		finishDialog_fields = new JTextField[fin_slots];
		finishDialog_labels2 = new JLabel[fin_slots];
		finishDialog_fields2 = new JTextField[fin_slots];
		finishDialog_centerPanel.setLayout(new GridLayout(fin_slots, 1));
		for(int i=0; i<fin_slots; i++)
		{
			finishDialog_pns[i] = new JPanel();
			finishDialog_pns[i].setLayout(new FlowLayout());
			//finishDialog_pns[i].setBorder(new EtchedBorder());
			finishDialog_centerPanel.add(finishDialog_pns[i]);
			finishDialog_labels[i] = new JLabel();
			if(usingFont != null)
				finishDialog_labels[i].setFont(usingFont);
			finishDialog_labels2[i] = new JLabel(lang.getText(Language.LEFT) + " " + lang.getText(Language.CARD) + " " + lang.getText(Language.COUNT));
			if(usingFont != null)
				finishDialog_labels2[i].setFont(usingFont);
			if(i < blocks.size()) finishDialog_labels[i].setText(blocks.get(i).getName() + lang.getText(Language.WHOS) + " " + lang.getText(Language.POINT));
			finishDialog_fields[i] = new JTextField(15);
			if(usingFont != null)
				finishDialog_fields[i].setFont(usingFont);
			finishDialog_fields[i].setBorder(new EtchedBorder());
			finishDialog_fields[i].setEditable(false);	
			finishDialog_fields2[i] = new JTextField(3);
			finishDialog_fields2[i] = new JTextField(15);
			//finishDialog_fields2[i].setBorder(new EtchedBorder());
			if(i < blocks.size()) finishDialog_fields2[i].setText(String.valueOf(blocks.get(i).getOwns().size()));
			finishDialog_fields2[i].setEditable(false);	
			finishDialog_ranks[i] = new JTextField(3);
			if(usingFont != null)
				finishDialog_ranks[i].setFont(usingFont);
			//finishDialog_ranks[i].setBorder(new EtchedBorder());
			finishDialog_ranks[i].setEditable(false);
			finishDialog_pns[i].add(finishDialog_ranks[i]);
			finishDialog_pns[i].add(finishDialog_labels[i]);
			finishDialog_pns[i].add(finishDialog_fields[i]);
			finishDialog_pns[i].add(finishDialog_labels2[i]);
			finishDialog_pns[i].add(finishDialog_fields2[i]);
			finishDialog_centerPanel.add(finishDialog_pns[i]);
		}
		for(int i=0; i<centerSecondButtons.size(); i++)
		{
			centerSecondButtons.get(i).setEnabled(false);
		}
		finishLabelText = lang.getText(Language.RESULT) + " : ";
		int haveFewCard = 0;
		int minCards = blocks.get(0).getOwns().size();
		for(int i=0; i<blocks.size(); i++)
		{
			if(minCards > blocks.get(i).getOwns().size())
			{
				haveFewCard = i;
				minCards = blocks.get(i).getOwns().size();
			}
		}
		calculate(haveFewCard);
		if(! isFirst) message(lang.getText(Language.BONUS_TARGET) + " : " + blocks.get(haveFewCard).getName());
		winner = 0;
		int[] rankList = new int[blocks.size()];
		int rank_temp = 0;
		BigInteger maxPoint = blocks.get(0).getPoint();
		int not_ai = 0;
		int not_ai_players = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			rankList[i] = i;
			//if(maxPoint < blocks.get(i).getPoint())
			if(maxPoint.compareTo(blocks.get(i).getPoint()) < 0)
			{
				maxPoint = blocks.get(i).getPoint();
				winner = i;
			}
			if(! blocks.get(i).isAi())
			{
				not_ai = i;
				not_ai_players++;
			}
		}
		for(int i=1; i<blocks.size(); i++)
		{
			for(int j=0; j<i; j++)
			{
				//if(blocks.get(rankList[i]).getPoint() > blocks.get(rankList[j]).getPoint())
				if(blocks.get(rankList[i]).getPoint().compareTo(blocks.get(rankList[j]).getPoint()) > 0)
				{
					rank_temp = rankList[j];
					rankList[j] = rankList[i];
					rankList[i] = rank_temp;
				}
			}
		}
		for(int i=0; i<rankList.length; i++)
		{
			finishDialog_ranks[rankList[i]].setText(String.valueOf((i+1)));
		}
		
		boolean event_acceptable = false;
		boolean dateCheck = false;
		ExtendedScenario extsev = null;
		
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			if(setting.getScenarios().get(scenario_mode) instanceof ExtendedScenario)
			{
				//System.out.println("~~");				
				extsev = (ExtendedScenario) setting.getScenarios().get(scenario_mode);
				//System.out.println(exts.getWeb_url());
				if(extsev.getWeb_url() != null)
				{
					try
					{
						scenario_web = extsev.getWeb_url();
						int year, month, day, hour, minute, second;							
						if(extsev.getEvent_deadline() != null)
						{
							year = Calendar.getInstance().get(Calendar.YEAR);
							month = Calendar.getInstance().get(Calendar.MONTH) + 1;
							day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
							hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
							minute = Calendar.getInstance().get(Calendar.MINUTE);
							second = Calendar.getInstance().get(Calendar.SECOND);
							
							/*
							System.out.println("!!!");
							System.out.println(exts.getEvent_deadline().getYear() + ", " + exts.getEvent_deadline().getMonth() 
									+ ", " + exts.getEvent_deadline().getDay() + ", " + exts.getEvent_deadline().getHour() 
									+ ", " + exts.getEvent_deadline().getMinute() + ", " + exts.getEvent_deadline().getSecond());
							System.out.println(year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second);							
							System.out.println(exts.getEvent_onlyWin().booleanValue());
							*/
							
							
							if(extsev.getEvent_deadline().getYear() > year)
							{
								dateCheck = true;
							}
							else if(extsev.getEvent_deadline().getYear() == year)
							{
								if(extsev.getEvent_deadline().getMonth() > month)
								{
									dateCheck = true;
								}
								else if(extsev.getEvent_deadline().getMonth() == month)
								{
									if(extsev.getEvent_deadline().getDay() > day)
									{
										dateCheck = true;
									}
									else if(extsev.getEvent_deadline().getDay() == day)
									{
										if(extsev.getEvent_deadline().getHour() > hour)
										{
											dateCheck = true;
										}
										else if(extsev.getEvent_deadline().getHour() == hour)
										{
											if(extsev.getEvent_deadline().getMinute() > minute)
											{
												dateCheck = true;
											}
											else if(extsev.getEvent_deadline().getMinute() == minute)
											{
												if(extsev.getEvent_deadline().getSecond() >= second)
												{
													dateCheck = true;
												}
											}
										}
									}
								}
							}
							
							if(dateCheck)
							{
								
								if(extsev.getEvent_onlyWin() != null)
								{
									//System.out.println("set : " + (extsev.getEvent_onlyWin()));
									//System.out.println("Win : " + (not_ai == winner));
									if(extsev.getEvent_onlyWin().booleanValue() && not_ai == winner)
									{
										//System.out.println("!!!!");
										finishDialog_web.setVisible(Desktop.isDesktopSupported());
										finishDialog_web.setEnabled(true);
										event_acceptable = true;
									}
									else if(! extsev.getEvent_onlyWin().booleanValue())
									{
										//System.out.println("!!!");
										finishDialog_web.setVisible(Desktop.isDesktopSupported());
										finishDialog_web.setEnabled(true);
										event_acceptable = true;
									}
									else
									{
										finishDialog_web.setEnabled(false);
										event_acceptable = false;
									}
								}
								else
								{
									//System.out.println("!!");
									finishDialog_web.setVisible(Desktop.isDesktopSupported());
									finishDialog_web.setEnabled(true);
									event_acceptable = true;
								}
							}
							else
							{
								finishDialog_web.setEnabled(false);
								event_acceptable = false;
							}
						}
					} 
					catch (Exception e)
					{
						
					}
				}
			}
		}
		
		//finishDialog_code.setText(lang.getText(Language.AUTHORITY));
		if((authority_mode && not_ai_players == 1) && (not_ai == winner || (not_ai != winner && event_acceptable)))
		{
			StringBuffer auth_code = new StringBuffer("");
			int code_size = 17;
			if(event_acceptable) code_size = 19;
			String[] auth_codes = new String[code_size];
			auth_codes[0] = String.valueOf(blocks.get(winner).getPoint());
			auth_codes[1] = new String(blocks.get(not_ai).getName());
			auth_codes[2] = String.valueOf(blocks.size());
			auth_codes[3] = String.valueOf(blocks.get(not_ai).getOwns().size());
			auth_codes[4] = String.valueOf(version_main);
			auth_codes[5] = String.valueOf(version_sub_1);
			auth_codes[6] = String.valueOf(version_sub_2);
			BigInteger secret_code = new BigInteger("0");
			BigInteger secret_nameCode = new BigInteger("0");
			secret_code = secret_code.add(new BigInteger(String.valueOf((version_main * 100) + (version_sub_1 * 10) + version_sub_2)));
			secret_code = secret_code.multiply(new BigInteger(String.valueOf(blocks.get(not_ai).getOwns().size())));
			secret_code = secret_code.add(blocks.get(winner).getPoint());
			// authority_code used
			long authority_code = getAuthorizedCode(1937283 + 1001008);
			secret_code = secret_code.add(Lint.big(authority_code).add(new BigInteger(String.valueOf(blocks.size() * ((version_main * 100) + (version_sub_1 * 10) + version_sub_2)))));
									
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
			auth_codes[14] = "calc";
			auth_codes[15] = "Calc";
			
			String scenarioName = "";
			String scenarioCode = "";
			
			
			if(scenario_mode == 0)
			{
				
			}
			else if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
			{
				scenarioName = setting.getScenarios().get(scenario_mode).getName();
				scenarioCode = String.valueOf(setting.getScenarios().get(scenario_mode).getAuthorize_code().longValue());
				auth_codes[14] = auth_codes[14] + " (" + scenarioName + "::" + scenarioCode + ")";	
				
			}			
			
			secret_nameCode = Lint.copy(secret_code);
			secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf((int) Math.round((double) RunManager.getNameCode(blocks.get(not_ai).getName()) / 100.0) + 5)));
			secret_code = secret_code.add(new BigInteger(String.valueOf((blocks.get(not_ai).getOwns().size() + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec))));
			auth_codes[7] = String.valueOf(secret_code);
			secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
			auth_codes[16] = String.valueOf(secret_nameCode.toString());
			
			if(event_acceptable)
			{
				try
				{
					BigInteger secret_scenarioCode = Lint.copy(secret_nameCode);
					auth_codes[17] = extsev.getWeb_url();					
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_year));
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_month));
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_day));
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_hour));
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_month));
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_sec));
					
					if(winner == not_ai)
					{
						secret_scenarioCode = secret_scenarioCode.multiply(Lint.big(2));
						auth_codes[17] = auth_codes[17] + ",win";
					}
					else
					{
						auth_codes[17] = auth_codes[17] + ",lose";
					}
					secret_scenarioCode = secret_scenarioCode.add(Lint.big(auth_codes[17].toCharArray().length));
					auth_codes[18] = secret_scenarioCode.toString();
				} 
				catch (Exception e)
				{
					
				}
			}	
			
			for(int i=0; i<auth_codes.length; i++)
			{
				auth_code.append(auth_codes[i]);
				if(i < auth_codes.length - 1) auth_code.append("||");
			}			
			finishDialog_code.setVisible(true);
			finishDialog_code.setText(auth_code.toString());
			finishDialog_copy.setEnabled(true);
			
			if(dateCheck && (! event_acceptable))
			{
				finishDialog_code.setText(lang.getText(Language.DESCRIPTIONS + 21));
				finishDialog_copy.setEnabled(false);
			}
		}
		else if(authority_mode && not_ai_players == 1)
		{
			finishDialog_code.setText(lang.getText(Language.DESCRIPTIONS + 21));
			finishDialog_copy.setEnabled(false);
		}
		else
		{
			finishDialog_code.setText(lang.getText(Language.DESCRIPTIONS + 18));
			finishDialog_copy.setEnabled(false);
		}
		//else finishDialog_code.setVisible(false);
		finishLabelText = finishLabelText + blocks.get(winner).getName() + " " + lang.getText(Language.WON);
		if(finishDialog_label != null) finishDialog_label.setText(finishLabelText);	
		
		notice_read_count = 0;
		selectDialog_noticeRetry.setEnabled(true);
		
		theme_refresh();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{								
				finish_theme_refresh(winner);				
			}			
		});		
	}
	
	public static Color getGradeColor()
	{
		Color base_color;
		int r, g, b;
		switch(calc_grade)
		{
			case 3:
				base_color = Color.CYAN;
				r = (int) ((double) base_color.getRed() - (((double) base_color.getRed()) * 0.25));
				g = (int) ((double) base_color.getGreen() - (((double) base_color.getGreen()) * 0.25));
				b = (int) ((double) base_color.getBlue() - (((double) base_color.getBlue()) * 0.25));
				break;
			case 2:
				base_color = Color.YELLOW;
				r = (int) (base_color.getRed());
				g = (int) (base_color.getGreen());
				b = (int) (base_color.getBlue());
				break;
			case 1:
				base_color = Color.RED;
				r = (int) (base_color.getRed());
				g = (int) (base_color.getGreen());
				b = (int) (base_color.getBlue());
				break;
			default:
				base_color = Color.WHITE;
				r = (int) (base_color.getRed());
				g = (int) (base_color.getGreen());
				b = (int) (base_color.getBlue());
		}
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		return new Color(r, g, b);
	}
	public static String getGradeString(Setting sets)
	{
		if(calc_grade <= -1) 
			return calc_grade_str;
		switch(calc_grade)
		{
			case 3:
				return sets.getLang().getText(Language.MASTER);
			case 2:
				return sets.getLang().getText(Language.ULTIMATE);
			case 1:
				return sets.getLang().getText(Language.PROFESSIONAL);
			default:
				return sets.getLang().getText(Language.BASIC_EDITION);
		}
	}
	public static Font getGradeFont()
	{
		Font result;
		String name;
		int type = Font.PLAIN, size = 14;
		if(usingFontName != null)
			prepareFont();
		if(usingFontName != null)
			name = usingFontName;
		else
			name = "dialog";
		switch(calc_grade)
		{
			case 3:
				type = Font.BOLD;
				break;
			case 2:
				type = Font.BOLD;
				break;
			case 1:
				type = Font.BOLD;
				break;
			case 0:
				type = Font.PLAIN;
				break;
		}
		result = new Font(name, type, size);
		return result;
	}
	public static int getGrade(Setting sets)
	{
		calc_grade = 0;
		try
		{
			if(sets.accept_mastered() && (! sets.abandoned_key()))
			{
				calc_grade = 3;
				return 3;
			}
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			if(sets.accept_net() && (! sets.abandoned_key()))
			{
				calc_grade = 2;
				return 2;
			}
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			if(sets.accepted())
			{
				calc_grade = 1;
				return 1;
			}
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			Key key = sets.getKey();
			String[] blocks = new String[key.getChars().length - 1];
			char[] keyChar = new char[5];
			long sum = 0;
			for(int i=0; i<key.getChars().length - 1; i++)
			{				
				blocks[i] = new String(key.getChars()[i].blocks);
				for(int j=0; j<key.getChars().length; j++)
				{
					for(int k=0; k<key.getChars()[j].getBlocks().length; k++)
					{
						sum = sum + (int) key.getChars()[j].getBlocks()[k];
					}
				}
			}
			for(int i=0; i<keyChar.length; i++)
			{
				switch((int)(sum % (i + 5)))
				{
					case 0:
						keyChar[i] = 'A';
						break;
					case 1:
						keyChar[i] = 'B';
						break;
					case 2:
						keyChar[i] = 'C';
						break;
					case 3:
						keyChar[i] = 'D';
						break;
					case 4:
						keyChar[i] = 'E';
						break;
					case 5:
						keyChar[i] = 'F';
						break;
					case 6:
						keyChar[i] = 'G';
						break;
					case 7:
						keyChar[i] = 'H';
						break;
					case 8:
						keyChar[i] = 'I';
						break;
					case 9:
						keyChar[i] = 'J';
						break;
					case 10:
						keyChar[i] = 'K';
						break;
					default:
						keyChar[i] = 'Z';
				}
			}	
			String keyStr = new String(keyChar);
			if(String.valueOf(key.getChars()[key.getChars().length - 1].blocks).equalsIgnoreCase(keyStr))
			{				
				calc_grade_str = "";
				for(int i=0; i<blocks.length; i++)
				{
					calc_grade_str = calc_grade_str + blocks[i];
				}
				if(calc_grade <= 0)
					calc_grade = -1;
				return -1;
			}
		}
		catch (Exception e)
		{
			
		}
		calc_grade = 0;
		return 0;
	}
	public static String getVersionString()
	{		
		return getVersionString(false);
	}
	public static String getVersionString(boolean detailed)
	{
		if(detailed)
		{
			String longValue = "";
			int howMany0 = 0;
			long nightly = version_nightly;
			while(nightly >= 10)
			{				
				howMany0++;
				nightly = nightly / 10;
			}
			howMany0 = 4 - howMany0;
			if(howMany0 < 0) howMany0 = 0;
			
			for(int i=0; i<howMany0; i++)
			{
				longValue = longValue + "0";
			}			
			longValue = longValue + String.valueOf(version_nightly);
			
			
			String results = String.format("v%d.%d%d  n", version_main, version_sub_1, version_sub_2) 
					+ longValue;		
			results = results + String.valueOf(version_test);
			return results;
		}
		else
		{
			String results = "v" + String.valueOf(version_main) + "." + String.valueOf(version_sub_1)
					+ "" + String.valueOf(version_sub_2);		
			results = results + String.valueOf(version_test);
			return results;
		}
	}
	private void finish_theme_refresh(int winner)
	{
		TrackStorage.addTrack(this.getClass().getName(), "finish_theme_refresh(" + winner + ") started", false);		
		for(int i=0; i<slots; i++)
		{
			try
			{
				if(i == winner)
				{
					finishDialog_pns[i].setBackground(selected_back);
				}
				else
				{
					finishDialog_pns[i].setBackground(unselected_back);
				}
			} 
			catch (ArrayIndexOutOfBoundsException e)
			{
				
			}
		}		
	}
	public void centerSecondSet(Block block, String lastCard, String[] cardList)
	{
		TrackStorage.addTrack(this.getClass().getName(), "centerSecondSet(" + block + ", " + lastCard + ", " + cardList + ") started", false);
		int index = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i) == block)
			{
				index = i;
				break;
			}
		}
		
		final int arrTarget2 = centerSecondThemeSetNow;
		centerSecondThemeSetIndex[centerSecondThemeSetNow] = index;
		centerSecondThemeSetNow++;
		if(centerSecondThemeSetNow >= centerSecondThemeSetIndex.length - 1) centerSecondThemeSetNow = 0;
		
		StringBuffer paidsLists = new StringBuffer("<html>" + lang.getText(Language.PAIDS) + "<hr>\n");
		
		if(block.getPaids().size() <= 0)
		{
			paidsLists.append(lang.getText(Language.NONE));
		}
		else
		{
			for(int i=0; i<block.getPaids().size(); i++)
			{
				paidsLists.append(block.getPaids().get(i).toBasicString(setting.getCard_separator()));
				paidsLists.append("<br>\n");
			}
		}
		
				
		centerSecondPoint[index].setText(String.valueOf(block.getPoint()));
		if(lastCard != null && (! lastCard.equals("")))
		{
			String last_target = new String(lastCard);
			String last_op, last_num;
			StringTokenizer last_token = new StringTokenizer(last_target, "|");
			last_op = last_token.nextToken();
			last_num = last_token.nextToken();
			centerSecondCards[index].setText(last_op + setting.getCard_separator() + last_num);
		}
		else centerSecondCards[index].setText("");
		centerSecondCounts[index].setText(String.valueOf(block.getOwns().size()));
		String[] empty = new String[1];
		empty[0] = new String(lang.getText(Language.SEALED));
		centerSecondList.setListData(cardList);
		
		paidsLists.append("</html>");
		final String paidsToString = paidsLists.toString();
		try
		{			
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					centerSecondPns[centerSecondThemeSetIndex[arrTarget2]].setToolTipText(paidsToString);	
				}
			});
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					blocks.get(centerSecondThemeSetIndex[arrTarget2]).setToolTipText(paidsToString);			
				}
			});
		} 
		catch (Exception e)
		{
			
		}
		/*
		if(index == turn) centerSecondList.setListData(cardList);
		else centerSecondList.setListData(empty);*/
	}
	public void centerSecondThemeSet(Block block)
	{
		TrackStorage.addTrack(this.getClass().getName(), "centerSecondThemeSet(" + block + ") started", false);
		secondBlock_index = 0;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i) == block)
			{
				secondBlock_index = i;
				break;
			}
		}
		//centerSecondList.setBackground(selected_inside_back);
		//centerSecondList.setForeground(selected_fore);
		//System.out.println("index : " + index);
		//System.out.println("centerSecondPns.length : " + centerSecondPns.length);
		if(getTurn() == secondBlock_index)
		{
			centerSecondThemeSet(secondBlock_index, true);					
		}
		else 
		{
			centerSecondThemeSet(secondBlock_index, false);			
		}		
	}
	public void centerSecondThemeSet(int index, boolean turn)
	{
		final int arrTarget = centerSecondThemeNow;
		centerSecondThemeIndex[centerSecondThemeNow] = index;
		centerSecondThemeNow++;
		if(centerSecondThemeNow >= centerSecondThemeIndex.length - 1) centerSecondThemeNow = 0;
		
		if(turn)
		{
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					centerSecondPns[centerSecondThemeIndex[arrTarget]].setBackground(selected_back);
					centerSecondLabels[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
					centerSecondPoint[centerSecondThemeIndex[arrTarget]].setBackground(selected_inside_back);
					centerSecondCards[centerSecondThemeIndex[arrTarget]].setBackground(selected_inside_back);
					centerSecondCounts[centerSecondThemeIndex[arrTarget]].setBackground(selected_inside_back);
					centerSecondPoint[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
					centerSecondCards[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
					centerSecondCounts[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
					centerSecondPointLabel[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
					centerSecondCountsLabel[centerSecondThemeIndex[arrTarget]].setForeground(selected_fore);
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
					centerSecondPns[centerSecondThemeIndex[arrTarget]].setBackground(unselected_back);
					centerSecondLabels[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
					centerSecondPoint[centerSecondThemeIndex[arrTarget]].setBackground(unselected_inside_back);
					centerSecondCards[centerSecondThemeIndex[arrTarget]].setBackground(unselected_inside_back);
					centerSecondCounts[centerSecondThemeIndex[arrTarget]].setBackground(unselected_inside_back);
					centerSecondPoint[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
					centerSecondCards[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
					centerSecondCounts[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
					centerSecondPointLabel[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
					centerSecondCountsLabel[centerSecondThemeIndex[arrTarget]].setForeground(unselected_fore);
				}
			});			
		}	
	}
	public void addActionListenerOnThis(JButton button)
	{		
		button.addActionListener(this);
		listener_added.add(button);
	}
	public void removeActionListenerOnThis(JButton button)
	{
		button.removeActionListener(this);
		listener_added.remove(button);
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
		if(setting.isUseAlertWindow()) JOptionPane.showMessageDialog(this, str);
		messages.append(str + "\n");
		messages.setCaretPosition(messages.getDocument().getLength() - 1);
		System.out.println(str);
	}
	public synchronized void calculate(int bonus_player)
	{
		TrackStorage.addTrack(this.getClass().getName(), "calculate(" + bonus_player + ") started", false);
		bonus_point = 5;
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			bonus_point = setting.getScenarios().get(scenario_mode).getBonuses();
		}
		for(int i=0; i<blocks.size(); i++)
		{
			if(i == bonus_player) blocks.get(i).calculate(false, bonus_point);
			else blocks.get(i).calculate(false, 0);
		}		
	}		
	public void runAI(boolean inThread)
	{
		TrackStorage.addTrack(this.getClass().getName(), "runAI(" + inThread + ") started", false);
		if(inThread) runAIInThread = true;
		else runAI(0);
	}
	public void runAI(boolean inThread, int mode)
	{
		TrackStorage.addTrack(this.getClass().getName(), "runAI(" + inThread + ", " + mode + ") started", false);
		if(inThread) runAIInThread = true;
		else runAI(mode);
	}
	private void runAI_finish()
	{
		TrackStorage.addTrack(this.getClass().getName(), "runAI_finish() started", false);
		runAIInThread = false;
	}
	public void runAI(int mode) // 0 : AI Player, 1 : Player use
	{
		TrackStorage.addTrack(this.getClass().getName(), "runAI() started", false);
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(false);
		}	
		
		
		int promising_i = 0; // 해당 블록에
		int promising_j = 0; // 자기가 보유한 카드 중
		int howManys = blocks.get(getTurn()).getOwns().size();
		boolean take = false;
		//String takeCard;
		
		if(setting.isUse_other_algorithm() && (! authority_mode))
		{
			if(! algorithm_ready)
			{
				algorithm = setting.getAi_algorithms()[setting.getUse_algorithm_index()].clone();
				algorithm.init(blocks, deck, getTurn(), setting.getOp_plus(), setting.getOp_minus(), setting.getOp_multiply(), setting.getOp_change(), setting.getCard_separator());
				algorithm_ready = true;
			}
			algorithm.setTurn(getTurn());
			
			algorithm_result = algorithm.act();		
			
			if(algorithm_result.getType() == AI_Algorithm_Result.TAKE)
			{
				take = true;
			}
			else if(algorithm_result.getType() == AI_Algorithm_Result.PAY)
			{
				take = false;
				promising_i = algorithm_result.getTarget_to_pay();
				promising_j = algorithm_result.getOwn_card_index();
			}
			if(take)
			{
				takeOneFromDeck(true);
			}
			else
			{
				payCard(promising_i, false, true, promising_j);
			}
		}
		else if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size() && setting.getScenarios().get(scenario_mode).getAi() != null)
		{
			if(! algorithm_ready)
			{
				algorithm = setting.getScenarios().get(scenario_mode).getAi().clone();
				algorithm.init(blocks, deck, getTurn(), setting.getOp_plus(), setting.getOp_minus(), setting.getOp_multiply(), setting.getOp_change(), setting.getCard_separator());
				algorithm_ready = true;
			}
			algorithm.setTurn(getTurn());
			
			algorithm_result = algorithm.act();		
			
			if(algorithm_result.getType() == AI_Algorithm_Result.TAKE)
			{
				take = true;
			}
			else if(algorithm_result.getType() == AI_Algorithm_Result.PAY)
			{
				take = false;
				promising_i = algorithm_result.getTarget_to_pay();
				promising_j = algorithm_result.getOwn_card_index();
			}
			if(take)
			{
				takeOneFromDeck(true);
			}
			else
			{
				payCard(promising_i, false, true, promising_j);
			}
		}
		else
		{
			virtual_blocks = new Vector<VirtualBlock>();
			virtual_blocks2 = new Vector<VirtualBlock>();
			virtual_deck = new Vector<StandardCard>();
			for(int i=0; i<deck.size(); i++)
			{
				virtual_deck.add(deck.get(i));
			}
			for(int i=0; i<blocks.size(); i++)
			{
				VirtualBlock newBlock = new VirtualBlock(setting);
				for(int j=0; j<blocks.get(i).getOwns().size(); j++)
				{
					newBlock.getOwns().add(blocks.get(i).getOwns().get(j));
				}
				for(int j=0; j<blocks.get(i).getPaids().size(); j++)
				{
					newBlock.getPaids().add(blocks.get(i).getPaids().get(j));
				}
				virtual_blocks.add(newBlock);
			}	
			virtual_block_clear();
			BigInteger max_virtual_point = new BigInteger(String.valueOf(Long.MIN_VALUE));
			BigInteger calc_temp = new BigInteger(String.valueOf(Long.MIN_VALUE));			
			StandardCard target_upCard = null;
			int targetNum, ownNum;
			char targetOp, ownOp;
			StandardCard ownCard = null;
			int block_count = virtual_blocks2.size();
			int card_count = virtual_blocks2.get(getTurn()).getOwns().size();
			boolean[][] pay_available_check = new boolean[block_count][card_count];
			for(int i=0; i<block_count; i++)
			{
				for(int j=0; j<card_count; j++)
				{
					try
					{
						calc_temp = new BigInteger(String.valueOf(Long.MIN_VALUE));
						virtual_block_clear();
						pay_available_check[i][j] = true;
						if(virtual_blocks2.get(i).getPaids().size() >= 1)
						{
							target_upCard = virtual_blocks2.get(i).getPaids().get(virtual_blocks2.get(i).getPaids().size() - 1);
						}
						else target_upCard = null;									
						if(target_upCard != null)
						{
							targetOp = target_upCard.getOp();
							targetNum = target_upCard.getNum();
						}
						else
						{
							targetOp = '\0';
							targetNum = -99;
						}
						ownOp = virtual_blocks2.get(getTurn()).getOwns().get(j).getOp();
						ownNum = virtual_blocks2.get(getTurn()).getOwns().get(j).getNum();
						boolean pay_accept = false;
						boolean change_card = false;
						
						if(target_upCard == null)
						{
							pay_accept = true;
						}
						else if(targetNum != 7 && ownNum == 1)
						{
							pay_accept = true;
							if(targetOp == setting.getOp_change()) change_card = true;
						}
						else if(targetNum == 7 && ownNum == 1)
						{
							if(getTurn() == i)
							{
								pay_accept = true;
							}
							else
								pay_accept = false;
						}
						else if(targetOp == ownOp || targetNum == ownNum)
						{
							if(targetNum != 7)
							{
								pay_accept = true;
							}
							else if(targetNum == 7 && getTurn() == i)
							{
								pay_accept = true;
							}
							else pay_accept = false;
						}
						else pay_accept = false;
						
						if(pay_accept)
						{
							ownCard = virtual_blocks2.get(getTurn()).getOwns().get(j);
							virtual_blocks2.get(getTurn()).getOwns().remove(j);
							virtual_blocks2.get(i).getPaids().add(ownCard);
							if(change_card)
							{
								Vector<StandardCard> tempOwns = virtual_blocks2.get(getTurn()).getOwns();
								virtual_blocks2.get(getTurn()).setOwns(virtual_blocks2.get(i).getOwns());
								virtual_blocks2.get(i).setOwns(tempOwns);
							}
						}
						else pay_available_check[i][j] = false;									
						
						if(! pay_available_check[i][j])
						{
							continue;
						}
							
						calc_temp = calc_virtual_point();
						if(blocks.get(getTurn()).getAi_difficulty() >= 1 && howManys == 1) // Have only one card
						{
							BigInteger max_p = new BigInteger(String.valueOf(Long.MIN_VALUE));
							int min_owns = 0;
							int min_owners = -1;
							for(int u=0; u<virtual_blocks2.size(); u++)
							{
								if(min_owns < virtual_blocks2.get(u).getOwns().size())
								{
									min_owns = virtual_blocks2.get(u).getOwns().size();
									min_owners = u;
								}
							}
							int now_winning = 0;
							for(int u=0; u<virtual_blocks2.size(); u++)
							{
								virtual_blocks2.get(u).calculate(false);
							}
							max_p = virtual_blocks2.get(0).getPoint();
							now_winning = 0;
							BigInteger virtual_calculate_point = null;
							for(int u=0; u<virtual_blocks2.size(); u++)
							{
								//if(max_p < virtual_blocks2.get(u).getPoint())
								virtual_calculate_point = virtual_blocks2.get(u).getPoint();
								if(getTurn() == min_owners)
								{
									virtual_calculate_point = virtual_calculate_point.add(Lint.big((int) bonus_point));
								}
								if(max_p.compareTo(virtual_calculate_point) < 0)
								{
									now_winning = u;
									max_p = virtual_calculate_point;
								}
							}
							if(getTurn() != now_winning)
							{
								/*
								if(calc_temp < 0) calc_temp = calc_temp * (2 * blocks.get(getTurn()).getAi_difficulty());
								else if(calc_temp > 0) calc_temp = calc_temp / (2 * blocks.get(getTurn()).getAi_difficulty());
								else calc_temp = calc_temp - (1000 * blocks.get(getTurn()).getAi_difficulty());
								*/
								if(calc_temp.compareTo(new BigInteger("0")) < 0) calc_temp = calc_temp.multiply(new BigInteger("2").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty()))));
								else if(calc_temp.compareTo(new BigInteger("0")) > 0) calc_temp = calc_temp.divide(new BigInteger("2").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty()))));
								else calc_temp = calc_temp.subtract(calc_temp.multiply(new BigInteger("1000").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty())))));
							}
						}
						
						//if(max_virtual_point < calc_temp)
						//System.out.println(calc_temp.toString());
						calc_temp = Lint.root_down(calc_temp);
						//System.out.println(calc_temp.toString());
						//System.out.println();
						if(max_virtual_point.compareTo(calc_temp) < 0)
						{
							max_virtual_point = calc_temp;
							promising_i = i;
							promising_j = j;
						}
					} 
					catch (Exception e)
					{
						e.printStackTrace();
						TrackStorage.addTrack(this.getClass().getName(), e);
						alert(lang.getText(Language.ERROR) + " : " + e.getMessage());
					}
				}
			}
			virtual_block_clear();			
			calc_temp = new BigInteger(String.valueOf(Long.MIN_VALUE));
			//System.out.println(calc_temp.toString());
			try
			{
				//virtual_blocks2.get(getTurn()).getOwns().add(virtual_deck.get(0));
				//virtual_deck.remove(0);
				//calc_temp = calc_virtual_point();
				calc_temp = Lint.big(0);
				BigInteger max_p = new BigInteger(String.valueOf(Long.MIN_VALUE));
				int now_winning = 0;
				for(int u=0; u<virtual_blocks2.size(); u++)
				{
					virtual_blocks2.get(u).calculate(false);
				}
				max_p = virtual_blocks2.get(0).getPoint();
				now_winning = 0;
				for(int u=0; u<virtual_blocks2.size(); u++)
				{
					//if(max_p < virtual_blocks2.get(u).getPoint())
					if(max_p.compareTo(virtual_blocks2.get(u).getPoint()) < 0)
					{
						now_winning = u;
						max_p = virtual_blocks2.get(u).getPoint();
					}
				}
				if(getTurn() != now_winning)
				{
					/*
					if(calc_temp < 0) calc_temp = calc_temp * (2 * blocks.get(getTurn()).getAi_difficulty());
					else if(calc_temp > 0) calc_temp = calc_temp / (2 * blocks.get(getTurn()).getAi_difficulty());
					else calc_temp = calc_temp - (1000 * blocks.get(getTurn()).getAi_difficulty());
					*/
					if(calc_temp.compareTo(new BigInteger("0")) < 0) calc_temp = calc_temp.multiply(new BigInteger("2").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty()))));
					else if(calc_temp.compareTo(new BigInteger("0")) > 0) calc_temp = calc_temp.divide(new BigInteger("2").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty()))));
					else calc_temp = calc_temp.subtract(new BigInteger("1000").multiply(new BigInteger(String.valueOf(blocks.get(getTurn()).getAi_difficulty()))));
				}
				//if(max_virtual_point < calc_temp)
				if(max_virtual_point.compareTo(calc_temp) < 0)
				{
					max_virtual_point = calc_temp;
					take = true;
				}
				else take = false;
			} 
			catch (ArrayIndexOutOfBoundsException e)
			{
				//System.out.println(virtual_deck.size());
				if(setting.isError_printDetail()) e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
				alert(lang.getText(Language.ERROR) + " : " + e.getMessage());
			}
			virtual_block_clear();			
		}		
		
		if(take)
		{
			if(setting.getKind_ai().booleanValue())
			{
				aiActedDialog.setSize(this.getWidth(), this.getHeight() - (titleBar.getHeight() + downPanel.getHeight()));
				aiActedDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + titleBar.getHeight()));
				endDialog.setVisible(false);
				aiActedDialog.open(blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 0));
			}
			takeOneFromDeck(true);			
		}
		else
		{			
			try
			{
				if(setting.getKind_ai().booleanValue())
				{
					StandardCard cardGet = blocks.get(turn).getOwns().get(promising_j);
					StandardCard cardResult = null;
					try
					{
						cardResult = (StandardCard) cardGet.clone();
						cardResult.setSeparator(setting.getCard_separator());
					} 
					catch (Exception e)
					{
						cardResult = cardGet;
					}
					aiActedDialog.setSize(this.getWidth(), this.getHeight() - (titleBar.getHeight() + downPanel.getHeight()));
					aiActedDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + titleBar.getHeight()));
					endDialog.setVisible(false);
					aiActedDialog.open(blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(promising_i).getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + cardResult);
				}
				payCard(promising_i, false, true, promising_j);				
			} 
			catch(ArrayIndexOutOfBoundsException e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
				alert(lang.getText(Language.ERROR) + " : " + e.getMessage());
			}
		}
		
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(true);
		}
		runAI_finish();
		if(mode == 1)
		{
			thisLocation = this.getLocation();
			thisSize = this.getSize();
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					endDialog.setSize((int) thisSize.getWidth(), (int) (thisSize.getHeight() - titleBar.getHeight()));
					endDialog.setLocation((int) thisLocation.getX(), (int) (thisLocation.getY() + titleBar.getHeight()));
					endDialog.setVisible(true);	
				}
			});			
			setPausedByEndDialog(true);
		}
		controlled();
	}
	private void virtual_block_clear()
	{
		TrackStorage.addTrack(this.getClass().getName(), "virtual_block_clear() started", false);
		virtual_blocks2.clear();
		for(int i=0; i<virtual_blocks.size(); i++)
		{
			virtual_blocks2.add(virtual_blocks.get(i).clone());
		}
		virtual_deck.clear();
		for(int i=0; i<deck.size(); i++)
		{
			virtual_deck.add(deck.get(i));
		}
	}
	private BigInteger calc_virtual_point()
	{
		TrackStorage.addTrack(this.getClass().getName(), "calc_virtual_point() started", false);
		BigInteger result = new BigInteger("0");
		virtual_blocks2.get(getTurn()).calculate(false);
		//result += virtual_blocks2.get(getTurn()).getPoint();
		result = result.add(virtual_blocks2.get(getTurn()).getPoint());
		BigInteger temp = new BigInteger("0");
		for(int i=0; i<virtual_blocks2.size(); i++)
		{
			if(i != getTurn())
			{
				virtual_blocks2.get(i).calculate(false);
				temp = virtual_blocks2.get(i).getPoint();
				if(blocks.get(getTurn()).getAi_difficulty() >= 2 && virtual_blocks2.get(i).getOwns().size() <= 2)
				{
					//if(temp < 0) temp = temp / 2;
					//else temp = temp * 2;
					if(temp.compareTo(new BigInteger("0")) < 0) temp = temp.divide(new BigInteger("2"));
					else temp = temp.multiply(new BigInteger("2"));
				}
				if(blocks.get(getTurn()).getAi_difficulty() >= 1 && virtual_blocks2.get(i).getOwns().size() <= 1)
				{
					//if(temp < 0) temp = temp / 2;
					//else temp = temp * 2;
					if(temp.compareTo(new BigInteger("0")) < 0) temp = temp.divide(new BigInteger("2"));
					else temp = temp.multiply(new BigInteger("2"));
				}
				result = result.subtract(temp);
				//result -= temp;
			}
		}		
		return result;
	}
	public void exit()
	{		
		TrackStorage.addTrack(this.getClass().getName(), "exit() started", false);
		if(scriptFactory != null) scriptFactory.exit();
		if(setting != null)
		{
			File exist_test = new File(setting.getDefault_path() + "setting.xml");
			File exist_test2 = new File(setting.getDefault_path() + "setting.clst");
			try
			{
				int index = 0;
				while(true)
				{
					if(setting.getOtherObjects().size() <= 0) break;
					if(setting.getOtherObjects().get(index) == null || setting.getOtherObjects().get(index).equals("")) 
						setting.getOtherObjects().remove(index);
					if(setting.getOtherObjects().get(index).startsWith("cheat|")
							|| setting.getOtherObjects().get(index).startsWith("Cheat|")
							|| setting.getOtherObjects().get(index).startsWith("CHEAT|"))
					{
						setting.getOtherObjects().remove(index);
						index = 0;
					}
					else
					{
						index++;
						if(index >= setting.getOtherObjects().size() - 1) break;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(exist_test.exists() || exist_test2.exists()) save_setting(null);
				else save_setting(null, false);
			} 
			catch (Exception e)
			{
				
			}			
		}
		ThreadAccumulate.exitAll();
		if(independence) System.exit(0);
		else
		{
			setActive(false);
			threadRun = false;
			this.setVisible(false);
		}
		try
		{
			TrackStorage.save();
		} 
		catch (Exception e)
		{
			
		}
	}
	
	public void relocation()
	{
		TrackStorage.addTrack(this.getClass().getName(), "relocation() started", false);
		
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		thisLocation = this.getLocation();
		thisSize = this.getSize();
		SwingUtilities.invokeLater(new Runnable()
		{				
			@Override
			public void run()
			{
				endDialog.setLocation((int) thisLocation.getX(), (int) (thisLocation.getY() + titleBar.getHeight()));
				selectDialog.setLocation((int)(screenSize.getWidth()/2 - selectDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - selectDialog.getHeight()/2));
				finishDialog.setLocation((int)(screenSize.getWidth()/2 - finishDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - finishDialog.getHeight()/2));
			}
		});
	}
	public synchronized void start()
	{
		TrackStorage.addTrack(this.getClass().getName(), "start() started", false);
		setActive(false);
		try
		{
			scenario_mode = scenarioDialog_list.getSelectedIndex();
		} 
		catch (Exception e1)
		{
			scenario_mode = 0;
		}
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			selectDialog_authorityMode.setSelected(true);
			time_limit_number3 = setting.getScenarios().get(scenario_mode).getTimelimit();
		}
		else
		{
			time_limit_number3 = 20;
			scenario_mode = 0;
		}
		if(selectDialog_authorityMode.isSelected())
		{			
			if(selectDialog_name[0].getText().equals("") || selectDialog_name[0].getText().equals(" "))
			{
				return;
			}
			authority_mode = true;
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					selectDialog_scriptUse.setSelected(false);
					scriptDialog.setVisible(false);	
				}
			});	
					
		}
		else authority_mode = false;
		
		if(selectDialog_scriptUse.isSelected())
		{
			scriptAllow = true;
		}
		else scriptAllow = false;
		
		setPausedBasic(false);
		setPausedByEndDialog(false);
		selectDialog.setVisible(false);
		control_collector.clear();
		this.setVisible(true);
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).nullWindow();
		}
		blocks.clear();
		turn_passed = new BigInteger("0");
		Vector<Block> tempBlock = new Vector<Block>();
		boolean playerExist = false;
		boolean detailedScenarios = false;
		DetailedScenario getDetailedScen = null;
		if(setting.getScenarios().size() > scenario_mode)
		{
			if(setting.getScenarios().get(scenario_mode) instanceof DetailedScenario)
			{
				detailedScenarios = true;
			}
		}
		if(detailedScenarios)
		{
			getDetailedScen = (DetailedScenario) setting.getScenarios().get(scenario_mode);
			if(getDetailedScen.getStartBlocks() != null)
			{
				for(int i=0; i<getDetailedScen.getStartBlocks().size(); i++)
				{
					Block newBlock = new Block(this);
					newBlock.setBlock(getDetailedScen.getStartBlocks().get(i), this);
					if(i == 0)
						newBlock.setAi(false);
					else
						newBlock.setAi(true);
					playerExist = true;
					newBlock.setTurn(false);
					newBlock.getPay().setText(lang.getText(Language.PAY));
					newBlock.setWindow(this);
					newBlock.setSet(setting);
					blocks.add(newBlock);
				}
			}
			fin_slots = blocks.size();
		}			
		else
		{			
			for(int i=0; i<selectDialog_pns.length; i++)
			{
				Block newBlock = new Block(this);	
				newBlock.setName(selectDialog_name[i].getText());
				newBlock.getNameField().setText(newBlock.getName());
				if(selectDialog_select_ai[i].isSelected())
				{
					newBlock.setAi(true);
					newBlock.setTurn(false);
					newBlock.setAi_difficulty(setting.getAi_difficulty());
					if(authority_mode) newBlock.setAi_difficulty(1);
				}
				else if(selectDialog_select_player[i].isSelected())
				{
					newBlock.setAi(false);
					newBlock.setTurn(false);
					playerExist = true;
				}
				else
				{
					for(int j=0; j<selectDialog_select_modules.size(); j++)
					{
						if(selectDialog_select_modules.get(j).radioButton[i].isSelected())
						{
							ScriptedBlock newScriptedBlock = new ScriptedBlock();
							newScriptedBlock.setModule(selectDialog_select_modules.get(j).module);
							newBlock = newScriptedBlock;
							break;
						}
					}
				}
				if(! selectDialog_select_none[i].isSelected())
				{					
					tempBlock.add(newBlock);
					newBlock.turnFinish();
				}
			}
			fin_slots = tempBlock.size();
		}
		random_order = selectDialog_randomOrder.isSelected();
		if(random_order || authority_mode)
		{
			while(tempBlock.size() >= 1) // Random
			{
				int random = (int)(Math.random() * tempBlock.size());
				blocks.add(tempBlock.get(random));
				tempBlock.remove(random);
			}
		}
		else
		{
			int tempSize = tempBlock.size();
			for(int i=0; i<tempSize; i++)
			{
				blocks.add(tempBlock.get(0));
				tempBlock.remove(0);
			}
		}
		tempBlock = null;
		if(! playerExist)
		{			
			if(setting.isOnlyAI_prevent())
			{
				single_start_problem_occur = true;
				return;
			}
		}
		if(authority_mode)
		{
			
			change_card_used = setting.getChange_card_count();
			setting.setChange_card_count(1);
			change_card_saved = true;
			auto_play_available_player = false;
		}
		else
		{
			if(center_tab_saved)
			{
				setting.setCenter_tab(center_tab_used);
				center_tab_saved = false;
			}
			if(centerBaseTab != null && setting.isCenter_tab())
			{
				SwingUtilities.invokeLater(new Runnable()
				{				
					@Override
					public void run()
					{
						centerBaseTab.setEnabled(true);
					}
				});	
			}
			if(change_card_saved)
			{
				setting.setChange_card_count(change_card_used);
				change_card_saved = false;
			}
			auto_play_available_player = true;
		}
		setTurn(0);
		blocks.get(0).thisTurn();
		deck.clear();
		Vector<StandardCard> card = new Vector<StandardCard>();
		boolean[] duplicateChecker;
		boolean giveFinish = false;
		boolean giveFinishCheck;
		char op = setting.getOp_plus();
		StandardCard newCard = null;
		int cardCount_plusMinus = setting.getPlusMinus_card_count();
		int cardCount_multiply = setting.getMultiply_card_count();
		int cardCount_change = setting.getChange_card_count();
		boolean use_trump_instead = setting.isUse_trump_card();
		if(authority_mode)
		{
			cardCount_plusMinus = 4;
			cardCount_multiply = 4;
			use_trump_instead = false;
		}
		if(scenario_mode == 0)
		{
			finishDialog_web.setVisible(false);
			bonus_point = 5;
		}
		else if(scenario_mode < setting.getScenarios().size())
		{
			Scenario gets = setting.getScenarios().get(scenario_mode);
			cardCount_multiply = gets.getMultiply_card_ratio();
			cardCount_plusMinus = gets.getPlusminus_card_ratio();
			cardCount_change = gets.getChange_card_count();
			if(setting.getScenarios().get(scenario_mode) instanceof ExtendedScenario)
			{
				ExtendedScenario getsEx = (ExtendedScenario) gets;
				if(getsEx.getRandom_multiply_use().booleanValue())
					cardCount_multiply = (int)(Math.random() * (getsEx.getRandom_multiply_max() - getsEx.getRandom_multiply_min()) + getsEx.getRandom_multiply_min());
				if(getsEx.getRandom_plus_use().booleanValue())
					cardCount_plusMinus = (int)(Math.random() * (getsEx.getRandom_plus_max() - getsEx.getRandom_plus_min()) + getsEx.getRandom_plus_min());
				
				if(getsEx.getWeb_url() != null)
				{
					SwingUtilities.invokeLater(new Runnable()
					{				
						@Override
						public void run()
						{
							finishDialog_web.setVisible(Desktop.isDesktopSupported());
							finishDialog_web.setEnabled(false);
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
							finishDialog_web.setVisible(false);
						}
					});
				}
			}
			bonus_point = gets.getBonuses();
		}
		else
		{			
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					finishDialog_web.setVisible(false);
				}
			});
			bonus_point = 5;
		}
		
		if(detailedScenarios)
		{
			getDetailedScen = (DetailedScenario) setting.getScenarios().get(scenario_mode);
			if(getDetailedScen.getDeck() != null)
			{
				for(int i=0; i<getDetailedScen.getDeck().size(); i++)
				{
					card.add((StandardCard) getDetailedScen.getDeck().get(i).clone());
				}
			}
		}	
		else
		{
			for(int k=0; k<cardCount_plusMinus; k++)
			{			
				for(int i=-1; i<10; i++)
				{
					for(int j=0; j<=1; j++)
					{
						if(use_trump_instead)
						{					
							switch(j)
							{
								case 0:
									op = setting.getOp_spade();
									break;
								case 1:
									op = setting.getOp_clover();
									break;
							}
						}
						else
						{
							switch(j)
							{
								case 0:
									op = setting.getOp_plus();
									break;
								case 1:
									op = setting.getOp_minus();
									break;
							}
						}
						newCard = new StandardCard(op, i, setting.isUse_trump_card());
						card.add(newCard);
					}				
				}
			}
			for(int k=0; k<cardCount_multiply; k++)
			{
				for(int i=-1; i<10; i++)
				{
					if(use_trump_instead) card.add(new StandardCard(setting.getOp_diamond(), i, setting.isUse_trump_card()));
					else card.add(new StandardCard(setting.getOp_multiply(), i, setting.isUse_trump_card()));
				}
			}
			
			for(int i=0; i<cardCount_change; i++)
			{
				card.add(new StandardCard(setting.getOp_change(), 1, setting.isUse_trump_card()));
			}
		}
		
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			if(setting.getScenarios().get(scenario_mode).getDeck_additionals().size() >= 1)
				for(int i=0; i<setting.getScenarios().get(scenario_mode).getDeck_additionals().size(); i++)
					card.add((StandardCard) setting.getScenarios().get(scenario_mode).getDeck_additionals().get(i).clone());
		}
		
		if(! authority_mode)
		{
			for(int i=0; i<setting.getModules().size(); i++)
			{
				if(setting.getModules().get(i) instanceof SpecialCardModule)
				{
					SpecialCardModule thisModule = (SpecialCardModule) setting.getModules().get(i);
					SpecialCard newSpecialCard = new SpecialCard();
					if(use_trump_instead)
					{
						if(thisModule.getOp() == setting.getOp_plus()) newSpecialCard.setOp(setting.getOp_spade());
						else if(thisModule.getOp() == setting.getOp_minus()) newSpecialCard.setOp(setting.getOp_clover());
						else if(thisModule.getOp() == setting.getOp_multiply()) newSpecialCard.setOp(setting.getOp_diamond());
						else newSpecialCard.setOp(thisModule.getOp());
					}
					else
					{
						newSpecialCard.setOp(thisModule.getOp());
					}
					newSpecialCard.setNum(thisModule.getNum());
					for(int j=0; j<thisModule.getCount(); j++)
					{
						card.add(newSpecialCard.clone());
					}
				}
			}
		}
		
		int random_Number = (int) Math.floor((Math.random() * card.size()));
		duplicateChecker = new boolean[card.size()];
		for(int i=0; i<duplicateChecker.length; i++)
		{
			duplicateChecker[i] = false;
		}
		
		if(detailedScenarios)
		{
			if(getDetailedScen == null) getDetailedScen = (DetailedScenario) setting.getScenarios().get(scenario_mode);
			if(getDetailedScen.getDeck_defined_random().booleanValue())
			{
				for(int i=0; i<card.size(); i++)
				{
					deck.add(card.get(i));
				}
				giveFinish = true;
			}
			else
			{
				giveFinish = false;
			}
		}
		while(! giveFinish)
		{				
			random_Number = (int) Math.floor((Math.random() * card.size()));
			if(duplicateChecker[random_Number]) continue;
			else
			{
				deck.add(card.get(random_Number));
				duplicateChecker[random_Number] = true;
			}
			giveFinishCheck = false;
			for(int i=0; i<duplicateChecker.length; i++)
			{
				if(! duplicateChecker[i])
				{
					giveFinishCheck = true;
					break;
				}
			}
			if(! giveFinishCheck)
			{
				giveFinish = true;
				break;
			}
		}	
		if(authority_mode)
		{
			start_givenCards = 10;
			ai_start_givenCards = 10;
			if(scenario_mode >= 1)
			{
				try
				{
					start_givenCards = setting.getScenarios().get(scenario_mode).getPlayer_cards_count();
					ai_start_givenCards = setting.getScenarios().get(scenario_mode).getAi_cards_count();
				} 
				catch (Exception e)
				{
					start_givenCards = 10;
					ai_start_givenCards = 10;
				}
				if(setting.getScenarios().get(scenario_mode) instanceof ExtendedScenario)
				{
					ExtendedScenario exts = (ExtendedScenario) setting.getScenarios().get(scenario_mode);
										
					if(exts.getRandom_player_cards_use().booleanValue())
					{
						start_givenCards = (int)(Math.random() * (exts.getRandom_player_cards_max().intValue() - exts.getRandom_player_cards_min().intValue()) + exts.getRandom_player_cards_min().intValue());
						ai_start_givenCards = start_givenCards;
					}
				}
			}
		}
		else 
		{
			start_givenCards = setting.getStart_givenCards();
			ai_start_givenCards = setting.getStart_givenCards();
		}
		
		isBetted = false;
		if(setting.isUser_selected())
		{
			int not_ai_count = 0;
			for(int i=0; i<blocks.size(); i++)
			{
				if(! blocks.get(i).isAi()) not_ai_count++;
			}
			if(not_ai_count == 1)
			{
				if(setting.getUsers()[setting.getNow_user_index()].getBetting() <= setting.getUsers()[setting.getNow_user_index()].getCredit())
				{
					isBetted = true;					
					betted = setting.getUsers()[setting.getNow_user_index()].getBetting();
					betted = betted * blocks.size();
					setting.getUsers()[setting.getNow_user_index()].setCredit(setting.getUsers()[setting.getNow_user_index()].getCredit() - setting.getUsers()[setting.getNow_user_index()].getBetting());
				}
			}			
		}	
		if(isBetted)
		{
			message(setting.getUsers()[setting.getNow_user_index()].getName() + " " + lang.getText(Language.DESCRIPTIONS + 15));
			message(lang.getText(Language.DESCRIPTIONS + 16) + String.valueOf(setting.getUsers()[setting.getNow_user_index()].getBetting()));
		}
		
		if(! detailedScenarios)
		{
			for(int i=0; i<blocks.size(); i++)
			{
				if(scenario_mode == 0)
				{
					for(int j=0; j<start_givenCards; j++)
					{
						blocks.get(i).getOwns().add(deck.get(0));
						deck.remove(0);					
					}
				}
				else if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
				{
					if(blocks.get(i).isAi())
					{
						for(int j=0; j<ai_start_givenCards; j++)
						{
							blocks.get(i).getOwns().add(deck.get(0));
							deck.remove(0);					
						}
						if(setting.getScenarios().get(scenario_mode).getAi_cards().size() >= 1)
						{
							for(int j=0; j<setting.getScenarios().get(scenario_mode).getAi_cards().size(); j++)
								blocks.get(i).getOwns().add((StandardCard) setting.getScenarios().get(scenario_mode).getAi_cards().get(j).clone());
						}
					}
					else
					{
						for(int j=0; j<start_givenCards; j++)
						{
							blocks.get(i).getOwns().add(deck.get(0));
							deck.remove(0);					
						}
						if(setting.getScenarios().get(scenario_mode).getPlayer_cards().size() >= 1)
						{
							for(int j=0; j<setting.getScenarios().get(scenario_mode).getPlayer_cards().size(); j++)
								blocks.get(i).getOwns().add((StandardCard) setting.getScenarios().get(scenario_mode).getPlayer_cards().get(j).clone());
						}
					}
				}
				blocks.get(i).getPay().setEnabled(true);
				blocks.get(i).refresh();			
				/*
				System.out.println(i + " : " + blocks.get(i).ai);
				if(! blocks.get(i).ai)
				{
					System.out.println("cheats");
					blocks.get(i).owns.add("*|-1");
					blocks.get(i).owns.add("*|7");
					blocks.get(i).owns.add("*|7");
					blocks.get(i).owns.add("*|7");
					blocks.get(i).refresh();
				}*/
			}
		}
		
		if(selectDialog_replayMode.isSelected()) save_replay = true;
		else save_replay = false;
		
		if(save_replay)
		{
			replay = new Replay(version_main, version_sub_1, version_sub_2, start_givenCards);
			replay.set(blocks, deck, slots);
			
			if(authority_mode)
			{
				// authority_code used
				long spcode = getAuthorizedCode(2938291);
				replay.setAuthority(new BigInteger(String.valueOf((version_main * 100) + (version_sub_1 * 10) + version_sub_2 + start_givenCards)).multiply(new BigInteger(String.valueOf(spcode))));
			}			
		}
		time_limit_number1 = 100;
		time_limit_number2 = time_limit_number3;
		time_limit.setValue(time_limit_number1);
		algorithm_ready = false;
		deck_empty = false;
		
		cheatApply();
		
		// Start
		try
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					center_refresh(false);	
					if(! mainFrame.isVisible()) mainFrame.setVisible(true);
					start_afterWork();										
					continue_order();
					setActive(true);
					if(blocks.get(0).isAi())
					{
						runAI(true);
					}
				}
			});
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
			setActive(false);
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					selectDialog.setVisible(true);					
				}				
			});			
			return;
		}
		if(detailedScenarios)
		{
			if(lang.getType() == Language.LANG_KOREAN && getDetailedScen.getKorean_startMessage() != null)
				JOptionPane.showMessageDialog(this, getDetailedScen.getKorean_startMessage());
			else if(getDetailedScen.getStartMessage() != null)
				JOptionPane.showMessageDialog(this, getDetailedScen.getStartMessage());
		}
		//System.out.println("in start(), centerSecondPns.length : " + centerSecondPns.length);
		//center_refresh();		
	}
	public void cheatApply(String cheat) throws Exception
	{
		boolean cheat_exist = false;
		if(! active) return;
		if(cheat != null)
		{
			try
			{
				if(cheat.equalsIgnoreCase("subtractor"))
				{
					for(int j=0; j<blocks.size(); j++)
					{
						if(! blocks.get(j).isAi())
						{
							blocks.get(j).getOwns().add(new StandardCard(setting.getOp_multiply(), -1, false));
						}										
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
				else if(cheat.equalsIgnoreCase("hypersolution"))
				{
					for(int j=0; j<blocks.size(); j++)
					{
						if(! blocks.get(j).isAi())
						{
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 1, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 0, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_plus(), 1, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_plus(), 9, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 9, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 7, false));
						}
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
				else if(cheat.equalsIgnoreCase("allzero"))
				{
					for(int j=0; j<blocks.size(); j++)
					{
						if(blocks.get(j).isAi())
						{
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 1, false));
							blocks.get(j).getPaids().add(new StandardCard(setting.getOp_multiply(), 0, false));
						}
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
				else if(cheat.equalsIgnoreCase("showall"))
				{
					for(int i=0; i<blocks.size(); i++)
					{
						blocks.get(i).setShowAll(true);
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
				else if(cheat.equalsIgnoreCase("extreme"))
				{
					for(int i=0; i<blocks.size(); i++)
					{
						for(int j=0; j<blocks.get(i).getOwns().size(); j++)
						{
							blocks.get(i).getOwns().get(j).setOp(setting.getOp_multiply());
						}						
					}
					for(int i=0; i<deck.size(); i++)
					{
						deck.get(i).setOp(setting.getOp_multiply());
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
				else if(cheat.equalsIgnoreCase("multy"))
				{
					for(int i=0; i<blocks.size(); i++)
					{
						for(int j=0; j<blocks.get(i).getOwns().size(); j++)
						{
							blocks.get(i).getOwns().get(j).setOp(setting.getOp_multiply());
						}
						for(int j=0; j<blocks.get(i).getPaids().size(); j++)
						{
							blocks.get(i).getPaids().get(j).setOp(setting.getOp_multiply());
						}
					}
					for(int i=0; i<deck.size(); i++)
					{
						deck.get(i).setOp(setting.getOp_multiply());
					}
					cheat_exist = true;
					message("!! " + cheat);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(cheat_exist && authority_mode)
		{
			message(lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.CANCEL));
			authority_mode = false;
		}
	}
	public void cheatApply()
	{
		// Cheat
		if(setting.getOtherObjects() != null && (! authority_mode))
		{
			StringTokenizer cheatToken;
			String contents;
			try
			{
				for(int i=0; i<setting.getOtherObjects().size(); i++)
				{
					if(setting.getOtherObjects().get(i) != null)
					{
						try
						{
							if(setting.getOtherObjects().get(i).startsWith("cheat|")
									|| setting.getOtherObjects().get(i).startsWith("Cheat|")
									|| setting.getOtherObjects().get(i).startsWith("CHEAT|"))
							{						
								cheatToken = new StringTokenizer(setting.getOtherObjects().get(i), "|");
								cheatToken.nextToken();
								contents = cheatToken.nextToken().trim();
								cheatApply(contents);
							}
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
	}
	private void start_afterWork()
	{
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).theme_refresh();
		}
		//if(! this.isVisible()) this.setVisible(true);		
		
		message(lang.getText(Language.DESCRIPTIONS + 11));
		
		SwingUtilities.invokeLater(new Runnable()
		{				
			@Override
			public void run()
			{
				takeButton.setEnabled(true);
				stop_game.setEnabled(true);
				help2.setEnabled(true);
				title_exit.setEnabled(true);
				chatOk.setEnabled(false);
				chatSet.setEnabled(false);
				deck_refresh();
				endDialog.setVisible(false);
				for(int i=0; i<centerSecondButtons.size(); i++)
				{
					centerSecondButtons.get(i).setEnabled(true);
				}
				
				if(! (setting.getLookAndFeel().equals(UIManager.getSystemLookAndFeelClassName()) && (setting.getOs().startsWith("Window") || setting.getOs().startsWith("window"))))
				{
					time_limit.setString(lang.getText(Language.IN_BAR));
				}
			}
		});
		
		
	}
	private synchronized void continue_order()
	{
		continue_ord = true;
	}
	private synchronized boolean getControlledOrder()
	{
		return continue_ord;
	}
	public synchronized void setActive(boolean acts)
	{
		active = acts;
	}
	public synchronized boolean getActive()
	{
		return active;
	}
	public synchronized void payButtonClicked(Object ob, boolean secondList)
	{
		TrackStorage.addTrack(this.getClass().getName(), "payCard(" + ob + ", " + secondList + ") started", false);
		int blockNumber = 0;
		if(secondList) 
		{
			for(int i=0; i<centerSecondButtons.size(); i++)
			{
				if(ob == centerSecondButtons.get(i))
				{
					blockNumber = i;
					break;
				}
			}
		}
		else
		{
			for(int i=0; i<blocks.size(); i++)
			{
				if(ob == blocks.get(i).getPay())
				{
					if(blocks.get(i) instanceof ScriptedBlock)
					{
						ScriptedBlock scriptedBlock = (ScriptedBlock) blocks.get(i);
						if(scriptedBlock.getModule().getUseActScript())
						{							
							try
							{								
								if(scriptFactory == null) scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);
								scriptFactory.actOnly(getBeforeCodes());
							}
							catch(Exception e)
							{
								
							}
							try
							{								
								scriptFactory.actOnly(scriptedBlock.getModule().getActScript());
							}
							catch(Exception e)
							{
								message(lang.getText(Language.ERROR) + " : " + e.getMessage());
							}
						}
						if(scriptedBlock.getModule().getActAfterDefault())
						{
							blockNumber = i;
							break;
						}
						else
						{
							controlled();
							return;
						}
					}
					else
					{
						blockNumber = i;
						break;
					}
				}
			}	
		}
		payCard(blockNumber, secondList, false, 0);
	}
	public void payCard(int blockNumber, int pay_card_index)
	{
		if(isActive())
			payCard(blockNumber, false, false, pay_card_index);
	}
	public synchronized void payCard(int blockNumber, boolean secondList, boolean ai, int pay_card_index)
	{
		TrackStorage.addTrack(this.getClass().getName(), "payCard(" + blockNumber + ", " + secondList + ", " + ai + ", " + pay_card_index +") started", false);
		boolean controlled = false;
		int target_card_index = pay_card_index;		
		StandardCard payCard = null;
		if(ai) // payCard(promising_i, false, true, promising_j);
		{
			payCard = blocks.get(getTurn()).getOwns().get(pay_card_index);
			blocks.get(getTurn()).getOwns().remove(pay_card_index);			
			blocks.get(blockNumber).getPaids().add(payCard);
			blocks.get(getTurn()).refresh();
			blocks.get(blockNumber).refresh();
			if(printDescriptDetail) message(blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(blockNumber).getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + payCard.toBasicString(setting.getCard_separator()));
			char takeCard_op = payCard.getOp();
			if(takeCard_op == setting.getOp_change())
			{
				Vector<StandardCard> tempOns = blocks.get(getTurn()).getOwns();
				blocks.get(getTurn()).setOwns(blocks.get(blockNumber).getOwns());
				blocks.get(blockNumber).setOwns(tempOns);
				blocks.get(getTurn()).refresh();
				blocks.get(blockNumber).refresh();
				message(lang.getText(Language.DESCRIPTIONS + 26) + blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 27) + blocks.get(blockNumber).getName() + lang.getText(Language.DESCRIPTIONS + 28));
			}
			if(! authority_mode)
			{
				if(payCard instanceof SpecialCard)
				{
					SpecialCard spCard = (SpecialCard) payCard;
					
					String beforeCode = getBeforeCodes();
					
					try
					{
						scriptFactory.actOnly(beforeCode);
					} 
					catch (Exception e)
					{
						
					}
					try
					{
						scriptFactory.actOnly(spCard.getScript());
					} 
					catch (Exception e)
					{
						message(setting.getLang().getText(Language.ERROR) + " : " + e.getMessage());
					}
				}
			}
			if(save_replay)
			{
				Control newControl = new Control();
				newControl.setTurn(getTurn());
				newControl.setTarget(blockNumber);
				newControl.setControl_type(Control.PAY);
				newControl.setCard((StandardCard) payCard.clone());
				newControl.setCard_index(pay_card_index);
				control_collector.controls.add(newControl);
			}
		}
		else
		{
			//System.out.println("In payCard, Turn : " + getTurn());
			try
			{
				//payCard = new String((String) blocks.get(turn).ownList.getSelectedValue());				
				if(secondList) target_card_index = centerSecondList.getSelectedIndex();
				else target_card_index = blocks.get(getTurn()).getOwnList().getSelectedIndex();	
				//System.out.println(target_card_index);
				payCard = (StandardCard) blocks.get(getTurn()).getOwns().get(target_card_index).clone();
				char payOp = payCard.getOp();
				int payNum = payCard.getNum();
				//target_card_index = blocks.get(turn).ownList.getSelectedIndex();
				
				if(blocks.get(blockNumber).getPaids().size() == 0)
				{
					blocks.get(getTurn()).getOwns().remove(target_card_index);
					blocks.get(getTurn()).refresh();
					blocks.get(blockNumber).getPaids().add(payCard);
					blocks.get(blockNumber).refresh();
					
					endDialog.setSize(this.getWidth(), this.getHeight() - titleBar.getHeight());
					endDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + titleBar.getHeight()));
					endDialog.setVisible(true);					
					setPausedByEndDialog(true);
					setActive(true);
					if(messageDialog.isVisible()) message_console.requestFocus();
					controlled();
				}
				else
				{
					StandardCard aboveCard = blocks.get(blockNumber).getPaids().get(blocks.get(blockNumber).getPaids().size() - 1);
					char aboveOp = aboveCard.getOp();
					int aboveNum = aboveCard.getNum();
					boolean pay_accept = false;
					boolean change_card = false;
					
					if(aboveNum == 7)
					{
						if(blockNumber == getTurn())
						{
							if(payNum == 1)
								pay_accept = true;
							else if(payOp == aboveOp || payNum == aboveNum)
								pay_accept = true;
							else
							{
								//alert(aboveCard + lang.getText(Language.DESCRIPTIONS + 7));	
								warn(aboveCard + lang.getText(Language.DESCRIPTIONS + 7));
							}
						}
						else
						{
							pay_accept = false;
							//alert(lang.getText(Language.DESCRIPTIONS + 6));
							warn(lang.getText(Language.DESCRIPTIONS + 6));
						}
					}					
					else if(aboveNum != 7 && payNum == 1)
					{
						pay_accept = true;
						if(payOp == setting.getOp_change()) change_card = true;
					}
					else if(aboveOp == setting.getOp_change() && payNum == aboveNum)
					{
						pay_accept = true;
					}
					else if(payOp == aboveOp || payNum == aboveNum)
					{
						pay_accept = true;
					}
					else
					{
						pay_accept = false;
						//alert(aboveCard + lang.getText(Language.DESCRIPTIONS + 7));		
						warn(aboveCard.toBasicString(setting.getCard_separator()) + lang.getText(Language.DESCRIPTIONS + 7));
					}
					
					if(pay_accept)
					{
						blocks.get(getTurn()).getOwns().remove(target_card_index);
						blocks.get(getTurn()).refresh();
						blocks.get(blockNumber).getPaids().add(payCard);
						blocks.get(blockNumber).refresh();
						
						if(setting.getUse_sound().booleanValue())
						{
							try
							{
								if(! Player.isPrepared()) Player.prepare();
								if(getTurn() == blockNumber)
								{
									Player.play(3);
								}
								else
								{
									
									if(blocks.get(blockNumber).getPoint().compareTo(Lint.big(0)) > 0)
									{
										if(payCard.getOp() == setting.getOp_multiply())
										{
											if(payCard.getNum() < 0)
												Player.play(2);
											else Player.play(3);
										}
										else if(payCard.getOp() == setting.getOp_minus())
										{
											Player.play(0);
										}
										else Player.play(3);
										
									}
									else if(blocks.get(blockNumber).getPoint().compareTo(Lint.big(0)) < 0)
									{
										if(payCard.getOp() == setting.getOp_multiply())
										{
											if(payCard.getNum() >= 2)
												Player.play(1);
										}
										else if(payCard.getOp() == setting.getOp_minus())
										{
											Player.play(0);
										}
										else Player.play(3);
									}
									else
									{
										if(payCard.getOp() == setting.getOp_minus())
										{
											Player.play(0);
										}
										else
											Player.play(3);
									}
								}
							} 
							catch (Exception e1)
							{
								if(setting.isError_printDetail())
									e1.printStackTrace();
							}
						}
						
						if(change_card)
						{
							Vector<StandardCard> tempOwns;
							tempOwns = blocks.get(turn).getOwns();
							blocks.get(getTurn()).setOwns(blocks.get(blockNumber).getOwns());
							blocks.get(blockNumber).setOwns(tempOwns);	
							
							blocks.get(getTurn()).refresh();
							blocks.get(blockNumber).refresh();
							
							message(lang.getText(Language.DESCRIPTIONS + 26) + blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 27) + blocks.get(blockNumber).getName() + lang.getText(Language.DESCRIPTIONS + 28));
						}
						if(! authority_mode)
						{
							if(payCard instanceof SpecialCard)
							{
								SpecialCard spCard = (SpecialCard) payCard;
								String beforeCode = "";
								beforeCode = beforeCode + "now_players = " + String.valueOf(blocks.size()) + "\n";
								beforeCode = beforeCode + "now_deck_size = " + String.valueOf(deck.size()) + "\n";
								beforeCode = beforeCode + "now_turn = " + String.valueOf(turn) + "\n";
								for(int i=0; i<blocks.size(); i++)
								{
									beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_owns" + " = " + String.valueOf(blocks.get(i).getOwns().size()) + "\n";
									beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paids" + " = " + String.valueOf(blocks.get(i).getPaids().size()) + "\n";
									for(int j=0; j<blocks.get(i).getOwns().size(); j++)
									{
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + " = " + "\"" + blocks.get(i).getOwns().get(j).toBasicString() + "\"" + "\n";
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_op" + " = " + "\"" + String.valueOf(blocks.get(i).getOwns().get(j).getOp()) + "\"" + "\n";
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_num" + " = " + String.valueOf(blocks.get(i).getOwns().get(j).getNum()) + "\n";
									}
									for(int j=0; j<blocks.get(i).getPaids().size(); j++)
									{
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + " = " + "\"" + blocks.get(i).getPaids().get(j).toBasicString() + "\"" + "\n";
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + "_op" + " = " + "\"" + String.valueOf(blocks.get(i).getPaids().get(j).getOp()) + "\"" + "\n";
										beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + "_num" + " = " + String.valueOf(blocks.get(i).getPaids().get(j).getNum()) + "\n";
									}
								}
								try
								{
									scriptFactory.actOnly(beforeCode);
								} 
								catch (Exception e)
								{
									
								}
								try
								{
									scriptFactory.actOnly(spCard.getScript());
								} 
								catch (Exception e)
								{
									message(setting.getLang().getText(Language.ERROR) + " : " + e.getMessage());
								}
							}
						}
						
						endDialog.setSize(this.getWidth(), this.getHeight() - titleBar.getHeight());
						endDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + titleBar.getHeight()));
						endDialog.setVisible(true);
						setPausedByEndDialog(true);
						setActive(true);
						controlled = true;
					}					
				}
				try
				{
					if(controlled)
					{
						if(printDescriptDetail)
						{
							message(blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 1) + blocks.get(blockNumber).getName() + lang.getText(Language.DESCRIPTIONS + 2) + " : " + payCard.toBasicString(setting.getCard_separator()));
						}
					}
					if(save_replay && controlled)
					{
						Control newControl = new Control();
						newControl.setTurn(getTurn());
						newControl.setTarget(blockNumber);
						newControl.setControl_type(Control.PAY);
						newControl.setCard_index(target_card_index);
						newControl.setCard((StandardCard) payCard.clone());
						control_collector.controls.add(newControl);
					}	
					if(controlled)
					{
						controlled();
					}
				} 
				catch (Exception e1)
				{
					message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
					authority_mode = false;
					save_replay = false;
				}
			} 
			catch(NullPointerException e2)
			{
				if(setting.isError_printDetail()) e2.printStackTrace();
				warn(lang.getText(Language.DESCRIPTIONS + 8));
				//alert(lang.getText(Language.DESCRIPTIONS + 8));
			}
			catch(ArrayIndexOutOfBoundsException e2)
			{
				if(setting.isError_printDetail()) e2.printStackTrace();
				//alert(lang.getText(Language.DESCRIPTIONS + 8));
				warn(lang.getText(Language.DESCRIPTIONS + 8));
			}
			catch (Exception e2)
			{
				message(lang.getText(Language.ERROR) + " : " + e2.getMessage());
				TrackStorage.addTrack(this.getClass().getName(), e2);
				if(setting.isUseAlertWindow()) JOptionPane.showMessageDialog(this, lang.getText(Language.ERROR) + " : " + e2.getMessage());
				if(setting.isError_printDetail())
				{
					e2.printStackTrace();
				}
			}
		}
	}
	public synchronized void takeOneFromDeck(boolean ai)
	{
		TrackStorage.addTrack(this.getClass().getName(), "takeOneFromDeck(" + ai + ") started", false);
		if(warnDialog.isVisible()) warn_close();
		//System.out.println("In takeOne, Turn : " + getTurn());
		if(deck.size() >= 1)
		{
			if(printDescriptDetail)
			{
				message(blocks.get(getTurn()).getName() + lang.getText(Language.DESCRIPTIONS + 0));
			}
			Block nowTurner = blocks.get(getTurn());			
			/*
			for(int i=0; i<blocks.size(); i++)
			{
				if(blocks.get(i).turn)
				{
					nowTurner = blocks.get(i);
					break;
				}
			}*/
			StandardCard took = deck.get(0);
			nowTurner.getOwns().add(took);
			deck.remove(0);
			
			if(! ai)
			{
				endDialog.setSize(this.getWidth(), this.getHeight() - titleBar.getHeight());
				endDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + titleBar.getHeight()));
				endDialog.setVisible(true);
				setPausedByEndDialog(true);
				nowTurner.refresh();
			}				
			
			if(save_replay)
			{
				Control newControl = new Control();
				newControl.setTurn(getTurn());
				newControl.setTarget(getTurn());
				newControl.setControl_type(Control.TAKE);
				newControl.setCard((StandardCard) took.clone());
				control_collector.controls.add(newControl);
			}
			setActive(true);
			controlled();
		}
		else
		{
			setActive(true);
			deck_empty = true;
			controlled();
		}
	}
	public void replay_save_dialog()
	{
		TrackStorage.addTrack(this.getClass().getName(), "replay_save_dialog() started", false);
		ff1 = new FileFilter()
		{
			@Override
			public boolean accept(File file1)
			{
				if(file1 != null)
				{
					if(file1.isDirectory()) return true;
					if(file1.getAbsolutePath().endsWith(".crep")) return true;
				}
				return false;
			}
			@Override
			public String getDescription()
			{					
				return "Replay (.crep)";
			}
		};
		ff3 = new FileFilter()
		{
			@Override
			public boolean accept(File file1)
			{
				if(file1 != null)
				{
					if(file1.isDirectory()) return true;
					if(file1.getAbsolutePath().endsWith(".crex")) return true;
				}
				return false;
			}
			@Override
			public String getDescription()
			{					
				return "Replay XML (.crex)";
			}
		};
		
		if(cfd == null) 
		{
			cfd = new JFileChooser(setting.getDefault_path());
			cfd.setFileFilter(ff1);
			cfd.addChoosableFileFilter(ff3);
		}
		int cfds = cfd.showSaveDialog(finishDialog);
		if(cfds == JFileChooser.APPROVE_OPTION)
		{
			File newFile = cfd.getSelectedFile();
			String newFileName = newFile.getAbsolutePath();
			if(newFileName.endsWith(".crep") || newFileName.endsWith(".CREP"))
					replay_save(newFile, false);
			else if(newFileName.endsWith(".crex") || newFileName.endsWith(".CREX"))
			{
				replay_save(newFile, true);
			}
			else
			{
				newFileName = newFileName + ".crex";
				newFile = new File(newFileName);
				replay_save(newFile, true);
			}
		}
	}
	public void replay_save(File target, boolean xml)
	{
		TrackStorage.addTrack(this.getClass().getName(), "replay_save(" + target + ", " + xml + ") started", false);
		for(int i=0; i<replay.getBlocks().length; i++)
		{			
			replay.getBlocks()[i].nullWindow();
		}
		try
		{
			FileOutputStream fout = new FileOutputStream(target);
			if(xml)
			{
				XMLEncoder encoder = new XMLEncoder(fout);
				replay.wrapperToObjects();
				encoder.writeObject(replay);
				encoder.close();
			}
			else
			{
				ObjectOutputStream ob = new ObjectOutputStream(fout);	
				replay.wrapperObjectClean();				
				ob.writeObject(replay);
				ob.close();
			}
			fout.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
			message(lang.getText(Language.ERROR) + " : " + e.getMessage());
			if(setting.isUseAlertWindow()) JOptionPane.showMessageDialog(finishDialog, e.getMessage());
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
			sets = setting;
		}
		FileOutputStream fout = null;
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
			//ProgressDialog.progress(10);
			
			// Scenario Data Overflow detection
			/*
			if(true)
			{
				BigInteger overflow_accumulate = Lint.big(0);
				System.out.println("시나리오 수 : " + setting.getScenarios().size());
				Scenario target;
				String targetStr;
				StringTokenizer targetToken;
				BigInteger calculated;
				for(int i=0; i<setting.getScenarios().size(); i++)
				{
					target = setting.getScenarios().get(i);
					if(target instanceof ExtendedScenario)
					{
						targetStr = Scenario.scenarioToString((ExtendedScenario) target);
						targetToken = new StringTokenizer(targetStr, "\n");
						calculated = Lint.big(targetToken.countTokens());
						if(target instanceof DetailedScenario)
						{
							calculated = calculated.add(Lint.big(((DetailedScenario) target).getStartBlocks().size()));
						}
						System.out.println("시나리오 " + target.getName() + " 의 크기 : " + calculated.toString());
						overflow_accumulate = overflow_accumulate.add(calculated);
					}
					else
					{
						calculated = Lint.big(target.getAi_cards().size() + target.getDeck_additionals().size() + target.getPlayer_cards().size());
						System.out.println("시나리오 " + target.getName() + " 의 크기 : " + calculated.toString());
						overflow_accumulate = overflow_accumulate.add(calculated);
					}
					if(target.getName().equalsIgnoreCase("Tutorial"))
					{
						sets.getScenarios().remove(i);
						i = 0;
					}
				}
			}
			*/
			for(int i=0; i<setting.getScenarios().size(); i++)
			{
				if(setting.getScenarios().get(i) instanceof DetailedScenario)
				{
					sets.getScenarios().remove(i);
					i = 0;
				}
			}
			
			if(xml)
			{
				try
				{
					File xmlFile = new File(setting.getDefault_path() + "setting.xml");
					if(xmlFile.exists()) xmlFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(setting.getDefault_path() + "setting.xml");
				XMLEncoder encoder = new XMLEncoder(fout);
				sets.wrapperToObjects();
				encoder.writeObject(sets);
				encoder.close();
				//ProgressDialog.progress(90);
				message(lang.getText(Language.SETTING) + " " + lang.getText(Language.SAVE) + " : " + setting.getDefault_path() + "setting.xml");
			}		
			else
			{
				try
				{
					File objectFile = new File(setting.getDefault_path() + "setting.clst");
					if(objectFile.exists()) objectFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(setting.getDefault_path() + "setting.clst");
				/*
				if(setting.getAi_algorithms() != null)
					System.out.println("AI : " + setting.getAi_algorithms().length);
				if(setting.getRanks() != null)
					System.out.println("Ranks : " + setting.getRanks().length);
				System.out.println("ScriptModule : " + setting.getModules().size());
				System.out.println("Scenario : " + setting.getScenarios().size());
				*/
				ObjectOutputStream obout = new ObjectOutputStream(fout);
				obout.writeObject(sets);
				obout.close();
				
				//ProgressDialog.progress(90);
				message(lang.getText(Language.SETTING) + " " + lang.getText(Language.SAVE) + " : " + setting.getDefault_path() + "setting.clst");
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
		/*
		try
		{
			fout = new FileOutputStream(setting.getDefault_path() + "scenarios.clst");
			ObjectOutputStream obout = new ObjectOutputStream(fout);
			obout.writeObject(sets.getScenarios());
			obout.close();
			fout.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
		//ProgressDialog.close();
	}
	public static void save_sets(Setting sets, boolean xml) throws Exception
	{		
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
				
			}
			
			try
			{
				fout.close();
			} 
			catch (Exception e)
			{
				
			}
		} 
		catch(Exception e)
		{
			throw e;
		}
	}
	public void addUser(User user)
	{
		TrackStorage.addTrack(this.getClass().getName(), "addUser(" + user + ") started", false);
		if(setting.getUsers() == null)
		{
			setting.setUsers(new User[1]);
			setting.getUsers()[0] = user.clone();
		}
		else
		{
			User[] newArray = new User[setting.getUsers().length + 1];
			for(int i=0; i<setting.getUsers().length; i++)
			{
				newArray[i] = setting.getUsers()[i].clone();
			}
			newArray[setting.getUsers().length] = user.clone();
			setting.setUsers(newArray);
		}
	}
	public void removeUser(int user_index)
	{
		TrackStorage.addTrack(this.getClass().getName(), "removeUser(" + user_index + ") started", false);
		if(setting.getUsers() != null)
		{
			if(setting.getUsers().length >= 2)
			{
				User[] newArray = new User[setting.getUsers().length - 1];
				for(int i=0; i<setting.getUsers().length; i++)
				{
					if(i < user_index)
					{
						newArray[i] = setting.getUsers()[i].clone();
					}
					else if(i > user_index)
					{
						newArray[i - 1] = setting.getUsers()[i].clone();
					}
				}
				setting.setUsers(newArray);
			}
			else if(setting.getUsers().length == 1)
			{
				setting.setUsers(null);
			}
		}
		setting.setUser_selected(false);
	}
	public void selectUser(int user_index)
	{
		TrackStorage.addTrack(this.getClass().getName(), "selectUser(" + user_index + ") started", false);
		setting.setNow_user_index(user_index);
		setting.setUser_selected(true);
	}
	public void unselectUser()
	{
		TrackStorage.addTrack(this.getClass().getName(), "unselectUser() started", false);
		if(setting != null)
		{
			setting.setUser_selected(false);
		}
	}
	private void warn_alreadyFinish(int index)
	{
		TrackStorage.addTrack(this.getClass().getName(), "warn_alreadyFinish(" + index + ") started", false);
		if(blocks.get(index).getOwns().size() == 1)
			message(lang.getText(Language.DESCRIPTIONS + 10) + blocks.get(index).getName());
	}
	private synchronized void finish_game()
	{
		TrackStorage.addTrack(this.getClass().getName(), "finish_game() started", false);
		if(setting.getKind_ai().booleanValue())
		{
			aiActedDialog.exit();
		}
		setActive(false);	
		message(lang.getText(Language.DESCRIPTIONS + 29));
		deck_empty = false;
		finished = false;
		finish_refresh(false);
		for(int i=0; i<blocks.size(); i++)
			finishDialog_fields[i].setText(String.valueOf(blocks.get(i).getPoint()));
		if(save_replay)
		{			
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					finishDialog_replay.setVisible(true);
					finishDialog_replay.setEnabled(true);
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
					finishDialog_replay.setEnabled(false);
				}
			});
		}
		BigInteger[] points = new BigInteger[blocks.size()];
		BigInteger max_point = new BigInteger(String.valueOf(Long.MIN_VALUE));
		int winner = 0;
		int not_ai = 0;
		for(int i=0; i<points.length; i++)
		{
			points[i] = blocks.get(i).getPoint();
			if(! blocks.get(i).isAi())
			{
				not_ai = i;
			}
		}
		max_point = points[0];
		for(int i=0; i<points.length; i++)
		{
			//if(max_point < points[i])
			if(max_point.compareTo(points[i]) < 0)
			{
				max_point = points[i];
				winner = i;
			}
		}
		Rank newOne;
		newOne = new Rank();
		newOne.ver_main = CalcWindow.version_main;
		newOne.ver_sub1 = CalcWindow.version_sub_1;
		newOne.ver_sub2 = CalcWindow.version_sub_2;
		newOne.point = blocks.get(not_ai).getPoint();
		Calendar calendar = Calendar.getInstance();
		newOne.year = calendar.get(Calendar.YEAR);
		newOne.month = calendar.get(Calendar.MONTH);
		newOne.day = calendar.get(Calendar.DAY_OF_MONTH);
		newOne.hour = calendar.get(Calendar.HOUR_OF_DAY);
		newOne.minute = calendar.get(Calendar.MINUTE);
		newOne.name = new String(blocks.get(not_ai).getName());
		if(winner == not_ai)
		{		
			if(authority_mode)
			{
				setting.addRank(newOne);
				save_setting(null);	
			}
		}
		if(setting.isUser_selected() && isBetted)
		{
			if(winner == not_ai)
			{
				setting.getUsers()[setting.getNow_user_index()].setCredit(setting.getUsers()[setting.getNow_user_index()].getCredit() + betted);
				setting.getUsers()[setting.getNow_user_index()].addRank(newOne);
				message(lang.getText(Language.ADD) + " " + lang.getText(Language.CREDIT) + " : " + String.valueOf(betted));
				if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
				{
					BigInteger bonus_star = Lint.big(1);
					if(setting.getScenarios().get(scenario_mode).getDifficulty() >= 0)
					{
						bonus_star = bonus_star.add(Lint.big(setting.getScenarios().get(scenario_mode).getDifficulty()));
					}
					setting.getUsers()[setting.getNow_user_index()].setStars(setting.getUsers()[setting.getNow_user_index()].getStars().add(bonus_star));
					message(setting.getUsers()[setting.getNow_user_index()].getName() + "\'s ★ : " + setting.getUsers()[setting.getNow_user_index()].getStars().toString());
				}
			}
			else
			{
				if(setting.getUsers()[setting.getNow_user_index()].getCredit() <= 0)
				{
					removeUser(setting.getNow_user_index());
				}
			}
			user_selector.refresh(user_selector.isVisible());
		}
		try
		{
			if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
			{
				if(setting.getScenarios().get(scenario_mode) instanceof ExtendedScenario)
				{
					ExtendedScenario ex = (ExtendedScenario) setting.getScenarios().get(scenario_mode);
					if(ex.getStar_line() != null)
					{
						if(winner == not_ai)
						{
							long befores = ex.getStars().longValue();
							long afters = 0; 
							for(int i=0; i<ex.getStar_line().length; i++)
							{
								if(points[not_ai].compareTo(Lint.big(ex.getStar_line()[i])) >= 0)
								{
									afters++;
								}
							}
							if(befores < afters)
							{
								ex.setStars(new Long(afters));
							}
							scenario_refresh();
						}
					}
				}
				if(setting.getScenarios().get(scenario_mode)  instanceof LankableScenario)
				{
					LankableScenario lks = (LankableScenario) setting.getScenarios().get(scenario_mode);
					String newLank;
					if(winner == not_ai)
					{
						newLank = points[not_ai].toString() + lks.getLank_separator() + blocks.get(not_ai).getName();
						lks.getLank().add(newLank);
						if((System.getProperty("user.language").equalsIgnoreCase("ko") || System.getProperty("user.language").equalsIgnoreCase("kr")
								|| System.getProperty("user.language").equalsIgnoreCase("kor") || System.getProperty("user.language").equalsIgnoreCase("korean"))
								&& lks.getKorean_endMessage() != null)
						{
							JOptionPane.showMessageDialog(this, lks.getKorean_endMessage());
						}
						else if(lks.getEndMessage() != null)
						{
							JOptionPane.showMessageDialog(this, lks.getEndMessage());
						}
					}
					else
					{
						if((System.getProperty("user.language").equalsIgnoreCase("ko") || System.getProperty("user.language").equalsIgnoreCase("kr")
								|| System.getProperty("user.language").equalsIgnoreCase("kor") || System.getProperty("user.language").equalsIgnoreCase("korean"))
								&& lks.getFail_korean_endMessage() != null)
						{
							JOptionPane.showMessageDialog(this, lks.getFail_korean_endMessage());
						}
						else if(lks.getFail_endMessage() != null)
						{
							JOptionPane.showMessageDialog(this, lks.getFail_endMessage());
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			
		}
		if(save_replay)
		{
			replay.setPoints(new BigInteger[blocks.size()]);
			for(int i=0; i<replay.getPoints().length; i++)
			{
				replay.getPoints()[i] = blocks.get(i).getPoint();
			}
			replay.getControls(control_collector);
			replay.setResult_points(points);
			BigInteger accumulated = new BigInteger("0");
			for(int i=0; i<replay.getControls().length; i++)
			{
				//accumulated += replay.getControls()[i].card_index;
				//accumulated += replay.getControls()[i].control_type;
				accumulated = accumulated.add(new BigInteger(String.valueOf(replay.getControls()[i].getCard_index())));
				accumulated = accumulated.add(new BigInteger(String.valueOf(replay.getControls()[i].getControl_type())));
			}
			//accumulated = accumulated * ((version_main * 100) + (version_sub_1 * 10) + version_sub_2);
			accumulated = accumulated.multiply(new BigInteger(String.valueOf((version_main * 100) + (version_sub_1 * 10) + version_sub_2)));
			replay.setCode_x(accumulated);
			/*
			for(int i=0; i<replay.first_blocks.length; i++)
			{
				System.out.println("first_blocks["+ i + "].owns.size() : " + replay.first_blocks[i].owns.size());
				for(int j=0; j<replay.first_blocks[i].owns.size(); j++)
					System.out.println(i + ", " + j + " : " + replay.first_blocks[i].owns.get(j));		
			}*/
		}
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(false);
		}
		if(center_tab_saved)
		{
			setting.setCenter_tab(center_tab_used);
			center_tab_saved = false;
			if(centerBaseTab != null && setting.isCenter_tab())
			{
				SwingUtilities.invokeLater(new Runnable()
				{				
					@Override
					public void run()
					{
						centerBaseTab.setEnabled(true);
					}
				});
			}
		}
		if(! (setting.getLookAndFeel().equals(UIManager.getSystemLookAndFeelClassName()) && (setting.getOs().startsWith("Window") || setting.getOs().startsWith("window"))))
		{
			time_limit.setString(lang.getText(Language.TITLE));
		}
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				takeButton.setEnabled(false);
				stop_game.setEnabled(false);
				//title_exit.setEnabled(false);
				help2.setEnabled(false);
				endDialog.setVisible(false);
				finishDialog.setVisible(true);				
			}			
		});		
		scriptAllow = true;
	}
	private void save_log()
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_log() started", false);
		ff2 = new FileFilter()
		{
			@Override
			public boolean accept(File file1)
			{
				if(file1 != null)
				{
					if(file1.isDirectory()) return true;
					if(file1.getAbsolutePath().endsWith(".txt")) return true;
				}
				return false;
			}
			@Override
			public String getDescription()
			{					
				return "Text (.txt)";
			}
		};
		if(cfd2 == null) 
		{
			cfd2 = new JFileChooser(setting.getDefault_path());
			cfd2.setFileFilter(ff2);
		}
		int cfds = cfd2.showSaveDialog(selectDialog);
		if(cfds == JFileChooser.APPROVE_OPTION)
		{
			File newFile = cfd2.getSelectedFile();
			String newFileName = newFile.getAbsolutePath();
			try
			{
				if(newFileName.endsWith(".txt"))
					save_log_to_file(newFile);
				else
				{
					newFileName = newFileName + ".txt";
					newFile = new File(newFileName);
					save_log_to_file(newFile);
				}
			} 
			catch (Exception e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e);
				JOptionPane.showMessageDialog(selectDialog, e.getMessage());
			}
		}		
	}
	private void save_log_to_file(File path) throws Exception
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_log_to_file(" + path + ") started", false);
		FileWriter fwriter = new FileWriter(path);
		BufferedWriter bfwriter = new BufferedWriter(fwriter);
		String writes = messages.getText();
		if(System.getProperty("os.name").startsWith("windows") || System.getProperty("os.name").startsWith("Windows"))
		{
			//System.out.println(writes);
			writes = new String(writes.getBytes("MS949"));
		}
		bfwriter.write(writes);
		
		bfwriter.close();
		fwriter.close();
	}
	private synchronized int getTurn()
	{
		//System.out.println("Turn : " + turn);
		return turn;
	}
	private synchronized void setTurn(int turn)
	{
		//System.out.println("Set Turn : " + turn);
		this.turn = turn;
	}
	private synchronized void nextTurn()
	{
		setTurn(turn + 1);	
		//System.out.println("Block size : " + blocks.size());
		if(getTurn() >= blocks.size())
		{			
			//System.out.println("Turn to zero, Block size : " + blocks.size());
			setTurn(0);
		}
	}
	public synchronized void controlled()
	{
		TrackStorage.addTrack(this.getClass().getName(), "controlled() started", false);
		runAI_finish();
		time_limit_number1 = 100;
		time_limit_number2 = time_limit_number3;
		time_limit.setValue(time_limit_number1);
		blocks.get(turn).turnFinish();
		
		turn_passed = turn_passed.add(Lint.big(1));
		
		if(! active)
		{
			return;
		}
		warn_alreadyFinish(turn);
		
		nextTurn();
		//System.out.println("In controlled, Turn : " + getTurn());
		
		blocks.get(turn).thisTurn();
		deck_changed = true;
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).theme_refresh();
		}					
		if(deck_changed)
		{
			deck_refresh();
			deck_changed = false;
		}
		finished = false;
		for(int i=0; i<blocks.size(); i++)
		{
			if(blocks.get(i).getOwns().size() == 0)
			{
				finished = true;
				break;
			}
		}
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			if(setting.getScenarios().get(scenario_mode) instanceof LankableScenario)
			{
				LankableScenario lks = (LankableScenario) setting.getScenarios().get(scenario_mode);
				if((turn_passed.compareTo(lks.getLimit_turn() ) >= 1)
						&& (lks.getLimit_turn().compareTo(Lint.big(0)) != 0))
				{
					finished = true;
				}
			}
			if(setting.getScenarios().get(scenario_mode) instanceof DetailedScenario)
			{
				DetailedScenario dts = (DetailedScenario) setting.getScenarios().get(scenario_mode);
				if(dts.getMessageIncludes() != null)
				{
					for(int i=0; i<dts.getMessageIncludes().size(); i++)
					{
						if(dts.getMessageIncludes().get(i) != null)
						{
							if(Lint.big(dts.getMessageIncludes().get(i).getTurn().intValue()).compareTo(turn_passed) == 0  )
							{
								if(lang.getType() == Language.LANG_KOREAN && dts.getMessageIncludes().get(i).getKor_contents() != null)
								{
									JOptionPane.showMessageDialog(this, dts.getMessageIncludes().get(i).getKor_contents());
								}
								else
								{
									JOptionPane.showMessageDialog(this, dts.getMessageIncludes().get(i).getContents());
								}
							}
						}
					}
				}
			}
		}
		if(messageDialog.isVisible()) message_console.requestFocus();
		if(active && (deck.size() == 0 || deck_empty || finished))
		{
			finish_game();
			return;
		}
		if(! active) return;
		if(blocks.get(turn) instanceof ScriptedBlock)
		{
			if(scriptFactory == null) scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);
			try
			{
				scriptFactory.actOnly(((ScriptedBlock) blocks.get(turn)).getModule().getTurnScript());
			} 
			catch (Exception e)
			{
				message(lang.getText(Language.ERROR) + " : " + e.getMessage());
			}
		}
		if(scenario_mode >= 1 && scenario_mode < setting.getScenarios().size())
		{
			if(scriptFactory == null) scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);
			String run_needed = setting.getScenarios().get(scenario_mode).getTurn_script();
			String prepared = getBeforeCodes();
			
			try
			{
				scriptFactory.actOnly(prepared);
			}
			catch(Exception e)
			{
				
			}
			try
			{
				AI_Script_Data newData = new AI_Script_Data();
				newData.blocks = new VirtualBlock[blocks.size()];
				for(int i=0; i<newData.blocks.length; i++)
				{
					newData.blocks[i] = blocks.get(i).toVirtualBlock();
				}
				newData.deck = new StandardCard[deck.size()];
				for(int i=0; i<newData.deck.length; i++)
				{
					newData.deck[i] = (StandardCard) deck.get(i).clone();
				}
				scriptFactory.putObject("data", newData);
			}
			catch(Exception e)
			{
				
			}
			
			try
			{
				scriptFactory.actOnly(run_needed);
			}
			catch (Exception e)
			{
				message(lang.getText(Language.ERROR) + " : " + e.getMessage());
			}
		}
		if(blocks.get(turn).isAi())
		{						
			//runAI(0);
			runAI(true);
			if(setting.getKind_ai().booleanValue())
			{
				pause();
				try
				{					
					for(int i=0; i<200; i++)
					{
						Thread.sleep(20);
						if(! aiActedDialog.isActive()) break;
						//System.out.println("o in Run " + isPaused());
					}				
				} 
				catch (Exception e)
				{
					
				}
				if(aiActedDialog.isActive()) aiActedDialog.exit();
				endDialog.setVisible(false);
				resume();
			}
			//continue;
		}
	}
	public boolean isPaused()
	{
		//System.out.println("Basic : " + isPausedBasic() + ", endDialog : " + isPausedByEndDialog());
		return (isPausedBasic() || isPausedByEndDialog());
	}
	private synchronized void setPausedBasic(boolean value)
	{
		paused = value;
	}
	private synchronized void setPausedByEndDialog(boolean value)
	{
		endDialog_paused = value;
	}
	private synchronized boolean isPausedBasic()
	{
		//System.out.println("Basic : " + paused);
		return paused;
	}
	private synchronized boolean isPausedByEndDialog()
	{
		//System.out.println("endDialog : " + endDialog_paused);
		return endDialog_paused;
	}
	public synchronized void pause()
	{
		paused = true;
	}
	public synchronized void resume()
	{
		paused = false;
		endDialog_paused = false;
	}
	public void act_console(String orders, int mode) throws Exception
	{
		TrackStorage.addTrack(this.getClass().getName(), "act_console( + " + orders + " + ) started", false);
		//message(orders + ", " + mode);
		if(orders == null || orders.equals(""))
		{
			if(warnDialog.isVisible()) warn_close();
			if(isPausedByEndDialog())
			{
				endDialog.setVisible(false);
				setPausedByEndDialog(false);
			}
			if(selectDialog_tab.getSelectedIndex() != 1)
			{
				selectDialog_tab.setSelectedIndex(1);
			}
			message_console.requestFocus();
		}
		else
		{
			String[] strings = orders.split(" ");
			String savingScript = "";
			if(active)
			{
				message(blocks.get(getTurn()).getName() + " > " + orders);
				boolean pay_mode = false;
				boolean pay_number_mode = false;				
				int string_type = 0; // 0 : String, 1 : Numbers			
				int inputed_number = 0;
				int pay_blockNumber = 0;
				for(int i=0; i<strings.length; i++)
				{
					string_type = 0;
					try
					{
						inputed_number = Integer.parseInt(strings[i]);
						string_type = 1;
					}
					catch(NumberFormatException e)
					{
						string_type = 0;
						pay_mode = false;						
					}
					if(pay_number_mode && string_type == 0)
					{
						int find_index = 0;
						boolean found_card = false;						
						for(int j=0; j<blocks.get(turn).getOwns().size(); j++)
						{
							if(blocks.get(turn).getOwns().get(j).equals(strings[i]))
							{
								find_index = j;
								found_card = true;
							}
						}
						if(found_card)
						{
							payCard(pay_blockNumber, false, false, find_index);
						}
						else
						{
							message(lang.getText(Language.DESCRIPTIONS + 8));
						}
						pay_number_mode = false;
					}
					else if(strings[i].equalsIgnoreCase("take") && (mode == -1 || mode == 0 || mode == 1))
					{
						takeOneFromDeck(false);
					}
					else if(strings[i].equalsIgnoreCase("auto") && (mode == -1 || mode == 0 || mode == 1))
					{
						if(auto_play_available_player)
						{
							message(blocks.get(turn).getName() + lang.getText(Language.AUTO_PASS));
							runAI(1);	
						}
					}
					else if(strings[i].equalsIgnoreCase("pay") && (mode == -1 || mode == 0 || mode == 1))
					{
						pay_mode = true;
						pay_number_mode = false;
					}
					else if(strings[i].equalsIgnoreCase("owns") && (mode == -1 || mode == 0 || mode == 1))
					{
						message(lang.getText(Language.OWNS));
						for(int j=0; j<blocks.get(turn).getOwns().size(); j++)
						{
							message(String.valueOf(j) + ". " + blocks.get(turn).getOwns().get(j));
						}
					}
					else if((strings[i].equalsIgnoreCase("stop") || strings[i].equalsIgnoreCase(lang.getText(Language.GAME_STOP))) && (mode == -1 || mode == 0 || mode == 1))
					{
						setActive(false);
						SwingUtilities.invokeLater(new Runnable()
						{				
							@Override
							public void run()
							{
								endDialog.setVisible(false);
								finishDialog.setVisible(false);
								takeButton.setEnabled(false);
								stop_game.setEnabled(false);
								chatOk.setEnabled(true);
								chatSet.setEnabled(true);
							}
						});
						//title_exit.setEnabled(false);
						for(int j=0; j<blocks.size(); j++)
						{
							blocks.get(j).getPay().setEnabled(false);
						}
						SwingUtilities.invokeLater(new Runnable()
						{				
							@Override
							public void run()
							{
								help2.setEnabled(false);
							}
						});
						if(! (setting.getLookAndFeel().equals(UIManager.getSystemLookAndFeelClassName()) && (setting.getOs().startsWith("Window") || setting.getOs().startsWith("window"))))
						{
							SwingUtilities.invokeLater(new Runnable()
							{				
								@Override
								public void run()
								{
									time_limit.setString(lang.getText(Language.TITLE));
								}
							});
						}
						SwingUtilities.invokeLater(new Runnable()
						{				
							@Override
							public void run()
							{
								selectDialog.setVisible(true);
							}
						});
						if(messageDialog.isVisible()) message_console.requestFocus();
					}
					else if((strings[i].equalsIgnoreCase("exit") || strings[i].equalsIgnoreCase(lang.getText(Language.EXIT))) && (mode == -1 || mode == 0 || mode == 1))
					{
						exit();
					}	
					else if(strings[i].equalsIgnoreCase("clear") && (mode == -1 || mode == 0 || mode == 1))
					{
						messages.setText("");
					}
					else if(string_type == 1 && (pay_mode || pay_number_mode))
					{
						if(pay_mode)
						{
							pay_blockNumber = inputed_number;
							pay_number_mode = true;
							pay_mode = false;
						}
						else if(pay_number_mode)
						{
							payCard(pay_blockNumber, false, false, inputed_number);
							pay_number_mode = false;
						}
					}
					else if((strings[i].equals(";") || strings[i].endsWith(";") || (mode == 2 && i >= strings.length - 1)) && (! authority_mode) && (mode == -1 || mode == 0 || mode == 2) && scriptAllow)
					{
						if(strings[i].equals(";"))
						{
							
						}
						else if(strings[i].endsWith(";"))
						{
							String removeSemicolon = "";
							char[] buffers = strings[i].toCharArray();
							for(int a=0; a<buffers.length - 1; a++)
							{
								removeSemicolon = removeSemicolon + String.valueOf(buffers[a]);
							}
							savingScript = savingScript + removeSemicolon;
						}
						else
						{
							savingScript = savingScript + strings[i];
						}
						if(scriptFactory == null)
						{
							scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
						}
						try
						{
							String beforeData = "";
							String define_int = "", define_string = "";
							if(scriptFactory.getEngine().equalsIgnoreCase("JavaScript"))
							{
								define_int = "";
								define_string = "";
							}
							beforeData = beforeData + define_int + " deck_size = " + deck.size() + ";\n";
							beforeData = beforeData + define_int + " turn = " + turn + ";\n";
							beforeData = beforeData + define_int + " players" + " = " + blocks.size() + ";\n";
							
							beforeData = beforeData + define_string + " player_myself" + " = " + "\"" + blocks.get(turn).getName() + "\"" + ";\n";
							beforeData = beforeData + define_int + " player_myself" + "_owns" + " = " + String.valueOf(blocks.get(turn).getOwns().size()) + ";\n";
							beforeData = beforeData + define_int + " point_myself" + " = " + String.valueOf(blocks.get(turn).getPoint()) + ";\n";
							beforeData = beforeData + define_int + " point_myself" + "_as_string" + " = " + "\"" + String.valueOf(blocks.get(turn).getPoint()) + "\"" + ";\n";
							for(int a=0; a<blocks.size(); a++)
							{
								beforeData = beforeData + define_string + " player_" + String.valueOf(a) + " = " + "\"" + blocks.get(a).getName() + "\"" + ";\n";
								beforeData = beforeData + define_int + " player_" + String.valueOf(a) + "_owns" + " = " + String.valueOf(blocks.get(a).getOwns().size()) + ";\n";
								beforeData = beforeData + define_int + " point_" + String.valueOf(a) + " = " + String.valueOf(blocks.get(a).getPoint()) + ";\n";
								beforeData = beforeData + define_int + " point_" + String.valueOf(a) + "_as_string" + " = " + "\"" + String.valueOf(blocks.get(a).getPoint()) + "\"" + ";\n";
							}
							
							
							if(blocks.get(turn).getPaids().size() >= 1) 
							{
								beforeData = beforeData + define_string + " last_myself" + " = " + "\"" + blocks.get(turn).getPaids().lastElement().toBasicString() + "\"" + ";\n";
								beforeData = beforeData + define_string + " last_myself" + "_op" + " = " + "\"" + String.valueOf(blocks.get(turn).getPaids().lastElement().getOp()) + "\"" + ";\n";
								beforeData = beforeData + define_int + " last_myself" + "_num" + " = " + String.valueOf(blocks.get(turn).getPaids().lastElement().getNum()) + ";\n";
							}
							else
							{
								beforeData = beforeData + define_string + " last_myself" + " = " + "\"empty\"" + ";\n";
								beforeData = beforeData + define_string + " last_myself" + "_op" + " = " + "\"empty\"" + ";\n";
								beforeData = beforeData + define_int + " last_myself" + "_num" + " = " + "0" + ";\n";
							}
							for(int b=0; b<blocks.size(); b++)
							{
								if(blocks.get(b).getPaids().size() >= 1) 
								{
									beforeData = beforeData + define_string + " last_" + String.valueOf(b) + " = " + "\"" + blocks.get(b).getPaids().lastElement().toBasicString() + "\"" + ";\n";
									beforeData = beforeData + define_string + " last_" + String.valueOf(b) + "_op" + " = " + "\"" + String.valueOf(blocks.get(b).getPaids().lastElement().getOp()) + "\"" + ";\n";
									beforeData = beforeData + define_int + " last_" + String.valueOf(b) + "_num" + " = " + String.valueOf(blocks.get(b).getPaids().lastElement().getNum()) + ";\n";
								}
								else
								{
									beforeData = beforeData + define_string + " last_" + String.valueOf(b) + " = " + "\"empty\"" + ";\n";
									beforeData = beforeData + define_string + " last_" + String.valueOf(b) + "_op" + " = " + "\"empty\"" + ";\n";
									beforeData = beforeData + define_int + " last_" + String.valueOf(b) + "_num" + " = " + "0" + ";\n";
								}
							}
							
							Object gets = scriptFactory.actAndGet(beforeData + savingScript);
							if(gets != null && (! gets.toString().equals("")))
							{
								message(gets.toString());
							}
							savingScript = "";
						} 
						catch (Exception e)
						{
							message(lang.getText(Language.ERROR) + " : " + e.getMessage());
						}
					}
					else if((mode == -1 || mode == 0 || mode == 2) && scriptAllow)
					{						
						savingScript = savingScript + strings[i];
						if(i >= strings.length - 1 && (! authority_mode))
						{
							if(scriptFactory == null)
							{
								scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
							}
							try
							{
								scriptFactory.act(savingScript);
								savingScript = "";
							} 
							catch (Exception e)
							{
								message(lang.getText(Language.ERROR) + " : " + e.getMessage());
							}
						}
					}
					else
					{
						//message("!!! " + strings[i] + ", " + mode);
					}
				}
			}
			else
			{
				message(lang.getText(Language.TITLE) + " > " + orders);
				boolean key_mode = false;
				for(int i=0; i<strings.length; i++)
				{
					if(((strings[i].equalsIgnoreCase("start") || strings[i].equalsIgnoreCase(lang.getText(Language.START))) && (mode == -1 || mode == 0 || mode == 1)) && selectDialog_tab.getSelectedIndex() == 1)
					{
						if(multiplay) net_start();
						else start();
						if(single_start_problem_occur)
						{
							single_start_problem_occur = false;
							selectDialog.setVisible(true);				
						}
						key_mode = false;
					}
					else if((strings[i].equalsIgnoreCase("set") || strings[i].equalsIgnoreCase(lang.getText(Language.SETTING))) && (mode == -1 || mode == 0 || mode == 1))
					{
						this.setVisible(false);
						threadRun = false;
						setActive(false);		
						selectDialog.setVisible(false);
						messageDialog.setVisible(false);
						endDialog.setVisible(false);
						new SettingManager(setting.clone(), screenSize, version_main, version_sub_1, version_sub_2, true, manager, false, arguments).open();
					}
					else if((strings[i].equalsIgnoreCase("exit") || strings[i].equalsIgnoreCase(lang.getText(Language.EXIT))) && (mode == -1 || mode == 0 || mode == 1))
					{
						exit();
					}
					else if((strings[i].equalsIgnoreCase("trump_mode")) && (mode == -1 || mode == 0 || mode == 1))
					{
						if(grade_mode >= 1)
						{
							setting.setUse_trump_card((! setting.isUse_trump_card()));
							if(setting.isUse_trump_card())
							{
								message(lang.getText(Language.DESCRIPTIONS + 31));
							}
							else
							{
								message(lang.getText(Language.DESCRIPTIONS + 32));
							}
						}
					}
					else if((strings[i].equalsIgnoreCase("save_setting")) && (mode == -1 || mode == 0 || mode == 1))
					{
						save_setting(setting);
					}					
					else if((strings[i].equalsIgnoreCase("authorize") || strings[i].equalsIgnoreCase(lang.getText(Language.AUTHORITY))) && (mode == -1 || mode == 0 || mode == 1))
					{
						selectDialog_authorityMode.setSelected(! selectDialog_authorityMode.isSelected());
						key_mode = false;
					}
					else if((strings[i].equalsIgnoreCase("checker")) && (mode == -1 || mode == 0 || mode == 1))
					{
						if(code_checker == null) code_checker = new Code_Checker(false, selectDialog, setting);
						code_checker.open();
					}
					else if((strings[i].equalsIgnoreCase("key")) && (mode == -1 || mode == 0 || mode == 1))
					{
						key_mode = true;
					}
					else if((strings[i].equalsIgnoreCase("clear")) && (mode == -1 || mode == 0 || mode == 1))
					{
						messages.setText("");
					}
					else if((strings[i].equalsIgnoreCase("city")) && (mode == -1 || mode == 0 || mode == 1) && grade_mode >= 1)
					{
						new CityManager(this, false, setting).open();
					}
					else if(key_mode)
					{
						StringTokenizer stoken = new StringTokenizer(strings[i], "-");
						String[] key_gets = new String[stoken.countTokens()];
						for(int j=0; j<key_gets.length; j++)
						{
							key_gets[j] = stoken.nextToken().toUpperCase(Locale.ENGLISH);
						}
						if(setting.input(key_gets))
						{							
							save_setting(null);
							grade_mode = getGrade(setting);
							ver_serial.setText(getGradeString(setting));
							ver_serial.setForeground(getGradeColor());
							gradeColor = ver_serial.getForeground();
							ver_serial.setFont(getGradeFont());							
							message(lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.COMPLETE) + " : " + getGradeString(setting));
							
							SwingUtilities.invokeLater(new Runnable()
							{				
								@Override
								public void run()
								{
									selectDialog_menu_anothers_oneCard.setEnabled(true);
									scenarioDialog_new.setVisible(true);
								}
							});
							
						}
						key_mode = false;
					}
					else if((strings[i].equals(";") || strings[i].endsWith(";") || (mode == 2 && i >= strings.length - 1)) && (! authority_mode) && (mode == -1 || mode == 0 || mode == 2))
					{						
						if(strings[i].equals(";"))
						{
							
						}
						else if(strings[i].endsWith(";"))
						{
							String removeSemicolon = "";
							char[] buffers = strings[i].toCharArray();
							for(int a=0; a<buffers.length - 1; a++)
							{
								removeSemicolon = removeSemicolon + String.valueOf(buffers[a]);
							}
							savingScript = savingScript + removeSemicolon;							
						}
						else
						{
							savingScript = savingScript + strings[i];
						}
						if(scriptFactory == null)
						{
							scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
						}
						try
						{
							scriptFactory.act(savingScript);							
							savingScript = "";
						} 
						catch (Exception e)
						{
							message(lang.getText(Language.ERROR) + " : " + e.getMessage());
						}
					}
					else if(mode == -1 || mode == 0 || mode == 2)
					{						
						savingScript = savingScript + strings[i];
						if(i >= strings.length - 1)
						{
							if(scriptFactory == null)
							{
								scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
							}
							try
							{
								scriptFactory.act(savingScript);
								savingScript = "";
							} 
							catch (Exception e)
							{
								message(lang.getText(Language.ERROR) + " : " + e.getMessage());
							}
						}
					}
					else
					{
						//message("!!!!!! " + strings[i] + ", " + mode);
					}
				}				
			}
		}
	}
	public String getBeforeCodes()
	{
		String beforeCode = "";
		beforeCode = beforeCode + "now_players = " + String.valueOf(blocks.size()) + "\n";
		beforeCode = beforeCode + "now_deck_size = " + String.valueOf(deck.size()) + "\n";
		beforeCode = beforeCode + "now_turn = " + String.valueOf(turn) + "\n";
		for(int i=0; i<blocks.size(); i++)
		{
			beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_owns" + " = " + String.valueOf(blocks.get(i).getOwns().size()) + "\n";
			beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paids" + " = " + String.valueOf(blocks.get(i).getPaids().size()) + "\n";
			for(int j=0; j<blocks.get(i).getOwns().size(); j++)
			{
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + " = " + "\"" + blocks.get(i).getOwns().get(j).toBasicString() + "\"" + "\n";
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_op" + " = " + "\"" + String.valueOf(blocks.get(i).getOwns().get(j).getOp()) + "\"" + "\n";
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_num" + " = " + String.valueOf(blocks.get(i).getOwns().get(j).getNum()) + "\n";
			}
			for(int j=0; j<blocks.get(i).getPaids().size(); j++)
			{
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + " = " + "\"" + blocks.get(i).getPaids().get(j).toBasicString() + "\"" + "\n";
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + "_op" + " = " + "\"" + String.valueOf(blocks.get(i).getPaids().get(j).getOp()) + "\"" + "\n";
				beforeCode = beforeCode + "now_player_" + String.valueOf(i) + "_paid_" + String.valueOf(j) + "_num" + " = " + String.valueOf(blocks.get(i).getPaids().get(j).getNum()) + "\n";
			}
		}
		beforeCode = beforeCode + "empty_string = \"\"" + "\n";
		return beforeCode;
	}
	public void warn(String message)
	{
		warnDialog_message.setText(message);
		SwingUtilities.invokeLater(new Runnable()
		{				
			@Override
			public void run()
			{
				takeButton.setEnabled(false);
				stop_game.setEnabled(false);
			}
		});
		//title_exit.setEnabled(false);
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(false);
		}
		SwingUtilities.invokeLater(new Runnable()
		{				
			@Override
			public void run()
			{
				warnDialog.setLocation((int)(screenSize.getWidth()/2 - warnDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - warnDialog.getHeight()/2));
				warnDialog.setVisible(true);
			}
		});
	}
	public void warn_close()
	{		
		SwingUtilities.invokeLater(new Runnable()
		{				
			@Override
			public void run()
			{
				warnDialog.setVisible(false);
				takeButton.setEnabled(true);
				stop_game.setEnabled(true);
				title_exit.setEnabled(true);
			}
		});
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(true);
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == selectDialog_authorityMode)
		{
			if(selectDialog_authorityMode.isSelected())
			{
				if(slots >= 4)
				{						
					SwingUtilities.invokeLater(new Runnable()
					{				
						@Override
						public void run()
						{
							selectDialog_select_player[0].setSelected(true);
							selectDialog_select_ai[1].setSelected(true);
							selectDialog_select_ai[2].setSelected(true);
							selectDialog_select_ai[3].setSelected(true);
						}
					});
					if(slots > 4)
					{
						for(int i=4; i<slots; i++)
						{
							selectDialog_select_none[i].setSelected(true);
						}
					}
					
					for(int i=0; i<slots; i++)
					{
						selectDialog_select_player[i].setEnabled(false);
						selectDialog_select_ai[i].setEnabled(false);
						selectDialog_select_none[i].setEnabled(false);
					}
					SwingUtilities.invokeLater(new Runnable()
					{				
						@Override
						public void run()
						{
							selectDialog_replayMode.setSelected(true);
							selectDialog_replayMode.setEnabled(false);
							selectDialog_randomOrder.setSelected(true);
							selectDialog_randomOrder.setEnabled(false);
							selectDialog_scriptUse.setEnabled(false);
							selectDialog_scriptUse.setSelected(false);
						}
					});
					
					
					authority_mode = true;
					random_order = true;
					save_replay = true;
				}
				else
				{					
					SwingUtilities.invokeLater(new Runnable()
					{				
						@Override
						public void run()
						{
							selectDialog_authorityMode.setSelected(false);
						}
					});
				}
			}
			else
			{
				for(int i=0; i<slots; i++)
				{
					selectDialog_select_player[i].setEnabled(true);
					selectDialog_select_ai[i].setEnabled(true);
					selectDialog_select_none[i].setEnabled(true);
				}
				SwingUtilities.invokeLater(new Runnable()
				{				
					@Override
					public void run()
					{
						selectDialog_replayMode.setEnabled(true);
						selectDialog_randomOrder.setEnabled(true);
						selectDialog_scriptUse.setEnabled(true);
						selectDialog_scriptUse.setSelected(true);
					}
				});
				
				authority_mode = false;
			}
		}
		else if(ob == selectDialog_replayMode)
		{
			
		}
		else if(ob == selectDialog_menu_anothers_oneCard || ob == selectDialog_menu_anothers_conquer || ob == selectDialog_menu_anothers_math)
		{			
			if(ob == selectDialog_menu_anothers_oneCard && selectDialog_menu_anothers_oneCard.isSelected())
			{
				selectDialog_menu_anothers_oneCard.setSelected(true);
				selectDialog_menu_anothers_conquer.setSelected(false);
				selectDialog_menu_anothers_math.setSelected(false);
				setting.setNext_execute(new SaveInt(1));
				setting.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(ob == selectDialog_menu_anothers_conquer && selectDialog_menu_anothers_conquer.isSelected())
			{
				selectDialog_menu_anothers_oneCard.setSelected(false);
				selectDialog_menu_anothers_conquer.setSelected(true);
				selectDialog_menu_anothers_math.setSelected(false);
				setting.setNext_execute(new SaveInt(2));
				setting.setNext_execute_saved(new SaveBoolean(true));
			}
			else if(ob == selectDialog_menu_anothers_math && selectDialog_menu_anothers_math.isSelected())
			{
				selectDialog_menu_anothers_conquer.setSelected(false);
				selectDialog_menu_anothers_oneCard.setSelected(false);
				selectDialog_menu_anothers_math.setSelected(true);
				setting.setNext_execute(new SaveInt(5));
				setting.setNext_execute_saved(new SaveBoolean(true));				
			}
			else if(! (selectDialog_menu_anothers_oneCard.isSelected() || selectDialog_menu_anothers_conquer.isSelected() 
					|| selectDialog_menu_anothers_math.isSelected()))
			{
				selectDialog_menu_anothers_oneCard.setSelected(false);
				selectDialog_menu_anothers_conquer.setSelected(false);
				selectDialog_menu_anothers_math.setSelected(false);
				setting.setNext_execute(new SaveInt(0));
				setting.setNext_execute_saved(new SaveBoolean(false));
			}
			save_setting(null);
		}
		else if(ob == miniSetting_useTab)
		{
			SwingUtilities.invokeLater(new Runnable()
			{				
				@Override
				public void run()
				{
					miniSetting_accept.setEnabled(true);
				}
			});
		}
		else if(ob == themeDialog_classic)
		{
			if(themeDialog_classic.isSelected())
			{
				themeDialog_combo.setEnabled(false);
				themeDialog_colorCombo.setEnabled(false);
			}
			else
			{
				themeDialog_combo.setEnabled(true);
				themeDialog_colorCombo.setEnabled(true);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		//System.out.println(ob);
		if(ob == exit)
		{
			exit();
		}
		else if(ob == title_exit)
		{
			exit();
		}
		else if(ob == selectDialog_menu_exit)
		{
			exit();
		}
		else if(ob == miniSetting_accept)
		{
			//Setting newSetting = setting.clone();
			scenarioDialog_list.setSelectedIndex(0);
			setting.setCenter_tab(miniSetting_useTab.isSelected());
			setting.setAi_difficulty(((Integer) miniSetting_difficulty.getValue()).intValue());
			setting.setChange_card_count(((Integer) miniSetting_changeCount.getValue()).intValue());
			setting.setMultiply_card_count(((Integer) miniSetting_multiplyCount.getValue()).intValue());
			setting.setStart_givenCards(((Integer) miniSetting_cardCount.getValue()).intValue());
			
			save_setting(null);
			miniSetting_accept.setEnabled(false);
		}		
		else if(ob == miniSetting_changeTheme)
		{
			try
			{
				LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
				if(looks == null || looks.length <= 0)
				{
					if(setting.getLookAndFeel().endsWith("NimbusLookAndFeel"))
					{
						themeDialog_combo.setSelectedIndex(1);
					}
					else if(setting.getLookAndFeel().endsWith("MetalLookAndFeel"))
					{
						themeDialog_combo.setSelectedIndex(0);
					}
					else if(setting.getLookAndFeel().equalsIgnoreCase(UIManager.getSystemLookAndFeelClassName()))
					{
						themeDialog_combo.setSelectedIndex(2);
					}
				}
				else
				{
					try
					{
						for(int i=0; i<looks.length; i++)
						{						
							if(looks[i].getClassName().equalsIgnoreCase(UIManager.getLookAndFeel().getClass().getName()))
							{
								themeDialog_combo.setSelectedIndex(i);
								break;
							}						
						}
					} 
					catch (Exception e1)
					{
						themeDialog_combo.setSelectedIndex(0);
					}
				}
				
				
				ThemeSet thmSet1 = new ThemeSet(0);
				ThemeSet thmSet2 = new ThemeSet(1);
				ThemeSet thmSet3 = new ThemeSet(2);
				ThemeSet thmSet4 = new ThemeSet(3);
				if(setting.getSelected_back().equals(thmSet1.getSelected_back()) && setting.getSelected_fore().equals(thmSet1.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet1.getSelected_inside()))
				{
					themeDialog_colorCombo.setSelectedIndex(0);
				}
				else if(setting.getSelected_back().equals(thmSet2.getSelected_back()) && setting.getSelected_fore().equals(thmSet2.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet2.getSelected_inside()))
				{
					themeDialog_colorCombo.setSelectedIndex(1);
				}
				else if(setting.getSelected_back().equals(thmSet3.getSelected_back()) && setting.getSelected_fore().equals(thmSet3.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet3.getSelected_inside()))
				{
					themeDialog_colorCombo.setSelectedIndex(2);
				}
				else if(setting.getSelected_back().equals(thmSet4.getSelected_back()) && setting.getSelected_fore().equals(thmSet4.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet4.getSelected_inside()))
				{
					themeDialog_colorCombo.setSelectedIndex(3);
				}
				/*
				if(setting.isUse_color())
				{
					if(setting.getSelected_back().equals(thmSet1.getSelected_back()) && setting.getSelected_fore().equals(thmSet1.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet1.getSelected_inside()))
					{
						themeDialog_colorCombo.setSelectedIndex(0);
					}
					else if(setting.getSelected_back().equals(thmSet2.getSelected_back()) && setting.getSelected_fore().equals(thmSet2.getSelected_fore()) && setting.getSelected_inside_back().equals(thmSet2.getSelected_inside()))
					{
						themeDialog_colorCombo.setSelectedIndex(3);
					}
				}
				else
				{
					themeDialog_colorCombo.setSelectedIndex(2);
				}
				*/
			} 
			catch (Exception e1)
			{
				
			}
			
			themeDialog.setVisible(true);
		}
		else if(ob == themeDialog_accept)
		{
			if(themeDialog_classic.isSelected())
			{
				setting.setClassic_mode(true);				
			}
			else
			{
				boolean original_select = false;
				if(setting.getThemeSelection_new() != null && setting.getThemeSelection_new())
				{
					try
					{
						LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
						for(int i=0; i<looks.length; i++)
						{
							//System.out.println(looks[i].getName() + " : " + themeDialog_combo.getItemAt(i).toString());
							if(looks[i].getName().equalsIgnoreCase(themeDialog_combo.getSelectedItem().toString()))
							{
								setting.setLookAndFeel(looks[i].getClassName());
								original_select = false;
								break;
							}
						}
					} 
					catch (Exception e1)
					{
						if(setting.isError_printDetail())
							e1.printStackTrace();
						original_select = true;
					}
				}
				else original_select = true;
				if(original_select)
				{
					switch(themeDialog_combo.getSelectedIndex())
					{
						case 0:
							setting.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
							break;
						case 1:
							setting.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
							break;
						case 2:
							setting.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							break;
						default:
							setting.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
				}
				ThemeSet thmSet = null;
				switch(themeDialog_colorCombo.getSelectedIndex())
				{
					case 0:
						setting.setUse_color(true);
						thmSet = new ThemeSet(0);
						break;
					case 1:
						setting.setUse_color(true);
						thmSet = new ThemeSet(1);
						break;
					case 2:
						setting.setUse_color(true);
						thmSet = new ThemeSet(2);
						break;
					case 3:
						setting.setUse_color(true);
						thmSet = new ThemeSet(3);
						break;
						/*
					case 4:
						thmSet = null;
						setting.setUse_color(false);
						*/
					default:
						setting.setUse_color(true);
						thmSet = new ThemeSet(0);
						break;
						
				}
				if(thmSet != null)
				{
					setting.setSelected_back(thmSet.getSelected_back());
					setting.setSelected_fore(thmSet.getSelected_fore());
					setting.setSelected_inside_back(thmSet.getSelected_inside());
					if(thmSet.getSelected_button() != null)
						setting.setSelected_button(thmSet.getSelected_button());				
					else setting.setSelected_button(null);				
					setting.setUnselected_back(thmSet.getUnselected_back());
					setting.setUnselected_fore(thmSet.getUnselected_fore());
					setting.setUnselected_inside_back(thmSet.getUnselected_inside());
					if(thmSet.getUnselected_button() != null)
						setting.setUnselected_button(thmSet.getUnselected_button());
					else setting.setUnselected_button(null);
					setting.setColor_reverse(thmSet.isColor_reverse());
				}
				setting.setClassic_mode(false);
			}
			
			themeDialog.setVisible(false);
		}
		else if(ob == themeDialog_close)
		{			
			themeDialog.setVisible(false);
		}
		else if(ob == message_act || ob == message_console)
		{
			int console_mode = message_mode.getSelectedIndex();
			if(console_mode >= -1 && console_mode <= 2)
			{
				try
				{
					act_console(message_console.getText(), console_mode);
				} 
				catch (Exception e1)
				{
					if(setting.isError_printDetail()) e1.printStackTrace();
					TrackStorage.addTrack(this.getClass().getName(), e1);
					message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
				}
			}
			else if(console_mode == 3)
			{
				if(chats.isAlive())
				{
					chats.send(message_console.getText());
				}
			}
			else
			{
				if(chats.isAlive())
				{
					try
					{
						chats.send(console_mode - 4, message_console.getText());
					} 
					catch (Exception e1)
					{
						message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
					}
				}
			}
			message_console.setText("");			
		}
		else if(ob == warnDialog_close)
		{
			warn_close();
		}
		else if(ob == endDialog_end)
		{
			endDialog.setVisible(false);
			setPausedByEndDialog(false);
		}
		else if(ob == help)
		{
			if(helpDialog != null) helpDialog.setVisible(false);
			helpDialog = new Help(setting, selectDialog);
			helpDialog.setVisible(true);
		}
		else if(ob == help2)
		{
			if(helpDialog != null) helpDialog.setVisible(false);
			helpDialog = new Help(setting, this);
			helpDialog.setVisible(true);
		}
		else if(ob == selectDialog_menu_help_view)
		{
			if(helpDialog != null) helpDialog.setVisible(false);
			helpDialog = new Help(setting, selectDialog);
			helpDialog.setVisible(true);
		}
		else if(ob == selectDialog_menu_anothers_city)
		{
			if(citym == null)
				citym = new CityManager(selectDialog, false, setting);
			citym.open();
		}
		else if(ob == selectDialog_menu_anothers_reflex)
		{
			if(reflex == null)
				reflex = new Reflexioner(selectDialog, setting, false);
			reflex.open();
		}
		else if(ob == selectDialog_noticeOK)
		{
			selectDialog_tab.setSelectedIndex(1);
		}
		else if(ob == selectDialog_menu_update)
		{
			tryUpdate();			
		}
		else if(ob == selectDialog_menu_uninstall)
		{
			String askMessage;
			if(lang instanceof Korean)
			{
				askMessage = "프로그램 종료 후 " + setting.getDefault_path() + " 폴더 내의 파일들을 삭제합니다.\n계속하시겠습니까?";
			}
			else
			{
				askMessage = "Delete all contents in " + setting.getDefault_path() + " directory after this program end.\nDo you want to continue?";
			}
			if(JOptionPane.showConfirmDialog(selectDialog, askMessage, "Uninstall", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				Uninstaller.uninstall(this, setting, true, true, true, false, selectDialog);
			}
		}
		else if(ob == scenarioDialog_close)
		{
			scenarioDialog.setVisible(false);
			setting.setScenario_open(false);
			save_setting(null);
		}
		else if(ob == scenarioDialog_new)
		{
			if(scenarioEditor == null)
			{
				scenarioEditor = new ScenarioEditor(false, setting, this);
			}
			scenarioEditor.open();
		}
		else if(ob == selectDialog_noticeRetry)
		{
			readed_notice = getNotice("notice.txt", null);
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_utf.txt", null);
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice.txt", "EUC-KR");
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice.txt", "MS949");
			}
			if(! notice_read_success)
			{			
				readed_notice = getNotice("notice_utf.txt", "UTF-8");
			}
			
			if(notice_read_success)
			{
				selectDialog_noticeArea.setText(readed_notice);
				notice_read_count += 2;				
			}
			else
			{
				selectDialog_noticeArea.setText(lang.getText(Language.NOTICE_NULL));
				notice_read_count++;
			}
			if(notice_read_count >= 5)
			{
				selectDialog_noticeRetry.setEnabled(false);
			}
			
		}
		else if(ob == selectDialog_menu_saveLog)
		{
			save_log();
		}
		else if(ob == selectDialog_menu_view_script)
		{
			if(! (active && authority_mode))
				scriptDialog.setVisible(true);
		}
		else if(ob == scriptDialog_close)
		{
			scriptDialog.setVisible(false);
		}
		else if(ob == scriptDialog_run)
		{
			if(! (active && authority_mode))
			{
				if(scriptAllow)
				{
					if(scriptFactory == null)
					{
						scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);							
					}
					try
					{
						Object getOb = scriptFactory.actAndGet(scriptDialog_text.getText());
						if(getOb != null)
							message(getOb.toString());
					} 
					catch (Exception e1)
					{
						message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		}
		else if(ob == selectDialog_menu_view_checker)
		{
			if(code_checker == null) code_checker = new Code_Checker(false, selectDialog, setting);
			code_checker.open();
		}
		else if(ob == selectDialog_menu_view_scenario)
		{
			scenarioDialog.setVisible(true);
			setting.setScenario_open(true);
		}
		else if(ob == selectDialog_menu_view_replay)
		{
			new ReplayPlayer(this, selectDialog);
		}
		else if(ob == selectDialog_menu_view_rank)
		{
			new RankViewer(selectDialog, setting, unselected_back, selected_back, unselected_inside_back, selected_inside_back, unselected_fore, selected_fore).open();
		}
		else if(ob == selectDialog_menu_view_user)
		{
			if(user_selector == null) user_selector = new User_Selector(setting, this);
			user_selector.open();
		}
		else if(ob == selectDialog_menu_help_ver)
		{
			ver.setVisible(true);
		}
		else if(ob == serialDialog_close)
		{
			serialDialog.setVisible(false);
		}
		else if(ob == serialDialog_ok || ob == serialDialog_keys[serialDialog_keys.length - 1])
		{
			String[] getKeys = new String[serialDialog_keys.length];
			for(int i=0; i<getKeys.length; i++)
			{
				getKeys[i] = serialDialog_keys[i].getText().toUpperCase(Locale.ENGLISH);
			}
			if(setting.input(getKeys))
			{
				grade_mode = getGrade(setting);
				ver_serial.setForeground(getGradeColor());
				gradeColor = ver_serial.getForeground();
				ver_serial.setText(getGradeString(setting));
				ver_serial.setFont(getGradeFont());
				
				if(grade_mode >= 2)
				{
					try
					{
						if(setting.getLookAndFeel() == null || setting.getLookAndFeel().equals("")
								|| setting.getLookAndFeel().equals("javax.swing.plaf.metal.MetalLookAndFeel")
								|| setting.getLookAndFeel().equals("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"))
						{
							setting.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
							ThemeSet thmSet = new ThemeSet(1);
							setting.setSelected_back(thmSet.getSelected_back());
							setting.setSelected_fore(thmSet.getSelected_fore());
							setting.setSelected_inside_back(thmSet.getSelected_inside());
							if(thmSet.getSelected_button() != null)
								setting.setSelected_button(thmSet.getSelected_button());				
							else setting.setSelected_button(null);				
							setting.setUnselected_back(thmSet.getUnselected_back());
							setting.setUnselected_fore(thmSet.getUnselected_fore());
							setting.setUnselected_inside_back(thmSet.getUnselected_inside());
							if(thmSet.getUnselected_button() != null)
								setting.setUnselected_button(thmSet.getUnselected_button());
							else setting.setUnselected_button(null);
							setting.setColor_reverse(thmSet.isColor_reverse());
						}
					} 
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
				if(grade_mode >= 1)
				{
					selectDialog_menu_view_script.setVisible(true);
					scenarioDialog_new.setVisible(true);
					selectDialog_menu_anothers_city.setVisible(true);
				}
				else 
				{
					selectDialog_menu_view_script.setVisible(false);
					scenarioDialog_new.setVisible(false);
					selectDialog_menu_anothers_city.setVisible(false);
				}
				default_scenario();
				save_setting(null);
				String[] options = new String[2];
				options[0] = lang.getText(Language.EXIT);
				options[1] = lang.getText(Language.CLOSE);
				int getSelection = JOptionPane.showOptionDialog(serialDialog, lang.getText(Language.NEED_RESTART), lang.getText(Language.NEED_RESTART), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
							
				serialDialog.setVisible(false);
				if(getSelection == JOptionPane.YES_OPTION)
				{
					exit();
				}
				else
				{
					
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(serialDialog, lang.getText(Language.DESCRIPTIONS + 20));
			}
		}
		else if(ob == ver_close)
		{
			ver.setVisible(false);
		}
		else if(ob == stop_game)
		{			
			stop_game();
		}			
		else if(ob == finishDialog_copy)
		{
			try
			{
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finishDialog_code.getText()), null);
				alert(lang.getText(Language.DESC_COPY_CLIPBOARD));
			} 
			catch (Exception e1)
			{
				if(setting.isError_printDetail()) e1.printStackTrace();
				TrackStorage.addTrack(this.getClass().getName(), e1);
				alert(lang.getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == settingButton)
		{			
			this.setVisible(false);
			threadRun = false;
			setActive(false);		
			selectDialog.setVisible(false);
			messageDialog.setVisible(false);
			endDialog.setVisible(false);
			new SettingManager(setting.clone(), screenSize, version_main, version_sub_1, version_sub_2, true, manager, false, arguments).open();
		}
		else if(ob == selectDialog_menu_set)
		{
			this.setVisible(false);
			threadRun = false;
			setActive(false);		
			selectDialog.setVisible(false);
			messageDialog.setVisible(false);
			endDialog.setVisible(false);
			new SettingManager(setting.clone(), screenSize, version_main, version_sub_1, version_sub_2, true, manager, false, arguments).open();
		}
		else if(ob == start)
		{					
			if(multiplay) net_start();
			else start();
			if(single_start_problem_occur)
			{
				single_start_problem_occur = false;
				selectDialog.setVisible(true);				
			}
		}
		else if(ob == start2)
		{					
			if(multiplay) net_start();
			else start();
			if(single_start_problem_occur)
			{
				single_start_problem_occur = false;
				selectDialog.setVisible(true);				
			}
		}
		else if(ob == finishDialog_close)
		{	
			finishDialog.setVisible(false);
			for(int i=0; i<blocks.size(); i++)
			{
				blocks.get(i).turnFinish();
			}			
			takeButton.setEnabled(false);
			stop_game.setEnabled(false);
			//title_exit.setEnabled(false);
			help2.setEnabled(false);
			finishDialog.setVisible(false);
			chatOk.setEnabled(true);
			chatSet.setEnabled(true);
			for(int i=0; i<blocks.size(); i++)
			{
				blocks.get(i).getPay().setEnabled(false);
			}
			selectDialog.setVisible(true);
		}
		else if(ob == finishDialog_web)
		{
			try
			{
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(scenario_web));
			} 
			catch (Exception e1)
			{
				finishDialog_web.setEnabled(false);
			}
		}
		else if(ob == takeButton)
		{
			takeOneFromDeck(false);		
		}
		else if(ob == finishDialog_replay)
		{
			replay_save_dialog();
		}
		else if(ob == selectDialog_multi_host)
		{
			host();
		}
		else if(ob == selectDialog_multi_join)
		{
			join();
		}
		else if(ob == selectDialog_multi_disconnect)
		{
			disconnect();
		}
		else if(ob == chatOk)
		{
			try
			{
				String gets = JOptionPane.showInputDialog(this, lang.getText(Language.INPUT) + " : " + lang.getText(Language.USER) + " " + lang.getText(Language.IP));
				if(gets != null)
				{
					if(! chats.isAlive())
					{
						chats.open();					
					}
					chats.addUser(gets);
					consoleMode.clear();
					consoleMode.add(lang.getText(Language.ORDER) + ", " + lang.getText(Language.SCRIPT));
					consoleMode.add(lang.getText(Language.ORDER));
					consoleMode.add(lang.getText(Language.SCRIPT));
					consoleMode.add("--------------");
					consoleMode.add(lang.getText(Language.ALL));
					for(int i=0; i<chats.getIps().size(); i++)
					{
						consoleMode.add(chats.getIps().get(i));
					}
					message_mode.setListData(consoleMode);	
					chatRemove.setEnabled(chats.isAlive());
				}
			} 
			catch (Exception e1)
			{
				message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == chatRemove)
		{
			try
			{
				chatRemove.setEnabled(chats.isAlive());
				int selectedIndex = message_mode.getSelectedIndex() - 5;
				if(selectedIndex >= 0)
				{
					chats.getIps().remove(selectedIndex);
					consoleMode.clear();
					consoleMode.add(lang.getText(Language.ORDER) + ", " + lang.getText(Language.SCRIPT));
					consoleMode.add(lang.getText(Language.ORDER));
					consoleMode.add(lang.getText(Language.SCRIPT));
					consoleMode.add("--------------");
					consoleMode.add(lang.getText(Language.ALL));
					for(int i=0; i<chats.getIps().size(); i++)
					{
						consoleMode.add(chats.getIps().get(i));
					}
					message_mode.setListData(consoleMode);	
				}
				chatRemove.setEnabled(chats.isAlive());
				if(chats.getIps().size() <= 0)
				{
					chatRemove.setEnabled(false);
					consoleMode.clear();
					consoleMode.add(lang.getText(Language.ORDER) + ", " + lang.getText(Language.SCRIPT));
					consoleMode.add(lang.getText(Language.ORDER));
					consoleMode.add(lang.getText(Language.SCRIPT));
				}
			}
			catch (Exception e1)
			{
				message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == chatSet)
		{
			try
			{
				String gets = JOptionPane.showInputDialog(this, lang.getText(Language.INPUT) + " : " + lang.getText(Language.PORT));
				if(gets != null)
				{
					int transes = Integer.parseInt(gets);
					chats.setPort(transes);
				}
			} 				
			catch (Exception e1)
			{
				message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
			}
			
		}
		else
		{			
			boolean skip = false;
			for(int i=0; i<listener_added.size(); i++)
			{
				if(ob == listener_added.get(i))
				{
					payButtonClicked(ob, false);
					skip = true;
					break;
				}
			}
			if(! skip)
			{
				for(int i=0; i<centerSecondButtons.size(); i++)
				{
					if(ob == centerSecondButtons.get(i))
					{
						payButtonClicked(ob, true);
						skip = true;
						break;
					}
				}	
			}
		}
		try
		{
			message_console.requestFocus();
		} 
		catch (Exception e1)
		{
			
		}
	}
	public void tryUpdate()
	{
		tryUpdate(true);
	}
	public void tryUpdate(boolean already_notice)
	{
		Component upper = null;
		try
		{
			upper = selectDialog;
			if(! selectDialog.isVisible()) upper = this;
			if(! this.isVisible()) upper = null;
		} 
		catch (Exception e)
		{
			
		}
		try
		{			
			String getStr = "";
			char[] getChars;
			int getVm, getV1, getV2;
			boolean need_update = false;
			String urlPath = setting.getNotice_url() + "notice_ver.txt";
			
			InputStreamReader inputs = null;
			try
			{
				inputs = new InputStreamReader(new URL(urlPath).openStream());
			} 
			catch (Exception e)
			{
				try
				{
					inputs.close();
				}
				catch(Exception e1)
				{
					
				}
				urlPath = setting.getNotice_url2() + "notice_ver.txt";
				inputs = new InputStreamReader(new URL(urlPath).openStream());
			}
			
			
			BufferedReader bfreaders = new BufferedReader(inputs);
			getStr = bfreaders.readLine();
			try
			{
				bfreaders.close();
			}
			catch(Exception e2)
			{
				
			}
			try
			{
				inputs.close();
			}
			catch(Exception e2)
			{
				
			}
			getChars = getStr.toCharArray();
			getVm = getChars[0] - '0';
			getV1 = getChars[1] - '0';
			getV2 = getChars[2] - '0';
			
			if(getVm > version_main)
				need_update = true;			
			else if(getVm == version_main && getV1 > version_sub_1)
				need_update = true;
			else if(getVm == version_main && getV1 == version_sub_1 && getV2 > version_sub_2)
				need_update = true;
			else if(getVm == version_main && getV1 == version_sub_1 && getV2 == version_sub_2 && 
					(String.valueOf(version_test).equalsIgnoreCase("a") || String.valueOf(version_test).equalsIgnoreCase("b") || String.valueOf(version_test).equalsIgnoreCase("c")))
				need_update = true;
			else
				need_update = false;
			
			if(! need_update)
			{
				int al_sel = -1;
				String[] al_list = new String[2];
				al_list[0] = lang.getText(Language.UPDATE);
				al_list[1] = lang.getText(Language.CLOSE);
				if(already_notice)
				{
					al_sel = JOptionPane.showOptionDialog(upper, lang.getText(Language.CHECK_RELIABILITY) + "\n" + urlPath + "\n\n" + lang.getText(Language.ALREADY_NEWEST) + "\n" + lang.getText(Language.DOYOUWANTTOUPDATE), lang.getText(Language.DOYOUWANTTOUPDATE), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, al_list, null);
					//al_sel = JOptionPane.showMessageDialog(upper, lang.getText(Language.CHECK_RELIABILITY) + "\n" + urlPath + "\n\n" + lang.getText(Language.ALREADY_NEWEST));
					if(al_sel == JOptionPane.YES_OPTION)
					{
						need_update = true;
					}
				}
			}
			if(need_update)
			{
				int getSelection = JOptionPane.showOptionDialog(upper, lang.getText(Language.CHECK_RELIABILITY) + "\n" + urlPath + "\n\nv" + String.valueOf(version_main) + "."
			+ String.valueOf(version_sub_1) + String.valueOf(version_sub_2) + String.valueOf(version_test) + " to v" + getStr + "\n"
			+ lang.getText(Language.DOYOUWANTTOUPDATE), lang.getText(Language.DOYOUWANTTOUPDATE), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
				if(getSelection == JOptionPane.YES_OPTION)
				{
					if(selectDialog != null)
						selectDialog.setVisible(false);
					this.setVisible(false);
					threadRun = false;
					
					SettingForRun sets;
					try
					{
						FileInputStream finst = new FileInputStream(setting.getDefault_path() + "run.crst");
						ObjectInputStream obinst = new ObjectInputStream(finst);
						sets = (SettingForRun) obinst.readObject();
						obinst.close();
						try
						{
							finst.close();
						} 
						catch (Exception e)
						{
							
						}
					} 
					catch (Exception e1)
					{
						sets = new SettingForRun();
					}
				
					
					if(! sets.isAuthorized())
					{
						System.out.println(sets.getLang().getFailed() + sets.getLang().getAuthorizing());
						sets = new SettingForRun();
					}
					
					sets.setVer_main(new Integer(getVm));
					sets.setVer_sub1(new Integer(getV1));
					sets.setVer_sub2(new Integer(getV2));
					
					sets.refresh_authorize();
					
					FileOutputStream foutst = new FileOutputStream(setting.getDefault_path() + "run.crst");
					ObjectOutputStream oboust = new ObjectOutputStream(foutst);
					oboust.writeObject(sets);
					oboust.close();
					try
					{
						foutst.close();
					} 
					catch (Exception e)
					{
						
					}
					
					Downloader.update(setting);
				}
			}
			/*
			else
			{
				if(already_notice)
				{
					JOptionPane.showMessageDialog(upper, lang.getText(Language.CHECK_RELIABILITY) + "\n" + urlPath + "\n\n" + lang.getText(Language.ALREADY_NEWEST));				
				}
			}
			*/
		}
		catch(Exception e1)
		{
			if(already_notice)
				JOptionPane.showMessageDialog(upper, lang.getText(Language.ERROR) + " : " + e1.getMessage());
			if(setting.isError_printDetail())
			{
				e1.printStackTrace();
			}
		}		
	}
	public JList getMessage_mode()
	{
		return message_mode;
	}
	public void setMessage_mode(JList message_mode)
	{
		this.message_mode = message_mode;
	}
	public Vector<String> getConsoleMode()
	{
		return consoleMode;
	}
	public void setConsoleMode(Vector<String> consoleMode)
	{
		this.consoleMode = consoleMode;
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(e.getSource() == titlePanel) 
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
			messageDialog.setLocation((int) this.getLocation().getX(), (int) (this.getLocation().getY() + (this.getHeight())));
		}		
		else if(e.getSource() == selectDialog_titlePanel) selectDialog.setLocation(e.getXOnScreen() - selectDialog_mousex, e.getYOnScreen() - selectDialog_mousey);
		else if(e.getSource() == finishDialog_upPanel) finishDialog.setLocation(e.getXOnScreen() - finishDialog_mousex, e.getYOnScreen() - finishDialog_mousey);
		else if(e.getSource() == serialDialog_titlePanel) serialDialog.setLocation(e.getXOnScreen() - serialDialog_mousex, e.getYOnScreen() - serialDialog_mousey);
		else if(e.getSource() == warnDialog_titlePanel) warnDialog.setLocation(e.getXOnScreen() - warn_mousex, e.getYOnScreen() - warn_mousey);
		else if(e.getSource() == scriptDialog_titlePanel) scriptDialog.setLocation(e.getXOnScreen() - script_mousex, e.getYOnScreen() - script_mousey);
		else if(e.getSource() == scenarioDialog_titlePanel) scenarioDialog.setLocation(e.getXOnScreen() - scenario_mousex, e.getYOnScreen() - scenario_mousey);
		else if(e.getSource() == themeDialog_titlePanel) themeDialog.setLocation(e.getXOnScreen() - theme_mousex, e.getYOnScreen() - theme_mousey);
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
		Object ob = e.getSource();
		if(ob == ver_serial)
		{
			int r, g, b;
			r = gradeColor.getRed();
			g = gradeColor.getGreen();
			b = gradeColor.getBlue();
			r = (int) (r / 1.3);
			g = (int) (g / 1.3);
			b = (int) (b / 1.3);
			ver_serial.setForeground(new Color(r, g, b));
		}
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == ver_serial)
		{
			ver_serial.setForeground(gradeColor);
		}
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
			selectDialog_mousex = e.getX();
			selectDialog_mousey = e.getY();
		}
		else if(ob == finishDialog_upPanel)
		{
			finishDialog_mousex = e.getX();
			finishDialog_mousey = e.getY();
		}
		else if(ob == finishDialog_code)
		{
			finishDialog_code.requestFocus();
		}
		else if(ob == ver_serial)
		{
			try
			{
				serialDialog.setVisible(true);
				serialDialog_keys[0].requestFocus();
			} 
			catch (Exception e1)
			{
				open_serialDialog = true;
			}
		}
		else if(ob == serialDialog_titlePanel)
		{
			serialDialog_mousex = e.getX();
			serialDialog_mousey = e.getY();
		}
		else if(ob == warnDialog_titlePanel)
		{
			warn_mousex = e.getX();
			warn_mousey = e.getY();
		}
		else if(ob == scriptDialog_titlePanel)
		{
			script_mousex = e.getX();
			script_mousey = e.getY();
		}
		else if(ob == scenarioDialog_titlePanel)
		{
			scenario_mousex = e.getX();
			scenario_mousey = e.getY();
		}
		else if(ob == themeDialog_titlePanel)
		{
			theme_mousex = e.getX();
			theme_mousey = e.getY();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		if(e.getSource() == this)
		{
			if(messageDialog.isVisible()) message_console.requestFocus();
		}
		
	}
	@Override
	public void windowClosed(WindowEvent arg0)
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
		else if(ob == selectDialog)
		{
			exit();
		}
		else if(ob == warnDialog)
		{
			warn_close();
		}
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		
		
	}
	@Override
	public void windowIconified(WindowEvent arg0)
	{
		
		
	}
	@Override
	public void windowOpened(WindowEvent arg0)
	{
		
		
	}
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Object ob = e.getSource();
		if(ob == selectDialog_tab)
		{
			int selectedIndex = selectDialog_tab.getSelectedIndex();
			if(setting.isNetworkEnabled())
			{				
				if(multiplay)
				{
					if(selectedIndex == 2)
					{
						scenarioDialog_list.setSelectedIndex(0);
						start.setEnabled(false);
						start2.setEnabled(false);
						selectDialog_authorityMode.setEnabled(false);
						selectDialog_replayMode.setEnabled(false);
						selectDialog_authorityMode.setSelected(false);
						selectDialog_replayMode.setSelected(false);
						selectDialog_randomOrder.setEnabled(false);
						selectDialog_scriptUse.setEnabled(false);
						selectDialog_scriptUse.setSelected(false);
						scenarioDialog_list.setEnabled(false);
					}
					else if(selectedIndex == 1) // single
					{
						start.setEnabled(false);
						start2.setEnabled(false);
						selectDialog_authorityMode.setEnabled(false);
						selectDialog_replayMode.setEnabled(false);
						selectDialog_authorityMode.setSelected(false);
						selectDialog_replayMode.setSelected(false);
						selectDialog_randomOrder.setEnabled(false);
						selectDialog_scriptUse.setEnabled(false);
						selectDialog_scriptUse.setSelected(false);
						scenarioDialog_list.setEnabled(false);
					}
					else
					{
						scenarioDialog_list.setSelectedIndex(0);
						start.setEnabled(false);
						start2.setEnabled(false);
						selectDialog_authorityMode.setEnabled(false);
						selectDialog_replayMode.setEnabled(false);
						selectDialog_authorityMode.setSelected(false);
						selectDialog_replayMode.setSelected(false);
						selectDialog_randomOrder.setEnabled(false);
						scenarioDialog_list.setEnabled(false);
						selectDialog_scriptUse.setEnabled(false);
						selectDialog_scriptUse.setSelected(false);
						
					}
				}
				else
				{
					if(selectedIndex == 2)
					{
						scenarioDialog_list.setSelectedIndex(0);
						start.setEnabled(false);
						start2.setEnabled(false);
						selectDialog_authorityMode.setEnabled(false);
						selectDialog_replayMode.setEnabled(false);
						selectDialog_replayMode.setSelected(false);
						selectDialog_randomOrder.setEnabled(false);
						scenarioDialog_list.setEnabled(false);
						selectDialog_scriptUse.setEnabled(false);
					}
					else if(selectedIndex == 1) // single
					{
						start.setEnabled(true);
						start2.setEnabled(true);
						selectDialog_authorityMode.setEnabled(true);
						if(! selectDialog_authorityMode.isSelected())
						{
							selectDialog_replayMode.setEnabled(true);
							selectDialog_randomOrder.setEnabled(true);
							selectDialog_scriptUse.setEnabled(true);
						}						
						scenarioDialog_list.setEnabled(true);
					}
					else
					{
						scenarioDialog_list.setSelectedIndex(0);
						start.setEnabled(false);
						start2.setEnabled(false);
						selectDialog_authorityMode.setEnabled(false);
						selectDialog_replayMode.setEnabled(false);
						selectDialog_authorityMode.setSelected(false);
						selectDialog_replayMode.setSelected(false);
						selectDialog_randomOrder.setEnabled(false);
						scenarioDialog_list.setEnabled(false);
						selectDialog_scriptUse.setEnabled(false);
					}
				}
			}
			else
			{
				if(selectedIndex == 2)
				{
					start.setEnabled(false);
					start2.setEnabled(false);
					selectDialog_authorityMode.setEnabled(false);
					selectDialog_replayMode.setEnabled(false);
					selectDialog_replayMode.setSelected(false);
					selectDialog_randomOrder.setEnabled(false);
					scenarioDialog_list.setEnabled(false);
					selectDialog_scriptUse.setEnabled(false);
					selectDialog_scriptUse.setSelected(false);
				}
				else if(selectedIndex == 1) // single
				{
					start.setEnabled(true);
					start2.setEnabled(true);
					selectDialog_authorityMode.setEnabled(true);
					if(! selectDialog_authorityMode.isSelected())
					{
						selectDialog_replayMode.setEnabled(true);
						selectDialog_randomOrder.setEnabled(true);
						selectDialog_scriptUse.setEnabled(true);
					}
					scenarioDialog_list.setEnabled(true);
				}
				else
				{
					start.setEnabled(false);
					start2.setEnabled(false);
					selectDialog_authorityMode.setEnabled(false);
					selectDialog_replayMode.setEnabled(false);
					selectDialog_authorityMode.setSelected(false);
					selectDialog_replayMode.setSelected(false);
					selectDialog_randomOrder.setEnabled(false);
					scenarioDialog_list.setEnabled(false);
					selectDialog_scriptUse.setEnabled(false);
					selectDialog_scriptUse.setSelected(false);
				}
			}
		}		
	}
	@Override
	public void run()
	{
		while(threadRun)
		{			
			try
			{
				finished = false;
				if(printDescriptDetail && (! printThreadMessageOnce))
				{
					message(lang.getText(Language.DESCRIPTIONS + 3));				
				}
				printThreadMessageOnce = true;
				if(! threadRun) break;
				if(getControlledOrder())
				{
					continue_ord = false;
					continue;
				}
				if(open_serialDialog)
				{
					open_serialDialog = false;
					serialDialog.setVisible(true);					
				}
				if(active)
				{						
					if(runAIInThread)
					{						
						runAI(0);	
					}
					if(! threadRun) break;
					//System.out.println("time_limit1 = " + time_limit_number1 + ", time_limit2 = " + time_limit_number2);
					if((active && (! isPaused())) && (! blocks.get(turn).isAi()))
					{						
						time_limit_number2--;
						if(time_limit_number2 <= 0)
						{
							time_limit_number2 = time_limit_number3;
							time_limit_number1--;
							time_limit.setValue(time_limit_number1);
							//System.out.println("o inThread " + isPaused());
						}				
						if(time_limit_number1 <= 0)
						{
							takeOneFromDeck(false);
							time_limit_number1 = 100;
							time_limit.setValue(time_limit_number1);
						}
					}
					if(! threadRun) break;
					if(active && (deck.size() == 0 || deck_empty || finished))
					{
						finish_game();
					}
				}				
				if(networkManager != null && (! networkManager.isOffed()) && networkManager.getQueue().size() >= 1)
				{
					network_work();
				}
				if(chatMode)
					chats.open();
			} 
			catch (Exception e1)
			{
				message(lang.getText(Language.ERROR) + " : " + e1.getMessage());
				TrackStorage.addTrack(this.getClass().getName(), e1);
				e1.printStackTrace();
			}			
			if(realTimeDelay >= 1000)
			{
				realTime();
				realTimeDelay = 0;
			}
			else
			{
				realTimeDelay++;
			}
			alives++;
			aliveBar.setValue(alives);
			/*
			if(alives % 5 == 0)
			{
				aliveBar.setValue(alives / 10);
			}
			*/
			if(alives >= 100)
			{
				alives = 0;
			}
			try
			{
				Thread.sleep(20);
			} 
			catch (InterruptedException e)
			{
				message(lang.getText(Language.ERROR) + " : " + e.getMessage());
				TrackStorage.addTrack(this.getClass().getName(), e);
				e.printStackTrace();
			}
		}
		printThreadMessageOnce = false;
	}
	
	// Multiplay Methods
	public void host()
	{
		if(networkManager == null) networkManager = new NetworkManager(this);
		try
		{
			if(networkManager.setHostMode(Integer.parseInt(selectDialog_multi_host.getText())))			
			{
				
				setMultiplayMode();
			}
			else setSinglePlayMode();
		} 
		catch (NumberFormatException e)
		{
			setSinglePlayMode();
		}		
	}
	public void join()
	{
		if(networkManager == null) networkManager = new NetworkManager(this);
		try
		{
			if(networkManager.setJoinMode(selectDialog_multi_ip.getText(), Integer.parseInt(selectDialog_multi_host.getText())))
			{
				
				setMultiplayMode();
			}				
			else setSinglePlayMode();			
		}
		catch(NumberFormatException e)
		{
			if(setting.isError_printDetail()) e.printStackTrace();
			TrackStorage.addTrack(this.getClass().getName(), e);
			setSinglePlayMode();
		}		
	}
	public synchronized void disconnect()
	{
		setSinglePlayMode();
	}
	public void setMultiplayMode()
	{		
		multiplay = true;
		selectDialog_multi_disconnect.setEnabled(true);
		int selectedIndex = selectDialog_tab.getSelectedIndex();
		if(selectedIndex == 1)
		{
			start.setEnabled(false);
			start2.setEnabled(false);
			selectDialog_authorityMode.setEnabled(false);
			selectDialog_replayMode.setEnabled(false);
			selectDialog_authorityMode.setSelected(false);
			selectDialog_replayMode.setSelected(false);
			if(active)
			{
				start.setEnabled(false);
				start2.setEnabled(false);
			}
			else
			{
				start.setEnabled(true);
				start2.setEnabled(true);
			}
		}
		else
		{
			start.setEnabled(false);
			start2.setEnabled(false);
			selectDialog_authorityMode.setEnabled(true);
			selectDialog_replayMode.setEnabled(true);
			start.setEnabled(false);
		}
		selectDialog_multi_host.setEnabled(false);
		selectDialog_multi_join.setEnabled(false);
		selectDialog_multi_ip.setEditable(false);
		selectDialog_multi_port.setEditable(false);
		
		
	}
	public void setSinglePlayMode()
	{
		multiplay = false;
		if(networkManager != null) networkManager.off();
		networkManager = null;
		selectDialog_multi_disconnect.setEnabled(false);
		int selectedIndex = selectDialog_tab.getSelectedIndex();
		if(selectedIndex == 1)
		{
			start.setEnabled(false);
			start2.setEnabled(false);
			selectDialog_authorityMode.setEnabled(false);
			selectDialog_replayMode.setEnabled(false);
			selectDialog_authorityMode.setSelected(false);
			selectDialog_replayMode.setSelected(false);
		}
		else
		{
			start.setEnabled(true);
			start2.setEnabled(true);
			selectDialog_authorityMode.setEnabled(true);
			selectDialog_replayMode.setEnabled(true);
		}
		selectDialog_multi_host.setEnabled(true);
		selectDialog_multi_join.setEnabled(true);
		selectDialog_multi_ip.setEditable(true);
		selectDialog_multi_port.setEditable(true);
	}
	public synchronized void network_work()
	{
		NetplayMessage message = networkManager.dequeue();
		int type = message.getType();
		int whois = 0;
		String sender_ip = message.getSender_ip();
		for(int i=0; i<networkManager.getGuests().size(); i++)
		{
			if(networkManager.getGuests().get(i).equals(sender_ip))
			{
				whois = i;
				break;
			}
		}
		if(whois == 1)
		{
			
		}
		switch(type)
		{
			case NetplayMessage.JOIN:
				
				
				break;
			case NetplayMessage.START:
				
				
				break;
			case NetplayMessage.READY:
				
				
				break;
			case NetplayMessage.CONTROL:
								
				break;
			case NetplayMessage.FINISH:
				
				
				break;
			case NetplayMessage.DISCONNECT:
				
				
				break;		
			case NetplayMessage.TEXT:
				
				break;
		}
	}
	public void net_start()
	{
		
	}
	
	
	public void refresh_room(String[] str, boolean[] ready_arr)
	{
		selectDialog_multi_playerPanel.removeAll();
		selectDialog_multi_playerPanel.setLayout(new GridLayout(str.length, 1));
		selectDialog_multi_playerPns = new JPanel[str.length];
		selectDialog_multi_playerIp = new JLabel[str.length];
		selectDialog_multi_playerReadied = new JCheckBox[str.length];		
		for(int i=0; i<str.length; i++)
		{
			selectDialog_multi_playerPns[i] = new JPanel();
			selectDialog_multi_playerPns[i].setLayout(new FlowLayout());
			selectDialog_multi_playerPns[i].setBorder(new EtchedBorder());
			if(str[i] != null)
			{						
				selectDialog_multi_playerIp[i] = new JLabel(str[i]);
				selectDialog_multi_playerReadied[i] = new JCheckBox();
				selectDialog_multi_playerReadied[i].setSelected(ready_arr[i]);
				selectDialog_multi_playerReadied[i].setEnabled(false);
				selectDialog_multi_playerPns[i].add(selectDialog_multi_playerIp[i]);
				selectDialog_multi_playerPns[i].add(selectDialog_multi_playerPns[i]);
			}
			selectDialog_multi_playerPanel.add(selectDialog_multi_playerPns[i]);
		}
	}
	@Override
	public void stop_game()
	{
		if(setting.getKind_ai().booleanValue())
		{
			if(aiActedDialog.isActive()) aiActedDialog.exit();
		}
		setActive(false);
		endDialog.setVisible(false);
		finishDialog.setVisible(false);
		takeButton.setEnabled(false);
		stop_game.setEnabled(false);			
		chatOk.setEnabled(true);
		chatSet.setEnabled(true);
		for(int i=0; i<centerSecondButtons.size(); i++)
		{
			centerSecondButtons.get(i).setEnabled(false);
		}
		//title_exit.setEnabled(false);
		for(int i=0; i<blocks.size(); i++)
		{
			blocks.get(i).getPay().setEnabled(false);
		}
		help2.setEnabled(false);
		if(! (setting.getLookAndFeel().equals(UIManager.getSystemLookAndFeelClassName()) && (setting.getOs().startsWith("Window") || setting.getOs().startsWith("window"))))
		{
			time_limit.setString(lang.getText(Language.TITLE));
		}
		selectDialog.setVisible(true);
		if(messageDialog.isVisible()) message_console.requestFocus();
	}
	@Override
	public void start_game()
	{
		start();
		
	}
	@Override
	public void takeCard()
	{
		if(isActive())
			takeOneFromDeck(false);		
	}
	@Override
	public void selectAuthorizeCheckbox()
	{
		if(selectDialog.isVisible())
			selectDialog_authorityMode.setSelected(! selectDialog_authorityMode.isSelected());
		
	}
	@Override
	public void selectPlayerSetting(int index, int value)
	{		
		if(selectDialog.isVisible())
		{
			switch(value)
			{
				case 0:
					selectDialog_select_none[index].setSelected(true);
					break;
				case 1:
					selectDialog_select_player[index].setSelected(true);
					break;
				case 2:
					selectDialog_select_ai[index].setSelected(true);
					break;
			}
		}
		
	}
	@Override
	public ScriptManager getScriptEngine()
	{
		if(scriptFactory == null) scriptFactory = new ScriptManager(setting, this, this, setting.getScript_engine(), this);	
		return scriptFactory;
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == scenarioDialog_list)
		{
			scenario_mode = scenarioDialog_list.getSelectedIndex();
			String texts = "";
			Difficulty difficulty = new Difficulty();
			if(scenario_mode == 0)
			{
				selectDialog_authorityMode.setSelected(false);
				selectDialog_authorityMode.setEnabled(true);
				difficulty.setDifficulty(new Integer(1));
				texts = lang.getText(Language.NO_SCENARIO_DESC);
				texts = lang.getText(Language.DIFFICULTY) + " : " + difficulty.toBasicString() + "\n\n" + texts;				
				scenarioDialog_descriptionPanel.setText(texts);
				scenarioDialog_descriptionPanel.setCaretPosition(0);
			}
			else if(scenario_mode >= 0 && scenario_mode < setting.getScenarios().size())
			{
				selectDialog_authorityMode.setSelected(true);
				selectDialog_authorityMode.setEnabled(false);	
				try
				{
					texts = setting.getScenarios().get(scenario_mode).getDescription();
					difficulty.setDifficulty(setting.getScenarios().get(scenario_mode).getDifficulty());					
					if(setting.getScenarios().get(scenario_mode) instanceof AuthorizedScenario)
					{
						AuthorizedScenario auts = (AuthorizedScenario) setting.getScenarios().get(scenario_mode);
						if(auts.getKoreanDescription() == null || auts.getKoreanDescription().equals(""))
						{
							
						}
						else
						{
							if(setting.getLang() instanceof Korean)
								texts = auts.getKoreanDescription();
						}
						
						if(auts.isAuthorized(setting))
							texts = texts + "\n\n" + lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.OK);
						else if(auts.getAuthorize_code().longValue() == 0)
							texts = texts + "\n\n" + lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.NONE);
						else
							texts = texts + "\n\n" + lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.FAIL);
					}
					texts = lang.getText(Language.DIFFICULTY) + " : " + difficulty.toBasicString() + "\n\n" + texts;
					
					if(setting.getScenarios().get(scenario_mode) instanceof ExtendedScenario)
					{
						ExtendedScenario exts = (ExtendedScenario) setting.getScenarios().get(scenario_mode);
						if(exts.getStar_line() != null)
						{
							texts = texts + "\n\n";
							for(int i=0; i<exts.getStar_line().length; i++)
							{
								texts = texts + Difficulty.starToString(i+1) + " : " + String.valueOf(exts.getStar_line()[i]) + "\n";
							}
						}
					}
					try
					{
						if(setting.getScenarios().get(scenario_mode) instanceof LankableScenario)
						{
							LankableScenario lks = (LankableScenario) setting.getScenarios().get(scenario_mode);
							if(lks.getLank().size() >= 1)
							{
								StringTokenizer lankTokenizer;
								String[] temps = new String[lks.getLank().size()];
								String tempa;
								for(int i=0; i<temps.length; i++)
								{
									temps[i] = lks.getLank().get(i);
								}
								BigInteger targetValue1, targetValue2;
								int sortedSize = 5;
								
								if(sortedSize >= lks.getLank().size()) 
									sortedSize = lks.getLank().size();
								
								try
								{
									for(int j=0; j<lks.getLank().size(); j++)
									{
										for(int i=1; i<j; i++)
										{
											try
											{
												lankTokenizer = new StringTokenizer(temps[j], lks.getLank_separator());
												targetValue1 = new BigInteger(lankTokenizer.nextToken());
												lankTokenizer = new StringTokenizer(temps[i], lks.getLank_separator());
												targetValue2 = new BigInteger(lankTokenizer.nextToken());
												if(targetValue1.compareTo(targetValue2) < 0)
												{
													tempa = temps[j];
													temps[j] = temps[i];
													temps[i] = tempa;
												}
											} 
											catch (Exception e1)
											{
												
											}
										}											
									}
								} 
								catch (Exception e1)
								{
									
								}
								texts = texts + "\n\n";
								for(int k=0; k<sortedSize; k++)
								{
									try
									{
										lankTokenizer = new StringTokenizer(temps[k], lks.getLank_separator());
										targetValue1 = new BigInteger(lankTokenizer.nextToken());
										texts = texts + targetValue1.toString() + " : " + lankTokenizer.nextToken() +"\n";
									} 
									catch (Exception e1)
									{
										
									}
									
								}
							}
						}
					} 
					catch (Exception e1)
					{
						
					}
					
					scenarioDialog_descriptionPanel.setText(texts);
					scenarioDialog_descriptionPanel.setCaretPosition(0);
				} 
				catch (Exception e1)
				{
					
				}
			}			
		}
		
	}
	public void default_scenario()
	{
		boolean added = false;
		Long[] newStarLine;
		if(grade_mode >= 0)
		{
			try
			{
				ProgressDialog.progress(0.70);
				Vector<Scenario> additionalScenario = new Vector<Scenario>();
							
				DetailedScenario tutorial = new DetailedScenario();
				tutorial.setName("Tutorial");
				tutorial.setKoreanName("Tutorial");
				VirtualBlock tutorialBlock;
				StandardCard tutorialDeckCard = null;
				tutorial.setStartBlocks(new Vector<VirtualBlock>());
				tutorial.setDeck(new Vector<StandardCard>());
				tutorial.setMessageIncludes(new Vector<MessageOnTurn>());
				
				tutorialBlock = new VirtualBlock(setting);
				tutorialBlock.setName("Student");
				Vector<StandardCard> tutorialDeck = new Vector<StandardCard>();
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 4));
				tutorialDeck.add(new StandardCard(setting.getOp_multiply(), 1));
				tutorialDeck.add(new StandardCard(setting.getOp_multiply(), 7));
				tutorialDeck.add(new StandardCard(setting.getOp_multiply(), 9));
				for(StandardCard sels : tutorialDeck)
					sels.setAce(false);
				tutorialBlock.setOwns(tutorialDeck);
				tutorialDeck = new Vector<StandardCard>();
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 3));
				for(StandardCard sels : tutorialDeck)
					sels.setAce(false);
				tutorialBlock.setPaids(tutorialDeck);				
				tutorial.getStartBlocks().add(tutorialBlock);
				
				tutorialBlock = new VirtualBlock(setting);
				tutorialBlock.setName("Tutor");
				tutorialDeck = new Vector<StandardCard>();
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 9));
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 3));
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 2));
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 2));
				for(StandardCard sels : tutorialDeck)
					sels.setAce(false);
				tutorialBlock.setOwns(tutorialDeck);
				tutorialDeck = new Vector<StandardCard>();
				tutorialDeck.add(new StandardCard(setting.getOp_plus(), 9));
				for(StandardCard sels : tutorialDeck)
					sels.setAce(false);
				tutorialBlock.setPaids(tutorialDeck);
				tutorial.getStartBlocks().add(tutorialBlock);	
				
				tutorial.setDescription("Learn how to play calc.");
				tutorial.setKoreanDescription("Calc 플레이하는 법을 알려 드립니다.");	
				for(int i=-1; i<10; i++)
				{
					for(int j=0; j<50; j++)
					{
						tutorialDeckCard = new StandardCard(setting.getOp_plus(), i);
						tutorialDeckCard.setAce(false);
						tutorial.getDeck().add((StandardCard) tutorialDeckCard.clone());
						tutorialDeckCard = new StandardCard(setting.getOp_minus(), i);
						tutorialDeckCard.setAce(false);
						tutorial.getDeck().add((StandardCard) tutorialDeckCard.clone());						
					}
					ProgressDialog.progress(0.70 + (0.002 * (i + 2)));
				}
				ProgressDialog.progress(0.72);
				tutorial.setDifficulty(new Integer(-100));				
				newStarLine = new Long[0];
				//newStarLine[0] = new Long(0);
				//newStarLine[1] = new Long(100);
				//newStarLine[2] = new Long(10000);
				tutorial.setStar_line(newStarLine);
				tutorial.setTimelimit(new Integer(10000));
				tutorial.setStartMessage("Welcome to learn Calc.\n\nYou won this game when you earn biggest point.\n\nYour point is made by \"paid\" cards.\nYou can see your \"paid\" card in your area, right side.\n\nOn the left side, you can see your own cards.\n\nOK. Select + 4 card on left list on your area, and pay it to your area using button on your area.");
				tutorial.setKorean_startMessage("튜토리얼에 오신 것을 환영합니다.\n\n이 게임에서 승리하려면 다른 플레이어보다 많은 점수를 획득해야 합니다.\n\n당신 앞에 놓인 카드들이 계산되어 당신의 점수가 됩니다.\n당신의 구역 오른쪽 리스트에서 당신 앞에 놓인 카드들을 볼 수 있습니다.\n\n왼쪽에서 당신이 보유한 카드들을 볼 수 있습니다.\n\n+ 4 카드를 당신의 구역에 놓아 보세요.\n먼저, 당신의 구역에 있는 왼쪽 리스트에서 + 4 카드를 선택한 후, \n당신의 구역에 있는 버튼을 클릭하시면 됩니다.");
				MessageOnTurn messageOnTurn = new MessageOnTurn(
						"+ 4 카드를 당신의 구역에 놓았습니까? 그렇다면 당신의 점수는 4점이 올라갑니다.\n"
						, "+ 4 카드를 당신의 구역에 놓았습니까? 그렇다면 당신의 점수는 4점이 올라갑니다.\n당신의 구역에 이미 + 3 카드가 있었음을 기억하십니까?\n\n이미 다른 카드가 놓여진 구역에는, \n마지막에 그 곳에 놓인 카드의 숫자 또는 기호가 같은 카드만을 놓을 수 있습니다.\n\n+ 3과 당신이 놓은 + 4 카드는 기호가 같으므로 놓을 수 있었던 것입니다.\n\n이 규칙은 대부분의 경우 적용되지만, 예외의 경우가 있습니다.\n\n기호에 상관없이 숫자가 1인 카드는 이러한 제약에 영향을 받지 않습니다.\n숫자도 1이 아니고 기호도 달라도 놓을 수 있는 특별한 능력을 가진 카드입니다.\n\n당신의 구역에 × 7 카드를 놓기 위해서는 × 1 카드를 먼저 놓아야겠지요?"
						, 1);
				tutorial.getMessageIncludes().add(messageOnTurn);
				messageOnTurn = new MessageOnTurn(
						"Now, you can use × card to improve your point.\nDo you decide between × 7 and × 9?\n\nNumber-7 card has another powerful ability.\nIf number-7 card is paid last time, only the owner of this area can pay next card.\n\nYou can use number-7 card to protect your area from - cards.\n\nSo, you can make your decision easily."
						, "× 1 카드를 놓았다면, 이제 × 기호를 가진 카드를 놓을 수 있겠군요.\n× 7과, × 9 카드 중 어떤 것을 먼저 내야 할까요?\n\n숫자 7인 카드 또한 엄청난 능력을 가지고 있습니다.\n어떤 구역에 숫자 7인 카드가 마지막에 놓였다면, 그 곳에는 그 곳의 주인만이 카드를 놓을 수 있게 됩니다.\n\n- 카드나 ×-1, ×0 카드를 이용한 공격으로부터 자신을 방어하는 데 숫자 7인 카드를 사용할 수 있는 것입니다.\n\n그러므로, × 7 카드를 먼저 사용하는 것이 좋겠지요?" 
						, 3);
				tutorial.getMessageIncludes().add(messageOnTurn);
				messageOnTurn = new MessageOnTurn(
						"Let\'s finish this game.\n\nIf one or many players don\'t have any card, the game is finished.\nThe player who has fewest cards earn 5 points at the last moment.\n\n5 is not big number. So, if you are in bad situation, you don\'t make the game finished.\nDon\'t you want to pay your card? Or, paying your card is impossible now? Then, you can take a card randomly from the deck and finish your turn.\nIf the deck is empty, the game is finished.\n\nAnyway, this time, you have × 9 card and you can win the game.\nFinish this game and try another scenario to improve your Calc skill !"
						, "이제 끝을 내야겠죠?\n\n어떤 하나 혹은 둘 이상의 플레이어가 보유한 카드가 없으면 게임이 끝납니다.\n마지막 순간 카드를 가장 적게 보유한 플레이어는 5점을 추가로 얻습니다.\n\n5점은 그리 큰 점수가 아니므로, 불리한 상황이라면 게임을 일부러 끝나지 않게 해야겠죠?\n카드를 놓기 싫거나 놓을 카드가 없다면, 덱 이라는 곳에 쌓여있는 카드들 중 한 장을 뽑아옴으로써 차례를 마칠 수도 있습니다.\n덱에는 카드들이 섞여 있으며, 선택해 뽑을 수는 없습니다.\n덱에 카드가 없어도 게임이 끝납니다.\n\n이 경우에는 × 9 카드를 당신의 구역에 놓기만 하면 엄청난 점수차로 승리할 수 있겠지요?\n일반 게임과 시나리오를 플레이하며 실력을 늘려 보세요." 
						, 5);
				tutorial.getMessageIncludes().add(messageOnTurn);
				tutorial.authorize(setting);
				additionalScenario.add(tutorial);
				
				ProgressDialog.progress(0.73);
				// What the X !
				ExtendedScenario basic_01 = new ExtendedScenario();
				basic_01.setName("What the X !");
				basic_01.setKoreanName("이게 뭔 X !");
				basic_01.setMultiply_card_ratio(new Integer(12));
				basic_01.setDifficulty(new Integer(230));
				basic_01.setDescription("There are multiply cards 3 times more than plus cards !\nYou cannot predict winner.");
				basic_01.setKoreanDescription("곱셈 카드가 덧셈 카드보다 세 배 더 많습니다 !\n마지막까지 승리를 절대 예측할 수 없을 것입니다.");		
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(10000);
				newStarLine[2] = new Long(1000000);
				basic_01.setStar_line(newStarLine);
				basic_01.authorize(setting);
				additionalScenario.add(basic_01);			
				
				ProgressDialog.progress(0.74);
				// It is too bored
				ExtendedScenario basic_02 = new ExtendedScenario();
				basic_02.setName("Fortunately, this is not too bored.");
				basic_02.setKoreanName("적어도 지루하지는 않아요");
				basic_02.setMultiply_card_ratio(new Integer(0));
				basic_02.setDifficulty(new Integer(-1));
				StandardCard onlyMultiplyCard1 = new StandardCard();
				onlyMultiplyCard1.setNum(1);
				onlyMultiplyCard1.setOp(setting.getOp_multiply());
				onlyMultiplyCard1.setAce(false);
				StandardCard onlyMultiplyCard2 = new StandardCard();
				onlyMultiplyCard2.setNum(2);
				onlyMultiplyCard2.setOp(setting.getOp_multiply());
				onlyMultiplyCard2.setAce(false);
				for(int i=0; i<16; i++)
				{
					if(i % 4 == 0)
					{
						basic_02.getDeck_additionals().add((StandardCard) onlyMultiplyCard1.clone());
					}
					basic_02.getDeck_additionals().add((StandardCard) onlyMultiplyCard2.clone());
				}
				basic_02.setDescription("There are only plus cards, minus cards, 1-numbered multiply cards and 2-numbered multiply cards.\nYou can feel relax !");
				basic_02.setKoreanDescription("곱셈 카드는 숫자가 1 또는 2 밖에 없습니다.\n이제 마음 놓고 편안하게 즐기세요 !");
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(1000);
				newStarLine[2] = new Long(10000);
				basic_02.setStar_line(newStarLine);
				basic_02.authorize(setting);
				additionalScenario.add(basic_02);
				
				ProgressDialog.progress(0.75);
				// Third Party
				ExtendedScenario basic_03 = new ExtendedScenario();
				basic_03.setName("Third Party");
				basic_03.setKoreanName("제 3자");
				basic_03.setDifficulty(new Integer(15000));
				basic_03.setDescription("How about watching others arguments?");
				basic_03.setKoreanDescription("남들이 싸우는 것만 구경하다가 나서는 것도 재미있습니다.");							
				basic_03.setAi_cards_count(new Integer(0));
				StandardCard superCard = new StandardCard();
				superCard.setOp(setting.getOp_multiply());
				superCard.setNum(-1);
				superCard.setAce(false);
				StandardCard superCard2 = new StandardCard();
				superCard2.setOp(setting.getOp_multiply());
				superCard2.setNum(9);
				superCard2.setAce(false);
				for(int i=0; i<5; i++)
				{
					basic_03.getAi_cards().add((StandardCard) superCard.clone());
					basic_03.getAi_cards().add((StandardCard) superCard2.clone());
				}
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(1000);
				newStarLine[2] = new Long(100000);
				basic_03.setStar_line(newStarLine);
				basic_03.authorize(setting);
				additionalScenario.add(basic_03);
				
				
				ProgressDialog.progress(0.76);
				// Very Easy
				
				ExtendedScenario basic_04 = new ExtendedScenario();
				basic_04.setName("Very Easy");
				basic_04.setKoreanName("정말 쉽네요 !");
				basic_04.setDifficulty(new Integer(-50));
				basic_04.setAi_cards_count(new Integer(20));
				basic_04.setDescription("Piece of cake, usually.");
				basic_04.setKoreanDescription("이 게임은 매우 쉽습니다. 대부분의 경우에는 말이지요.");				
				basic_04.getPlayer_cards().add((StandardCard) superCard.clone());
				basic_04.getPlayer_cards().add((StandardCard) superCard2.clone());
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(100000);
				newStarLine[2] = new Long(10000000);
				basic_04.setStar_line(newStarLine);
				basic_04.authorize(setting);
				additionalScenario.add(basic_04);
				
				
				ProgressDialog.progress(0.77);
				ExtendedScenario basic_05 = new ExtendedScenario();
				basic_05.setName("Consume your card immediately !");
				basic_05.setKoreanName("카드를 지금 즉시 버리세요 !");
				basic_05.setDifficulty(new Integer(50));
				basic_05.setBonuses(new Integer(1000));
				basic_05.setDescription("The player who has least number of cards will take great bonus point at the end.");
				basic_05.setKoreanDescription("게임이 끝났을 때 카드를 가장 적게 소유한 플레이어는 상당한 보너스 점수를 받습니다.");
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(1100);
				newStarLine[2] = new Long(10000);
				basic_05.setStar_line(newStarLine);
				basic_05.authorize(setting);
				additionalScenario.add(basic_05);
				
				
				ProgressDialog.progress(0.78);
				ExtendedScenario basic_06 = new ExtendedScenario();
				basic_06.setName("Adder, Subtractor, and then, Multiplier");
				basic_06.setKoreanName("더하는 놈, 빼는 놈, 곱하는 놈");
				basic_06.setDescription("There are only 7 cards. You should use your cards only for you !");
				basic_06.setKoreanDescription("숫자가 7인 카드만 나옵니다. 즉, 모든 카드를 자신에게만 쓸 수 있다는 뜻이죠 !");
				basic_06.setDifficulty(new Integer(125));
				basic_06.setPlayer_cards_count(new Integer(20));
				basic_06.setAi_cards_count(new Integer(20));
				basic_06.setPlusminus_card_ratio(new Integer(0));
				basic_06.setMultiply_card_ratio(new Integer(0));
				StandardCard seven01, seven02, seven03;
				seven01 = new StandardCard(setting.getOp_plus(), 7);
				seven01.setAce(false);
				seven02 = new StandardCard(setting.getOp_minus(), 7);
				seven02.setAce(false);
				seven03 = new StandardCard(setting.getOp_multiply(), 7);
				seven03.setAce(false);
				for(int i=0; i<100; i++)
				{
					basic_06.getDeck_additionals().add((StandardCard) seven01.clone());
					basic_06.getDeck_additionals().add((StandardCard) seven02.clone());
					basic_06.getDeck_additionals().add((StandardCard) seven03.clone());
				}
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(1000);
				newStarLine[2] = new Long(10000);
				basic_06.authorize(setting);
				additionalScenario.add(basic_06);
				
				ExtendedScenario basic_07 = new ExtendedScenario();
				basic_07.setName("Find the treasure !");
				basic_07.setKoreanName("보물 찾기");
				basic_07.setDescription("You can\'t find good card easily.");
				basic_07.setKoreanDescription("좋은 카드는 매우 찾기 어려울 것입니다.");
				basic_07.setDifficulty(new Integer(1250));
				basic_07.setPlayer_cards_count(new Integer(20));
				basic_07.setAi_cards_count(new Integer(20));
				basic_07.setPlusminus_card_ratio(new Integer(0));
				basic_07.setMultiply_card_ratio(new Integer(0));
				StandardCard preciousCard;
				int exp_lim = 0;
				for(int k=2; k<=9; k++)
				{
					exp_lim = 2048;
					for(int i=0; i<k; i++)
					{
						exp_lim /= 2;
					}
					for(int i=0; i<exp_lim; i++)
					{
						preciousCard = new StandardCard(setting.getOp_minus(), k);
						preciousCard.setAce(false);										
						basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());
						preciousCard = new StandardCard(setting.getOp_plus(), k);
						preciousCard.setAce(false);										
						basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());
						if(i % 4 == 0)
						{
							preciousCard = new StandardCard(setting.getOp_multiply(), k);
							preciousCard.setAce(false);										
							basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());
						}
					}
				}				
				preciousCard = new StandardCard(setting.getOp_plus(), 1);
				preciousCard.setAce(false);										
				basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());
				for(int i=0; i<128; i++)
				{
					preciousCard = new StandardCard(setting.getOp_plus(), 1);
					preciousCard.setAce(false);										
					basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());
				}				
				preciousCard = new StandardCard(setting.getOp_multiply(), -1);
				preciousCard.setAce(false);										
				basic_07.getDeck_additionals().add((StandardCard) preciousCard.clone());				
				newStarLine = new Long[3];
				newStarLine[0] = new Long(0);
				newStarLine[1] = new Long(100);
				newStarLine[2] = new Long(10000);
				basic_07.authorize(setting);
				additionalScenario.add(basic_07);
				
				if(grade_mode >= 1) // Only for Professional Edition
				{
					// Slow, please !
					ExtendedScenario professional_01 = new ExtendedScenario();
					professional_01.setName("Slow, please !");
					professional_01.setKoreanName("벌써 끝났어요?");
					professional_01.setAi_cards_count(new Integer(5));
					professional_01.setPlayer_cards_count(new Integer(10));
					professional_01.setDifficulty(new Integer(502));
					professional_01.setChange_card_count(new Integer(0));
					professional_01.setDescription("The other ai\'s will takes less cards when the game starts.\nCan you earn more points before the game finished?");
					professional_01.setKoreanDescription("상대방은 카드를 더 적게 가지고 시작합니다.\n순식간에 패배로 끝나기 전에 점수 차이를 벌릴 수 있을까요?");		
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(50);
					newStarLine[2] = new Long(200);
					professional_01.setStar_line(newStarLine);
					professional_01.authorize(setting);
					additionalScenario.add(professional_01);
					// Minus War
					ExtendedScenario professional_02 = new ExtendedScenario();
					professional_02.setName("Minus War");
					professional_02.setKoreanName("음수 전쟁");
					professional_02.setPlusminus_card_ratio(new Integer(0));
					professional_02.setDifficulty(new Integer(250));
					professional_02.setDescription("There are minus cards much more than plus cards !\nThe player will be won if the player has small absolute number of points.\n\nOh, do you have that card?\nThen, it is not predictable !");
					professional_02.setKoreanDescription("뺄셈 카드가 덧셈 카드보다 훨씬 더 많습니다 !\n눈에 보이는 숫자가 작을 수록 유리하겠죠?\n\n아, 그 카드가 있다구요? 그러면 또 모르겠네요 !");
					
					for(int i=0; i<10; i++)
					{
						StandardCard newCard = new StandardCard();
						newCard.setOp('-');
						newCard.setNum(i);
						newCard.setAce(false);
						StandardCard newCard2 = new StandardCard();
						newCard2.setOp('+');
						newCard2.setNum(i);
						newCard2.setAce(false);
						for(int j=0; j<4; j++)
						{
							professional_02.getDeck_additionals().add((StandardCard) newCard.clone());
							if(j % 4 == 0)
								professional_02.getDeck_additionals().add((StandardCard) newCard2.clone());
						}
					}
					newStarLine = new Long[3];
					newStarLine[0] = new Long(-100);
					newStarLine[1] = new Long(0);
					newStarLine[2] = new Long(1000);
					professional_02.setStar_line(newStarLine);
					professional_02.authorize(setting);
					additionalScenario.add(professional_02);
					
					// Change, and change !
					ExtendedScenario professional_03 = new ExtendedScenario();
					professional_03.setName("Change, and change !");
					professional_03.setKoreanName("바꾸고, 또 바꾸고");
					professional_03.setDifficulty(new Integer(25));
					professional_03.setChange_card_count(new Integer(50));
					professional_03.setDescription("Again?\nHow many change cards?");
					professional_03.setKoreanDescription("또 바꾸자구요?\n또?\n이게 몇 번째야?");	
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(1000);
					newStarLine[2] = new Long(100000);
					professional_03.setStar_line(newStarLine);
					professional_03.authorize(setting);
					additionalScenario.add(professional_03);
					
					// Click now !
					ExtendedScenario professional_04 = new ExtendedScenario();
					professional_04.setName("Click now !");
					professional_04.setKoreanName("지금 바로 뭔가를 하세요, 당장 !");
					professional_04.setDifficulty(new Integer(490));
					professional_04.setTimelimit(new Integer(2));
					professional_04.setDescription("Slow, please !");
					professional_04.setKoreanDescription("아 좀 천천히 가요 !");	
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(1000);
					newStarLine[2] = new Long(100000);
					professional_04.setStar_line(newStarLine);
					professional_04.authorize(setting);
					additionalScenario.add(professional_04);
					
					// Reverse Party
					ExtendedScenario professional_05 = new ExtendedScenario();
					professional_05.setName("Reverse Party");
					professional_05.setKoreanName("대반전 파티");
					professional_05.setDifficulty(new Integer(19999));
					professional_05.setMultiply_card_ratio(new Integer(0));
					StandardCard superCard3 = new StandardCard();
					superCard3.setAce(false);
					superCard3.setOp(setting.getOp_multiply());
					superCard3.setNum(1);
					StandardCard superCard4 = new StandardCard();
					superCard4.setAce(false);
					superCard4.setOp(setting.getOp_multiply());
					superCard4.setNum(-10);
					for(int i=0; i<40; i++)
					{
						if(i % 4 == 0)
							professional_05.getDeck_additionals().add((StandardCard) superCard3.clone());
						professional_05.getDeck_additionals().add((StandardCard) superCard4.clone());
					}
					professional_05.setKoreanDescription("곱셈 카드는 숫자가 1 또는 -10 밖에 없습니다 !\n점수를 늘리려면 곱셈을 쓰기는 써야겠죠?");
					professional_05.setDescription("All multiply cards have number 1 or -10 !");					
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(100000);
					newStarLine[2] = new Long(10000000);
					professional_05.setStar_line(newStarLine);
					professional_05.authorize(setting);
					additionalScenario.add(professional_05);
					
					// Wait, and wait
					ExtendedScenario professional_06 = new ExtendedScenario();
					professional_06.setDifficulty(12800);
					professional_06.setName("Wait, and wait");
					professional_06.setKoreanName("끈기를 가져 봐");
					professional_06.setMultiply_card_ratio(new Integer(0));
					professional_06.setAi_cards_count(new Integer(0));
					professional_06.setPlayer_cards_count(new Integer(0));
					StandardCard multiplyCards1;
					for(int i=1; i<=9; i++)
					{
						if(i != 7)
						{
							multiplyCards1 = new StandardCard();
							multiplyCards1.setAce(false);
							multiplyCards1.setNum(i);
							multiplyCards1.setOp(setting.getOp_multiply());						
							professional_06.getAi_cards().add((StandardCard) multiplyCards1.clone());
							professional_06.getPlayer_cards().add((StandardCard) multiplyCards1.clone());
						}
					}
					StandardCard minusOneCard = new StandardCard();
					minusOneCard.setAce(false);
					minusOneCard.setNum(-1);
					minusOneCard.setOp(setting.getOp_multiply());			
					for(int i=0; i<2; i++)
					{
						professional_06.getAi_cards().add((StandardCard) minusOneCard.clone());
					}
					StandardCard plusSimpleCard = new StandardCard();
					plusSimpleCard.setAce(false);
					plusSimpleCard.setNum(1);
					plusSimpleCard.setOp(setting.getOp_plus());
					for(int i=0; i<3; i++)
					{
						professional_06.getPlayer_cards().add((StandardCard) plusSimpleCard.clone());
					}
					professional_06.setKoreanDescription("점수는 나중에라도 올릴 수 있습니다.");
					professional_06.setDescription("Do not hurry if you earn your point well !");
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(100);
					newStarLine[2] = new Long(10000);
					professional_06.setStar_line(newStarLine);
					professional_06.authorize(setting);
					additionalScenario.add(professional_06);
					
					// This is different whenever
					ExtendedScenario professional_07 = new ExtendedScenario();
					professional_07.setDifficulty(50);
					professional_07.setName("This is different whenever");
					professional_07.setKoreanName("언제나 다른 것");
					double randoms = Math.random() * 1000 + 200;
					int random_decks = (int) randoms;
					professional_07.setRandom_multiply_use(new Boolean(true));
					professional_07.setRandom_multiply_min(new Integer(4));
					professional_07.setRandom_multiply_max(new Integer(8));
					professional_07.setRandom_plus_use(new Boolean(true));
					professional_07.setRandom_plus_min(new Integer(4));
					professional_07.setRandom_plus_max(new Integer(8));
					randoms = Math.random() * 20 + 1;
					random_decks = (int) randoms;
					professional_07.setRandom_player_cards_use(new Boolean(true));
					professional_07.setRandom_player_cards_max(new Integer(20));
					professional_07.setRandom_player_cards_min(new Integer(15));
					professional_07.setAi_cards_count(new Integer(random_decks));
					professional_07.setPlayer_cards_count(new Integer(random_decks));
					professional_07.setKoreanDescription("모든 것이 예측 가능하다고요? 그 반례가 여기 있습니다 !");
					professional_07.setDescription("Do you believe you can predict everything? Here is counter example for you !");					
					professional_07.authorize(setting);
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(1000);
					newStarLine[2] = new Long(100000);
					professional_07.setStar_line(newStarLine);
					additionalScenario.add(professional_07);
					
					// Tycoon
					ExtendedScenario professional_08 = new ExtendedScenario();
					professional_08.setDifficulty(5);
					professional_08.setName("Tycoon");
					professional_08.setKoreanName("타이쿤");
					professional_08.setMultiply_card_ratio(new Integer(0));
					professional_08.setPlusminus_card_ratio(new Integer(0));
					professional_08.setChange_card_count(new Integer(0));
					for(int i=1; i<19; i++)
					{
						plusSimpleCard = new StandardCard();
						plusSimpleCard.setAce(false);
						plusSimpleCard.setNum(i);
						plusSimpleCard.setOp(setting.getOp_plus());
						for(int j=0; j<12; j++)
							professional_08.getDeck_additionals().add((StandardCard) plusSimpleCard.clone());
						plusSimpleCard = new StandardCard();
						plusSimpleCard.setAce(false);
						plusSimpleCard.setNum(i);
						plusSimpleCard.setOp(setting.getOp_multiply());
						for(int j=0; j<4; j++)
							professional_08.getDeck_additionals().add((StandardCard) plusSimpleCard.clone());
					}
					professional_08.setAi_cards_count(new Integer(30));
					professional_08.setPlayer_cards_count(new Integer(30));
					professional_08.setKoreanDescription("즐기고 있나요? 아닌가요?");
					professional_08.setDescription("Do you playing? No?");	
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(50);
					newStarLine[2] = new Long(1000);
					professional_08.setStar_line(newStarLine);
					professional_08.authorize(setting);
					additionalScenario.add(professional_08);
					
					ExtendedScenario professional_09 = new ExtendedScenario();
					professional_09.setName("Find more treasure !");
					professional_09.setKoreanName("보물 더 찾기");
					professional_09.setDescription("There are very few good cards inside another poor cards.");
					professional_09.setKoreanDescription("흔한 카드들 속에 좋은 카드가 극소량 섞여있습니다.");
					professional_09.setDifficulty(new Integer(12500));
					professional_09.setPlayer_cards_count(new Integer(20));
					professional_09.setAi_cards_count(new Integer(20));
					professional_09.setPlusminus_card_ratio(new Integer(0));
					professional_09.setMultiply_card_ratio(new Integer(0));
					exp_lim = 0;
					for(int k=2; k<=9; k++)
					{
						exp_lim = 262144;
						for(int i=0; i<k; i++)
						{
							exp_lim /= 4;
						}
						for(int i=0; i<exp_lim; i++)
						{
							preciousCard = new StandardCard(setting.getOp_minus(), k);
							preciousCard.setAce(false);										
							professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
							preciousCard = new StandardCard(setting.getOp_plus(), k);
							preciousCard.setAce(false);										
							professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
							if(i % 8 == 0)
							{
								preciousCard = new StandardCard(setting.getOp_multiply(), k);
								preciousCard.setAce(false);										
								professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
							}
						}
					}				
					preciousCard = new StandardCard(setting.getOp_plus(), 1);
					preciousCard.setAce(false);										
					professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
					for(int i=0; i<128; i++)
					{
						preciousCard = new StandardCard(setting.getOp_plus(), 1);
						preciousCard.setAce(false);										
						professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
					}				
					preciousCard = new StandardCard(setting.getOp_multiply(), -1);
					preciousCard.setAce(false);										
					professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());
					professional_09.getDeck_additionals().add((StandardCard) preciousCard.clone());	
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(100);
					newStarLine[2] = new Long(10000);
					professional_09.authorize(setting);
					additionalScenario.add(professional_09);
				}
				ProgressDialog.progress(0.79);
				if(grade_mode >= 2) // Only for Ultimate Edition
				{
					// It is insane !
					ExtendedScenario ultimate_01 = new ExtendedScenario();
					ultimate_01.setName("It is insane !");
					ultimate_01.setKoreanName("제정신이 아니야 !");
					ultimate_01.setMultiply_card_ratio(new Integer(0));
					ultimate_01.setDifficulty(new Integer(1000));
					ultimate_01.setDescription("Very high numbers are seen at multiply cards.\nDo you want to use these multiply cards?\nIt is very dangerous !");
					ultimate_01.setKoreanDescription("곱셈 카드의 숫자가 심상치 않습니다 !\n위험 부담을 무릅쓰고 곱셈 카드를 사용해야 할까요?");					
					StandardCard[] addCards = new StandardCard[10];
					for(int i=0; i<addCards.length; i++)
					{
						addCards[i] = new StandardCard();
						addCards[i].setNum(i + 10);
						addCards[i].setOp(setting.getOp_multiply());
						addCards[i].setAce(false);
					}
					
					for(int i=0; i<4; i++)
					{
						for(int j=0; j<addCards.length; j++)
							basic_02.getDeck_additionals().add((StandardCard) addCards[j].clone());
					}
					StandardCard addCard = new StandardCard();
					addCard.setNum((-1));
					addCard.setOp(setting.getOp_multiply());
					addCard.setAce(false);
					StandardCard addCard2 = new StandardCard();
					addCard2.setNum(0);
					addCard2.setOp(setting.getOp_multiply());
					addCard2.setAce(false);
					for(int i=0; i<16; i++)
						basic_02.getDeck_additionals().add((StandardCard) addCard2.clone());
					
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(100000);
					newStarLine[2] = new Long(10000000);
					ultimate_01.setStar_line(newStarLine);
					ultimate_01.authorize(setting);
					additionalScenario.add(ultimate_01);
					
					// Binary
					ExtendedScenario ultimate_02 = new ExtendedScenario();
					ultimate_02.setName("Binary");
					ultimate_02.setKoreanName("이진법");
					ultimate_02.setPlusminus_card_ratio(new Integer(0));
					ultimate_02.setMultiply_card_ratio(new Integer(0));
					ultimate_02.setDifficulty(new Integer(5));
					StandardCard plusCard = new StandardCard();
					plusCard.setNum(0);
					plusCard.setOp(setting.getOp_plus());
					plusCard.setAce(false);
					StandardCard minusCard = new StandardCard();
					minusCard.setNum(0);
					minusCard.setOp(setting.getOp_minus());
					minusCard.setAce(false);
					StandardCard multiplyCard = new StandardCard();
					multiplyCard.setNum(0);
					multiplyCard.setOp(setting.getOp_multiply());
					multiplyCard.setAce(false);
					StandardCard plusCard2 = new StandardCard();
					plusCard2.setNum(1);
					plusCard2.setOp(setting.getOp_plus());
					plusCard2.setAce(false);
					StandardCard minusCard2 = new StandardCard();
					minusCard2.setNum(1);
					minusCard2.setOp(setting.getOp_minus());
					minusCard2.setAce(false);
					StandardCard multiplyCard2 = new StandardCard();
					multiplyCard2.setNum(1);
					multiplyCard2.setOp(setting.getOp_multiply());
					multiplyCard2.setAce(false);
					ultimate_02.setDescription("Is it binary? Yes, there are only 0 or 1.");
					ultimate_02.setKoreanDescription("이진수인가요? 예. 여기에는 0과 1밖에 없습니다 !");					
					for(int i=0; i<16; i++)
					{
						ultimate_02.getDeck_additionals().add((StandardCard) plusCard.clone());
						ultimate_02.getDeck_additionals().add((StandardCard) minusCard.clone());
						ultimate_02.getDeck_additionals().add((StandardCard) multiplyCard.clone());
						ultimate_02.getDeck_additionals().add((StandardCard) plusCard2.clone());
						ultimate_02.getDeck_additionals().add((StandardCard) minusCard2.clone());
						ultimate_02.getDeck_additionals().add((StandardCard) multiplyCard2.clone());
					}
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(50);
					newStarLine[2] = new Long(1000);
					ultimate_02.setStar_line(newStarLine);
					ultimate_02.authorize(setting);
					additionalScenario.add(ultimate_02);
					
					// LTE
					ExtendedScenario ultimate_03 = new ExtendedScenario();
					ultimate_03.setName("LTE (Long Term Evolution)");
					ultimate_03.setKoreanName("LTE (Long Term Evolution)");
					ultimate_03.setPlusminus_card_ratio(new Integer(0));
					ultimate_03.setMultiply_card_ratio(new Integer(0));
					ultimate_03.setChange_card_count(new Integer(0));
					ultimate_03.setDifficulty(new Integer(52));
					StandardCard onePlus = new StandardCard(setting.getOp_plus(), 1);
					StandardCard oneMult = new StandardCard(setting.getOp_multiply(), 1);
					StandardCard extr = new StandardCard(setting.getOp_multiply(), 2);
					onePlus.setAce(false);
					oneMult.setAce(false);
					extr.setAce(false);
					for(int i=0; i<1000; i++)
					{
						ultimate_03.getDeck_additionals().add((StandardCard) onePlus.clone());
						ultimate_03.getDeck_additionals().add((StandardCard) oneMult.clone());
						if(i % 10 == 0) 
							ultimate_03.getDeck_additionals().add((StandardCard) extr.clone()); 
					}			
					ultimate_03.setDescription("Relax. You can evolve.");
					ultimate_03.setKoreanDescription("진정하세요. 당신은 진화할 수 있어요.");
					newStarLine = new Long[3];
					newStarLine[0] = new Long(0);
					newStarLine[1] = new Long(50);
					newStarLine[2] = new Long(1000);
					ultimate_03.setStar_line(newStarLine);
					ultimate_03.authorize(setting);
					additionalScenario.add(ultimate_03);
					
					
				}
				
				if(grade_mode >= 3) // Only for Master Edition
				{
					ExtendedScenario master_01 = new ExtendedScenario();
					master_01.setName("Treasure Hunter");
					master_01.setKoreanName("Treasure Hunter");
					master_01.setDescription("You have to wait a long time to find good cards.");
					master_01.setKoreanDescription("좋은 카드를 찾으려면 기나긴 시간이 필요합니다.");
					master_01.setDifficulty(new Integer(12500));
					master_01.setPlayer_cards_count(new Integer(100));
					master_01.setAi_cards_count(new Integer(100));
					master_01.setPlusminus_card_ratio(new Integer(0));
					master_01.setMultiply_card_ratio(new Integer(0));
					exp_lim = 0;
					for(int k=2; k<=9; k++)
					{
						exp_lim = 262144;
						for(int i=0; i<k; i++)
						{
							exp_lim /= 4;
						}
						for(int i=0; i<exp_lim; i++)
						{
							preciousCard = new StandardCard(setting.getOp_minus(), k);
							preciousCard.setAce(false);										
							master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
							preciousCard = new StandardCard(setting.getOp_plus(), k);
							preciousCard.setAce(false);										
							master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
							if(i % 16 == 0)
							{
								preciousCard = new StandardCard(setting.getOp_multiply(), k);
								preciousCard.setAce(false);										
								master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
							}
						}
					}				
					preciousCard = new StandardCard(setting.getOp_plus(), 1);
					preciousCard.setAce(false);										
					master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
					for(int i=0; i<1024; i++)
					{
						preciousCard = new StandardCard(setting.getOp_plus(), 1);
						preciousCard.setAce(false);										
						master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
					}				
					preciousCard = new StandardCard(setting.getOp_multiply(), -1);
					preciousCard.setAce(false);										
					master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());
					master_01.getDeck_additionals().add((StandardCard) preciousCard.clone());	
					newStarLine = new Long[3];
					newStarLine[0] = new Long(100);
					newStarLine[1] = new Long(10000);
					newStarLine[2] = new Long(1000000);
					master_01.authorize(setting);
					additionalScenario.add(master_01);
				}
				
				ProgressDialog.progress(0.80);
				InputStream inputs = null;
				InputStreamReader inputReader = null;
				BufferedReader bfread = null;
				DataInputStream din = null;
				XMLDecoder decoder = null;
				
				try
				{
					File directory = new File(setting.getDefault_path());
					File[] lists = directory.listFiles();
					String fileName = "";
					AuthorizedScenarioPackage loadedPackage = null;
					for(int i=0; i<lists.length; i++)
					{
						fileName = lists[i].getAbsolutePath();
						if(fileName.endsWith(".scen") || fileName.endsWith(".SCEN"))
						{
							try
							{
								inputs = new FileInputStream(lists[i]);
								if(din_use)
								{
									din = new DataInputStream(inputs);
									decoder = new XMLDecoder(din);
								}
								else
								{
									decoder = new XMLDecoder(inputs);
								}
								loadedPackage = (AuthorizedScenarioPackage) decoder.readObject();
								if(loadedPackage.isAuthorized() && (loadedPackage.getEdition() <= grade_mode))
								{
									for(int j=0; j<loadedPackage.getScenarios().size(); j++)
									{
										additionalScenario.add(loadedPackage.getScenarios().get(j));
									}
								}
							} 
							catch (Exception e)
							{
								
							}
							try
							{
								decoder.close();								
							}
							catch(Exception e2)
							{
								
							}
							try
							{
								din.close();
							}
							catch(Exception e2)
							{
								
							}
							try
							{
								inputs.close();
							}
							catch(Exception e2)
							{
								
							}
						}
						else if(fileName.endsWith(".scnx") || fileName.endsWith(".SCNX"))
						{
							String savingSc = "";
							String savingTemp = "";
							try
							{
								inputs = new FileInputStream(lists[i]);
								if(din_use)
								{
									din = new DataInputStream(inputs);
									bfread = new BufferedReader(new InputStreamReader(din));
								}
								else
								{
									bfread = new BufferedReader(new InputStreamReader(inputs));
								}
								
								while(true)
								{
									savingTemp = bfread.readLine();
									if(savingTemp == null) break;
									savingSc = savingSc + savingTemp + "\n";
								}
								Scenario makeSc = Scenario.stringToScenario(savingSc);
								if(makeSc != null)
									additionalScenario.add(makeSc);
							}
							catch(Exception e)
							{
								
							}
							try
							{
								bfread.close();
								
							}
							catch(Exception e2)
							{
								
							}
							try
							{
								din.close();
								
							}
							catch(Exception e2)
							{
								
							}
							try
							{
								inputs.close();
							}
							catch(Exception e2)
							{
								
							}
						}
						ProgressDialog.progress(0.80 + (((double) i / lists.length) * 0.1));
					}
				}
				catch(Exception e)
				{
					
				}
				
				ProgressDialog.progress(0.90);
				if(grade_mode >= 1) // Download Online Contents
				{
					
					try
					{
						ScenarioPackage scenario_gets = null;
						String fileName = "additional_scenario.scen";
						URL scenario_url = new URL(setting.getNotice_url() + fileName);
						inputs = scenario_url.openStream();
						if(din_use)
						{
							din = new DataInputStream(inputs);
							decoder = new XMLDecoder(din);							
						}
						else
						{
							decoder = new XMLDecoder(inputs);
						}
						
						scenario_gets = (ScenarioPackage) decoder.readObject();					
						
						for(int i=0; i<scenario_gets.getScenarios().size(); i++)
						{
							additionalScenario.add(scenario_gets.getScenarios().get(i));
						}					
					}
					catch(Exception e)
					{
						try
						{
							decoder.close();
						}
						catch(Exception e1)
						{
							
						}
						try
						{
							din.close();
						}
						catch(Exception e1)
						{
							
						}
						try
						{
							inputs.close();
						}
						catch(Exception e1)
						{
							
						}
						try
						{
							ScenarioPackage scenario_gets = null;
							String fileName = "additional_scenario.scen";
							URL scenario_url = new URL(setting.getNotice_url2() + fileName);
							inputs = scenario_url.openStream();
							if(din_use)
							{
								din = new DataInputStream(inputs);
								decoder = new XMLDecoder(din);
							}
							else
							{
								decoder = new XMLDecoder(inputs);
							}
							
							scenario_gets = (ScenarioPackage) decoder.readObject();
							decoder.close();
							inputs.close();						
							
							for(int i=0; i<scenario_gets.getScenarios().size(); i++)
							{
								additionalScenario.add(scenario_gets.getScenarios().get(i));
							}
						}
						catch(Exception e1)
						{
							
						}
						finally
						{
							try
							{
								decoder.close();
							}
							catch(Exception e1)
							{
								
							}
							try
							{
								din.close();
							}
							catch(Exception e1)
							{
								
							}
							try
							{
								inputs.close();
							}
							catch(Exception e1)
							{
								
							}
						}
					}
					
					Vector<String> addScenarioList = new Vector<String>();
					String readed = "";
					
					int infiniteLoopPrevent = 0;
					
					try
					{
						URL scenario_url = new URL(setting.getNotice_url() + "additional_scenario_list.txt");
						if(din_use)
						{
							din = new DataInputStream(scenario_url.openStream());
							inputReader = new InputStreamReader(din);
						}
						else
						{
							inputReader = new InputStreamReader(scenario_url.openStream());
						}						
						bfread = new BufferedReader(inputReader);
						while(true)
						{
							readed = bfread.readLine();
							if(readed == null) break;
							infiniteLoopPrevent++;
							if(infiniteLoopPrevent >= 1000) break;
							addScenarioList.add(readed);
						}
					}
					catch(Exception e)
					{
						
					}
					try
					{
						decoder.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						din.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						inputs.close();
					}
					catch(Exception e1)
					{
						
					}
					infiniteLoopPrevent = 0;
					try
					{
						URL scenario_url = new URL(setting.getNotice_url2() + "additional_scenario_list.txt");
						if(din_use)
						{
							din = new DataInputStream(scenario_url.openStream());
							inputReader = new InputStreamReader(din);
						}
						else
						{
							inputReader = new InputStreamReader(scenario_url.openStream());
						}
						bfread = new BufferedReader(inputReader);
						while(true)
						{
							readed = bfread.readLine();
							if(readed == null) break;
							infiniteLoopPrevent++;
							if(infiniteLoopPrevent >= 1000) break;
							addScenarioList.add(readed);
						}
					}
					catch(Exception e)
					{
						
					}
					try
					{
						decoder.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						din.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						inputs.close();
					}
					catch(Exception e1)
					{
						
					}
					AuthorizedScenario getScenario = null;
					StringBuffer buffer = null;
					for(int i=0; i<addScenarioList.size(); i++)
					{
						buffer = new StringBuffer("");
						try
						{
							URL scenario_url = new URL(addScenarioList.get(i));
							if(din_use)
							{
								din = new DataInputStream(scenario_url.openStream());
								inputReader = new InputStreamReader(scenario_url.openStream());
							}
							else
							{
								inputReader = new InputStreamReader(din);
							}
							
							bfread = new BufferedReader(inputReader);
							while(true)
							{
								readed = bfread.readLine();
								if(readed == null) break;
								buffer.append(readed);
								buffer.append("\n");
								//accumulate = accumulate + readed + "\n";
							}
							getScenario = Scenario.stringToScenario(buffer.toString());
							if(getScenario != null && (getScenario.isAuthorized(setting) || getScenario.getTurn_script() == null || getScenario.getTurn_script().equals("")))
								additionalScenario.add(getScenario);
						}
						catch(Exception e)
						{
							
						}
						try
						{
							decoder.close();
						}
						catch(Exception e1)
						{
							
						}
						try
						{
							din.close();
						}
						catch(Exception e1)
						{
							
						}
						try
						{
							inputs.close();
						}
						catch(Exception e1)
						{
							
						}
					}
				}
				
				ProgressDialog.progress(0.91);
				
				//int check_duplicate_size = setting.getScenarios().size();
				boolean duplicated = false;
				int duplicate_index = 0;
				Scenario target = null;
				Long save_stars = new Long(0);
				Vector<Scenario> newList = new Vector<Scenario>();
				for(int i=0; i<additionalScenario.size(); i++)
				{
					target = additionalScenario.get(i).clone();
					duplicated = false;
					save_stars = new Long(1);
					for(int j=0; j<setting.getScenarios().size(); j++)
					{
						if(setting.getScenarios().get(j).getName().equals(target.getName()))
						{
							duplicated = true;
							duplicate_index = j;
						}	
					}
					if(duplicated)
					{
						
						if(setting.getScenarios().get(duplicate_index) instanceof ExtendedScenario)
						{
							save_stars = new Long(((ExtendedScenario) setting.getScenarios().get(duplicate_index)).getStars().longValue());								
							if((target instanceof ExtendedScenario) && (((ExtendedScenario) target).getStars().longValue() < save_stars.longValue()))
							{
								((ExtendedScenario) target).setStars(save_stars);
							}
							
						}
						
						setting.getScenarios().remove(duplicate_index);	
					}
					else added = true;					
					
					setting.getScenarios().add(target);
					ProgressDialog.progress(0.91 + (((double) i  / (double)additionalScenario.size() ) * 0.080));
				}
				
				for(int i=0; i<setting.getScenarios().size(); i++)
				{
					newList.add(setting.getScenarios().get(i).clone());
				}
				setting.setScenarios(newList);
				
				System.gc();
				scenario_refresh();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(added)
		{
			scenarioDialog.setVisible(true);
		}
	}
	public void scenario_refresh()
	{
		String[] scenarioList = new String[setting.getScenarios().size()];
		for(int i=0; i<scenarioList.length; i++)
		{					
			String getsName = setting.getScenarios().get(i).getName();
			if(setting.getScenarios().get(i) instanceof AuthorizedScenario)
			{
				AuthorizedScenario auts = (AuthorizedScenario) setting.getScenarios().get(i);
				if(! (auts.getKoreanName() == null || auts.getKoreanName().equals("")))
				{
					if(setting.getLang().getType() == Language.LANG_KOREAN)
					{
						getsName = auts.getKoreanName();
					}
				}
			}
			if(setting.getScenarios().get(i) instanceof ExtendedScenario)
			{
				ExtendedScenario getExs = (ExtendedScenario) setting.getScenarios().get(i);
				if(getExs.getStar_line() != null)
				{
					if(getExs.getStars().longValue() >= 1)
					{
						getsName = getsName + " (";
						getsName = getsName + Difficulty.starToString(getExs.getStars().longValue());
						getsName = getsName + ")";
					}
				}
			}
			scenarioList[i] = new String(getsName);
		}
		scenarioDialog_list.setListData(scenarioList);
		scenarioDialog_list.setSelectedIndex(0);
	}
	@Override
	public void showAll()
	{
		if(authority_mode)
			message(lang.getText(Language.AUTHORITY));
		else
		{
			try
			{
				for(int i=0; i<blocks.size(); i++)
				{
					message_bar();
					message_bar();
					message(lang.getText(Language.NAME) + " : " + blocks.get(i).getName());
					message_bar();
					message(lang.getText(Language.OWNS));
					for(int j=0; j<blocks.get(i).getOwns().size(); j++)
					{
						message(blocks.get(i).getOwns().get(j).toBasicString(setting.getCard_separator()));
					}
					
					message_bar();
					message_bar();
				}
				authority_mode = false;
			}
			catch(Exception e)
			{
				
			}
		}		
	}
	// authority_code used
	public static long getAuthorizedCode(long password)
	{
		if(password == 2938291)
			return 4928539;
		else 
			return -1;
	}
	public static String version_auts()
	{
		return "sgagiowopm1634onoiansdwsd";
	}
	public static boolean isBeta()
	{
		char[] lists = {'a', 'b', 'c'};
		for(int i=0; i<lists.length; i++)
		{
			if(lists[i] == CalcWindow.version_test)
			{
				return true;
			}
		}
		return false;
			
		
	}
	@Override
	public Setting getSetting()
	{
		return setting;
	}
	@Override
	public void openConsole()
	{
		messageDialog.setVisible(true);
		
	}
}

class JRadioButtonWithModule
{
	public JRadioButton[] radioButton;
	public BlockModule module;
}
