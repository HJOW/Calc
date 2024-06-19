package network;

import java.io.File;

import main_classes.StandardCard;
import setting.Setting;

public class ScenarioEditor 
{

	public static void edit(Setting sets)
	{
		Setting settings = sets;
		if(settings == null) settings = Setting.getNewInstance();
		try
		{
			String lang = System.getProperty("user.language");
			int langs = 0;
			
			if(lang.equalsIgnoreCase("ko") || lang.equalsIgnoreCase("kor") || lang.equalsIgnoreCase("kr") || lang.equalsIgnoreCase("korean")) langs = 1;
			else langs = 0;
			
			if(langs == 1)
				System.out.println("Calc 시나리오 팩 생성기");
			else
				System.out.println("Calc Scenario Pack Creator");
			
			System.out.println();
			System.out.println("Made by HJOW");
			System.out.println();
			System.out.println();
			network.AuthorizedScenarioPackage packs = new network.AuthorizedScenarioPackage();
			java.io.InputStreamReader inputs = new java.io.InputStreamReader(System.in);
			java.io.BufferedReader binputs = new java.io.BufferedReader(inputs);
			String inputStrings = "";
			boolean makeNotFinished = true;
			
			if(langs == 1)
				System.out.print("새로운 패키지의 이름을 입력하십시오 : ");
			else
				System.out.print("Input name of new package : ");
				
			inputStrings = binputs.readLine();
			packs.setName(inputStrings);
						
			System.out.println();
			if(langs == 1)
			{
				System.out.println(packs.getName() + " 패키지의 설명을 입력합니다. 여러 줄을 입력하실 수 있습니다.");
				System.out.println("입력을 완료하였으면 -exit 를 입력하고 엔터 키를 누르십시오.");
			}
			else
			{
				System.out.println("Input the package " + packs.getName() + " \'s descriptions.");
				System.out.println("If you completely input descriptions, input  -exit  and press enter key to finish input mode.");
			}
			packs.setDescription("");
			while(true)
			{
				inputStrings = binputs.readLine();								
				if(inputStrings.equalsIgnoreCase("-exit")) break;
				packs.setDescription(packs.getDescription() + inputStrings + "\n");
			}
			
			while(true)
			{
				if(langs == 1)
				{
					System.out.println("이 시나리오 패키지를 Basic 에디션에서 이용할 수 있게 할까요?");
					System.out.println("0 을 입력하면 모든 에디션에서 이용 가능합니다.");
					System.out.println("1 을 입력하면 Professional, Ultimate 에디션에서 이용 가능합니다.");
					System.out.println("2 를 입력하면 Ultimate 에디션에서만 이용 가능합니다.");
					System.out.print("허가 : ");
				}
				else
				{
					System.out.println("Do you want to allow to play this pack on Basic edition?");
					System.out.println("0. Playable at all editions.");
					System.out.println("1. For only Professional and Ultimate.");
					System.out.println("2. For only Ultimate.");
					System.out.print("Allows : ");
				}
				inputStrings = binputs.readLine();
				int edit = 0;
				try
				{
					edit = Integer.parseInt(inputStrings);
					if(edit < 0 || edit > 2)
					{
						if(langs == 1)
						{
							System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
						}
						else
						{
							System.out.println("Input correct integer value in range.");
						}
						continue;
					}
					packs.setEdition(new Integer(edit));
					break;
				} 
				catch (NumberFormatException e)
				{
					if(langs == 1)
						System.out.println("정수 값을 입력하여야 합니다.");
					else
						System.out.println("Input integer value.");
				}
			}
			
			System.out.println();
			if(langs == 1)
				System.out.println("패키지에 들어갈 시나리오들을 작성합니다.");
			else
				System.out.println("Now, time to create scenarios.");
			System.out.println();
			
			while(makeNotFinished)
			{
				setting.AuthorizedScenario newScenario = new setting.AuthorizedScenario();
				
				if(langs == 1)
					System.out.print("새로운 시나리오의 영문 이름을 입력하십시오 : ");
				else
					System.out.print("Input new scenario\'s name : ");
				inputStrings = binputs.readLine();
				newScenario.setName(inputStrings);
				
				if(langs == 1)
					System.out.print("새로운 시나리오의 한글 이름을 입력하십시오 : ");
				else
					System.out.print("Input new scenario\'s korean name : ");
				inputStrings = binputs.readLine();
				newScenario.setKoreanName(inputStrings);
				
				System.out.println();
				if(langs == 1)
				{
					System.out.println(newScenario.getName() + " 시나리오의 설명을 영어로 입력합니다. 여러 줄을 입력하실 수 있습니다.");
					System.out.println("입력을 완료하였으면 -exit 를 입력하고 엔터 키를 누르십시오.");
				}
				else
				{
					System.out.println("Input descriptions of scenario " + newScenario.getName() + ".");
					System.out.println("If you completely input descriptions, input  -exit  and press enter key to finish input mode.");
				}
				newScenario.setDescription("");
				while(true)
				{
					inputStrings = binputs.readLine();								
					if(inputStrings.equalsIgnoreCase("-exit")) break;
					newScenario.setDescription(newScenario.getDescription() + inputStrings + "\n");
				}
				if(langs == 1)
				{
					System.out.println(newScenario.getName() + " 시나리오의 설명을 한글로 입력합니다. 여러 줄을 입력하실 수 있습니다.");
					System.out.println("입력을 완료하였으면 -exit 를 입력하고 엔터 키를 누르십시오.");
				}
				else
				{
					System.out.println("Input korean descriptions of scenario " + newScenario.getName() + ".");
					System.out.println("If you completely input descriptions, input  -exit  and press enter key to finish input mode.");
				}
				newScenario.setKoreanDescription("");
				while(true)
				{
					inputStrings = binputs.readLine();								
					if(inputStrings.equalsIgnoreCase("-exit")) break;
					newScenario.setKoreanDescription(newScenario.getKoreanDescription() + inputStrings + "\n");
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오의 난이도는 얼마입니까? 정수 -10 ~ 10000 사이로 입력해 주세요.");
						System.out.print("난이도 : ");
					}
					else
					{
						System.out.println("Input this scenario\'s difficulty as integer -10 ~ 10000.");
						System.out.print("Difficulty : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						if(diff < -10 || diff > 10000)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
								
							continue;
						}
						newScenario.setDifficulty(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 카드 수가 가장 적은 상태로 게임이 끝났을 때 플레이어가 받는 보너스 점수를 지정합니다.");
						System.out.println("5 를 권장합니다.");
						System.out.print("보너스 점수 : ");
					}
					else
					{
						System.out.println("Input bonus points. When the game is finished, the player who has the least cards get this bonus points.");
						System.out.println("Recommend : 5");
						System.out.print("Point : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						/*
						if(diff < -10000 || diff > 10000)
						{
							System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							continue;
						}
						*/
						newScenario.setBonuses(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				
				System.out.println();
				
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 곱셈 카드 비율을 지정합니다. 각 곱셈 카드들은 이 비율 만큼 만들어져 분배됩니다.");
						System.out.println("정수 값으로, 0 ~ 10 사이의 값을 입력하십시오. 4 를 권장합니다.");
						System.out.print("곱셈 카드 비율 : ");
					}
					else
					{
						System.out.println("Input multiply card ratio.");
						System.out.println("Input integer number 0 ~ 10. Recommend : 4.");
						System.out.print("Ratio : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 0 || diff > 10)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setMultiply_card_ratio(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 덧셈과 뺄셈 카드 비율을 지정합니다. 각 덧셈과 뺄셈 카드들은 이 비율 만큼 만들어져 분배됩니다.");
						System.out.println("정수 값으로, 0 ~ 10 사이의 값을 입력하십시오. 4 를 권장합니다.");
						System.out.print("덧/뺄셈 카드 비율 : ");
					}
					else
					{
						System.out.println("Input plus and minus cards ratio.");
						System.out.println("Input integer number 0 ~ 10. Recommend : 4.");
						System.out.print("Ratio : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 0 || diff > 10)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setPlusminus_card_ratio(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, §카드 수를 지정합니다.");
						System.out.println("정수 값으로, 0 ~ 100 사이의 값을 입력하십시오. 1 을 권장합니다.");
						System.out.print("§수 : ");
					}
					else
					{
						System.out.println("Input how many §cards allowed to use in this scenario.");
						System.out.println("Input integer number 0 ~ 100. Recommend : 1.");
						System.out.print("§cards : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 0 || diff > 100)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setChange_card_count(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 플레이어가 처음에 받는 카드 수를 지정합니다.");
						System.out.println("정수 값으로, 0 ~ 100 사이의 값을 입력하십시오. 10 을 권장합니다.");
						System.out.print("플레이어 카드 수 : ");
					}
					else
					{
						System.out.println("Input integer value how many cards the player takes at the game starts.");
						System.out.println("Input integer number 0 ~ 100. Recommend : 10.");
						System.out.print("Cards : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 0 || diff > 100)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setPlayer_cards_count(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 각 인공지능들이 처음에 받는 카드 수를 지정합니다.");
						System.out.println("정수 값으로, 0 ~ 100 사이의 값을 입력하십시오. 10 을 권장합니다.");
						System.out.print("인공지능 카드 수 : ");
					}
					else
					{
						System.out.println("Input integer value how many cards each AIs take at the game starts.");
						System.out.println("Input integer number 0 ~ 100. Recommend : 10.");
						System.out.print("Cards : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 0 || diff > 100)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setAi_cards_count(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				System.out.println();
				while(true)
				{
					if(langs == 1)
					{
						System.out.println("이 시나리오에서, 플레이어가 매 차례에 활동하는 제한 시간을 지정합니다.");
						System.out.println("정수 값으로, 1 ~ 1000 사이의 값을 입력하십시오. 20 을 권장합니다.");
						System.out.print("시간 제한 : ");
					}
					else
					{
						System.out.println("Input time limit each players get.");
						System.out.println("Input integer number 0 ~ 1000. Recommend : 20.");
						System.out.print("Time : ");
					}
					inputStrings = binputs.readLine();
					int diff = 0;
					try
					{
						diff = Integer.parseInt(inputStrings);
						
						if(diff < 1 || diff > 1000)
						{
							if(langs == 1)
								System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
							else
								System.out.println("Input correct integer value in range.");
							
							continue;
						}
						
						newScenario.setTimelimit(new Integer(diff));
						break;
					} 
					catch (NumberFormatException e)
					{
						if(langs == 1)
							System.out.println("정수값을 입력하여야 합니다.");
						else
							System.out.println("Input integer value.");
					}
				}
				
				while(true)
				{
					if(langs == 1)
						System.out.print("특별한 카드를 추가하시겠습니까? (y/n) : ");
					else
						System.out.print("Do you want to add more special cards? (y/n) : ");
						
					inputStrings = binputs.readLine();
					if(inputStrings.equalsIgnoreCase("n")) break;
					else if(inputStrings.equalsIgnoreCase("y"))
					{
						if(langs == 1)
							System.out.println("새로운 카드의 기호를 입력합니다.");
						else
							System.out.println("Input the operation of new card.");
						
						System.out.println("1. +\t 2. -\t 3. ×");
						
						if(langs == 1)
							System.out.print("Operation : ");
						inputStrings = binputs.readLine();
						char op = ' ';
						if(inputStrings.equalsIgnoreCase("1"))
						{
							op = '+';
						}
						else if(inputStrings.equalsIgnoreCase("2"))
						{
							op = '-';
						}
						else if(inputStrings.equalsIgnoreCase("3"))
						{
							op = '×';
						}
						else
						{
							if(langs == 1)
								System.out.println("다시 입력해 주세요.");
							else
								System.out.println("Try again.");
								
							continue;
						}
						int po = 0;
						while (true)
						{
							try
							{
								if(langs == 1)
									System.out.print("새로운 카드의 숫자를 입력하십시오 : ");
								else
									System.out.print("Input the number of new card : ");
									
								inputStrings = binputs.readLine();
								po = Integer.parseInt(inputStrings);	
							} 
							catch (NumberFormatException e)
							{
								if(langs == 1)
									System.out.println("정수값을 입력하여야 합니다.");
								else
									System.out.println("Input integer value.");
								
								continue;
							}
							break;
						}
						int wheres = 0;
						while (true)
						{
							try
							{
								if(langs == 1)
								{
									System.out.println("새로운 카드를 배치할 위치를 지정합니다.");
									System.out.println("1. 덱");
									System.out.println("2. 플레이어가 처음에 받을 카드");
									System.out.println("3. 인공지능들이 처음에 받을 카드");
									System.out.print("어디 : ");
								}
								else
								{
									System.out.println("Where do you want to deploy new card?");
									System.out.println("1. Deck");
									System.out.println("2. Players (At the game starts)");
									System.out.println("3. AIs (At the game starts)");
									System.out.print("Where : ");
								}
								inputStrings = binputs.readLine();
								wheres = Integer.parseInt(inputStrings);
								if(wheres <= 0 || wheres >= 4)
								{
									if(langs == 1)
										System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
									else
										System.out.println("Input correct integer value in range.");
									
									continue;
								}
							} 
							catch (NumberFormatException e)
							{
								if(langs == 1)
									System.out.println("정수값을 입력하여야 합니다.");
								else
									System.out.println("Input integer value.");
								
								continue;
							}
							break;
						}
						int howMany = 0;
						while (true)
						{
							try
							{
								if(langs == 1)
									System.out.print("새로 만들어진 카드를 몇 개 넣겠습니까? (1 ~ 9999) : ");
								else
									System.out.print("How many times do you want to input new card? (1 ~ 9999) : ");
									
								inputStrings = binputs.readLine();
								howMany = Integer.parseInt(inputStrings);	
								if(howMany <= 0 || howMany >= 10000)
								{
									if(langs == 1)
										System.out.println("범위에 맞는 정수 값을 입력하여야 합니다.");
									else
										System.out.println("Input correct integer value in range.");
									
									continue;
								}
							} 
							catch (NumberFormatException e)
							{
								if(langs == 1)
									System.out.println("정수값을 입력하여야 합니다.");
								else
									System.out.println("Input integer value.");
								
								continue;
							}
							break;
						}
						StandardCard newCard = new StandardCard();
						newCard.setAce(false);
						newCard.setOp(op);
						newCard.setNum(po);
						for(int i=0; i<howMany; i++)
						{
							switch(wheres)
							{
								case 1:
									newScenario.getDeck_additionals().add((StandardCard) newCard.clone());
									break;
								case 2:
									newScenario.getPlayer_cards().add((StandardCard) newCard.clone());
									break;
								case 3:
									newScenario.getAi_cards().add((StandardCard) newCard.clone());
									break;
							}
						}			
						if(langs == 1)
							System.out.println("새로운 카드 " + newCard.toBasicString(settings.getCard_separator()) + " 이/가 추가되었습니다.");
						else
							System.out.println("New card " + newCard.toBasicString(settings.getCard_separator()) + " added.");
					}
					else
					{
						if(langs == 1)
							System.out.println("다시 입력해 주세요.");
						else
							System.out.println("Try again.");		
					}
				}				
				
				if(newScenario.getTurn_script() == null || newScenario.getTurn_script().trim().equals(""))
					newScenario.authorize(settings);
				packs.getScenarios().add(newScenario);
				
				if(langs == 1)
					System.out.println("시나리오 " + newScenario.getName() + " 이/가 추가되었습니다.");
				else
					System.out.println("New scenario " + newScenario.getName() + " added.");
				
				while(makeNotFinished)
				{
					if(langs == 1)
					{
						System.out.println("현재 시나리오 갯수 : " + packs.getScenarios().size());
						System.out.print("시나리오를 더 만드시겠습니까? (y/n) : ");
					}
					else
					{
						System.out.println("Now : " + packs.getScenarios().size() + " scenarios in this pack.");
						System.out.print("Do you want to make more scenario? (y/n) : ");
					}
					inputStrings = binputs.readLine();
					if(inputStrings.equalsIgnoreCase("n")) makeNotFinished = false;
					else if(inputStrings.equalsIgnoreCase("y")) break;					
					else
					{
						if(langs == 1)
							System.out.println("다시 입력해 주세요.");
						else
							System.out.println("Try again.");
					}
				}
			}
			
			try
			{
				binputs.close();
				inputs.close();
			}
			catch(Exception e)
			{
				
			}
			
			if(langs == 1)
				System.out.println("만들어진 패키지를 파일로 저장합니다. 잠시만 기다려 주시기 바랍니다.");
			else
				System.out.println("Please wait until new pack is finalizing...");
				
			packs.authorize();
			
			inputStrings = "additional_scenario";
			
			if(langs == 1)
				System.out.println("저장 준비가 끝났습니다.");
			else
				System.out.println("File ready.");
			
			try
			{
				if(langs == 1)
					System.out.print("파일 이름은 무엇으로 정하시겠습니까? : ");
				else
					System.out.print("Input new file name : ");
				
				inputStrings = binputs.readLine();
				binputs.close();
				inputs.close();
			}
			catch(Exception e)
			{
				inputStrings = "additional_scenario";
			}
			File dirs = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator"));
			if(! dirs.exists()) dirs.mkdir();
			java.io.FileOutputStream outputs = new java.io.FileOutputStream(System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator") + inputStrings + ".scen");
			java.beans.XMLEncoder encoder = new java.beans.XMLEncoder(outputs);
			encoder.writeObject(packs);
			encoder.close();
			outputs.close();
			
			if(langs == 1)
				System.out.println(System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator") + inputStrings + ".scen" + " 으로 시나리오 패키지가 저장되었습니다.");
			else
				System.out.println("Saved : " + System.getProperty("user.home") + System.getProperty("file.separator") + "calc" + System.getProperty("file.separator") + inputStrings + ".scen");
		} 
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
}
