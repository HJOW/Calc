package setting;

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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lang.Korean;
import lang.Language;
import main_classes.CalcWindow;
import main_classes.Openable;
import main_classes.StandardCard;


public class ScenarioEditor extends JDialog implements Openable, ActionListener, ItemListener, WindowListener, MouseListener, MouseMotionListener, ChangeListener, ListSelectionListener, Runnable
{
	private static final long serialVersionUID = -1195001412194035572L;
	int lang = 0;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private JPanel centerPanel;
	private JPanel[] controlPanels;
	private String[] controlTitles;
	private JPanel[] basicPns;
	private JLabel[] basicLabels;
	private JTextField nameField;
	private JTextField korNameField;
	private JSpinner difficultyPinn;
	private JSpinner firstStar;
	private JSpinner secondStar;
	private JSpinner thirdStar;
	private JPanel[] descPns;
	private JPanel[] descContentPns;
	private JPanel[] descLabelPns;
	private JLabel[] descLabels;
	private JTextArea descriptionArea;
	private JTextArea korDescriptionArea;
	private JScrollPane descriptionScroll;
	private JScrollPane korDescriptionScroll;
	private JPanel[] ratioPns;
	private JLabel[] ratioLabels;
	private JSpinner bonusPinner;
	private JSpinner plusminusRatioPinner;
	private JSpinner multiplyRatioPinner;
	private JSpinner playerCardCountPinner;
	private JSpinner aiCardCountPinner;
	private JSpinner timeoutPinner;
	private JSpinner limitTurnPinner;
	private JSpinner changeCardPinner;
	private boolean independence = false;
	private JPanel scriptLabelPanel;
	private JTextArea scriptArea;
	private JLabel scriptLabel;
	private JCheckBox scriptCheck;
	private JScrollPane scriptScroll;
	private JPanel[] filePns;
	private JButton saveButton;
	private JButton loadButton;
	private JButton exitButton;
	private JButton newButton;
	private ScenarioFileFilter filter;
	private JTextArea contentArea;
	private JScrollPane contentScroll;
	private JFileChooser fchooser;
	private Setting sets;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mousex;
	private int mousey;
	private JPanel upPanel;
	private JButton exitButton2;
	private File target = null;
	private CalcWindow superComponent = null;
	private JPanel[] savePns;
	private JButton saveasButton;
	private JPanel[] randomPns;
	private JPanel[] messagePns;
	private JPanel[] addCardPns;
	private JLabel[] randomLbs;
	private JCheckBox randomPlusCheck;
	private JSpinner randomPlusMinPinner;
	private JLabel randomPlusMidLabel;
	private JSpinner randomPlusMaxPinner;
	private JCheckBox randomMultiplyCheck;
	private JSpinner randomMultiplyMinPinner;
	private JLabel randomMultiplyMidLabel;
	private JSpinner randomMultiplyMaxPinner;
	private JCheckBox randomPlayerCheck;
	private JSpinner randomPlayerMinPinner;
	private JLabel randomPlayerMidLabel;
	private JSpinner randomPlayerMaxPinner;
	private JCheckBox randomDeckCheck;
	private JSpinner randomDeckMinPinner;
	private JLabel randomDeckMidLabel;
	private JSpinner randomDeckMaxPinner;
	private JPanel[] endMessagePns;
	private JPanel[] korEndMessagePns;
	private JPanel[] failMessagePns;
	private JPanel[] korFailMessagePns;
	private JLabel endMessageLabel;
	private JCheckBox endMessageCheck;
	private JTextArea endMessageArea;
	private JScrollPane endMessageScroll;
	private JTextArea korEndMessageArea;
	private JScrollPane korEndMessageScroll;
	private JCheckBox korEndMessageCheck;
	private JLabel korEndMessageLabel;
	private JCheckBox failMessageCheck;
	private JLabel failMessageLabel;
	private JTextArea failMessageArea;
	private JScrollPane failMessageScroll;
	private JCheckBox korFailMessageCheck;
	private JLabel korFailMessageLabel;
	private JTextArea korFailMessageArea;
	private JScrollPane korFailMessageScroll;
	private JPanel addPlayerCardLabelPanel;
	private JLabel addPlayerCardLabel;
	private JList addPlayerCardList;
	private JScrollPane addPlayerCardScroll;
	private JPanel addPlayerCardControlPanel;
	private Vector<StandardCards> addPlayerCardContents;
	private JComboBox addPlayerCardOperation;
	private JSpinner addPlayerCardNumber;
	private JButton addPlayerCardPlus;
	private Vector<StandardCards> addAICardContents;
	private JPanel addAICardLabelPanel;
	private JLabel addAICardLabel;
	private JList addAICardList;
	private JScrollPane addAICardScroll;
	private JPanel addAICardControlPanel;
	private JComboBox addAICardOperation;
	private JSpinner addAICardNumber;
	private JButton addAICardPlus;
	private Vector<StandardCards> addDeckCardContents;
	private JPanel addDeckCardLabelPanel;
	private JLabel addDeckCardLabel;
	private JList addDeckCardList;
	private JScrollPane addDeckCardScroll;
	private JPanel addDeckCardControlPanel;
	private JComboBox addDeckCardOperation;
	private JSpinner addDeckCardNumber;
	private JButton addDeckCardPlus;
	private JPanel rightPanel;
	private JTextArea rightArea;
	private JScrollPane rightScroll;
	private SpinnerNumberModel spinnerModel;
	private JComboBox addPlayerCardHowMany;
	private JComboBox addAICardHowMany;
	private JComboBox addDeckCardHowMany;
	private int addPlayerCardListSelected = -1;
	private int addAICardListSelected = -1;
	private int addDeckCardListSelected = -1;
	private int addPlayerCardListClicked;
	private int addAICardListClicked;
	private int addDeckCardListClicked;
	private JPanel[] etcPns;
	private JLabel[] etcLbs;
	private JTextField eventWebPage;
	private JCheckBox eventUse;
	private JSpinner eventYear;
	private JComboBox eventMonth;
	private JComboBox eventDay;
	private JComboBox eventHour;
	private JComboBox eventMinute;
	private JComboBox eventSecond;
	private JButton authorizeButton;
	private long auths = 0;
	private boolean event_onlywin = false;
	public ScenarioEditor()
	{
		super();
		init();
	}
	public ScenarioEditor(boolean independence, Setting sets)
	{
		super();
		this.independence = independence;
		this.sets = sets.clone();
		init();
	}
	public ScenarioEditor(boolean independence, Setting sets, CalcWindow sp)
	{
		super(sp);
		this.independence = independence;
		this.sets = sets.clone();
		superComponent = sp;
		init();
	}
	private void init()
	{
		String lng = System.getProperty("user.language");
		if(lng.equalsIgnoreCase("kr") || lng.equalsIgnoreCase("kor") || lng.equalsIgnoreCase("ko") || lng.equalsIgnoreCase("korean"))
		{
			lang = 1;
		}
		else
		{
			lang = 0;
		}
		
		if(sets == null) sets = Setting.getNewInstance();
		
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(600, 450);
		this.setLocation((int) screenSize.getWidth()/2 - this.getWidth()/2,(int) screenSize.getHeight()/2 - this.getHeight()/2);
		
		this.addWindowListener(this);
		
		try
		{
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("calc_ico.png")).getImage());
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		
		if(CalcWindow.usingFont == null) CalcWindow.prepareFont();
		
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setBackground(sets.getSelected_back());
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		if(CalcWindow.usingFont != null)
			tabbedPane.setFont(CalcWindow.usingFont);
		mainPanel.add(tabbedPane, BorderLayout.WEST);		
		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBackground(sets.getSelected_back());
		tabbedPane.setForeground(sets.getSelected_fore());
		tabbedPane.setBackground(sets.getSelected_back());		
		
		rightArea = new JTextArea();
		if(CalcWindow.usingFont != null)
			rightArea.setFont(CalcWindow.usingFont);
		rightArea.setLineWrap(true);
		rightArea.setEditable(false);
		rightArea.setBackground(sets.getSelected_inside_back());
		rightArea.setForeground(sets.getSelected_fore());
		rightScroll = new JScrollPane(rightArea);
		
		rightPanel = new JPanel();
		mainPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(sets.getSelected_back());
		rightArea = new JTextArea();
		if(CalcWindow.usingFont != null)
			rightArea.setFont(CalcWindow.usingFont);
		rightArea.setLineWrap(true);
		rightArea.setEditable(false);
		rightArea.setBackground(sets.getSelected_inside_back());
		rightArea.setForeground(sets.getSelected_fore());
		rightScroll = new JScrollPane(rightArea);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(rightScroll, BorderLayout.CENTER);
		
		
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		upPanel.setBackground(sets.getSelected_back());
		
		
		titlePanel = new JPanel();
		titlePanel.setBorder(new EtchedBorder());
		titleLabel = new JLabel();
		if(CalcWindow.usingFont != null)
			titleLabel.setFont(CalcWindow.usingFont);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);
		titleLabel.setForeground(sets.getSelected_fore());
		
		titlePanel.setBackground(sets.getSelected_inside_back());
		if(sets.getColor_reverse().booleanValue())
		{
			titlePanel.setBackground(sets.getSelected_back());
		}
		
		exitButton2 = new JButton("X");
		if(CalcWindow.usingFont != null)
			exitButton2.setFont(CalcWindow.usingFont);
		exitButton2.addActionListener(this);
		exitButton2.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			exitButton2.setBackground(sets.getSelected_button());
		}
		
		if(sets != null)
		{
			if(sets.isUse_own_titleBar())
			{
				this.setUndecorated(true);
				mainPanel.add(upPanel, BorderLayout.NORTH);
				upPanel.add(titlePanel, BorderLayout.CENTER);
				upPanel.add(exitButton2, BorderLayout.EAST);
				titlePanel.addMouseListener(this);
				titlePanel.addMouseMotionListener(this);
				this.setSize(600, 450);
				this.setLocation((int) screenSize.getWidth()/2 - this.getWidth()/2,(int) screenSize.getHeight()/2 - this.getHeight()/2);
			}
		}
		
		centerPanel.setLayout(new BorderLayout());
		contentArea = new JTextArea();
		if(CalcWindow.usingFont != null)
			contentArea.setFont(CalcWindow.usingFont);
		contentArea.setLineWrap(true);
		contentArea.setEditable(false);
		contentScroll = new JScrollPane(contentArea);
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		contentArea.setBackground(sets.getSelected_inside_back());
		contentArea.setForeground(sets.getSelected_fore());
		
		controlPanels = new JPanel[9];
		controlTitles = new String[controlPanels.length];
		if(lang == 1)
		{
			this.setTitle("시나리오 에디터");
			titleLabel.setText("시나리오 에디터");
			controlTitles[0] = "파일";
			controlTitles[1] = "기본";
			controlTitles[2] = "설명";
			controlTitles[3] = "비율";
			controlTitles[4] = "스크립트";
			controlTitles[5] = "임의의 경험";
			controlTitles[6] = "메시지";
			controlTitles[7] = "카드 추가";
			controlTitles[8] = "기타";
		}
		else
		{
			this.setTitle("Scenario Editor");
			titleLabel.setText("Scenario Editor");
			controlTitles[0] = "File";
			controlTitles[1] = "Basic";
			controlTitles[2] = "Description";
			controlTitles[3] = "Ratio";
			controlTitles[4] = "Script";
			controlTitles[5] = "Random Experience";
			controlTitles[6] = "Message";
			controlTitles[7] = "Add card";
			controlTitles[8] = "etc.";
		}
		for(int i=0; i<controlPanels.length; i++)
		{
			controlPanels[i] = new JPanel();
			controlPanels[i].setBackground(sets.getSelected_back());
			tabbedPane.addTab(controlTitles[i], controlPanels[i]);
		}
		
		basicPns = new JPanel[10];
		controlPanels[1].setLayout(new GridLayout(basicPns.length, 1));
		basicLabels = new JLabel[basicPns.length];
		for(int i=0; i<basicPns.length; i++)
		{
			basicPns[i] = new JPanel();			
			basicPns[i].setBackground(sets.getSelected_back());
			basicLabels[i] = new JLabel();
			if(CalcWindow.usingFont != null)
				basicLabels[i].setFont(CalcWindow.usingFont);
			basicLabels[i].setForeground(sets.getSelected_fore());
			basicPns[i].setLayout(new FlowLayout());
			basicPns[i].add(basicLabels[i]);
			controlPanels[1].add(basicPns[i]);
		}
		nameField = new JTextField(10);
		korNameField = new JTextField(10);		
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		difficultyPinn = new JSpinner(spinnerModel);
		difficultyPinn.setValue(new Integer(1));
		spinnerModel = new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		firstStar = new JSpinner(spinnerModel);
		firstStar.setValue(new Integer(0));
		spinnerModel = new SpinnerNumberModel(1000, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		secondStar = new JSpinner(spinnerModel);
		secondStar.setValue(new Integer(1000));
		spinnerModel = new SpinnerNumberModel(10000, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		thirdStar = new JSpinner(spinnerModel);
		thirdStar.setValue(new Integer(10000));
		if(CalcWindow.usingFont != null)
		{
			nameField.setFont(CalcWindow.usingFont);
			korNameField.setFont(CalcWindow.usingFont);
			difficultyPinn.setFont(CalcWindow.usingFont);
			firstStar.setFont(CalcWindow.usingFont);
			secondStar.setFont(CalcWindow.usingFont);
			thirdStar.setFont(CalcWindow.usingFont);
		}
		nameField.addMouseListener(this);
		korNameField.addMouseListener(this);
		difficultyPinn.addMouseListener(this);
		firstStar.addMouseListener(this);
		secondStar.addMouseListener(this);
		thirdStar.addMouseListener(this);
		nameField.setBackground(sets.getSelected_inside_back());
		nameField.setForeground(sets.getSelected_fore());
		korNameField.setBackground(sets.getSelected_inside_back());
		korNameField.setForeground(sets.getSelected_fore());
		difficultyPinn.setBackground(sets.getSelected_inside_back());
		difficultyPinn.setForeground(sets.getSelected_fore());
		firstStar.setBackground(sets.getSelected_inside_back());
		firstStar.setForeground(sets.getSelected_fore());
		secondStar.setBackground(sets.getSelected_inside_back());
		secondStar.setForeground(sets.getSelected_fore());
		thirdStar.setBackground(sets.getSelected_inside_back());
		thirdStar.setForeground(sets.getSelected_fore());
		basicPns[0].add(nameField);
		basicPns[1].add(korNameField);
		basicPns[2].add(difficultyPinn);
		basicPns[6].add(firstStar);
		basicPns[7].add(secondStar);
		basicPns[8].add(thirdStar);
		
		if(lang == 1)
		{
			basicLabels[0].setText("시나리오 제목");
			basicLabels[1].setText("한글 제목");
			basicLabels[2].setText("난이도");
			basicLabels[5].setText("별 획득 조건");
			basicLabels[6].setText("★");
			basicLabels[7].setText("★★");
			basicLabels[8].setText("★★★");
		}
		else
		{
			basicLabels[0].setText("Scenario Title");
			basicLabels[1].setText("Korean Title");
			basicLabels[2].setText("Difficulty");
			basicLabels[5].setText("Earning-Star Condition");
			basicLabels[6].setText("★");
			basicLabels[7].setText("★★");
			basicLabels[8].setText("★★★");
		}
		
		descPns = new JPanel[2];
		controlPanels[2].setLayout(new GridLayout(descPns.length, 1));
		descContentPns = new JPanel[descPns.length];
		descLabelPns = new JPanel[descPns.length];
		descLabels = new JLabel[descPns.length];
		for(int i=0; i<descPns.length; i++)
		{
			descPns[i] = new JPanel();
			descPns[i].setBackground(sets.getSelected_back());
			descPns[i].setLayout(new BorderLayout());
			controlPanels[2].add(descPns[i]);
			descContentPns[i] = new JPanel();
			descContentPns[i].setBackground(sets.getSelected_back());
			descLabelPns[i] = new JPanel();
			descLabelPns[i].setBackground(sets.getSelected_back());
			descLabels[i] = new JLabel();
			if(CalcWindow.usingFont != null)
				descLabels[i].setFont(CalcWindow.usingFont);
			descLabels[i].setForeground(sets.getSelected_fore());
			descPns[i].add(descLabelPns[i], BorderLayout.NORTH);
			descLabelPns[i].setLayout(new FlowLayout());
			descLabelPns[i].add(descLabels[i]);
			descPns[i].add(descContentPns[i], BorderLayout.CENTER);
			descContentPns[i].setLayout(new BorderLayout());
		}
		descriptionArea = new JTextArea();
		if(CalcWindow.usingFont != null)
			descriptionArea.setFont(CalcWindow.usingFont);
		descriptionArea.setLineWrap(true);
		descriptionArea.setBackground(sets.getSelected_inside_back());
		descriptionArea.setForeground(sets.getSelected_fore());
		descriptionScroll = new JScrollPane(descriptionArea);
		descriptionArea.addMouseListener(this);
		korDescriptionArea = new JTextArea();
		if(CalcWindow.usingFont != null)
			korDescriptionArea.setFont(CalcWindow.usingFont);
		korDescriptionArea.setLineWrap(true);		
		korDescriptionArea.setBackground(sets.getSelected_inside_back());
		korDescriptionArea.setForeground(sets.getSelected_fore());
		korDescriptionArea.addMouseListener(this);
		korDescriptionScroll = new JScrollPane(korDescriptionArea);
		descContentPns[0].add(descriptionScroll);
		descContentPns[1].add(korDescriptionScroll);
		
		if(lang == 1)
		{
			descLabels[0].setText("시나리오 설명");
			descLabels[1].setText("한글 설명");
		}
		else
		{
			descLabels[0].setText("Scenario Description");
			descLabels[1].setText("Korean Description");
		}
		
		ratioPns = new JPanel[10];
		controlPanels[3].setLayout(new GridLayout(ratioPns.length, 1));
		ratioLabels = new JLabel[ratioPns.length];
		for(int i=0; i<ratioPns.length; i++)
		{
			ratioPns[i] = new JPanel();
			ratioPns[i].setBackground(sets.getSelected_back());
			ratioPns[i].setLayout(new FlowLayout());
			ratioLabels[i] = new JLabel();
			if(CalcWindow.usingFont != null)
				ratioLabels[i].setFont(CalcWindow.usingFont);
			ratioLabels[i].setForeground(sets.getSelected_fore());
			ratioPns[i].add(ratioLabels[i]);
			controlPanels[3].add(ratioPns[i]);
		}
		spinnerModel = new SpinnerNumberModel(5, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		bonusPinner = new JSpinner(spinnerModel);
		bonusPinner.setValue(new Integer(5));
		bonusPinner.setBackground(sets.getSelected_inside_back());
		bonusPinner.setForeground(sets.getSelected_fore());
		bonusPinner.addMouseListener(this);
		ratioPns[0].add(bonusPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		plusminusRatioPinner = new JSpinner(spinnerModel);
		plusminusRatioPinner.addMouseListener(this);
		plusminusRatioPinner.setValue(new Integer(4));
		plusminusRatioPinner.setBackground(sets.getSelected_inside_back());
		plusminusRatioPinner.setForeground(sets.getSelected_fore());
		ratioPns[1].add(plusminusRatioPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		multiplyRatioPinner = new JSpinner(spinnerModel);
		multiplyRatioPinner.addMouseListener(this);
		multiplyRatioPinner.setValue(new Integer(4));
		multiplyRatioPinner.setBackground(sets.getSelected_inside_back());
		multiplyRatioPinner.setForeground(sets.getSelected_fore());
		ratioPns[2].add(multiplyRatioPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		changeCardPinner = new JSpinner(spinnerModel);
		changeCardPinner.addMouseListener(this);
		changeCardPinner.setValue(new Integer(2));
		changeCardPinner.setBackground(sets.getSelected_inside_back());
		changeCardPinner.setForeground(sets.getSelected_fore());
		ratioPns[3].add(changeCardPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		playerCardCountPinner = new JSpinner(spinnerModel);
		playerCardCountPinner.addMouseListener(this);
		playerCardCountPinner.setValue(new Integer(10));
		playerCardCountPinner.setBackground(sets.getSelected_inside_back());
		playerCardCountPinner.setForeground(sets.getSelected_fore());
		ratioPns[4].add(playerCardCountPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		aiCardCountPinner = new JSpinner(spinnerModel);
		aiCardCountPinner.addMouseListener(this);
		aiCardCountPinner.setValue(new Integer(10));
		aiCardCountPinner.setBackground(sets.getSelected_inside_back());
		aiCardCountPinner.setForeground(sets.getSelected_fore());
		ratioPns[5].add(aiCardCountPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		timeoutPinner = new JSpinner(spinnerModel);
		timeoutPinner.addMouseListener(this);
		timeoutPinner.setValue(new Integer(100));
		timeoutPinner.setBackground(sets.getSelected_inside_back());
		timeoutPinner.setForeground(sets.getSelected_fore());
		ratioPns[6].add(timeoutPinner);
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		limitTurnPinner = new JSpinner(spinnerModel);
		limitTurnPinner.addMouseListener(this);
		limitTurnPinner.setValue(new Integer(0));
		limitTurnPinner.setBackground(sets.getSelected_inside_back());
		limitTurnPinner.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			bonusPinner.setFont(CalcWindow.usingFont);
			plusminusRatioPinner.setFont(CalcWindow.usingFont);
			multiplyRatioPinner.setFont(CalcWindow.usingFont);
			changeCardPinner.setFont(CalcWindow.usingFont);
			playerCardCountPinner.setFont(CalcWindow.usingFont);
			aiCardCountPinner.setFont(CalcWindow.usingFont);
			timeoutPinner.setFont(CalcWindow.usingFont);
			limitTurnPinner.setFont(CalcWindow.usingFont);
			
		}
		ratioPns[7].add(limitTurnPinner);
		
		if(lang == 1)
		{
			ratioLabels[0].setText("보너스");
			ratioLabels[1].setText("+, - 비율");
			ratioLabels[2].setText("× 비율");
			ratioLabels[3].setText("§카드 수");
			ratioLabels[4].setText("시작 시 플레이어 카드 수");
			ratioLabels[5].setText("시작 시 AI 카드 수");
			ratioLabels[6].setText("매 차례 당 시간 제한");
			ratioLabels[7].setText("최대 차례 수 (0 : 무한)");
		}
		else
		{
			ratioLabels[0].setText("Bonus");
			ratioLabels[1].setText("+, - Ratio");
			ratioLabels[2].setText("× Ratio");
			ratioLabels[3].setText("§Cards");
			ratioLabels[4].setText("Player Cards at start");
			ratioLabels[5].setText("AI Cards at start");
			ratioLabels[6].setText("Time Limit on each turns");
			ratioLabels[7].setText("Max turns (0 : infinite)");
		}
		
		controlPanels[4].setLayout(new BorderLayout());
		scriptLabelPanel = new JPanel();
		scriptLabelPanel.setBackground(sets.getSelected_back());
		scriptLabelPanel.setLayout(new FlowLayout());
		controlPanels[4].add(scriptLabelPanel, BorderLayout.NORTH);
		scriptLabel = new JLabel();
		scriptLabel.setForeground(sets.getSelected_fore());
		scriptCheck = new JCheckBox();
		scriptCheck.addItemListener(this);
		scriptCheck.setBackground(sets.getSelected_back());
		scriptCheck.setForeground(sets.getSelected_fore());
		scriptLabelPanel.add(scriptLabel);
		scriptLabelPanel.add(scriptCheck);
		scriptArea = new JTextArea();
		scriptArea.setLineWrap(true);
		scriptArea.setEditable(false);
		scriptArea.setBackground(sets.getSelected_back());
		scriptArea.setForeground(sets.getSelected_fore());
		scriptArea.addMouseListener(this);
		scriptScroll = new JScrollPane(scriptArea);
		controlPanels[4].add(scriptScroll, BorderLayout.CENTER);
		if(CalcWindow.usingFont != null)
		{
			scriptLabel.setFont(CalcWindow.usingFont);
			scriptCheck.setFont(CalcWindow.usingFont);
			scriptArea.setFont(CalcWindow.usingFont);
		}
		if(lang == 1)
		{
			scriptLabel.setText("스크립트");
			scriptCheck.setText("사용");
		}
		else
		{
			scriptLabel.setText("Script");
			scriptCheck.setText("Use");
		}
		
		filePns = new JPanel[5];
		controlPanels[0].setLayout(new GridLayout(filePns.length, 1));
		for(int i=0; i<filePns.length; i++)
		{
			filePns[i] = new JPanel();
			filePns[i].setBackground(sets.getSelected_back());
			filePns[i].setLayout(new FlowLayout());
			controlPanels[0].add(filePns[i]);
		}
		newButton = new JButton();
		newButton.setForeground(sets.getSelected_fore());
		newButton.addActionListener(this);		
		filePns[0].add(newButton);
		if(sets.getSelected_button() != null)
		{
			newButton.setBackground(sets.getSelected_button());
		}
		
		saveButton = new JButton();
		saveButton.setForeground(sets.getSelected_fore());
		saveButton.addActionListener(this);
		saveasButton = new JButton();
		saveasButton.setForeground(sets.getSelected_fore());
		saveasButton.addActionListener(this);
		if(sets.getSelected_button() != null)
		{
			saveButton.setBackground(sets.getSelected_button());
			saveasButton.setBackground(sets.getSelected_button());
		}
		savePns = new JPanel[2];
		filePns[1].setLayout(new GridLayout(savePns.length, 1));
		for(int i=0; i<savePns.length; i++)
		{
			savePns[i] = new JPanel();
			savePns[i].setBackground(sets.getSelected_back());
			savePns[i].setLayout(new FlowLayout());
			filePns[1].add(savePns[i]);
		}
		savePns[0].add(saveButton);
		savePns[1].add(saveasButton);
		
		loadButton = new JButton();
		loadButton.setForeground(sets.getSelected_fore());
		loadButton.addActionListener(this);
		filePns[2].add(loadButton);
		exitButton = new JButton();
		exitButton.setForeground(sets.getSelected_fore());
		exitButton.addActionListener(this);
		filePns[3].add(exitButton);
		
		filePns[4].setLayout(new FlowLayout());
		authorizeButton = new JButton();
		authorizeButton.setForeground(sets.getSelected_fore());
		authorizeButton.addActionListener(this);
		filePns[4].add(authorizeButton);
		if(sets.getSelected_button() != null)
		{
			loadButton.setBackground(sets.getSelected_button());
			exitButton.setBackground(sets.getSelected_button());
			authorizeButton.setBackground(sets.getSelected_button());
		}
		if(CalcWindow.usingFont != null)
		{
			newButton.setFont(CalcWindow.usingFont);
			saveButton.setFont(CalcWindow.usingFont);
			saveasButton.setFont(CalcWindow.usingFont);
			loadButton.setFont(CalcWindow.usingFont);
			exitButton.setFont(CalcWindow.usingFont);
			authorizeButton.setFont(CalcWindow.usingFont);
			
		}
			
		
		if(lang == 1)
		{
			newButton.setText("새 시나리오");
			saveButton.setText("저장");
			saveasButton.setText("다른 이름으로 저장");
			loadButton.setText("불러오기");
			exitButton.setText("종료");
			authorizeButton.setText("인증");
		}
		else
		{
			newButton.setText("New");
			saveButton.setText("Save");
			saveasButton.setText("Save as...");
			loadButton.setText("Load");
			exitButton.setText("Exit");
			authorizeButton.setText("Authorize");
		}
		authorizeButton.setVisible(false);
		try
		{
			if(CalcWindow.grade_mode >= 3)
			{
				authorizeButton.setVisible(true);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String basicPath = System.getProperty("user.home");
		if(sets != null)
		{
			basicPath = sets.getDefault_path();
		}
		fchooser = new JFileChooser(basicPath);
		filter = new ScenarioFileFilter();
		fchooser.setFileFilter(filter);
		
		randomPns = new JPanel[8];
		controlPanels[5].setLayout(new GridLayout(randomPns.length, 1));
		randomLbs = new JLabel[randomPns.length];
		for(int i=0; i<randomPns.length; i++)
		{
			randomPns[i] = new JPanel();
			randomPns[i].setLayout(new FlowLayout());
			randomPns[i].setBackground(sets.getSelected_back());
			randomLbs[i] = new JLabel();
			if(CalcWindow.usingFont != null)
				randomLbs[i].setFont(CalcWindow.usingFont);
			randomLbs[i].setForeground(sets.getSelected_fore());
			randomPns[i].add(randomLbs[i]);
			controlPanels[5].add(randomPns[i]);
		}
		
		randomPlusCheck = new JCheckBox();
		randomPlusCheck.setForeground(sets.getSelected_fore());
		randomPlusCheck.setBackground(sets.getSelected_back());
		randomPlusCheck.addItemListener(this);
		randomPns[0].add(randomPlusCheck);
		
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomPlusMinPinner = new JSpinner(spinnerModel);
		randomPlusMinPinner.setForeground(sets.getSelected_fore());
		randomPlusMinPinner.setBackground(sets.getSelected_back());
		randomPlusMinPinner.setValue(new Integer(2));
		randomPlusMinPinner.addMouseListener(this);
		randomPlusMidLabel = new JLabel("~");
		randomPlusMidLabel.setForeground(sets.getSelected_fore());
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomPlusMaxPinner = new JSpinner(spinnerModel);
		randomPlusMaxPinner.setForeground(sets.getSelected_fore());
		randomPlusMaxPinner.setBackground(sets.getSelected_back());
		randomPlusMaxPinner.setValue(new Integer(4));
		randomPlusMaxPinner.addMouseListener(this);
		randomPns[1].add(randomPlusMinPinner);
		randomPns[1].add(randomPlusMidLabel);
		randomPns[1].add(randomPlusMaxPinner);
		randomPlusMaxPinner.setEnabled(false);
		randomPlusMinPinner.setEnabled(false);
		
		randomMultiplyCheck = new JCheckBox();
		randomMultiplyCheck.setForeground(sets.getSelected_fore());
		randomMultiplyCheck.setBackground(sets.getSelected_back());
		randomMultiplyCheck.addItemListener(this);
		randomPns[2].add(randomMultiplyCheck);
		
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomMultiplyMinPinner = new JSpinner(spinnerModel);
		randomMultiplyMinPinner.setForeground(sets.getSelected_fore());
		randomMultiplyMinPinner.setBackground(sets.getSelected_back());
		randomMultiplyMinPinner.setValue(new Integer(2));
		randomMultiplyMinPinner.addMouseListener(this);
		randomMultiplyMidLabel = new JLabel("~");
		randomMultiplyMidLabel.setForeground(sets.getSelected_fore());
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomMultiplyMaxPinner = new JSpinner(spinnerModel);
		randomMultiplyMaxPinner.setForeground(sets.getSelected_fore());
		randomMultiplyMaxPinner.setBackground(sets.getSelected_back());
		randomMultiplyMaxPinner.setValue(new Integer(4));
		randomMultiplyMaxPinner.addMouseListener(this);
		randomPns[3].add(randomMultiplyMinPinner);
		randomPns[3].add(randomMultiplyMidLabel);
		randomPns[3].add(randomMultiplyMaxPinner);
		randomMultiplyMaxPinner.setEnabled(false);
		randomMultiplyMinPinner.setEnabled(false);
		
		randomPlayerCheck = new JCheckBox();
		randomPlayerCheck.setForeground(sets.getSelected_fore());
		randomPlayerCheck.setBackground(sets.getSelected_back());
		randomPlayerCheck.addItemListener(this);
		randomPns[4].add(randomPlayerCheck);
		
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomPlayerMinPinner = new JSpinner(spinnerModel);
		randomPlayerMinPinner.setForeground(sets.getSelected_fore());
		randomPlayerMinPinner.setBackground(sets.getSelected_back());
		randomPlayerMinPinner.setValue(new Integer(2));
		randomPlayerMinPinner.addMouseListener(this);
		randomPlayerMidLabel = new JLabel("~");
		randomPlayerMidLabel.setForeground(sets.getSelected_fore());
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomPlayerMaxPinner = new JSpinner(spinnerModel);
		randomPlayerMaxPinner.setForeground(sets.getSelected_fore());
		randomPlayerMaxPinner.setBackground(sets.getSelected_back());
		randomPlayerMaxPinner.setValue(new Integer(4));
		randomPlayerMaxPinner.addMouseListener(this);
		randomPns[5].add(randomPlayerMinPinner);
		randomPns[5].add(randomPlayerMidLabel);
		randomPns[5].add(randomPlayerMaxPinner);
		randomPlayerMaxPinner.setEnabled(false);
		randomPlayerMinPinner.setEnabled(false);
		
		randomDeckCheck = new JCheckBox();
		randomDeckCheck.setForeground(sets.getSelected_fore());
		randomDeckCheck.setBackground(sets.getSelected_back());
		randomDeckCheck.addItemListener(this);
		randomPns[6].add(randomDeckCheck);
		
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomDeckMinPinner = new JSpinner(spinnerModel);
		randomDeckMinPinner.setForeground(sets.getSelected_fore());
		randomDeckMinPinner.setBackground(sets.getSelected_back());
		randomDeckMinPinner.setValue(new Integer(2));
		randomDeckMinPinner.addMouseListener(this);
		randomDeckMidLabel = new JLabel("~");
		randomDeckMidLabel.setForeground(sets.getSelected_fore());
		spinnerModel = new SpinnerNumberModel(1, 0, 100000, 1);
		randomDeckMaxPinner = new JSpinner(spinnerModel);
		randomDeckMaxPinner.setForeground(sets.getSelected_fore());
		randomDeckMaxPinner.setBackground(sets.getSelected_back());
		randomDeckMaxPinner.setValue(new Integer(4));
		randomDeckMaxPinner.addMouseListener(this);
		randomPns[7].add(randomDeckMinPinner);
		randomPns[7].add(randomDeckMidLabel);
		randomPns[7].add(randomDeckMaxPinner);
		randomDeckMaxPinner.setEnabled(false);
		randomDeckMinPinner.setEnabled(false);
		if(CalcWindow.usingFont != null)
		{
			randomPlusCheck.setFont(CalcWindow.usingFont);
			randomPlusMinPinner.setFont(CalcWindow.usingFont);
			randomPlusMidLabel.setFont(CalcWindow.usingFont);
			randomPlusMaxPinner.setFont(CalcWindow.usingFont);
			randomMultiplyCheck.setFont(CalcWindow.usingFont);
			randomMultiplyMinPinner.setFont(CalcWindow.usingFont);
			randomMultiplyMidLabel.setFont(CalcWindow.usingFont);
			randomMultiplyMaxPinner.setFont(CalcWindow.usingFont);
			randomPlayerCheck.setFont(CalcWindow.usingFont);
			randomPlayerMinPinner.setFont(CalcWindow.usingFont);
			randomPlayerMidLabel.setFont(CalcWindow.usingFont);
			randomPlayerMaxPinner.setFont(CalcWindow.usingFont);
			randomDeckCheck.setFont(CalcWindow.usingFont);
			randomDeckMinPinner.setFont(CalcWindow.usingFont);
			randomDeckMidLabel.setFont(CalcWindow.usingFont);
			randomDeckMaxPinner.setFont(CalcWindow.usingFont);
			
		}
		
		if(lang == 1)
		{
			randomLbs[0].setText("+- 카드 비율 임의 설정");
			randomLbs[1].setText("최소, 최대");
			randomPlusCheck.setText("사용");
			
			randomLbs[2].setText("×카드 비율 임의 설정");
			randomLbs[3].setText("최소, 최대");
			randomMultiplyCheck.setText("사용");
			
			randomLbs[4].setText("플레이어가 받을 카드 수 임의 설정");
			randomLbs[5].setText("최소, 최대");
			randomPlayerCheck.setText("사용");
			
			randomLbs[6].setText("덱 카드 비율 임의 설정");
			randomLbs[7].setText("최소, 최대");
			randomDeckCheck.setText("사용");
			
		}
		else
		{
			randomLbs[0].setText("+- card ratio randomize");
			randomLbs[1].setText("Min, Max");
			randomPlusCheck.setText("Use");
			
			randomLbs[2].setText("×card ratio randomize");
			randomLbs[3].setText("Min, Max");
			randomMultiplyCheck.setText("Use");
			
			randomLbs[4].setText("Randomize player cards");
			randomLbs[5].setText("Min, Max");
			randomPlayerCheck.setText("Use");
			
			randomLbs[6].setText("Randomize deck cards");
			randomLbs[7].setText("Min, Max");
			randomDeckCheck.setText("Use");
		}
		
		messagePns = new JPanel[4];
		controlPanels[6].setLayout(new GridLayout(messagePns.length, 1));
		for(int i=0; i<messagePns.length; i++)
		{
			messagePns[i] = new JPanel();
			messagePns[i].setLayout(new BorderLayout());
			messagePns[i].setBackground(sets.getSelected_back());
			controlPanels[6].add(messagePns[i]);
		}
		endMessagePns = new JPanel[2];
		for(int i=0; i<2; i++)
		{
			endMessagePns[i] = new JPanel();
			endMessagePns[i].setBackground(sets.getSelected_back());
						
		}
		messagePns[0].add(endMessagePns[0], BorderLayout.NORTH);
		messagePns[0].add(endMessagePns[1], BorderLayout.CENTER);
		endMessagePns[0].setLayout(new FlowLayout());
		endMessagePns[1].setLayout(new BorderLayout());
		endMessageCheck = new JCheckBox();
		endMessageCheck.setBackground(sets.getSelected_back());
		endMessageCheck.setForeground(sets.getSelected_fore());
		endMessageCheck.addItemListener(this);
		endMessageLabel = new JLabel();
		endMessageLabel.setForeground(sets.getSelected_fore());
		endMessagePns[0].add(endMessageLabel);
		endMessagePns[0].add(endMessageCheck);
		endMessageArea = new JTextArea();
		endMessageArea.setLineWrap(true);
		endMessageArea.setEditable(false);
		endMessageArea.addMouseListener(this);
		endMessageArea.setBackground(sets.getSelected_inside_back());
		endMessageArea.setForeground(sets.getSelected_fore());
		endMessageScroll = new JScrollPane(endMessageArea);
		endMessagePns[1].add(endMessageScroll);
		
		korEndMessagePns = new JPanel[2];
		for(int i=0; i<2; i++)
		{
			korEndMessagePns[i] = new JPanel();
			korEndMessagePns[i].setBackground(sets.getSelected_back());
		}
		messagePns[1].add(korEndMessagePns[0], BorderLayout.NORTH);
		messagePns[1].add(korEndMessagePns[1], BorderLayout.CENTER);
		korEndMessagePns[0].setLayout(new FlowLayout());
		korEndMessagePns[1].setLayout(new BorderLayout());
		korEndMessageCheck = new JCheckBox();
		korEndMessageCheck.setBackground(sets.getSelected_back());
		korEndMessageCheck.setForeground(sets.getSelected_fore());
		korEndMessageCheck.addItemListener(this);
		korEndMessageLabel = new JLabel();
		korEndMessageLabel.setForeground(sets.getSelected_fore());
		korEndMessagePns[0].add(korEndMessageLabel);
		korEndMessagePns[0].add(korEndMessageCheck);
		korEndMessageArea = new JTextArea();
		korEndMessageArea.setLineWrap(true);
		korEndMessageArea.setEditable(false);
		korEndMessageArea.addMouseListener(this);
		korEndMessageArea.setBackground(sets.getSelected_inside_back());
		korEndMessageArea.setForeground(sets.getSelected_fore());
		korEndMessageScroll = new JScrollPane(korEndMessageArea);
		korEndMessagePns[1].add(korEndMessageScroll);
		
		failMessagePns = new JPanel[2];
		for(int i=0; i<2; i++)
		{
			failMessagePns[i] = new JPanel();
			failMessagePns[i].setBackground(sets.getSelected_back());
		}
		messagePns[2].add(failMessagePns[0], BorderLayout.NORTH);
		messagePns[2].add(failMessagePns[1], BorderLayout.CENTER);
		failMessagePns[0].setLayout(new FlowLayout());
		failMessagePns[1].setLayout(new BorderLayout());
		failMessageCheck = new JCheckBox();
		failMessageCheck.setBackground(sets.getSelected_back());
		failMessageCheck.setForeground(sets.getSelected_fore());
		failMessageCheck.addItemListener(this);
		failMessageLabel = new JLabel();
		failMessageLabel.setForeground(sets.getSelected_fore());
		failMessagePns[0].add(failMessageLabel);
		failMessagePns[0].add(failMessageCheck);
		failMessageArea = new JTextArea();
		failMessageArea.setLineWrap(true);
		failMessageArea.setEditable(false);
		failMessageArea.addMouseListener(this);
		failMessageArea.setBackground(sets.getSelected_inside_back());
		failMessageArea.setForeground(sets.getSelected_fore());
		failMessageScroll = new JScrollPane(failMessageArea);
		failMessagePns[1].add(failMessageScroll);
		
		korFailMessagePns = new JPanel[2];
		for(int i=0; i<2; i++)
		{
			korFailMessagePns[i] = new JPanel();
			korFailMessagePns[i].setBackground(sets.getSelected_back());		
		}
		messagePns[3].add(korFailMessagePns[0], BorderLayout.NORTH);
		messagePns[3].add(korFailMessagePns[1], BorderLayout.CENTER);
		korFailMessagePns[0].setLayout(new FlowLayout());
		korFailMessagePns[1].setLayout(new BorderLayout());
		korFailMessageCheck = new JCheckBox();
		korFailMessageCheck.setBackground(sets.getSelected_back());
		korFailMessageCheck.setForeground(sets.getSelected_fore());
		korFailMessageCheck.addItemListener(this);
		korFailMessageLabel = new JLabel();
		korFailMessageLabel.setForeground(sets.getSelected_fore());
		korFailMessagePns[0].add(korFailMessageLabel);
		korFailMessagePns[0].add(korFailMessageCheck);
		korFailMessageArea = new JTextArea();
		korFailMessageArea.setLineWrap(true);
		korFailMessageArea.setEditable(false);
		korFailMessageArea.addMouseListener(this);
		korFailMessageArea.setBackground(sets.getSelected_inside_back());
		korFailMessageArea.setForeground(sets.getSelected_fore());
		korFailMessageScroll = new JScrollPane(korFailMessageArea);
		korFailMessagePns[1].add(korFailMessageScroll);
		
		if(CalcWindow.usingFont != null)
		{
			endMessageCheck.setFont(CalcWindow.usingFont);
			endMessageLabel.setFont(CalcWindow.usingFont);
			endMessageArea.setFont(CalcWindow.usingFont);
			
			korEndMessageCheck.setFont(CalcWindow.usingFont);
			korEndMessageLabel.setFont(CalcWindow.usingFont);
			korEndMessageArea.setFont(CalcWindow.usingFont);
			
			failMessageCheck.setFont(CalcWindow.usingFont);
			failMessageLabel.setFont(CalcWindow.usingFont);
			failMessageArea.setFont(CalcWindow.usingFont);
			
			korFailMessageCheck.setFont(CalcWindow.usingFont);
			korFailMessageLabel.setFont(CalcWindow.usingFont);
			korFailMessageArea.setFont(CalcWindow.usingFont);
		}
		
		if(lang == 1)
		{
			endMessageLabel.setText("승리 시 메시지 보이기");
			endMessageCheck.setText("사용");
			korEndMessageLabel.setText("승리 시 메시지 보이기 (한글)");
			korEndMessageCheck.setText("사용");
			failMessageLabel.setText("패배 시 메시지 보이기");
			failMessageCheck.setText("사용");
			korFailMessageLabel.setText("패배 시 메시지 보이기 (한글)");
			korFailMessageCheck.setText("사용");
		}
		else
		{
			endMessageLabel.setText("Message on victory");
			endMessageCheck.setText("Use");
			korEndMessageLabel.setText("Message on victory (Korean)");
			korEndMessageCheck.setText("Use");
			failMessageLabel.setText("Message on failure");
			failMessageCheck.setText("Use");
			korFailMessageLabel.setText("Message on failure (Korean)");
			korFailMessageCheck.setText("Use");
		}
		
		addCardPns = new JPanel[3];
		controlPanels[7].setLayout(new GridLayout(addCardPns.length, 1));
		for(int i=0; i<addCardPns.length; i++)
		{
			addCardPns[i] = new JPanel();
			addCardPns[i].setLayout(new BorderLayout());
			addCardPns[i].setBackground(sets.getSelected_back());
			addCardPns[i].setBorder(new EtchedBorder());
			controlPanels[7].add(addCardPns[i]);
		}
		
		String[] operationList = new String[4];
		operationList[0] = "+";
		operationList[1] = "-";
		operationList[2] = "×";
		operationList[3] = "§";
		
		String[] howManyCardAdd = new String[3];
		if(lang == 1)
		{
			howManyCardAdd[0] = "1개";
			howManyCardAdd[1] = "10개";
			howManyCardAdd[2] = "100개";
		}
		else
		{
			howManyCardAdd[0] = "1s";
			howManyCardAdd[1] = "10s";
			howManyCardAdd[2] = "100s";
		}
		
		addPlayerCardContents = new Vector<StandardCards>();
		addPlayerCardLabelPanel = new JPanel();
		addCardPns[0].add(addPlayerCardLabelPanel, BorderLayout.NORTH);
		addPlayerCardLabelPanel.setLayout(new FlowLayout());
		addPlayerCardLabel = new JLabel();
		addPlayerCardLabelPanel.add(addPlayerCardLabel);
		addPlayerCardLabel.setForeground(sets.getSelected_fore());
		addPlayerCardLabelPanel.setBackground(sets.getSelected_back());
		addPlayerCardList = new JList();
		addPlayerCardList.addMouseListener(this);
		addPlayerCardList.addListSelectionListener(this);
		addPlayerCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addPlayerCardList.setForeground(sets.getSelected_fore());
		addPlayerCardList.setBackground(sets.getSelected_inside_back());
		addPlayerCardScroll = new JScrollPane(addPlayerCardList);
		addCardPns[0].add(addPlayerCardScroll, BorderLayout.CENTER);
		addPlayerCardControlPanel = new JPanel();
		addPlayerCardControlPanel.setLayout(new FlowLayout());
		addPlayerCardControlPanel.setBackground(sets.getSelected_back());
		addCardPns[0].add(addPlayerCardControlPanel, BorderLayout.SOUTH);
		addPlayerCardOperation = new JComboBox(operationList);
		spinnerModel = new SpinnerNumberModel(1, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		addPlayerCardNumber = new JSpinner(spinnerModel);
		addPlayerCardNumber.setValue(new Integer(1));
		addPlayerCardPlus = new JButton("+");
		addPlayerCardPlus.addActionListener(this);
		addPlayerCardHowMany = new JComboBox(howManyCardAdd);
		addPlayerCardHowMany.setBackground(sets.getSelected_inside_back());
		addPlayerCardHowMany.setForeground(sets.getSelected_fore());
		addPlayerCardOperation.setBackground(sets.getSelected_inside_back());
		addPlayerCardOperation.setForeground(sets.getSelected_fore());
		addPlayerCardNumber.setBackground(sets.getSelected_inside_back());
		addPlayerCardNumber.setForeground(sets.getSelected_fore());
		addPlayerCardPlus.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			addPlayerCardPlus.setBackground(sets.getSelected_button());
		}		
		addPlayerCardControlPanel.add(addPlayerCardOperation);
		addPlayerCardControlPanel.add(addPlayerCardNumber);
		addPlayerCardControlPanel.add(addPlayerCardHowMany);
		addPlayerCardControlPanel.add(addPlayerCardPlus);	
		
		if(CalcWindow.usingFont != null)
		{
			addPlayerCardLabel.setFont(CalcWindow.usingFont);
			addPlayerCardList.setFont(CalcWindow.usingFont);
			addPlayerCardNumber.setFont(CalcWindow.usingFont);
			addPlayerCardPlus.setFont(CalcWindow.usingFont);
			addPlayerCardHowMany.setFont(CalcWindow.usingFont);			
		}
		
		addAICardContents = new Vector<StandardCards>();
		addAICardLabelPanel = new JPanel();
		addCardPns[1].add(addAICardLabelPanel, BorderLayout.NORTH);
		addAICardLabelPanel.setLayout(new FlowLayout());
		addAICardLabel = new JLabel();
		addAICardLabelPanel.add(addAICardLabel);
		addAICardLabel.setForeground(sets.getSelected_fore());
		addAICardLabelPanel.setBackground(sets.getSelected_back());
		addAICardList = new JList();
		addAICardList.addMouseListener(this);
		addAICardList.addListSelectionListener(this);
		addAICardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addAICardList.setForeground(sets.getSelected_fore());
		addAICardList.setBackground(sets.getSelected_inside_back());
		addAICardScroll = new JScrollPane(addAICardList);
		addCardPns[1].add(addAICardScroll, BorderLayout.CENTER);
		addAICardControlPanel = new JPanel();
		addAICardControlPanel.setLayout(new FlowLayout());
		addAICardControlPanel.setBackground(sets.getSelected_back());
		addCardPns[1].add(addAICardControlPanel, BorderLayout.SOUTH);
		addAICardOperation = new JComboBox(operationList);
		spinnerModel = new SpinnerNumberModel(1, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		addAICardNumber = new JSpinner(spinnerModel);
		addAICardNumber.setValue(new Integer(1));
		addAICardPlus = new JButton("+");
		addAICardPlus.addActionListener(this);
		addAICardHowMany = new JComboBox(howManyCardAdd);
		addAICardHowMany.setBackground(sets.getSelected_inside_back());
		addAICardHowMany.setForeground(sets.getSelected_fore());
		addAICardOperation.setBackground(sets.getSelected_inside_back());
		addAICardOperation.setForeground(sets.getSelected_fore());
		addAICardNumber.setBackground(sets.getSelected_inside_back());
		addAICardNumber.setForeground(sets.getSelected_fore());
		addAICardPlus.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			addAICardPlus.setBackground(sets.getSelected_button());
		}
		addAICardControlPanel.add(addAICardOperation);
		addAICardControlPanel.add(addAICardNumber);
		addAICardControlPanel.add(addAICardHowMany);
		addAICardControlPanel.add(addAICardPlus);
		
		if(CalcWindow.usingFont != null)
		{
			addAICardLabel.setFont(CalcWindow.usingFont);
			addAICardList.setFont(CalcWindow.usingFont);
			addAICardNumber.setFont(CalcWindow.usingFont);
			addAICardPlus.setFont(CalcWindow.usingFont);
			addAICardHowMany.setFont(CalcWindow.usingFont);			
		}
		
		addDeckCardContents = new Vector<StandardCards>();
		addDeckCardLabelPanel = new JPanel();
		addCardPns[2].add(addDeckCardLabelPanel, BorderLayout.NORTH);
		addDeckCardLabelPanel.setLayout(new FlowLayout());
		addDeckCardLabel = new JLabel();
		addDeckCardLabelPanel.add(addDeckCardLabel);
		addDeckCardLabel.setForeground(sets.getSelected_fore());
		addDeckCardLabelPanel.setBackground(sets.getSelected_back());
		addDeckCardList = new JList();
		addDeckCardList.addMouseListener(this);
		addDeckCardList.addListSelectionListener(this);
		addDeckCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addDeckCardList.setForeground(sets.getSelected_fore());
		addDeckCardList.setBackground(sets.getSelected_inside_back());
		addDeckCardScroll = new JScrollPane(addDeckCardList);
		addCardPns[2].add(addDeckCardScroll, BorderLayout.CENTER);
		addDeckCardControlPanel = new JPanel();
		addDeckCardControlPanel.setLayout(new FlowLayout());
		addDeckCardControlPanel.setBackground(sets.getSelected_back());
		addCardPns[2].add(addDeckCardControlPanel, BorderLayout.SOUTH);
		addDeckCardOperation = new JComboBox(operationList);
		spinnerModel = new SpinnerNumberModel(1, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
		addDeckCardNumber = new JSpinner(spinnerModel);
		addDeckCardNumber.setValue(new Integer(1));
		addDeckCardPlus = new JButton("+");
		addDeckCardPlus.addActionListener(this);
		addDeckCardHowMany = new JComboBox(howManyCardAdd);
		addDeckCardHowMany.setBackground(sets.getSelected_inside_back());
		addDeckCardHowMany.setForeground(sets.getSelected_fore());
		addDeckCardOperation.setBackground(sets.getSelected_inside_back());
		addDeckCardOperation.setForeground(sets.getSelected_fore());
		addDeckCardNumber.setBackground(sets.getSelected_inside_back());
		addDeckCardNumber.setForeground(sets.getSelected_fore());
		addDeckCardPlus.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			addDeckCardPlus.setBackground(sets.getSelected_button());
		}
		addDeckCardControlPanel.add(addDeckCardOperation);
		addDeckCardControlPanel.add(addDeckCardNumber);
		addDeckCardControlPanel.add(addDeckCardHowMany);
		addDeckCardControlPanel.add(addDeckCardPlus);		
		
		if(CalcWindow.usingFont != null)
		{
			addDeckCardLabel.setFont(CalcWindow.usingFont);
			addDeckCardList.setFont(CalcWindow.usingFont);
			addDeckCardNumber.setFont(CalcWindow.usingFont);
			addDeckCardPlus.setFont(CalcWindow.usingFont);
			addDeckCardHowMany.setFont(CalcWindow.usingFont);			
		}
		if(lang == 1)
		{
			addPlayerCardLabel.setText("각 플레이어가 초기에 받는 추가 카드");
			addAICardLabel.setText("각 AI가 초기에 받는 추가 카드");
			addDeckCardLabel.setText("게임 시작 시 덱에 추가되는 카드");
		}
		else
		{
			addPlayerCardLabel.setText("Add cards for each players");
			addAICardLabel.setText("Add cards for each AIs");
			addDeckCardLabel.setText("Add cards to the deck");
		}
		
		
		etcPns = new JPanel[8];
		controlPanels[8].setLayout(new GridLayout(etcPns.length, 1));
		etcLbs = new JLabel[etcPns.length];
		for(int i=0; i<etcPns.length; i++)
		{
			etcPns[i] = new JPanel();
			etcPns[i].setBackground(sets.getSelected_back());
			etcPns[i].setLayout(new FlowLayout());
			etcLbs[i] = new JLabel();
			if(CalcWindow.usingFont != null)
				etcLbs[i].setFont(CalcWindow.usingFont);	
			etcLbs[i].setForeground(sets.getSelected_fore());
			etcPns[i].add(etcLbs[i]);
			controlPanels[8].add(etcPns[i]);
		}
		eventUse = new JCheckBox();
		if(CalcWindow.usingFont != null)
			eventUse.setFont(CalcWindow.usingFont);	
		eventUse.addItemListener(this);
		eventUse.setBackground(sets.getSelected_back());
		eventUse.setForeground(sets.getSelected_fore());
		etcPns[0].add(eventUse);
		eventWebPage = new JTextField(10);
		if(CalcWindow.usingFont != null)
			eventWebPage.setFont(CalcWindow.usingFont);	
		eventWebPage.setBackground(sets.getSelected_inside_back());
		eventWebPage.setForeground(sets.getSelected_fore());
		eventWebPage.setEditable(false);
		etcPns[1].add(eventWebPage);
				
		eventYear = new JSpinner();
		eventYear.setValue(new Integer(Date.nowDate().getYear()));
		eventYear.setBackground(sets.getSelected_inside_back());
		eventYear.setForeground(sets.getSelected_fore());
		eventYear.setEnabled(false);
		if(CalcWindow.usingFont != null)
			eventYear.setFont(CalcWindow.usingFont);	
		etcPns[2].add(eventYear);
		
		String[] months = new String[12];
		for(int i=0; i<months.length; i++)
		{
			months[i] = String.valueOf(i + 1);
		}
		eventMonth = new JComboBox(months);
		if(CalcWindow.usingFont != null)
			eventMonth.setFont(CalcWindow.usingFont);	
		eventMonth.setBackground(sets.getSelected_inside_back());
		eventMonth.setForeground(sets.getSelected_fore());
		eventMonth.setEnabled(false);
		etcPns[3].add(eventMonth);
		
		String[] days = new String[31];
		for(int i=0; i<days.length; i++)
		{
			days[i] = String.valueOf(i + 1);
		}
		eventDay = new JComboBox(days);
		if(CalcWindow.usingFont != null)
			eventDay.setFont(CalcWindow.usingFont);
		eventDay.setBackground(sets.getSelected_inside_back());
		eventDay.setForeground(sets.getSelected_fore());
		eventDay.setEnabled(false);
		etcPns[4].add(eventDay);
		
		String[] hours = new String[24];
		for(int i=0; i<hours.length; i++)
		{
			hours[i] = String.valueOf(i);
		}
		eventHour = new JComboBox(hours);
		if(CalcWindow.usingFont != null)
			eventHour.setFont(CalcWindow.usingFont);
		eventHour.setBackground(sets.getSelected_inside_back());
		eventHour.setForeground(sets.getSelected_fore());
		eventHour.setEnabled(false);
		etcPns[5].add(eventHour);
		
		String[] minute = new String[60];
		for(int i=0; i<minute.length; i++)
		{
			minute[i] = String.valueOf(i);
		}
		eventMinute = new JComboBox(minute);
		eventMinute.setBackground(sets.getSelected_inside_back());
		eventMinute.setForeground(sets.getSelected_fore());
		eventMinute.setEnabled(false);
		if(CalcWindow.usingFont != null)
			eventMinute.setFont(CalcWindow.usingFont);
		etcPns[6].add(eventMinute);
		
		String[] seconds = new String[60];
		for(int i=0; i<seconds.length; i++)
		{
			seconds[i] = String.valueOf(i);
		}
		eventSecond = new JComboBox(seconds);
		if(CalcWindow.usingFont != null)
			eventSecond.setFont(CalcWindow.usingFont);
		eventSecond.setBackground(sets.getSelected_inside_back());
		eventSecond.setForeground(sets.getSelected_fore());
		eventSecond.setEnabled(false);
		etcPns[7].add(eventSecond);
		
		if(lang == 1)
		{
			eventUse.setText("이벤트 정의");
			etcLbs[1].setText("이벤트 참여 홈페이지 주소");
			etcLbs[2].setText("이벤트 마감 시점 년도");
			etcLbs[3].setText("이벤트 마감 시점 월");
			etcLbs[4].setText("이벤트 마감 시점 날짜");
			etcLbs[5].setText("이벤트 마감 시점 시각");
			etcLbs[6].setText("이벤트 마감 시점 분");
			etcLbs[7].setText("이벤트 마감 시점 초");
		}
		else
		{
			eventUse.setText("Define event");
			etcLbs[1].setText("Event web page address");
			etcLbs[2].setText("Event Deadline - Year");
			etcLbs[3].setText("Event Deadline - Month");
			etcLbs[4].setText("Event Deadline - Day");
			etcLbs[5].setText("Event Deadline - Hour");
			etcLbs[6].setText("Event Deadline - Minute");
			etcLbs[7].setText("Event Deadline - Second");
		}
		
		tabbedPane.addChangeListener(this);
		stateChanged(null);
	}
	public void refresh(String contents)
	{
		StringTokenizer stoken = new StringTokenizer(contents, "\n");
		String[] tokens = new String[stoken.countTokens()];
		String first, second;
		String errors = "";
		int stars = 0;
		descriptionArea.setText("");
		korDescriptionArea.setText("");
		scriptArea.setText("");
		for(int i=0; i<tokens.length; i++)
		{
			tokens[i] = stoken.nextToken();
		}
		nameField.setText("");
		korNameField.setText("");
		descriptionArea.setText("");
		korDescriptionArea.setText("");
		scriptArea.setText("");
		endMessageArea.setText("");
		korEndMessageArea.setText("");
		failMessageArea.setText("");
		korFailMessageArea.setText("");
		addPlayerCardContents.clear();
		addAICardContents.clear();
		addDeckCardContents.clear();
		scriptCheck.setSelected(false);
		for(int i=1; i<tokens.length; i++)
		{
			try
			{
				stoken = new StringTokenizer(tokens[i], "|");
				first = stoken.nextToken();
				second = stoken.nextToken();
				if(first.equalsIgnoreCase("name"))
				{
					nameField.setText(second);
				}
				else if(first.equalsIgnoreCase("name_kor"))
				{
					korNameField.setText(second);
				}
				else if(first.equalsIgnoreCase("difficulty"))
				{
					difficultyPinn.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("bonus"))
				{
					bonusPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("multiply_card_ratio"))
				{
					multiplyRatioPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("plus_card_ratio"))
				{
					plusminusRatioPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("change_card_count"))
				{
					changeCardPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("player_card_count"))
				{
					playerCardCountPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("ai_card_count"))
				{
					aiCardCountPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("timelimit"))
				{
					timeoutPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("change_card_count"))
				{
					changeCardPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("star_object") && stars == 0)
				{
					firstStar.setValue(new Integer(Integer.parseInt(second)));
					stars++;
				}
				else if(first.equalsIgnoreCase("star_object") && stars == 1)
				{
					secondStar.setValue(new Integer(Integer.parseInt(second)));
					stars++;
				}
				else if(first.equalsIgnoreCase("star_object") && stars == 2)
				{
					thirdStar.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("description"))
				{
					descriptionArea.setText(descriptionArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("description_kor"))
				{
					korDescriptionArea.setText(korDescriptionArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("script"))
				{
					scriptCheck.setSelected(true);
					scriptArea.setText(scriptArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("random_plus"))
				{
					randomPlusCheck.setSelected(Boolean.parseBoolean(second));
				}
				else if(first.equalsIgnoreCase("random_multiply"))
				{
					randomMultiplyCheck.setSelected(Boolean.parseBoolean(second));
				}
				else if(first.equalsIgnoreCase("random_player"))
				{
					randomPlayerCheck.setSelected(Boolean.parseBoolean(second));
				}
				else if(first.equalsIgnoreCase("random_deck"))
				{
					randomDeckCheck.setSelected(Boolean.parseBoolean(second));
				}
				else if(first.equalsIgnoreCase("random_player_min"))
				{
					randomPlayerMinPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_player_max"))
				{
					randomPlayerMaxPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_plus_min"))
				{
					randomPlusMinPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_plus_max"))
				{
					randomPlusMaxPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_multiply_min"))
				{
					randomMultiplyMinPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_multiply_max"))
				{
					randomMultiplyMaxPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_deck_min"))
				{
					randomDeckMinPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("random_deck_max"))
				{
					randomDeckMaxPinner.setValue(new Integer(Integer.parseInt(second)));
				}
				else if(first.equalsIgnoreCase("end_message"))
				{
					endMessageArea.setText(endMessageArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("kor_end_message"))
				{
					korEndMessageArea.setText(korEndMessageArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("fail_end_message"))
				{
					failMessageArea.setText(failMessageArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("fail_kor_end_message"))
				{
					korFailMessageArea.setText(korFailMessageArea.getText() + second + "\n");
				}
				else if(first.equalsIgnoreCase("authorize"))
				{
					auths = Long.parseLong(second);
				}
				else if(first.equalsIgnoreCase("player_card"))
				{
					StringTokenizer stkcard = new StringTokenizer(second, ",");
					StandardCard newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(stkcard.nextToken().toCharArray()[0]);
					newCard.setNum(Integer.parseInt(stkcard.nextToken()));
					newCard.setSeparator(sets.getCard_separator());
					addPlayerCardContents.add(new StandardCards(newCard, 1));
				}
				else if(first.equalsIgnoreCase("ai_card"))
				{
					StringTokenizer stkcard = new StringTokenizer(second, ",");
					StandardCard newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(stkcard.nextToken().toCharArray()[0]);
					newCard.setNum(Integer.parseInt(stkcard.nextToken()));
					newCard.setSeparator(sets.getCard_separator());
					addAICardContents.add(new StandardCards(newCard, 1));
				}
				else if(first.equalsIgnoreCase("deck"))
				{
					StringTokenizer stkcard = new StringTokenizer(second, ",");
					StandardCard newCard = new StandardCard();
					newCard.setAce(false);
					newCard.setOp(stkcard.nextToken().toCharArray()[0]);
					newCard.setNum(Integer.parseInt(stkcard.nextToken()));
					newCard.setSeparator(sets.getCard_separator());
					addDeckCardContents.add(new StandardCards(newCard, 1));
				}
				else if(first.equalsIgnoreCase("web_url"))
				{
					eventUse.setSelected(true);
					eventWebPage.setText(second);
				}
				else if(first.equalsIgnoreCase("event_onlywin"))
				{
					event_onlywin = Boolean.parseBoolean(second);
				}
				else if(first.equalsIgnoreCase("event_deadline"))
				{
					StringTokenizer stkdate = new StringTokenizer(second, ",");
					eventUse.setSelected(true);
					eventYear.setValue(new Integer(stkdate.nextToken()));
					try
					{
						eventMonth.setSelectedIndex(Integer.parseInt(stkdate.nextToken()) - 1);
					} 
					catch (Exception e)
					{
						eventMonth.setSelectedIndex(0);
						eventDay.setSelectedIndex(0);
						eventHour.setSelectedIndex(0);
						eventMinute.setSelectedIndex(0);
						eventSecond.setSelectedIndex(0);
					}
					try
					{
						eventDay.setSelectedIndex(Integer.parseInt(stkdate.nextToken()) - 1);
					} 
					catch (Exception e)
					{
						eventDay.setSelectedIndex(0);
						eventHour.setSelectedIndex(0);
						eventMinute.setSelectedIndex(0);
						eventSecond.setSelectedIndex(0);
					}
					try
					{
						eventHour.setSelectedIndex(Integer.parseInt(stkdate.nextToken()));
					} 
					catch (Exception e)
					{
						eventHour.setSelectedIndex(0);
						eventMinute.setSelectedIndex(0);
						eventSecond.setSelectedIndex(0);
					}
					try
					{
						eventMinute.setSelectedIndex(Integer.parseInt(stkdate.nextToken()));
					} 
					catch (Exception e)
					{
						eventMinute.setSelectedIndex(0);
						eventSecond.setSelectedIndex(0);
					}
					try
					{
						eventSecond.setSelectedIndex(Integer.parseInt(stkdate.nextToken()));
					} 
					catch (Exception e)
					{
						eventSecond.setSelectedIndex(0);
					}
				}
			} 
			catch (Exception e)
			{
				errors = errors + e.getMessage() + "\n";
				e.printStackTrace();
			}
		}
		if(! errors.equals("")) JOptionPane.showMessageDialog(this, errors);
		refresh();
	}
	public void refresh()
	{
		String results = "scenario\n";
		results = results + "name|" + nameField.getText() + "\n";
		results = results + "name_kor|" + korNameField.getText() + "\n";
		
		results = results + "difficulty|" + difficultyPinn.getValue().toString() + "\n";
		results = results + "bonus|" + bonusPinner.getValue().toString() + "\n";
		results = results + "multiply_card_ratio|" + multiplyRatioPinner.getValue().toString() + "\n";
		results = results + "plus_card_ratio|" + plusminusRatioPinner.getValue().toString() + "\n";
		results = results + "change_card_count|" + changeCardPinner.getValue().toString() + "\n";
		results = results + "player_card_count|" + playerCardCountPinner.getValue().toString() + "\n";
		results = results + "ai_card_count|" + aiCardCountPinner.getValue().toString() + "\n";
		results = results + "timelimit|" + timeoutPinner.getValue().toString() + "\n";
		results = results + "change_card_count|" + changeCardPinner.getValue().toString() + "\n";
		results = results + "star_object|" + firstStar.getValue().toString() + "\n";
		results = results + "star_object|" + secondStar.getValue().toString() + "\n";
		results = results + "star_object|" + thirdStar.getValue().toString() + "\n";
		
		results = results + "event_onlywin|" + String.valueOf(event_onlywin) + "\n";
		results = results + "authorize|" + String.valueOf(auths) + "\n";
		
		StringTokenizer stoken = new StringTokenizer(descriptionArea.getText(), "\n");
		String[] descriptions = new String[stoken.countTokens()];
		for(int i=0; i<descriptions.length; i++)
		{
			descriptions[i] = stoken.nextToken();
			results = results + "description|" + descriptions[i] + "\n";
		}
		stoken = new StringTokenizer(korDescriptionArea.getText(), "\n");
		descriptions = new String[stoken.countTokens()];
		for(int i=0; i<descriptions.length; i++)
		{
			descriptions[i] = stoken.nextToken();
			results = results + "description_kor|" + descriptions[i] + "\n";
		}
		if(addPlayerCardContents.size() >= 1)
		{
			for(int i=0; i<addPlayerCardContents.size(); i++)
			{
				for(int j=0; j<addPlayerCardContents.get(i).count; j++)
					results = results + "player_card|" + String.valueOf(addPlayerCardContents.get(i).target.getOp()) + "," + String.valueOf(addPlayerCardContents.get(i).target.getNum()) + "\n";
			}
		}
		if(addAICardContents.size() >= 1)
		{
			for(int i=0; i<addAICardContents.size(); i++)
			{
				for(int j=0; j<addAICardContents.get(i).count; j++)
					results = results + "ai_card|" + String.valueOf(addAICardContents.get(i).target.getOp()) + "," + String.valueOf(addAICardContents.get(i).target.getNum()) + "\n";
			}
		}
		if(addDeckCardContents.size() >= 1)
		{
			for(int i=0; i<addDeckCardContents.size(); i++)
			{
				for(int j=0; j<addDeckCardContents.get(i).count; j++)
					results = results + "deck|" + String.valueOf(addDeckCardContents.get(i).target.getOp()) + "," + String.valueOf(addDeckCardContents.get(i).target.getNum()) + "\n";
			}
		}
		if(scriptCheck.isSelected())
		{
			stoken = new StringTokenizer(scriptArea.getText(), "\n");
			descriptions = new String[stoken.countTokens()];
			for(int i=0; i<descriptions.length; i++)
			{
				descriptions[i] = stoken.nextToken();
				results = results + "script|" + descriptions[i] + "\n";
			}
		}
		if(randomPlayerCheck.isSelected())
		{
			results = results + "random_player|true" + "\n";
			results = results + "random_player_min|" + randomPlayerMinPinner.getValue().toString() + "\n";
			results = results + "random_player_max|" + randomPlayerMaxPinner.getValue().toString() + "\n";
		}
		if(randomPlusCheck.isSelected())
		{
			results = results + "random_plus|true" + "\n";
			results = results + "random_plus_min|" + randomPlusMinPinner.getValue().toString() + "\n";
			results = results + "random_plus_max|" + randomPlusMaxPinner.getValue().toString() + "\n";
		}
		if(randomMultiplyCheck.isSelected())
		{
			results = results + "random_multiply|true" + "\n";
			results = results + "random_multiply_min|" + randomMultiplyMinPinner.getValue().toString() + "\n";
			results = results + "random_multiply_max|" + randomMultiplyMaxPinner.getValue().toString() + "\n";
		}
		if(randomDeckCheck.isSelected())
		{
			results = results + "random_deck|true" + "\n";
			results = results + "random_deck_min|" + randomDeckMinPinner.getValue().toString() + "\n";
			results = results + "random_deck_max|" + randomDeckMaxPinner.getValue().toString() + "\n";
		}
		if(endMessageCheck.isSelected())
		{
			stoken = new StringTokenizer(endMessageArea.getText(), "\n");
			descriptions = new String[stoken.countTokens()];
			for(int i=0; i<descriptions.length; i++)
			{
				descriptions[i] = stoken.nextToken();
				results = results + "end_message|" + descriptions[i] + "\n";
			}
		}
		if(korEndMessageCheck.isSelected())
		{
			stoken = new StringTokenizer(korEndMessageArea.getText(), "\n");
			descriptions = new String[stoken.countTokens()];
			for(int i=0; i<descriptions.length; i++)
			{
				descriptions[i] = stoken.nextToken();
				results = results + "kor_end_message|" + descriptions[i] + "\n";
			}
		}
		if(failMessageCheck.isSelected())
		{
			stoken = new StringTokenizer(failMessageArea.getText(), "\n");
			descriptions = new String[stoken.countTokens()];
			for(int i=0; i<descriptions.length; i++)
			{
				descriptions[i] = stoken.nextToken();
				results = results + "fail_end_message|" + descriptions[i] + "\n";
			}
		}
		if(korFailMessageCheck.isSelected())
		{
			stoken = new StringTokenizer(korFailMessageArea.getText(), "\n");
			descriptions = new String[stoken.countTokens()];
			for(int i=0; i<descriptions.length; i++)
			{
				descriptions[i] = stoken.nextToken();
				results = results + "fail_kor_end_message|" + descriptions[i] + "\n";
			}
		}
		if(eventUse.isSelected())
		{
			results = results + "web_url|" + eventWebPage.getText() + "\n";
			results = results + "event_deadline|";
			results = results + String.valueOf(((Integer) eventYear.getValue()).intValue()) + ",";
			results = results + String.valueOf(((int) eventMonth.getSelectedIndex() + 1)) + ",";
			results = results + String.valueOf(((int) eventDay.getSelectedIndex() + 1)) + ",";
			results = results + String.valueOf(((int) eventHour.getSelectedIndex())) + ",";
			results = results + String.valueOf(((int) eventMinute.getSelectedIndex())) + ",";
			results = results + String.valueOf(((int) eventSecond.getSelectedIndex())) + ",";
			results = results + "\n";
		}
				
		contentArea.setText(results);		
		String[] lists;
		lists = new String[addPlayerCardContents.size()];
		for(int i=0; i<lists.length; i++)
		{
			lists[i] = addPlayerCardContents.get(i).target.toBasicString(sets.getCard_separator()) + ", #" + String.valueOf(addPlayerCardContents.get(i).count);
		}
		addPlayerCardList.setListData(lists);
		lists = new String[addAICardContents.size()];
		for(int i=0; i<lists.length; i++)
		{
			lists[i] = addAICardContents.get(i).target.toBasicString(sets.getCard_separator()) + ", #" + String.valueOf(addAICardContents.get(i).count);
		}
		addAICardList.setListData(lists);
		lists = new String[addDeckCardContents.size()];
		for(int i=0; i<lists.length; i++)
		{
			lists[i] = addDeckCardContents.get(i).target.toBasicString(sets.getCard_separator()) + ", #" + String.valueOf(addDeckCardContents.get(i).count);
		}
		addDeckCardList.setListData(lists);
		
		
		String texts = "";
		Difficulty difficulty = new Difficulty();
		try
		{
			Scenario tempSc = Scenario.stringToScenario(contentArea.getText());
			texts = tempSc.getDescription();
			difficulty.setDifficulty(tempSc.getDifficulty());					
			if(tempSc instanceof AuthorizedScenario)
			{
				AuthorizedScenario auts = (AuthorizedScenario) tempSc;
				if(auts.getKoreanDescription() == null || auts.getKoreanDescription().equals(""))
				{
					
				}
				else
				{
					if(sets.getLang() instanceof Korean)
						texts = auts.getKoreanDescription();
				}
				
				if(auts.isAuthorized(sets))
					texts = texts + "\n\n" + sets.getLang().getText(Language.AUTHORITY) + " " + sets.getLang().getText(Language.OK);
			}
			texts = sets.getLang().getText(Language.DIFFICULTY) + " : " + difficulty.toBasicString() + "\n\n" + texts;
			
			if(tempSc instanceof ExtendedScenario)
			{
				ExtendedScenario exts = (ExtendedScenario) tempSc;
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
				if(tempSc instanceof LankableScenario)
				{
					LankableScenario lks = (LankableScenario) tempSc;
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
				e1.printStackTrace();
			}
			
			rightArea.setText(texts);
			rightArea.setCaretPosition(0);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
	public void open()
	{
		this.setVisible(true);
	}
	public void close()
	{				
		this.setVisible(false);
		if(independence)
			System.exit(0);
	}
	public void save(File target)
	{
		FileOutputStream outputs = null;
		OutputStreamWriter outWrits = null;
		BufferedWriter writs = null;
		File changed = target;
		if(! (target.getAbsolutePath().endsWith(".scnx") || target.getAbsolutePath().endsWith(".SCNX")))
		{
			changed = new File(target.getAbsolutePath() + ".scnx");
		}
		try
		{
			outputs = new FileOutputStream(changed);
			outWrits = new OutputStreamWriter(outputs);
			writs = new BufferedWriter(outWrits);
			writs.write(contentArea.getText());
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		finally
		{
			try
			{
				writs.close();
			}
			catch(Exception e2)
			{
				
			}
			try
			{
				outWrits.close();
			}
			catch(Exception e2)
			{
				
			}
			try
			{
				outputs.close();
			}
			catch(Exception e2)
			{
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		refresh();
		if(ob == exitButton)
		{
			close();
		}
		else if(ob == exitButton2)
		{
			close();
		}
		else if(ob == authorizeButton)
		{
			String asking, taking;
			if(lang == 1)
			{
				asking = "인증을 위해서는 인증 비밀번호가 필요합니다.";
			}
			else
			{
				asking = "Need secret code to authorize this scenario.";
			}
			taking = JOptionPane.showInputDialog(this, asking);
			long takingCode = Long.parseLong(taking);
			if(CalcWindow.getAuthorizedCode(takingCode) >= 1)
			{
				refresh();
				LankableScenario changed = Scenario.stringToScenario(contentArea.getText());
				changed.authorize(sets);
				auths = changed.getAuthorize_code().longValue();
				refresh(Scenario.scenarioToString(changed));
			}
			else
			{
				String failed;
				if(lang == 1)
				{
					failed = "인증 실패";
				}
				else
				{
					failed = "Fail to authorize";
				}
				JOptionPane.showMessageDialog(this, failed);
			}
		}			
		else if(ob == saveasButton)
		{
			refresh();			
			if(fchooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = fchooser.getSelectedFile();
				save(selectedFile);
				if(superComponent != null)
				{
					String lasts = "";
					if(lang == 1)
					{
						lasts = sets.getLang().getText(Language.NEED_RESTART);
					}
					else
					{
						lasts = sets.getLang().getText(Language.NEED_RESTART);
					}
					JOptionPane.showMessageDialog(this, lasts);
				}
			}
			
		}
		else if(ob == saveButton)
		{
			refresh();
			if(target == null)
			{
				if(fchooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fchooser.getSelectedFile();
					save(selectedFile);
					if(superComponent != null)
					{
						String lasts = "";
						if(lang == 1)
						{
							lasts = sets.getLang().getText(Language.NEED_RESTART);
						}
						else
						{
							lasts = sets.getLang().getText(Language.NEED_RESTART);
						}
						JOptionPane.showMessageDialog(this, lasts);
					}
				}
			}
			else
			{
				save(target);
			}
		}
		else if(ob == loadButton)
		{		
			if(fchooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = fchooser.getSelectedFile();
				FileInputStream outputs = null;
				InputStreamReader outWrits = null;
				BufferedReader writs = null;
				String readContents = "", readTemp = "";
				try
				{
					outputs = new FileInputStream(selectedFile);
					outWrits = new InputStreamReader(outputs);
					writs = new BufferedReader(outWrits);
					while(true)
					{
						readTemp = writs.readLine();
						if(readTemp == null) break;
						readContents = readContents + readTemp + "\n";
					}
					refresh(readContents);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
				finally
				{
					try
					{
						writs.close();
					}
					catch(Exception e2)
					{
						
					}
					try
					{
						outWrits.close();
					}
					catch(Exception e2)
					{
						
					}
					try
					{
						outputs.close();
					}
					catch(Exception e2)
					{
						
					}
				}
			}
			refresh();
		}
		else if(ob == newButton)
		{
			refresh("");
			target = null;
			refresh();
			tabbedPane.setSelectedIndex(1);			
		}
		else if(ob == addPlayerCardPlus)
		{
			int selIndex = addPlayerCardOperation.getSelectedIndex();
			int howmany = 1;
			switch(addPlayerCardHowMany.getSelectedIndex())
			{
				case 0:
					howmany = 1;
					break;
				case 1:
					howmany = 10;
					break;
				case 2:
					howmany = 100;
					break;
				default:
					howmany = 1;
			}
			StandardCard newCard = new StandardCard();
			newCard.setAce(false);
			newCard.setNum(Integer.parseInt(addPlayerCardNumber.getValue().toString()));
			switch(selIndex)
			{
				case 0:
					newCard.setOp(sets.getOp_plus());
					break;
				case 1:
					newCard.setOp(sets.getOp_minus());
					break;
				case 2:
					newCard.setOp(sets.getOp_multiply());
					break;
				case 3:
					newCard.setOp(sets.getOp_change());
					break;
			}
			addPlayerCardContents.add(new StandardCards(newCard, howmany));
			refresh();
		}
		else if(ob == addAICardPlus)
		{
			int selIndex = addAICardOperation.getSelectedIndex();
			int howmany = 1;
			switch(addAICardHowMany.getSelectedIndex())
			{
				case 0:
					howmany = 1;
					break;
				case 1:
					howmany = 10;
					break;
				case 2:
					howmany = 100;
					break;
				default:
					howmany = 1;
			}
			StandardCard newCard = new StandardCard();
			newCard.setAce(false);
			newCard.setNum(Integer.parseInt(addAICardNumber.getValue().toString()));
			switch(selIndex)
			{
				case 0:
					newCard.setOp(sets.getOp_plus());
					break;
				case 1:
					newCard.setOp(sets.getOp_minus());
					break;
				case 2:
					newCard.setOp(sets.getOp_multiply());
					break;
				case 3:
					newCard.setOp(sets.getOp_change());
					break;
			}
			addAICardContents.add(new StandardCards(newCard, howmany));
			refresh();
		}
		else if(ob == addDeckCardPlus)
		{
			int selIndex = addDeckCardOperation.getSelectedIndex();
			int howmany = 1;
			switch(addDeckCardHowMany.getSelectedIndex())
			{
				case 0:
					howmany = 1;
					break;
				case 1:
					howmany = 10;
					break;
				case 2:
					howmany = 100;
					break;
				default:
					howmany = 1;
			}
			StandardCard newCard = new StandardCard();
			newCard.setAce(false);
			newCard.setNum(Integer.parseInt(addDeckCardNumber.getValue().toString()));
			switch(selIndex)
			{
				case 0:
					newCard.setOp(sets.getOp_plus());
					break;
				case 1:
					newCard.setOp(sets.getOp_minus());
					break;
				case 2:
					newCard.setOp(sets.getOp_multiply());
					break;
				case 3:
					newCard.setOp(sets.getOp_change());
					break;
			}
			addDeckCardContents.add(new StandardCards(newCard, howmany));
			refresh();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		refresh();
		if(ob == scriptCheck)
		{
			scriptArea.setEditable(scriptCheck.isSelected());
		}
		else if(ob == randomPlusCheck)
		{
			randomPlusMinPinner.setEnabled(randomPlusCheck.isSelected());
			randomPlusMaxPinner.setEnabled(randomPlusCheck.isSelected());
		}
		else if(ob == randomMultiplyCheck)
		{
			randomMultiplyMinPinner.setEnabled(randomMultiplyCheck.isSelected());
			randomMultiplyMaxPinner.setEnabled(randomMultiplyCheck.isSelected());
		}
		else if(ob == randomPlayerCheck)
		{
			randomPlayerMinPinner.setEnabled(randomPlayerCheck.isSelected());
			randomPlayerMaxPinner.setEnabled(randomPlayerCheck.isSelected());
		}
		else if(ob == randomDeckCheck)
		{
			randomDeckMinPinner.setEnabled(randomDeckCheck.isSelected());
			randomDeckMaxPinner.setEnabled(randomDeckCheck.isSelected());
		}
		else if(ob == endMessageCheck)
		{
			endMessageArea.setEditable(endMessageCheck.isSelected());
		}
		else if(ob == korEndMessageCheck)
		{
			korEndMessageArea.setEditable(korEndMessageCheck.isSelected());
		}
		else if(ob == failMessageCheck)
		{
			failMessageArea.setEditable(failMessageCheck.isSelected());
		}
		else if(ob == korFailMessageCheck)
		{
			korFailMessageArea.setEditable(korFailMessageCheck.isSelected());
		}
		else if(ob == eventUse)
		{
			eventWebPage.setEditable(eventUse.isSelected());
			eventYear.setEnabled(eventUse.isSelected());
			eventMonth.setEnabled(eventUse.isSelected());
			eventDay.setEnabled(eventUse.isSelected());
			eventHour.setEnabled(eventUse.isSelected());
			eventMinute.setEnabled(eventUse.isSelected());
			eventSecond.setEnabled(eventUse.isSelected());
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		close();
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exit()
	{
		this.setVisible(false);
		if(independence)
			System.exit(0);
		
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
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
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
		if(ob == titlePanel) 
		{
			mousex = e.getX();
			mousey = e.getY();	
		}
		else if(ob == addPlayerCardList)
		{
			if(addPlayerCardListClicked >= 2)
			{
				addPlayerCardContents.remove(addPlayerCardListSelected);
				addPlayerCardListClicked = 0;
				refresh();
			}
			else
			{
				addPlayerCardListClicked++;
			}
		}
		else if(ob == addAICardList)
		{
			if(addAICardListClicked >= 2)
			{
				addAICardContents.remove(addAICardListSelected);
				addAICardListClicked = 0;
				refresh();
			}
			else
			{
				addAICardListClicked++;
			}
		}
		else if(ob == addDeckCardList)
		{
			if(addDeckCardListClicked >= 2)
			{
				addDeckCardContents.remove(addDeckCardListSelected);
				addDeckCardListClicked = 0;
				refresh();
			}
			else
			{
				addDeckCardListClicked++;
			}
		}
		else
		{
			refresh();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		refresh();
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == addPlayerCardList)
		{
			addPlayerCardListSelected = addPlayerCardList.getSelectedIndex();
			addPlayerCardListClicked = 0;
		}
		else if(ob == addAICardList)
		{
			addAICardListSelected = addAICardList.getSelectedIndex();
			addAICardListClicked = 0;
		}
		else if(ob == addDeckCardList)
		{
			addDeckCardListSelected = addDeckCardList.getSelectedIndex();
			addDeckCardListClicked = 0;
		}
	}
}
class ScenarioFileFilter extends javax.swing.filechooser.FileFilter
{

	@Override
	public boolean accept(File pathname)
	{
		if(pathname != null)
		{
			if(pathname.isDirectory()) return true;
			if(pathname.getAbsolutePath().endsWith(".scnx")) return true;
		}
		return false;
	}

	@Override
	public String getDescription()
	{				
		return "Scenario Text (.scnx)";
	}
	
}
class StandardCards
{
	public StandardCard target;
	public int count = 1;
	public StandardCards(StandardCard tg)
	{
		target = tg;
	}
	public StandardCards(StandardCard tg, int sz)
	{
		target = tg;
		count = sz;
	}
}
