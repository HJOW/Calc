package network;

public class ReadyMessage extends NetplayMessage
{
	private static final long serialVersionUID = -1150732447607221051L;

	public boolean ready = false;
	
	@Override
	public int getType()
	{
		return NetplayMessage.READY;
	}
	@Override
	public NetplayMessage clone()
	{
		ReadyMessage newOne = new ReadyMessage();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		return newOne;
	}	
}
