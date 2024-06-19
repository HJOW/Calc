package reflex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import lang.Language;
import main_classes.RunManager;
import setting.Lint;
import setting.Setting;

public class SpaceShip extends OvalObject implements HaveEnergy, ControllableShip
{
	private static final long serialVersionUID = 1250863114692291667L;
	private long damage = 100;
	private int missiles = 1;
	private long energy = 1000;
	private long max_energy = 1000;
	private int energy_heal = 1;
	private long max_hp_heal = 100;
	private long max_energy_heal = 100;
	private int accel = 1;
	private int max_accel = 10;
	private long max_damage = 1000;
	private int mode = 1;
	private int w1_fireDelay = Reflexioner.fire_delay, w2_fireDelay = Reflexioner.fire_delay, w3_fireDelay = Reflexioner.fire_delay, fire_delay = 0;
	private transient int accel_x = 0, accel_y = 0, fire_missiles = 0, heal_delay = 0, getKeyCode;
	private BigInteger point;
	private boolean notOwn = false;
	private boolean image_w1_exist = false, image_w2_exist = false, image_w3_exist = false;
	private transient BufferedImage image_w1, image_w2, image_w3;
	private transient List<Enemy> enemies;
	
	public static final int USERDEFINED = -1, FLEX = 1, BERSERK = 2, CLIPPER = 3, WARSHIP = 4, CHASER = 5, CARRIER = 6, SATELLITE = 7;

	
	public SpaceShip()
	{
		super();
		setFireDelaySeries();
	}
	public SpaceShip(List<Enemy> enemies)
	{
		this();
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR(Reflexioner.getSpaceShip_r());
		setColor(Reflexioner.color_spaceShip);
		setAccel(Reflexioner.getSpeed());
		setMax_accel(getAccel() * 4);
		setHp(1000);
		setMax_hp(1000);
		setEnergy(1000);
		setMax_energy(1000);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setHp_heal(1);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
		//setMissiles(2);
		this.enemies = enemies;
		point = Lint.big(0);
	}
	public static List<String> spaceShipKeyList()
	{
		return spaceShipKeyList(1000);
	}
	public static List<String> spaceShipKeyList(int grade_mode)
	{
		Vector<String> lists = new Vector<String>();
		lists.add(new SpaceShip().getKeyName());
		lists.add(new Cruiser().getKeyName());
		lists.add(new Satellite().getKeyName());
		if(grade_mode >= 1) lists.add(new Clipper().getKeyName());
		if(grade_mode >= 2) lists.add(new Warship().getKeyName());
		if(grade_mode >= 2) lists.add(new Chaser().getKeyName());
		if(grade_mode >= 3) lists.add(new Carrier().getKeyName());
		
		return lists;
	}
	public static List<Integer> spaceShipKeyIntsList()
	{
		return spaceShipKeyIntsList(1000);
	}
	public static List<Integer> spaceShipKeyIntsList(int grade_mode)
	{
		Vector<Integer> lists = new Vector<Integer>();
		lists.add(new Integer(new SpaceShip().getKeyInt()));
		lists.add(new Integer(new Cruiser().getKeyInt()));
		lists.add(new Integer(new Satellite().getKeyInt()));
		if(grade_mode >= 1) lists.add(new Integer(new Clipper().getKeyInt()));
		if(grade_mode >= 2) lists.add(new Integer(new Warship().getKeyInt()));
		if(grade_mode >= 2) lists.add(new Integer(new Chaser().getKeyInt()));
		if(grade_mode >= 3) lists.add(new Integer(new Carrier().getKeyInt()));
		
		return lists;
	}
	public static List<String> spaceShipNameList(Setting sets)
	{
		return spaceShipNameList(sets, 1000);
	}
	public static List<String> spaceShipNameList(Setting sets, int grade_mode)
	{
		Vector<String> lists = new Vector<String>();
		lists.add(new SpaceShip().getName(sets));
		lists.add(new Cruiser().getName(sets));
		lists.add(new Satellite().getName(sets));
		if(grade_mode >= 1) lists.add(new Clipper().getName(sets));
		if(grade_mode >= 2) lists.add(new Warship().getName(sets));
		if(grade_mode >= 2) lists.add(new Chaser().getName(sets));
		if(grade_mode >= 3) lists.add(new Carrier().getName(sets));
		
		return lists;
	}
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(5);
		setW2_fireDelay(4);
		setW3_fireDelay(12);
	}
	public void setImage(int weapon, BufferedImage img)
	{
		switch(weapon)
		{
			case 1:
				image_w1 = img;
				if(image_w1 != null) image_w1_exist = true;
				break;
			case 2:
				image_w2 = img;
				if(image_w2 != null) image_w2_exist = true;
				break;
			case 3:
				image_w3 = img;
				if(image_w3 != null) image_w3_exist = true;
				break;
		}		
	}
	@Override
	public Area area()
	{
		switch(mode) // new Area(new Ellipse2D.Double((double) getX(), (double)getY(), (double)getR(), (double)getR()));
		{
			case 1:
				if(image_w1_exist)
					return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
				else
					return super.area();
			case 2:
				if(image_w2_exist)
					return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
				else
					return super.area();
			case 3:
				if(image_w3_exist)
					return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
				else
					return super.area();
			default:
				if(image_w1_exist)
					return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
				else
					return super.area();
		}
	}
	@Override
	public void draw(Graphics g)		
	{
		if(getHp() <= 0) return;
		switch(mode)
		{
			case 1:
				if(image_w1_exist)				
					g.drawImage(image_w1, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);				
				else
					super.draw(g);
				break;
			case 2:
				if(image_w2_exist)
					g.drawImage(image_w2, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);
				else
					super.draw(g);
				break;
			case 3:
				if(image_w3_exist)
					g.drawImage(image_w3, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);
				else
					super.draw(g);
				break;
			default:
				if(image_w1_exist)
					g.drawImage(image_w1, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);
				else
					super.draw(g);
				break;
		}
	}
	public void removeImage(int weapon)
	{
		switch(weapon)
		{
			case 1:
				image_w1 = null;
				image_w1_exist = false;
				break;
			case 2:
				image_w2 = null;
				image_w2_exist = false;
				break;
			case 3:
				image_w3 = null;
				image_w3_exist = false;
				break;
		}
	}
	public String getKeyName()
	{
		return "flex";
	}
	public int getKeyInt()
	{
		return FLEX;
	}
	
	public boolean[] loadImages()
	{
		return loadImages(System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator"));
	}
	
	public boolean loadImage(int ind)
	{
		return loadImage(System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator"), ind);
	}
	public boolean loadImage(String path, int ind)
	{
		boolean results = false;
		File w;
		if(! Reflexioner.image_allow) return false;
		if(loadCacheImage(ind)) return true;
		
		switch(ind)
		{
			case 1:			
				image_w1_exist = false;
				if(! image_w1_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w1.png"));
						if(w.exists())
						{
							image_w1 = ImageIO.read(w);
							image_w1_exist = true;
							results = true;
						}
						else
						{
							image_w1_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w1_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w1_exist = false;
						results = false;
					}
				}
				if(! image_w1_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w1.jpg"));
						if(w.exists())
						{
							image_w1 = ImageIO.read(w);
							image_w1_exist = true;
							results = true;
						}
						else
						{
							image_w1_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w1_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w1_exist = false;
						results = false;
					}
				}	
				if(! image_w1_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
						if(! w.exists()) w = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));
						
						if(w.exists())
						{
							image_w1 = ImageIO.read(w);
							image_w1_exist = true;
							results = true;
						}
						else
						{
							image_w1_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w1_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w1_exist = false;
						results = false;
					}
				}
				break;
			case 2:
				image_w2_exist = false;
				if(! image_w2_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w2.png"));
						if(w.exists())
						{
							image_w2 = ImageIO.read(w);
							image_w2_exist = true;
							results = true;
						}
						else
						{
							image_w2_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w2_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w2_exist = false;
						results = false;
					}
				}
				if(! image_w2_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w2.jpg"));
						if(w.exists())
						{
							image_w2 = ImageIO.read(w);
							image_w2_exist = true;
							results = true;
						}
						else
						{
							image_w2_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w2_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w2_exist = false;
						results = false;
					}
				}
				if(! image_w2_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
						if(! w.exists()) w = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));
						if(w.exists())
						{
							image_w2 = ImageIO.read(w);
							image_w2_exist = true;
							results = true;
						}
						else
						{
							image_w2_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w2_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w2_exist = false;
						results = false;
					}
				}
				break;
			case 3:
				image_w3_exist = false;
				if(! image_w3_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w3.png"));
						if(w.exists())
						{
							image_w3 = ImageIO.read(w);
							image_w3_exist = true;
							results = true;
						}
						else
						{
							image_w3_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w3_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w3_exist = false;
						results = false;
					}
				}
				if(! image_w3_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + getKeyName() + "_w3.jpg"));
						if(w.exists())
						{
							image_w3 = ImageIO.read(w);
							image_w3_exist = true;
							results = true;
						}
						else
						{
							image_w3_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w3_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w3_exist = false;
						results = false;
					}
				}
				if(! image_w3_exist)
				{
					try
					{
						w = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
						if(! w.exists()) w = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));
						if(w.exists())
						{
							image_w3 = ImageIO.read(w);
							image_w3_exist = true;
							results = true;
						}
						else
						{
							image_w3_exist = false;
							results = false;
						}
					} 
					catch (FileNotFoundException e)
					{
						image_w3_exist = false;
						results = false;
					}
					catch (IOException e)
					{
						e.printStackTrace();
						image_w3_exist = false;
						results = false;
					}
				}
				break;
		}
		return results;
	}
	
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_flex_w1;
				if(target != null)
				{
					image_w1 = target;
					image_w1_exist = true;
					return true;
				}
				else
				{
					image_w1_exist = false;
				}
				break;
			case 2:
				target = ImageCache.img_flex_w2;
				if(target != null)
				{
					image_w2 = target;
					image_w2_exist = true;
					return true;
				}
				else
				{
					image_w2_exist = false;
				}
				break;
			case 3:
				target = ImageCache.img_flex_w3;
				if(target != null)
				{
					image_w3 = target;
					image_w3_exist = true;
					return true;
				}
				else
				{
					image_w3_exist = false;
				}
				break;
		}
		return false;
	}
	public boolean[] loadImages(String path)
	{
		boolean[] results = new boolean[3];
		for(int i=0; i<results.length; i++)
		{
			results[i] = false;
		}
		
		if(Reflexioner.image_allow)
		{
			for(int i=0; i<results.length; i++)
			{
				results[i] = loadImage(path, i+1);
			}
			image_w1_exist = results[0];
			image_w2_exist = results[1];
			image_w3_exist = results[2];
		}
		else
		{
			image_w1_exist = false;
			image_w2_exist = false;
			image_w3_exist = false;			
		}
		
		
		return results;
	}
	public void setEnemyList(List<Enemy> enemies)
	{
		this.enemies = enemies;
	}
	public int afterFireDelay()
	{
		switch(mode)
		{
			case 1:
				return w1_fireDelay;
			case 2:
				return w2_fireDelay;
			case 3:
				return w3_fireDelay;
			default:
				return w1_fireDelay;
		}
	}
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = missiles / 2.0;
		switch(mode)
		{
			case 1:
				fire_missiles = missiles;
				if(fire_missiles >= 4) fire_missiles = 3;
				centers = fire_missiles / 2.0;
				for(int i=0; i<fire_missiles; i++)
				{
					newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30.0));
					newMissile.setY(getY() - getR());
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					if(fire_missiles >= 1)
						newMissile.setDamage(Math.round(getDamage() / ((double) fire_missiles - ((fire_missiles - 1.0) / 2.0) ) ));
					newMissiles.add(newMissile);
				}				
				SoundCache.play("fire_missile");
				break;
			case 2:
				if(getEnergy() >= 100)
				{					
					fire_missiles = missiles;
					if(fire_missiles >= 10) fire_missiles = 9;
					centers = fire_missiles / 2.0;
					for(int i=0; i<fire_missiles; i++)
					{
						newMissile = new GuidedMissile(enemies, this);
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
						newMissile.setY(getY() - getR());
						((GuidedMissile) newMissile).setSpeed(((GuidedMissile) newMissile).getSpeed() * 1);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						if(fire_missiles >= 1)
							newMissile.setDamage(Math.round((getDamage() / 1.25) / ((double) fire_missiles - ((fire_missiles - 1.0) / 1.5    ) ) ));
						newMissiles.add(newMissile);
					}	
					setEnergy(getEnergy() - 100);
					if(fire_missiles <= 4)
					{
						SoundCache.play("fire_missile");
					}
					else
					{
						SoundCache.play("fire_multiple_missiles");
					}
				}
				
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					newMissile = new Beam(11);
					newMissile.setX(getX() - ((int) Math.round(getR() / 2.0)));					
					newMissile.setY(0);
					newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
					//System.out.println(this.getY() + ", " + (newMissile.getH() - (Reflexioner.size_y - this.getY())));
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage(getDamage() * 3);
					newMissiles.add(newMissile);
					setEnergy(getEnergy() - 300);
					SoundCache.play("fire_beam");
				}				
				
				break;
				
			default:
				mode = 1;
		}
		return newMissiles;
	}
	public double controlif_missile_right()
	{
		return -1.0;
	}
	public double controlif_missile_left()
	{
		return -1.0;
	}
	public double controlif_missile_centers()
	{
		return 2.0;
	}
	public double controlif_missile_centers_shift()
	{
		return 0.4;
	}
	public double controlif_enemy_left()
	{
		return 0.1;
	}
	public double controlif_enemy_right()
	{
		return 0.1;
	}
	@Override
	public void control_auto(List<Enemy> enemyList, List<Missile> missileList)
	{
		double left = 0.0, right = 0.0, shift = 0.0;
		
		
		Missile targets = null;
		Area leftArea = new Area(new Rectangle(getX() - (Reflexioner.getSize_x() / 10), getY() - getR(), (Reflexioner.getSize_x() / 10), Reflexioner.getSize_y() / 5));
		Area rightArea = new Area(new Rectangle(getX(), getY() - getR(), (Reflexioner.getSize_x() / 10), Reflexioner.getSize_y() / 5));
		int center_exist = 0;
		for(Missile m : missileList)
		{	
			targets = null;
			center_exist = 0;
			if(m != null)
			{
				if(m.getOwner() != Missile.SPACESHIP)
					targets = m.clone(true);
				else targets = null;
				
				if(targets != null)
				{
					targets.setY(targets.getY() + targets.getDy());
					
					if(targets.collapse(rightArea))
					{
						right = right + controlif_missile_right();
					}
					else if(targets.collapse(leftArea))
					{
						left = left + controlif_missile_left();
					}
					
					if(Math.abs(targets.getX() - getX()) < getR() * 2)
					{
						center_exist++;
					}
					
				}
			}
		}
		
		for(int i=0; i<center_exist; i++)
		{
			if(right > left)
			{
				right = right * controlif_missile_centers();
			}
			else
			{
				left = left * controlif_missile_centers();
			}
		}
		shift = shift + (center_exist * controlif_missile_centers_shift());
		for(Enemy e : enemyList)
		{
			if(e != null)
			{
				if(e instanceof Boss)
				{
					if((((Boss) e).getBeam_energy() <= (((Boss) e).getBeam_std() - (((Boss) e).getBeam_std() / 9.5)))
											&& (((Boss) e).getBeam_energy() >= (((Boss) e).getBeam_std() - (((Boss) e).getBeam_std() / 8.5))))
					{
						if((e instanceof ExtremeBoss) || (e instanceof HyperBoss) || (e instanceof UltraBoss) || (e instanceof SealedBoss))
						{
							if(getX() > e.getX())
							{
								left = left + controlif_enemy_left();
							}
							else if(getX() < e.getX())
							{
								right = right + controlif_enemy_right();
							}
						}
						else
						{
							if(Math.abs(getX() - e.getX()) < 15)
							{
								shift = shift + 0.5;
								if(left > right)
									left = left + controlif_missile_centers();
								else
									right = right + controlif_missile_centers();
							}
						}
					}
				}
				else
				{
					if(getX() > e.getX())
					{
						left = left + controlif_enemy_left();
					}
					else if(getX() < e.getX())
					{
						right = right + controlif_enemy_right();
					}
				}				
			}
		}
		
		if(right > left && getX() >= Reflexioner.getSize_x() - (Reflexioner.getSize_y() / 4))
		{
			left = left + Math.abs(right) + 2;
		}
		else if(right < left && getX() <= (Reflexioner.getSize_y() / 4))
		{
			right = right + Math.abs(left) + 2;
		}
		
		if(shift >= 1.0)
		{
			if(left > right)
			{
				control_a();
			}
			else
			{
				control_d();
			}
		}
		if(left == right)
		{
			control_break();
		}
		else if(left > right)
		{
			control_left();
		}
		else
		{
			control_right();
		}		
	}
	public void control(int k)
	{
		switch(k)
		{
			case KeyEvent.VK_1:
				control_1();
				break;
			case KeyEvent.VK_2:
				control_2();
				break;
			case KeyEvent.VK_3:
				control_3();
				break;
			case KeyEvent.VK_SHIFT:
				control_break();
				break;
			case KeyEvent.VK_UP:
				control_up();
				break;				
			case KeyEvent.VK_DOWN:
				control_down();
				break;
			case KeyEvent.VK_LEFT:
				control_left();
				break;
			case KeyEvent.VK_RIGHT:
				control_right();
				break;
			case KeyEvent.VK_W:
				control_w();
				break;
			case KeyEvent.VK_A:
				control_a();
				break;
			case KeyEvent.VK_S:
				control_s();
				break;
			case KeyEvent.VK_D:
				control_d();
				break;
		}
	}
	@Override
	public void control_up()
	{		
		accel_y = -accel;			
	}
	@Override
	public void control_down()
	{
		accel_y = accel;
	}
	@Override
	public void control_left()
	{
		accel_x = -accel;
	}
	@Override
	public void control_right()
	{
		accel_x = accel;
	}
	@Override
	public void control_break()
	{
		accel_x = 0;
		accel_y = 0;
	}
	@Override
	public void control_w()
	{
		if(Arena.swift_available())
		{
			setY(getY() - getAccel());
			setAccel_y(0);
			Arena.setSwiftDelay(2);
		}		
	}
	@Override
	public void control_s() 
	{
		if(Arena.swift_available())
		{
			setY(getY() + getAccel());
			setAccel_y(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	public void control_a() 
	{
		if(Arena.swift_available())
		{
			setX(getX() - getAccel());
			setAccel_x(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	public void control_d() 
	{
		if(Arena.swift_available())
		{
			setX(getX() + getAccel());
			setAccel_x(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	public void control_1() 
	{
		setMode(1);
	}
	@Override
	public void control_2() 
	{
		setMode(2);
	}
	@Override
	public void control_3() 
	{
		setMode(3);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		getKeyCode = e.getKeyCode();
		if(getHp() <= 0) return;
		if(getKeyCode == Reflexioner.KEY_UP)
		{
			control_up();
		}
		else if(getKeyCode == Reflexioner.KEY_DOWN)
		{
			control_down();
		}
		else if(getKeyCode == Reflexioner.KEY_LEFT)
		{
			control_left();
		}
		else if(getKeyCode == Reflexioner.KEY_RIGHT)
		{
			control_right();
		}
		else if(getKeyCode == Reflexioner.KEY_SHIFT)
		{
			control_break();
		}
		else if(getKeyCode == Reflexioner.KEY_W)
		{
			control_w();
		}
		else if(getKeyCode == Reflexioner.KEY_S)
		{
			control_s();
		}
		else if(getKeyCode == Reflexioner.KEY_A)
		{
			control_a();
		}
		else if(getKeyCode == Reflexioner.KEY_D)
		{
			control_d();
		}
		else if(getKeyCode == Reflexioner.KEY_1)
		{
			control_1();
		}
		else if(getKeyCode == Reflexioner.KEY_2)
		{
			control_2();
		}
		else if(getKeyCode == Reflexioner.KEY_3)
		{
			control_3();
		}		
	}
	@Override
	public void update()
	{
		heal_delay++;
		if(heal_delay >= 10)
		{
			if(getHp() < getMax_hp() - getHp_heal())
			{
				setHp(getHp() + getHp_heal()); 
			}
			heal_delay = 0;
		}
		if(getEnergy() <= getMax_energy() - 1)
		{
			setEnergy(getEnergy() + getEnergy_heal());
		}
		setX(getX() + accel_x);
		setY(getY() + accel_y);
		if(getX() < getR()) 
		{
			setX(getR());
			refresh_autoControl();
		}
		if(getX() > Reflexioner.getSize_x() - getR()) 
		{
			setX(Reflexioner.getSize_x() - getR());
			refresh_autoControl();
		}
		if(getY() < getR() + (Reflexioner.getSize_y() / 3)) 
		{
			setY(getR() + (Reflexioner.getSize_y() / 3));
		}
		if(getY() > (Reflexioner.getSize_y() - getR())) 
		{
			setY((Reflexioner.getSize_y() - getR()));
		}
	}
	public void refresh_autoControl()
	{
		if(Arena.isAutoControlMode() || notOwn)
			setAccel_x(getAccel_x() * -1);		
	}
	public long getDamage()
	{
		return damage;
	}
	public void setDamage(long damage)
	{
		this.damage = damage;
	}
	public int getAccel()
	{
		return accel;
	}
	public void setAccel(int accel)
	{
		this.accel = accel;
	}
	public int getMissiles()
	{
		return missiles;
	}
	public void setMissiles(int missiles)
	{
		this.missiles = missiles;
	}
	public BigInteger getPoint()
	{
		return point;
	}
	public void setPoint(BigInteger point)
	{
		this.point = point;
	}
	public long getEnergy()
	{
		return energy;
	}
	public void setEnergy(long energy)
	{
		this.energy = energy;
	}
	public int getMode()
	{
		return mode;
	}
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	public long getMax_energy()
	{
		return max_energy;
	}
	public void setMax_energy(long max_energy)
	{
		this.max_energy = max_energy;
	}
	public int getEnergy_heal()
	{
		return energy_heal;
	}
	public void setEnergy_heal(int energy_heal)
	{
		this.energy_heal = energy_heal;
	}
	public int getAccel_x()
	{
		return accel_x;
	}
	public void setAccel_x(int accel_x)
	{
		this.accel_x = accel_x;
	}
	public int getAccel_y()
	{
		return accel_y;
	}
	public void setAccel_y(int accel_y)
	{
		this.accel_y = accel_y;
	}
	public int getFire_missiles()
	{
		return fire_missiles;
	}
	public void setFire_missiles(int fire_missiles)
	{
		this.fire_missiles = fire_missiles;
	}
	public int getHeal_delay()
	{
		return heal_delay;
	}
	public void setHeal_delay(int heal_delay)
	{
		this.heal_delay = heal_delay;
	}
	public int getGetKeyCode()
	{
		return getKeyCode;
	}
	public void setGetKeyCode(int getKeyCode)
	{
		this.getKeyCode = getKeyCode;
	}
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	public void setEnemies(List<Enemy> enemies)
	{
		this.enemies = enemies;
	}
	public int getFire_delay()
	{
		return fire_delay;
	}
	public void setFire_delay(int fire_delay)
	{
		this.fire_delay = fire_delay;
	}
	public int getMax_accel()
	{
		return max_accel;
	}
	public void setMax_accel(int max_accel)
	{
		this.max_accel = max_accel;
	}
	public long getMax_damage()
	{
		return max_damage;
	}
	public void setMax_damage(long max_damage)
	{
		this.max_damage = max_damage;
	}
	public long getMax_hp_heal()
	{
		return max_hp_heal;
	}
	public void setMax_hp_heal(long max_hp_heal)
	{
		this.max_hp_heal = max_hp_heal;
	}
	public long getMax_energy_heal()
	{
		return max_energy_heal;
	}
	public void setMax_energy_heal(long max_energy_heal)
	{
		this.max_energy_heal = max_energy_heal;
	}
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_FLEX);
	}	
	public long difficultyGrade()
	{
		return 0;
	}
	public boolean isImage_w1_exist()
	{
		return image_w1_exist;
	}
	public void setImage_w1_exist(boolean image_w1_exist)
	{
		this.image_w1_exist = image_w1_exist;
	}
	public boolean isImage_w2_exist()
	{
		return image_w2_exist;
	}
	public void setImage_w2_exist(boolean image_w2_exist)
	{
		this.image_w2_exist = image_w2_exist;
	}
	public boolean isImage_w3_exist()
	{
		return image_w3_exist;
	}
	public void setImage_w3_exist(boolean image_w3_exist)
	{
		this.image_w3_exist = image_w3_exist;
	}
	public BufferedImage getImage_w1()
	{
		return image_w1;
	}
	public void setImage_w1(BufferedImage image_w1)
	{
		this.image_w1 = image_w1;
	}
	public BufferedImage getImage_w2()
	{
		return image_w2;
	}
	public void setImage_w2(BufferedImage image_w2)
	{
		this.image_w2 = image_w2;
	}
	public BufferedImage getImage_w3()
	{
		return image_w3;
	}
	public void setImage_w3(BufferedImage image_w3)
	{
		this.image_w3 = image_w3;
	}
	public int getW1_fireDelay()
	{
		return w1_fireDelay;
	}
	public void setW1_fireDelay(int w1_fireDelay)
	{
		this.w1_fireDelay = w1_fireDelay;
	}
	public int getW2_fireDelay()
	{
		return w2_fireDelay;
	}
	public void setW2_fireDelay(int w2_fireDelay)
	{
		this.w2_fireDelay = w2_fireDelay;
	}
	public int getW3_fireDelay()
	{
		return w3_fireDelay;
	}
	public void setW3_fireDelay(int w3_fireDelay)
	{
		this.w3_fireDelay = w3_fireDelay;
	}
	public boolean isNotOwn()
	{
		return notOwn;
	}
	public void setNotOwn(boolean notOwn)
	{
		this.notOwn = notOwn;
	}
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		if(boss_exist)
		{
			value = value + 0.3;
		}
		value = value + ((getMissiles() - 2)/ 100.0);
		return value;
	}
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		return value;
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new SpaceShip(enemies);
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
	protected boolean img_w1_exist()
	{
		return image_w1_exist;
	}
	protected boolean img_w2_exist()
	{
		return image_w2_exist;
	}
	protected boolean img_w3_exist()
	{
		return image_w3_exist;
	}	
}
class Cruiser extends SpaceShip
{
	private static final long serialVersionUID = 5497899391386702566L;
	public Cruiser()
	{
		super();
	}
	public Cruiser(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r() * 1.2));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed() / 1.2));
		setMax_accel(getAccel() * 4);
		setHp_heal(2);
		setHp(2000);
		setMax_hp(2000);
		setEnergy(800);
		setMax_energy(800);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(3);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(3);
		setW2_fireDelay(5);
		setW3_fireDelay(10);
	}
	public String getKeyName()
	{
		return "berserk";
	}
	public int getKeyInt()
	{
		return BERSERK;
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		switch(getMode())
		{
			case 1:
				setFire_missiles(getMissiles());
				if(getFire_missiles() >= 8) setFire_missiles(7);
				centers = getFire_missiles() / 2.0;
				for(int i=0; i<getFire_missiles(); i++)
				{
					newMissile = new Missile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
					newMissile.setY(getY() - getR());
					newMissile.setW((int) Math.round(newMissile.getW() / 1.5));
					newMissile.setLaunched(true);
					if(getFire_missiles() >= 1)
						newMissile.setDamage(Math.round((getDamage() / 3) / ((double) getFire_missiles() - ((getFire_missiles() - 1.0) / 2.0) ) ));
					newMissiles.add(newMissile);
				}				
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 2:
				if(getEnergy() >= 100)
				{					
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 8) setFire_missiles(7);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new SuperMissile();
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
						newMissile.setY(getY() - getR());
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						if(getFire_missiles() >= 1)
							newMissile.setDamage(Math.round((getDamage() * 6) / ((double) getFire_missiles() - ((getFire_missiles() - 1.0) / 2.0) ) ));
						newMissiles.add(newMissile);
					}	
					setEnergy(getEnergy() - 100);
				}
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 13) setFire_missiles(12);
					setFire_missiles(getFire_missiles() / 4);
					if(getFire_missiles() <= 0) setFire_missiles(1);
					
					setFire_missiles(getFire_missiles() * 20);
					
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new GuidedMissile(getEnemies(), this);
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
						newMissile.setY(getY() - getR());
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						((GuidedMissile) newMissile).setSpeed(((GuidedMissile) newMissile).getSpeed() * 1);
						if(getFire_missiles() >= 1)
							newMissile.setDamage(Math.round((getDamage()) / ((double) getFire_missiles() - ((getFire_missiles() - 1.0) / 1.5    ) ) ));
						newMissiles.add(newMissile);
					}	
					setEnergy(getEnergy() - 300);
				}
				SoundCache.play("fire_multiple_missiles");
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_berserk_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_berserk_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_berserk_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		if(boss_exist)
		{
			value = value + 0.3;
		}
		value = value + ((getMissiles() - 5)/ 100.0);
		return value;
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		return value;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_BERSERK);
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Cruiser(getEnemies());
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
class Clipper extends SpaceShip
{
	private static final long serialVersionUID = 4497899391386702566L;
	public Clipper()
	{
		super();
	}
	public Clipper(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r() / 1.2));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed() * 1.4));
		setMax_accel(getAccel() * 4);
		setHp_heal(2);
		setHp(500);
		setMax_hp(500);
		setEnergy(1000);
		setMax_energy(1000);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(1);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
	}
	public String getKeyName()
	{
		return "clipper";
	}
	public int getKeyInt()
	{
		return CLIPPER;
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(5);
		setW2_fireDelay(4);
		setW3_fireDelay(12);
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		switch(getMode())
		{
			case 1:
				newMissile = new Beam(6);
				newMissile.setX(getX());					
				newMissile.setY(0);
				newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
				newMissile.setLaunched(true);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setDamage(Math.round(getDamage() / 3.0));
				newMissiles.add(newMissile);				
				SoundCache.play("fire_beam");
				break;
			case 2:
				if(getEnergy() >= 100)
				{					
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 6) setFire_missiles(5);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new ReflexMissile();	
						newMissile.setHp(600);
						newMissile.setW(newMissile.getW() * 2);
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
						newMissile.setY(getY() - getR());
						((ReflexMissile) newMissile).setDx(0);
						((ReflexMissile) newMissile).setSide_reflex(true);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setDamage(Math.round(getDamage() / 1.5));
						newMissiles.add(newMissile);	
					}
					
					setEnergy(getEnergy() - 100);
					if(getFire_missiles() <= 4)
					{
						SoundCache.play("fire_missile");
					}
					else
					{
						SoundCache.play("fire_multiple_missiles");
					}
				}
				
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					setFire_missiles(getMissiles());
					if(getFire_missiles() < 2) setFire_missiles(2);
					if(getFire_missiles() >= 6) setFire_missiles(5);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new Beam(8);	
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
						newMissile.setY(0);
						newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setDamage(getDamage());
						newMissiles.add(newMissile);	
					}
					
					setEnergy(getEnergy() - 300);
					if(getFire_missiles() <= 4)
					{
						SoundCache.play("fire_beam");
					}
					else
					{
						SoundCache.play("fire_beam");
					}
				}
				
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_clipper_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_clipper_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_clipper_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		value = value + ((getMissiles() - 2)/ 100.0);
		/*
		if(boss_exist)
		{
			value = value + 0.3;
		}
		*/
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		value = value + ((getMissiles() - 1)/ 100.0);
		return value;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_CLIPPER);
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Clipper(getEnemies());
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
class Warship extends Clipper
{
	private static final long serialVersionUID = 5497899391366702566L;
	public Warship()
	{
		super();
	}
	public Warship(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r()));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed()));
		setMax_accel(getAccel() * 4);
		setHp_heal(2);
		setHp(1000);
		setMax_hp(1000);
		setEnergy(1000);
		setMax_energy(1000);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(1);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
	}
	public String getKeyName()
	{
		return "warship";
	}
	public int getKeyInt()
	{
		return WARSHIP;
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(4);
		setW2_fireDelay(4);
		setW3_fireDelay(5);
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_warship_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_warship_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_warship_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		switch(getMode())
		{
			case 1:
				setFire_missiles(getMissiles());
				if(getFire_missiles() >= 4) setFire_missiles(3);
				centers = getFire_missiles() / 2.0;
				for(int i=0; i<getFire_missiles(); i++)
				{
					newMissile = new ReflexMissile();
					newMissile.setW(newMissile.getW() * 3);
					newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
					newMissile.setY(getY() - getR());
					((ReflexMissile) newMissile).setDx(0);
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage(getDamage() / 2);
					newMissiles.add(newMissile);	
				}		
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 2:
				if(getEnergy() >= 100)
				{					
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 10) setFire_missiles(9);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new GuidedMissile(getEnemies(), this);	
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
						newMissile.setY(getY() - getR());
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						((GuidedMissile) newMissile).setSpeed(((GuidedMissile) newMissile).getSpeed() * 1);
						newMissile.setDamage(getDamage());
						newMissiles.add(newMissile);	
					}
					
					setEnergy(getEnergy() - 100);
					if(getFire_missiles() <= 4)
					{
						SoundCache.play("fire_missile");
					}
					else
					{
						SoundCache.play("fire_multiple_missiles");
					}
				}
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 6) setFire_missiles(5);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new ReflexMissile();	
						((ReflexMissile) newMissile).setSide_reflex(true);
						newMissile.setW(newMissile.getW() * 5);
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
						newMissile.setY(getY() - getR());
						((ReflexMissile) newMissile).setDx(0);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setDamage(getDamage());
						newMissiles.add(newMissile);	
					}		
					
					setEnergy(getEnergy() - 300);
					if(getFire_missiles() <= 4)
					{
						SoundCache.play("fire_missile");
					}
					else
					{
						SoundCache.play("fire_multiple_missiles");
					}
				}
				
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_WARSHIP);
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Warship(getEnemies());
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
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		
		if(boss_exist)
		{
			value = value + 0.3;
		}
		value = value + ((getMissiles() - 2)/ 100.0);
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		value = value + ((getMissiles() - 2)/ 100.0);
		return value;
	}
}
class Chaser extends Warship
{
	private static final long serialVersionUID = 5467899391366702566L;
	public Chaser()
	{
		super();
	}
	public Chaser(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r() / 1.5));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed()));
		setMax_accel(getAccel() * 7);
		setHp_heal(1);
		setHp(400);
		setMax_hp(400);
		setEnergy(600);
		setMax_energy(600);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(1);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
	}
	public String getKeyName()
	{
		return "chaser";
	}
	public int getKeyInt()
	{
		return CHASER;
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(4);
		setW2_fireDelay(1);
		setW3_fireDelay(11);
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_chaser_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_chaser_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_chaser_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		double combo_ys = 0.0;
		int combo_count = 4;
		switch(getMode())
		{
			case 1:
				setFire_missiles(getMissiles());
				if(getFire_missiles() >= 4) setFire_missiles(3);
				centers = getFire_missiles() / 2.0;
				for(int i=0; i<getFire_missiles(); i++)
				{
					newMissile = new GuidedMissile(getEnemies(), this, Missile.SPACESHIP, Reflexioner.getFile_path());
					((GuidedMissile) newMissile).setSpeed(((GuidedMissile) newMissile).getSpeed() * 2);
					newMissile.setDy(newMissile.getDy() * 2);
					newMissile.setW(newMissile.getW() * 3);
					newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
					newMissile.setY(getY() - getR());
					newMissile.setW(newMissile.getW() / 2);
					newMissile.setH(newMissile.getH() / 2);
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage((int) Math.ceil(getDamage() / 3.0));
					newMissiles.add(newMissile);	
				}		
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 2:
				if(getEnergy() >= 30)
				{					
					setFire_missiles((int) Math.ceil(getMissiles() / 4.0));
					if(getFire_missiles() >= 3) setFire_missiles(2);
					centers = getFire_missiles() / 2.0;
					
					for(int i=0; i<getFire_missiles(); i++)
					{
						for(int j=0; j<combo_count * getFire_missiles(); j++)
						{
							combo_ys = 4.0 - (0.5 * j);
							newMissile = new Missile();
							newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
							newMissile.setY(getY() - (int) Math.round(getR() * combo_ys));						
							newMissile.setLaunched(true);
							newMissile.setOwner(Missile.SPACESHIP);
							newMissile.setDamage(getDamage() / 2);
							newMissiles.add(newMissile);
						}						
					}	
					setEnergy(getEnergy() - 30);
				}
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					newMissile = new Beam(11);
					newMissile.setX(getX() - ((int) Math.round(getR() / 2.0)));					
					newMissile.setY(0);
					newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
					//System.out.println(this.getY() + ", " + (newMissile.getH() - (Reflexioner.size_y - this.getY())));
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage(getDamage() * 3);
					newMissiles.add(newMissile);
					setEnergy(getEnergy() - 300);
					SoundCache.play("fire_beam");
				}
				
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_CHASER);
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		
		if(boss_exist)
		{
			value = value + 0.3;
		}
		value = value + ((getMissiles() - 2)/ 100.0);
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.3;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.05;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.01;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		return value;
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Chaser(getEnemies());
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
class Carrier extends Warship
{
	private static final long serialVersionUID = 5467899391366702566L;
	public Carrier()
	{
		super();
	}
	public Carrier(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r() * 1.5));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed() / 2.0));
		setMax_accel(getAccel() * 7);
		setHp_heal(3);
		setHp(2000);
		setMax_hp(2000);
		setEnergy(1000);
		setMax_energy(5000);
		setMax_hp_heal(getMax_hp() / 20);
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(3);
		setDamage(100);
		setMax_damage(getDamage() * 1000);
	}
	public String getKeyName()
	{
		return "carrier";
	}
	public int getKeyInt()
	{
		return CARRIER;
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(4);
		setW2_fireDelay(20);
		setW3_fireDelay(20);
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_carrier_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_carrier_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_carrier_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		switch(getMode())
		{
			case 1:
				setFire_missiles(getMissiles());
				if(getFire_missiles() >= 4) setFire_missiles(3);
				centers = getFire_missiles() / 2.0;
				for(int i=0; i<getFire_missiles(); i++)
				{
					newMissile = new GuidedMissile(getEnemies(), this, Missile.SPACESHIP, Reflexioner.getFile_path());
					((GuidedMissile) newMissile).setSpeed(((GuidedMissile) newMissile).getSpeed() * 1);
					newMissile.setDy(newMissile.getDy() * 1);
					newMissile.setW(newMissile.getW() * 3);
					newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 30));
					newMissile.setY(getY() - getR());
					newMissile.setW(newMissile.getW() / 2);
					newMissile.setH(newMissile.getH() / 2);
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage((int) Math.ceil(getDamage() / 3.0));
					newMissiles.add(newMissile);	
				}		
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 2:
				if(getEnergy() >= 300)
				{					
					setFire_missiles((int) Math.ceil(getMissiles() / 3.0));
					if(getFire_missiles() >= 4) setFire_missiles(3);
					centers = getFire_missiles() / 2.0;
					
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new HelperSpread();
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
						newMissile.setY(getY() - (int) Math.round(getR()));						
						((HelperSpread) newMissile).setHelper_type(1);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setDamage(getDamage() / 2);
						newMissiles.add(newMissile);					
					}	
					setEnergy(getEnergy() - 300);
				}
				if(getFire_missiles() <= 4)
				{
					SoundCache.play("fire_missile");
				}
				else
				{
					SoundCache.play("fire_multiple_missiles");
				}
				break;
			case 3:
				if(getEnergy() >= 4000)
				{
					setFire_missiles((int) Math.ceil(getMissiles() / 8.0));
					if(getFire_missiles() >= 4) setFire_missiles(3);
					centers = getFire_missiles() / 2.0;
					
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new HelperSpread();
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));
						newMissile.setY(getY() - (int) Math.round(getR()));						
						((HelperSpread) newMissile).setHelper_type(2);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setDamage(getDamage() / 2);
						newMissiles.add(newMissile);					
					}	
					setEnergy(getEnergy() - 4000);
					SoundCache.play("fire_missile");
				}
				
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		
		if(boss_exist)
		{
			value = value + 0.3;
		}
		value = value + ((getMissiles() - 1)/ 100.0);
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.3;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.1;
		value = value + ((getMissiles() - 1)/ 100.0);
		return value;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_CARRIER);
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Carrier(getEnemies());
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
class Satellite extends SpaceShip
{
	private static final long serialVersionUID = 4497898391386702566L;
	public Satellite()
	{
		super();
	}
	public Satellite(List<Enemy> enemies)
	{
		super(enemies);
		setX((int) (Reflexioner.getSize_x() / 3.0));
		setY((int) (Reflexioner.getSize_y() / 1.5));
		setR((int) Math.round(Reflexioner.getSpaceShip_r() / 2.0));
		setColor(Reflexioner.color_spaceShip);
		setAccel((int) Math.round(Reflexioner.getSpeed() * 1.6));
		setMax_accel(getAccel() * 8);
		setHp_heal(20);
		setHp(50);
		setMax_hp(100);
		setEnergy(1000);
		setMax_energy(1000);
		setMax_hp_heal(getMax_hp());
		setMax_energy_heal(getMax_energy() / 20);
		setMissiles(2);
		setDamage(90);
		setMax_damage(getDamage() * 1000);
	}
	public String getKeyName()
	{
		return "satellite";
	}
	public int getKeyInt()
	{
		return SATELLITE;
	}
	@Override
	public void control_w()
	{
		if(Arena.swift_available())
		{
			setY(getY() - (getAccel() * 4));
			setAccel_y(0);
			Arena.setSwiftDelay(2);
		}		
	}
	@Override
	public void control_s() 
	{
		if(Arena.swift_available())
		{
			setY(getY() + (getAccel() * 4));
			setAccel_y(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	public void control_a() 
	{
		if(Arena.swift_available())
		{
			setX(getX() - (getAccel() * 4));
			setAccel_x(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	public void control_d() 
	{
		if(Arena.swift_available())
		{
			setX(getX() + (getAccel() * 4));
			setAccel_x(0);
			Arena.setSwiftDelay(2);
		}
	}
	@Override
	protected void setFireDelaySeries()
	{
		setW1_fireDelay(4);
		setW2_fireDelay(4);
		setW3_fireDelay(12);
	}
	@Override
	public List<Missile> fire()
	{
		List<Missile> newMissiles = new Vector<Missile>();
		Missile newMissile;
		double centers = getMissiles() / 2.0;
		switch(getMode())
		{
			case 1:
				newMissile = new Beam(6);
				newMissile.setX(getX());					
				newMissile.setY(0);
				newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
				newMissile.setLaunched(true);
				newMissile.setOwner(Missile.SPACESHIP);
				newMissile.setDamage(Math.round(getDamage() / 3.0));
				newMissiles.add(newMissile);				
				SoundCache.play("fire_beam");
				break;
			case 2:
				if(getEnergy() >= 100)
				{					
					setFire_missiles(getMissiles());
					if(getFire_missiles() >= 6) setFire_missiles(5);
					centers = getFire_missiles() / 2.0;
					for(int i=0; i<getFire_missiles(); i++)
					{
						newMissile = new Beam(6);
						newMissile.setX(getX() + (int) Math.round((centers - i - 0.5) * 15));					
						newMissile.setY(0);
						newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
						newMissile.setLaunched(true);
						newMissile.setOwner(Missile.SPACESHIP);
						newMissile.setColor(new Color(Reflexioner.color_spaceShip_missile.getRed() / 2, Reflexioner.color_spaceShip_missile.getGreen(), Reflexioner.color_spaceShip_missile.getBlue()));
						newMissile.setDamage(Math.round(getDamage() / 2.0));
						newMissiles.add(newMissile);
					}
					
					setEnergy(getEnergy() - 100);
					SoundCache.play("fire_beam");
				}
				
				break;
			case 3:
				if(getEnergy() >= 300)
				{
					newMissile = new PulseMissile(Reflexioner.getFile_path(), Missile.SPACESHIP);
					newMissile.setX(getX());
					newMissile.setY(getY() - getR());
					newMissile.setH(newMissile.getH() - (Reflexioner.getSize_y() - this.getY()) - 10);
					newMissile.setLaunched(true);
					newMissile.setOwner(Missile.SPACESHIP);
					newMissile.setDamage(Math.round(getDamage() * 30.0));
					newMissile.setColor(new Color(Reflexioner.color_spaceShip_missile.getRed(), Reflexioner.color_spaceShip_missile.getGreen() / 2, Reflexioner.color_spaceShip_missile.getBlue() / 2));
					newMissiles.add(newMissile);
					setEnergy(getEnergy() - 300);
					SoundCache.play("fire_beam");
				}
				
				break;
				
			default:
				setMode(1);
		}
		return newMissiles;
	}
	@Override
	protected boolean loadCacheImage(int ind)
	{
		BufferedImage target = null;
		switch(ind)
		{
			case 1:
				target = ImageCache.img_satellite_w1;
				if(target != null)
				{
					setImage_w1(target);
					setImage_w1_exist(true);
					return true;
				}
				else
				{
					setImage_w1_exist(false);
				}
				break;
			case 2:
				target = ImageCache.img_satellite_w2;
				if(target != null)
				{
					setImage_w2(target);
					setImage_w2_exist(true);
					return true;
				}
				else
				{
					setImage_w2_exist(false);
				}
				break;
			case 3:
				target = ImageCache.img_satellite_w3;
				if(target != null)
				{
					setImage_w3(target);
					setImage_w3_exist(true);
					return true;
				}
				else
				{
					setImage_w3_exist(false);
				}
				break;
		}
		return false;
	}
	@Override
	public double ai_advantage_mode2(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(enemies >= max_enemy_limit * 0.9) value = value + 0.4;
		else if(enemies >= max_enemy_limit * 0.8) value = value + 0.2;
		else if(enemies >= max_enemy_limit * 0.7) value = value + 0.1;
		else if(enemies >= max_enemy_limit * 0.6) value = value + 0.05;
		if(underOnes >= 3) value = value + 0.3;
		else if(underOnes >= 1) value = value + 0.1;
		value = value + ((getMissiles() - 2)/ 100.0);
		/*
		if(boss_exist)
		{
			value = value + 0.3;
		}
		*/
		
		return value;
	}
	@Override
	public double ai_advantage_mode3(int enemies, int max_enemy_limit, int underOnes, boolean boss_exist)
	{
		double value = 0.0;
		if(boss_exist) value = value + 0.1;
		if(underOnes >= 3) value = value + 0.4;
		else if(underOnes >= 1) value = value + 0.3;
		value = value + ((getMissiles() - 1)/ 100.0);
		return value;
	}
	@Override
	public String getName(Setting sets)
	{
		return sets.getLang().getText(Language.REFLEX_SATELLITE);
	}
	public SpaceShip clone()
	{
		return clone(false);
	}
	@Override
	public SpaceShip clone(boolean imgnull)
	{
		SpaceShip results = new Satellite(getEnemies());
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
