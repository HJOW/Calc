package setting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import lang.English;
import lang.Korean;
import lang.Language;
import main_classes.CalcWindow;
import main_classes.User;
import ranking.Rank;
import scripting.ScriptModule;
import ai_algorithm.AI_Algorithm;

public class Setting implements CanBeClone, Objectable
{
	private static final long serialVersionUID = 8204104111362161792L;	
	
	private Dimension screenSize;
	private Language lang;	
	private String os;
	private String locale;
	private String default_path;
	private String separator;
	private String notice_url, notice_url2;
	private String charset;
	private String jarfile_path = "";
	private String script_engine = "JavaScript";
	private String version_aut = null;
	
	private String downloaded_path = null;
	private VersionData downloaded_ver = new VersionData();
	
	private Font usingFont = null;
	
	private Hashtable<String, String> properties = new Hashtable<String, String>();
	
	private Color selected_back = null;
	private Color selected_inside_back = null;
	private Color selected_fore = null;
	private Color selected_button = null;
	private Color unselected_back = null;
	private Color unselected_inside_back = null;
	private Color unselected_fore = null;
	private Color unselected_button = null;
	private Boolean color_reverse;
	private Boolean themeSelection_new = null;
	
	private AI_Algorithm[] ai_algorithms;
	private String card_separator = "  ";
	private String lookAndFeel = "";
	private Vector<ScriptModule> modules;
	
	private Vector<String> otherObjects;
	private Vector<Scenario> scenarios;
	
	private Vector<String> reflexioner_ranks;
	
	private SaveBoolean ob_use_other_algorithm = null;
	private SaveBoolean ob_active_authority_mode = null;
	private SaveBoolean ob_use_own_titleBar = null;
	private SaveBoolean ob_useAlertWindow = null;
	private SaveBoolean ob_networkEnabled = null;
	private SaveBoolean ob_onlyAI_prevent = null;
	private SaveBoolean ob_error_printDetail = null;
	private SaveBoolean ob_center_tab = null;
	private SaveBoolean ob_notice_not_view = null;
	private SaveBoolean ob_notice_not_view_option_viewed = null;
	private SaveBoolean ob_user_selected = null;
	private SaveBoolean ob_use_color = null;
	private SaveBoolean ob_scrollBar = null;
	private SaveBoolean ob_professional_contents_loaded = null;
	private SaveBoolean ob_classic_mode = null;
	private SaveBoolean ob_use_track = null;
	private SaveBoolean ob_use_track_realtime = null;
	private SaveBoolean ob_auto_scrollBar = null;
	private SaveBoolean ob_use_trump_card = null;
	private SaveBoolean ob_scenario_open = null;
	
	private SaveInt ob_now_user_index = null;
	private SaveInt ob_theme = null;
	private SaveInt ob_slots = null;
	private SaveInt ob_start_givenCards = null;
	private SaveInt ob_ai_difficulty = null;
	private SaveInt ob_ver_main = null;
	private SaveInt ob_ver_sub1 = null;
	private SaveInt ob_ver_sub2 = null;
	private SaveInt ob_width = null;
	private SaveInt ob_height = null;
	private SaveInt ob_use_algorithm_index = null;
	private SaveInt ob_change_card_count = null;
	private SaveInt ob_use_track_save_delay = null;
	private SaveInt ob_plusMinus_card_count = null;
	private SaveInt ob_multiply_card_count = null;
	
	private SaveChar ob_op_plus = null;
	private SaveChar ob_op_minus = null;
	private SaveChar ob_op_multiply = null;
	private SaveChar ob_op_divide = null;
	private SaveChar ob_op_power = null;
	private SaveChar ob_op_change = null;
	private SaveChar ob_op_spade = null;
	private SaveChar ob_op_clover = null;
	private SaveChar ob_op_diamond = null;
	private SaveChar ob_op_heart = null;
	private SaveChar ob_ver_test = null;
	
	private Key key;
	
	private SaveBoolean next_execute_saved = null;
	private SaveInt next_execute = null;
	
	private User[] users;
	private Rank[] ranks;
				
	private char op_plus = '+';
	private char op_minus = '-';
	private char op_multiply = '*';
	private char op_divide = '/';	
	private char op_power = '^';
	private char op_change = '§';
	private char op_spade = '♠';
	private char op_clover = '♣';
	private char op_diamond = '◆';
	private char op_heart = '♥';
	private char ver_test = ' ';
	
	
	private int now_user_index = 0;
	private int theme = 0;
	private int slots = 4;
	private int start_givenCards = 10;
	private int ai_difficulty = 1;
	private int ver_main = 0, ver_sub1 = 0, ver_sub2 = 0;
	private int width = 600;
	private int height = 400;	
	private int use_algorithm_index = 0;
	private int change_card_count = 1;	
	private int use_track_save_delay = 1000;
	private int plusMinus_card_count = 4;
	private int multiply_card_count = 4;
	
	private boolean use_other_algorithm = false;
	private boolean active_authority_mode = true;
	private boolean use_own_titleBar = true;
	private boolean useAlertWindow = true;
	private boolean networkEnabled = false;
	private boolean onlyAI_prevent = false;	
	private boolean center_tab = true;
	private boolean notice_not_view = false;
	private boolean notice_not_view_option_viewed = false;
	private boolean user_selected = false;
	private boolean use_color = true;
	private boolean scrollBar = true;
	private boolean auto_scrollBar = true;
	private boolean professional_contents_loaded = false;
	private boolean classic_mode = false;
	private boolean use_track = false;
	private boolean use_track_realtime = false;
	private boolean error_printDetail = false;
	private boolean use_trump_card = false;
	private boolean scenario_open = true;
	
	private Boolean kind_ai = new Boolean(false);
	private Boolean tutorial_needed = new Boolean(false);
	private Boolean use_sound = new Boolean(false);
	private Boolean script_directLoad = new Boolean(false);
	private Boolean script_urlLoad = new Boolean(false);
	private Boolean use_image = new Boolean(true);
	
	private Integer key_1 = new Integer(KeyEvent.VK_1);
	private Integer key_2 = new Integer(KeyEvent.VK_2);
	private Integer key_3 = new Integer(KeyEvent.VK_3);
	private Integer key_shift = new Integer(KeyEvent.VK_SHIFT);
	private Integer key_space = new Integer(KeyEvent.VK_SPACE);
	private Integer key_l = new Integer(KeyEvent.VK_L);
	private Integer key_k = new Integer(KeyEvent.VK_K);
	private Integer key_up = new Integer(KeyEvent.VK_UP);
	private Integer key_down = new Integer(KeyEvent.VK_DOWN);
	private Integer key_left = new Integer(KeyEvent.VK_LEFT);
	private Integer key_right = new Integer(KeyEvent.VK_RIGHT);
	
		
	public Setting()
	{
		key = new Key();
		next_execute_saved = new SaveBoolean(true);
		next_execute = new SaveInt(0);
	}	
	@Override
	public Setting clone()
	{
		Setting newOne = Setting.getNewInstance();
		try
		{
			if(this.users != null)
			{			
				newOne.users = new User[this.users.length];
				for(int i=0; i<newOne.users.length; i++)
				{
					newOne.users[i] = this.users[i];
				}
			}
			if(this.ai_algorithms != null)
			{
				newOne.ai_algorithms = new AI_Algorithm[this.ai_algorithms.length];
				for(int i=0; i<newOne.ai_algorithms.length; i++)
				{
					newOne.ai_algorithms[i] = this.ai_algorithms[i].clone();
				}
			}
			
			newOne.theme = theme;
			newOne.lang = lang.clone();
			if(os != null)
				newOne.os = new String(os);
			if(locale != null)
				newOne.locale = new String(locale);
			if(default_path != null)
				newOne.default_path = new String(default_path);
			if(separator != null)
				newOne.separator = new String(separator);
			if(screenSize != null)
				newOne.screenSize = new Dimension(this.screenSize);
			newOne.use_own_titleBar = this.use_own_titleBar;
			if(selected_back != null)
				newOne.selected_back = new Color(selected_back.getRed(), selected_back.getGreen(), selected_back.getBlue());
			if(selected_inside_back != null)
				newOne.selected_inside_back = new Color(selected_inside_back.getRed(), selected_inside_back.getGreen(), selected_inside_back.getBlue());
			if(selected_fore != null)
				newOne.selected_fore = new Color(selected_fore.getRed(), selected_fore.getGreen(), selected_fore.getBlue());
			if(unselected_back != null)
				newOne.unselected_back = new Color(unselected_back.getRed(), unselected_back.getGreen(), unselected_back.getBlue());
			if(unselected_inside_back != null)
				newOne.unselected_inside_back = new Color(unselected_inside_back.getRed(), unselected_inside_back.getGreen(), unselected_inside_back.getBlue());
			if(unselected_fore != null)
				newOne.unselected_fore = new Color(unselected_fore.getRed(), unselected_fore.getGreen(), unselected_fore.getBlue());
			if(selected_button != null)
				newOne.selected_button = new Color(selected_button.getRed(), selected_button.getGreen(), selected_button.getBlue());
			if(unselected_button != null)
				newOne.unselected_button = new Color(unselected_button.getRed(), unselected_button.getGreen(), unselected_button.getBlue());
			newOne.width = width;
			newOne.height = height;
			newOne.use_color = this.use_color;
			newOne.active_authority_mode = this.active_authority_mode;
			newOne.networkEnabled = this.networkEnabled;
			newOne.slots = this.slots;
			newOne.use_other_algorithm = this.use_other_algorithm;
			newOne.useAlertWindow = this.useAlertWindow;
			newOne.start_givenCards = this.start_givenCards;
			newOne.use_algorithm_index = this.use_algorithm_index;
			newOne.lookAndFeel = new String(this.lookAndFeel);
			newOne.ai_difficulty = this.ai_difficulty;
			newOne.notice_url = new String(this.notice_url);
			newOne.notice_url2 = new String(this.notice_url2);
			newOne.script_engine = new String(this.script_engine);
			newOne.card_separator = new String(this.card_separator);
			newOne.center_tab = this.center_tab;
			newOne.notice_not_view = this.notice_not_view;
			newOne.error_printDetail = this.error_printDetail;
			newOne.notice_not_view_option_viewed = this.notice_not_view_option_viewed;
			newOne.now_user_index = this.now_user_index;
			newOne.ver_main = this.ver_main;
			newOne.ver_sub1 = this.ver_sub1;
			newOne.ver_sub2 = this.ver_sub2;
			newOne.op_plus = this.op_plus;
			newOne.op_minus = this.op_minus;
			newOne.op_multiply = this.op_multiply;
			newOne.op_divide = this.op_divide;	
			newOne.op_change = this.op_change;
			newOne.op_power = this.op_power;
			newOne.change_card_count = this.change_card_count;
			newOne.professional_contents_loaded = this.professional_contents_loaded;			
			newOne.scrollBar = this.scrollBar;	
			newOne.charset = new String(this.charset);
			newOne.classic_mode = this.classic_mode;
			newOne.use_track = this.use_track;
			newOne.use_track_realtime = this.use_track_realtime;
			newOne.ver_test = this.ver_test;
			newOne.auto_scrollBar = this.auto_scrollBar;
			newOne.use_track_save_delay = this.use_track_save_delay;
			newOne.use_trump_card = this.use_trump_card;
			newOne.plusMinus_card_count = this.plusMinus_card_count;
			newOne.multiply_card_count = this.multiply_card_count;
			newOne.kind_ai = new Boolean(this.kind_ai);
			newOne.tutorial_needed = new Boolean(this.tutorial_needed);
			newOne.scenario_open = this.scenario_open;
			newOne.color_reverse = new Boolean(this.color_reverse.booleanValue());
			newOne.use_sound = new Boolean(use_sound.booleanValue());			
			
			if(properties != null)
			{
				newOne.properties = new Hashtable<String, String>();
				Set<String> keys = properties.keySet();
				for(String s : keys)
				{
					try
					{
						newOne.properties.put(new String(s), new String(properties.get(s)));
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
			if(themeSelection_new != null)
				newOne.themeSelection_new = new Boolean(themeSelection_new.booleanValue());
			if(usingFont != null)
				newOne.usingFont = usingFont.deriveFont(usingFont.getStyle(), usingFont.getSize2D());
			else if(CalcWindow.usingFont != null)
				newOne.usingFont = CalcWindow.usingFont.deriveFont(CalcWindow.usingFont.getStyle(), CalcWindow.usingFont.getSize2D());
			
			if(scenarios == null) scenarios = new Vector<Scenario>();
			for(int i=0; i<scenarios.size(); i++)
			{
				if(scenarios.get(i) != null)
					newOne.scenarios.add(scenarios.get(i).clone());
			}
			if(otherObjects == null) otherObjects = new Vector<String>();
			for(int i=0; i<otherObjects.size(); i++)
			{
				if(otherObjects.get(i) != null)
					newOne.otherObjects.add(new String(otherObjects.get(i)));
			}
			if(modules == null) modules = new Vector<ScriptModule>();
			for(int i=0; i<modules.size(); i++)
			{
				if(modules.get(i) != null)
					newOne.modules.add(modules.get(i).clone());
			}
			if(this.jarfile_path != null) newOne.jarfile_path = new String(this.jarfile_path);
			if(this.next_execute_saved != null) newOne.next_execute_saved = (SaveBoolean) this.next_execute_saved.clone();
			if(this.next_execute != null) newOne.next_execute = (SaveInt) this.next_execute.clone();
			if(key != null) newOne.key = this.key.clone();
			
			if(use_image != null) newOne.use_image = new Boolean(this.use_image.booleanValue());
			else newOne.use_image = new Boolean(true);
			
			if(reflexioner_ranks != null)
			{
				newOne.reflexioner_ranks = new Vector<String>();
				for(int i=0; i<reflexioner_ranks.size(); i++)
				{
					newOne.reflexioner_ranks.add(reflexioner_ranks.get(i));
				}
			}
			else newOne.reflexioner_ranks = new Vector<String>();
			
			//newOne.wrapperBooleanToObjects();
			if(ob_active_authority_mode != null)
				newOne.ob_active_authority_mode = new SaveBoolean(ob_active_authority_mode.booleanValue());		
			if(ob_center_tab != null)
				newOne.ob_center_tab = new SaveBoolean(ob_center_tab.booleanValue());
			if(ob_error_printDetail != null)
				newOne.ob_error_printDetail = new SaveBoolean(ob_error_printDetail.booleanValue());
			if(ob_networkEnabled != null)
				newOne.ob_networkEnabled = new SaveBoolean(ob_networkEnabled.booleanValue());
			if(ob_notice_not_view != null)
				newOne.ob_notice_not_view = new SaveBoolean(ob_notice_not_view.booleanValue());
			if(ob_notice_not_view_option_viewed != null)
				newOne.ob_notice_not_view_option_viewed = new SaveBoolean(ob_notice_not_view_option_viewed.booleanValue());
			if(ob_onlyAI_prevent != null)
				newOne.ob_onlyAI_prevent = new SaveBoolean(ob_onlyAI_prevent.booleanValue());
			if(ob_use_color != null)
				newOne.ob_use_color = new SaveBoolean(ob_use_color.booleanValue());
			if(ob_use_other_algorithm != null)
				newOne.ob_use_other_algorithm = new SaveBoolean(ob_use_other_algorithm.booleanValue());
			if(ob_use_own_titleBar != null)
				newOne.ob_use_own_titleBar = new SaveBoolean(ob_use_own_titleBar.booleanValue());
			if(ob_useAlertWindow != null)
				newOne.ob_useAlertWindow = new SaveBoolean(ob_useAlertWindow.booleanValue());
			if(ob_user_selected != null)
				newOne.ob_user_selected = new SaveBoolean(ob_user_selected.booleanValue());
			if(ob_scrollBar != null)
				newOne.ob_scrollBar = new SaveBoolean(ob_scrollBar.booleanValue());
			if(ob_professional_contents_loaded != null)
				newOne.ob_professional_contents_loaded = new SaveBoolean(ob_professional_contents_loaded.booleanValue());
			if(ob_classic_mode != null)
				newOne.ob_classic_mode = new SaveBoolean(ob_classic_mode.booleanValue());
			if(ob_use_track != null)
				newOne.ob_use_track = new SaveBoolean(ob_use_track.booleanValue());
			if(ob_use_track_realtime != null)
				newOne.ob_use_track_realtime = new SaveBoolean(ob_use_track_realtime.booleanValue());
			if(ob_auto_scrollBar != null)
				newOne.ob_auto_scrollBar = new SaveBoolean(ob_auto_scrollBar.booleanValue());
			if(ob_use_trump_card != null)
				newOne.ob_use_trump_card = new SaveBoolean(ob_use_trump_card.booleanValue());
			if(ob_scenario_open != null)
				newOne.ob_scenario_open = new SaveBoolean(ob_scenario_open.booleanValue());
			
			if(ob_ai_difficulty != null)				
				newOne.ob_ai_difficulty = new SaveInt(ob_ai_difficulty.intValue());
			if(ob_height != null)
				newOne.ob_height = new SaveInt(ob_height.intValue());
			if(ob_now_user_index != null)
				newOne.ob_now_user_index = new SaveInt(ob_now_user_index.intValue());
			if(ob_slots != null)
				newOne.ob_slots = new SaveInt(ob_slots.intValue());
			if(ob_start_givenCards != null)
				newOne.ob_start_givenCards = new SaveInt(ob_start_givenCards.intValue());
			if(ob_theme != null)
				newOne.ob_theme = new SaveInt(ob_theme.intValue());
			if(ob_use_algorithm_index != null)
				newOne.ob_use_algorithm_index = new SaveInt(ob_use_algorithm_index.intValue());
			if(ob_ver_main != null)
				newOne.ob_ver_main = new SaveInt(ob_ver_main.intValue());
			if(ob_ver_sub1 != null)
				newOne.ob_ver_sub1 = new SaveInt(ob_ver_sub1.intValue());
			if(ob_ver_sub2 != null)
				newOne.ob_ver_sub2 = new SaveInt(ob_ver_sub2.intValue());
			if(ob_width != null)
				newOne.ob_width = new SaveInt(ob_width.intValue());
			if(ob_change_card_count != null)
				newOne.ob_change_card_count = new SaveInt(ob_change_card_count.intValue());
			if(ob_use_track_save_delay != null)
				newOne.ob_use_track_save_delay = new SaveInt(ob_use_track_save_delay.intValue());
			if(ob_plusMinus_card_count != null)
				newOne.ob_plusMinus_card_count = new SaveInt(ob_plusMinus_card_count.intValue());
			if(ob_multiply_card_count != null)
				newOne.ob_multiply_card_count = new SaveInt(ob_multiply_card_count.intValue());
				
			if(ob_op_divide != null)
				newOne.ob_op_divide = new SaveChar(ob_op_divide.charValue());
			if(ob_op_minus != null)
				newOne.ob_op_minus = new SaveChar(ob_op_minus.charValue());
			if(ob_op_multiply != null)
				newOne.ob_op_multiply = new SaveChar(ob_op_multiply.charValue());
			if(ob_op_plus != null)
				newOne.ob_op_plus = new SaveChar(ob_op_plus.charValue());
			if(ob_op_power != null)
				newOne.ob_op_power = new SaveChar(ob_op_power.charValue());
			if(ob_op_change != null)
				newOne.ob_op_change = new SaveChar(ob_op_change.charValue());
			if(ob_op_clover != null)
				newOne.ob_op_clover = new SaveChar(ob_op_clover.charValue());
			if(ob_op_spade != null)
				newOne.ob_op_spade = new SaveChar(ob_op_spade.charValue());
			if(ob_op_heart != null)
				newOne.ob_op_heart = new SaveChar(ob_op_heart.charValue());
			if(ob_op_diamond != null)
				newOne.ob_op_diamond = new SaveChar(ob_op_diamond.charValue());
			if(ob_ver_test != null)
				newOne.ob_ver_test = new SaveChar(ob_ver_test.charValue());
			
			if(script_directLoad != null) newOne.script_directLoad = new Boolean(script_directLoad.booleanValue());
			if(script_urlLoad != null) newOne.script_urlLoad = new Boolean(script_urlLoad.booleanValue());
			
			if(key_1 != null) newOne.key_1 = new Integer(key_1.intValue());
			else newOne.key_1 = new Integer(KeyEvent.VK_1);
			if(key_2 != null) newOne.key_2 = new Integer(key_2.intValue());
			else newOne.key_2 = new Integer(KeyEvent.VK_2);
			if(key_3 != null) newOne.key_3 = new Integer(key_3.intValue());
			else newOne.key_3 = new Integer(KeyEvent.VK_3);
			if(key_up != null) newOne.key_up = new Integer(key_up.intValue());
			else newOne.key_up = new Integer(KeyEvent.VK_UP);
			if(key_down != null) newOne.key_down = new Integer(key_down.intValue());
			else newOne.key_down = new Integer(KeyEvent.VK_DOWN);
			if(key_left != null) newOne.key_left = new Integer(key_left.intValue());
			else newOne.key_left = new Integer(KeyEvent.VK_LEFT);
			if(key_right != null) newOne.key_right = new Integer(key_right.intValue());
			else newOne.key_right = new Integer(KeyEvent.VK_RIGHT);
			if(key_k != null) newOne.key_k = new Integer(key_k.intValue());
			else newOne.key_k = new Integer(KeyEvent.VK_K);
			if(key_l != null) newOne.key_l = new Integer(key_l.intValue());
			else newOne.key_l = new Integer(KeyEvent.VK_L);
			if(key_space != null) newOne.key_space = new Integer(key_space.intValue());
			else newOne.key_space = new Integer(KeyEvent.VK_SPACE);
			if(key_shift != null) newOne.key_shift = new Integer(key_shift.intValue());
			else newOne.key_shift = new Integer(KeyEvent.VK_SHIFT);
			
			if(version_aut != null) newOne.version_aut = new String(version_aut);
			
			if(downloaded_path != null)
				newOne.downloaded_path = new String(downloaded_path);
			if(downloaded_ver != null)
				newOne.downloaded_ver = downloaded_ver.clone();
			else
				downloaded_ver = new VersionData();
			
		} 
		catch (NullPointerException e)
		{
			newOne = Setting.getNewInstance();
			//e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			newOne = Setting.getNewInstance();
		}
		//System.out.println("1. " + scrollBar + " = " + newOne.scrollBar);
		//newOne.scrollBar = this.scrollBar;
		//System.out.println("2. " + scrollBar + " = " + newOne.scrollBar);
		return newOne;
	}	
	public void addRank(Rank newOne)
	{
		Rank[] newArray;
		if(ranks == null || ranks.length == 0)
		{
			newArray = new Rank[1];
			newArray[0] = newOne;
		}
		else
		{
			newArray = new Rank[ranks.length + 1];
			for(int i=0; i<ranks.length; i++)
			{
				newArray[i] = ranks[i];
			}
			newArray[ranks.length] = newOne;
			newArray = sortRank(newArray);
		}
		ranks = newArray;
	}
	public Rank[] sortRank(Rank[] newArray)
	{		
		Rank temp;			
		for(int i=1; i<newArray.length; i++)
		{
			for(int j=0; j<i; j++)
			{
				//if(newArray[j].point < newArray[i].point)
				if(newArray[j].point.compareTo(newArray[i].point) < 0)
				{
					temp = newArray[j];
					newArray[j] = newArray[i];
					newArray[i] = temp;						
				}
			}
		}
		return newArray;
	}
	public void removeSmallRank(int count)
	{
		ranks = sortRank(ranks);
		Rank[] newArray = new Rank[ranks.length - count];
		for(int i=0; i<newArray.length; i++)
		{
			newArray[i] = ranks[i];
		}
		ranks = newArray;
	}
	public void objectToWrapper()
	{
		if(ob_active_authority_mode != null) active_authority_mode = ob_active_authority_mode.booleanValue();
		if(ob_center_tab != null) center_tab = ob_center_tab.booleanValue();
		if(ob_error_printDetail != null) error_printDetail = ob_error_printDetail.booleanValue();
		if(ob_networkEnabled != null) networkEnabled = ob_networkEnabled.booleanValue();
		if(ob_notice_not_view != null) notice_not_view = ob_notice_not_view.booleanValue();
		if(ob_notice_not_view_option_viewed != null) notice_not_view_option_viewed = ob_notice_not_view_option_viewed.booleanValue();
		if(ob_onlyAI_prevent != null) onlyAI_prevent = ob_onlyAI_prevent.booleanValue();
		if(ob_use_color != null) use_color = ob_use_color.booleanValue();
		if(ob_use_other_algorithm != null) use_other_algorithm = ob_use_other_algorithm.booleanValue();
		if(ob_use_own_titleBar != null) use_own_titleBar = ob_use_own_titleBar.booleanValue();
		if(ob_useAlertWindow != null) useAlertWindow = ob_useAlertWindow.booleanValue();
		if(ob_user_selected != null) user_selected = ob_user_selected.booleanValue();
		if(ob_scrollBar != null) scrollBar = ob_scrollBar.booleanValue();
		if(ob_professional_contents_loaded != null) professional_contents_loaded = ob_professional_contents_loaded.booleanValue();
		if(ob_classic_mode != null) classic_mode = ob_classic_mode.booleanValue();
		if(ob_use_track != null) use_track = ob_use_track.booleanValue();
		if(ob_use_track_realtime != null) use_track_realtime = ob_use_track_realtime.booleanValue();		
		if(ob_auto_scrollBar != null) auto_scrollBar = ob_auto_scrollBar.booleanValue();
		if(ob_use_trump_card != null) use_trump_card = ob_use_trump_card.booleanValue();
		if(ob_scenario_open != null) scenario_open = ob_scenario_open.booleanValue();
		
		if(ob_ai_difficulty != null) ai_difficulty = ob_ai_difficulty.intValue();
		if(ob_height != null) height = ob_height.intValue();
		if(ob_now_user_index != null) now_user_index = ob_now_user_index.intValue();
		if(ob_slots != null) slots = ob_slots.intValue();
		if(ob_start_givenCards != null) start_givenCards = ob_start_givenCards.intValue();
		if(ob_theme != null) theme = ob_theme.intValue();
		if(ob_use_algorithm_index != null) use_algorithm_index = ob_use_algorithm_index.intValue();
		if(ob_ver_main != null) ver_main = ob_ver_main.intValue();
		if(ob_ver_sub1 != null) ver_sub1 = ob_ver_sub1.intValue();
		if(ob_ver_sub2 != null) ver_sub2 = ob_ver_sub2.intValue();
		if(ob_width != null) width = ob_width.intValue();
		if(ob_change_card_count != null) change_card_count = ob_change_card_count.intValue();
		if(ob_use_track_save_delay != null) use_track_save_delay = ob_use_track_save_delay.intValue();
		if(ob_plusMinus_card_count != null) plusMinus_card_count = ob_plusMinus_card_count.intValue();
		if(ob_multiply_card_count != null) multiply_card_count = ob_multiply_card_count.intValue();
		
		if(ob_op_divide != null) op_divide = ob_op_divide.charValue();
		if(ob_op_minus != null) op_minus = ob_op_minus.charValue();
		if(ob_op_multiply != null) op_multiply = ob_op_multiply.charValue();
		if(ob_op_plus != null) op_plus = ob_op_plus.charValue();
		if(ob_op_power != null) op_power = ob_op_power.charValue();
		if(ob_op_change != null) op_change = ob_op_change.charValue();
		if(ob_op_clover != null) op_clover = ob_op_clover.charValue();
		if(ob_op_spade != null) op_spade = ob_op_spade.charValue();
		if(ob_op_heart != null) op_heart = ob_op_heart.charValue();
		if(ob_op_diamond != null) op_diamond = ob_op_diamond.charValue();
		if(ob_ver_test != null) ver_test = ob_ver_test.charValue();
	}
	public void wrapperToObjects()
	{		
		ob_active_authority_mode = new SaveBoolean(active_authority_mode);
		ob_center_tab = new SaveBoolean(center_tab);
		ob_error_printDetail = new SaveBoolean(error_printDetail);
		ob_networkEnabled = new SaveBoolean(networkEnabled);
		ob_notice_not_view = new SaveBoolean(notice_not_view);
		ob_notice_not_view_option_viewed = new SaveBoolean(notice_not_view_option_viewed);
		ob_onlyAI_prevent = new SaveBoolean(onlyAI_prevent);
		ob_use_color = new SaveBoolean(use_color);
		ob_use_other_algorithm = new SaveBoolean(use_other_algorithm);
		ob_use_own_titleBar = new SaveBoolean(use_own_titleBar);
		ob_useAlertWindow = new SaveBoolean(useAlertWindow);
		ob_user_selected = new SaveBoolean(user_selected);
		ob_scrollBar = new SaveBoolean(scrollBar);
		ob_professional_contents_loaded = new SaveBoolean(professional_contents_loaded);
		ob_classic_mode = new SaveBoolean(classic_mode);
		ob_use_track = new SaveBoolean(use_track);
		ob_use_track_realtime = new SaveBoolean(use_track_realtime);
		ob_auto_scrollBar = new SaveBoolean(auto_scrollBar);
		ob_use_trump_card = new SaveBoolean(use_trump_card);
		ob_scenario_open = new SaveBoolean(scenario_open);
		
		ob_ai_difficulty = new SaveInt(ai_difficulty);
		ob_height = new SaveInt(height);
		ob_now_user_index = new SaveInt(now_user_index);
		ob_slots = new SaveInt(slots);
		ob_start_givenCards = new SaveInt(start_givenCards);
		ob_theme = new SaveInt(theme);
		ob_use_algorithm_index = new SaveInt(use_algorithm_index);
		ob_ver_main = new SaveInt(ver_main);
		ob_ver_sub1 = new SaveInt(ver_sub1);
		ob_ver_sub2 = new SaveInt(ver_sub2);
		ob_width = new SaveInt(width);
		ob_change_card_count = new SaveInt(change_card_count);
		ob_use_track_save_delay = new SaveInt(use_track_save_delay);
		ob_plusMinus_card_count = new SaveInt(plusMinus_card_count);
		ob_multiply_card_count = new SaveInt(multiply_card_count);
		
		ob_op_divide = new SaveChar(op_divide);
		ob_op_minus = new SaveChar(op_minus);
		ob_op_multiply = new SaveChar(op_multiply);
		ob_op_plus = new SaveChar(op_plus);
		ob_op_power = new SaveChar(op_power);
		ob_op_change = new SaveChar(op_change);
		ob_op_clover = new SaveChar(op_clover);
		ob_op_spade = new SaveChar(op_spade);
		ob_op_heart = new SaveChar(op_heart);
		ob_op_diamond = new SaveChar(op_diamond);
		ob_ver_test = new SaveChar(ver_test);
	}
	public void wrapperObjectClean()
	{
		ob_active_authority_mode = null;
		ob_center_tab = null;
		ob_error_printDetail = null;
		ob_networkEnabled = null;
		ob_notice_not_view = null;
		ob_notice_not_view_option_viewed = null;
		ob_onlyAI_prevent = null;
		ob_use_color = null;
		ob_use_other_algorithm = null;
		ob_use_own_titleBar = new SaveBoolean(use_own_titleBar);
		ob_useAlertWindow = null;
		ob_user_selected = null;
		ob_scrollBar = null;
		ob_professional_contents_loaded = null;
		ob_classic_mode = null;
		ob_use_track = null;
		ob_use_track_realtime = null;
		ob_auto_scrollBar = null;
		ob_use_trump_card = null;
		ob_scenario_open = null;
		
		ob_ai_difficulty = null;
		ob_height = null;
		ob_now_user_index = null;
		ob_slots = null;
		ob_start_givenCards = null;
		ob_theme = null;
		ob_use_algorithm_index = null;
		ob_ver_main = null;
		ob_ver_sub1 = null;
		ob_ver_sub2 = null;
		ob_width = null;
		ob_change_card_count = null;
		ob_use_track_save_delay = null;
		ob_plusMinus_card_count = null;
		ob_multiply_card_count = null;
		
		ob_op_divide = null;
		ob_op_minus = null;
		ob_op_multiply = null;
		ob_op_plus = null;
		ob_op_power = null;
		ob_op_change = null;
		ob_op_clover = null;
		ob_op_spade = null;
		ob_op_heart = null;
		ob_op_diamond = null;
		ob_ver_test = null;
	}
	public static Setting getNewInstance()
	{
		Setting newOne = new Setting();
		
		return newOne.getInstance();
	}
	public Setting getInstance()
	{
		Setting setting = new Setting();
		
		String default_path = System.getProperty("user.home");
		String os = System.getProperty("os.name");
		String lang = System.getProperty("user.language");
		String separator = System.getProperty("file.separator");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + "calc" + separator;
		
		setting.version_aut = CalcWindow.version_auts();
		setting.default_path = new String(default_path);
		setting.os = new String(os);
		setting.locale = new String(lang);
		setting.separator = new String(separator);
		setting.screenSize = screenSize;
		setting.notice_url = "http://hujinone11.oranc.co.kr/calc/";		
		setting.notice_url2 = "http://netstorm.woobi.co.kr/calc/";
		
		setting.properties = new Hashtable<String, String>();
		
		if(CalcWindow.isBeta())
			setting.setScript_urlLoad(new Boolean(true));
				
		//System.out.println(setting.jarfile_path);
			
		setting.next_execute_saved = new SaveBoolean(false);
		setting.next_execute = new SaveInt(0);		
		
		reflexioner_ranks = new Vector<String>();
		
		/*
		if(setting.os.startsWith("window") || setting.os.startsWith("Window"))
		{
			setting.charset = "MS949";
		}
		else
		{
			setting.charset = "UTF-8";
		}*/
		setting.charset = "UTF-8";
		
		if(setting.locale.startsWith("en") || setting.locale.startsWith("EN") || setting.locale.startsWith("eng") || setting.locale.startsWith("ENG")) setting.lang = new English();
		else if(setting.locale.startsWith("ko") || setting.locale.startsWith("KO") || setting.locale.startsWith("kr") || setting.locale.startsWith("KR") || setting.locale.startsWith("kor") || setting.locale.startsWith("KOR")) setting.lang = new Korean();
		else setting.lang = new English();
		
		setting.lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		
		ThemeSet thmSet = new ThemeSet(0);
		/*
		setting.selected_back = new Color(190, 210, 255);
		setting.selected_inside_back = new Color(135, 180, 235);
		setting.selected_fore = new Color(0, 0, 139);
		setting.unselected_back = new Color(220, 230, 255);
		setting.unselected_inside_back = new Color(175, 200, 255);
		setting.unselected_fore = new Color(0, 0, 255);
		*/
		setting.selected_back = thmSet.getSelected_back();
		setting.selected_inside_back = thmSet.getSelected_inside();
		setting.selected_fore = thmSet.getSelected_fore();
		setting.unselected_back = thmSet.getUnselected_back();
		setting.unselected_inside_back = thmSet.getUnselected_inside();
		setting.unselected_fore = thmSet.getUnselected_fore();
		setting.color_reverse = new Boolean(thmSet.isColor_reverse());
		if(thmSet.getSelected_button() != null)
		{
			setting.selected_button = thmSet.getSelected_button();
		}
		if(thmSet.getUnselected_button() != null)
		{
			setting.unselected_button = thmSet.getUnselected_button();
		}
		setting.use_color = true;
		
		setting.otherObjects = new Vector<String>();
		setting.scenarios = new Vector<Scenario>();
		Scenario noneScenario = new Scenario();
		noneScenario.setName(setting.lang.getText(Language.NONE));
		setting.scenarios.add(noneScenario);
		
		setting.modules = new Vector<ScriptModule>();
		
		if(screenSize.getWidth() >= 1920)
		{
			setting.width = 1800;			
		}
		else if(screenSize.getWidth() >= 1600)
		{
			setting.width = 1560;					
		}
		else if(screenSize.getWidth() >= 1280)
		{
			setting.width = 1200;
		}
		else if(screenSize.getWidth() >= 1024)
		{
			setting.width = 960;
		}			
		else setting.width = 620;
		
		if(screenSize.getHeight() >= 1080)
		{
			setting.height = 700;
		}
		else if(screenSize.getHeight() >= 1024)
		{
			setting.height = 650;
		}
		else if(screenSize.getHeight() >= 864)
		{
			setting.height = 550;
		}
		else if(screenSize.getHeight() >= 768)
		{
			setting.height = 450;
		}
		else if(screenSize.getHeight() >= 720)
		{
			setting.height = 410;
		}
		else setting.height = 350;
		
		try
		{
			if(! (System.getProperty("os.name").startsWith("Windows") || System.getProperty("os.name").startsWith("windows")))
			{
				setting.height = setting.height - 50;
			}
		}
		catch(Exception e)
		{
			
		}
			
		if(setting.auto_scrollBar)
		{
			int scrollCount = setting.slots * 300;
			if(setting.width > scrollCount) setting.scrollBar = false;
			else setting.scrollBar = true;
		}
		else
		{
			if(setting.width >= 1280 && setting.slots <= 4) setting.scrollBar = false;
			else setting.scrollBar = true;
		}
		
		
		if(os.startsWith("window") || os.startsWith("windows") || os.startsWith("Window") || os.startsWith("Windows"))
		{
			
			setting.op_multiply = '×';
		}
		else
		{
			setting.op_multiply = '*';
		}
		
		setting.notice_url = "http://hujinone11.oranc.co.kr/calc/";
		setting.ver_main = CalcWindow.version_main;
		setting.ver_sub1 = CalcWindow.version_sub_1;
		setting.ver_sub2 = CalcWindow.version_sub_2;
		setting.ver_test = CalcWindow.version_test;
		setting.change_card_count = 1;
		
		setting.themeSelection_new = new Boolean(true);
		
		return setting;
	}
	public boolean input(String[] keys)
	{
		Key newKey = new Key();
		for(int i=0; i<keys.length; i++)
		{
			newKey.getChars()[i] = new KeyBlock(keys[i]);
		}
		if(newKey.accepted(this))
		{
			key = newKey;
			return true;
		}
		else return false;
	}
	public void input(AI_Algorithm alg)
	{
		if(ai_algorithms == null)
		{
			ai_algorithms = new AI_Algorithm[1];
			ai_algorithms[0] = alg;
		}
		else if(ai_algorithms.length == 0)
		{
			ai_algorithms = new AI_Algorithm[1];
			ai_algorithms[0] = alg;
		}
		else if(ai_algorithms.length >= 1)
		{
			AI_Algorithm[] newArray = new AI_Algorithm[ai_algorithms.length + 1];
			for(int i=0; i<ai_algorithms.length; i++)
			{
				newArray[i] = ai_algorithms[i];
			}
			newArray[ai_algorithms.length] = alg;
			ai_algorithms = newArray;
		}		
	}
	public void removeAI(int index)
	{
		AI_Algorithm[] newArray = new AI_Algorithm[ai_algorithms.length - 1];
		for(int i=0; i<ai_algorithms.length; i++)
		{
			if(i < index) newArray[i] = ai_algorithms[i];
			else if(i > index) newArray[i - 1] = ai_algorithms[i];
		}
		ai_algorithms = newArray;
	}
	public int getTheme()
	{
		return theme;
	}
	public void setTheme(int theme)
	{
		this.theme = theme;
	}
	public int getSlots()
	{
		return slots;
	}
	public void setSlots(int slots)
	{
		this.slots = slots;
	}
	public int getStart_givenCards()
	{
		return start_givenCards;
	}
	public void setStart_givenCards(int start_givenCards)
	{
		this.start_givenCards = start_givenCards;
	}
	public int getAi_difficulty()
	{
		return ai_difficulty;
	}
	public void setAi_difficulty(int ai_difficulty)
	{
		this.ai_difficulty = ai_difficulty;
	}
	public boolean isActive_authority_mode()
	{
		return active_authority_mode;
	}
	public void setActive_authority_mode(boolean active_authority_mode)
	{
		this.active_authority_mode = active_authority_mode;
	}
	public Dimension getScreenSize()
	{
		return screenSize;
	}
	public void setScreenSize(Dimension screenSize)
	{
		this.screenSize = screenSize;
	}
	public Language getLang()
	{
		return lang;
	}
	public void setLang(Language lang)
	{
		this.lang = lang;
	}
	public boolean isUse_own_titleBar()
	{
		return use_own_titleBar;
	}
	public void setUse_own_titleBar(boolean use_own_titleBar)
	{
		this.use_own_titleBar = use_own_titleBar;
	}
	public String getOs()
	{
		return os;
	}
	public void setOs(String os)
	{
		this.os = os;
	}
	public String getLocale()
	{
		return locale;
	}
	public void setLocale(String locale)
	{
		this.locale = locale;
	}
	public String getDefault_path()
	{
		return default_path;
	}
	public void setDefault_path(String default_path)
	{
		this.default_path = default_path;
	}
	public String getSeparator()
	{
		return separator;
	}
	public void setSeparator(String separator)
	{
		this.separator = separator;
	}
	public String getNotice_url()
	{
		return notice_url;
	}
	public void setNotice_url(String notice_url)
	{
		this.notice_url = notice_url;
	}
	public boolean isUse_color()
	{
		return use_color;
	}
	public void setUse_color(boolean use_color)
	{
		this.use_color = use_color;
	}
	public Color getSelected_back()
	{
		return selected_back;
	}
	public void setSelected_back(Color selected_back)
	{
		this.selected_back = selected_back;
	}
	public Color getSelected_inside_back()
	{
		return selected_inside_back;
	}
	public void setSelected_inside_back(Color selected_inside_back)
	{
		this.selected_inside_back = selected_inside_back;
	}
	public Color getSelected_fore()
	{
		return selected_fore;
	}
	public void setSelected_fore(Color selected_fore)
	{
		this.selected_fore = selected_fore;
	}
	public Color getUnselected_back()
	{
		return unselected_back;
	}
	public void setUnselected_back(Color unselected_back)
	{
		this.unselected_back = unselected_back;
	}
	public Color getUnselected_inside_back()
	{
		return unselected_inside_back;
	}
	public void setUnselected_inside_back(Color unselected_inside_back)
	{
		this.unselected_inside_back = unselected_inside_back;
	}
	public Color getUnselected_fore()
	{
		return unselected_fore;
	}
	public void setUnselected_fore(Color unselected_fore)
	{
		this.unselected_fore = unselected_fore;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public boolean isUse_other_algorithm()
	{
		return use_other_algorithm;
	}
	public void setUse_other_algorithm(boolean use_other_algorithm)
	{
		this.use_other_algorithm = use_other_algorithm;
	}
	public int getUse_algorithm_index()
	{
		return use_algorithm_index;
	}
	public void setUse_algorithm_index(int use_algorithm_index)
	{
		this.use_algorithm_index = use_algorithm_index;
	}
	public AI_Algorithm[] getAi_algorithms()
	{
		return ai_algorithms;
	}
	public void setAi_algorithms(AI_Algorithm[] ai_algorithms)
	{
		this.ai_algorithms = ai_algorithms;
	}
	public String getCard_separator()
	{
		return card_separator;
	}
	public void setCard_separator(String card_separator)
	{
		this.card_separator = card_separator;
	}
	public String getLookAndFeel()
	{
		return lookAndFeel;
	}
	public void setLookAndFeel(String lookAndFeel)
	{
		this.lookAndFeel = lookAndFeel;
	}
	public boolean isUseAlertWindow()
	{
		return useAlertWindow;
	}
	public void setUseAlertWindow(boolean useAlertWindow)
	{
		this.useAlertWindow = useAlertWindow;
	}
	public boolean isNetworkEnabled()
	{
		return networkEnabled;
	}
	public void setNetworkEnabled(boolean networkEnabled)
	{
		this.networkEnabled = networkEnabled;
	}
	public boolean isOnlyAI_prevent()
	{
		return onlyAI_prevent;
	}
	public void setOnlyAI_prevent(boolean onlyAI_prevent)
	{
		this.onlyAI_prevent = onlyAI_prevent;
	}
	public boolean isError_printDetail()
	{
		return error_printDetail;
	}
	public void setError_printDetail(boolean error_printDetail)
	{
		this.error_printDetail = error_printDetail;
	}
	public boolean isCenter_tab()
	{
		return center_tab;
	}
	public boolean getCenter_tab()
	{
		return center_tab;
	}
	public void setCenter_tab(boolean center_tab)
	{
		this.center_tab = center_tab;
	}
	public boolean isNotice_not_view()
	{
		return notice_not_view;
	}
	public boolean getNotice_not_view()
	{
		return notice_not_view;
	}
	public void setNotice_not_view(boolean notice_not_view)
	{
		this.notice_not_view = notice_not_view;
	}
	public boolean isNotice_not_view_option_viewed()
	{
		return notice_not_view_option_viewed;
	}
	public boolean getNotice_not_view_option_viewed()
	{
		return notice_not_view_option_viewed;
	}
	public void setNotice_not_view_option_viewed(
			boolean notice_not_view_option_viewed)
	{
		this.notice_not_view_option_viewed = notice_not_view_option_viewed;
	}
	public Rank[] getRanks()
	{
		return ranks;
	}
	public void setRanks(Rank[] ranks)
	{
		this.ranks = ranks;
	}
	public User[] getUsers()
	{
		return users;
	}
	public void setUsers(User[] users)
	{
		this.users = users;
	}
	public int getNow_user_index()
	{
		return now_user_index;
	}
	public void setNow_user_index(int now_user_index)
	{
		this.now_user_index = now_user_index;
	}
	public boolean isUser_selected()
	{
		return user_selected;
	}
	public boolean getUser_selected()
	{
		return user_selected;
	}
	public void setUser_selected(boolean user_selected)
	{
		this.user_selected = user_selected;
	}
	public int getVer_main()
	{
		return ver_main;
	}
	public void setVer_main(int ver_main)
	{
		this.ver_main = ver_main;
	}
	public int getVer_sub1()
	{
		return ver_sub1;
	}
	public void setVer_sub1(int ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}
	public int getVer_sub2()
	{
		return ver_sub2;
	}
	public void setVer_sub2(int ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}
	public char getOp_plus()
	{
		return op_plus;
	}
	public void setOp_plus(char op_plus)
	{
		this.op_plus = op_plus;
	}
	public char getOp_minus()
	{
		return op_minus;
	}
	public void setOp_minus(char op_minus)
	{
		this.op_minus = op_minus;
	}
	public char getOp_multiply()
	{
		return op_multiply;
	}
	public void setOp_multiply(char op_multiply)
	{
		this.op_multiply = op_multiply;
	}
	public char getOp_divide()
	{
		return op_divide;
	}
	public void setOp_divide(char op_divide)
	{
		this.op_divide = op_divide;
	}
	public SaveBoolean getOb_use_other_algorithm()
	{
		return ob_use_other_algorithm;
	}
	public void setOb_use_other_algorithm(SaveBoolean ob_use_other_algorithm)
	{
		this.ob_use_other_algorithm = ob_use_other_algorithm;
	}
	public SaveBoolean getOb_active_authority_mode()
	{
		return ob_active_authority_mode;
	}
	public void setOb_active_authority_mode(SaveBoolean ob_active_authority_mode)
	{
		this.ob_active_authority_mode = ob_active_authority_mode;
	}
	public SaveBoolean getOb_use_own_titleBar()
	{
		return ob_use_own_titleBar;
	}
	public void setOb_use_own_titleBar(SaveBoolean ob_use_own_titleBar)
	{
		this.ob_use_own_titleBar = ob_use_own_titleBar;
	}
	public SaveBoolean getOb_useAlertWindow()
	{
		return ob_useAlertWindow;
	}
	public void setOb_useAlertWindow(SaveBoolean ob_useAlertWindow)
	{
		this.ob_useAlertWindow = ob_useAlertWindow;
	}
	public SaveBoolean getOb_networkEnabled()
	{
		return ob_networkEnabled;
	}
	public void setOb_networkEnabled(SaveBoolean ob_networkEnabled)
	{
		this.ob_networkEnabled = ob_networkEnabled;
	}
	public SaveBoolean getOb_onlyAI_prevent()
	{
		return ob_onlyAI_prevent;
	}
	public void setOb_onlyAI_prevent(SaveBoolean ob_onlyAI_prevent)
	{
		this.ob_onlyAI_prevent = ob_onlyAI_prevent;
	}
	public SaveBoolean getOb_error_printDetail()
	{
		return ob_error_printDetail;
	}
	public void setOb_error_printDetail(SaveBoolean ob_error_printDetail)
	{
		this.ob_error_printDetail = ob_error_printDetail;
	}
	public SaveBoolean getOb_center_tab()
	{
		return ob_center_tab;
	}
	public void setOb_center_tab(SaveBoolean ob_center_tab)
	{
		this.ob_center_tab = ob_center_tab;
	}
	public SaveBoolean getOb_notice_not_view()
	{
		return ob_notice_not_view;
	}
	public void setOb_notice_not_view(SaveBoolean ob_notice_not_view)
	{
		this.ob_notice_not_view = ob_notice_not_view;
	}
	public SaveBoolean getOb_notice_not_view_option_viewed()
	{
		return ob_notice_not_view_option_viewed;
	}
	public void setOb_notice_not_view_option_viewed(
			SaveBoolean ob_notice_not_view_option_viewed)
	{
		this.ob_notice_not_view_option_viewed = ob_notice_not_view_option_viewed;
	}
	public SaveBoolean getOb_user_selected()
	{
		return ob_user_selected;
	}
	public void setOb_user_selected(SaveBoolean ob_user_selected)
	{
		this.ob_user_selected = ob_user_selected;
	}
	public SaveBoolean getOb_use_color()
	{
		return ob_use_color;
	}
	public void setOb_use_color(SaveBoolean ob_use_color)
	{
		this.ob_use_color = ob_use_color;
	}
	public SaveInt getOb_now_user_index()
	{
		return ob_now_user_index;
	}
	public void setOb_now_user_index(SaveInt ob_now_user_index)
	{
		this.ob_now_user_index = ob_now_user_index;
	}
	public SaveInt getOb_theme()
	{
		return ob_theme;
	}
	public void setOb_theme(SaveInt ob_theme)
	{
		this.ob_theme = ob_theme;
	}
	public SaveInt getOb_slots()
	{
		return ob_slots;
	}
	public void setOb_slots(SaveInt ob_slots)
	{
		this.ob_slots = ob_slots;
	}
	public SaveInt getOb_start_givenCards()
	{
		return ob_start_givenCards;
	}
	public void setOb_start_givenCards(SaveInt ob_start_givenCards)
	{
		this.ob_start_givenCards = ob_start_givenCards;
	}
	public SaveInt getOb_ai_difficulty()
	{
		return ob_ai_difficulty;
	}
	public void setOb_ai_difficulty(SaveInt ob_ai_difficulty)
	{
		this.ob_ai_difficulty = ob_ai_difficulty;
	}
	public SaveInt getOb_ver_main()
	{
		return ob_ver_main;
	}
	public void setOb_ver_main(SaveInt ob_ver_main)
	{
		this.ob_ver_main = ob_ver_main;
	}
	public SaveInt getOb_ver_sub1()
	{
		return ob_ver_sub1;
	}
	public void setOb_ver_sub1(SaveInt ob_ver_sub1)
	{
		this.ob_ver_sub1 = ob_ver_sub1;
	}
	public SaveInt getOb_ver_sub2()
	{
		return ob_ver_sub2;
	}
	public void setOb_ver_sub2(SaveInt ob_ver_sub2)
	{
		this.ob_ver_sub2 = ob_ver_sub2;
	}
	public SaveInt getOb_width()
	{
		return ob_width;
	}
	public void setOb_width(SaveInt ob_width)
	{
		this.ob_width = ob_width;
	}
	public SaveInt getOb_height()
	{
		return ob_height;
	}
	public void setOb_height(SaveInt ob_height)
	{
		this.ob_height = ob_height;
	}
	public SaveInt getOb_use_algorithm_index()
	{
		return ob_use_algorithm_index;
	}
	public void setOb_use_algorithm_index(SaveInt ob_use_algorithm_index)
	{
		this.ob_use_algorithm_index = ob_use_algorithm_index;
	}
	public SaveChar getOb_op_plus()
	{
		return ob_op_plus;
	}
	public void setOb_op_plus(SaveChar ob_op_plus)
	{
		this.ob_op_plus = ob_op_plus;
	}
	public SaveChar getOb_op_minus()
	{
		return ob_op_minus;
	}
	public void setOb_op_minus(SaveChar ob_op_minus)
	{
		this.ob_op_minus = ob_op_minus;
	}
	public SaveChar getOb_op_multiply()
	{
		return ob_op_multiply;
	}
	public void setOb_op_multiply(SaveChar ob_op_multiply)
	{
		this.ob_op_multiply = ob_op_multiply;
	}
	public SaveChar getOb_op_divide()
	{
		return ob_op_divide;
	}
	public void setOb_op_divide(SaveChar ob_op_divide)
	{
		this.ob_op_divide = ob_op_divide;
	}
	public char getOp_power()
	{
		return op_power;
	}
	public void setOp_power(char op_power)
	{
		this.op_power = op_power;
	}
	public char getOp_change()
	{
		return op_change;
	}
	public void setOp_change(char op_change)
	{
		this.op_change = op_change;
	}
	public SaveChar getOb_op_power()
	{
		return ob_op_power;
	}
	public void setOb_op_power(SaveChar ob_op_power)
	{
		this.ob_op_power = ob_op_power;
	}
	public SaveChar getOb_op_change()
	{
		return ob_op_change;
	}
	public void setOb_op_change(SaveChar ob_op_change)
	{
		this.ob_op_change = ob_op_change;
	}
	public SaveInt getOb_change_card_count()
	{
		return ob_change_card_count;
	}
	public void setOb_change_card_count(SaveInt ob_change_card_count)
	{
		this.ob_change_card_count = ob_change_card_count;
	}
	public int getChange_card_count()
	{
		return change_card_count;
	}
	public void setChange_card_count(int change_card_count)
	{
		this.change_card_count = change_card_count;
	}
	public SaveBoolean getOb_scrollBar()
	{
		return ob_scrollBar;
	}
	public void setOb_scrollBar(SaveBoolean ob_scrollBar)
	{
		this.ob_scrollBar = ob_scrollBar;
	}
	public boolean isScrollBar()
	{
		return scrollBar;
	}
	public void setScrollBar(boolean scrollBar)
	{
		this.scrollBar = scrollBar;
	}
	public boolean accepted()
	{
		if(key != null)
		{
			try
			{
				if(abandoned_key()) return false;
				else return key.accepted(this);
			} 
			catch (Exception e)
			{
				return key.accepted(this);
			}
		}
		else return false;
	}
	public boolean accept_net() throws Exception
	{
		if(key != null) return key.accept_net(this);
		else return false;
	}
	public boolean accept_mastered()
	{
		if(key != null) return key.accepted_master(this);
		else return false;
	}
	public boolean abandoned_key() throws Exception
	{
		if(key != null) return key.abandoned(this);
		else return false;
	}
	public boolean isProfessional_contents_loaded()
	{
		return professional_contents_loaded;
	}
	public void setProfessional_contents_loaded(boolean professional_contents_loaded)
	{
		this.professional_contents_loaded = professional_contents_loaded;
	}
	public String getCharset()
	{
		return charset;
	}
	public void setCharset(String charset)
	{
		this.charset = charset;
	}
	public SaveBoolean getOb_professional_contents_loaded()
	{
		return ob_professional_contents_loaded;
	}
	public void setOb_professional_contents_loaded(
			SaveBoolean ob_professional_contents_loaded)
	{
		this.ob_professional_contents_loaded = ob_professional_contents_loaded;
	}
	public Key getKey()
	{
		return key;
	}
	public void setKey(Key key)
	{
		this.key = key;
	}
	public boolean isClassic_mode()
	{
		return classic_mode;
	}
	public void setClassic_mode(boolean classic_mode)
	{
		this.classic_mode = classic_mode;
	}
	public SaveBoolean getOb_classic_mode()
	{
		return ob_classic_mode;
	}
	public void setOb_classic_mode(SaveBoolean ob_classic_mode)
	{
		this.ob_classic_mode = ob_classic_mode;
	}
	public SaveChar getOb_op_spade()
	{
		return ob_op_spade;
	}
	public void setOb_op_spade(SaveChar ob_op_spade)
	{
		this.ob_op_spade = ob_op_spade;
	}
	public SaveChar getOb_op_clover()
	{
		return ob_op_clover;
	}
	public void setOb_op_clover(SaveChar ob_op_clover)
	{
		this.ob_op_clover = ob_op_clover;
	}
	public SaveChar getOb_op_diamond()
	{
		return ob_op_diamond;
	}
	public void setOb_op_diamond(SaveChar ob_op_diamond)
	{
		this.ob_op_diamond = ob_op_diamond;
	}
	public SaveChar getOb_op_heart()
	{
		return ob_op_heart;
	}
	public void setOb_op_heart(SaveChar ob_op_heart)
	{
		this.ob_op_heart = ob_op_heart;
	}
	public char getOp_spade()
	{
		return op_spade;
	}
	public void setOp_spade(char op_spade)
	{
		this.op_spade = op_spade;
	}
	public char getOp_clover()
	{
		return op_clover;
	}
	public void setOp_clover(char op_clover)
	{
		this.op_clover = op_clover;
	}
	public char getOp_diamond()
	{
		return op_diamond;
	}
	public void setOp_diamond(char op_diamond)
	{
		this.op_diamond = op_diamond;
	}
	public char getOp_heart()
	{
		return op_heart;
	}
	public void setOp_heart(char op_heart)
	{
		this.op_heart = op_heart;
	}
	public SaveBoolean getNext_execute_saved()
	{
		return next_execute_saved;
	}
	public void setNext_execute_saved(SaveBoolean next_execute_saved)
	{
		this.next_execute_saved = next_execute_saved;
	}
	public SaveInt getNext_execute()
	{
		return next_execute;
	}
	public void setNext_execute(SaveInt next_execute)
	{
		this.next_execute = next_execute;
	}
	public SaveBoolean getOb_use_track()
	{
		return ob_use_track;
	}
	public void setOb_use_track(SaveBoolean ob_use_track)
	{
		this.ob_use_track = ob_use_track;
	}
	public boolean isUse_track()
	{
		return use_track;
	}
	public void setUse_track(boolean use_track)
	{
		this.use_track = use_track;
	}
	public SaveBoolean getOb_use_track_realtime()
	{
		return ob_use_track_realtime;
	}
	public void setOb_use_track_realtime(SaveBoolean ob_use_track_realtime)
	{
		this.ob_use_track_realtime = ob_use_track_realtime;
	}
	public boolean getUse_track_realtime()
	{
		return use_track_realtime;
	}
	public boolean isUse_track_realtime()
	{
		return use_track_realtime;
	}
	public void setUse_track_realtime(boolean use_track_realtime)
	{
		this.use_track_realtime = use_track_realtime;
	}
	public SaveChar getOb_ver_test()
	{
		return ob_ver_test;
	}
	public void setOb_ver_test(SaveChar ob_ver_test)
	{
		this.ob_ver_test = ob_ver_test;
	}
	public char getVer_test()
	{
		return ver_test;
	}
	public void setVer_test(char ver_test)
	{
		this.ver_test = ver_test;
	}
	public int getUse_track_save_delay()
	{
		return use_track_save_delay;
	}
	public void setUse_track_save_delay(int use_track_save_delay)
	{
		this.use_track_save_delay = use_track_save_delay;
	}
	public String getJarfile_path()
	{
		return jarfile_path;
	}
	public void setJarfile_path(String jarfile_path)
	{
		this.jarfile_path = jarfile_path;
	}
	public SaveInt getOb_use_track_save_delay()
	{
		return ob_use_track_save_delay;
	}
	public void setOb_use_track_save_delay(SaveInt ob_use_track_save_delay)
	{
		this.ob_use_track_save_delay = ob_use_track_save_delay;
	}
	public boolean isAuto_scrollBar()
	{
		return auto_scrollBar;
	}
	public void setAuto_scrollBar(boolean auto_scrollBar)
	{
		this.auto_scrollBar = auto_scrollBar;
	}
	public SaveBoolean getOb_auto_scrollBar()
	{
		return ob_auto_scrollBar;
	}
	public void setOb_auto_scrollBar(SaveBoolean ob_auto_scrollBar)
	{
		this.ob_auto_scrollBar = ob_auto_scrollBar;
	}
	public SaveBoolean getOb_use_trump_card()
	{
		return ob_use_trump_card;
	}
	public void setOb_use_trump_card(SaveBoolean ob_use_trump_card)
	{
		this.ob_use_trump_card = ob_use_trump_card;
	}
	public SaveInt getOb_plusMinus_card_count()
	{
		return ob_plusMinus_card_count;
	}
	public void setOb_plusMinus_card_count(SaveInt ob_plusMinus_card_count)
	{
		this.ob_plusMinus_card_count = ob_plusMinus_card_count;
	}
	public SaveInt getOb_multiply_card_count()
	{
		return ob_multiply_card_count;
	}
	public void setOb_multiply_card_count(SaveInt ob_multiply_card_count)
	{
		this.ob_multiply_card_count = ob_multiply_card_count;
	}
	public int getPlusMinus_card_count()
	{
		return plusMinus_card_count;
	}
	public void setPlusMinus_card_count(int plusMinus_card_count)
	{
		this.plusMinus_card_count = plusMinus_card_count;
	}
	public int getMultiply_card_count()
	{
		return multiply_card_count;
	}
	public void setMultiply_card_count(int multiply_card_count)
	{
		this.multiply_card_count = multiply_card_count;
	}
	public boolean isUse_trump_card()
	{
		return use_trump_card;
	}
	public void setUse_trump_card(boolean use_trump_card)
	{
		this.use_trump_card = use_trump_card;
	}
	public Boolean getKind_ai()
	{
		return kind_ai;
	}
	public void setKind_ai(Boolean kind_ai)
	{
		this.kind_ai = kind_ai;
	}
	public Boolean getTutorial_needed()
	{
		return tutorial_needed;
	}
	public void setTutorial_needed(Boolean tutorial_needed)
	{
		this.tutorial_needed = tutorial_needed;
	}
	public String getNotice_url2()
	{
		return notice_url2;
	}
	public void setNotice_url2(String notice_url2)
	{
		this.notice_url2 = notice_url2;
	}
	public String getScript_engine()
	{
		return script_engine;
	}
	public void setScript_engine(String script_engine)
	{
		this.script_engine = script_engine;
	}
	public Vector<ScriptModule> getModules()
	{
		return modules;
	}
	public void setModules(Vector<ScriptModule> modules)
	{
		this.modules = modules;
	}
	public Vector<Scenario> getScenarios()
	{
		return scenarios;
	}
	public void setScenarios(Vector<Scenario> scenarios)
	{
		this.scenarios = scenarios;
	}
	public SaveBoolean getOb_scenario_open()
	{
		return ob_scenario_open;
	}
	public void setOb_scenario_open(SaveBoolean ob_scenario_open)
	{
		this.ob_scenario_open = ob_scenario_open;
	}
	public boolean isScenario_open()
	{
		return scenario_open;
	}
	public void setScenario_open(boolean scenario_open)
	{
		this.scenario_open = scenario_open;
	}
	public Vector<String> getOtherObjects()
	{
		return otherObjects;
	}
	public void setOtherObjects(Vector<String> otherObjects)
	{
		this.otherObjects = otherObjects;
	}
	public Color getSelected_button()
	{
		return selected_button;
	}
	public void setSelected_button(Color selected_button)
	{
		this.selected_button = selected_button;
	}
	public Color getUnselected_button()
	{
		return unselected_button;
	}
	public void setUnselected_button(Color unselected_button)
	{
		this.unselected_button = unselected_button;
	}
	public Boolean getColor_reverse()
	{
		return color_reverse;
	}
	public void setColor_reverse(Boolean color_reverse)
	{
		this.color_reverse = color_reverse;
	}
	public Boolean getUse_sound()
	{
		return use_sound;
	}
	public void setUse_sound(Boolean use_sound)
	{
		this.use_sound = use_sound;
	}
	
	public Font getUsingFont()
	{
		return usingFont;
	}
	public void setUsingFont(Font usingFont)
	{
		this.usingFont = usingFont;
	}
	public Boolean getThemeSelection_new()
	{
		return themeSelection_new;
	}
	public void setThemeSelection_new(Boolean themeSelection_new)
	{
		this.themeSelection_new = themeSelection_new;
	}
	public Boolean getScript_directLoad()
	{
		return script_directLoad;
	}
	public void setScript_directLoad(Boolean script_directLoad)
	{
		this.script_directLoad = script_directLoad;
	}
	public Boolean getScript_urlLoad()
	{
		return script_urlLoad;
	}
	public void setScript_urlLoad(Boolean script_urlLoad)
	{
		this.script_urlLoad = script_urlLoad;
	}
	public Integer getKey_1()
	{
		return key_1;
	}
	public void setKey_1(Integer key_1)
	{
		this.key_1 = key_1;
	}
	public Integer getKey_2()
	{
		return key_2;
	}
	public void setKey_2(Integer key_2)
	{
		this.key_2 = key_2;
	}
	public Integer getKey_3()
	{
		return key_3;
	}
	public void setKey_3(Integer key_3)
	{
		this.key_3 = key_3;
	}
	public Integer getKey_shift()
	{
		return key_shift;
	}
	public void setKey_shift(Integer key_shift)
	{
		this.key_shift = key_shift;
	}
	public Integer getKey_space()
	{
		return key_space;
	}
	public void setKey_space(Integer key_space)
	{
		this.key_space = key_space;
	}
	public Integer getKey_l()
	{
		return key_l;
	}
	public void setKey_l(Integer key_l)
	{
		this.key_l = key_l;
	}
	public Integer getKey_k()
	{
		return key_k;
	}
	public void setKey_k(Integer key_k)
	{
		this.key_k = key_k;
	}
	public Integer getKey_up()
	{
		return key_up;
	}
	public void setKey_up(Integer key_up)
	{
		this.key_up = key_up;
	}
	public Integer getKey_down()
	{
		return key_down;
	}
	public void setKey_down(Integer key_down)
	{
		this.key_down = key_down;
	}
	public Integer getKey_left()
	{
		return key_left;
	}
	public void setKey_left(Integer key_left)
	{
		this.key_left = key_left;
	}
	public Integer getKey_right()
	{
		return key_right;
	}
	public void setKey_right(Integer key_right)
	{
		this.key_right = key_right;
	}
	public String getVersion_aut()
	{
		return version_aut;
	}
	public void setVersion_aut(String version_aut)
	{
		this.version_aut = version_aut;
	}
	public String getDownloaded_path()
	{
		return downloaded_path;
	}
	public void setDownloaded_path(String downloaded_path)
	{
		this.downloaded_path = downloaded_path;
	}
	public VersionData getDownloaded_ver()
	{
		return downloaded_ver;
	}
	public void setDownloaded_ver(VersionData downloaded_ver)
	{
		this.downloaded_ver = downloaded_ver;
	}
	public Boolean getUse_image()
	{
		return use_image;
	}
	public void setUse_image(Boolean use_image)
	{
		this.use_image = use_image;
	}
	public Vector<String> getReflexioner_ranks()
	{
		return reflexioner_ranks;
	}
	public void setReflexioner_ranks(Vector<String> reflexioner_ranks)
	{
		this.reflexioner_ranks = reflexioner_ranks;
	}
	public Hashtable<String, String> getProperties()
	{
		return properties;
	}
	public void setProperties(Hashtable<String, String> properties)
	{
		this.properties = properties;
	}	
}