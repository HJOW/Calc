package accumulate;

import java.math.BigInteger;

import setting.Lint;

public class Cure extends Section
{
	private static final long serialVersionUID = 453764670007065813L;
	private BigInteger capacity = null;
	private BigInteger power = null;

	public Cure()
	{
		
	}
	public Cure(long grade)
	{
		int size = 4;
		for(int i=0; i<grade; i++)
			size = size * 2;
		capacity = Lint.big(size);
		power = Lint.big((grade / 2) + 1);
		setName("Cure " + String.valueOf((grade + 1)));
	}
	public BigInteger getPower()
	{
		return power;
	}
	public void setPower(BigInteger power)
	{
		this.power = power;
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
