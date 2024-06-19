package main_classes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import lang.English;
import lang.Korean;
import lang.Language;
import lang.UserDefinedKor;
import lang.UserDefinedLang;
import setting.Setting;
import setting.ThemeSet;

public class ConfigMaker implements Openable
{
	public Setting setting;
	public boolean gui_mode = false;
	private boolean independence = false;
	public ConfigMaker(boolean independence, Setting sets)
	{
		this.independence = independence;
		this.setting = sets;
	}
	
	public void open()
	{
		FileWriter langWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			File dir = new File(setting.getDefault_path());
			if(! dir.exists()) dir.mkdir();
			File langFile = new File(setting.getDefault_path() + "lang.cfg");
			
			langWriter = new FileWriter(langFile);
			bufferWriter = new BufferedWriter(langWriter);
			if(setting.getLang() instanceof Korean)
			{
				bufferWriter.write(UserDefinedKor.convert(setting.getLang()));
			}
			else
			{
				bufferWriter.write(UserDefinedLang.convert(setting.getLang()));
			}
		} 				
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufferWriter.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				langWriter.close();
			}
			catch (Exception e)
			{
				
			}
		}
		try
		{
			File cfgFile = new File(setting.getDefault_path() + "config.cfg");
			String writingConfig = "# Edit this file to force setting" + String.format("%n") + "#" + String.format("%n") + "# korean / english / file" + String.format("%n") + "# If this value is \"file\", the program load \"lang.cfg\" file." + String.format("%n") + "language = file" + String.format("%n") + "# Edit other values";
		
			writingConfig = writingConfig + String.format("%n") + "width = " + String.valueOf(setting.getWidth());
			writingConfig = writingConfig + String.format("%n") + "height = " + String.valueOf(setting.getHeight());
			if(setting.isClassic_mode())
			{
				writingConfig = writingConfig + String.format("%n") + "theme = " + "classic";
			}
			else
			{
				writingConfig = writingConfig + String.format("%n") + "theme = " + setting.getLookAndFeel();
			}
			writingConfig = writingConfig + String.format("%n") + "slot = " + String.valueOf(setting.getSlots());
			writingConfig = writingConfig + String.format("%n") + "difficulty = " + String.valueOf(setting.getAi_difficulty());
			writingConfig = writingConfig + String.format("%n") + "start_card = " + String.valueOf(setting.getStart_givenCards());
			writingConfig = writingConfig + String.format("%n") + "plus_card_ratio = " + String.valueOf(setting.getPlusMinus_card_count());
			writingConfig = writingConfig + String.format("%n") + "multiply_card_ratio = " + String.valueOf(setting.getMultiply_card_count());
			writingConfig = writingConfig + String.format("%n") + "change_card = " + String.valueOf(setting.getChange_card_count());
			writingConfig = writingConfig + String.format("%n") + "use_simple = " + String.valueOf(setting.isCenter_tab());
			writingConfig = writingConfig + String.format("%n") + "use_own_title = " + String.valueOf(setting.isUse_own_titleBar());
			writingConfig = writingConfig + String.format("%n") + "load_script_on_url = " + String.valueOf(setting.getScript_urlLoad());
			writingConfig = writingConfig + String.format("%n") + "load_script_on_js = " + String.valueOf(setting.getScript_directLoad());
			writingConfig = writingConfig + String.format("%n") + "error_message = " + String.valueOf(setting.isError_printDetail());
			
			ThemeSet thm_blue, thm_black;
			thm_blue = new ThemeSet(0);
			thm_black = new ThemeSet(1);
			boolean color_allFine = true;
			Color[] compare_c, compare_d; 
			
			compare_c = new Color[8];
			compare_c[0] = setting.getSelected_back();
			compare_c[1] = setting.getSelected_fore();
			compare_c[2] = setting.getSelected_inside_back();
			compare_c[3] = setting.getSelected_button();
			compare_c[4] = setting.getUnselected_back();
			compare_c[5] = setting.getUnselected_fore();
			compare_c[6] = setting.getUnselected_inside_back();
			compare_c[7] = setting.getUnselected_button();
			
			compare_d = new Color[8];
			compare_d[0] = thm_blue.getSelected_back();
			compare_d[1] = thm_blue.getSelected_fore();
			compare_d[2] = thm_blue.getSelected_inside();
			compare_d[3] = thm_blue.getSelected_button();
			compare_d[4] = thm_blue.getUnselected_back();
			compare_d[5] = thm_blue.getUnselected_fore();
			compare_d[6] = thm_blue.getUnselected_inside();
			compare_d[7] = thm_blue.getUnselected_button();
			for(int i=0; i<compare_c.length; i++)
			{				
				if(compare_c[i] == null)
				{
					if(compare_d[i] != null)
					{
						color_allFine = false; 
						//System.out.println("NULL");
						break;
					}
				}
				else
				{
					if(compare_c[i].getRed() == compare_d[i].getRed() &&
					compare_c[i].getGreen() == compare_d[i].getGreen() &&
					compare_c[i].getBlue() == compare_d[i].getBlue())
					{
						
					}
					else
					{
						color_allFine = false; 
						//System.out.println(i);
						//System.out.println(compare_c[i].getRed() + " - " + compare_d[i].getRed());
						//System.out.println(compare_c[i].getGreen() + " - " + compare_d[i].getGreen());
						//System.out.println(compare_c[i].getBlue() + " - " + compare_d[i].getBlue());
						break;
					}
				}
			}
			if(color_allFine)
			{
				writingConfig = writingConfig + String.format("%n") + "color = " + "blue";
			}
			else
			{
				compare_d = new Color[8];
				compare_d[0] = thm_black.getSelected_back();
				compare_d[1] = thm_black.getSelected_fore();
				compare_d[2] = thm_black.getSelected_inside();
				compare_d[3] = thm_black.getSelected_button();
				compare_d[4] = thm_black.getUnselected_back();
				compare_d[5] = thm_black.getUnselected_fore();
				compare_d[6] = thm_black.getUnselected_inside();
				compare_d[7] = thm_black.getUnselected_button();
				for(int i=0; i<compare_c.length; i++)
				{				
					if(compare_c[i] == null)
					{
						if(compare_d[i] != null)
						{
							color_allFine = false; break;
						}
					}
					else
					{
						if(compare_c[i].getRed() == compare_d[i].getRed() &&
						compare_c[i].getGreen() == compare_d[i].getGreen() &&
						compare_c[i].getBlue() == compare_d[i].getBlue())
						{
							
						}
						else
						{
							color_allFine = false; break;
						}
					}
				}
				if(color_allFine)
				{
					writingConfig = writingConfig + String.format("%n") + "color = " + "black";
				}
			}
			if(! color_allFine)
			{
				writingConfig = writingConfig + String.format("%n") + "color = " + String.valueOf(setting.getSelected_back().getRed()) + ", " 
			+ String.valueOf(setting.getSelected_back().getGreen()) + ", " + String.valueOf(setting.getSelected_back().getBlue());
			}
			
			langWriter = new FileWriter(cfgFile);
			bufferWriter = new BufferedWriter(langWriter);
			bufferWriter.write(writingConfig);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufferWriter.close();
			}
			catch (Exception e)
			{
				
			}
			try
			{
				langWriter.close();
			}
			catch (Exception e)
			{
				
			}
		}
		String mes;
		
		mes = setting.getLang().getText(Language.FILE_CREATED) + "\n" + setting.getDefault_path() + "config.cfg" + "\n"
				+ setting.getDefault_path() + "lang.cfg" + "\n\n";
		if(gui_mode)
		{
			JOptionPane.showMessageDialog(null, mes);
		}
		else
		{
			System.out.println(mes);
		}
		exit();
	}
	public static ConfigSetting readConfig(Setting setting)
	{
		File additionalSetitngFile = null;
		FileInputStream fin = null;
		InputStreamReader inr = null;
		BufferedReader buf = null;
		String buf_takes = null;
		String buf_one = null;
		StringTokenizer buf_token = null;	
		int base_r, base_g, base_b;
		boolean lang_file = false;
		ConfigSetting results = new ConfigSetting();
		try
		{
			additionalSetitngFile = new File(setting.getDefault_path() + "config.cfg");
			if(additionalSetitngFile.exists())
			{
				fin = new FileInputStream(additionalSetitngFile);
				inr = new InputStreamReader(fin);
				buf = new BufferedReader(inr);
				while(true)
				{
					buf_takes = buf.readLine();
					if(buf_takes == null) break;
					if(buf_takes.startsWith("#")) continue;
					try
					{
						buf_token = new StringTokenizer(buf_takes, "=");
						if(buf_token.countTokens() == 2)
						{
							buf_one = buf_token.nextToken().trim();
							if(buf_one.equalsIgnoreCase("width"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setWidth(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("height"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setHeight(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("theme"))
							{
								buf_one = buf_token.nextToken().trim();
								if(buf_one.equalsIgnoreCase("classic"))
								{
									setting.setClassic_mode(true);
								}
								else
								{
									setting.setLookAndFeel(buf_one);
									try
									{
										UIManager.setLookAndFeel(buf_one);
									}
									catch(Exception e)
									{
										UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
									}
									setting.setClassic_mode(false);
								}
							}
							else if(buf_one.equalsIgnoreCase("slot"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setSlots(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("difficulty"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setAi_difficulty(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("start_card"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setStart_givenCards(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("plus_card_ratio"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setPlusMinus_card_count(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("multiply_card_ratio"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setMultiply_card_count(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("change_card"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setChange_card_count(Integer.parseInt(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("load_script_on_url"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setScript_urlLoad(new Boolean(Boolean.parseBoolean(buf_one)));
							}
							else if(buf_one.equalsIgnoreCase("load_script_on_js"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setScript_directLoad(new Boolean(Boolean.parseBoolean(buf_one)));
							}
							else if(buf_one.equalsIgnoreCase("color"))
							{
								buf_one = buf_token.nextToken().trim();
								boolean defined_color = false;
								
								ThemeSet themeSets = null;
								if(buf_one.equalsIgnoreCase("blue"))
								{
									themeSets = new ThemeSet(0);
									defined_color = true;
								}
								else if(buf_one.equalsIgnoreCase("black"))
								{
									themeSets = new ThemeSet(1);
									defined_color = true;
								}
								if(defined_color)
								{
									setting.setSelected_back(themeSets.getSelected_back());
									setting.setSelected_fore(themeSets.getSelected_fore());
									setting.setSelected_inside_back(themeSets.getSelected_inside());
									setting.setSelected_button(themeSets.getSelected_button());
									setting.setUnselected_back(themeSets.getUnselected_back());
									setting.setUnselected_fore(themeSets.getUnselected_fore());
									setting.setUnselected_inside_back(themeSets.getUnselected_inside());
									setting.setUnselected_button(themeSets.getUnselected_button());
								}
								else
								{
									buf_token = new StringTokenizer(buf_one, ",");
									base_r = Integer.parseInt(buf_token.nextToken().trim());
									base_g = Integer.parseInt(buf_token.nextToken().trim());
									base_b = Integer.parseInt(buf_token.nextToken().trim());
									setting.setSelected_back(new Color(base_r, base_g, base_b));
									setting.setSelected_inside_back(new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.85)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.85)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.85))));
									setting.setSelected_fore(new Color(base_r - ((int) ((((double) base_r)) * 0.3)), base_g - ((int) ((((double) base_g)) * 0.3)), base_b - ((int) ((((double) base_b)) * 0.3))));
									setting.setUnselected_back(new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.3)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.3)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.3))));
									setting.setUnselected_inside_back(new Color(base_r + ((int) ((255.0 - ((double) base_r)) * 0.9)), base_g + ((int) ((255.0 - ((double) base_g)) * 0.9)), base_b + ((int) ((255.0 - ((double) base_b)) * 0.9))));
									setting.setUnselected_fore(new Color(base_r - ((int) ((((double) base_r)) * 0.4)), base_g - ((int) ((((double) base_g)) * 0.4)), base_b - ((int) ((((double) base_b)) * 0.4))));
									setting.setSelected_button(new Color(base_r - ((int) ((((double) base_r)) * 0.1)), base_g - ((int) ((((double) base_g)) * 0.1)), base_b - ((int) ((((double) base_b)) * 0.1))));
									setting.setUnselected_button(new Color(base_r - ((int) ((((double) base_r)) * 0.05)), base_g - ((int) ((((double) base_g)) * 0.05)), base_b - ((int) ((((double) base_b)) * 0.05))));
									if(buf_token.countTokens() >= 4)
									{
										setting.setColor_reverse(new Boolean(Boolean.parseBoolean(buf_token.nextToken())));
									}
									else
									{
										setting.setColor_reverse(new Boolean(true));
									}
								}
							}
							else if(buf_one.equalsIgnoreCase("use_simple"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setCenter_tab(Boolean.parseBoolean(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("use_own_title"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setUse_own_titleBar(Boolean.parseBoolean(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("error_message"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setError_printDetail(Boolean.parseBoolean(buf_one));
							}
							else if(buf_one.equalsIgnoreCase("charset"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.setCharset(buf_one);
							}
							else if(buf_one.equalsIgnoreCase("cheat"))
							{
								buf_one = buf_token.nextToken().trim();
								setting.getOtherObjects().add("cheat|" + buf_one);
							}
							else if(buf_one.equalsIgnoreCase("language") || buf_one.equalsIgnoreCase("lang"))
							{
								buf_one = buf_token.nextToken().trim();
								if(buf_one.equalsIgnoreCase("korean") || buf_one.equalsIgnoreCase("kor")
										|| buf_one.equalsIgnoreCase("ko") || buf_one.equalsIgnoreCase("kr"))
								{
									setting.setLang(new Korean());
								}
								else if(buf_one.equalsIgnoreCase("english") || buf_one.equalsIgnoreCase("eng")
										|| buf_one.equalsIgnoreCase("en"))
								{
									setting.setLang(new English());
								}
								else if(buf_one.equalsIgnoreCase("file"))
								{
									lang_file = true;
								}
								else
								{
									setting.setLang(new English());
								}
							}
						}
					} 
					catch (Exception e)
					{
						if(setting.isError_printDetail())
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				buf.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				inr.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				fin.close();
			} 
			catch (Exception e)
			{
				
			}
		}
		results.lang_file = lang_file;
		results.sets = setting;
		return results;
	}
	@Override
	public void exit()
	{
		if(independence) System.exit(0);
		
		
	}
}
