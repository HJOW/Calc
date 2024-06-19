package main_classes;
import java.io.Serializable;
import java.math.BigInteger;

import ranking.Rank;
import setting.Lint;

public class User implements Serializable, Cloneable
{
	private static final long serialVersionUID = 4987274754145527985L;
	private String name = "";
	private long betting = 0;
	private long accumulated_point = 1000;	
	private String password = "";
	private int encryptType = 1; // 0 : None
	private long code = 0;
	private Rank[] ranks;
	private BigInteger stars = new BigInteger("0");
	
	public User()
	{
		
	}
	public User(String name)
	{
		this.name = new String(name);
		accumulated_point = 1000;
	}
	public void addRank(Rank rank)
	{
		if(ranks == null)
		{
			ranks = new Rank[1];
			ranks[0] = rank.clone();
		}
		else
		{
			Rank[] newArray = new Rank[ranks.length + 1];
			for(int i=0; i<ranks.length; i++)
			{
				newArray[i] = ranks[i];
			}
			newArray[ranks.length] = rank.clone();
			ranks = newArray;
		}
	}
	public boolean accept(String inp_password)
	{
		if(inp_password == null) return false;
		if(inp_password.equals(takePassword()))
		{
			long calculated = 0;
			char[] passed = inp_password.toCharArray();
			for(int i=0; i<passed.length; i++)
			{
				calculated += (long) passed[i];
			}
			System.out.println("code : " + code + ", calculated : " + calculated);
			if(code == calculated)
			{
				return true;
			}
		}		
		return false;
	}
	public long getCredit()
	{
		return getAccumulated_point();
	}
	public void setCredit(long credit)
	{
		long calculated = 0;
		char[] passed = takePassword().toCharArray();
		for(int i=0; i<passed.length; i++)
		{
			calculated += (long) passed[i];
		}
		code = calculated;
		accumulated_point = credit;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public long getBetting()
	{
		return betting;
	}
	public void setBetting(long betting)
	{
		this.betting = betting;
	}
	public long getAccumulated_point()
	{
		return accumulated_point;
	}
	public void setAccumulated_point(long accumulated_point)
	{
		this.accumulated_point = accumulated_point;
	}
	public User clone()
	{
		User newOne = new User();
		newOne.name = new String(this.name);
		newOne.accumulated_point = this.accumulated_point;
		newOne.betting = this.betting;
		newOne.code = this.code;
		newOne.password = new String(this.password);
		newOne.stars = Lint.copy(stars);
		return newOne;
	}
	public void inputPassword(String original)
	{		
		switch(encryptType)
		{
			case 1:
				try
				{
					byte[] takes = original.getBytes("UTF-8");
					for(int i=0; i<takes.length; i++)
					{
						if(takes[i] >= 64)
						{
							takes[i] -= 64;
						}
						else
						{
							takes[i] += 64;
						}						
					}
					this.password = new String(takes);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			case 0:
			default:
				this.password = original;
		}
	}
	public String takePassword()
	{
		switch(encryptType)
		{
			case 1:
				try
				{
					byte[] takes = this.password.getBytes();
					for(int i=0; i<takes.length; i++)
					{
						if(takes[i] >= 64)
						{
							takes[i] -= 64;
						}
						else
						{
							takes[i] += 64;
						}						
					}
					return new String(takes, "UTF-8");
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return null;
			case 0:
			default:
				return password;
		}
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public long getCode()
	{
		return code;
	}
	public void setCode(long code)
	{
		this.code = code;
	}
	public Rank[] getRanks()
	{
		return ranks;
	}
	public void setRanks(Rank[] ranks)
	{
		this.ranks = ranks;
	}
	public int getEncryptType()
	{
		return encryptType;
	}
	public void setEncryptType(int encryptType)
	{
		this.encryptType = encryptType;
	}
	public BigInteger getStars()
	{
		return stars;
	}
	public void setStars(BigInteger stars)
	{
		this.stars = stars;
	}
}