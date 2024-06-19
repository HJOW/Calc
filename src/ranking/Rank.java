package ranking;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Rank implements Serializable, Cloneable
{
	private static final long serialVersionUID = 5489231807727350747L;
	public String name = "";
	public BigInteger point = new BigInteger("0");
	
	public int ver_main = 0;
	public int ver_sub1 = 0;
	public int ver_sub2 = 0;
	
	public int year, month, day, hour, minute;
	public JPanel getTableColumn(Color background, Color foreground, Color inside_background)
	{
		JPanel newOne = new JPanel();
		newOne.setLayout(new GridLayout(1, 4));
		JPanel[] blocks = new JPanel[4];
		JLabel[] texts = new JLabel[4];
		newOne.setBackground(background);
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i] = new JPanel();
			texts[i] = new JLabel();
			blocks[i].setLayout(new FlowLayout());
			blocks[i].setBorder(new EtchedBorder());
			blocks[i].add(texts[i]);
			blocks[i].setBackground(inside_background);
			texts[i].setForeground(foreground);
			newOne.add(blocks[i]);
		}
		texts[0].setText(name);
		texts[1].setText(String.valueOf(point));
		texts[2].setText(String.valueOf(year) + "." + month + "." + day + "." + hour + "." + minute);
		texts[3].setText(String.valueOf(ver_main) + "." + ver_sub1 + "" + ver_sub2);
		newOne.setBorder(new EtchedBorder());
		return newOne;
	}
	public Rank clone()
	{
		Rank newOne = new Rank();
		newOne.name = new String(this.name);
		newOne.point = this.point;
		newOne.ver_main = this.ver_main;
		newOne.ver_sub1 = this.ver_sub1;
		newOne.ver_sub2 = this.ver_sub2;
		newOne.year = this.year;
		newOne.month = this.month;
		newOne.day = this.day;
		newOne.hour = this.hour;
		newOne.minute = this.minute;
		return newOne;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public BigInteger getPoint()
	{
		return point;
	}
	public void setPoint(BigInteger point)
	{
		this.point = point;
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
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getHour()
	{
		return hour;
	}
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	public int getMinute()
	{
		return minute;
	}
	public void setMinute(int minute)
	{
		this.minute = minute;
	}
}
