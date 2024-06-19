package ai_algorithm;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import setting.Setting;
import main_classes.StandardCard;

public class VirtualBlock implements Serializable
{
	private static final long serialVersionUID = 4995476150194384979L;
	private String name = "";
	private Vector<StandardCard> owns;
	private Vector<StandardCard> paids;
	private BigInteger point = new BigInteger("0"), virtual_point = new BigInteger("0");
	private boolean ai = false;
	private Setting set;
	public VirtualBlock()
	{
		
	}
	public VirtualBlock(Setting set)
	{
		owns = new Vector<StandardCard>();
		paids = new Vector<StandardCard>();
		this.set = set.clone();
	}
	public void calculate(boolean virtual)
	{
		calculate(virtual, 0);
	}
	public void calculate(boolean virtual, int bonus)
	{
		int cardNum = 0;
		char cardOp = '+';
		if(virtual)
		{
			virtual_point = new BigInteger("0");
			for(int j=0; j<paids.size(); j++)
			{
				cardOp = paids.get(j).getOp();
				cardNum = paids.get(j).getNum();				
				if(j == 0)
				{
					//virtual_point += cardNum;
				}
				else
				{
					if(cardOp == set.getOp_plus() || cardOp == set.getOp_spade())
					{
						//virtual_point += cardNum;
						virtual_point = virtual_point.add(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_minus() || cardOp == set.getOp_clover())
					{
						//virtual_point -= cardNum;
						virtual_point = virtual_point.subtract(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_multiply() || cardOp == set.getOp_diamond())
					{
						//virtual_point *= cardNum;
						virtual_point = virtual_point.multiply(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_divide())
					{
						//virtual_point /= cardNum;
						virtual_point = virtual_point.divide(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_power())
					{
						for(int k=0; k<cardNum; k++)
						{
							//virtual_point *= virtual_point;
							virtual_point = virtual_point.multiply(virtual_point);
						}
					}
					else if(cardOp == set.getOp_change())
					{
						//virtual_point *= cardNum;
						virtual_point = virtual_point.multiply(new BigInteger(String.valueOf(cardNum)));
					}
				}
			}
			virtual_point = virtual_point.multiply(new BigInteger(String.valueOf(bonus)));
			//virtual_point += bonus;
		}
		else
		{					
			point = new BigInteger("0");
			for(int j=0; j<paids.size(); j++)
			{
				cardOp = paids.get(j).getOp();
				cardNum = paids.get(j).getNum();
				if(j == 0)
				{
					//point += cardNum;
					point = point.add(new BigInteger(String.valueOf(cardNum)));
				}
				else
				{
					if(cardOp == set.getOp_plus() || cardOp == set.getOp_spade())
					{
						//point += cardNum;
						point = point.add(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_minus() || cardOp == set.getOp_clover())
					{
						//point -= cardNum;
						point = point.subtract(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_multiply() || cardOp == set.getOp_diamond())
					{
						//point *= cardNum;
						point = point.multiply(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_divide())
					{
						//point /= cardNum;
						point = point.divide(new BigInteger(String.valueOf(cardNum)));
					}
					else if(cardOp == set.getOp_power())
					{
						for(int k=0; k<cardNum; k++)
						{
							//point *= point;
							point = point.multiply(point);
						}
					}
					else if(cardOp == set.getOp_change())
					{
						//point *= cardNum;
						point = point.multiply(new BigInteger(String.valueOf(cardNum)));
					}
				}
			}
			//point += bonus;
			point = point.add(new BigInteger(String.valueOf(bonus)));
		}
	}
	public VirtualBlock clone()
	{
		VirtualBlock newOne = new VirtualBlock(set);
		for(int i=0; i<this.owns.size(); i++)
		{
			newOne.owns.add((StandardCard) this.owns.get(i).clone());
		}
		for(int i=0; i<this.paids.size(); i++)
		{
			newOne.paids.add((StandardCard) this.paids.get(i).clone());
		}
		newOne.calculate(false);
		if(name != null)
			newOne.setName(this.getName());
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
	public Vector<StandardCard> getOwns()
	{
		return owns;
	}
	public void setOwns(Vector<StandardCard> owns)
	{
		this.owns = owns;
	}
	public Vector<StandardCard> getPaids()
	{
		return paids;
	}
	public void setPaids(Vector<StandardCard> paids)
	{
		this.paids = paids;
	}
	public BigInteger getPoint()
	{
		return point;
	}
	public void setPoint(BigInteger point)
	{
		this.point = point;
	}
	public BigInteger getVirtual_point()
	{
		return virtual_point;
	}
	public void setVirtual_point(BigInteger virtual_point)
	{
		this.virtual_point = virtual_point;
	}
	public boolean isAi()
	{
		return ai;
	}
	public void setAi(boolean ai)
	{
		this.ai = ai;
	}
	public Setting getSet()
	{
		return set;
	}
	public void setSet(Setting set)
	{
		this.set = set;
	}
}
