package scripting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JDialog;

import lang.Language;
import main_classes.CalcWindow;
import main_classes.Controllable;
import main_classes.MessageShowable;
import main_classes.Openable;
import setting.Setting;

public class ScriptManager implements MessageShowable, Openable, Runnable, ScriptActor
{
	
	private MessageShowable superComponent;
	private ScriptEngineManager scriptFactory;
	private ScriptEngine scriptEngine;
	private BufferedWriter writer;
	private StringWriter stringWriter;
	private volatile boolean threadSwitch = true;
	private String engine = "JavaScript";
	private Script_Console console;
	private Script_Control controller;
	private Script_Thread_Generator thread_generator;
	private Script_Component component_generator;
	private Thread thread;
	private Script_File file_controller;
	private Script_Cheat cheater;
	private Script_Reflect reflecter;
	private Script_Run runs;
	private Setting sets = null;
	private Script_CalcWindow calcwindowControl = null;
	private Script_New newer;
	private Helpable[] helpList;
	private ScriptHelp helpWindow;
	
	public ScriptManager()
	{
		this(Setting.getNewInstance(), null, null, "JavaScript");
		//this(sp, "Groovy");
	}
	public ScriptManager(Setting sets, MessageShowable sp, Controllable cont)
	{
		this(sets, sp, cont, "JavaScript");
		//this(sp, "Groovy");
	}
	public ScriptManager(Setting sets, MessageShowable sp, Controllable cont, String engine)
	{
		this(sets, sp, cont, engine, null);
	}
	public ScriptManager(Setting sets, MessageShowable sp, Controllable cont, String engine, Cheatable cht)
	{
		Vector<Helpable> helpVector = new Vector<Helpable>();
		this.sets = sets;
		if(this.sets == null) this.sets = Setting.getNewInstance();
		superComponent = sp;
		this.engine = new String(engine);
		scriptFactory = new ScriptEngineManager();
		scriptEngine = scriptFactory.getEngineByName(this.engine);
		stringWriter = new StringWriter();
		writer = new BufferedWriter(stringWriter);
		scriptEngine.getContext().setWriter(writer);
		console = new Script_Console(sp);
		controller = new Script_Control(cont, console);
		controller.setSetting(cont.getSetting());
		thread_generator = new Script_Thread_Generator(this, sp);
		component_generator = new Script_Component(this, console);
		file_controller = new Script_File(console);
		cheater = new Script_Cheat(cht);
		reflecter = new Script_Reflect(console);
		runs = new Script_Run(this);
		newer = new Script_New(sp);
		if(sp instanceof CalcWindow)
			calcwindowControl = new Script_CalcWindow((CalcWindow) sp, sets);
		
		try
		{
			scriptEngine.put("newer", this.newer);
			helpVector.add(this.newer);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("console", this.console);
			helpVector.add(this.console);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("control", this.controller);
			helpVector.add(this.controller);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("thread_generator", this.thread_generator);
			helpVector.add(this.thread_generator);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("component", this.component_generator);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("file", this.file_controller);
			helpVector.add(this.file_controller);
		} 
		catch (Exception e1)
		{
			
		}
		
		try
		{
			scriptEngine.put("cheat", this.cheater);
			helpVector.add(this.cheater);
		} 
		catch (Exception e1)
		{
			
		}
		try
		{
			scriptEngine.put("reflect", this.reflecter);
		} 
		catch (Exception e1)
		{
			
		}
		
		try
		{
			scriptEngine.put("manager", this.runs);
			helpVector.add(this.runs);
		} 
		catch (Exception e1)
		{
			
		}
		if(calcwindowControl != null)
		{
			try
			{
				scriptEngine.put("window", this.calcwindowControl);
			} 
			catch (Exception e1)
			{
				
			}
		}
		
		String makeHelpFunction = "";
		makeHelpFunction = makeHelpFunction + "function help(){" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message_bar()" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"console.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"control.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"newer.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"loader.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"component.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"chat.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"file.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"thread_generator.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"manager.help()\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message()" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"위의 함수들을 실행해 해당하는 도움말을 볼 수 있습니다.\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"Try these functions to view helps.\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message()" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"console 는 메시지 출력에, control 는 게임 조작에 사용됩니다.\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(\"Use console to print message, and use control to control the game.\")" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message_bar()" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function cht(ch){" +"\n";
		makeHelpFunction = makeHelpFunction + "cheat.c(ch)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function message(msg){" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message(msg)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function alert(msg){" +"\n";
		makeHelpFunction = makeHelpFunction + "console.alert(msg)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function message_bar(){" +"\n";
		makeHelpFunction = makeHelpFunction + "console.message_bar()" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function string(ob){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.string(ob)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function isType(type, obj){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.isType(type, obj)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function none(){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.none()" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function isNull(_i){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.isNull(_i)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function parse(_i, _j){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.parse(_i, _j)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function wrap(_i){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.wrap(_i)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
		makeHelpFunction = makeHelpFunction + "function vector(_i){" +"\n";
		makeHelpFunction = makeHelpFunction + "return newer.vector(_i)" +"\n";
		makeHelpFunction = makeHelpFunction + "}" +"\n";
				
		try
		{
			scriptEngine.eval(makeHelpFunction);
		}
		catch(Exception e)
		{
			
		}	
		try
		{
			scriptEngine.eval("function print(msg){ \n console.message(msg) \n }");
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
			load_js_files(true);
			if(sets.getScript_directLoad().booleanValue())
			{
				load_js_files(false);
			}
			if(sets.getScript_urlLoad().booleanValue())
			{
				load_js_url();
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{			
			if(sets.getScript_urlLoad().booleanValue())
			{
				load_js_url();
			}
		}
		catch(Exception e)
		{
			
		}
		
		helpList = new Helpable[helpVector.size()];
		for(int i=0; i<helpVector.size(); i++)
		{
			helpList[i] = helpVector.get(i);
		}
		helpWindow = new ScriptHelp(this, sets);
		
		thread = new Thread(this);		
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
	@Override
	public void message()
	{
		if(superComponent != null) superComponent.message();
	}

	@Override
	public void message_bar()
	{
		if(superComponent != null) superComponent.message_bar();
	}

	@Override
	public void message(String str)
	{
		if(superComponent != null) superComponent.message(str);
	}

	@Override
	public void alert(String str)
	{
		if(superComponent != null) superComponent.alert(str);
	}
	public MessageShowable getSuperComponent()
	{
		return superComponent;
	}
	public void setSuperComponent(MessageShowable superComponent)
	{
		this.superComponent = superComponent;
	}
	public ScriptEngineManager getScriptFactory()
	{
		return scriptFactory;
	}
	public void setScriptFactory(ScriptEngineManager scriptFactory)
	{
		this.scriptFactory = scriptFactory;
	}
	public ScriptEngine getScriptEngine()
	{
		return scriptEngine;
	}
	public void setScriptEngine(ScriptEngine scriptEngine)
	{
		this.scriptEngine = scriptEngine;
	}
	public BufferedWriter getWriter()
	{
		return writer;
	}
	public void setWriter(BufferedWriter writer)
	{
		this.writer = writer;
	}
	public StringWriter getStringWriter()
	{
		return stringWriter;
	}
	public void setStringWriter(StringWriter stringWriter)
	{
		this.stringWriter = stringWriter;
	}
	public boolean isThreadSwitch()
	{
		return threadSwitch;
	}
	public void setThreadSwitch(boolean threadSwitch)
	{
		this.threadSwitch = threadSwitch;
	}
	public Thread getThread()
	{
		return thread;
	}
	public void setThread(Thread thread)
	{
		this.thread = thread;
	}
	public String getEngine()
	{
		return engine;
	}
	public void setEngine(String engine)
	{
		this.engine = engine;
	}
	@Override
	public void open()
	{
		thread.start();		
	}
	@Override
	public void exit()
	{
		superComponent = null;
		threadSwitch = false;
		try
		{
			writer.close();
			stringWriter.close();
		} 
		catch (Exception e)
		{
			
		}
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
	public void run()
	{
		while(threadSwitch)
		{
			String output = stringWriter.getBuffer().toString();
			message(output);
		}
	}
	@Override
	public Helpable[] helpList()
	{
		return helpList;
	}
	@Override
	public Openable getHelpWindow()
	{
		return helpWindow;
	}
	@Override
	public Openable getHelpWindow(JDialog dialog)
	{
		helpWindow = new ScriptHelp(this, sets, dialog);
		return helpWindow;
	}
	@Override
	public void openConsole()
	{
		superComponent.openConsole();
		
	}
}
