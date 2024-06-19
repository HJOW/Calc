package lang;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public abstract class Language implements Serializable
{
	private static final long serialVersionUID = 4621038632869565105L;
	
	public static final int LANG_ENGLISH = 0;
	public static final int LANG_KOREAN = 1;
	
	public static final int LANG_USER_DEFINED = 10000;
	
	public static final int TITLE = 0;
	public static final int X = 1;
	public static final int IS = 2;
	public static final int ARE = 3;
	public static final int GET = 4;
	public static final int START = 5;
	public static final int EXIT = 6;
	public static final int CLOSE = 7;
	public static final int RESULT = 8;
	public static final int WON = 9;
	public static final int WHOS = 10;
	public static final int POINT = 11;
	public static final int PAY = 12;
	public static final int OWNS = 13;
	public static final int PAIDS = 14;
	public static final int NAME = 15;
	public static final int FINISH = 16;
	public static final int SEALED = 17;
	public static final int NONE = 18;
	public static final int PLAYER = 19;
	public static final int AI = 20;
	public static final int GAME_STOP = 21;
	public static final int DECK_LABEL1 = 22;
	public static final int DECK_LABEL2 = 23;
	public static final int DECK_LABEL3 = 24;
	public static final int DECK_LABEL4 = 25;
	public static final int MENU_FILE = 26;
	public static final int BACKGROUND = 27;
	public static final int FOREGROUND = 28;
	public static final int INSIDE_BACKGROUND = 29;
	public static final int ACTIVE = 30;
	public static final int DEACTIVE = 31;
	public static final int WIDTH = 32;
	public static final int HEIGHT = 33;
	public static final int SAVE = 34;
	public static final int LOAD = 35;
	public static final int RED = 36;
	public static final int GREEN = 37;
	public static final int BLUE = 38;
	public static final int SETTING = 39;
	public static final int COUNT = 40;
	public static final int MAX = 41;
	public static final int NEED_RESTART = 42;
	public static final int SELF = 43;
	public static final int TITLE_BAR = 44;
	public static final int USE = 45;
	public static final int HELP = 46;
	public static final int ON_START_CARDS = 47;
	public static final int AUTHORITY = 48;
	public static final int REPLAY = 49;
	public static final int EDIT = 50;
	public static final int VIEW = 51;
	public static final int MAKER = 52;
	public static final int ABOUT = 53;
	public static final int CARD = 54;
	public static final int THEME = 55;	
	public static final int ALERT = 56;
	public static final int WINDOW = 57;
	public static final int SHOW = 58;
	public static final int COMPLETE = 59;
	public static final int FAIL = 60;
	public static final int SINGLE = 61;
	public static final int MULTI = 62;
	public static final int PLAY = 63;
	public static final int HOST = 64;
	public static final int JOIN = 65;
	public static final int CONNECT = 66;
	public static final int IP = 67;
	public static final int PORT = 68;
	public static final int DISCONNECT = 69;
	public static final int HOSTING = 70;
	public static final int CONNECTING = 71;
	public static final int READY = 72;
	public static final int CANCEL = 73;
	public static final int DATE = 74;
	public static final int VERSION = 75;
	public static final int RANKING = 76;
	public static final int BONUS_TARGET = 77;
	public static final int COLOR = 78;
	public static final int DIFFICULTY = 79;
	public static final int RANDOM_ORDER = 80;
	public static final int PRINT_ERROR_DETAIL = 81;
	public static final int SEPARATOR = 82;
	public static final int HOW_TO_SHOW_CARD = 83;
	public static final int LOG = 84;
	public static final int DETAIL = 85;
	public static final int SUMMARY = 86;
	public static final int SUMMARY_USE = 87;
	public static final int NOTICE = 88;
	public static final int NOTICE_NOT_VIEW_AGAIN = 89;
	public static final int USE_XML_SETTING = 90;
	public static final int RESET = 91;
	public static final int NOW_PLAYER_CARD = 92;
	public static final int LEFT = 93;
	public static final int USER = 94;
	public static final int NEW_USER = 95;
	public static final int BET = 96;
	public static final int SELECT = 97;
	public static final int CREDIT = 98;
	public static final int ADD = 99;
	public static final int REMOVE = 100;
	public static final int PASSWORD = 101;
	public static final int VIRTUAL = 102;
	public static final int POINT_CODE = 103;
	public static final int CODE_CHECKER = 104;
	public static final int CHECK = 105;
	public static final int MENU = 106;
	public static final int INPUT = 107;
	public static final int YEAR = 108;
	public static final int MONTH = 109;
	public static final int DAY = 110;
	public static final int HOUR = 111;
	public static final int MINUTE = 112;
	public static final int TIME = 113;
	public static final int INFORMATION = 114;
	public static final int MAIN = 115;
	public static final int CHANGE_CARD = 116;
	public static final int SCROLLBAR = 117;
	public static final int IN_BAR = 118;
	public static final int BASIC_EDITION = 119;
	public static final int PROFESSIONAL = 120;
	public static final int OK = 121;
	public static final int ACCEPT = 122;
	public static final int ULTIMATE = 123;
	public static final int RESTART = 124;
	public static final int CLASSIC = 125;
	public static final int ONECARD = 126;
	public static final int MODE = 127;
	public static final int DIR_UP = 128;
	public static final int DIR_DOWN = 129;
	public static final int DIR_LEFT = 130;
	public static final int DIR_RIGHT = 131;
	public static final int DIR_CENTER = 132;
	public static final int ACCUMULATED = 133;
	public static final int ANOTHER_GAMES = 134;
	public static final int RUN = 135;
	public static final int DESC_CALC = 136;
	public static final int DESC_ONECARD = 137;
	public static final int ONECARD_PANELTY = 138;	
	public static final int COPY_CLIPBOARD = 139;
	public static final int DESC_COPY_CLIPBOARD = 140;
	public static final int ERROR = 141;
	public static final int TRACKING = 142;
	public static final int TRACKING_AUTOSAVE = 143;
	public static final int DELAY = 144;	
	public static final int REFRESH = 145;
	public static final int AUTO_PASS = 146;
	public static final int CONQUER = 147;
	public static final int EASY = 148;
	public static final int NORMAL = 149;
	public static final int HARD = 150;
	public static final int SCRIPT = 151;
	public static final int ENGINE = 152;
	public static final int MODULE = 153;
	public static final int TYPE = 154;
	public static final int BLOCK = 155;
	public static final int WARN = 156;
	public static final int WARN_SCRIPT = 157;
	public static final int SCENARIO = 158;
	public static final int NO_SCENARIO_DESC = 159;
	public static final int ORDER = 160;
	public static final int ALL = 161;
	public static final int CHAT = 162;
	public static final int TO = 163;
	public static final int COUNTERPARTY = 164;
	public static final int ONLY_BETA = 165;
	public static final int DOWNLOAD = 166;
	public static final int UPDATE = 167;
	public static final int ALREADY_NEWEST = 168;
	public static final int DOYOUWANTTOUPDATE = 169;
	public static final int DOWNLOAD_FINISH = 170;
	public static final int CHECK_RELIABILITY = 171;
	public static final int EVENT = 172;
	public static final int HOMEPAGE = 173;
	public static final int ADDRESS = 174;
	public static final int WEB = 175;
	public static final int UNINSTALL_ASK = 176;
	public static final int UNINSTALL_DELETED = 177;
	public static final int UNINSTALL_COMPLETED = 178;
	public static final int UNINSTALL_ERROR = 179;
	public static final int UNINSTALL_AFTER_ASK = 180;
	public static final int UNINSTALL_AFTER_DELETED = 181;
	public static final int UNINSTALL_AFTER_COMPLETED = 182;
	public static final int UNINSTALL_AFTER_ERROR = 183;
	public static final int UNINSTALL = 184;
	public static final int MANAGEMENT = 185;
	public static final int MASTER = 186;
	public static final int MAKE = 187;
	public static final int MATHCONQ = 188;
	public static final int REFLEX = 189;
	public static final int ANSWER = 190;
	public static final int MATHQ_SIMPLEHELP = 191;
	public static final int MATHQ_HELP = 192;
	
	public static final int SHELTER_CITY = 193;
	public static final int PAUSE = 194;
	public static final int PAUSE_BREAK = 195;
	public static final int TAX = 196;
	public static final int TOTAL_INFO = 197;
	public static final int BUILD = 198;
	public static final int RESIDENT = 199;
	public static final int MARKETPLACE = 200;
	public static final int POWERPLANT = 201;
	public static final int WAREHOUSE = 202;
	public static final int FARM = 203;
	public static final int CLINIC = 204;
	public static final int ACADEMY = 205;
	public static final int COST = 206;
	public static final int POLICY = 207;
	public static final int MAKE_ACTIVE = 208;
	public static final int MAKE_DEACTIVE = 209;
	public static final int ECONOMY = 210;
	public static final int ETC = 211;
	public static final int NEW_CITY = 212;
	public static final int CITY = 213;
	public static final int BUDGET = 214;
	public static final int POPULATION = 215;
	public static final int AVERAGE = 216;
	public static final int INTELLIGENT = 217;
	public static final int STRENGTH = 218;
	public static final int MINIMUM = 219;
	public static final int MAXIMUM = 220;
	public static final int HP = 221;
	public static final int HAPPINESS = 222;
	public static final int DESEASE = 223;
	public static final int DEEP = 224;
	public static final int FOOD = 225;
	public static final int PRODUCTION = 226;
	public static final int SIZE = 227;
	public static final int SITUATION = 228;
	public static final int DESC_RESIDENT = 229;
	public static final int DESC_MARKETPLACE = 230;
	public static final int DESC_POWERPLANT = 231;
	public static final int DESC_WAREHOUSE = 232;
	public static final int DESC_FARM = 233;
	public static final int DESC_CURE = 234;
	public static final int DESC_ACADEMY = 235;
	public static final int NEED_SELECT_CITY = 236;
	public static final int FULL_SLOT = 237;
	public static final int NEED_SELECT_SECTION = 238;
	public static final int NEED_BUDGET = 239;
	public static final int ON = 240;
	public static final int STOP_CONTROL_CITY = 241;
	public static final int BUILD_WARN = 242;
	public static final int VIEW_ON_WEB = 243;
	public static final int CATCH = 244;
	public static final int ENEMY = 245;
	public static final int ITEM = 246;
	public static final int REFLEX_SIMPLEHELP = 247;
	public static final int REFLEX_HELP = 248;
	public static final int REFLEX_FLEX = 249;
	public static final int REFLEX_BERSERK = 250;
	public static final int FILE_CREATED = 251;
	public static final int FILE_DELETED = 252;
	public static final int REFLEX_CLIPPER = 253;
	public static final int REFLEX_WARSHIP = 254;
	public static final int USER_DEFINED = 255;
	public static final int DOWNLOAD_PACK = 256;
	public static final int PATH = 257;
	public static final int WILL_DOWNLOAD_AT = 258;
	public static final int LICENSE = 259;
	public static final int ALLOW = 260;
	public static final int FIRST_ADDITIONAL_DOWNLOAD = 261;
	public static final int NOT_NOW = 262;
	public static final int FILE_NEEDS = 263;
	public static final int CONTINUES = 264;	

	public static final int SHIP_NAME = 265;
	public static final int SHIP_HP = 266;
	public static final int SHIP_E = 267;
	public static final int SHIP_SIZE = 268;
	public static final int SHIP_SPEED = 269;
	public static final int WEAPON = 270;
	public static final int DAMAGE = 271;
	public static final int COOLING_TIME = 272;
	public static final int RANGE = 273;
	public static final int GUIDE = 274;
	public static final int HORIZONTAL_INTERVAL = 275;
	public static final int IF_MULTIPLE = 276;
	public static final int FIRE_COUNT = 277;
	public static final int SPEND_E = 278;
	public static final int MISSILE_SIZE = 279;
	public static final int KIND_INTERCEPTOR = 280;
	public static final int COUNT_INTERCEPTOR = 281;
	public static final int MISSILE_SPEED = 282;
	public static final int KINDS = 283;
	public static final int MISSILE_KIND = 284;
	public static final int SHAPE = 285;
	
	public static final int GAME_READY = 286;
	public static final int TODAY_GAME = 287;
	public static final int SHIP = 288;
	public static final int TODAY_ITEM = 289;
	public static final int TODAY_WARN = 290;
	public static final int CONTINUE_ON_ZERO = 291;
	public static final int INPUT_URL = 292;
	public static final int INPUT_URL2 = 293;
	public static final int INPUT_REPLAY_GAP = 294;
	
	public static final int REFLEX_CHASER = 295;
	public static final int REFLEX_SATELLITE = 296;
	public static final int REFLEX_CARRIER = 297;
	
	
	public static final int HELP_CONTENT = 20000;
	public static final int DESCRIPTIONS = 100000;
	public static final int NOTICE_NULL = 30000;
	public static final int INPUT_SERIAL_AGREEMENT = 40000;
	
	//private SaveInt description_length;
	
	protected String title, x, is, are, deckLabel1, deckLabel2, deckLabel3, deckLabel4, get, start, exit, close, result, won, whos, point, pay, owns, paids, name, finish, sealed, none, player, ai;
	protected String menu_file, game_stop, background, foreground, inside_background, active, deactive, width, height, save, load, red, green, blue, setting, count, max, needRestart, self, titleBar, use;
	protected String help_contents, help, on_start_cards, authority, replay, edit, view, maker, about, card, theme, alert, window, show, complete, fail, check, menu, input;
	protected String single, multi, play, host, join, connect, ip, port, disconnect, hosting, connecting, ready, cancel, ranking, point_code, code_checker, error, view_on_web;
	protected String date, version, bonus_target, color, difficulty, randomOrder, print_error_detail, separator, howToShowCard, log, detail, summary, summary_use, notice;
	protected String notice_not_view_again, use_xml_setting, reset, now_player_card, left, user, new_user, bet, select, credit, add, remove, password, virtuals, script, engine;
	protected String year, month, day, hour, minute, time, download, update, already_newest, doyouwantupdate, downloadfinish, checkreliability, mathconq, reflex;
	protected String tracking, tracking_autosave, delay, notice_null, refresh, auto_pass, conquer, module, type, block, warn, warn_script, scenario, noscenario_description;
	protected String easy, normal, hard, order, all, chat, to, counterparty, onlyBeta, event, homepage, address, web, management, uninstall, make, answer, mathq_simplehelp, mathq_help;
	protected String uninstall_askMessage, uninstall_deleted, uninstall_completed, uninstall_error, uninstall_after_askMessage, uninstall_after_deleted, uninstall_after_completed, uninstall_after_error;
	protected String information, main, change_card, scrollBar, in_bar, basic_edition, professional, ultimate, master, ok, accept, restart, classic, copy_clipboard, desc_copy_clipboard;
	protected String oneCard, modes, dir_center, dir_left, dir_right, dir_down, dir_up, accumulated, another_games, runs, oneCard_panelty, input_serial_agreement;
	protected String shelter_city, pause, pause_break, tax, total_info, build, resident, marketplace, powerplant, warehouse, farm, clinic, academy, cost, policy, make_active, make_deactive, economy, etc, new_city, city;
	protected String budget, population, average, intelligent, strength, minimum, maximum, hp, happiness, desease, deep, food, production, size, situation, stop_control_city, build_warn;
	protected String desc_resident, desc_marketplace, desc_powerplant, desc_warehouse, desc_farm, desc_cure, desc_academy, need_selectCity, full_slot, need_selectSection, need_budget, on;
	protected String catchs, enemy, item, reflex_simplehelp, reflex_help, reflex_flex, reflex_berserk, reflex_clipper, reflex_warship, download_pack, user_defined, path, will_download_at;
	protected String license, allow, first_additional_download, not_now;
	protected String file_created, file_deleted;
	protected String desc_calc, desc_oneCard;
	protected String file_needs, continues, ship_name, ship_startHP, ship_startE, shipSize, ship_startSpeed, weapon;
	protected String spendE, missile_size, shape;
	protected String game_ready, today_game, ship, today_item, today_warn, continue_on_zero, input_url, input_url2;
	protected String input_replay_gap, reflex_chaser, reflex_satellite, reflex_carrier;
	protected String attackPower, cooltime, weapon_range, guide_helperonly, horizontal_gap, if_multipleShoot, fireCount, kind_interceptor, count_interceptor, missile_speed, kinds, missile_kind;
	protected String description[];
	protected String basic_charset = "UTF-8";
	
	public abstract int getType();
	
	public String getText(int index, String charset) throws UnsupportedEncodingException
	{
		return new String(getText(index).getBytes(basic_charset), charset);
	}
	
	public String getText(int index)
	{
		if(index >= DESCRIPTIONS && index < DESCRIPTIONS + description.length)
		{
			String gets = "";
			try
			{
				gets = description[index - DESCRIPTIONS];
				return gets;
			}
			catch(IndexOutOfBoundsException e)
			{
				return "";
			}			
		}		
		else
		switch(index)
		{		
			case GAME_READY:
				return game_ready;
			case TODAY_GAME:
				return today_game;
			case SHIP:
				return ship;
			case TODAY_ITEM:
				return today_item;
			case TODAY_WARN:
				return today_warn;
			case CONTINUE_ON_ZERO:
				return continue_on_zero;
			case INPUT_URL:
				return input_url;
			case INPUT_URL2:
				return input_url2;
			case INPUT_REPLAY_GAP:
				return input_replay_gap;		
			case FILE_NEEDS:
				return file_needs;
			case CONTINUES:
				return continues;
			case SHIP_NAME:
				return ship_name;
			case SHIP_HP:
				return ship_startHP;
			case SHIP_SIZE:
				return shipSize;
			case SHIP_E:
				return ship_startE;
			case SHIP_SPEED:
				return ship_startSpeed;
			case WEAPON:
				return weapon;
			case DAMAGE:
				return attackPower;
			case COOLING_TIME:
				return cooltime;
			case RANGE:
				return weapon_range;
			case GUIDE:
				return guide_helperonly;
			case HORIZONTAL_INTERVAL:
				return horizontal_gap;
			case IF_MULTIPLE:
				return if_multipleShoot;
			case FIRE_COUNT:
				return fireCount;
			case SPEND_E:
				return spendE;
			case MISSILE_SIZE:
				return missile_size;
			case KIND_INTERCEPTOR:
				return kind_interceptor;
			case COUNT_INTERCEPTOR:
				return count_interceptor;
			case MISSILE_SPEED:
				return missile_speed;
			case KINDS:
				return kinds;
			case MISSILE_KIND:
				return missile_kind;
			case SHAPE:
				return shape;		
			case LICENSE:
				return license;
			case ALLOW:
				return allow;
			case FIRST_ADDITIONAL_DOWNLOAD:
				return first_additional_download;
			case NOT_NOW:
				return not_now;
			case FILE_CREATED:
				return file_created;
			case FILE_DELETED:
				return file_deleted;
			case WILL_DOWNLOAD_AT:
				return will_download_at;
			case ORDER:
				return order;
			case TITLE:
				return title;
			case X:
				return x;
			case MODULE:
				return module;
			case CHAT:
				return chat;
			case TO:
				return to;
			case MAKE:
				return make;
			case MATHCONQ:
				return mathconq;
			case REFLEX:
				return reflex;
			case ANSWER:
				return answer;
			case CATCH:
				return catchs;
			case ENEMY:
				return enemy;
			case ITEM:
				return item;
			case REFLEX_SIMPLEHELP:
				return reflex_simplehelp;
			case REFLEX_HELP:
				return reflex_help;
			case REFLEX_FLEX:
				return reflex_flex;
			case REFLEX_BERSERK:
				return reflex_berserk;
			case REFLEX_CLIPPER:
				return reflex_clipper;
			case REFLEX_WARSHIP:
				return reflex_warship;
			case REFLEX_SATELLITE:
				return reflex_satellite;
			case REFLEX_CHASER:
				return reflex_chaser;
			case REFLEX_CARRIER:
				return reflex_carrier;
			case PATH:
				return path;
			case DOWNLOAD_PACK:
				return download_pack;
			case USER_DEFINED:
				return user_defined;
			case MATHQ_SIMPLEHELP:
				return mathq_simplehelp;
			case MATHQ_HELP:
				return mathq_help;
			case UNINSTALL_ASK:
				return uninstall_askMessage;
			case UNINSTALL_COMPLETED:
				return uninstall_completed;
			case UNINSTALL_DELETED:
				return uninstall_deleted;
			case UNINSTALL_ERROR:
				return uninstall_error;
			case UNINSTALL_AFTER_ASK:
				return uninstall_after_askMessage;
			case UNINSTALL_AFTER_COMPLETED:
				return uninstall_after_completed;
			case UNINSTALL_AFTER_DELETED:
				return uninstall_after_deleted;
			case UNINSTALL_AFTER_ERROR:
				return uninstall_after_error;
			case UNINSTALL:
				return uninstall;
			case MANAGEMENT:
				return management;
			case VIEW_ON_WEB:
				return view_on_web;				
			case COUNTERPARTY:
				return counterparty;
			case TYPE:
				return type;
			case BLOCK:
				return block;
			case ONLY_BETA:
				return onlyBeta;
			case ALL:
				return all;
			case DOWNLOAD:
				return download;
			case UPDATE:
				return update;
			case EVENT:
				return event;
			case HOMEPAGE:
				return homepage;
			case ADDRESS:
				return address;
			case WEB:
				return web;
			case ALREADY_NEWEST:
				return already_newest;
			case DOWNLOAD_FINISH:
				return downloadfinish;
			case DOYOUWANTTOUPDATE:
				return doyouwantupdate;
			case CHECK_RELIABILITY:
				return checkreliability;
			case WARN:
				return warn;
			case WARN_SCRIPT:
				return warn_script;
			case SCENARIO:
				return scenario;
			case NO_SCENARIO_DESC:
				return noscenario_description;
			case IS:
				return is;
			case ARE:
				return are;
			case GET:
				return get;
			case START:
				return start;
			case EXIT:
				return exit;
			case CLOSE:
				return close;
			case RESULT:
				return result;
			case WON:
				return won;
			case WHOS:
				return whos;
			case POINT:
				return point;
			case PAY:
				return pay;
			case OWNS:
				return owns;
			case PAIDS:
				return paids;
			case REFRESH:
				return refresh;
			case NAME:
				return name;
			case FINISH:
				return finish;
			case SEALED:
				return sealed;
			case NONE:
				return none;
			case PLAYER:
				return player;
			case AI:
				return ai;
			case SCRIPT:
				return script;
			case ENGINE:
				return engine;
			case WIDTH:
				return width;
			case HEIGHT:
				return height;
			case SAVE:
				return save;
			case LOAD:
				return load;
			case RED:
				return red;
			case GREEN:
				return green;
			case BLUE:
				return blue;
			case SETTING:
				return setting;
			case ERROR:
				return error;
			case COUNT:
				return count;
			case MAX:
				return max;
			case CHECK:
				return check;
			case MENU:
				return menu;
			case INPUT:
				return input;
			case DELAY:
				return delay;
			case TIME:
				return time;
			case YEAR:
				return year;
			case MONTH:
				return month;
			case DAY:
				return day;
			case HOUR:
				return hour;
			case MINUTE:
				return minute;
			case IN_BAR:
				return in_bar;
			case CONQUER:
				return conquer;
			case EASY:
				return easy;
			case NORMAL:
				return normal;
			case HARD:
				return hard;
			case NOTICE_NULL:
				return notice_null;
			case AUTO_PASS:
				return auto_pass;
			case TRACKING:
				return tracking;
			case TRACKING_AUTOSAVE:
				return tracking_autosave;
			case ONECARD_PANELTY:
				return oneCard_panelty;
			case INPUT_SERIAL_AGREEMENT:
				return input_serial_agreement;
			case ACCEPT:
				return accept;
			case OK:
				return ok;
			case CLASSIC:
				return classic;
			case ONECARD:
				return oneCard;
			case MODE:
				return modes;
			case RUN:
				return runs;
			case COPY_CLIPBOARD:
				return copy_clipboard;
			case DESC_COPY_CLIPBOARD:
				return desc_copy_clipboard;
			case DESC_CALC:
				return desc_calc;
			case DESC_ONECARD:
				return desc_oneCard;
			case ANOTHER_GAMES:
				return another_games;
			case DIR_CENTER:
				return dir_center;
			case DIR_LEFT:
				return dir_left;
			case DIR_RIGHT:
				return dir_right;
			case DIR_UP:
				return dir_up;
			case DIR_DOWN:
				return dir_down;
			case BASIC_EDITION:
				return basic_edition;
			case PROFESSIONAL:
				return professional;
			case ULTIMATE:
				return ultimate;
			case MASTER:
				return master;
			case SCROLLBAR:
				return scrollBar;
			case CHANGE_CARD:
				return change_card;
			case POINT_CODE:
				return point_code;
			case CODE_CHECKER:
				return code_checker;
			case NEED_RESTART:
				return needRestart;
			case SELF:
				return self;
			case TITLE_BAR:
				return titleBar;
			case USE:
				return use;
			case HELP:
				return help;
			case DIFFICULTY:
				return difficulty;
			case VERSION:
				return version;
			case RANKING:
				return ranking;
			case AUTHORITY:
				return authority;
			case REPLAY:
				return replay;
			case EDIT:
				return edit;
			case VIEW:
				return view;
			case MAKER:
				return maker;
			case ABOUT:
				return about;
			case CARD:
				return card;
			case THEME:
				return theme;
			case ALERT:
				return alert;
			case WINDOW:
				return window;
			case SHOW:
				return show;
			case COMPLETE:
				return complete;
			case FAIL:
				return fail;
			case CREDIT:
				return credit;
			case ADD:
				return add;
			case REMOVE:
				return remove;
			case RESTART:
				return restart;
			case HOW_TO_SHOW_CARD:
				return howToShowCard;
			case PRINT_ERROR_DETAIL:
				return print_error_detail;
			case SINGLE:
				return single;
			case MULTI:
				return multi;
			case PLAY:
				return play;
			case HOST:
				return host;
			case JOIN:
				return join;
			case CONNECT:
				return connect;
			case IP:
				return ip;
			case PORT:
				return port;
			case HOSTING:
				return hosting;
			case NOTICE:
				return notice;
			case RESET:
				return reset;
			case VIRTUAL:
				return virtuals;
			case ACCUMULATED:
				return accumulated;
			case NOTICE_NOT_VIEW_AGAIN:
				return notice_not_view_again;
			case READY:
				return ready;
			case CANCEL:
				return cancel;
			case DATE:
				return date;
			case COLOR:
				return color;
			case LOG:
				return log;
			case LEFT:
				return left;
			case DETAIL:
				return detail;
			case SUMMARY:
				return summary;
			case SUMMARY_USE:
				return summary_use;
			case NOW_PLAYER_CARD:
				return now_player_card;
			case SEPARATOR:
				return separator;
			case RANDOM_ORDER:
				return randomOrder;
			case BONUS_TARGET:
				return bonus_target;
			case CONNECTING:
				return connecting;
			case DISCONNECT:
				return disconnect;
			case ON_START_CARDS:
				return on_start_cards;
			case HELP_CONTENT:
				return help_contents;
			case GAME_STOP:
				return game_stop;
			case MENU_FILE:
				return menu_file;
			case DECK_LABEL1:
				return deckLabel1;
			case DECK_LABEL2:
				return deckLabel2;
			case DECK_LABEL3:
				return deckLabel3;
			case DECK_LABEL4:
				return deckLabel4;
			case BACKGROUND:
				return background;
			case FOREGROUND:
				return foreground;
			case INSIDE_BACKGROUND:
				return inside_background;
			case PASSWORD:
				return password;
			case ACTIVE:
				return active;
			case DEACTIVE:
				return deactive;
			case USE_XML_SETTING:
				return use_xml_setting;
			case USER:
				return user;
			case NEW_USER:
				return new_user;
			case BET:
				return bet;
			case SELECT:
				return select;
			case INFORMATION:
				return information;
			case MAIN:
				return main;				
			case SHELTER_CITY:
				return shelter_city;
			case PAUSE:
				return pause;
			case PAUSE_BREAK:
				return pause_break;
			case TAX:
				return tax;
			case TOTAL_INFO:
				return total_info;
			case BUILD:
				return build;
			case RESIDENT:
				return resident;
			case MARKETPLACE:
				return marketplace;
			case POWERPLANT:
				return powerplant;
			case WAREHOUSE:
				return warehouse;
			case FARM:
				return farm;
			case CLINIC:
				return clinic;
			case ACADEMY:
				return academy;
			case COST:
				return cost;
			case POLICY:
				return policy;
			case MAKE_ACTIVE:
				return make_active;
			case MAKE_DEACTIVE:
				return make_deactive;
			case ECONOMY:
				return economy;
			case ETC:
				return etc;
			case NEW_CITY:
				return new_city;
			case CITY:
				return city;
			case BUDGET:
				return budget;
			case POPULATION:
				return population;
			case AVERAGE:
				return average;
			case INTELLIGENT:
				return intelligent;
			case STRENGTH:
				return strength;
			case MINIMUM:
				return minimum;
			case MAXIMUM:
				return maximum;
			case HP:
				return hp;
			case HAPPINESS:
				return happiness;
			case DESEASE:
				return desease;
			case DEEP:
				return deep;
			case FOOD:
				return food;
			case PRODUCTION:
				return production;
			case SIZE:
				return size;
			case SITUATION:
				return situation;
			case DESC_RESIDENT:
				return desc_resident;
			case DESC_MARKETPLACE:
				return desc_marketplace;
			case DESC_POWERPLANT:
				return desc_powerplant;
			case DESC_WAREHOUSE:
				return desc_warehouse;
			case DESC_FARM:
				return desc_farm;
			case DESC_CURE:
				return desc_cure;
			case DESC_ACADEMY:
				return desc_academy;
			case NEED_SELECT_CITY:
				return need_selectCity;
			case FULL_SLOT:
				return full_slot;
			case NEED_SELECT_SECTION:
				return need_selectSection;
			case NEED_BUDGET:
				return need_budget;
			case ON:
				return on;
			case STOP_CONTROL_CITY:
				return stop_control_city;
			case BUILD_WARN:
				return build_warn;
			default:
				return "";
		}
	}
	public void setText(int index, String strs)
	{
		String str = null;
		if(strs != null) str = new String(strs);
		else str = null;
		if(index >= DESCRIPTIONS && index < DESCRIPTIONS + description.length)
			description[index - DESCRIPTIONS] = str;
		else
		switch(index)
		{
			case GAME_READY:
				game_ready = str;
				break;
			case TODAY_GAME:
				today_game = str;
				break;
			case SHIP:
				ship = str;
				break;
			case TODAY_ITEM:
				today_item = str;
				break;
			case TODAY_WARN:
				today_warn = str;
				break;
			case CONTINUE_ON_ZERO:
				continue_on_zero = str;
				break;
			case INPUT_URL:
				input_url = str;
				break;
			case INPUT_URL2:
				input_url2 = str;
				break;
			case INPUT_REPLAY_GAP:
				input_replay_gap = str;
				break;
			case FILE_NEEDS:
				file_needs = str;
				break;
			case CONTINUES:
				continues = str;
				break;
			case SHIP_NAME:
				ship_name = str;
				break;
			case SHIP_SIZE:
				shipSize = str;
				break;
			case SHIP_HP:
				ship_startHP = str;
				break;
			case SHIP_E:
				ship_startE = str;
				break;
			case SHIP_SPEED:
				ship_startSpeed = str;
				break;
			case WEAPON:
				weapon = str;
				break;
			case DAMAGE:
				attackPower = str;
				break;
			case COOLING_TIME:
				cooltime = str;
				break;
			case RANGE:
				weapon_range = str;
				break;
			case GUIDE:
				guide_helperonly = str;
				break;
			case HORIZONTAL_INTERVAL:
				horizontal_gap = str;
				break;
			case IF_MULTIPLE:
				if_multipleShoot = str;
				break;
			case FIRE_COUNT:
				fireCount = str;
				break;
			case SPEND_E:
				spendE = str;
				break;
			case MISSILE_SIZE:
				missile_size = str;
				break;
			case KIND_INTERCEPTOR:
				kind_interceptor = str;
				break;
			case COUNT_INTERCEPTOR:
				count_interceptor = str;
				break;
			case MISSILE_SPEED:
				missile_speed = str;
				break;
			case KINDS:
				kinds = str;
				break;
			case MISSILE_KIND:
				missile_kind = str;
				break;
			case SHAPE:
				shape = str;
				break;
		
			case LICENSE:
				license = str;
				break;
			case ALLOW:
				allow = str;
				break;
			case FIRST_ADDITIONAL_DOWNLOAD:
				first_additional_download = str;
				break;
			case NOT_NOW:
				not_now = str;
				break;
			case FILE_CREATED:
				file_created = str;
				break;
			case FILE_DELETED:
				file_deleted = str;
				break;
			case WILL_DOWNLOAD_AT:
				will_download_at = str;
				break;
			case ORDER:
				order = str;
				break;
			case TITLE:
				title = str;
				break;
			case X:
				x = str;
				break;
			case CHAT:
				chat = str;
				break;
			case TO:
				to = str;
				break;
			case MAKE:
				make = str;
				break;
			case MATHCONQ:
				mathconq = str;
				break;
			case REFLEX:
				reflex = str;
				break;
			case COUNTERPARTY:
				counterparty = str;
				break;
			case MODULE:
				module = str;
				break;
			case TYPE:
				type = str;
				break;
			case BLOCK:
				block = str;
				break;
			case ONLY_BETA:
				onlyBeta = str;
				break;
			case ALL:
				all = str;
				break;
			case REFLEX_SIMPLEHELP:
				reflex_simplehelp = str;
				break;
			case REFLEX_HELP:
				reflex_help = str;
				break;
			case MATHQ_SIMPLEHELP:
				mathq_simplehelp = str;
				break;
			case REFLEX_FLEX:
				reflex_flex = str;
				break;
			case REFLEX_BERSERK:
				reflex_berserk = str;
				break;
			case REFLEX_CLIPPER:
				reflex_clipper = str;
				break;
			case REFLEX_WARSHIP:
				reflex_warship = str;
				break;
			case REFLEX_SATELLITE:
				reflex_satellite = str;
				break;
			case REFLEX_CHASER:
				reflex_chaser = str;
				break;
			case REFLEX_CARRIER:
				reflex_carrier = str;
				break;
			case PATH:
				path = str;
				break;
			case DOWNLOAD_PACK:
				download_pack = str;
				break;
			case USER_DEFINED:
				user_defined = str;
				break;
			case MATHQ_HELP:
				mathq_help = str;
				break;
			case WARN:
				warn = str;
				break;
			case WARN_SCRIPT:
				warn_script = str;
				break;
			case SCENARIO:
				scenario = str;
				break;
			case NO_SCENARIO_DESC:
				noscenario_description = str;
				break;
			case IS:
				is = str;
				break;
			case ARE:
				are = str;
				break;
			case GET:
				get = str;
				break;
			case START:
				start = str;
				break;
			case EXIT:
				exit = str;
				break;
			case CLOSE:
				close = str;
				break;
			case DELAY:
				delay = str;
				break;
			case NOTICE_NULL:
				notice_null = str;
				break;
			case CONQUER:
				conquer = str;
				break;
			case ANSWER:
				answer = str;
				break;
			case TRACKING:
				tracking = str;
				break;
			case TRACKING_AUTOSAVE:
				tracking_autosave = str;
				break;
			case ONECARD_PANELTY:
				oneCard_panelty = str;
				break;
			case AUTO_PASS:
				auto_pass = str;
				break;
			case REFRESH:
				refresh = str;
				break;
			case RESULT:
				result = str;
				break;
			case WON:
				won = str;
				break;
			case WHOS:
				whos = str;
				break;
			case POINT:
				point = str;
				break;
			case PAY:
				pay = str;
				break;
			case OWNS:
				owns = str;
				break;
			case PAIDS:
				paids = str;
				break;
			case NAME:
				name = str;
				break;
			case DOWNLOAD:
				download = str;
				break;
			case UPDATE:
				update = str;
				break;
			case EVENT:
				event = str;
				break;
			case HOMEPAGE:
				homepage = str;
				break;
			case ADDRESS:
				address = str;
				break;
			case WEB:
				web = str;
				break;
			case UNINSTALL_ASK:
				uninstall_askMessage = str;
				break;
			case UNINSTALL_COMPLETED:
				uninstall_completed = str;
				break;
			case UNINSTALL_DELETED:
				uninstall_deleted = str;
				break;
			case UNINSTALL_ERROR:
				uninstall_error = str;
				break;
			case UNINSTALL_AFTER_ASK:
				uninstall_after_askMessage = str;
				break;
			case UNINSTALL_AFTER_COMPLETED:
				uninstall_after_completed = str;
				break;
			case UNINSTALL_AFTER_DELETED:
				uninstall_after_deleted = str;
				break;
			case UNINSTALL_AFTER_ERROR:
				uninstall_after_error = str;
				break;
			case UNINSTALL:
				uninstall = str;
				break;
			case MANAGEMENT:
				management = str;
				break;
			case ALREADY_NEWEST:
				already_newest = str;
				break;
			case DOWNLOAD_FINISH:
				downloadfinish = str;
				break;
			case DOYOUWANTTOUPDATE:
				doyouwantupdate = str;
				break;
			case CHECK_RELIABILITY:
				checkreliability = str;
				break;
			case FINISH:
				finish = str;
				break;
			case SEALED:
				sealed = str;
				break;
			case NONE:
				none = str;
				break;
			case PLAYER:
				player = str;
				break;
			case AI:
				ai = str;
				break;
			case SCRIPT:
				script = str;
				break;
			case ENGINE:
				engine = str;
				break;
			case EASY:
				easy = str;
				break;
			case NORMAL:
				normal = str;
				break;
			case HARD:
				hard = str;
				break;
			case ADD:
				add = str;
				break;
			case REMOVE:
				remove = str;
				break;
			case ERROR:
				error = str;
				break;
			case TIME:
				time = str;
				break;
			case YEAR:
				year = str;
				break;
			case MONTH:
				month = str;
				break;
			case DAY:
				day = str;
				break;
			case HOUR:
				hour = str;
				break;
			case MINUTE:
				minute = str;
				break;
			case ACCEPT:
				accept = str;
				break;
			case OK:
				ok = str;
				break;
			case RESTART:
				restart = str;
				break;
			case ONECARD:
				oneCard = str;
				break;
			case MODE:
				modes = str;
				break;
			case RUN:
				runs = str;
				break;
			case COPY_CLIPBOARD:
				copy_clipboard = str;
				break;
			case DESC_COPY_CLIPBOARD:
				desc_copy_clipboard = str;
				break;
			case DESC_CALC:
				desc_calc = str;
				break;
			case DESC_ONECARD:
				desc_oneCard = str;
				break;
			case INPUT_SERIAL_AGREEMENT:
				input_serial_agreement = str;
				break;
			case ANOTHER_GAMES:
				another_games = str;
				break;
			case ACCUMULATED:
				accumulated = str;
				break;
			case ULTIMATE:
				ultimate = str;
				break;
			case MASTER:
				master = str;
				break;
			case BASIC_EDITION:
				basic_edition = str;
				break;
			case PROFESSIONAL:
				professional = str;
				break;
			case SCROLLBAR:
				scrollBar = str;
				break;
			case PASSWORD:
				password = str;
				break;
			case CHANGE_CARD:
				change_card = str;
				break;
			case GAME_STOP:
				game_stop = str;
				break;
			case POINT_CODE:
				point_code = str;
				break;
			case CODE_CHECKER:
				code_checker = str;
				break;
			case WIDTH:
				width = str;
				break;
			case HEIGHT:
				height = str;
				break;
			case SAVE:
				save = str;
				break;
			case LOAD:
				load = str;
				break;
			case RED:
				red = str;
				break;
			case GREEN:
				green = str;
				break;
			case BLUE:
				blue = str;
				break;
			case LEFT:
				left = str;
				break;
			case CREDIT:
				credit = str;
				break;
			case SUMMARY:
				summary = str;
				break;
			case CHECK:
				check = str;
				break;
			case MENU:
				menu = str;
				break;
			case INPUT:
				input = str;
				break;
			case IN_BAR:
				in_bar = str;
				break;
			case CLASSIC:
				classic = str;
				break;
			case CATCH:
				catchs = str;
				break;
			case ENEMY:
				enemy = str;
				break;
			case ITEM:
				item = str;
				break;
			case SUMMARY_USE:
				summary_use = str;
				break;
			case NOW_PLAYER_CARD:
				now_player_card = str;
				break;
			case RESET:
				reset = str;
				break;
			case DETAIL:
				detail = str;
				break;
			case SETTING:
				setting = str;
				break;
			case VERSION:
				version = str;
				break;
			case COUNT:
				count = str;
				break;
			case MAX:
				max = str;
				break;
			case SELF:
				self = str;
				break;
			case TITLE_BAR:
				titleBar = str;
				break;
			case USE:
				use = str;
				break;
			case HELP:
				help = str;
				break;
			case COLOR:
				color = str;
				break;
			case RANKING:
				ranking = str;
				break;
			case AUTHORITY:
				authority = str;
				break;
			case REPLAY:
				replay = str;
				break;
			case EDIT:
				edit = str;
				break;
			case VIEW:
				view = str;
				break;
			case MAKER:
				maker = str;
				break;
			case ABOUT:
				about = str;
				break;
			case CARD:
				card = str;
				break;
			case THEME:
				theme = str;
				break;
			case ALERT:
				alert = str;
				break;
			case WINDOW:
				window = str;
				break;
			case SHOW:
				show = str;
				break;
			case COMPLETE:
				complete = str;
				break;
			case FAIL:
				fail = str;
				break;
			case SEPARATOR:
				separator = str;
				break;
			case NOTICE:
				notice = str;
				break;
			case VIRTUAL:
				virtuals = str;
				break;
			case NOTICE_NOT_VIEW_AGAIN:
				notice_not_view_again = str;
				break;
			case HOW_TO_SHOW_CARD:
				howToShowCard = str;
				break;
			case PRINT_ERROR_DETAIL:
				print_error_detail = str;
				break;
			case SINGLE:
				single = str;
				break;
			case MULTI:
				multi = str;
				break;
			case PLAY:
				play = str;
				break;
			case HOST:
				host = str;
				break;
			case JOIN:
				join = str;
				break;
			case CONNECT:
				connect = str;
				break;
			case IP:
				ip = str;
				break;
			case PORT:
				port = str;
				break;
			case HOSTING:
				hosting = str;
				break;
			case READY:
				ready = str;
				break;
			case CANCEL:
				cancel = str;
				break;
			case DATE:
				date = str;
				break;
			case LOG:
				log = str;
				break;
			case DIR_CENTER:
				dir_center = str;
				break;
			case DIR_DOWN:
				dir_down = str;
				break;
			case DIR_UP:
				dir_up = str;
				break;
			case DIR_LEFT:
				dir_left = str;
				break;
			case DIR_RIGHT:
				dir_right = str;
				break;
			case RANDOM_ORDER:
				randomOrder = str;
				break;
			case DIFFICULTY:
				difficulty = str;
				break;
			case BONUS_TARGET:
				bonus_target = str;
				break;
			case CONNECTING:
				connecting = str;
				break;
			case DISCONNECT:
				disconnect = str;
				break;
			case ON_START_CARDS:
				on_start_cards = str;
				break;
			case HELP_CONTENT:
				help_contents = str;
				break;
			case NEED_RESTART:
				needRestart = str;
				break;
			case MENU_FILE:
				menu_file = str;
				break;
			case DECK_LABEL1:
				deckLabel1 = str;
				break;
			case DECK_LABEL2:
				deckLabel2 = str;
				break;
			case DECK_LABEL3:
				deckLabel3 = str;
				break;
			case DECK_LABEL4:
				deckLabel4 = str;
				break;
			case BACKGROUND:
				background = str;
				break;
			case FOREGROUND:
				foreground = str;
				break;
			case INSIDE_BACKGROUND:
				inside_background = str;
				break;
			case ACTIVE:
				active = str;
				break;
			case DEACTIVE:
				deactive = str;
				break;
			case USE_XML_SETTING:
				use_xml_setting = str;
				break;
			case USER:
				user = str;
				break;
			case NEW_USER:
				new_user = str;
				break;
			case BET:
				bet = str;
				break;
			case SELECT:
				select = str;
				break;
			case INFORMATION:
				information = str;
				break;
			case MAIN:
				main = str;
				break;
			case SHELTER_CITY:
				shelter_city = str; 
				break;
			case PAUSE:
				pause = str; 
				break;
			case PAUSE_BREAK:
				pause_break = str; 
				break;
			case TAX:
				tax = str; 
				break;
			case TOTAL_INFO:
				total_info = str; 
				break;
			case BUILD:
				build = str; 
				break;
			case RESIDENT:
				resident = str; 
				break;
			case MARKETPLACE:
				marketplace = str; 
				break;
			case POWERPLANT:
				powerplant = str; 
				break;
			case WAREHOUSE:
				warehouse = str; 
				break;
			case FARM:
				farm = str; 
				break;
			case CLINIC:
				clinic = str; 
				break;
			case ACADEMY:
				academy = str; 
				break;
			case COST:
				cost = str; 
				break;
			case POLICY:
				policy = str; 
				break;
			case MAKE_ACTIVE:
				make_active = str; 
				break;
			case MAKE_DEACTIVE:
				make_deactive = str; 
				break;
			case ECONOMY:
				economy = str; 
				break;
			case ETC:
				etc = str; 
				break;
			case NEW_CITY:
				new_city = str; 
				break;
			case CITY:
				city = str; 
				break;
			case BUDGET:
				budget = str; 
				break;
			case POPULATION:
				population = str; 
				break;
			case AVERAGE:
				average = str; 
				break;
			case INTELLIGENT:
				intelligent = str; 
				break;
			case STRENGTH:
				strength = str; 
				break;
			case MINIMUM:
				minimum = str; 
				break;
			case MAXIMUM:
				maximum = str; 
				break;
			case HP:
				hp = str; 
				break;
			case HAPPINESS:
				happiness = str; 
				break;
			case DESEASE:
				desease = str; 
				break;
			case DEEP:
				deep = str; 
				break;
			case FOOD:
				food = str; 
				break;
			case PRODUCTION:
				production = str; 
				break;
			case SIZE:
				size = str; 
				break;
			case SITUATION:
				situation = str; 
				break;
			case DESC_RESIDENT:
				desc_resident = str;
				break;
			case DESC_MARKETPLACE:
				desc_marketplace = str; 
				break;
			case DESC_POWERPLANT:
				desc_powerplant = str; 
				break;
			case DESC_WAREHOUSE:
				desc_warehouse = str; 
				break;
			case DESC_FARM:
				desc_farm = str; 
				break;
			case DESC_CURE:
				desc_cure = str; 
				break;
			case DESC_ACADEMY:
				desc_academy = str; 
				break;
			case NEED_SELECT_CITY:
				need_selectCity = str; 
				break;
			case FULL_SLOT:
				full_slot = str; 
				break;
			case NEED_SELECT_SECTION:
				need_selectSection = str; 
				break;
			case NEED_BUDGET:
				need_budget = str; 
				break;
			case ON:
				on = str; 
				break;
			case STOP_CONTROL_CITY:
				stop_control_city = str;
				break;
			case BUILD_WARN:
				build_warn = str;
				break;
			case VIEW_ON_WEB:
				view_on_web = str;
				break;
		}
	}
	public int[] getList()
	{
		int normal = 297;
		int[] newOne = new int[normal + description.length + 1];
		
		for(int i=0; i<=normal; i++)
		{
			newOne[i] = i;
		}
		for(int i=normal + 1; i<newOne.length; i++)
		{
			newOne[i] = DESCRIPTIONS + (i - normal - 1);
		}
		return newOne;
	}
	public Language clone()
	{
		Language newOne;
		switch (this.getType())
		{
			case LANG_ENGLISH :
				newOne = new English();
				break;
			case LANG_KOREAN:
				newOne = new Korean();
				break;
			default :
				newOne = new English();
				break;
		}
		for(int i=0; i<getList().length; i++)
		{
			newOne.setText(this.getList()[i], this.getText(this.getList()[i]));
		}
		newOne.help_contents = new String(this.help_contents);
		return newOne;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getX()
	{
		return x;
	}

	public void setX(String x)
	{
		this.x = x;
	}

	public String getIs()
	{
		return is;
	}

	public void setIs(String is)
	{
		this.is = is;
	}

	public String getAre()
	{
		return are;
	}

	public void setAre(String are)
	{
		this.are = are;
	}

	public String getDeckLabel1()
	{
		return deckLabel1;
	}

	public void setDeckLabel1(String deckLabel1)
	{
		this.deckLabel1 = deckLabel1;
	}

	public String getDeckLabel2()
	{
		return deckLabel2;
	}

	public void setDeckLabel2(String deckLabel2)
	{
		this.deckLabel2 = deckLabel2;
	}

	public String getDeckLabel3()
	{
		return deckLabel3;
	}

	public void setDeckLabel3(String deckLabel3)
	{
		this.deckLabel3 = deckLabel3;
	}

	public String getDeckLabel4()
	{
		return deckLabel4;
	}

	public void setDeckLabel4(String deckLabel4)
	{
		this.deckLabel4 = deckLabel4;
	}

	public String getGet()
	{
		return get;
	}

	public void setGet(String get)
	{
		this.get = get;
	}

	public String getStart()
	{
		return start;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getExit()
	{
		return exit;
	}

	public void setExit(String exit)
	{
		this.exit = exit;
	}

	public String getClose()
	{
		return close;
	}

	public void setClose(String close)
	{
		this.close = close;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getWon()
	{
		return won;
	}

	public void setWon(String won)
	{
		this.won = won;
	}

	public String getWhos()
	{
		return whos;
	}

	public void setWhos(String whos)
	{
		this.whos = whos;
	}

	public String getPoint()
	{
		return point;
	}

	public void setPoint(String point)
	{
		this.point = point;
	}

	public String getPay()
	{
		return pay;
	}

	public void setPay(String pay)
	{
		this.pay = pay;
	}

	public String getOwns()
	{
		return owns;
	}

	public void setOwns(String owns)
	{
		this.owns = owns;
	}

	public String getPaids()
	{
		return paids;
	}

	public void setPaids(String paids)
	{
		this.paids = paids;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFinish()
	{
		return finish;
	}

	public void setFinish(String finish)
	{
		this.finish = finish;
	}

	public String getSealed()
	{
		return sealed;
	}

	public void setSealed(String sealed)
	{
		this.sealed = sealed;
	}

	public String getNone()
	{
		return none;
	}

	public void setNone(String none)
	{
		this.none = none;
	}

	public String getPlayer()
	{
		return player;
	}

	public void setPlayer(String player)
	{
		this.player = player;
	}

	public String getAi()
	{
		return ai;
	}

	public void setAi(String ai)
	{
		this.ai = ai;
	}

	public String getMenu_file()
	{
		return menu_file;
	}

	public void setMenu_file(String menu_file)
	{
		this.menu_file = menu_file;
	}

	public String getGame_stop()
	{
		return game_stop;
	}

	public void setGame_stop(String game_stop)
	{
		this.game_stop = game_stop;
	}

	public String getBackground()
	{
		return background;
	}

	public void setBackground(String background)
	{
		this.background = background;
	}

	public String getForeground()
	{
		return foreground;
	}

	public void setForeground(String foreground)
	{
		this.foreground = foreground;
	}

	public String getInside_background()
	{
		return inside_background;
	}

	public void setInside_background(String inside_background)
	{
		this.inside_background = inside_background;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}

	public String getDeactive()
	{
		return deactive;
	}

	public void setDeactive(String deactive)
	{
		this.deactive = deactive;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	public String getSave()
	{
		return save;
	}

	public void setSave(String save)
	{
		this.save = save;
	}

	public String getLoad()
	{
		return load;
	}

	public void setLoad(String load)
	{
		this.load = load;
	}

	public String getRed()
	{
		return red;
	}

	public void setRed(String red)
	{
		this.red = red;
	}

	public String getGreen()
	{
		return green;
	}

	public void setGreen(String green)
	{
		this.green = green;
	}

	public String getBlue()
	{
		return blue;
	}

	public void setBlue(String blue)
	{
		this.blue = blue;
	}

	public String getSetting()
	{
		return setting;
	}

	public void setSetting(String setting)
	{
		this.setting = setting;
	}

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	public String getMax()
	{
		return max;
	}

	public void setMax(String max)
	{
		this.max = max;
	}

	public String getNeedRestart()
	{
		return needRestart;
	}

	public void setNeedRestart(String needRestart)
	{
		this.needRestart = needRestart;
	}

	public String getSelf()
	{
		return self;
	}

	public void setSelf(String self)
	{
		this.self = self;
	}

	public String getTitleBar()
	{
		return titleBar;
	}

	public void setTitleBar(String titleBar)
	{
		this.titleBar = titleBar;
	}

	public String getUse()
	{
		return use;
	}

	public void setUse(String use)
	{
		this.use = use;
	}

	public String getHelp_contents()
	{
		return help_contents;
	}

	public void setHelp_contents(String help_contents)
	{
		this.help_contents = help_contents;
	}

	public String getHelp()
	{
		return help;
	}

	public void setHelp(String help)
	{
		this.help = help;
	}

	public String getOn_start_cards()
	{
		return on_start_cards;
	}

	public void setOn_start_cards(String on_start_cards)
	{
		this.on_start_cards = on_start_cards;
	}

	public String getAuthority()
	{
		return authority;
	}

	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	public String getReplay()
	{
		return replay;
	}

	public void setReplay(String replay)
	{
		this.replay = replay;
	}

	public String getEdit()
	{
		return edit;
	}

	public void setEdit(String edit)
	{
		this.edit = edit;
	}

	public String getView()
	{
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}

	public String getMaker()
	{
		return maker;
	}

	public void setMaker(String maker)
	{
		this.maker = maker;
	}

	public String getAbout()
	{
		return about;
	}

	public void setAbout(String about)
	{
		this.about = about;
	}

	public String getCard()
	{
		return card;
	}

	public void setCard(String card)
	{
		this.card = card;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getAlert()
	{
		return alert;
	}

	public void setAlert(String alert)
	{
		this.alert = alert;
	}

	public String getWindow()
	{
		return window;
	}

	public void setWindow(String window)
	{
		this.window = window;
	}

	public String getShow()
	{
		return show;
	}

	public void setShow(String show)
	{
		this.show = show;
	}

	public String getComplete()
	{
		return complete;
	}

	public void setComplete(String complete)
	{
		this.complete = complete;
	}

	public String getFail()
	{
		return fail;
	}

	public void setFail(String fail)
	{
		this.fail = fail;
	}

	public String getSingle()
	{
		return single;
	}

	public void setSingle(String single)
	{
		this.single = single;
	}

	public String getMulti()
	{
		return multi;
	}

	public void setMulti(String multi)
	{
		this.multi = multi;
	}

	public String getPlay()
	{
		return play;
	}

	public void setPlay(String play)
	{
		this.play = play;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getJoin()
	{
		return join;
	}

	public void setJoin(String join)
	{
		this.join = join;
	}

	public String getConnect()
	{
		return connect;
	}

	public void setConnect(String connect)
	{
		this.connect = connect;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}

	public String getDisconnect()
	{
		return disconnect;
	}

	public void setDisconnect(String disconnect)
	{
		this.disconnect = disconnect;
	}

	public String getHosting()
	{
		return hosting;
	}

	public void setHosting(String hosting)
	{
		this.hosting = hosting;
	}

	public String getConnecting()
	{
		return connecting;
	}

	public void setConnecting(String connecting)
	{
		this.connecting = connecting;
	}

	public String getReady()
	{
		return ready;
	}

	public void setReady(String ready)
	{
		this.ready = ready;
	}

	public String getCancel()
	{
		return cancel;
	}

	public void setCancel(String cancel)
	{
		this.cancel = cancel;
	}

	public String getRanking()
	{
		return ranking;
	}

	public void setRanking(String ranking)
	{
		this.ranking = ranking;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getBonus_target()
	{
		return bonus_target;
	}

	public void setBonus_target(String bonus_target)
	{
		this.bonus_target = bonus_target;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getDifficulty()
	{
		return difficulty;
	}

	public void setDifficulty(String difficulty)
	{
		this.difficulty = difficulty;
	}

	public String getRandomOrder()
	{
		return randomOrder;
	}

	public void setRandomOrder(String randomOrder)
	{
		this.randomOrder = randomOrder;
	}

	public String getPrint_error_detail()
	{
		return print_error_detail;
	}

	public void setPrint_error_detail(String print_error_detail)
	{
		this.print_error_detail = print_error_detail;
	}

	public String getSeparator()
	{
		return separator;
	}

	public void setSeparator(String separator)
	{
		this.separator = separator;
	}

	public String getHowToShowCard()
	{
		return howToShowCard;
	}

	public void setHowToShowCard(String howToShowCard)
	{
		this.howToShowCard = howToShowCard;
	}

	public String getLog()
	{
		return log;
	}

	public void setLog(String log)
	{
		this.log = log;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getSummary_use()
	{
		return summary_use;
	}

	public void setSummary_use(String summary_use)
	{
		this.summary_use = summary_use;
	}

	public String getNotice()
	{
		return notice;
	}

	public void setNotice(String notice)
	{
		this.notice = notice;
	}

	public String getNotice_not_view_again()
	{
		return notice_not_view_again;
	}

	public void setNotice_not_view_again(String notice_not_view_again)
	{
		this.notice_not_view_again = notice_not_view_again;
	}

	public String getUse_xml_setting()
	{
		return use_xml_setting;
	}

	public void setUse_xml_setting(String use_xml_setting)
	{
		this.use_xml_setting = use_xml_setting;
	}

	public String[] getDescription()
	{
		return description;
	}

	public void setDescription(String[] description)
	{
		this.description = description;
	}

	public String getReset()
	{
		return reset;
	}

	public void setReset(String reset)
	{
		this.reset = reset;
	}

	public String getNow_player_card()
	{
		return now_player_card;
	}

	public void setNow_player_card(String now_player_card)
	{
		this.now_player_card = now_player_card;
	}

	public String getLeft()
	{
		return left;
	}

	public void setLeft(String left)
	{
		this.left = left;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getNew_user()
	{
		return new_user;
	}

	public void setNew_user(String new_user)
	{
		this.new_user = new_user;
	}

	public String getBet()
	{
		return bet;
	}

	public void setBet(String bet)
	{
		this.bet = bet;
	}

	public String getSelect()
	{
		return select;
	}

	public void setSelect(String select)
	{
		this.select = select;
	}

	public String getCredit()
	{
		return credit;
	}

	public void setCredit(String credit)
	{
		this.credit = credit;
	}

	public String getAdd()
	{
		return add;
	}

	public void setAdd(String add)
	{
		this.add = add;
	}

	public String getRemove()
	{
		return remove;
	}

	public void setRemove(String remove)
	{
		this.remove = remove;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getCheck()
	{
		return check;
	}

	public void setCheck(String check)
	{
		this.check = check;
	}

	public String getMenu()
	{
		return menu;
	}

	public void setMenu(String menu)
	{
		this.menu = menu;
	}

	public String getInput()
	{
		return input;
	}

	public void setInput(String input)
	{
		this.input = input;
	}

	public String getPoint_code()
	{
		return point_code;
	}

	public void setPoint_code(String point_code)
	{
		this.point_code = point_code;
	}

	public String getCode_checker()
	{
		return code_checker;
	}

	public void setCode_checker(String code_checker)
	{
		this.code_checker = code_checker;
	}

	public String getVirtuals()
	{
		return virtuals;
	}

	public void setVirtuals(String virtuals)
	{
		this.virtuals = virtuals;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
	}

	public String getHour()
	{
		return hour;
	}

	public void setHour(String hour)
	{
		this.hour = hour;
	}

	public String getMinute()
	{
		return minute;
	}

	public void setMinute(String minute)
	{
		this.minute = minute;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getInformation()
	{
		return information;
	}

	public void setInformation(String information)
	{
		this.information = information;
	}

	public String getMain()
	{
		return main;
	}

	public void setMain(String main)
	{
		this.main = main;
	}

	public String getChange_card()
	{
		return change_card;
	}

	public void setChange_card(String change_card)
	{
		this.change_card = change_card;
	}

	public String getScrollBar()
	{
		return scrollBar;
	}

	public void setScrollBar(String scrollBar)
	{
		this.scrollBar = scrollBar;
	}

	public String getIn_bar()
	{
		return in_bar;
	}

	public void setIn_bar(String in_bar)
	{
		this.in_bar = in_bar;
	}

	public String getBasic_edition()
	{
		return basic_edition;
	}

	public void setBasic_edition(String basic_edition)
	{
		this.basic_edition = basic_edition;
	}

	public String getProfessional()
	{
		return professional;
	}

	public void setProfessional(String professional)
	{
		this.professional = professional;
	}

	public String getOk()
	{
		return ok;
	}

	public void setOk(String ok)
	{
		this.ok = ok;
	}

	public String getAccept()
	{
		return accept;
	}

	public void setAccept(String accept)
	{
		this.accept = accept;
	}

	public String getUltimate()
	{
		return ultimate;
	}

	public void setUltimate(String ultimate)
	{
		this.ultimate = ultimate;
	}

	public String getRestart()
	{
		return restart;
	}

	public void setRestart(String restart)
	{
		this.restart = restart;
	}

	public String getClassic()
	{
		return classic;
	}

	public void setClassic(String classic)
	{
		this.classic = classic;
	}

	public String getBasic_charset()
	{
		return basic_charset;
	}

	public void setBasic_charset(String basic_charset)
	{
		this.basic_charset = basic_charset;
	}

	public String getOneCard()
	{
		return oneCard;
	}

	public void setOneCard(String oneCard)
	{
		this.oneCard = oneCard;
	}

	public String getModes()
	{
		return modes;
	}

	public void setModes(String modes)
	{
		this.modes = modes;
	}
	public String getDir_left()
	{
		return dir_left;
	}

	public void setDir_left(String dir_left)
	{
		this.dir_left = dir_left;
	}

	public String getDir_right()
	{
		return dir_right;
	}

	public void setDir_right(String dir_right)
	{
		this.dir_right = dir_right;
	}

	public String getDir_down()
	{
		return dir_down;
	}

	public void setDir_down(String dir_down)
	{
		this.dir_down = dir_down;
	}

	public String getDir_up()
	{
		return dir_up;
	}

	public void setDir_up(String dir_up)
	{
		this.dir_up = dir_up;
	}

	public String getDir_center()
	{
		return dir_center;
	}

	public void setDir_center(String dir_center)
	{
		this.dir_center = dir_center;
	}

	public String getAccumulated()
	{
		return accumulated;
	}

	public void setAccumulated(String accumulated)
	{
		this.accumulated = accumulated;
	}

	public String getAnother_games()
	{
		return another_games;
	}

	public void setAnother_games(String another_games)
	{
		this.another_games = another_games;
	}

	public String getRuns()
	{
		return runs;
	}

	public void setRuns(String runs)
	{
		this.runs = runs;
	}

	public String getDesc_calc()
	{
		return desc_calc;
	}

	public void setDesc_calc(String desc_calc)
	{
		this.desc_calc = desc_calc;
	}

	public String getDesc_oneCard()
	{
		return desc_oneCard;
	}

	public void setDesc_oneCard(String desc_oneCard)
	{
		this.desc_oneCard = desc_oneCard;
	}

	public String getOneCard_panelty()
	{
		return oneCard_panelty;
	}

	public void setOneCard_panelty(String oneCard_panelty)
	{
		this.oneCard_panelty = oneCard_panelty;
	}

	public String getInput_serial_agreement()
	{
		return input_serial_agreement;
	}

	public void setInput_serial_agreement(String input_serial_agreement)
	{
		this.input_serial_agreement = input_serial_agreement;
	}

	public String getCopy_clipboard()
	{
		return copy_clipboard;
	}

	public void setCopy_clipboard(String copy_clipboard)
	{
		this.copy_clipboard = copy_clipboard;
	}
	public String getDesc_copy_clipboard()
	{
		return desc_copy_clipboard;
	}

	public void setDesc_copy_clipboard(String desc_copy_clipboard)
	{
		this.desc_copy_clipboard = desc_copy_clipboard;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getTracking()
	{
		return tracking;
	}

	public void setTracking(String tracking)
	{
		this.tracking = tracking;
	}

	public String getTracking_autosave()
	{
		return tracking_autosave;
	}

	public void setTracking_autosave(String tracking_autosave)
	{
		this.tracking_autosave = tracking_autosave;
	}

	public String getDelay()
	{
		return delay;
	}

	public void setDelay(String delay)
	{
		this.delay = delay;
	}

	public String getNotice_null()
	{
		return notice_null;
	}

	public void setNotice_null(String notice_null)
	{
		this.notice_null = notice_null;
	}

	public String getRefresh()
	{
		return refresh;
	}

	public void setRefresh(String refresh)
	{
		this.refresh = refresh;
	}

	public String getAuto_pass()
	{
		return auto_pass;
	}

	public void setAuto_pass(String auto_pass)
	{
		this.auto_pass = auto_pass;
	}

	public String getConquer()
	{
		return conquer;
	}

	public void setConquer(String conquer)
	{
		this.conquer = conquer;
	}

	public String getEasy()
	{
		return easy;
	}

	public void setEasy(String easy)
	{
		this.easy = easy;
	}

	public String getNormal()
	{
		return normal;
	}

	public void setNormal(String normal)
	{
		this.normal = normal;
	}

	public String getHard()
	{
		return hard;
	}

	public void setHard(String hard)
	{
		this.hard = hard;
	}

	public String getScript()
	{
		return script;
	}

	public void setScript(String script)
	{
		this.script = script;
	}

	public String getEngine()
	{
		return engine;
	}

	public void setEngine(String engine)
	{
		this.engine = engine;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(String module)
	{
		this.module = module;
	}

	public String getBlock()
	{
		return block;
	}

	public void setBlock(String block)
	{
		this.block = block;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getWarn()
	{
		return warn;
	}

	public void setWarn(String warn)
	{
		this.warn = warn;
	}

	public String getWarn_script()
	{
		return warn_script;
	}

	public void setWarn_script(String warn_script)
	{
		this.warn_script = warn_script;
	}

	public String getScenario()
	{
		return scenario;
	}

	public void setScenario(String scenario)
	{
		this.scenario = scenario;
	}

	public String getNoscenario_description()
	{
		return noscenario_description;
	}

	public void setNoscenario_description(String noscenario_description)
	{
		this.noscenario_description = noscenario_description;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getAll()
	{
		return all;
	}

	public void setAll(String all)
	{
		this.all = all;
	}

	public String getChat()
	{
		return chat;
	}

	public void setChat(String chat)
	{
		this.chat = chat;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getCounterparty()
	{
		return counterparty;
	}

	public void setCounterparty(String counterparty)
	{
		this.counterparty = counterparty;
	}

	public String getOnlyBeta()
	{
		return onlyBeta;
	}

	public void setOnlyBeta(String onlyBeta)
	{
		this.onlyBeta = onlyBeta;
	}

	public String getDownload()
	{
		return download;
	}

	public void setDownload(String download)
	{
		this.download = download;
	}

	public String getUpdate()
	{
		return update;
	}

	public void setUpdate(String update)
	{
		this.update = update;
	}

	public String getAlready_newest()
	{
		return already_newest;
	}

	public void setAlready_newest(String already_newest)
	{
		this.already_newest = already_newest;
	}

	public String getDoyouwantupdate()
	{
		return doyouwantupdate;
	}

	public void setDoyouwantupdate(String doyouwantupdate)
	{
		this.doyouwantupdate = doyouwantupdate;
	}

	public String getDownloadfinish()
	{
		return downloadfinish;
	}

	public void setDownloadfinish(String downloadfinish)
	{
		this.downloadfinish = downloadfinish;
	}

	public static int getDownloadFinish()
	{
		return DOWNLOAD_FINISH;
	}

	public String getCheckreliability()
	{
		return checkreliability;
	}

	public void setCheckreliability(String checkreliability)
	{
		this.checkreliability = checkreliability;
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getWeb()
	{
		return web;
	}

	public void setWeb(String web)
	{
		this.web = web;
	}

	public String getUninstall_askMessage()
	{
		return uninstall_askMessage;
	}

	public void setUninstall_askMessage(String uninstall_askMessage)
	{
		this.uninstall_askMessage = uninstall_askMessage;
	}

	public String getUninstall_deleted()
	{
		return uninstall_deleted;
	}

	public void setUninstall_deleted(String uninstall_deleted)
	{
		this.uninstall_deleted = uninstall_deleted;
	}

	public String getUninstall_completed()
	{
		return uninstall_completed;
	}

	public void setUninstall_completed(String uninstall_completed)
	{
		this.uninstall_completed = uninstall_completed;
	}

	public String getUninstall_error()
	{
		return uninstall_error;
	}

	public void setUninstall_error(String uninstall_error)
	{
		this.uninstall_error = uninstall_error;
	}

	public String getUninstall_after_askMessage()
	{
		return uninstall_after_askMessage;
	}

	public void setUninstall_after_askMessage(String uninstall_after_askMessage)
	{
		this.uninstall_after_askMessage = uninstall_after_askMessage;
	}

	public String getUninstall_after_deleted()
	{
		return uninstall_after_deleted;
	}

	public void setUninstall_after_deleted(String uninstall_after_deleted)
	{
		this.uninstall_after_deleted = uninstall_after_deleted;
	}

	public String getUninstall_after_completed()
	{
		return uninstall_after_completed;
	}

	public void setUninstall_after_completed(String uninstall_after_completed)
	{
		this.uninstall_after_completed = uninstall_after_completed;
	}

	public String getUninstall_after_error()
	{
		return uninstall_after_error;
	}

	public void setUninstall_after_error(String uninstall_after_error)
	{
		this.uninstall_after_error = uninstall_after_error;
	}

	public String getManagement()
	{
		return management;
	}

	public void setManagement(String management)
	{
		this.management = management;
	}

	public String getUninstall()
	{
		return uninstall;
	}

	public void setUninstall(String uninstall)
	{
		this.uninstall = uninstall;
	}

	public String getMaster()
	{
		return master;
	}

	public void setMaster(String master)
	{
		this.master = master;
	}

	public String getMake()
	{
		return make;
	}

	public void setMake(String make)
	{
		this.make = make;
	}

	public String getMathconq()
	{
		return mathconq;
	}

	public void setMathconq(String mathconq)
	{
		this.mathconq = mathconq;
	}

	public String getReflex()
	{
		return reflex;
	}

	public void setReflex(String reflex)
	{
		this.reflex = reflex;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	public String getMathq_simplehelp()
	{
		return mathq_simplehelp;
	}

	public void setMathq_simplehelp(String mathq_simplehelp)
	{
		this.mathq_simplehelp = mathq_simplehelp;
	}

	public String getMathq_help()
	{
		return mathq_help;
	}

	public void setMathq_help(String mathq_help)
	{
		this.mathq_help = mathq_help;
	}

	public String getShelter_city()
	{
		return shelter_city;
	}

	public void setShelter_city(String shelter_city)
	{
		this.shelter_city = shelter_city;
	}

	public String getPause()
	{
		return pause;
	}

	public void setPause(String pause)
	{
		this.pause = pause;
	}

	public String getPause_break()
	{
		return pause_break;
	}

	public void setPause_break(String pause_break)
	{
		this.pause_break = pause_break;
	}

	public String getTax()
	{
		return tax;
	}

	public void setTax(String tax)
	{
		this.tax = tax;
	}

	public String getTotal_info()
	{
		return total_info;
	}

	public void setTotal_info(String total_info)
	{
		this.total_info = total_info;
	}

	public String getBuild()
	{
		return build;
	}

	public void setBuild(String build)
	{
		this.build = build;
	}

	public String getResident()
	{
		return resident;
	}

	public void setResident(String resident)
	{
		this.resident = resident;
	}

	public String getMarketplace()
	{
		return marketplace;
	}

	public void setMarketplace(String marketplace)
	{
		this.marketplace = marketplace;
	}

	public String getPowerplant()
	{
		return powerplant;
	}

	public void setPowerplant(String powerplant)
	{
		this.powerplant = powerplant;
	}

	public String getWarehouse()
	{
		return warehouse;
	}

	public void setWarehouse(String warehouse)
	{
		this.warehouse = warehouse;
	}

	public String getFarm()
	{
		return farm;
	}

	public void setFarm(String farm)
	{
		this.farm = farm;
	}

	public String getClinic()
	{
		return clinic;
	}

	public void setClinic(String clinic)
	{
		this.clinic = clinic;
	}

	public String getAcademy()
	{
		return academy;
	}

	public void setAcademy(String academy)
	{
		this.academy = academy;
	}

	public String getCost()
	{
		return cost;
	}

	public void setCost(String cost)
	{
		this.cost = cost;
	}

	public String getPolicy()
	{
		return policy;
	}

	public void setPolicy(String policy)
	{
		this.policy = policy;
	}

	public String getMake_active()
	{
		return make_active;
	}

	public void setMake_active(String make_active)
	{
		this.make_active = make_active;
	}

	public String getEconomy()
	{
		return economy;
	}

	public void setEconomy(String economy)
	{
		this.economy = economy;
	}

	public String getEtc()
	{
		return etc;
	}

	public void setEtc(String etc)
	{
		this.etc = etc;
	}

	public String getNew_city()
	{
		return new_city;
	}

	public void setNew_city(String new_city)
	{
		this.new_city = new_city;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getBudget()
	{
		return budget;
	}

	public void setBudget(String budget)
	{
		this.budget = budget;
	}

	public String getPopulation()
	{
		return population;
	}

	public void setPopulation(String population)
	{
		this.population = population;
	}

	public String getAverage()
	{
		return average;
	}

	public void setAverage(String average)
	{
		this.average = average;
	}

	public String getIntelligent()
	{
		return intelligent;
	}

	public void setIntelligent(String intelligent)
	{
		this.intelligent = intelligent;
	}

	public String getStrength()
	{
		return strength;
	}

	public void setStrength(String strength)
	{
		this.strength = strength;
	}

	public String getMinimum()
	{
		return minimum;
	}

	public void setMinimum(String minimum)
	{
		this.minimum = minimum;
	}

	public String getMaximum()
	{
		return maximum;
	}

	public void setMaximum(String maximum)
	{
		this.maximum = maximum;
	}

	public String getHp()
	{
		return hp;
	}

	public void setHp(String hp)
	{
		this.hp = hp;
	}

	public String getHappiness()
	{
		return happiness;
	}

	public void setHappiness(String happiness)
	{
		this.happiness = happiness;
	}

	public String getDesease()
	{
		return desease;
	}

	public void setDesease(String desease)
	{
		this.desease = desease;
	}

	public String getDeep()
	{
		return deep;
	}

	public void setDeep(String deep)
	{
		this.deep = deep;
	}

	public String getFood()
	{
		return food;
	}

	public void setFood(String food)
	{
		this.food = food;
	}

	public String getProduction()
	{
		return production;
	}

	public void setProduction(String production)
	{
		this.production = production;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getSituation()
	{
		return situation;
	}

	public void setSituation(String situation)
	{
		this.situation = situation;
	}

	public String getDesc_resident()
	{
		return desc_resident;
	}

	public void setDesc_resident(String desc_resident)
	{
		this.desc_resident = desc_resident;
	}

	public String getDesc_marketplace()
	{
		return desc_marketplace;
	}

	public void setDesc_marketplace(String desc_marketplace)
	{
		this.desc_marketplace = desc_marketplace;
	}

	public String getDesc_powerplant()
	{
		return desc_powerplant;
	}

	public void setDesc_powerplant(String desc_powerplant)
	{
		this.desc_powerplant = desc_powerplant;
	}

	public String getDesc_warehouse()
	{
		return desc_warehouse;
	}

	public void setDesc_warehouse(String desc_warehouse)
	{
		this.desc_warehouse = desc_warehouse;
	}

	public String getDesc_farm()
	{
		return desc_farm;
	}

	public void setDesc_farm(String desc_farm)
	{
		this.desc_farm = desc_farm;
	}

	public String getDesc_cure()
	{
		return desc_cure;
	}

	public void setDesc_cure(String desc_cure)
	{
		this.desc_cure = desc_cure;
	}

	public String getDesc_academy()
	{
		return desc_academy;
	}

	public void setDesc_academy(String desc_academy)
	{
		this.desc_academy = desc_academy;
	}

	public String getNeed_selectCity()
	{
		return need_selectCity;
	}

	public void setNeed_selectCity(String need_selectCity)
	{
		this.need_selectCity = need_selectCity;
	}

	public String getFull_slot()
	{
		return full_slot;
	}

	public void setFull_slot(String full_slot)
	{
		this.full_slot = full_slot;
	}

	public String getNeed_selectSection()
	{
		return need_selectSection;
	}

	public void setNeed_selectSection(String need_selectSection)
	{
		this.need_selectSection = need_selectSection;
	}

	public String getNeed_budget()
	{
		return need_budget;
	}

	public void setNeed_budget(String need_budget)
	{
		this.need_budget = need_budget;
	}

	public String getOn()
	{
		return on;
	}

	public void setOn(String on)
	{
		this.on = on;
	}

	public String getMake_deactive()
	{
		return make_deactive;
	}

	public void setMake_deactive(String make_deactive)
	{
		this.make_deactive = make_deactive;
	}

	public String getStop_control_city()
	{
		return stop_control_city;
	}

	public void setStop_control_city(String stop_control_city)
	{
		this.stop_control_city = stop_control_city;
	}

	public String getBuild_warn()
	{
		return build_warn;
	}

	public void setBuild_warn(String build_warn)
	{
		this.build_warn = build_warn;
	}

	public String getView_on_web()
	{
		return view_on_web;
	}

	public void setView_on_web(String view_on_web)
	{
		this.view_on_web = view_on_web;
	}

	public String getCatchs()
	{
		return catchs;
	}

	public void setCatchs(String catchs)
	{
		this.catchs = catchs;
	}

	public String getEnemy()
	{
		return enemy;
	}

	public void setEnemy(String enemy)
	{
		this.enemy = enemy;
	}

	public String getItem()
	{
		return item;
	}

	public void setItem(String item)
	{
		this.item = item;
	}

	public String getReflex_simplehelp()
	{
		return reflex_simplehelp;
	}

	public void setReflex_simplehelp(String reflex_simplehelp)
	{
		this.reflex_simplehelp = reflex_simplehelp;
	}

	public String getReflex_help()
	{
		return reflex_help;
	}

	public void setReflex_help(String reflex_help)
	{
		this.reflex_help = reflex_help;
	}

	public String getReflex_flex()
	{
		return reflex_flex;
	}

	public void setReflex_flex(String reflex_flex)
	{
		this.reflex_flex = reflex_flex;
	}

	public String getReflex_berserk()
	{
		return reflex_berserk;
	}

	public void setReflex_berserk(String reflex_berserk)
	{
		this.reflex_berserk = reflex_berserk;
	}

	public static int getReflexBerserk()
	{
		return REFLEX_BERSERK;
	}

	public String getFile_created()
	{
		return file_created;
	}

	public void setFile_created(String file_created)
	{
		this.file_created = file_created;
	}

	public String getFile_deleted()
	{
		return file_deleted;
	}

	public void setFile_deleted(String file_deleted)
	{
		this.file_deleted = file_deleted;
	}

	public String getReflex_clipper()
	{
		return reflex_clipper;
	}

	public void setReflex_clipper(String reflex_clipper)
	{
		this.reflex_clipper = reflex_clipper;
	}

	public String getReflex_warship()
	{
		return reflex_warship;
	}

	public void setReflex_warship(String reflex_warship)
	{
		this.reflex_warship = reflex_warship;
	}

	public String getDownload_pack()
	{
		return download_pack;
	}

	public void setDownload_pack(String download_pack)
	{
		this.download_pack = download_pack;
	}

	public String getUser_defined()
	{
		return user_defined;
	}

	public void setUser_defined(String user_defined)
	{
		this.user_defined = user_defined;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getWill_download_at()
	{
		return will_download_at;
	}

	public void setWill_download_at(String will_download_at)
	{
		this.will_download_at = will_download_at;
	}

	public String getLicense()
	{
		return license;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}

	public String getAllow()
	{
		return allow;
	}

	public void setAllow(String allow)
	{
		this.allow = allow;
	}

	public String getFirst_additional_download()
	{
		return first_additional_download;
	}

	public void setFirst_additional_download(String first_additional_download)
	{
		this.first_additional_download = first_additional_download;
	}

	public String getNot_now()
	{
		return not_now;
	}

	public void setNot_now(String not_now)
	{
		this.not_now = not_now;
	}

	public String getFile_needs()
	{
		return file_needs;
	}

	public void setFile_needs(String file_needs)
	{
		this.file_needs = file_needs;
	}

	public String getContinues()
	{
		return continues;
	}

	public void setContinues(String continues)
	{
		this.continues = continues;
	}

	public String getShip_name()
	{
		return ship_name;
	}

	public void setShip_name(String ship_name)
	{
		this.ship_name = ship_name;
	}

	public String getShip_startHP()
	{
		return ship_startHP;
	}

	public void setShip_startHP(String ship_startHP)
	{
		this.ship_startHP = ship_startHP;
	}

	public String getShip_startE()
	{
		return ship_startE;
	}

	public void setShip_startE(String ship_startE)
	{
		this.ship_startE = ship_startE;
	}

	public String getShipSize()
	{
		return shipSize;
	}

	public void setShipSize(String shipSize)
	{
		this.shipSize = shipSize;
	}

	public String getShip_startSpeed()
	{
		return ship_startSpeed;
	}

	public void setShip_startSpeed(String ship_startSpeed)
	{
		this.ship_startSpeed = ship_startSpeed;
	}

	public String getWeapon()
	{
		return weapon;
	}

	public void setWeapon(String weapon)
	{
		this.weapon = weapon;
	}

	public String getSpendE()
	{
		return spendE;
	}

	public void setSpendE(String spendE)
	{
		this.spendE = spendE;
	}

	public String getMissile_size()
	{
		return missile_size;
	}

	public void setMissile_size(String missile_size)
	{
		this.missile_size = missile_size;
	}

	public String getShape()
	{
		return shape;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

	public String getAttackPower()
	{
		return attackPower;
	}

	public void setAttackPower(String attackPower)
	{
		this.attackPower = attackPower;
	}

	public String getCooltime()
	{
		return cooltime;
	}

	public void setCooltime(String cooltime)
	{
		this.cooltime = cooltime;
	}

	public String getWeapon_range()
	{
		return weapon_range;
	}

	public void setWeapon_range(String weapon_range)
	{
		this.weapon_range = weapon_range;
	}

	public String getGuide_helperonly()
	{
		return guide_helperonly;
	}

	public void setGuide_helperonly(String guide_helperonly)
	{
		this.guide_helperonly = guide_helperonly;
	}

	public String getHorizontal_gap()
	{
		return horizontal_gap;
	}

	public void setHorizontal_gap(String horizontal_gap)
	{
		this.horizontal_gap = horizontal_gap;
	}

	public String getIf_multipleShoot()
	{
		return if_multipleShoot;
	}

	public void setIf_multipleShoot(String if_multipleShoot)
	{
		this.if_multipleShoot = if_multipleShoot;
	}

	public String getFireCount()
	{
		return fireCount;
	}

	public void setFireCount(String fireCount)
	{
		this.fireCount = fireCount;
	}

	public String getKind_interceptor()
	{
		return kind_interceptor;
	}

	public void setKind_interceptor(String kind_interceptor)
	{
		this.kind_interceptor = kind_interceptor;
	}

	public String getCount_interceptor()
	{
		return count_interceptor;
	}

	public void setCount_interceptor(String count_interceptor)
	{
		this.count_interceptor = count_interceptor;
	}

	public String getMissile_speed()
	{
		return missile_speed;
	}

	public void setMissile_speed(String missile_speed)
	{
		this.missile_speed = missile_speed;
	}

	public String getKinds()
	{
		return kinds;
	}

	public void setKinds(String kinds)
	{
		this.kinds = kinds;
	}

	public String getMissile_kind()
	{
		return missile_kind;
	}

	public void setMissile_kind(String missile_kind)
	{
		this.missile_kind = missile_kind;
	}

	public String getGame_ready()
	{
		return game_ready;
	}

	public void setGame_ready(String game_ready)
	{
		this.game_ready = game_ready;
	}

	public String getToday_game()
	{
		return today_game;
	}

	public void setToday_game(String today_game)
	{
		this.today_game = today_game;
	}

	public String getShip()
	{
		return ship;
	}

	public void setShip(String ship)
	{
		this.ship = ship;
	}

	public String getToday_item()
	{
		return today_item;
	}

	public void setToday_item(String today_item)
	{
		this.today_item = today_item;
	}

	public String getToday_warn()
	{
		return today_warn;
	}

	public void setToday_warn(String today_warn)
	{
		this.today_warn = today_warn;
	}

	public String getContinue_on_zero()
	{
		return continue_on_zero;
	}

	public void setContinue_on_zero(String continue_on_zero)
	{
		this.continue_on_zero = continue_on_zero;
	}

	public String getInput_url()
	{
		return input_url;
	}

	public void setInput_url(String input_url)
	{
		this.input_url = input_url;
	}

	public String getInput_url2()
	{
		return input_url2;
	}

	public void setInput_url2(String input_url2)
	{
		this.input_url2 = input_url2;
	}

	public String getInput_replay_gap()
	{
		return input_replay_gap;
	}

	public void setInput_replay_gap(String input_replay_gap)
	{
		this.input_replay_gap = input_replay_gap;
	}

	public String getReflex_chaser()
	{
		return reflex_chaser;
	}

	public void setReflex_chaser(String reflex_chaser)
	{
		this.reflex_chaser = reflex_chaser;
	}

	public String getReflex_satellite()
	{
		return reflex_satellite;
	}

	public void setReflex_satellite(String reflex_satellite)
	{
		this.reflex_satellite = reflex_satellite;
	}

	public String getReflex_carrier()
	{
		return reflex_carrier;
	}

	public void setReflex_carrier(String reflex_carrier)
	{
		this.reflex_carrier = reflex_carrier;
	}
}