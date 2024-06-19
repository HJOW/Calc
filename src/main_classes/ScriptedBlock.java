package main_classes;

import scripting.BlockModule;

public class ScriptedBlock extends Block
{
	private static final long serialVersionUID = -7188672363861956117L;
	private BlockModule module;
	public ScriptedBlock()
	{
		super();
	}
	public ScriptedBlock(CalcWindow window)
	{
		super(window);
	}
	public ScriptedBlock(BlockModule module)
	{
		super();
		this.module = module;
	}
	public ScriptedBlock(CalcWindow window, BlockModule module)
	{
		super(window);
		this.module = module;
	}
	public BlockModule getModule()
	{
		return module;
	}
	public void setModule(BlockModule module)
	{
		this.module = module;
	}
	
}
