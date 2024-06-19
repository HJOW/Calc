package accumulate;

public class Store extends Section
{
	private static final long serialVersionUID = -793842419123317721L;
	private Integer capacity = null;
	private Integer grade = null;
	private Integer power = null;
	private transient int now_customer = 0;
	
	public Store()
	{
		
	}
	public Store(int grade)
	{
		int size = 12;
		for(int i=0; i<grade; i++)
			size = size - 1;
		this.capacity = new Integer(size);
		this.grade = new Integer(grade);
		now_customer = 0;
		power = new Integer(grade + 1);
		setName("Store " + String.valueOf((grade + 1)));
	}
	public Integer getCapacity()
	{
		return capacity;
	}

	public void setCapacity(Integer capacity)
	{
		this.capacity = capacity;
	}

	public Integer getGrade()
	{
		return grade;
	}

	public void setGrade(Integer grade)
	{
		this.grade = grade;
	}

	public Integer getNow_customer()
	{
		return now_customer;
	}

	public void setNow_customer(Integer now_customer)
	{
		this.now_customer = now_customer;
	}
	public Integer getPower()
	{
		return power;
	}
	public void setPower(Integer power)
	{
		this.power = power;
	}
	public int power()
	{
		return power.intValue();
	}
}
