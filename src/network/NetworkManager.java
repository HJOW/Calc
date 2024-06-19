package network;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import main_classes.CalcWindow;

public class NetworkManager
{
	private boolean offed = false;
	private boolean hostMode = false;
	private CalcWindow window = null;
	private Vector<NetplayMessage> queue = null;
	private Vector<String> guests = null;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	public NetworkManager(CalcWindow window)
	{
		this.window = window;
		queue = new Vector<NetplayMessage>();
		guests = new Vector<String>();
	}
	public NetplayMessage dequeue()
	{
		NetplayMessage takes = queue.get(0);
		queue.remove(0);
		return takes;
	}
	public boolean setHostMode(int port)
	{
		guests.clear();
		try
		{			
			serverSocket = new ServerSocket(port);
			hostMode = true;
			return true;
		} 
		catch (Exception e)
		{
			hostMode = false;
			return false;
		}
	}
	public boolean setJoinMode(String ip, int port)
	{
		guests.clear();
		try
		{
			socket = new Socket(ip, port);
			return true;
		} 
		catch (Exception e)
		{
			return false;
		}		
	}
	
	public void off()
	{
		try
		{
			socket.close();
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			serverSocket.close();
		} 
		catch (Exception e)
		{
			
		}
		offed = true;
	}

	public boolean isOffed()
	{
		return offed;
	}

	public void setOffed(boolean offed)
	{
		this.offed = offed;
	}

	public CalcWindow getWindow()
	{
		return window;
	}

	public void setWindow(CalcWindow window)
	{
		this.window = window;
	}

	public Vector<NetplayMessage> getQueue()
	{
		return queue;
	}

	public void setQueue(Vector<NetplayMessage> queue)
	{
		this.queue = queue;
	}

	public boolean isHostMode()
	{
		return hostMode;
	}

	public void setHostMode(boolean hostMode)
	{
		this.hostMode = hostMode;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	public Vector<String> getGuests()
	{
		return guests;
	}
	public void setGuests(Vector<String> guests)
	{
		this.guests = guests;
	}
}
