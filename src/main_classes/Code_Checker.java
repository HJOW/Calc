package main_classes;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.Lint;
import setting.Setting;

public class Code_Checker implements ActionListener, WindowListener, MouseListener, MouseMotionListener, Openable
{
	boolean independence = false;
	boolean gui = false;
	private JDialog checker_dialog;
	private InputStreamReader inread;
	private BufferedReader bfreader;
	boolean while_loop = true;
	Setting setting;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JPanel inputPanel;
	private JLabel inputLabel;
	private JPanel[] inputPns;
	private JTextField inputField;
	private JButton checkButton;
	private JButton closeButton;
	private JPanel contentPanel;
	private JPanel[] contentPns;
	private JLabel[] contentLabels;
	private JTextField[] contentFields;
	
	public Code_Checker(boolean independence)
	{
		this.gui = false;
		this.independence = independence;		
	}
	public Code_Checker(boolean independence, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame();
	}
	public Code_Checker(boolean independence, JDialog upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Code_Checker(boolean independence, Frame upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Code_Checker(boolean independence, Dialog upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Code_Check_Result check_code(String code)
	{
		//System.out.println(code);
		try
		{
			String[] codes;
			StringTokenizer stokens = new StringTokenizer(code, "||");
			int token_count = stokens.countTokens();
			codes = new String[token_count];
			for(int i=0; i<token_count; i++)
			{
				codes[i] = stokens.nextToken();
			}
			
			/*
			for(int i=0; i<codes.length; i++)
			{
				System.out.println(codes[i]);
			}*/
			long get_point = Long.parseLong(codes[0]);
			String name = codes[1];
			int players = Integer.parseInt(codes[2]);
			int own_cards = Integer.parseInt(codes[3]);
			int ver_main = Integer.parseInt(codes[4]);
			int ver_sub1 = Integer.parseInt(codes[5]);
			int ver_sub2 = Integer.parseInt(codes[6]);
			BigInteger secret_code_inputed = new BigInteger(codes[7]);
			int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
			String play_mode = "";
			aut_year = Integer.parseInt(codes[8]);
			aut_month = Integer.parseInt(codes[9]);
			aut_day = Integer.parseInt(codes[10]);
			aut_hour = Integer.parseInt(codes[11]);
			aut_min = Integer.parseInt(codes[12]);
			aut_sec = Integer.parseInt(codes[13]);
			play_mode = codes[14];
			BigInteger secret_code = Lint.zero();
			BigInteger secret_nameCode, secret_nameCode_inputed;
			secret_nameCode_inputed = new BigInteger("0");
			
			//System.out.println("1 : " + secret_code);
			secret_code = secret_code.add(Lint.big((ver_main * 100) + (ver_sub1 * 10) + ver_sub2));
			//System.out.println("2 : " + secret_code);
			secret_code = secret_code.multiply(Lint.big(own_cards));
			//System.out.println("3 : " + secret_code);
			secret_code = secret_code.add(Lint.big(get_point));
			//System.out.println("4 : " + secret_code);
			long authcode = 0;
			
			// authority_code used
			if(ver_main == CalcWindow.version_main && ver_sub1 == CalcWindow.version_sub_1 && ver_sub2 == CalcWindow.version_sub_2) authcode = CalcWindow.getAuthorizedCode(2938291);
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 2) authcode = 9564715;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 3) authcode = 6484634;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 4) authcode = 5135947;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 5) authcode = 2849281;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 6) authcode = 8273271;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 7) authcode = 9204813;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 8) authcode = 8294881;
			else if(ver_main == 0 && ver_sub1 == 2 && ver_sub2 == 9) authcode = 7728471;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 0) authcode = 9283927;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 1) authcode = 8293392;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 2) authcode = 7293811;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 3) authcode = 9238192;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 4) authcode = 1002937;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 5) authcode = 4283829;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 6) authcode = 2938172;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 7) authcode = 1928321;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 8) authcode = 2993812;
			else if(ver_main == 0 && ver_sub1 == 3 && ver_sub2 == 9) authcode = 8294729;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 0) authcode = 3849273;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 1) authcode = 5283918;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 2) authcode = 8394729;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 3) authcode = 6184729;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 4) authcode = 2947192;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 5) authcode = 8104928;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 6) authcode = 1219283;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 7) authcode = 1227920;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 8) authcode = 3219372;
			else if(ver_main == 0 && ver_sub1 == 4 && ver_sub2 == 9) authcode = 4928391;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 0) authcode = 7283927;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 1) authcode = 2938472;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 2) authcode = 6283729;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 3) authcode = 5283917;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 4) authcode = 4929301;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 5) authcode = 6291839;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 6) authcode = 1928392;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 7) authcode = 6288471;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 8) authcode = 9273928;
			else if(ver_main == 0 && ver_sub1 == 5 && ver_sub2 == 9) authcode = 1729472;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 0) authcode = 1927382;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 1) authcode = 2819482;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 2) authcode = 3275918;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 3) authcode = 1958293;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 4) authcode = 3729572;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 5) authcode = 4871295;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 6) authcode = 2848194;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 7) authcode = 1928392;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 8) authcode = 6927349;
			else if(ver_main == 0 && ver_sub1 == 6 && ver_sub2 == 9) authcode = 8294711;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 0) authcode = 9271827;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 1) authcode = 1927492;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 2) authcode = 4928392;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 3) authcode = 7261827;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 4) authcode = 9279372;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 5) authcode = 6284373;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 6) authcode = 8293784;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 7) authcode = 7928491;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 8) authcode = 6284719;
			else if(ver_main == 0 && ver_sub1 == 7 && ver_sub2 == 9) authcode = 3817392;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 0) authcode = 8492832;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 1) authcode = 7264892;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 2) authcode = 3159715;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 3) authcode = 9928492;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 4) authcode = 2948193;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 5) authcode = 1927492;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 6) authcode = 7384712;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 7) authcode = 8294723;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 8) authcode = 6928492;
			else if(ver_main == 0 && ver_sub1 == 8 && ver_sub2 == 9) authcode = 4928539;
			else if(ver_main == 0 && ver_sub1 == 9 && ver_sub2 == 0) authcode = 5471534;
			
			boolean version_check = false;
			if(ver_main > 0)
			{
				version_check = true;
			}
			else if(ver_main == 0)
			{
				if(ver_sub1 > 4)
				{
					version_check = true;
				}
				else if(ver_sub1 == 4)
				{
					if(ver_sub2 >= 6)
					{
						version_check = true;
					}
				}
			}
			if(version_check)
			{
				try
				{
					secret_nameCode_inputed = new BigInteger(codes[16]);
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					secret_nameCode_inputed = new BigInteger("0");
				}
				catch(NumberFormatException e)
				{
					secret_nameCode_inputed = new BigInteger("0");
				}
			}
			
			//System.out.println("5 : " + secret_code);
			secret_code = secret_code.add(Lint.big(authcode + (players * ((ver_main * 100) + (ver_sub1 * 10) + ver_sub2))));
			secret_nameCode = new BigInteger(String.valueOf(secret_code));
			secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf((int) Math.round((double) RunManager.getNameCode(name) / 100.0) + 5)));
			//System.out.println("6 : " + secret_code);
			secret_code = secret_code.add(Lint.big((own_cards + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
			if(ver_main >= 0 && ver_sub1 >= 7 && ver_sub2 >= 0)
				secret_nameCode = secret_nameCode.add(Lint.big(codes[14].length()).multiply(Lint.big(authcode)));
			//System.out.println("7 : " + secret_code);
			
			//System.out.println("Secret Code : " + secret_code.toString());
			//System.out.println("Get Secret Code : " + secret_code_inputed.toString());
			//System.out.println("Secret Name Code : " + secret_nameCode.toString());
			//System.out.println("Get Secret Name Code : " + secret_nameCode_inputed.toString());
						
			boolean auths = false;
			if(secret_code.compareTo(secret_code_inputed) == 0)
			{
				auths = true;				
			}	
			else auths = false;
			
			boolean auths_name = false;
			if(secret_nameCode.compareTo(secret_nameCode_inputed) == 0)
			{
				auths_name = true;
			}
			else auths_name = false;	
			
			if(codes.length >= 19)
			{
				BigInteger secret_scenarioCode = Lint.copy(secret_nameCode);
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_year));
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_month));
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_day));
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_hour));
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_month));
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(aut_sec));
								
				StringTokenizer scenarioTokens = new StringTokenizer(codes[17], ",");
				scenarioTokens.nextToken();
				String eventWin = scenarioTokens.nextToken();
				BigInteger tg_secret_scenarioCode = Lint.big(codes[18]);
				
				
				if(eventWin.equalsIgnoreCase("win"))
				{
					secret_scenarioCode = secret_scenarioCode.multiply(Lint.big(2));					
				}
				secret_scenarioCode = secret_scenarioCode.add(Lint.big(codes[17].toCharArray().length));
				if(secret_scenarioCode.compareTo(tg_secret_scenarioCode) != 0)
				{
					auths_name = false;
				}
			}
			
			
			Code_Check_Result result = new Code_Check_Result();
			result.setName(new String(name));
			result.setCode(new String(code));
			result.setPlayers(players);
			result.setOwn_cards(own_cards);
			result.setVer_main(ver_main);
			result.setVer_sub1(ver_sub1);
			result.setVer_sub2(ver_sub2);
			result.setPoint(get_point);
			result.setAut_year(aut_year);
			result.setAut_month(aut_month);
			result.setAut_day(aut_day);
			result.setAut_hour(aut_hour);
			result.setAut_min(aut_min);
			result.setAut_sec(aut_sec);
			result.setAuthorized(auths);
			result.setPlay_mode(play_mode);
			result.setAuthorized_name(auths_name);
			return result;
		} 
		catch (NumberFormatException e)
		{
			return null;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void open()
	{
		if(gui) checker_dialog.setVisible(true);
		else
		{
			inread = new InputStreamReader(System.in);
			bfreader = new BufferedReader(inread);
			while(while_loop)
			{
				int input = 0;				
				String input_code = "";
				System.out.println(setting.getLang().getText(Language.CODE_CHECKER));
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println(setting.getLang().getText(Language.MENU));
				System.out.println("1. " + setting.getLang().getText(Language.CHECK));
				System.out.println("2. " + setting.getLang().getText(Language.EXIT));
				System.out.println("-----------------------------------------");
				System.out.print(setting.getLang().getText(Language.INPUT) + " : ");
				try
				{
					input = Integer.parseInt(bfreader.readLine());
					switch(input)
					{
						case 1:
							System.out.print(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.INPUT) + " : ");
							input_code = bfreader.readLine();
							Code_Check_Result result = check_code(input_code);
							check_refresh(result);
							System.out.println("-----------------------------------------");
							break;
						case 2:
							exit();
							break;							
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
					continue;
				}
				catch(NullPointerException e)
				{
					System.out.println(setting.getLang().getText(Language.DESCRIPTIONS + 20));
					continue;
				}
				catch(NumberFormatException e)
				{
					System.out.println(setting.getLang().getText(Language.DESCRIPTIONS + 20));
					continue;
				}				
			}
		}
	}
	public void exit()
	{
		if(independence) System.exit(0);
		else
		{
			if(gui)
			{
				checker_dialog.setVisible(false);
			}
			else
			{
				while_loop = false;
				try
				{
					inread.close();
					bfreader.close();
				} 
				catch (Exception e)
				{
					
				}
			}
		}
	}
	public void init_frame()
	{
		checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(JDialog upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(Dialog upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(Frame upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public Window getWindow()
	{
		return checker_dialog;
	}
	public void init_other()
	{
		if(CalcWindow.usingFont == null)
			CalcWindow.prepareFont();
		checker_dialog.setSize(500, 400);
		checker_dialog.setLocation((int) (setting.getScreenSize().getWidth()/2 - checker_dialog.getWidth()/2), (int) (setting.getScreenSize().getHeight()/2 - checker_dialog.getHeight()/2));
		checker_dialog.setUndecorated(setting.isUse_own_titleBar());
		if(! setting.isUse_own_titleBar()) checker_dialog.addWindowListener(this);
		mainPanel = new JPanel();
		checker_dialog.getContentPane().setLayout(new BorderLayout());
		checker_dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EtchedBorder());
		
		centerPanel = new JPanel();
		upPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		mainPanel.setBackground(setting.getSelected_back());
		
		upPanel.setLayout(new BorderLayout());
		upPanel.setBackground(setting.getSelected_back());
		
		titlePanel = new JPanel();
		title = new JLabel(setting.getLang().getText(Language.CODE_CHECKER));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		if(setting.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			titlePanel.setBorder(new EtchedBorder());
			titlePanel.setBackground(setting.getSelected_inside_back());
			title.setForeground(setting.getSelected_fore());
			titlePanel.setLayout(new FlowLayout());
			titlePanel.add(title);
		}
		
		inputPanel = new JPanel();
		upPanel.add(inputPanel, BorderLayout.CENTER);
		inputPanel.setBackground(setting.getSelected_back());
		inputPns = new JPanel[2];
		inputPanel.setLayout(new GridLayout(inputPns.length, 1));
		for(int i=0; i<inputPns.length; i++)
		{
			inputPns[i] = new JPanel();
			inputPns[i].setBackground(setting.getSelected_back());
			inputPns[i].setLayout(new FlowLayout());
			inputPanel.add(inputPns[i]);
		}
		inputLabel = new JLabel(setting.getLang().getText(Language.DESCRIPTIONS + 19));
		if(CalcWindow.usingFont != null)
			inputLabel.setFont(CalcWindow.usingFont);
		inputLabel.setForeground(setting.getSelected_fore());
		inputPns[0].add(inputLabel);
		inputField = new JTextField(35);
		if(CalcWindow.usingFont != null)
			inputField.setFont(CalcWindow.usingFont);
		inputField.setForeground(setting.getSelected_fore());
		inputField.setBackground(setting.getSelected_inside_back());
		inputPns[1].add(inputField);
		checkButton = new JButton(setting.getLang().getText(Language.CHECK));
		if(CalcWindow.usingFont != null)
			checkButton.setFont(CalcWindow.usingFont);
		checkButton.addActionListener(this);
		checkButton.setForeground(setting.getSelected_fore());
		inputPns[1].add(checkButton);
		
		downPanel.setLayout(new FlowLayout());
		downPanel.setBackground(setting.getSelected_back());
		closeButton = new JButton(setting.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			closeButton.setFont(CalcWindow.usingFont);
		closeButton.addActionListener(this);
		closeButton.setForeground(setting.getSelected_fore());
		downPanel.add(closeButton);
		
		centerPanel.setBackground(setting.getSelected_back());
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder());
		contentPanel.setBackground(setting.getSelected_back());
		centerPanel.add(contentPanel, BorderLayout.CENTER);
		
		contentPns = new JPanel[8];
		contentLabels = new JLabel[contentPns.length];
		contentFields = new JTextField[contentPns.length];
		contentPanel.setLayout(new GridLayout(contentPns.length, 1));
		for(int i=0; i<contentPns.length; i++)
		{
			contentPns[i] = new JPanel();
			contentPns[i].setLayout(new FlowLayout());
			contentLabels[i] = new JLabel();
			contentFields[i] = new JTextField(30);
			if(CalcWindow.usingFont != null)
			{
				contentLabels[i].setFont(CalcWindow.usingFont);
				contentFields[i].setFont(CalcWindow.usingFont);
			}
			contentPns[i].setBackground(setting.getSelected_back());
			contentLabels[i].setForeground(setting.getSelected_fore());
			contentFields[i].setForeground(setting.getSelected_fore());
			contentFields[i].setBackground(setting.getSelected_inside_back());
			contentFields[i].setEditable(false);
			contentFields[i].setBorder(new EtchedBorder());
			contentPns[i].add(contentLabels[i]);
			contentPns[i].add(contentFields[i]);
			contentPanel.add(contentPns[i]);
		}
		contentLabels[0].setText(setting.getLang().getText(Language.NAME));
		contentLabels[1].setText(setting.getLang().getText(Language.POINT));
		contentLabels[2].setText(setting.getLang().getText(Language.CARD) + " " + setting.getLang().getText(Language.COUNT));
		contentLabels[3].setText(setting.getLang().getText(Language.PLAYER) + " " + setting.getLang().getText(Language.COUNT));
		contentLabels[4].setText(setting.getLang().getText(Language.VERSION));
		contentLabels[5].setText(setting.getLang().getText(Language.AUTHORITY));
		contentLabels[6].setText(setting.getLang().getText(Language.PLAY));
		contentLabels[7].setText(setting.getLang().getText(Language.MODE));
	}
	public void check_refresh(Code_Check_Result result)
	{
		if(gui)
		{
			if(result == null)
			{
				for(int i=0; i<contentPns.length; i++)
					contentFields[i].setText("");
			}
			else
			{					
				contentFields[0].setText(result.getName());
				contentFields[1].setText(String.valueOf(result.getPoint()));
				contentFields[2].setText(String.valueOf(result.getOwn_cards()));
				contentFields[3].setText(String.valueOf(result.getPlayers()));
				contentFields[4].setText(result.getVer_main() + "." + result.getVer_sub1() + "" + result.getVer_sub2());
				if(result.isAuthorized()) contentFields[5].setText(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.COMPLETE));
				else contentFields[5].setText(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.FAIL));
				contentFields[6].setText(String.valueOf(result.getAut_year()) + setting.getLang().getText(Language.YEAR) + " " + String.valueOf((result.getAut_month() + 1)) + setting.getLang().getText(Language.MONTH) + " " + String.valueOf(result.getAut_day()) + setting.getLang().getText(Language.DAY) + " " + String.valueOf(result.getAut_hour()) + setting.getLang().getText(Language.HOUR) + " " + String.valueOf(result.getAut_min()) + setting.getLang().getText(Language.MINUTE) + " ");
				if(result.isAuthorized_name()) contentFields[7].setText(result.getPlay_mode() + " +");
				else contentFields[7].setText(result.getPlay_mode());
			}
		}
		else
		{
			if(result != null)
			{
				System.out.println(setting.getLang().getText(Language.NAME) + " : " + result.getName());
				System.out.println(setting.getLang().getText(Language.POINT) + " : " + result.getPoint());
				System.out.println(setting.getLang().getText(Language.CARD) + " " + setting.getLang().getText(Language.COUNT) + " : " + result.getOwn_cards());
				System.out.println(setting.getLang().getText(Language.PLAYER) + " " + setting.getLang().getText(Language.COUNT) + " : " + result.getPlayers());
				System.out.println(setting.getLang().getText(Language.VERSION) + " : " + result.getVer_main() + "." + result.getVer_sub1() + "" + result.getVer_sub2());
				System.out.println(result.getAut_year() + "." + (result.getAut_month() + 1) + "." + result.getAut_day() + "." + result.getAut_hour() + "." + result.getAut_min());
				System.out.println(result.getPlay_mode());
				System.out.println(result.isAuthorized_name());
				if(result.isAuthorized())
				{
					System.out.println(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.COMPLETE));
				}
				else System.out.println(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.FAIL));
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		checker_dialog.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
		
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
		exit();
		
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
		if(ob == closeButton)
		{
			exit();
		}
		else if(ob == checkButton)
		{
			//System.out.println(inputField.getText());
			check_refresh(check_code(inputField.getText()));
		}
		
	}
	
}
