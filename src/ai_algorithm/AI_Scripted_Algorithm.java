package ai_algorithm;
import java.math.BigInteger;

import scripting.ScriptManager;
import main_classes.CalcWindow;
import main_classes.RunManager;
import main_classes.StandardCard;

public class AI_Scripted_Algorithm extends AI_Order_Included_Algorithm
{
	private static final long serialVersionUID = -7622742111598388748L;
	private String script = "";
	private String scriptType = "JavaScript";
	private String basicData = null;
	private ScriptManager manager;
	private Long authorized = new Long(0);
	private Boolean superAI = new Boolean(false);
	private Integer difficulty = new Integer(1);
	
	public AI_Scripted_Algorithm()
	{
		super();
	}
	public int getType()
	{
		return SCRIPT;
	}
	
	/*
	 * Make this AI authorized
	 * */
	public void authorize()
	{
		long code = 0;
		char[] script_chars = script.toCharArray();
		for(int i=0; i<script_chars.length; i++)
		{
			code += (int) script_chars[i];
		}
		code *= 3;
		char[] scriptType_chars = scriptType.toCharArray();
		for(int i=0; i<scriptType_chars.length; i++)
		{
			code += (int) scriptType_chars[i];
		}
		code *= (difficulty.intValue() + 1);
		if(superAI.booleanValue()) code += 1;
		
		authorized = new Long(code);
	}
	public boolean isAuthorized()
	{
		long code = 0;
		char[] script_chars = script.toCharArray();
		for(int i=0; i<script_chars.length; i++)
		{
			code += (int) script_chars[i];
		}
		code *= 3;
		char[] scriptType_chars = scriptType.toCharArray();
		for(int i=0; i<scriptType_chars.length; i++)
		{
			code += (int) scriptType_chars[i];
		}	
		code *= (difficulty.intValue() + 1);
		if(superAI.booleanValue()) code += 1;
		
		return authorized.longValue() == code;
	}
	
	/*
	 * Script preparing method.
	 * Prepare many variables to calculate bound.
	 * */
	public void prepareBasicData(boolean firstTime)
	{
		String variable_define_string = "", variable_define_int = "";
		if(scriptType.equalsIgnoreCase("JavaScript")) 
		{
			variable_define_string = "";
			variable_define_int = "";
		}
		
		if(! firstTime)
		{
			variable_define_string = "";
			variable_define_int = "";
		}
		
		String addScript = variable_define_int + " players = " + String.valueOf(getBlocks().length) + ";\n";
		
		setTemp(new AI_Temp());
		getTemp().input(getBlocks(), getDeck());
		BigInteger[] calculateds = new BigInteger[getTemp().getBlocks().size()];
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			getTemp().getBlocks().get(i).calculate(false);
			calculateds[i] = getTemp().getBlocks().get(i).getPoint();
		}
		
		
		addScript = addScript + variable_define_int + " point_myself" + " = " + String.valueOf(calculateds[getTurn()].longValue()) + ";\n";
		addScript = addScript + variable_define_int + " point_myself_as_BigInteger" + " = " + String.valueOf(calculateds[getTurn()]) + ";\n";
		addScript = addScript + variable_define_int + " point_myself" + "_as_string" + " = " + "\"" + calculateds[getTurn()].toString() + "\"" + ";\n";
		//addScript = addScript + variable_define_string + " point_array" + " = " + "Array(10)" + ";\n";
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			addScript = addScript + variable_define_int + " point_" + String.valueOf(i) + " = " + String.valueOf(calculateds[i].longValue()) + ";\n";
			addScript = addScript + variable_define_int + " point_" + String.valueOf(i) + "_as_BigInteger" + " = " + String.valueOf(calculateds[i]) + ";\n";
			addScript = addScript + variable_define_int + " point_" + String.valueOf(i) + "_as_string" + " = " + "\"" + calculateds[i].toString() + "\"" + ";\n";
			//addScript = addScript + variable_define_string + " point_array" + String.valueOf(i) + " = " + String.valueOf(calculateds[i]) + ";\n";
		}
		
		
		if(getTemp().getBlocks().get(getTurn()).getPaids().size() >= 1) 
		{
			addScript = addScript + variable_define_string + " last_myself" + " = " + "\"" + getTemp().getBlocks().get(getTurn()).getPaids().lastElement().toBasicString() + "\"" + ";\n";
			addScript = addScript + variable_define_string + " last_myself" + "_op" + " = " + "\"" + String.valueOf(getTemp().getBlocks().get(getTurn()).getPaids().lastElement().getOp()) + "\"" + ";\n";
			addScript = addScript + variable_define_int + " last_myself" + "_num" + " = " + String.valueOf(getTemp().getBlocks().get(getTurn()).getPaids().lastElement().getNum()) + ";\n";
		}
		else
		{
			addScript = addScript + variable_define_string + " last_myself" + " = " + "\"empty\"" + ";\n";
			addScript = addScript + variable_define_string + " last_myself" + "_op" + " = " + "\"empty\"" + ";\n";
			addScript = addScript + variable_define_int + " last_myself" + "_num" + " = " + "0" + ";\n";
		}
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			if(getTemp().getBlocks().get(i).getPaids().size() >= 1) 
			{
				addScript = addScript + variable_define_string + " last_" + String.valueOf(i) + " = " + "\"" + getTemp().getBlocks().get(i).getPaids().lastElement().toBasicString() + "\"" + ";\n";
				addScript = addScript + variable_define_string + " last_" + String.valueOf(i) + "_op" + " = " + "\"" + String.valueOf(getTemp().getBlocks().get(i).getPaids().lastElement().getOp()) + "\"" + ";\n";
				addScript = addScript + variable_define_int + " last_" + String.valueOf(i) + "_num" + " = " + String.valueOf(getTemp().getBlocks().get(i).getPaids().lastElement().getNum()) + ";\n";
			}
			else
			{
				addScript = addScript + variable_define_string + " last_" + String.valueOf(i) + " = " + "\"empty\"" + ";\n";
				addScript = addScript + variable_define_string + " last_" + String.valueOf(i) + "_op" + " = " + "\"empty\"" + ";\n";
				addScript = addScript + variable_define_int + " last_" + String.valueOf(i) + "_num" + " = " + "0" + ";\n";
			}
		}
		
		addScript = addScript + variable_define_string + " player_myself" + "_name" + " = " + "\"" + getTemp().getBlocks().get(getTurn()).getName() + "\"" + ";\n";
		addScript = addScript + variable_define_int + " player_myself" + "_owns" + " = " + getTemp().getBlocks().get(getTurn()).getOwns().size() + ";\n";
		
		for(int i=0; i<getTemp().getBlocks().size(); i++)
		{
			addScript = addScript + variable_define_string + " player_" + String.valueOf(i) + "_name" + " = " + "\"" + getTemp().getBlocks().get(i).getName() + "\"" + ";\n";
			addScript = addScript + variable_define_int + " player_" + String.valueOf(i) + "_owns" + " = " + getTemp().getBlocks().get(i).getOwns().size() + ";\n";
			for(int j=0; j<getTemp().getBlocks().get(i).getOwns().size(); i++)
			{					
				if(j == getTurn() || superAI.booleanValue())
				{
					addScript = addScript + variable_define_string + " player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + " = " + "\"" + getTemp().getBlocks().get(i).getOwns().get(j).toBasicString() + "\"" + ";\n";
					addScript = addScript + variable_define_string + " player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_op" + " = " + "\"" + getTemp().getBlocks().get(i).getOwns().get(j).getOp() + "\"" + ";\n";
					addScript = addScript + variable_define_int + " player_" + String.valueOf(i) + "_own_" + String.valueOf(j) + "_num" + " = " + getTemp().getBlocks().get(i).getOwns().get(j).getNum() + ";\n";
				}
			}
		}
		
		int blockCount = getTemp().getBlocks().size();
		int cardCount = getTemp().getBlocks().get(getTurn()).getOwns().size();
		StandardCard payCard, lastCard;
		boolean pay_available = false;
		for(int i=0; i<blockCount; i++)
		{				
			for(int j=0; j<cardCount; j++)
			{
				getTemp().reset();
				pay_available = false;
				
				payCard = getTemp().getBlocks().get(getTurn()).getOwns().get(j);
				
				if(getTemp().getBlocks().get(i).getPaids().size() >= 1) 
					lastCard = getTemp().getBlocks().get(i).getPaids().lastElement();
				else
					lastCard = null;
				
				if(lastCard != null)
				{
					if(lastCard.getNum() == 7)
					{
						if(i == getTurn())
						{
							if(payCard.getNum() == 1)
								pay_available = true;
							else if(lastCard.getOp() == payCard.getOp() || lastCard.getNum() == payCard.getNum())
								pay_available = true;
							else
								pay_available = false;
						}
						else pay_available = false;
					}
					else if(payCard.getNum() == 1)
					{
						pay_available = true;
					}
					else if(lastCard.getOp() == payCard.getOp() || lastCard.getNum() == payCard.getNum())
					{
						pay_available = true;
					}
					else pay_available = false;
				}
				else pay_available = true;
				
				
				if(pay_available)
				{
					getTemp().getBlocks().get(getTurn()).getOwns().remove(j);	
					getTemp().getBlocks().get(i).getPaids().add(payCard);
					for(int k=0; k<blockCount; k++)
					{
						getTemp().getBlocks().get(k).calculate(false);
						addScript = addScript + variable_define_int + " point_of_pay_" + String.valueOf(j) + "_to_player_" + String.valueOf(i) + "_then_" + String.valueOf(k) + " = " + String.valueOf(getTemp().getBlocks().get(k).getPoint()) +  ";\n";
					}
					addScript = addScript + variable_define_int + " point_of_pay_" + String.valueOf(j) + "_to_player_" + String.valueOf(i) + "_then_myself" + String.valueOf(getTurn()) + " = " + String.valueOf(getTemp().getBlocks().get(getTurn()).getPoint()) +  ";\n";
				}
			}
		}
		getTemp().reset();
		addScript = addScript + variable_define_int + " deck_size = " + String.valueOf(getTemp().getDeck().size()) + ";\n";
		addScript = addScript + variable_define_int + " turn = " + String.valueOf(getTurn()) + ";\n";
		addScript = addScript + variable_define_string + " ver = " + "\"" + String.valueOf(CalcWindow.version_main) + "." + String.valueOf(CalcWindow.version_sub_1) + String.valueOf(CalcWindow.version_sub_2) + "\"" + ";\n";
		basicData = addScript;
	}
	
	/**
	 * Define how to calculate the priority.
	 */
	@Override
	public BigInteger calc_bound(int target_card_index, StandardCard target_card) // Most importants
	{
		if(script == null || script.equals("") || script.equals(" "))
		{
			return super.calc_bound(target_card_index, target_card);
		}
		else
		{
			if(script == null || (! manager.getEngine().equalsIgnoreCase(scriptType)))
			{
				//manager = new ScriptManager(RunManager.getMainGame(), RunManager.getMainGame(), scriptType);
				try
				{
					manager = RunManager.getMainGame().getScriptEngine();
				} 
				catch (Exception e)
				{
					manager = new ScriptManager(null, null, null, scriptType);
				}
				prepareBasicData(true);
			}
			prepareBasicData(false);
			try
			{
				manager.act(basicData);
			}
			catch(Exception e)
			{
				return super.calc_bound(target_card_index, target_card);
			}
			try
			{
				
				manager.act("target_card_index = " + String.valueOf(target_card_index));
				if(target_card != null)
				{
					manager.act("target_act = \"pay\"");
					manager.act("target_card = " + "\"" + target_card.toBasicString() + "\"");
					manager.act("target_card_num = " + String.valueOf(target_card.getNum()));
					manager.act("target_card_op = " + "\"" + String.valueOf(target_card.getOp()) + "\"");
				}
				else
				{
					manager.act("target_act = \"take\"");
					manager.act("target_card = \"empty\"");
					manager.act("target_card_num = \"empty\"");
					manager.act("target_card_op = \"empty\"");
				}
			}
			catch(Exception e)
			{
				return super.calc_bound(target_card_index, target_card);
			}
			try
			{
				manager.act(script);
			}
			catch(Exception e)
			{
				return super.calc_bound(target_card_index, target_card);
			}
			
			try
			{
				AI_Script_Data allData = new AI_Script_Data();
				allData.blocks = new VirtualBlock[getBlocks().length];
				for(int i=0; i<getBlocks().length; i++)
				{
					allData.blocks[i] = getBlocks()[i].toVirtualBlock().clone();
				}
				allData.deck = new StandardCard[getDeck().length];
				for(int i=0; i<getDeck().length; i++)
				{
					allData.deck[i] = (StandardCard) getDeck()[i].clone();
				}
				manager.putObject("data", allData);
			}
			catch(Exception e)
			{
				
			}
			
			try
			{
				return new BigInteger(manager.actAndGet("calc_bound()").toString());
			} 
			catch (NumberFormatException e)
			{
				return super.calc_bound(target_card_index, target_card);
			} 
			catch (Exception e)
			{
				return super.calc_bound(target_card_index, target_card);
			}
		}
	}
	/**
	 * Define how to modify the priority value when the game is almost finished.
	 */
	public AI_Bonus calc_finish_check(boolean take)
	{
		AI_Bonus newOne = new AI_Bonus();
		BigInteger[] points = new BigInteger[getTemp().getBlocks().size()];
		getTemp().getBlocks().get(0).calculate(false);
		BigInteger max_point = getTemp().getBlocks().get(0).getPoint();
		int winner = 0;
		for(int i=0; i<points.length; i++)
		{
			getTemp().getBlocks().get(i).calculate(false);
			points[i] = getTemp().getBlocks().get(i).getPoint();
			//if(getTemp().getBlocks().get(i).getPoint() > max_point)
			if(getTemp().getBlocks().get(i).getPoint().compareTo(max_point) > 0)
			{
				max_point = getTemp().getBlocks().get(i).getPoint();
				winner = i;
			}
		}
		if(take)
		{
			if(getTemp().getDeck().size() <= 1)
			{
				if(winner == getTurn())
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.MULTIPLY_ABS;
				}
				else
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.DIVIDE_ABS;
				}
			}
		}
		else
		{
			if(getTemp().getBlocks().size() <= 0)
			{
				if(winner == getTurn())
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.MULTIPLY_ABS;
				}
				else
				{
					newOne.bonus = 2;
					newOne.op_type = AI_Bonus.DIVIDE_ABS;
				}
			}
		}
		return newOne;
	}
	public AI_Order_Included_Algorithm clone()
	{
		this.clean();
		AI_Scripted_Algorithm newOne = (AI_Scripted_Algorithm) super.clone();
		newOne.script = new String(this.script);
		newOne.scriptType = new String(this.scriptType);
		newOne.authorized = new Long(authorized.longValue());
		newOne.superAI = new Boolean(superAI.booleanValue());
		newOne.difficulty = new Integer(difficulty.intValue());
		newOne.basicData = new String(basicData);
		return newOne;
	}

	public String getScript()
	{
		return script;
	}

	public void setScript(String script)
	{
		this.script = script;
	}

	public String getScriptType()
	{
		return scriptType;
	}

	public void setScriptType(String scriptType)
	{
		this.scriptType = scriptType;
	}

	public ScriptManager getManager()
	{
		return manager;
	}

	public void setManager(ScriptManager manager)
	{
		this.manager = manager;
	}

	public Long getAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(Long authorized)
	{
		this.authorized = authorized;
	}

	public Boolean getSuperAI()
	{
		return superAI;
	}

	public void setSuperAI(Boolean superAI)
	{
		this.superAI = superAI;
	}

	public Integer getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(Integer difficulty)
	{
		this.difficulty = difficulty;
	}

	public String getBasicData()
	{
		return basicData;
	}

	public void setBasicData(String basicData)
	{
		this.basicData = basicData;
	}
}
