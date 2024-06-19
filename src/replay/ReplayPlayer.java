package replay;

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
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import setting.Lint;
import lang.Language;
import main_classes.CalcWindow;
import main_classes.StandardCard;

public class ReplayPlayer extends JDialog implements ActionListener, WindowListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 7058416957576326730L;
	public boolean loaded = false;
	public Replay loaded_replay;
	private FileFilter ff1;
	private JFileChooser cfd;
	private CalcWindow window;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel contentPanel;
	private JPanel titlePanel;
	private JLabel title;
	private JButton close;
	private JScrollPane contentScroll;
	private JPanel controlPanel;
	private JButton reset;
	private JButton play;
	private JButton load;
	private int progress = 0;
	private int mousex;
	private int mousey;
	private JButton open_area;
	private JDialog areaDialog;
	private JPanel areaDialog_mainPanel;
	private JTextArea area;
	private JScrollPane area_scroll;
	private JPanel areaDialog_centerPanel;
	private JPanel areaDialog_downPanel;
	private JButton areaDialog_close;
	private JCheckBox authorized;
	private JButton close2;
	private JProgressBar progressBar;
	private JPanel areaDialog_upPanel;
	private JLabel areaDialog_title;
	private JPanel areaDialog_titlePanel;
	private int area_mousex;
	private int area_mousey;
	private boolean areaDialog_moved = false;
	private FileFilter ff2;
	
	public ReplayPlayer(CalcWindow window, JDialog dialog)
	{
		super(dialog, false);
		this.window = window;
		this.setSize(600, 400);
		this.setLocation((int)(window.screenSize.getWidth()/2 - this.getWidth()/2), (int)(window.screenSize.getHeight()/2 - this.getHeight()/2));
		if(window.setting.isUse_own_titleBar()) this.setUndecorated(true);
		else this.addWindowListener(this);
		
		CalcWindow.prepareFont();
		
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		mainPanel.setBorder(new EtchedBorder());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titlePanel.setBorder(new EtchedBorder());
		if(window.setting.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.CENTER);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
		}
		titlePanel.setLayout(new FlowLayout());
		title = new JLabel(window.lang.getText(Language.REPLAY) + " " + window.lang.getText(Language.VIEW));
		if(CalcWindow.usingFont != null)
			title.setFont(CalcWindow.usingFont);
		titlePanel.add(title);
		close = new JButton(window.lang.getText(Language.X));
		if(CalcWindow.usingFont != null)
			close.setFont(CalcWindow.usingFont);
		close.addActionListener(this);
		open_area = new JButton(window.lang.getText(Language.LOG) + " " + window.lang.getText(Language.VIEW));
		if(CalcWindow.usingFont != null)
			open_area.setFont(CalcWindow.usingFont);
		open_area.addActionListener(this);
		if(window.setting.isUse_own_titleBar()) upPanel.add(close, BorderLayout.EAST);
		
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(contentScroll, BorderLayout.CENTER);
		
		downPanel.setLayout(new BorderLayout());
		controlPanel = new JPanel();
		downPanel.add(controlPanel, BorderLayout.CENTER);
		controlPanel.setLayout(new FlowLayout());
		authorized = new JCheckBox(window.lang.getText(Language.AUTHORITY));
		authorized.setEnabled(false);
		reset = new JButton(window.lang.getText(Language.RESET));
		play = new JButton("▶");
		load = new JButton(window.lang.getText(Language.LOAD));		
		close2 = new JButton(window.lang.getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
		{
			authorized.setFont(CalcWindow.usingFont);
			reset.setFont(CalcWindow.usingFont);
			play.setFont(CalcWindow.usingFont);
			load.setFont(CalcWindow.usingFont);
			close2.setFont(CalcWindow.usingFont);
		}
		reset.addActionListener(this);
		play.addActionListener(this);
		load.addActionListener(this);
		close2.addActionListener(this);
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setStringPainted(true);
		progressBar.setString("0 / 0");
		if(CalcWindow.usingFont != null)
			progressBar.setFont(CalcWindow.usingFont);
		controlPanel.add(progressBar);
		controlPanel.add(authorized);
		controlPanel.add(play);
		controlPanel.add(reset);
		controlPanel.add(load);
		controlPanel.add(open_area);
		controlPanel.add(close2);
		reset.setEnabled(false);
		play.setEnabled(false);
		
		areaDialog = new JDialog(this, false);
		//areaDialog.setSize(400, 300);
		//areaDialog.setLocation((int)(window.screenSize.getWidth()/2 - areaDialog.getWidth()/2), (int)(window.screenSize.getHeight()/2 - areaDialog.getHeight()/2));
		areaDialog.setSize(this.getWidth(), 200);
		areaDialog.setLocation((int)(this.getLocation().getX()), (int)(this.getLocation().getY()) + this.getHeight());
		if(window.setting.isUse_own_titleBar())
		{
			areaDialog.setUndecorated(true);
		}
		else
		{
			areaDialog.setTitle(window.lang.getText(Language.REPLAY) + " " + window.lang.getText(Language.LOG) + " " + window.lang.getText(Language.VIEW));
		}
		areaDialog.setLayout(new BorderLayout());
		areaDialog_mainPanel = new JPanel();
		areaDialog.getContentPane().add(areaDialog_mainPanel);
		area = new JTextArea();
		if(CalcWindow.usingFont != null)
			area.setFont(CalcWindow.usingFont);
		area_scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		area.setEditable(false);
		area.setLineWrap(true);
		areaDialog_centerPanel = new JPanel();
		areaDialog_downPanel = new JPanel();
		areaDialog_upPanel = new JPanel();
		areaDialog_mainPanel.setLayout(new BorderLayout());
		areaDialog_mainPanel.setBorder(new EtchedBorder());
		areaDialog_mainPanel.add(areaDialog_centerPanel, BorderLayout.CENTER);
		areaDialog_mainPanel.add(areaDialog_downPanel, BorderLayout.SOUTH);
		if(window.setting.isUse_own_titleBar()) areaDialog_mainPanel.add(areaDialog_upPanel, BorderLayout.NORTH);
		areaDialog_centerPanel.setLayout(new BorderLayout());
		areaDialog_centerPanel.add(area_scroll);
		areaDialog_downPanel.setLayout(new FlowLayout());
		areaDialog_close = new JButton(window.lang.getText(Language.CLOSE));
		if(CalcWindow.usingFont != null)
			areaDialog_close.setFont(CalcWindow.usingFont);
		areaDialog_close.addActionListener(this);
		areaDialog_downPanel.add(areaDialog_close);
		areaDialog_upPanel.setLayout(new BorderLayout());
		areaDialog_titlePanel = new JPanel();
		areaDialog_upPanel.add(areaDialog_titlePanel, BorderLayout.CENTER);
		areaDialog_titlePanel.setLayout(new FlowLayout());
		areaDialog_titlePanel.setBorder(new EtchedBorder());
		areaDialog_title = new JLabel(window.lang.getText(Language.REPLAY) + " " + window.lang.getText(Language.LOG) + " " + window.lang.getText(Language.VIEW));
		if(CalcWindow.usingFont != null)
			areaDialog_title.setFont(CalcWindow.usingFont);
		areaDialog_titlePanel.add(areaDialog_title);
		if(window.setting.isUse_own_titleBar())
		{
			areaDialog_titlePanel.addMouseListener(this);
			areaDialog_titlePanel.addMouseMotionListener(this);
		}
		theme_refresh();
		this.setVisible(true);
		areaDialog.setVisible(true);
	}
	public void theme_refresh()
	{
		if(window.setting.isUse_color())
		{
			upPanel.setBackground(window.selected_back);
			titlePanel.setBackground(window.selected_inside_back);
			centerPanel.setBackground(window.selected_back);
			downPanel.setBackground(window.selected_back);
			controlPanel.setBackground(window.selected_back);
			contentPanel.setBackground(window.selected_back);
			authorized.setBackground(window.selected_back);
			authorized.setForeground(window.selected_fore);
			area.setBackground(window.selected_inside_back);
			area.setForeground(window.selected_fore);
			areaDialog_downPanel.setBackground(window.selected_back);
			areaDialog_upPanel.setBackground(window.selected_back);
			areaDialog_titlePanel.setBackground(window.selected_inside_back);
			areaDialog_title.setForeground(window.selected_fore);
			title.setForeground(window.selected_fore);
			reset.setForeground(window.selected_fore);
			play.setForeground(window.selected_fore);
			load.setForeground(window.selected_fore);
			close.setForeground(window.selected_fore);
			close2.setForeground(window.selected_fore);
			open_area.setForeground(window.selected_fore);
			areaDialog_close.setForeground(window.selected_fore);
		}
	}
	public void load()
	{
		ff1 = new FileFilter()
		{
			@Override
			public boolean accept(File file1)
			{
				if(file1 != null)
				{
					if(file1.isDirectory()) return true;
					if(file1.getAbsolutePath().endsWith(".crep")) return true;
				}
				return false;
			}
			@Override
			public String getDescription()
			{					
				return "Replay (.crep)";
			}
		};
		ff2 = new FileFilter()
		{
			@Override
			public boolean accept(File file1)
			{
				if(file1 != null)
				{
					if(file1.isDirectory()) return true;
					if(file1.getAbsolutePath().endsWith(".crex")) return true;
				}
				return false;
			}
			@Override
			public String getDescription()
			{					
				return "Replay XML (.crex)";
			}
		};
		if(cfd == null) 
		{
			cfd = new JFileChooser(window.setting.getDefault_path());
			cfd.setFileFilter(ff1);
			cfd.addChoosableFileFilter(ff2);
		}
		int cfds = cfd.showOpenDialog(this);
		if(cfds == JFileChooser.APPROVE_OPTION)
		{
			File newFile = cfd.getSelectedFile();
			String fileName = newFile.getAbsolutePath();
			try
			{
				FileInputStream fin = new FileInputStream(newFile);
				if(fileName.endsWith(".crex") || fileName.endsWith(".CREX"))
				{
					XMLDecoder decoder = new XMLDecoder(fin);
					loaded_replay = (Replay) decoder.readObject();
					loaded_replay.objectToWrapper();
					decoder.close();
				}
				else
				{
					ObjectInputStream ob = new ObjectInputStream(fin);
					loaded_replay = (Replay) ob.readObject();
					ob.close();
				}
				load_refresh();
				fin.close();
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
				message(e.getMessage());
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				message(e.getMessage());
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				message(e.getMessage());
			}
		}
	}
	private void load_refresh()
	{
		this.setVisible(false);		
		contentPanel.removeAll();
		loaded_replay.reset();
		contentPanel.setLayout(new GridLayout(1, loaded_replay.getBlocks().length));
		for(int i=0; i<loaded_replay.getBlocks().length; i++)
		{
			contentPanel.add(loaded_replay.getBlocks()[i]);	
			loaded_replay.getBlocks()[i].init(window);
			loaded_replay.getBlocks()[i].getPay().setEnabled(false);
			loaded_replay.getBlocks()[i].setWindow(window);
			loaded_replay.getBlocks()[i].setReplay_play(true);
		}
		
		/*
		for(int i=0; i<loaded_replay.first_blocks.length; i++)
		{
			System.out.println("first_blocks["+ i + "].owns.size() : " + loaded_replay.first_blocks[i].owns.size());
			for(int j=0; j<loaded_replay.first_blocks[i].owns.size(); j++)
				System.out.println(i + ", " + j + " : " + loaded_replay.first_blocks[i].owns.get(j));		
		}*/
		
		
		play.setEnabled(true);		
		reset.setEnabled(true);
		progress = 0;		
		
		if(loaded_replay.isAuthority())
		{
			BigInteger code = loaded_replay.getCode();
			BigInteger code2 = new BigInteger("0");
			boolean control_authorized = true;
			long special_code = 0;
			// authority_code used		
			if(loaded_replay.getVersion_main() == CalcWindow.version_main && loaded_replay.getVersion_sub1() == CalcWindow.version_sub_1 && loaded_replay.getVersion_sub2() == CalcWindow.version_sub_2) special_code = CalcWindow.getAuthorizedCode(2938291);
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 6 && loaded_replay.getVersion_sub2() == 6) special_code = 2848194;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 6 && loaded_replay.getVersion_sub2() == 7) special_code = 1928392;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 6 && loaded_replay.getVersion_sub2() == 8) special_code = 6927349;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 6 && loaded_replay.getVersion_sub2() == 9) special_code = 8294711;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 0) special_code = 9271827;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 1) special_code = 1927492;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 2) special_code = 4928392;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 3) special_code = 7261827;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 4) special_code = 9279372;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 5) special_code = 6284373;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 6) special_code = 8293784;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 7) special_code = 7928491;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 8) special_code = 6284719;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 7 && loaded_replay.getVersion_sub2() == 9) special_code = 3817392;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 0) special_code = 8492832;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 1) special_code = 7264892;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 2) special_code = 3159715;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 3) special_code = 9928492;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 4) special_code = 2948193;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 5) special_code = 1927492;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 6) special_code = 7384712;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 7) special_code = 8294723;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 8) special_code = 6928492;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 8 && loaded_replay.getVersion_sub2() == 9) special_code = 4928539;
			else if(loaded_replay.getVersion_main() == 0 && loaded_replay.getVersion_sub1() == 9 && loaded_replay.getVersion_sub2() == 0) special_code = 5471534;
			
			code2 = Lint.big(special_code);
			
			if(loaded_replay.getVersion_main() >= 0 && loaded_replay.getVersion_sub1() >= 1 && loaded_replay.getVersion_sub2() >= 9)
			{
				BigInteger accumulated = new BigInteger("0");
				for(int i=0; i<loaded_replay.getControls().length; i++)
				{
					//accumulated += loaded_replay.getControls()[i].card_index;
					//accumulated += loaded_replay.getControls()[i].control_type;
					accumulated = accumulated.add(new BigInteger(String.valueOf(loaded_replay.getControls()[i].getCard_index())));
					accumulated = accumulated.add(new BigInteger(String.valueOf(loaded_replay.getControls()[i].getControl_type())));
				}
				//accumulated = accumulated * ((loaded_replay.getVersion_main() * 100) + (loaded_replay.getVersion_sub1() * 10) + loaded_replay.getVersion_sub2());
				//if(accumulated != loaded_replay.getCode_x()) control_authorized = false;
				
				accumulated = accumulated.multiply(new BigInteger(String.valueOf((loaded_replay.getVersion_main() * 100) + (loaded_replay.getVersion_sub1() * 10) + loaded_replay.getVersion_sub2())));
				if(accumulated.compareTo(loaded_replay.getCode_x()) != 0) control_authorized = false;
			}
			//if(code == (loaded_replay.getVersion_main() * 100) + (loaded_replay.getVersion_sub1() * 10) + loaded_replay.getVersion_sub2() + loaded_replay.getStart_card_code() * code2 && control_authorized)
			if(code.compareTo((new BigInteger(String.valueOf(loaded_replay.getStart_card_code())).multiply(code2)).add(new BigInteger(String.valueOf((loaded_replay.getVersion_main() * 100) + (loaded_replay.getVersion_sub1() * 10) + loaded_replay.getVersion_sub2())))) == 0 && control_authorized)
				authorized.setSelected(true);
			else
				authorized.setSelected(false);
		}
		else
		{
			authorized.setSelected(false);
		}
		progressBar.setMaximum(loaded_replay.getControls().length);
		play();
		
		this.setVisible(true);
	}
	public void close()
	{
		this.setVisible(false);
	}
	public void play()
	{
		try
		{
			Control thisControl = loaded_replay.getControls()[progress];		
			for(int i=0; i<loaded_replay.getBlocks().length; i++)
			{
				loaded_replay.getBlocks()[i].turnFinish();
			}
			loaded_replay.getBlocks()[thisControl.getTurn()].thisTurn();
			Vector<StandardCard> deck = new Vector<StandardCard>();
			for(int i=0; i<loaded_replay.getDeck().length; i++)
			{
				deck.add((StandardCard) loaded_replay.getDeck()[i].clone());
			}
			StandardCard card;
			if(thisControl.getControl_type() == 0) // take
			{
				card = (StandardCard) deck.get(0).clone();
				deck.remove(0);
				loaded_replay.getBlocks()[thisControl.getTurn()].getOwns().add(card);
				message(loaded_replay.getBlocks()[thisControl.getTurn()].getName() + window.lang.getText(Language.DESCRIPTIONS + 0));
			}
			else if(thisControl.getControl_type() == 1) // pay
			{
				/*
				System.out.println("thisControl.turn : " + thisControl.turn + ", loaded_replay.blocks.length : " + loaded_replay.blocks.length);
				System.out.println("loaded_replay.blocks[].owns.size() : " + loaded_replay.blocks[thisControl.turn].owns.size());
				for(int i=0; i<loaded_replay.blocks[thisControl.turn].owns.size(); i++)
				{
					System.out.println(loaded_replay.blocks[thisControl.turn].owns.get(i));
				}*/
				card = (StandardCard) loaded_replay.getBlocks()[thisControl.getTurn()].getOwns().get(thisControl.getCard_index()).clone();
				loaded_replay.getBlocks()[thisControl.getTurn()].getOwns().remove(thisControl.getCard_index());
				loaded_replay.getBlocks()[thisControl.getTarget()].getPaids().add(card);	
				message(loaded_replay.getBlocks()[thisControl.getTurn()].getName() + window.lang.getText(Language.DESCRIPTIONS + 1) + loaded_replay.getBlocks()[thisControl.getTarget()].getName() + ", " + card.toBasicString(window.setting.getCard_separator()));
				char card_op = card.getOp();
				if(card_op == window.setting.getOp_change())
				{
					Vector<StandardCard> tempOwns = loaded_replay.getBlocks()[thisControl.getTurn()].getOwns();
					loaded_replay.getBlocks()[thisControl.getTurn()].setOwns(loaded_replay.getBlocks()[thisControl.getTarget()].getOwns());
					loaded_replay.getBlocks()[thisControl.getTarget()].setOwns(tempOwns);
					message(window.setting.getLang().getText(Language.DESCRIPTIONS + 26) + loaded_replay.getBlocks()[thisControl.getTurn()].getName() + window.setting.getLang().getText(Language.DESCRIPTIONS + 27) + loaded_replay.getBlocks()[thisControl.getTarget()].getName() + window.setting.getLang().getText(Language.DESCRIPTIONS + 28));
				}
			}
			loaded_replay.getBlocks()[thisControl.getTarget()].refresh();
			
			for(int i=0; i<loaded_replay.getBlocks().length; i++)
			{
				loaded_replay.getBlocks()[i].theme_refresh();
			}
			
			progress++;
			progressBar.setString(String.valueOf(progress) + " / " + String.valueOf(loaded_replay.getControls().length));
			progressBar.setValue(progress);
			if(progress >= loaded_replay.getControls().length)
			{
				play.setEnabled(false);
				for(int i=0; i<loaded_replay.getBlocks().length; i++)
					message(loaded_replay.getBlocks()[i].getName() + window.lang.getText(Language.WHOS) + " " + window.lang.getText(Language.POINT) + " : " + String.valueOf(loaded_replay.getPoints()[i]));
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			message(e.getMessage());
			reset();
		}
	}
	public void reset()
	{
		this.setVisible(false);
		contentPanel.removeAll();
		play.setEnabled(false);
		reset.setEnabled(false);
		progress = 0;
		progressBar.setString("0 / 0");
		progressBar.setValue(0);
		this.setVisible(true);
	}
	public void message(String str)
	{
		area.append(str + "\n");
		if(areaDialog.isVisible()) area.setCaretPosition(area.getDocument().getLength() - 1);
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == close)
		{
			close();
		}
		else if(ob == close2)
		{
			close();
		}
		else if(ob == play)
		{
			play();
		}
		else if(ob == load)
		{
			load();
		}
		else if(ob == reset)
		{
			reset();
		}
		else if(ob == areaDialog_close)
		{
			areaDialog.setVisible(false);
		}
		else if(ob == open_area)
		{
			areaDialog.setVisible(true);
			if(area.getDocument().getLength() >= 1) area.setCaretPosition(area.getDocument().getLength() - 1);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
			if(! areaDialog_moved)
			{
				areaDialog.setLocation((int)(this.getLocation().getX()), (int)(this.getLocation().getY()) + this.getHeight());
			}
		}
		else if(ob == areaDialog_titlePanel)
		{
			areaDialog.setLocation(e.getXOnScreen() - area_mousex, e.getYOnScreen() - area_mousey);
			areaDialog_moved  = true;
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
		else if(ob == areaDialog_titlePanel)
		{
			area_mousex = e.getX();
			area_mousey = e.getY();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}