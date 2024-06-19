package accumulate;

public class SocialSecurityService extends Policy
{
	private static final long serialVersionUID = -7669602150820157649L;
	

	public SocialSecurityService()
	{
		super();
	}
	public SocialSecurityService(int grade)
	{
		super();
		setGrade(new Integer(grade));
	}
	@Override
	public long give(long rank, long population, long average) 
	{
		//return (int) Math.round(Math.pow((double) getGrade(), -1.0 * grade) * tax() * 2);
		return (int) Math.round(Math.pow((double) getGrade().intValue(), rank / population) * tax(average) * 2);
	}
	@Override
	public String toBasicString()
	{
		return "Social Security Service";
	}
	@Override
	public int cost(long population)
	{
		return (int) Math.pow(population , 1.0 + (getGrade().intValue() / 20.0));
	}
	@Override
	public int tax(long average)
	{
		return (int) Math.round((double) (getGrade().intValue() * (average / 100.0)));
	}
	@Override
	public boolean hasGrade()
	{
		return true;
	}
	@Override
	public String description()
	{
		return "사회보장제도입니다.\n자본의 균형을 유지하며, 모든 인구에게 부자가 될 기회를 제공합니다.";
	}
}
