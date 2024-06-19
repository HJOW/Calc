package accumulate;

public class ExercisePolicy extends Policy
{
	private static final long serialVersionUID = -1831033073131867734L;
	public ExercisePolicy()
	{
		super();
	}
	@Override
	public String toBasicString()
	{
		return "Exercise Campaign";
	}
	@Override
	public int cost(long population)
	{
		return 2 + (int) Math.round(population / 50.0);
	}
	@Override
	public boolean hasGrade()
	{
		return false;
	}
	@Override
	public String description()
	{
		return "운동을 장려하는 캠페인입니다.";
	} 

}
