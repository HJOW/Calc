package accumulate;

import java.math.BigInteger;

import setting.Lint;

public class Academy extends Section
{
	private static final long serialVersionUID = -735197716496887370L;
	public transient long used = 0;
	private BigInteger capacity = null;
	private BigInteger power = null;
	private Long grade = null;

	public Academy()
	{
		
	}
	public Academy(long grade)
	{
		int size = 15;
		this.grade = new Long(grade);
		for(int i=0; i<grade; i++)
			size = size - 1;
		capacity = Lint.big(size);
		power = Lint.big((grade / 2) + 1);
		setName("Academy " + String.valueOf((grade + 1)));
	}
	@Override
	public int power()
	{
		return power.intValue();
	}
	public BigInteger getCapacity()
	{
		return capacity;
	}
	public void setCapacity(BigInteger capacity)
	{
		this.capacity = capacity;
	}
	public BigInteger getPower()
	{
		return power;
	}
	public void setPower(BigInteger power)
	{
		this.power = power;
	}
	public Long getGrade()
	{
		return grade;
	}
	public void setGrade(Long grade)
	{
		this.grade = grade;
	}
}
