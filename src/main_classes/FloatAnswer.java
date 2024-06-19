package main_classes;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FloatAnswer extends MathAnswer
{
	private static final long serialVersionUID = 8909039164418466992L;
	private BigDecimal answer = new BigDecimal(0.0);
	public FloatAnswer()
	{
		init();
	}
	public BigDecimal getAnswer()
	{
		return answer;
	}
	public double answer()
	{
		return answer.doubleValue();
	}
	public void setAnswer(BigDecimal answer)
	{
		this.answer = answer;
	}
	public void setAnswer(double answer)
	{
		this.answer = new BigDecimal(String.valueOf(answer));
	}
	@Override
	public String stringValue()
	{
		return answer.toString();
	}
	@Override
	public boolean isCorrect(Object ob) throws NumberFormatException
	{
		if(ob instanceof BigDecimal)
		{
			BigDecimal dec = (BigDecimal) ob;
			if(dec.compareTo(answer) == 0) return true;
			else return false;
		}
		else if(ob instanceof BigInteger)
		{
			BigInteger in = (BigInteger) ob;
			if(answer.toBigInteger().compareTo(in) == 0)
			{
				BigDecimal original = new BigDecimal(answer.toString());
				if(original.compareTo(answer) == 0) return true;
				else return false;				
			}
		}
		else if(ob instanceof FloatAnswer)
		{
			return isCorrect(((FloatAnswer) ob).getAnswer());
		}
		else if(ob instanceof IntAnswer)
		{
			return isCorrect(((IntAnswer) ob).getAnswer());
		}
		else if(ob instanceof String)
		{
			return isCorrect(new BigDecimal((String) ob));
		}
		return false;
	}	
}
