package reflex;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

public class SpaceShipEnemy extends Boss
{
	private static final long serialVersionUID = -669961409629093011L;
	private SpaceShip ship;
	public SpaceShipEnemy()
	{
		super();
	}
	public SpaceShipEnemy(String path)
	{
		super(path);
	}
	public SpaceShip getShip()
	{
		return ship;
	}
	@Override
	public void draw(Graphics g)
	{
		setX(ship.getX());
		setY(ship.getY());
		ship.draw(g);
	}
	@Override
	public void update()
	{
		ship.update();
		if(getX() < getR())
		{
			ship.setAccel_x((int) ((Reflexioner.getSpeed() / 2) * Math.random()));
		}
		if(getX() > Reflexioner.getSize_x() - getR())
		{
			ship.setAccel_x((int) -((Reflexioner.getSpeed() / 2) * Math.random()));
		}
		if(getY() < getR())
		{
			ship.setAccel_x((int) ((Reflexioner.getSpeed() / 2) * Math.random()));
		}
		if(getY() > (Reflexioner.getSize_y() / 3) - getR())
		{
			ship.setAccel_x((int) -((Reflexioner.getSpeed() / 2) * Math.random()));
		}
	}
	@Override
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		double controlRandom = Math.random();
		if(spaceShip.getEnergy() / (double)ship.getMax_energy() >= 0.5)
		{
			controlRandom = controlRandom + 0.4;
		}
		else if(spaceShip.getEnergy() / (double)ship.getMax_energy() >= 0.2)
		{
			controlRandom = controlRandom + 0.3;
		}
		if(enemies.size() >= Reflexioner.max_enemies * 0.8)
		{
			controlRandom = controlRandom + 0.1;
		}
		else if(enemies.size() >= Reflexioner.max_enemies * 0.5)
		{
			controlRandom = controlRandom + 0.05;
		}
		if(controlRandom >= 1.3) spaceShip.setMode(3);
		else if(controlRandom >= 1.0) spaceShip.setMode(2);
		else spaceShip.setMode(1);
		List<Missile> results = ship.fire();
		if(results == null) results = new Vector<Missile>();
		for(int i=0; i<results.size(); i++)
		{
			results.get(i).setOwner(owner);
			results.get(i).setDamage(results.get(i).getDamage() + (difficulty / 100));
			if(results.get(i) instanceof GuidedMissile)
			{
				((GuidedMissile) results.get(i)).setSpaceShipData(spaceShip);
				((GuidedMissile) results.get(i)).setEnemyList(enemies);
			}
		}
		return results;
	}
	public void setShip(SpaceShip ship)
	{
		this.ship = ship;
	}
	public String getEnemyName()
	{
		return "spaceship";
	}
	public void setDx(int dx)
	{
		ship.setAccel_x(dx);
	}
	@Override
	public void setX(int x)
	{
		super.setX(x);
		if(ship != null)
			ship.setX(x);
	}
	@Override
	public void setY(int y)
	{
		super.setY(y);
		if(ship != null)
			ship.setY(y);
	}
	@Override
	public Enemy clone()
	{
		SpaceShipEnemy newOne = new SpaceShipEnemy();
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
		newOne.setShip(getShip().clone());
		
		return newOne;
	}
}
