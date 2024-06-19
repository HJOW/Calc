package reflex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;

public interface Boom
{
	public void update();
	public Area area();
	public boolean collapse(GraphicObject other);
	public long getHp();
	public void draw(Graphics g);
	public Color getColor();
	public void setX(int x);
	public void setY(int y);
	public int getX();
	public int getY();
	public String getBoomName(boolean makerInclude);
	public String getBoomName();
	public void loadImage();
	public void loadImage(String path);
	public Boom clone();
	public Boom clone(boolean imgnull);
}
