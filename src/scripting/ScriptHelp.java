package scripting;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import reflex.Reflexioner;
import setting.Setting;
import lang.Language;
import main_classes.MessageShowable;
import main_classes.Openable;

public class ScriptHelp implements Openable, ActionListener, ListSelectionListener, MessageShowable
{
	private Window window;
	private ScriptActor actor;
	private Setting sets;
	private Panel mainPanel;
	private Panel upPanel;
	private Panel downPanel;
	private Panel centerPanel;
	private Panel westPanel;
	private MenuBar menuBar;
	private Menu menu_file;
	private MenuItem menu_file_exit;
	private JMenuBar jmenuBar;
	private JMenu jmenu_file;
	private JMenuItem jmenu_file_exit;
	private JList list;
	private Helpable[] helpData;
	private TextArea helpArea;
	public ScriptHelp(ScriptActor actor, Setting sets)
	{
		this.actor = actor;
		this.sets = sets;
		if(this.sets == null) this.sets = Setting.getNewInstance();
		window = new Frame();
		init();
	}
	public ScriptHelp(ScriptActor actor, Setting sets, JDialog dialog)
	{
		this.actor = actor;
		this.sets = sets;
		if(this.sets == null) this.sets = Setting.getNewInstance();
		window = new JDialog(dialog);
		init();
	}
	private void init()
	{
		window.setSize(600, 420);
		window.setLocation((int)(sets.getScreenSize().getWidth()/2 - window.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - window.getHeight()/2));
				
		window.setLayout(new BorderLayout());
		mainPanel = new Panel();
		window.add(mainPanel);
		
		mainPanel.setLayout(new BorderLayout());
		upPanel = new Panel();
		downPanel = new Panel();
		centerPanel = new Panel();
		westPanel = new Panel();
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(westPanel, BorderLayout.WEST);
		
		menuBar = new MenuBar();
		jmenuBar = new JMenuBar();
		if(window instanceof Frame)
		{
			((Frame) window).setMenuBar(menuBar);
			((Frame) window).setTitle(sets.getLang().getText(Language.SCRIPT) + " " + sets.getLang().getText(Language.HELP));
		}
		
		if(window instanceof JDialog)
		{
			((JDialog) window).setJMenuBar(jmenuBar);
			((JDialog) window).setTitle(sets.getLang().getText(Language.SCRIPT) + " " + sets.getLang().getText(Language.HELP));
		}
		
		menu_file = new Menu(sets.getLang().getText(Language.MENU_FILE));
		menuBar.add(menu_file);		
		
		menu_file_exit = new MenuItem(sets.getLang().getText(Language.EXIT), new MenuShortcut(KeyEvent.VK_4));
		menu_file_exit.addActionListener(this);
		menu_file.add(menu_file_exit);
		
		jmenu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		jmenuBar.add(jmenu_file);		
		
		jmenu_file_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		jmenu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));	
		jmenu_file_exit.addActionListener(this);
		jmenu_file.add(jmenu_file_exit);
		
		westPanel.setLayout(new BorderLayout());
		
		helpData = new Helpable[actor.helpList().length + 2];
		for(int i=0; i<helpData.length; i++)
		{
			if(i >= 2)
				helpData[i] = actor.helpList()[i - 2];
		}
		helpData[0] = new InitialHelp(this, sets);
		helpData[1] = new KeywordHelp(this, sets);
		
		list = new JList();
		list.setBorder(new EtchedBorder());
		String[] listData = new String[helpData.length];
		for(int i=0; i<helpData.length; i++)
		{
			listData[i] = helpData[i].title();
		}
		list.setListData(listData);
		list.addListSelectionListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		westPanel.add(list, BorderLayout.CENTER);
		
		centerPanel.setLayout(new BorderLayout());
		helpArea = new TextArea("", 10, 10, TextArea.SCROLLBARS_VERTICAL_ONLY);
		helpArea.setEditable(false);
		centerPanel.add(helpArea);
		
		try
		{
			if(window instanceof JDialog) Reflexioner.try_transparent(window, Reflexioner.getTransparent_opacity());
		}
		catch(Exception e)
		{
			
		}
	}

	@Override
	public void open()
	{
		window.setVisible(true);
		list.setSelectedIndex(0);
	}

	@Override
	public void exit()
	{
		window.setVisible(false);
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == list)
		{
			String selected = (String) list.getSelectedValue();
			int selectedIndex = -1;
			for(int i=0; i<helpData.length; i++)
			{
				if(helpData[i].title().equalsIgnoreCase(selected))
				{
					selectedIndex = i;
					break;
				}
			}
			helpArea.setText("");
			helpData[selectedIndex].newHelp(this).help();		
			helpArea.setCaretPosition(0);
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_file_exit || ob == jmenu_file_exit)
		{
			exit();
		}
		
	}
	@Override
	public void message()
	{
		helpArea.append("\n");
		
	}
	@Override
	public void message_bar()
	{
		helpArea.append("---------------------------------------------------\n");
		
	}
	@Override
	public void message(String str)
	{
		helpArea.append(str + "\n");
		
	}
	@Override
	public void alert(String str)
	{
		JOptionPane.showMessageDialog(window, str);
		helpArea.append(str + "\n");		
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}
}
class InitialHelp implements Helpable
{
	protected MessageShowable showable;
	protected Setting sets;
	public InitialHelp(MessageShowable showable, Setting sets)
	{
		this.showable = showable;
		this.sets = sets;
	}
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}

	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				showable.message("왼쪽 리스트에는 게임에서 쓸 수 있는 스크립트 객체들의 이름이 있습니다.");
				showable.message("클릭해 선택하여 해당 객체의 도움말을 볼 수 있습니다.");
				showable.message("");
				showable.message("객체에는 여러 상수와 메소드가 있습니다.");
				showable.message("객체 이름을 쓰고, 뒤에 마침표(.)를 붙인 후 메소드나 상수 이름을 사용합니다.");
				showable.message("");
				showable.message("예를 들어, console 객체의 alert(_i) 메소드를 써서 \"Hello\" 라는 메시지를 띄우고 싶으면");
				showable.message("console.alert(\"Hello\")");
				showable.message("라고 씁니다.");
				showable.message("");
				showable.message("이 게임의 스크립트는 Java 에서 제공하는 JavaScript 엔진으로 동작합니다.");
				showable.message("변수 선언에는 데이터 타입을 명시하지 않아도 되지만, 그 외에는 Java 언어 표현을 따릅니다.");
				showable.message("");
				showable.message("");
				showable.message("");
				showable.message("먼저, 변수에 대해 알아보겠습니다.");
				showable.message("");
				showable.message("변수는 데이터에 이름을 붙여 메모리에 저장하는 데 사용합니다.");
				showable.message("변수는 선언을 하고 사용해야 합니다.");
				showable.message("변수 선언에는 데이터 타입 명시가 필요하지 않습니다.");
				showable.message("");
				showable.message("예를 들어, a 라는 변수를 만들고 싶으면");
				showable.message("a = 1");
				showable.message("이렇게 씁니다. a 라는 변수가 만들어지고 값 1이 a에 들어갑니다.");
				showable.message("");
				showable.message("변수 이름은 반드시 영어 알파벳으로 시작해야 합니다. 이름 중간에 띄어쓰기가 들어가서는 안됩니다.");
				showable.message("변수 이름에 숫자가 들어갈 수는 있지만, 이름의 첫 글자에는 위치할 수 없습니다.");
				showable.message("");
				showable.message("= 기호는 오른쪽 계산 결과를 왼쪽의 변수에 넣는 명령 기호입니다.");
				showable.message("왼쪽의 변수에, 기존에 들어 있던 값은 사라집니다.");
				showable.message("왼쪽에는 반드시 변수 이름 하나만을 사용하여야 합니다.");
				showable.message("우측의 계산 결과가 먼저 계산되어 왼쪽의 변수에 들어갑니다.");
				showable.message("");
				showable.message("숫자는 +, -, *, / 연산을 사용할 수 있습니다.");
				showable.message("예를 들어, a 에, 20 * 129 라는 연산 결과를 넣고 싶다면");
				showable.message("a = 20 * 129");
				showable.message("라고 씁니다.");
				showable.message("");
				showable.message("덧셈과 뺄셈은 곱셈과 나눗셈보다 나중에 실행됩니다.");
				showable.message("계산에 괄호를 사용해 연산 순서를 바꿀 수도 있습니다.");
				showable.message("a = 10 + 2 * (125 + 1)");
				showable.message("이 경우 125 + 1이 먼저 실행되고, 2가 곱해진 후 10이 더해져 a에 들어갑니다.");
				showable.message("");
				showable.message("계산식에 변수 이름을 사용할 수도 있습니다.");
				showable.message("예를 들어, a에, 이전에 a에 있던 값에 39를 더하고 싶다면");
				showable.message("a = a + 39");
				showable.message("라고 씁니다. 우측 계산식이 먼저 계산된 이후에 a에 값이 들어갑니다.");
				showable.message("");
				showable.message("어떤 값을 비교할 때에는 ==, <=, >=, <, >, != 를 사용합니다.");
				showable.message("a에 아무 숫자나 입력한 후,");
				showable.message("a = a > 128");
				showable.message("을 실행해 보면, a의 값이 128보다 큰 경우 > 연산의 결과로 true가 나와 a에 들어갑니다.");
				showable.message("console.alert(a)");
				showable.message("를 실행해 보면 true 라는 메시지 창이 나타날 것입니다.");
				showable.message("");
				showable.message("한 줄에 여러 명령을 실행하려면 세미콜론(;) 기호를 사용합니다.");
				showable.message("예를 들어, 한 줄의 명령으로 a에 1을 넣고, 다시 a에 a의 값의 2배의 값을 넣으려면");
				showable.message("a = 1; a = a * 2;");
				showable.message("를 입력합니다. 하지만 가급적이면 여러 줄로 나누어 쓰는 것이 좋습니다.");
				showable.message("");
				showable.message("여러 줄의 스크립트를 실행하려면");
				showable.message("메뉴의 " + sets.getLang().getText(Language.VIEW) + " - " + sets.getLang().getText(Language.SCRIPT) + " 를 선택해 나타나는 창에 스크립트를 입력하고 " 
				+ sets.getLang().getText(Language.RUN) + " " + sets.getLang().getText(Language.SCRIPT) + " 를 클릭합니다.");
				showable.message("이 창에 5, 6, 7 이렇게 3개의 탭이 있으며, 각각에 스크립트를 입력하고 " + sets.getLang().getText(Language.ACCEPT) + " 버튼을 누르면");
				showable.message("게임 시작 시 키보드로 5, 6, 7 키를 누르면 그에 해당하는 스크립트가 실행됩니다.");
				showable.message("");
				showable.message("비교 연산의 결과로 true, false 값이 나온 것을 기억해 주세요.");
				showable.message("이 결과 자체도 값의 일종이며, 변수에 넣을 수 있습니다.");
				showable.message("");
				showable.message("true, false 를 논리 데이터라 하며, 숫자 데이터와 구분됩니다.");
				showable.message("데이터 종류가 다른 두 값을 연산할 수는 없습니다.");
				showable.message("");
				showable.message("논리 데이터를 위한 연산으로는 ! 가 있습니다. ! 는 논리 데이터를 반대로 바꾸는 데 사용합니다.");
				showable.message("예를 들어,");
				showable.message("a = ! a");
				showable.message("를 실행하면, a에 true가 들어 있는 경우 false 가 a에 들어가며, false가 들어 있었다면 true가 들어갑니다.");
				showable.message("");
				showable.message("어떤 조건에서만 뭔가 실행되게 하고 싶으면 if() {} 구문을 사용합니다.");
				showable.message("if() {} 구문에서는 if()의 괄호 안에 논리 데이터가 들어가야 합니다.");
				showable.message("계산 결과가 논리 데이터이면 되므로, 그 자리에 비교 연산이 들어가도 됩니다.");
				showable.message("{} 안에는, if() 안의 논리 데이터가 true인 경우 실행할 명령을 입력합니다.");
				showable.message("");
				showable.message("0과 1 사이의 임의의 값을 넣으려면 newer 객체의 random() 메소드를 이용합니다.");
				showable.message("");
				showable.message("b에 0과 1 사이의 임의의 값을 넣고, b가 0.5 이상인지의 비교 결과를 a에 넣은 후,");
				showable.message("a가 true이면 b를 2로 나누어 b에 넣으려 한다면 ");
				showable.message("b = newer.random()");
				showable.message("a = b >= 0.5");
				showable.message("if(a)");
				showable.message("{");
				showable.message("  b = b / 2");
				showable.message("}");
				showable.message("이렇게 합니다.");
				showable.message("");
				showable.message("어떤 조건이 만족하지 않을 때까지 뭔가를 반복해 실행하려면 while() {} 문을 사용합니다.");
				showable.message("while() 안에는 논리 데이터가 들어가야 합니다. 이 논리 데이터가 true인 이상 {} 안의 명령이 계속 실행될 것입니다.");
				showable.message("마찬가지로 while() 안에 논리 데이터 대신 비교 연산이 들어갈 수도 있습니다.");
				showable.message("");
				showable.message("예를 들어, a에, 1부터 100까지 더한 결과를 넣고 싶다면");
				showable.message("a = 0");
				showable.message("b = 1");
				showable.message("while(b <= 55)");
				showable.message("{");
				showable.message("  a = a + b");
				showable.message("  b = b + 1");
				showable.message("}");
				showable.message("이렇게 합니다.");
				showable.message("");
				showable.message("데이터에는 숫자, 논리, 그리고 문자 데이터가 있습니다.");
				showable.message("데이터는 그 외에도 여러 가지가 있습니다. 자세한 사항은 newer 객체의 도움말을 참고하세요.");
				showable.message("");
				showable.message("문자 데이터에는 =, ==, + 연산만 가능합니다.");
				showable.message("변수가 아닌 문자 데이터에는 \"\" 를 둘러 싸야 문자 데이터로 인식합니다.");				
				showable.message("");
				showable.message("예를 들어, a에 Hello 라는 문자 데이터를 넣으려면");
				showable.message("a = \"Hello\"");
				showable.message("이렇게 합니다.");
				showable.message("");
				showable.message("띄어쓰기도 기호이므로, 필요에 따라 \"\" 안에 띄어쓰기 또한 포함해 써야 합니다.");
				showable.message("줄 띄기 또한 기호이며, \\n 을 쓰면 줄 띄기 기호로 인식합니다.");				
				showable.message("");
				showable.message("문자 데이터에 문자 혹은 다른 종류의 데이터와 +할 수 있습니다.");
				showable.message("이 경우 문자 데이터에 이어 붙여 문자 데이터가 결과로 나옵니다.");
				showable.message("예를 들어");
				showable.message("a = \"Hello \" + (100 == 100) + \" World \" + 129");
				showable.message("를 실행하면 Hello true World 129 가 a에 들어갈 것입니다.");
				showable.message("");
				showable.message("Vector 는 같은 종류의 데이터를 여러 개를 포함할 수 있는 데이터입니다.");
				showable.message("예를 들어, 문자 데이터를 여러 개를 넣을 Vector을 만들어 a에 넣으려면");
				showable.message("a = newer.vector(newer.TYPE_STRING)");
				showable.message("이렇게 합니다.");
				showable.message("");
				showable.message("이 Vector 에 데이터를 넣으려면 add(_i) 메소드를 이용합니다. _i에는 넣을 데이터를 넣습니다.");
				showable.message("안에 있는 데이터 갯수는 size() 메소드를 이용해 알아냅니다.");
				showable.message("안에 있는 몇 번째 데이터를 알아내려면 get(_i) 메소드를 이용합니다. _i에는 자연수 숫자 값 혹은 이런 값이 넣어진 변수 이름을 넣습니다.");
				showable.message("");
				showable.message("예를 들어, a에 문자 데이터를 넣을 Vector을 만들어 넣고, 여기에 \"Hello\", \"World\" 두 개를 넣고, 차례로 메시지를 띄우려면");
				showable.message("a = newer.vector(newer.TYPE_STRING)");
				showable.message("a.add(\"Hello\")");
				showable.message("a.add(\"World\")");
				showable.message("b = 0");
				showable.message("while(b < a.size())");
				showable.message("{");
				showable.message("  console.alert(a.get(b))");
				showable.message("  b = b + 1");
				showable.message("}");
				showable.message("이렇게 합니다.");
				showable.message("");
				showable.message("자주 사용하는 명령 여러 줄을 함수로 만들어 두면 편하게 사용할 수 있습니다.");
				showable.message("function 이름() {} 구문을 사용합니다.");
				showable.message("");
				showable.message("function 뒤에는 한 칸을 띄고, 함수 이름을 넣습니다.");
				showable.message("함수 이름은 변수 이름과 마찬가지로, 영어 알파벳으로 시작해야 합니다.");
				showable.message("{} 안에는 이 함수 실행 시 할 명령들을 입력합니다.");
				showable.message("");
				showable.message("예를 들어, 이미 사용된 변수 a, b, c에 0을 넣는 함수를 setZero() 라는 이름으로 만들고 싶다면 ");
				showable.message("function setZero()");
				showable.message("{");
				showable.message("  a = 0");
				showable.message("  b = 0");
				showable.message("  c = 0");
				showable.message("}");
				showable.message("이렇게 합니다. 실행한 이후, setZero() 명령을 실행할 때마다 안에 넣은 명령들이 실행됩니다.");
				showable.message("");
				showable.message("함수 실행 시 필요한 데이터가 있다면, 이름 옆의 () 안에 넣습니다.");
				showable.message("이를 위해서는, 함수를 만들 때 () 안에 그를 대표할 변수 이름을 넣어 주어야 합니다.");
				showable.message("");
				showable.message("함수 실행의 결과가 있는 경우 {} 안에 넣을 명령에 return 문을 사용합니다.");
				showable.message("return 문이 실행되면 함수를 즉시 벗어나게 되므로, 함수 내에서 명령의 끝에 보통 위치합니다.");
				showable.message("return 뒤에 한 칸을 띄고, 함수의 결과가 될 변수나 수식을 넣습니다.");
				showable.message("");
				showable.message("숫자를 넣고 실행하면, 1부터 넣은 숫자까지 더하는 함수를 adds(n) 라는 이름으로 만들어 봅시다.");
				showable.message("이름의 괄호 안에 n을 넣은 이유는 이 함수에는 숫자 데이터 하나가 필요하기 때문입니다.");
				showable.message("이 함수는 여러 번 실행될 수 있으며, n의 자리에는 서로 다른 숫자가 들어갈 수도 있습니다.");
				showable.message("");
				showable.message("function adds(n)");
				showable.message("{");
				showable.message("  a = 0");
				showable.message("  b = 1");
				showable.message("  while(b <= n)");
				showable.message("  {");
				showable.message("    a = a + b");
				showable.message("    b = b + 1");
				showable.message("  }");
				showable.message("  return a");
				showable.message("}");
				showable.message("");
				showable.message("이 함수 안에 썼던 a와 b는, 이미 선언된 적이 없었다면 지역 변수로 만들어집니다.");
				showable.message("지역 변수는 해당하는 함수 내에서만 사용할 수 있으며, 함수가 실행될 때마다 따로 선언되어 만들어집니다.");
				showable.message("");
				showable.message("이 함수를 사용해 봅시다.");
				showable.message("a 에는 1부터 100을 더한 결과를, b에는 1부터 1000까지 더한 결과를, c에는 1부터 10000까지 더한 결과를 넣어 봅시다.");
				showable.message("위의 함수 선언문을 한번 실행한 후");
				showable.message("");
				showable.message("a = adds(100)");
				showable.message("b = adds(1000)");
				showable.message("c = adds(10000)");
				showable.message("");
				showable.message("이렇게 합니다. a = adds(100) 에서, adds(100) 자체가 일종의 수식처럼 사용되었습니다.");
				showable.message("괄호 안의 100은 function문의 n을 대신하며, 함수 내 명령문들을 실행하게 됩니다.");
				showable.message("return 문을 만나면, 그 뒤의 값을 되돌립니다. adds(100) 했던 위치로 돌아가, adds(100) 대신 return한 값이 그 자리에 위치합니다.");
				showable.message("a = adds(100) 이었으므로 a 에는 adds(100)의 계산 결과가 들어갈 것입니다.");
				showable.message("");
				showable.message("return 문으로는 하나의 값만을 함수 밖으로 보낼 수 있습니다.");
				showable.message("여러 값을 보내야 한다면 Vector를 만들어 그 안에 여러 데이터를 넣고 return 문에 쓰면 됩니다.");
				showable.message("");
				showable.message("이것으로 스크립트의 기초 설명을 마칩니다.");
				showable.message("준비되어 있는 객체들의 설명을 보면서, 객체 안에 있는 메소드들을 사용해 보세요.");
				showable.message("console 은 메시지를 보여줄 때, newer는 문자나 숫자, 논리가 아닌 데이터를 만들거나, 데이터를 변환하는 데 사용합니다.");
				break;
			default:
				showable.message("On left side, there are object names on the list.");
				showable.message("Select one to watch its help.");
				showable.message("");
				showable.message("Script will be run by JavaScript engine supported by Java Virtual Machine.");
				showable.message("Do not need write data-type name to declare variable, but, otherwise, you need to keep Java syntax.");
				break;
		}
	}

	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new InitialHelp(showable, Setting.getNewInstance());
	}
	public Helpable newHelp(MessageShowable showable, Setting sets)
	{
		return new InitialHelp(showable, sets);
	}

	@Override
	public String title()
	{
		return "Main";
	}	
}
class KeywordHelp extends InitialHelp
{
	public KeywordHelp(MessageShowable showable, Setting sets)
	{
		super(showable, sets);
	}
	@Override
	public String title()
	{
		return "Terms";
	}	
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new KeywordHelp(showable, Setting.getNewInstance());
	}
	public Helpable newHelp(MessageShowable showable, Setting sets)
	{
		return new KeywordHelp(showable, sets);
	}
	public void help()
	{
		super.help();
	}
	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				showable.message("\t문자열");
				showable.message("문자열은 문자들의 나열을 의미합니다.");
				showable.message("한 글자 이상의 글자들을 묶은 단위입니다.");
				showable.message("단어도 문자열이 될 수 있으며, 문장도, 단락도, 장문 또한 문자열이 될 수 있습니다.");
				showable.message("여러 문자들로 이루어진 단위를 통틀어 말하는 용어입니다.");
				showable.message("영어로는 String 이라고 합니다.");
				showable.message("");
				showable.message("\t변수");
				showable.message("어떤 값을 담아두는 공간입니다.");
				showable.message("계산을 위해서는 어떤 값을 담아 두는 것이 필요할 때가 있습니다.");
				showable.message("대입 기호(=)를 이용해 원하는 값을 넣어둔 후, 필요할 때 숫자 대신 변수의 이름을 수식에 넣어 사용합니다.");
				showable.message("이 때문에 변수에는 이름이 필요합니다.");
				showable.message("변수 이름은 숫자로는 시작할 수 없으며 영문자로 구성될 수 있습니다.");
				showable.message("변수에는 숫자 뿐 아니라, 문자열 및 각종 데이터들이 담길 수 있습니다.");
				showable.message("");
				showable.message("\t함수");
				showable.message("하나 이상의 명령줄을 포함하는 논리적 단위입니다.");
				showable.message("반복적으로 사용되는 작업을 함수로 만들어 두면, 작업 시 여러 줄의 반복적인 명령줄 대신 함수 이름과 괄호만 쓰면 됩니다.");
				showable.message("예를 들어, 슈팅 게임에서 미사일이 발사되려면, 새로운 미사일 단위가 만들어져야 하고, 위치가 주인공 캐릭터 바로 앞으로 지정되어야 하고, 공격력과 속도가 설정되어야 합니다.");
				showable.message("이러한 복잡한 명령들을 함수로 만들어 두면, 차후 읽고 수정하기도 편해집니다.");
				showable.message("자세한 내용은 Main 도움말을 참고하세요.");
				showable.message("");
				showable.message("\t객체");
				showable.message("자체 속성과 함수(메소드)를 담는 논리적 단위입니다.");
				showable.message("예를 들어, 슈팅 게임에서 미사일은, 현재 위치 좌표와 속도, 공격력, 크기 등을 속성으로 가집니다.");
				showable.message("이러한 속성들은 종종 변수인 것처럼 사용됩니다. 대부분의 경우 대입 기호를 사용해 값을 변경할 수 있습니다.");
				showable.message("미사일은 \"나아가다\" 라는 행위를 할 수 있습니다.");
				showable.message("실제 게임에서는 종종 update() 라는 함수(메소드)로 만들어져 있으며, 필요에 따라 호출됩니다.");
				showable.message("하나의 객체에도 속성과 메소드는 여러 개가 될 수 있습니다.");
				showable.message("객체 자체도 변수에 담겨 사용되며, 그 안의 속성, 메소드 사용 시 변수명 뒤에 마침표를 찍고 그 뒤에 속성, 메소드명을 붙여 사용합니다.");
				showable.message("");
				break;
			default:
				showable.message("This contents is only for korean language.");
				break;
		}
	}
}
