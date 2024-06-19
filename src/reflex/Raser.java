package reflex;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Raser extends Missile implements Boom, FormulaUsedMissile
{
	private static final long serialVersionUID = 5331932519839090663L;
	private Point2D p1, p2;
	private double progress = 0.0, max_progress = 1000.0;
	private String formula;
	private double range_unit = 1000.0;
	private double min_x = -Reflexioner.getSize_x() * 16, max_x = Reflexioner.getSize_x() * 16, min_y = -Reflexioner.getSize_y() * 16, max_y = Reflexioner.getSize_y() * 16;
	private boolean range_absolute = false;
	
	public Raser clone()
	{
		return clone(false);
	}
	public Raser clone(boolean imgnull)
	{		
		Raser newMissile = new Raser();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		newMissile.setImage(getNowImage());
		newMissile.setHp(getHp());
		newMissile.setP1(new Point((int)(p1.getX()), (int)(p1.getY())));
		newMissile.setP2(new Point((int)(p2.getX()), (int)(p2.getY())));
		newMissile.setProgress(getProgress());
		newMissile.setMax_progress(getMax_progress());
		newMissile.setFormula(new String(getFormula()));
		newMissile.setRange_unit(getRange_unit());
		newMissile.setMin_x(getMin_x());
		newMissile.setMin_x(getMin_y());
		newMissile.setMax_x(getMax_x());
		newMissile.setMax_x(getMax_y());
		newMissile.setRange_absolute(isRange_absolute());
		newMissile.setColor(getColor());
		if(imgnull) newMissile.setImage(null);
		else newMissile.setImage(getNowImage());
		return newMissile;
	}
	
	public Raser()
	{
		super();
		p1 = new Point(0, 0);
		p2 = new Point(0, 0);
		progress = 0.0;
		max_progress = 1000.0;
	}
	public Raser(String path, int owner, String formula)
	{
		this();
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);		
		setFormula(formula);
		//System.out.println("New Raser");
	}
	public void setMax_progressAsRange()
	{
		setMax_progress(getRange_unit());
	}
	@Override
	public void update()
	{
		super.update();
		decreaseHP();
		progress = progress + (getDy() * 0.1);
		
		FormulaResult r = FormulaResult.progress(formula, progress, range_unit, this, this);
		int p1_x, p1_y, p2_x, p2_y;
		
		if(range_unit >= 1)
		{
			p1_x = (int)Math.round( (r.getP1().getX() / range_unit) * Reflexioner.getSize_x() );
			p1_y = (int)Math.round( (r.getP1().getY() / range_unit) * Reflexioner.getSize_y());
			p2_x = (int)Math.round( (r.getP2().getX() / range_unit) * Reflexioner.getSize_x() );
			p2_y = (int)Math.round( (r.getP2().getY() / range_unit) * Reflexioner.getSize_y());
		}
		else
		{
			p1_x = (int)Math.round( (r.getP1().getX()));
			p1_y = (int)Math.round( (r.getP1().getY()));
			p2_x = (int)Math.round( (r.getP2().getX()));
			p2_y = (int)Math.round( (r.getP2().getY()));
		}
		
		//System.out.println("Update - None : (" + p1_x + ", " + p1_y + ") (" + p2_x + ", " + p2_y + ")" + progress);
		
		if(p1_x < min_x)
		{
			p1_x = (int) Math.round(min_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p1_x > max_x)
		{
			p1_x = (int) Math.round(max_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p1_y < min_y)
		{
			p1_y = (int) Math.round(min_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p1_y > max_y) 
		{
			p1_y = (int) Math.round(max_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p2_x < min_x)
		{
			p2_x = (int) Math.round(min_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p2_x > max_x) 
		{
			p2_x = (int) Math.round(max_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p2_y < min_y)
		{
			p2_y = (int) Math.round(min_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p2_y > max_y) 
		{
			p2_y = (int) Math.round(max_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		//System.out.println("Update - Zero : (" + p1_x + ", " + p1_y + ") (" + p2_x + ", " + p2_y + ")");
		//System.out.println(getHp());
		
		p1 = new Point(p1_x, p1_y);
		p2 = new Point(p2_x, p2_y);
		if(max_progress >= 0 && progress >= max_progress)
		{
			setLaunched(false);
		}
	}
	@Override
	public void draw(Graphics g)
	{
		int[] xs, ys;
		double thicks = 5.0;
		double x_d, y_d, distance;
		xs = new int[4];
		ys = new int[xs.length];
		xs[0] = (int) Math.round(p1.getX());
		ys[0] = (int) Math.round(p1.getY());
		
		xs[3] = (int) Math.round(p2.getX());
		ys[3] = (int) Math.round(p2.getY());
		
		x_d = (xs[3] - xs[0]);
		y_d = (ys[3] - ys[0]);
		distance = Math.sqrt(Math.pow(x_d, 2.0) + Math.pow(y_d,  2.0));
		x_d = x_d / distance;
		y_d = y_d / distance;
		x_d = x_d * thicks;
		y_d = y_d * thicks;
		
		xs[1] = (int) Math.round(p1.getX() - (x_d));
		ys[1] = (int) Math.round(p1.getY() + (y_d));
				
		xs[2] = (int) Math.round(p2.getX() - (x_d));
		ys[2] = (int) Math.round(p2.getY() + (y_d));
		
		if(Math.abs(xs[0] - xs[1]) < thicks / 10.0)
		{
			xs[1] = xs[0] + (int) Math.round(thicks);
			xs[2] = xs[0] + (int) Math.round(thicks);
		}
		if(Math.abs(ys[0] - ys[1]) < thicks / 10.0)
		{
			ys[1] = ys[0] + (int) Math.round(thicks);
			ys[2] = ys[0] + (int) Math.round(thicks);
		}
		
		g.fillPolygon(xs, ys, xs.length);
		
	}
	@Override
	public Area area()
	{		
		//return new Area(new Line2D.Double(p1, p2));
		int[] xs, ys;
		double thicks = 5.0;
		xs = new int[4];
		ys = new int[xs.length];
		double x_d, y_d, distance;
		xs = new int[4];
		ys = new int[xs.length];
		xs[0] = (int) Math.round(p1.getX());
		ys[0] = (int) Math.round(p1.getY());
		
		xs[3] = (int) Math.round(p2.getX());
		ys[3] = (int) Math.round(p2.getY());
		
		x_d = (xs[3] - xs[0]);
		y_d = (ys[3] - ys[0]);
		distance = Math.sqrt(Math.pow(x_d, 2.0) + Math.pow(y_d,  2.0));
		x_d = x_d / distance;
		y_d = y_d / distance;
		x_d = x_d * thicks;
		y_d = y_d * thicks;
		
		xs[1] = (int) Math.round(p1.getX() - (x_d));
		ys[1] = (int) Math.round(p1.getY() + (y_d));
				
		xs[2] = (int) Math.round(p2.getX() - (x_d));
		ys[2] = (int) Math.round(p2.getY() + (y_d));
		
		if(Math.abs(xs[0] - xs[1]) < thicks)
		{
			xs[1] = xs[0] + (int) Math.round(thicks);
			xs[2] = xs[0] + (int) Math.round(thicks);
		}
		if(Math.abs(ys[0] - ys[1]) < thicks)
		{
			ys[1] = ys[0] + (int) Math.round(thicks);
			ys[2] = ys[0] + (int) Math.round(thicks);
		}
		
		return new Area(new Polygon(xs, ys, xs.length));
	}
	public String getMissileName()
	{
		return "raser";
	}
	public String getBoomName()
	{
		return "raser";
	}
	@Override
	public String getBoomName(boolean makerInclude)
	{
		return "raser";
	}
	public Point2D getP1()
	{
		return p1;
	}
	public void setP1(Point2D p1)
	{
		this.p1 = p1;
	}
	public Point2D getP2()
	{
		return p2;
	}
	public void setP2(Point2D p2)
	{
		this.p2 = p2;
	}
	public double getProgress()
	{
		return progress;
	}
	public void setProgress(double progress)
	{
		this.progress = progress;
	}
	public String getFormula()
	{
		return formula;
	}
	public void setFormula(String formula)
	{
		this.formula = formula;
	}
	public double getMax_progress()
	{
		return max_progress;
	}
	public void setMax_progress(double max_progress)
	{
		this.max_progress = max_progress;
	}
	public double getRange_unit()
	{
		return range_unit;
	}
	public void setRange_unit(double range_unit)
	{
		this.range_unit = range_unit;
	}
	public double getMin_x()
	{
		return min_x;
	}
	public void setMin_x(double min_x)
	{
		this.min_x = min_x;
	}
	public double getMax_x()
	{
		return max_x;
	}
	public void setMax_x(double max_x)
	{
		this.max_x = max_x;
	}
	public double getMin_y()
	{
		return min_y;
	}
	public void setMin_y(double min_y)
	{
		this.min_y = min_y;
	}
	public double getMax_y()
	{
		return max_y;
	}
	public void setMax_y(double max_y)
	{
		this.max_y = max_y;
	}
	public boolean isRange_absolute()
	{
		return range_absolute;
	}
	public void setRange_absolute(boolean range_absolute)
	{
		this.range_absolute = range_absolute;
	}	
}
class FormulaMissile extends Missile implements FormulaUsedMissile
{
	private static final long serialVersionUID = 4702246615815813873L;
	private Point2D p1, p2;
	private int first_x = 0, first_y = 0;
	private double progress = 0.0, max_progress = 1000.0;
	private String formula;
	private double range_unit = 1000.0;
	private double min_x = -Reflexioner.getSize_x() * 16, max_x = Reflexioner.getSize_x() * 16, min_y = -Reflexioner.getSize_y() * 16, max_y = Reflexioner.getSize_y() * 16;
	private boolean range_absolute = false;
	public FormulaMissile()
	{
		super();
		p1 = new Point(0, 0);
		p2 = new Point(0, 0);
		progress = 0.0;
		max_progress = 1000.0;
	}
	public FormulaMissile(String path, int owner, String formula)
	{
		this();
		setOwner(owner);
		if(path != null && Reflexioner.image_allow)
			loadImage(path);		
		setFormula(formula);
		//System.out.println("New Raser");
	}
	public void setMax_progressAsRange()
	{
		setMax_progress(getRange_unit());
	}
	public FormulaMissile clone()
	{
		return clone(false);
	}
	public FormulaMissile clone(boolean imgnull)
	{		
		FormulaMissile newMissile = new FormulaMissile();
		newMissile.setX(getX());
		newMissile.setY(getY());
		newMissile.setDy(getDy());
		newMissile.setColor(getColor());
		newMissile.setW(getW());
		newMissile.setH(getH());
		newMissile.setLaunched(isLaunched());
		newMissile.setOwner(getOwner());
		newMissile.setDamage(getDamage());
		newMissile.setImage(getNowImage());
		newMissile.setHp(getHp());
		newMissile.setP1(new Point((int)(p1.getX()), (int)(p1.getY())));
		newMissile.setP2(new Point((int)(p2.getX()), (int)(p2.getY())));
		newMissile.setProgress(getProgress());
		newMissile.setMax_progress(getMax_progress());
		newMissile.setFormula(new String(getFormula()));
		newMissile.setRange_unit(getRange_unit());
		newMissile.setMin_x(getMin_x());
		newMissile.setMin_x(getMin_y());
		newMissile.setMax_x(getMax_x());
		newMissile.setMax_x(getMax_y());
		newMissile.setRange_absolute(isRange_absolute());
		newMissile.setColor(getColor());
		if(imgnull) newMissile.setImage(null);
		else newMissile.setImage(getNowImage());
		return newMissile;
	}
	@Override
	public void update()
	{
		super.update();
		decreaseHP();
		progress = progress + (getDy() * 0.1);
		
		FormulaResult r = FormulaResult.progress(formula, progress, range_unit, this, this);
		int p1_x, p1_y, p2_x, p2_y;
		
		if(range_unit >= 1)
		{
			p1_x = (int)Math.round( (r.getP1().getX() / range_unit) * Reflexioner.getSize_x() );
			p1_y = (int)Math.round( (r.getP1().getY() / range_unit) * Reflexioner.getSize_y());
			p2_x = (int)Math.round( (r.getP2().getX() / range_unit) * Reflexioner.getSize_x() );
			p2_y = (int)Math.round( (r.getP2().getY() / range_unit) * Reflexioner.getSize_y());
		}
		else
		{
			p1_x = (int)Math.round( (r.getP1().getX()));
			p1_y = (int)Math.round( (r.getP1().getY()));
			p2_x = (int)Math.round( (r.getP2().getX()));
			p2_y = (int)Math.round( (r.getP2().getY()));
		}
		
		//System.out.println("Update - None : (" + p1_x + ", " + p1_y + ") (" + p2_x + ", " + p2_y + ")" + progress);
		
		if(p1_x < min_x)
		{
			p1_x = (int) Math.round(min_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p1_x > max_x)
		{
			p1_x = (int) Math.round(max_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p1_y < min_y)
		{
			p1_y = (int) Math.round(min_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p1_y > max_y) 
		{
			p1_y = (int) Math.round(max_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p2_x < min_x)
		{
			p2_x = (int) Math.round(min_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p2_x > max_x) 
		{
			p2_x = (int) Math.round(max_x);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		if(p2_y < min_y)
		{
			p2_y = (int) Math.round(min_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		if(p2_y > max_y) 
		{
			p2_y = (int) Math.round(max_y);
			if(range_absolute && progress >= max_progress / 4 && progress >= 1.0) setLaunched(false);
		}
		
		//System.out.println("Update - Zero : (" + p1_x + ", " + p1_y + ") (" + p2_x + ", " + p2_y + ")");
		//System.out.println(getHp());
		
		p1 = new Point(p1_x, p1_y);
		p2 = new Point(p2_x, p2_y);
		
		setX(first_x + p1_x);
		setY(first_y + p1_y);
		if(max_progress >= 0 && progress >= max_progress)
		{
			setLaunched(false);
		}
	}
	public Point2D getP1()
	{
		return p1;
	}
	public void setP1(Point2D p1)
	{
		this.p1 = p1;
	}
	public Point2D getP2()
	{
		return p2;
	}
	public void setP2(Point2D p2)
	{
		this.p2 = p2;
	}
	public double getProgress()
	{
		return progress;
	}
	public void setProgress(double progress)
	{
		this.progress = progress;
	}
	public double getMax_progress()
	{
		return max_progress;
	}
	public void setMax_progress(double max_progress)
	{
		this.max_progress = max_progress;
	}
	public String getFormula()
	{
		return formula;
	}
	public void setFormula(String formula)
	{
		this.formula = formula;
	}
	public double getRange_unit()
	{
		return range_unit;
	}
	public void setRange_unit(double range_unit)
	{
		this.range_unit = range_unit;
	}
	public double getMin_x()
	{
		return min_x;
	}
	public void setMin_x(double min_x)
	{
		this.min_x = min_x;
	}
	public double getMax_x()
	{
		return max_x;
	}
	public void setMax_x(double max_x)
	{
		this.max_x = max_x;
	}
	public double getMin_y()
	{
		return min_y;
	}
	public void setMin_y(double min_y)
	{
		this.min_y = min_y;
	}
	public double getMax_y()
	{
		return max_y;
	}
	public void setMax_y(double max_y)
	{
		this.max_y = max_y;
	}
	public boolean isRange_absolute()
	{
		return range_absolute;
	}
	public void setRange_absolute(boolean range_absolute)
	{
		this.range_absolute = range_absolute;
	}
	public int getFirst_x()
	{
		return first_x;
	}
	public void setFirst_x(int first_x)
	{
		this.first_x = first_x;
	}
	public int getFirst_y()
	{
		return first_y;
	}
	public void setFirst_y(int first_y)
	{
		this.first_y = first_y;
	}
	
}
