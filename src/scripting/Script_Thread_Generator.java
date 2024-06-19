package scripting;

import main_classes.MessageShowable;
import main_classes.ThreadAccumulate;

public class Script_Thread_Generator implements Helpable
{
	private ScriptActor manager;
	private MessageShowable cons;
	public Script_Thread_Generator(ScriptActor manager, MessageShowable cons)
	{
		this.manager = manager;
		this.cons = cons;
	}
	public Script_Thread generate(String str)
	{
		Script_Thread newThread = new Script_Thread(manager, str);
		ThreadAccumulate.add(newThread);
		return newThread;
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
				cons.message("===== thread_generator commands =====");
				cons.message();
				cons.message("generate(_i) : _i 스크립트를 반복해서 실행하는 쓰레드 객체를 반환합니다.");
				cons.message("  이 객체에는 쓰레드를 실행하는 start(), 쓰레드를 멈추는 offThread() 메소드가 있습니다.");
				cons.message();
				cons.message_bar();
				break;
			default:
				cons.message("===== thread_generator commands =====");
				cons.message();
				cons.message("generate(_i) : Return new thread object which runs _i script infinitely.");
				cons.message("  There are start(), and offThread() method in this object.");
				cons.message();
				cons.message_bar();
				//showable.message("I\'m sorry that there is only korean helps for chat commands.");
				//help(1);
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Thread_Generator(manager, showable);
	}
	@Override
	public String title()
	{
		return "thread_generator";
	}
}
