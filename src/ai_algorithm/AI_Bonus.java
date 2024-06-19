package ai_algorithm;

import java.io.Serializable;

public class AI_Bonus implements Serializable
{
	private static final long serialVersionUID = -1734395549050150006L;
	public long bonus = 1;
	public int op_type = 0; // 0 : +, 1 : *, 2 : *(abs
	
	public static final int PLUS = 0;
	public static final int MULTIPLY = 1;
	public static final int MULTIPLY_ABS = 2;
	public static final int MINUS = 3;
	public static final int DIVIDE = 4;
	public static final int DIVIDE_ABS = 5;
	public long getBonus()
	{
		return bonus;
	}
	public void setBonus(long bonus)
	{
		this.bonus = bonus;
	}
	public int getOp_type()
	{
		return op_type;
	}
	public void setOp_type(int op_type)
	{
		this.op_type = op_type;
	}
}
