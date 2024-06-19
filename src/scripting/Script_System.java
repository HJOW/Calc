package scripting;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import main_classes.MessageShowable;

public class Script_System implements Helpable
{
	private ScriptActor actor;
	private MessageShowable showable;
	public Script_System()
	{
		
	}
	public Script_System(MessageShowable showable, ScriptActor actor)
	{
		this.actor = actor;
		this.showable = showable;
	}
	public void gc()
	{
		System.gc();
	}
	public String property(String key)
	{
		return System.getProperty(key);
	}
	public Properties properties()
	{
		return System.getProperties();
	}
	public long currentTimeMills()
	{
		return System.currentTimeMillis();
	}
	public void exit(int v)
	{
		System.exit(v);
	}
	public Dimension screenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public long freeMemory()
	{
		return Runtime.getRuntime().freeMemory();		
	}
	public long totalMemory()
	{
		return Runtime.getRuntime().totalMemory();		
	}
	public int availableProcessors()
	{
		return Runtime.getRuntime().availableProcessors();
	}
	public void println(double ob)
	{
		System.out.println(ob);
	}
	public void println(boolean ob)
	{
		System.out.println(ob);
	}
	public void println(Object ob)
	{
		System.out.println(ob);
	}
	public void println()
	{
		System.out.println();
	}
	public void print(double ob)
	{
		System.out.print(ob);
	}
	public void print(boolean ob)
	{
		System.out.print(ob);
	}
	public void print(Object ob)
	{
		System.out.print(ob);
	}
	public String string(double l)
	{
		return String.valueOf(l);
	}
	public String string(boolean l)
	{
		return String.valueOf(l);
	}
	public String string(Object l)
	{
		if(l instanceof char[])
		{
			return new String((char[]) l);
		}
		else if(l instanceof byte[])
		{
			return new String((byte[]) l);
		}
		return String.valueOf(l);
	}
	public String string(byte[] l, String charset) throws UnsupportedEncodingException
	{
		return new String(l, charset);
	}
	public long integer(String str) throws NumberFormatException
	{
		return Long.parseLong(str);
	}
	public double floats(String str) throws NumberFormatException
	{
		return Double.parseDouble(str);
	}
	public boolean bools(String str)
	{
		return Boolean.parseBoolean(str);
	}
	public Exception tryAndCatch(String script)
	{
		return tryAndCatch(script, false);
	}
	public Exception tryAndCatch(String script, boolean printTrace)
	{
		try
		{
			actor.actOnly(script);
			return null;
		} 
		catch (Exception e)
		{
			if(printTrace) e.printStackTrace();	
			return e;
		}
	}
	public byte[] byteArray(int size)
	{
		byte[] newArr = new byte[size];
		for(int i=0; i<newArr.length; i++)
		{
			newArr[i] = 0;
		}
		return newArr;
	}
	public long[] intArray(int size)
	{
		long[] newArr = new long[size];
		for(int i=0; i<newArr.length; i++)
		{
			newArr[i] = 0;
		}
		return newArr;
	}
	public double[] floatArray(int size)
	{
		double[] newArr = new double[size];
		for(int i=0; i<newArr.length; i++)
		{
			newArr[i] = 0;
		}
		return newArr;
	}
	public Socket connect(String address,int port) throws UnknownHostException, IOException
	{
		return new Socket(address, port);
	}
	public ServerSocket host(int port) throws IOException
	{
		return new ServerSocket(port);
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
				showable.message("===== system commands =====");
				showable.message();
				showable.message("gc() : 쓰이지 않을 데이터들을 메모리에서 비웁니다.");
				showable.message("property(_i) : _i 에 해당하는 시스템 속성을 문자열로 반환합니다.");
				showable.message("\t 자세한 내용은 Java API 문서에서 System.getProperty(String key) 메소드를 확인하세요.");
				showable.message("currentTimeMills() : 현재 시간을 분, 초, 밀리초까지 하나의 정수값으로 반환합니다.");
				showable.message("exit(_i) : 게임을 종료합니다. 프로그램 자체가 종료됩니다.");
				showable.message("screenSize() : 화면 크기를 Dimension 객체로 반환합니다.");
				showable.message("\t 이 객체에는 getWidth(), getHeight() 메소드가 있습니다.");
				showable.message("freeMemory() : 가용 메모리 용량을 정수값으로 반환합니다.");
				showable.message("totalMemory() : 전체 메모리 용량을 정수값으로 반환합니다.");
				showable.message("availableProcessors() : 사용 가능한 프로세서 수를 반환합니다.");
				showable.message("print(_i) : 표준 출력으로 _i를 출력합니다.");
				showable.message("\t 게임 화면에는 보이지 않으며, 터미널로 실행한 경우 터미널 화면에는 보일 것입니다.");
				showable.message("\t _i 에는 숫자, 객체 등 여러가지가 올 수 있습니다.");
				showable.message("println(_i) : print(_i)와 동일하지만 줄을 띄워 줍니다. _i에 아무것도 넣지 않으면 줄만 띄웁니다.");
				showable.message("string(_i) : _i 에 해당하는 데이터를 문자열 객체로 변환합니다.");
				showable.message("\t _i가 바이트 배열인 경우 이 바이트 배열을 이용해 문자열 객체를 만듭니다.");
				showable.message("string(_i, _j) : _i는 바이트 배열이고 _j는 문자 셋에 대한 문자열입니다.");
				showable.message("\t _i 바이트 배열로 문자열을 만들어 반환하는 데 _j로 지정한 문자 셋을 이용합니다.");
				showable.message("integer(_i) : 문자열 _i를 정수로 변환합니다.");
				showable.message("floats(_i) : 문자열 _i를 실수로 변환합니다.");
				showable.message("bools(_i) : 문자열 _i를 boolean 으로 변환합니다.");
				showable.message("tryAndCatch(_script) : _script 에 해당하는 문자열을 스크립트로 실행합니다.");
				showable.message("\t 실행 과정에서 예외 발생 시 예외 객체를 반환합니다. 이 객체에는 e.getMessage() 라는 메소드가 있습니다.");
				showable.message("\t 실행 과정에서 문제가 없었다면 null 을 반환합니다.");
				showable.message("tryAndCatch(_script, _i) : tryAndCatch(_script) 와 동일하지만, _i가 true인 경우 예외 발생 시 발생 지점을 추적합니다.");
				showable.message("\t 추적 과정은 표준 출력으로 보여지므로 터미널에만 나타납니다.");
				showable.message("byteArray(_i) : _i 크기의 byte 배열을 반환합니다. 0으로 초기화되어 있습니다.");
				showable.message("intArray(_i) : _i 크기의 long 배열을 반환합니다. 0으로 초기화되어 있습니다.");
				showable.message("floatArray(_i) : _i 크기의 double 배열을 반환합니다. 0으로 초기화되어 있습니다.");
				showable.message("\t long은 보다 큰 정수까지 감당할 수 있는 타입이며, double은 보다 큰 실수까지 감당할 수 있는 타입입니다.");
				showable.message("connect(_i, _j) : _i에 해당하는 IP 주소, _j에 해당하는 포트 번호로 서버에 접속할 수 있는 Socket 객체를 반환합니다.");
				showable.message("\t 자세한 내용은 Java API 문서의 Socket 객체의 생성자를 참고하세요.");
				showable.message("host(_i) : _i 포트 번호로 접속을 대기하는 데 사용하는 ServerSocket 객체를 반환합니다.");
				showable.message("\t 자세한 내용은 Java API 문서의 ServerSocket 객체의 생성자를 참고하세요.");
				showable.message();
				showable.message_bar();
				break;
			default:				
				showable.message("===== system commands =====");
				showable.message();
				showable.message("gc() : Call the garbage collector to clean the memory.");
				showable.message("property(_i) : Return system property with _i key.");
				showable.message("\t It is same as System.getProperty(_i) on Java.");
				showable.message("currentTimeMills() : Returns current time as long number.");
				showable.message("exit(_i) : Exit the program.");
				showable.message("screenSize() : Return Dimension object which is presents the screen size.");
				showable.message("\t Use getWidth(), getHeight() on Dimension object to get width, height.");
				showable.message("freeMemory() : Returns how much space available on the memory.");
				showable.message("totalMemory() : Returns how much space total on the memory.");
				showable.message("availableProcessors() : Returns how many processors available.");
				showable.message("print(_i) : Print _i with standard output.");
				showable.message("println(_i) : Same as print(_i), but this also jump next line.");
				showable.message("string(_i) : Convert _i as String.");
				showable.message("string(_i, _j) : _i will be byte[], and _j will be String presents the character set.");
				showable.message("\t This method make new String with byte[].");
				showable.message("integer(_i) : Convert String into long.");
				showable.message("floats(_i) : Convert String into double.");
				showable.message("bools(_i) : Convert String into boolean.");
				showable.message("tryAndCatch(_script) : Run string _script as script.");
				showable.message("\t If the exception occurs when the _script runs, the exception object will be returned.");
				showable.message("\t If there is no exception, null will be returned.");
				showable.message("tryAndCatch(_script, _i) : Same as tryAndCatch(_script), if _i is true, stack traces will be printed on the terminal. ");
				showable.message("byteArray(_i) : Make empty byte[] as _i size. This array will be initialized as 0.");
				showable.message("intArray(_i) : Make empty long[] as _i size. This array will be initialized as 0.");
				showable.message("floatArray(_i) : Make empty double[] as _i size. This array will be initialized as 0.");
				showable.message("connect(_i, _j) : Return Socket object to connect to server as TCP/IP. _i is String presents IP, and _j is integer presents Port.");
				showable.message("host(_i) : Return ServerSocket object to accept the connection. _i is integer presents Port.");
				showable.message();
				showable.message_bar();
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_System(showable, actor);
	}
	@Override
	public String title()
	{
		return "system";
	}
}
