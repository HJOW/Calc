package main_classes;
import setting.Setting;

public class StandardCard extends Card
{
	private static final long serialVersionUID = 6837903216896928982L;
	private char op = '◆';
	private int num = 1;
	private String separator = "|";
	private boolean ace = true;
	public StandardCard()
	{
		
	}
	public StandardCard(char op, int num)
	{
		this.op = op;
		this.num = num;
	}
	public StandardCard(char op, int num, boolean trump_mode)
	{
		this.op = op;
		this.num = num;
		this.ace = trump_mode;
	}
	public StandardCard(char op, int num, String separator)
	{
		this.op = op;
		this.num = num;
		this.separator = new String(separator);
	}
	public StandardCard(char op, int num, String separator, boolean trump_mode)
	{
		this.op = op;
		this.num = num;
		this.separator = new String(separator);
		this.ace = trump_mode;
	}
	public char getOp()
	{
		return op;
	}
	public void setOp(char op)
	{
		this.op = op;
	}
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}	
	public int getType()
	{
		return STANDARD;
	}
	public String toBasicString()
	{
		String num_st = String.valueOf(num);
		if(ace)
		{
			if(num == 1)
			{
				num_st = "A";
			}
			else if(num == 11)
			{
				num_st = "J";
			}
			else if(num == 12)
			{
				num_st = "Q";
			}
			else if(num == 13)
			{
				num_st = "K";
			}
		}
		return String.valueOf(op) + separator + num_st;
	}
	public String toBasicString(String separator)
	{
		String num_st = String.valueOf(num);
		if(ace)
		{
			if(num == 1 && ace)
			{
				num_st = "A";
			}
			else if(num == 11)
			{
				num_st = "J";
			}
			else if(num == 12)
			{
				num_st = "Q";
			}
			else if(num == 13)
			{
				num_st = "K";
			}
		}
		return String.valueOf(op) + separator + num_st;
	}
	@Override
	public Card clone()
	{
		StandardCard newOne = new StandardCard(op, num, separator, ace);
		return newOne;
	}
	@Override
	public int grade(Setting set)
	{
		if(num == 1)
		{
			if(op == set.getOp_spade())
			{
				return 5;
			}
			else
			{
				return 3;
			}
		}
		else if(num == 2)
		{
			return 2;
		}
		else
			return 0;
	}
	public String getSeparator()
	{
		return separator;
	}
	public void setSeparator(String separator)
	{
		this.separator = new String(separator);
	}
	public boolean isAce()
	{
		return ace;
	}
	public void setAce(boolean ace)
	{
		this.ace = ace;
	}
}
