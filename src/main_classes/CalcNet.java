package main_classes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import network.DisconnectMessage;
import lang.Language;
import setting.Setting;
import ai_algorithm.VirtualBlock;

public class CalcNet extends JFrame implements Openable, Controllable, MessageShowable, ActionListener, ItemListener, WindowListener, MouseListener, MouseMotionListener, Runnable
{
	private static final long serialVersionUID = -3223806035971491214L;
	int player_max = 4;
	private Setting sets;
	private Language lang;
	private boolean independence = true;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel contentPanel;
	private NetBlock[] blocks;
	private JScrollPane contentScroll;
	private JPanel rightPanel;
	private JPanel messagePanel;
	private JPanel consolePanel;
	private JTextArea messageArea;
	private JScrollPane messageScroll;
	private JTextField consoleField;
	private JButton consoleButton;
	private JPanel deckPanel;
	private JButton take;
	private JLabel deckLabel1;
	private JLabel deckLabel2;
	private Vector<StandardCard> deck;
	private JDialog connectDialog;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private int mousex;
	private int mousey;
	private JPanel connect_mainPanel;
	private JPanel connect_upPanel;
	private JPanel connect_centerPanel;
	private JPanel connect_downPanel;
	private JPanel[] connect_contentPanels;
	private JRadioButton connect_hostCheck;
	private JRadioButton connect_joinCheck;
	private ButtonGroup connect_group;
	private JLabel connect_nameLabel;
	private JTextField connect_nameField;
	private JLabel connect_portLabel;
	private JTextField connect_portField;
	private JLabel connect_ipLabel;
	private JTextField connect_ipField;
	private JButton connect_ok;
	private JButton connect_exit;
	private JPanel connect_titlePanel;
	private JLabel connect_title;
	private boolean online = false;
	private boolean hostMode = false;
	private Vector<Steakholders> room_list; 
	public RoomBlock[] roomBlocks;
	private Socket socket;
	private ServerSocket serverSocket;
	private ObjectInputStream socketInputStream;
	private ObjectOutputStream socketOutputStream;
	private JDialog roomDialog;
	private JPanel room_mainPanel;
	private AcceptThread acceptThread;
	private Thread gameThread;
	private boolean threadSwitch = true;
	private JPanel room_upPanel;
	private JPanel room_centerPanel;
	private JPanel room_downPanel;
	private JPanel room_contentPanel;
	private JScrollPane room_scroll;
	private JButton room_ready;
	private JButton room_disconnect;
	
	public CalcNet(Setting sets, boolean independence)
	{
		this.independence = independence;
		this.sets = sets;
		if(sets == null) this.sets = Setting.getNewInstance();
		this.lang = sets.getLang();
		this.addWindowListener(this);
		deck = new Vector<StandardCard>();
		room_list = new Vector<Steakholders>();	
		
		acceptThread = new AcceptThread(this);
		gameThread = new Thread(this);
		
		acceptThread.start();
		gameThread.start();
		
		init();
		
		connectDialog = new JDialog(this, true);
		connectDialog.addWindowListener(this);		
		if(sets.isUse_own_titleBar())
		{
			connectDialog.setUndecorated(true);
		}
		connectDialog.setSize(400, 300);
		connectDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - connectDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - connectDialog.getHeight()/2));
		
		connect_mainPanel = new JPanel();
		connectDialog.getContentPane().setLayout(new BorderLayout());
		connectDialog.getContentPane().add(connect_mainPanel, BorderLayout.CENTER);
		
		connect_mainPanel.setBorder(new EtchedBorder());
		connect_mainPanel.setBackground(sets.getSelected_back());
		connect_mainPanel.setLayout(new BorderLayout());
		
		 connect_upPanel = new JPanel();
		 connect_centerPanel = new JPanel();
		 connect_downPanel = new JPanel();
		 
		 connect_upPanel.setBackground(sets.getSelected_back());
		 connect_centerPanel.setBackground(sets.getSelected_back());
		 connect_downPanel.setBackground(sets.getSelected_back());
		 
		 connect_mainPanel.add(connect_upPanel, BorderLayout.NORTH);
		 connect_mainPanel.add(connect_centerPanel, BorderLayout.CENTER);
		 connect_mainPanel.add(connect_downPanel, BorderLayout.SOUTH);
		 
		 connect_contentPanels = new JPanel[4];
		 connect_centerPanel.setLayout(new GridLayout(connect_contentPanels.length, 1));
		 for(int i=0; i<connect_contentPanels.length; i++)
		 {
			 connect_contentPanels[i] = new JPanel();
			 connect_contentPanels[i].setBorder(new EtchedBorder());
			 connect_contentPanels[i].setBackground(sets.getSelected_back());
			 connect_contentPanels[i].setLayout(new FlowLayout());
			 connect_centerPanel.add(connect_contentPanels[i]);
		 }
		 
		 connect_nameLabel = new JLabel(lang.getText(Language.NAME));
		 connect_nameField = new JTextField(10);
		 connect_nameField.setBackground(sets.getSelected_inside_back());
		 connect_nameField.setForeground(sets.getSelected_fore());
		 connect_nameLabel.setForeground(sets.getSelected_fore());
		 connect_contentPanels[0].add(connect_nameLabel);
		 connect_contentPanels[0].add(connect_nameField);
		 
		 
		 
		 connect_group = new ButtonGroup();
		 connect_hostCheck = new JRadioButton(lang.getText(Language.HOST));
		 connect_hostCheck.addItemListener(this);
		 connect_group.add(connect_hostCheck);
		 connect_hostCheck.setBackground(sets.getSelected_back());
		 connect_hostCheck.setForeground(sets.getSelected_fore());
		 connect_contentPanels[1].add(connect_hostCheck);		 
		 
		 connect_joinCheck = new JRadioButton(lang.getText(Language.JOIN));
		 connect_joinCheck.addItemListener(this);
		 connect_group.add(connect_joinCheck);
		 connect_joinCheck.setBackground(sets.getSelected_back());
		 connect_joinCheck.setForeground(sets.getSelected_fore());
		 connect_contentPanels[1].add(connect_joinCheck);
		 
		 connect_ipLabel = new JLabel(lang.getText(Language.IP));
		 connect_ipField = new JTextField(20);
		 connect_ipField.setEditable(false);
		 connect_ipLabel.setForeground(sets.getSelected_fore());
		 connect_ipField.setForeground(sets.getSelected_fore());
		 connect_ipField.setBackground(sets.getSelected_inside_back());
		 connect_portLabel = new JLabel(lang.getText(Language.PORT));
		 connect_portField = new JTextField(5);
		 connect_portLabel.setForeground(sets.getSelected_fore());
		 connect_portField.setForeground(sets.getSelected_fore());
		 connect_portField.setBackground(sets.getSelected_inside_back());
		 connect_portField.setText(String.valueOf(5000));		 
		 connect_contentPanels[3].add(connect_ipLabel);
		 connect_contentPanels[3].add(connect_ipField);
		 connect_contentPanels[3].add(connect_portLabel);
		 connect_contentPanels[3].add(connect_portField);
		 
		 connect_downPanel.setLayout(new FlowLayout());
		 connect_ok = new JButton(lang.getText(Language.OK));
		 connect_ok.addActionListener(this);
		 connect_ok.setForeground(sets.getSelected_fore());
		 connect_exit = new JButton(lang.getText(Language.EXIT));
		 connect_exit.setForeground(sets.getSelected_fore());
		 connect_exit.addActionListener(this);
		 connect_downPanel.add(connect_ok);
		 connect_downPanel.add(connect_exit);
		 
		 connect_titlePanel = new JPanel();
		 connect_titlePanel.setBorder(new EtchedBorder());
		 connect_titlePanel.setLayout(new FlowLayout());
		 connect_titlePanel.setBackground(sets.getSelected_inside_back());
		 connect_title = new JLabel(lang.getText(Language.TITLE));
		 connect_title.setForeground(sets.getSelected_fore());
		 connect_titlePanel.add(connect_title);
		 connect_titlePanel.addMouseListener(this);
		 connect_titlePanel.addMouseMotionListener(this);
		 
		 if(sets.isUse_own_titleBar())
		 {
			connect_upPanel.setLayout(new BorderLayout());
			connect_upPanel.add(connect_titlePanel, BorderLayout.CENTER);
		 }
		 
		 roomDialog = new JDialog(this, true);
		 roomDialog.addWindowListener(this);
		 roomDialog.getContentPane().setLayout(new BorderLayout());
		 if(sets.isUse_own_titleBar())
		 {
			 roomDialog.setUndecorated(true);
		 }
		 roomDialog.setSize(400, 300);
		 roomDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - roomDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - roomDialog.getHeight()/2));
				
		 room_mainPanel = new JPanel();		 
		 roomDialog.getContentPane().add(room_mainPanel, BorderLayout.CENTER);
		 room_mainPanel.setLayout(new BorderLayout());
		 room_mainPanel.setBorder(new EtchedBorder());
		 
		 room_upPanel = new JPanel();
		 room_centerPanel = new JPanel();
		 room_downPanel = new JPanel();
		 room_mainPanel.add(room_upPanel, BorderLayout.NORTH);
		 room_mainPanel.add(room_centerPanel, BorderLayout.CENTER);
		 room_mainPanel.add(room_downPanel, BorderLayout.SOUTH);		 
		 
		 room_mainPanel.setBackground(sets.getSelected_back());
		 room_upPanel.setBackground(sets.getSelected_back());
		 room_centerPanel.setBackground(sets.getSelected_back());
		 room_downPanel.setBackground(sets.getSelected_back());
		 
		 room_centerPanel.setLayout(new BorderLayout());
		 room_contentPanel = new JPanel();
		 room_scroll = new JScrollPane(room_contentPanel);
		 room_centerPanel.add(room_scroll, BorderLayout.CENTER);
		 
		 room_contentPanel.setBackground(sets.getSelected_back());
		 room_contentPanel.setLayout(new GridLayout(player_max, 1));
		 roomBlocks = new RoomBlock[player_max];
		 for(int i=0; i<player_max; i++)
		 {
			 roomBlocks[i] = new RoomBlock(sets);
			 room_contentPanel.add(roomBlocks[i]);
			 roomBlocks[i].empty.addItemListener(this);
			 roomBlocks[i].none.addItemListener(this);
			 roomBlocks[i].banButton.addItemListener(this);
		 }
		 
		 room_downPanel.setLayout(new FlowLayout());
		 room_ready = new JButton(lang.getText(Language.READY));
		 room_ready.addActionListener(this);
		 room_disconnect = new JButton(lang.getText(Language.DISCONNECT));
		 room_disconnect.addActionListener(this);
		 room_ready.setForeground(sets.getSelected_fore());
		 room_disconnect.setForeground(sets.getSelected_fore());
		 room_downPanel.add(room_ready);
		 room_downPanel.add(room_disconnect);
		 
		 
	}
	public void init()
	{
		if(sets.isUse_own_titleBar())
		{
			this.setUndecorated(true);
		}
		
		this.setSize(sets.getWidth(), sets.getHeight());
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.getContentPane().removeAll();
		
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setBorder(new EtchedBorder());
		
		mainPanel.setBackground(sets.getSelected_back());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		rightPanel = new JPanel();
		
		upPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		rightPanel.setBackground(sets.getSelected_back());
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		rightPanel.setLayout(new BorderLayout());
		
		messagePanel = new JPanel();
		consolePanel = new JPanel();
		
		messagePanel.setBackground(sets.getSelected_back());
		consolePanel.setBackground(sets.getSelected_back());
		
		rightPanel.add(messagePanel, BorderLayout.CENTER);
		rightPanel.add(consolePanel, BorderLayout.SOUTH);
		
		messagePanel.setLayout(new BorderLayout());
		
		messageArea = new JTextArea();
		messageArea.setBackground(sets.getSelected_inside_back());
		messageArea.setForeground(sets.getSelected_fore());
		messageArea.setLineWrap(true);
		messageArea.setEditable(false);
		messageScroll = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messagePanel.add(messageScroll, BorderLayout.CENTER);
		
		consolePanel.setLayout(new BorderLayout());
		consoleField = new JTextField();
		consoleField.setBackground(sets.getSelected_inside_back());
		consoleField.setForeground(sets.getSelected_fore());
		consoleButton = new JButton(lang.getText(Language.RUN));
		consoleButton.addActionListener(this);
		consoleButton.setForeground(sets.getSelected_fore());
		consoleField.addActionListener(this);
		consolePanel.add(consoleField, BorderLayout.CENTER);
		consolePanel.add(consoleButton, BorderLayout.EAST);
		
		
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBackground(sets.getSelected_back());
		blocks = new NetBlock[4];
		contentPanel.setLayout(new GridLayout(1, blocks.length));
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i] = new NetBlock(sets.clone());
			contentPanel.add(blocks[i]);
			blocks[i].getButton().addActionListener(this);
		}
		contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(contentScroll);
		
		upPanel.setLayout(new BorderLayout());
		
		deckPanel = new JPanel();
		deckPanel.setBackground(sets.getSelected_back());
		upPanel.add(deckPanel, BorderLayout.CENTER);
		
		titlePanel = new JPanel(); 
		if(sets.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.setBorder(new EtchedBorder());
			titlePanel.setBackground(sets.getSelected_inside_back());
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			titlePanel.setLayout(new FlowLayout());
			titleLabel = new JLabel(lang.getText(Language.TITLE));
			titleLabel.setForeground(sets.getSelected_fore());
			titlePanel.add(titleLabel);
		}
		
		deckPanel.setLayout(new FlowLayout());
		take = new JButton(lang.getText(Language.GET));
		take.setEnabled(false);
		take.addActionListener(this);
		take.setForeground(sets.getSelected_fore());
		deckLabel1 = new JLabel(lang.getText(Language.DECK_LABEL1));
		deckLabel2 = new JLabel(lang.getText(Language.DECK_LABEL2));
		deckLabel1.setForeground(sets.getSelected_fore());
		deckLabel2.setForeground(sets.getSelected_fore());
		deckPanel.add(deckLabel1);
		deckPanel.add(deckLabel2);
		deckPanel.add(take);		
	}
	
	public int getTurn()
	{
		for(int i=0; i<blocks.length; i++)
		{
			if(blocks[i].turn) return i;
		}
		return 0;
	}
	private void net_allClose()
	{
		try
		{
			if(online && hostMode)
			for(int i=0; i<room_list.size(); i++)
			{
				try
				{
					DisconnectMessage newMessage = new DisconnectMessage();
					newMessage.setIP(InetAddress.getLocalHost().getHostAddress(), room_list.get(i).getIp());
					newMessage.setVersion(CalcWindow.version_main, CalcWindow.version_sub_1, CalcWindow.version_sub_2);
					room_list.get(i).getOutputStream().writeObject(newMessage);
				} 
				catch (Exception e)
				{
					
				}
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			room_list.clear();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			socketInputStream.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			socketOutputStream.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			socket.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			serverSocket.close();
		}
		catch(Exception e)
		{
			
		}
	}
	private void tryConnect()
	{
		connectDialog.setVisible(false);
		net_allClose();
		try
		{
			String ip = "";
			int port = 0;
			
			port = Integer.parseInt(connect_portField.getText());
			
			if(connect_hostCheck.isSelected())
			{
				serverSocket = new ServerSocket(port);
				
				Counterparty newCounterparty = new Counterparty();
				newCounterparty.setIp("localhost");
				newCounterparty.setNumber(0);
				room_list.add(newCounterparty);
				
				hostMode = true;
				online = true;
			}
			else if(connect_joinCheck.isSelected())
			{
				ip = connect_ipField.getText();				
				
				socket = new Socket(ip, port);
				socketInputStream = new ObjectInputStream(socket.getInputStream());
				socketOutputStream = new ObjectOutputStream(socket.getOutputStream());
				
				hostMode = false;
				online = true;
			}			
			
			if(online)
			{
				connectDialog.setVisible(false);
				roomDialog.setVisible(true);
				room_refresh();
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			roomDialog.setVisible(false);
			connectDialog.setVisible(true);
			net_allClose();			
		}
	}
	public void room_refresh() throws Exception
	{
		for(int i=0; i<roomBlocks.length; i++)
		{
			roomBlocks[i].first();
		}		
		for(int i=0; i<room_list.size(); i++)
		{
			roomBlocks[room_list.get(i).getNumber()].next();
			roomBlocks[room_list.get(i).getNumber()].setIP(room_list.get(i).getIp());
			roomBlocks[room_list.get(i).getNumber()].readyCheck.setSelected(room_list.get(i).isReady());
		}
		if(hostMode)
		{
			roomBlocks[0].banButton.setEnabled(false);
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
		if(ob == this || ob == connectDialog)
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
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel) 
		{
			this.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
		}
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
		if(ob == connect_hostCheck)
		{
			if(connect_hostCheck.isSelected())
			{
				connect_ipField.setEditable(false);
			}
		}
		else if(ob == connect_joinCheck)
		{
			if(connect_joinCheck.isSelected())
			{
				connect_ipField.setEditable(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == connect_exit)
		{
			exit();
		}
		else if(ob == connect_ok)
		{			
			tryConnect();
		}
		else if(ob == room_ready)
		{
			
		}
		else if(ob == room_disconnect)
		{
			
		}
		else
		{
			
		}
	}

	@Override
	public void message()
	{
		messageArea.append("\n");
		
	}

	@Override
	public void message_bar()
	{
		messageArea.append("=====================\n");
		
	}

	@Override
	public void message(String str)
	{
		messageArea.append(str + "\n");
		
	}

	@Override
	public void alert(String str)
	{
		messageArea.append(str + "\n");
		JOptionPane.showMessageDialog(this, str);
	}

	@Override
	public void stop_game()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start_game()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeCard()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void payCard(int blockNumber, int pay_card_index)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectAuthorizeCheckbox()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectPlayerSetting(int index, int value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open()
	{
		if(! CalcWindow.isBeta())
		{
			JOptionPane.showMessageDialog(null, sets.getLang().getText(Language.ONLY_BETA));
			System.exit(0);
		}
		
		this.setVisible(true);
		if(independence)
			connectDialog.setVisible(true);
	}

	@Override
	public void exit()
	{
		if(independence)
		{
			System.exit(0);
		}
		else
		{
			this.setVisible(false);
		}
		
	}
	public Setting getSets()
	{
		return sets;
	}
	public void setSets(Setting sets)
	{
		this.sets = sets;
	}
	public Language getLang()
	{
		return lang;
	}
	public void setLang(Language lang)
	{
		this.lang = lang;
	}
	public boolean isIndependence()
	{
		return independence;
	}
	public void setIndependence(boolean independence)
	{
		this.independence = independence;
	}
	public NetBlock[] getBlocks()
	{
		return blocks;
	}
	public void setBlocks(NetBlock[] blocks)
	{
		this.blocks = blocks;
	}
	public Vector<StandardCard> getDeck()
	{
		return deck;
	}
	public void setDeck(Vector<StandardCard> deck)
	{
		this.deck = deck;
	}
	public boolean isOnline()
	{
		return online;
	}
	public void setOnline(boolean online)
	{
		this.online = online;
	}
	public boolean isHostMode()
	{
		return hostMode;
	}
	public void setHostMode(boolean hostMode)
	{
		this.hostMode = hostMode;
	}
	public Vector<Steakholders> getRoom_list()
	{
		return room_list;
	}
	public void setRoom_list(Vector<Steakholders> room_list)
	{
		this.room_list = room_list;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{
			
			
			
			try
			{
				room_refresh();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				Thread.sleep(10);
			}
			catch (Exception e)
			{
				
			}
		}		
	}
	@Override
	public Setting getSetting()
	{
		return sets.clone();
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}	

}
class RoomBlock extends JPanel
{
	private static final long serialVersionUID = 3483235166293256129L;
	public JRadioButton empty;
	public JRadioButton none;
	private ButtonGroup radioGroup;
	private JTextField ipField;
	private JLabel ipLabel;
	private CardLayout layout;
	private JPanel[] cards;
	public JButton banButton;
	public JCheckBox readyCheck;
	public int page = 0;

	public RoomBlock(Setting sets)
	{
		layout = new CardLayout();
		this.setLayout(layout);
		this.setBackground(sets.getSelected_back());
		
		cards = new JPanel[2];
		for(int i=0; i<cards.length; i++)
		{
			cards[i] = new JPanel();
			cards[i].setLayout(new FlowLayout());
			cards[i].setBorder(new EtchedBorder());
			cards[i].setBackground(sets.getSelected_back());
			this.add(cards[i], String.valueOf(i));
		}
		
		radioGroup = new ButtonGroup();
		empty = new JRadioButton(sets.getLang().getText(Language.PLAYER));
		none = new JRadioButton(sets.getLang().getText(Language.NONE));
		none.setSelected(true);
		radioGroup.add(empty);
		radioGroup.add(none);
		cards[0].add(empty);
		cards[0].add(none);
		empty.setBackground(sets.getSelected_back());
		none.setBackground(sets.getSelected_back());
		empty.setForeground(sets.getSelected_fore());
		none.setForeground(sets.getSelected_fore());
		
		ipLabel = new JLabel(sets.getLang().getText(Language.IP));
		ipField = new JTextField();
		ipField.setEditable(false);
		readyCheck = new JCheckBox();
		readyCheck.setEnabled(false);
		banButton = new JButton(sets.getLang().getText(Language.X));
		ipField.setBackground(sets.getSelected_back());
		ipField.setForeground(sets.getSelected_fore());
		ipLabel.setForeground(sets.getSelected_fore());
		readyCheck.setForeground(sets.getSelected_fore());
		readyCheck.setBackground(sets.getSelected_back());
		banButton.setForeground(sets.getSelected_fore());
		cards[1].add(ipLabel);
		cards[1].add(ipField);
		cards[1].add(readyCheck);
		cards[1].add(banButton);	
	}
	public void setIP(String ip)
	{
		ipField.setText(ip);
	}
	public void first()
	{
		layout.first(this);
		page = 0;
	}
	public void next()
	{
		layout.next(this);
		page++;
	}
	public void prev()
	{
		layout.previous(this);
		page--;
	}
}
class NetBlock extends JPanel
{
	private static final long serialVersionUID = -9125359844181487998L;
	public boolean active = false;
	public boolean owner = false;
	public volatile boolean turn = false;
	public String name = "";
	public VirtualBlock data;
	private Setting sets;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JPanel pointPanel;
	private JPanel cardCountPanel;
	private JLabel cardCountLabel;
	private JTextField cardCountField;
	private JLabel pointLabel;
	private JTextField pointField;
	private JPanel dataPanel;
	private JPanel payPanel;
	private JButton pay;
	private JList ownList;
	private JList payList;
	private JPanel[] listPanels;
	private JScrollPane ownScroll;
	private JScrollPane payScroll;
	private JTextField lastCardField;
	public NetBlock(Setting sets)
	{
		this.sets = sets.clone();
		//this.sets = Setting.getNewInstance();
		
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder());
		
		data = new VirtualBlock(sets);
		
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerPanel = new JPanel();
		
		this.add(upPanel, BorderLayout.NORTH);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
		upPanel.setLayout(new FlowLayout());
		nameLabel = new JLabel(this.sets.getLang().getText(Language.NAME));
		nameField = new JTextField(10);
		nameField.setBorder(new EtchedBorder());
		nameField.setEditable(false);
		upPanel.add(nameLabel);
		upPanel.add(nameField);
		
		downPanel.setLayout(new BorderLayout());
		dataPanel = new JPanel();
		downPanel.add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(new GridLayout(2, 1));
		cardCountPanel = new JPanel();
		pointPanel = new JPanel();
		dataPanel.add(cardCountPanel);
		dataPanel.add(pointPanel);
		cardCountPanel.setLayout(new BorderLayout());
		cardCountLabel = new JLabel(sets.getLang().getText(Language.CARD) + " " + sets.getLang().getText(Language.COUNT));
		cardCountField = new JTextField();
		cardCountField.setBorder(new EtchedBorder());
		cardCountField.setEditable(false);
		cardCountPanel.add(cardCountLabel, BorderLayout.WEST);
		cardCountPanel.add(cardCountField, BorderLayout.CENTER);
		pointPanel.setLayout(new BorderLayout());
		pointLabel = new JLabel(sets.getLang().getText(Language.POINT));
		pointField = new JTextField();
		pointField.setBorder(new EtchedBorder());
		pointField.setEditable(false);
		pointPanel.add(pointLabel, BorderLayout.WEST);
		pointPanel.add(pointField, BorderLayout.CENTER);
		payPanel = new JPanel();
		downPanel.add(payPanel, BorderLayout.EAST);
		payPanel.setLayout(new FlowLayout());
		pay = new JButton(sets.getLang().getText(Language.PAY));
		payPanel.add(pay);
		pay.setEnabled(false);
		
		listPanels = new JPanel[2];
		centerPanel.setLayout(new GridLayout(1, listPanels.length));
		for(int i=0; i<listPanels.length; i++)
		{
			listPanels[i] = new JPanel();
			listPanels[i].setLayout(new BorderLayout());
			centerPanel.add(listPanels[i]);
		}
		
		ownList = new JList();
		payList = new JList();
		ownScroll = new JScrollPane(ownList);
		payScroll = new JScrollPane(payList);
		ownList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		payList.setEnabled(false);
		listPanels[0].add(ownScroll, BorderLayout.CENTER);
		listPanels[1].add(payScroll, BorderLayout.CENTER);
		
		lastCardField = new JTextField();
		lastCardField.setEditable(false);
		lastCardField.setBorder(new EtchedBorder());
		listPanels[1].add(lastCardField, BorderLayout.SOUTH);
		
		refresh();
	}
	public JButton getButton()
	{
		return pay;
	}
	public void refresh()
	{
		String[] ownArray;
		String[] paidArray = new String[data.getPaids().size()];
		
		for(int i=0; i<paidArray.length; i++)
		{
			paidArray[i] = data.getPaids().get(i).toBasicString(sets.getCard_separator());
		}
		
		if(turn)
		{
			ownArray = new String[data.getOwns().size()];
			for(int i=0; i<ownArray.length; i++)
			{
				ownArray[i] = data.getOwns().get(i).toBasicString(sets.getCard_separator());
			}
		}
		else
		{
			ownArray = new String[1];
			ownArray[0] = sets.getLang().getText(Language.SEALED);
		}
		
		if(data.getPaids().size() >= 1)
		{
			lastCardField.setText(data.getPaids().lastElement().toBasicString(sets.getCard_separator()));
		}
		else
		{
			lastCardField.setText("");
		}
		
		cardCountField.setText(String.valueOf(data.getOwns().size()));
		
		ownList.setListData(ownArray);
		payList.setListData(paidArray);
		
		data.calculate(true);
		pointField.setText(String.valueOf(data.getPoint()));
		
		color_refresh();
	}
	public void color_refresh()
	{
		Color back, fore, inside;
		
		
		if(turn)
		{
			back = sets.getSelected_back();
			fore = sets.getSelected_fore();
			inside = sets.getSelected_inside_back();
		}
		else
		{
			back = sets.getUnselected_back();
			fore = sets.getUnselected_fore();
			inside = sets.getUnselected_inside_back();
		}
		
		this.setBackground(back);
		upPanel.setBackground(back);
		centerPanel.setBackground(back);
		downPanel.setBackground(back);
		for(int i=0; i<listPanels.length; i++)
		{
			listPanels[i].setBackground(back);
		}
		cardCountPanel.setBackground(back);
		payPanel.setBackground(back);
		pointPanel.setBackground(back);
		
		nameLabel.setForeground(fore);
		nameField.setBackground(inside);
		nameField.setForeground(fore);
		
		pointLabel.setForeground(fore);
		pointField.setBackground(inside);
		pointField.setForeground(fore);
		
		cardCountLabel.setForeground(fore);
		cardCountField.setBackground(inside);
		cardCountField.setForeground(fore);
		
		lastCardField.setBackground(inside);
		lastCardField.setForeground(fore);
		
		pay.setForeground(fore);
		
		ownList.setForeground(fore);
		payList.setForeground(fore);
		ownList.setBackground(inside);
		payList.setBackground(inside);
	}
}
class Steakholders implements Serializable
{
	private static final long serialVersionUID = -7318884407056723974L;
	private String ip = "";
	private int number = 0;
	private boolean host = false;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	public String getIp()
	{
		return ip;
	}
	public void close()
	{
		try
		{
			inputStream.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			outputStream.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			socket.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public boolean isHost()
	{
		return host;
	}
	public void setHost(boolean host)
	{
		this.host = host;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public void setSocket(Socket socket)
	{
		try
		{
			this.socket = socket;
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			close();
		}
	}
	public ObjectInputStream getInputStream()
	{
		return inputStream;
	}
	public void setInputStream(ObjectInputStream inputStream)
	{
		this.inputStream = inputStream;
	}
	public ObjectOutputStream getOutputStream()
	{
		return outputStream;
	}
	public void setOutputStream(ObjectOutputStream outputStream)
	{
		this.outputStream = outputStream;
	}
	public boolean isReady()
	{
		return true;
	}
	public void setReady(boolean b)
	{
		
	}
}
class Counterparty extends Steakholders
{
	private static final long serialVersionUID = 1277334771760480996L;
	private boolean ready = false;
	public boolean isReady()
	{
		return ready;
	}
	public void setReady(boolean b)
	{
		ready = b;
	}
}
class AcceptThread implements Runnable
{
	private boolean swtch = true;
	private CalcNet spComp;
	
	public AcceptThread(CalcNet spComp)
	{
		this.spComp = spComp;
	}
	
	public void off_thread()
	{
		swtch = false;
		spComp = null;
	}
	
	public void start()
	{
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		while(swtch)
		{
			try
			{
				if(spComp.isOnline())
				{
					if(spComp.isHostMode())
					{
						boolean emptyExist = false;
						for(int i=0; i<spComp.roomBlocks.length; i++)
						{
							if(spComp.roomBlocks[i].page == 0)
							{
								if(spComp.roomBlocks[i].empty.isSelected())
								{
									emptyExist = true;
									break;
								}
							}
						}
						
						if(emptyExist)
						{				
							Counterparty newCounterParty = new Counterparty();				
							Socket newSoc = (spComp.getServerSocket().accept());
							newCounterParty.setSocket(newSoc);				
							newCounterParty.setHost(false);
							newCounterParty.setIp(newSoc.getInetAddress().toString());
							
							for(int i=0; i<spComp.getRoom_list().size(); i++)
							{
								if(spComp.getRoom_list().get(i).getNumber() != i)
								{
									newCounterParty.setNumber(i);
								}
							}
							
							spComp.getRoom_list().add(newCounterParty);
						}
					}
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}	
}

