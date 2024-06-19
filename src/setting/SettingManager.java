package setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import lang.Korean;
import lang.Language;
import lang.UserDefinedKor;
import lang.UserDefinedLang;
import main_classes.CalcWindow;
import main_classes.Openable;
import main_classes.RunManager;
import scripting.BlockModule;
import scripting.ModulePackage;
import scripting.ScriptModule;
import scripting.SpecialCardModule;
import ai_algorithm.AI_Algorithm;
import ai_algorithm.AI_Editor;

public class SettingManager extends JFrame implements ActionListener, WindowListener, MouseListener, MouseMotionListener, ItemListener, Openable
{
	private static final long serialVersionUID = 6478189428668567015L;
	public int south_contents = 41;
	public Setting target;
	public Language lang;
	private JPanel mainPanel;
	private JPanel southPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel[] southPns;
	private JPanel[] northPns;
	private JLabel[] southLabels;
	private JLabel[] northLabels;
	private JTextField[] northFields;
	private JPanel southInsidePanel;
	private JPanel northInsidePanel;
	private JScrollPane southInsideScroll;
	private JScrollPane northInsideScroll;
	private JPanel controlPanel;
	private JTextField[] southFields;
	private JButton save;
	private JButton exit;
	private JPanel bottomPanel;
	private JLabel needRestartLabel;
	private JCheckBox southCheckBox1;
	private JTextArea northArea;
	private JScrollPane northAreaScroll;
	private JDialog helpContentDialog;
	private JPanel helpContentDialog_panel;
	private JPanel helpContentDialog_centerPanel;
	private JPanel helpContentDialog_downPanel;
	private JButton helpContentDialog_close;
	private JButton helpOpen;
	private JCheckBox southCheckBox2;
	private JCheckBox southCheckBox3;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mousex;
	private int mousey;
	private JPanel helpContentDialog_titlePanel;
	private JLabel helpContentDialog_title;
	private int help_mousex;
	private int help_mousey;
	private JCheckBox southCheckBox4;
	private JDialog lookAndFeelDialog;
	private JPanel lookAndFeelDialog_panel;
	private JPanel lookAndFeelDialog_upPanel;
	private JPanel lookAndFeelDialog_centerPanel;
	private JPanel lookAndFeelDialog_downPanel;
	private JButton themeGen;
	private JPanel lookAndFeelDialog_titlePanel;
	private JLabel lookAndFeelDialog_title;
	private JButton lookAndFeelDialog_close;
	private int look_mousex;
	private int look_mousey;
	private JPanel[] lookAndFeelDialog_Pns;
	private JPanel lookAndFeelDialog_contentPanel;
	private JScrollPane lookAndFeelDialog_contentScroll;
	private JLabel[] lookAndFeelDialog_lbs;
	private JTextField[] lookAndFeelDialog_fields;
	private JButton[] lookAndFeelDialog_use;
	private JCheckBox southCheckBox5;
	private JCheckBox southCheckBox6;
	private JCheckBox southCheckBox7;
	private JCheckBox use_xml;
	private JButton reset;
	public int ver_main, ver_sub1, ver_sub2;
	public boolean independence = false;
	private JCheckBox southCheckBox8;
	private JCheckBox southCheckBox9;
	private JButton ai_load;
	private FileFilter ff1;
	private JFileChooser cfd;
	private JButton ai_edit;
	private JPanel controlDownPanel;
	private AI_Editor ai_editor;
	private JDialog ai_dialog;
	private JPanel ai_dialog_mainPanel;
	private JPanel ai_dialog_upPanel;
	private JPanel ai_dialog_centerPanel;
	private JPanel ai_dialog_downPanel;
	private JPanel ai_dialog_titlePanel;
	private int ai_mousex;
	private int ai_mousey;
	private JButton ai_select;
	private JLabel ai_dialog_title;
	private JButton ai_dialog_close;
	private JPanel ai_dialog_contentPanel;
	private JScrollPane ai_dialog_contentScroll;
	private JPanel[] ai_dialog_contents;
	private JLabel[] ai_dialog_labels;
	private JRadioButton[] ai_dialog_radios;
	private ButtonGroup ai_dialog_group;
	private RunManager manager;
	private JButton restart;
	private JCheckBox southCheckBox10;
	private String[] arguments;
	private boolean before_scrollBar = false;
	private JCheckBox southCheckBox11;
	private JCheckBox southCheckBox12;
	private JTabbedPane helpContentDialog_tab;
	private JPanel[] helpContentDialog_tabs;
	private JTextArea[] helpContentArea;
	private JScrollPane[] helpContentAreaScroll;
	private ModuleViewer moduleManager;
	private JButton moduleViewer;
	private FlowLayout[] southLayouts;
	private FlowLayout[] northLayouts;
	public SettingManager(Setting gets, Dimension screenSize, int ver_main, int ver_sub1, int ver_sub2, boolean independence, RunManager manager, boolean runAsFirst, String[] args)
	{
		this.independence = independence;
		this.manager = manager;
		this.arguments = args;
		target = gets.clone();
		lang = target.getLang().clone();
		int width = 630, height = 350;
		if(screenSize.getWidth() >= 1024)
		{
			width = 1000;
		}
		else if(screenSize.getWidth() >= 800)
		{
			width = 750;
		}
		if(screenSize.getHeight() >= 768)
		{
			height = 700;
		}
		else if(screenSize.getHeight() >= 600)
		{
			height = 550;
		}
		this.setSize(width, height);
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();	
		mainPanel.setBorder(new EtchedBorder());
		this.addWindowListener(this);		
		this.getContentPane().add(mainPanel);
		this.setTitle(lang.getText(Language.TITLE) + " " + lang.getText(Language.SETTING));
		if(gets.isUse_own_titleBar())	this.setUndecorated(true);
		
		moduleManager = new ModuleViewer(this, gets);
		
		centerPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();	
		titlePanel = new JPanel();
		southPanel.setBorder(new EtchedBorder());
		northPanel.setBorder(new EtchedBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		if(gets.isUse_own_titleBar()) mainPanel.add(titlePanel, BorderLayout.NORTH);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlDownPanel = new JPanel();
		controlDownPanel.setLayout(new FlowLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		//controlScroll = new JScrollPane(controlPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		save = new JButton(lang.getText(Language.SAVE));
		exit = new JButton(lang.getText(Language.EXIT));
		restart = new JButton(lang.getText(Language.RESTART));
		ai_load = new JButton(lang.getText(Language.AI) + " " + lang.getText(Language.LOAD));
		ai_edit = new JButton(lang.getText(Language.AI) + " " + lang.getText(Language.EDIT));
		ai_select = new JButton(lang.getText(Language.AI) + " " + lang.getText(Language.SELECT));
		use_xml = new JCheckBox(lang.getText(Language.USE_XML_SETTING));
		helpOpen = new JButton(lang.getText(Language.HELP) + " " + lang.getText(Language.EDIT));
		themeGen = new JButton(lang.getText(Language.THEME));
		reset = new JButton(lang.getText(Language.RESET));	
		moduleViewer = new JButton(lang.getText(Language.MODULE));
		if(CalcWindow.usingFont != null)
		{
			save.setFont(CalcWindow.usingFont);
			exit.setFont(CalcWindow.usingFont);
			restart.setFont(CalcWindow.usingFont);
			ai_load.setFont(CalcWindow.usingFont);
			ai_edit.setFont(CalcWindow.usingFont);
			ai_select.setFont(CalcWindow.usingFont);
			use_xml.setFont(CalcWindow.usingFont);
			helpOpen.setFont(CalcWindow.usingFont);
			themeGen.setFont(CalcWindow.usingFont);
			reset.setFont(CalcWindow.usingFont);
			moduleViewer.setFont(CalcWindow.usingFont);
			
		}
		use_xml.setSelected(false);
		save.addActionListener(this);
		exit.addActionListener(this);
		restart.addActionListener(this);
		helpOpen.addActionListener(this);
		themeGen.addActionListener(this);
		reset.addActionListener(this);
		ai_load.addActionListener(this);
		ai_edit.addActionListener(this);
		ai_select.addActionListener(this);
		moduleViewer.addActionListener(this);
		controlPanel.add(helpOpen);
		//controlPanel.add(themeGen);
		controlDownPanel.add(save);		
		controlDownPanel.add(use_xml);
		// controlPanel.add(ai_load);
		controlPanel.add(moduleViewer);
		controlPanel.add(ai_select);
		controlPanel.add(ai_edit);		
		controlDownPanel.add(reset);
		controlDownPanel.add(restart);
		controlDownPanel.add(exit);		
		restart.setEnabled(false);
		//restart.setEnabled((! runAsFirst));
		needRestartLabel = new JLabel(lang.getText(Language.NEED_RESTART));		
		bottomPanel.add(needRestartLabel);
		bottomPanel.add(controlPanel);
		bottomPanel.add(controlDownPanel);
		
		// centerPanel.setLayout(new GridLayout(2, 1));
		centerPanel.setLayout(new GridLayout(1, 2));
		centerPanel.add(southPanel);
		centerPanel.add(northPanel);			
		
		southPanel.setLayout(new BorderLayout());
		southInsidePanel = new JPanel();
		southInsideScroll = new JScrollPane(southInsidePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		southPanel.add(southInsideScroll);
		southPns = new JPanel[south_contents];
		southInsidePanel.setLayout(new GridLayout(south_contents, 1));
		southLabels = new JLabel[south_contents];
		southFields = new JTextField[south_contents];
		southLayouts = new FlowLayout[south_contents];
		for(int i=0; i<southPns.length; i++)
		{
			southLayouts[i] = new FlowLayout();
			southLayouts[i].setAlignment(FlowLayout.CENTER);
			southPns[i] = new JPanel();
			southPns[i].setLayout(southLayouts[i]);
			southLabels[i] = new JLabel(String.valueOf(i));
			southFields[i] = new JTextField(5);
			if(CalcWindow.usingFont != null)
			{
				southLabels[i].setFont(CalcWindow.usingFont);
				southFields[i].setFont(CalcWindow.usingFont);
			}
			southPns[i].add(southLabels[i]);
			if(i <= 21 || (i >= 27 && ((i != 28 && (! (i >= 33 && i <= 37))) && (i >= 32 || i <= 29)))) southPns[i].add(southFields[i]);
			else if(i == 24)
			{
				southFields[i] = new JTextField(15);
				if(CalcWindow.usingFont != null)
					southFields[i].setFont(CalcWindow.usingFont);
				southPns[i].add(southFields[i]);
				southPns[i].add(themeGen);
			}
			
			southInsidePanel.add(southPns[i]);
		}		
		
		southCheckBox1 = new JCheckBox(lang.getText(Language.SELF) + " " + lang.getText(Language.TITLE_BAR) + " " + lang.getText(Language.USE));
		southPns[22].add(southCheckBox1);
		southCheckBox2 = new JCheckBox(lang.getText(Language.AUTHORITY) + " " + lang.getText(Language.USE));
		southPns[26].add(southCheckBox2);
		southCheckBox3 = new JCheckBox(lang.getText(Language.ALERT) + " " + lang.getText(Language.WINDOW) + " " + lang.getText(Language.SHOW));
		southPns[25].add(southCheckBox3);
		southCheckBox4 = new JCheckBox(lang.getText(Language.COLOR) + " " + lang.getText(Language.USE));
		southPns[23].add(southCheckBox4);
		southCheckBox4.addItemListener(this);
		southCheckBox5 = new JCheckBox(lang.getText(Language.PRINT_ERROR_DETAIL));
		southPns[28].add(southCheckBox5);
		southCheckBox6 = new JCheckBox(lang.getText(Language.SUMMARY_USE));
		southPns[30].add(southCheckBox6);
		southCheckBox7 = new JCheckBox(lang.getText(Language.NOTICE_NOT_VIEW_AGAIN));
		southPns[31].add(southCheckBox7);
		southCheckBox8 = new JCheckBox(lang.getText(Language.SCROLLBAR));
		southCheckBox8.addItemListener(this);
		southPns[33].add(southCheckBox8);
		southCheckBox9 = new JCheckBox(lang.getText(Language.USER) + " " + lang.getText(Language.AI));
		southPns[34].add(southCheckBox9);
		southCheckBox10 = new JCheckBox(lang.getText(Language.CLASSIC));
		southPns[35].add(southCheckBox10);
		southCheckBox11 = new JCheckBox(lang.getText(Language.TRACKING) + " " + lang.getText(Language.USE));
		southCheckBox11.addItemListener(this);
		southPns[36].add(southCheckBox11);
		southCheckBox12 = new JCheckBox(lang.getText(Language.TRACKING_AUTOSAVE));
		southCheckBox12.setEnabled(false);
		southPns[37].add(southCheckBox12);
		if(CalcWindow.usingFont != null)
		{
			southCheckBox1.setFont(CalcWindow.usingFont);
			southCheckBox2.setFont(CalcWindow.usingFont);
			southCheckBox3.setFont(CalcWindow.usingFont);
			southCheckBox4.setFont(CalcWindow.usingFont);
			southCheckBox5.setFont(CalcWindow.usingFont);
			southCheckBox6.setFont(CalcWindow.usingFont);
			southCheckBox7.setFont(CalcWindow.usingFont);
			southCheckBox8.setFont(CalcWindow.usingFont);
			southCheckBox9.setFont(CalcWindow.usingFont);
			southCheckBox10.setFont(CalcWindow.usingFont);
			southCheckBox11.setFont(CalcWindow.usingFont);
			southCheckBox12.setFont(CalcWindow.usingFont);
		}
		
		southLabels[0].setText(lang.getText(Language.WIDTH));
		southLabels[1].setText(lang.getText(Language.HEIGHT));
		southLabels[2].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.RED));
		southLabels[3].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[4].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[5].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.RED));
		southLabels[6].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[7].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[8].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.RED));
		southLabels[9].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[10].setText(lang.getText(Language.ACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[11].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.RED));
		southLabels[12].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[13].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.BACKGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[14].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.RED));
		southLabels[15].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[16].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.FOREGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[17].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.RED));
		southLabels[18].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.GREEN));
		southLabels[19].setText(lang.getText(Language.DEACTIVE) + " " + lang.getText(Language.INSIDE_BACKGROUND) + " " + lang.getText(Language.BLUE));
		southLabels[20].setText(lang.getText(Language.MAX) + " " + lang.getText(Language.PLAYER) + " " + lang.getText(Language.COUNT));
		southLabels[21].setText(lang.getText(Language.ON_START_CARDS));
		southLabels[22].setText("");
		southLabels[23].setText("");
		southLabels[24].setText(lang.getText(Language.THEME));
		southLabels[25].setText("");
		southLabels[26].setText("");
		southLabels[27].setText(lang.getText(Language.AI) + " " + lang.getText(Language.DIFFICULTY));
		southLabels[28].setText("");
		southLabels[29].setText(lang.getText(Language.HOW_TO_SHOW_CARD));
		southLabels[30].setText("");
		southLabels[31].setText("");
		southLabels[32].setText(lang.getText(Language.CHANGE_CARD) + " " + lang.getText(Language.COUNT));
		southLabels[33].setText("");
		southLabels[34].setText("");
		southLabels[35].setText("");
		southLabels[36].setText("");
		southLabels[37].setText("");
		southLabels[38].setText(lang.getText(Language.TRACKING) + " " + lang.getText(Language.SAVE) + " " + lang.getText(Language.DELAY));
		southLabels[39].setText(String.valueOf(gets.getOp_plus()) + ", " + String.valueOf(gets.getOp_minus()) + " " + lang.getText(Language.COUNT));
		southLabels[40].setText(String.valueOf(gets.getOp_multiply()) + " " + lang.getText(Language.COUNT));
		
		northInsidePanel = new JPanel();
		northPanel.setLayout(new BorderLayout());		
		northPns = new JPanel[lang.getList().length + 1];
		northInsideScroll = new JScrollPane(northInsidePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		northPanel.add(northInsideScroll, BorderLayout.CENTER);
		northInsidePanel.setLayout(new GridLayout(lang.getList().length + 1, 1));
		northLabels = new JLabel[lang.getList().length + 1];
		northFields = new JTextField[lang.getList().length];
		northLayouts = new FlowLayout[lang.getList().length + 1];
		for(int i=0; i<northPns.length; i++)
		{
			northLayouts[i] = new FlowLayout();
			northLayouts[i].setAlignment(FlowLayout.LEFT);
			northPns[i] = new JPanel();
			northPns[i].setLayout(northLayouts[i]);
			if(i < lang.getList().length)
			{				
				northLabels[i] = new JLabel(lang.getText(lang.getList()[i]));
				northFields[i] = new JTextField(20);
				if(CalcWindow.usingFont != null)
				{
					northLabels[i].setFont(CalcWindow.usingFont);
					northFields[i].setFont(CalcWindow.usingFont);
				}
				northFields[i].setText(lang.getText(lang.getList()[i]));
				// System.out.println(i + " : " + lang.getText(lang.getList()[i]) + " / " + northPns.length + ", " + lang.getList().length + ", " + lang.description.length);
				northPns[i].add(northLabels[i]);
				northPns[i].add(northFields[i]);				
			}
			else
			{
				northLabels[i] = new JLabel("");
				northArea = new JTextArea();
				if(CalcWindow.usingFont != null)
				{
					northLabels[i].setFont(CalcWindow.usingFont);
					northArea.setFont(CalcWindow.usingFont);
				}
				northAreaScroll = new JScrollPane(northArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				northArea.setLineWrap(true);
				northArea.setText(lang.getText(Language.HELP_CONTENT));
				northPns[i].add(northLabels[i]);
				//northPns[i].add(northAreaScroll);					
			}
			northInsidePanel.add(northPns[i]);
		}
		helpContentDialog = new JDialog(this, true);
		if(gets.isUse_own_titleBar()) helpContentDialog.setUndecorated(true);
		else helpContentDialog.addWindowListener(this);
		helpContentDialog_panel = new JPanel();
		helpContentDialog.setLayout(new BorderLayout());
		helpContentDialog.getContentPane().add(helpContentDialog_panel);
		helpContentDialog_panel.setBorder(new EtchedBorder());
		helpContentDialog.setSize(500, 400);
		helpContentDialog.setLocation((int)(screenSize.getWidth()/2 - helpContentDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - helpContentDialog.getHeight()/2));
		helpContentDialog_panel.setLayout(new BorderLayout());
		helpContentDialog_centerPanel = new JPanel();
		helpContentDialog_downPanel = new JPanel();
		helpContentDialog_panel.add(helpContentDialog_centerPanel, BorderLayout.CENTER);
		helpContentDialog_panel.add(helpContentDialog_downPanel, BorderLayout.SOUTH);
		helpContentDialog_centerPanel.setLayout(new BorderLayout());
		helpContentDialog_tab = new JTabbedPane();
		if(CalcWindow.usingFont != null)
		{
			helpContentDialog_tab.setFont(CalcWindow.usingFont);
		}
		helpContentDialog_centerPanel.add(helpContentDialog_tab, BorderLayout.CENTER);
		helpContentDialog_tabs = new JPanel[3]; //northAreaScroll
		for(int i=0; i<helpContentDialog_tabs.length; i++)
		{
			helpContentDialog_tabs[i] = new JPanel();
			helpContentDialog_tabs[i].setLayout(new BorderLayout());
			helpContentDialog_tab.add(String.valueOf(i + 1), helpContentDialog_tabs[i]);
		}
		helpContentDialog_tabs[0].add(northAreaScroll, BorderLayout.CENTER);
		helpContentArea = new JTextArea[2];
		helpContentAreaScroll = new JScrollPane[helpContentArea.length];
		for(int i=0; i<helpContentArea.length; i++)
		{
			helpContentArea[i] = new JTextArea();
			if(CalcWindow.usingFont != null)
				helpContentArea[i].setFont(CalcWindow.usingFont);
			helpContentArea[i].setLineWrap(true);
			helpContentAreaScroll[i] = new JScrollPane(helpContentArea[i], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			
		}
		helpContentDialog_tabs[1].add(helpContentAreaScroll[0], BorderLayout.CENTER);
		helpContentDialog_tabs[2].add(helpContentAreaScroll[1], BorderLayout.CENTER);
		helpContentDialog_downPanel.setLayout(new FlowLayout());
		helpContentDialog_close = new JButton(target.getLang().getText(Language.CLOSE));
		helpContentDialog_close.addActionListener(this);
		helpContentDialog_downPanel.add(helpContentDialog_close);
		helpContentDialog_titlePanel = new JPanel();
		if(gets.isUse_own_titleBar()) helpContentDialog_panel.add(helpContentDialog_titlePanel, BorderLayout.NORTH);
		else helpContentDialog.setTitle(gets.getLang().getText(Language.HELP) + " " + gets.getLang().getText(Language.EDIT));
		helpContentDialog_titlePanel.setLayout(new FlowLayout());
		helpContentDialog_title = new JLabel(gets.getLang().getText(Language.HELP) + " " + gets.getLang().getText(Language.EDIT));
		helpContentDialog_titlePanel.add(helpContentDialog_title);
		helpContentDialog_titlePanel.setBorder(new EtchedBorder());
		if(CalcWindow.usingFont != null)
		{
			helpContentDialog_close.setFont(CalcWindow.usingFont);	
			helpContentDialog_title.setFont(CalcWindow.usingFont);
		}
		if(gets.isUse_own_titleBar())
		{
			helpContentDialog_titlePanel.addMouseListener(this);
			helpContentDialog_titlePanel.addMouseMotionListener(this);
		}
		
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(gets.getLang().getText(Language.SETTING));
		if(CalcWindow.usingFont != null)
			titleLabel.setFont(CalcWindow.usingFont);
		titlePanel.add(titleLabel);
		if(gets.isUse_own_titleBar()) 
		{
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		titlePanel.setBorder(new EtchedBorder());
		
		
		lookAndFeelDialog = new JDialog(this, true);
		lookAndFeelDialog.setSize(600, 300);
		lookAndFeelDialog.setLocation((int)(screenSize.getWidth()/2 - lookAndFeelDialog.getWidth()/2), (int)(screenSize.getHeight()/2 - lookAndFeelDialog.getHeight()/2));
		if(gets.isUse_own_titleBar()) lookAndFeelDialog.setUndecorated(true);
		else lookAndFeelDialog.addWindowListener(this);
		lookAndFeelDialog_panel = new JPanel();
		lookAndFeelDialog.setLayout(new BorderLayout());
		lookAndFeelDialog.getContentPane().add(lookAndFeelDialog_panel);
		lookAndFeelDialog_panel.setLayout(new BorderLayout());
		lookAndFeelDialog_panel.setBorder(new EtchedBorder());
		lookAndFeelDialog_upPanel = new JPanel();
		lookAndFeelDialog_centerPanel = new JPanel();
		lookAndFeelDialog_downPanel = new JPanel();
		lookAndFeelDialog_panel.add(lookAndFeelDialog_upPanel, BorderLayout.NORTH);
		lookAndFeelDialog_panel.add(lookAndFeelDialog_centerPanel, BorderLayout.CENTER);
		lookAndFeelDialog_panel.add(lookAndFeelDialog_downPanel, BorderLayout.SOUTH);
		lookAndFeelDialog_upPanel.setLayout(new BorderLayout());
		lookAndFeelDialog_centerPanel.setLayout(new BorderLayout());
		lookAndFeelDialog_downPanel.setLayout(new FlowLayout());
		lookAndFeelDialog_centerPanel.setBorder(new EtchedBorder());
		lookAndFeelDialog_titlePanel = new JPanel();
		lookAndFeelDialog_titlePanel.setBorder(new EtchedBorder());
		if(gets.isUse_own_titleBar())
		{
			lookAndFeelDialog_upPanel.add(lookAndFeelDialog_titlePanel);
			lookAndFeelDialog_titlePanel.addMouseListener(this);
			lookAndFeelDialog_titlePanel.addMouseMotionListener(this);
		}
		lookAndFeelDialog_titlePanel.setLayout(new FlowLayout());
		lookAndFeelDialog_title = new JLabel(gets.getLang().getText(Language.THEME));
		lookAndFeelDialog_titlePanel.add(lookAndFeelDialog_title);
		lookAndFeelDialog_close = new JButton(gets.getLang().getText(Language.CLOSE));
		lookAndFeelDialog_close.addActionListener(this);
		lookAndFeelDialog_downPanel.add(lookAndFeelDialog_close);
		if(CalcWindow.usingFont != null)
		{
			lookAndFeelDialog_title.setFont(CalcWindow.usingFont);
			lookAndFeelDialog_close.setFont(CalcWindow.usingFont);
		}
		
		if(gets.getOs().equalsIgnoreCase("Linux")) lookAndFeelDialog_Pns = new JPanel[5];
		else lookAndFeelDialog_Pns = new JPanel[4];
		lookAndFeelDialog_contentPanel = new JPanel();
		lookAndFeelDialog_contentScroll = new JScrollPane(lookAndFeelDialog_contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lookAndFeelDialog_centerPanel.add(lookAndFeelDialog_contentScroll);
		lookAndFeelDialog_contentPanel.setLayout(new GridLayout(lookAndFeelDialog_Pns.length, 1));
		lookAndFeelDialog_lbs = new JLabel[lookAndFeelDialog_Pns.length];
		lookAndFeelDialog_fields = new JTextField[lookAndFeelDialog_Pns.length];
		lookAndFeelDialog_use = new JButton[lookAndFeelDialog_Pns.length];
		for(int i=0; i<lookAndFeelDialog_Pns.length; i++)
		{
			lookAndFeelDialog_Pns[i] = new JPanel();
			lookAndFeelDialog_lbs[i] = new JLabel();
			lookAndFeelDialog_fields[i] = new JTextField(30);
			lookAndFeelDialog_use[i] = new JButton(gets.getLang().getText(Language.USE));
			if(CalcWindow.usingFont != null)
			{
				lookAndFeelDialog_Pns[i].setFont(CalcWindow.usingFont);
				lookAndFeelDialog_lbs[i].setFont(CalcWindow.usingFont);
				lookAndFeelDialog_fields[i].setFont(CalcWindow.usingFont);
				lookAndFeelDialog_use[i].setFont(CalcWindow.usingFont);
			}
			lookAndFeelDialog_use[i].addActionListener(this);
			lookAndFeelDialog_fields[i].setEditable(false);
			lookAndFeelDialog_Pns[i].setLayout(new FlowLayout());
			lookAndFeelDialog_Pns[i].add(lookAndFeelDialog_lbs[i]);
			lookAndFeelDialog_Pns[i].add(lookAndFeelDialog_fields[i]);
			lookAndFeelDialog_Pns[i].add(lookAndFeelDialog_use[i]);
			lookAndFeelDialog_contentPanel.add(lookAndFeelDialog_Pns[i]);
		}
		lookAndFeelDialog_lbs[0].setText(gets.getOs());
		lookAndFeelDialog_fields[0].setText(UIManager.getSystemLookAndFeelClassName());
		lookAndFeelDialog_lbs[1].setText("Metal");
		lookAndFeelDialog_fields[1].setText("javax.swing.plaf.metal.MetalLookAndFeel");
		lookAndFeelDialog_lbs[2].setText("Nimbus");
		lookAndFeelDialog_fields[2].setText("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		lookAndFeelDialog_lbs[3].setText("Motif");
		lookAndFeelDialog_fields[3].setText("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		if(gets.getOs().equalsIgnoreCase("Linux"))
		{
			lookAndFeelDialog_lbs[4].setText("GTK");
			lookAndFeelDialog_fields[4].setText("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		}
		
		ai_dialog = new JDialog(this, true);
		ai_dialog.setSize(400, 300);
		ai_dialog.setLocation((int)(screenSize.getWidth()/2 - ai_dialog.getWidth()/2), (int)(screenSize.getHeight()/2 - ai_dialog.getHeight()/2));
		if(gets.isUse_own_titleBar()) ai_dialog.setUndecorated(true);
		else ai_dialog.addWindowListener(this);
		ai_dialog_mainPanel = new JPanel();
		ai_dialog_mainPanel.setBorder(new EtchedBorder());
		ai_dialog.getContentPane().setLayout(new BorderLayout());
		ai_dialog.getContentPane().add(ai_dialog_mainPanel);
		ai_dialog_upPanel = new JPanel();
		ai_dialog_centerPanel = new JPanel();
		ai_dialog_downPanel = new JPanel();
		ai_dialog_mainPanel.setLayout(new BorderLayout());
		ai_dialog_mainPanel.add(ai_dialog_upPanel, BorderLayout.NORTH);
		ai_dialog_mainPanel.add(ai_dialog_centerPanel, BorderLayout.CENTER);
		ai_dialog_mainPanel.add(ai_dialog_downPanel, BorderLayout.SOUTH);
		ai_dialog_upPanel.setLayout(new BorderLayout());
		ai_dialog_titlePanel = new JPanel();
		ai_dialog_title = new JLabel(lang.getText(Language.AI) + " " + lang.getText(Language.SELECT));
		if(gets.isUse_own_titleBar()) ai_dialog_upPanel.add(ai_dialog_titlePanel, BorderLayout.NORTH);
		ai_dialog_titlePanel.setBorder(new EtchedBorder());
		ai_dialog_titlePanel.addMouseListener(this);
		ai_dialog_titlePanel.addMouseMotionListener(this);
		ai_dialog_titlePanel.setLayout(new FlowLayout());
		ai_dialog_titlePanel.add(ai_dialog_title);		
		ai_dialog_downPanel.setLayout(new FlowLayout());
		ai_dialog_close = new JButton(lang.getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			ai_dialog_close.setFont(CalcWindow.usingFont);
		ai_dialog_close.addActionListener(this);
		ai_dialog_downPanel.add(ai_load);
		ai_dialog_downPanel.add(ai_dialog_close);
		ai_dialog_centerPanel.setLayout(new BorderLayout());
		ai_dialog_contentPanel = new JPanel();
		ai_dialog_contentScroll = new JScrollPane(ai_dialog_contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ai_dialog_centerPanel.add(ai_dialog_contentScroll);
		ai_dialog_group = new ButtonGroup();
		if(target.getAi_algorithms() != null)
		{
			ai_dialog_contents = new JPanel[target.getAi_algorithms().length];
			ai_dialog_labels = new JLabel[target.getAi_algorithms().length];
			ai_dialog_radios = new JRadioButton[target.getAi_algorithms().length];
		}
		else
		{
			ai_dialog_contents = new JPanel[1];	
			ai_dialog_labels = new JLabel[1];
			ai_dialog_radios = new JRadioButton[1];
		}
		ai_dialog_refresh();
		
		theme_refresh(gets);
	}
	public void theme_refresh(Setting setting)
	{
		if(setting.isUse_color())
		{
			mainPanel.setBackground(setting.getSelected_back());
			titlePanel.setBackground(setting.getSelected_inside_back());
			titleLabel.setForeground(setting.getSelected_fore());
			controlPanel.setBackground(setting.getSelected_back());
			controlDownPanel.setBackground(setting.getSelected_back());
			needRestartLabel.setForeground(setting.getSelected_fore());
			bottomPanel.setBackground(setting.getSelected_back());
			use_xml.setBackground(setting.getSelected_back());
			use_xml.setForeground(setting.getSelected_fore());
			moduleViewer.setForeground(setting.getSelected_fore());
			save.setForeground(setting.getSelected_fore());
			exit.setForeground(setting.getSelected_fore());
			restart.setForeground(setting.getSelected_fore());
			ai_load.setForeground(setting.getSelected_fore());
			ai_edit.setForeground(setting.getSelected_fore());
			ai_select.setForeground(setting.getSelected_fore());
			helpOpen.setForeground(setting.getSelected_fore());
			themeGen.setForeground(setting.getSelected_fore());
			reset.setForeground(setting.getSelected_fore());
			helpContentDialog_close.setForeground(setting.getSelected_fore());
			ai_dialog_close.setForeground(setting.getSelected_fore());
			lookAndFeelDialog_close.setForeground(setting.getSelected_fore());
			
			for(int i=0; i<lookAndFeelDialog_use.length; i++)
			{
				lookAndFeelDialog_use[i].setForeground(setting.getSelected_fore());
			}
			
			southInsidePanel.setBackground(setting.getSelected_inside_back());		
			southCheckBox1.setBackground(setting.getSelected_back());
			southCheckBox2.setBackground(setting.getSelected_back());
			southCheckBox3.setBackground(setting.getSelected_back());
			southCheckBox4.setBackground(setting.getSelected_back());
			southCheckBox5.setBackground(setting.getSelected_back());
			southCheckBox6.setBackground(setting.getSelected_back());
			southCheckBox7.setBackground(setting.getSelected_back());
			southCheckBox8.setBackground(setting.getSelected_back());
			southCheckBox9.setBackground(setting.getSelected_back());
			southCheckBox10.setBackground(setting.getSelected_back());
			southCheckBox11.setBackground(setting.getSelected_back());
			southCheckBox12.setBackground(setting.getSelected_back());
			southCheckBox1.setForeground(setting.getSelected_fore());
			southCheckBox2.setForeground(setting.getSelected_fore());
			southCheckBox3.setForeground(setting.getSelected_fore());
			southCheckBox4.setForeground(setting.getSelected_fore());
			southCheckBox5.setForeground(setting.getSelected_fore());
			southCheckBox6.setForeground(setting.getSelected_fore());
			southCheckBox7.setForeground(setting.getSelected_fore());
			southCheckBox8.setForeground(setting.getSelected_fore());
			southCheckBox9.setForeground(setting.getSelected_fore());
			southCheckBox10.setForeground(setting.getSelected_fore());
			southCheckBox11.setForeground(setting.getSelected_fore());
			southCheckBox12.setForeground(setting.getSelected_fore());
			for(int i=0; i<southPns.length; i++)
			{
				southPns[i].setBackground(setting.getSelected_back());
				southLabels[i].setForeground(setting.getSelected_fore());
				southFields[i].setBackground(setting.getSelected_inside_back());
				southFields[i].setForeground(setting.getSelected_fore());
			}	
			
			for(int i=0; i<northPns.length; i++)
			{
				northPns[i].setBackground(setting.getSelected_back());
				northLabels[i].setForeground(setting.getSelected_fore());
				if(i < lang.getList().length)
				{
					northFields[i].setBackground(setting.getSelected_inside_back());
					northFields[i].setForeground(setting.getSelected_fore());
				}
				else
				{
					northArea.setBackground(setting.getSelected_inside_back());
					northArea.setForeground(setting.getSelected_fore());
				}
			}
			helpContentDialog_centerPanel.setBackground(setting.getSelected_back());
			helpContentDialog_downPanel.setBackground(setting.getSelected_back());
			helpContentDialog_panel.setBackground(setting.getSelected_back());
			helpContentDialog_title.setForeground(setting.getSelected_fore());
			helpContentDialog_titlePanel.setBackground(setting.getSelected_inside_back());
			helpContentDialog_tab.setBackground(setting.getSelected_back());
			helpContentDialog_tab.setForeground(setting.getSelected_fore());
			for(int i=0; i<helpContentDialog_tabs.length; i++)
			{
				helpContentDialog_tabs[i].setBackground(setting.getSelected_back());
			}
			for(int i=0; i<helpContentArea.length; i++)
			{
				helpContentArea[i].setBackground(setting.getSelected_inside_back());
				helpContentArea[i].setForeground(setting.getSelected_fore());
			}
			
			lookAndFeelDialog_panel.setBackground(setting.getSelected_back());
			lookAndFeelDialog_centerPanel.setBackground(setting.getSelected_back());
			lookAndFeelDialog_upPanel.setBackground(setting.getSelected_back());
			lookAndFeelDialog_downPanel.setBackground(setting.getSelected_back());
			lookAndFeelDialog_titlePanel.setBackground(setting.getSelected_inside_back());
			lookAndFeelDialog_title.setForeground(setting.getSelected_fore());
			for(int i=0; i<lookAndFeelDialog_Pns.length; i++)
			{
				lookAndFeelDialog_lbs[i].setForeground(setting.getSelected_fore());
				lookAndFeelDialog_fields[i].setBackground(setting.getSelected_inside_back());
				lookAndFeelDialog_fields[i].setForeground(setting.getSelected_fore());
				lookAndFeelDialog_Pns[i].setBackground(setting.getSelected_back());
			}
			ai_dialog_mainPanel.setBackground(setting.getSelected_back());
			ai_dialog_centerPanel.setBackground(setting.getSelected_back());
			ai_dialog_upPanel.setBackground(setting.getSelected_back());
			ai_dialog_centerPanel.setBackground(setting.getSelected_back());
			ai_dialog_contentPanel.setBackground(setting.getSelected_back());
			ai_dialog_downPanel.setBackground(setting.getSelected_back());
			ai_dialog_titlePanel.setBackground(setting.getSelected_inside_back());
			if(ai_dialog_contents != null)
			{
				for(int i=0; i<ai_dialog_contents.length; i++)
				{
					if(ai_dialog_contents[i] != null) ai_dialog_contents[i].setBackground(setting.getSelected_back());
					if(ai_dialog_labels[i] != null) ai_dialog_labels[i].setForeground(setting.getSelected_fore());
					if(ai_dialog_radios != null)
					{
						if(ai_dialog_radios[i] != null)
						{
							ai_dialog_radios[i].setForeground(setting.getSelected_fore());
							ai_dialog_radios[i].setBackground(setting.getSelected_back());
						}
					}
				}
			}
			ai_dialog_title.setForeground(setting.getSelected_fore());
		}
	}
	public void ai_dialog_refresh()
	{		
		//System.out.println("1");
		ai_dialog.setVisible(false);
		this.setVisible(false);
		//System.out.println("2");
		ai_dialog_contentPanel.removeAll();
		ai_dialog_contentPanel.setLayout(new GridLayout(ai_dialog_contents.length, 1));
		if(ai_dialog_radios != null)
		{
			for(int i=0; i<ai_dialog_radios.length; i++)
			{
				if(ai_dialog_radios[i] != null)
				{
					ai_dialog_group.remove(ai_dialog_radios[i]);
					ai_dialog_radios[i].removeMouseListener(this);
				}
			}
		}	
		//System.out.println("3");
		if(target.getAi_algorithms() != null)
		{
			ai_dialog_contents = new JPanel[target.getAi_algorithms().length];
			ai_dialog_labels = new JLabel[target.getAi_algorithms().length];
			ai_dialog_radios = new JRadioButton[target.getAi_algorithms().length];
		}
		else
		{
			ai_dialog_contents = new JPanel[1];	
			ai_dialog_labels = new JLabel[1];
			ai_dialog_radios = new JRadioButton[1];
		}
		for(int i=0; i<ai_dialog_radios.length; i++)
		{
			ai_dialog_group.add(ai_dialog_radios[i]);
		}
		if(target.getUse_algorithm_index() < ai_dialog_radios.length)
		{
			if(ai_dialog_radios[target.getUse_algorithm_index()] != null) ai_dialog_radios[target.getUse_algorithm_index()].setSelected(true);
		}
		ai_dialog_contentPanel.setLayout(new GridLayout(ai_dialog_contents.length, 1));
		String names = "";
		if(target.getAi_algorithms() != null && target.getAi_algorithms()[0] != null)
		{
			for(int i=0; i<ai_dialog_contents.length; i++)
			{
				if(target.getAi_algorithms() != null && target.getAi_algorithms()[i] != null) names = target.getAi_algorithms()[i].getName();
				ai_dialog_contents[i] = new JPanel();
				ai_dialog_labels[i] = new JLabel(String.valueOf((i+1)) + ". " + names);
				ai_dialog_radios[i] = new JRadioButton();
				if(CalcWindow.usingFont != null)
				{
					ai_dialog_labels[i].setFont(CalcWindow.usingFont);
					ai_dialog_radios[i].setFont(CalcWindow.usingFont);
				}
				ai_dialog_radios[i].addMouseListener(this);
				ai_dialog_group.add(ai_dialog_radios[i]);
				ai_dialog_contents[i].setLayout(new FlowLayout());
				ai_dialog_contents[i].add(ai_dialog_labels[i]);
				ai_dialog_contents[i].add(ai_dialog_radios[i]);
				ai_dialog_contentPanel.add(ai_dialog_contents[i]);
			}
		}		
		theme_refresh(target);
		this.setVisible(true);
		//ai_dialog.setVisible(true);
	}
	public void open()
	{
		Setting temp = target;
		String path_original = temp.getDefault_path() + "setting.clst";
		String path_xml = temp.getDefault_path() + "setting.xml";
		boolean fileAlreadyExist = false;
		boolean exist_original = false; // true : original, false : xml or not exist
		File file;
		try
		{
			file = new File(path_original);
			if(file.exists())
			{
				exist_original = true;
				fileAlreadyExist = true;
			}
			else
			{
				file = new File(path_xml);
				if(file.exists())
				{
					exist_original = false;
					fileAlreadyExist = true;
				}
				else
				{
					fileAlreadyExist = false;
				}
			}
		}
		catch(Exception e)
		{
			fileAlreadyExist = false;
		}
		if(fileAlreadyExist)
		{
			if(exist_original)
			{
				use_xml.setSelected(false);
			}
			else
			{
				use_xml.setSelected(true);
			}
			if(target.accepted())
			{
				use_xml.setEnabled(true);
			}
			else
			{
				use_xml.setEnabled(false);
			}
		}
		
		load();
		this.setVisible(true);
	}
	public void exit()
	{
		try
		{
			if(independence) System.exit(0);
			else
			{
				this.setVisible(false);				
			}
		} 
		catch (Exception e)
		{
			
		}
	}
	/*
	private void north_refresh()
	{
		this.setVisible(false);
		northInsidePanel.removeAll();
		for(int i=0; i<northPns.length; i++)
		{
			northPns[i].removeAll();
		}
		northPns = new JPanel[lang.getList().length + 1];
		northInsideScroll = new JScrollPane(northInsidePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		northPanel.add(northInsideScroll, BorderLayout.CENTER);
		northInsidePanel.setLayout(new GridLayout(lang.getList().length + 1, 1));
		northLabels = new JLabel[lang.getList().length + 1];
		northFields = new JTextField[lang.getList().length];
		for(int i=0; i<northPns.length; i++)
		{
			northPns[i] = new JPanel();
			northPns[i].setLayout(new FlowLayout());
			if(i < lang.getList().length)
			{				
				northLabels[i] = new JLabel(lang.getText(lang.getList()[i]));
				northFields[i] = new JTextField(20);
				northFields[i].setText(lang.getText(lang.getList()[i]));
				northPns[i].add(northLabels[i]);
				northPns[i].add(northFields[i]);				
			}
			else
			{
				northLabels[i] = new JLabel("");
				northArea = new JTextArea();
				northAreaScroll = new JScrollPane(northArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				northArea.setLineWrap(true);
				northArea.setText(lang.getText(Language.HELP_CONTENT));
				northPns[i].add(northLabels[i]);
				//northPns[i].add(northAreaScroll);					
			}
			northInsidePanel.add(northPns[i]);
		}
		this.setVisible(true);
	}*/
	public void load()
	{
		southFields[0].setText(String.valueOf(target.getWidth()));
		southFields[1].setText(String.valueOf(target.getHeight()));
		southFields[2].setText(String.valueOf(target.getSelected_back().getRed()));
		southFields[3].setText(String.valueOf(target.getSelected_back().getGreen()));
		southFields[4].setText(String.valueOf(target.getSelected_back().getBlue()));
		southFields[5].setText(String.valueOf(target.getSelected_fore().getRed()));
		southFields[6].setText(String.valueOf(target.getSelected_fore().getGreen()));
		southFields[7].setText(String.valueOf(target.getSelected_fore().getBlue()));
		southFields[8].setText(String.valueOf(target.getSelected_inside_back().getRed()));
		southFields[9].setText(String.valueOf(target.getSelected_inside_back().getGreen()));
		southFields[10].setText(String.valueOf(target.getSelected_inside_back().getBlue()));
		southFields[11].setText(String.valueOf(target.getUnselected_back().getRed()));
		southFields[12].setText(String.valueOf(target.getUnselected_back().getGreen()));
		southFields[13].setText(String.valueOf(target.getUnselected_back().getBlue()));
		southFields[14].setText(String.valueOf(target.getUnselected_fore().getRed()));
		southFields[15].setText(String.valueOf(target.getUnselected_fore().getGreen()));
		southFields[16].setText(String.valueOf(target.getUnselected_fore().getBlue()));
		southFields[17].setText(String.valueOf(target.getUnselected_inside_back().getRed()));
		southFields[18].setText(String.valueOf(target.getUnselected_inside_back().getGreen()));
		southFields[19].setText(String.valueOf(target.getUnselected_inside_back().getBlue()));
		southFields[20].setText(String.valueOf(target.getSlots()));
		southFields[21].setText(String.valueOf(target.getStart_givenCards()));
		southFields[27].setText(String.valueOf(target.getAi_difficulty()));
		southFields[29].setText("+" + target.getCard_separator() + "1");
		southFields[32].setText(String.valueOf(target.getChange_card_count()));
		southFields[38].setText(String.valueOf(target.getUse_track_save_delay()));
		southFields[39].setText(String.valueOf(target.getPlusMinus_card_count()));
		southFields[40].setText(String.valueOf(target.getMultiply_card_count()));
		southCheckBox1.setSelected(target.isUse_own_titleBar());
		southCheckBox2.setSelected(target.isActive_authority_mode());
		southCheckBox3.setSelected(target.isUseAlertWindow());
		southCheckBox4.setSelected(target.isUse_color());
		southCheckBox5.setSelected(target.isError_printDetail());
		southCheckBox6.setSelected(target.isCenter_tab());
		southCheckBox7.setSelected(target.isNotice_not_view());
		southCheckBox8.setSelected(target.isScrollBar());
		southCheckBox9.setSelected(target.isUse_other_algorithm());
		southCheckBox10.setSelected(target.isClassic_mode());
		southCheckBox11.setSelected(target.isUse_track());
		southCheckBox12.setSelected(target.isUse_track_realtime());
		helpContentArea[0].setText(target.getLang().getText(Language.NOTICE_NULL));
		helpContentArea[1].setText(target.getLang().getText(Language.INPUT_SERIAL_AGREEMENT));
		before_scrollBar = target.isScrollBar();
		//restart.setEnabled(true);
		restart.setEnabled(false);
		if(target.getLookAndFeel() == null || target.getLookAndFeel().equals("")) southFields[24].setText("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		else southFields[24].setText(target.getLookAndFeel());
		
		
		if(southCheckBox4.isSelected())
		{
			for(int i=2; i<=19; i++)
			{
				southFields[i].setEditable(true);
			}
		}
		else
		{
			for(int i=2; i<=19; i++)
			{
				southFields[i].setEditable(false);
			}
		}
		
	}
	public void save() throws Exception
	{
		save(true);
	}
	public void save(boolean fileSave) throws Exception
	{
		try
		{
			for(int i=0; i<lang.getList().length; i++)
			{
				target.getLang().setText(i, northFields[i].getText());
				//System.out.println(i + " : " + northFields[i].getText());
			}
			target.getLang().setText(Language.HELP_CONTENT, northArea.getText());
			target.setWidth(Integer.parseInt(southFields[0].getText()));
			target.setHeight(Integer.parseInt(southFields[1].getText()));
			int r, g, b;
			r = Integer.parseInt(southFields[2].getText());
			g = Integer.parseInt(southFields[3].getText());
			b = Integer.parseInt(southFields[4].getText());
			target.setSelected_back(new Color(r, g, b));
			r = Integer.parseInt(southFields[5].getText());
			g = Integer.parseInt(southFields[6].getText());
			b = Integer.parseInt(southFields[7].getText());
			target.setSelected_fore(new Color(r, g, b));
			r = Integer.parseInt(southFields[8].getText());
			g = Integer.parseInt(southFields[9].getText());
			b = Integer.parseInt(southFields[10].getText());
			target.setSelected_inside_back(new Color(r, g, b));
			r = Integer.parseInt(southFields[11].getText());
			g = Integer.parseInt(southFields[12].getText());
			b = Integer.parseInt(southFields[13].getText());
			target.setUnselected_back(new Color(r, g, b));
			r = Integer.parseInt(southFields[14].getText());
			g = Integer.parseInt(southFields[15].getText());
			b = Integer.parseInt(southFields[16].getText());
			target.setUnselected_fore(new Color(r, g, b));
			r = Integer.parseInt(southFields[17].getText());
			g = Integer.parseInt(southFields[18].getText());
			b = Integer.parseInt(southFields[19].getText());
			target.setUnselected_inside_back(new Color(r, g, b));
			target.setSlots(Integer.parseInt(southFields[20].getText()));
			target.setUse_own_titleBar(southCheckBox1.isSelected());
			target.setActive_authority_mode(southCheckBox2.isSelected());
			target.setUseAlertWindow(southCheckBox3.isSelected());
			target.setUse_color(southCheckBox4.isSelected());
			target.setError_printDetail(southCheckBox5.isSelected());
			target.setCenter_tab(southCheckBox6.isSelected());
			//System.out.println(southCheckBox6.isSelected());
			target.setNotice_not_view(southCheckBox7.isSelected());
			target.setScrollBar(southCheckBox8.isSelected());
			target.setStart_givenCards(Integer.parseInt(southFields[21].getText()));
			target.setLookAndFeel(southFields[24].getText());
			target.setAi_difficulty(Integer.parseInt(southFields[27].getText()));
			target.setVer_main(CalcWindow.version_main);
			target.setVer_sub1(CalcWindow.version_sub_1);
			target.setVer_sub2(CalcWindow.version_sub_2);		
			target.setChange_card_count(Integer.parseInt(southFields[32].getText()));
			target.setUse_other_algorithm(southCheckBox9.isSelected());
			target.setClassic_mode(southCheckBox10.isSelected());
			target.setUse_track(southCheckBox11.isSelected());
			target.setUse_track_realtime(southCheckBox12.isSelected());
			target.setUse_track_save_delay(Integer.parseInt(southFields[38].getText()));
			target.setPlusMinus_card_count(Integer.parseInt(southFields[39].getText()));
			target.setMultiply_card_count(Integer.parseInt(southFields[40].getText()));
			//heloContentArea[0].setText(target.lang.getText(Language.NOTICE_NULL));
			//heloContentArea[1].setText(target.lang.getText(Language.INPUT_SERIAL_AGREEMENT));
			target.getLang().setText(Language.NOTICE_NULL, helpContentArea[0].getText());
			target.getLang().setText(Language.INPUT_SERIAL_AGREEMENT, helpContentArea[1].getText());
			if(target.getAi_algorithms() == null)
			{
				target.setAi_algorithms(new AI_Algorithm[1]);			
				target.setUse_other_algorithm(false);
			}
			if(target.getAi_algorithms()[0] == null)
			{
				target.getAi_algorithms()[0] = new AI_Algorithm();
				target.setUse_other_algorithm(false);
			}
			
			char[] separator_temp = southFields[29].getText().toCharArray();
			String separator_complete = "";
			for(int i=0; i<separator_temp.length; i++)
			{
				if(i != 0 && i != (separator_temp.length - 1)) separator_complete = separator_complete + String.valueOf(separator_temp[i]);
			}
			target.setCard_separator(separator_complete);
			
			//System.out.println("~~~");
			//System.out.println("Saved ver " + target.ver_main + "." + target.ver_sub1 + "" + target.ver_sub2);
						
			if(fileSave)
			{
				if(use_xml.isSelected()) save_xml();
				else save_file();
				
				
				FileWriter langWriter = null;
				BufferedWriter bufferWriter = null;
				try
				{
					File dir = new File(target.getDefault_path());
					if(! dir.exists()) dir.mkdir();
					File langFile = new File(target.getDefault_path() + "lang.cfg");
					if(langFile.exists())
					{					
						langWriter = new FileWriter(langFile);
						bufferWriter = new BufferedWriter(langWriter);
						if(target.getLang() instanceof Korean)
						{
							bufferWriter.write(UserDefinedKor.convert(target.getLang()));
						}
						else
						{
							bufferWriter.write(UserDefinedLang.convert(target.getLang()));
						}
					}
				} 				
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						bufferWriter.close();
					}
					catch (Exception e)
					{
						
					}
					try
					{
						langWriter.close();
					}
					catch (Exception e)
					{
						
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}	
	public void save_file() throws FileNotFoundException, IOException
	{
		File file = new File(target.getDefault_path());
		if(! file.exists())
		{
			file.mkdir();
		}		
		target.setVer_main(CalcWindow.version_main);
		target.setVer_sub1(CalcWindow.version_sub_1);
		target.setVer_sub2(CalcWindow.version_sub_2);
		target.wrapperObjectClean();
		FileOutputStream fout = new FileOutputStream(target.getDefault_path() + "setting.clst");
		ObjectOutputStream obout = new ObjectOutputStream(fout);
		obout.writeObject(target);
		obout.close();
		fout.close();
		JOptionPane.showMessageDialog(this, lang.getText(Language.SAVE) + " " + lang.getText(Language.COMPLETE) + " : " + target.getDefault_path() + "setting.clst");
	}
	public void save_xml() throws FileNotFoundException, IOException
	{
		File file = new File(target.getDefault_path());
		if(! file.exists())
		{
			file.mkdir();
		}
		target.setVer_main(CalcWindow.version_main);
		target.setVer_sub1(CalcWindow.version_sub_1);
		target.setVer_sub2(CalcWindow.version_sub_2);
		target.wrapperToObjects();
		FileOutputStream fout = new FileOutputStream(target.getDefault_path() + "setting.xml");
		XMLEncoder encoder = new XMLEncoder(fout);
		encoder.writeObject(target);
		encoder.close();
		fout.close();
		JOptionPane.showMessageDialog(this, lang.getText(Language.SAVE) + " " + lang.getText(Language.COMPLETE) + " : " + target.getDefault_path() + "setting.xml");
	}
	private void ai_load(File path)
	{
		try
		{
			FileInputStream fin = new FileInputStream(path);
			XMLDecoder decoder = new XMLDecoder(fin);
			AI_Algorithm algorithm = (AI_Algorithm) decoder.readObject();
			decoder.close();
			fin.close();
			ai_dialog.setVisible(false);
			target.input(algorithm);			
			JOptionPane.showMessageDialog(this, target.getLang().getText(Language.LOAD) + " " + target.getLang().getText(Language.COMPLETE));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
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
		if(ob == ai_dialog)
		{
			ai_dialog.setVisible(false);
		}
		else if(ob == helpContentDialog)
		{
			helpContentDialog.setVisible(false);
		}
		else if(ob == lookAndFeelDialog)
		{
			lookAndFeelDialog.setVisible(false);
		}
		else if(ob == this)
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
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == save)
		{
			try
			{
				save();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
		}
		else if(ob == exit)
		{
			exit();
		}
		else if(ob == restart)
		{
			this.setVisible(false);
			try
			{
				Vector<String> accumulator = new Vector<String>();
				String[] result;
				if(arguments != null)
				{
					for(int i=0; i<arguments.length; i++)
					{
						if(! arguments[i].equalsIgnoreCase("set_mode"))
						{
							accumulator.add(new String(arguments[i]));
						}
					}						
				}
				result = new String[accumulator.size()];
				for(int i=0; i<result.length; i++)
				{
					result[i] = accumulator.get(i);
				}
				try
				{
					save(false);
					// manager.run_calc(result, null);
					manager.run_calc(result, target, null);
				} 
				catch (Exception e1)
				{
					if(target.isError_printDetail())
					{
						e1.printStackTrace();
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
				}				
			} 
			catch (HeadlessException e1)
			{
				if(target.isError_printDetail())
					e1.printStackTrace();
			}
		}
		else if(ob == reset)
		{
			target = Setting.getNewInstance();
			load();
			//lang = target.lang.clone();
			//north_refresh();
		}
		else if(ob == helpContentDialog_close)
		{
			helpContentDialog.setVisible(false);
		}
		else if(ob == helpOpen)
		{
			helpContentDialog.setVisible(true);
		}
		else if(ob == themeGen)
		{
			lookAndFeelDialog.setVisible(true);
		}
		else if(ob == lookAndFeelDialog_close)
		{
			lookAndFeelDialog.setVisible(false);
		}
		else if(ob == moduleViewer)
		{
			moduleManager.setVisible(true);
		}
		else if(ob == ai_select)
		{
			ai_dialog_refresh();
			if(target.getAi_algorithms() != null && target.isError_printDetail())
			for(int i=0; i<target.getAi_algorithms().length; i++)
			{
				if(target.getAi_algorithms()[i] != null) System.out.println(target.getAi_algorithms()[i].getName());
			}
			ai_dialog.setVisible(true);
		}
		else if(ob == ai_dialog_close)
		{
			for(int i=0; i<ai_dialog_radios.length; i++)
			{
				if(ai_dialog_radios[i] != null && ai_dialog_radios[i].isSelected())
				{
					target.setUse_algorithm_index(i);
				}
			}
			// southCheckBox9.setSelected(true);
			ai_dialog.setVisible(false);
		}
		else if(ob == ai_load)
		{
			ff1 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".cai")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Artificial Intelligent (.cai)";
				}
			};
			if(cfd == null) 
			{
				cfd = new JFileChooser(target.getDefault_path());
				cfd.setFileFilter(ff1);
			}
			int cfds = cfd.showOpenDialog(ai_dialog);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd.getSelectedFile();
				String newFileName = newFile.getAbsolutePath();
				try
				{
					if(newFileName.endsWith(".cai") || newFileName.endsWith(".CAI"))
					{
						ai_load(newFile);
					}
					else
					{
						newFileName = newFileName + ".cai";
						newFile = new File(newFileName);
						ai_load(newFile);
					}
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}			
		}
		else if(ob == ai_edit)
		{
			ai_editor = new AI_Editor(this, false, target);
			ai_editor.open();
		}
		else
		{
			int index = 0;
			String result = "";
			for(int i=0; i<lookAndFeelDialog_use.length; i++)
			{
				if(ob == lookAndFeelDialog_use[i])
				{
					index = i;
					break;
				}
			}
			result = lookAndFeelDialog_fields[index].getText();			
			southFields[24].setText(result);
			lookAndFeelDialog.setVisible(false);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel) this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);
		else if(ob == helpContentDialog_titlePanel) helpContentDialog.setLocation(e.getXOnScreen() - help_mousex, e.getYOnScreen() - help_mousey);
		else if(ob == lookAndFeelDialog_titlePanel) lookAndFeelDialog.setLocation(e.getXOnScreen() - look_mousex, e.getYOnScreen() - look_mousey);
		else if(ob == ai_dialog_titlePanel) ai_dialog.setLocation(e.getXOnScreen() - ai_mousex, e.getYOnScreen() - ai_mousey);
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
		else if(ob == helpContentDialog_titlePanel)
		{
			help_mousex = e.getX();
			help_mousey = e.getY();
		}
		else if(ob == lookAndFeelDialog_titlePanel)
		{
			look_mousex = e.getX();
			look_mousey = e.getY();
		}
		else if(ob == ai_dialog_titlePanel)
		{
			ai_mousex = e.getX();
			ai_mousey = e.getY();
		}
		else
		{
			//southCheckBox9.setSelected(true);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == southCheckBox4)
		{
			if(southCheckBox4.isSelected())
			{
				for(int i=2; i<=19; i++)
				{
					southFields[i].setEditable(true);
				}
			}
			else
			{
				for(int i=2; i<=19; i++)
				{
					southFields[i].setEditable(false);
				}
			}
		}
		else if(ob == southCheckBox8)
		{
			// System.out.println(southCheckBox8.isSelected() + ", " + before_scrollBar);
			
			if(southCheckBox8.isSelected() == before_scrollBar)
			{				
				//restart.setEnabled(true);
				restart.setEnabled(false);
			}
			else
			{
				restart.setEnabled(false);
			}
			
		}
		else if(ob == southCheckBox11)
		{
			if(southCheckBox11.isSelected())
			{
				southCheckBox12.setEnabled(true);
			}
			else
			{
				southCheckBox12.setEnabled(false);
				southCheckBox12.setSelected(false);
			}
		}
	}

}
class ModuleViewer extends JFrame implements Openable, ListSelectionListener, ActionListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -3801676859211082960L;
	private SettingManager superContent;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JPanel upPanel;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JList moduleList;
	private JScrollPane moduleScroll;
	private int mousex;
	private int mousey;
	private JPanel centerMainPanel;
	private JPanel centerSubPanel;
	private JTextArea centerDescriptionPanel;
	private JScrollPane centerDescriptionScroll;
	private JButton remove;
	private JButton close;
	private boolean selected = false;
	private int selectedIndex = 0;
	private Language lang;
	private JButton load;
	private JFileChooser cfd;
	private ModulePackageViewer modulePackageViewer;
	private ModulePackage loaded;

	public ModuleViewer(SettingManager superContent, Setting sets)
	{
		this.superContent = superContent;
		this.setSize(500, 400);
		
		if(sets.isUse_own_titleBar())
		{
			this.setUndecorated(true);
		}
		lang = sets.getLang().clone();
		
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setBackground(sets.getSelected_back());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		upPanel = new JPanel();
		leftPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		bottomPanel.setBackground(sets.getSelected_back());
		upPanel.setBackground(sets.getSelected_back());
		
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titleLabel = new JLabel(sets.getLang().getText(Language.MODULE));
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(sets.getSelected_inside_back());
		titlePanel.add(titleLabel);
		titleLabel.setForeground(sets.getSelected_fore());
		
		if(sets.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.CENTER);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		
		moduleList = new JList();
		moduleScroll = new JScrollPane(moduleList);
		moduleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(moduleScroll);
		moduleList.setBackground(sets.getSelected_back());
		moduleList.setForeground(sets.getSelected_fore());
		moduleList.addListSelectionListener(this);
		
		centerPanel.setLayout(new BorderLayout());
		centerMainPanel = new JPanel();
		centerSubPanel = new JPanel();
		centerPanel.add(centerMainPanel, BorderLayout.CENTER);
		centerPanel.add(centerSubPanel, BorderLayout.SOUTH);
		centerMainPanel.setBackground(sets.getSelected_back());
		centerSubPanel.setBackground(sets.getSelected_back());		
		centerDescriptionPanel = new JTextArea();
		centerDescriptionScroll = new JScrollPane(centerDescriptionPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerDescriptionPanel.setEditable(false);
		centerDescriptionPanel.setLineWrap(true);
		centerMainPanel.setLayout(new BorderLayout());		
		centerMainPanel.add(centerDescriptionScroll, BorderLayout.CENTER);
		centerDescriptionPanel.setBackground(sets.getSelected_inside_back());
		centerDescriptionPanel.setForeground(sets.getSelected_fore());
		centerSubPanel.setLayout(new FlowLayout());
		
		remove = new JButton(sets.getLang().getText(Language.REMOVE));
		remove.addActionListener(this);
		remove.setForeground(sets.getSelected_fore());
		centerSubPanel.add(remove);
		
		bottomPanel.setLayout(new FlowLayout());
		load = new JButton(lang.getText(Language.LOAD));
		load.addActionListener(this);
		load.setForeground(sets.getSelected_fore());
		close = new JButton(sets.getLang().getText(Language.CLOSE));
		close.addActionListener(this);
		close.setForeground(sets.getSelected_fore());
		bottomPanel.add(load);
		bottomPanel.add(close);
		
		modulePackageViewer = new ModulePackageViewer(this, sets);
	}
	public void refresh()
	{
		Vector<ScriptModule> modules = superContent.target.getModules();
		String[] moduleNames = new String[modules.size()];
		for(int i=0; i<moduleNames.length; i++)
		{
			moduleNames[i] = modules.get(i).getName();
		}
		moduleList.setListData(moduleNames);
	}
	public void loadOk()
	{
		for(int i=0; i<loaded.getModules().size(); i++)
		{
			superContent.target.getModules().add(loaded.getModules().get(i).clone());
		}
		refresh();
	}
	public void loadCancel()
	{
		loaded = null;
	}
	@Override
	public void open()
	{
		refresh();
		selected = false;
		centerDescriptionPanel.setText("");
		this.setVisible(true);
	}

	@Override
	public void exit()
	{
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{
			this.setVisible(false);
		}
		else if(ob == remove)
		{
			if(selected)
			{
				superContent.target.getModules().remove(selectedIndex);
				selected = false;
				centerDescriptionPanel.setText("");
				refresh();
			}
		}
		else if(ob == load)
		{
			FileFilter ff1 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".cpack")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Module Package (.cpack)";
				}
			};
			if(cfd == null) 
			{
				cfd = new JFileChooser(superContent.target.getDefault_path());
				cfd.setFileFilter(ff1);
			}
			int cfds = cfd.showOpenDialog(this);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd.getSelectedFile();
				try
				{
					loaded = ModulePackage.load(newFile);
					modulePackageViewer.input(loaded);
					modulePackageViewer.setVisible(true);
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, lang.getText(Language.ERROR) + " : " + e1.getMessage());
				}
			}	
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == moduleList)
		{
			selectedIndex = moduleList.getSelectedIndex();
			selected = true;
			String contents = lang.getText(Language.TYPE) + " : ";
			if(superContent.target.getModules().get(selectedIndex) instanceof BlockModule)
			{
				contents = contents + lang.getText(Language.BLOCK) + "\n\n";
			}
			else if(superContent.target.getModules().get(selectedIndex) instanceof SpecialCardModule)
			{
				contents = contents + lang.getText(Language.CARD) + "\n\n";
			}
			else
			{
				selected = false;
				centerDescriptionPanel.setText(lang.getText(Language.ERROR));
				return;
			}
			contents = contents + lang.getText(Language.NAME) + " : " + superContent.target.getModules().get(selectedIndex).getName() + "\n\n";
			contents = contents + superContent.target.getModules().get(selectedIndex).getDescription();
			centerDescriptionPanel.setText(contents);
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);
		
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
		mousex = e.getX();
		mousey = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}	
}
class ModulePackageViewer extends JDialog implements ActionListener, MouseListener, MouseMotionListener, WindowListener
{
	private static final long serialVersionUID = -2496185111634514568L;
	private JPanel mainPanel;
	private int mousex;
	private int mousey;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JLabel titleLabel;
	private JButton cancel;
	private JButton ok;
	private ModuleViewer upper;
	private JTextArea texts;
	private JScrollPane textScroll;
	private Language lang;

	public ModulePackageViewer(ModuleViewer upper, Setting sets)
	{
		super(upper, true);
		this.upper = upper;
		this.setSize(500, 400);
		
		if(sets.isUse_own_titleBar())
		{
			this.setUndecorated(true);
		}
		lang = sets.getLang().clone();
		
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.addWindowListener(this);
		
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setBackground(sets.getSelected_back());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		upPanel.setBackground(sets.getSelected_inside_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		
		titleLabel = new JLabel(sets.getLang().getText(Language.LOAD) + " " + sets.getLang().getText(Language.MODULE));
		titleLabel.setForeground(sets.getSelected_fore());
		if(sets.isUse_own_titleBar())
		{
			mainPanel.add(upPanel, BorderLayout.NORTH);
			upPanel.addMouseListener(this);
			upPanel.addMouseMotionListener(this);
			upPanel.setLayout(new FlowLayout());
			upPanel.add(titleLabel);
			upPanel.setBorder(new EtchedBorder());
		}
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		downPanel.setLayout(new FlowLayout());
		cancel = new JButton(sets.getLang().getText(Language.CANCEL));
		cancel.addActionListener(this);
		cancel.setForeground(sets.getSelected_fore());
		ok = new JButton(sets.getLang().getText(Language.OK));
		ok.addActionListener(this);
		ok.setForeground(sets.getSelected_fore());
		downPanel.add(ok);
		downPanel.add(cancel);
		
		centerPanel.setLayout(new BorderLayout());
		texts = new JTextArea();
		textScroll = new JScrollPane(texts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		texts.setLineWrap(true);
		texts.setEditable(false);
		texts.setBackground(sets.getSelected_inside_back());
		texts.setForeground(sets.getSelected_fore());
		centerPanel.add(textScroll);
		
	}
	public void input(ModulePackage packs)
	{
		String contents = "";
		contents = contents + lang.getText(Language.WARN) + " : " + lang.getText(Language.WARN_SCRIPT) + "\n\n";
		contents = contents + lang.getText(Language.NAME) + " : " + packs.getName() + "\n\n";
		contents = contents + packs.getDescription() + "\n\n";
		for(int i=0; i<packs.getModules().size(); i++)
		{
			contents = contents + packs.getModules().get(i).getName() + "\n";
		}
		texts.setText(contents);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == ok)
		{
			upper.loadOk();
			this.setVisible(false);
		}
		else if(ob == cancel)
		{
			upper.loadCancel();
			this.setVisible(false);
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);
		
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
		mousex = e.getX();
		mousey = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
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
		upper.loadCancel();
		this.setVisible(false);
		
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
}