package scripting;

import main_classes.CalcWindow;
import main_classes.MessageShowable;

public class Script_Console implements MessageShowable, Helpable
{
	private MessageShowable superComponent = null;
	public Script_Console(MessageShowable console)
	{
		this.superComponent = console;
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
				message("===== console commands =====");
				message();
				message("message(_i) : 문자열 _i를 출력합니다.");
				message("alert(_i) : 메시지 대화상자를 열어 문자열 _i 를 보여줍니다.");
				message("version() : 스크립트 실행기의 버전을 출력합니다.");
				message("openConsole() : 메시지 창을 띄웁니다.");
				message();
				message_bar();
				break;
			default:
				message("===== console commands =====");
				message();
				message("message(_i) : print _i.");
				message("alert(_i) : open message dialog and show _i.");
				message("version() : Print script manager version.");
				message("openConsole() : Open message window.");
				message();
				message_bar();
				break;
		}
	}
	public String version()
	{
		String results = "CalcScript " + CalcWindow.getVersionString();
		message(results);
		return results;
	}
	public String version(boolean detailed)
	{
		String results = "CalcScript " + CalcWindow.getVersionString(detailed);
		message(results);
		return results;
	}
	@Override
	public void message()
	{
		if(superComponent != null)
			superComponent.message();
		else
			System.out.println();
	}
	@Override
	public void message_bar()
	{
		if(superComponent != null)
			superComponent.message_bar();
		else
			System.out.println("-------------------------------------------");
	}

	@Override
	public void message(String str)
	{
		if(superComponent != null)
			superComponent.message(str);
		else
			System.out.println(str);
	}
	public void message(Object str)
	{
		if(superComponent != null)
			superComponent.message(String.valueOf(str));
		else
			System.out.println(str);
	}

	@Override
	public void alert(String str)
	{
		if(superComponent != null)
			superComponent.alert(str);
		else
			System.out.println(str);
	}
	public void alert(Object str)
	{
		if(superComponent != null)
			superComponent.alert(str.toString());
		else
			System.out.println(str);
	}

	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Console(showable);
	}

	@Override
	public String title()
	{
		return "console";
	}

	@Override
	public void openConsole()
	{
		superComponent.openConsole();		
	}
}
