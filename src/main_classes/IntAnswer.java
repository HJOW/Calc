package main_classes;
import java.math.BigDecimal;
import java.math.BigInteger;

public class IntAnswer extends MathAnswer
{
	private static final long serialVersionUID = 8909039164418466992L;
	private BigInteger answer = new BigInteger("0");
	public IntAnswer()
	{
		init();
	}
	
	@Override
	public String stringValue()
	{
		return answer.toString();
	}
	@Override
	public boolean isCorrect(Object ob) throws NumberFormatException
	{
		if(ob instanceof BigInteger)
		{
			return (answer.compareTo((BigInteger) ob) == 0);
		}
		else if(ob instanceof IntAnswer)
		{
			return isCorrect(((IntAnswer)(ob)).getAnswer());
		}
		else if(ob instanceof BigDecimal)
		{
			return (answer.compareTo(((BigDecimal) ob).toBigInteger()) == 0);
		}
		else if(ob instanceof FloatAnswer)
		{
			return isCorrect(((FloatAnswer)(ob)).getAnswer());
		}
		else if(ob instanceof String)
		{
			return isCorrect(new BigInteger((String) ob));
		}
		return false;
	}
	public BigInteger getAnswer()
	{
		return answer;
	}
	public long answer()
	{
		return answer.longValue();
	}
	public void setAnswer(BigInteger answer)
	{
		this.answer = answer;
	}
	public void setAnswer(long answer)
	{
		this.answer = new BigInteger(String.valueOf(answer));
	}
}
