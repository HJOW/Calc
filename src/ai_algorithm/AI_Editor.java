package ai_algorithm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import lang.Language;
import main_classes.CalcWindow;
import main_classes.MessageShowable;
import main_classes.Openable;
import setting.Setting;

public class AI_Editor implements ActionListener, WindowListener, Openable, MessageShowable, MouseListener, MouseMotionListener, ItemListener
{
	public Setting set;
	public boolean independence = false;	
	public boolean gui = false;
	public JDialog dialog;
	public AI_Scripted_Algorithm target;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel[] panels;
	private JLabel[] labels;
	private JTextField[] fields;
	private JButton save;
	private JButton close;
	private JPanel upPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private FileFilter ff1;
	private JFileChooser cfd;
	private JScrollPane centerScroll;
	private JPanel titlePanel;
	private JPanel upControlPanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JDialog scriptDialog;
	private JPanel scriptDialog_mainPanel;
	private JPanel scriptDialog_upPanel;
	private JPanel scriptDialog_centerPanel;
	private JPanel scriptDialog_downPanel;
	private JCheckBox scriptDialog_enable;
	private JButton scriptDialog_accept;
	private JButton scriptDialog_close;
	private JTextArea scriptDialog_scriptPanel;
	private JScrollPane scriptDialog_scriptScroll;
	private String script = "";
	private JButton edit_script;
	private JPanel scriptDialog_titlePanel;
	private JPanel scriptDialog_enablePanel;
	private JLabel scriptDialog_title;
	private int script_mousex;
	private int script_mousey;
	private JTextField scriptDialog_kind;
	private String default_script = "";
	
	public AI_Editor(boolean independence, Setting set)
	{
		gui = false;
		this.independence = independence;
		this.set = set.clone();
		target = new AI_Scripted_Algorithm();
	}
	public AI_Editor(JDialog dialog, boolean independence, Setting set)
	{
		gui = true;
		this.independence = independence;
		this.set = set.clone();
		target = new AI_Scripted_Algorithm();
		init_dialog(dialog, true);
	}
	public AI_Editor(JFrame frame, boolean independence, Setting set)
	{
		gui = true;
		this.independence = independence;
		this.set = set.clone();
		target = new AI_Scripted_Algorithm();
		init_dialog(frame, false);
	}
	public void init_dialog(Object ob, boolean isDialog)
	{
		JDialog gets;
		if(isDialog)
		{
			gets = (JDialog) ob;
			dialog = new JDialog(gets);
		}
		else
		{
			dialog = new JDialog((JFrame)(ob));
		}
		default_script = "// point_i : Keyword that means player i\'s point, i is number and starts at 0";
		default_script = default_script + "\n// deck_size : Keyword that means number of cards in deck.";
		default_script = default_script + "\n// turn : Keyword that means number of player turn.";
		default_script = default_script + "\n// ver : Keyword that means this game version.";
		default_script = default_script + "\n// target_act : Keyword that means acting of calculating bounds now. This will be only \"take\" of \"pay\"";
		default_script = default_script + "\n// target_card : Keyword that means target card of calculating bounds now.";
		default_script = default_script + "\n// target_card_num : Keyword that means number of target card of calculating bounds now.";
		default_script = default_script + "\n// target_card_op : Keyword that means operation of target card of calculating bounds now.";
		default_script = default_script + "\n// last_i : Keyword that means the last card in player i\'s paid slot.";
		default_script = default_script + "\n// players : Keyword that means number of players.";
		default_script = default_script + "\n// player_i_name : Keyword that means player i\'s name.";
		default_script = default_script + "\n// player_i_owns : Keyword that means the number of player i\'s own cards.";
		default_script = default_script + "\n// player_i_own_j : Keyword that means player i\'s j th card.";
		default_script = default_script + "\n// player_i_own_j_op : Keyword that means player i\'s j th card\'s operation.";
		default_script = default_script + "\n// player_i_own_j_num : Keyword that means player i\'s j th card\'s number.";
		default_script = default_script + "\nfunction calc_bound()\n{\n\t// Insert script here\n\t\n\t// Need to return a number that effects to decide action.\n\treturn 0;\n}";
		
		dialog.setSize(600, 400);
		dialog.setLocation((int)(set.getScreenSize().getWidth()/2 - dialog.getWidth()/2), (int)(set.getScreenSize().getHeight()/2 - dialog.getHeight()/2));
		dialog.addWindowListener(this);
		if(set.isUse_own_titleBar()) dialog.setUndecorated(true);
		mainPanel = new JPanel();
		mainPanel.setBackground(set.getSelected_back());
		mainPanel.setBorder(new EtchedBorder());
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().add(mainPanel);
		
		centerPanel = new JPanel();
		downPanel = new JPanel();
		upPanel = new JPanel();
		centerPanel.setBackground(set.getSelected_back());
		downPanel.setBackground(set.getSelected_back());
		upPanel.setBackground(set.getSelected_back());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upPanel, BorderLayout.NORTH);		
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		panels = new JPanel[9];
		labels = new JLabel[panels.length];
		fields = new JTextField[panels.length];
		centerPanel.setLayout(new GridLayout(panels.length, 1));
		centerScroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(centerScroll, BorderLayout.CENTER);
		for(int i=0; i<panels.length; i++)
		{
			panels[i] = new JPanel();
			labels[i] = new JLabel();
			fields[i] = new JTextField(5);
			if(CalcWindow.usingFont != null)
			{
				labels[i].setFont(CalcWindow.usingFont);
				fields[i].setFont(CalcWindow.usingFont);
			}
			panels[i].setBackground(set.getSelected_back());
			labels[i].setForeground(set.getSelected_fore());
			fields[i].setBackground(set.getSelected_inside_back());
			fields[i].setForeground(set.getSelected_fore());
			panels[i].setLayout(new FlowLayout());
			panels[i].add(labels[i]);
			panels[i].add(fields[i]);
			centerPanel.add(panels[i]);
		}
		
		labels[0].setText("Operation that acts when the current player\'s turn : ");
		labels[1].setText("Value that be multiplied when the current player\'s turn : ");
		labels[2].setText("Operation that acts when the other player\'s turn : ");
		labels[3].setText("Value that be multiplied when the other player\'s turn : ");
		labels[4].setText("Value that be calculated when the game is almost end : ");
		labels[5].setText("Operation that acts when the game is almost end with current player winning and decided to take : ");
		labels[6].setText("Operation that acts when the game is almost end with current player losing and decided to take : ");
		labels[7].setText("Operation that acts when the game is almost end with current player losing and decided to pay : ");
		labels[8].setText("Operation that acts when the game is almost end with current player winning and decided to pay : ");
		
		fields[0].setText("+");
		fields[1].setText(String.valueOf(1.0));
		fields[2].setText("-");
		fields[3].setText(String.valueOf(1.0));
		fields[4].setText(String.valueOf(2));
		fields[5].setText(String.valueOf(AI_Bonus.MULTIPLY_ABS));
		fields[6].setText(String.valueOf(AI_Bonus.DIVIDE_ABS));
		fields[7].setText(String.valueOf(AI_Bonus.MULTIPLY_ABS));
		fields[8].setText(String.valueOf(AI_Bonus.DIVIDE_ABS));
		
		nameLabel = new JLabel(set.getLang().getText(Language.NAME));
		nameField = new JTextField(10);
		nameLabel.setForeground(set.getSelected_fore());
		nameField.setBackground(set.getSelected_inside_back());
		nameField.setForeground(set.getSelected_fore());
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setLayout(new FlowLayout());
		title = new JLabel(set.getLang().getText(Language.AI) + " " + set.getLang().getText(Language.EDIT));
		title.setForeground(set.getSelected_fore());
		titlePanel.add(title);
		upControlPanel = new JPanel();
		upControlPanel.setLayout(new FlowLayout());
		upControlPanel.add(nameLabel);
		upControlPanel.add(nameField);
		upPanel.add(upControlPanel, BorderLayout.CENTER);
		if(CalcWindow.usingFont != null)
		{
			nameLabel.setFont(CalcWindow.usingFont);
			nameField.setFont(CalcWindow.usingFont);
			title.setFont(CalcWindow.usingFont);
		}
		if(set.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		upControlPanel.setBackground(set.getSelected_back());
		titlePanel.setBackground(set.getSelected_inside_back());
		
		downPanel.setLayout(new FlowLayout());
		save = new JButton(set.getLang().getText(Language.SAVE));
		close = new JButton(set.getLang().getText(Language.CLOSE));
		edit_script = new JButton(set.getLang().getText(Language.SCRIPT));
		save.setForeground(set.getSelected_fore());
		close.setForeground(set.getSelected_fore());
		edit_script.setForeground(set.getSelected_fore());
		save.addActionListener(this);
		close.addActionListener(this);
		edit_script.addActionListener(this);
		downPanel.add(edit_script);
		downPanel.add(save);
		downPanel.add(close);
		if(CalcWindow.usingFont != null)
		{
			save.setFont(CalcWindow.usingFont);
			close.setFont(CalcWindow.usingFont);
			edit_script.setFont(CalcWindow.usingFont);			
		}
		
		scriptDialog = new JDialog(dialog);
		scriptDialog.setUndecorated(set.isUse_own_titleBar());
		scriptDialog.setTitle(set.getLang().getText(Language.SCRIPT));
		scriptDialog.setSize(500, 400);
		scriptDialog.setLocation((int)(set.getScreenSize().getWidth()/2 - scriptDialog.getWidth()/2), (int)(set.getScreenSize().getHeight()/2 - scriptDialog.getHeight()/2));
		scriptDialog.getContentPane().setLayout(new BorderLayout());
		scriptDialog_mainPanel = new JPanel();
		scriptDialog.getContentPane().add(scriptDialog_mainPanel);
		scriptDialog_mainPanel.setBackground(set.getSelected_back());
		scriptDialog_mainPanel.setBorder(new EtchedBorder());
		scriptDialog_mainPanel.setLayout(new BorderLayout());
		scriptDialog_upPanel = new JPanel();
		scriptDialog_centerPanel = new JPanel();
		scriptDialog_downPanel = new JPanel();
		scriptDialog_upPanel.setBackground(set.getSelected_back());
		scriptDialog_downPanel.setBackground(set.getSelected_back());
		scriptDialog_centerPanel.setBackground(set.getSelected_back());
		scriptDialog_mainPanel.add(scriptDialog_centerPanel, BorderLayout.CENTER);
		scriptDialog_mainPanel.add(scriptDialog_upPanel, BorderLayout.NORTH);
		scriptDialog_mainPanel.add(scriptDialog_downPanel, BorderLayout.SOUTH);
		scriptDialog_upPanel.setLayout(new BorderLayout());
		scriptDialog_downPanel.setLayout(new FlowLayout());
		scriptDialog_enable = new JCheckBox(set.getLang().getText(Language.SCRIPT) + " " + set.getLang().getText(Language.USE));
		scriptDialog_enable.addItemListener(this);
		scriptDialog_titlePanel = new JPanel();
		scriptDialog_enablePanel = new JPanel();
		if(set.isUse_own_titleBar())
		{
			scriptDialog_upPanel.add(scriptDialog_titlePanel, BorderLayout.NORTH);
			scriptDialog_titlePanel.setLayout(new FlowLayout());
			scriptDialog_titlePanel.addMouseListener(this);
			scriptDialog_titlePanel.addMouseMotionListener(this);
			scriptDialog_titlePanel.setBorder(new EtchedBorder());
			scriptDialog_title = new JLabel(set.getLang().getText(Language.SCRIPT));	
			if(CalcWindow.usingFont != null)
				scriptDialog_title.setFont(CalcWindow.usingFont);
			scriptDialog_titlePanel.add(scriptDialog_title);
			scriptDialog_titlePanel.setBackground(set.getSelected_inside_back());
			scriptDialog_title.setForeground(set.getSelected_fore());
		}
		scriptDialog_upPanel.add(scriptDialog_enablePanel, BorderLayout.CENTER);
		scriptDialog_enablePanel.setLayout(new FlowLayout());
		scriptDialog_kind = new JTextField(10);
		scriptDialog_enablePanel.add(scriptDialog_enable);
		scriptDialog_enablePanel.add(scriptDialog_kind);		
		scriptDialog_enablePanel.setBackground(set.getSelected_back());
		scriptDialog_kind.setBackground(set.getSelected_inside_back());
		scriptDialog_kind.setForeground(set.getSelected_fore());
		scriptDialog_enable.setForeground(set.getSelected_fore());
		scriptDialog_enable.setBackground(set.getSelected_back());
		scriptDialog_accept = new JButton(set.getLang().getText(Language.ACCEPT));
		scriptDialog_close = new JButton(set.getLang().getText(Language.CLOSE));
		scriptDialog_accept.setForeground(set.getSelected_fore());
		scriptDialog_close.setForeground(set.getSelected_fore());
		scriptDialog_accept.addActionListener(this);
		scriptDialog_close.addActionListener(this);
		scriptDialog_downPanel.add(scriptDialog_accept);
		scriptDialog_downPanel.add(scriptDialog_close);
		scriptDialog_centerPanel.setLayout(new BorderLayout());
		scriptDialog_scriptPanel = new JTextArea();
		scriptDialog_scriptPanel.setBackground(set.getSelected_inside_back());
		scriptDialog_scriptPanel.setForeground(set.getSelected_fore());
		scriptDialog_scriptScroll = new JScrollPane(scriptDialog_scriptPanel);
		scriptDialog_centerPanel.add(scriptDialog_scriptScroll);
		scriptDialog_scriptPanel.setEditable(scriptDialog_enable.isSelected());
		scriptDialog_accept.setEnabled(scriptDialog_enable.isSelected());
		scriptDialog_kind.setEditable(scriptDialog_enable.isSelected());
		if(CalcWindow.usingFont != null)
		{
			scriptDialog_kind.setFont(CalcWindow.usingFont);
			scriptDialog_accept.setFont(CalcWindow.usingFont);
			scriptDialog_close.setFont(CalcWindow.usingFont);
			scriptDialog_scriptPanel.setFont(CalcWindow.usingFont);
		}
	}
	
	public void exit()
	{
		if(independence) System.exit(0);
		else dialog.setVisible(false);
	}
	
	public void console()
	{
		try
		{			
			String[] orders = new String[9];
			String line = "", reads = "", name = "", path = "";
			InputStreamReader inputReader = new InputStreamReader(System.in);
			BufferedReader bfreader = new BufferedReader(inputReader);
			
			line = "AI Name : ";
			System.out.print(line);
			name = bfreader.readLine();
			
			line = "Operation that acts when the current player\'s turn : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[0] = new String(reads);
			
			line = "Value that be multiplied when the current player\'s turn : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[1] = new String(reads);
			
			line = "Operation that acts when the other player\'s turn : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[2] = new String(reads);
			
			line = "Value that be multiplied when the other player\'s turn : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[3] = new String(reads);
			
			line = "Value that be calculated when the game is almost end : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[4] = new String(reads);
			
			line = "Operation that acts when the game is almost end with current player winning and decided to take : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[5] = new String(reads);
			
			line = "Operation that acts when the game is almost end with current player losing and decided to take : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[6] = new String(reads);
			
			line = "Operation that acts when the game is almost end with current player losing and decided to pay : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[7] = new String(reads);
			
			line = "Operation that acts when the game is almost end with current player winning and decided to pay : ";
			System.out.print(line);
			reads = bfreader.readLine();
			orders[8] = new String(reads);
			
			target = new AI_Scripted_Algorithm();
			target.setName(name);
			target.readOrder(orders);			
			
			message("Success to make");
			System.out.print("Where to save and what name to save : ");
			
			path = bfreader.readLine();
			
			bfreader.close();
			inputReader.close();
			
			if(! (path.endsWith(".cai") || path.endsWith(".CAI")))
			{
				path = path + ".cai";
			}
			target.authorize();
			
			FileOutputStream fout = new FileOutputStream(path);
			XMLEncoder encoder = new XMLEncoder(fout);
			encoder.writeObject(target);
			encoder.close();
			fout.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	private void ai_save(File file) throws Exception
	{
		target = new AI_Scripted_Algorithm();
		String[] orders = new String[panels.length];
		String lines = "";
		for(int i=0; i<panels.length; i++)
		{
			lines = labels[i].getText();
			lines = lines + fields[i].getText();
			orders[i] = lines;
		}
		target.setName(nameField.getText());
		target.readOrder(orders);
		if(scriptDialog_enable.isSelected())
		{
			target.setScript(script);
		}
		else
		{
			target.setScript("");
		}
		target.authorize();
		FileOutputStream fout = new FileOutputStream(file);
		XMLEncoder encoder = new XMLEncoder(fout);
		encoder.writeObject(target);
		encoder.close();
		fout.close();
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
		if(ob == close)
		{
			exit();
		}
		else if(ob == save)
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
				cfd = new JFileChooser(set.getDefault_path());
				cfd.setFileFilter(ff1);
			}
			int cfds = cfd.showSaveDialog(dialog);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd.getSelectedFile();
				String newFileName = newFile.getAbsolutePath();
				try
				{
					if(newFileName.endsWith(".cai") || newFileName.endsWith(".CAI"))
					{
						ai_save(newFile);
					}
					else
					{
						newFileName = newFileName + ".cai";
						newFile = new File(newFileName);
						ai_save(newFile);
					}
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			
		}
		else if(ob == scriptDialog_accept)
		{
			script = scriptDialog_scriptPanel.getText();
			target.setScript(script);
			if(! scriptDialog_kind.getText().trim().equals(""))
				target.setScriptType(scriptDialog_kind.getText());
			scriptDialog.setVisible(false);
		}
		else if(ob == scriptDialog_close)
		{
			scriptDialog_scriptPanel.setText(script);
			scriptDialog.setVisible(false);
		}
		else if(ob == edit_script)
		{
			scriptDialog.setVisible(true);
			scriptDialog_kind.setText(target.getScriptType());
			scriptDialog_scriptPanel.setText(target.getScript());
			if(target.getScript() == null || target.getScript().equals(""))
			{
				scriptDialog_scriptPanel.setText(default_script);
			}
		}
	}
	@Override
	public void open()
	{
		if(gui) dialog.setVisible(true);
		else console();
		
	}
	@Override
	public void message()
	{
		System.out.println();
	}
	@Override
	public void message(String str)
	{
		System.out.println(str);
	}
	@Override
	public void alert(String str)
	{
		message(str);
	}
	@Override
	public void message_bar()
	{
		System.out.println("-----------------------------------------------------------");		
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			dialog.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);		
		}
		else if(ob == scriptDialog_titlePanel)
		{
			scriptDialog.setLocation(e.getXOnScreen() - script_mousex, e.getYOnScreen() - script_mousey);
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
		else if(ob == scriptDialog_titlePanel)
		{
			script_mousex = e.getX();
			script_mousey = e.getY();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == scriptDialog_enable)
		{
			scriptDialog_accept.setEnabled(scriptDialog_enable.isSelected());
			scriptDialog_scriptPanel.setEditable(scriptDialog_enable.isSelected());
			scriptDialog_kind.setEditable(scriptDialog_enable.isSelected());
		}
		
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}	
}
