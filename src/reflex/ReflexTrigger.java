package reflex;

import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;

public class ReflexTrigger implements Serializable
{
	private static final long serialVersionUID = -5862762253507920567L;
	private TriggerCondition[] conditions;
	private TriggerAction[] actions;
	
	public ReflexTrigger()
	{
		
	}
	public static ReflexTrigger[] stringToTriggers(String str)
	{
		StringTokenizer lineToken = new StringTokenizer(str, "\n");
		StringTokenizer keyToken;
		String lines, key, target;
		boolean exists = false;
		Vector<ReflexTrigger> triggers = new Vector<ReflexTrigger>();
		Vector<IntegerWithIndex> existList = new Vector<IntegerWithIndex>();
		Vector<String> newTriggerAccum = new Vector<String>();
		IntegerWithIndex ind = null;
		while(lineToken.hasMoreTokens())
		{
			try
			{
				lines = lineToken.nextToken();
				keyToken = new StringTokenizer(lines, "<[]>");
				key = keyToken.nextToken();
				if(! keyToken.hasMoreTokens()) continue;
				target = keyToken.nextToken();
				exists = false;
				for(IntegerWithIndex i : existList)
				{
					if(Integer.parseInt(key) == i.key)
					{
						exists = true;
						ind = i;
						break;
					}
				}
				if(exists)
				{
					newTriggerAccum.set(ind.index, newTriggerAccum.get(ind.index) + target + "\n");
				}
				else
				{
					newTriggerAccum.add(target);
					existList.add(new IntegerWithIndex(newTriggerAccum.size() - 1, Integer.parseInt(key)));
				}
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for(String s : newTriggerAccum)
		{
			triggers.add(stringToTrigger(s));
		}
		
		ReflexTrigger[] results = new ReflexTrigger[triggers.size()];
		for(int i=0; i<results.length; i++)
		{
			results[i] = triggers.get(i);
		}		
		
		return results;
	}
	public static ReflexTrigger stringToTrigger(String str)
	{
		StringTokenizer lineToken = new StringTokenizer(str, "\n");
		StringTokenizer keyToken;
		String lines, key, target, additional;
		Vector<TriggerCondition> addConditions = new Vector<TriggerCondition>();
		Vector<TriggerAction> addActions = new Vector<TriggerAction>();
		while(lineToken.hasMoreTokens())
		{
			try
			{
				lines = lineToken.nextToken();
				keyToken = new StringTokenizer(lines, "<>");
				key = keyToken.nextToken();
				if(! keyToken.hasMoreTokens()) continue;
				additional = keyToken.nextToken();
				if(! keyToken.hasMoreTokens()) continue;
				target = keyToken.nextToken();
				
				if(key.equalsIgnoreCase("condition"))
				{
					addConditions.add(new TriggerCondition(Integer.parseInt(additional), Double.parseDouble(target)));
				}
				else if(key.equalsIgnoreCase("action"))
				{
					addActions.add(new TriggerAction(Integer.parseInt(additional), Double.parseDouble(target)));
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		TriggerCondition[] conditions = new TriggerCondition[addConditions.size()];
		for(int i=0; i<conditions.length; i++)
		{
			conditions[i] = addConditions.get(i);
		}
		TriggerAction[] actions = new TriggerAction[addActions.size()];
		for(int i=0; i<actions.length; i++)
		{
			actions[i] = addActions.get(i);
		}
		ReflexTrigger newTrig = new ReflexTrigger();
		newTrig.setConditions(conditions);
		newTrig.setActions(actions);
		
		return newTrig;
	}
	public boolean isAccept(Arena arena)
	{		
		boolean returns = true;
		for(TriggerCondition c : conditions)
		{
			if(! c.isAccept(arena))
			{
				return false;
			}
		}
		return returns;
	}
	public void action(Arena arena) throws Exception
	{
		for(TriggerAction a : actions)
		{
			a.action(arena);
		}
	}

	public TriggerAction[] getActions()
	{
		return actions;
	}

	public void setActions(TriggerAction[] actions)
	{
		this.actions = actions;
	}
	public TriggerCondition[] getConditions()
	{
		return conditions;
	}
	public void setConditions(TriggerCondition[] conditions)
	{
		this.conditions = conditions;
	}
}
class IntegerWithIndex
{
	public int index;
	public int key;
	public IntegerWithIndex(int index, int key)
	{
		this.index = index;
		this.key = key;
	}
}
