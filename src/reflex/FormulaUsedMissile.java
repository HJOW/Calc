package reflex;

import java.awt.geom.Point2D;

public interface FormulaUsedMissile
{
	double getMax_x();
	double getMax_y();
	double getMin_x();
	double getMin_y();
	int getDy();
	
	public Point2D getP1();
	public void setP1(Point2D p1);
	public Point2D getP2();
	public void setP2(Point2D p2);
	public double getProgress();
	public void setProgress(double progress);
	public double getMax_progress();
	public void setMax_progress(double max_progress);
	public String getFormula();
	public void setFormula(String formula);
	public double getRange_unit();
	public void setRange_unit(double range_unit);
	
	public boolean isRange_absolute();
	public void setRange_absolute(boolean range_absolute);
}
