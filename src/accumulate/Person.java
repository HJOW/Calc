package accumulate;

import java.io.Serializable;
import java.math.BigInteger;

import setting.Lint;

public class Person implements Serializable
{
	private static final long serialVersionUID = -5680984160081883725L;
	public transient static BigInteger now_ids = new BigInteger("0");
	public transient boolean worked = false;
	public transient BigInteger rich_level = null;
	private BigInteger id = null;
	private BigInteger budget = null;
	private Integer hp = null;
	private Integer intelligent = null;
	private Integer strength = null;
	private Integer happiness = null;
	private Integer desease = null;
	private Goods[] haves = null;
	

	public Person()
	{
		
	}

	public BigInteger getBudget()
	{
		return budget;
	}

	public void setBudget(BigInteger budget)
	{
		this.budget = budget;
	}

	public BigInteger getId()
	{
		return id;
	}

	public void setId(BigInteger id)
	{
		this.id = id;
	}
	public Integer getHp()
	{
		return hp;
	}

	public void setHp(Integer hp)
	{
		this.hp = hp;
	}

	public Integer getIntelligent()
	{
		return intelligent;
	}

	public void setIntelligent(Integer intelligent)
	{
		this.intelligent = intelligent;
	}

	public Integer getStrength()
	{
		return strength;
	}

	public void setStrength(Integer strength)
	{
		this.strength = strength;
	}

	public Integer getHappiness()
	{
		return happiness;
	}

	public void setHappiness(Integer happiness)
	{
		this.happiness = happiness;
	}

	public Integer getDesease()
	{
		return desease;
	}

	public void setDesease(Integer desease)
	{
		this.desease = desease;
	}
	public static Person newPerson()
	{
		return newPerson(10, 10);
	}
	public static Person newPerson(int intelligent, int strength)
	{
		now_ids = now_ids.add(Lint.big(1));
		Person newOne = new Person();
		newOne.setId(Lint.copy(now_ids));
		newOne.setBudget(Lint.big(0));
		newOne.setHp(new Integer(10));
		newOne.setIntelligent(new Integer(intelligent));
		newOne.setStrength(new Integer(strength));
		newOne.setHappiness(new Integer(50));
		newOne.setDesease(new Integer(0));
		newOne.setHaves(new Goods[100]);
		return newOne;
	}

	public Goods[] getHaves()
	{
		return haves;
	}

	public void setHaves(Goods[] haves)
	{
		this.haves = haves;
	}
}
