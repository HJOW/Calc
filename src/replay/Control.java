package replay;

import java.io.Serializable;

import main_classes.StandardCard;
import network.NetplayMessage;

public class Control extends NetplayMessage implements Serializable
{
	private static final long serialVersionUID = 7314838138376387419L;
	
	public static final byte TAKE = 0;
	public static final byte PAY = 1;
	
	private int turn = 0;
	private byte control_type = 0; // 0 : take, 1 : pay
	private int target = 0;
	private int card_index = 0;
	private StandardCard card;
	@Override
	public int getType()
	{
		return NetplayMessage.CONTROL;
	}
	@Override
	public NetplayMessage clone()
	{
		Control newOne = new Control();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		newOne.turn = this.turn;
		newOne.control_type = this.control_type;
		newOne.target = this.target;
		newOne.card_index = this.control_type;
		newOne.card = (StandardCard) this.card.clone();
		return newOne;
	}
	public int getTurn()
	{
		return turn;
	}
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	public byte getControl_type()
	{
		return control_type;
	}
	public void setControl_type(byte control_type)
	{
		this.control_type = control_type;
	}
	public int getTarget()
	{
		return target;
	}
	public void setTarget(int target)
	{
		this.target = target;
	}
	public int getCard_index()
	{
		return card_index;
	}
	public void setCard_index(int card_index)
	{
		this.card_index = card_index;
	}
	public StandardCard getCard()
	{
		return card;
	}
	public void setCard(StandardCard card)
	{
		this.card = card;
	}
}
