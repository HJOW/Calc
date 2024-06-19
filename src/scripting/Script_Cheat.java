package scripting;

import main_classes.MessageShowable;

public class Script_Cheat implements Helpable
{
	private MessageShowable superComponent;
	public Script_Cheat(MessageShowable superComponent)
	{
		this.superComponent = superComponent;
	}
	public void message()
	{
		superComponent.message();
	}
	public void message(String str)
	{
		superComponent.message(str);
	}
	public void message_bar()
	{
		superComponent.message_bar();
	}
	public String[] list()
	{
		String[] result = new String[6];
		result[0] = "subtractor";
		result[1] = "hypersolution";
		result[2] = "allzero";
		result[3] = "showall";
		result[4] = "extreme";
		result[5] = "multy";
				
		return result;
	}
	public void showAll()
	{
		if(superComponent != null)
		{
			if(superComponent instanceof Cheatable)
				((Cheatable) superComponent).showAll();
		}
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
		String[] cheats = list();
		switch(lang)
		{
			case 1:
				message("===== Cheat =====");
				message("Cheat 를 사용하면 인증 모드가 해제됩니다.");
				message();
				message("사용 방법");
				message("cheat.c(\'<이 안에 사용할 Cheat를 입력합니다.>\')");
				message("또 다른 사용 방법");
				message("cht(\'<이 안에 사용할 Cheat를 입력합니다.>\')");
				message();
				message("예 : cheat.c('showall')");
				message("예 : cht('subtractor')");
				break;
			default:
				message("===== Cheat =====");
				message("Authorize mode will be cancelled if you use cheat.");
				message();
				message("How to use");
				message("cheat.c(\'<Input cheat keyword here>\')");
				message("Another method");
				message("cht(\'<Input cheat keyword here>\')");
				message();
				message("Example : cheat.c('showall')");
				message("Example : cht('subtractor')");	
				break;
		}
		message("===== Cheat list =====");
		for(int i=0; i<cheats.length; i++)
		{
			message(cheats[i]);
		}
		message_bar();
	}
	public void c(String cheat)
	{
		try
		{
			if(superComponent instanceof Cheatable)
				((Cheatable) superComponent).cheatApply(cheat);
		} 
		catch (Exception e)
		{
			
		}
	}
	public void cheat(String cheat)
	{
		try
		{
			if(superComponent instanceof Cheatable)
				((Cheatable) superComponent).cheatApply(cheat);
		} 
		catch (Exception e)
		{
			
		}
	}
	public void cheat()
	{
		if(superComponent instanceof Cheatable)
			((Cheatable) superComponent).cheatApply();
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		Helpable helpables = new Script_Cheat(showable);
		return helpables;
	}
	@Override
	public String title()
	{
		return "cheat";
	}
}
