package network;

public class DisconnectMessage extends NetplayMessage
{
	private static final long serialVersionUID = -8680753708336344639L;

	@Override
	public int getType()
	{
		return NetplayMessage.DISCONNECT;
	}

	@Override
	public NetplayMessage clone()
	{
		DisconnectMessage newOne = new DisconnectMessage();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		return newOne;
	}
}
