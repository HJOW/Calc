package main_classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

public class NoticeDialog extends JDialog implements ActionListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 3381908815902927126L;
	public Setting setting;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JLabel title;
	private JEditorPane contents;
	private JScrollPane contentScroll;
	private JButton close;
	private int mousex;
	private int mousey;
	public NoticeDialog(JFrame bef, Setting set, String url)
	{
		super(bef, true);
		this.setting = set.clone();
		init(url);
	}
	public void init(String url)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(600, 400);
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EtchedBorder());
		
		centerPanel = new JPanel();
		upPanel = new JPanel();
		downPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		upPanel.add(titlePanel, BorderLayout.CENTER);
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setLayout(new FlowLayout());
		title = new JLabel(setting.getLang().getText(Language.NOTICE));
		titlePanel.add(title);
		titlePanel.addMouseListener(this);
		titlePanel.addMouseMotionListener(this);
		upPanel.setBackground(setting.getSelected_inside_back());
		titlePanel.setBackground(setting.getSelected_inside_back());
		title.setForeground(setting.getSelected_fore());
		
		centerPanel.setLayout(new BorderLayout());
		contents = new JEditorPane();
		contents.setEditable(false);
		contentScroll = new JScrollPane(contents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		contents.setBackground(setting.getSelected_back());
		contents.setForeground(setting.getSelected_fore());
		
		downPanel.setLayout(new FlowLayout());
		close = new JButton(setting.getLang().getText(Language.CLOSE));
		close.addActionListener(this);
		close.setForeground(setting.getSelected_fore());
		downPanel.add(close);
		downPanel.setBackground(setting.getSelected_back());
	}
	public void setPage(String url) throws Exception
	{
		contents.setPage(url);
	}
	public void open()
	{
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close) this.setVisible(false);
		
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
