package ai_algorithm;
import main_classes.StandardCard;

public class AI_Script_Data
{
	public VirtualBlock[] blocks;
	public StandardCard[] deck;
	public VirtualBlock[] getBlocks()
	{
		return blocks;
	}
	public void setBlocks(VirtualBlock[] blocks)
	{
		this.blocks = blocks;
	}
	public StandardCard[] getDeck()
	{
		return deck;
	}
	public void setDeck(StandardCard[] deck)
	{
		this.deck = deck;
	}
	public VirtualBlock getPlayerData(int index)
	{
		return blocks[index];
	}
	public StandardCard getCardOnDeck(int index)
	{
		return deck[index];
	}
	public int getPlayerCount()
	{
		return blocks.length;
	}
	public int getDeckCount()
	{
		return deck.length;
	}
}
