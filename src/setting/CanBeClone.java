package setting;
import java.io.Serializable;

public interface CanBeClone extends java.lang.Cloneable, Serializable
{
	public Object clone();
}
