package ai_algorithm;

import java.io.Serializable;

public abstract class Work_Block implements Serializable
{
	private static final long serialVersionUID = -1277863182608868012L;
	public int type;
	public Work_Block[] inside;
	public abstract AI_Algorithm_Data returns();
	
	public static final int DECLARE = 0;
	public static final int INPUT = 1;
	public static final int ADD = 2;
	public static final int SUBTRACT = 3;
	public static final int MULTIPLY = 4;
	public static final int DIVIDE = 5;
	public static final int IF = 6;
	public static final int END = 7;
	public static final int FINISH = 8;
}
