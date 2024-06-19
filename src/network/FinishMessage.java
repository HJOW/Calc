package network;

public class FinishMessage extends NetplayMessage
{
	private static final long serialVersionUID = -4637767259421746313L;

	@Override
	public int getType()
	{
		return NetplayMessage.FINISH;
	}	

	@Override
	public NetplayMessage clone()
	{
		FinishMessage newOne = new FinishMessage();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		return newOne;
	}	
}
