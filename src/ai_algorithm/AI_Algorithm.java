package ai_algorithm;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import setting.SaveLong;
import main_classes.Block;
import main_classes.StandardCard;

public class AI_Algorithm implements Serializable
{
	private static final long serialVersionUID = -7893410465927946361L;
	private String name = "";
	private int turn;
	private Block[] blocks;
	private StandardCard[] deck;	
	private AI_Algorithm_Result result;	
	private AI_Temp temp = null;
	private char op_plus, op_minus, op_multiply, op_change;
	private String separator = "  ";	
	private SaveLong randoms = null;
	
	private Vector<AI_Algorithm_Number> work_variables;
	
	public static final int DEFAULT = 0;
	public static final int DEFINED = 1;
	public static final int ORDER_INCLUDED = 2;
	public static final int SCRIPT = 3;
	
	public AI_Algorithm()
	{
		long ranNum = (long) (Math.random() * Long.MAX_VALUE);
		randoms = new SaveLong(ranNum);
	}	
	public void init(int turn, char plus, char minus, char multiply, char change, String separator)
	{
		this.turn = turn;
		op_plus = plus;
		op_minus = minus;
		op_multiply = multiply;
		op_change = change;
		separator = new String(separator);
	}
	public void init(Vector<Block> blocks, Vector<StandardCard> deck, int turn, char plus, char minus, char multiply, char change, String separator)
	{
		this.blocks = new Block[blocks.size()];
		for(int i=0; i<this.blocks.length; i++)
		{
			this.blocks[i] = blocks.get(i);
		}
		this.deck = new StandardCard[deck.size()];
		for(int i=0; i<this.deck.length; i++)
		{
			this.deck[i] = deck.get(i);
		}
		work_variables = new Vector<AI_Algorithm_Number>();
		init(turn, plus, minus, multiply, change, separator);
	}
	public int getType()
	{
		return DEFAULT;
	}
	public void addToDeck(StandardCard card)
	{
		StandardCard[] temp = new StandardCard[deck.length + 1];
		for(int i=0; i<deck.length; i++)
		{
			temp[i] = deck[i];
		}
		temp[deck.length] = card;
		deck = temp;
	}
	public void removeCardFromDeck(int index)
	{
		StandardCard[] temp = new StandardCard[deck.length - 1];
		for(int i=0; i<deck.length; i++)
		{
			if(i < index) temp[i] = deck[i];
			else if(i > index) temp[i-1] = deck[i];
		}
		deck = temp;
	}
	public synchronized void setTurn(int turn)
	{
		this.turn = turn;
	}
	/*
	public void input(String order)
	{
		char[] chars = order.toCharArray();
		//String buffers = "";
		for(int i=0; i<chars.length; i++)
		{
			
		}
		
	}*/
	public BigInteger calc_bound(int target_card_index, StandardCard target_card) // Most importants
	{
		BigInteger calcs = new BigInteger("0");
		
		for(int i=0; i<temp.blocks.size(); i++)
		{
			if(i == turn)
			{
				temp.blocks.get(i).calculate(false);
				calcs = calcs.add(temp.blocks.get(i).getPoint());
				//calcs += temp.blocks.get(i).getPoint();
			}
			else
			{
				temp.blocks.get(i).calculate(false);
				//calcs -= temp.blocks.get(i).getPoint();
			}
		}
		
		return calcs;
	}
	public AI_Bonus calc_finish_check(boolean take)
	{
		AI_Bonus newOne = new AI_Bonus();
		BigInteger[] points = new BigInteger[temp.blocks.size()];
		temp.blocks.get(0).calculate(false);
		BigInteger max_point = temp.blocks.get(0).getPoint();
		int winner = 0;
		for(int i=0; i<points.length; i++)
		{
			temp.blocks.get(i).calculate(false);
			points[i] = temp.blocks.get(i).getPoint();
			//if(temp.blocks.get(i).getPoint() > max_point)
			if(temp.blocks.get(i).getPoint().compareTo(max_point) > 0)
			{
				max_point = temp.blocks.get(i).getPoint();
				winner = i;
			}
		}
		if(take)
		{
			if(temp.deck.size() <= 1)
			{
				if(winner == turn)
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
			if(temp.blocks.size() <= 0)
			{
				if(winner == turn)
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
	public synchronized AI_Algorithm_Result act()
	{
		result = new AI_Algorithm_Result();
		
		if(temp == null) temp = new AI_Temp();
		temp.input(blocks, deck);
		
		StandardCard card = null;
		BigInteger max_point = new BigInteger(String.valueOf(Long.MIN_VALUE));
		BigInteger point = new BigInteger(String.valueOf(0));
		result.setType(AI_Algorithm_Result.TAKE);		
		
		// take
		
		if(temp.deck.size() >= 1)
		{
			card = temp.deck.get(0);
			temp.blocks.get(turn).getOwns().add(card);
			temp.deck.remove(0);
			point = calc_bound(-1, null);
			
			AI_Bonus finish_check = calc_finish_check(true);
			switch(finish_check.op_type)
			{
				case 0: // +
					//point += finish_check.bonus;
					point = point.add(new BigInteger(String.valueOf(finish_check.bonus)));
					break;
				case 1: // *
					//point *= finish_check.bonus;
					point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
					break;		
				case 2: // * abs
					//if(point < 0) point /= finish_check.bonus;
					//else point *= finish_check.bonus;
					if(point.compareTo(new BigInteger(String.valueOf(0))) < 0) point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
					else point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
					break;
				case 3: // -
					//point -= finish_check.bonus;
					point = point.subtract(new BigInteger(String.valueOf(finish_check.bonus)));
					break;
				case 4: // divide
					//point /= finish_check.bonus;
					point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
					break;
				case 5: // divide abs
					//if(point < 0) point *= finish_check.bonus;
					//else point /= finish_check.bonus;
					if(point.compareTo(new BigInteger(String.valueOf(0))) < 0) point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
					else point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
					break;
			}
			
			//if(point > max_point)
			if(point.compareTo(max_point) > 0)
			{
				max_point = point;
				result.setType(AI_Algorithm_Result.TAKE);		
			}
		}
		
		temp.reset();
		
		// pay
		
		char op = op_plus;
		int num = 0;
		boolean target_paid_empty = true;
		boolean pay_enable = false;
		char last_op = op_plus;
		int last_num = 0;
		for(int i=0; i<blocks.length; i++)
		{
			temp.reset();
			if(blocks[i].getPaids().size() >= 1)
			{
				last_op = temp.blocks.get(i).getPaids().lastElement().getOp();
				last_num = temp.blocks.get(i).getPaids().lastElement().getNum();
				target_paid_empty = false;
			}
			else
			{
				target_paid_empty = true;
			}
			for(int j=0; j<blocks[turn].getOwns().size(); j++)
			{
				temp.reset();
				if(target_paid_empty)
				{
					pay_enable = true;
				}				
				else
				{
					//System.out.println("temp.blocks " + temp.blocks.size());
					//System.out.println("temp.blocks.get(turn).owns " + temp.blocks.get(turn).owns.size());
					card = temp.blocks.get(turn).getOwns().get(j);
					temp.blocks.get(turn).getOwns().remove(j);
					op = card.getOp();
					num = card.getNum();
					
					if(last_num == 7)
					{
						if(i == turn)
						{
							if(num == 1)
							{
								pay_enable = true;
							}
							else if(num == last_num || op == last_op)
							{
								pay_enable = true;
							}
							else pay_enable = false;
						}
						else
						{
							pay_enable = false;
						}
					}
					else if(num == 1)
					{
						pay_enable = true;
					}
					else if(num == last_num || op == last_op)
					{
						pay_enable = true;
					}
				}
				
				if(pay_enable)
				{
					temp.blocks.get(i).getPaids().add(card);
					point = calc_bound(j, card);
					
					AI_Bonus finish_check = calc_finish_check(false);
					switch(finish_check.op_type)
					{
						case 0: // +
							//point += finish_check.bonus;
							point = point.add(new BigInteger(String.valueOf(finish_check.bonus)));
							break;
						case 1: // *
							//point *= finish_check.bonus;
							point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
							break;		
						case 2: // * abs
							//if(point < 0) point /= finish_check.bonus;
							//else point *= finish_check.bonus;
							if(point.compareTo(new BigInteger("0")) < 0) point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
							else point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
							break;
						case 3: // -
							//point -= finish_check.bonus;
							point = point.subtract(new BigInteger(String.valueOf(finish_check.bonus)));
							break;
						case 4: // divide
							//point /= finish_check.bonus;
							point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
							break;
						case 5: // divide abs
							//if(point < 0) point *= finish_check.bonus;
							//else point /= finish_check.bonus;
							if(point.compareTo(new BigInteger("0")) < 0) point = point.multiply(new BigInteger(String.valueOf(finish_check.bonus)));
							else point = point.divide(new BigInteger(String.valueOf(finish_check.bonus)));
							break;
					}
					
					//if(point > max_point)
					if(point.compareTo(max_point) > 0)
					{
						max_point = point;
						result.setType(AI_Algorithm_Result.PAY);	
						result.setTarget_to_pay(i);
						result.setOwn_card_index(j);
					}
				}
				
				
			}
			
		}
		
		temp = null;		
		return result;
	}	
	public void clean()
	{		
		result = null;
		blocks = null;
		deck = null;		
	}
	public AI_Algorithm clone()
	{
		this.clean();
		AI_Algorithm newOne = new AI_Algorithm();
		Vector<Block> newBlockArr = new Vector<Block>();
		Vector<StandardCard> newDeck = new Vector<StandardCard>();
		if(blocks != null)
		{
			for(int i=0; i<blocks.length; i++)
			{
				newBlockArr.add(blocks[i]);
			}
		}
		if(deck != null)
		{
			for(int i=0; i<deck.length; i++)
			{
				newDeck.add(deck[i]);
			}
		}
		newOne.init(newBlockArr, newDeck, turn, op_plus, op_minus, op_multiply, op_change, separator);
		return newOne;
	}	
	public Block[] getBlocks()
	{
		return blocks;
	}
	public void setBlocks(Block[] blocks)
	{
		this.blocks = blocks;
	}
	public StandardCard[] getDeck()
	{
		return deck;
	}
	public void setDeck(StandardCard[] deck)
	{
		this.deck = deck;
	}
	public AI_Algorithm_Result getResult()
	{
		return result;
	}
	public void setResult(AI_Algorithm_Result result)
	{
		this.result = result;
	}
	public Vector<AI_Algorithm_Number> getWork_variables()
	{
		return work_variables;
	}
	public void setWork_variables(Vector<AI_Algorithm_Number> work_variables)
	{
		this.work_variables = work_variables;
	}
	public int getTurn()
	{
		return turn;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public AI_Temp getTemp()
	{
		return temp;
	}
	public void setTemp(AI_Temp temp)
	{
		this.temp = temp;
	}
	public char getOp_plus()
	{
		return op_plus;
	}
	public void setOp_plus(char op_plus)
	{
		this.op_plus = op_plus;
	}
	public char getOp_minus()
	{
		return op_minus;
	}
	public void setOp_minus(char op_minus)
	{
		this.op_minus = op_minus;
	}
	public char getOp_multiply()
	{
		return op_multiply;
	}
	public void setOp_multiply(char op_multiply)
	{
		this.op_multiply = op_multiply;
	}
	public char getOp_change()
	{
		return op_change;
	}
	public void setOp_change(char op_change)
	{
		this.op_change = op_change;
	}
	public String getSeparator()
	{
		return separator;
	}
	public void setSeparator(String separator)
	{
		this.separator = separator;
	}
	public SaveLong getRandoms()
	{
		return randoms;
	}
	public void setRandoms(SaveLong randoms)
	{
		this.randoms = randoms;
	}
}
