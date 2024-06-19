package scripting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import main_classes.MessageShowable;
import setting.Setting;

public class Script_File implements Helpable
{
	private MessageShowable console = null;
	
	public Script_File(MessageShowable console)
	{
		this.console = console;
	}
	
	public void write(String str, String fileName)
	{
		String default_path = System.getProperty("user.home");
		String separator = System.getProperty("file.separator");		
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + "calc" + separator;
		if(fileName.endsWith("config.cfg") || fileName.endsWith("CONFIG.cfg") || fileName.endsWith("Config.cfg")
				|| fileName.endsWith("config.CFG") || fileName.endsWith("CONFIG.CFG") || fileName.endsWith("Config.CFG"))
		{
			console.message("Cannot edit config.cfg on script !!");
			return;
		}
		try
		{
			FileOutputStream fileoutputStream = new FileOutputStream(default_path + fileName);
			OutputStreamWriter outputWriter = new OutputStreamWriter(fileoutputStream);
			BufferedWriter bufferWriter = new BufferedWriter(outputWriter);
			bufferWriter.write(str);
			bufferWriter.close();
			outputWriter.close();
			fileoutputStream.close();			
		} 
		catch (Exception e)
		{			
			e.printStackTrace();
			console.message(e.getMessage());
		}
	}
	public String read(String fileName)
	{
		String default_path = System.getProperty("user.home");
		String separator = System.getProperty("file.separator");		
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + "calc" + separator;
		String readed = "";
		String result = "";
		int limit = 0;
		try
		{
			FileInputStream fileinputStream = new FileInputStream(default_path + fileName);
			InputStreamReader inputWriter = new InputStreamReader(fileinputStream);
			BufferedReader bufferReader = new BufferedReader(inputWriter);
			
			while(true)
			{
				readed = bufferReader.readLine();
				if(readed == null) break;
				limit++;
				if(limit >= 10000) break;
				result += readed;
			}
			bufferReader.close();
			inputWriter.close();
			fileinputStream.close();			
		} 
		catch (Exception e)
		{			
			e.printStackTrace();
			console.message(e.getMessage());
			result = null;
		}
		return result;
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
	public void help()
	{
		String lang = System.getProperty("user.language");
		if(lang.equalsIgnoreCase("english") || lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("eng")) help(0);
		else if(lang.equalsIgnoreCase("korean") || lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr")) help(1);
		else help(0);
	}
	public String defaultPath()
	{
		return Setting.getNewInstance().getDefault_path();
	}
	public String fileSeparator()
	{
		return Setting.getNewInstance().getSeparator();
	}
	public void help(int lang)
	{		
		switch(lang)
		{
			case 1:
				console.message("===== file commands =====");
				console.message();
				console.message("read(_i) : 홈 폴더의 calc 폴더에 있는 _i 이름의 파일을 읽어 내용을 문자열로 반환합니다.");
				console.message("write(_i, _j) : 홈 폴더의 calc 폴더에 _i 내용을 _j 파일 이름으로 저장합니다.");
				console.message("fileIn(_i) : _i 파일 또는 _i 파일 경로에 대한 파일 InputStream을 만들어 반환합니다."
						+ "\n\t 홈 폴더의 calc 폴더 안으로만 경로를 지정해야 하며, 그렇지 않으면 null 을 반환합니다.");
				console.message("fileOut(_i) : _i 파일 또는 _i 파일 경로에 대한 파일 OutputStream을 만들어 반환합니다."
						+ "\n\t 홈 폴더의 calc 폴더 안으로만 경로를 지정해야 하며, 그렇지 않으면 null 을 반환합니다.");
				console.message("defaultPath() : 홈 폴더의 calc 폴더 경로를 문자열로 반환합니다.");
				console.message("fileSeparator() : 폴더 구분 기호를 반환합니다. 운영체제에 따라 다릅니다.");
				console.message();
				console.message_bar();
				break;
			default:
				console.message("===== file commands =====");
				console.message();
				console.message("read(_i) : Load texts at _i file and return. _i should be located at \"calc\" directory (in home directory).");
				console.message("write(_i, _j) : Save _i as texts at file _j. _j should be located at \"calc\" directory (in home directory).");
				console.message("defaultPath() : Return home - calc folder full path.");
				console.message("fileSeparator() : Return directory separator simbol. This is belong to OS.");
				console.message();
				console.message_bar();
				break;
		}
	}

	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_File(showable);
	}

	@Override
	public String title()
	{
		return "file";
	}
}
