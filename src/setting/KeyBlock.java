package setting;

import java.io.Serializable;

public class KeyBlock implements Serializable, Cloneable
{
	private static final long serialVersionUID = 4724999420216104586L;
	public char[] blocks;
	public KeyBlock()
	{
		blocks = new char[5];
	}
	public KeyBlock(char[] chars)
	{
		this();
		for(int i=0; i<5; i++)
		{
			blocks[i] = chars[i];
		}		
	}
	public KeyBlock(String str)
	{
		this(str.toCharArray());
	}
	public char[] getBlocks()
	{
		return blocks;
	}
	public void setBlocks(char[] blocks)
	{
		this.blocks = blocks;
	}
	public int values()
	{
		int result = 0;
		for(int i=0; i<blocks.length; i++)
		{
			result = result + (int) blocks[i];
		}
		return result;
	}
	public KeyBlock clone()
	{
		KeyBlock newOne = new KeyBlock();
		newOne.blocks = new char[5];
		for(int i=0; i<blocks.length; i++)
		{
			newOne.blocks[i] = this.blocks[i];
		}
		return newOne;
	}
}