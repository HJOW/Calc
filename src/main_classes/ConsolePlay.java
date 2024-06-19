package main_classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import lang.Language;
import setting.Lint;
import setting.Setting;

public class ConsolePlay implements Openable, MessageShowable
{
	public int version_main = 0;
	public int version_sub_1 = 0;
	public int version_sub_2 = 0;
	public int clear_lines = 1000;
	Setting setting;
	boolean authorized = false;
	boolean run_game = false;
	int select_menu = 0;
	int turn = 0;
	private InputStreamReader input_str;
	private BufferedReader bfreader;
	public ConsoleBlock[] blocks;
	private Vector<String> deck;
	private boolean game_while;
	//private boolean authorize_mode = false;
	
	public ConsolePlay(Setting setting)
	{
		this.setting = setting.clone();		
		version_main = CalcWindow.version_main;
		version_sub_1 = CalcWindow.version_sub_1;
		version_sub_2 = CalcWindow.version_sub_2;
		input_str = new InputStreamReader(System.in);
		bfreader = new BufferedReader(input_str);
		blocks = new ConsoleBlock[setting.getSlots()];
		deck = new Vector<String>();
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i] = new ConsoleBlock(this.setting);
			if(i == 0) blocks[i].setName(this.setting.getLang().getText(Language.PLAYER) + " " + String.valueOf(i));
			else
			{
				blocks[i].setName(this.setting.getLang().getText(Language.AI) + " " + String.valueOf(i));
				blocks[i].setAi(true);
			}
		}
	}
	public void clear()
	{
		try
		{
			if(setting.getOs().startsWith("windows") || setting.getOs().startsWith("Windows")) Runtime.getRuntime().exec("cls");
			else Runtime.getRuntime().exec("clear");
		}
		catch(Exception e)
		{
			try
			{
				if(setting.getOs().startsWith("windows") || setting.getOs().startsWith("Windows")) Runtime.getRuntime().exec("clear");
				else Runtime.getRuntime().exec("cls");
			}
			catch(Exception f)
			{
				for(int i=0; i<1000; i++)
				{
					message();
				}
			}
		}		
	}
	private int main_menu()
	{
		boolean main_menu_loop = true;
		int selectes = 0;
		while(main_menu_loop)
		{
			message_bar();
			message("★ " + setting.getLang().getText(Language.MAIN) + " " + setting.getLang().getText(Language.MENU));
			message_bar();
			message("1. " + setting.getLang().getText(Language.PLAYER) + " " + setting.getLang().getText(Language.EDIT));
			message("2. " + setting.getLang().getText(Language.SETTING));
			message("3. " + setting.getLang().getText(Language.START));
			message("4. " + setting.getLang().getText(Language.EXIT));
			try
			{
				message_bar();
				System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
				selectes = Integer.parseInt(bfreader.readLine());
				main_menu_loop = false;
			} 
			catch (NumberFormatException e)
			{
				message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
				main_menu_loop = true;
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				main_menu_loop = true;
			}		
		}
		return selectes;
	}
	private void player_set()
	{
		boolean player_set_loop = true;
		int selectes = 0;
		message();
		message_bar();
		message("★ " + setting.getLang().getText(Language.PLAYER) + " " + setting.getLang().getText(Language.EDIT));
		//message_bar();
		while(player_set_loop)
		{
			message("0. ...");
			for(int i=0; i<blocks.length; i++)
			{
				message(String.valueOf((i+1)) + ". " + blocks[i].name + " " + setting.getLang().getText(Language.SETTING));
			}
			try
			{
				message_bar();
				System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
				selectes = Integer.parseInt(bfreader.readLine());
				if(selectes == 0) 
				{
					player_set_loop = false;
				}
				else if(selectes >= 1 && selectes <= blocks.length)
				{
					selectes--;
					message_bar();
					blocks[selectes].show(this, setting, false);
					//message_bar();
					
					message("0. ...");
					message("1. " + setting.getLang().getText(Language.EDIT));
					
					boolean edit_loop = true;
					int select2 = 0;
					while(edit_loop)
					{
						try
						{
							System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
							select2 = Integer.parseInt(bfreader.readLine());
							edit_loop = false;
						}
						catch (NumberFormatException e)
						{
							if(setting.isError_printDetail()) e.printStackTrace();
							message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
							edit_loop = true;
						}
						catch(Exception e)
						{
							if(setting.isError_printDetail()) e.printStackTrace();
							edit_loop = true;
						}
					}
					if(select2 == 0)
					{
						player_set_loop = true;
					}
					else
					{
						String edit_input = "";
						message_bar();
						if(blocks[selectes].isAi())
						{
							edit_input = setting.getLang().getText(Language.AI);
						}
						else
						{
							edit_input = setting.getLang().getText(Language.PLAYER);
						}
						message("  ◆ " + edit_input + " " + blocks[selectes].name + " " + setting.getLang().getText(Language.EDIT));
						edit_input = "";
						System.out.print(setting.getLang().getText(Language.NAME) + " : ");
						edit_input = bfreader.readLine();
						blocks[selectes].setName(edit_input);
						System.out.print(setting.getLang().getText(Language.AI) + " (y/n) : ");						
						edit_loop = true;
						while(edit_loop)
						{
							edit_input = bfreader.readLine();
							char edit_yorn = edit_input.toCharArray()[0];
							if(edit_yorn == 'y' || edit_yorn == 'Y')
							{
								blocks[selectes].setAi(true);
								edit_loop = false;
							}
							else if(edit_yorn == 'n' || edit_yorn == 'N')
							{
								blocks[selectes].setAi(false);
								edit_loop = false;
							}
							else
							{
								message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
								edit_loop = true;
							}
						}
						message("  " + setting.getLang().getText(Language.EDIT) + " " + setting.getLang().getText(Language.COMPLETE));
						message_bar();
					}
				}				
			} 
			catch (NumberFormatException e)
			{
				message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
				player_set_loop = true;
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				player_set_loop = true;
			}	
		}		
	}
	public void run_game()
	{
		message(setting.getLang().getText(Language.TITLE) + " v" + version_main + "." + version_sub_1 + "" + version_sub_2);
		run_game = true;
		while(run_game)
		{
			message();
			select_menu = main_menu();
			switch(select_menu)
			{
				case 1: // Player setting
					player_set();
					break;
				case 2:
					set_play();
					break;
				case 3: // Game Start
					play();
					break;
				case 4: // Exit
					run_game = false;
					break;
			}			
		}
		exit();
	}
	private void set_play()
	{
		boolean temp_loop = true;
		int select2 = 0;
		while(temp_loop)
		{
			message(setting.getLang().getText(Language.AUTHORITY) + " : " + String.valueOf(authorized));
			message("1. " + setting.getLang().getText(Language.EDIT));
			message("2. ...");
			try
			{
				System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
				select2 = Integer.parseInt(bfreader.readLine());
				switch(select2)
				{
					case 1:
						authorized = (! authorized);
						break;
					case 2:
						temp_loop = false;
						break;
				}				
			}
			catch (NumberFormatException e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
				temp_loop = true;
			}
			catch(Exception e)
			{
				if(setting.isError_printDetail()) e.printStackTrace();
				temp_loop = true;
			}
		}		
	}
	private void play()
	{
		clear();
		int selects = 0;
		boolean turn_success = false;
		StringTokenizer stokener;
		start();
		while(game_while)
		{
			turn_success = false;
			message_bar();
			message("※ " + blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 22) + " " + setting.getLang().getText(Language.CARD) + " " + setting.getLang().getText(Language.COUNT) + " : " + blocks[turn].owns.size());
			message_bar();
			
			// AI and Player control
			if(blocks[turn].ai) // AI
			{
				long calc_max = Long.MIN_VALUE;
				long calc_temp = Long.MIN_VALUE;
				int promising_player = 0;
				int promising_card = 0;
				boolean take_or_pay = false; // true : Take, false = Pay
				
				// Virtual Data create
				Vector<String> virtual_deck = new Vector<String>();
				for(int i=0; i<deck.size(); i++)
				{
					virtual_deck.add(new String(deck.get(i)));
				}
				Vector<ConsoleBlock> virtual_blocks_spare = new Vector<ConsoleBlock>();
				Vector<ConsoleBlock> virtual_blocks = new Vector<ConsoleBlock>();
				for(int i=0; i<blocks.length; i++)
				{
					virtual_blocks_spare.add(blocks[i].clone());
					virtual_blocks.add(blocks[i].clone());
				}
				
				// Taking check
				
				String takes = virtual_deck.get(0);
				virtual_blocks.get(turn).owns.add(takes);
				for(int i=0; i<virtual_blocks.size(); i++)
				{
					virtual_blocks.get(i).calculate(0);					
				}
				calc_temp = virtual_blocks.get(turn).getPoint();
				for(int i=0; i<virtual_blocks.size(); i++)
				{
					if(i != turn) calc_temp = calc_temp - virtual_blocks.get(i).getPoint();
				}
				if(calc_max < calc_temp)
				{
					calc_max = calc_temp;
					take_or_pay = true;
				}
				
				// Reset virtuals
				
				virtual_blocks.clear();
				for(int i=0; i<virtual_blocks_spare.size(); i++)
				{
					virtual_blocks.add(virtual_blocks_spare.get(i).clone());
				}
				
				// Paying check
				int card_count = virtual_blocks.get(turn).owns.size();
				int player_count = virtual_blocks.size();
				String select_card = "";
				String last_card = "";
				char select_op = setting.getOp_plus();
				char last_op = setting.getOp_plus();
				int select_num = 0;
				int last_num = 0;
				boolean virtual_pay_fin = false;
				
				//StringTokenizer stokener;
				for(int i=0; i<card_count; i++) // what to pay
				{
					for(int j=0; j<player_count; j++) // where to pay
					{
						virtual_pay_fin = false;
						
						select_card = virtual_blocks.get(turn).owns.get(i);
						if(virtual_blocks.get(j).paids.size() >= 1)
							last_card = virtual_blocks.get(j).getLastPaid();
						else last_card = null;
						
						stokener = new StringTokenizer(select_card, setting.getCard_separator());
						select_op = stokener.nextToken().toCharArray()[0];
						select_num = Integer.parseInt(stokener.nextToken());
						
						if(last_card != null)
						{
							stokener = new StringTokenizer(last_card, setting.getCard_separator());
							last_op = stokener.nextToken().toCharArray()[0];
							last_num = Integer.parseInt(stokener.nextToken());
						}
						
						if(last_card == null)
						{
							virtual_pay_fin = true;
						}
						else if(last_num == 7 && (turn == j))
						{
							virtual_pay_fin = true;
						}
						else if(select_num == 1)
						{
							virtual_pay_fin = true;
						}
						else if(select_num == last_num || select_op == last_op)
						{
							virtual_pay_fin = true;
						}
						else
						{
							virtual_pay_fin = false;
						}						
						
						if(virtual_pay_fin)
						{
							virtual_blocks.get(turn).owns.remove(i);
							virtual_blocks.get(j).paids.add(select_card);
							
							for(int k=0; k<virtual_blocks.size(); k++)
							{
								virtual_blocks.get(k).calculate(0);					
							}
							calc_temp = virtual_blocks.get(turn).getPoint();
							for(int k=0; k<virtual_blocks.size(); k++)
							{
								if(k != turn) calc_temp = calc_temp - virtual_blocks.get(k).getPoint();
							}
							if(calc_max < calc_temp)
							{
								calc_max = calc_temp;
								promising_card = i;
								promising_player = j;
								take_or_pay = false;
							}							
						}
						
						// Reset virtuals
						
						virtual_blocks.clear();
						for(int k=0; k<virtual_blocks_spare.size(); k++)
						{
							virtual_blocks.add(virtual_blocks_spare.get(k).clone());
						}
					}
				}
				
				// Select better choice
				
				if(take_or_pay) // take
				{
					blocks[turn].owns.add(deck.get(0));
					deck.remove(0);
					message(blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 0));
					turn_success = true;
				}
				else // pay
				{
					String pay_card = blocks[turn].owns.get(promising_card);
					blocks[promising_player].paids.add(pay_card);
					blocks[turn].owns.remove(promising_card);
					
					char pay_op = pay_card.toCharArray()[0];
					if(pay_op == setting.getOp_change())
					{
						Vector<String> tempOwns = blocks[turn].owns;
						blocks[turn].owns = blocks[promising_player].owns;
						blocks[promising_player].owns = tempOwns;
						message(setting.getLang().getText(Language.DESCRIPTIONS + 26) + blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 27) + blocks[promising_player].name + setting.getLang().getText(Language.DESCRIPTIONS + 28));
					}
					
					message(blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 1) + blocks[promising_player].name + setting.getLang().getText(Language.PAIDS) + ", " + pay_card);
					turn_success = true;
				}
				
			}
			else // Player
			{
				for(int i=0; i<blocks.length; i++)
				{
					blocks[i].calculate(0);
					message(i + ". " + setting.getLang().getText(Language.DESCRIPTIONS + 23) + blocks[i].name + " " + setting.getLang().getText(Language.DESCRIPTIONS + 24) + blocks[i].getLastPaid() + ", " + setting.getLang().getText(Language.CARD) + " " + setting.getLang().getText(Language.COUNT) + " : " + blocks[i].owns.size() + ", " + setting.getLang().getText(Language.POINT) + " : " + blocks[i].getPoint());
				}
				message(blocks.length + ". " + setting.getLang().getText(Language.DESCRIPTIONS + 25) + deck.size());
				message((blocks.length + 1) + ". " + setting.getLang().getText(Language.EXIT));
				
				try
				{
					System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
					selects = Integer.parseInt(bfreader.readLine());
					String cards = "";
					if(selects >= 0 && selects < blocks.length)
					{
						String last_card = null;
						boolean select_cancel = false;
						char last_op_part= setting.getOp_plus();
						int last_num_part= 0;
						if(blocks[selects].paids.size() >= 1)
						{
							last_card = blocks[selects].getLastPaid();
							last_op_part = setting.getOp_plus();
							last_num_part = 0;
							stokener = new StringTokenizer(last_card, setting.getCard_separator());
							last_op_part = stokener.nextToken().toCharArray()[0];
							last_num_part = Integer.parseInt(stokener.nextToken());
						}
						else
						{
							last_card = null;
						}
							boolean select_while = true;
							int select_target_index = 0;
							select_cancel = false;
							message_bar();
							message(setting.getLang().getText(Language.CARD) + " " + setting.getLang().getText(Language.SELECT));
							message_bar();
							while(select_while)
							{
								for(int i=0; i<blocks[turn].owns.size(); i++)
								{
									message(i + ". " + blocks[turn].owns.get(i));
								}
								message(blocks[turn].owns.size() + ". ...");
								
								try
								{
									System.out.print(setting.getLang().getText(Language.SELECT) + " : ");
									select_target_index = Integer.parseInt(bfreader.readLine());
									
									clear();
									
									if(select_target_index == blocks[turn].owns.size())
									{
										select_cancel = true;
										select_while = false;
									}
									else if(select_target_index < blocks[turn].owns.size() && select_target_index >= 0)
									{
										String selected_card = blocks[turn].owns.get(select_target_index);
										stokener = new StringTokenizer(selected_card, setting.getCard_separator());
										char selected_op = stokener.nextToken().toCharArray()[0];
										int selected_num = Integer.parseInt(stokener.nextToken());
										boolean pay_available = false;
										boolean change_card = false;
										
										if(last_card == null)
										{
											pay_available = true;
										}
										else if(last_num_part == 7)
										{
											if(turn == selects) pay_available = true;
											else
											{
												message(setting.getLang().getText(Language.DESCRIPTIONS + 6));
												pay_available = false;
											}
										}
										else if(selected_num == 1)
										{
											pay_available = true;
											if(selected_op == setting.getOp_change()) change_card = true;
										}
										else if(selected_num == last_num_part || selected_op == last_op_part)
										{
											pay_available = true;
										}
										else
										{
											pay_available = false;
										}
										if(pay_available)
										{
											String pay_card = blocks[turn].owns.get(select_target_index);
											blocks[selects].paids.add(pay_card);
											blocks[turn].owns.remove(select_target_index);
											message(blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 1) + blocks[selects].name + setting.getLang().getText(Language.PAIDS) + ", " + pay_card);
											if(change_card)
											{
												Vector<String> tempOwns = blocks[turn].owns;
												blocks[turn].owns = blocks[selects].owns;
												blocks[selects].owns = tempOwns;
												message(setting.getLang().getText(Language.DESCRIPTIONS + 26) + blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 27) + blocks[selects].name + setting.getLang().getText(Language.DESCRIPTIONS + 28));
											}
											for(int i=0; i<blocks.length; i++)
											{
												blocks[i].calculate(0);
											}
											turn_success = true;
											select_while = false;
										}
										else
										{
											message(last_card + setting.getLang().getText(Language.DESCRIPTIONS + 7));
											continue;
										}
										
									}
									else
									{
										message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
										continue;
									}
								} 
								catch (Exception e)
								{
									if(setting.isError_printDetail()) e.printStackTrace();
									message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
									continue;
								}
							}
						
						if(select_cancel)
						{
							continue;
						}
						
						
					}
					else if(selects == blocks.length)
					{
						cards = deck.get(0);
						deck.remove(0);
						blocks[turn].owns.add(cards);
						message(blocks[turn].name + setting.getLang().getText(Language.DESCRIPTIONS + 0));
						turn_success = true;
					}
					else if(selects == (blocks.length + 1))
					{
						game_while = false;
						break;
					}
					else
					{
						message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
						continue;
					}
					
					
				}
				catch(NumberFormatException e)
				{
					if(setting.isError_printDetail()) e.printStackTrace();
					message(setting.getLang().getText(Language.DESCRIPTIONS + 20));
					continue;
				} 
				catch (IOException e)
				{
					e.printStackTrace();
					continue;
				}				
			}
			if(turn_success)
			{
				if(finish_check())
				{
					finish();
				}					
				else
				{
					turn++;
					if(turn >= blocks.length)
					{
						turn = 0;
					}
				}
			}
		}
	}
	private boolean finish_check()
	{
		if(deck.size() <= 0) return true;
		else
		{
			for(int i=0; i<blocks.length; i++)
			{
				if(blocks[i].owns.size() <= 0) return true;
			}						
		}
		return false;
	}
	private void finish()
	{
		game_while = false;
		int few_card_player = 0;
		int min_card_count = blocks[0].owns.size();
		for(int i=0; i<blocks.length; i++)
		{
			if(min_card_count > blocks[i].owns.size())
			{
				min_card_count = blocks[i].owns.size();
				few_card_player = i;
			}
		}
		message_bar();
		message(setting.getLang().getText(Language.BONUS_TARGET) + blocks[few_card_player].name);
		for(int i=0; i<blocks.length; i++)
		{
			if(i != few_card_player) blocks[i].calculate(0);
			else blocks[i].calculate(5);
		}
		
		int winner = 0;
		long max_point = blocks[0].getPoint();
		for(int i=0; i<blocks.length; i++)
		{
			if(max_point < blocks[i].getPoint())
			{
				max_point = blocks[i].getPoint();
				winner = i;
			}
		}
		
		for(int i=0; i<blocks.length; i++)
		{
			message(" " + blocks[i].name + " " + setting.getLang().getText(Language.POINT) + " : " + blocks[i].getPoint());
		}
		message(blocks[winner].name + " " + setting.getLang().getText(Language.WON));
		
		if(authorized && (! blocks[winner].ai)) make_authorized(winner);
		
		message_bar();
	}
	private void make_authorized(int winner)
	{
		StringBuffer auth_code = new StringBuffer("");
		String[] auth_codes = new String[17];
		auth_codes[0] = String.valueOf(blocks[winner].point);
		auth_codes[1] = new String(blocks[winner].name + " (※)");
		auth_codes[2] = String.valueOf(blocks.length);
		auth_codes[3] = String.valueOf(blocks[winner].owns.size());
		auth_codes[4] = String.valueOf(version_main);
		auth_codes[5] = String.valueOf(version_sub_1);
		auth_codes[6] = String.valueOf(version_sub_2);
		BigInteger secret_code = Lint.big(0);
		BigInteger secret_nameCode = Lint.big(0);
		secret_code = secret_code.add(Lint.big((version_main * 100) + (version_sub_1 * 10) + version_sub_2));
		secret_code = secret_code.multiply(Lint.big(blocks[winner].owns.size()));
		secret_code = secret_code.add(Lint.big(blocks[winner].point));
		
		// authority_code used
		long authority_code = CalcWindow.getAuthorizedCode(2938291);
		secret_code = secret_code.add(Lint.big(authority_code + (blocks.length * ((version_main * 100) + (version_sub_1 * 10) + version_sub_2))));
								
		Calendar calendar_inst = Calendar.getInstance();
		int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
		aut_year = calendar_inst.get(Calendar.YEAR);
		aut_month = calendar_inst.get(Calendar.MONTH);
		aut_day = calendar_inst.get(Calendar.DATE);
		aut_hour = calendar_inst.get(Calendar.HOUR);
		aut_min = calendar_inst.get(Calendar.MINUTE);
		aut_sec = calendar_inst.get(Calendar.SECOND);
		
		auth_codes[8] = String.valueOf(aut_year);
		auth_codes[9] = String.valueOf(aut_month);
		auth_codes[10] = String.valueOf(aut_day);
		auth_codes[11] = String.valueOf(aut_hour);
		auth_codes[12] = String.valueOf(aut_min);
		auth_codes[13] = String.valueOf(aut_sec);
		auth_codes[14] = "calc_console";
		auth_codes[15] = "Calc";
		
		secret_nameCode = Lint.copy(secret_code);
		secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(blocks[winner].name + " (※)") / 100.0) + 5)));
		secret_code = secret_code.add(Lint.big((blocks[winner].owns.size() + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
		secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
		
		auth_codes[7] = String.valueOf(secret_code);
		auth_codes[16] = String.valueOf(secret_nameCode.toString());
		
		for(int i=0; i<auth_codes.length; i++)
		{
			auth_code.append(auth_codes[i]);
			if(i < auth_codes.length - 1) auth_code.append("||");
		}		
		
		message_bar();
		message(setting.getLang().getText(Language.AUTHORITY) + " : " + auth_code.toString());
		message_bar();
	}
	private void start()
	{
		turn = 0;
		int change_cards = setting.getChange_card_count();
		
		// players clear
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i].clear();
		}
		
		if(authorized)
		{
			blocks = new ConsoleBlock[4];
			for(int i=0; i<blocks.length; i++)
			{
				blocks[i] = new ConsoleBlock(setting);				
			}
			blocks[0].setName(setting.getLang().getText(Language.PLAYER));
			blocks[1].setName(setting.getLang().getText(Language.AI) + " " + String.valueOf(1));
			blocks[2].setName(setting.getLang().getText(Language.AI) + " " + String.valueOf(2));
			blocks[3].setName(setting.getLang().getText(Language.AI) + " " + String.valueOf(3));
			blocks[0].setAi(false);
			blocks[1].setAi(true);
			blocks[2].setAi(true);
			blocks[3].setAi(true);
			change_cards = 1;
		}
		
		// randomize players
		Vector<ConsoleBlock> newBlock1 = new Vector<ConsoleBlock>();
		Vector<ConsoleBlock> newBlock2 = new Vector<ConsoleBlock>();
		int randoms = 0;
		
		for(int i=0; i<blocks.length; i++)
		{
			newBlock1.add(blocks[i].clone());
		}
		while(newBlock1.size() >= 1)
		{
			randoms = (int) (Math.random() * newBlock1.size());
			newBlock2.add(newBlock1.get(randoms));
			newBlock1.remove(randoms);
		}
		blocks = new ConsoleBlock[newBlock2.size()];
		for(int i=0; i<newBlock2.size(); i++)
		{
			blocks[i] = newBlock2.get(i);
		}
		
		 // deck prepare
		deck.clear();
		char op = setting.getOp_plus();
		String newCard = "";
		for(int i=-1; i<10; i++)
		{
			for(int j=0; j<3; j++)
			{
				switch(j)
				{
					case 0:
						op = setting.getOp_plus();
						break;
					case 1:
						op = setting.getOp_minus();
						break;
					case 2:
						op = setting.getOp_multiply();
						break;
				}
				newCard = String.valueOf(op);
				newCard = newCard + setting.getCard_separator() + String.valueOf(i);
				for(int k=0; k<4; k++)
					deck.add(newCard);
			}
		}
		
		// change card
		String change_card = null;
		for(int i=0; i<change_cards; i++)
		{
			change_card = String.valueOf(setting.getOp_change()) + setting.getCard_separator() + String.valueOf(1);
			deck.add(change_card);
		}
		
		// deck randomize
		Vector<String> deck2 = new Vector<String>();
		while(deck.size() >= 1)
		{
			randoms = (int) (Math.random() * deck.size());
			deck2.add(deck.get(randoms));
			deck.remove(randoms);
		}
		deck.clear();
		for(int i=0; i<deck2.size(); i++)
		{
			deck.add(deck2.get(i));
		}
		
		// cards to each players
		for(int i=0; i<blocks.length; i++)
		{
			blocks[i].clear();
			for(int j=0; j<setting.getStart_givenCards(); j++)
			{
				blocks[i].owns.add(deck.get(0));
				deck.remove(0);
			}
		}	
		
		// ready to set
		game_while = true;
	}
	@Override
	public void open()
	{
		run_game();
	}
	@Override
	public void exit()
	{
		run_game = false;
		try
		{
			bfreader.close();
			input_str.close();
		} 
		catch (Exception e)
		{
			
		}
		System.exit(0);
	}
	@Override
	public void message()
	{
		System.out.println();
	}
	@Override
	public void message(String str)
	{
		System.out.println(str);
	}
	@Override
	public void alert(String str)
	{
		message(str);
	}
	@Override
	public void message_bar()
	{
		System.out.println("-----------------------------------------------------------");		
	}
	@Override
	public void openConsole()
	{
		// TODO Auto-generated method stub
		
	}
}
class ConsoleBlock implements Cloneable, Serializable
{
	private static final long serialVersionUID = 3923850112445382379L;
	public String name = "사용자";
	public Vector<String> owns;
	public Vector<String> paids;
	public long point = 0;
	public boolean ai = false;
	public Setting set;
	public ConsoleBlock(Setting set)
	{
		this.set = set.clone();
		owns = new Vector<String>();
		paids = new Vector<String>();
		point = 0;
	}
	public void clear()
	{
		owns.clear();
		paids.clear();
		point = 0;
	}
	public ConsoleBlock clone()
	{
		ConsoleBlock newOne = new ConsoleBlock(set);
		newOne.name = new String(this.name);
		for(int i=0; i<owns.size(); i++)
		{
			newOne.owns.add(owns.get(i));
		}
		for(int i=0; i<paids.size(); i++)
		{
			newOne.paids.add(paids.get(i));
		}
		newOne.point = this.point;
		newOne.ai = this.ai;
		
		return newOne;
	}
	public void calculate(long bonus)
	{
		if(paids.size() >= 1)
		{
			point = 0;
			String card = "";
			char op = set.getOp_plus();
			int num = 0;
			StringTokenizer stoken;
			for(int i=0; i<paids.size(); i++)
			{
				card = paids.get(i);
				stoken = new StringTokenizer(card, set.getCard_separator());
				op = stoken.nextToken().toCharArray()[0];
				num = Integer.parseInt(stoken.nextToken());
				if(i == 0)
				{
					point += num;
				}
				else
				{
					if(op == set.getOp_plus())
					{
						point += num;
					}
					else if(op == set.getOp_minus())
					{
						point -= num;
					}
					else if(op == set.getOp_multiply())
					{
						point *= num;
					}					
				}
			}
			point += bonus;
		}
		else point = bonus;
	}
	public void show(MessageShowable mn, Setting set, boolean turn)
	{
		//mn.message_bar();
		String ai_player = "";
		if(ai) ai_player = set.getLang().getText(Language.AI);
		else ai_player = set.getLang().getText(Language.PLAYER);
		mn.message("■ " + ai_player + " " + this.name + " " + set.getLang().getText(Language.INFORMATION));
		mn.message(" " + set.getLang().getText(Language.POINT) + " : " + this.point);
		mn.message(" " + set.getLang().getText(Language.PAIDS) + " (" + paids.size() + ")\t" + set.getLang().getText(Language.OWNS) + " (" + owns.size() + ")");
		int print_lines = owns.size();
		if(print_lines < paids.size()) print_lines = paids.size();
		String left = "";
		String right = "";
		for(int i=0; i<print_lines; i++)
		{
			if(i < owns.size())
			{
				if(turn)
				{
					left = owns.get(i);
				}
				else
				{
					if(i == 0) left = set.getLang().getText(Language.SEALED);
					else left = "";
				}
			}
			if(i < paids.size())
			{
				right = paids.get(i);
			}
			mn.message(" " + left + "\t" + right);
		}
		//mn.message_bar();
	}
	public String getLastPaid()
	{
		if(paids.size() >= 1)
		{
			return paids.get(paids.size() - 1);
		}
		else return set.getLang().getText(Language.NONE);
	}
	public void removeLastPaid()
	{
		paids.remove(paids.size() - 1);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Vector<String> getOwns()
	{
		return owns;
	}
	public void setOwns(Vector<String> owns)
	{
		this.owns = owns;
	}
	public Vector<String> getPaids()
	{
		return paids;
	}
	public void setPaids(Vector<String> paids)
	{
		this.paids = paids;
	}
	public long getPoint()
	{
		return point;
	}
	public void setPoint(long point)
	{
		this.point = point;
	}
	public boolean isAi()
	{
		return ai;
	}
	public void setAi(boolean ai)
	{
		this.ai = ai;
	}
}
