package scripting;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import lang.Language;
import main_classes.MessageShowable;
import setting.Setting;

public class Script_Terminal
{
	private Setting sets;
	private MessageShowable showable;
	public Script_Terminal(Setting sets, MessageShowable showable)
	{
		this.sets = sets;
		this.showable = showable;
	}
	public void ls()
	{
		File file = new File(NormalScriptManager.path());
		File[] files = file.listFiles();
		String results = "";
		
		for(File f : files)
		{
			results = results + f.getName() + "\t" + f.length() + "\n";
		}
		
		showable.message(results);
	}	
	public void dir()
	{
		ls();
	}
	public void cd()
	{
		ls();
	}
	public void cd(String com)
	{
		StringTokenizer pathToken = new StringTokenizer(NormalScriptManager.path(), sets.getSeparator());
		if(com.equalsIgnoreCase("../"))
		{
			String newPath = "";
			int tokens = pathToken.countTokens();
			for(int i=0; i<tokens - 1; i++)
			{
				newPath = newPath + pathToken.nextToken() + sets.getSeparator();
			}
			NormalScriptManager.setPath(newPath);
			return;
		}
		else if(com.equalsIgnoreCase(sets.getSeparator()) || com.equalsIgnoreCase("/"))
		{
			String newPath = pathToken.nextToken();
			NormalScriptManager.setPath(newPath);
			return;
		}
		for(char i='a'; i<='z'; i++)
		{
			if((sets.getOs().startsWith("Windows") || sets.getOs().startsWith("windows") || sets.getOs().startsWith("WINDOWS")) && com.equalsIgnoreCase(String.valueOf(i) + ":"))
			{
				NormalScriptManager.setPath(String.valueOf(i) + ":" + sets.getSeparator());
				return;
			}
		}
		
		NormalScriptManager.setPath(NormalScriptManager.path() + com);
	}
	public void mkdir(String name)
	{
		File file = new File(NormalScriptManager.path() + name);
		file.mkdir();
	}
	public void rm(String name)
	{
		File file = new File(NormalScriptManager.path() + name);
		if(file.delete())
		{
			showable.message(sets.getLang().getText(Language.UNINSTALL_DELETED));
		}
		else
		{
			showable.message(sets.getLang().getText(Language.UNINSTALL_ERROR));
		}
	}
	public void run(String name) throws IOException
	{
		Runtime.getRuntime().exec(NormalScriptManager.path() + name);
	}
	public void exit()
	{
		System.exit(0);
	}
}
