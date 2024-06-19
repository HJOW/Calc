package accumulate;

public class ReadingCampaign extends StudyPolicy
{
	private static final long serialVersionUID = -2839052411159949051L;

	public ReadingCampaign()
	{
		super(2);
	}
	@Override
	public String toBasicString()
	{
		return "Reading Campaign";
	}
	@Override
	public boolean hasGrade()
	{
		return false;
	}
	@Override
	public String description()
	{
		return "독서를 장려합니다.\n약간의 초등, 중등 교육 효과를 가집니다.";
	}
}
