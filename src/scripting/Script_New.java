package scripting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

import main_classes.MessageShowable;
import setting.Lint;
import setting.Setting;

public class Script_New implements Helpable
{
	public static final int TYPE_INT = 0;
	public static final int TYPE_LONG = 1;
	public static final int TYPE_DOUBLE = 2;
	public static final int TYPE_CHAR = 3;
	public static final int TYPE_BOOL = 4;
	public static final int TYPE_BYTE = 5;
	public static final int TYPE_STRING = 10;
	public static final int TYPE_BIGINT = 11;
	public static final int TYPE_BIGDEC = 12;
	public static final int TYPE_FILE = 13;
	public static final int TYPE_URL = 14;
	public static final int TYPE_URI = 15;
	public static final int TYPE_INPUTSTREAM = 60;
	public static final int TYPE_OUTPUTSTREAM = 70;
	public static final int TYPE_READER = 80;
	public static final int TYPE_WRITER = 90;
	public static final int TYPE_OBJECT = 1000;
	
	private MessageShowable message = null;
	
	public Script_New()
	{
		
	}
	public Script_New(MessageShowable message)
	{
		this.message = message;
	}
	public Object array(int size)
	{
		return new Object[size];
	}
	public Object array(int type, int size) throws Exception
	{
		Object ob = null;
		switch(type)
		{
			case TYPE_INT:
				ob = new int[size];
				for(int i=0; i<((int[]) ob).length; i++)
				{
					((int[]) ob)[i] = 0;
				}
				break;
			case TYPE_LONG:
				ob = new long[size];
				for(int i=0; i<((long[]) ob).length; i++)
				{
					((long[]) ob)[i] = 0;
				}
				break;
			case TYPE_DOUBLE:
				ob = new double[size];
				for(int i=0; i<((double[]) ob).length; i++)
				{
					((double[]) ob)[i] = 0.0;
				}
				break;
			case TYPE_CHAR:
				ob = new char[size];
				for(int i=0; i<((char[]) ob).length; i++)
				{
					((char[]) ob)[i] = ' ';
				}
				break;
			case TYPE_BOOL:
				ob = new boolean[size];
				for(int i=0; i<((boolean[]) ob).length; i++)
				{
					((boolean[]) ob)[i] = false;
				}
				break;	
			case TYPE_BYTE:
				ob = new byte[size];
				for(int i=0; i<((byte[]) ob).length; i++)
				{
					((byte[]) ob)[i] = 0;
				}
				break;	
			case TYPE_STRING:
				ob = new String[size];
				for(int i=0; i<((String[]) ob).length; i++)
				{
					((String[]) ob)[i] = "";
				}
				break;
			case TYPE_BIGINT:
				ob = new BigInteger[size];
				for(int i=0; i<((BigInteger[]) ob).length; i++)
				{
					((BigInteger[]) ob)[i] = Lint.big(0);
				}
				break;
			case TYPE_BIGDEC:
				ob = new BigDecimal[size];
				for(int i=0; i<((BigDecimal[]) ob).length; i++)
				{
					((BigDecimal[]) ob)[i] = new BigDecimal(String.valueOf(0.0));
				}
				break;
			case TYPE_FILE:
				ob = new File[size];
				for(int i=0; i<((File[]) ob).length; i++)
				{
					((File[]) ob)[i] = new File(String.valueOf(0.0));
				}
				break;
			case TYPE_URL:
				ob = new URL[size];
				for(int i=0; i<((URL[]) ob).length; i++)
				{
					((URL[]) ob)[i] = new URL(String.valueOf(0.0));
				}
				break;
			case TYPE_URI:
				ob = new URI[size];
				for(int i=0; i<((URI[]) ob).length; i++)
				{
					((URI[]) ob)[i] = new URI(String.valueOf(0.0));
				}
				break;
			case TYPE_INPUTSTREAM:
				ob = new InputStream[size];
				for(int i=0; i<((InputStream[]) ob).length; i++)
				{
					((InputStream[]) ob)[i] = null;
				}
				break;
			case TYPE_OUTPUTSTREAM:
				ob = new OutputStream[size];
				for(int i=0; i<((OutputStream[]) ob).length; i++)
				{
					((OutputStream[]) ob)[i] = null;
				}
				break;
			case TYPE_READER:
				ob = new Reader[size];
				for(int i=0; i<((Reader[]) ob).length; i++)
				{
					((Reader[]) ob)[i] = null;
				}
				break;
			case TYPE_WRITER:
				ob = new Writer[size];
				for(int i=0; i<((Writer[]) ob).length; i++)
				{
					((Writer[]) ob)[i] = null;
				}
				break;
			case TYPE_OBJECT:
				ob = new Object[size];
				for(int i=0; i<((Object[]) ob).length; i++)
				{
					((Object[]) ob)[i] = null;
				}
				break;				
			default:
				ob = new Object[size];
				for(int i=0; i<((Object[]) ob).length; i++)
				{
					((Object[]) ob)[i] = null;
				}
				break;
		}
		return ob;
	}
	
	public Object parse(int type, String value) throws Exception
	{
		Object ob = null;
		switch(type)
		{
			case TYPE_INT:
				ob = new Integer(Integer.parseInt(value));
				break;
			case TYPE_LONG:
				ob = new Long(Long.parseLong(value));
				break;
			case TYPE_DOUBLE:
				ob = new Double(Double.parseDouble(value));
				break;
			case TYPE_CHAR:
				ob = new Character(value.toCharArray()[0]);
				break;
			case TYPE_BOOL:
				ob = new Boolean(Boolean.parseBoolean(value));
				break;	
			case TYPE_BYTE:
				ob = new Byte(Byte.parseByte(value));
				break;	
			case TYPE_STRING:
				ob = new String(value);
				break;
			case TYPE_BIGINT:
				ob = Lint.big(value);
				break;
			case TYPE_BIGDEC:
				ob = new BigDecimal(value);
				break;
			case TYPE_FILE:
				ob = new File(value);
				break;
			case TYPE_OBJECT:
				ob = new String(value);
				break;
			case TYPE_URL:
				ob = new URL(value);
				break;
			case TYPE_URI:
				ob = new URI(value);
				break;
			default:
				ob = new String(value);
				break;
		}
		return ob;
	}
	public Object vector(int type)
	{
		Object ob = null;
		switch(type)
		{
			case TYPE_INT:
				ob = new Vector<Integer>();
				break;
			case TYPE_LONG:
				ob = new Vector<Long>();
				break;
			case TYPE_DOUBLE:
				ob = new Vector<Double>();
				break;
			case TYPE_CHAR:
				ob = new Vector<Character>();
				break;
			case TYPE_BOOL:
				ob = new Vector<Boolean>();
				break;	
			case TYPE_BYTE:
				ob = new Vector<Byte>();
				break;	
			case TYPE_STRING:
				ob = new Vector<String>();
				break;
			case TYPE_BIGINT:
				ob = new Vector<BigInteger>();
				break;
			case TYPE_BIGDEC:
				ob = new Vector<BigDecimal>();
				break;
			case TYPE_FILE:
				ob = new Vector<File>();
				break;
			case TYPE_URI:
				ob = new Vector<URI>();
				break;
			case TYPE_URL:
				ob = new Vector<URL>();
				break;
			case TYPE_INPUTSTREAM:
				ob = new Vector<InputStream>();
				break;
			case TYPE_OUTPUTSTREAM:
				ob = new Vector<OutputStream>();
				break;
			case TYPE_READER:
				ob = new Vector<Reader>();
				break;
			case TYPE_WRITER:
				ob = new Vector<Writer>();
				break;
			case TYPE_OBJECT:
				ob = new Vector<Object>();
				break;
			default:
				ob = new Vector<Object>();
				break;
		}
		return ob;
	}
	public boolean isType(int type, Object ob)
	{
		switch(type)
		{
			case TYPE_INT:
				return (ob instanceof Integer);
			case TYPE_LONG:
				return (ob instanceof Long);
			case TYPE_DOUBLE:
				return (ob instanceof Double);
			case TYPE_CHAR:
				return (ob instanceof Character);
			case TYPE_BOOL:
				return (ob instanceof Boolean);
			case TYPE_BYTE:
				return (ob instanceof Byte);
			case TYPE_STRING:
				return (ob instanceof String);
			case TYPE_BIGINT:
				return (ob instanceof BigInteger);
			case TYPE_BIGDEC:
				return (ob instanceof BigDecimal);
			case TYPE_FILE:
				return (ob instanceof File);
			case TYPE_URI:
				return (ob instanceof URI);
			case TYPE_URL:
				return (ob instanceof URL);
			case TYPE_INPUTSTREAM:
				return (ob instanceof InputStream);
			case TYPE_OUTPUTSTREAM:
				return (ob instanceof OutputStream);
			case TYPE_READER:
				return (ob instanceof Reader);
			case TYPE_WRITER:
				return (ob instanceof Writer);	
			case TYPE_OBJECT:
				return true;
			default:
				return false;
		}
	}
	public Vector<Object> vector()
	{
		return new Vector<Object>();
	}
	public boolean isNull(int ob)
	{
		return false;
	}
	public boolean isNull(double ob)
	{
		return false;
	}
	public boolean isNull(byte ob)
	{
		return false;
	}
	public boolean isNull(char ob)
	{
		return false;
	}
	public boolean isNull(boolean ob)
	{
		return false;
	}
	public boolean isNull(long ob)
	{
		return false;
	}
	public boolean isNull(Object ob)
	{
		return (ob == null);
	}
	public double random(double max)
	{
		return Math.random() * max;
	}
	public double random()
	{
		return Math.random();
	}
	public Object none()
	{
		return null;
	}
	public URL url(String url) throws MalformedURLException
	{
		return new URL(url);
	}
	public URI uri(String uri) throws URISyntaxException
	{
		return new URI(uri);
	}
	public Integer wrap(int value)
	{
		return new Integer(value);
	}
	public Byte wrap(byte value)
	{
		return new Byte(value);
	}
	public Long wrap(long value)
	{
		return new Long(value);
	}
	public Double wrap(double value)
	{
		return new Double(value);
	}
	public Character wrap(char value)
	{
		return new Character(value);
	}
	public Boolean wrap(boolean value)
	{
		return new Boolean(value);
	}
	public Object wrap(Object ob)
	{
		return ob;
	}
	public String newString(Object ob)
	{
		if(ob == null) return "null";
		else if(ob instanceof char[]) return new String(((char[]) ob));
		else if(ob instanceof byte[]) return new String(((byte[]) ob));
		else return String.valueOf(ob);
	}
	public String string(byte[] obj, String charType) throws UnsupportedEncodingException
	{
		return new String(obj, charType);
	}		
	public String string(Object obj)
	{
		if(obj == null) return "null";
		else if(obj instanceof char[])
			return new String(((char[]) obj));
		else if(obj instanceof byte[])
			return new String(((byte[]) obj));
		else return String.valueOf(obj);
	}
	public String string(char obj)
	{
		return String.valueOf(obj);
	}
	public String string(boolean obj)
	{
		return String.valueOf(obj);
	}
	public String string(byte obj)
	{
		return String.valueOf(obj);
	}
	public String string(int obj)
	{
		return String.valueOf(obj);
	}
	public String string(long obj)
	{
		return String.valueOf(obj);
	}
	public String string(double obj)
	{
		return String.valueOf(obj);
	}
	public String typeof(Object obj)
	{
		if(obj == null)
		{
			return "null";
		}
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
		else if(obj instanceof File)
		{
			return "File";
		}
		else
			return obj.getClass().toString();
	}
	public String typeof(byte value)
	{
		return "byte";
	}
	public String typeof(int value)
	{
		return "int";
	}
	public String typeof(long value)
	{
		return "long";
	}
	public String typeof(double value)
	{
		return "double";
	}
	public String typeof(boolean value)
	{
		return "boolean";
	}
	public String typeof(char value)
	{
		return "char";
	}
	public File file(String path)
	{
		return new File(path);
	}
	public BigInteger big(int value)
	{
		return Lint.big(value);
	}
	public BigInteger big(long value)
	{
		return Lint.big(value);
	}
	public StringTokenizer stringTokenizer(String str)
	{
		return new StringTokenizer(str);
	}
	public StringTokenizer stringTokenizer(String str, String delimiter)
	{
		return new StringTokenizer(str, delimiter);
	}
	public BigDecimal big(double value)
	{
		return new BigDecimal(String.valueOf(value));
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
	public BigInteger convert(int value)
	{
		return Lint.big(value);
	}
	public long convert(BigInteger value)
	{
		return value.longValue();
	}
	public Reader reader(InputStream inputStream)
	{
		return new InputStreamReader(inputStream);
	}
	public Writer writer(OutputStream outputStream)
	{
		return new OutputStreamWriter(outputStream);
	}
	public BufferedReader buffered(Reader reader)
	{
		return new BufferedReader(reader);
	}
	public BufferedWriter buffered(Writer writer)
	{
		return new BufferedWriter(writer);
	}
	public FileInputStream fileIn(String path) throws FileNotFoundException
	{
		FileInputStream results = null;
		if(path.startsWith(Setting.getNewInstance().getDefault_path())) results = new FileInputStream(path);
		return results;
	}
	public FileInputStream fileIn(File path) throws FileNotFoundException
	{
		FileInputStream results = null;
		if(path.getAbsolutePath().startsWith(Setting.getNewInstance().getDefault_path())) results = new FileInputStream(path);
		return results;
	}
	public FileOutputStream fileOut(String path) throws FileNotFoundException
	{
		FileOutputStream results = null;
		if(path.startsWith(Setting.getNewInstance().getDefault_path())) results = new FileOutputStream(path);
		return results;
	}
	public FileOutputStream fileOut(File path) throws FileNotFoundException
	{
		FileOutputStream results = null;
		if(path.getAbsolutePath().startsWith(Setting.getNewInstance().getDefault_path())) results = new FileOutputStream(path);
		return results;
	}
	public ObjectOutputStream objectIO(OutputStream out) throws IOException
	{
		return new ObjectOutputStream(out);
	}
	public ObjectInputStream objectIO(InputStream in) throws IOException
	{
		return new ObjectInputStream(in);
	}
	public void message()
	{
		message.message();
	}
	public void message_bar()
	{
		message.message_bar();
	}
	public void message(String str)
	{
		message.message(str);
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
		message();
		message("===== Type - Define  Constants =====");
		message("Name\t\t Value\t Type\t\t Wrapper");
		message("TYPE_INT\t\t 0\t int\t\t Integer");
		message("TYPE_LONG\t\t 1\t long\t\t Long");
		message("TYPE_DOUBLE\t\t 2\t double\t\t Double");
		message("TYPE_CHAR\t\t 3\t char\t\t Character");
		message("TYPE_BOOL\t\t 4\t boolean\t\t Boolean");
		message("TYPE_BYTE\t\t 5\t byte\t\t Byte");
		message("TYPE_STRING\t\t 10\t String\t\t String");
		message("TYPE_BIGINT\t\t 11\t BigInteger\t\t BigInteger");
		message("TYPE_BIGDEC\t\t 12\t BigDecimal\t\t BigDecimal");
		message("TYPE_FILE\t\t 13\t File\t\t File");
		message("TYPE_URL\t\t 14\t URL\t\t URL");
		message("TYPE_URI\t\t 15\t URI\t\t URI");
		message("TYPE_INPUTSTREAM\t 60\t InputStream\t\t InputStream");
		message("TYPE_OUTPUTSTREAM\t 70\t OutputStream\t\t OutputStream");
		message("TYPE_READER\t\t 80\t Reader\t\t Reader");
		message("TYPE_WRITER\t\t 90\t Writer\t\t Writer");
		message("TYPE_OBJECT\t\t 1000\t Object\t\t Object");
		message();
		switch(lang)
		{
			case 1:
				message("===== newer commands =====");
				message();
				message("array(_i) : 배열을 _j 사이즈로 만들어 반환합니다.");
				message("array(_i, _j) : _i 타입의 배열을 _j 사이즈로 만들어 반환합니다.");				
				message("parse(_i, _j) : _j 문자열을 _i 타입으로 변환해 반환합니다.");
				message("vector(_i) : _i 타입의 가변 배열(Vector)을 반환합니다.");
				message("isNull(_i) : _i 이 null 를 참조하는지 검사하여 그 결과를 boolean 으로 반환합니다.");
				message("none() : 항상 null 을 반환합니다.");
				message("url(_i) : _i 문자열을 URL 객체로 반환합니다. URL 객체는 openStream() 메소드가 있어 InputStream 객체를 만들 수 있습니다.");
				message("uri(_i) : _i 문자열을 URI 객체로 반환합니다.");
				message("wrap(_i) : _i 타입에 맞는 Wrapper 클래스 객체를 반환합니다. 지원하지 않는 객체는 그대로 반환합니다.");
				message("string(_i) : _i를 문자열로 변환해 반환합니다."
						+ "\n\t _i에 byte형 배열이나 char형 배열을 사용하면 모호성 문제가 생길 수 있습니다."
						+ "\n\t 이 경우 newString(_i) 메소드를 대신 사용하십시오.");
				message("string(_i, _j) : _i에 있는 byte형 배열을 _j 문자열 세트를 이용해 문자열로 변환해 반환합니다.");
				message("typeof(_i) : _i의 타입 이름을 문자열로 반환합니다."
						+ "\n\t이 메소드 사용은 권장하지 않으며, 대신 isType(_i, _j) 을 사용하십시오.");
				message("isType(_i, _j) : _j 객체가 _i 에 해당하는 타입이면 true를 반환합니다. 객체에 대해서만 검사가 가능합니다.");
				message("file(_i) : _i 문자열을 파일 경로로 하는 파일 객체를 반환합니다.");
				message("big(_i) : _i가 정수값(int, long) 이면 BigInteger 객체로, 실수값(double) 이면 BigDecimal 객체로 변환해 반환합니다.");
				message("stringTokenizer(_i, _d) : 문자열 _i 를 _d 구분자로 나누는 기능을 하는 StringTokenizer 객체를 반환합니다.");
				message("convert(_i) : double 값은 BigDecimal로, long 값은 BigInteger로 반환합니다. 반대로도 사용 가능합니다.");
				message("reader(_i) : _i 에 InputStream 객체를 받아 Reader 객체를 반환합니다.");
				message("writer(_i) : _i 에 있는 OutputStream 객체에 대응하는 Writer 객체를 반환합니다.");
				message("buffered(_i) : _i 에 있는 Reader/Writer 객체에 대응하는 BufferedReader/BufferedWriter 객체를 반환합니다. ");
				message("objectIO(_i) : _i 에 있는 InputStream/OutputStream 객체에 대응하는 ObjectInputStream/ObjectOutputStream 객체를 반환합니다. ");
				message();
				message_bar();
				break;
			default:
				message("===== newer commands =====");
				message();
				message("array(_i) : Return array as _i size");
				message("array(_i, _j) : Return _i type array as _j size.");				
				message("parse(_i, _j) : Convert _j String to _i type value.");
				message("vector(_i) : Return _i type Vector Object.");
				message("isNull(_i) : Return true if _i is null.");
				message("none() : Return null.");
				message("url(_i) : Return URL object as _i String.");
				message("uri(_i) : Return URI object as _i String");
				message("wrap(_i) : Return wrapper object as _i type.");
				message("string(_i) : Return String converted by _i value.");
				message("\t If you use byte[] or char[], ambiguous problem may be occured.");
				message("\t In these case, you can use newString(_i) method instead of string(_i).");
				message("string(_i, _j) : Return String converted by _i byte - array and _j character set.");
				message("typeof(_i) : Return _i type name as String. (Not recommended)");
				message("isType(_i, _j) : If _j object is _i type, return true. Only for objects.");
				message("file(_i) : Return _i - path File object.");
				message("big(_i) : Convert _i to big object. long --> BigInteger, double --> BigDecimal.");
				message("stringTokenizer(_i, _d) : Return StringTokenizer, _i is target String, _d is delimiter.");
				message("convert(_i) : Convert double <--> BigDecimal, long <--> BigInteger.");
				message();
				message_bar();
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_New(showable);
	}
	@Override
	public String title()
	{
		return "newer";
	}
}
