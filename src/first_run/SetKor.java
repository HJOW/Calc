package first_run;

public class SetKor extends SetLang
{
	private static final long serialVersionUID = -1983824267365524776L;

	public SetKor()
	{
		setTry_run("게임을 실행합니다.");
		setCheck_version("게임의 버전을 확인합니다.");
		setDownloading("게임을 다운로드합니다.");
		setFailed("문제가 발생하였습니다 : ");
		setNew_version_exist("새 버전을 다운로드하여 실행할까요? (새 버전 : ");
		setAlready_newVersion("이미 최신 버전으로 실행 중입니다. 게임을 실행합니다.");
		setCompleted("최신 버전의 실행 파일을 다음 장소에 다운로드하였습니다 : ");
		setGet_location("다음 장소에서 파일을 받습니다 : ");
		setAuthorizing("인증");
		setAsk_run_download("다운로드받은 파일로 실행할까요?");
	}
	
	@Override
	public SetLang clone()
	{
		SetKor newOne = new SetKor();
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
