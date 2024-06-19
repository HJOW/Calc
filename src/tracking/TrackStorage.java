package tracking;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

import main_classes.CalcWindow;
import setting.SaveChar;
import setting.SaveInt;
import setting.Setting;

public class TrackStorage implements Serializable, Cloneable
{
	private static final long serialVersionUID = -1988515413607106319L;
	private static TrackStorage storage;
	private static XMLEncoder xml_writer;
	private static ObjectOutputStream writer;
	private static FileOutputStream stream;
	private static Setting set;
	private static int delay_save = 0;
	private SaveInt ver_main, ver_sub1, ver_sub2;
	private SaveChar ver_test;
	private SaveInt year, month, day;
	private Vector<TrackBlock> blocks;
	
	public TrackStorage()
	{
		blocks = new Vector<TrackBlock>();
		ver_main = new SaveInt();
		ver_sub1 = new SaveInt();
		ver_sub2 = new SaveInt();
		ver_test = new SaveChar();
		year = new SaveInt();
		month = new SaveInt();
		day = new SaveInt();	
		blocks = new Vector<TrackBlock>();
	}
	public TrackStorage(Setting set)
	{
		this();
		TrackStorage.set = set.clone();
	}
	public static void newInstance(Setting set)
	{
		storage = new TrackStorage(set);
	}
	public static TrackStorage getInstance()
	{
		if(storage == null) storage = new TrackStorage();
		return storage;
	}
	public static void save_xml() throws IOException
	{		
		Calendar calendar = Calendar.getInstance();
		if(stream == null)
		{
			File file = new File(set.getDefault_path());
			if(! file.exists()) file.mkdir();
			stream = new FileOutputStream(set.getDefault_path() + "log_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".xml");
		}
		if(xml_writer == null) xml_writer = new XMLEncoder(stream);
		TrackStorage gets = TrackStorage.getInstance();
		gets.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		gets.setVersion(CalcWindow.version_main, CalcWindow.version_sub_1, CalcWindow.version_sub_2, CalcWindow.version_test);
		xml_writer.writeObject(gets);
		System.out.println("Saved : " + set.getDefault_path() + "log_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".xml");
		xml_writer.close();
		stream.close();
		xml_writer = null;
		stream = null;
	}
	public static void save() throws IOException
	{		
		Calendar calendar = Calendar.getInstance();
		if(stream == null)
		{
			File file = new File(set.getDefault_path());
			if(! file.exists()) file.mkdir();
			stream = new FileOutputStream(set.getDefault_path() + "log_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".ctr");
		}
		if(writer == null) writer = new ObjectOutputStream(stream);
		TrackStorage gets = TrackStorage.getInstance();
		gets.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		gets.setVersion(CalcWindow.version_main, CalcWindow.version_sub_1, CalcWindow.version_sub_2, CalcWindow.version_test);
		writer.writeObject(gets);
		System.out.println("Saved : " + set.getDefault_path() + "log_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".ctr");
		if(writer != null) writer.close();
		if(stream != null) stream.close();
		writer = null;
		stream = null;
	}
	public static void addTrack(String className, String message, int line_number, boolean isProblem)
	{
		if(set.isUse_track())
		{			
			TrackBlock newTrack = new TrackBlock();
			newTrack.setMessage(message);
			newTrack.setClassName(className);
			newTrack.setLine_number(line_number);
			newTrack.setProblem(isProblem);
			TrackStorage.addTrack(newTrack);
			if(set.isUse_track_realtime())
			{
				delay_save++;
				if(delay_save >= set.getUse_track_save_delay())
				{
					try
					{
						save();
					} 
					catch (IOException e2)
					{
						e2.printStackTrace();
					}
					delay_save = 0;
				}
			}
		}
	}
	public static void addTrack(String className, String message, boolean isProblem)
	{
		if(set.isUse_track())
		{
			TrackBlock newTrack = new TrackBlock();
			newTrack.setMessage(message);
			newTrack.setClassName(className);
			newTrack.setLine_number(Thread.currentThread().getStackTrace()[2].getLineNumber());
			newTrack.setProblem(isProblem);
			TrackStorage.addTrack(newTrack);
			if(set.isUse_track_realtime())
			{
				delay_save++;
				if(delay_save >= set.getUse_track_save_delay())
				{
					try
					{
						save();
					} 
					catch (IOException e2)
					{
						e2.printStackTrace();
					}
					delay_save = 0;
				}
			}
		}
	}
	public static void addTrack(String className, Exception e, int line_number)
	{
		if(set.isUse_track())
		{
			TrackBlock newTrack = new TrackBlock();
			newTrack.setMessage(e.getLocalizedMessage());
			newTrack.setClassName(className);
			newTrack.setLine_number(line_number);
			newTrack.setProblem(true);
			TrackStorage.addTrack(newTrack);
			if(set.isUse_track_realtime())
			{
				delay_save++;
				if(delay_save >= set.getUse_track_save_delay())
				{
					try
					{
						save();
					} 
					catch (IOException e2)
					{
						e2.printStackTrace();
					}
					delay_save = 0;
				}
			}
		}
	}
	public static void addTrack(String className, Exception e)
	{
		if(set.isUse_track())
		{
			TrackBlock newTrack = new TrackBlock();
			newTrack.setMessage(e.getLocalizedMessage());
			newTrack.setClassName(className);
			newTrack.setLine_number(e.getStackTrace()[2].getLineNumber());
			newTrack.setProblem(true);
			TrackStorage.addTrack(newTrack);
			if(set.isUse_track_realtime())
			{
				delay_save++;
				if(delay_save >= set.getUse_track_save_delay())
				{
					try
					{
						save();
					} 
					catch (IOException e2)
					{
						e2.printStackTrace();
					}
					delay_save = 0;
				}
			}
		}
	}
	public static void addTrack(TrackBlock block)
	{
		TrackStorage.getInstance().blocks.add(block);		
	}
	
	public SaveInt getYear()
	{
		return year;
	}

	public void setYear(SaveInt year)
	{
		this.year = year;
	}

	public SaveInt getMonth()
	{
		return month;
	}

	public void setMonth(SaveInt month)
	{
		this.month = month;
	}

	public SaveInt getDay()
	{
		return day;
	}

	public void setDay(SaveInt day)
	{
		this.day = day;
	}	
	
	public TrackStorage clone()
	{
		TrackStorage newOne = new TrackStorage();
		for(int i=0; i<blocks.size(); i++)
		{
			newOne.blocks.add(blocks.get(i));
		}
		newOne.ver_main.setValue(ver_main.getValue());
		newOne.ver_sub1.setValue(ver_sub1.getValue());
		newOne.ver_sub2.setValue(ver_sub2.getValue());
		newOne.ver_test.setValue(ver_test.getValue());
		newOne.year.setValue(year.getValue());
		newOne.month.setValue(month.getValue());
		newOne.day.setValue(day.getValue());
		
		return newOne;
	}
	public void setDate(int year, int month, int day)
	{
		this.year.setValue(year);
		this.month.setValue(month);
		this.day.setValue(day);
	}
	public void setVersion(int ver_m, int ver_s1, int ver_s2, char ver_t)
	{
		ver_main.setValue(ver_m);
		ver_sub1.setValue(ver_s1);
		ver_sub2.setValue(ver_s2);
		ver_test.setValue(ver_t);
	}
	public Vector<TrackBlock> getBlocks()
	{
		return blocks;
	}

	public void setBlocks(Vector<TrackBlock> blocks)
	{
		this.blocks = blocks;
	}

	public SaveInt getVer_main()
	{
		return ver_main;
	}

	public void setVer_main(SaveInt ver_main)
	{
		this.ver_main = ver_main;
	}

	public SaveInt getVer_sub1()
	{
		return ver_sub1;
	}

	public void setVer_sub1(SaveInt ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}

	public SaveInt getVer_sub2()
	{
		return ver_sub2;
	}

	public void setVer_sub2(SaveInt ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}
	public SaveChar getVer_test()
	{
		return ver_test;
	}
	public void setVer_test(SaveChar ver_test)
	{
		this.ver_test = ver_test;
	}	
}
