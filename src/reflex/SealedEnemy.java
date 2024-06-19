package reflex;

import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

public class SealedEnemy extends Enemy
{
	private static final long serialVersionUID = 4568353697968759963L;
	private int disable_seal = 0;
	private int seal_weakness = 50;
	public SealedEnemy()
	{
		super();
	}
	public SealedEnemy(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public SealedEnemy(String path, boolean player_own)
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
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> returns = super.fire(difficulty, owner, spaceShip, enemies, file_path);
		if(returns == null) return new Vector<Missile>();
		disable_seal = disable_seal + seal_weakness;
		List<Missile> newList = new Vector<Missile>();
		for(int i=0; i<returns.size(); i++)
		{
			try
			{
				newList.add(returns.get(i).toSuperMissile(file_path));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return newList;
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
	public String getEnemyName()
	{
		return "sealed";
	}
	@Override
	public Enemy clone(boolean imgnull)
	{
		SealedEnemy newOne = new SealedEnemy();
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
		newOne.setColor(getColor());
		newOne.setOwn_unique(isOwn_unique());
		newOne.setDisable_seal(getDisable_seal());
		newOne.setSeal_weakness(getSeal_weakness());
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	@Override
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.7) return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.9) return 1;
		}
		else
		{
			if(randomNumber >= 0.95) return 1;
		}
		return 0;
	}
}
