package reflex;

import java.awt.Graphics;

public class StringDecorate extends ReflexDecorate
{
	private static final long serialVersionUID = -5836400727609586307L;
	private String contents = "";
	public StringDecorate()
	{
		super();
		setHp(50);
	}
	public StringDecorate(int x, int y, int dy, int r)
	{
		super(x, y, dy, r);
		setHp(50);
	}
	public StringDecorate(String name, int x, int y, int dy, int r)
	{
		super(name, x, y, dy, r);
		setHp(50);
	}
	public String getContents()
	{
		return contents;
	}
	public void setContents(String contents)
	{
		this.contents = contents;
	}
	public ReflexDecorate clone()
	{
		return clone(false);
	}
	public ReflexDecorate clone(boolean imgnull)
	{
		StringDecorate newDeco = new StringDecorate(new String(getName()), getX(), getY(), getDy(), getR());
		newDeco.setContents(getContents());
		return newDeco;
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawString(contents, getX(), getY());
	}
	@Override
	public void update()
	{
		setHp(getHp() - 1);
		setY(getY() + getDy());
	}
}
