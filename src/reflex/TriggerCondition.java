package reflex;

import java.io.Serializable;

public class TriggerCondition implements Serializable
{
	private static final long serialVersionUID = -7264974560466864149L;
	private int type = 0;
	private double value = 0.0;
	public static final int TIME_UP = 0;
	public static final int TIME_DOWN = 1;
	public static final int TIME_PLUSMINUS = 2;
	public static final int PLAYER_HP_UP = 3;
	public static final int PLAYER_HP_DOWN = 4;
	public static final int PLAYER_HP_PLUSMINUS = 5;
	
	public static final int COUNT_LEFT_UP = 100;
	public static final int COUNT_LEFT_DOWN = 101;
	public static final int COUNT_LEFT_PLUSMINUS = 102;
	
	public TriggerCondition()
	{
		
	}
	public TriggerCondition(int type)
	{
		this.type = type;
	}
	public TriggerCondition(int type, double value)
	{
		this.type = type;
		this.value = value;
	}
	public boolean isAccept(Arena arena)
	{
		switch(getType())
		{
			case TIME_UP:
				return arena.getDifficulty() >= value;
			case TIME_DOWN:
				return arena.getDifficulty() <= value;
			case TIME_PLUSMINUS:
				return (arena.getDifficulty() >= value - (arena.getDifficulty() / 10)
				&& arena.getDifficulty() <= value + (arena.getDifficulty() / 10));
			case PLAYER_HP_UP:
				return arena.getSpaceShip().getHp() >= value;
			case PLAYER_HP_DOWN:
				return arena.getSpaceShip().getHp() <= value;
			case PLAYER_HP_PLUSMINUS:
				return (arena.getSpaceShip().getHp() >= value - (arena.getSpaceShip().getHp() / 10.0)
				&& arena.getSpaceShip().getHp() <= value + (arena.getSpaceShip().getHp() / 10.0));
			case COUNT_LEFT_UP:
				return (arena.getTimeout() >= value);
			case COUNT_LEFT_DOWN:
				return (arena.getTimeout() <= value);
			case COUNT_LEFT_PLUSMINUS:
				return (arena.getTimeout() >= value - (arena.getTimeout() / 10.0)
				&& arena.getTimeout() <= value + (arena.getTimeout() / 10.0));
			
		}
		return false;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public double getValue()
	{
		return value;
	}
	public void setValue(double value)
	{
		this.value = value;
	}
}

