package reflex;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import main_classes.RunManager;
import setting.Setting;

public class Enemy extends OvalObject implements HaveEnergy
{
	private static final long serialVersionUID = 6365464792268179056L;
	private int dx = -5;
	private int dy = -5;
	private long energy = 0; 
	private long damage = 0;
	private long max_energy = 200;
	private boolean player_own = false;
	private transient BufferedImage image = null;
	private boolean own_unique = false;
	
	public Enemy()
	{
		super();
		setX(Reflexioner.getSize_x());
		setY(10);
		setR(Reflexioner.getEnemy_r());
		setColor(Reflexioner.color_enemy);
		setHp(500);
		setMax_hp(500);
		setDamage((getHp() / 10));
	}
	public Enemy(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public Enemy(String path, boolean player_own)
	{
		this();
		setPlayer_own(player_own);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	public void removeImage()
	{
		this.image = null;
	}
	public void loadImage()
	{
		loadImage(Setting.getNewInstance().getDefault_path());
	}
	protected boolean loadCache()
	{
		if(isPlayer_own())
		{
			if(ImageCache.img_r_enemy != null)
			{
				image = ImageCache.img_r_enemy;
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_enemy != null)
			{
				image = ImageCache.img_enemy;
				return true;
			}
			else return false;
		}
	}
	public void loadImage(String path)
	{
		if(loadCache()) return;
		if(isPlayer_own())
		{
			try
			{
				File target = new File(RunManager.r65279(path + "r_" + getEnemyName() + ".png"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + getEnemyName() + ".jpg"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
				if(! target.exists()) return;
				image = ImageIO.read(target);
			} 
			catch (Exception e)
			{
				
			}
		}
		else
		{
			try
			{
				File target = new File(RunManager.r65279(path + getEnemyName() + ".png"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + getEnemyName() + ".jpg"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".png"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
				if(! target.exists()) return;
				image = ImageIO.read(target);
			} 
			catch (Exception e)
			{
				
			}
		}
	}
	public synchronized List<Missile> fire(long difficulty, int owner, SpaceShip spaceShip, List<Enemy> enemies, String file_path)
	{
		List<Missile> results = new Vector<Missile>();
		Missile newMissile;
		
		newMissile = new Missile(file_path);
		newMissile.setOwner(owner);
		newMissile.setLaunched(true);
		newMissile.setX(this.getX());
		newMissile.setY(this.getY() + this.getR());
		newMissile.setDy(newMissile.getDy() * -1);
		if(player_own)
		{
			newMissile.setDy(newMissile.getDy() * -1);
			newMissile.setOwner(Missile.SPACESHIP);
			newMissile.setColor(Reflexioner.color_spaceShip_missile);
		}
		else newMissile.setColor(Reflexioner.color_enemy_missile);
		newMissile.setDamage(this.getDamage());
		results.add(newMissile);
		this.setEnergy(0);
		
		return results;
	}
	public String getEnemyName()
	{
		return "normal";
	}
	@Override
	public Area area()
	{
		if(image == null)
			return super.area();
		else
			return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
	}
	@Override
	public void draw(Graphics g)
	{
		if(image == null || (! Reflexioner.image_allow))
			super.draw(g);
		else
		{			
			g.drawImage(image, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);			
		}
	}
	@Override
	public void update()
	{
		setX(getX() + dx);
		setY(getY() + dy);
		
		if(player_own)
		{
			if(getX() < getR())
			{
				dx = (int) ((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getX() > Reflexioner.getSize_x() - getR())
			{
				dx = (int) -((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getY() < ((Reflexioner.getSize_y() * 2) / 3) - getR())
			{
				dy = (int) ((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getY() > (Reflexioner.getSize_y()) - getR())
			{
				dy = (int) -((Reflexioner.getSpeed() / 2) * Math.random());
			}
		}
		else
		{
			if(getX() < getR())
			{
				//System.out.println("!! X " + getX() + "/" + getR());
				dx = (int) ((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getX() > Reflexioner.getSize_x() - getR())
			{
				//System.out.println("!! X " + getX() + "/" + (Reflexioner.size_x - getR()));
				dx = (int) -((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getY() < getR())
			{
				//System.out.println("!! Y " + getY() + "/" + getR());
				dy = (int) ((Reflexioner.getSpeed() / 2) * Math.random());
			}
			if(getY() > (Reflexioner.getSize_y() / 3) - getR())
			{
				//System.out.println("!! Y " + getY() + "/" + ((Reflexioner.size_y / 5) - getR()));
				dy = (int) -((Reflexioner.getSpeed() / 2) * Math.random());
			}
		}
		if(energy <= max_energy) energy++;
		
		//System.out.println(getDx() + ", " + getDy());
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}
	
	public int getDx()
	{
		return dx;
	}
	public void setDx(int dx)
	{
		this.dx = dx;
	}
	public int getDy()
	{
		return dy;
	}
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	public long getEnergy()
	{
		return energy;
	}
	public void setEnergy(long energy)
	{
		this.energy = energy;
	}
	public long getDamage()
	{
		return damage;
	}
	public void setDamage(long damage)
	{
		this.damage = damage;
	}
	public long getMax_energy()
	{
		return max_energy;
	}
	public void setMax_energy(long max_energy)
	{
		this.max_energy = max_energy;
	}	
	public int getMissiles()
	{
		return 1;
	}
	public boolean isGuide()
	{
		return false;
	}
	public boolean isPlayer_own()
	{
		return player_own;
	}
	public void setPlayer_own(boolean player_own)
	{
		this.player_own = player_own;
	}
	public Enemy clone()
	{
		return clone(false);
	}
	public Enemy clone(boolean imgnull)
	{
		Enemy newOne = new Enemy();
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
		if(imgnull) newOne.setImage(null);
		else newOne.setImage(loadedImage());
		return newOne;
	}
	protected BufferedImage loadedImage()
	{
		return image;
	}
	public int makeItemCount(long difficulty, long difficulty_delay, double randomNumber)
	{
		if(isOwn_unique()) return 0;
		if(difficulty < difficulty_delay)
		{
			if(randomNumber >= 0.8) return 1;
		}
		else if(difficulty < difficulty_delay * 2)
		{
			if(randomNumber >= 0.99) return 1;
		}
		else
		{
			if(randomNumber >= 0.995) return 1;
		}
		return 0;
	}
	public boolean isOwn_unique()
	{
		return own_unique;
	}
	public void setOwn_unique(boolean own_unique)
	{
		this.own_unique = own_unique;
	}
}
