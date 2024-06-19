package network;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Vector;

import main_classes.MessageShowable;
import main_classes.Openable;
import main_classes.ThreadControl;

public class ChatManager implements ThreadControl, Openable
{
	private static ChatManager chatManager = new ChatManager();
	private MessageShowable console;
	private volatile boolean threadSwitch = true;
	private Thread thread;
	private DatagramPacket packet, gets;
	private Vector<Message> queues;
	private Vector<String> ips;
	private Vector<String> banned;
	private byte[] buffer;
	private DatagramSocket socket;
	private volatile int port = 5000;
	private Message takes;
	private boolean bannedFlag = false;
	private boolean alive = false;
	private int bufferSize = 1024;
	private ChatManager()
	{
		
	}
	public static void ready(MessageShowable c)
	{
		chatManager.console = c;
		chatManager.buffer = new byte[chatManager.bufferSize];
		chatManager.queues = new Vector<Message>();
		chatManager.ips = new Vector<String>();		
		chatManager.banned = new Vector<String>();
		chatManager.thread = new Thread(chatManager);
	}
	public static ChatManager getInstance()
	{		
		return chatManager;
	}
	public void setBufferSize(int size)
	{
		bufferSize = size;
		buffer = new byte[size];
	}
	public void setPort(int port)
	{
		this.port = port;
	}
	public void send(String message)
	{
		for(int i=0; i<ips.size(); i++)
		{
			try
			{
				send(i, message);
			} 
			catch (IOException e)
			{
				console.message(e.getMessage());
			}
		}
	}
	public void send(int target, String message) throws IOException
	{
		socket = new DatagramSocket();
		buffer = message.getBytes();
		packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ips.get(target)), port);
		socket.send(packet);
		socket.close();
		console.message(InetAddress.getByName(ips.get(target)) + " <-- " + message);
	}
	public void receive() throws IOException
	{
		socket = new DatagramSocket(port);
		buffer = new byte[bufferSize];
		gets = new DatagramPacket(buffer, buffer.length);
		socket.receive(gets);
		queues.add(new Message(new String(buffer), gets.getAddress().toString()));
		socket.close();		
	}
	public Vector<Message> getQueues()
	{
		return queues;
	}
	public void setQueues(Vector<Message> queues)
	{
		this.queues = queues;
	}
	public void ban(String ip)
	{
		banned.add(ip);
	}
	public void addUser(String ip)
	{
		ips.add(ip);
	}
	@Override
	public void run()
	{
		while(threadSwitch)
		{
			try
			{
				receive();
				
				if(queues.size() >= 1)
				{
					takes = queues.get(0);
					if(banned.size() >= 1)
					{
						for(int i=0; i<banned.size(); i++)
						{
							if(banned.get(i).equals(takes.getAddress()))
							{
								bannedFlag = true;
								break;
							}
						}
					}
					if(! bannedFlag)
					{
						console.openConsole();
						console.message(takes.getAddress() + " --> " + takes.getMessage());
					}
					queues.remove(0);
					bannedFlag = false;
				}
				
				Thread.sleep(200);
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	@Override
	public void open()
	{	
		if(! alive)
			thread.start();
		alive = true;
	}
	@Override
	public void exit()
	{
		threadSwitch = false;
		alive = false;
	}
	public boolean isAlive()
	{
		return alive;
	}
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	public Vector<String> getIps()
	{
		return ips;
	}
	public void setIps(Vector<String> ips)
	{
		this.ips = ips;
	}
	
}
class Message implements Serializable
{
	private static final long serialVersionUID = 5839741856773585103L;
	private String message;
	private String address;
	public Message()
	{
		message = "";
		address = "";
	}
	public Message(String message, String address)
	{
		this.message = message;
		this.address = address;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
}
