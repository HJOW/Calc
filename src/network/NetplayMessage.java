package network;

import java.io.Serializable;

public abstract class NetplayMessage implements Serializable
{
	private static final long serialVersionUID = 8122149894769547423L;
	public static final int DISCONNECT = 0;
	public static final int READY = 1;
	public static final int JOIN = 2;
	public static final int CONTROL = 10;
	public static final int START = 20;
	public static final int FINISH = 30;
	public static final int TEXT = 100;	
	
	private String sender_ip = "";
	private String receiver_ip = "";
	private int version_main = 0;
	private int version_sub1 = 0;
	private int version_sub2 = 0;
	public NetplayMessage()
	{
		
	}
	
	public void setVersion(int ver_main, int ver_sub1, int ver_sub2)
	{
		version_main = ver_main;
		version_sub1 = ver_sub1;
		version_sub2 = ver_sub2;
	}
	public void setIP(String sender, String receiver)
	{
		sender_ip = sender;
		receiver_ip = receiver;
	}
	public abstract int getType();
	public abstract NetplayMessage clone();

	public String getSender_ip()
	{
		return sender_ip;
	}

	public void setSender_ip(String sender_ip)
	{
		this.sender_ip = sender_ip;
	}

	public String getReceiver_ip()
	{
		return receiver_ip;
	}

	public void setReceiver_ip(String receiver_ip)
	{
		this.receiver_ip = receiver_ip;
	}

	public int getVersion_main()
	{
		return version_main;
	}

	public void setVersion_main(int version_main)
	{
		this.version_main = version_main;
	}

	public int getVersion_sub1()
	{
		return version_sub1;
	}

	public void setVersion_sub1(int version_sub1)
	{
		this.version_sub1 = version_sub1;
	}

	public int getVersion_sub2()
	{
		return version_sub2;
	}

	public void setVersion_sub2(int version_sub2)
	{
		this.version_sub2 = version_sub2;
	}
}
