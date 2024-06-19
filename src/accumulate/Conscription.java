package accumulate;

public class Conscription extends ExercisePolicy
{
	private static final long serialVersionUID = -1182959767811143653L;
	public Conscription()
	{
		super();
	}
	@Override
	public String toBasicString()
	{
		return "Conscription";
	}
	@Override
	public int cost(long population)
	{
		return 2 + (int) Math.round(population / 100.0);
	}
	@Override
	public boolean hasGrade()
	{
		return false;
	}
	@Override
	public String description()
	{
		return "전 인구가 의무적으로 훈련을 받게 됩니다.\n체력 향상 효과는 확실하지만 행복도가 감소할 수 있습니다.";
	} 
}
