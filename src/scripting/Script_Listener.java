package scripting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Script_Listener implements ActionListener, ItemListener, WindowListener, MouseListener, MouseMotionListener, ListSelectionListener
{
	public Script_Listener(ScriptActor manager, Script_Console console)
	{
		this.manager = manager;
		this.console = console;
	}
	
	private ScriptActor manager;
	private Script_Console console;
	public String _valueChanged = "", _mouseClicked = "", _mouseEntered = "", _mouseExited = "", _mousePressed = "", _mouseReleased = "", _windowActivated = "", _windowClosed = "", _windowClosing = "", _windowDeactivated = "";
	public String _windowDeiconified = "", _windowIconified = "", _windowOpened = "", _itemStateChanged = "", _actionPerformed = "", _mouseDragged = "", _mouseMoved = "";

	@Override
	public void valueChanged(ListSelectionEvent e)
	{		
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_valueChanged);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseClicked);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseEntered);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseExited);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mousePressed);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseReleased);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowActivated);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowClosed);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowClosing);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowDeactivated);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowDeiconified);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowIconified);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_windowOpened);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_itemStateChanged);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_actionPerformed);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseDragged);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		try
		{
			manager.act("event_x = " + String.valueOf(e.getX()));
			manager.act("event_y = " + String.valueOf(e.getY()));
			manager.act("event_source = " + "\"" + e.toString() + "\"");
			manager.act(_mouseMoved);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
			console.message(e1.getMessage());
		}
		
	}
}
