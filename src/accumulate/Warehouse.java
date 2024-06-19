package accumulate;

import java.math.BigInteger;

import setting.Lint;

public class Warehouse extends Section
{
	private static final long serialVersionUID = 6146272760436450659L;
	private BigInteger capacity = null;
	public Warehouse()
	{
		
	}
	public Warehouse(long grade)
	{
		int size = 20;
		for(int i=0; i<grade; i++)
			size = size * 2;
		capacity = Lint.big(size);
		setName("Resident " + String.valueOf((grade + 1)));
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
