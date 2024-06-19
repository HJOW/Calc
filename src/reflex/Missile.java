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

public class Missile extends RectObject
{
	private static final long serialVersionUID = -6638787495916217682L;
	private boolean launched = false;
	private int owner = 0;
	private int dy = 0;
	private long damage = 1;
	protected transient BufferedImage image = null;
	
	public static final int SPACESHIP = -1;
	
	
	public Missile()
	{
		super();
		setHp(10000);
		setY(-200);
		setW(5);
		setH(15);
		setDy(- (Reflexioner.getSpeed() * 2));
		setColor(Reflexioner.color_spaceShip_missile);
		if(Reflexioner.getFile_path() != null && Reflexioner.image_allow)
			loadImage(Reflexioner.getFile_path());
	}
	public Missile(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public Missile(String path, int owner)
	{
		this();
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update()
	{
		if(launched) setY(getY() + dy);
		setHp(getHp() - 1);
		//if(getY() <= -10 || getY() >= Reflexioner.size_y + 10) launched = false;
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
		if(getOwner() <= -1)
		{			
			if(ImageCache.img_missile != null)
			{
				setImage(ImageCache.img_missile);
				return true;
			}
			else return false;
		}
		else
		{
			if(ImageCache.img_enemy_missile != null)
			{
				setImage(ImageCache.img_enemy_missile);
				return true;
			}
			else return false;
		}
	}
	public void loadImage(String path)
	{
		if(loadCache()) return;
		try
		{
			File target = null;
			if(owner >= 0)
			{
				target = new File(RunManager.r65279(path + "enemy_missile_" + getMissileName() + ".png"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + getMissileName() + ".jpg"));
				if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + getMissileName() + ".png"));
			}
			else
			{
				target = new File(RunManager.r65279(path + "missile_" + getMissileName() + ".png"));
			}
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + getMissileName() + ".jpg"));
			if(! target.exists()) return;
			image = ImageIO.read(target);
		} 
		catch (Exception e)
		{
			
		}
	}
	@Override
	public Area area()
	{
		if(image == null)
			return super.area();
		else
			return new Area(new Rectangle(getX() - (int)(getW() / 2.0), getY() - (int)(getH() / 2.0), (int)(getW()), (int)(getH())));
	}
	@Override
	public void draw(Graphics g)
	{
		//System.out.println("Draw Missile " + image);
		if(image == null)
		{
			super.draw(g);
		}
		else
		{
			g.drawImage(image, getX() - (int)(getW() / 2.0), getY() - (int)(getH() / 2.0), getW(), getH(), null);
			
		}
	}
	public String getMissileName()
	{
		return "normal";
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}
	public boolean isLaunched()
	{
		return launched;
	}
	public void setLaunched(boolean launched)
	{
		this.launched = launched;
	}
	public int getOwner()
	{
		return owner;
	}
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	public int getDy()
	{
		return dy;
	}
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	public long getDamage()
	{
		return damage;
	}
	public void setDamage(long damage)
	{
		this.damage = damage;
	}	
	protected BufferedImage getNowImage()
	{
		return image;
	}
	public Missile clone()
	{
		return clone(false);
	}
	public Missile clone(boolean imgnull)
	{		
		Missile newMissile = new Missile();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		if(imgnull) newMissile.setImage(null);
		newMissile.setColor(getColor());
		
		return newMissile;
	}
	public SuperMissile toSuperMissile(String path)
	{
		SuperMissile newOne = new SuperMissile(path);
		newOne.setLaunched(isLaunched());
		newOne.setOwner(getOwner());
		newOne.setDy(getDy());
		newOne.setDamage((int)Math.round(getDamage() * 1.5));
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setH((int)Math.round(getH() * 1.5));
		newOne.setW((int)Math.round(getW() * 1.5));
		newOne.setColor(getColor());
		
		return newOne;
	}
	public PulseMissile toPulseMissile(String path)
	{
		PulseMissile newOne = new PulseMissile(path);
		newOne.setLaunched(isLaunched());
		newOne.setOwner(getOwner());
		newOne.setDy(getDy());
		newOne.setDamage(getDamage());
		newOne.setX(getX());
		newOne.setY(getY());
		newOne.setMax_hp(getMax_hp());
		newOne.setHp(getHp());
		newOne.setH(getH());
		newOne.setW(getW());
		newOne.setColor(getColor());
		
		return newOne;
	}
}

class PulseMissile extends Missile
{
	private static final long serialVersionUID = 5719141422214292768L;
	public PulseMissile()
	{
		super();
		setDy(getDy() * 8);
		setH(getH() * 8);
	}
	
	public PulseMissile(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public PulseMissile(String path, int owner)
	{
		this();
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawRect(getX(), getY(), getW(), getH());
	}	
	public Missile clone(boolean imgnull)
	{		
		Missile newMissile = new PulseMissile();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		if(imgnull) newMissile.setImage(null);
		newMissile.setColor(getColor());
		
		return newMissile;
	}
}
class EnemyMissile extends Missile
{
	private static final long serialVersionUID = -6287434987192214127L;
	private List<Enemy> enemies = null;
	public EnemyMissile()
	{
		super();
		
		if(Reflexioner.getFile_path() != null && Reflexioner.image_allow)
			loadImage(Reflexioner.getFile_path());
	}
	public EnemyMissile(String path)
	{
		this();
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	public EnemyMissile(String path, int owner)
	{
		this();
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update()
	{
		super.update();
		setHp(getHp() - 1);
	}
	
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	public void setEnemies(List<Enemy> enemies)
	{
		this.enemies = enemies;
	}
	public String getMissileName()
	{
		return "trojan";
	}
	public Missile clone()
	{
		return clone(false);
	}
	public Missile clone(boolean imgnull)
	{		
		EnemyMissile newMissile = new EnemyMissile();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		if(imgnull) newMissile.setImage(null);
		List<Enemy> insideEnemies = new Vector<Enemy>();
		for(int i=0; i<enemies.size(); i++)
		{
			insideEnemies.add(enemies.get(i));
		}
		newMissile.setEnemies(insideEnemies);
		newMissile.setColor(getColor());
		return newMissile;
	}
}
class HelperSpread extends Missile
{
	private static final long serialVersionUID = -8680397867688570446L;
	private int helper_type = 0;
	private int helper_count = 1;
	
	public HelperSpread()
	{
		super();
		setHp(5);	
		setH(getH() * 3);
		setW(getW() * 5);
	}
	public HelperSpread(String path, int owner)
	{
		super(path, owner);
		setHp(5);	
		setH(getH() * 3);
		setW(getW() * 5);
	}
	public String getMissileName()
	{
		return "helper";
	}
	@Override
	public void update()
	{
		super.update();
		setHp(getHp() - 1);
	}
	public List<Enemy> open(SpaceShip owner, double difficulty_mode, String path)
	{
		List<Enemy> results = new Vector<Enemy>();
		Enemy newEnemy = null;
		BigEnemy newBigEnemy = null;
		double centers = helper_count / 2.0;
		for(int i=0; i<helper_count; i++)
		{
			newEnemy = null;
			newBigEnemy = null;
			switch(helper_type)
			{
				case 0:
					newEnemy = new Enemy(path, true);
					newEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
					newEnemy.setHp(newEnemy.getHp() + (int) (owner.getDamage() / 20));
					newEnemy.setMax_hp(newEnemy.getHp());
					newEnemy.setMax_energy(200 - (int)(owner.getDamage() / 10.0));										
					newEnemy.setDamage(Math.round(owner.getDamage() / 2.0));
					if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
					if(difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
					if(difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
					break;
				case 1:
					newBigEnemy = new BigEnemy(path, true);
					newBigEnemy.setGuide(true);
					newBigEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
					newBigEnemy.setHp((newBigEnemy.getHp() + (int) (owner.getDamage() / 20)) * 2);
					newBigEnemy.setMax_hp(newBigEnemy.getHp());
					newBigEnemy.setMax_energy(200 - (int)(owner.getDamage() / 10));
					newBigEnemy.setDamage(Math.round(owner.getDamage() / 2.0));
					if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
					if(difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
					if(difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
					newEnemy = newBigEnemy;
					break;
				case 2:
					newBigEnemy = new QuickBoss(path, true);
					newBigEnemy.setGuide(true);
					newBigEnemy.setX((int) (Math.random() * Reflexioner.getSize_x()));
					newBigEnemy.setHp((newBigEnemy.getHp() + (int) (owner.getDamage())) * 2);
					newBigEnemy.setMax_hp(newBigEnemy.getHp());
					newBigEnemy.setMax_energy(200 - (int)(owner.getDamage() / 10));
					((QuickBoss) newBigEnemy).setBeam_energy(0);
					((QuickBoss) newBigEnemy).setBeam_std(350);
					newBigEnemy.setDamage(Math.round(owner.getDamage()));
					if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
					if(difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
					if(difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
					newEnemy = newBigEnemy;
					break;
			}
			if(newEnemy != null)
			{
				newEnemy.setX(getX());
				newEnemy.setY((int) Math.round(getY() + (30 * (i - centers))));
				newEnemy.setPlayer_own(true);
				newEnemy.setMax_hp(owner.getMax_hp());
				newEnemy.setHp(owner.getMax_hp());
				newEnemy.setMax_energy(200 - owner.getDamage());
				
				if(newEnemy.getMax_energy() < 50) newEnemy.setMax_energy(50);
				newEnemy.setEnergy(newEnemy.getMax_energy());
				results.add(newEnemy);
			}
		}	
		return results;
	}
	public int getHelper_type()
	{
		return helper_type;
	}
	public void setHelper_type(int helper_type)
	{
		this.helper_type = helper_type;
	}
	public int getHelper_count()
	{
		return helper_count;
	}
	public void setHelper_count(int helper_count)
	{
		this.helper_count = helper_count;
	}
	public Missile clone()
	{
		return clone(false);
	}
	public Missile clone(boolean imgnull)
	{		
		HelperSpread newMissile = new HelperSpread();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		if(imgnull) newMissile.setImage(null);
		newMissile.setHelper_count(getHelper_count());
		newMissile.setHelper_type(getHelper_type());
		newMissile.setColor(getColor());
		return newMissile;
	}
}
