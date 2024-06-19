package accumulate;

public class CuringPolicy extends Policy
{
	private static final long serialVersionUID = -7740084732366152235L;
	public CuringPolicy()
	{
		super();
	}
	public CuringPolicy(int grade)
	{
		super();
		this.setGrade(new Integer(grade));
	}
	@Override
	public double cure() 
	{
		return getGrade() * 0.2;
	}
	@Override
	public String toBasicString()
	{
		String adds;
		switch(getGrade().intValue())
		{
			case 0:
				adds = "D";
				break;
			case 1:
				adds = "C";
				break;
			case 2:
				adds = "B";
				break;
			case 3:
				adds = "B+";
				break;
			case 4:
				adds = "A";
				break;
			case 5:
				adds = "A+";
				break;
			case 6:
				adds = "S";	
				break;
			default:
				adds = "X";
		}
		return "Cure " + adds + " Plan";
	}
	@Override
	public int cost(long population)
	{
		return ((int) Math.round(population / 50.0)) * getGrade().intValue();
	}
	@Override
	public boolean hasGrade()
	{
		return true;
	}
	@Override
	public String description()
	{
		return "보건 정책입니다.\n질병 발생 확률을 줄이며, 질병 자체 제거 효과도 있습니다.";
	}
}
