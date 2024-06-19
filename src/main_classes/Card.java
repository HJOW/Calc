package main_classes;

import java.io.Serializable;

import setting.Setting;

public abstract class Card implements Serializable, Cloneable
{
	private static final long serialVersionUID = -4899266731951918262L;
	public static final int STANDARD = 0;
	public static final int JOKER = 1;
	public abstract String toBasicString(String separator);
	public abstract int getType();
	public abstract Card clone();
	public abstract int grade(Setting set);
}
