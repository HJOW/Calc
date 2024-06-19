package lang;

import setting.Difficulty;

public class English extends Language
{
	private static final long serialVersionUID = 3346721188515475503L;

	public English()
	{
		title = "Calc";
		x = "X";
		file_created = "Following files are created.";
		file_deleted = "Following files are deleted.";
		warn = "Warn";
		warn_script = "You should use trustworthy module.";
		module = "Module";
		type = "Type";
		block = "Block";
		chat = "Chat";
		to = "To";
		counterparty = "others";
		onlyBeta = "This mode is for test version only";
		is = "is";
		are = "are";
		all = "All";
		catchs = "Catched ";
		enemy = "enemy";
		item = "item";
		license = "License";
		allow = "Allow";
		not_now = "not now";
		first_additional_download = "Download additional resources to make this game more fantastic.";
		reflex_simplehelp = "→←↑↓ : Move, SHIFT : Stop move, SPACE : Fire, 123 : Change weapon, 4 : Auto Fire, L : Pause, K : Exit";
		reflex_help = "Reflexioner\n\n"
				+ "You can play with keyboard.\n"
				+ "If you using touch screen, press ▲ button to open other buttons to control.\n\n"				
				+ "You will control the green circle. See following key you will press.\n\n"
				+ reflex_simplehelp + "\n\n"
				+ "After the game start, dark circles will appear, and they shoot some missiles.\n"
				+ "You will control the green circle to avoid these missiles.\n\n"
				+ "Also, you can shoot missiles to attack dark circles.\n"
				+ "You can get points to earn points.\n\n"
				+ "Sometimes, pink circles will appear, and you can improve the green circle\'s abilities.\n\n"
				+ "The game will finish when the green circle is destroyed.\n"
				+ "You can see your points, and authority code for you to demonstrate your game skill to others.";
		
		file_needs = "File needs";
		continues = "Continue";
		ship_name = "Ship Name";
		ship_startHP = "Ship HP";
		ship_startE = "Ship Energy";
		shipSize = "Ship Size";
		ship_startSpeed = "Ship Speed";
		weapon = "Weapon";
		attackPower = "Damage";
		cooltime = "Cooling Time";
		weapon_range = "Range";
		guide_helperonly = "Only for guide, and helper";
		horizontal_gap = "Interval";
		if_multipleShoot = "";
		fireCount = "";
		spendE = "Needs";
		missile_size = "Size";
		kind_interceptor = "Interceptor Type";
		count_interceptor = "Interceptor Count";
		missile_speed = "Speed";
		kinds = "Type";
		missile_kind = "(normal, super, guide, reflex, reflex_perfect, beam, helper)";
		shape = "Shape";
		game_ready = "Game is ready to start.";
		today_game = "Start this game and get double points !";
		ship = "Ship";
		today_item = "Reinforcement at starts";
		today_warn = "Caution : Double-point effect is work at the game finish. This effect is not saved in state, and replay.";
		continue_on_zero = "Set the point to zero and continue";
		input_url = "Input the URL where to download resources.";
		input_url2 = "Input the URL where to download resources. (2nd)";
		input_replay_gap = "Input number to skip frames when saving replays.\nInput big number to make replay file smaller.\nToo big number occurs unnatural play.";
		
		reflex_flex = "Flex";
		reflex_berserk = "Berserk";
		reflex_clipper = "Clipper";
		reflex_warship = "Warship";
		reflex_satellite = "Satellite";
		reflex_chaser = "Chaser";
		reflex_carrier = "Carrier";
		path = "path";
		user_defined = "User Defined";
		download_pack = "Additional Contents";
		will_download_at = "Files will be located at";
		order = "Order";
		event = "Event";
		homepage = "Homepage";
		address = "Address";
		web = "Web";
		answer = "Answer";
		mathq_simplehelp = "Input the value to get more points. \nThe value is in solutions of equations. \nThe time passed, more equation will appear. \nIf you input wrong value, limit-time will be cut very much.";
		mathq_help = "Math Conqueror\n------------------------------------------------------\n" + mathq_simplehelp;
		uninstall_askMessage = " will be removed. Do you want to continue?";
		uninstall_completed = "Try to delete : ";
		uninstall_deleted = "Uninstall complete";
		uninstall_error = "There are some problems to uninstall...\n";
		uninstall_after_askMessage = " will be removed after the program end. Do you want to continue?";
		uninstall_after_completed = "Try to reserve deletion : ";
		uninstall_after_deleted = "Uninstall complete";
		uninstall_after_error = "There are some problems to uninstall...\n";
		uninstall = "Uninstall";
		management = "Management";
		scenario = "Scenario";
		download = "Download";
		update = "Update";		
		doyouwantupdate = "Do you want to update?";
		downloadfinish = "Download complete.";
		already_newest = "This is already newest version.";
		checkreliability = "Do you trust this web address?";
		noscenario_description = "You don\'t select scenario.\n\nScenario Difficulty Units\n";
		noscenario_description = noscenario_description + "♬" + " : " + String.valueOf((int) ((-10) * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "♪" + " : " + String.valueOf(-10) + "\n";
		noscenario_description = noscenario_description + "♥" + " : " + String.valueOf((int) ((-1) * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "♡" + " : " + String.valueOf(-1) + "\n";	
		noscenario_description = noscenario_description + "○" + " : " + String.valueOf(1) + "\n";
		noscenario_description = noscenario_description + "●" + " : " + String.valueOf((int) (1 * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "□" + " : " + String.valueOf(10) + "\n";
		noscenario_description = noscenario_description + "■" + " : " + String.valueOf((int) (10 * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "◇" + " : " + String.valueOf(100) + "\n";
		noscenario_description = noscenario_description + "◆" + " : " + String.valueOf((int) (100 * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "☆" + " : " + String.valueOf(1000) + "\n";
		noscenario_description = noscenario_description + "★" + " : " + String.valueOf((int) (1000 * Difficulty.DEFAULT_UNIT_VALUE)) + "\n";
		noscenario_description = noscenario_description + "♨" + " : " + String.valueOf(10000);
		get = "Take 1 card from deck.";
		deckLabel1 = "On deck, there ";
		deckLabel2 = "is no card.";
		deckLabel3 = "is 1 card.";
		deckLabel4 = " cards.";
		start = "Game Start";
		exit = "Exit";
		close = "Close";
		result = "Result";
		won = "Won !!";
		whos = "\'s";
		point = "point";
		add = "Add";
		easy = "Easy";
		normal = "Normal";
		hard = "Hard";
		conquer = "Direct Conquer";
		mathconq = "Math Conquer";
		reflex = "Reflexioner";
		auto_pass = " use AI to act his/her turn.";
		refresh = "Refresh";
		tracking = "Event tracking";
		tracking_autosave = "Save trackings to files";
		accumulated = "Accumulated";
		oneCard = "One Card";
		modes = "Mode";
		dir_center = "Center";
		dir_down = "Down";
		dir_up = "Up";
		dir_left = "Left";
		dir_right = "Right";
		delay = "Delay";
		runs = "Run";
		copy_clipboard = "Copy";
		desc_copy_clipboard = "The code is copied to the clipboard.";
		desc_calc = "";
		desc_oneCard = "";
		oneCard_panelty = " take some cards : ";
		another_games = "Another Games";
		classic = "Classic Mode (More fast)";
		restart = "Restart";
		remove = "Remove";
		password = "Password";
		time = "Time";
		year = "year";
		month = "month";
		day = "day";
		hour = "hour";
		minute = "minute";
		ok = "OK";
		accept = "Accept";
		basic_edition = "Basic";
		professional = "Professional";
		ultimate = "Ultimate";
		master = "Master";
		scrollBar = "Scroll Bar";
		change_card = "§ card";
		in_bar = "Pay a card, or take a card from deck to finish your turn.";
		pay = "Pay selected card here";
		code_checker = "Check the code";
		owns = "Owns";
		paids = "Paids";
		name = "Name";
		information = "information";
		main = "Main";
		point_code = "Point authorize code";
		finish = "Click here to finish your turn.";
		sealed = "Sealed !";
		none = "None";
		player = "Player";
		ai = "AI";
		script = "Script";
		engine = "Engine";
		game_stop = "Stop this game";
		background = "Background";
		foreground = "Foreground";
		inside_background = "Inside background";
		check = "Check";
		menu = "Menu";
		input = "Input";
		left = "Left ";
		active = "Active";
		deactive = "Deactive";
		width = "Width";
		height = "Height";
		save = "Save";
		load = "Load";
		red = "Red";
		green = "Green";
		blue = "Blue";
		setting = "Setting";
		max = "Max";
		count = "count";
		virtuals = "Virtual";
		needRestart = "Restart Calc to apply settings.";
		self = "Self";
		titleBar = "title bar";
		notice = "Notice";
		notice_not_view_again = "Do not show the notice window again.";
		now_player_card = "Player\'s owns list";
		use = "use";
		help = "Help";
		authority = "Authority";
		randomOrder = "Random orders";
		replay = "Replay";
		edit = "Edit";
		view = "View";
		card = "Card";
		theme = "Theme";
		alert = "alert";
		window = "window";
		show = "show";
		complete = "complete";
		fail = "fail";
		single = "single";
		date = "Date";
		multi = "multi";
		play = "play";
		color = "Color";
		credit = "Credit";
		error = "Error";
		print_error_detail = "Show error message detail";
		host = "host";
		join = "join";
		connect = "connect";
		disconnect = "disconnect";
		ip = "IP";
		port = "PORT";
		ready = "Ready";
		cancel = "Cancel";
		log = "Log";
		detail = "Detail";
		summary = "Summary";
		summary_use = "Use summary view (Make game slowly)";
		howToShowCard = "How to show card";
		bonus_target = "Get bonus point";
		difficulty = "difficulty";
		separator = "Separator";
		use_xml_setting = "Use XML to save";
		reset = "Reset";
		user = "User";
		new_user = "New user";
		bet = "Bet";
		select = "Select";
		ranking = "Ranking";
		version = "Version";
		maker = "Made by HJOW";
		make = "Make";
		about = "About Calc";
		
		shelter_city = "Shelter City"; 
		pause = "Pause"; 
		pause_break = "Continue"; 
		tax = "Tax"; 
		total_info = "Total Information"; 
		build = "Build"; 
		resident = "Resident Area"; 
		marketplace = "Marketplace"; 
		powerplant = "Powerplant"; 
		warehouse = "Warehouse"; 
		farm = "Farm"; 
		clinic = "Clinic"; 
		academy = "Academy"; 
		cost = "Cost"; 
		policy = "Policy"; 
		make_active = "Active"; 
		make_deactive = "Deactive";
		economy = "Economy"; 
		etc = "etc."; 
		new_city = "New City"; 
		city = "City";
		budget = "Budget";
		population = "Population";
		average = "Average";
		intelligent = "Intelligent";
		strength = "Strength";
		minimum = "Minimum";
		maximum = "Maximum";
		hp = "HP";
		happiness = "Happiness";
		desease = "Desease";
		deep = "Depp";
		food = "Food";
		production = "Production";
		size = "Size";
		situation = "Situation";
		desc_resident = "주거 구역은 수용할 인구 한계를 높입니다.\n등급이 높을 수록 수용량은 줄어듭니다.."
				+ "\n지능이 높은 인구가 등급이 지나치게 낮은 곳에 거주하면 행복도가 떨어집니다."
				+ "\n지능에 비해 높은 등급에 거주하면 행복도가 증가합니다.";
		desc_marketplace = "상업 구역에는 쇼핑몰과 더불어 여러 서비스업과 사무실이 수용됩니다."
				+ "\n상업 구역이 많을수록 예산 증가폭이 증가합니다."
				+ "\n상업 구역이 충분하면 인구 행복도가 늘어납니다."
				+ "\n등급이 높을 수록 수용량은 줄어드는 대신 지능이 높은 인구만큼 예산 증가폭이 증가합니다.";
		desc_powerplant = "발전 구역은 전력을 생산합니다.\n생산된 전력은 저장되지 않으므로, 과도하게 생산된 전력은 무효화됩니다."
				+ "\n전력 공급은 구역 번호 순으로 공급되며, 전력 공급이 충분히 되지 않은 구역은 파괴됩니다."
				+ "\n등급이 높을 수록 용량도 늘어납니다.";
		desc_warehouse = "창고는 식량을 저장하는 공간입니다.\n"
				+ "등급이 높을 수록 수용량도 늘어납니다.\n";
		desc_farm = "농장은 식량을 생산하는 곳입니다.\n"
				+ "자동화되어 있으므로 인구가 일할 필요가 없으며, 배송도 전용 수송 장치로 무인 배달됩니다.\n"
				+ "등급이 높을수록 생산량 또한 높습니다.";
		desc_cure = "치료소는 질병 수치가 있는 인구에게서 질병을 제거합니다.\n"
				+ "질병 수치는 확률에 따라 임의로 발생하며, 질병 수치가 5 이상이 되면 전염성이 있습니다.\n"
				+ "질병 수치가 있는 인구는 질병 수치만큼 체력이 감소합니다.\n"
				+ "주거 구역의 등급이 낮을수록 질병 발생 확률도 높습니다.";
		desc_academy = "교육원은 인구를 교육시켜 지능을 높이는 곳입니다.\n"
				+ "교육원 등급에 따라 교육시킬 수 있는 인구가 다릅니다.\n"
				+ "교육원 등급과 지능 차이가 너무 큰 인구는 교육시킬 수 없습니다.\n"
				+ "지능이 높으면 높은 등급의 상업 구역에서 상당한 양의 예산을 얻을 수 있게 됩니다.";
		need_selectCity = "Select city and try again.";
		full_slot = "No more empty slot.";
		need_selectSection = "Select section and try again.";
		need_budget = "Don't have enough budget.";
		on = "on";
		stop_control_city = "Stop control city";
		
		on_start_cards = "Number of cards to players when the game start";
		notice_null = "Cannot read notice online.\n"
				+ "\n"
				+ "Visit http://hujinone11.oranc.co.kr\n"
				+ "to check newest version and notices.";
		input_serial_agreement = "If you input the serial code, you can use additional functions.";
		help_contents = "Calc\n"
				+ "\n"
				+ "Calc is, turn-based strategy card game.\n"
				+ "\n"
				+ "Before the game started, you will select player settings.\n"
				+ "If you had not been changed setting, maximum 4 players can join the game.\n"
				+ "If you choose \"Player\", then, when the current player turn came, user can control the current player. \n"
				+ "If you choose \"AI\", then, the PC control the current player automatic.\n"
				+ "\n"
				+ "On the game, you can see each player\'s area on center.\n"
				+ "There are some spaces that you can see the current player\'s own cards, and the current player's paid-card slot.\n"
				+ "Also, there are spaces that you can see the current player\'s point.\n"
				+ "\n"
				+ "The space of the player\'s own cards will be sealed when the turn is not the player\'s turn.\n"
				+ "\n"
				+ "The game starts, each player take 10 cards. Other cards are on the deck.\n"
				+ "You can see the number of deck cards on the screen.\n"
				+ "\n"
				+ "On your turn, you should pay 1 card, or you should take 1 card from deck.\n"
				+ "If you want to pay, you should choose the space you want to pay.\n"
				+ "\n"
				+ "If there are already some cards exist, then you should choose the card which has equal number or equal operation to the last card.\n"
				+ "\n"
				+ "If the card which has number 7 is the last card, only the owner of the space can pay the card there.\n"
				+ "\n"
				+ "If the card which has number 1 you want to pay, you can pay this card anywhere except the place which is protected by 7-card.\n"
				+ "\n"
				+ "§ means \"multiply\", but it has special ability.\n"
				+ "If you pay this card to other player, then you have to change all of your own cards to other player\'s owns.\n"
				+ "\n"
				+ "When anyone don\'t have any card, or no card on the deck, then the game finish.\n"
				+ "\n"
				+ "Additional 5 points will be given to the player who has fewest cards.\n"
				+ "\n"
				+ "Made by HJOW\n"
				+ "hujinone22@naver.com\n"
				+ "http://hujinone11.oranc.co.kr";
		
		menu_file = "File";
		build_warn = "You can\'t destroy sections if the power is enough."
				+ "Be careful to build new section !";
		
		description = new String[33];
		description[0] = " take 1 card.";
		description[1] = " pay 1 card to ";
		description[2] = "\'s paids.";
		description[3] = "Calc is successfully prepared.";
		description[4] = "\'s turn started.";
		description[5] = "\'s turn finished.";
		description[6] = "Number-7-Protected. This paids is protected. Choose another paids.";
		description[7] = "Please choose the card which has same number or same operation.";
		description[8] = "Couldn\'t pay the card because you didn\'t select the card.";
		description[9] = "Couldn\'t load setting because of setting\'s version.";
		description[10] = "Have only 1 card : ";
		description[11] = "Game is started.";
		description[12] = "Don't you want to see the notice next time?";
		description[13] = "\'s password?";
		description[14] = "Wrong password !";
		description[15] = " selected.";
		description[16] = "Betted credit : ";
		description[17] = "How many credits do you want to bet? Now betted : ";
		description[18] = "Play authorized game for get authorized code.";
		description[19] = "Input your point authorized code here.";
		description[20] = "Retry, please.";
		description[21] = "You can\'t get authorize because you don\'t won.";
		description[22] = " turns.";
		description[23] = "Pay a card to ";
		description[24] = ", Last paid-slot card : ";
		description[25] = "Take a card from deck. On deck, there are... : ";
		description[26] = "Change ";
		description[27] = " 's own cards to ";
		description[28] = " 's owns.";
		description[29] = "Game is finished.";
		description[30] = "Checking start.";
		description[31] = "Trump Mode enabled";
		description[32] = "Trump Mode disabled";
	}

	@Override
	public int getType()
	{
		return Language.LANG_ENGLISH;
	}	
}
