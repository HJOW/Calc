package reflex;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main_classes.CalcWindow;
import main_classes.RunManager;
import setting.Setting;

public class Item extends OvalObject
{
	private static final long serialVersionUID = 8428324469893761016L;
	private int dy = 5;
	private int dx = 0;
	private int type = 0;
	private transient String printFont = "";
	private transient BufferedImage image = null;
	public static final int R_HP_FULL = 0;
	public static final int C_E_FULL = 1;
	public static final int A_HP_ADD = 2;
	public static final int E_E_ADD = 3;
	public static final int M_M_ADD = 4;
	public static final int D_D_ADD = 5;
	public static final int S_S_ADD = 6;
	public static final int K_HP_H_ADD = 7;
	public static final int G_E_H_ADD = 8;
	public static final int X_PT_ADD = 9;
	
	public Item()
	{
		super();
	}
	public Item(int type)
	{
		super();
		this.type = type;
		setHp(1);
		setR(20);
		setColor(Reflexioner.color_item);
	}
	public Item(int type, String path)
	{
		this(type);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);
	}
	@Override
	public void update()
	{
		setX(getX() + dx);
		setY(getY() + dy);
	}
	public void update(int spaceShip_x, int spaceShip_y)
	{
		double m_dx = 0.0, m_dy = 0.0, dist = 0.0;
		m_dx = spaceShip_x - getX();
		m_dy = spaceShip_y - getY();
		dist = Math.sqrt(Math.pow(m_dx, 2.0) + Math.pow(m_dy, 2.0));
		m_dx = m_dx / dist;
		m_dy = m_dy / dist;
		setDx((int) Math.round(m_dx * 5.0));
		setDy((int) Math.round(m_dy * 5.0));
		update();
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		
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
		try
		{
			File target = new File(RunManager.r65279(path + "item_" + getItemName() + ".png"));
			if(! target.exists()) target = new File(RunManager.r65279(path + "item_" + getItemName() + ".jpg"));
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
			return new Area(new Rectangle(getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), (int)(getR()), (int)(getR())));
	}
	public String getItemName()
	{
		return String.valueOf(type);
	}
	public void draw(Graphics g)
	{
		if(image == null)
		{
			super.draw(g);
			g.setColor(Reflexioner.color_item_text);
			try
			{
				g.setFont(new Font(CalcWindow.usingFontName, Font.BOLD, 12));
			} 
			catch (Exception e)
			{
				
			}
			switch(type)
			{
				case R_HP_FULL:
					printFont = "R";
					break;
				case A_HP_ADD:
					printFont = "A";
					break;
				case K_HP_H_ADD:
					printFont = "K";
					break;
				case C_E_FULL:
					printFont = "C";
					break;
				case E_E_ADD:
					printFont = "E";
					break;
				case G_E_H_ADD:
					printFont = "G";
					break;
				case D_D_ADD:
					printFont = "D";
					break;
				case S_S_ADD:
					printFont = "S";
					break;
				case M_M_ADD:
					printFont = "M";
					break;
				case X_PT_ADD:
					printFont = "X";
					break;
				default:
					printFont = "D";
			}
			g.drawString(printFont, getX(), getY());
		}
		else
		{
			g.drawImage(image, getX() - (int)(getR() / 2.0), getY() - (int)(getR() / 2.0), getR(), getR(), null);
		}
	}
	public int getDy()
	{
		return dy;
	}
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getDx()
	{
		return dx;
	}
	public void setDx(int dx)
	{
		this.dx = dx;
	}	
	public Item clone()
	{
		return clone(false);
	}
	public Item clone(boolean imgnull)
	{
		Item newItem = new Item();
		
		newItem.setX(getX());
		newItem.setY(getY());
		newItem.setDx(getDx());
		newItem.setDy(getDy());
		newItem.setType(getType());
		newItem.setColor(getColor());
		newItem.setR(getR());
		newItem.setColor(getColor());
		if(imgnull) newItem.setImage(null);
		else newItem.setImage(image);
		return newItem;
	}
}
