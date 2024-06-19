package network;

public class StartMessage extends NetplayMessage
{
	private static final long serialVersionUID = 4986162213513288481L;
	public int yourTurn = 0;
	
	@Override
	public int getType()
	{
		return NetplayMessage.START;
	}
	@Override
	public NetplayMessage clone()
	{
		StartMessage newOne = new StartMessage();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		newOne.yourTurn = this.yourTurn;
		return newOne;
	}	
}
