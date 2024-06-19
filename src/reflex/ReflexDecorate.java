package reflex;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main_classes.RunManager;
import setting.Setting;

public class ReflexDecorate extends OvalObject
{
	private static final long serialVersionUID = 7309715590955478285L;
	private transient BufferedImage image = null;
	private int dy = 1;
	private String name = "";
	
	public ReflexDecorate()
	{
		super();
		setHp(1);
	}
	public ReflexDecorate(int x, int y, int dy, int r)
	{
		super();
		setX(x);
		setY(y);
		setDy(dy);
		setR(r);
		setHp(1);
	}
	public ReflexDecorate(String name, int x, int y, int dy, int r)
	{
		super();
		setName(name);
		setX(x);
		setY(y);
		setDy(dy);
		setR(r);
		setHp(1);
	}
	public ReflexDecorate(String name, int x, int y, int dy, int r, String path)
	{
		super();
		setName(name);
		setX(x);
		setY(y);
		setDy(dy);
		setR(r);
		setHp(1);
		loadImage(path);
	}
	@Override
	public void update()
	{
		setY(getY() + getDy());
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}
	public int getDy()
	{
		return dy;
	}
	public void setDy(int dy)
	{
		this.dy = dy;
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
	public void loadImage(String path)
	{
		if(ImageCache.img_decorate != null)
		{
			image = ImageCache.img_decorate;
			return;
		}
		try
		{
			File target = new File(RunManager.r65279(path + "deco_" + name + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + name + ".jpg"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + "default" + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "deco_" + "default" + ".jpg"));
			if(! target.exists()) return;
			image = ImageIO.read(target);
		} 
		catch (Exception e)
		{
			image = null;
		}
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public ReflexDecorate clone()
	{
		return clone(false);
	}
	public ReflexDecorate clone(boolean imgnull)
	{
		ReflexDecorate newDeco = new ReflexDecorate(new String(getName()), getX(), getY(), getDy(), getR());
		return newDeco;
	}
}
