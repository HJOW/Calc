package reflex;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

public abstract class RectObject extends GraphicObject
{
	private static final long serialVersionUID = 4039505623161233294L;
	private int w, h;
	public RectObject()
	{
		super();
		w = 0;
		h = 0;
	}
	public Area area()
	{
		return new Area(new Rectangle(getX(), getY(), getW(), getH()));
	}	
	@Override
	public void draw(Graphics g)
	{
		//System.out.println("DrawRect " + g.getColor() + ", 좌표 " + getX() + ", " + getY() + " 크기 " + w + ", " + h);
		g.fillRect(getX() - (int)(w/2.0), getY() - (int)(h/2.0), w, h);	
	}
	public int getW()
	{
		return w;
	}
	public void setW(int w)
	{
		this.w = w;
	}
	public int getH()
	{
		return h;
	}
	public void setH(int h)
	{
		this.h = h;
	}	
}
