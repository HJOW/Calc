package accumulate;

public class Resident extends Section
{
	private static final long serialVersionUID = 5962989233217348863L;
	private Integer capacity = null;
	private Integer grade = null;
	private Person[] living = null;
	private Integer power = null;
	
	public Resident()
	{
		
	}
	public Resident(int grade)
	{
		int size = 10;
		for(int i=0; i<grade; i++)
		{
			size = size - 1;
		}
		
		capacity = new Integer(size);
		this.grade = new Integer(grade);
		living = new Person[size];
		for(int i=0; i<size; i++)
		{
			living[i] = null;
		}
		power = new Integer(grade + 1);
		setName("Resident " + String.valueOf((grade + 1)));
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

	public Person[] getLiving()
	{
		return living;
	}

	public void setLiving(Person[] living)
	{
		this.living = living;
	}
	public int nowLiving()
	{
		int results = 0;
		for(int i=0; i<living.length; i++)
		{
			if(living[i] != null) results++;
		}
		return results;
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
