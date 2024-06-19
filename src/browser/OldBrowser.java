package browser;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import lang.Language;
import setting.Setting;

public class OldBrowser extends JEditorPane implements IsBrowser, HyperlinkListener
{
	private static final long serialVersionUID = 8051815449356272555L;
	private List<String> pageList;
	private BrowserFrame supers;
	private Setting sets;
	public OldBrowser(Setting sets)
	{
		super();
		pageList = new Vector<String>();
		this.sets = sets;
	}
	@Override
	public Component getComponent()
	{
		return this;
	}
	public void showPage(URL pageURL, boolean addToList)
	{		
		try
		{
			URL currentURL = getPage();
			setPage(pageURL);
			URL newURL = getPage();
			if(addToList)
			{
				int listSize = pageList.size();
				if(listSize > 0)
				{
					int pageIndex = pageList.indexOf(currentURL.toString());
					if(pageIndex < listSize - 1)
					{
						for(int i=listSize - 1; i > pageIndex; i--)
						{
							pageList.remove(i);
						}
					}
				}
				pageList.add(newURL.toString());
			}
			supers.setAddressText(newURL.toString());
			updateButtons();
		} 
		catch (NullPointerException e)
		{
			showError(sets.getLang().getText(Language.ERROR), e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			showError(sets.getLang().getText(Language.ERROR), e.getMessage());
		}
	}	
	public void actionGo()
	{
		String getURL = supers.getAddressText();
		if(getURL.equals("about:blank"))
		{
			setText("");
		}
		else
		{
			URL verifiedURL = verifyURL(getURL);
			if(verifiedURL != null)
			{
				showPage(verifiedURL, true);
			}
			else
			{
				showError(sets.getLang().getText(Language.ERROR), null);
			}
		}
	}
	public void actionBack()
	{
		URL currentURL = getPage();
		int pageIndex = pageList.indexOf(currentURL.toString());
		try
		{
			showPage(new URL(pageList.get(pageIndex - 1)), false);
		} 
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void actionForward()
	{
		URL currentURL = getPage();
		int pageIndex = pageList.indexOf(currentURL.toString());
		try
		{
			showPage(new URL(pageList.get(pageIndex + 1)), false);
		} 
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	public void clear()
	{
		setText("");
	}
	public void showError(String message, String detail)
	{
		showError(message, detail, false);
	}
	public void showError(String message, String detail, boolean showDialog)
	{
		String additional = "";
		if(detail != null) additional = " : " + detail;
		//System.out.println(supers.getLanguage().getErrors() + " : " + message + additional);
		//if(showDialog) JOptionPane.showMessageDialog(supers.getContentPane(), message, "", JOptionPane.ERROR_MESSAGE);
		//showMessage(supers.getLanguage().getErrors() + " : " + message);
		supers.error(sets.getLang().getText(Language.ERROR) + additional);
	}
	public void showMessage(String message)
	{
		supers.showMessage(message);
	}
	@Override
	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		HyperlinkEvent.EventType eventType = e.getEventType();
		if(eventType == HyperlinkEvent.EventType.ACTIVATED)
		{
			if(e instanceof HTMLFrameHyperlinkEvent)
			{
				HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) e;
				HTMLDocument document = (HTMLDocument) getDocument();
				document.processHTMLFrameHyperlinkEvent(linkEvent);
			}
			else
			{
				showPage(e.getURL(), true);
			}
		}		
	}	
	private URL verifyURL(String url)
	{
		URL verifiedURL = null;
		try
		{
			verifiedURL = new URL(url);
		} 
		catch (MalformedURLException e)
		{
			try
			{
				verifiedURL = new URL("http://" + url);
			} 
			catch (MalformedURLException e1)
			{
				return null;
			}
		}
		return verifiedURL;
	}
	private void updateButtons()
	{
		String currentPage = null;
		int currentIndex = 0;
		try
		{
			currentPage = getPage().toString();
			currentIndex = pageList.indexOf(currentPage);
		} 
		catch (Exception e)
		{
			currentPage = null;
			currentIndex = -1;
		}
		// Make Vector to Array of pages
		String[] newList = new String[pageList.size()];
		for(int i=0; i<newList.length; i++)
		{
			newList[i] = pageList.get(i);
		}
		supers.updateButtons(newList, currentIndex);
	}
}
