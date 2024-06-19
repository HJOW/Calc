package browser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.event.HyperlinkListener;

public interface IsBrowser
{
	public Component getComponent();
	public void setPage(String str) throws IOException;
	public void addHyperlinkListener(HyperlinkListener listener);
	public void setBackground(Color color);
	public void setForeground(Color color);
	public void setFont(Font font);
	public void setText(String str);
}
