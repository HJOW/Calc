package scripting;

import javax.swing.JOptionPane;

import accumulate.CityManager;
import setting.Setting;
import main_classes.CalcWindow;

public class Script_CalcWindow
{
	private CalcWindow calc;
	private Setting sets;
	
	public String status = "";
	
	public Script_CalcWindow(CalcWindow c, Setting sets)
	{
		calc = c;
		this.sets = sets;
	}
	public void city()
	{
		if(CalcWindow.grade_mode >= 1)
		{
			new CityManager(calc, false, sets).open();
		}
	}
	public void resizeTo(int width, int height)
	{
		calc.setSize(width, height);
	}
	public void moveBy(int x, int y)
	{
		calc.setLocation(x, y);
	}
	public void open()
	{
		calc.open();
	}
	public void close()
	{
		calc.exit();
	}
	public void alert(String s)
	{
		calc.alert(s);
	}
	public String prompt(String str, String x)
	{
		return JOptionPane.showInputDialog(calc, str, x);
	}
	public int confirm(String str)
	{
		return JOptionPane.showConfirmDialog(calc, str);
	}
	public void set_width(int w)
	{		
		calc.setting.setWidth(w);
	}
	public void set_height(int w)
	{		
		calc.setting.setWidth(w);
	}
	public void try_update()
	{
		calc.tryUpdate();
	}
	public void try_update(boolean already_notice)
	{
		calc.tryUpdate(already_notice);
	}
}
