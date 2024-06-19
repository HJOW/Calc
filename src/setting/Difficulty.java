package setting;
import java.io.Serializable;

public class Difficulty implements Serializable
{	
	private static final long serialVersionUID = -3786474189851313009L;
	public static final double DEFAULT_UNIT_VALUE = 5.0;
	private Integer difficulty;
	public Difficulty()
	{
		difficulty = new Integer(1);
	}
	public Integer getDifficulty()
	{
		return difficulty;
	}
	public void setDifficulty(Integer difficulty)
	{
		this.difficulty = difficulty;
	}
	public Difficulty clone()
	{
		Difficulty newOne = new Difficulty();
		newOne.difficulty = new Integer(difficulty.intValue());
		return newOne;
	}	
	public String toBasicString()
	{
		return toBasicString(DEFAULT_UNIT_VALUE);
	}
	public String toBasicString(double unit)
	{
		int value = difficulty.intValue();
		String results = "";	
		
		while(value >= 1)
		{
			if(value >= 1000000)
			{
				results = results + "∬";
				value -= 1000000;
			}
			else if(value >= ((int) (100000.0 * unit)))
			{
				results = results + "∫";
				value -= (int) (100000.0 * unit);
			}
			else if(value >= 100000)
			{
				results = results + "∂";
				value -= 100000;
			}
			else if(value >= ((int) (10000.0 * unit)))
			{
				results = results + "∵";
				value -= (int) (10000.0 * unit);
			}
			else if(value >= 10000)
			{
				results = results + "♨";
				value -= 10000;
			}
			else if(value >= ((int) (1000.0 * unit)))
			{
				results = results + "★";
				value -= (int) (1000.0 * unit);
			}
			else if(value >= 1000)
			{
				results = results + "☆";
				value -= 1000;
			}
			else if(value >= ((int) (100.0 * unit)))
			{
				results = results + "◆";
				value -= (int) (100.0 * unit);
			}
			else if(value >= 100)
			{
				results = results + "◇";
				value -= 100;
			}
			else if(value >= ((int) (10.0 * unit)))
			{
				results = results + "■";
				value -= (int) (10.0 * unit);
			}
			else if(value >= 10)
			{
				results = results + "□";
				value -= 10;
			}
			else if(value >= ((int) unit))
			{
				results = results + "●";
				value -= (int) unit;
			}
			else if(value >= 1)
			{
				results = results + "○";
				value -= 1;
			}
			else break;
		}
		while(value <= -1)
		{
			if(value <= ((int) (-10.0 * unit)))
			{
				results = results + "♬";
				value += (int) (10.0 * unit);
			}
			else if(value <= (-10))
			{
				results = results + "♪";
				value += 10;
			}
			else if(value <= ((int) (-1.0 * unit)))
			{
				results = results + "♥";
				value += (int) (1.0 * unit);
			}
			else if(value <= (-1))
			{
				results = results + "♡";
				value += 1;
			}
			else break;
		}
		
		return results;
	}
	public static String starToString(long stars)
	{
		long value = stars;
		String results = "";
		while(value >= 1)
		{
			if(value >= 6)
			{
				results = results + "★";
				value -= 6;
			}
			else if(value >= 1)
			{
				results = results + "☆";
				value -= 1;
			}
		}
		return results;
	}
	public static String intToString(long v, double unit)
	{
		long value = v;
		String results = "";	
		
		while(value >= 1)
		{
			if(value >= 1000000)
			{
				results = results + "∬";
				value -= 1000000;
			}
			else if(value >= ((int) (100000.0 * unit)))
			{
				results = results + "∫";
				value -= (int) (100000.0 * unit);
			}
			else if(value >= 100000)
			{
				results = results + "∂";
				value -= 100000;
			}
			else if(value >= ((int) (10000.0 * unit)))
			{
				results = results + "∵";
				value -= (int) (10000.0 * unit);
			}			
			else if(value >= 10000)
			{
				results = results + "♨";
				value -= 10000;
			}
			else if(value >= ((int) (1000.0 * unit)))
			{
				results = results + "★";
				value -= (int) (1000.0 * unit);
			}
			else if(value >= 1000)
			{
				results = results + "☆";
				value -= 1000;
			}
			else if(value >= ((int) (100.0 * unit)))
			{
				results = results + "◆";
				value -= (int) (100.0 * unit);
			}
			else if(value >= 100)
			{
				results = results + "◇";
				value -= 100;
			}
			else if(value >= ((int) (10.0 * unit)))
			{
				results = results + "■";
				value -= (int) (10.0 * unit);
			}
			else if(value >= 10)
			{
				results = results + "□";
				value -= 10;
			}
			else if(value >= ((int) unit))
			{
				results = results + "●";
				value -= (int) unit;
			}
			else if(value >= 1)
			{
				results = results + "○";
				value -= 1;
			}
			else break;
		}
		while(value <= -1)
		{
			if(value <= ((int) (-10.0 * unit)))
			{
				results = results + "♬";
				value += (int) (10.0 * unit);
			}
			else if(value <= (-10))
			{
				results = results + "♪";
				value += 10;
			}
			else if(value <= ((int) (-1.0 * unit)))
			{
				results = results + "♥";
				value += (int) (1.0 * unit);
			}
			else if(value <= (-1))
			{
				results = results + "♡";
				value += 1;
			}
			else break;
		}
		
		return results;
	}
}
