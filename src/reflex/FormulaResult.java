package reflex;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.StringTokenizer;

public class FormulaResult implements Serializable
{
	private static final long serialVersionUID = 5850564477894900475L;
	private Point2D p1, p2;
	public FormulaResult()
	{
		p1 = new Point(0, 0);
		p2 = new Point(0, 0);
	}
	public FormulaResult(Point p1, Point p2)
	{
		p1 = new Point(p1);
		p2 = new Point(p2);
	}
	public FormulaResult(int x1, int y1, int x2, int y2)
	{
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
	}
	public FormulaResult(double x1, double y1, double x2, double y2)
	{
		p1 = new Point((int)Math.round(x1), (int)Math.round(y1));
		p2 = new Point((int)Math.round(x2), (int)Math.round(y2));
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
	public static FormulaResult progress(String formula, double xvalue, double range_unit, GraphicObject shooter, FormulaUsedMissile target)
	{
		FormulaResult results = new FormulaResult();
		StringTokenizer spaceToken = new StringTokenizer(formula, "\n");
		StringTokenizer operToken, coToken;
		double coefficient, powers, calcs;
		int operations;
		String xy, tp, op, co, po, tokens;
		
		while(spaceToken.hasMoreTokens())
		{
			operToken = new StringTokenizer(spaceToken.nextToken(), " ");
			try
			{
				calcs = 0.0;
				xy = operToken.nextToken();
				tp = operToken.nextToken();
				op = operToken.nextToken();
				co = operToken.nextToken();
				if(operToken.hasMoreTokens())
				{
					po = operToken.nextToken();
				}
				else po = null;
				
				if(op.equalsIgnoreCase("plus") || op.equalsIgnoreCase("+"))
				{
					operations = 0;
				}
				else if(op.equalsIgnoreCase("minus") || op.equalsIgnoreCase("-"))
				{
					operations = 1;
				}
				else if(op.equalsIgnoreCase("multiply") || op.equalsIgnoreCase("*"))
				{
					operations = 2;
				}
				else if(op.equalsIgnoreCase("divide") || op.equalsIgnoreCase("/"))
				{
					operations = 3;
				}
				else
				{
					operations = 0;
				}
								
				coToken = new StringTokenizer(co, "*");
				coefficient = 1.0;
				while(coToken.hasMoreTokens())
				{
					tokens = coToken.nextToken();
					try
					{
						coefficient = coefficient * Double.parseDouble(tokens);
					}
					catch(NumberFormatException e)
					{
						if(tokens.equalsIgnoreCase("size_x"))
						{
							coefficient = coefficient * Reflexioner.getSize_x();
						}
						else if(tokens.equalsIgnoreCase("size_y"))
						{
							coefficient = coefficient * Reflexioner.getSize_y();
						}
						else if(tokens.equalsIgnoreCase("range"))
						{
							coefficient = coefficient * range_unit;
						}
						else if(tokens.equalsIgnoreCase("location_x") || tokens.equalsIgnoreCase("loc_x"))
						{
							coefficient = coefficient * shooter.getX();
						}
						else if(tokens.equalsIgnoreCase("location_y") || tokens.equalsIgnoreCase("loc_y"))
						{
							coefficient = coefficient * shooter.getY();
						}
						else if(tokens.equalsIgnoreCase("u_x"))
						{
							coefficient = coefficient * (shooter.getX() / (double)Reflexioner.getSize_x()) * range_unit;
						}
						else if(tokens.equalsIgnoreCase("u_y"))
						{
							coefficient = coefficient * (shooter.getY() / (double)Reflexioner.getSize_y()) * range_unit;
						}
						else if(target != null && tokens.equalsIgnoreCase("max_x"))
						{
							coefficient = coefficient * target.getMax_x();
						}
						else if(target != null && tokens.equalsIgnoreCase("max_y"))
						{
							coefficient = coefficient * target.getMax_y();
						}
						else if(target != null && tokens.equalsIgnoreCase("min_x"))
						{
							coefficient = coefficient * target.getMin_x();
						}
						else if(target != null && tokens.equalsIgnoreCase("min_y"))
						{
							coefficient = coefficient * target.getMin_y();
						}
						else if(target != null && tokens.equalsIgnoreCase("speed"))
						{
							coefficient = coefficient * target.getDy();
						}
						else if(target != null && tokens.equalsIgnoreCase("speed_ratio"))
						{
							coefficient = coefficient * (1.0 + (target.getDy() / 100.0));
						}
						else if(tokens.equalsIgnoreCase("value"))
						{
							coefficient = coefficient * xvalue;
						}
						else
						{
							e.printStackTrace();
							coefficient = 1.0;
						}
					}
				}
				
				
				if(po != null)
				{					
					coToken = new StringTokenizer(po, "*");
					powers = 1.0;
					while(coToken.hasMoreTokens())
					{
						tokens = coToken.nextToken();
						try
						{
							powers = powers * Double.parseDouble(tokens);
						}
						catch(NumberFormatException e)
						{
							if(tokens.equalsIgnoreCase("size_x"))
							{
								powers = powers * Reflexioner.getSize_x();
							}
							else if(tokens.equalsIgnoreCase("size_y"))
							{
								powers = powers * Reflexioner.getSize_y();
							}
							else if(tokens.equalsIgnoreCase("range"))
							{
								powers = powers * range_unit;
							}
							else if(tokens.equalsIgnoreCase("location_x") || tokens.equalsIgnoreCase("loc_x"))
							{
								powers = powers * shooter.getX();
							}
							else if(tokens.equalsIgnoreCase("location_y") || tokens.equalsIgnoreCase("loc_y"))
							{
								powers = powers * shooter.getY();
							}
							else if(tokens.equalsIgnoreCase("u_x"))
							{
								powers = powers * (shooter.getX() / (double)Reflexioner.getSize_x()) * range_unit;
							}
							else if(tokens.equalsIgnoreCase("u_y"))
							{
								powers = powers * (shooter.getY() / (double)Reflexioner.getSize_y()) * range_unit;
							}
							else if(target != null && tokens.equalsIgnoreCase("max_x"))
							{
								powers = powers * target.getMax_x();
							}
							else if(target != null && tokens.equalsIgnoreCase("max_y"))
							{
								powers = powers * target.getMax_y();
							}
							else if(target != null && tokens.equalsIgnoreCase("min_x"))
							{
								powers = powers * target.getMin_x();
							}
							else if(target != null && tokens.equalsIgnoreCase("min_y"))
							{
								powers = powers * target.getMin_y();
							}
							else if(target != null && tokens.equalsIgnoreCase("speed"))
							{
								powers = powers * target.getDy();
							}
							else if(target != null && tokens.equalsIgnoreCase("speed_ratio"))
							{
								powers = powers * (1.0 + (target.getDy() / 100.0));
							}
							else if(tokens.equalsIgnoreCase("value"))
							{
								powers = powers * xvalue;
							}
							else
							{
								e.printStackTrace();
								powers = 1.0;
							}
						}
					}
				}
				else
				{
					powers = 1.0;
				}
				
				if(tp.equalsIgnoreCase("default"))
				{
					calcs = coefficient * Math.pow(xvalue, powers);					
				}
				else if(tp.equalsIgnoreCase("constant"))
				{
					calcs = coefficient;
				}
				else if(tp.equalsIgnoreCase("sin"))
				{
					calcs = coefficient * Math.pow(Math.sin(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("cos"))
				{
					calcs = coefficient * Math.pow(Math.cos(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("tan"))
				{
					calcs = coefficient * Math.pow(Math.tan(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("sinh"))
				{
					calcs = coefficient * Math.pow(Math.sinh(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("cosh"))
				{
					calcs = coefficient * Math.pow(Math.cosh(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("tanh"))
				{
					calcs = coefficient * Math.pow(Math.tanh(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("asin"))
				{
					calcs = coefficient * Math.pow(Math.asin(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("acos"))
				{
					calcs = coefficient * Math.pow(Math.acos(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("atan"))
				{
					calcs = coefficient * Math.pow(Math.atan(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("pow"))
				{
					calcs = coefficient * Math.pow(powers, xvalue);
				}
				else if(tp.equalsIgnoreCase("log"))
				{
					calcs = coefficient * Math.pow(Math.log(xvalue), powers);
				}
				else if(tp.equalsIgnoreCase("log10"))
				{
					calcs = coefficient * Math.pow(Math.log10(xvalue), powers);
				}
				else continue;
				
				
				switch(operations)
				{
					case 0:
						if(xy.equalsIgnoreCase("x1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX() + calcs), (int)Math.round(results.getP1().getY())));
						}
						else if(xy.equalsIgnoreCase("y1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX()), (int)Math.round(results.getP1().getY() + calcs)));
						}
						else if(xy.equalsIgnoreCase("x2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX() + calcs), (int)Math.round(results.getP2().getY())));
						}
						else if(xy.equalsIgnoreCase("y2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX()), (int)Math.round(results.getP2().getY() + calcs)));
						}
						else continue;	
						break;
					case 1:
						if(xy.equalsIgnoreCase("x1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX() - calcs), (int)Math.round(results.getP1().getY())));
						}
						else if(xy.equalsIgnoreCase("y1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX()), (int)Math.round(results.getP1().getY() - calcs)));
						}
						else if(xy.equalsIgnoreCase("x2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX() - calcs), (int)Math.round(results.getP2().getY())));
						}
						else if(xy.equalsIgnoreCase("y2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX()), (int)Math.round(results.getP2().getY() - calcs)));
						}
						else continue;	
						break;
					case 2:
						if(xy.equalsIgnoreCase("x1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX() * calcs), (int)Math.round(results.getP1().getY())));
						}
						else if(xy.equalsIgnoreCase("y1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX()), (int)Math.round(results.getP1().getY() * calcs)));
						}
						else if(xy.equalsIgnoreCase("x2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX() * calcs), (int)Math.round(results.getP2().getY())));
						}
						else if(xy.equalsIgnoreCase("y2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX()), (int)Math.round(results.getP2().getY() * calcs)));
						}
						else continue;	
						break;
					case 3:
						if(xy.equalsIgnoreCase("x1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX() / calcs), (int)Math.round(results.getP1().getY())));
						}
						else if(xy.equalsIgnoreCase("y1"))
						{
							results.setP1(new Point((int)Math.round(results.getP1().getX()), (int)Math.round(results.getP1().getY() / calcs)));
						}
						else if(xy.equalsIgnoreCase("x2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX() / calcs), (int)Math.round(results.getP2().getY())));
						}
						else if(xy.equalsIgnoreCase("y2"))
						{
							results.setP2(new Point((int)Math.round(results.getP2().getX()), (int)Math.round(results.getP2().getY() / calcs)));
						}
						else continue;	
						break;
					default:
						continue;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return results;
	}
}
