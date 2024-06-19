package setting;

import java.io.Serializable;

public class MessageOnTurn implements Serializable
{
	private static final long serialVersionUID = -3728153706437813480L;
	private String contents = "";
	private String kor_contents;
	private Integer turn;
	
	public MessageOnTurn()
	{
		turn = new Integer(0);
	}
	public MessageOnTurn(String contents, int turn)
	{
		this.contents = new String(contents);
		this.turn = new Integer(turn);
	}
	public MessageOnTurn(String contents, String korc, int turn)
	{
		this.contents = new String(contents);
		this.kor_contents = new String(korc);
		this.turn = new Integer(turn);
	}
	public String getContents()
	{
		return contents;
	}
	public void setContents(String contents)
	{
		this.contents = contents;
	}
	public Integer getTurn()
	{
		return turn;
	}
	public void setTurn(Integer turn)
	{
		this.turn = turn;
	}
	public String getKor_contents()
	{
		return kor_contents;
	}
	public void setKor_contents(String kor_contents)
	{
		this.kor_contents = kor_contents;
	}
	public MessageOnTurn clone()
	{
		if(kor_contents == null)
			return new MessageOnTurn(contents, turn.intValue());
		else
			return new MessageOnTurn(contents, kor_contents, turn.intValue());
	}
}
