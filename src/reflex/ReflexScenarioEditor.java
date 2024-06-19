package reflex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import lang.Language;
import main_classes.CalcWindow;
import main_classes.Openable;
import setting.Setting;

public class ReflexScenarioEditor extends JDialog implements Openable, ActionListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1940512597716306791L;
	private Setting sets;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private JTextArea contents;
	private JScrollPane contentScroll;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem menu_file_exit;
	private JMenuItem menu_file_save;
	private JMenuItem menu_file_load;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JFileChooser fileChooser;
	private FileFilter fileFilter;
	private JMenuItem menu_file_auth;
	private Reflexioner sp;
	private JButton bt_play;
	private JButton bt_add;
	private JButton bt_close;
	private int mouse_x = 0;
	private int mouse_y = 0;
	
	public ReflexScenarioEditor()
	{
		super();
	}
	public ReflexScenarioEditor(Setting sets)
	{
		super();
		this.sets = sets;
		if(this.sets == null) this.sets = Setting.getNewInstance();
		init();
	}
	public ReflexScenarioEditor(JDialog upper)
	{
		this(upper, null, null);
	}
	public ReflexScenarioEditor(JDialog upper, Reflexioner reflex)
	{
		this(upper, null, reflex);
	}
	public ReflexScenarioEditor(JDialog upper, Setting sets)
	{
		this(upper, sets, null);
	}
	public ReflexScenarioEditor(JDialog upper, Setting sets, Reflexioner reflex)
	{
		super(upper);
		sp = reflex;
		this.sets = sets;
		if(this.sets == null) this.sets = Setting.getNewInstance();
		init();
	}
	private void init()
	{
		setUndecorated(true);
		setSize(620, 410);
		setLocation((int)(sets.getScreenSize().getWidth()/2 - getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - getHeight()/2));
		getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(sets.getSelected_back());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EtchedBorder());
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerPanel = new JPanel();
		upPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		titlePanel = new JPanel();
		titlePanel.addMouseListener(this);
		titlePanel.addMouseMotionListener(this);
		titlePanel.setBackground(sets.getSelected_inside_back());
		titlePanel.setBorder(new EtchedBorder());
		titlePanel.setLayout(new FlowLayout());
		titleLabel = new JLabel(sets.getLang().getText(Language.SCENARIO) + " " + sets.getLang().getText(Language.EDIT));
		titleLabel.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			titleLabel.setFont(CalcWindow.usingFont);
		}
		titlePanel.add(titleLabel);
		
		centerPanel.setLayout(new BorderLayout());
		contents = new JTextArea();
		contents.setLineWrap(true);
		contentScroll = new JScrollPane(contents);
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		centerPanel.setBackground(sets.getSelected_back());
		contents.setBackground(sets.getSelected_inside_back());
		contents.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			contents.setFont(CalcWindow.usingFont);
		}
		
		upPanel.setLayout(new BorderLayout());
		upPanel.add(titlePanel, BorderLayout.CENTER);
		
		downPanel.setLayout(new BorderLayout());
		
		bt_play = new JButton(sets.getLang().getText(Language.PLAY));
		bt_play.addActionListener(this);
		bt_play.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			bt_play.setFont(CalcWindow.usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_play.setBackground(sets.getSelected_back());
		}
		downPanel.add(bt_play, BorderLayout.CENTER);
		if(sp == null) bt_play.setVisible(false);
		
				
		bt_add = new JButton(sets.getLang().getText(Language.ADD));
		bt_add.addActionListener(this);
		bt_add.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			bt_add.setFont(CalcWindow.usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_add.setBackground(sets.getSelected_back());
		}
		downPanel.add(bt_add, BorderLayout.WEST);
		if(sp == null) bt_add.setVisible(false);
		
		bt_close = new JButton(sets.getLang().getText(Language.CLOSE));
		bt_close.addActionListener(this);
		bt_close.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			bt_close.setFont(CalcWindow.usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_close.setBackground(sets.getSelected_back());
		}
		downPanel.add(bt_close, BorderLayout.EAST);
		
		menuBar = new JMenuBar();
		upPanel.add(menuBar, BorderLayout.SOUTH);
		menuBar.setBackground(sets.getSelected_inside_back());
		menuBar.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menuBar.setFont(CalcWindow.usingFont);
		}
		
		menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		menu_file.setBackground(sets.getSelected_inside_back());
		menu_file.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file.setFont(CalcWindow.usingFont);
		}
		menuBar.add(menu_file);
		
		menu_file_auth = new JMenuItem(sets.getLang().getText(Language.AUTHORITY));
		menu_file_auth.addActionListener(this);
		menu_file_auth.setBackground(sets.getSelected_back());
		menu_file_auth.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_auth.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_auth);
		
		menu_file_save = new JMenuItem(sets.getLang().getText(Language.SAVE));
		menu_file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));	
		menu_file_save.addActionListener(this);
		menu_file_save.setBackground(sets.getSelected_back());
		menu_file_save.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_save.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_save);
		
		menu_file_load = new JMenuItem(sets.getLang().getText(Language.LOAD));
		menu_file_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));	
		menu_file_load.addActionListener(this);
		menu_file_load.setBackground(sets.getSelected_back());
		menu_file_load.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_load.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_load);
		
		menu_file_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		menu_file_exit.addActionListener(this);
		menu_file_exit.setBackground(sets.getSelected_back());
		menu_file_exit.setForeground(sets.getSelected_fore());
		if(CalcWindow.usingFont != null)
		{
			menu_file_exit.setFont(CalcWindow.usingFont);
		}
		menu_file.add(menu_file_exit);
	}
	private void prepareFileChooser()
	{
		if(fileChooser == null)
			fileChooser = new JFileChooser(new File(sets.getDefault_path()));
		if(CalcWindow.usingFont != null)
		{
			CalcWindow.setFontRecursively(fileChooser, CalcWindow.usingFont);
		}
		if(fileFilter == null)
		{
			fileFilter = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rfst")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Scenario Script (.rfst)";
				}
			};
		}
		
		fileChooser.setFileFilter(fileFilter);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == menu_file_load)
		{
			load();
		}
		else if(ob == menu_file_save)
		{
			save();
		}
		else if(ob == bt_play)
		{
			sp.playScenario(new DReflexScenario(contents.getText()));
		}
		else if(ob == bt_add)
		{
			sp.inputScenario(new DReflexScenario(contents.getText()));
		}
		else if(ob == bt_close)
		{
			exit();
		}
		else if(ob == menu_file_auth)
		{
			int values = 0;
			try
			{
				String gets = JOptionPane.showInputDialog(this, sets.getLang().getText(Language.AUTHORITY));
				if(gets != null)
				{
					values = Integer.parseInt(gets);				
					BReflexScenario bf = new DReflexScenario(contents.getText());
					bf.authorized(values);
					contents.setText(bf.stringData());
				}
				else
				{
					JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + sets.getLang().getText(Language.NONE));
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
	}
	private void saveScenario(File file, boolean xml)
	{
		FileOutputStream stream = null;
		OutputStreamWriter writer = null;
		BufferedWriter buffered = null;
		XMLEncoder encoder = null;
		try
		{
			stream = new FileOutputStream(file);
			if(xml)
			{
				encoder = new XMLEncoder(stream);
				encoder.writeObject(new DReflexScenario(contents.getText()));
			}
			else
			{
				writer = new OutputStreamWriter(stream);
				buffered = new BufferedWriter(writer);
				buffered.write(contents.getText());
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				encoder.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				buffered.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				writer.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (IOException e)
			{
				
			}
		}
	}
	private void loadScenario(File file, boolean xml)
	{
		FileInputStream stream = null;
		InputStreamReader reader = null;
		BufferedReader buffered = null;
		XMLDecoder decoder = null;
		String lines = "", accum = "";
		try
		{
			stream = new FileInputStream(file);
			if(xml)
			{
				decoder = new XMLDecoder(stream);
				setScenario((AReflexScenario) decoder.readObject());
			}
			else
			{
				reader = new InputStreamReader(stream);
				buffered = new BufferedReader(reader);
				while(true)
				{
					lines = buffered.readLine();
					if(lines == null) break;
					accum = accum + lines + "\n";
				}
				setScenario(new DReflexScenario(accum));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				decoder.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				buffered.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				reader.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (IOException e)
			{
				
			}
		}
	}
	public void save()
	{
		prepareFileChooser();
		
		File selectedFile = null;
		if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			selectedFile = fileChooser.getSelectedFile();
			if(selectedFile.getAbsolutePath().endsWith(".rfst") || selectedFile.getAbsolutePath().endsWith(".RFST") || selectedFile.getAbsolutePath().endsWith(".Rfst"))
			{
				saveScenario(selectedFile, false);
			}
			else if(selectedFile.getAbsolutePath().endsWith(".rfsx") || selectedFile.getAbsolutePath().endsWith(".RFSX") || selectedFile.getAbsolutePath().endsWith(".Rfsx"))
			{
				saveScenario(selectedFile, true);
			}
			else
			{
				selectedFile = new File(selectedFile.getAbsolutePath() + ".rfst");
				saveScenario(selectedFile, false);
			}
		}		
	}
	public void load()
	{
		prepareFileChooser();
		
		File selectedFile = null;
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			selectedFile = fileChooser.getSelectedFile();
			if(selectedFile.getAbsolutePath().endsWith(".rfst") || selectedFile.getAbsolutePath().endsWith(".RFST") || selectedFile.getAbsolutePath().endsWith(".Rfst"))
			{
				loadScenario(selectedFile, false);
			}
			else if(selectedFile.getAbsolutePath().endsWith(".rfsx") || selectedFile.getAbsolutePath().endsWith(".RFSX") || selectedFile.getAbsolutePath().endsWith(".Rfsx"))
			{
				loadScenario(selectedFile, true);
			}
			else
			{
				selectedFile = new File(selectedFile.getAbsolutePath() + ".rfst");
				if(selectedFile.exists())
				{
					loadScenario(selectedFile, false);
				}
				else
				{
					selectedFile = new File(selectedFile.getAbsolutePath() + ".rfsx");
					if(selectedFile.exists())
					{
						loadScenario(selectedFile, true);
					}
					else
					{
						JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + sets.getLang().getText(Language.NONE));
					}
				}
			}
		}		
	}
	@Override
	public void open()
	{
		this.setVisible(true);
		
	}
	@Override
	public void exit()
	{
		this.setVisible(false);
		
	}
	public void setScenario(AReflexScenario scen)
	{
		contents.setText(scen.stringData());
	}
	public AReflexScenario toScenario() throws Exception
	{
		return new DReflexScenario(contents.getText());
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			setLocation(e.getXOnScreen() - mouse_x, e.getYOnScreen() - mouse_y);
		}		
	}
	@Override
	public void mouseMoved(MouseEvent arg0)
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
			mouse_x = e.getX();
			mouse_y = e.getY();
		}		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
