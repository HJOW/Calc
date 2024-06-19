package network;

public class JoinListMessage extends NetplayMessage
{
	private static final long serialVersionUID = -2325912128843088740L;
	private String[] list;
	private boolean[] ready_list;
	
	@Override
	public int getType()
	{		
		return NetplayMessage.JOIN;
	}

	public String[] getList()
	{
		return list;
	}

	public void setList(String[] list)
	{
		this.list = list;
	}

	public boolean[] getReady_list()
	{
		return ready_list;
	}

	public void setReady_list(boolean[] ready_list)
	{
		this.ready_list = ready_list;
	}

	@Override
	public NetplayMessage clone()
	{
		JoinListMessage newOne = new JoinListMessage();
		newOne.setReceiver_ip(new String(this.getReceiver_ip()));
		newOne.setSender_ip(new String(this.getSender_ip()));
		newOne.setVersion_main(this.getVersion_main());
		newOne.setVersion_sub1(this.getVersion_sub1());
		newOne.setVersion_sub2(this.getVersion_sub2());
		if(this.list != null)
		{
			newOne.list = new String[this.list.length];
			for(int i=0; i<this.list.length; i++)
			{
				if(this.list[i] != null) newOne.list[i] = new String(this.list[i]);
			}
		}
		if(this.ready_list != null)
		{
			newOne.ready_list = new boolean[this.ready_list.length];
			for(int i=0; i<this.ready_list.length; i++)
			{
				newOne.ready_list[i] = this.ready_list[i];
			}
		}
		return newOne;
	}

}
