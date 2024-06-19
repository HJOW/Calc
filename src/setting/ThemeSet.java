package setting;

import java.awt.Color;
import java.io.Serializable;

public class ThemeSet implements Serializable
{
	private static final long serialVersionUID = -4468086207113862283L;
	private Color selected_back, selected_fore, selected_inside, selected_button, unselected_back, unselected_fore, unselected_inside, unselected_button;
	private boolean color_reverse = false;
	

	public ThemeSet()
	{
		selected_back = new Color(190, 210, 255);
		selected_inside = new Color(135, 180, 235);
		selected_fore = new Color(0, 0, 139);
		unselected_back = new Color(220, 230, 255);
		unselected_inside = new Color(175, 200, 255);
		unselected_fore = new Color(0, 0, 255);
		selected_button = null;
		unselected_button = null;
	}
	
	public ThemeSet(int type)
	{
		int base_r = 0, base_g = 0, base_b = 0;
		switch(type)
		{
			case 0:
				selected_back = new Color(190, 210, 235);
				selected_inside = new Color(135, 180, 235);
				selected_fore = new Color(0, 0, 139);
				unselected_back = new Color(220, 230, 255);
				unselected_inside = new Color(175, 200, 255);
				unselected_fore = new Color(0, 0, 255);
				selected_button = null;
				unselected_button = null;
				break;
			case 1:
				selected_back = new Color(76, 76, 76);
				selected_inside = new Color(90, 90, 90);
				selected_fore = new Color(150, 150, 150);
				unselected_back = new Color(85, 85, 85);
				unselected_inside = new Color(105, 105, 105);
				unselected_fore = new Color(135, 135, 135);
				selected_button = new Color(50, 50, 50);
				unselected_button = new Color(70, 70, 70);
				color_reverse = true;
				break;
			case 2:
				base_r = 71; base_g = 200; base_b = 62;
				selected_back = new Color(base_r, base_g, base_b);
				selected_inside = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.85)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.85)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.85)));
				selected_fore = new Color(base_r - ((int) ((((double) base_r)) * 0.3)), base_g - ((int) ((((double) base_g)) * 0.3)), base_b - ((int) ((((double) base_b)) * 0.3)));
				unselected_back = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.3)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.3)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.3)));
				unselected_inside = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.9)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.9)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.9)));
				unselected_fore = new Color(base_r - ((int) ((((double) base_r)) * 0.4)), base_g - ((int) ((((double) base_g)) * 0.4)), base_b - ((int) ((((double) base_b)) * 0.4)));
				selected_button = new Color(base_r - ((int) ((((double) base_r)) * 0.1)), base_g - ((int) ((((double) base_g)) * 0.1)), base_b - ((int) ((((double) base_b)) * 0.1)));
				unselected_button = new Color(base_r - ((int) ((((double) base_r)) * 0.05)), base_g - ((int) ((((double) base_g)) * 0.05)), base_b - ((int) ((((double) base_b)) * 0.05)));
				color_reverse = true;
				break;
			case 3:
				base_r = 241; base_g = 110; base_b = 110;
				selected_back = new Color(base_r, base_g, base_b);
				selected_inside = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.85)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.85)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.85)));
				selected_fore = new Color(base_r - ((int) ((((double) base_r)) * 0.3)), base_g - ((int) ((((double) base_g)) * 0.3)), base_b - ((int) ((((double) base_b)) * 0.3)));
				unselected_back = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.3)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.3)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.3)));
				unselected_inside = new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.9)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.9)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.9)));
				unselected_fore = new Color(base_r - ((int) ((((double) base_r)) * 0.4)), base_g - ((int) ((((double) base_g)) * 0.4)), base_b - ((int) ((((double) base_b)) * 0.4)));
				selected_button = new Color(base_r - ((int) ((((double) base_r)) * 0.1)), base_g - ((int) ((((double) base_g)) * 0.1)), base_b - ((int) ((((double) base_b)) * 0.1)));
				unselected_button = new Color(base_r - ((int) ((((double) base_r)) * 0.05)), base_g - ((int) ((((double) base_g)) * 0.05)), base_b - ((int) ((((double) base_b)) * 0.05)));
				color_reverse = true;
				break;
			case 100: // TEST
				selected_back = new Color(190, 210, 255);
				selected_inside = new Color(135, 180, 235);
				selected_fore = new Color(0, 0, 139);
				unselected_back = new Color(220, 230, 255);
				unselected_inside = new Color(175, 200, 255);
				unselected_fore = new Color(0, 0, 255);
				selected_button = null;
				unselected_button = null;
				break;
				
			default:
				selected_back = new Color(190, 210, 255);
				selected_inside = new Color(135, 180, 235);
				selected_fore = new Color(0, 0, 139);
				unselected_back = new Color(220, 230, 255);
				unselected_inside = new Color(175, 200, 255);
				unselected_fore = new Color(0, 0, 255);	
				selected_button = null;
				unselected_button = null;
		}
	}
	public static StringWithInt[] list()
	{
		StringWithInt[] newList = new StringWithInt[4];
		newList[0] = new StringWithInt("Blue", 0);
		newList[1] = new StringWithInt("Dark", 1);
		newList[2] = new StringWithInt("Green", 2);
		newList[3] = new StringWithInt("Red", 3);
		return newList;
	}
	public Color getSelected_back()
	{
		return selected_back;
	}

	public void setSelected_back(Color selected_back)
	{
		this.selected_back = selected_back;
	}

	public Color getSelected_fore()
	{
		return selected_fore;
	}

	public void setSelected_fore(Color selected_fore)
	{
		this.selected_fore = selected_fore;
	}

	public Color getSelected_inside()
	{
		return selected_inside;
	}

	public void setSelected_inside(Color selected_inside)
	{
		this.selected_inside = selected_inside;
	}

	public Color getUnselected_back()
	{
		return unselected_back;
	}

	public void setUnselected_back(Color unselected_back)
	{
		this.unselected_back = unselected_back;
	}

	public Color getUnselected_fore()
	{
		return unselected_fore;
	}

	public void setUnselected_fore(Color unselected_fore)
	{
		this.unselected_fore = unselected_fore;
	}

	public Color getUnselected_inside()
	{
		return unselected_inside;
	}

	public void setUnselected_inside(Color unselected_inside)
	{
		this.unselected_inside = unselected_inside;
	}
	public Color getSelected_button()
	{
		return selected_button;
	}
	public void setSelected_button(Color selected_button)
	{
		this.selected_button = selected_button;
	}
	public Color getUnselected_button()
	{
		return unselected_button;
	}
	public void setUnselected_button(Color unselected_button)
	{
		this.unselected_button = unselected_button;
	}
	public boolean isColor_reverse()
	{
		return color_reverse;
	}
	public void setColor_reverse(boolean color_reverse)
	{
		this.color_reverse = color_reverse;
	}
	
}
