package accumulate;

import java.math.BigInteger;

import setting.Lint;

public class PowerPlant extends Section
{
	private static final long serialVersionUID = -4475338046002308895L;
	private BigInteger capacity = null;
	public PowerPlant()
	{
		
	}
	public PowerPlant(long grade)
	{
		BigInteger newCap = Lint.big(10);
		for(int i=0; i<grade; i++)
			newCap = newCap.multiply(Lint.big(2));
		capacity = newCap;
		setName("PowerPlant " + String.valueOf((grade + 1)));
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
