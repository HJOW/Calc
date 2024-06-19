package ai_algorithm;

import java.math.BigInteger;
import java.util.Vector;

import main_classes.Block;
import main_classes.StandardCard;

public class AI_Order_Included_Algorithm extends AI_Defined_Algorithm
{
	private static final long serialVersionUID = 4613993031258167463L;
	
	private boolean calc_bound_myTurn_op = true; // true : +, false : *
	private double calc_bound_myTurn_adds = 1.0;
	private boolean calc_bound_other_op = true; // true : -, false : /
	private double calc_bound_other_adds = 1.0;
	private long finish_add = 2;
	private int finish_op_take_winning = AI_Bonus.MULTIPLY_ABS;
	private int finish_op_take_losing = AI_Bonus.DIVIDE_ABS;
	private int finish_op_pay_winning = AI_Bonus.MULTIPLY_ABS;
	private int finish_op_pay_losing = AI_Bonus.DIVIDE_ABS;	
	
	public AI_Order_Included_Algorithm()
	{
		super();
	}
	public void readOrder(String[] read_orders) throws NumberFormatException
	{
		String[] orders = read_orders;
		String[] reads;
		for(int i=0; i<orders.length; i++)
		{
			reads = orders[i].split(":");
			for(int j=0; j<reads.length; j++)
			{
				reads[j] = reads[j].trim();
			}
			if(reads.length >= 2)
			{
				if(reads[0].equalsIgnoreCase("Operation that acts when the current player\'s turn") || reads[0].equalsIgnoreCase("calc op my"))
				{
					if(reads[1].equals("+")) calc_bound_myTurn_op = true;
					else if(reads[1].equals("×")) calc_bound_myTurn_op = false;
					else if(reads[1].equalsIgnoreCase("plus") || reads[1].equalsIgnoreCase("add")) calc_bound_myTurn_op = true;
					else if(reads[1].equalsIgnoreCase("multiply")) calc_bound_myTurn_op = false;
				}
				else if(reads[0].equalsIgnoreCase("Value that be multiplied when the current player\'s turn") || reads[0].equalsIgnoreCase("calc value my"))
				{
					calc_bound_myTurn_adds = Double.parseDouble(reads[1]);
				}
				else if(reads[0].equalsIgnoreCase("Operation that acts when the other player\'s turn") || reads[0].equalsIgnoreCase("calc op other"))
				{
					if(reads[1].equals("-")) calc_bound_other_op = true;
					else if(reads[1].equals("/"))  calc_bound_other_op = false;
					else if(reads[1].equalsIgnoreCase("plus") || reads[1].equalsIgnoreCase("add")) calc_bound_other_op = true;
					else if(reads[1].equalsIgnoreCase("multiply")) calc_bound_other_op = false;
				}
				else if(reads[0].equalsIgnoreCase("Value that be multiplied when the other player\'s turn") || reads[0].equalsIgnoreCase("calc value other"))
				{
					calc_bound_other_adds = Double.parseDouble(reads[1]);
				}
				else if(reads[0].equalsIgnoreCase("Value that be calculated when the game is almost end") || reads[0].equalsIgnoreCase("finish value"))
				{
					finish_add = Long.parseLong(reads[1]);
				}
				else if(reads[0].equalsIgnoreCase("Operation that acts when the game is almost end with current player winning and decided to take") || reads[0].equalsIgnoreCase("finish op take winning"))
				{
					if(reads[1].equals("+") || reads[1].equalsIgnoreCase("plus")) finish_op_take_winning = AI_Bonus.PLUS;
					else if(reads[1].equals("-") || reads[1].equalsIgnoreCase("minus")) finish_op_take_winning = AI_Bonus.MINUS;
					else if(reads[1].equals("*") || reads[1].equalsIgnoreCase("multiply")) finish_op_take_winning = AI_Bonus.MULTIPLY;
					else if(reads[1].equals("|*|") || reads[1].equalsIgnoreCase("multiply absolutely")) finish_op_take_winning = AI_Bonus.MULTIPLY_ABS;
					else if(reads[1].equals("/") || reads[1].equalsIgnoreCase("divide")) finish_op_take_winning = AI_Bonus.DIVIDE;
					else if(reads[1].equals("|/|") || reads[1].equalsIgnoreCase("divide absolutely")) finish_op_take_winning = AI_Bonus.DIVIDE_ABS;
				}
				else if(reads[0].equalsIgnoreCase("Operation that acts when the game is almost end with current player losing and decided to take") || reads[0].equalsIgnoreCase("finish op take losing"))
				{
					if(reads[1].equals("+") || reads[1].equalsIgnoreCase("plus")) finish_op_take_losing = AI_Bonus.PLUS;
					else if(reads[1].equals("-") || reads[1].equalsIgnoreCase("minus")) finish_op_take_losing = AI_Bonus.MINUS;
					else if(reads[1].equals("*") || reads[1].equalsIgnoreCase("multiply")) finish_op_take_losing = AI_Bonus.MULTIPLY;
					else if(reads[1].equals("|*|") || reads[1].equalsIgnoreCase("multiply absolutely")) finish_op_take_losing = AI_Bonus.MULTIPLY_ABS;
					else if(reads[1].equals("/") || reads[1].equalsIgnoreCase("divide")) finish_op_take_losing = AI_Bonus.DIVIDE;
					else if(reads[1].equals("|/|") || reads[1].equalsIgnoreCase("divide absolutely")) finish_op_take_losing = AI_Bonus.DIVIDE_ABS;
				}
				else if(reads[0].equalsIgnoreCase("Operation that acts when the game is almost end with current player losing and decided to pay") || reads[0].equalsIgnoreCase("finish op pay losing"))
				{
					if(reads[1].equals("+") || reads[1].equalsIgnoreCase("plus")) finish_op_pay_losing = AI_Bonus.PLUS;
					else if(reads[1].equals("-") || reads[1].equalsIgnoreCase("minus")) finish_op_pay_losing = AI_Bonus.MINUS;
					else if(reads[1].equals("*") || reads[1].equalsIgnoreCase("multiply")) finish_op_pay_losing = AI_Bonus.MULTIPLY;
					else if(reads[1].equals("|*|") || reads[1].equalsIgnoreCase("multiply absolutely")) finish_op_pay_losing = AI_Bonus.MULTIPLY_ABS;
					else if(reads[1].equals("/") || reads[1].equalsIgnoreCase("divide")) finish_op_pay_losing = AI_Bonus.DIVIDE;
					else if(reads[1].equals("|/|") || reads[1].equalsIgnoreCase("divide absolutely")) finish_op_pay_losing = AI_Bonus.DIVIDE_ABS;
				}
				else if(reads[0].equalsIgnoreCase("Operation that acts when the game is almost end with current player winning and decided to pay") || reads[0].equalsIgnoreCase("finish op pay winning"))
				{
					if(reads[1].equals("+") || reads[1].equalsIgnoreCase("plus")) finish_op_pay_winning = AI_Bonus.PLUS;
					else if(reads[1].equals("-") || reads[1].equalsIgnoreCase("minus")) finish_op_pay_winning = AI_Bonus.MINUS;
					else if(reads[1].equals("*") || reads[1].equalsIgnoreCase("multiply")) finish_op_pay_winning = AI_Bonus.MULTIPLY;
					else if(reads[1].equals("|*|") || reads[1].equalsIgnoreCase("multiply absolutely")) finish_op_pay_winning = AI_Bonus.MULTIPLY_ABS;
					else if(reads[1].equals("/") || reads[1].equalsIgnoreCase("divide")) finish_op_pay_winning = AI_Bonus.DIVIDE;
					else if(reads[1].equals("|/|") || reads[1].equalsIgnoreCase("divide absolutely")) finish_op_pay_winning = AI_Bonus.DIVIDE_ABS;
				}
			}
		}
	}
	public int getType()
	{
		return ORDER_INCLUDED;
	}
	@Override
	public BigInteger calc_bound(int target_card_index, StandardCard target_card) // Most importants
	{
		BigInteger calcs = new BigInteger("0");
		
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			if(i == getTurn())
			{
				getTemp().getBlocks().get(i).calculate(false);
				if(calc_bound_myTurn_op)
				{
					//if(calc_bound_myTurn_adds == 1.0) calcs += getTemp().getBlocks().get(i).getPoint();
					//else calcs += (long) Math.round(getTemp().getBlocks().get(i).getPoint() * calc_bound_myTurn_adds);
					if(calc_bound_myTurn_adds == 1.0) calcs = calcs.add(getTemp().getBlocks().get(i).getPoint());
					else calcs = calcs.add(getTemp().getBlocks().get(i).getPoint().multiply(new BigInteger(String.valueOf(calc_bound_myTurn_adds))));
				}
				else
				{
					//if(calc_bound_myTurn_adds == 1.0) calcs *= getTemp().getBlocks().get(i).getPoint();
					//else calcs *= (long) Math.round(getTemp().getBlocks().get(i).getPoint() * calc_bound_myTurn_adds);
					if(calc_bound_myTurn_adds == 1.0) calcs = calcs.multiply(getTemp().getBlocks().get(i).getPoint());
					else calcs = calcs.multiply(getTemp().getBlocks().get(i).getPoint().multiply(new BigInteger(String.valueOf(calc_bound_myTurn_adds))));
				}
			}
			else
			{
				getTemp().getBlocks().get(i).calculate(false);
				if(calc_bound_other_op)
				{
					//if(calc_bound_other_adds == 1.0) calcs -= getTemp().getBlocks().get(i).getPoint();
					//else calcs -= (long) Math.round(getTemp().getBlocks().get(i).getPoint() * calc_bound_other_adds);
					if(calc_bound_myTurn_adds == 1.0) calcs = calcs.subtract(getTemp().getBlocks().get(i).getPoint());
					else calcs = calcs.subtract(getTemp().getBlocks().get(i).getPoint().multiply(new BigInteger(String.valueOf(calc_bound_myTurn_adds))));
				}
				else
				{
					//if(calc_bound_other_adds == 1.0) calcs /= getTemp().getBlocks().get(i).getPoint();
					//else calcs /= (long) Math.round(getTemp().getBlocks().get(i).getPoint() * calc_bound_other_adds);
					if(calc_bound_myTurn_adds == 1.0) calcs = calcs.multiply(getTemp().getBlocks().get(i).getPoint());
					else calcs = calcs.divide(getTemp().getBlocks().get(i).getPoint().multiply(new BigInteger(String.valueOf(calc_bound_myTurn_adds))));
				}				
			}
		}
		
		return calcs;
	}
	/**
	 * Define how to modify the priority value when the game is almost finished.
	 */
	public AI_Bonus calc_finish_check(boolean take)
	{
		AI_Bonus newOne = new AI_Bonus();
		BigInteger[] points = new BigInteger[getTemp().getBlocks().size()];
		getTemp().getBlocks().get(0).calculate(false);
		BigInteger max_point = getTemp().getBlocks().get(0).getPoint();
		int winner = 0;
		for(int i=0; i<points.length; i++)
		{
			getTemp().getBlocks().get(i).calculate(false);
			points[i] = getTemp().getBlocks().get(i).getPoint();
			//if(getTemp().getBlocks().get(i).getPoint() > max_point)
			if(getTemp().getBlocks().get(i).getPoint().compareTo(max_point) > 0)
			{
				max_point = getTemp().getBlocks().get(i).getPoint();
				winner = i;
			}
		}
		if(take)
		{
			if(getTemp().getDeck().size() <= 1)
			{
				if(winner == getTurn())
				{
					newOne.bonus = finish_add;
					newOne.op_type = finish_op_take_winning;
				}
				else
				{
					newOne.bonus = finish_add;
					newOne.op_type = finish_op_take_losing;
				}
			}
		}
		else
		{
			if(getTemp().getBlocks().size() <= 0)
			{
				if(winner == getTurn())
				{
					newOne.bonus = finish_add;
					newOne.op_type = finish_op_pay_winning;
				}
				else
				{
					newOne.bonus = finish_add;
					newOne.op_type = finish_op_pay_losing;
				}
			}
		}
		return newOne;
	}	
	public AI_Order_Included_Algorithm clone()
	{
		this.clean();
		AI_Order_Included_Algorithm newOne = new AI_Order_Included_Algorithm();
		Vector<Block> newBlockArr = new Vector<Block>();
		Vector<StandardCard> newDeck = new Vector<StandardCard>();
		if(getBlocks() != null)
		for(int i=0; i<getBlocks().length; i++)
		{
			newBlockArr.add(getBlocks()[i]);
		}
		if(getDeck() != null)
		for(int i=0; i<getDeck().length; i++)
		{
			newDeck.add(getDeck()[i]);
		}
		newOne.init(newBlockArr, newDeck, getTurn(), getOp_plus(), getOp_minus(), getOp_multiply(), getOp_change(), getSeparator());
		
		return newOne;
	}
	public boolean isCalc_bound_myTurn_op()
	{
		return calc_bound_myTurn_op;
	}
	public void setCalc_bound_myTurn_op(boolean calc_bound_myTurn_op)
	{
		this.calc_bound_myTurn_op = calc_bound_myTurn_op;
	}
	public double getCalc_bound_myTurn_adds()
	{
		return calc_bound_myTurn_adds;
	}
	public void setCalc_bound_myTurn_adds(double calc_bound_myTurn_adds)
	{
		this.calc_bound_myTurn_adds = calc_bound_myTurn_adds;
	}
	public boolean isCalc_bound_other_op()
	{
		return calc_bound_other_op;
	}
	public void setCalc_bound_other_op(boolean calc_bound_other_op)
	{
		this.calc_bound_other_op = calc_bound_other_op;
	}
	public double getCalc_bound_other_adds()
	{
		return calc_bound_other_adds;
	}
	public void setCalc_bound_other_adds(double calc_bound_other_adds)
	{
		this.calc_bound_other_adds = calc_bound_other_adds;
	}
	public long getFinish_add()
	{
		return finish_add;
	}
	public void setFinish_add(long finish_add)
	{
		this.finish_add = finish_add;
	}
	public int getFinish_op_take_winning()
	{
		return finish_op_take_winning;
	}
	public void setFinish_op_take_winning(int finish_op_take_winning)
	{
		this.finish_op_take_winning = finish_op_take_winning;
	}
	public int getFinish_op_take_losing()
	{
		return finish_op_take_losing;
	}
	public void setFinish_op_take_losing(int finish_op_take_losing)
	{
		this.finish_op_take_losing = finish_op_take_losing;
	}
	public int getFinish_op_pay_winning()
	{
		return finish_op_pay_winning;
	}
	public void setFinish_op_pay_winning(int finish_op_pay_winning)
	{
		this.finish_op_pay_winning = finish_op_pay_winning;
	}
	public int getFinish_op_pay_losing()
	{
		return finish_op_pay_losing;
	}
	public void setFinish_op_pay_losing(int finish_op_pay_losing)
	{
		this.finish_op_pay_losing = finish_op_pay_losing;
	}
}
