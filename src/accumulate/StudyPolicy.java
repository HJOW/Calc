package accumulate;

public abstract class StudyPolicy extends Policy
{
	private static final long serialVersionUID = -1016672991762746095L;

	public StudyPolicy()
	{
		super();
	}
	public StudyPolicy(int grade)
	{
		super();
		this.setGrade(new Integer(grade));
	}
	@Override
	public int intelligent()
	{
		return getGrade();
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
		return adds;
	}
	@Override
	public int cost(long population)
	{
		return (int) Math.round(Math.pow(population, 1.0 + (getGrade().intValue() / 500.0)));
	}

}
