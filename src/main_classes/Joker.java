package main_classes;

import setting.Setting;

public class Joker extends Card
{
	private static final long serialVersionUID = -792526497525074634L;
	public boolean colored = false;

	public Joker()
	{
		
	}
	public Joker(boolean colored)
	{
		this.colored = colored;
	}
	@Override
	public String toBasicString(String separator)
	{
		if(colored) return "COLOR JOKER";
		else return "GRAY JOKER";
	}

	@Override
	public int getType()
	{
		return Card.JOKER;
	}
	public boolean isColored()
	{
		return colored;
	}
	public void setColored(boolean colored)
	{
		this.colored = colored;
	}
	@Override
	public Card clone()
	{
		Joker newOne = new Joker(colored);
		return newOne;
	}
	@Override
	public int grade(Setting set)
	{
		if(colored) return 10;
		else return 9;
	}
}
