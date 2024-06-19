package replay;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import main_classes.Block;
import main_classes.StandardCard;
import setting.Objectable;
import setting.SaveBoolean;
import setting.SaveInt;

public class Replay implements Serializable, Objectable
{
	private static final long serialVersionUID = 9162077817055345327L;
	private Block[] first_blocks;
	private StandardCard[] first_deck;
	private Block[] blocks;
	private StandardCard[] deck;
	private Control[] controls;
	private BigInteger[] result_points;
	private int slots = 4;
	private boolean authority = false;
	private BigInteger code = new BigInteger("0");
	private int version_main = 0;
	private int version_sub1 = 0;
	private int version_sub2 = 0;
	private int start_card_code = 0;
	private BigInteger code_x = new BigInteger("0");
	private BigInteger[] points;
	
	private SaveInt ob_slots = null;
	private SaveInt ob_version_main = null;
	private SaveInt ob_version_sub1 = null;
	private SaveInt ob_version_sub2 = null;
	private SaveInt ob_start_card_code = null;
	private SaveBoolean ob_authority = null;
	
	public Replay()
	{
		
	}
	public Replay(int ver_main, int ver_sub1, int ver_sub2, int start_card_for_authority)
	{
		blocks = new Block[1];
		deck = new StandardCard[1];
		controls = new Control[1];
		result_points = new BigInteger[1];
		version_main = ver_main;
		version_sub1 = ver_sub1;
		version_sub2 = ver_sub2;
		start_card_code = start_card_for_authority;
	}
	public void set(Vector<Block> start_state, Vector<StandardCard> deck, int slots)
	{
		first_blocks = new Block[start_state.size()];
		for(int i=0; i<start_state.size(); i++)
		{
			first_blocks[i] = new Block();
			first_blocks[i].setBlock(start_state.get(i).toVirtualBlock());
			first_blocks[i].setName(new String(start_state.get(i).getName()));
		}
		first_deck = new StandardCard[deck.size()];
		for(int i=0; i<deck.size(); i++)
		{
			first_deck[i] = (StandardCard) deck.get(i).clone();
		}
		reset();
		this.slots = slots;
		result_points = new BigInteger[slots];
	}
	public void reset()
	{
		blocks = new Block[first_blocks.length];
		deck = new StandardCard[first_deck.length];
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i] = new Block();
			blocks[i].setBlock(first_blocks[i].toVirtualBlock());
			blocks[i].setName(new String(first_blocks[i].getName()));
		}
		for(int i=0; i<deck.length; i++)
		{
			deck[i] = (StandardCard) first_deck[i].clone();
		}
	}
	public void getControls(ControlCollector collector)
	{
		controls = new Control[collector.controls.size()];
		for(int i=0; i<controls.length; i++)
		{
			controls[i] = collector.controls.get(i);
		}
	}
	public void setAuthority(BigInteger code)
	{
		authority = true;
		this.code = code;
	}
	
	
	
	public Block[] getFirst_blocks()
	{
		return first_blocks;
	}
	public void setFirst_blocks(Block[] first_blocks)
	{
		this.first_blocks = first_blocks;
	}
	public StandardCard[] getFirst_deck()
	{
		return first_deck;
	}
	public void setFirst_deck(StandardCard[] first_deck)
	{
		this.first_deck = first_deck;
	}
	public Block[] getBlocks()
	{
		return blocks;
	}
	public void setBlocks(Block[] blocks)
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
	public Control[] getControls()
	{
		return controls;
	}
	public void setControls(Control[] controls)
	{
		this.controls = controls;
	}
	public BigInteger[] getResult_points()
	{
		return result_points;
	}
	public void setResult_points(BigInteger[] result_points)
	{
		this.result_points = result_points;
	}
	public int getSlots()
	{
		return slots;
	}
	public void setSlots(int slots)
	{
		this.slots = slots;
	}
	public boolean isAuthority()
	{
		return authority;
	}
	public void setAuthority(boolean authority)
	{
		this.authority = authority;
	}
	public BigInteger getCode()
	{
		return code;
	}
	public void setCode(BigInteger code)
	{
		this.code = code;
	}
	public int getVersion_main()
	{
		return version_main;
	}
	public void setVersion_main(int version_main)
	{
		this.version_main = version_main;
	}
	public int getVersion_sub1()
	{
		return version_sub1;
	}
	public void setVersion_sub1(int version_sub1)
	{
		this.version_sub1 = version_sub1;
	}
	public int getVersion_sub2()
	{
		return version_sub2;
	}
	public void setVersion_sub2(int version_sub2)
	{
		this.version_sub2 = version_sub2;
	}
	public int getStart_card_code()
	{
		return start_card_code;
	}
	public void setStart_card_code(int start_card_code)
	{
		this.start_card_code = start_card_code;
	}
	public BigInteger[] getPoints()
	{
		return points;
	}
	public void setPoints(BigInteger[] points)
	{
		this.points = points;
	}
	public BigInteger getCode_x()
	{
		return code_x;
	}
	public void setCode_x(BigInteger code_x)
	{
		this.code_x = code_x;
	}
	@Override
	public void objectToWrapper()
	{		
		if(ob_slots != null) slots = ob_slots.intValue();
		if(ob_version_main != null) version_main = ob_version_main.intValue();
		if(ob_version_sub1 != null) version_sub1 = ob_version_sub1.intValue();
		if(ob_version_sub2 != null) version_sub2 = ob_version_sub2.intValue();
		if(ob_start_card_code != null) start_card_code = ob_start_card_code.intValue();
		if(ob_authority != null) authority = ob_authority.booleanValue();
		
	}
	@Override
	public void wrapperToObjects()
	{
		ob_slots = new SaveInt(slots);
		ob_version_main = new SaveInt(version_main);
		ob_version_sub1 = new SaveInt(version_sub1);
		ob_version_sub2 = new SaveInt(version_sub2);
		ob_start_card_code = new SaveInt(start_card_code);
		ob_authority = new SaveBoolean(authority);
	}
	@Override
	public void wrapperObjectClean()
	{
		ob_slots = null;
		ob_version_main = null;
		ob_version_sub1 = null;
		ob_version_sub2 = null;
		ob_start_card_code = null;
		ob_authority = null;
		
	}
	public SaveInt getOb_slots()
	{
		return ob_slots;
	}
	public void setOb_slots(SaveInt ob_slots)
	{
		this.ob_slots = ob_slots;
	}
	public SaveInt getOb_version_main()
	{
		return ob_version_main;
	}
	public void setOb_version_main(SaveInt ob_version_main)
	{
		this.ob_version_main = ob_version_main;
	}
	public SaveInt getOb_version_sub1()
	{
		return ob_version_sub1;
	}
	public void setOb_version_sub1(SaveInt ob_version_sub1)
	{
		this.ob_version_sub1 = ob_version_sub1;
	}
	public SaveInt getOb_version_sub2()
	{
		return ob_version_sub2;
	}
	public void setOb_version_sub2(SaveInt ob_version_sub2)
	{
		this.ob_version_sub2 = ob_version_sub2;
	}
	public SaveInt getOb_start_card_code()
	{
		return ob_start_card_code;
	}
	public void setOb_start_card_code(SaveInt ob_start_card_code)
	{
		this.ob_start_card_code = ob_start_card_code;
	}
	public SaveBoolean getOb_authority()
	{
		return ob_authority;
	}
	public void setOb_authority(SaveBoolean ob_authority)
	{
		this.ob_authority = ob_authority;
	}
}
