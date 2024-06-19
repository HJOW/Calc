package main_classes;

import scripting.ScriptManager;

public interface MainGame extends Openable, Controllable, MessageShowable
{
	public ScriptManager getScriptEngine();
}
