package reflex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import main_classes.RunManager;
import setting.Setting;

public class ImageCache
{
	public static BufferedImage img_flex_w1 = null, img_flex_w2 = null, img_flex_w3 = null, img_berserk_w1 = null, img_berserk_w2 = null, img_berserk_w3 = null;
	public static BufferedImage img_clipper_w1 = null, img_clipper_w2 = null, img_clipper_w3 = null, img_warship_w1 = null, img_warship_w2 = null, img_warship_w3 = null;
	public static BufferedImage img_satellite_w1 = null, img_satellite_w2 = null, img_satellite_w3 = null;
	public static BufferedImage img_chaser_w1 = null, img_chaser_w2 = null, img_chaser_w3 = null;
	public static BufferedImage img_carrier_w1 = null, img_carrier_w2 = null, img_carrier_w3 = null;
	public static BufferedImage img_enemy = null, img_bigEnemy = null, img_boss = null, img_ovalboom = null, img_enemy_ovalboom = null, img_itemuseboom = null;
	public static BufferedImage img_r_enemy = null, img_r_bigEnemy = null, img_r_boss = null;
	public static BufferedImage img_missile = null, img_enemy_missile = null, img_guide_missile = null, img_enemy_guide_missile = null, img_direct_missile = null, img_enemy_direct_missile = null;
	public static BufferedImage img_flex_missile = null, img_enemy_flex_missile = null, img_beam = null, img_enemy_beam = null, img_super_missile = null, img_enemy_super_missile = null;
	public static BufferedImage img_background = null, img_decorate = null;
	public static void prepareImage(Setting sets)
	{
		Setting st = sets;
		if(! Reflexioner.image_allow) return;
		if(st == null) st = Setting.getNewInstance();
		String path = st.getDefault_path();
		flex_prepare(path);
		berserk_prepare(path);
		clipper_prepare(path);
		warship_prepare(path);
		chaser_prepare(path);
		carrier_prepare(path);
		satellite_prepare(path);
		File target = null;
		String keyname = null;
		try
		{
			keyname = new Enemy().getEnemyName();
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_enemy = ImageIO.read(target);
			}
			else img_enemy = null;
		}
		catch(Exception e)
		{
			img_enemy = null;
		}
		try
		{
			keyname = new Enemy().getEnemyName();
			target = new File(RunManager.r65279(path + "r_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_r_enemy = ImageIO.read(target);
			}
			else img_r_enemy = null;
		}
		catch(Exception e)
		{
			img_r_enemy = null;
		}
		try
		{
			keyname = new BigEnemy().getEnemyName();
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_bigEnemy = ImageIO.read(target);
			}
			else img_bigEnemy = null;
		}
		catch(Exception e)
		{
			img_bigEnemy = null;
		}
		try
		{
			keyname = new BigEnemy().getEnemyName();
			target = new File(RunManager.r65279(path + "r_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_r_bigEnemy = ImageIO.read(target);
			}
			else img_r_bigEnemy = null;
		}
		catch(Exception e)
		{
			img_r_bigEnemy = null;
		}
		try
		{
			keyname = new Boss().getEnemyName();
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_boss = ImageIO.read(target);
			}
			else img_boss = null;
		}
		catch(Exception e)
		{
			img_boss = null;
		}
		try
		{
			keyname = new Boss().getEnemyName();
			target = new File(RunManager.r65279(path + "r_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_r_boss = ImageIO.read(target);
			}
			else img_r_boss = null;
		}
		catch(Exception e)
		{
			img_r_boss = null;
		}
		try
		{
			Missile missile = new Missile();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_missile = ImageIO.read(target);
			}
			else img_enemy_missile = null;
		}
		catch(Exception e)
		{
			img_enemy_missile = null;
		}
		try
		{
			Missile missile = new Missile();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_missile = ImageIO.read(target);
			}
			else img_missile = null;
		}
		catch(Exception e)
		{
			img_missile = null;
		}
		
		try
		{
			Missile missile = new GuidedMissile();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_guide_missile = ImageIO.read(target);
			}
			else img_enemy_guide_missile = null;
		}
		catch(Exception e)
		{
			img_enemy_guide_missile = null;
		}
		try
		{
			Missile missile = new GuidedMissile();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_guide_missile = ImageIO.read(target);
			}
			else img_guide_missile = null;
		}
		catch(Exception e)
		{
			img_guide_missile = null;
		}
		
		try
		{
			Missile missile = new SuperMissile();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_super_missile = ImageIO.read(target);
			}
			else img_enemy_super_missile = null;
		}
		catch(Exception e)
		{
			img_enemy_super_missile = null;
		}
		try
		{
			Missile missile = new SuperMissile();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_super_missile = ImageIO.read(target);
			}
			else img_super_missile = null;
		}
		catch(Exception e)
		{
			img_super_missile = null;
		}
		
		try
		{
			Missile missile = new ReflexMissile();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_flex_missile = ImageIO.read(target);
			}
			else img_enemy_flex_missile = null;
		}
		catch(Exception e)
		{
			img_enemy_flex_missile = null;
		}
		try
		{
			Missile missile = new ReflexMissile();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_flex_missile = ImageIO.read(target);
			}
			else img_flex_missile = null;
		}
		catch(Exception e)
		{
			img_flex_missile = null;
		}
		
		try
		{
			Missile missile = new DirectMissile();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_direct_missile = ImageIO.read(target);
			}
			else img_enemy_direct_missile = null;
		}
		catch(Exception e)
		{
			img_enemy_direct_missile = null;
		}
		try
		{
			Missile missile = new DirectMissile();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_direct_missile = ImageIO.read(target);
			}
			else img_direct_missile = null;
		}
		catch(Exception e)
		{
			img_direct_missile = null;
		}
		
		try
		{
			Missile missile = new Beam();
			missile.setOwner(0);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_enemy_beam = ImageIO.read(target);
			}
			else img_enemy_beam = null;
		}
		catch(Exception e)
		{
			img_enemy_beam = null;
		}
		try
		{
			Missile missile = new Beam();
			missile.setOwner(-1);
			keyname = missile.getMissileName();
			target = new File(RunManager.r65279(path + "missile_" + keyname + ".png"));			
			if(! target.exists()) target = new File(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
			if(target.exists())
			{
				img_beam = ImageIO.read(target);
			}
			else img_beam = null;
		}
		catch(Exception e)
		{
			img_beam = null;
		}
		
		try
		{
			OvalBoom boom = new OvalBoom();
			boom.setMaker(0);
			keyname = boom.getBoomName(true);
			String keyname2 = boom.getBoomName(false);
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname2 + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname2 + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_enemy_ovalboom = ImageIO.read(target);
			}
			else img_enemy_ovalboom = null;
		}
		catch(Exception e)
		{
			img_enemy_ovalboom = null;
		}
		try
		{
			OvalBoom boom = new OvalBoom();
			boom.setMaker(-1);
			keyname = boom.getBoomName(true);
			String keyname2 = boom.getBoomName(false);
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname2 + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname2 + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_ovalboom = ImageIO.read(target);
			}
			else img_ovalboom = null;
		}
		catch(Exception e)
		{
			img_ovalboom = null;
		}
		try
		{
			ItemUseBoom boom = new ItemUseBoom();
			keyname = boom.getBoomName(true);
			target = new File(RunManager.r65279(path + keyname + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + keyname + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_itemuseboom = ImageIO.read(target);
			}
			else img_itemuseboom = null;
		}
		catch(Exception e)
		{
			img_itemuseboom = null;
		}
		try
		{
			
			target = new File(RunManager.r65279(path + "background.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "background.jpg"));
			if(target.exists())
			{
				img_background = ImageIO.read(target);
			}
			else img_background = null;
		}
		catch(Exception e)
		{
			img_background = null;
		}
		try
		{			
			target = new File(RunManager.r65279(path + "deco_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + "default" + ".jpg"));
			if(target.exists())
			{
				img_decorate = ImageIO.read(target);
			}
			else img_decorate = null;
		}
		catch(Exception e)
		{
			img_decorate = null;
		}
	}
	private static void flex_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "flex" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "flex" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_flex_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_flex_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "flex" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "flex" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_flex_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_flex_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "flex" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "flex" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_flex_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_flex_w3 = null;
		}
	}
	private static void berserk_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "berserk" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "berserk" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_berserk_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_berserk_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "berserk" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "berserk" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_berserk_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_berserk_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "berserk" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "berserk" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_berserk_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_berserk_w3 = null;
		}
	}
	private static void clipper_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "clipper" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "clipper" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_clipper_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_clipper_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "clipper" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "clipper" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_clipper_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_clipper_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "clipper" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "clipper" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_clipper_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_clipper_w3 = null;
		}
	}
	private static void warship_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "warship" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "warship" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_warship_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_warship_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "warship" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "warship" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_warship_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_warship_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "warship" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "warship" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_warship_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_warship_w3 = null;
		}
	}
	private static void chaser_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "chaser" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "chaser" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_chaser_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_chaser_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "chaser" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "chaser" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_chaser_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_chaser_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "chaser" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "chaser" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_chaser_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_chaser_w3 = null;
		}
	}
	private static void carrier_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "carrier" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "carrier" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_carrier_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_carrier_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "carrier" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "carrier" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_carrier_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_carrier_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "carrier" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "carrier" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_carrier_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_carrier_w3 = null;
		}
	}
	private static void satellite_prepare(String path)
	{
		File target = null;
		try
		{
			target = new File(RunManager.r65279(path + "satellite" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "satellite" + "_w1.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w1.jpg"));			
			img_satellite_w1 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_satellite_w1 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "satellite" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "satellite" + "_w2.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w2.jpg"));			
			img_satellite_w2 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_satellite_w2 = null;
		}
		try
		{
			target = new File(RunManager.r65279(path + "satellite" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "satellite" + "_w3.jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "default_space" + "_w3.jpg"));			
			img_satellite_w3 = ImageIO.read(target);
		}
		catch(Exception e)
		{
			img_satellite_w3 = null;
		}
	}
	public static List<String> needFiles(Setting st)
	{
		return needFiles(st, false);
	}
	public static List<String> needFiles(Setting st, boolean descriptions)
	{
		Vector<String> lists = new Vector<String>();
		
		if(descriptions) lists.add("");
		if(descriptions) lists.add("도형을 대체해 이미지로 적과 함선, 미사일 등을 표시하려면 이미지 파일이 필요합니다.");
		if(descriptions) lists.add("");
		if(descriptions) lists.add("다음은 필요한 이미지 파일 목록입니다. 다 있을 필요는 없으며, 각 항목 당 하나씩만 있으면 됩니다.");
		if(descriptions) lists.add("");
		if(descriptions) lists.add("파일 이름 뿐만 아니라 경로까지 포함해 보여집니다. 해당 경로에 해당 이름으로 이미지 파일을 넣어야 한다는 뜻입니다.");
		if(descriptions) lists.add("");
				
		String path = st.getDefault_path();		
		
		if(descriptions) lists.add("기본적인 적을 대신할 이미지 파일로 사용됩니다.");
		String keyname = new Enemy().getEnemyName();		
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));	
		if(descriptions) lists.add("");
		if(descriptions) lists.add("기본적인 적을 대신할 이미지 파일로 사용됩니다. (180도 뒤집힌 이미지)");
		lists.add(RunManager.r65279(path + "r_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("향상된 적을 대신할 이미지 파일로 사용됩니다.");
		keyname = new BigEnemy().getEnemyName();
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("향상된 적을 대신할 이미지 파일로 사용됩니다. (180도 뒤집힌 이미지)");
		lists.add(RunManager.r65279(path + "r_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("큰 적을 대신할 이미지 파일로 사용됩니다.");
		keyname = new Boss().getEnemyName();
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "enemy_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("큰 적을 대신할 이미지 파일로 사용됩니다. (180도 뒤집힌 이미지)");
		lists.add(RunManager.r65279(path + "r_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "r_" + "enemy_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 기본적인 발사체를 대신할 이미지 파일로 사용됩니다.");
		Missile missile = new Missile();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));		
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 기본적인 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new Missile();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 유도 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new GuidedMissile();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 유도 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new GuidedMissile();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 강화된 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new SuperMissile();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 강화된 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new SuperMissile();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 반사성 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new ReflexMissile();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 반사성 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new ReflexMissile();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 방향성 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new DirectMissile();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 방향성 발사체를 대신할 이미지 파일로 사용됩니다.");
		missile = new DirectMissile();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적이 발사하는 빔을 대신할 이미지 파일로 사용됩니다.");
		missile = new Beam();
		missile.setOwner(0);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "enemy_missile_" + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선이 발사하는 빔을 대신할 이미지 파일로 사용됩니다.");
		missile = new Beam();
		missile.setOwner(-1);
		keyname = missile.getMissileName();
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".png"));
		lists.add(RunManager.r65279(path + "missile_" + keyname + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("적의 공격으로 발생한 폭발을 대신할 이미지 파일로 사용됩니다.");
		OvalBoom boom = new OvalBoom();
		boom.setMaker(0);
		keyname = boom.getBoomName(true);
		String keyname2 = boom.getBoomName(false);
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + keyname2 + ".png"));
		lists.add(RunManager.r65279(path + keyname2 + ".jpg"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("플레이어의 함선의 공격으로 발생한 폭발을 대신할 이미지 파일로 사용됩니다.");
		boom = new OvalBoom();
		boom.setMaker(-1);
		keyname = boom.getBoomName(true);
		keyname2 = boom.getBoomName(false);
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + keyname2 + ".png"));
		lists.add(RunManager.r65279(path + keyname2 + ".jpg"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("아이템을 획득할 때 발생하는 효과를 대신할 이미지 파일로 사용됩니다.");
		boom = new ItemUseBoom();
		keyname = boom.getBoomName(true);
		lists.add(RunManager.r65279(path + keyname + ".png"));
		lists.add(RunManager.r65279(path + keyname + ".jpg"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "boom_" + "default" + ".jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("배경 이미지로 사용됩니다.");		
		lists.add(RunManager.r65279(path + "background.png"));
		lists.add(RunManager.r65279(path + "background.jpg"));
		if(descriptions) lists.add("");
		if(descriptions) lists.add("배경에 지나가는 별을 대신하는 이미지로 사용됩니다.");
		lists.add(RunManager.r65279(path + "deco_" + "default" + ".png"));
		lists.add(RunManager.r65279(path + "deco_" + "default" + ".jpg"));
		
		List<String> ships;
		ships = SpaceShip.spaceShipKeyList();
		
		for(int i=0; i<ships.size(); i++)
		{
			for(int j=1; j<=3; j++)
			{
				if(descriptions) lists.add("");
				if(descriptions) lists.add("플레이어의 함선 " + ships.get(i) + " 의 " + String.valueOf(j) + " 번째 무기가 장착된 모습을 대체하는 이미지로 사용됩니다.");
				lists.add(RunManager.r65279(path + ships.get(i) + "_w" + String.valueOf(j) + ".png"));
				lists.add(RunManager.r65279(path + ships.get(i) + "_w" + String.valueOf(j) + ".jpg"));
				lists.add(RunManager.r65279(path + "default_space" + "_w" + String.valueOf(j) + ".png"));
				lists.add(RunManager.r65279(path + "default_space" + "_w" + String.valueOf(j) + ".jpg"));
			}
		}
		
		
		
		return lists;
	}
}
