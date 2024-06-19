package ai_algorithm;

import java.util.Vector;

import main_classes.Block;
import main_classes.StandardCard;

public class AI_Temp
{
	public Vector<VirtualBlock> original_blocks;
	public Vector<VirtualBlock> blocks;
	public Vector<StandardCard> deck, original_deck;
	
	public AI_Temp()
	{
		original_blocks = new Vector<VirtualBlock>();
		blocks = new Vector<VirtualBlock>();
		deck = new Vector<StandardCard>();
		original_deck = new Vector<StandardCard>();
	}
	public void input(Block[] blocks, StandardCard[] deck)
	{
		original_blocks.clear();
		original_deck.clear();
		for(int i=0; i<blocks.length; i++)
		{
			original_blocks.add(blocks[i].toVirtualBlock());
		}
		for(int i=0; i<deck.length; i++)
		{
			original_deck.add(deck[i]);
		}
		reset();
	}
	public void reset()
	{
		blocks.clear();
		deck.clear();
		for(int i=0; i<original_blocks.size(); i++)
		{
			blocks.add(original_blocks.get(i).clone());
		}
		for(int i=0; i<original_deck.size(); i++)
		{
			deck.add((StandardCard) original_deck.get(i).clone());
		}
	}
	public Vector<VirtualBlock> getOriginal_blocks()
	{
		return original_blocks;
	}
	public void setOriginal_blocks(Vector<VirtualBlock> original_blocks)
	{
		this.original_blocks = original_blocks;
	}
	public Vector<VirtualBlock> getBlocks()
	{
		return blocks;
	}
	public void setBlocks(Vector<VirtualBlock> blocks)
	{
		this.blocks = blocks;
	}
	public Vector<StandardCard> getDeck()
	{
		return deck;
	}
	public void setDeck(Vector<StandardCard> deck)
	{
		this.deck = deck;
	}
	public Vector<StandardCard> getOriginal_deck()
	{
		return original_deck;
	}
	public void setOriginal_deck(Vector<StandardCard> original_deck)
	{
		this.original_deck = original_deck;
	}
}
