package reflex;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import setting.Lint;

public class ReflexSave implements Serializable
{
	private static final long serialVersionUID = -2815301101980193764L;
	private Long version_difference = new Long(3219984983516L);
	private List<Enemy> enemies;
	private SpaceShip spaceShip;
	private List<Missile> missile;
	private List<Boom> booms;
	private List<Item> items;
	private List<Enemy> ourEnemy;
	private Long difficulty = new Long(0);
	private Double difficultyMode = new Double(1.0);
	private BigInteger authority = new BigInteger("0");
	private String version = "";
	private ReflexScenario scenario = null;
	private List<ReflexDecorate> decorates;
	private Integer continue_left;
	private Boolean autoControl = new Boolean(false);
	public ReflexSave()
	{
		
	}
	public ReflexSave(SpaceShip spaceShip, List<Enemy> enemies, List<Missile> missiles, List<Boom> booms, List<Item> items, long difficulty, double difmode, String ver, int continue_left, ReflexScenario scen, boolean autoControl, List<Enemy> ourEnemy)
	{
		this.spaceShip = spaceShip;
		this.enemies = enemies;
		this.missile = missiles;
		this.booms = booms;
		this.items = items;
		this.difficulty = new Long(difficulty);
		this.difficultyMode = new Double(difmode);
		this.version = ver;
		this.continue_left = new Integer(continue_left);
		this.scenario = scen;
		this.autoControl = new Boolean(autoControl);
		this.ourEnemy = ourEnemy;
		auth();
	}
	public ReflexSave(SpaceShip spaceShip, List<Enemy> enemies, List<Missile> missiles, List<Boom> booms, List<Item> items, List<ReflexDecorate> decorates, long difficulty, double difmode, String ver, int continue_left, ReflexScenario scen, boolean autoControl, List<Enemy> ourEnemy)
	{
		this.spaceShip = spaceShip;
		this.enemies = enemies;
		this.missile = missiles;
		this.booms = booms;
		this.items = items;
		this.decorates = decorates;
		this.difficulty = new Long(difficulty);
		this.difficultyMode = new Double(difmode);
		this.version = ver;
		this.continue_left = new Integer(continue_left);
		this.scenario = scen;
		this.autoControl = new Boolean(autoControl);
		this.ourEnemy = ourEnemy;
		auth();
	}
	public void loadImages(String file_path)
	{
		for(Enemy e : enemies)
		{
			e.loadImage(file_path);
		}
		for(Enemy e : ourEnemy)
		{
			e.loadImage(file_path);
		}
		spaceShip.loadImages(file_path);
		for(Missile m : missile)
		{
			m.loadImage(file_path);
		}
		for(Boom b : booms)
		{
			b.loadImage(file_path);
		}
		for(ReflexDecorate d : decorates)
		{
			d.loadImage(file_path);
		}
	}
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	public void setEnemies(List<Enemy> enemies)
	{
		this.enemies = enemies;
	}
	public SpaceShip getSpaceShip()
	{
		return spaceShip;
	}
	public void setSpaceShip(SpaceShip spaceShip)
	{
		this.spaceShip = spaceShip;
	}
	public List<Missile> getMissile()
	{
		return missile;
	}
	public void setMissile(List<Missile> missile)
	{
		this.missile = missile;
	}
	public List<Boom> getBooms()
	{
		return booms;
	}
	public void setBooms(List<Boom> booms)
	{
		this.booms = booms;
	}
	public List<Item> getItems()
	{
		return items;
	}
	public void setItems(List<Item> items)
	{
		this.items = items;
	}	
	public Long getDifficulty()
	{
		return difficulty;
	}
	public void setDifficulty(Long difficulty)
	{
		this.difficulty = difficulty;
	}
	public BigInteger getAuthority()
	{
		return authority;
	}
	public void setAuthority(BigInteger authority)
	{
		this.authority = authority;
	}
	private static BigInteger newAuth(ReflexSave target)
	{
		BigInteger a = Lint.big(0);
		if(target.getSpaceShip() != null)
		{
			try
			{
				a = a.add(target.getSpaceShip().getPoint());
				a = a.add(Lint.big(target.getSpaceShip().getAccel()));
				a = a.add(Lint.big(target.getSpaceShip().getDamage()));
				a = a.multiply(Lint.big(3));
				a = a.add(Lint.big(target.getSpaceShip().getMax_energy()));
				a = a.add(Lint.big(target.getSpaceShip().getMax_hp()));
				a = a.multiply(Lint.big(13));
				a = a.add(Lint.big(target.getSpaceShip().getHp()));
				a = a.add(Lint.big(target.getSpaceShip().getEnergy()));
				a = a.add(Lint.big(target.getSpaceShip().getEnergy_heal()));
				a = a.add(Lint.big(target.getSpaceShip().getHp_heal()));
				a = a.add(Lint.big(target.getSpaceShip().getFire_delay()));
				a = a.add(Lint.big(target.getSpaceShip().getR()));
				a = a.add(Lint.big(target.getSpaceShip().getX()));
				a = a.add(Lint.big(target.getSpaceShip().getY()));
				a = a.divide(Lint.big(3));
				if(target.getSpaceShip() instanceof Cruiser)
				{
					a = a.add(Lint.big(294));
				}
				if(target.getSpaceShip() instanceof Clipper)
				{
					a = a.add(Lint.big(-285));
				}
				if(target.getSpaceShip() instanceof Warship)
				{
					a = a.add(Lint.big(724));
				}
				if(target.getSpaceShip() instanceof UserDefinedShip)
				{
					UserDefinedShip userDefined = (UserDefinedShip) target.getSpaceShip();
					try
					{
						for(Weapon w : userDefined.getWeapons())
						{
							try
							{
								if(w != null)
								{
									a = a.add(Lint.big(w.getMissile_type()));
									a = a.add(Lint.big(w.getDelay()));
									a = a.add(Lint.big(w.getInterval()));
									a = a.add(Lint.big(w.getMax_missile()));
									a = a.add(Lint.big(w.getMin_missile()));
									a = a.add(Lint.big(w.getMissile_hp()));
									a = a.add(Lint.big(w.getMissile_type()));
									a = a.add(Lint.big(w.getNeed_e()));
									a = a.add(Lint.big(Math.round(w.getDamage_ratio() * 100)));
									a = a.add(Lint.big(Math.round(w.getSize() * 100)));
									a = a.add(Lint.big(Math.round(w.getSpeed() * 100)));
								}
							}
							catch(Exception e)
							{
								
							}
						}
					} 
					catch (Exception e)
					{
						
					}
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(target.getEnemies() != null)
		{
			Enemy en;
			for(int i=0; i<target.getEnemies().size(); i++)
			{
				en = target.getEnemies().get(i);
				try
				{
					a = a.add(Lint.big(en.getDamage()));
					a = a.add(Lint.big(en.getMax_hp()));
					a = a.add(Lint.big(en.getHp()));
					a = a.add(Lint.big(en.getHp_heal()));
					a = a.add(Lint.big(en.getEnergy()));
					a = a.add(Lint.big(en.getX()));
					a = a.add(Lint.big(en.getY()));
					a = a.add(Lint.big(en.getMax_energy()));
					a = a.multiply(Lint.big(2));
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		a = a.multiply(Lint.big(17));
		if(target.getItems() != null)
		{
			Item it;
			for(int i=0; i<target.getItems().size(); i++)
			{
				it = target.getItems().get(i);
				try
				{
					a = a.add(Lint.big(it.getHp()));
					a = a.add(Lint.big(it.getHp_heal()));
					a = a.add(Lint.big(it.getMax_hp()));
					a = a.add(Lint.big(it.getX()));
					a = a.add(Lint.big(it.getY()));
					a = a.add(Lint.big(it.getR()));
					a = a.add(Lint.big(it.getType()));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		a = a.multiply(Lint.big(11));
		if(target.getMissile() != null)
		{
			Missile mi;
			for(int i=0; i<target.getMissile().size(); i++)
			{
				mi = target.getMissile().get(i);
				try
				{
					a = a.add(Lint.big(mi.getX()));
					a = a.add(Lint.big(mi.getY()));
					a = a.add(Lint.big(mi.getDy()));
					a = a.add(Lint.big(mi.getOwner()));
					if(mi instanceof GuidedMissile)
					{
						a = a.add(Lint.big(((GuidedMissile) mi).getDx()));
						a = a.add(Lint.big(((GuidedMissile) mi).getSpeed()));
						a = a.add(Lint.big(((GuidedMissile) mi).getGuideRound()));
					}
					else if(mi instanceof DirectMissile)
					{
						a = a.add(Lint.big(((DirectMissile) mi).getDx()));
						a = a.add(Lint.big(((GuidedMissile) mi).getSpeed()));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		a = a.multiply(Lint.big(19));
		char[] newVerChar = target.getVersion().toCharArray();
		for(int i=0; i<newVerChar.length; i++)
		{
			a = a.add(Lint.big((long) newVerChar[i]));
		}
		if(target.continue_left != null)
			a = a.add(Lint.big(target.continue_left.intValue()));
		if(target.autoControl != null)
		{
			if(target.autoControl.booleanValue()) a = a.add(Lint.big(26));
			else a = a.add(Lint.big(85));
		}
		if(target instanceof AReflexSave)
		{
			AReflexSave atarget = (AReflexSave) target;
			if(atarget.getAuthority_game() != null)
			{
				if(atarget.getAuthority_game().booleanValue()) a = a.add(Lint.big(62));
				else a = a.add(Lint.big(16));
			}	
			if(atarget.getPause_left() != null)
			{
				a = a.add(Lint.big(atarget.getPause_left().longValue()));
			}
		}
		a = a.add(Lint.big(628533));
		a = a.multiply(Lint.big(21));
		return a;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public Double getDifficultyMode()
	{
		return difficultyMode;
	}
	public void setDifficultyMode(Double difficultyMode)
	{
		this.difficultyMode = difficultyMode;
	}
	protected void auth()
	{
		authority = newAuth(this);		
	}
	public boolean accept()
	{
		if(version_difference.longValue() != new ReflexSave().version_difference.longValue()) return false;
		BigInteger objective = newAuth(this);
		if(objective.compareTo(authority) == 0)
		{
			if(scenario == null) return true;
			else if(scenario.isAuthorized()) return true;
			else return false;
		}
		return false;
	}
	public ReflexScenario getScenario()
	{
		return scenario;
	}
	public void setScenario(ReflexScenario scenario)
	{
		this.scenario = scenario;
	}
	public List<ReflexDecorate> getDecorates()
	{
		return decorates;
	}
	public void setDecorates(List<ReflexDecorate> decorates)
	{
		this.decorates = decorates;
	}
	public Long getVersion_difference()
	{
		return version_difference;
	}
	public void setVersion_difference(Long version_difference)
	{
		this.version_difference = version_difference;
	}
	public Integer getContinue_left()
	{
		return continue_left;
	}
	public void setContinue_left(Integer continue_left)
	{
		this.continue_left = continue_left;
	}
	public Boolean getAutoControl()
	{
		return autoControl;
	}
	public void setAutoControl(Boolean autoControl)
	{
		this.autoControl = autoControl;
	}
	public List<Enemy> getOurEnemy()
	{
		return ourEnemy;
	}
	public void setOurEnemy(List<Enemy> ourEnemy)
	{
		this.ourEnemy = ourEnemy;
	}
}
