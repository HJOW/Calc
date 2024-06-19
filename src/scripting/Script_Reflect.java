package scripting;

public class Script_Reflect
{
	private Script_Console console;
	public Script_Reflect(Script_Console console)
	{
		this.console = console;
	}
	public Class<?> forName(String className)
	{
		try
		{
			return Class.forName(className);
		} 
		catch (ClassNotFoundException e)
		{
			console.message(e.getMessage());
			return null;
		}
	}
}
