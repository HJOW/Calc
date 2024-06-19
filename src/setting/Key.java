package setting;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.Vector;

import main_classes.CalcWindow;
import main_classes.RunManager;
import network.OnlineContentURLList;

public class Key implements Serializable, Cloneable
{
	private static final long serialVersionUID = 6711110577650857699L;
	private KeyBlock[] chars;
	public Key()
	{
		chars = new KeyBlock[5];
		char[] temp_chars = new char[5];
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				temp_chars[j] = (char) 1;
			}
			chars[i] = new KeyBlock(temp_chars);
		}
	}	
	
	public String generate_master(Setting sets, long code)
	{
		if(CalcWindow.getAuthorizedCode(code) >= 100)
		{
			char[] charvalues = new char[50];
			boolean loop_fin = true;
			while(loop_fin)
			{
				for(int i=0; i<charvalues.length; i++)
				{
					charvalues[i] = (char) ((int) (Math.random() * 10 + '0'));
				}
				if(check_master(sets, charvalues))
				{
					loop_fin = false;
				}
			}
			return new String(charvalues);
		}	
		else return null;
	}
	private boolean check_master(Setting sets, char[] readed_char)
	{
		long readed_long = 0;
		long readed_3 = 0;
		long readed_5 = 0;
		long readed_7 = 0;
		long readed_11 = 0;
		int primed = 0;
		long accms = 0;
		for(int i=0; i<readed_char.length; i++)
		{
			readed_long = readed_long + (readed_char[i] - '0');
			if(i % 3 == 0)
				readed_3 = readed_3 + (readed_char[i] - '0');
			if(i % 5 == 0)
				readed_5 = readed_5 + (readed_char[i] - '0');
			if(i % 7 == 0)
				readed_7 = readed_7 + (readed_char[i] - '0');
			if(i % 11 == 0)
				readed_11 = readed_11 + (readed_char[i] - '0');
		}
		for(int i=0; i<chars.length; i++)
		{
			for(int j=0; j<chars[i].getBlocks().length; j++)
			{
				readed_long = readed_long + (int) (chars[i].getBlocks()[j] - '0');
				if(i % 3 == 0)
					readed_3 = readed_3 + ((int) chars[i].getBlocks()[j] - '0');
				if(i % 5 == 0)
					readed_5 = readed_5 + ((int) chars[i].getBlocks()[j] - '0');
				if(i % 7 == 0)
					readed_7 = readed_7 + ((int) chars[i].getBlocks()[j] - '0');
				if(i % 11 == 0)
					readed_11 = readed_11 + ((int) chars[i].getBlocks()[j] - '0');
			}
		}
		if(readed_char.length < 40) return false;
		else
		{
			if(isPrime(readed_long, false)) primed++;
			if(isPrime(readed_3, false)) primed++;
			if(isPrime(readed_5, false)) primed++;
			if(isPrime(readed_7, false)) primed++;
			if(isPrime(readed_11, false)) primed++;
			for(int i=0; i<chars.length; i++)
			{
				for(int j=0; j<chars[i].blocks.length; j++)
				{
					accms = accms + chars[i].blocks[j];
				}
			}
			if(isPrime(accms, false)) primed++;
			if(isPrime(RunManager.getNameCode(readed_char)))
			{
				primed = primed + 1;
			}
			if(isPrime(readed_char[0]))
			{
				primed = primed + 1;
			}
			//System.out.println(primed);
			if(isPrime(primed, false) && primed >= 5 && accepted(sets))
			{
				System.out.println("Master checked");
				return true;
			}
			else return false;
		}
	}
	public boolean accepted_master(Setting sets)
	{
		try
		{
			if(accept_net(sets))
			{
				File keyFile = new File(sets.getDefault_path() + "key.ckey");
				BufferedReader bfreader;
				FileReader freader;
				String readed = "";
				char[] readed_char;
				
				if(keyFile.exists())
				{
					freader = new FileReader(keyFile);
					bfreader = new BufferedReader(freader);
					readed = bfreader.readLine();
					readed_char = readed.toCharArray();					
					try
					{
						bfreader.close();
					}
					catch(Exception e)
					{
						
					}
					try
					{
						freader.close();
					}
					catch(Exception e)
					{
						
					}
					return check_master(sets, readed_char);
				}
				else
				{
					return false;
				}
			}
			else return false;
		} 
		catch (Exception e)
		{
			return false;
		}
	}
	public boolean accepted(Setting sets)
	{
		long[] codes = new long[5];
		long calcs = 0;
		int zero_check = 0;
		int prime_checks = 0;
		char[] temp_chars = new char[5];
		try
		{
			for(int i=0; i<codes.length; i++)
			{
				zero_check = 0;
				temp_chars = chars[i].blocks;
				for(int j=0; j<5; j++)
				{
					zero_check += (int) temp_chars[j];
				}
				if(zero_check <= 5) return false;
			}
		} 
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		catch (Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
			return false;
		}
		for(int i=0; i<codes.length; i++)
		{
			codes[i] = 0;
		}
		for(int i=0; i<codes.length; i++)
		{
			temp_chars = chars[i].blocks;
			for(int j=0; j<5; j++)
			{
				calcs = ((int) temp_chars[j]);
				for(int k=1; k<j; k++)
				{
					calcs = calcs * 10;
				}
				codes[i] += calcs;
			}
		}
		calcs = 0;
		for(int i=0; i<codes.length; i++)
		{
			calcs += codes[i];
		}
		
		boolean result = false;
		if(isPrime(calcs)) result = true;
		else return false;
		
		prime_checks = 0;
		for(int i=0; i<codes.length; i++)
		{
			if(isPrime(codes[i]))
			{
				prime_checks++;
			}
		}
		
		if(result && prime_checks >= 4)
		{
			result = true;
		}
		else result = false;
		
		return result;
	}
	
	public boolean accept_net(Setting sets) throws Exception
	{
		OnlineContentURLList onlineConts = null;
		XMLDecoder oin = null;
		URL onlineURL = null;
		InputStreamReader inp = null;
		BufferedReader bfr = null;
		try
		{			
			onlineURL = new URL(sets.getNotice_url() + "urls.curt");
			inp = new InputStreamReader(onlineURL.openStream());
			bfr = new BufferedReader(inp);
			Vector<String> vt = new Vector<String>();
			String readed = "";
			while(true)
			{
				readed = bfr.readLine();
				if(readed == null) break;
				vt.add(readed);
			}
			//System.out.println(readed);
			onlineConts = OnlineContentURLList.convert(vt);
			if(onlineConts == null) throw new NullPointerException();
		} 
		catch (Exception e)
		{
			try
			{
				inp.close();
				bfr.close();
			}
			catch(Exception e1)
			{
				
			}
			try
			{
				onlineURL = new URL(sets.getNotice_url() + "urls.curl");
				oin = new XMLDecoder(onlineURL.openStream());
				onlineConts = (OnlineContentURLList) oin.readObject();
				if(onlineConts == null) throw new NullPointerException();
			} 
			catch (Exception e1)
			{
				try
				{
					try
					{
						oin.close();
						inp.close();
						bfr.close();
					}
					catch(Exception e2)
					{
						
					}					
					onlineURL = new URL(sets.getNotice_url2() + "urls.curt");
					inp = new InputStreamReader(onlineURL.openStream());
					bfr = new BufferedReader(inp);
					Vector<String> vt = new Vector<String>();
					String readed = "";
					while(true)
					{
						readed = bfr.readLine();
						if(readed == null) break;
						vt.add(readed);
					}
					onlineConts = OnlineContentURLList.convert(vt);
					if(onlineConts == null) throw new NullPointerException();
				} 
				catch(Exception e2)
				{
					try
					{
						try
						{
							oin.close();
							inp.close();
							bfr.close();
						}
						catch(Exception e3)
						{
							
						}
						onlineURL = new URL(sets.getNotice_url2() + "urls.curl");
						oin = new XMLDecoder(onlineURL.openStream());
						onlineConts = (OnlineContentURLList) oin.readObject();
					} 
					catch(Exception e3)
					{
						try
						{
							oin.close();
							inp.close();
							bfr.close();
						}
						catch(Exception e4)
						{
							
						}
						return false;
					}
				}
			}			
		}
		try
		{
			oin.close();			
		}
		catch(Exception e)
		{
			
		}
		try
		{
			bfr.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			inp.close();
		}
		catch(Exception e)
		{
			
		}
		boolean result = false;
		if(onlineConts == null) return false;
		if(onlineConts.getUltimate_keys().size() == 0) return false;
		for(int i=0; i<onlineConts.getUltimate_keys().size(); i++)
		{
			//System.out.print(onlineConts.getUltimate_keys() + " ");
			//System.out.println(this + " " + onlineConts.getUltimate_keys().get(i).equals(this));
			if(onlineConts.getUltimate_keys().get(i).equals(this))
			{
				result = true;
				break;
			}
		}
		String lines = null;
		Vector<String> urlList = new Vector<String>();
		if(! result)
		{
			onlineURL = new URL(sets.getNotice_url() + "calc_key.key");
			try
			{
				inp = new InputStreamReader(onlineURL.openStream());				
				bfr = new BufferedReader(inp);
				while(true)
				{
					lines = bfr.readLine();
					if(lines == null) break;
					lines = RunManager.r65279(lines);
					urlList.add(lines);
				}
				
			}
			catch(Exception e)
			{
				try
				{
					bfr.close();
				}
				catch(Exception e1)
				{
					
				}
				try
				{
					inp.close();
				}
				catch(Exception e1)
				{
					
				}
				onlineURL = new URL(sets.getNotice_url2() + "calc_key.key");
				try
				{
					inp = new InputStreamReader(onlineURL.openStream());				
					bfr = new BufferedReader(inp);
					while(true)
					{
						lines = bfr.readLine();
						if(lines == null) break;
						lines = RunManager.r65279(lines);
						urlList.add(lines);
					}
				}
				catch(Exception e2)
				{
					
				}
				finally
				{
					try
					{
						bfr.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						inp.close();
					}
					catch(Exception e1)
					{
						
					}
				}
			}
			finally
			{
				try
				{
					bfr.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					inp.close();
				}
				catch(Exception e)
				{
					
				}
			}
			char[] emailCheck;
			if(urlList.size() >= 1)
			{
				long urlValue = 0;
				for(int i=0; i<urlList.size(); i++)
				{
					if(urlList.get(i) != null)
					{
						try
						{
							urlValue = Long.parseLong(urlList.get(i));
							if(number_accepted(urlValue))
							{
								result = true;
							}
							break;
						}
						catch(Exception e3)
						{
							try
							{
								urlValue = RunManager.getNameCode(urlList.get(i)) * (RunManager.getNameCode(urlList.get(i)) + 1);
								if(number_accepted(urlValue))
								{
									emailCheck = urlList.get(i).toCharArray();
									for(int j=0; j<emailCheck.length; j++)
									{
										if(emailCheck[j] == '@' && j >= 1 && j <= emailCheck.length - 1)
										{
											result = true;
											break;
										}
									}									
								}
								break;
							} 
							catch (Exception e4)
							{
								
							}
						}
					}
				}
			}
		}
		
		if(result)
		{
			if(result && onlineConts.getAbandoned_keys().size() != 0)
			{			
				for(int i=0; i<onlineConts.getAbandoned_keys().size(); i++)
				{
					if(onlineConts.getAbandoned_keys().get(i).equals(this))
					{
						result = false;
						break;
					}
				}
			}
		}
		
		
		lines = null;
		urlList.clear();
		if(result)
		{
			onlineURL = new URL(sets.getNotice_url() + "calc_abandoned.key");
			try
			{
				inp = new InputStreamReader(onlineURL.openStream());				
				bfr = new BufferedReader(inp);
				while(true)
				{
					lines = bfr.readLine();
					if(lines == null) break;
					lines = RunManager.r65279(lines);
					urlList.add(lines);
				}
				
			}
			catch(Exception e)
			{
				try
				{
					bfr.close();
				}
				catch(Exception e1)
				{
					
				}
				try
				{
					inp.close();
				}
				catch(Exception e1)
				{
					
				}
				onlineURL = new URL(sets.getNotice_url2() + "calc_abandoned.key");
				try
				{
					inp = new InputStreamReader(onlineURL.openStream());				
					bfr = new BufferedReader(inp);
					while(true)
					{
						lines = bfr.readLine();
						if(lines == null) break;
						lines = RunManager.r65279(lines);
						urlList.add(lines);
					}
				}
				catch(Exception e2)
				{
					
				}
				finally
				{
					try
					{
						bfr.close();
					}
					catch(Exception e1)
					{
						
					}
					try
					{
						inp.close();
					}
					catch(Exception e1)
					{
						
					}
				}
			}
			finally
			{
				try
				{
					bfr.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					inp.close();
				}
				catch(Exception e)
				{
					
				}
			}
			
			if(urlList.size() >= 1)
			{
				long urlValue = 0;
				for(int i=0; i<urlList.size(); i++)
				{
					if(urlList.get(i) != null)
					{
						try
						{
							urlValue = Long.parseLong(urlList.get(i));
							if(number_accepted(urlValue))
							{
								result = false;
							}
							break;
						}
						catch(Exception e3)
						{
							
						}
					}
				}
			}
		}
		
		return result;
	}
	protected long number_get(long password)
	{
		long comp = 0;
		for(int i=0; i<chars.length; i++)
		{
			comp = comp + chars[i].values();
		}
		comp = comp + 294;
		comp = comp * 948;
		comp = comp - 211;
		if(CalcWindow.getAuthorizedCode(password) <= 0) return -1;
		return comp;
	}
	protected boolean number_accepted(long value)
	{		
		return value == number_get(1937283 + 1001008);
	}
	public boolean abandoned(Setting set) throws Exception
	{
		OnlineContentURLList onlineConts = null;
		URL onlineURL = null;
		XMLDecoder oin = null;	
		InputStreamReader inp = null;
		BufferedReader bfr = null;
		
		try
		{		
			onlineURL = new URL(set.getNotice_url() + "urls.curt");
			inp = new InputStreamReader(onlineURL.openStream());
			bfr = new BufferedReader(inp);
			Vector<String> vt = new Vector<String>();
			String readed = "";
			while(true)
			{
				readed = bfr.readLine();
				if(readed == null) break;
				vt.add(readed);
			}
			onlineConts = OnlineContentURLList.convert(vt);
		} 
		catch (Exception e)
		{
			try
			{
				inp.close();
				bfr.close();
			}
			catch(Exception e1)
			{
				
			}
			try
			{
				onlineURL = new URL(set.getNotice_url() + "urls.curl");
				oin = new XMLDecoder(onlineURL.openStream());
				onlineConts = (OnlineContentURLList) oin.readObject();
			} 
			catch (Exception e1)
			{
				try
				{
					oin.close();
					inp.close();
					bfr.close();
				}
				catch(Exception e2)
				{
					
				}
				try
				{		
					onlineURL = new URL(set.getNotice_url2() + "urls.curt");
					inp = new InputStreamReader(onlineURL.openStream());
					bfr = new BufferedReader(inp);
					Vector<String> vt = new Vector<String>();
					String readed = "";
					while(true)
					{
						readed = bfr.readLine();
						if(readed == null) break;
						vt.add(readed);
					}
					onlineConts = OnlineContentURLList.convert(vt);
				} 
				catch(Exception e3)
				{
					try
					{
						oin.close();
						inp.close();
						bfr.close();
					}
					catch(Exception e2)
					{
						
					}
					try
					{
						onlineURL = new URL(set.getNotice_url() + "urls.curl");
						oin = new XMLDecoder(onlineURL.openStream());
						onlineConts = (OnlineContentURLList) oin.readObject();
					}
					catch(Exception e4)
					{
						return false;
					}
				}
			}
		}
		try
		{
			oin.close();
			inp.close();
			bfr.close();
		}
		catch(Exception e2)
		{
			
		}
		
		boolean result = false;
		if(onlineConts == null) return false;
		if(onlineConts.getAbandoned_keys().size() == 0) return false;
		for(int i=0; i<onlineConts.getAbandoned_keys().size(); i++)
		{
			if(onlineConts.getAbandoned_keys().equals(this))
			{
				result = true;
				break;
			}
		}
		
		return result;
	}
	public static boolean isPrime(long value)
	{
		return isPrime(value, true);
	}
	public static boolean isPrime(long value, boolean one_accepted)
	{
		long gets = value;
		long index = 2;
		if(value <= 1 && (! one_accepted)) return false;
		while(index <= (value/2) + 1)
		{
			if(gets % index == 0) return false;
			else index++;
		}
		return true;
	}
	public Key clone()
	{
		Key newOne = new Key();
		newOne.chars = new KeyBlock[chars.length];
		for(int i=0; i<newOne.chars.length; i++)
		{
			newOne.chars[i] = this.chars[i];
		}
		return newOne;
	}
	public KeyBlock[] getChars()
	{
		return chars;
	}
	public void setChars(KeyBlock[] chars)
	{
		this.chars = chars;
	}
	public boolean equals(Key another)
	{
		//System.out.println(chars.length);
		//System.out.println(another.getChars().length);		
		if(chars.length != another.getChars().length) return false;
		for(int i=0; i<chars.length; i++)
		{
			if(chars[i].getBlocks().length != another.getChars()[i].getBlocks().length) return false;
			for(int j=0; j<chars[i].getBlocks().length; j++)
			{
				if(chars[i].getBlocks()[j] != another.getChars()[i].getBlocks()[j]) return false;
			}
		}		
		return true;
	}
}
