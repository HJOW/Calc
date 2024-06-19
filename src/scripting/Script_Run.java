package scripting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import main_classes.MessageShowable;

public class Script_Run implements Helpable
{
	private ScriptActor manager;
	private InputStream inputStream = null;
	private Reader reader = null;
	private BufferedReader buffered = null;
	private String temp, script;
	private MessageShowable showable;
	
	public Script_Run(ScriptActor manager)
	{
		this.manager = manager;
		showable = manager;
	}
	public Script_Run(MessageShowable manager)
	{
		showable = manager;
		this.manager = null;
	}
	public Object actAndGet(String str) throws Exception
	{
		return manager.actAndGet(str);
	}
	public void act(String str) throws Exception
	{
		manager.actOnly(str);
	}
	private void null_final()
	{
		try
		{
			buffered.close();
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
		try
		{
			inputStream.close();
		}
		catch(Exception e)
		{
			
		}
		buffered = null;
		reader = null;
		inputStream = null;
		script = "";
		temp = "";
	}
	public void loadFile(String path) throws Exception
	{
		if(path.endsWith(".js") || path.endsWith(".JS") || path.endsWith(".Js"))
		{
			try
			{
				reader = new FileReader(path);
				buffered = new BufferedReader(reader);
				script = "";
				while(true)
				{
					temp = buffered.readLine();
					if(temp == null) break;
					script = script + temp + "\n";
				}
				act(script);
			} 
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				null_final();
			}
		}			
	}
	public void loadUrl(String url) throws Exception
	{
		if(url.endsWith(".js") || url.endsWith(".JS") || url.endsWith(".Js"))
		{
			try
			{
				inputStream = new URL(url).openStream();
				reader = new InputStreamReader(inputStream);
				buffered = new BufferedReader(reader);
				script = "";
				while(true)
				{
					temp = buffered.readLine();
					if(temp == null) break;
					script = script + temp + "\n";
				}
				act(script);
			} 
			catch (Exception e)
			{
				throw e;
			}
			finally
			{
				null_final();
			}
		}				
	}
	public HandleResult tryAndGet(String commands)
	{
		HandleResult ob = new HandleResult();
		try
		{
			ob.setReturns(manager.actAndGet(commands));
			ob.setError(false);
			ob.setMessages("");
		}
		catch(Exception e)
		{
			ob = new HandleResult(e, true, e.getMessage());
		}
		return ob;
	}
	public HandleResult tryOnly(String commands)
	{
		HandleResult ob = new HandleResult();
		try
		{
			manager.actOnly(commands);
			ob.setReturns(null);
			ob.setError(false);
			ob.setMessages("");
		}
		catch(Exception e)
		{
			ob = new HandleResult(e, true, e.getMessage());
		}
		return ob;
	}
	public void load_js_files()
	{
		manager.load_js_files(false);
	}
	public void load_js_files(boolean func)
	{
		manager.load_js_files(func);
	}
	public void exec(String str) throws Exception
	{
		Runtime.getRuntime().exec(str);
	}
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}
	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				showable.message("===== manager commands =====");
				showable.message();
				showable.message("act(_i) : 스크립트 _i 를 실행합니다.");
				showable.message("actAndGet(_i) : 스크립트 _i 를 실행하고 반환값을 반환합니다.");
				showable.message("tryAndGet(_i) : 스크립트 _i 를 실행하고 반환값을 반환합니다."
						+ "\n\t 예외 발생 시 예외를 대신 받아, 해당 예외 객체를 반환합니다.");
				showable.message("tryOnly(_i) : 스크립트 _i 를 실행하고 반환값을 반환합니다."
						+ "\n\t 예외 발생 시 예외를 대신 받아, 해당 예외 객체를 반환합니다.");
				showable.message("exec(_i) : _i 를 운영체제의 터미널로 실행합니다.");
				showable.message("loadFile(_i) : 파일 _i 를 불러와 그 내용을 스크립트로 실행합니다.");
				showable.message("\t파일의 확장자가 js 이어야 실행됩니다.");
				showable.message("loadUrl(_i) : 웹 주소 _i 에서 스크립트를 불러와 그 내용을 스크립트로 실행합니다.");
				showable.message("\t웹 주소가 가리키는 파일의 확장자가 js 이어야 실행됩니다.");
				showable.message("load_js_files() : calc 폴더에 있는 파일들 중 js 확장자를 가진 파일들을 모두 스크립트로 실행합니다.");
				showable.message();
				showable.message_bar();
				break;
			default:
				showable.message("===== manager commands =====");
				showable.message();
				showable.message("act(_i) : Run command _i as script.");
				showable.message("actAndGet(_i) : Run command _i as script and return.");
				showable.message("exec(_i) : Run command _i with OS terminal.");
				showable.message("loadFile(_i) : Load file _i and run contents as script.");
				showable.message("\tFile type must be \'js\'.");
				showable.message("loadUrl(_i) : Load file from URL _i and run contents as script.");
				showable.message("\tFile type must be \'js\'.");
				showable.message("load_js_files() : Find \'js\' files at calc directory and run all as script.");
				showable.message();
				showable.message_bar();
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Run(showable);
	}
	@Override
	public String title()
	{
		return "manager";
	}
}
