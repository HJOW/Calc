package ai_algorithm;

import java.io.Serializable;

public abstract class AI_Algorithm_Data implements Serializable
{	
	private static final long serialVersionUID = 6263501128472868441L;
	public String name = "";
	public abstract int getType();
	
	public static final int NUMBER = 0;
	public static final int STRING = 1;
}
