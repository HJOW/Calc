package browser;

public interface BrowserFrame
{
	public String getAddressText();
	public void showMessage(String str);
	public void updateButtons(String[] newList, int currentIndex);
	public void setAddressText(String address);
	public void error(String msg);
}
