package ai_algorithm;

import java.math.BigInteger;
import java.util.Vector;

import main_classes.Block;
import main_classes.StandardCard;

public class AI_Defined_Algorithm extends AI_Algorithm
{
	private static final long serialVersionUID = 7721152710484833870L;
	
	
	public AI_Defined_Algorithm()
	{
		super();
	}
	public int getType()
	{
		return DEFINED;
	}
	
	/**
	 * Define how to calculate the priority.
	 */
	@Override
	public BigInteger calc_bound(int target_card_index, StandardCard target_card) // Most importants
	{
		BigInteger calcs = new BigInteger(String.valueOf(0));
		
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			if(i == getTurn())
			{
				getTemp().getBlocks().get(i).calculate(false);
				//calcs += getTemp().getBlocks().get(i).getPoint();
				calcs = calcs.add(getTemp().getBlocks().get(i).getPoint());
			}
			else
			{
				getTemp().getBlocks().get(i).calculate(false);
				//calcs -= getTemp().getBlocks().get(i).getPoint();
				calcs = calcs.subtract(getTemp().getBlocks().get(i).getPoint());
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
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.MULTIPLY_ABS;
				}
				else
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.DIVIDE_ABS;
				}
			}
		}
		else
		{
			if(getTemp().getBlocks().size() <= 0)
			{
				if(winner == getTurn())
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.MULTIPLY_ABS;
				}
				else
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.DIVIDE_ABS;
				}
			}
		}
		return newOne;
	}
	public AI_Defined_Algorithm clone()
	{
		this.clean();
		AI_Defined_Algorithm newOne = new AI_Defined_Algorithm();
		Vector<Block> newBlockArr = new Vector<Block>();
		Vector<StandardCard> newDeck = new Vector<StandardCard>();
		for(int i=0; i<getBlocks().length; i++)
		{
			newBlockArr.add(getBlocks()[i]);
		}
		for(int i=0; i<getDeck().length; i++)
		{
			newDeck.add(getDeck()[i]);
		}
		newOne.init(newBlockArr, newDeck, getTurn(), getOp_plus(), getOp_minus(), getOp_multiply(), getOp_change(), getSeparator());
		return newOne;
	}
}
