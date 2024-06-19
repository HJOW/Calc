package reflex;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Weapon implements Serializable
{
	private static final long serialVersionUID = -6352492028137047417L;
	public static final int MISSILE_NORMAL = 0;
	public static final int MISSILE_REFLEX = 1;
	public static final int MISSILE_REFLEX_P = 2;
	public static final int MISSILE_GUIDE = 3;
	public static final int MISSILE_BEAM = 4;
	public static final int MISSILE_SUPER = 5;
	public static final int MISSILE_HELPER = 6;
	public static final int MISSILE_RASER = 7;
	public static final int MISSILE_FORMULA = 8;
	private int missile_type = 0;
	private int max_missile = 0;
	private int min_missile = 0;
	private int interval = 0;
	private int missile_hp = 0;
	private int need_e = 0;
	private int delay = Reflexioner.fire_delay;
	private double damage_ratio = 0.0;
	private double size = 0.0;
	private double speed = 0.0;
	private int helper_count = 1, helper_type = 0;
	private double progress = 0.0, max_progress = 1000.0;
	private String formula = "";
	private double range_unit = 1000.0;
	private double min_x = -Reflexioner.getSize_x() * 16, max_x = Reflexioner.getSize_x() * 16, min_y = -Reflexioner.getSize_y() * 16, max_y = Reflexioner.getSize_y() * 16;
	private boolean range_absolute = false;
	protected transient int fire_missiles;
	protected transient double center;
	protected transient int getTarget; 
	protected transient Missile newMissile;
	protected transient SpaceShip owner;
	
	public Weapon()
	{
		super();
	}
	public Weapon(String commands, SpaceShip owner)
	{		
		super();
		
		StringTokenizer lineToken, equalToken;
		String lineTarget, option, contents;
		double getTarget2;
		lineToken = new StringTokenizer(commands, "\n");
		this.owner = owner;
		while(lineToken.hasMoreTokens())
		{
			lineTarget = lineToken.nextToken();
			if((lineTarget != null) && (! lineTarget.trim().startsWith("#")) && (! lineTarget.trim().equals("")))
			{
				equalToken = new StringTokenizer(lineTarget, "=");
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
					if(option.equalsIgnoreCase("type"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.missile_type = getTarget;
						}
						catch(NumberFormatException e)
						{
							if(contents.equalsIgnoreCase("normal"))
							{
								this.missile_type = MISSILE_NORMAL;
							}
							else if(contents.equalsIgnoreCase("guide"))
							{
								this.missile_type = MISSILE_GUIDE;
							}
							else if(contents.equalsIgnoreCase("reflex"))
							{
								this.missile_type = MISSILE_REFLEX;
							}
							else if(contents.equalsIgnoreCase("reflex_perfect"))
							{
								this.missile_type = MISSILE_REFLEX_P;
							}
							else if(contents.equalsIgnoreCase("beam"))
							{
								this.missile_type = MISSILE_BEAM;
							}
							else if(contents.equalsIgnoreCase("super"))
							{
								this.missile_type = MISSILE_SUPER;
							}
							else if(contents.equalsIgnoreCase("helper"))
							{
								this.missile_type = MISSILE_HELPER;
							}
							else if(contents.equalsIgnoreCase("raser"))
							{
								this.missile_type = MISSILE_RASER;
							}
							else if(contents.equalsIgnoreCase("formula"))
							{
								this.missile_type = MISSILE_FORMULA;
							}
							else
							{
								this.missile_type = MISSILE_NORMAL;
							}
						}
					}
					else if(option.equalsIgnoreCase("max"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.max_missile = getTarget;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("min"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.min_missile = getTarget;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("interval"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.interval = getTarget;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("hp"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.missile_hp = getTarget;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("needs"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.need_e = getTarget;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("delay"))
					{
						try
						{
							getTarget = Integer.parseInt(contents);
							this.delay = getTarget;
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
							getTarget2 = Double.parseDouble(contents);
							this.size = getTarget2;
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
							getTarget2 = Double.parseDouble(contents);
							this.speed = getTarget2;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("damage"))
					{
						try
						{
							getTarget2 = Double.parseDouble(contents);
							this.damage_ratio = getTarget2;
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(option.equalsIgnoreCase("helper_type"))
					{
						if(this.missile_type == MISSILE_HELPER)
						{							
							try
							{
								getTarget = Integer.parseInt(contents);
								this.helper_type = getTarget;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("helper_count"))
					{
						if(this.missile_type == MISSILE_HELPER)
						{							
							try
							{
								getTarget = Integer.parseInt(contents);
								this.helper_count = getTarget;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_progress"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.progress = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_max_progress"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.max_progress = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_range_unit"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.range_unit = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_range_absolute"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								this.range_absolute = Boolean.parseBoolean(contents);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_min_x"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.min_x = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_min_y"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.min_y = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_max_x"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.max_x = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula_max_y"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								getTarget2 = Double.parseDouble(contents);
								this.max_y = getTarget2;
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					else if(option.equalsIgnoreCase("formula"))
					{
						if(this.missile_type == MISSILE_RASER || this.missile_type == MISSILE_FORMULA)
						{
							try
							{
								this.formula = this.formula + contents + "\n";
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}				
			}
		}
	}
	public List<Missile> fire(long damage, int missiles, int owner_x, int owner_y, int owner_r, int owner, int player_x, int player_y, HaveEnergy owner_ref)
	{		
		Vector<Missile> result_missiles = new Vector<Missile>();
		
		if(owner_ref.getEnergy() < getNeed_e())
		{
			return result_missiles;
		}
		else
		{
			owner_ref.setEnergy(owner_ref.getEnergy() - getNeed_e());
		}
		
		fire_missiles = missiles;
		if(fire_missiles < min_missile)
		{
			fire_missiles = min_missile;
		}
		else if(fire_missiles > max_missile)
		{
			fire_missiles = max_missile;
		}
		center = fire_missiles / 2.0;
		for(int i=0; i<fire_missiles; i++)
		{
			newMissile = null;
			switch (missile_type)
			{
				case MISSILE_NORMAL:
					newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					getTarget = 5;
					break;
				case MISSILE_REFLEX:
					newMissile = new ReflexMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					((ReflexMissile) newMissile).setSide_reflex(false);
					getTarget = 2;
					break;
				case MISSILE_REFLEX_P:
					newMissile = new ReflexMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					((ReflexMissile) newMissile).setSide_reflex(true);
					getTarget = 3;
					break;
				case MISSILE_GUIDE:
					newMissile = new GuidedMissile(this.owner.getEnemies(), this.owner, Missile.SPACESHIP, Reflexioner.getFile_path());
					getTarget = 1;
					break;
				case MISSILE_BEAM:
					newMissile = new Beam(Missile.SPACESHIP, Reflexioner.getFile_path());
					getTarget = 10;
					break;
				case MISSILE_SUPER:
					newMissile = new SuperMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					getTarget = 5;
					break;
				case MISSILE_HELPER:
					newMissile = new HelperSpread(Reflexioner.getFile_path(), Missile.SPACESHIP);					
					getTarget = 10;
					break;
				case MISSILE_RASER:
					newMissile = new Raser(Reflexioner.getFile_path(), Missile.SPACESHIP, "");					
					getTarget = 10;
					break;
				case MISSILE_FORMULA:
					newMissile = new FormulaMissile(Reflexioner.getFile_path(), Missile.SPACESHIP, "");					
					getTarget = 10;
					break;
				default:
					newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					getTarget = 5;
					break;
			}			
			
			if(newMissile != null)
			{
				newMissile.setDamage(Math.round(this.damage_ratio * damage));
				newMissile.setHp(this.missile_hp);
				newMissile.setW((int) Math.round(this.size * getTarget));
				newMissile.setOwner(owner);
				newMissile.setLaunched(true);				
				if(owner == Missile.SPACESHIP)
				{
					newMissile.setDy((int) - (Reflexioner.getSpeed() * speed));
					newMissile.setX(owner_x + (int) Math.round((center - i - 0.5) * 30));
					newMissile.setY(owner_y - owner_r);
					newMissile.setColor(Reflexioner.color_spaceShip_missile);
					if(newMissile instanceof ReflexMissile)
					{
						((ReflexMissile) newMissile).setDx(0);
					}					
				}
				else
				{
					newMissile.setDy((int) (Reflexioner.getSpeed() * speed));
					newMissile.setX(owner_x + (int) Math.round((center - i - 0.5) * 30));
					newMissile.setY(owner_y + owner_r);
					newMissile.setColor(Reflexioner.color_enemy_missile);
				}
				if(newMissile instanceof Beam)
				{
					if(owner == Missile.SPACESHIP)
					{
						newMissile.setY(0);
						newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - owner_y) - 10);
					}
					else
					{
						newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - owner_y) - 10);
						newMissile.setH((int)Math.round(newMissile.getH() * 2.0));
						newMissile.setY(owner_y + newMissile.getH());
						if(newMissile.getY() > Reflexioner.getSize_y()) newMissile.setY(Reflexioner.getSize_y() - 1);
					}
				}
				if(newMissile instanceof HelperSpread)
				{
					((HelperSpread) newMissile).setHelper_type(helper_type);
					((HelperSpread) newMissile).setHelper_count(helper_count);
				}
				if(newMissile instanceof FormulaUsedMissile)
				{
					((FormulaUsedMissile) newMissile).setFormula(formula);
					((FormulaUsedMissile) newMissile).setMax_progress(max_progress);
					((FormulaUsedMissile) newMissile).setProgress(progress);
					((FormulaUsedMissile) newMissile).setRange_absolute(range_absolute);
					((FormulaUsedMissile) newMissile).setRange_unit(range_unit);
				}
				result_missiles.add(newMissile);
			}
		}
		return result_missiles;
	}
	public int getMissile_type()
	{
		return missile_type;
	}
	public void setMissile_type(int missile_type)
	{
		this.missile_type = missile_type;
	}
	public int getMax_missile()
	{
		return max_missile;
	}
	public void setMax_missile(int max_missile)
	{
		this.max_missile = max_missile;
	}
	public int getMin_missile()
	{
		return min_missile;
	}
	public void setMin_missile(int min_missile)
	{
		this.min_missile = min_missile;
	}
	public int getMissile_hp()
	{
		return missile_hp;
	}
	public void setMissile_hp(int missile_hp)
	{
		this.missile_hp = missile_hp;
	}
	public double getSize()
	{
		return size;
	}
	public void setSize(double size)
	{
		this.size = size;
	}
	public double getSpeed()
	{
		return speed;
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	public int getInterval()
	{
		return interval;
	}
	public void setInterval(int interval)
	{
		this.interval = interval;
	}
	public double getDamage_ratio()
	{
		return damage_ratio;
	}
	public void setDamage_ratio(double damage_ratio)
	{
		this.damage_ratio = damage_ratio;
	}
	public int getNeed_e()
	{
		return need_e;
	}
	public void setNeed_e(int need_e)
	{
		this.need_e = need_e;
	}
	public void setSpaceShip(SpaceShip owner)
	{
		this.owner = owner;
	}
	public int getDelay()
	{
		return delay;
	}
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
	public int getHelper_count()
	{
		return helper_count;
	}
	public void setHelper_count(int helper_count)
	{
		this.helper_count = helper_count;
	}
	public int getHelper_type()
	{
		return helper_type;
	}
	public void setHelper_type(int helper_type)
	{
		this.helper_type = helper_type;
	}
	public double getProgress()
	{
		return progress;
	}
	public void setProgress(double progress)
	{
		this.progress = progress;
	}
	public double getMax_progress()
	{
		return max_progress;
	}
	public void setMax_progress(double max_progress)
	{
		this.max_progress = max_progress;
	}
	public String getFormula()
	{
		return formula;
	}
	public void setFormula(String formula)
	{
		this.formula = formula;
	}
	public double getRange_unit()
	{
		return range_unit;
	}
	public void setRange_unit(double range_unit)
	{
		this.range_unit = range_unit;
	}
	public double getMin_x()
	{
		return min_x;
	}
	public void setMin_x(double min_x)
	{
		this.min_x = min_x;
	}
	public double getMax_x()
	{
		return max_x;
	}
	public void setMax_x(double max_x)
	{
		this.max_x = max_x;
	}
	public double getMin_y()
	{
		return min_y;
	}
	public void setMin_y(double min_y)
	{
		this.min_y = min_y;
	}
	public double getMax_y()
	{
		return max_y;
	}
	public void setMax_y(double max_y)
	{
		this.max_y = max_y;
	}
	public boolean isRange_absolute()
	{
		return range_absolute;
	}
	public void setRange_absolute(boolean range_absolute)
	{
		this.range_absolute = range_absolute;
	}	
}
