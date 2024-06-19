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
		newDate.year = Calendar.getInstance().get(Calendar.YEAR);
		newDate.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		newDate.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		newDate.hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		newDate.minute = Calendar.getInstance().get(Calendar.MINUTE);
		newDate.second = Calendar.getInstance().get(Calendar.SECOND);
		return newDate;
	}
	public void setNowDate()
	{
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
		second = Calendar.getInstance().get(Calendar.SECOND);
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
