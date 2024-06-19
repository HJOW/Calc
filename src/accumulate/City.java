package accumulate;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import setting.Lint;

public class City implements Serializable
{
	private static final long serialVersionUID = -669222987970451546L;
	
	private BigInteger id = null;
	private String name = "";
	private Section[] sections = null;
	private Integer section_size = null;
	private Policy[] policies = null;
	private volatile BigInteger budget = null;
	private BigInteger time = null;
	private BigInteger food = null;
	private BigInteger tax = null;
	private transient BigInteger income = null;
	private transient BigInteger outcome = null;
	private double randomV = 0.0;
	private BigInteger rich_level;
	private Vector<Person> rich_target;
	private int rich_index;
	private BigInteger rich_sum;

	private long populate;
	public City()
	{
		
	}
	public City(String name, int size, BigInteger id)
	{
		this.name = name;
		this.id = Lint.copy(id);
		
				
		section_size = new Integer(size);
		sections = new Section[size];
		
		policies = new Policy[5];
		policies[0] = new CuringPolicy();
		policies[1] = new ReadingCampaign();
		policies[2] = new SocialSecurityService();
		policies[3] = new ExercisePolicy();
		policies[4] = new Conscription();
		
		//System.out.println("Policy");
		
		for(int i=0; i<section_size.intValue(); i++)
		{
			sections[i] = new EmptySection();
		}
		budget = Lint.big(10000);
		time = Lint.big(0);
		food = Lint.big(10);
		tax = Lint.big(1);
		
		sections[0] = new PowerPlant(6);
		sections[1] = new Warehouse(6);
		sections[2] = new Farm(6);
		sections[3] = new Resident(6);
		((Resident) sections[3]).getLiving()[0] = Person.newPerson(40, 30);
		sections[4] = new Store(6);
		sections[5] = new Cure(6);
	}
	public static City newInstance(String name, int size, BigInteger id)
	{
		return new City(name, size, id);
	}
	public void addPolicy(Policy policy)
	{
		Policy[] newArr = new Policy[policies.length + 1];
		for(int i=0; i<policies.length; i++)
		{
			newArr[i] = policies[i];
		}
		newArr[newArr.length - 1] = policy;
		policies = newArr;
	}
	public String[] policyList()
	{
		String[] strs = new String[policies.length];
		for(int i=0; i<strs.length; i++)
		{
			if(policies[i] == null) strs[i] = "";
			strs[i] = policies[i].toBasicString();
		}
		return strs;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Section[] getSections()
	{
		return sections;
	}
	public void setSections(Section[] sections)
	{
		this.sections = sections;
	}
	public Integer getSize_x()
	{
		return section_size;
	}
	public void setSize_x(Integer size_x)
	{
		this.section_size = size_x;
	}
	public BigInteger getBudget()
	{
		return budget;
	}
	public void setBudget(BigInteger budget)
	{
		this.budget = budget;
	}
	public BigInteger getId()
	{
		return id;
	}
	public void setId(BigInteger id)
	{
		this.id = id;
	}
	public Integer getSection_size()
	{
		return section_size;
	}
	public void setSection_size(Integer section_size)
	{
		this.section_size = section_size;
	}
	public BigInteger getTime()
	{
		return time;
	}
	public void setTime(BigInteger time)
	{
		this.time = time;
	}
	public BigInteger getFood()
	{
		return food;
	}
	public void setFood(BigInteger food)
	{
		this.food = food;
	}
	public BigInteger getTax()
	{
		return tax;
	}
	public void setTax(BigInteger tax)
	{
		this.tax = tax;
	}
	public Policy[] getPolicies()
	{
		return policies;
	}
	public void setPolicies(Policy[] policies)
	{
		this.policies = policies;
	}
	public BigInteger income()
	{
		return income;
	}
	public BigInteger outcome()
	{
		return outcome;
	}
	public BigInteger population()
	{
		BigInteger result = Lint.big(0);
		for(int i=0; i<section_size.intValue(); i++)
		{
			if(sections[i] instanceof Resident)
			{
				Resident residents = (Resident) sections[i];
				for(int j=0; j<residents.getCapacity().intValue(); j++)
				{
					if(residents.getLiving()[j] != null) result = result.add(Lint.big(1));
				}
			}
		}
		return result;
	}
	public Vector<Person> people()
	{
		Vector<Person> newOne = new Vector<Person>();
		for(int i=0; i<section_size.intValue(); i++)
		{
			if(sections[i] instanceof Resident)
			{
				Resident residents = (Resident) sections[i];
				for(int j=0; j<residents.getCapacity().intValue(); j++)
				{
					if(residents.getLiving()[j] != null) newOne.add(residents.getLiving()[j]);
				}
			}
		}
		return newOne;
	}
	public void next() throws Exception
	{
		BigInteger target_id;
		Vector<Person> people = people();
		income = Lint.big(0);
		outcome = Lint.big(0);
		int calc_grade = 0;
		
		// Initialize
		for(int i=0; i<people.size(); i++)
		{
			people.get(i).worked = false;
		}
		for(int i=0; i<sections.length; i++)
		{
			sections[i].worked = false;
			if(sections[i] instanceof Store)
			{
				((Store) sections[i]).setNow_customer(new Integer(0));
			}
		}
		
		// Resident
		try
		{
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Resident)
				{
					Resident residents = (Resident) sections[i];
					for(int j=0; j<residents.getLiving().length; j++)
					{
						if(residents.getLiving()[j] != null)
						{
							calc_grade = residents.getLiving()[j].getIntelligent() / 10;
							if(calc_grade > 5) calc_grade = 5;
							if(residents.getGrade().intValue() < calc_grade - 2) // Poor grade --> lower happiness
							{
								residents.getLiving()[j].setHappiness(new Integer(residents.getLiving()[j].getHappiness().intValue() - 1));
							}
							else if(residents.getGrade().intValue() > calc_grade) // Good grade --> higher happiness
							{
								residents.getLiving()[j].setHappiness(new Integer(residents.getLiving()[j].getHappiness().intValue() + (residents.getGrade().intValue() - calc_grade)));
							}
						}
					}
					if(residents.nowLiving() < residents.getCapacity().intValue())
					{
						for(int j=0; j<residents.getCapacity().intValue(); j++)
						{
							if(residents.getLiving()[j] == null)
							{
								residents.getLiving()[j] = Person.newPerson();
								break;
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		boolean remove_exist = false;
		// Hungry
		try
		{
			for(int i=0; i<people.size(); i++)
			{
				people.get(i).worked = false;
			}
			for(int i=0; i<people.size(); i++)
			{
				if(! people.get(i).worked)
				{
					// Decrease HP
					people.get(i).setHp(new Integer(people.get(i).getHp().intValue() - 1));
					people.get(i).worked = true;
					
					// If HP <= 0, eliminate
					if(people.get(i).getHp().intValue() <= 0)
					{				
						remove_exist = false;
						target_id = people.get(i).getId();
						people.remove(i);
						for(int j=0; j<section_size.intValue(); j++)
						{
							if(sections[j] instanceof Resident)
							{
								Resident residents = (Resident) sections[j];
								for(int k=0; k<residents.getCapacity().intValue(); k++)
								{
									if(residents.getLiving()[k].getId().compareTo(target_id) == 0)
									{
										residents.getLiving()[k] = null;
										people = people();
										i = 0;
										remove_exist = true;
										break;
									}
								}
								if(remove_exist) break;
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Consume
		
		long value;
		try
		{
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Store)
				{
					Store store = (Store) sections[i];
					store.setNow_customer(new Integer(0));
				}
			}
			value = 0;
			boolean find = false;
			int store_index = -1;
			for(int i=0; i<people.size(); i++)
			{
				find = false;
				value = people.get(i).getIntelligent().intValue();
				if(value < people.get(i).getStrength().intValue()) value = people.get(i).getStrength().intValue();
				if(people.get(i).getBudget().compareTo(Lint.big( 1 + value)) >= 0 && people.get(i).getHappiness().intValue() <= 197)
				{
					for(int j=0; j<section_size.intValue(); j++)
					{
						if(sections[j] instanceof Store)
						{
							Store store = (Store) sections[j];
							//System.out.println("Store " + j + ", " + store.getNow_customer());
							if(store.getNow_customer().intValue() < store.getCapacity().intValue())
							{
								store_index = j;
								find = true;
								store.setNow_customer(new Integer(store.getNow_customer() + 1));
							}
						}
					}
				}
				//System.out.println("People" + i + ", " + find);
				if(find)
				{
					Store store = (Store) sections[store_index];
					calc_grade = people.get(i).getIntelligent() / 10;
					people.get(i).setBudget(people.get(i).getBudget().subtract(Lint.big(1 + value)));
					people.get(i).setBudget(people.get(i).getBudget().subtract(tax.multiply(Lint.big(1 + value))));
					budget = budget.add(tax.multiply(Lint.big(1 + value)));
					income = income.add(tax.multiply(Lint.big(1 + value)));
					people.get(i).setHappiness(new Integer(people.get(i).getHappiness().intValue() + 5 + (store.getGrade().intValue() - calc_grade)));
					store.setNow_customer(new Integer(store.getNow_customer().intValue() + 1));
					break;
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		// Working
		
		try
		{
			for(int i=0; i<people.size(); i++)
			{
				people.get(i).worked = false;
			}
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Store)
				{
					Store store = (Store) sections[i];
					store.setNow_customer(new Integer(0));
				}
			}
			int howManyWorked = 0;
			calc_grade = 0;
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Store)
				{
					Store store = (Store) sections[i];
					howManyWorked = 0;
					for(int j=0; j<people.size(); j++)
					{
						if(people.get(j).worked) howManyWorked++;
					}
					if(howManyWorked < people.size() && store.getNow_customer().intValue() < store.getCapacity().intValue())
					{					
						for(int j=0; j<people.size(); j++)
						{
							if(! people.get(j).worked)
							{
								people.get(j).worked = true;
								value = people.get(j).getIntelligent().intValue() * 2;
								value = value + people.get(j).getStrength().intValue();
								value++;
								people.get(j).setBudget(people.get(j).getBudget().add(Lint.big(value).multiply(Lint.big(2))));
								people.get(j).setHappiness(new Integer(people.get(j).getHappiness().intValue() - 1));
								calc_grade = people.get(j).getIntelligent().intValue() / 10;
								if(calc_grade >= 4) calc_grade = 4;
								if(store.getGrade().intValue() < calc_grade) // If store grade is not enough, happiness is become lower
									people.get(j).setHappiness(new Integer(people.get(j).getHappiness().intValue() - (calc_grade - store.getGrade().intValue())));
								store.setNow_customer(new Integer(store.getNow_customer() + 1));
								break;
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Eat
		try
		{
			for(int i=0; i<people.size(); i++)
			{
				people.get(i).worked = false;
			}
			for(int i=0; i<people.size(); i++)
			{
				if(food.compareTo(Lint.big(1)) >= 0 && (! people.get(i).worked))
				{
					food = food.subtract(Lint.big(1));
					people.get(i).setHp(new Integer(people.get(i).getHp().intValue() + 2));
					if(people.get(i).getStrength().intValue() < people.get(i).getHp().intValue())
						people.get(i).setHp(new Integer(people.get(i).getStrength().intValue()));
					people.get(i).worked = true;
				}
			}
			for(int i=0; i<people.size(); i++)
			{
				if(! people.get(i).worked) // Cannot eat
				{
					people.get(i).setHappiness(new Integer(people.get(i).getHappiness().intValue() - 1));
					if(people.get(i).getHappiness().intValue() <= 0)
					{
						people.get(i).setHappiness(new Integer(0));
						people.get(i).setHp(new Integer(people.get(i).getHp() - 1));
						
						// If HP <= 0, eliminate
						if(people.get(i).getHp().intValue() <= 0)
						{				
							remove_exist = false;
							target_id = people.get(i).getId();
							people.remove(i);
							for(int j=0; j<section_size.intValue(); j++)
							{
								if(sections[j] instanceof Resident)
								{
									Resident residents = (Resident) sections[i];
									for(int k=0; k<residents.getCapacity().intValue(); k++)
									{
										if(residents.getLiving()[k].getId().compareTo(target_id) == 0)
										{
											residents.getLiving()[k] = null;
											people = people();
											i = 0;
											remove_exist = true;
											break;
										}
									}
									if(remove_exist) break;
								}
							}
						}
					}
				}
			}
			people = people();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Create food
		try
		{
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Farm)
				{
					Farm farm = (Farm) sections[i];
					food = food.add(farm.getCapacity());				
				}
			}
			
			// Calculate Warehouse
			BigInteger ware_cap = Lint.big(0);
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Warehouse)
				{
					ware_cap = ware_cap.add(((Warehouse) sections[i]).getCapacity());
				}
			}
			
			if(food.compareTo(ware_cap) >= 1)
			{
				food = ware_cap;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Power
		try
		{
			BigInteger powers = Lint.big(0);
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof PowerPlant)
				{
					PowerPlant powerplant = (PowerPlant) sections[i];
					powers = powers.add(powerplant.getCapacity());
				}
			}
			for(int i=0; i<section_size.intValue(); i++)
			{
				sections[i].worked = false;
			}
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(powers.compareTo(Lint.big(sections[i].power())) >= 0)
				{
					powers = powers.subtract(Lint.big(sections[i].power()));
					sections[i].worked = true;
				}
			}
			for(int i=0; i<section_size.intValue(); i++) // Eliminate supplied section
			{
				if(! sections[i].worked)
				{
					sections[i] = new EmptySection();
				}
			}
			people = people();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		// Cure Desease
		try
		{
			BigInteger cure_cap = Lint.big(0);
			for(int i=0; i<section_size.intValue(); i++)
			{
				if(sections[i] instanceof Cure)
				{
					Cure cure = (Cure) sections[i];
					cure_cap = cure_cap.add(cure.getCapacity());
				}
			}
			for(int i=0; i<people.size(); i++)
			{
				if(people.get(i).getDesease().intValue() >= 1)
				{
					if(cure_cap.compareTo(Lint.big(1)) >= 0)
					{
						people.get(i).setDesease(new Integer(0));
						cure_cap = cure_cap.subtract(Lint.big(1));
					}
					else
					{
						people.get(i).setDesease(new Integer(people.get(i).getDesease().intValue() + 1));
						people.get(i).setHappiness(new Integer(people.get(i).getHappiness().intValue() - 1));
						
						if(people.get(i).getHappiness().intValue() <= 0)
						{
							people.get(i).setHappiness(new Integer(0));
							people.get(i).setHp(new Integer(people.get(i).getHp() - 1));
							
							// If HP <= 0, eliminate
							if(people.get(i).getHp().intValue() <= 0)
							{				
								remove_exist = false;
								target_id = people.get(i).getId();
								people.remove(i);
								for(int j=0; j<section_size.intValue(); j++)
								{
									if(sections[j] instanceof Resident)
									{
										Resident residents = (Resident) sections[i];
										for(int k=0; k<residents.getCapacity().intValue(); k++)
										{
											if(residents.getLiving()[k].getId().compareTo(target_id) == 0)
											{
												residents.getLiving()[k] = null;
												people = people();
												i = 0;
												remove_exist = true;
												break;
											}
										}
										if(remove_exist) break;
									}
								}
							}
						}					
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Apply Desease
		try
		{
			value = 0;
			for(int i=0; i<people.size(); i++)
			{
				people.get(i).worked = false;
			}
			for(int i=0; i<people.size(); i++)
			{
				if(! people.get(i).worked)
				{
					if(people.get(i).getDesease().intValue() >= 1)
					{
						people.get(i).setHp(new Integer(people.get(i).getHp().intValue() - people.get(i).getDesease().intValue()));
						people.get(i).setDesease(new Integer(people.get(i).getDesease().intValue() + 1));
						if(people.get(i).getDesease().intValue() >= 5)
						{
							people.get(i).setDesease(new Integer(5));
							value++;
						}
						people.get(i).worked = true;
						// If HP <= 0, eliminate
						if(people.get(i).getHp().intValue() <= 0)
						{				
							remove_exist = false;
							target_id = people.get(i).getId();
							people.remove(i);
							for(int j=0; j<section_size.intValue(); j++)
							{
								if(sections[j] instanceof Resident)
								{
									Resident residents = (Resident) sections[i];
									for(int k=0; k<residents.getCapacity().intValue(); k++)
									{
										if(residents.getLiving()[k].getId().compareTo(target_id) == 0)
										{
											residents.getLiving()[k] = null;
											people = people();
											i = 0;
											remove_exist = true;
											break;
										}
									}
									if(remove_exist) break;
								}
							}
						}
					}
				}
			}
			people = people();
			for(int i=0; i<people.size(); i++)
			{
				if(value <= 0) break;
				if(people.get(i).getDesease().intValue() <= 0)
				{
					people.get(i).setDesease(new Integer(1));
					value--;
					i = 0;
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Make desease
		try
		{
			for(int i=0; i<sections.length; i++)
			{
				if(sections[i] instanceof Resident)
				{
					value = (long) (Math.random() * 100);
					value = value - ((Resident) sections[i]).getGrade().intValue();
					if(policies != null)
					{
						for(int j=0; j<policies.length; j++)
						{
							if(policies[j] == null) continue;
							if((policies[j] instanceof CuringPolicy) && policies[j].getActive().booleanValue())
							{
								CuringPolicy cur = (CuringPolicy) policies[j];
								value = value - (long) cur.cure();
							}
						}
					}
					if(value >= 90)
					{
						for(int j=0; j<((Resident) sections[i]).getLiving().length; j++)
						{
							if(((Resident) sections[i]).getLiving()[j] != null)
							{
								if(((Resident) sections[i]).getLiving()[j].getDesease().intValue() <= 0)
								{
									((Resident) sections[i]).getLiving()[j].setDesease(new Integer(1));
									break;
								}
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Learn
		try
		{
			value = 0;
			for(int i=0; i<people.size(); i++)
			{
				people.get(i).worked = false;
			}
			for(int i=0; i<sections.length; i++)
			{
				if(sections[i] instanceof Academy)
				{
					((Academy) sections[i]).used = 0;
				}
			}
			for(int i=0; i<people.size(); i++)
			{
				for(int j=0; j<sections.length; j++)
				{
					if(people.get(i).worked) break;
					if(sections[j] instanceof Academy)
					{
						Academy ac = (Academy) sections[j];
						if(Lint.big(ac.used).compareTo(ac.getCapacity()) >= 0)
						{
							continue;
						}
						else
						{
							if(((people.get(i).getIntelligent() / 10) < (ac.getGrade().longValue() + 1))
									&& ((people.get(i).getIntelligent() / 10) > (ac.getGrade().longValue() - 2)))
							{
								people.get(i).worked = true;
								people.get(i).setHappiness(new Integer(people.get(i).getHappiness().intValue() + 1));
								ac.used++;
							}
						}
						
					}
				}
			}
			for(int i=0; i<people.size(); i++)
			{
				if(people.get(i).worked)
				{
					people.get(i).setIntelligent(new Integer(people.get(i).getIntelligent() + 1));
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Policies
		try
		{
			Policy pol;
			
			people = people();
			rich_level = Lint.big(0);
			rich_index = 0;
			if(rich_target == null)
				rich_target = new Vector<Person>();
			else
				rich_target.clear();
			rich_sum = Lint.big(0);
			value = 0;
			randomV = 0.0;
			
			while(people.size() >= 1)
			{
				rich_level = Lint.big(0);
				for(int i=0; i<people.size(); i++)
				{
					if(people.get(i).getBudget().compareTo(rich_level) >= 0)
					{
						rich_level = Lint.copy(people.get(i).getBudget());
						rich_index = i;					
					}
				}	
				rich_target.add(people.get(rich_index));
				people.remove(rich_index);
			}
			for(int i=0; i<rich_target.size(); i++)
			{
				rich_target.get(i).rich_level = Lint.big(i);
				rich_sum = rich_sum.add(rich_target.get(i).getBudget());
			}
			//System.out.println("빠져 나옴");
			people = people();
			populate = population().longValue();
			if(policies != null)
			{
				if(policies.length >= 1)
				{
					for(int i=0; i<policies.length; i++)
					{
						pol = policies[i];
						if(pol == null) continue;
						if(pol.getActive().booleanValue())
						{
							budget = budget.subtract(Lint.big(pol.cost(populate)));
							outcome = outcome.add(Lint.big(pol.cost(populate)));
							
							if(pol instanceof CuringPolicy)
							{
								CuringPolicy cur = (CuringPolicy) pol;
								value = (long) cur.cure();
								for(int j=0; j<people.size(); j++)
								{
									if(value <= 0) break;
									if(people.get(j).getDesease().intValue() >= 1)
									{
										people.get(j).setDesease(new Integer(0));
										value--;
									}
								}
							}
							if(pol instanceof ReadingCampaign)
							{
								ReadingCampaign red = (ReadingCampaign) pol;
								for(int j=0; j<people.size(); j++)
								{
									if(people.get(j).getIntelligent().intValue() / 10 >= red.getGrade().intValue() - 2
											&& people.get(j).getIntelligent().intValue() / 10 <= red.getGrade().intValue() + 2)
									{
										people.get(j).setIntelligent(new Integer(people.get(j).getIntelligent().intValue() + 1));
									}
								}
							}
							if(pol instanceof SocialSecurityService)
							{
								SocialSecurityService sc = (SocialSecurityService) pol;
								for(int j=0; j<people.size(); j++)
								{
									people.get(j).setBudget(people.get(j).getBudget().subtract(Lint.big(sc.tax(rich_sum.divide(population()).longValue()))));								
									people.get(j).setBudget(people.get(j).getBudget().add(Lint.big(sc.give((people.get(j).rich_level.intValue()), population().longValue(), rich_sum.divide(population()).longValue()))));
									if(people.get(j).getBudget().compareTo(Lint.big(0)) < 0)
									{
										people.get(j).setBudget(Lint.big(0));
									}
								}
							}
							if(pol instanceof ExercisePolicy)
							{
								for(int j=0; j<people.size(); j++)
								{								
									if(pol instanceof Conscription)
									{
										people.get(j).setStrength(new Integer(people.get(j).getStrength().intValue() + 1));	
										randomV = Math.random();
										if(randomV >= ( ((double) people.get(j).getIntelligent().intValue()) / 100.0 )
												|| randomV >= 0.9)
										{
											people.get(j).setHappiness(new Integer(people.get(j).getHappiness().intValue() - 1));								
										}
									}
									else
									{
										randomV = Math.random();
										if(randomV >= ( ((double) people.get(j).getIntelligent().intValue()) / 100.0 )
												|| randomV >= 0.9)
										{
											people.get(j).setStrength(new Integer(people.get(j).getStrength().intValue() + 1));									
										}
									}
									
								}
							}
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// Cut happiness which is so big
		try
		{
			for(int i=0; i<people.size(); i++)
			{
				if(people.get(i).getHappiness().intValue() >= 200)
				{
					people.get(i).setHappiness(new Integer(200));
				}
			}
			
			time = time.add(Lint.big(1));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
