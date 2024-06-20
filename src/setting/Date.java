package setting;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable
{
	private static final long serialVersionUID = -5617089384835824513L;
	private int year, month, day, hour, minute, second;
	public Date()
	{
		
	}
	public Date clone()
	{
		Date newDate = new Date();
		newDate.year = year;
		newDate.month = month;
		newDate.day = day;
		newDate.hour = hour;
		newDate.minute = minute;
		newDate.second = second;
		return newDate;
	}
	public static Date nowDate()
	{
		Date newDate = new Date();
		Calendar c = Calendar.getInstance();
		newDate.year   = c.get(Calendar.YEAR);
		newDate.month  = c.get(Calendar.MONTH) + 1;
		newDate.day    = c.get(Calendar.DAY_OF_MONTH);
		newDate.hour   = c.get(Calendar.HOUR_OF_DAY);
		newDate.minute = c.get(Calendar.MINUTE);
		newDate.second = c.get(Calendar.SECOND);
		return newDate;
	}
	public void setNowDate()
	{
		Calendar c = Calendar.getInstance();
		year   = c.get(Calendar.YEAR);
		month  = c.get(Calendar.MONTH) + 1;
		day    = c.get(Calendar.DAY_OF_MONTH);
		hour   = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);
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

	public int getSecond()
	{
		return second;
	}

	public void setSecond(int second)
	{
		this.second = second;
	}
}
