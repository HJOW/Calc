package reflex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.List;
import java.util.StringTokenizer;

import main_classes.CalcWindow;
import setting.DetailedVersionData;
import setting.Lint;
import setting.Setting;

public class UserDefinedShip extends SpaceShip
{
	private static final long serialVersionUID = 3154870331524038543L;
	private String player_name = "";
	private Integer hp_capacity, speed_capacity, energy_capacity;
	private Long authCode;
	private Weapon[] weapons;
	private DetailedVersionData version;
	private Integer shape;
	private Double w2_advantage_bossExist, w2_advantage_enemies9, w2_advantage_enemies8, w2_advantage_enemies7, w2_advantage_enemies6, w2_advantage_underOnes3, w2_advantage_underOnes2, w2_advantage_starts;
	private Double w3_advantage_bossExist, w3_advantage_enemies9, w3_advantage_enemies8, w3_advantage_enemies7, w3_advantage_enemies6, w3_advantage_underOnes3, w3_advantage_underOnes2, w3_advantage_starts;
	private Integer w2_missile_std, w3_missile_std;
	
	
	public UserDefinedShip()
	{
		super();
		this.player_name = "";		
		hp_capacity = new Integer(1000);
		speed_capacity = new Integer(Reflexioner.getSpeed());
		shape = new Integer(0);
		authCode = new Long(0);
		init();
	}
	public UserDefinedShip(String name, List<Enemy> enemies, Weapon[] weapons)
	{
		super(enemies);
		this.player_name = name;		
		hp_capacity = new Integer(1000);
		energy_capacity = new Integer(1000);
		speed_capacity = new Integer(Reflexioner.getSpeed());
		this.weapons = weapons;
		shape = new Integer(0);
		authCode = new Long(0);
		init();
	}
	public UserDefinedShip(String name, List<Enemy> enemies, Weapon[] weapons, int hp_capacity, int energy_capacity, double speed_capacity)
	{
		super(enemies);
		this.player_name = name;		
		this.hp_capacity = new Integer(hp_capacity);
		this.speed_capacity = new Integer((int) Math.round(Reflexioner.getSpeed() * speed_capacity));
		this.energy_capacity = new Integer(energy_capacity);
		this.weapons = weapons;
		shape = new Integer(0);
		authCode = new Long(0);
		init();
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(5);
		setW2_fireDelay(5);
		setW3_fireDelay(5);
	}
	public UserDefinedShip(String commands, List<Enemy> enemies) throws NullPointerException
	{		
		super(enemies);
		StringTokenizer lineToken, equalToken;
		String lineTarget, option, contents, missile1, missile2, missile3;
		lineToken = new StringTokenizer(commands, "\n");
		missile1 = "";
		missile2 = "";
		missile3 = "";
		shape = new Integer(0);
		authCode = new Long(0);
		weapons = new Weapon[3];
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
					if(option.equalsIgnoreCase("name"))
					{
						player_name = new String(contents);
					}
					else if(option.equalsIgnoreCase("hp"))
					{
						try
						{
							this.hp_capacity = new Integer(contents);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("energy"))
					{
						try
						{
							this.energy_capacity = new Integer(contents);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("speed"))
					{
						try
						{
							this.speed_capacity = new Integer(contents);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("size"))
					{
						try
						{
							this.setR(new Integer(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("shape"))
					{						
						try
						{
							this.setShape(new Integer(contents));
						}
						catch(NumberFormatException e)
						{
							if(contents.equalsIgnoreCase("circle") || contents.equalsIgnoreCase("oval"))
							{
								this.setShape(new Integer(0));
							}
							else if(contents.equalsIgnoreCase("rectangle") || contents.equalsIgnoreCase("rect"))
							{
								this.setShape(new Integer(1));
							}
							else
							{
								this.setShape(new Integer(0));
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("auth"))
					{
						try
						{
							this.setAuthCode(new Long(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setAuthCode(new Long(0));
						}
					}
					else if(option.equalsIgnoreCase("version_main"))
					{
						try
						{
							this.getVersion().setV_m(new Integer(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setVersion(new DetailedVersionData());
							try
							{
								this.getVersion().setV_m(new Integer(contents));
							} 
							catch (Exception e1)
							{
								e1.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("version_1"))
					{
						try
						{
							this.getVersion().setV_1(new Integer(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setVersion(new DetailedVersionData());
							try
							{
								this.getVersion().setV_1(new Integer(contents));
							} 
							catch (NumberFormatException e1)
							{
								e1.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("version_2"))
					{
						try
						{
							this.getVersion().setV_2(new Integer(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setVersion(new DetailedVersionData());
							try
							{
								this.getVersion().setV_2(new Integer(contents));
							} 
							catch (NumberFormatException e1)
							{
								e1.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("version_test"))
					{
						try
						{
							this.getVersion().setV_t(new Character(contents.toCharArray()[0]));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setVersion(new DetailedVersionData());
							try
							{
								this.getVersion().setV_t(new Character(contents.toCharArray()[0]));
							} 
							catch (Exception e1)
							{
								e1.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("version_nightly"))
					{
						try
						{
							this.getVersion().setNightly(new Long(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							this.setVersion(new DetailedVersionData());
							try
							{
								this.getVersion().setNightly(new Long(contents));
							} 
							catch (NumberFormatException e1)
							{
								e1.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("version"))
					{
						try
						{
							this.setVersion(new DetailedVersionData(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_bossexist"))
					{
						try
						{
							this.setW2_advantage_bossExist(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_enemies9"))
					{
						try
						{
							this.setW2_advantage_enemies9(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_enemies8"))
					{
						try
						{
							this.setW2_advantage_enemies8(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_enemies7"))
					{
						try
						{
							this.setW2_advantage_enemies7(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_enemies6"))
					{
						try
						{
							this.setW2_advantage_enemies6(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_starts"))
					{
						try
						{
							this.setW2_advantage_starts(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_underones2"))
					{
						try
						{
							this.setW2_advantage_underOnes2(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w2_advantage_underones3"))
					{
						try
						{
							this.setW2_advantage_underOnes3(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}					
					else if(option.equalsIgnoreCase("w3_advantage_bossexist"))
					{
						try
						{
							this.setW3_advantage_bossExist(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_enemies9"))
					{
						try
						{
							this.setW3_advantage_enemies9(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_enemies8"))
					{
						try
						{
							this.setW3_advantage_enemies8(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_enemies7"))
					{
						try
						{
							this.setW3_advantage_enemies7(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_enemies6"))
					{
						try
						{
							this.setW3_advantage_enemies6(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_starts"))
					{
						try
						{
							this.setW3_advantage_starts(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_underones2"))
					{
						try
						{
							this.setW3_advantage_underOnes2(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("w3_advantage_underones3"))
					{
						try
						{
							this.setW3_advantage_underOnes3(new Double(contents));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}					
					else if(option.equalsIgnoreCase("weapon1"))
					{
						missile1 = missile1 + contents + "\n";
					}
					else if(option.equalsIgnoreCase("weapon2"))
					{
						missile2 = missile2 + contents + "\n";
					}
					else if(option.equalsIgnoreCase("weapon3"))
					{
						missile3 = missile3 + contents + "\n";
					}
				}				
			}
		}
		if(! missile1.equals(""))
		{
			weapons[0] = new Weapon(missile1, this);
			setW1_fireDelay(weapons[0].getDelay());
			//System.out.println(weapons[0]);
		}
		if(! missile2.equals(""))
		{
			weapons[1] = new Weapon(missile2, this);
			setW2_fireDelay(weapons[1].getDelay());
			//System.out.println(weapons[1]);
		}
		if(! missile3.equals(""))
		{
			weapons[2] = new Weapon(missile3, this);
			setW3_fireDelay(weapons[2].getDelay());
			//System.out.println(weapons[2]);
		}
		init();
	}
	public String getKeyName()
	{
		return player_name;
	}
	public int getKeyInt()
	{
		return SpaceShip.USERDEFINED;
	}
	public long authorize(long password, boolean setNow)
	{
		long calced_auth = -1;
		if(CalcWindow.getAuthorizedCode(password) >= 1000)
		{
			calced_auth = 0;
			if(hp_capacity != null)
				calced_auth = calced_auth + hp_capacity.intValue();
			
			calced_auth = calced_auth * 31;
			
			if(speed_capacity != null)
				calced_auth = calced_auth + speed_capacity.intValue();
			
			calced_auth = calced_auth / 19;
			
			if(energy_capacity != null)
				calced_auth = calced_auth + energy_capacity.intValue();
			
			calced_auth = calced_auth * 29;
			
			char[] chars = new char[0];
			if(player_name != null)
				chars = player_name.toCharArray();
			
			for(int i=0; i<chars.length; i++)
			{
				calced_auth = calced_auth + (long) chars[i];
				if(i % 3 == 0) calced_auth = calced_auth + 3;
			}
			
			if(w2_advantage_bossExist != null) calced_auth = calced_auth + (long)(w2_advantage_bossExist.doubleValue() * 100);
			if(w2_advantage_enemies9 != null) calced_auth = calced_auth + (long)(w2_advantage_enemies9.doubleValue() * 100);
			if(w2_advantage_enemies8 != null) calced_auth = calced_auth + (long)(w2_advantage_enemies8.doubleValue() * 100);
			if(w2_advantage_enemies7 != null) calced_auth = calced_auth + (long)(w2_advantage_enemies7.doubleValue() * 100);
			if(w2_advantage_enemies6 != null) calced_auth = calced_auth + (long)(w2_advantage_enemies6.doubleValue() * 100);
			if(w2_advantage_starts != null) calced_auth = calced_auth + (long)(w2_advantage_starts.doubleValue() * 100);
			if(w2_advantage_underOnes3 != null) calced_auth = calced_auth + (long)(w2_advantage_underOnes3.doubleValue() * 100);
			if(w2_advantage_underOnes2 != null) calced_auth = calced_auth + (long)(w2_advantage_underOnes2.doubleValue() * 100);
			if(w3_advantage_bossExist != null) calced_auth = calced_auth + (long)(w3_advantage_bossExist.doubleValue() * 100);
			if(w3_advantage_enemies9 != null) calced_auth = calced_auth + (long)(w3_advantage_enemies9.doubleValue() * 100);
			if(w3_advantage_enemies8 != null) calced_auth = calced_auth + (long)(w3_advantage_enemies8.doubleValue() * 100);
			if(w3_advantage_enemies7 != null) calced_auth = calced_auth + (long)(w3_advantage_enemies7.doubleValue() * 100);
			if(w3_advantage_enemies6 != null) calced_auth = calced_auth + (long)(w3_advantage_enemies6.doubleValue() * 100);
			if(w3_advantage_starts != null) calced_auth = calced_auth + (long)(w3_advantage_starts.doubleValue() * 100);
			if(w3_advantage_underOnes3 != null) calced_auth = calced_auth + (long)(w3_advantage_underOnes3.doubleValue() * 100);
			if(w3_advantage_underOnes2 != null) calced_auth = calced_auth + (long)(w3_advantage_underOnes2.doubleValue() * 100);
			
			calced_auth = calced_auth / 43;
			
			if(weapons != null)
			{
				for(int i=0; i<weapons.length; i++)
				{
					calced_auth = calced_auth + weapons[i].getNeed_e();
					calced_auth = calced_auth + weapons[i].getMissile_type();
					calced_auth = calced_auth + weapons[i].getMissile_hp();
					calced_auth = calced_auth + weapons[i].getMin_missile();
					calced_auth = calced_auth + weapons[i].getMax_missile();
					calced_auth = calced_auth + weapons[i].getInterval();
					calced_auth = calced_auth + (long) weapons[i].getDamage_ratio();
					calced_auth = calced_auth + (long) weapons[i].getSize();
					calced_auth = calced_auth + (long) weapons[i].getSpeed();
					if(i % 5 == 0) calced_auth = calced_auth / 5;
					else if(i % 3 == 0) calced_auth = calced_auth / 3;
				}
			}
			
			calced_auth = calced_auth + 283918;
			calced_auth = calced_auth * 3;
						
		}
		
		if(setNow) this.setAuthCode(new Long(calced_auth));
		return calced_auth;
	}
	public boolean isAuthorized()
	{
		long calced_auth = 0;
		long target_auth = 0;
		if(authCode != null)
			target_auth = authCode.longValue();
		
		calced_auth = authorize(1937283 + 1001008, false);		
		
		return (calced_auth == target_auth);
	}
	public void init() throws NullPointerException
	{
		if(getVersion() != null) setVersion(new DetailedVersionData());
		
		this.setMax_hp(hp_capacity.longValue());
		this.setHp(this.getMax_hp());
		this.setHp_heal(1);
		this.setEnergy_heal(2);
		this.setDamage(100);
		this.setMax_accel(speed_capacity.intValue() * 10);
		this.setAccel(speed_capacity.intValue());
		this.setMax_energy(energy_capacity.longValue());
		this.setEnergy(energy_capacity.longValue());
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
	}
	@Override
	public Area area()
	{
		if(shape == null) return super.area();
		switch(shape.intValue())
		{
			case 0:
				return super.area();
			case 1:
				return new Area(new Rectangle(getX(), getY(), getR(), getR()));
			default:
				return super.area();
		}
	}	
	@Override
	public void draw(Graphics g)
	{
		if(shape == null) super.draw(g);
		switch(shape.intValue())
		{
			case 0:
				super.draw(g);
				break;
			case 1:
				g.fillRect(getX(), getY(), getR(), getR());
				break;
			default:
				super.draw(g);
		}	
	}
	@Override
	public List<Missile> fire()
	{
		try
		{
			switch(getMode())
			{
				case 1:
					if(weapons[0].getNeed_e() <= getEnergy())
					{
						setEnergy(getEnergy() - weapons[0].getNeed_e());
						return weapons[0].fire(getDamage(), getMissiles(), getX(), getY(), getR(), Missile.SPACESHIP, getX(), getY(), this);
					}	
					break;
				case 2:
					if(weapons[1].getNeed_e() <= getEnergy())
					{
						setEnergy(getEnergy() - weapons[0].getNeed_e());
						return weapons[1].fire(getDamage(), getMissiles(), getX(), getY(), getR(), Missile.SPACESHIP, getX(), getY(), this);
					}
					break;
				case 3:
					if(weapons[2].getNeed_e() <= getEnergy())
					{
						setEnergy(getEnergy() - weapons[0].getNeed_e());
						return weapons[2].fire(getDamage(), getMissiles(), getX(), getY(), getR(), Missile.SPACESHIP, getX(), getY(), this);
					}
					break;
				default:
					setMode(1);
					return fire();
			}
		} 
		catch (Exception e)
		{			
			e.printStackTrace();
			return null;
		}
		return null;
	}
	@Override
	public String getName(Setting sets)
	{
		return player_name;
	}
	
	public Integer getHp_capacity()
	{
		return hp_capacity;
	}
	public void setHp_capacity(Integer hp_capacity)
	{
		this.hp_capacity = hp_capacity;
	}
	public Integer getSpeed_capacity()
	{
		return speed_capacity;
	}
	public void setSpeed_capacity(Integer speed_capacity)
	{
		this.speed_capacity = speed_capacity;
	}
	public Integer getEnergy_capacity()
	{
		return energy_capacity;
	}
	public void setEnergy_capacity(Integer energy_capacity)
	{
		this.energy_capacity = energy_capacity;
	}
	public Weapon[] getWeapons()
	{
		return weapons;
	}
	public void setWeapons(Weapon[] weapons)
	{
		this.weapons = weapons;
	}
	public Long getAuthCode()
	{
		return authCode;
	}
	public void setAuthCode(Long authCode)
	{
		this.authCode = authCode;
	}
	public DetailedVersionData getVersion()
	{
		return version;
	}
	public void setVersion(DetailedVersionData version)
	{
		this.version = version;
	}
	@Override
	public void setEnemyList(List<Enemy> enemyList)
	{
		super.setEnemyList(enemyList);
		if(weapons != null)
		{
			for(int i=0; i<weapons.length; i++)
			{
				if(weapons[i] != null)
				{
					weapons[i].setSpaceShip(this);
				}
			}
		}
	}
	public Integer getShape()
	{
		return shape;
	}
	public void setShape(Integer shape)
	{
		this.shape = shape;
	}
	public String getPlayer_name()
	{
		return player_name;
	}
	public void setPlayer_name(String player_name)
	{
		this.player_name = player_name;
	}
	public Double getW2_advantage_bossExist()
	{
		return w2_advantage_bossExist;
	}
	public void setW2_advantage_bossExist(Double w2_advantage_bossExist)
	{
		this.w2_advantage_bossExist = w2_advantage_bossExist;
	}
	public Double getW2_advantage_enemies9()
	{
		return w2_advantage_enemies9;
	}
	public void setW2_advantage_enemies9(Double w2_advantage_enemies9)
	{
		this.w2_advantage_enemies9 = w2_advantage_enemies9;
	}
	public Double getW2_advantage_enemies8()
	{
		return w2_advantage_enemies8;
	}
	public void setW2_advantage_enemies8(Double w2_advantage_enemies8)
	{
		this.w2_advantage_enemies8 = w2_advantage_enemies8;
	}
	public Double getW2_advantage_enemies7()
	{
		return w2_advantage_enemies7;
	}
	public void setW2_advantage_enemies7(Double w2_advantage_enemies7)
	{
		this.w2_advantage_enemies7 = w2_advantage_enemies7;
	}
	public Double getW2_advantage_enemies6()
	{
		return w2_advantage_enemies6;
	}
	public void setW2_advantage_enemies6(Double w2_advantage_enemies6)
	{
		this.w2_advantage_enemies6 = w2_advantage_enemies6;
	}
	public Double getW2_advantage_underOnes3()
	{
		return w2_advantage_underOnes3;
	}
	public void setW2_advantage_underOnes3(Double w2_advantage_underOnes3)
	{
		this.w2_advantage_underOnes3 = w2_advantage_underOnes3;
	}
	public Double getW2_advantage_underOnes2()
	{
		return w2_advantage_underOnes2;
	}
	public void setW2_advantage_underOnes2(Double w2_advantage_underOnes2)
	{
		this.w2_advantage_underOnes2 = w2_advantage_underOnes2;
	}
	public Double getW2_advantage_starts()
	{
		return w2_advantage_starts;
	}
	public void setW2_advantage_starts(Double w2_advantage_starts)
	{
		this.w2_advantage_starts = w2_advantage_starts;
	}
	public Double getW3_advantage_bossExist()
	{
		return w3_advantage_bossExist;
	}
	public void setW3_advantage_bossExist(Double w3_advantage_bossExist)
	{
		this.w3_advantage_bossExist = w3_advantage_bossExist;
	}
	public Double getW3_advantage_enemies9()
	{
		return w3_advantage_enemies9;
	}
	public void setW3_advantage_enemies9(Double w3_advantage_enemies9)
	{
		this.w3_advantage_enemies9 = w3_advantage_enemies9;
	}
	public Double getW3_advantage_enemies8()
	{
		return w3_advantage_enemies8;
	}
	public void setW3_advantage_enemies8(Double w3_advantage_enemies8)
	{
		this.w3_advantage_enemies8 = w3_advantage_enemies8;
	}
	public Double getW3_advantage_enemies7()
	{
		return w3_advantage_enemies7;
	}
	public void setW3_advantage_enemies7(Double w3_advantage_enemies7)
	{
		this.w3_advantage_enemies7 = w3_advantage_enemies7;
	}
	public Double getW3_advantage_enemies6()
	{
		return w3_advantage_enemies6;
	}
	public void setW3_advantage_enemies6(Double w3_advantage_enemies6)
	{
		this.w3_advantage_enemies6 = w3_advantage_enemies6;
	}
	public Double getW3_advantage_underOnes3()
	{
		return w3_advantage_underOnes3;
	}
	public void setW3_advantage_underOnes3(Double w3_advantage_underOnes3)
	{
		this.w3_advantage_underOnes3 = w3_advantage_underOnes3;
	}
	public Double getW3_advantage_underOnes2()
	{
		return w3_advantage_underOnes2;
	}
	public void setW3_advantage_underOnes2(Double w3_advantage_underOnes2)
	{
		this.w3_advantage_underOnes2 = w3_advantage_underOnes2;
	}
	public Double getW3_advantage_starts()
	{
		return w3_advantage_starts;
	}
	public void setW3_advantage_starts(Double w3_advantage_starts)
	{
		this.w3_advantage_starts = w3_advantage_starts;
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		if(w2_advantage_starts != null) value = w2_advantage_starts.doubleValue();
		
		if(boss_exist)
		{
			if(w2_advantage_bossExist != null) value = value + w2_advantage_bossExist.doubleValue();
			else value = value + 0.3;
		}
		if(enemies >= max_enemy_limit * 0.9)
		{
			if(w2_advantage_enemies9 != null)  value = value + w2_advantage_enemies9.doubleValue();
			else value = value + 0.3;
		}
		else if(enemies >= max_enemy_limit * 0.8)
		{
			if(w2_advantage_enemies8 != null)  value = value + w2_advantage_enemies8.doubleValue();
			else value = value + 0.1;
		}
		else if(enemies >= max_enemy_limit * 0.7)
		{
			if(w2_advantage_enemies7 != null)  value = value + w2_advantage_enemies7.doubleValue();
			else value = value + 0.05;
		}
		else if(enemies >= max_enemy_limit * 0.6)
		{
			if(w2_advantage_enemies6 != null)  value = value + w2_advantage_enemies6.doubleValue();
			else value = value + 0.01;
		}
		if(underOnes >= 3)
		{
			if(w2_advantage_underOnes3 != null)  value = value + w2_advantage_underOnes3.doubleValue();
			else value = value + 0.3;
		}
		else if(underOnes >= 2)
		{
			if(w2_advantage_underOnes2 != null)  value = value + w2_advantage_underOnes2.doubleValue();
			else value = value + 0.1;
		}
		if(w2_missile_std != null)
		{
			value = value + ((getMissiles() - w2_missile_std.intValue()) / 100.0);
		}
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(w3_advantage_starts != null) value = w3_advantage_starts.doubleValue();
		
		if(boss_exist)
		{
			if(w3_advantage_bossExist != null) value = value + w3_advantage_bossExist.doubleValue();
			else value = value + 0.3;
		}
		if(enemies >= max_enemy_limit * 0.9)
		{
			if(w3_advantage_enemies9 != null)  value = value + w3_advantage_enemies9.doubleValue();
			else value = value + 0.3;
		}
		else if(enemies >= max_enemy_limit * 0.8)
		{
			if(w3_advantage_enemies8 != null)  value = value + w3_advantage_enemies8.doubleValue();
			else value = value + 0.1;
		}
		else if(enemies >= max_enemy_limit * 0.7)
		{
			if(w3_advantage_enemies7 != null)  value = value + w3_advantage_enemies7.doubleValue();
			else value = value + 0.05;
		}
		else if(enemies >= max_enemy_limit * 0.6)
		{
			if(w3_advantage_enemies6 != null)  value = value + w3_advantage_enemies6.doubleValue();
			else value = value + 0.01;
		}
		if(underOnes >= 3)
		{
			if(w3_advantage_underOnes3 != null)  value = value + w3_advantage_underOnes3.doubleValue();
			else value = value + 0.3;
		}
		else if(underOnes >= 2)
		{
			if(w3_advantage_underOnes2 != null)  value = value + w3_advantage_underOnes2.doubleValue();
			else value = value + 0.1;
		}
		if(w3_missile_std != null)
		{
			value = value + ((getMissiles() - w3_missile_std.intValue()) / 100.0);
		}
		
		return value;
	}
	public Integer getW2_missile_std()
	{
		return w2_missile_std;
	}
	public void setW2_missile_std(Integer w2_missile_std)
	{
		this.w2_missile_std = w2_missile_std;
	}
	public Integer getW3_missile_std()
	{
		return w3_missile_std;
	}
	public void setW3_missile_std(Integer w3_missile_std)
	{
		this.w3_missile_std = w3_missile_std;
	}
	public SpaceShip clone(boolean imgnull)
	{
		UserDefinedShip results = new UserDefinedShip(getPlayer_name(), getEnemies(), weapons);
		if(getHp_capacity() != null) results.setHp_capacity(new Integer(getHp_capacity().intValue()));
		if(getSpeed_capacity() != null) results.setSpeed_capacity(new Integer(getSpeed_capacity().intValue()));
		if(getAuthCode() != null) results.setAuthCode(new Long(getAuthCode().longValue()));
		results.version = getVersion();
		if(getShape() != null) results.setShape(new Integer(getShape().intValue()));
		if(w2_advantage_bossExist != null) results.setW2_advantage_bossExist(new Double(getW2_advantage_bossExist().doubleValue()));
		if(w3_advantage_bossExist != null) results.setW3_advantage_bossExist(new Double(getW3_advantage_bossExist().doubleValue()));
		if(w2_advantage_enemies9 != null) results.setW2_advantage_enemies9(new Double(getW2_advantage_enemies9().doubleValue()));
		if(w3_advantage_enemies9 != null) results.setW3_advantage_enemies9(new Double(getW3_advantage_enemies9().doubleValue()));
		if(w2_advantage_enemies8 != null) results.setW2_advantage_enemies9(new Double(getW2_advantage_enemies8().doubleValue()));
		if(w3_advantage_enemies8 != null) results.setW3_advantage_enemies9(new Double(getW3_advantage_enemies8().doubleValue()));
		if(w2_advantage_enemies7 != null) results.setW2_advantage_enemies9(new Double(getW2_advantage_enemies7().doubleValue()));
		if(w3_advantage_enemies7 != null) results.setW3_advantage_enemies9(new Double(getW3_advantage_enemies7().doubleValue()));
		if(w2_advantage_enemies6 != null) results.setW2_advantage_enemies9(new Double(getW2_advantage_enemies6().doubleValue()));
		if(w3_advantage_enemies6 != null) results.setW3_advantage_enemies9(new Double(getW3_advantage_enemies6().doubleValue()));
		if(w2_advantage_underOnes3 != null) results.setW2_advantage_underOnes3(new Double(getW2_advantage_underOnes3().doubleValue()));
		if(w3_advantage_underOnes3 != null) results.setW3_advantage_underOnes3(new Double(getW3_advantage_underOnes3().doubleValue()));
		if(w2_advantage_underOnes2 != null) results.setW2_advantage_underOnes2(new Double(getW2_advantage_underOnes2().doubleValue()));
		if(w3_advantage_underOnes2 != null) results.setW3_advantage_underOnes2(new Double(getW3_advantage_underOnes2().doubleValue()));
		if(w2_advantage_starts != null) results.setW2_advantage_starts(new Double(getW2_advantage_starts().doubleValue()));
		if(w3_advantage_starts != null) results.setW3_advantage_starts(new Double(getW3_advantage_starts().doubleValue()));
		if(w2_missile_std != null) results.setW2_missile_std(new Integer(getW2_missile_std().intValue()));
		if(w3_missile_std != null) results.setW3_missile_std(new Integer(getW3_missile_std().intValue()));
		results.setX(getX());
		results.setY(getY());
		results.setHeal_delay(getHeal_delay());
		results.setR(getR());
		results.setColor(new Color(getColor().getRed() , getColor().getGreen()  , getColor().getBlue() ));
		results.setDamage(getDamage());
		results.setMissiles(getMissiles());
		results.setEnergy(getEnergy());
		results.setEnergy_heal(getEnergy_heal());
		results.setMax_energy(getMax_energy());
		results.setMax_energy_heal(getMax_energy_heal());
		results.setHp(getHp());
		results.setMax_hp(getMax_hp());
		results.setHp_heal(getHp_heal());
		results.setMax_hp_heal(getMax_hp_heal());
		results.setAccel(getAccel());
		results.setAccel_x(getAccel_x());
		results.setAccel_y(getAccel_y());
		results.setMax_accel(getMax_accel());
		results.setMax_damage(getMax_damage());
		results.setMode(getMode());
		results.setW1_fireDelay(getW1_fireDelay());
		results.setW2_fireDelay(getW2_fireDelay());
		results.setW3_fireDelay(getW3_fireDelay());
		results.setFire_delay(getFire_delay());
		results.setFire_missiles(getFire_missiles());
		results.setPoint(Lint.copy(getPoint()));
		results.setNotOwn(isNotOwn());
		results.setColor(getColor());
		if(imgnull)
		{
			results.setImage_w1(null);
			results.setImage_w2(null);
			results.setImage_w3(null);
			results.setImage_w1_exist(false);
			results.setImage_w2_exist(false);
			results.setImage_w3_exist(false);
		}
		else
		{
			results.setImage_w1(getImage_w1());
			results.setImage_w2(getImage_w2());
			results.setImage_w3(getImage_w3());
			results.setImage_w1_exist(img_w1_exist());
			results.setImage_w2_exist(img_w2_exist());
			results.setImage_w3_exist(img_w3_exist());
		}
		return results;
	}
}
