package reflex;

import java.util.List;
import java.util.StringTokenizer;

public class AUserDefinedShip extends UserDefinedShip
{
	private static final long serialVersionUID = 7219704095013725382L;
	private Double ctrl_missile_left_w1, ctrl_missile_left_w2, ctrl_missile_left_w3;
	private Double ctrl_missile_right_w1, ctrl_missile_right_w2, ctrl_missile_right_w3;
	private Double ctrl_missile_center_w1, ctrl_missile_center_w2, ctrl_missile_center_w3;
	private Double ctrl_missile_shift_w1, ctrl_missile_shift_w2, ctrl_missile_shift_w3;
	private Double ctrl_enemy_left_w1, ctrl_enemy_left_w2, ctrl_enemy_left_w3;
	private Double ctrl_enemy_right_w1, ctrl_enemy_right_w2, ctrl_enemy_right_w3;

	public AUserDefinedShip()
	{
		super();
	}
	public AUserDefinedShip(String name, List<Enemy> enemies, Weapon[] weapons)
	{
		super(name, enemies, weapons);
	}
	public AUserDefinedShip(String name, List<Enemy> enemies, Weapon[] weapons, int hp_capacity, int energy_capacity, double speed_capacity)
	{
		super(name, enemies, weapons, hp_capacity, energy_capacity, speed_capacity);
	}
	
	public AUserDefinedShip(String commands, List<Enemy> enemies) throws NullPointerException
	{		
		super(commands, enemies);
		StringTokenizer lineToken, equalToken;
		String lineTarget, option, contents;
		lineToken = new StringTokenizer(commands, "\n");
		while(lineToken.hasMoreTokens())
		{
			lineTarget = lineToken.nextToken();
			if((lineTarget != null) && (! lineTarget.trim().startsWith("#")) && (! lineTarget.trim().equals("")))
			{
				equalToken = new StringTokenizer(lineTarget, "||");
				option = equalToken.nextToken().trim();
				
				if(equalToken.hasMoreTokens())
				{
					contents = equalToken.nextToken().trim();
				}
				else
				{
					contents = null;
				}
				
				if(contents == null)
				{
					
				}
				else
				{					
					if(option.equalsIgnoreCase("auto_missile_left_w1"))
					{
						try
						{
							ctrl_missile_left_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_left_w2"))
					{
						try
						{
							ctrl_missile_left_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_left_w3"))
					{
						try
						{
							ctrl_missile_left_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_right_w1"))
					{
						try
						{
							ctrl_missile_right_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_right_w2"))
					{
						try
						{
							ctrl_missile_right_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_right_w3"))
					{
						try
						{
							ctrl_missile_right_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_center_w1"))
					{
						try
						{
							ctrl_missile_center_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_center_w2"))
					{
						try
						{
							ctrl_missile_center_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_center_w3"))
					{
						try
						{
							ctrl_missile_center_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_shift_w1"))
					{
						try
						{
							ctrl_missile_shift_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_shift_w2"))
					{
						try
						{
							ctrl_missile_shift_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_shift_w3"))
					{
						try
						{
							ctrl_missile_shift_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_left_w1"))
					{
						try
						{
							ctrl_missile_left_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_left_w2"))
					{
						try
						{
							ctrl_missile_left_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_missile_left_w3"))
					{
						try
						{
							ctrl_missile_left_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_enemy_right_w1"))
					{
						try
						{
							ctrl_enemy_right_w1 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_enemy_right_w2"))
					{
						try
						{
							ctrl_enemy_right_w2 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auto_enemy_right_w3"))
					{
						try
						{
							ctrl_enemy_right_w3 = new Double(contents);
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}				
			}
		}
		init();
	}
	
	public double controlif_missile_right()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_missile_right_w1 != null) return ctrl_missile_right_w1.doubleValue();
				break;
			case 2:
				if(ctrl_missile_right_w2 != null) return ctrl_missile_right_w2.doubleValue();
				break;
			case 3:
				if(ctrl_missile_right_w3 != null) return ctrl_missile_right_w3.doubleValue();
				break;
		}
		return -1.0;
	}
	public double controlif_missile_left()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_missile_left_w1 != null) return ctrl_missile_left_w1.doubleValue();
				break;
			case 2:
				if(ctrl_missile_left_w2 != null) return ctrl_missile_left_w2.doubleValue();
				break;
			case 3:
				if(ctrl_missile_left_w3 != null) return ctrl_missile_left_w3.doubleValue();
				break;
		}
		return -1.0;
	}
	public double controlif_missile_centers()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_missile_center_w1 != null) return ctrl_missile_center_w1.doubleValue();
				break;
			case 2:
				if(ctrl_missile_center_w2 != null) return ctrl_missile_center_w2.doubleValue();
				break;
			case 3:
				if(ctrl_missile_center_w3 != null) return ctrl_missile_center_w3.doubleValue();
				break;
		}
		return 2.0;
	}
	public double controlif_missile_centers_shift()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_missile_shift_w1 != null) return ctrl_missile_shift_w1.doubleValue();
				break;
			case 2:
				if(ctrl_missile_shift_w2 != null) return ctrl_missile_shift_w2.doubleValue();
				break;
			case 3:
				if(ctrl_missile_shift_w3 != null) return ctrl_missile_shift_w3.doubleValue();
				break;
		}
		return 0.4;
	}
	public double controlif_enemy_left()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_enemy_left_w1 != null) return ctrl_enemy_left_w1.doubleValue();
				break;
			case 2:
				if(ctrl_enemy_left_w2 != null) return ctrl_enemy_left_w2.doubleValue();
				break;
			case 3:
				if(ctrl_enemy_left_w3 != null) return ctrl_enemy_left_w3.doubleValue();
				break;
		}
		return 0.1;
	}
	public double controlif_enemy_right()
	{
		switch(getMode())
		{
			case 1:
				if(ctrl_enemy_right_w1 != null) return ctrl_enemy_right_w1.doubleValue();
				break;
			case 2:
				if(ctrl_enemy_right_w2 != null) return ctrl_enemy_right_w2.doubleValue();
				break;
			case 3:
				if(ctrl_enemy_right_w3 != null) return ctrl_enemy_right_w3.doubleValue();
				break;
		}
		return 0.1;
	}
	public Double getCtrl_missile_left_w1()
	{
		return ctrl_missile_left_w1;
	}
	public void setCtrl_missile_left_w1(Double ctrl_missile_left_w1)
	{
		this.ctrl_missile_left_w1 = ctrl_missile_left_w1;
	}
	public Double getCtrl_missile_left_w2()
	{
		return ctrl_missile_left_w2;
	}
	public void setCtrl_missile_left_w2(Double ctrl_missile_left_w2)
	{
		this.ctrl_missile_left_w2 = ctrl_missile_left_w2;
	}
	public Double getCtrl_missile_left_w3()
	{
		return ctrl_missile_left_w3;
	}
	public void setCtrl_missile_left_w3(Double ctrl_missile_left_w3)
	{
		this.ctrl_missile_left_w3 = ctrl_missile_left_w3;
	}
	public Double getCtrl_missile_right_w1()
	{
		return ctrl_missile_right_w1;
	}
	public void setCtrl_missile_right_w1(Double ctrl_missile_right_w1)
	{
		this.ctrl_missile_right_w1 = ctrl_missile_right_w1;
	}
	public Double getCtrl_missile_right_w2()
	{
		return ctrl_missile_right_w2;
	}
	public void setCtrl_missile_right_w2(Double ctrl_missile_right_w2)
	{
		this.ctrl_missile_right_w2 = ctrl_missile_right_w2;
	}
	public Double getCtrl_missile_right_w3()
	{
		return ctrl_missile_right_w3;
	}
	public void setCtrl_missile_right_w3(Double ctrl_missile_right_w3)
	{
		this.ctrl_missile_right_w3 = ctrl_missile_right_w3;
	}
	public Double getCtrl_missile_center_w1()
	{
		return ctrl_missile_center_w1;
	}
	public void setCtrl_missile_center_w1(Double ctrl_missile_center_w1)
	{
		this.ctrl_missile_center_w1 = ctrl_missile_center_w1;
	}
	public Double getCtrl_missile_center_w2()
	{
		return ctrl_missile_center_w2;
	}
	public void setCtrl_missile_center_w2(Double ctrl_missile_center_w2)
	{
		this.ctrl_missile_center_w2 = ctrl_missile_center_w2;
	}
	public Double getCtrl_missile_center_w3()
	{
		return ctrl_missile_center_w3;
	}
	public void setCtrl_missile_center_w3(Double ctrl_missile_center_w3)
	{
		this.ctrl_missile_center_w3 = ctrl_missile_center_w3;
	}
	public Double getCtrl_missile_shift_w1()
	{
		return ctrl_missile_shift_w1;
	}
	public void setCtrl_missile_shift_w1(Double ctrl_missile_shift_w1)
	{
		this.ctrl_missile_shift_w1 = ctrl_missile_shift_w1;
	}
	public Double getCtrl_missile_shift_w2()
	{
		return ctrl_missile_shift_w2;
	}
	public void setCtrl_missile_shift_w2(Double ctrl_missile_shift_w2)
	{
		this.ctrl_missile_shift_w2 = ctrl_missile_shift_w2;
	}
	public Double getCtrl_missile_shift_w3()
	{
		return ctrl_missile_shift_w3;
	}
	public void setCtrl_missile_shift_w3(Double ctrl_missile_shift_w3)
	{
		this.ctrl_missile_shift_w3 = ctrl_missile_shift_w3;
	}
	public Double getCtrl_enemy_left_w1()
	{
		return ctrl_enemy_left_w1;
	}
	public void setCtrl_enemy_left_w1(Double ctrl_enemy_left_w1)
	{
		this.ctrl_enemy_left_w1 = ctrl_enemy_left_w1;
	}
	public Double getCtrl_enemy_left_w2()
	{
		return ctrl_enemy_left_w2;
	}
	public void setCtrl_enemy_left_w2(Double ctrl_enemy_left_w2)
	{
		this.ctrl_enemy_left_w2 = ctrl_enemy_left_w2;
	}
	public Double getCtrl_enemy_left_w3()
	{
		return ctrl_enemy_left_w3;
	}
	public void setCtrl_enemy_left_w3(Double ctrl_enemy_left_w3)
	{
		this.ctrl_enemy_left_w3 = ctrl_enemy_left_w3;
	}
	public Double getCtrl_enemy_right_w1()
	{
		return ctrl_enemy_right_w1;
	}
	public void setCtrl_enemy_right_w1(Double ctrl_enemy_right_w1)
	{
		this.ctrl_enemy_right_w1 = ctrl_enemy_right_w1;
	}
	public Double getCtrl_enemy_right_w2()
	{
		return ctrl_enemy_right_w2;
	}
	public void setCtrl_enemy_right_w2(Double ctrl_enemy_right_w2)
	{
		this.ctrl_enemy_right_w2 = ctrl_enemy_right_w2;
	}
	public Double getCtrl_enemy_right_w3()
	{
		return ctrl_enemy_right_w3;
	}
	public void setCtrl_enemy_right_w3(Double ctrl_enemy_right_w3)
	{
		this.ctrl_enemy_right_w3 = ctrl_enemy_right_w3;
	}
}
