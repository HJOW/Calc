package first_run;

public class SetEng extends SetLang
{
	private static final long serialVersionUID = -1973824267365524776L;

	public SetEng()
	{
		setTry_run("Trying to run game.");
		setCheck_version("Checking new version of this game.");
		setDownloading("Downloading new version of this game.");
		setFailed("There are some problems : ");
		setNew_version_exist("New version available. Do you want to download? (New version : ");
		setAlready_newVersion("This program is already newest version. Now the game will be run.");
		setCompleted("Download newest version of this game at : ");
		setGet_location("Access this url to download newest version : ");
		setAuthorizing("Authorization");
		setAsk_run_download("Do you want to run this game with downloaded file?");
	}
	
	@Override
	public SetLang clone()
	{
		SetEng newOne = new SetEng();
		newOne.setTry_run(new String(getTry_run()));
		newOne.setCheck_version(new String(getCheck_version()));
		newOne.setDownloading(new String(getDownloading()));
		newOne.setFailed(new String(getFailed()));
		newOne.setNew_version_exist(getNew_version_exist());
		newOne.setAlready_newVersion(new String(getAlready_newVersion()));
		newOne.setCompleted(new String(getAlready_newVersion()));
		newOne.setGet_location(new String(getGet_location()));
		newOne.setAuthorizing(new String(getAuthorizing()));
		newOne.setAsk_run_download(new String(getAsk_run_download()));
		return newOne;
	}
	
}
