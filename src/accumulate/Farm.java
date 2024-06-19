package accumulate;

import java.math.BigInteger;

import setting.Lint;

public class Farm extends Section
{
	private static final long serialVersionUID = -99923530653495768L;
	private BigInteger capacity = null;
	public Farm()
	{
		
	}
	public Farm(long grade)
	{
		int size = 5;
		for(int i=0; i<grade; i++)
			size = size * 2;
		this.capacity = Lint.big(size);
		setName("Farm " + String.valueOf((grade + 1)));
	}
	public BigInteger getCapacity()
	{
		return capacity;
	}
	public void setCapacity(BigInteger capacity)
	{
		this.capacity = capacity;
	}
}
