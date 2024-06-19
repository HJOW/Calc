package network;

public class TextMessage extends NetplayMessage
{
	private static final long serialVersionUID = -2659130644964234242L;
	private String message = "";
	@Override
	public int getType()
	{
		return NetplayMessage.TEXT;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public NetplayMessage clone()
	{
		TextMessage newOne = new TextMessage();
		newOne.setSender_ip(new String(getSender_ip()));
		newOne.setReceiver_ip(new String(getReceiver_ip()));
		newOne.setVersion_main(getVersion_main());
		newOne.setVersion_sub1(getVersion_sub1());
		newOne.setVersion_sub2(getVersion_sub2());
		newOne.message = new String(message);
		return newOne;
	}

}
