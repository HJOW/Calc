package main_classes;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.Setting;

public class Help extends JDialog implements ActionListener, WindowListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -1619999356014139490L;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JEditorPane area;
	private JScrollPane area_scroll;
	private JButton close;
	private JPanel upPanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JButton gotoweb;
	private Setting sets;
	public Help(Setting sets, JDialog sup)
	{
		super(sup);
		prepare(sets);
	}
	public Help(Setting sets, JFrame sup)
	{
		super(sup);
		prepare(sets);
	}
	public void prepare(Setting sets)
	{
		CalcWindow.prepareFont();
		this.addWindowListener(this);
		this.setSize(550, 400);
		if(sets.isUse_own_titleBar()) this.setUndecorated(true);
		else this.setTitle(sets.getLang().getText(Language.HELP));
		if(sets.getScreenSize() == null)
		{
			sets.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
		}
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.sets = sets;
		
		mainPanel = new JPanel();
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		upPanel.setBorder(new EtchedBorder());
		centerPanel.setBorder(new EtchedBorder());
		downPanel.setBorder(new EtchedBorder());
		upPanel.addMouseListener(this);
		upPanel.addMouseMotionListener(this);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		area = new JEditorPane();
		area_scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//area.setLineWrap(true);
		area.setEditable(false);
		
		try
		{
			String loc = System.getProperty("user.language");
			if(loc.equalsIgnoreCase("ko") || loc.equalsIgnoreCase("kr") || loc.equalsIgnoreCase("kor") || loc.equalsIgnoreCase("korean"))
			{
				area.setPage(sets.getNotice_url() + "howtoplay_kor.htm");
			}
			else
			{
				area.setPage(sets.getNotice_url() + "howtoplay.htm");
			}
		}
		catch(Exception e)
		{
			area.setText(sets.getLang().getText(Language.HELP_CONTENT));
		}
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(area_scroll);
		gotoweb = new JButton(sets.getLang().getText(Language.VIEW) + " " + sets.getLang().getText(Language.HOMEPAGE));
		gotoweb.addActionListener(this);
		if(CalcWindow.usingFont != null)
			gotoweb.setFont(CalcWindow.usingFont);
		close = new JButton(sets.getLang().getText(Language.CLOSE));
		close.addActionListener(this);
		if(CalcWindow.usingFont != null)
			close.setFont(CalcWindow.usingFont);
		downPanel.setLayout(new FlowLayout());
		downPanel.add(gotoweb);
		downPanel.add(close);
		gotoweb.setVisible(Desktop.isDesktopSupported());
		
		title = new JLabel(sets.getLang().getText(Language.TITLE) + " " + sets.getLang().getText(Language.HELP));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		upPanel.setLayout(new FlowLayout());
		upPanel.add(title);
		mainPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		upPanel.setBackground(sets.getSelected_inside_back());
		area.setBackground(sets.getSelected_back());
		area.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
			area.setFont(CalcWindow.usingFont);
		title.setForeground(sets.getSelected_fore());
		gotoweb.setForeground(sets.getSelected_fore());
		close.setForeground(sets.getSelected_fore());
		area.setCaretPosition(0);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{
			this.setVisible(false);
		}
		else if(ob == gotoweb)
		{			
			try
			{
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(sets.getNotice_url() + "howtoplay_kor.htm"));
			}
			catch(Exception e2)
			{
				gotoweb.setEnabled(false);
			}
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
	public void windowClosing(WindowEvent e)
	{
		this.setVisible(true);
		
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
		mousex = e.getX();
		mousey = e.getY();		
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
