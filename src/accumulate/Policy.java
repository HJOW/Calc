package accumulate;

import java.io.Serializable;

public abstract class Policy implements Serializable
{
	private static final long serialVersionUID = 1130673835527375994L;
	private Boolean active = null;
	private Integer grade = null;
	
	
	public static final int CURE = 1;
	public static final int STUDY = 2;
	public static final int DISTRIBUTION = 3;
	public static final int HEALTH = 4;
	public static final int ETC = 10;
	
	public Policy()
	{
		active = new Boolean(false);
		grade = new Integer(1);
	}
	
	public abstract String toBasicString();
	public abstract int cost(long population);
	public abstract boolean hasGrade();
	public abstract String description();
	
	public double cure()
	{
		return 0;
	}
	public int intelligent()
	{
		return 0;
	}
	public int tax(long average)
	{
		return 0;
	}
	public long give(long rank, long population, long average)
	{
		return 0;
	}
	
	
	public Boolean getActive()
	{
		return active;
	}
	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public Integer getGrade()
	{
		return grade;
	}

	public void setGrade(Integer grade)
	{
		this.grade = grade;
	}
}
