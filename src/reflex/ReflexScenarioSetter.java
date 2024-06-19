package reflex;

import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import lang.Language;
import setting.Lint;
import setting.Setting;

public class ReflexScenarioSetter
{
	public static List<ReflexScenario> standards()
	{
		return standards(false, null);
	}
	public static List<ReflexScenario> standards(boolean alsoFiles)
	{
		return standards(alsoFiles, Setting.getNewInstance().getDefault_path());
	}
	public static List<ReflexScenario> standards(String path)
	{
		return standards(true, path);
	}
	public static List<ReflexScenario> standards(boolean alsoFiles, String path)
	{
		Vector<ReflexScenario> scenarios = new Vector<ReflexScenario>();
		
		BReflexScenario newScenario;
		Vector<EnemyPattern> patterns = new Vector<EnemyPattern>();
		EnemyPattern[] patternArray;
		EnemyPattern newEnemyPattern;
		Enemy newEnemy;
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new DReflexScenario();
		newScenario.setName("Easy to play");
		newScenario.setDescription("This is sample for scenario editor.");		
		newScenario.setKoreanDescription("시나리오 에디터를 위한 샘플입니다.");
		newScenario.setDifficulty(new Integer(1));
		newScenario.setDiff_delay(new Long(7000));
		newScenario.setEnemy_limit(new Integer(30));
		newScenario.setSpaceShip("flex");
		((CReflexScenario) newScenario).setSpaceShip_selectable(new Boolean(true));
		
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.1);
		newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(500);
		newEnemy.setDamage(10);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);	
		
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(8000));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.001);
		newEnemy = new HyperBoss();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(5000);
		newEnemy.setDamage(10);
		((Boss)newEnemy).setBeam_energy(300);
		((Boss)newEnemy).setRatio(0.99);
		((Boss)newEnemy).setUnique(true);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		patterns.add(newEnemyPattern);
		
		patternArray = new EnemyPattern[patterns.size()];		
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);			
		newScenario.setGrade_limit(new Integer(0));	
		((DReflexScenario) newScenario).setDeadline(Lint.big(19999));
		((DReflexScenario) newScenario).setStar1_least(new Long(10000));
		newScenario.setStar1(new Long(0));
		newScenario.setStar2(new Long(100000));
		newScenario.setStar3(new Long(1000000));
		String newScript = " // 10 프레임 마다 프레임 번호를 출력합니다.\n"; 			
		newScript = newScript + " if(reflexer.time() % 10 == 0) // 현재까지 진행된 프레임 수가 10으로 나누어 떨어질 때\n";
		newScript = newScript + " {\n";
		//newScript = newScript + " reflexer.addMessage( \"1234567891011121314151617181920나는단국대학생\" );\n";
		newScript = newScript + " \t reflexer.clearMessage(); // 메시지 리스트를 비우기\n";
		newScript = newScript + " \t reflexer.addMessage( reflexer.time() ); // 프레임 수를 메시지 리스트에 추가\n";
		newScript = newScript + " }\n";
		newScript = newScript + " if(reflexer.time() >= 10000) // 프레임이 10000 만큼 지났는지 확인\n";
		newScript = newScript + " {\n";
		newScript = newScript + " \t reflexer.addMessage(\"Great ! The time is over 10000\")\n";
		newScript = newScript + " }\n";
		
		newScenario.setScript(newScript);
		newScenario.setRandomCode(new Long(82729313));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new CReflexScenario();
		newScenario.setName("Hyper Flex");
		newScenario.setDescription("There are only bosses.");		
		newScenario.setKoreanDescription("처음부터 끝까지 대형 적만 등장합니다.");
		newScenario.setDifficulty(new Integer(15000));
		newScenario.setDiff_delay(new Long(3000));
		newScenario.setEnemy_limit(new Integer(3));
		newScenario.setSpaceShip("flex");
		((CReflexScenario) newScenario).setSpaceShip_selectable(new Boolean(true));
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.01);
		newEnemy = new Boss();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(5000);		
		((Boss)newEnemy).setBeam_energy(300);
		((Boss)newEnemy).setRatio(0.99);
		((Boss)newEnemy).setUnique(false);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);	
		
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.001);
		newEnemy = new HyperBoss();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(5000);
		((Boss)newEnemy).setBeam_energy(300);
		((Boss)newEnemy).setRatio(0.99);
		((Boss)newEnemy).setUnique(true);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		patterns.add(newEnemyPattern);
		
		patternArray = new EnemyPattern[patterns.size()];
				
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);			
		newScenario.setGrade_limit(new Integer(0));	
		newScenario.setRandomCode(new Long(4773723));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new CReflexScenario();
		newScenario.setName("See Carefully");
		newScenario.setDescription("Enemies cre sealed from shadow.");		
		newScenario.setKoreanDescription("적들은 그림자에 가려져 있습니다.");
		newScenario.setDifficulty(new Integer(5100));
		newScenario.setDiff_delay(new Long(3000));
		newScenario.setEnemy_limit(new Integer(3));
		newScenario.setSpaceShip("flex");
		((CReflexScenario) newScenario).setSpaceShip_selectable(new Boolean(true));
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.1));
		newEnemyPattern.setRatio(0.01);
		newEnemy = new SealedEnemy(Reflexioner.getFile_path());
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(200);		
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);	
		
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.001);
		newEnemy = new SealedQuickBoss(Reflexioner.getFile_path());
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(5000);
		((Boss)newEnemy).setBeam_energy(300);
		((Boss)newEnemy).setRatio(0.99);
		((Boss)newEnemy).setUnique(false);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		patterns.add(newEnemyPattern);
		
		patternArray = new EnemyPattern[patterns.size()];
				
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);	
		
		newScenario.setGrade_limit(new Integer(0));	
		newScenario.setRandomCode(new Long(2984952));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
		newScenario = new CReflexScenario();
		newScenario.setName("Clipper Experience");
		newScenario.setDescription("Try to control special ship called \'Clipper\'.\nThis ship is for Professional, Ultimate, and Master Edition.");
		newScenario.setKoreanDescription("\'Clipper\' 라는 특별한 함선을 체험해 보세요.\n이 함선은 원래 Professional, Ultimate, 또는 Master 버전에서만 사용할 수 있습니다.\n\n함선 체험 시나리오는 난이도가 매우 높습니다.");
		newScenario.setDifficulty(new Integer(50));
		newScenario.setDiff_delay(new Long(3000));
		newScenario.setEnemy_limit(new Integer(20));
		newScenario.setSpaceShip("clipper");
		((CReflexScenario) newScenario).setSpaceShip_selectable(new Boolean(false));
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.1);
		newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(800);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);
		
		newEnemyPattern = new BossEnemyPattern();
		patterns.add(newEnemyPattern);
		
		patternArray = new EnemyPattern[patterns.size()];
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);		
		newScenario.setGrade_limit(new Integer(0));	
		newScenario.setRandomCode(new Long(92482914));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);	
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new BReflexScenario();
		newScenario.setName("Warship Experience");
		newScenario.setDescription("Try to control special ship called \'Warship\'.\nThis ship is for Ultimate, and Master Edition.");
		newScenario.setKoreanDescription("\'Warship\' 라는 특별한 함선을 체험해 보세요.\n이 함선은 원래 Ultimate, 또는 Master 버전에서만 사용할 수 있습니다.\n\n함선 체험 시나리오는 난이도가 매우 높습니다.");
		newScenario.setDifficulty(new Integer(25));
		newScenario.setDiff_delay(new Long(3000));
		newScenario.setEnemy_limit(new Integer(20));
		newScenario.setSpaceShip("warship");
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.1);
		newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(800);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);
		
		newEnemyPattern = new BossEnemyPattern();
		patterns.add(newEnemyPattern);		
		
		patternArray = new EnemyPattern[patterns.size()];
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);		
		newScenario.setGrade_limit(new Integer(1));	
		newScenario.setRandomCode(new Long(7253672));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new BReflexScenario();
		newScenario.setName("Chaser Experience");
		newScenario.setDescription("Try to control special ship called \'Chaser\'.\nThis ship is for Ultimate, and Master Edition.");
		newScenario.setKoreanDescription("\'Chaser\' 라는 특별한 함선을 체험해 보세요.\n이 함선은 원래 Ultimate, 또는 Master 버전에서만 사용할 수 있습니다.\n\n함선 체험 시나리오는 난이도가 매우 높습니다.");
		newScenario.setDifficulty(new Integer(25));
		newScenario.setDiff_delay(new Long(5500));
		newScenario.setEnemy_limit(new Integer(20));
		newScenario.setSpaceShip("chaser");
		
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.1);
		newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(800);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);
		
		newEnemyPattern = new BossEnemyPattern();
		patterns.add(newEnemyPattern);		
		
		patternArray = new EnemyPattern[patterns.size()];
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);		
		newScenario.setGrade_limit(new Integer(1));
		newScenario.setRandomCode(new Long(16835231));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
		patterns = new Vector<EnemyPattern>();
		newScenario = new BReflexScenario();
		newScenario.setName("Carrier Experience");
		newScenario.setDescription("Try to control special ship called \'Carrier\'.\nThis ship is for Ultimate, and Master Edition.");
		newScenario.setKoreanDescription("\'Carrier\' 라는 특별한 함선을 체험해 보세요.\n이 함선은 원래 Master 버전에서만 사용할 수 있습니다.\n\n함선 체험 시나리오는 난이도가 매우 높습니다.");
		newScenario.setDifficulty(new Integer(25));
		newScenario.setDiff_delay(new Long(5550));
		newScenario.setEnemy_limit(new Integer(20));
		newScenario.setSpaceShip("carrier");
		newEnemyPattern = new EnemyPattern();
		newEnemyPattern.setMin_delay(new Long(0));
		newEnemyPattern.setMax_delay(new Long(-1));
		newEnemyPattern.setAddDamageRatio(new Double(0.01));
		newEnemyPattern.setAddHPRatio(new Double(0.01));
		newEnemyPattern.setRatio(0.1);
		newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(100);
		newEnemy.setMax_hp(800);
		newEnemy.init();
		newEnemyPattern.setEnemy(newEnemy);
		newScenario.setDefaultPattern(newEnemyPattern);
		
		newEnemyPattern = new BossEnemyPattern();
		patterns.add(newEnemyPattern);
		patternArray = new EnemyPattern[patterns.size()];
		for(int i=0; i<patternArray.length; i++)
		{
			patternArray[i] = patterns.get(i);
		}
		newScenario.setPatterns(patternArray);		
		newScenario.setGrade_limit(new Integer(1));
		newScenario.setRandomCode(new Long(83623842));
		newScenario.setAuth(new Long(newScenario.authorized(1937283 + 1001008)));
		scenarios.add(newScenario);
		
				
		
		if(alsoFiles)
		{
			try
			{
				File folder = new File(path);
				if(! folder.exists()) folder.mkdir();
				File target;
				FileOutputStream fout = null;
				XMLEncoder encoder = null;
				OutputStreamWriter outWriter = null;
				BufferedWriter writer = null;
				String reports = Setting.getNewInstance().getLang().getText(Language.FILE_CREATED);
				for(ReflexScenario s : scenarios)
				{
					try
					{
						target = new File(path + s.getName() + ".rfsx");
						fout = new FileOutputStream(target);
						encoder = new XMLEncoder(fout);
						encoder.writeObject(s);
						reports = reports + target.getAbsolutePath() + "\n";
					} 
					catch (Exception e)
					{
						reports = reports + s.getName() + " (" + e.getMessage() + ")\n";
						e.printStackTrace();
					}	
					finally
					{
						try
						{
							encoder.close();
						} 
						catch (Exception e)
						{
							
						}
						try
						{
							fout.close();
						} 
						catch (Exception e)
						{
							
						}
					}
					if(s instanceof AReflexScenario)
					{
						try
						{
							target = new File(path + s.getName() + ".rfst");
							fout = new FileOutputStream(target);
							outWriter = new OutputStreamWriter(fout);
							writer = new BufferedWriter(outWriter);
							StringTokenizer lineToken = new StringTokenizer(((AReflexScenario) s).stringData(), "\n");
							while(lineToken.hasMoreTokens())
							{
								writer.write(lineToken.nextToken());
								writer.newLine();
							}
							reports = reports + target.getAbsolutePath() + "\n";
						}
						catch (Exception e)
						{
							reports = reports + s.getName() + " (" + e.getMessage() + ")\n";
							e.printStackTrace();
						}	
						finally
						{
							try
							{
								writer.close();
							} 
							catch (Exception e)
							{
								
							}
							try
							{
								outWriter.close();
							} 
							catch (Exception e)
							{
								
							}
							try
							{
								fout.close();
							} 
							catch (Exception e)
							{
								
							}
						}
					}
				}
				JOptionPane.showMessageDialog(null, reports);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return scenarios;
	}
}
