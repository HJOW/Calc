package main_classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.Setting;

public class User_Selector extends JDialog implements ActionListener, WindowListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -6739467140331946941L;
	public Setting sets;
	public CalcWindow window;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JPanel controlPanel;
	private JButton close;
	private JButton addUser;
	public Vector<User_Block> blocks;
	private Vector<JButton> addListeners;
	private JPanel contentPanel;
	private JScrollPane contentScroll;
	private JDialog addUserDialog;
	private JPanel addUserDialog_mainPanel;
	private JPanel addUserDialog_titlePanel;
	private int addUserDialog_mousex;
	private int addUserDialog_mousey;
	private JPanel addUserDialog_centerPanel;
	private JPanel addUserDialog_downPanel;
	private JButton addUserDialog_add;
	private JButton addUserDialog_close;
	private JPanel addUserDialog_contentPanel;
	private JPanel[] addUserDialog_contentPns;
	private JLabel[] addUserDialog_contentLabels;
	private JTextField[] addUserDialog_contentFields;
	private JLabel addUserDialog_title;
	private JButton unselect;
	
	public User_Selector(Setting sets, CalcWindow window)
	{
		super(window, true);
		this.sets = sets.clone();
		this.window = window;
		blocks = new Vector<User_Block>();
		addListeners = new Vector<JButton>();
		
		
		this.setSize(500, 300);
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.setUndecorated(sets.isUse_own_titleBar());
		
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		this.addWindowListener(this);
		mainPanel.setBorder(new EtchedBorder());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(sets.getSelected_back());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		upPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		//title = new JLabel(set.getLang().getText(Language.VIRTUAL) + " " + set.getLang().getText(Language.BET) + " " + set.getLang().getText(Language.SELECT));
		title = new JLabel(sets.getLang().getText(Language.USER) + " " + sets.getLang().getText(Language.SELECT));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		title.setForeground(sets.getSelected_fore());
		if(sets.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.CENTER);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			titlePanel.setBorder(new EtchedBorder());
			titlePanel.setBackground(sets.getSelected_inside_back());
			titlePanel.setLayout(new FlowLayout());
			titlePanel.add(title);
		}
		
		downPanel.setLayout(new BorderLayout());
		controlPanel = new JPanel();
		downPanel.add(controlPanel, BorderLayout.CENTER);
		controlPanel.setLayout(new FlowLayout());
		controlPanel.setBackground(sets.getSelected_back());
		
		close = new JButton(sets.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			close.setFont(CalcWindow.usingFont);
		close.addActionListener(this);
		close.setForeground(sets.getSelected_fore());
		addUser = new JButton("+");
		if(CalcWindow.usingFont != null)
			addUser.setFont(CalcWindow.usingFont);
		addUser.addActionListener(this);
		addUser.setForeground(sets.getSelected_fore());
		unselect = new JButton(sets.getLang().getText(Language.SELECT) + " " + sets.getLang().getText(Language.RESET));
		if(CalcWindow.usingFont != null)
			unselect.setFont(CalcWindow.usingFont);
		unselect.addActionListener(this);
		unselect.setEnabled(false);
		unselect.setForeground(sets.getSelected_fore());
		controlPanel.add(addUser);
		controlPanel.add(unselect);
		controlPanel.add(close);
		
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPanel.setBackground(sets.getSelected_back());
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		
		addUserDialog = new JDialog(this, true);
		int op_height = 200;
		if(sets.isUse_own_titleBar()) op_height = op_height - 50;
		addUserDialog.setSize(300, op_height);
		addUserDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - addUserDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - addUserDialog.getHeight()/2));
		addUserDialog.setUndecorated(sets.isUse_own_titleBar());
		addUserDialog_mainPanel = new JPanel();
		addUserDialog.getContentPane().setLayout(new BorderLayout());		
		addUserDialog.getContentPane().add(addUserDialog_mainPanel, BorderLayout.CENTER);		
		addUserDialog_mainPanel.setBackground(sets.getSelected_back());
		addUserDialog_mainPanel.setBorder(new EtchedBorder());
		addUserDialog_mainPanel.setLayout(new BorderLayout());
		addUserDialog_titlePanel = new JPanel();
		addUserDialog_centerPanel = new JPanel();
		addUserDialog_downPanel = new JPanel();
		addUserDialog_titlePanel.setLayout(new FlowLayout());
		addUserDialog_centerPanel.setLayout(new BorderLayout());
		addUserDialog_downPanel.setLayout(new FlowLayout());
		if(sets.isUse_own_titleBar())
		{
			addUserDialog_mainPanel.add(addUserDialog_titlePanel, BorderLayout.NORTH);
			addUserDialog_title = new JLabel(sets.getLang().getText(Language.USER) + " " + sets.getLang().getText(Language.ADD));
			if(CalcWindow.usingFont != null)
				addUserDialog_title.setFont(CalcWindow.usingFont);
			addUserDialog_titlePanel.add(addUserDialog_title);
			addUserDialog_titlePanel.addMouseListener(this);
			addUserDialog_titlePanel.addMouseMotionListener(this);
			addUserDialog_titlePanel.setBorder(new EtchedBorder());
			addUserDialog_titlePanel.setBackground(sets.getSelected_inside_back());
			addUserDialog_title.setForeground(sets.getSelected_fore());
		}		
		addUserDialog_centerPanel.setBackground(sets.getSelected_back());
		addUserDialog_downPanel.setBackground(sets.getSelected_back());
		addUserDialog_add = new JButton(sets.getLang().getText(Language.ADD));
		addUserDialog_close = new JButton(sets.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
		{
			addUserDialog_add.setFont(CalcWindow.usingFont);
			addUserDialog_close.setFont(CalcWindow.usingFont);
		}
		addUserDialog_add.setForeground(sets.getSelected_fore());
		addUserDialog_close.setForeground(sets.getSelected_fore());
		addUserDialog_add.addActionListener(this);
		addUserDialog_close.addActionListener(this);
		//addUserDialog_downPanel.setLayout(new FlowLayout());
		addUserDialog_downPanel.add(addUserDialog_add);
		addUserDialog_downPanel.add(addUserDialog_close);
		addUserDialog_contentPanel = new JPanel();
		//addUserDialog_centerPanel.setLayout(new BorderLayout());
		addUserDialog_centerPanel.add(addUserDialog_contentPanel, BorderLayout.CENTER);
		addUserDialog_contentPns = new JPanel[2];
		addUserDialog_contentLabels = new JLabel[addUserDialog_contentPns.length];
		addUserDialog_contentFields = new JTextField[addUserDialog_contentPns.length];
		addUserDialog_contentPanel.setLayout(new GridLayout(addUserDialog_contentPns.length, 1));
		for(int i=0; i<addUserDialog_contentPns.length; i++)
		{
			addUserDialog_contentPns[i] = new JPanel();
			addUserDialog_contentPns[i].setLayout(new FlowLayout());
			addUserDialog_contentLabels[i] = new JLabel();
			addUserDialog_contentFields[i] = new JTextField(10);
			if(CalcWindow.usingFont != null)
			{
				addUserDialog_contentLabels[i].setFont(CalcWindow.usingFont);
				addUserDialog_contentFields[i].setFont(CalcWindow.usingFont);
			}
			addUserDialog_contentPns[i].add(addUserDialog_contentLabels[i]);
			addUserDialog_contentPns[i].add(addUserDialog_contentFields[i]);
			addUserDialog_contentPns[i].setBackground(sets.getSelected_back());
			addUserDialog_contentLabels[i].setForeground(sets.getSelected_fore());
			addUserDialog_contentFields[i].setBackground(sets.getSelected_inside_back());
			addUserDialog_contentFields[i].setForeground(sets.getSelected_fore());
			addUserDialog_contentPanel.add(addUserDialog_contentPns[i]);
		}
		addUserDialog_contentLabels[0].setText(sets.getLang().getText(Language.NAME));
		addUserDialog_contentLabels[1].setText(sets.getLang().getText(Language.PASSWORD));
		addUserDialog_mainPanel.add(addUserDialog_centerPanel, BorderLayout.CENTER);
		addUserDialog_mainPanel.add(addUserDialog_downPanel, BorderLayout.SOUTH);
		
	}
	public void open()
	{
		refresh(true);
	}
	public void refresh(boolean reopen)
	{		
		this.setVisible(false);		
		contentPanel.removeAll();
		for(int i=0; i<addListeners.size(); i++)
		{
			addListeners.get(i).removeActionListener(this);
		}
		addListeners.clear();
		blocks.clear();
		
		if(window.setting.isUser_selected()) unselect.setEnabled(true);
		else unselect.setEnabled(false);
		
		if(window.setting.getUsers() != null)
		{
			for(int i=0; i<window.setting.getUsers().length; i++)
			{
				User_Block newBlock = new User_Block(window.setting.getUsers()[i].getName(), sets);
				blocks.add(newBlock);
			}
			
			if(blocks.size() >= 1)
			{			
				contentPanel.setLayout(new GridLayout(blocks.size(), 1));
				for(int i=0; i<blocks.size(); i++)
				{
					blocks.get(i).removeButton.addActionListener(this);
					blocks.get(i).selectButton.addActionListener(this);
					blocks.get(i).betButton.addActionListener(this);
					addListeners.add(blocks.get(i).removeButton);
					addListeners.add(blocks.get(i).selectButton);
					addListeners.add(blocks.get(i).betButton);
					contentPanel.add(blocks.get(i));
					blocks.get(i).setName(window.setting.getUsers()[i].getName());
					blocks.get(i).setCredit(window.setting.getUsers()[i].getAccumulated_point());
					blocks.get(i).selectButton.setEnabled(true);
				}	
				if(window != null)
				{
					if(window.setting != null)
					{
						if(window.setting.isUser_selected()) blocks.get(window.setting.getNow_user_index()).selectButton.setEnabled(false);
					}
				}				
			}	
		}
		if(reopen) this.setVisible(true);
	}
	public void addUser()
	{
		for(int i=0; i<addUserDialog_contentPns.length; i++)
		{
			addUserDialog_contentFields[i].setText("");
		}
		addUserDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{
			close();
		}
		else if(ob == addUser)
		{
			addUser();
		}
		else if(ob == unselect)
		{
			window.unselectUser();
			for(int i=0; i<blocks.size(); i++)
			{
				blocks.get(i).setSelected(false);
			}
			unselect.setEnabled(false);
		}
		else if(ob == addUserDialog_close)
		{
			addUserDialog.setVisible(false);
		}
		else if(ob == addUserDialog_add)
		{
			User newOne = new User();
			String newName = addUserDialog_contentFields[0].getText();
			String newPassword = addUserDialog_contentFields[1].getText();
			newOne.setName(newName);
			newOne.inputPassword(newPassword);
			newOne.setCredit(1000);
			newOne.setBetting(100);
			window.addUser(newOne);
			addUserDialog.setVisible(false);
			refresh(true);			
		}
		else
		{
			int index = 0;
			boolean betButton = false;
			boolean selectOrRemove = false;
			for(int i=0; i<blocks.size(); i++)
			{				
				if(ob == blocks.get(i).selectButton)
				{
					selectOrRemove = true;
					betButton = false;
					index = i;
					break;
				}
				else if(ob == blocks.get(i).removeButton)
				{
					selectOrRemove = false;
					betButton = false;
					index = i;
					break;
				}
				else if(ob == blocks.get(i).betButton)
				{
					betButton = true;
					index = i;
					break;
				}
			}
			
			String inp_password = JOptionPane.showInputDialog(window.setting.getUsers()[index].getName() + " " + sets.getLang().getText(Language.DESCRIPTIONS + 13));
			boolean accepted = window.setting.getUsers()[index].accept(inp_password);
			if(accepted)
			{
				if(betButton)
				{
					String bet_input = JOptionPane.showInputDialog(sets.getLang().getText(Language.DESCRIPTIONS + 17) + window.setting.getUsers()[index].getBetting());
					try
					{
						long bet_changed = Long.parseLong(bet_input);
						window.setting.getUsers()[index].setBetting(bet_changed);
					}
					catch(NumberFormatException f)
					{
						JOptionPane.showMessageDialog(this, f.getMessage());
					}
				}
				else
				{
					if(selectOrRemove)
					{					
						window.selectUser(index);
						for(int i=0; i<blocks.size(); i++)
						{
							if(i == index) blocks.get(i).setSelected(true);
							else blocks.get(i).setSelected(false);
						}				
						close();				
					}
					else
					{
						contentPanel.removeAll();
						blocks.remove(index);
						window.removeUser(index);
						refresh(true);
					}	
				}				
			}
			else JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.DESCRIPTIONS + 14));				
		}
	}
	public void off()
	{
		window = null;
	}

	public synchronized void close()
	{
		this.setVisible(false);
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel) this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);
		else if(ob == addUserDialog_titlePanel) addUserDialog.setLocation(e.getXOnScreen() - addUserDialog_mousex, e.getYOnScreen() - addUserDialog_mousey);
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
		else if(ob == addUserDialog_titlePanel)
		{
			addUserDialog_mousex = e.getX();
			addUserDialog_mousey = e.getY();
		}
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
		close();
		
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
