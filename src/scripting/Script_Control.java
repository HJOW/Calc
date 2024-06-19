package scripting;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import setting.Lint;
import setting.Setting;
import network.DisconnectMessage;
import network.Downloader;
import network.FinishMessage;
import network.JoinListMessage;
import network.NetplayMessage;
import network.ReadyMessage;
import network.StartMessage;
import main_classes.Controllable;
import main_classes.MessageShowable;

public class Script_Control implements Controllable, Helpable
{
	private Controllable superComponent;
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;
	private MessageShowable console;
	private boolean showError = false;
	private boolean disabable = false;
	private Setting sets = null;
	
	public Script_Control(Controllable superComponent, MessageShowable console)
	{
		this.superComponent = superComponent;
		this.console = console;
	}
	public void setSetting(Setting sets)
	{
		this.sets = sets;
	}
	public InputStream stdout()
	{
		return System.in;
	}
	public OutputStream stdin()
	{
		return System.out;
	}
	public void offControl()
	{
		if(disabable)
		{
			try
			{
				closeAll();
				console = null;
				superComponent = null;
			} 
			catch (Exception e)
			{
				
			}
		}
		else
		{
			String lang = System.getProperty("user.language");
			if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) console.message("Basic control cannot be disabled.");
			else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) console.message("기본 control 은 비활성화되지 않습니다.");
			else console.message("Basic control cannot be disabled.");
		}
	}
	public void download(String url)
	{
		try
		{
			Downloader.download(sets, url);
		} 
		catch (Exception e)
		{
			message(e.getMessage());
		}
	}
	public void downloads(String[] url, boolean ui)
	{
		try
		{
			Downloader.downloads(sets, url, ui);
		} 
		catch (Exception e)
		{
			message(e.getMessage());
		}
	}
	public Script_Control newControl()
	{
		Script_Control newControl = new Script_Control(superComponent, console);
		newControl.setSetting(sets);
		newControl.disabable = true;
		return newControl;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList newArrayList()
	{
		return new ArrayList();
	}
	
	public void gc()
	{
		try
		{
			long before = Runtime.getRuntime().freeMemory();
			System.gc();
			messageNotError(String.valueOf(before) + " --> " + String.valueOf(Runtime.getRuntime().freeMemory()));
		} 
		catch (Exception e)
		{
			message(e.getMessage());
		}
	}
	
	public String typeof(Object obj)
	{
		if(obj instanceof String)
		{
			return "String";
		}
		else if(obj instanceof BigInteger)
		{
			return "BigInteger";
		}
		else if(obj instanceof BigDecimal)
		{
			return "BigDecimal";
		}
		else
			return obj.getClass().toString();
	}
	public String typeof(int value)
	{
		return "int";
	}
	public String typeof(long value)
	{
		return "int";
	}
	public String typeof(double value)
	{
		return "double";
	}
	public String typeof(boolean value)
	{
		return "boolean";
	}	
	public File file(String path)
	{
		return new File(path);
	}
	public BigInteger newBigInteger(long value)
	{
		return Lint.big(value);
	}
	public StringTokenizer newStringTokenizer(String str)
	{
		return new StringTokenizer(str);
	}
	public StringTokenizer newStringTokenizer(String str, String delimiter)
	{
		return new StringTokenizer(str, delimiter);
	}
	public BigDecimal newBigDecimal(double value)
	{
		return new BigDecimal(value);
	}
	public BigDecimal convert(double value)
	{
		return new BigDecimal(value);
	}
	public double convert(BigDecimal value)
	{
		return value.doubleValue();
	}
	public BigInteger convert(long value)
	{
		return Lint.big(value);
	}
	public long convert(BigInteger value)
	{
		return value.longValue();
	}
	public String getProperty(String key)
	{
		return System.getProperty(key);
	}
	public long totalmemory()
	{
		return totalmemory("");
	}
	public long totalmemory(String unit)
	{
		try
		{
			if(unit.equalsIgnoreCase("KB"))
			{
				return Runtime.getRuntime().totalMemory() / 1024;
			}
			else if(unit.equalsIgnoreCase("MB"))
			{
				return Runtime.getRuntime().totalMemory() / (1024 * 1024);
			}
			else if(unit.equalsIgnoreCase("GB"))
			{
				return Runtime.getRuntime().totalMemory() / (1024 * 1024 * 1024);
			}
			else
				return Runtime.getRuntime().totalMemory();
		} 
		catch (Exception e)
		{
			return -1;
		}
	}
	public long maxmemory()
	{
		return maxmemory("");
	}
	public long maxmemory(String unit)
	{
		try
		{
			if(unit.equalsIgnoreCase("KB"))
			{
				return Runtime.getRuntime().maxMemory() / 1024;
			}
			else if(unit.equalsIgnoreCase("MB"))
			{
				return Runtime.getRuntime().maxMemory() / (1024 * 1024);
			}
			else if(unit.equalsIgnoreCase("GB"))
			{
				return Runtime.getRuntime().maxMemory() / (1024 * 1024 * 1024);
			}
			else
				return Runtime.getRuntime().maxMemory();
		} 
		catch (Exception e)
		{
			return -1;
		}
	}
	public long freememory()
	{
		return freememory("");
	}
	public long freememory(String unit)
	{
		try
		{
			if(unit.equalsIgnoreCase("KB"))
			{
				return Runtime.getRuntime().freeMemory() / 1024;
			}
			else if(unit.equalsIgnoreCase("MB"))
			{
				return Runtime.getRuntime().freeMemory() / (1024 * 1024);
			}
			else if(unit.equalsIgnoreCase("GB"))
			{
				return Runtime.getRuntime().freeMemory() / (1024 * 1024 * 1024);
			}
			else
				return Runtime.getRuntime().freeMemory();
		} 
		catch (Exception e)
		{
			return -1;
		}
	}
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}
	public void help(MessageShowable console)
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0, console);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1, console);
		else help(0, console);
	}
	public void help(int lang)
	{
		help(lang, console);
	}	
	public void help(int lang, MessageShowable console)
	{
		switch(lang)
		{
			case 1:
				console.message("===== control 명령어 =====");
				console.message();
				console.message("showError() : 스크립트 상에서 앞으로 발생하는 오류 메시지를 보입니다.");
				console.message("dontShowError() : 스크립트 상에서 앞으로 발생하는 오류 메시지를 보이지 않습니다");
				console.message();
				console.message("start_game() : 게임을 시작합니다.");
				console.message("stop_game() : 게임을 중단합니다.");
				console.message("exit() : 게임을 종료합니다.");
				console.message();				
				console.message("selectAuthorizeCheckbox() : 인증 체크박스를 체크하거나 해제합니다.");
				console.message("selectPlayerSetting(int index, int value) : _i 번째 플레이어를 설정합니다.");
				console.message("\t _j 는 다음과 같습니다... 0 : 빈 슬롯, 1 : 플레이어, 2 : 인공지능");
				console.message();
				console.message("takeCard() : 게임 중이라면 카드를 덱에서 한 장 받습니다.");
				console.message("payCard(_i, _j) : 게임 중이라면 자신이 보유한 _j 번째 카드를 _i 번째 플레이어에게 냅니다.");
				console.message();
				console.message("objectToString(_i) : 객체  _i를 문자열로 변환하여 반환합니다.");
				console.message("printObject(_i) : 객체 _i를 출력합니다.");
				console.message();
				console.message("getProperty(_i) : _i에 해당하는 데이터를 반환합니다. 자바의 System.getProperty(_i) 와 대응합니다.");
				console.message("\t 만약 _i 를 \"user.language\" 로 넣으면 사용 중인 언어를 문자열로 반환합니다.");
				console.message("\t 만약 _i 를 \"os.name\" 로 넣으면 사용 중인 운영체제 이름을 문자열로 반환합니다.");
				console.message();
				console.message("newArrayList() : 새로운 ArrayList 객체를 반환합니다. 가변 배열로서 활용할 수 있습니다.");
				console.message("\t 처음 객체 생성 시에는 아무 원소도 들어 있지 않습니다. 이 객체의 add(_obj) 메소드를 이용해 _obj 객체를 원소로 넣습니다.");
				console.message("\t\t 이 외에도 get(_i) , remove(_i) 등의 메소드가 있습니다.");
				console.message("\t 되도록이면 같은 타입의 원소만 넣어 사용하십시오. 다른 타입의 원소는 다른 객체에 넣으십시오.");
				console.message();
				console.message("newBigInteger(_i) : 숫자 _i에 해당하는 BigInteger 객체를 반환합니다. 무한 자리수 정수를 사용할 수 있습니다.");
				console.message("newBigDecimal(_i) : 실수 _i에 해당하는 BigDecimal 객체를 반환합니다. 무한 자리수 실수를 사용할 수 있습니다.");		
				console.message("newStringTokenizer(_i) : 문자열 _i 를 구분자로 나누는 StringTokenizer 객체를 반환합니다.");
				console.message("newStringTokenizer(_i, _j) : 문자열 _i 를 구분자 _j로 나누는 StringTokenizer 객체를 반환합니다.");
				console.message("\t 이 객체의 nextToken() 메소드로 토큰 하나를 문자열로 반환받을 수 있습니다.");
				console.message();
				console.message("주의 : 아래의 메소드와 객체는 함부로 사용하지 마십시오.");
				console.message();
				console.message("host(_i) : 포트번호 _i 로 호스트합니다. 서버소켓을 사용합니다.");
				console.message("accept() : host(_i) 를 먼저 사용해야 합니다. 다른 사람이 접속하기를 기다립니다.");
				console.message("connect(_i, _j) : IP 주소 _i, 포트 번호 _j 에 접속합니다. 상대가 대기하고 있어야 합니다.");
				console.message("closeAll() : 접속을 끊습니다.");
				console.message("send(_i) : 객체 _i 를 상대에게 보냅니다.");
				console.message("receive() : 상대에게 객체를 받기를 기다렸다 받은 객체를 반환합니다.");
				console.message();
				console.message("offControl() : 컨트롤을 닫습니다. 더 이상 해당 컨트롤을 사용할 수 없게 됩니다.");
				console.message("newControl() : 새로운 컨트롤을 만들어 반환합니다.");
				console.message();
				console.message("stdout() : 표준 출력 OutputStream 을 반환합니다.");
				console.message("stdin() : 표준 입력 InputStream 을 반환합니다.");
				console.message();
				console.message("file(_f) : _f 경로에 있는 파일에 해당하는 파일 객체를 반환합니다.");
				console.message("\t 파일 객체를 만들고 메소드를 이용해, 해당 파일이 존재하는지 확인할 수 있으며, 경로 이름으로 폴더를 만들거나, 파일을 삭제할 수 있습니다.");
				console.message();
				console.message("thread_generator 를 사용하여 쓰레드를 생성할 수 있습니다.\n thread_generator.generate(_i) 는 문자열 _i를 스크립트 코드로 반복 실행하는 쓰레드 객체를 반환합니다.");
				console.message();
				console.message_bar();
				break;
			default:
				console.message("===== control order set =====");
				console.message();
				console.message("showError() : turn on showing error messages.");
				console.message("dontShowError() : turn off showing error messages.");
				console.message();				
				console.message("start_game() : start the game.");
				console.message("stop_game() : stop the game.");
				console.message("exit() : exit the game.");
				console.message();
				console.message("selectAuthorizeCheckbox() : select or unselect authorize checkbox.");
				console.message("selectPlayerSetting(int index, int value) : select player setting. control _i \'th player setting.");
				console.message("\t _j is... 0 : None, 1 : Player, 2 : AI");
				console.message();
				console.message("takeCard() : take one card from deck, only on gaming.");
				console.message("payCard(_i, _j) : pay _j\'th card to player number _i, only on gaming.");
				console.message();
				console.message("objectToString(_i) : convert _i to string and return.");
				console.message("printObject(_i) : print _i. _i can be object.");
				console.message();
				console.message("getProperty(_i) : return data. this is similar as System.getProperty(_i) at Java.\n\t if _i is \"user.language\" then return language as string.");
				console.message("\t if _i is \"os.name\" then return operating system name as string.");				
				console.message();
				console.message("Warning : Be careful to use methods and object below.");
				console.message();
				console.message("host(_i) : make server socket as port number _i.");
				console.message("accept() : wait until another person connects here, host(_i) needed.");
				console.message("connect(_i, _j) : connect to server, input ip at _i and input port number at _j.");
				console.message("closeAll() : try to close the socket and the server socket.");
				console.message("send(_i) : use the socket to send object _i");
				console.message("receive() : use the socket to receive and return object.");
				console.message();
				console.message("offControl() : turn off this control.");
				console.message("newControl() : Make and return new controls");
				console.message();
				console.message("stdout() : Return standard OutputStream.");
				console.message("stdin() : Return standard InputStream.");
				console.message();
				console.message("You can use thread_generator to make new thread.\n thread_generator.generate(_i) generates new thread to run _i and return thread object.");
				console.message();
				console.message_bar();
		}
	}
	@Override
	public void takeCard()
	{
		superComponent.takeCard();
		
	}

	@Override
	public void payCard(int blockNumber, int pay_card_index)
	{
		superComponent.payCard(blockNumber, pay_card_index);
		
	}
	
	@Override
	public void stop_game()
	{
		superComponent.stop_game();
		
	}

	@Override
	public void start_game()
	{
		superComponent.start_game();
		
	}

	@Override
	public void exit()
	{
		superComponent.exit();
		
	}	
	
	public void showError()
	{
		showError = true;
	}
	public void dontShowError()
	{
		showError = false;
	}
	
	public String objectToString(Object ob)
	{
		return ob.toString();
	}
	
	public void printObject(Object object)
	{
		console.message(object.toString());
	}
	
	public NetplayMessage makeDisconnectMessage()
	{
		return new DisconnectMessage();
	}
	public NetplayMessage makeFinishMessage()
	{
		return new FinishMessage();
	}
	public NetplayMessage makeJoinListMessage()
	{
		return new JoinListMessage();
	}
	public NetplayMessage makeReadyMessage()
	{
		return new ReadyMessage();
	}
	public NetplayMessage makeStartMessage()
	{
		return new StartMessage();
	}
	
	private void message(String str)
	{
		if(showError) console.message(str);
	}
	private void messageNotError(String str)
	{
		console.message(str);
	}
	
	public String getIpAtServer()
	{
		String ip = null;
		
		try
		{			
			ip = serverSocket.getInetAddress().toString();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
		
		return ip;
	}
	
	public String getIp()
	{
		String ip = null;
		
		try
		{			
			ip = socket.getInetAddress().toString();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
		
		return ip;
	}
	
	public String getLocalIp()
	{
		String ip = null;
		
		try
		{			
			ip = socket.getLocalAddress().toString();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
		
		return ip;
	}
	
	public void send(Object object)
	{
		try
		{
			objectOutputStream.writeObject(object);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
	}
	
	public Object receive()
	{
		Object ob = null;
		try
		{
			ob = objectInputStream.readObject();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
		return ob;
	}
	
	public void host(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
	}
	public void accept()
	{
		try
		{
			socket = serverSocket.accept();
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			console.message(socket.getInetAddress().toString() + " connected.");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
	}
	public void connect(String ip, int port)
	{
		try
		{
			socket = new Socket(ip, port);
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			console.message("Connect " + ip + ", " + String.valueOf(port) + " success.");
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			message(e.getMessage());
		}
	}
	public void closeAll()
	{
		try
		{
			objectInputStream.close();
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			objectOutputStream.close();
		}
		catch (Exception e)
		{
			
		}
		try
		{
			socket.close();
		} 
		catch (Exception e)
		{
			
		}
		try
		{
			serverSocket.close();
		} 
		catch (Exception e)
		{
			
		}
	}

	@Override
	public void selectAuthorizeCheckbox()
	{
		superComponent.selectAuthorizeCheckbox();
		
	}

	@Override
	public void selectPlayerSetting(int index, int value)
	{
		superComponent.selectPlayerSetting(index, value);
		
	}
	@Override
	public Setting getSetting()
	{
		return sets;
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Control(superComponent, showable);
	}
	@Override
	public String title()
	{
		return "control";
	}	
}
