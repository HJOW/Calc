package reflex;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;


public class Boss extends BigEnemy
{
	private static final long serialVersionUID = -8833079238493004722L;
	private double ratio = 0.9;
	private int beam_energy = 0;
	private long beam_std = Reflexioner.getBoss_beam_delay();
	private boolean unique = true;
	public Boss()
	{
		super();
		setMissiles(6);
		setR(getR() * 3);		
	}
	public Boss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public Boss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	public double getRatio()
	{
		return ratio;
	}
	public void setRatio(double ratio)
	{
		this.ratio = ratio;
	}
	public String getEnemyName()
	{
		return "boss";
	}
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{
		List<Missile> returns = new Vector<Missile>();
		Beam beam = new Beam(12);
		beam.setOwner(owner);
		beam.setX(this.getX());				
		beam.setH(beam.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
		beam.setH((int)Math.round(beam.getH() * 2.0));
		beam.setY(this.getY() + beam.getH() - this.getR());
		if(beam.getY() > Reflexioner.getSize_y()) beam.setY(Reflexioner.getSize_y() - 1);
		beam.setDamage(200 + (difficulty / 500));
		beam.setColor(Reflexioner.color_enemy_missile);
		if(isPlayer_own())
		{
			beam.setOwner(Missile.SPACESHIP);
			beam.setY(0);
		}
		beam.setLaunched(true);
		returns.add(beam);
		return returns;
	}
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> results;
		if(isPlayer_own())
		{
			results  = DirectMissile.spread(this.getX(), this.getY(), this.getR(), Missile.SPACESHIP, getMissiles(), spaceShip, getRatio(), enemies, difficulty);
			
		}
		else
		{
			if(isUnique())
				results = DirectMissile.spread(this.getX(), this.getY(), this.getR(), owner, getMissiles(), spaceShip, getRatio(), enemies, difficulty);
			else
				results = DirectMissile.spread(this.getX(), this.getY(), this.getR(), owner, (int)Math.round(getMissiles() / 1.5), spaceShip, getRatio(), enemies, difficulty);
		}
		
		// DirectMissile.spread(target_enemy.getX(), target_enemy.getY(), target_enemy.getR(), i, target_enemy.getMissiles(), spaceShip, newBoss.getRatio(), enemies, difficulty)
		setEnergy(0);
		return results;
	}
	public int getBeam_energy()
	{
		return beam_energy;
	}
	public void setBeam_energy(int beam_energy)
	{
		this.beam_energy = beam_energy;
	}
	public void update()
	{
		super.update();
		if(beam_energy <= beam_std + 1)
		{
			if(getHp() >= (getMax_hp() / 4.0))
			{
				beam_energy++;
			}
			else				
			{
				beam_energy = beam_energy + 2;
				setEnergy(getEnergy() + 1);
			}
		}
	}
	public long getBeam_std()
	{
		return beam_std;
	}
	public void setBeam_std(long beam_std)
	{
		this.beam_std = beam_std;
	}
	@Override
	public Enemy clone(boolean imgnull)
	{
		Boss newOne = new Boss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setColor(getColor());
		newOne.setOwn_unique(isOwn_unique());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public static Boss getNewInstance(String path, long difficulty, boolean unique)
	{
		return getNewInstance(path, difficulty, false, unique);
	}
	public static Boss getNewInstance(String path, long difficulty, boolean player_own, boolean unique)
	{
		Boss newBoss = null;
		double ratio = 0.9, ratio2 = 0.99, ratio3 = 0.999, ratio4 = 0.9999, decise = 0.0;
		ratio = ratio - (difficulty / 100000.0);
		ratio2 = ratio2 - (difficulty / 1000000.0);
		ratio3 = ratio3 - (difficulty / 2000000.0);
		ratio4 = ratio4 - (difficulty / 10000000.0);
		if(ratio < 0.3) ratio = 0.3;
		if(ratio2 < 0.9) ratio2 = 0.9;
		if(ratio3 < 0.98) ratio3 = 0.98;
		if(ratio4 < 0.998) ratio4 = 0.998;
		decise = Math.random();
		
		if(decise >= ratio4)
		{
			if(Math.random() >= 0.5) newBoss = new SealedBoss(path, player_own);
			else newBoss = new SealedQuickBoss(path, player_own);
		}
		else if(decise >= ratio3)
		{
			if(Math.random() >= 0.5) newBoss = new UltraBoss(path, player_own);
			else newBoss = new QuickBoss(path, player_own);
		}
		else if(decise >= ratio2)
		{
			newBoss = new HyperBoss(path, player_own);
		}
		else if(decise >= ratio)
		{
			newBoss = new ExtremeBoss(path, player_own);
		}
		else
		{
			newBoss = new Boss(path, player_own);
		}
		
		newBoss.setMax_energy(50);
		newBoss.setR(60);
		newBoss.setMax_hp(5000 + difficulty);
		newBoss.setHp(newBoss.getMax_hp());
		newBoss.setY(newBoss.getY() + newBoss.getR());
		newBoss.setDamage(20 + (difficulty / 1000));
		newBoss.setBeam_energy(0);
		newBoss.setUnique(unique);
		
		return newBoss;
	}
	public boolean isUnique()
	{
		return unique;
	}
	public void setUnique(boolean unique)
	{
		this.unique = unique;
	}
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.7) return 2;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.7) return 2;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.99) return 3;
			else if(randomNumber >= 0.9) return 2;
			else if(randomNumber >= 0.2) return 1;
		}
		return 0;
	}
}
class ExtremeBoss extends Boss
{
	private static final long serialVersionUID = -6965022709385043596L;
	public ExtremeBoss()
	{
		super();	
	}
	public ExtremeBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public ExtremeBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> results = new Vector<Missile>();
		int fires = getMissiles();
		Missile newMissile;
		double calcs_x, calcs_y, dist;
		for(int i=0; i<fires; i++)
		{
			newMissile = new ReflexMissile(file_path, owner);
			newMissile.setHp(1000);
			newMissile.setDamage(75 + (int) (difficulty / 1500));
			newMissile.setX(getX() + ((i - (fires / 2)) * (480 / fires)));
			newMissile.setY(getY());
			((ReflexMissile) newMissile).setW(((ReflexMissile) newMissile).getW() * 2);
			((ReflexMissile) newMissile).setH(((ReflexMissile) newMissile).getH() * 2);
			calcs_y = spaceShip.getY() - newMissile.getY();
			calcs_x = spaceShip.getX() - newMissile.getX();
			((ReflexMissile) newMissile).setDx((int)(calcs_x));
			newMissile.setDy((int)(calcs_y));
			dist = Math.sqrt(Math.pow(((ReflexMissile) newMissile).getDx(), 2) + Math.pow(newMissile.getDy(), 2));
			((ReflexMissile) newMissile).setDx((int)(((ReflexMissile) newMissile).getDx() * 4.0 / (dist)));
			newMissile.setDy((int)(newMissile.getDy() * 4.0 / (dist)));
			if(Math.random() >= 0.8) ((ReflexMissile) newMissile).setSide_reflex(true);
			else ((ReflexMissile) newMissile).setSide_reflex(false);
			if(isPlayer_own())
			{
				newMissile.setDy(newMissile.getDy() * -1);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setColor(Reflexioner.color_spaceShip_missile);
			}
			else newMissile.setColor(Reflexioner.color_enemy_missile);
			newMissile.setLaunched(true);
			results.add(newMissile);
		}
		setEnergy(0);
		return results;
	}
	@Override
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{
		List<Missile> returns = new Vector<Missile>();
		EnemyMissile mis = new EnemyMissile();
		mis.setHp(10);
		mis.setOwner(owner);
		mis.setX(this.getX());				
		mis.setY(getY());
		mis.setDy(1);
		mis.setW(mis.getW() * 4);
		mis.setH(mis.getH() * 4);
		mis.setDamage(800 + (difficulty / 200));
		mis.setColor(Reflexioner.color_enemy_missile);
		mis.setLaunched(true);
		Vector<Enemy> insides = new Vector<Enemy>();
		for(int i=0; i<getMissiles(); i++)
		{
			Enemy newEnemy = new Enemy();
			newEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
			newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
			newEnemy.setMax_hp(newEnemy.getHp());
			newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));										
			newEnemy.setDamage(50 + (difficulty / 100));
			newEnemy.setOwn_unique(true);
			if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
			insides.add(newEnemy);
		}
		mis.setEnemies(insides);
		returns.add(mis);
		return returns;
	}
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		Boss newOne = new ExtremeBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setColor(getColor());
		newOne.setOwn_unique(isOwn_unique());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_2";
	}
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.85) return 3;
			else if(randomNumber >= 0.65) return 2;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.7) return 2;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.95) return 3;
			else if(randomNumber >= 0.85) return 2;
			else if(randomNumber >= 0.1) return 1;
		}
		return 0;
	}
}
class HyperBoss extends ExtremeBoss
{
	private static final long serialVersionUID = -6965022709385043596L;
	private String[] formula = null;
	private double raser_range = -1, raser_speed = 50.0;
	private int raser_hp = 200;	
	private boolean onlyOneSide = true;
	
	public HyperBoss()
	{
		super();	
		formula = setDefaultFormula();
	}
	public HyperBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
		formula = setDefaultFormula();
	}
	public HyperBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
		formula = setDefaultFormula();
	}
	@Override
	public void init()
	{
		super.init();
	}
	public static String[] setDefaultFormula()
	{
		String[] formulas = new String[2];
		
		String formula = "";							
		formula = formula + "x2 constant - 0.5*range 1.0" + "\n";
		formula = formula + "x2 default + 1.0 0.8*speed_ratio" + "\n";
		formula = formula + "y2 constant + range 1.0" + "\n";
		formula = formula + "y1 constant + 0.0 1.0" + "\n";
		formula = formula + "x1 constant + 0.5*range 1.0" + "\n";
		formula = formula + "x2 constant + 0 1.0" + "\n";
		formulas[0] = formula;
		formula = "";
		formula = formula + "x2 constant + 1.5*range 1.0" + "\n";
		formula = formula + "x2 default - 1.0 0.8*speed_ratio" + "\n";
		formula = formula + "y2 constant + range 1.0" + "\n";
		formula = formula + "y1 constant + 0.0 1.0" + "\n";
		formula = formula + "x1 constant + 0.5*range 1.0" + "\n";
		formula = formula + "x2 constant + 0 1.0" + "\n";
		formulas[1] = formula;
		
		return formulas;
	}
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> results = new Vector<Missile>();
		int fires = getMissiles();
		Missile newMissile;
		double calcs_x, calcs_y, dist;
		for(int i=0; i<fires; i++)
		{
			
			newMissile = new ReflexMissile(file_path, owner);
			newMissile.setHp(1000);
			newMissile.setDamage(75 + (int) (difficulty / 1500));
			newMissile.setX(getX() + ((i - (fires / 2)) * (480 / fires)));
			newMissile.setY(getY());
			((ReflexMissile) newMissile).setW(((ReflexMissile) newMissile).getW() * 2);
			((ReflexMissile) newMissile).setH(((ReflexMissile) newMissile).getH() * 2);
			calcs_y = spaceShip.getY() - newMissile.getY();
			calcs_x = spaceShip.getX() - newMissile.getX();
			((ReflexMissile) newMissile).setDx((int)(calcs_x));
			newMissile.setDy((int)(calcs_y));
			dist = Math.sqrt(Math.pow(((ReflexMissile) newMissile).getDx(), 2) + Math.pow(newMissile.getDy(), 2));
			((ReflexMissile) newMissile).setDx((int)(((ReflexMissile) newMissile).getDx() * 4.0 / (dist)));
			newMissile.setDy((int)(newMissile.getDy() * 4.0 / (dist)));
			if(Math.random() >= 0.8) ((ReflexMissile) newMissile).setSide_reflex(true);
			else ((ReflexMissile) newMissile).setSide_reflex(false);
			if(isPlayer_own())
			{
				newMissile.setDy(newMissile.getDy() * -1);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setColor(Reflexioner.color_spaceShip_missile);
			}
			else newMissile.setColor(Reflexioner.color_enemy_missile);
			newMissile.setLaunched(true);
			results.add(newMissile);
		}
		setEnergy(0);
		return results;
	}
	@Override
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{
		List<Missile> returns = new Vector<Missile>();
		
		if(formula == null) return returns;
		int rand = 0;
		if(Math.random() >= 0.5) rand = 1;
		for(int i=0; i<formula.length; i++)
		{
			if(formula[i] != null && (rand == i))
			{
				Raser raser = new Raser(null, owner, formula[i]);
				raser.setHp(100);
				raser.setDamage(200 + (difficulty / 500));
				raser.setColor(Reflexioner.color_enemy_missile);				
				raser.setRange_unit(1000.0);
				if(raser_range < 0) raser.setMax_progress(-1.0);
				else raser.setMax_progress(raser_range);
								
				raser.setDy((int)Math.round(raser_speed + ((difficulty / 100000.0) * raser_speed)  ));
				if(raser.getDy() > raser_speed * 2) raser.setDy((int)Math.round(raser_speed * 2));
				
				if(raser_hp < 0) raser.setHp(200 - (int)Math.round((difficulty / 100000.0) * 200)  );				
				else raser.setHp(raser_hp - (int)Math.round((difficulty / 100000.0) * raser_hp));
				
				if(raser_hp < 0)
				{
					if(raser.getHp() < 100) raser.setHp(100);
				}
				else
				{
					if(raser.getHp() < raser_hp / 2) raser.setHp(raser_hp / 2);
				}
				
				if(i % 2 == 0)
				{
					raser.setMax_x(Reflexioner.getSize_x() / 1.5);
				}
				else
				{
					raser.setMin_x(Reflexioner.getSize_x() / 2.5);
				}
				if(isPlayer_own())
				{					
					raser.setOwner(Missile.SPACESHIP);
				}
				raser.setLaunched(true);
				returns.add(raser);
			}
		}
		
		return returns;
	}
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		HyperBoss newOne = new HyperBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setRaser_hp(getRaser_hp());
		newOne.setRaser_range(getRaser_range());
		newOne.setRaser_speed(getRaser_speed());
		newOne.setOnlyOneSide(isOnlyOneSide());
		newOne.setOwn_unique(isOwn_unique());
		if(getFormula() != null)
		{
			String[] newFormula = new String[getFormula().length];
			for(int i=0; i<newFormula.length; i++)
			{
				newFormula[i] = getFormula()[i];
			}
			newOne.setFormula(newFormula);
		}
		newOne.setColor(getColor());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_3";
	}
	public String[] getFormula()
	{
		return formula;
	}
	public void setFormula(String[] formula)
	{
		this.formula = formula;
	}
	
	public double getRaser_range()
	{
		return raser_range;
	}
	public void setRaser_range(double raser_range)
	{
		this.raser_range = raser_range;
	}
	public double getRaser_speed()
	{
		return raser_speed;
	}
	public void setRaser_speed(double raser_speed)
	{
		this.raser_speed = raser_speed;
	}
	public int getRaser_hp()
	{
		return raser_hp;
	}
	public void setRaser_hp(int raser_hp)
	{
		this.raser_hp = raser_hp;
	}
	public boolean isOnlyOneSide()
	{
		return onlyOneSide;
	}
	public void setOnlyOneSide(boolean onlyOneSide)
	{
		this.onlyOneSide = onlyOneSide;
	}
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.85) return 3;
			else if(randomNumber >= 0.65) return 2;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.7) return 2;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.95) return 3;
			else if(randomNumber >= 0.85) return 2;
			else if(randomNumber >= 0.1) return 1;
		}
		return 0;
	}
}

class UltraBoss extends HyperBoss
{
	private static final long serialVersionUID = -8722600960303942602L;
	public UltraBoss()
	{
		super();	
	}
	public UltraBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public UltraBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void init()
	{
		super.init();
	}
	
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> returns = new Vector<Missile>();
		EnemyMissile mis = new EnemyMissile();
		mis.setHp(10);
		mis.setOwner(owner);
		mis.setX(this.getX());				
		mis.setY(getY());
		mis.setDy(1);
		mis.setW(mis.getW() * 4);
		mis.setH(mis.getH() * 4);
		mis.setDamage(800 + (difficulty / 200));
		mis.setColor(Reflexioner.color_enemy_missile);
		mis.setLaunched(true);
		Vector<Enemy> insides = new Vector<Enemy>();
		for(int i=0; i<getMissiles(); i++)
		{
			Enemy newEnemy;
			if(getHp() > getMax_hp() / 4.0)
				newEnemy = new Enemy();
			else
				newEnemy = new BigEnemy();
			newEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
			newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
			newEnemy.setMax_hp(newEnemy.getHp());
			newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));										
			newEnemy.setDamage(50 + (difficulty / 100));
			newEnemy.setOwn_unique(true);
			if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
			insides.add(newEnemy);
		}
		mis.setEnemies(insides);
		returns.add(mis);
		setEnergy(0);
		return returns;
	}
	
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		HyperBoss newOne = new HyperBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setRaser_hp(getRaser_hp());
		newOne.setRaser_range(getRaser_range());
		newOne.setRaser_speed(getRaser_speed());
		newOne.setOnlyOneSide(isOnlyOneSide());
		newOne.setOwn_unique(isOwn_unique());
		if(getFormula() != null)
		{
			String[] newFormula = new String[getFormula().length];
			for(int i=0; i<newFormula.length; i++)
			{
				newFormula[i] = getFormula()[i];
			}
			newOne.setFormula(newFormula);
		}
		newOne.setColor(getColor());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_4";
	}
	
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.8) return 3;
			else if(randomNumber >= 0.6) return 2;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.85) return 3;
			else if(randomNumber >= 0.65) return 2;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.8) return 2;
			else if(randomNumber >= 0.075) return 1;
		}
		return 0;
	}
}
class QuickBoss extends UltraBoss
{
	private static final long serialVersionUID = -8722600950303941602L;
	public QuickBoss()
	{
		super();	
		setMissiles((int)Math.ceil(getMissiles() / 2.0));
	}
	public QuickBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public QuickBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void init()
	{
		super.init();
	}
	
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> results = new Vector<Missile>();
		int fires = getMissiles();
		Missile newMissile;
		double calcs_x, calcs_y, dist;
		
		for(int i=0; i<fires; i++)
		{
			newMissile = new DirectMissile(file_path, owner);			
			newMissile.setHp(1000);
			newMissile.setDamage(75 + (int) (difficulty / 1500));
			newMissile.setX(getX() + ((i - (fires / 2)) * (480 / fires)));
			newMissile.setY(getY());
			((DirectMissile) newMissile).setW(((DirectMissile) newMissile).getW() * 2);
			((DirectMissile) newMissile).setH(((DirectMissile) newMissile).getH() * 2);
			calcs_y = spaceShip.getY() - newMissile.getY();
			calcs_x = spaceShip.getX() - newMissile.getX();
			((DirectMissile) newMissile).setDx((int)(calcs_x));
			newMissile.setDy((int)(calcs_y));
			dist = Math.sqrt(Math.pow(((DirectMissile) newMissile).getDx(), 2) + Math.pow(newMissile.getDy(), 2));
			((DirectMissile) newMissile).setDx((int)(((DirectMissile) newMissile).getDx() * 4.0 / (dist)));
			newMissile.setDy((int)(newMissile.getDy() * 12.0 / (dist)));
			if(isPlayer_own())
			{
				newMissile.setDy(newMissile.getDy() * -1);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setColor(Reflexioner.color_spaceShip_missile);
			}
			else newMissile.setColor(Reflexioner.color_enemy_missile);
			newMissile.setLaunched(true);
			results.add(newMissile);
		}
		setEnergy(getMax_energy() / 2);
		return results;
	}
	@Override
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{
		List<Missile> results = new Vector<Missile>();
		int fires = getMissiles() * 4;
		Missile newMissile;
		double calcs_x, calcs_y, dist;
		for(int i=0; i<fires; i++)
		{
			newMissile = new DirectMissile(Reflexioner.getFile_path(), owner);
			newMissile.setHp(1000);
			newMissile.setDamage(75 + (int) (difficulty / 1500));
			newMissile.setX(getX() + ((i - (fires / 2)) * (960 / fires)));
			newMissile.setY(getY());
			((DirectMissile) newMissile).setW(((DirectMissile) newMissile).getW() * 2);
			((DirectMissile) newMissile).setH(((DirectMissile) newMissile).getH() * 2);
			calcs_y = spaceShip.getY() - newMissile.getY();
			calcs_x = spaceShip.getX() - newMissile.getX();
			((DirectMissile) newMissile).setDx((int)(calcs_x));
			newMissile.setDy((int)(calcs_y));
			dist = Math.sqrt(Math.pow(((DirectMissile) newMissile).getDx(), 2) + Math.pow(newMissile.getDy(), 2));
			((DirectMissile) newMissile).setDx((int)(((DirectMissile) newMissile).getDx() * 4.0 / (dist)));
			newMissile.setDy((int)(newMissile.getDy() * 12.0 / (dist)));
			if(isPlayer_own())
			{
				newMissile.setDy(newMissile.getDy() * -1);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setColor(Reflexioner.color_spaceShip_missile);
			}
			else newMissile.setColor(Reflexioner.color_enemy_missile);
			newMissile.setLaunched(true);
			results.add(newMissile);
		}		
		
		setBeam_energy(0);
		return results;
	}
	
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		HyperBoss newOne = new QuickBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setRaser_hp(getRaser_hp());
		newOne.setRaser_range(getRaser_range());
		newOne.setRaser_speed(getRaser_speed());
		newOne.setOnlyOneSide(isOnlyOneSide());
		newOne.setOwn_unique(isOwn_unique());
		if(getFormula() != null)
		{
			String[] newFormula = new String[getFormula().length];
			for(int i=0; i<newFormula.length; i++)
			{
				newFormula[i] = getFormula()[i];
			}
			newOne.setFormula(newFormula);
		}
		newOne.setColor(getColor());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_6";
	}
	
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.8) return 3;
			else if(randomNumber >= 0.6) return 2;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.85) return 3;
			else if(randomNumber >= 0.65) return 2;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.9) return 3;
			else if(randomNumber >= 0.8) return 2;
			else if(randomNumber >= 0.075) return 1;
		}
		return 0;
	}
}
class SealedBoss extends HyperBoss
{
	private static final long serialVersionUID = -8722600960303942602L;
	private int disable_seal = 0;
	private int seal_weakness = 100;
	public SealedBoss()
	{
		super();	
	}
	public SealedBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public SealedBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update() 
	{
		super.update();
		if(disable_seal >= 1) disable_seal--;
	}
	@Override
	public void draw(Graphics g)
	{
		if(disable_seal >= 1)
		{
			if(loadedImage() == null || (! Reflexioner.image_allow))
				super.draw(g);
			else
			{			
				g.drawImage(loadedImage(), getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);			
			}
		}
	}
	@Override
	public void init()
	{
		super.init();
	}
	@Override
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{		
		setDisable_seal(getSeal_weakness());
		return super.shootBeam(difficulty, owner, spaceShip);
	}
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> returns = new Vector<Missile>();
		EnemyMissile mis = new EnemyMissile();
		mis.setHp(10);
		mis.setOwner(owner);
		mis.setX(this.getX());				
		mis.setY(getY());
		mis.setDy(1);
		mis.setW(mis.getW() * 4);
		mis.setH(mis.getH() * 4);
		mis.setDamage(800 + (difficulty / 200));
		mis.setColor(Reflexioner.color_enemy_missile);
		mis.setLaunched(true);
		Vector<Enemy> insides = new Vector<Enemy>();
		for(int i=0; i<getMissiles(); i++)
		{
			Enemy newEnemy;
			if(getHp() > getMax_hp() / 4.0)
				newEnemy = new Enemy();
			else
				newEnemy = new BigEnemy();
			newEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
			newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
			newEnemy.setMax_hp(newEnemy.getHp());
			newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));										
			newEnemy.setDamage(50 + (difficulty / 100));
			newEnemy.setOwn_unique(true);
			if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
			insides.add(newEnemy);
		}
		mis.setEnemies(insides);
		returns.add(mis);
		setEnergy(0);		
		return returns;
	}
	
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		HyperBoss newOne = new SealedBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setRaser_hp(getRaser_hp());
		newOne.setRaser_range(getRaser_range());
		newOne.setRaser_speed(getRaser_speed());
		newOne.setOnlyOneSide(isOnlyOneSide());
		newOne.setOwn_unique(isOwn_unique());
		if(getFormula() != null)
		{
			String[] newFormula = new String[getFormula().length];
			for(int i=0; i<newFormula.length; i++)
			{
				newFormula[i] = getFormula()[i];
			}
			newOne.setFormula(newFormula);
		}
		newOne.setColor(getColor());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_5";
	}
	
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.8) return 5;
			else if(randomNumber >= 0.6) return 3;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.85) return 5;
			else if(randomNumber >= 0.65) return 3;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.8) return 3;
			else if(randomNumber >= 0.7) return 2;
			else if(randomNumber >= 0.05) return 1;
		}
		return 0;
	}
	public int getDisable_seal()
	{
		return disable_seal;
	}
	public void setDisable_seal(int disable_seal)
	{
		this.disable_seal = disable_seal;
	}
	public int getSeal_weakness()
	{
		return seal_weakness;
	}
	public void setSeal_weakness(int seal_weakness)
	{
		this.seal_weakness = seal_weakness;
	}
}
class SealedQuickBoss extends QuickBoss
{
	private static final long serialVersionUID = -8722600560303742602L;
	private int disable_seal = 0;
	private int seal_weakness = 100;
	public SealedQuickBoss()
	{
		super();	
	}
	public SealedQuickBoss(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public SealedQuickBoss(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update() 
	{
		super.update();
		if(disable_seal >= 1) disable_seal--;
	}
	@Override
	public void draw(Graphics g)
	{
		if(disable_seal >= 1)
		{
			if(loadedImage() == null || (! Reflexioner.image_allow))
				super.draw(g);
			else
			{			
				g.drawImage(loadedImage(), getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);			
			}
		}
	}
	@Override
	public void init()
	{
		super.init();
	}
	@Override
	public List<Missile> shootBeam(long difficulty, int owner, SpaceShip spaceShip)
	{		
		setDisable_seal(getSeal_weakness());
		return super.shootBeam(difficulty, owner, spaceShip);
	}
	
	@Override
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_boss != null)
			{
				setImage(ImageCache.img_r_boss);
				return true;
			}
			else if(ImageCache.img_r_bigEnemy != null)
			{
				setImage(ImageCache.img_r_bigEnemy);
				return true;
			}
			else if(ImageCache.img_r_enemy != null)
			{
				setImage(ImageCache.img_r_enemy);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_boss != null)
			{
				setImage(ImageCache.img_boss);
				return true;
			}
			else if(ImageCache.img_bigEnemy != null)
			{
				setImage(ImageCache.img_bigEnemy);
				return true;
			}
			else if(ImageCache.img_enemy != null)
			{
				setImage(ImageCache.img_enemy);
				return true;
			}
			else return false;
		}		
	}
	
	@Override
	public Enemy clone(boolean imgnull)
	{
		HyperBoss newOne = new SealedBoss();
		newOne.setDx(getDx());
		newOne.setDy(getDy());
		newOne.setEnergy(getEnergy());
		newOne.setDamage(getDamage());
		newOne.setMax_energy(getMax_energy());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setColor(getColor());
		newOne.setR(getR());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setGuide(isGuide());
		newOne.setMissiles(getMissiles());
		newOne.setRatio(getRatio());
		newOne.setBeam_energy(getBeam_energy());
		newOne.setBeam_std(getBeam_std());
		newOne.setUnique(isUnique());
		newOne.setRaser_hp(getRaser_hp());
		newOne.setRaser_range(getRaser_range());
		newOne.setRaser_speed(getRaser_speed());
		newOne.setOnlyOneSide(isOnlyOneSide());
		newOne.setOwn_unique(isOwn_unique());
		if(getFormula() != null)
		{
			String[] newFormula = new String[getFormula().length];
			for(int i=0; i<newFormula.length; i++)
			{
				newFormula[i] = getFormula()[i];
			}
			newOne.setFormula(newFormula);
		}
		newOne.setColor(getColor());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	public String getEnemyName()
	{
		return "boss_7";
	}
	
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.8) return 5;
			else if(randomNumber >= 0.6) return 3;
			return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.85) return 5;
			else if(randomNumber >= 0.65) return 3;
			return 1;
		}
		else
		{
			if(randomNumber >= 0.8) return 3;
			else if(randomNumber >= 0.7) return 2;
			else if(randomNumber >= 0.05) return 1;
		}
		return 0;
	}
	public int getDisable_seal()
	{
		return disable_seal;
	}
	public void setDisable_seal(int disable_seal)
	{
		this.disable_seal = disable_seal;
	}
	public int getSeal_weakness()
	{
		return seal_weakness;
	}
	public void setSeal_weakness(int seal_weakness)
	{
		this.seal_weakness = seal_weakness;
	}
}
