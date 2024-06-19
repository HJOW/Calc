package setting;

import java.math.BigInteger;

public class Lint
{			
	public static BigInteger newBigInteger(long value)
	{
		return new BigInteger(String.valueOf(value));
	}
	public static BigInteger newBigInteger(String value)
	{
		return new BigInteger(value);
	}
	public static BigInteger big(long value)
	{
		return newBigInteger(value);
	}
	public static BigInteger big(String value)
	{
		return newBigInteger(value);
	}
	public static BigInteger zero()
	{
		return new BigInteger("0");
	}
	public static BigInteger one()
	{
		return new BigInteger("0");
	}
	public static BigInteger copy(BigInteger other)
	{
		byte[] arr = other.toByteArray();
		byte[] arr2 = new byte[arr.length];
		for(int i=0; i<arr.length; i++)
		{
			arr2[i] = arr[i];
		}
		return new BigInteger(arr2);
	}
	public static BigInteger root_down(BigInteger value)
	{
		if(value.compareTo(Lint.big(0)) == 0) return Lint.big(0);
		else if(value.compareTo(Lint.big(0)) > 0)
		{
			BigInteger first = Lint.big(1);
			BigInteger second = value.shiftRight(5).add(Lint.big(8));
			BigInteger cent = null;
			while(second.compareTo(first) >= 0)
			{
				cent = first.add(second).shiftRight(1);
				if(cent.multiply(cent).compareTo(value) > 0) second = cent.subtract(Lint.big(1));
				else first = cent.add(Lint.big(1));
			}
			return first.subtract(Lint.big(1));
		}
		else
		{
			BigInteger target = value.multiply(Lint.big(-1));
			return root_down(target).multiply(Lint.big(-1));
		}
	}
}
