package scripting;

import java.awt.geom.Area;
import java.util.List;

import lang.Language;
import main_classes.MessageShowable;
import reflex.Arena;
import reflex.ControllableShip;
import reflex.Enemy;
import reflex.ImageCache;
import reflex.Missile;
import reflex.ReflexDecorate;
import reflex.ReflexScenario;
import reflex.Reflexioner;
import reflex.StringDecorate;
import setting.Setting;

public class Script_Reflex implements Helpable, ControllableShip
{
	private Arena arena;
	private MessageShowable showable;
	private Setting sets;
	
	public static int A = Reflexioner.KEY_A;
	public static int W = Reflexioner.KEY_W;
	public static int S = Reflexioner.KEY_S;
	public static int D = Reflexioner.KEY_D;
	public static int UP = Reflexioner.KEY_UP;
	public static int DOWN = Reflexioner.KEY_DOWN;
	public static int LEFT = Reflexioner.KEY_LEFT;
	public static int RIGHT = Reflexioner.KEY_RIGHT;
	public static int SHIFT = Reflexioner.KEY_SHIFT;
	public static int NO1 = Reflexioner.KEY_1;
	public static int NO2 = Reflexioner.KEY_2;
	public static int NO3 = Reflexioner.KEY_3;
	public static int NO4 = Reflexioner.KEY_4;
	public static int NO5 = Reflexioner.KEY_5;
	public static int NO6 = Reflexioner.KEY_6;
	public static int NO7 = Reflexioner.KEY_7;
	public static int NO8 = Reflexioner.KEY_8;
	public static int NO9 = Reflexioner.KEY_9;
	public static int NO0 = Reflexioner.KEY_0;
		
	public Script_Reflex()
	{
		init_key();
	}
	public Script_Reflex(Arena arena, MessageShowable showable, Setting sets)
	{
		this.arena = arena;
		this.showable = showable;
		this.sets = sets.clone();
		init_key();
	}
	public void init_key()
	{
		A = Reflexioner.KEY_A;
		W = Reflexioner.KEY_W;
		S = Reflexioner.KEY_S;
		D = Reflexioner.KEY_D;
		UP = Reflexioner.KEY_UP;
		DOWN = Reflexioner.KEY_DOWN;
		LEFT = Reflexioner.KEY_LEFT;
		RIGHT = Reflexioner.KEY_RIGHT;
		SHIFT = Reflexioner.KEY_SHIFT;
		NO1 = Reflexioner.KEY_1;
		NO2 = Reflexioner.KEY_2;
		NO3 = Reflexioner.KEY_3;
		NO4 = Reflexioner.KEY_4;
		NO5 = Reflexioner.KEY_5;
		NO6 = Reflexioner.KEY_6;
		NO7 = Reflexioner.KEY_7;
		NO8 = Reflexioner.KEY_8;
		NO9 = Reflexioner.KEY_9;
		NO0 = Reflexioner.KEY_0;
	}
	public void needfiles()
	{
		List<String> files = ImageCache.needFiles(Setting.getNewInstance(), true);
		for(String s : files)
		{
			showable.message(s);
		}
	}
	public void save_properties() throws Exception
	{
		save_properties(false);
	}
	public void save_properties(boolean details) throws Exception
	{
		arena.try_save_properties(details);
	}
	public void reapply_properties() throws Exception
	{
		arena.try_apply_properties();
	}
	public Language strings()
	{
		return sets.getLang();
	}
	public String property(String key) throws Exception
	{
		return sets.getProperties().get(key);
	}
	public Object[] property_keys() throws Exception
	{
		return sets.getProperties().keySet().toArray();
	}
	public ReflexScenario getScenario()
	{
		return arena.getScenario();
	}
	public String scenario_name()
	{
		return arena.scenario_name();
	}
	public int scenario_difficulty()
	{
		return arena.scenario_difficulty();
	}
	public long scenario_difficulty_delay()
	{
		return arena.scenario_difficulty_delay();
	}
	public int messageCount()
	{
		return arena.messageCount();
	}
	public void addMessage(String str)
	{
		arena.addMessage(str);
	}
	public void removeMessage(int i)
	{
		arena.removeMessage(i);
	}
	public void removeMessage(String s)
	{
		arena.removeMessage(s);
	}
	public String getMessage(int i)
	{
		return arena.getMessage(i);
	}
	public void clearMessage()
	{
		arena.clearMessage();
	}
	public void addDecorate(ReflexDecorate deco)
	{
		arena.addDecorate(deco);
	}
	public ReflexDecorate newDecorate(String name, int x, int y, int dy, int r)
	{
		return new ReflexDecorate(name, x, y, dy, r);
	}
	public StringDecorate newStringDecorate(int x, int y, int dy, String contents)
	{
		StringDecorate newDeco = new StringDecorate(x, y, dy, 1);
		newDeco.setContents(contents);
		return newDeco;
	}
	public void start()
	{
		arena.game_start_by_reflex();
	}
	public static int size_x()
	{
		return Reflexioner.getSize_x();
	}
	public static int size_y()
	{
		return Reflexioner.getSize_y();
	}
	public long player_hp()
	{
		return arena.getSpaceShip().getHp();
	}
	public long player_max_hp()
	{
		return arena.getSpaceShip().getMax_hp();
	}
	public long player_hp_heal()
	{
		return arena.getSpaceShip().getHp_heal();
	}
	public long player_energy()
	{
		return arena.getSpaceShip().getEnergy();
	}
	public long player_max_energy()
	{
		return arena.getSpaceShip().getMax_energy();
	}
	public long player_energy_heal()
	{
		return arena.getSpaceShip().getEnergy_heal();
	}
	public int player_x()
	{
		return arena.getSpaceShip().getX();
	}
	public int player_y()
	{
		return arena.getSpaceShip().getY();
	}
	public void flushReplay()
	{
		arena.flushReplay();
	}
	public void pause()
	{
		arena.pause();
	}
	public void pause_break()
	{
		arena.pause_time(0);
		arena.resume();
	}
	public void pause(long time)
	{
		arena.pause_time(time);
	}
	public Area player_area()
	{
		return arena.getSpaceShip().area();
	}
	public Enemy[] enemies()
	{
		Enemy[] enemies = new Enemy[arena.getEnemies().size()];
		for(int i=0; i<enemies.length; i++)
		{
			enemies[i] = arena.getEnemies().get(i).clone();
		}
		return enemies;
	}
	public void addEnemy(String str)
	{
		arena.addEnemy(str);
	}
	public void addEnemy(Enemy enemy)
	{
		arena.addEnemy(enemy);
	}
	public Enemy newEnemy(String str)
	{
		return arena.newEnemy(str);
	}
	
	public void heal(int target) throws Exception
	{
		if(target < 0) arena.getSpaceShip().setHp(arena.getSpaceShip().getMax_hp());
		else arena.getEnemies().get(target).setHp(arena.getEnemies().get(target).getMax_hp());
	}
	public void removeMissiles()
	{
		for(Missile m : arena.getMissile())
		{
			if(m != null) m.setLaunched(false);
		}
	}
	public void removeEnemies()
	{
		for(Enemy e : arena.getEnemies())
		{
			if(e != null) e.setHp(0);
		}
	}
	public long time()
	{		
		return arena.getDifficulty();
	}
	@Override
	public void control_up()
	{
		arena.control_up();
	}
	@Override
	public void control_down()
	{
		arena.control_down();
	}
	@Override
	public void control_left()
	{
		arena.control_left();
	}
	@Override
	public void control_right()
	{
		arena.control_right();
	}
	@Override
	public void control_break()
	{
		arena.control_break();
	}
	@Override
	public void control_w()
	{
		arena.control_w();
	}
	@Override
	public void control_a()
	{
		arena.control_a();
	}
	@Override
	public void control_s()
	{
		arena.control_s();
	}
	@Override
	public void control_d()
	{
		arena.control_d();
	}
	@Override
	public void control_1()
	{
		arena.control_1();
	}
	@Override
	public void control_2()
	{
		arena.control_2();
	}
	@Override
	public void control_3()
	{
		arena.control_3();
	}
	public void control_4()
	{
		arena.control_4();
	}
	public void control_5()
	{
		arena.control_5();
	}
	public void control_6()
	{
		arena.control_6();
	}
	public void control_7()
	{
		arena.control_7();
	}
	public void control_8()
	{
		arena.control_8();
	}
	public void control_9()
	{
		arena.control_9();
	}
	public void control_0()
	{
		arena.control_0();
	}
	public void control(int k)
	{
		arena.control(k);
	}
	public void control()
	{
		control_auto();
	}
	@Override
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}
	@Override
	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				showable.message("===== reflexer commands =====");
				showable.message();
				showable.message("addMessage(_i) : 메시지 _i를 리스트에 추가합니다. 게임 화면에 보여질 것입니다.");
				showable.message("clearMessage() : 메시지 리스트를 비웁니다.");
				showable.message("removeMessage(_i) : _i가 숫자일 경우 _i번째 메시지를 리스트에서 지웁니다.");
				showable.message("\t_i가 문장일 경우 해당 메시지가 리스트에 있다면 리스트에서 지웁니다.");
				showable.message("getMessage(_i) : 리스트에서 _i번째 메시지를 받아 반환합니다.");
				showable.message("messageCount() : 메시지 리스트에 있는 메시지 수를 반환합니다.");
				showable.message("newDecorate(_i, _x, _y, _dy, _r) : 새로운 장식물 객체를 반환합니다.");
				showable.message("\t _i는 이름, _x와 _y는 초기 위치 좌표, _dy는 아래로 내려가는 속도, _r은 크기입니다.");
				showable.message("newStringDecorate(_x, _y, _dy, _i) : 새로운 문자형 장식물 객체를 반환합니다.");
				showable.message("\t _x와 _y는 초기 위치 좌표, _dy는 아래로 내려가는 속도, _i는 표시할 문장입니다.");
				showable.message("addDecorate(_i) : 장식물 객체 _i를 게임에 반영합니다."); 
				showable.message("size_x() : 게임이 진행되는 구역의 가로 크기를 반환합니다.");
				showable.message("size_y() : 게임이 진행되는 구역의 세로 크기를 반환합니다.");
				showable.message("player_hp() : 함선의 체력을 반환합니다.");
				showable.message("player_max_hp() : 함선의 최대 체력을 반환합니다.");
				showable.message("player_energy() : 함선의 E를 반환합니다.");
				showable.message("player_max_energy() : 함선의 E 최대량을 반환합니다.");
				showable.message("player_energy_heal() : 함선의 E 회복 속도를 반환합니다.");
				showable.message("player_x() : 함선의 위치 x 좌표를 반환합니다.");
				showable.message("player_y() : 함선의 위치 y 좌표를 반환합니다.");
				showable.message("player_area() : 함선이 차지하고 있는 영역에 해당하는 Area 객체를 반환합니다.");
				showable.message("newEnemy(_i) : _i에 해당하는 적 객체를 반환합니다. _i 에는 normal, big, guide, boss 이렇게 4가지 중 하나의 문자열을 받습니다.");
				showable.message("\t 적 객체에는 setEnergy(_energy), getEnergy(), setHp(_hp), getHp() 등의 setter, getter 메소드가 있습니다.");
				showable.message("addEnemy(_i) : _i에 넣은 적 객체를 게임에 반영합니다. \n\t 적 객체가 아닌 문자열을 넣으면 newEnemy(_i) 메소드를 먼저 호출해 반환된 결과를 이용합니다.");
				showable.message("enemies() : 게임이 진행되는 구역 내에 있는 적들에 해당하는 Enemy 객체들을 배열로 반환합니다.");
				showable.message("\t 값이 복사되어 반환되므로, 수정하여도 실제 게임에 반영되지는 않습니다.");
				showable.message("getScenario() : 시나리오 객체를 반환합니다.");
				showable.message("\t 이 객체에는 getName(), getDifficulty() 등의 메소드가 있습니다.");
				showable.message("\t 시나리오를 플레이 중이 아니라면 null 을 반환합니다.");
				showable.message("heal(_i) : _i 번째 적의 체력을 최대로 채웁니다.");
				showable.message("\t _i가 음수이면 함선의 체력을 최대로 채웁니다.");
				showable.message("removeMissiles() : 게임이 진행되는 구역에서 모든 미사일과 발사체, 광선 등을 제거합니다.");
				showable.message("removeEnemies() : 게임이 진행되는 구역에서 모든 적을 제거합니다.");
				showable.message("time() : 게임이 진행된 시간을 반환합니다. 이 시간은 초 단위가 아닌 프레임 단위입니다.");
				showable.message("pause(_i) : 게임을 _i 시간 만큼 일시 정지합니다. _i를 명시하지 않으면 L 키를 누를 때까지 일시 정지합니다.");
				showable.message("pause_break() : 게임 일시 정지를 해제합니다.");
				showable.message("flushReplay() : 메모리에서 현재까지 저장된 리플레이를 날립니다.");
				showable.message("\t 보조기억장치(HDD, SSD)에 저장된 데이터에는 영향을 미치지 않습니다.");
				showable.message("\t 게임이 끝나고 리플레이를 저장하면 날린 시점부터 저장됩니다.");
				showable.message("control(_i) : _i 에 해당하는 키를 누른 것과 동일한 동작을 합니다.");
				showable.message("\t _i는 정수입니다. reflexer 객체에 이를 위한 상수들이 포함되어 있습니다.");
				showable.message("\t 상수로는 UP, DOWN, LEFT, RIGHT, SHIFT, W, A, S, D, NO1, NO2, NO3");
				showable.message("\t 예 : reflexer.control(reflexer.UP)");
				showable.message("strings() : 문자열 셋(언어)을 객체로 반환합니다.");
				showable.message("\t 이 객체에는 getText(_i) 메소드가 있습니다. _i에 숫자를 넣으면 그에 해당하는 단어나 문장이 나옵니다.");
				showable.message("\t 숫자 리스트는 이 객체의 getList() 메소드를 실행하면 배열로 반환되어 나옵니다.");
				showable.message("property(_i) : 현재 적용되어 있는 속성을 반환합니다. _i에 키를 넣으면 그에 대한 속성이 반환됩니다.");
				showable.message("property_keys() : 속성에 쓰이는 키들을 배열로 반환합니다.");
				showable.message("reapply_properties() : 속성을 게임에 다시 적용시킵니다.");
				showable.message("save_properties(_i) : 속성들을 파일로 저장합니다. _i에는 true 또는 false가 들어갈 수 있습니다.");
				showable.message("\t " + sets.getDefault_path() + " 에 property.cfg 파일로 저장됩니다.");
				showable.message("\t _i를 true로 한 경우 현재 지원되는 모든 속성들이 파일에 포함됩니다.");
				showable.message("\t _i를 true로 했을 때 생성된 파일에 있는 속성들만 속성 파일을 수정하여 변경할 수 있는 속성들입니다.");
				showable.message("\t 생성된 파일의 내용을 변경하면 다음 번 게임 실행 시 적용됩니다.");
				showable.message();
				showable.message_bar();				
				break;
			default:				
				showable.message("===== reflexer commands =====");
				showable.message();
				showable.message("addMessage(_i) : Add the message _i (Should be String type) on the list.");
				showable.message("\tIt will be shown on the screen at playing.");
				showable.message("clearMessage() : Clear the message list.");
				showable.message("removeMessage(_i) : Remove _i th message on the list if the _i is number.");
				showable.message("\tRemove _i message on the list if the _i is String type.");
				showable.message("getMessage(_i) : Return _i th message on the list.");
				showable.message("messageCount() : Return how many messages on the list.");
				showable.message("newDecorate(_i, _x, _y, _dy, _r) : Return new decorate object.");
				showable.message("\t _i is name, _x and _y are the location, _dy is speed, _r is size.");
				showable.message("newStringDecorate(_x, _y, _dy, _i) : Return new String-type decorate object.");
				showable.message("\t _x and _y are the location, _dy is speed, _i is contents.");
				showable.message("addDecorate(_i) : Put decorate object into the arena."); 
				showable.message("size_x() : Return the width of the arena.");
				showable.message("size_y() : Return the height of the arena.");
				showable.message("player_hp() : Return your ship\'s HP.");
				showable.message("player_max_hp() : Return your ship\'s max HP.");
				showable.message("player_energy() : Return your ship\'s E.");
				showable.message("player_max_energy() : Return your ship\'s max E.");
				showable.message("player_energy_heal() : Return your ship\'s E charging speed.");
				showable.message("player_x() : Return your ship\'s x-point.");
				showable.message("player_y() : Return your ship\'s y-point.");
				showable.message("player_area() : Return java.awt.geom.Area object presents your ship\'s area.");
				showable.message("newEnemy(_i) : Make Enemy object with String _i. _i can be normal, big, guide, boss.");
				showable.message("\t In Enemy object, there are some methods like setEnergy(_energy), getEnergy(), setHp(_hp), getHp(), ...");
				showable.message("addEnemy(_i) : Put the Enemy object _i into the arena.");
				showable.message("enemies() : Return Enemy objects which is in the arena.");
				showable.message("\t Return copied objects.");
				showable.message("getScenario() : Return scenario object.");
				showable.message("\t If you don\'t playing any scenario, null will be returned.");
				showable.message("heal(_i) : Heal _i th enemy.");
				showable.message("\t If _i is negative number, all enemies will be healed.");
				showable.message("removeMissiles() : Remove all missiles, rasers, beams at the arena.");
				showable.message("removeEnemies() : Remove all enemies at the arena.");
				showable.message("time() : Return how many frame passed.");				
				showable.message("pause(_i) : Pause the game _i frames.");
				showable.message("pause_break() : Resume the game directly.");
				showable.message("flushReplay() : Delete all replay data from memory.");
				showable.message("\t This method does not effect on secondary disks (HDD, SSD).");
				showable.message("control(_i) : Act like you press the key.");
				showable.message("\t _i should be integer value. There are some constant values in reflexer object. ");
				showable.message("\t Available constants : UP, DOWN, LEFT, RIGHT, SHIFT, W, A, S, D, NO1, NO2, NO3");
				showable.message("\t For example : reflexer.control(reflexer.UP)");
				showable.message_bar();
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Reflex(arena, showable, sets);
	}
	@Override
	public String title()
	{
		return "reflexer";
	}
	@Override
	public void control_auto(List<Enemy> enemyList, List<Missile> missileList)
	{
		arena.control_auto(enemyList, missileList);		
	}
	public void control_auto()
	{
		arena.control_auto();
	}
}
