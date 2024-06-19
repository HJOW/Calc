package scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JDialog;

import lang.Language;
import main_classes.MessageShowable;
import main_classes.Openable;
import setting.Setting;

public class NormalScriptManager implements ScriptActor, Openable
{
	private ScriptEngineManager scriptFactory;
	private ScriptEngine scriptEngine;
	private Setting sets;
	private Script_Console console;
	private Script_New newer;
	private Script_Component components;
	private Script_Reflect reflector;
	private Script_Thread_Generator thread;
	private Script_System systems;
	private Helpable[] helpList;
	private ScriptHelp helpWindow;
	private Script_Terminal terminal;
	private static String path = "";

	public NormalScriptManager(MessageShowable showable, Setting sets)
	{
		Vector<Helpable> helpVector = new Vector<Helpable>();
		scriptFactory = new ScriptEngineManager();
		scriptEngine = scriptFactory.getEngineByName("js");
		this.sets = sets.clone();
		
		
		console = new Script_Console(showable);
		putObject("console", console);
		helpVector.add(console);
		
		newer = new Script_New(showable);
		putObject("newer", newer);
		helpVector.add(newer);
		
		components = new Script_Component(this, console);
		putObject("component", components);		
		
		reflector = new Script_Reflect(console);
		putObject("reflect", reflector);
		
		thread = new Script_Thread_Generator(this, console);
		putObject("thread_generator", thread);
		helpVector.add(thread);
		
		systems = new Script_System(console, this);
		putObject("system", systems);	
		helpVector.add(systems);
		
		terminal = new Script_Terminal(sets, console);
		putObject("terminal", terminal);	
		
		helpList = new Helpable[helpVector.size()];
		for(int i=0; i<helpVector.size(); i++)
		{
			helpList[i] = helpVector.get(i);
		}
		
		helpWindow = new ScriptHelp(this, sets);
		
		path = sets.getDefault_path();
	}
	public static String path()
	{
		return path;
	}
	static void setPath(String newPath)
	{
		path = newPath;
	}
	public void open()
	{
		InputStreamReader inputReader = null;
		BufferedReader bufferedReader = null;
		String commands = "";
		Object gets;
		try
		{
			String prepares;
			prepares = "function dir(){terminal.dir()};";
			prepares = prepares + "function ls(){terminal.ls()};";
			prepares = prepares + "function cd(a){terminal.cd(a)};";
			prepares = prepares + "function mkdir(a){terminal.mkdir(a)};";
			prepares = prepares + "function rm(a){terminal.rm(a)};";
			prepares = prepares + "function run(a){terminal.run(a)};";
			prepares = prepares + "function exit(){terminal.exit()};";
			prepares = prepares + "function gc(){system.gc()};";
			prepares = prepares + "function currentTimeMills(){return system.currentTimeMills()};";
			prepares = prepares + "function screenSize(){return system.screenSize()};";
			prepares = prepares + "function freeMemory(){system.freeMemory()};";
			prepares = prepares + "function totalMemory(){system.totalMemory()};";
			prepares = prepares + "function availableProcessors(){return system.availableProcessors()};";
			prepares = prepares + "function println(a){system.println(a)};";
			prepares = prepares + "function string(a){return system.string(a)};";
			prepares = prepares + "function integer(a){return system.integer(a)};";
			prepares = prepares + "function floats(a){return system.floats(a)};";
			prepares = prepares + "function bools(a){return system.bools(a)};";
			prepares = prepares + "function tryAndCatch(a){return system.tryAndCatch(a)};";
			prepares = prepares + "function connect(a, b){return system.connect(a, b)};";
			prepares = prepares + "function host(a){return system.host(a)};";
			actOnly(prepares);
		}
		catch(Exception e)
		{
			
		}
		try
		{			
			inputReader = new InputStreamReader(System.in);
			bufferedReader = new BufferedReader(inputReader);
			while(true)
			{
				System.out.print(path + ">");
				commands = bufferedReader.readLine();
				if(commands != null)
				{
					try
					{
						gets = actAndGet(commands);
						if(gets != null)
							System.out.println(gets);
					}
					catch(Exception e)
					{
						System.out.println(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufferedReader.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				inputReader.close();
			} 
			catch (Exception e)
			{
				
			}
		}
	}
	@Override
	public void message()
	{
		console.message();
		
	}

	@Override
	public void message_bar()
	{
		console.message_bar();
		
	}

	@Override
	public void message(String str)
	{
		console.message(str);
		
	}

	@Override
	public void alert(String str)
	{
		console.alert(str);
		
	}

	public Openable getHelpWindow()
	{
		return helpWindow;
	}
	public Openable getHelpWindow(JDialog dialog)
	{
		helpWindow = new ScriptHelp(this, sets, dialog);
		return helpWindow;
	}
	public void putObject(String name, Object object)
	{
		scriptEngine.put(name, object);
	}	
	public Object actAndGet(String orders) throws Exception
	{
		return scriptEngine.eval(orders);
	}
	public void actOnly(String orders) throws Exception
	{
		scriptEngine.eval(orders);
	}
	public void act(String orders) throws Exception
	{
		System.out.println("Order : " + orders);
		Object result = scriptEngine.eval(orders);
		if(result != null)
			message(result.toString());
	}
	public void load_js_files(boolean func)
	{
		File[] lists;
		FileReader freader = null;
		BufferedReader breader = null;
		String readScript = "", temp = "";
		int infinite_loop = 0;
		try
		{
			File loadDir = new File(sets.getDefault_path());
			if(loadDir.exists())
			{
				lists = loadDir.listFiles();
				for(int i=0; i<lists.length; i++)
				{
					infinite_loop = 0;
					try
					{
						if(lists[i].getName().endsWith(".js") || lists[i].getName().endsWith(".Js") || lists[i].getName().endsWith(".JS"))
						{
							freader = new FileReader(lists[i]);
							breader = new BufferedReader(freader);
							while(true)
							{
								temp = breader.readLine();
								if(temp == null) break;
								readScript = readScript + temp + "\n";
								infinite_loop++;
								if(infinite_loop >= 100000000) break;
							}
							if(func)
							{
								StringTokenizer elimPoint = new StringTokenizer(lists[i].getName(), ".");
								String elimed = elimPoint.nextToken();
								//System.out.println("function " + elimed + "(){" + readScript + "}");
								scriptEngine.eval("function u_" + elimed + "(){" + readScript + "}");
							}
							else
							{
								scriptEngine.eval(readScript);
							}
						}
					}
					catch(Exception e1)
					{
						System.out.println();
						message(sets.getLang().getText(Language.ERROR) + " at " + lists[i].getAbsolutePath() + "\n\t " + e1.getMessage());
					}
					finally
					{
						try
						{
							breader.close();
						} 
						catch (Exception e)
						{
							
						}
						try
						{
							freader.close();
						} 
						catch (Exception e)
						{
							
						}						
					}
				}
			}
		}
		catch(Exception e1)
		{
			
		}
	}
	public void load_js_url()
	{
		InputStream inputs = null;
		InputStreamReader reader = null;
		BufferedReader buffered = null;
		Vector<String> urlList = new Vector<String>();
		String temp = "";
		String basic_url = null;
		try
		{
			inputs = new URL(sets.getNotice_url() + "script_list.txt").openStream();
			basic_url = sets.getNotice_url() + "script_list.txt";
		}
		catch(Exception e)
		{
			basic_url = null;
		}
		finally
		{
			try
			{
				inputs.close();
			} 
			catch (Exception e)
			{
				
			}
		}
		if(basic_url == null)
		{
			try
			{
				inputs = new URL(sets.getNotice_url2() + "script_list.txt").openStream();
				basic_url = sets.getNotice_url2() + "script_list.txt";
			}
			catch(Exception e)
			{
				basic_url = null;
			}
			finally
			{
				try
				{
					inputs.close();
				} 
				catch (Exception e)
				{
					
				}
			}
		}
		if(basic_url != null)
		{
			try
			{
				//System.out.println("Load : " + sets.getNotice_url() + "script_list.txt");
				inputs = new URL(basic_url).openStream();
				reader = new InputStreamReader(inputs);
				buffered = new BufferedReader(reader);
				while(true)
				{
					temp = buffered.readLine();
					if(temp == null) break;
					urlList.add(temp);
				}
				
			}
			catch (Exception e)
			{
				
			}
			finally
			{
				try
				{
					reader.close();
				} 
				catch (IOException e)
				{
					
				}
				try
				{
					buffered.close();
				} 
				catch (IOException e)
				{
					
				}
				try
				{
					inputs.close();
				} 
				catch (IOException e)
				{
					
				}			
			}
			if(urlList.size() >= 1)
			{
				StringTokenizer barSep = null;
				int grade = 0;
				boolean accepted = false;
				for(String url : urlList)
				{
					barSep = new StringTokenizer(url, "|");
					if(barSep.countTokens() >= 2)
					{
						try
						{
							grade = Integer.parseInt(barSep.nextToken());
							if(sets.accepted() || grade <= 1)
							{
								accepted = true;
							}
							if(! accepted)
							{
								try
								{
									if(sets.accept_net() || grade <= 2)
									{
										accepted = true;
									}
								} 
								catch (Exception e)
								{
									accepted = false;
								}
							}
							if(! accepted)
							{
								try
								{
									if(sets.accept_mastered() || grade <= 3)
									{
										accepted = true;
									}
								} 
								catch (Exception e)
								{
									accepted = false;
								}
							}
							if(accepted)
							{
								load_js_url(url);
							}
						}
						catch(NumberFormatException e)
						{
							
						}
					}
					else
					{
						load_js_url(url);
					}
					
				}
			}
		}		
	}
	public void load_js_url(String str)
	{
		InputStream inputs = null;
		InputStreamReader reader = null;
		BufferedReader buffered = null;
		//System.out.println("Load : " + str);
		String accumulated = "";
		String temp = "";
		try
		{
			inputs = new URL(str).openStream();
			reader = new InputStreamReader(inputs);
			buffered = new BufferedReader(reader);
			while(true)
			{
				temp = buffered.readLine();
				if(temp == null) break;
				accumulated = accumulated + temp + "\n";
			}
			scriptEngine.eval(accumulated);
		}
		catch (Exception e)
		{
			message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			//e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				buffered.close();
			} 
			catch (IOException e)
			{
				
			}
			try
			{
				inputs.close();
			} 
			catch (IOException e)
			{
				
			}			
		}
	}
	@Override
	public Helpable[] helpList()
	{
		return helpList;
	}
	public Object actFile(String files)
	{
		FileReader reader = null;
		BufferedReader bfreader = null;
		try
		{
			String contents = "", lines = "";
			reader = new FileReader(files);
			bfreader = new BufferedReader(reader);
			while(true)
			{
				lines = bfreader.readLine();
				if(lines == null) break;
				contents = contents + lines + "\n";
			}
			try
			{
				bfreader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				
			}
			return actAndGet(contents);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bfreader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				
			}
		}
		return null;
	}

	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exit()
	{
		systems.exit(0);
		
	}
}
