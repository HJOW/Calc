package scripting;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Script_Component
{
	private ScriptActor manager;
	private Script_Console console;
	public Script_Component(ScriptActor manager, Script_Console console)
	{
		this.manager = manager;
		this.console = console;
	}
	public void help(int lang, Script_Console console)
	{
		switch(lang)
		{
			case 1:
				console.message("===== component 명령어 =====");
				console.message();
				console.message("이 객체는 GUI 객체를 만들기 위해 사용합니다.");
				console.message("newFrame(), newPanel(), newButton() 등을 사용해 컴포넌트 객체를 만듭니다.");
				console.message();
				console.message("newFrame() 을 사용하여 상위의 Frame 객체를 만듭니다. 이 객체에 여러 하위 컴포넌트를 add() 하여 붙입니다.");
				console.message("  setSize(w, h) 를 이용해 크기를 지정하여야 사용할 수 있습니다. w 와 h 에 각각 수평, 수직 길이를 정수로 지정합니다.");
				console.message("  setVisible(true) 를 이용해 화면에 보여지게 할 수 있습니다. true 대신 false 를 넣어 사용하여 창을 닫을 수 있습니다.");
				console.message();
				console.message("Frame 객체의 setLayout(ly) 을 사용하여 레이아웃 매니저를 지정합니다. ly 에 레이아웃 매니저 객체를 넣습니다.");
				console.message("  newFlowLayout(), newBorderLayout(), newGridLayout(i, j) 를 사용하여 레이아웃 매니저를 만들어 사용합니다.");
				console.message();
				console.message("Flow Layout 레이아웃 매니저는 모든 컴포넌트를 가운데로 정렬해 배치하고 적절한 크기를 부여합니다.");
				console.message("Border Layout 레이아웃 매니저는 모든 컴포넌트를 꽉 채워 배치합니다. Frame 에 add(comp, adds) 하여 컴포넌트를 동쪽, 서쪽, 남쪽, 북쪽, 중앙에 배치할 수 있습니다.");
				console.message("  adds 에 어느 쪽에 배치할 지를 상수로 넣습니다. 저 위치에 newBorderLayout().EAST, newBorderLayout().WEST 등을 넣습니다.");
				console.message("  그냥 add(comp) 할 경우 자동으로 중앙으로 배치합니다. 이는 newBorderLayout().CENTER 에 해당합니다.");
				console.message("Grid Layout 레이아웃 매니저는 격자 형태로 컴포넌트들을 배치합니다. 행과 열의 수를 newGridLayout(i, j) 의 i, j에 각각 정수 값을 넣어 지정합니다.");
				console.message();
				console.message("newListener() 를 사용하여 리스너 객체를 만들 수 있습니다.");
				console.message("  이 객체에는 온갖 종류의 이벤트 처리기가 정의되어 있습니다.");
				console.message("  이 객체에는 문자열을 넣을 수 있는 멤버변수들이 정의되어 있으며, 이 변수들 안에 이벤트 발생 시 처리할 스크립트를 넣어 사용합니다.");
				console.message("  예를 들어, 버튼이 눌렸을 때 스크립트를 처리하게 하고 싶다면, 버튼 객체를 addActionListener(listener) 하여야 합니다.");
				console.message("  이 때 listener 에 리스너 객체가 들어갑니다.");
				console.message("  버튼이 눌렸을 때 리스너 객체에 있는 _actionPerformed 멤버변수에 들어 있는 문자열대로 스크립트가 실행됩니다.");
				console.message("  이 문자열을 사전에 변경하여 사용하십시오.");
				console.message("  이러한 문자열에 사용하는 스크립트를 위해, 매 이벤트 발생 시 event_source 라는 변수에 이벤트 발생 주체 정보가 문자열로 입력됩니다.");
				console.message("  컴포넌트들을 각각 toString() 하여 얻은 문자열을 equals(event_source) 해서 비교하여, true가 나온다면 해당 컴포넌트가 작동된 것입니다.");
				
				console.message_bar();
				break;
			default:
				console.message("===== component order set =====");
				console.message("I\'m sorry that there are only korean helps for this module.");
				help(1, console);
		}
	}
	public Script_Listener newListener()
	{
		return new Script_Listener(manager, console);
	}
	public JFrame newFrame()
	{
		JFrame newFrame = new JFrame();
		return newFrame;
	}
	public JPanel newPanel()
	{
		return new JPanel();
	}
	public JButton newButton()
	{
		return new JButton();
	}
	public JButton newButton(String label)
	{
		return new JButton(label);
	}
	public JTextField newTextField()
	{
		return new JTextField();
	}
	public JTextField newTextField(int length)
	{
		return new JTextField(length);
	}
	public JTextArea newTextArea()
	{
		return new JTextArea();
	}
	public JEditorPane newEditorPane()
	{
		return new JEditorPane();
	}
	public JScrollPane newScrollPane(Component e)
	{
		return new JScrollPane(e);
	}
	public JScrollPane newScrollPane(Component e, int vertical, int horizontal)
	{
		return new JScrollPane(e, vertical, horizontal);
	}
	public ButtonGroup newButtonGroup()
	{
		return new ButtonGroup();
	}
	public JRadioButton newRadioButton()
	{
		return new JRadioButton();
	}
	public JRadioButton newRadioButton(String label)
	{
		return new JRadioButton(label);
	}
	public JCheckBox newCheckBox()
	{
		return new JCheckBox();
	}
	public JCheckBox newCheckBox(String label)
	{
		return new JCheckBox(label);
	}
	public JComboBox newComboBox()
	{
		return new JComboBox();
	}
	public JProgressBar newProgressBar()
	{
		return new JProgressBar();
	}
	public JProgressBar newProgressBar(int orient, int min, int max)
	{
		return new JProgressBar(orient, min, max);
	}
	public JDialog newDialog()
	{
		return new JDialog();
	}
	public JDialog newDialog(JDialog parentComponent)
	{
		return new JDialog(parentComponent);
	}
	public JDialog newDialog(JFrame parentComponent)
	{
		return new JDialog(parentComponent);
	}
	public JDialog newDialog(JFrame parentComponent, boolean modal)
	{
		return new JDialog(parentComponent, modal);
	}
	public JMenuBar newMenuBar()
	{
		return new JMenuBar();
	}
	public JMenu newMenu()
	{
		return new JMenu();
	}
	public JMenu newMenu(String label)
	{
		return new JMenu(label);
	}
	public JMenuItem newMenuItem()
	{
		return new JMenuItem();
	}
	public JMenuItem newMenuItem(String label)
	{
		return new JMenuItem(label);
	}
	public void showMessageDialog(Component parentComponent, String message)
	{
		JOptionPane.showMessageDialog(parentComponent, message);
	}
	public int showConfirmDialog(Component parentComponent, String message, String title, int optionType, int messageType)
	{
		return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType);
	}
	public JLabel newLabel()
	{
		return new JLabel();
	}
	public JLabel newLabel(String label)
	{
		return new JLabel(label);
	}
	public BorderLayout newBorderLayout()
	{
		return new BorderLayout();
	}
	public FlowLayout newFlowLayout()
	{
		return new FlowLayout();
	}
	public GridLayout newGridLayout(int rows, int cols)
	{
		return new GridLayout(rows, cols);
	}
	public EtchedBorder newEtchedBorder()
	{
		return new EtchedBorder();
	}
}
