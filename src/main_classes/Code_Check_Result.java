package main_classes;

import java.io.Serializable;

public class Code_Check_Result implements Serializable, Cloneable
{
	private static final long serialVersionUID = 495142077599668700L;
	String code;
	String name;
	String play_mode;
	int players = 0;
	int own_cards = 0;
	int ver_main = 0;
	int ver_sub1 = 0;
	int ver_sub2 = 0;
	int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
	long point = 0;
	boolean authorized_name = false;
	boolean authorized = false;
	public Code_Check_Result clone()
	{
		Code_Check_Result newOne = new Code_Check_Result();
		if(this.code != null) newOne.code = new String(this.code);
		if(this.name != null) newOne.name = new String(this.name);
		if(this.play_mode != null) newOne.play_mode = new String(this.play_mode);
		newOne.players = this.players;
		newOne.own_cards = this.own_cards;
		newOne.ver_main = this.ver_main;
		newOne.ver_sub1 = this.ver_sub1;
		newOne.ver_sub2 = this.ver_sub2;
		newOne.point = this.point;
		newOne.aut_year = this.aut_year;
		newOne.aut_month = this.aut_month;
		newOne.aut_day = this.aut_day;
		newOne.aut_hour = this.aut_hour;
		newOne.aut_min = this.aut_min;
		newOne.aut_sec = this.aut_sec;
		return newOne;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getPlayers()
	{
		return players;
	}
	public void setPlayers(int players)
	{
		this.players = players;
	}
	public int getOwn_cards()
	{
		return own_cards;
	}
	public void setOwn_cards(int own_cards)
	{
		this.own_cards = own_cards;
	}
	public int getVer_main()
	{
		return ver_main;
	}
	public void setVer_main(int ver_main)
	{
		this.ver_main = ver_main;
	}
	public int getVer_sub1()
	{
		return ver_sub1;
	}
	public void setVer_sub1(int ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}
	public int getVer_sub2()
	{
		return ver_sub2;
	}
	public void setVer_sub2(int ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}
	public boolean isAuthorized()
	{
		return authorized;
	}
	public void setAuthorized(boolean authorized)
	{
		this.authorized = authorized;
	}
	public long getPoint()
	{
		return point;
	}
	public void setPoint(long point)
	{
		this.point = point;
	}
	public int getAut_year()
	{
		return aut_year;
	}
	public void setAut_year(int aut_year)
	{
		this.aut_year = aut_year;
	}
	public int getAut_month()
	{
		return aut_month;
	}
	public void setAut_month(int aut_month)
	{
		this.aut_month = aut_month;
	}
	public int getAut_day()
	{
		return aut_day;
	}
	public void setAut_day(int aut_day)
	{
		this.aut_day = aut_day;
	}
	public int getAut_hour()
	{
		return aut_hour;
	}
	public void setAut_hour(int aut_hour)
	{
		this.aut_hour = aut_hour;
	}
	public int getAut_min()
	{
		return aut_min;
	}
	public void setAut_min(int aut_min)
	{
		this.aut_min = aut_min;
	}
	public int getAut_sec()
	{
		return aut_sec;
	}
	public void setAut_sec(int aut_sec)
	{
		this.aut_sec = aut_sec;
	}
	public String getPlay_mode()
	{
		return play_mode;
	}
	public void setPlay_mode(String play_mode)
	{
		this.play_mode = play_mode;
	}
	public boolean isAuthorized_name()
	{
		return authorized_name;
	}
	public void setAuthorized_name(boolean authorized_name)
	{
		this.authorized_name = authorized_name;
	}
}
