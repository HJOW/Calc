package reflex;

import java.util.List;

public class AReflexSave extends ReflexSave
{
	private static final long serialVersionUID = 3836185875492189922L;
	private Boolean authority_game;
	private Long pause_left;
	public AReflexSave()
	{
		super();
	}
	public AReflexSave(SpaceShip spaceShip, List<Enemy> enemies, List<Missile> missiles, List<Boom> booms, List<Item> items, List<ReflexDecorate> decorates, long difficulty, double difmode, String ver, int continue_left, ReflexScenario scen, boolean autoControl, List<Enemy> ourEnemy, boolean authority_mode, long pause_left)
	{
		super(spaceShip, enemies, missiles, booms, items, decorates, difficulty, difmode, ver, continue_left, scen, autoControl, ourEnemy);
		setAuthority_game(new Boolean(authority_mode));
		setPause_left(new Long(pause_left));
		auth();
	}
	public Boolean getAuthority_game()
	{
		return authority_game;
	}
	public void setAuthority_game(Boolean authority_game)
	{
		this.authority_game = authority_game;
	}
	public Long getPause_left()
	{
		return pause_left;
	}
	public void setPause_left(Long pause_left)
	{
		this.pause_left = pause_left;
	}
}
