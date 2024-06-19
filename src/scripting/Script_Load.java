package scripting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

import lang.Language;
import main_classes.MessageShowable;
import setting.ExtendedScenario;
import setting.Scenario;
import setting.Setting;

public class Script_Load implements Helpable
{
	private Setting sets;
	private MessageShowable showable;
	public Script_Load(Setting sets, MessageShowable sh)
	{
		this.sets = sets;
		this.showable = sh;
	}
	public void save_scenario(int index, String path)
	{
		String saves = null;
		FileWriter fwriter = null;
		BufferedWriter bwriter = null;
		try
		{
			saves = Scenario.scenarioToString((ExtendedScenario) sets.getScenarios().get(index));
			
			File dirCheck = new File(sets.getDefault_path());
			if(! dirCheck.exists())
				dirCheck.mkdir();
			
			fwriter = new FileWriter(sets.getDefault_path() + path);
			bwriter = new BufferedWriter(fwriter);
			bwriter.write(saves);
			
			showable.message(sets.getScenarios().get(index).getName() + " " + sets.getLang().getText(Language.SAVE) + " : " + sets.getDefault_path() + path);
		} 
		catch (Exception e)
		{
			showable.message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
		}
		try
		{
			bwriter.close();			
		}
		catch(Exception e)
		{
			
		}
		try
		{
			fwriter.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public void load_scenario(String path)
	{
		String gets = "";
		String accumulated = "";
		
		ExtendedScenario readed = null;
		
		InputStreamReader inpReader = null;
		FileReader freader = null;
		BufferedReader bfreader = null;		
		URL url = null;
		
		try
		{			
			if(path.startsWith("http:") || path.startsWith("ftp:"))
			{
				url = new URL(path);
				inpReader = new InputStreamReader(url.openStream());
				bfreader = new BufferedReader(inpReader);
			}
			else
			{
				freader = new FileReader(path);
				bfreader = new BufferedReader(freader);
			}
			
			while(true)
			{
				gets = bfreader.readLine();
				if(gets == null) break;
				accumulated = accumulated + gets + "\n";
			}
			readed = Scenario.stringToScenario(accumulated);
			if(readed != null)
			{
				sets.getScenarios().add(readed);
				showable.message(sets.getLang().getText(Language.LOAD) + " : " + readed.getName());
				showable.message(sets.getLang().getText(Language.NEED_RESTART));
			}
		} 
		catch (Exception e)
		{
			showable.message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
		}
		try
		{
			bfreader.close();
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
		try
		{			
			inpReader.close();
		} 
		catch (Exception e)
		{
			
		}
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
				showable.message("===== loader commands =====");
				showable.message();
				showable.message("load_scenario(_i) : _i 파일(텍스트 파일)로부터 시나리오를 불러옵니다. _i에는 전체 절대경로를 포함해야 합니다.");
				showable.message("  \t웹 상의 파일로부터 불러오려면 _i는 \"http:\" 또는 \"ftp:\" 로 시작해야 합니다.");
				showable.message("save_scenario(_i, _j) : _i 번째 시나리오를 _j 파일 이름으로 저장합니다. 홈 폴더 내 calc 폴더에 저장됩니다.");
				showable.message();
				showable.message_bar();
				break;
			default:
				showable.message("===== loader commands =====");
				showable.message();
				showable.message("load_scenario(_i) : Load scenario from file _i. _i should includes absolute path.");
				showable.message("  \tThis function can load scenario from web. In this case, _i should starts \"http:\" or \"ftp:\".");
				showable.message("save_scenario(_i, _j) : Save _i \'th scenario as text file. File name will be _j. The file will saved at \"calc\" directory (in home directory).");
				showable.message();
				showable.message_bar();
				//showable.message("I\'m sorry that there is only korean helps for chat commands.");
				//help(1);
				break;
		}
	}
	@Override
	public Helpable newHelp(MessageShowable showable)
	{
		return new Script_Load(sets, showable);
	}
	@Override
	public String title()
	{
		return "loader";
	}
}
