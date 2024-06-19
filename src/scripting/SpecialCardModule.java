package scripting;

public class SpecialCardModule extends ScriptModule
{
	private static final long serialVersionUID = 3603727895189479977L;
	private Character op;
	private Integer num;
	private Integer count;
	public Character getOp()
	{
		return op;
	}
	public void setOp(Character op)
	{
		this.op = op;
	}
	public Integer getNum()
	{
		return num;
	}
	public void setNum(Integer num)
	{
		this.num = num;
	}
	public Integer getCount()
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	@Override
	public ScriptModule clone()
	{
		SpecialCardModule newOne = new SpecialCardModule();
		newOne.setName(new String(getName()));
		newOne.setDescription(new String(getDescription()));
		newOne.setScripts(new String(getScripts()));
		newOne.setAuthorized(new Long(getAuthorized().longValue()));
		newOne.op = new Character(op.charValue());
		newOne.num = new Integer(num.intValue());
		newOne.count = new Integer(count.intValue());
		return newOne;
	}
}
