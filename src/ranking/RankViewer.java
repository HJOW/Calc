package ranking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import lang.Language;
import main_classes.CalcWindow;
import setting.Setting;

public class RankViewer extends JDialog implements ActionListener, MouseListener, MouseMotionListener, WindowListener
{
	private static final long serialVersionUID = -7489537074521469705L;

	public Setting setting;
	public Color unselected_back, selected_back, unselected_inside_back, selected_inside_back, unselected_fore, selected_fore;
	private Dimension screenSize;

	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JPanel contentPanel;
	private JPanel[] contentPns;

	private JScrollPane contentScroll;

	private int mousex;

	private int mousey;

	private JButton close;

	private JLabel title;

	public RankViewer(JDialog dialog, Setting setting, Color unselected_back, Color selected_back, Color unselected_inside_back, Color selected_inside_back, Color unselected_fore, Color selected_fore)
	{
		super(dialog, false);
		this.setting = setting;
		this.setSize(500, 400);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - (this.getHeight() + 40)/2 - 10));
		this.setUndecorated(setting.isUse_own_titleBar());
		if(! setting.isUse_own_titleBar()) this.addWindowListener(this);
		
		this.unselected_back = unselected_back;
		this.unselected_fore = unselected_fore;
		this.unselected_inside_back = unselected_inside_back;
		this.selected_back = selected_back;
		this.selected_fore = selected_fore;
		this.selected_inside_back = selected_inside_back;
		
		this.setLayout(new BorderLayout());
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setBorder(new EtchedBorder());
		mainPanel.setLayout(new BorderLayout());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		if(setting.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}		
		titlePanel.setBorder(new EtchedBorder());
		title = new JLabel(setting.getLang().getText(Language.RANKING));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		titlePanel.add(title);
		
		contentPanel = new JPanel();
		contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(contentScroll);		
		
		downPanel.setLayout(new FlowLayout());
		close = new JButton(setting.getLang().getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			close.setFont(CalcWindow.usingFont);
		close.addActionListener(this);
		downPanel.add(close);		
		
		refresh();
	}
	public void refresh()
	{
		mainPanel.setBackground(selected_back);
		upPanel.setBackground(selected_back);
		centerPanel.setBackground(selected_back);
		downPanel.setBackground(selected_back);
		titlePanel.setBackground(selected_inside_back);	
		title.setForeground(selected_fore);
		close.setForeground(selected_fore);
		
		contentPanel.removeAll();
		if(setting.isUser_selected())
		{
			if(setting.getUsers() != null)
			{
				if(setting.getUsers()[setting.getNow_user_index()].getRanks() != null)
				{
					if(setting.getUsers()[setting.getNow_user_index()].getRanks().length >= 1)
					{
						int table_col = setting.getUsers()[setting.getNow_user_index()].getRanks().length;
						int table_min_col = 4;
						int table_layout = table_col;
						if(table_layout < table_min_col) table_layout = table_min_col;
						contentPanel.setLayout(new GridLayout(table_layout + 1, 1));		
						JPanel table_label = new JPanel();		
						JPanel[] table_labels = new JPanel[4];
						JLabel[] table_label_text = new JLabel[table_labels.length];
						table_label.setLayout(new GridLayout(1, table_labels.length));
						for(int i=0; i<table_labels.length; i++)
						{
							table_labels[i] = new JPanel();
							table_labels[i].setLayout(new FlowLayout());
							table_label_text[i] = new JLabel();
							if(CalcWindow.usingFont != null)
								table_label_text[i].setFont(CalcWindow.usingFont);
							table_labels[i].add(table_label_text[i]);
							table_label.add(table_labels[i]);
							table_labels[i].setBackground(selected_back);
							table_label_text[i].setForeground(selected_fore);
						}
						table_label_text[0].setText(setting.getLang().getText(Language.NAME));
						table_label_text[1].setText(setting.getLang().getText(Language.POINT));
						table_label_text[2].setText(setting.getLang().getText(Language.DATE));
						table_label_text[3].setText(setting.getLang().getText(Language.VERSION));
						contentPanel.add(table_label);
						contentPns = new JPanel[table_col];
						for(int i=0; i<table_col; i++)
						{
							contentPns[i] = setting.getUsers()[setting.getNow_user_index()].getRanks()[i].getTableColumn(selected_back, selected_fore, selected_inside_back);
							contentPanel.add(contentPns[i]);
						}
						for(int i=0; i<(table_min_col - table_col); i++)
						{
							JPanel empties = new JPanel();
							empties.setBackground(selected_back);
							contentPanel.add(empties);
						}
					}
				}				
			}
		}
		else
		{
			if(setting.getRanks() != null)
			{
				if(setting.getRanks().length >= 1)
				{
					int table_col = setting.getRanks().length;
					int table_min_col = 4;
					int table_layout = table_col;
					if(table_layout < table_min_col) table_layout = table_min_col;
					contentPanel.setLayout(new GridLayout(table_layout + 1, 1));		
					JPanel table_label = new JPanel();		
					JPanel[] table_labels = new JPanel[4];
					JLabel[] table_label_text = new JLabel[table_labels.length];
					table_label.setLayout(new GridLayout(1, table_labels.length));
					for(int i=0; i<table_labels.length; i++)
					{
						table_labels[i] = new JPanel();
						table_labels[i].setLayout(new FlowLayout());
						table_label_text[i] = new JLabel();
						if(CalcWindow.usingFont != null)
							table_label_text[i].setFont(CalcWindow.usingFont);
						table_labels[i].add(table_label_text[i]);
						table_label.add(table_labels[i]);
						table_labels[i].setBackground(selected_back);
						table_label_text[i].setForeground(selected_fore);
					}
					table_label_text[0].setText(setting.getLang().getText(Language.NAME));
					table_label_text[1].setText(setting.getLang().getText(Language.POINT));
					table_label_text[2].setText(setting.getLang().getText(Language.DATE));
					table_label_text[3].setText(setting.getLang().getText(Language.VERSION));
					contentPanel.add(table_label);
					contentPns = new JPanel[table_col];
					for(int i=0; i<table_col; i++)
					{
						contentPns[i] = setting.getRanks()[i].getTableColumn(selected_back, selected_fore, selected_inside_back);
						contentPanel.add(contentPns[i]);
					}
					for(int i=0; i<(table_min_col - table_col); i++)
					{
						JPanel empties = new JPanel();
						empties.setBackground(selected_back);
						contentPanel.add(empties);
					}
				}
			}
		}
		contentPanel.setBackground(selected_back);
		
	}
	public void open()
	{
		refresh();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{			
			this.setVisible(false);
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
		this.setVisible(false);
		
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
