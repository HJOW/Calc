package scripting;

import main_classes.MessageShowable;

public interface Cheatable extends MessageShowable
{
	public void showAll();
	public void cheatApply();
	public void cheatApply(String cheat) throws Exception;
}
