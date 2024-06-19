package setting;

public class S
{
	public static String encoding = "UTF-8";
	public static String _t(byte[] str)
	{
		try
		{
			return new String(str, encoding);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return new String(str);
		}
	}	
	public static String _t(String str)
	{
		try
		{
			return new String(str.getBytes(), encoding);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return str;
		}
	}
	public static String _t(byte[] str, String encoding)
	{
		try
		{
			return new String(str, encoding);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return new String(str);
		}
	}	
	public static String _t(String str, String encoding)
	{
		try
		{
			return new String(str.getBytes(), encoding);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return str;
		}
	}
}
