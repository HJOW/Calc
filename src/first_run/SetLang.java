package first_run;
import java.io.Serializable;


public abstract class SetLang implements Serializable, Cloneable
{
	private static final long serialVersionUID = 270905954922418178L;
	
	private String check_version, downloading, try_run, failed, new_version_exist, already_newVersion, completed, get_location, authorizing, ask_run_download;
	
	

	public String getCheck_version()
	{
		return check_version;
	}

	public void setCheck_version(String check_version)
	{
		this.check_version = check_version;
	}

	public String getDownloading()
	{
		return downloading;
	}

	public void setDownloading(String downloading)
	{
		this.downloading = downloading;
	}

	public String getTry_run()
	{
		return try_run;
	}

	public void setTry_run(String try_run)
	{
		this.try_run = try_run;
	}

	public String getFailed()
	{
		return failed;
	}

	public void setFailed(String failed)
	{
		this.failed = failed;
	}
	public String getNew_version_exist()
	{
		return new_version_exist;
	}

	public void setNew_version_exist(String new_version_exist)
	{
		this.new_version_exist = new_version_exist;
	}

	public String getAlready_newVersion()
	{
		return already_newVersion;
	}

	public void setAlready_newVersion(String already_newVersion)
	{
		this.already_newVersion = already_newVersion;
	}

	public String getCompleted()
	{
		return completed;
	}

	public void setCompleted(String completed)
	{
		this.completed = completed;
	}

	public String getGet_location()
	{
		return get_location;
	}

	public void setGet_location(String get_location)
	{
		this.get_location = get_location;
	}

	public String getAuthorizing()
	{
		return authorizing;
	}

	public void setAuthorizing(String authorizing)
	{
		this.authorizing = authorizing;
	}

	public String getAsk_run_download()
	{
		return ask_run_download;
	}

	public void setAsk_run_download(String ask_run_download)
	{
		this.ask_run_download = ask_run_download;
	}

	public abstract SetLang clone();
}
