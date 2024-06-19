package reflex;

import java.util.List;

public interface ControllableShip
{
	public void control_up();
	public void control_down();
	public void control_left();
	public void control_right();
	public void control_break();
	public void control_w();
	public void control_a();
	public void control_s();
	public void control_d();
	public void control_1();
	public void control_2();
	public void control_3();
	public void control(int k);
	public void control_auto(List<Enemy> enemyList, List<Missile> missileList);
}
