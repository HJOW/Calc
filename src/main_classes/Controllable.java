package main_classes;
import setting.Setting;

public interface Controllable
{
	public void stop_game();
	public void start_game();
	public void exit();
	public void takeCard();
	public void payCard(int blockNumber, int pay_card_index);
	public void selectAuthorizeCheckbox();
	public void selectPlayerSetting(int index, int value);
	public Setting getSetting();
}
