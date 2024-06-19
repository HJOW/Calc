package first_run;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;

import main_classes.CalcWindow;


public class SettingForRun implements Serializable, Cloneable
{
	private static final long serialVersionUID = -914382946270696673L;
	
	private String exec_path, default_path, separator, basic_url, basic_url2;
	private Integer ver_main, ver_sub1, ver_sub2;
	private Character ver_test;
	private Dimension screenSize;
	private SetLang lang;
	private Boolean always_download;
	private Long authorize_code;
	
	public SettingForRun()
	{
		default_path = System.getProperty("user.home");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + "calc" + separator;
		exec_path = default_path + "calc_game.jar";
		basic_url = "http://hujinone11.oranc.co.kr/calc/";
		basic_url2 = "http://netstorm.woobi.co.kr/calc/";
		ver_main = new Integer(CalcWindow.version_main);
		ver_sub1 = new Integer(CalcWindow.version_sub_1);
		ver_sub2 = new Integer(CalcWindow.version_sub_2);
		ver_test = new Character(' ');
		always_download = new Boolean(false);
		authorize_code = new Long(0);
		String lang_loc = System.getProperty("user.language");

		if(lang_loc.startsWith("en") || lang_loc.startsWith("EN") || lang_loc.startsWith("eng") || lang_loc.startsWith("ENG")) lang = new SetEng();
		else if(lang_loc.startsWith("ko") || lang_loc.startsWith("KO") || lang_loc.startsWith("kr") || lang_loc.startsWith("KR") || lang_loc.startsWith("kor") || lang_loc.startsWith("KOR")) lang = new SetKor();
		else lang = new SetEng();
	}
	public void refresh_authorize()
	{
		long authorize_result = 0;			
		long basic_url_length = getStringToCode(basic_url);
		long exec_path_length = getStringToCode(exec_path);
		long default_path_length = getStringToCode(default_path);
		long separator_length = getStringToCode(separator);
		long ver_value = 0;
		
		ver_value += ver_main.longValue() * 100;
		ver_value += ver_sub1.longValue() * 10;
		ver_value += ver_sub2.longValue();
		
		authorize_result += (basic_url_length * 3 + exec_path_length * 4 + default_path_length * 5) * (separator_length * 3 + ver_value + 2);
		if(always_download) authorize_result++;
		
		authorize_code = new Long(authorize_result);
	}
	public boolean isAuthorized()
	{
		long authorize_result = 0;			
		long basic_url_length = getStringToCode(basic_url);
		long exec_path_length = getStringToCode(exec_path);
		long default_path_length = getStringToCode(default_path);
		long separator_length = getStringToCode(separator);
		long ver_value = 0;
		
		ver_value += ver_main.longValue() * 100;
		ver_value += ver_sub1.longValue() * 10;
		ver_value += ver_sub2.longValue();
		
		authorize_result += (basic_url_length * 3 + exec_path_length * 4 + default_path_length * 5) * (separator_length * 3 + ver_value + 2);
		if(always_download) authorize_result++;
		
		return (authorize_code.longValue() == authorize_result);
	}
	public String getExec_path()
	{
		return new String(exec_path);
	}

	public void setExec_path(String exec_path)
	{
		this.exec_path = exec_path;
	}

	public Integer getVer_main()
	{
		return ver_main;
	}

	public void setVer_main(Integer ver_main)
	{
		this.ver_main = ver_main;
	}

	public Integer getVer_sub1()
	{
		return ver_sub1;
	}

	public void setVer_sub1(Integer ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}

	public Integer getVer_sub2()
	{
		return ver_sub2;
	}

	public void setVer_sub2(Integer ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}

	public Character getVer_test()
	{
		return ver_test;
	}

	public void setVer_test(Character ver_test)
	{
		this.ver_test = ver_test;
	}

	public Dimension getScreenSize()
	{
		return screenSize;
	}
	public void setScreenSize(Dimension screenSize)
	{
		this.screenSize = screenSize;
	}
	public String getDefault_path()
	{
		return new String(default_path);
	}
	public void setDefault_path(String default_path)
	{
		this.default_path = default_path;
	}
	public String getSeparator()
	{
		return new String(separator);
	}
	public void setSeparator(String separator)
	{
		this.separator = separator;
	}
	public SetLang getLang()
	{
		return lang;
	}
	public void setLang(SetLang lang)
	{
		this.lang = lang;
	}
	public String getBasic_url()
	{
		return basic_url;
	}
	public void setBasic_url(String basic_url)
	{
		this.basic_url = basic_url;
	}
	public Boolean getAlways_download()
	{
		return always_download;
	}
	public void setAlways_download(Boolean always_download)
	{
		this.always_download = always_download;
	}
	public Long getAuthorize_code()
	{
		return authorize_code;
	}
	public void setAuthorize_code(Long authorize_code)
	{
		this.authorize_code = authorize_code;
	}
	public long getStringToCode(String str)
	{
		char[] strArray = str.toCharArray();
		long results = 0;
		for(int i=0; i<strArray.length; i++)
		{
			results += ((int) strArray[i]);
		}
		results += strArray.length;
		
		return results;
	}
	public String getBasic_url2()
	{
		return basic_url2;
	}
	public void setBasic_url2(String basic_url2)
	{
		this.basic_url2 = basic_url2;
	}
	public SettingForRun clone()
	{
		SettingForRun newOne = new SettingForRun();
		newOne.exec_path = new String(this.exec_path);
		newOne.default_path = new String(this.default_path);
		newOne.separator = new String(this.separator);
		newOne.basic_url = new String(this.basic_url);
		newOne.basic_url2 = new String(this.basic_url2);
		newOne.ver_main = new Integer(this.ver_main.intValue());
		newOne.ver_sub1 = new Integer(this.ver_sub1.intValue());
		newOne.ver_sub2 = new Integer(this.ver_sub2.intValue());
		newOne.ver_test = new Character(this.ver_test.charValue());
		newOne.authorize_code = new Long(this.authorize_code.longValue());
		newOne.always_download = new Boolean(this.always_download.booleanValue());
		newOne.screenSize = new Dimension(screenSize);
		newOne.lang = this.lang.clone();
		return newOne;
	}
}
