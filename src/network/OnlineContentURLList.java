package network;

import java.beans.XMLDecoder;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import main_classes.CalcWindow;
import setting.Key;
import setting.KeyBlock;
import setting.SaveInt;
import setting.Setting;
import ai_algorithm.AI_Algorithm;

public class OnlineContentURLList implements Serializable
{
	private static final long serialVersionUID = -5277401835999675072L;
	private String name = "";
	private SaveInt ver_main, ver_sub1, ver_sub2;
	private Vector<String> ai_contents;
	private Vector<String> setting_contents;
	private Vector<Key> ultimate_keys;
	private Vector<Key> abandoned_keys;
	public OnlineContentURLList()
	{
		ai_contents = new Vector<String>();
		setting_contents = new Vector<String>();
		ultimate_keys = new Vector<Key>();
		abandoned_keys = new Vector<Key>();
		ver_main = new SaveInt(CalcWindow.version_main);
		ver_sub1 = new SaveInt(CalcWindow.version_sub_1);
		ver_sub2 = new SaveInt(CalcWindow.version_sub_2);
	}
	public static OnlineContentURLList convert(Vector<String> strs)
	{
		OnlineContentURLList newOne = new OnlineContentURLList();
		StringTokenizer stokens;
		String firsts = null, seconds = null;
		int nums = 0;
		for(int i=0; i<strs.size(); i++)
		{
			//System.out.println(strs.get(i));
			try
			{
				stokens = new StringTokenizer(strs.get(i), "|");
				firsts = stokens.nextToken();
				seconds = stokens.nextToken();
				nums = Integer.parseInt(firsts);
				StringTokenizer blockToken;
				Key newKey;
				Vector<KeyBlock> blocks;
				int sizes;
				KeyBlock[] blockArray;
				switch(nums)
				{
					case 0:
						newOne.setName(seconds);
						break;
					case 10:
						newOne.setVer_main(new SaveInt(Integer.parseInt(seconds)));
						break;
					case 11:
						newOne.setVer_sub1(new SaveInt(Integer.parseInt(seconds)));
						break;
					case 12:
						newOne.setVer_sub2(new SaveInt(Integer.parseInt(seconds)));
						break;
					case 20:
						newOne.getAi_contents().add(seconds);
						break;
					case 30:
						newOne.getSetting_contents().add(seconds);
						break;
					case 40:
						blockToken = new StringTokenizer(decrypt(seconds), "-");
						newKey = new Key();
						blocks = new Vector<KeyBlock>();
						sizes = blockToken.countTokens();
						for(int j=0; j<sizes; j++)
						{
							KeyBlock newKeyBlock = new KeyBlock(blockToken.nextToken());
							blocks.add(newKeyBlock);
						}
						blockArray = new KeyBlock[blocks.size()];
						for(int j=0; j<blockArray.length; j++)
						{
							blockArray[j] = blocks.get(j);
						}
						newKey.setChars(blockArray);
						newOne.getUltimate_keys().add(newKey);
						break;
					case 41:
						blockToken = new StringTokenizer(decrypt(seconds), "-");
						newKey = new Key();
						blocks = new Vector<KeyBlock>();
						sizes = blockToken.countTokens();
						for(int j=0; j<sizes; j++)
						{
							KeyBlock newKeyBlock = new KeyBlock(blockToken.nextToken());
							blocks.add(newKeyBlock);
						}
						blockArray = new KeyBlock[blocks.size()];
						for(int j=0; j<blockArray.length; j++)
						{
							blockArray[i] = blocks.get(i);
						}
						newKey.setChars(blockArray);
						newOne.getAbandoned_keys().add(newKey);
						break;
				}
			} 
			catch (NoSuchElementException e)
			{
				return null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return newOne;
	}
	public static String crypt(String str)
	{
		char[] chars = str.toCharArray();
		char changes = '\0';
		for(int i=0; i<chars.length; i++)
		{
			switch(chars[i])
			{
				case 'A':
					changes = '5';
					break;
				case 'E':
					changes = '9';
					break;
				case 'I':
					changes = '8';
					break;
				case 'O':
					changes = '6';
					break;
				case 'U':
					changes = '4';
					break;
				case 'B':
					changes = '2';
					break;
				case 'C':
					changes = '3';
					break;
				case 'D':
					changes = '7';
					break;
				case 'F':
					changes = '0';
					break;
				default:
					changes = chars[i];
			}
			chars[i] = changes;
		}
		return new String(chars);
	}
	public static String decrypt(String str)
	{
		char[] chars = str.toCharArray();
		char changes = '\0';
		for(int i=0; i<chars.length; i++)
		{
			switch(chars[i])
			{
				case '5':
					changes = 'A';
					break;
				case '9':
					changes = 'E';
					break;
				case '8':
					changes = 'I';
					break;
				case '6':
					changes = 'O';
					break;
				case '4':
					changes = 'U';
					break;
				case '2':
					changes = 'B';
					break;
				case '3':
					changes = 'C';
					break;
				case '7':
					changes = 'D';
					break;
				case '0':
					changes = 'F';
					break;
				default:
					changes = chars[i];
			}
			chars[i] = changes;
		}
		return new String(chars);
	}	
	public AI_Algorithm readAI(int index) throws Exception
	{
		URL url = new URL(ai_contents.get(index));
		InputStream inp = url.openStream();
		XMLDecoder decoder = new XMLDecoder(inp);
		AI_Algorithm result = (AI_Algorithm) decoder.readObject();
		return result;
	}
	public Setting readSetting(int index) throws Exception
	{
		URL url = new URL(setting_contents.get(index));
		InputStream inp = url.openStream();
		XMLDecoder decoder = new XMLDecoder(inp);
		Setting result = (Setting) decoder.readObject();
		return result;
	}
	public Vector<String> getAi_contents()
	{
		return ai_contents;
	}
	public void setAi_contents(Vector<String> ai_contents)
	{
		this.ai_contents = ai_contents;
	}
	public Vector<String> getSetting_contents()
	{
		return setting_contents;
	}
	public void setSetting_contents(Vector<String> setting_contents)
	{
		this.setting_contents = setting_contents;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public SaveInt getVer_main()
	{
		return ver_main;
	}
	public void setVer_main(SaveInt ver_main)
	{
		this.ver_main = ver_main;
	}
	public SaveInt getVer_sub1()
	{
		return ver_sub1;
	}
	public void setVer_sub1(SaveInt ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}
	public SaveInt getVer_sub2()
	{
		return ver_sub2;
	}
	public void setVer_sub2(SaveInt ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}
	public Vector<Key> getUltimate_keys()
	{
		return ultimate_keys;
	}
	public void setUltimate_keys(Vector<Key> ultimate_keys)
	{
		this.ultimate_keys = ultimate_keys;
	}
	public Vector<Key> getAbandoned_keys()
	{
		return abandoned_keys;
	}
	public void setAbandoned_keys(Vector<Key> abandoned_keys)
	{
		this.abandoned_keys = abandoned_keys;
	}
}
