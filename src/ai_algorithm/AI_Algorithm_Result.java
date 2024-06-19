package ai_algorithm;

import java.io.Serializable;

public class AI_Algorithm_Result implements Serializable
{
	private static final long serialVersionUID = -4264302045134011134L;
	private int type = 0;
	private int own_card_index = 0;
	private int target_to_pay = 0;
	
	public static final int TAKE = 0;
	public static final int PAY = 1;
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public int getOwn_card_index()
	{
		return own_card_index;
	}
	public void setOwn_card_index(int own_card_index)
	{
		this.own_card_index = own_card_index;
	}
	public int getTarget_to_pay()
	{
		return target_to_pay;
	}
	public void setTarget_to_pay(int target_to_pay)
	{
		this.target_to_pay = target_to_pay;
	}
}
