package accumulate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lang.Language;
import main_classes.CalcWindow;
import main_classes.Openable;
import main_classes.RunManager;
import setting.Difficulty;
import setting.Lint;
import setting.SaveBoolean;
import setting.SaveInt;
import setting.Setting;
import tracking.TrackStorage;

public class CityManager extends JDialog implements Openable, ActionListener, Runnable, WindowListener, ItemListener, ListSelectionListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -4737903420736608514L;
	private boolean independence = false;
	private CityData cityData = null;
	private InputStream inputStream = null;
	private ObjectInputStream objectInput = null;
	private OutputStream outputStream = null;
	private ObjectOutputStream objectOutput = null;
	private String default_path = "";
	private String path_separator = "";
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private JTabbedPane infoTab;
	private JPanel infoPanel;
	private JTextArea infoPane;
	private JScrollPane infoScroll;
	private City target = null;
	private JDialog newCityDialog;
	private JPanel new_mainPanel;
	private JPanel new_upPanel;
	private JPanel new_downPanel;
	private JPanel new_centerPanel;
	private JButton bt_exit;
	private JButton bt_newCity;
	private Thread thread;
	private boolean threadSwitch = false;
	private boolean pause = true;
	private boolean main_active = true;
	private JPanel new_newPanel;
	private JLabel new_nameLabel;
	private JTextField new_nameField;
	private JList new_list;
	private JScrollPane new_listScroll;
	private JButton bt_load;
	private int refresh_count = 0;
	private int refresh_speed = 20;
	private JPanel buildPanel;
	private JPanel build_leftPanel;
	private JPanel build_centerPanel;
	private JList build_list;
	private JScrollPane build_scroll;
	private JPanel build_upPanel;
	private JComboBox build_combo;
	private JComboBox build_grade;
	private JTextField build_price;
	private JButton bt_build;
	private JPanel build_downPanel;
	private JLabel build_label;
	private JTextArea build_desc;
	private JScrollPane build_descScroll;
	private JPanel simpleInfoPanel;
	private JTextField simpleInfoField;
	private JPanel filePanel;
	private JPanel[] filePns;
	private JButton bt_closeCity;
	private String simpleInfoStr;
	private BigInteger temp_data;
	private BigInteger temp_data2;
	private String infoPaneStr;
	private JPanel taxPanel;
	private JTextField taxField;
	private JButton bt_taxUp;
	private JButton bt_taxDown;
	private JLabel taxLabel;
	private JPanel policyPanel;
	private JPanel policy_leftPanel;
	private JPanel policy_centerPanel;
	private JList policy_list;
	private JScrollPane policy_scroll;
	private JPanel policy_activePanel;
	private JCheckBox policy_check;
	private JComboBox policy_grade;
	private JTextArea policy_desc;
	private JScrollPane policy_descScroll;
	private BigInteger tax_accumulate, tax_count;
	private JPanel incomePanel;
	private JTextArea incomeInfo;
	private JScrollPane incomeScroll;
	private String incomeInfoStr;
	private JPanel controlPanel;
	private JButton bt_pause;
	private JLabel cityLabel;
	private JProgressBar controlProgress;
	private JButton bt_exit2;
	private Setting sets;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mousex = 0;
	private int mousey = 0;
	private JPanel new_titlePanel;
	private JLabel new_titleLabel;
	private int new_mousex;
	private int new_mousey;
	private JFrame mainFrame = null;
	private JMenuBar menuBar;
	private JMenu menu_another;
	private JMenuItem menu_another_warn;
	private JCheckBoxMenuItem menu_another_calc;
	private JCheckBoxMenuItem menu_another_onecard;
	private JCheckBoxMenuItem menu_another_math;
	private JMenu menu_file;
	private JMenuItem menu_file_exit;
	private JCheckBoxMenuItem menu_another_conquer;
	private static String[] listData = null;
	
	public CityManager(boolean independence, Setting sets)
	{		
		super();
		mainFrame = new JFrame();
		mainFrame.addWindowListener(this);
		this.independence = independence;
		this.sets = sets;
		init();
	}
	public CityManager(JFrame upper, boolean independence, Setting sets)
	{
		super(upper);
		this.independence = independence;
		this.sets = sets;
		init();
	}
	public CityManager(JDialog upper, boolean independence, Setting sets)
	{
		super(upper);
		this.independence = independence;
		this.sets = sets;
		init();
	}
	public void init()
	{		
		if(sets == null) sets = Setting.getNewInstance();
		
		path_separator = System.getProperty("file.separator");
		default_path = System.getProperty("user.home") + path_separator + "calc" + path_separator;
		try
		{
			File dirs = new File(System.getProperty("user.home") + path_separator + "calc");
			//System.out.println(dirs + ", " + dirs.exists() + ", " + default_path + "citydata.cty , " + new File(default_path + "citydata.cty").exists());
			if(dirs.exists())
			{
				File loadFile = new File(default_path + "citydata.cty");
				if(loadFile.exists())
				{
					inputStream = new FileInputStream(loadFile);
					objectInput = new ObjectInputStream(inputStream);
					cityData = (CityData) objectInput.readObject();
				}
				else
				{
					cityData = new CityData();
					cityData.setCities(new Vector<City>());
				}
			}
			else
			{
				cityData = new CityData();
				cityData.setCities(new Vector<City>());
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			cityData = new CityData();
			cityData.setCities(new Vector<City>());
		}
		finally
		{
			try
			{
				objectInput.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				inputStream.close();
			}
			catch (Exception e)
			{
				
			}
		}
		
		if(CalcWindow.usingFont == null)
			CalcWindow.prepareFont();
				
		this.setSize(600, 450);
		this.addWindowListener(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		
		this.setUndecorated(sets.isUse_own_titleBar());		
		
		tax_accumulate = Lint.big(0);
		tax_count = Lint.big(0);		
		
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		
		if(mainFrame != null)
		{
			mainFrame.setUndecorated(sets.isUse_own_titleBar());
			mainFrame.setSize(600, 450);
			mainFrame.setLocation((int)(screenSize.getWidth()/2 - mainFrame.getWidth()/2), (int)(screenSize.getHeight()/2 - mainFrame.getHeight()/2));
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
		}
		else
		{
			this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		}
		
		
		
		
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
		downPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(new BorderLayout());
		
		titlePanel = new JPanel();
		
		controlPanel = new JPanel();
		controlPanel.setBackground(sets.getSelected_back());
		controlPanel.setLayout(new FlowLayout());
		upPanel.add(controlPanel, BorderLayout.SOUTH);
		if(sets.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.CENTER);
		}
		bt_pause = new JButton(sets.getLang().getText(Language.PAUSE_BREAK));
		if(sets.getSelected_button() != null)
			bt_pause.setBackground(sets.getSelected_button());
		bt_pause.setForeground(sets.getSelected_fore());
		bt_pause.addActionListener(this);
		if(CalcWindow.usingFont != null)
			bt_pause.setFont(CalcWindow.usingFont);
		cityLabel = new JLabel();
		cityLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			cityLabel.setFont(CalcWindow.usingFont);
		controlProgress = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		controlPanel.add(cityLabel);
		controlPanel.add(controlProgress);
		controlPanel.add(bt_pause);
		
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setBackground(sets.getSelected_inside_back());
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(sets.getLang().getText(Language.SHELTER_CITY));
		titleLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			titleLabel.setFont(CalcWindow.usingFont);
		this.setTitle(sets.getLang().getText(Language.SHELTER_CITY));
		titlePanel.add(titleLabel);
		if(sets.isUse_own_titleBar())
		{
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		
		if(mainFrame != null)
		{
			mainFrame.setTitle(sets.getLang().getText(Language.SHELTER_CITY));
		}
		
		simpleInfoPanel = new JPanel();
		simpleInfoPanel.setBackground(sets.getSelected_back());
		downPanel.add(simpleInfoPanel, BorderLayout.NORTH);
		simpleInfoPanel.setLayout(new BorderLayout());
		simpleInfoField = new JTextField();
		simpleInfoField.setForeground(sets.getSelected_fore());
		simpleInfoField.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			simpleInfoField.setFont(CalcWindow.usingFont);
		simpleInfoField.setEditable(false);
		simpleInfoField.setBorder(new EtchedBorder());
		simpleInfoPanel.add(simpleInfoField, BorderLayout.CENTER);
		taxPanel = new JPanel();
		taxPanel.setBackground(sets.getSelected_back());
		simpleInfoPanel.add(taxPanel, BorderLayout.EAST);
		taxPanel.setLayout(new FlowLayout());
		
		taxLabel = new JLabel(sets.getLang().getText(Language.TAX));
		taxLabel.setForeground(sets.getSelected_fore());
		taxField = new JTextField(2);
		taxField.setForeground(sets.getSelected_fore());
		taxField.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			taxLabel.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			taxField.setFont(CalcWindow.usingFont);
		taxField.setEditable(false);
		bt_taxUp = new JButton("+");
		bt_taxDown = new JButton("-");
		if(sets.getSelected_button() != null)
			bt_taxUp.setBackground(sets.getSelected_button());
		if(sets.getSelected_button() != null)
			bt_taxDown.setBackground(sets.getSelected_button());
		bt_taxDown.setForeground(sets.getSelected_fore());
		bt_taxUp.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_taxUp.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			bt_taxDown.setFont(CalcWindow.usingFont);
		bt_taxUp.addActionListener(this);
		bt_taxDown.addActionListener(this);
		taxPanel.add(taxLabel);
		taxPanel.add(taxField);
		taxPanel.add(bt_taxUp);
		taxPanel.add(bt_taxDown);
		
		infoTab = new JTabbedPane();
		infoTab.setForeground(sets.getSelected_fore());
		infoTab.setBackground(sets.getSelected_back());
		if(CalcWindow.usingFont != null)
			infoTab.setFont(CalcWindow.usingFont);
		centerPanel.add(infoTab, BorderLayout.CENTER);
		
		
		infoPanel = new JPanel();
		infoPanel.setBackground(sets.getSelected_back());
		infoTab.add(sets.getLang().getText(Language.TOTAL_INFO), infoPanel);
		
		infoPanel.setLayout(new BorderLayout());
		infoPane = new JTextArea();
		infoPane.setForeground(sets.getSelected_fore());
		infoPane.setBackground(sets.getSelected_inside_back());
		infoPane.setEditable(false);
		infoPane.setEditable(false);
		infoScroll = new JScrollPane(infoPane);
		infoPanel.add(infoScroll, BorderLayout.CENTER);
		
		buildPanel = new JPanel();
		buildPanel.setBackground(sets.getSelected_back());
		infoTab.add(sets.getLang().getText(Language.BUILD), buildPanel);
		buildPanel.setLayout(new BorderLayout());
		build_leftPanel = new JPanel();
		build_centerPanel = new JPanel();
		build_leftPanel.setBackground(sets.getSelected_back());
		build_centerPanel.setBackground(sets.getSelected_back());
		buildPanel.add(build_leftPanel, BorderLayout.WEST);
		buildPanel.add(build_centerPanel, BorderLayout.CENTER);		
		build_list = new JList();
		build_list.setForeground(sets.getSelected_fore());
		build_list.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			build_list.setFont(CalcWindow.usingFont);
		build_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		build_scroll = new JScrollPane(build_list);
		build_leftPanel.setLayout(new BorderLayout());
		build_leftPanel.add(build_scroll, BorderLayout.CENTER);
		build_centerPanel.setLayout(new BorderLayout());
		build_upPanel = new JPanel();
		build_upPanel.setBackground(sets.getSelected_back());
		build_centerPanel.add(build_upPanel, BorderLayout.NORTH);		
		build_upPanel.setLayout(new FlowLayout());
		build_downPanel = new JPanel();
		build_downPanel.setBackground(sets.getSelected_back());
		build_downPanel.setLayout(new FlowLayout());
		build_centerPanel.add(build_downPanel, BorderLayout.SOUTH);	
		String[] buildList = new String[7];
		buildList[0] = sets.getLang().getText(Language.RESIDENT);
		buildList[1] = sets.getLang().getText(Language.MARKETPLACE);
		buildList[2] = sets.getLang().getText(Language.POWERPLANT);
		buildList[3] = sets.getLang().getText(Language.WAREHOUSE);
		buildList[4] = sets.getLang().getText(Language.FARM);
		buildList[5] = sets.getLang().getText(Language.CLINIC);
		buildList[6] = sets.getLang().getText(Language.ACADEMY);
		String[] gradeList = new String[6];
		for(int i=0; i<gradeList.length; i++)
		{
			gradeList[i] = Difficulty.starToString((i + 1));
		}
		build_combo = new JComboBox(buildList);
		build_grade = new JComboBox(gradeList);
		build_combo.setForeground(sets.getSelected_fore());
		build_grade.setForeground(sets.getSelected_fore());
		build_combo.setBackground(sets.getSelected_inside_back());
		build_grade.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			build_combo.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			build_grade.setFont(CalcWindow.usingFont);
		build_combo.setSelectedIndex(0);
		build_grade.setSelectedIndex(0);
		build_combo.addItemListener(this);
		build_grade.addItemListener(this);
		build_label = new JLabel(sets.getLang().getText(Language.COST));
		build_label.setForeground(sets.getSelected_fore());
		build_price = new JTextField(10);
		build_price.setForeground(sets.getSelected_fore());
		build_price.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			build_label.setFont(CalcWindow.usingFont);
		if(CalcWindow.usingFont != null)
			build_price.setFont(CalcWindow.usingFont);
		build_price.setEditable(false);
		build_price.setText(String.valueOf(getBuildCost()));
		bt_build = new JButton(sets.getLang().getText(Language.BUILD));
		if(CalcWindow.usingFont != null)
			bt_build.setFont(CalcWindow.usingFont);
		bt_build.setForeground(sets.getSelected_fore());
		bt_build.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_build.setBackground(sets.getSelected_button());
		build_upPanel.add(build_combo);
		build_upPanel.add(build_grade);
		build_downPanel.add(build_label);
		build_downPanel.add(build_price);
		build_downPanel.add(bt_build);
		build_desc = new JTextArea();
		build_desc.setForeground(sets.getSelected_fore());
		build_desc.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			build_desc.setFont(CalcWindow.usingFont);
		build_desc.setLineWrap(true);
		build_desc.setEditable(false);
		build_desc.setText(getBuildDesc());
		build_descScroll = new JScrollPane(build_desc);
		build_centerPanel.add(build_descScroll, BorderLayout.CENTER);
		
		policyPanel = new JPanel();
		policyPanel.setBackground(sets.getSelected_back());
		infoTab.add(sets.getLang().getText(Language.POLICY), policyPanel);
		policyPanel.setLayout(new BorderLayout());
		policy_leftPanel = new JPanel();
		policy_centerPanel = new JPanel();
		policy_leftPanel.setBackground(sets.getSelected_back());
		policy_centerPanel.setBackground(sets.getSelected_back());
		policyPanel.add(policy_leftPanel, BorderLayout.WEST);
		policyPanel.add(policy_centerPanel, BorderLayout.CENTER);
		policy_list = new JList();
		policy_list.setForeground(sets.getSelected_fore());
		policy_list.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			policy_list.setFont(CalcWindow.usingFont);
		policy_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		policy_scroll = new JScrollPane(policy_list);
		policy_leftPanel.setLayout(new BorderLayout());
		policy_leftPanel.add(policy_scroll);
		policy_list.addListSelectionListener(this);
		//policy_list.setListData(target.policyList());
		policy_list.setSelectedIndex(0);
		policy_centerPanel.setLayout(new BorderLayout());
		policy_activePanel = new JPanel();
		policy_activePanel.setBackground(sets.getSelected_back());
		policy_centerPanel.add(policy_activePanel, BorderLayout.NORTH);
		policy_activePanel.setLayout(new FlowLayout());
		String[] policy_grades = new String[5];
		for(int i=0; i<policy_grades.length; i++)
		{
			policy_grades[i] = Difficulty.starToString((i + 1));
		}
		policy_grade = new JComboBox(policy_grades);
		policy_grade.setForeground(sets.getSelected_fore());
		policy_grade.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			policy_grade.setFont(CalcWindow.usingFont);
		policy_grade.setEnabled(false);
		policy_grade.addItemListener(this);
		policy_check = new JCheckBox(sets.getLang().getText(Language.MAKE_ACTIVE));
		policy_check.setForeground(sets.getSelected_fore());
		policy_check.setBackground(sets.getSelected_back());
		if(CalcWindow.usingFont != null)
			policy_check.setFont(CalcWindow.usingFont);
		policy_check.setEnabled(false);
		policy_check.addItemListener(this);
		policy_activePanel.add(policy_grade);
		policy_activePanel.add(policy_check);
		policy_desc = new JTextArea();
		policy_desc.setForeground(sets.getSelected_fore());
		policy_desc.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			policy_desc.setFont(CalcWindow.usingFont);
		policy_desc.setLineWrap(true);
		policy_desc.setEditable(false);
		policy_descScroll = new JScrollPane(policy_desc);
		policy_centerPanel.add(policy_descScroll, BorderLayout.CENTER);
		//policy_desc.setText(target.getPolicies()[0].description());
		
		incomePanel = new JPanel();
		incomePanel.setBackground(sets.getSelected_back());
		infoTab.add(sets.getLang().getText(Language.ECONOMY), incomePanel);
		incomePanel.setLayout(new BorderLayout());
		incomeInfo = new JTextArea();
		incomeInfo.setForeground(sets.getSelected_fore());
		incomeInfo.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			incomeInfo.setFont(CalcWindow.usingFont);
		incomeInfo.setLineWrap(true);
		incomeInfo.setEditable(false);
		incomeScroll = new JScrollPane(incomeInfo);
		incomePanel.add(incomeScroll, BorderLayout.CENTER);
		
		
		filePanel = new JPanel();
		filePanel.setBackground(sets.getSelected_back());
		infoTab.add(sets.getLang().getText(Language.ETC), filePanel);
		filePns = new JPanel[10];
		filePanel.setLayout(new GridLayout(filePns.length, 1));
		for(int i=0; i<filePns.length; i++)
		{
			filePns[i] = new JPanel();
			filePns[i].setBackground(sets.getSelected_back());
			filePns[i].setLayout(new FlowLayout());
			filePanel.add(filePns[i]);
		}
		bt_closeCity = new JButton(sets.getLang().getText(Language.STOP_CONTROL_CITY));
		if(sets.getSelected_button() != null)
			bt_closeCity.setBackground(sets.getSelected_button());
		bt_closeCity.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_closeCity.setFont(CalcWindow.usingFont);
		bt_closeCity.addActionListener(this);
		filePns[0].add(bt_closeCity);
		
		bt_exit2 = new JButton(sets.getLang().getText(Language.EXIT));
		if(sets.getSelected_button() != null)
			bt_exit2.setBackground(sets.getSelected_button());
		bt_exit2.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_exit2.setFont(CalcWindow.usingFont);
		bt_exit2.addActionListener(this);
		filePns[2].add(bt_exit2);
		
		newCityDialog = new JDialog(this, true);
		newCityDialog.setUndecorated(sets.isUse_own_titleBar());		
		newCityDialog.addWindowListener(this);
		newCityDialog.setSize(400, 300);
		newCityDialog.setLocation((int)(screenSize.getWidth()/2 - newCityDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - newCityDialog.getHeight()/2));
		new_mainPanel = new JPanel();
		new_mainPanel.setBackground(sets.getSelected_back());
		newCityDialog.getContentPane().setLayout(new BorderLayout());
		newCityDialog.getContentPane().add(new_mainPanel, BorderLayout.CENTER);
		new_mainPanel.setLayout(new BorderLayout());
		new_mainPanel.setBorder(new EtchedBorder());
		new_upPanel = new JPanel();
		new_downPanel = new JPanel();
		new_centerPanel = new JPanel();
		new_upPanel.setBackground(sets.getSelected_back());
		new_downPanel.setBackground(sets.getSelected_back());
		new_centerPanel.setBackground(sets.getSelected_back());
		new_mainPanel.add(new_upPanel, BorderLayout.NORTH);
		new_mainPanel.add(new_downPanel, BorderLayout.SOUTH);
		new_mainPanel.add(new_centerPanel, BorderLayout.CENTER);		
		new_downPanel.setLayout(new FlowLayout());			
		bt_exit = new JButton(sets.getLang().getText(Language.EXIT));
		if(sets.getSelected_button() != null)
			bt_exit.setBackground(sets.getSelected_button());
		bt_exit.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_exit.setFont(CalcWindow.usingFont);
		bt_exit.addActionListener(this);
		bt_load = new JButton(sets.getLang().getText(Language.LOAD));
		if(sets.getSelected_button() != null)
			bt_load.setBackground(sets.getSelected_button());
		bt_load.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_load.setFont(CalcWindow.usingFont);
		bt_load.addActionListener(this);
		new_downPanel.add(bt_load);	
		new_downPanel.add(bt_exit);		
		new_upPanel.setLayout(new BorderLayout());
		new_newPanel = new JPanel();
		new_newPanel.setBackground(sets.getSelected_back());
		new_newPanel.setLayout(new FlowLayout());
		new_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		new_nameLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			new_nameLabel.setFont(CalcWindow.usingFont);
		new_nameField = new JTextField(10);
		new_nameField.setForeground(sets.getSelected_fore());
		new_nameField.setBackground(sets.getSelected_inside_back());
		bt_newCity = new JButton(sets.getLang().getText(Language.NEW_CITY));
		if(sets.getSelected_button() != null)
			bt_newCity.setBackground(sets.getSelected_button());
		bt_newCity.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			bt_newCity.setFont(CalcWindow.usingFont);
		bt_newCity.addActionListener(this);
		new_newPanel.add(new_nameLabel);
		new_newPanel.add(new_nameField);
		new_newPanel.add(bt_newCity);
		new_upPanel.add(new_newPanel, BorderLayout.SOUTH);
		new_centerPanel.setLayout(new BorderLayout());
		new_list = new JList();
		new_list.setForeground(sets.getSelected_fore());
		new_list.setBackground(sets.getSelected_inside_back());
		if(CalcWindow.usingFont != null)
			new_list.setFont(CalcWindow.usingFont);
		new_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		new_listScroll = new JScrollPane(new_list);
		new_centerPanel.add(new_listScroll, BorderLayout.CENTER);
		new_titlePanel = new JPanel();
		if(sets.isUse_own_titleBar())
		{
			new_upPanel.add(new_titlePanel, BorderLayout.NORTH);
			new_titlePanel.addMouseListener(this);
			new_titlePanel.addMouseMotionListener(this);
		}
		new_titlePanel.setBorder(new EtchedBorder());
		new_titlePanel.setBackground(sets.getSelected_inside_back());
		new_titleLabel = new JLabel(sets.getLang().getText(Language.CITY) + " " + sets.getLang().getText(Language.SELECT));
		newCityDialog.setTitle(sets.getLang().getText(Language.CITY) + " " + sets.getLang().getText(Language.SELECT));
		new_titleLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			new_titleLabel.setFont(CalcWindow.usingFont);
		new_titlePanel.setLayout(new FlowLayout());
		new_titlePanel.add(new_titleLabel);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(sets.getSelected_inside_back());
		menuBar.setForeground(sets.getSelected_fore());
		new_upPanel.add(menuBar, BorderLayout.CENTER);
		
		menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		menu_file.setBackground(sets.getSelected_inside_back());
		menu_file.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			menu_file.setFont(CalcWindow.usingFont);
		
		menu_file_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		menu_file_exit.setBackground(sets.getSelected_inside_back());
		menu_file_exit.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			menu_file_exit.setFont(CalcWindow.usingFont);
		menu_file_exit.addActionListener(this);
		menu_file.add(menu_file_exit);
		
		menu_another = new JMenu(sets.getLang().getText(Language.ANOTHER_GAMES));
		menu_another.setBackground(sets.getSelected_inside_back());
		menu_another.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			menu_another.setFont(CalcWindow.usingFont);
		
		menu_another_warn = new JMenuItem(sets.getLang().getText(Language.NEED_RESTART));
		if(CalcWindow.usingFont != null)
			menu_another_warn.setFont(CalcWindow.usingFont);
		menu_another_warn.setBackground(sets.getSelected_back());
		menu_another_warn.setForeground(sets.getSelected_fore());
		menu_another_warn.addActionListener(this);
		menu_another_warn.setEnabled(false);
		menu_another.add(menu_another_warn);
		
		menu_another_calc = new JCheckBoxMenuItem(sets.getLang().getText(Language.TITLE));
		if(CalcWindow.usingFont != null)
			menu_another_calc.setFont(CalcWindow.usingFont);
		menu_another_calc.setBackground(sets.getSelected_inside_back());
		menu_another_calc.setForeground(sets.getSelected_fore());
		menu_another_calc.addItemListener(this);
		menu_another.add(menu_another_calc);
		
		menu_another_onecard = new JCheckBoxMenuItem(sets.getLang().getText(Language.ONECARD));
		if(CalcWindow.usingFont != null)
			menu_another_onecard.setFont(CalcWindow.usingFont);
		menu_another_onecard.setBackground(sets.getSelected_inside_back());
		menu_another_onecard.setForeground(sets.getSelected_fore());
		menu_another_onecard.addItemListener(this);
		menu_another.add(menu_another_onecard);	
		
		menu_another_conquer = new JCheckBoxMenuItem(sets.getLang().getText(Language.CONQUER));
		if(CalcWindow.usingFont != null)
			menu_another_conquer.setFont(CalcWindow.usingFont);
		menu_another_conquer.setBackground(sets.getSelected_inside_back());
		menu_another_conquer.setForeground(sets.getSelected_fore());
		menu_another_conquer.addItemListener(this);
		menu_another.add(menu_another_conquer);	
		
		menu_another_math = new JCheckBoxMenuItem(sets.getLang().getText(Language.MATHCONQ));
		if(CalcWindow.usingFont != null)
			menu_another_math.setFont(CalcWindow.usingFont);
		menu_another_math.setBackground(sets.getSelected_inside_back());
		menu_another_math.setForeground(sets.getSelected_fore());
		menu_another_math.addItemListener(this);
		menu_another.add(menu_another_math);	
		
		menuBar.add(menu_file);
		menuBar.add(menu_another);
		
		if(RunManager.STANDALONE_MODE >= 0)
			menu_another.setVisible(false);
		else if(mainFrame == null)
			menu_another.setVisible(false);
		
		thread = new Thread(this);
		threadSwitch = true;
		
	}
	public void refresh()
	{			
		if(target == null)
		{
			if(! newCityDialog.isVisible())
			{
				listData = new String[cityData.getCities().size()];
				for(int i=0; i<cityData.getCities().size(); i++)
				{
					listData[i] = cityData.getCities().get(i).getName();
				}
				main_active = false;
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						new_list.setListData(listData);
						newCityDialog.setVisible(true);	
					}
					
				});				
			}
		}
		else
		{
			try
			{
				simpleInfoStr = "";
				incomeInfoStr = "";
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				infoPaneStr = "";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.BUDGET) + " " + target.getBudget().toString() + "\n";	
				incomeInfoStr = incomeInfoStr + sets.getLang().getText(Language.BUDGET) + " " + target.getBudget().toString() + "\n\n";	
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.POPULATION) +" " + target.population().toString() + "\n";
				simpleInfoStr = sets.getLang().getText(Language.BUDGET) + " " + target.getBudget().toString() + "\t" + sets.getLang().getText(Language.POPULATION) + " " + target.population().toString();
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				Vector<Person> persons = target.people();
				for(int i=0; i<persons.size(); i++)
				{
					temp_data = temp_data.add(Lint.big(persons.get(i).getIntelligent().intValue()));
				}
				temp_data = temp_data.divide(target.population());
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.INTELLIGENT) + " " + temp_data.toString() + "\n";
				
				temp_data = Lint.big(0);
				for(int i=0; i<persons.size(); i++)
				{
					temp_data = temp_data.add(Lint.big(persons.get(i).getStrength().intValue()));
				}
				temp_data = temp_data.divide(target.population());
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.STRENGTH) +" " + temp_data.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				for(int i=0; i<persons.size(); i++)
				{
					if(Lint.big(persons.get(i).getHp().intValue()).compareTo(temp_data) >= 1)
					{
						temp_data = Lint.big(persons.get(i).getHp().intValue());
					}
					temp_data2 = temp_data2.add(Lint.big(persons.get(i).getHp().intValue()));
				}
				for(int i=0; i<persons.size(); i++)
				{
					if(Lint.big(persons.get(i).getHp().intValue()).compareTo(temp_data) <= -1)
					{
						temp_data = Lint.big(persons.get(i).getHp().intValue());
					}
				}
				temp_data2 = temp_data2.divide(target.population());
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.HP) + " " + temp_data2.toString() + "\n";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.MINIMUM) + " " + sets.getLang().getText(Language.HP) + " " + temp_data.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				for(int i=0; i<persons.size(); i++)
				{
					if(Lint.big(persons.get(i).getHappiness().intValue()).compareTo(temp_data) >= 1)
					{
						temp_data = Lint.big(persons.get(i).getHappiness().intValue());
					}
					temp_data2 = temp_data2.add(Lint.big(persons.get(i).getHappiness().intValue()));
				}
				for(int i=0; i<persons.size(); i++)
				{
					if(Lint.big(persons.get(i).getHappiness().intValue()).compareTo(temp_data) <= -1)
					{
						temp_data = Lint.big(persons.get(i).getHappiness().intValue());
					}
				}
				temp_data2 = temp_data2.divide(target.population());
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.HAPPINESS) + " " + temp_data2.toString() + "\n";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.MINIMUM) + " " + sets.getLang().getText(Language.HAPPINESS) + " " + temp_data.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				for(int i=0; i<persons.size(); i++)
				{
					if(persons.get(i).getBudget().compareTo(temp_data) >= 1)
					{
						temp_data = persons.get(i).getBudget();
					}
					temp_data2 = temp_data2.add(persons.get(i).getBudget());
				}
				for(int i=0; i<persons.size(); i++)
				{
					if(persons.get(i).getBudget().compareTo(temp_data) <= -1)
					{
						temp_data = persons.get(i).getBudget();
					}
				}
				temp_data2 = temp_data2.divide(target.population());
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.BUDGET) + " " + temp_data2.toString() + "\n";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.MINIMUM) + " " + sets.getLang().getText(Language.BUDGET) + " " + temp_data.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				String[] buildingList = new String[target.getSection_size()];
				Section[] buildings = target.getSections();
				for(int i=0; i<buildingList.length; i++)
				{
					buildingList[i] = String.valueOf((i+1)) + " " + buildings[i].getName() + " E" + String.valueOf(buildings[i].power());
					temp_data = temp_data.add(Lint.big(buildings[i].power()));
					if(buildings[i] instanceof PowerPlant)
					{
						temp_data2 = temp_data2.add(((PowerPlant) buildings[i]).getCapacity());
					}
				}
				build_list.setListData(buildingList);				
				simpleInfoStr = simpleInfoStr + "\tE " + temp_data.toString() + " / " + temp_data2.toString();
				infoPaneStr = infoPaneStr + "E " + temp_data.toString() + " / " + temp_data2.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				for(int i=0; i<persons.size(); i++)
				{
					if(persons.get(i).getDesease().intValue() >= 1)
					{
						temp_data = temp_data.add(Lint.big(1));
						temp_data2 = temp_data2.add(Lint.big(persons.get(i).getDesease().intValue()));
					}
				}
				temp_data2 = temp_data2.divide(Lint.big(persons.size()));
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.DESEASE) + " " + sets.getLang().getText(Language.POPULATION) + " " + temp_data.toString() + "\n";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.DESEASE) + " " + sets.getLang().getText(Language.DEEP) + " " + temp_data2.toString() + "\n";
				
				infoPaneStr = infoPaneStr + "\n";
				
				temp_data = Lint.big(0);
				temp_data2 = Lint.big(0);
				for(int i=0; i<buildings.length; i++)
				{
					if(buildings[i] instanceof Farm)
					{
						Farm fm = (Farm) buildings[i];
						temp_data = temp_data.add(fm.getCapacity());
					}
					else if(buildings[i] instanceof Warehouse)
					{
						Warehouse wm = (Warehouse) buildings[i];
						temp_data2 = temp_data2.add(wm.getCapacity());
					}
				}
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.FOOD) + " " + sets.getLang().getText(Language.PRODUCTION) + " " + temp_data.toString() + "\n";
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.FOOD) + " " + target.getFood().toString() + " / " + temp_data2.toString() + "\n";
				simpleInfoStr = simpleInfoStr + "\t" + sets.getLang().getText(Language.FOOD) + " " + target.getFood().toString() + " / " + temp_data2.toString();
				
				
				infoPaneStr = infoPaneStr + "\n";
				temp_data = Lint.big(0);
				for(int i=0; i<buildings.length; i++)
				{
					if(! (buildings[i] instanceof EmptySection))
						temp_data = temp_data.add(Lint.big(1));
				}
				infoPaneStr = infoPaneStr + sets.getLang().getText(Language.CITY) + " " + sets.getLang().getText(Language.SIZE) + " " + temp_data.toString() + " / " + target.getSection_size().toString() + "\n";
				
				if(tax_count.compareTo(Lint.big(0)) != 0)
					incomeInfoStr = incomeInfoStr + sets.getLang().getText(Language.AVERAGE) + " " + sets.getLang().getText(Language.ECONOMY) + " " + sets.getLang().getText(Language.SITUATION) + " " + ": " + tax_accumulate.divide(tax_count) + "\n";
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						incomeInfo.setText(incomeInfoStr);
						taxField.setText(target.getTax().toString());
						simpleInfoField.setText(simpleInfoStr);
						infoPane.setText(infoPaneStr);
						
						policy_list.setListData(target.policyList());
						cityLabel.setText(target.getName());
					}					
				});				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void save()
	{
		try
		{
			if(target != null)
			{
				for(int i=0; i<cityData.getCities().size(); i++)
				{
					//System.out.println(cityData.getCities().get(i));
					
					if(cityData.getCities().get(i).getId().compareTo(target.getId()) == 0)
					{
						cityData.getCities().remove(i);					
					}
				}
				
				cityData.getCities().add(target);
			}
			
			/*
			for(int i=0; i<cityData.getCities().size(); i++)
			{
				System.out.println(cityData.getCities().get(i).getName());
			}
			*/
			
			File dirs = new File(path_separator);
			if(! dirs.exists()) dirs.mkdir();
			outputStream = new FileOutputStream(new File(default_path + "citydata.cty"));
			objectOutput = new ObjectOutputStream(outputStream);
			objectOutput.writeObject(cityData);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				objectOutput.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				outputStream.close();
			}
			catch (Exception e)
			{
				
			}
		}
	}
	@Override
	public void open()
	{
		main_active = true;
		tax_accumulate = Lint.big(0);
		tax_count = Lint.big(0);
		if(mainFrame != null)
		{
			mainFrame.setVisible(true);
		}
		else
		{
			this.setVisible(true);
		}
		if(! threadSwitch)
		{
			try
			{
				threadSwitch = true;
				thread.start();
			} 
			catch (Exception e)
			{
				if(mainFrame == null)
					JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
				else
					JOptionPane.showMessageDialog(mainFrame, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			}
		}
		pause = true;
		setPauseMode();
		refresh();
	}

	@Override
	public void exit()
	{
		main_active = false;
		save();
		if(independence) System.exit(0);
		else
		{
			newCityDialog.setVisible(false);
			this.setVisible(false);
			if(mainFrame != null)
			{
				mainFrame.setVisible(false);
			}
		}
	}

	@Override
	public void run()
	{
		while(threadSwitch)
		{
			if(main_active)
			{
				if(! pause)
				{
					refresh_count++;
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							controlProgress.setMaximum(refresh_speed);
							controlProgress.setValue(refresh_count);
						}						
					});
					if(target == null) 
					{
						try
						{
							refresh();
							refresh_count = 0;
						} 
						catch (Exception e)
						{
							
						}
					}
					try
					{
						if(refresh_count >= refresh_speed)
						{
							target.next();
							tax_accumulate = tax_accumulate.add(target.income());
							tax_accumulate = tax_accumulate.subtract(target.outcome());
							tax_count = tax_count.add(Lint.big(1));
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					try
					{
						if(refresh_count >= refresh_speed)
						{
							refresh();
							refresh_count = 0;
						}
					} 
					catch (Exception e)
					{
						refresh_count = 0;
						e.printStackTrace();
					}					
				}
			}
			
			try
			{
				Thread.sleep(101);
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	public String getBuildDesc()
	{
		String result = "";
		switch(build_combo.getSelectedIndex())
		{
			case 0: // Resident
				result = sets.getLang().getText(Language.DESC_RESIDENT);
				break;
			case 1: // Store
				result = sets.getLang().getText(Language.DESC_MARKETPLACE);
				break;
			case 2: // PowerPlant
				result = sets.getLang().getText(Language.DESC_POWERPLANT);
				break;
			case 3: // Warehouse
				result = sets.getLang().getText(Language.DESC_WAREHOUSE);
				break;
			case 4: // Farm
				result = sets.getLang().getText(Language.DESC_FARM);
				break;
			case 5: // Cure
				result = sets.getLang().getText(Language.DESC_CURE);
				break;
			case 6: // Academy
				result = sets.getLang().getText(Language.DESC_ACADEMY);
				break;
		}
		
		result = result + "\n\n" + sets.getLang().getText(Language.BUILD_WARN);
		return result;
	}
	public int getBuildCost()
	{
		int cost = 0;
		switch(build_combo.getSelectedIndex())
		{
			case 0: // Resident
				cost = 1000;
				break;
			case 1: // Store
				cost = 2000;
				break;
			case 2: // PowerPlant
				cost = 3000;
				break;
			case 3: // Warehouse
				cost = 2500;
				break;
			case 4: // Farm
				cost = 3500;
				break;
			case 5: // Cure
				cost = 5000;
				break;
			case 6: // Academy
				cost = 3500;
				break;
		}
		for(int i=0; i<build_grade.getSelectedIndex(); i++)
		{
			cost = cost * 2;
		}
		return cost;
	}
	public void setPauseMode()
	{
		try
		{			
			pause = (! pause);
			refresh();
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					if(pause)
						bt_pause.setText(sets.getLang().getText(Language.PAUSE_BREAK));
					else
						bt_pause.setText(sets.getLang().getText(Language.PAUSE));
					
					bt_build.setEnabled(pause);
					bt_taxDown.setEnabled(pause);
					bt_taxUp.setEnabled(pause);
					build_combo.setEnabled(pause);
					build_grade.setEnabled(pause);
					policy_list.setEnabled(pause);
					policy_check.setEnabled(pause);
					
					try
					{
						if(policy_list.getSelectedIndex() >= 0)
						{
							Policy pol = target.getPolicies()[policy_list.getSelectedIndex()];
							if(pol.hasGrade())
							{
								if(pol.getActive().booleanValue())
									policy_grade.setEnabled(pause);
								else
									policy_grade.setEnabled(false);
							}
							else
							{
								policy_grade.setEnabled(false);
								policy_grade.setSelectedIndex(1);
							}
						}
						else
						{
							policy_grade.setEnabled(false);
							policy_grade.setSelectedIndex(1);
						}
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}				
			});			
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == policy_list)
		{
			if(policy_list.getSelectedIndex() <= -1)
			{
				policy_check.setEnabled(false);
				policy_grade.setEnabled(false);
				policy_desc.setText("");
			}
			else
			{
				try
				{
					Policy pol = target.getPolicies()[policy_list.getSelectedIndex()];
					if(pol != null)
					{
						policy_check.setEnabled(true);					
						policy_grade.setEnabled(pol.hasGrade() && pol.getActive().booleanValue());
						if(! pol.hasGrade())
						{
							policy_grade.setSelectedIndex(0);
						}
						else
						{
							policy_grade.setSelectedIndex(pol.getGrade().intValue() - 1);
						}
						policy_desc.setText(pol.description());						
						policy_check.setSelected(pol.getActive().booleanValue());
					}
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		Object ob = e.getSource();
		if(ob == build_combo || ob == build_grade)
		{						
			build_price.setText(String.valueOf(getBuildCost()));
			build_desc.setText(getBuildDesc());
		}
		else if(ob == policy_check || ob == policy_grade)
		{
			try
			{
				if(policy_list.getSelectedIndex() >= 0)
				{
					Policy pol = target.getPolicies()[policy_list.getSelectedIndex()];
					pol.setActive(new Boolean(policy_check.isSelected()));
					if(pol.hasGrade())
					{
						pol.setGrade(new Integer(policy_grade.getSelectedIndex() + 1));
					}
					if(policy_check.isSelected())
					{
						policy_grade.setEnabled(pol.hasGrade());
					}
					else
					{
						policy_grade.setEnabled(false);
						pol.setGrade(new Integer(1));
					}
				}
			} 
			catch (Exception e1)
			{
				
			}
		}
		if(ob == menu_another_calc || ob == menu_another_onecard || ob == menu_another_math || ob == menu_another_conquer)
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
						menu_another_conquer.setSelected(false);
					}
				});				
				sets.setNext_execute(new SaveInt(0));
				sets.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
			else if(ob == menu_another_conquer && menu_another_conquer.isSelected())
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(false);
						menu_another_calc.setSelected(false);
						menu_another_math.setSelected(false);
						menu_another_conquer.setSelected(true);
					}
				});
				
				sets.setNext_execute(new SaveInt(2));
				sets.setNext_execute_saved(new SaveBoolean(true));
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
						menu_another_conquer.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(1));
				sets.setNext_execute_saved(new SaveBoolean(true));
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
						menu_another_conquer.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(5));
				sets.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
			else if(! (menu_another_calc.isSelected() || menu_another_onecard.isSelected() || menu_another_math.isSelected() || menu_another_conquer.isSelected()))
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						menu_another_onecard.setSelected(false);
						menu_another_calc.setSelected(false);
						menu_another_math.setSelected(false);
						menu_another_conquer.setSelected(false);
					}
				});
				
				sets.setNext_execute(new SaveInt(6));
				sets.setNext_execute_saved(new SaveBoolean(true));
				save_setting();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == bt_exit || ob == bt_exit2 || ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == bt_pause)
		{
			setPauseMode();
		}
		else if(ob == bt_load)
		{
			try
			{
				if(new_list.getSelectedIndex() > cityData.getCities().size() || new_list.getSelectedIndex() <= -1)
				{
					JOptionPane.showMessageDialog(newCityDialog, sets.getLang().getText(Language.NEED_SELECT_CITY));
				}
				else
				{
					target = cityData.getCities().get(new_list.getSelectedIndex());
					newCityDialog.setVisible(false);
					refresh();
					main_active = true;
				}				
			}
			catch(Exception e1)
			{
				target = null;
				refresh();
			}
		}
		else if(ob == bt_closeCity)
		{
			pause = true;
			save();
			target = null;
			pause = false;
		}
		else if(ob == bt_newCity)
		{
			target = City.newInstance(new_nameField.getText(), 1000, cityData.newId());
			newCityDialog.setVisible(false);
			refresh();
			main_active = true;
		}
		else if(ob == bt_build)
		{
			int empty_slot = -1;
			for(int i=0; i<target.getSection_size(); i++)
			{
				System.out.println(target.getSections()[i].getName());
				if(target.getSections()[i] instanceof EmptySection)
				{
					empty_slot = i;
					break;
				}
			}
			System.out.println(build_combo.getSelectedIndex() + ", " + build_grade.getSelectedIndex());
			if(empty_slot == -1) JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.FULL_SLOT));
			else if(build_combo.getSelectedIndex() == -1 || build_grade.getSelectedIndex() == -1) JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.NEED_SELECT_SECTION));
			else
			{
				Section newSection = null;
				if(target.getBudget().compareTo(Lint.big(getBuildCost())) <= -1)
				{
					JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.NEED_BUDGET));
					return;
				}
				switch(build_combo.getSelectedIndex())
				{
					case 0:						
						newSection = new Resident(build_grade.getSelectedIndex());
						break;
					case 1:
						newSection = new Store(build_grade.getSelectedIndex());
						break;
					case 2:
						newSection = new PowerPlant(build_grade.getSelectedIndex());
						break;
					case 3:
						newSection = new Warehouse(build_grade.getSelectedIndex());
						break;
					case 4:
						newSection = new Farm(build_grade.getSelectedIndex());
						break;
					case 5:
						newSection = new Cure(build_grade.getSelectedIndex());
						break;
					case 6:
						newSection = new Academy(build_grade.getSelectedIndex());
						break;
				}
				if(newSection == null)
				{
					JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.NEED_SELECT_SECTION));
					return;
				}
				target.setBudget(target.getBudget().subtract(Lint.big(getBuildCost())));
				
				Section[] newSectionList = target.getSections();
				for(int i=0; i<newSectionList.length; i++)
				{
					newSectionList[empty_slot] = newSection;
				}
				target.setSections(newSectionList);
				JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.BUILD) + " : " + newSection.getName() + " " + sets.getLang().getText(Language.ON) + " " + String.valueOf((empty_slot + 1)));
				refresh();
			}
		}
		else if(ob == bt_taxUp)
		{
			target.setTax(target.getTax().add(Lint.big(1)));
			refresh();
		}
		else if(ob == bt_taxDown)
		{
			target.setTax(target.getTax().subtract(Lint.big(1)));
			refresh();
		}
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		Object ob = e.getSource();
		if(ob == this || ob == newCityDialog)
		{
			exit();
		}
		else if(mainFrame != null && ob == mainFrame)
		{
			exit();
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(e.getSource() == titlePanel) 
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
		}
		else if(e.getSource() == new_titlePanel)
		{
			newCityDialog.setLocation(e.getXOnScreen() - new_mousex, e.getYOnScreen() - new_mousey);
		}
	}
	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
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
		else if(ob == new_titlePanel)
		{
			new_mousex = e.getX();
			new_mousey = e.getY();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	public void save_setting()
	{
		TrackStorage.addTrack(this.getClass().getName(), "save_setting() started", false);
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
			setting.wrapperToObjects();
			encoder.writeObject(setting);
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
}
