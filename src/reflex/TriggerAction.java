package reflex;

import java.io.Serializable;

public class TriggerAction implements Serializable
{
	private static final long serialVersionUID = -8894155098147175488L;
	private int type = 0;
	private double value = 0;
	public static final int HEAL_ALL = 0;
	public static final int HEAL_PLAYER = 1;
	public static final int HEAL_ENEMIES = 2;
	public static final int DESTROY_ALL_ENEMY= 3;
	public static final int DESTROY_ALL_ENEMY_WITHOUT_BOSS = 4;
	
	public static final int COUNT_SET = 100;
	public static final int COUNT_REMOVE = 101;
	public static final int FINISH = 1000;
	public TriggerAction()
	{
		
	}
	public TriggerAction(int type, double value)
	{
		this.type = type;
		this.value = value;
	}
	public void action(Arena arena) throws Exception
	{
		switch(type)
		{
			case HEAL_ALL:
				arena.getSpaceShip().setHp(arena.getSpaceShip().getMax_hp());
				for(Enemy e : arena.getEnemies())
				{
					e.setHp(e.getMax_hp());
				}
				break;
			case HEAL_PLAYER:
				arena.getSpaceShip().setHp(arena.getSpaceShip().getMax_hp());
				break;
			case HEAL_ENEMIES:
				for(Enemy e : arena.getEnemies())
				{
					e.setHp(e.getMax_hp());
				}
				break;
			case DESTROY_ALL_ENEMY:
				for(Enemy e : arena.getEnemies())
				{
					e.setHp(0);
				}
				break;
			case DESTROY_ALL_ENEMY_WITHOUT_BOSS:
				for(Enemy e : arena.getEnemies())
				{
					if(! (e instanceof Boss))
						e.setHp(0);
				}
				break;
			case COUNT_SET:
				arena.setTimeout(Math.round(value));
				break;
			case COUNT_REMOVE:
				arena.setTimeout(-1);
				break;
			case FINISH:
				arena.game_finish();
				break;
		}
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
