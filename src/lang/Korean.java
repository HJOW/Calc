package lang;

import setting.Difficulty;

public class Korean extends Language
{
	private static final long serialVersionUID = 3346721188515475503L;

	public Korean()
	{				
		title = "Calc";
		x = "X";
		file_created = "다음과 같은 파일들이 생성되었습니다.";
		file_deleted = "다음 파일들이 삭제되었습니다.";
		module = "모듈";
		warn = "경고";
		warn_script = "믿을 수 있는 모듈만 사용하십시오.";
		type = "종류";
		block = "블럭";
		is = "";
		are = "";
		catchs = "잡은 ";
		enemy = "적";
		item = "아이템";
		license = "라이센스";
		allow = "허가";
		not_now = "나중에 하기";
		first_additional_download = "추가 리소스를 다운로드하면 화려하게 즐길 수 있습니다.";
		reflex_simplehelp = "→←↑↓ : 이동, SHIFT : 브레이크, SPACE : 발사, 123 : 무기 변경, 4 : 자동 발사, L : 일시 정지, K : 중단";
		reflex_help = "Reflexioner\n\n"
				+ "이 게임은 키보드를 사용하는 것이 편리합니다.\n"
				+ "터치 환경을 이용한다면, ▲ 버튼을 클릭해 터치 및 버튼으로 컨트롤할 수 있습니다.\n\n"				
				+ "이 게임에서, 당신은 연두색 원을 조작합니다. 조작법은 아래와 같습니다.\n\n"
				+ reflex_simplehelp + "\n\n"
				+ "게임이 시작하면 화면 상단에서 어두운 색 원이 등장하며, 이 물체들이 투사체를 발사해 연두색 원을 공격할 것입니다.\n"
				+ "당신은 연두색 원을 잘 이동시켜 이러한 공격을 피해야 합니다.\n\n"
				+ "연두색 원을 조작하여, 투사체를 발사해 어두운 색들을 공격할 수도 있습니다.\n"
				+ "어두운 색을 파괴할 때마다 점수를 얻습니다.\n\n"
				+ "때로 연붉은 색 원이 발생하며, 이 원과 부딛쳐 능력을 향상시킬 수 있습니다.\n\n"
				+ "연두색 원이 파괴될 때까지 게임이 진행되며, 게임이 끝나면 점수와 인증 코드를 볼 수 있습니다.";
		
		
		file_needs = "필요한 파일";
		continues = "계속하기";
		ship_name = "함선 이름 (이미지 파일 이름에 영향)";
		ship_startHP = "함선 초기 HP";
		ship_startE = "함선 초기 E 양";
		shipSize = "함선 크기";
		ship_startSpeed = "함선 초기 이동 속도";
		weapon = "무기";
		attackPower = "공격력";
		cooltime = "쿨 타임";
		weapon_range = "사거리";
		guide_helperonly = "guide, helper 에만 적용";
		horizontal_gap = "수평 간격";
		if_multipleShoot = "여러 발 발사 시";
		fireCount = "발사량";
		spendE = "E 소모량";
		missile_size = "발사체 크기";
		kind_interceptor = "요격기 종류 (helper 일 때만)";
		count_interceptor = "요격기 방출 수 (helper 일 때만)";
		missile_speed = "발사체 속도";
		kinds = "종류";
		missile_kind = "(normal, super, guide, reflex, reflex_perfect, beam, helper)";
		shape = "모양";
		game_ready = "게임이 준비되었습니다.";
		today_game = "아래의 버튼으로 게임을 시작하면 점수가 두 배입니다 !";
		ship = "함선";
		today_item = "시작 시 강화";
		today_warn = "주의 : 점수 2배 혜택은 게임 종료 시 적용되며, 상태 저장 및 리플레이 시 이 혜택은 저장되지 않습니다.";
		continue_on_zero = "점수만 0인 채로 계속하기";
		input_url = "다운로드 팩이 있는 URL을 입력하십시오.";
		input_url2 = "다운로드에 실패한 경우 다음으로 시도할 URL을 입력하십시오.";
		input_replay_gap = "리플레이 저장 시 건너 뛸 프레임 단위를 입력합니다.\n크게 입력하면 리플레이 파일의 용량은 줄어들지만 재생 시 자연스럽지 않습니다.";
				
		
		reflex_flex = "Flex";
		reflex_berserk = "Berserk";
		reflex_clipper = "Clipper";
		reflex_warship = "Warship";
		reflex_satellite = "Satellite";
		reflex_chaser = "Chaser";
		reflex_carrier = "Carrier";
		path = "경로";
		user_defined = "사용자 지정";
		download_pack = "추가 컨텐츠 다운로드";
		onlyBeta = "테스트 버전에서만 사용할 수 있는 기능입니다.";
		all = "전체";
		order = "명령어";
		scenario = "시나리오";
		download = "다운로드";
		update = "업데이트";
		event = "이벤트";
		homepage = "홈페이지";
		address = "주소";
		web = "웹";
		answer = "답";
		mathq_simplehelp = "답안을 입력하여 점수를 획득합니다. \n답안은 중앙에 나오는 방정식의 해 중 하나입니다. \n시간이 지날수록 방정식 수가 늘어나 답안을 구하기 쉬워집니다. \n오답을 입력하면 제한 시간이 대폭 감소합니다.";
		mathq_help = "Math Conqueror\n\n"
				+ "게임이 시작되면 중앙에 방정식이 나타나고, 아래에 답안을 입력하는 란이 나타납니다.\n" 
				+ mathq_simplehelp + "\n답안을 연속으로 성공적으로 입력할 경우 점수 획득량이 늘어납니다.";
		uninstall_askMessage = " 폴더가 삭제됩니다. 계속하시겠습니까?";
		uninstall_completed = "삭제 중 : ";
		uninstall_deleted = "삭제 완료";
		uninstall_error = "삭제 중 문제가 있었습니다\n";
		uninstall_after_askMessage = " 폴더가 종료 후 삭제됩니다. 계속하시겠습니까?";
		uninstall_after_completed = "삭제 예약 중 : ";
		uninstall_after_deleted = "삭제 완료";
		uninstall_after_error = "삭제 예약 중 문제가 있었습니다\n";		
		uninstall = "삭제";
		management = "관리";
		will_download_at = "다운로드 위치";
		doyouwantupdate = "업데이트 하시겠습니까?";
		downloadfinish = "다운로드 완료되었습니다.";
		already_newest = "이미 최신 버전의 Calc 를 실행하고 있습니다.";
		checkreliability = "다음 주소가 신뢰할 수 있는 곳인지 확인하십시오.";
		noscenario_description = "시나리오를 선택하지 않았습니다.\n\n시나리오 난이도 기호\n";
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
		get = "덱에서 1장의 카드를 받습니다.";
		deckLabel1 = "덱에는";
		deckLabel2 = "카드가 없습니다.";
		deckLabel3 = "카드가 1장 있습니다.";
		deckLabel4 = " 장의 카드가 있습니다.";
		start = "게임 시작하기";
		exit = "종료";
		close = "닫기";
		result = "결과";
		won = "승리 !!";
		whos = "의";
		point = "점수";
		scrollBar = "스크롤바";
		ok = "확인";
		accept = "확인";
		oneCard = "원카드";
		modes = "모드";
		dir_center = "중앙";
		dir_down = "아래";
		dir_up = "위";
		dir_left = "왼쪽";
		dir_right = "오른쪽";
		delay = "지연";
		runs = "실행";
		easy = "쉬움";
		normal = "보통";
		hard = "어려움";
		refresh = "새로 고침";
		conquer = "Direct Conquer";
		mathconq = "Math Conquer";
		reflex = "Reflexioner";
		auto_pass = " 이/가 인공지능을 사용하여 차례를 완료하였습니다.";
		tracking = "이벤트 추적";
		tracking_autosave = "이벤트 추적 내역 자동 저장";
		copy_clipboard = "복사";
		desc_copy_clipboard = "클립보드로 코드를 복사했습니다.";
		desc_calc = "";
		desc_oneCard = "";
		oneCard_panelty = " 이(가) 카드를 다음 수 만큼 받습니다 : ";
		another_games = "다른 게임";
		accumulated = "축적된";
		classic = "고전 모드 (더 빠름)";
		pay = "선택한 카드를 이 곳에 놓습니다.";
		owns = "소유한 카드 목록";
		paids = "놓인 카드 슬롯";
		name = "이름";
		restart = "종료 후 다시 실행";
		basic_edition = "Basic";
		professional = "Professional";
		ultimate = "Ultimate";
		master = "Master";
		finish = "여기를 클릭하여 턴을 종료하십시오.";
		sealed = "숨겨짐 !";
		none = "없음";
		player = "사용자";
		ai = "인공지능";
		script = "스크립트";
		engine = "엔진";
		check = "검사";
		menu = "메뉴";
		input = "입력";
		time = "시간";
		year = "년";
		month = "월";
		day = "일";
		hour = "시";
		minute = "분";
		code_checker = "점수 인증코드 확인";
		game_stop = "현재 게임 끝내기";
		background = "배경색";
		foreground = "전경색";
		inside_background = "안쪽 배경색";
		active = "활성";
		deactive = "비활성";
		width = "수평";
		height = "수직";
		save = "저장";
		load = "불러오기";
		red = "빨강";
		green = "초록";
		blue = "파랑";
		setting = "설정";
		max = "최대";
		count = "수";
		point_code = "점수 인증 코드";
		needRestart = "프로그램을 종료 후 다시 실행해야 적용됩니다.";
		self = "자체";
		titleBar = "제목 막대";
		left = "남은";
		use = "사용";
		help = "도움말";
		add = "추가";
		remove = "제거";
		password = "암호";
		on_start_cards = "시작 시 나눠 줄 카드 수";
		authority = "인증";
		replay = "리플레이";
		edit = "편집";
		view = "보기";
		card = "카드";
		theme = "테마";
		alert = "경고";
		window = "창";
		show = "보이기";
		complete = "완료";
		fail = "실패";
		error = "오류";
		randomOrder = "순서 섞기";
		single = "싱글";
		multi = "멀티";
		play = "플레이";
		host = "호스트";
		join = "참가";
		
		connect = "접속";
		disconnect = "접속 끊기";
		chat = "채팅";
		to = "To";
		counterparty = "상대";
		ip = "IP";
		port = "포트";
		ready = "준비";
		cancel = "취소";
		date = "날짜";
		log = "내역";
		in_bar = "카드를 한 장 받거나, 한 장 놓아 차례를 완료하십시오.";
		change_card = "§ 카드";
		now_player_card = "현재 플레이어의 소유한 카드 목록";
		notice = "공지사항";
		notice_not_view_again = "시작 시 공지사항 창 보지 않기";
		detail = "자세히";
		summary = "간략히";
		summary_use = "간략히 보기 사용 (느려질 수 있음)";
		version = "버전";
		ranking = "랭킹";
		user = "사용자";
		new_user = "사용자 추가";
		bet = "베팅";
		select = "선택";
		bonus_target = "추가 점수 대상자";
		color = "색";
		reset = "초기화";
		credit = "Credit";
		howToShowCard = "카드 보이는 방법";
		print_error_detail = "오류 메시지 상세 출력";
		difficulty = "난이도";
		separator = "분리자";
		virtuals = "가상";
		information = "정보";
		main = "메인";
		use_xml_setting = "XML 사용";
		about = "Calc는?";
		maker = "Made by HJOW";
		make = "만들기";
		
		shelter_city = "Shelter City"; 
		pause = "일시정지"; 
		pause_break = "일시정지 해제"; 
		tax = "세금"; 
		total_info = "종합 정보"; 
		build = "건설"; 
		resident = "주거 구역"; 
		marketplace = "상업 구역"; 
		powerplant = "발전소"; 
		warehouse = "저장소"; 
		farm = "농장"; 
		clinic = "치료소"; 
		academy = "교육원"; 
		cost = "비용"; 
		policy = "정책"; 
		make_active = "활성화"; 
		make_deactive = "비활성화";
		economy = "경제"; 
		etc = "기타"; 
		new_city = "새 도시"; 
		city = "도시";
		budget = "예산";
		population = "인구";
		average = "평균";
		intelligent = "지능";
		strength = "힘";
		minimum = "최소";
		maximum = "최대";
		hp = "체력";
		happiness = "행복도";
		desease = "질병";
		deep = "심도";
		food = "식량";
		production = "생산";
		size = "크기";
		situation = "상황";
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
		need_selectCity = "도시를 선택한 후 다시 시도하십시오.";
		full_slot = "슬롯이 가득 찼습니다.";
		need_selectSection = "구역을 선택한 후 다시 시도하십시오.";
		need_budget = "예산이 부족합니다.";
		on = "on";
		stop_control_city = "도시 제어 중단";
		
		notice_null = "공지사항을 받아올 수 없었습니다.\n"
				+ "\n"
				+ "http://hujinone11.oranc.co.kr\n"
				+ "에서 공지사항과 최신 버전을 확인하실 수 있습니다.";
		input_serial_agreement = "시리얼 코드를 입력하시면 다양한 기능을 사용할 수 있게 됩니다.\n"
				+ "\n"
				+ "http://hujinone11.oranc.co.kr\n"
				+ "에 방문하여 시리얼 번호를 무료로 받으실 수 있습니다.\n";
		help_contents = "Calc\n"
				+ "\n"
				+ "Calc는, 숫자와 기호를 이용한 턴제 전략 카드 게임입니다.\n"
				+ "\n"
				+ "게임을 시작하기 위하여, 플레이어를 선택합니다.\n"
				+ "설정을 바꾸지 않았다면, 최대 4명의 플레이어를 게임에 참여시킬 수 있습니다.\n"
				+ "\"사용자\" 를 선택하면 실제 사용자로 간주하고, 해당 플레이어의 턴이 왔을 때 조작이 가능합니다.\n"
				+ "\"인공지능\" 을 선택하면 해당 플레이어의 턴이 왔을 때 자동으로 조작되고 턴이 넘어갑니다.\n"
				+ "\n"
				+ "게임에 들어가면, 중앙에 각 플레이어의 공간이 주어집니다.\n"
				+ "그 안에 해당 플레이어가 보유한 카드, 그리고 해당 플레이어의 앞에 놓일 카드 목록을 보여주는 공간이 있으며, \n"
				+ "해당 플레이어의 점수와, 놓여진 카드의 맨 위에 놓인 카드가 보이는 공간이 있습니다.\n"
				+ "\n"
				+ "보유한 카드 란은 해당 플레이어의 턴이 왔을 때만 보여집니다.\n"
				+ "\n"
				+ "게임이 시작되면, 각 플레이어들은 10장을 받게 되며, 덱에는 나머지 카드들이 보이지 않게 쌓여집니다.\n"
				+ "덱에 있는 카드의 수는 화면 상단에 표시됩니다.\n"
				+ "\n"
				+ "플레이어의 턴이 되면, 카드를 한 장 내거나, 카드를 한 장 덱에서 가져와야 합니다.\n"
				+ "카드를 내려면, 자기자신 또는 다른 플레이어의 앞에 놓아야 합니다.\n"
				+ "\n"
				+ "카드를 가져감으로써 턴을 완료하려면, 화면 상단에 있는 버튼을 클릭하면 됩니다.\n"
				+ "\n"
				+ "카드를 내려면, 내려는 곳을 나타내는 \"놓인 카드\" 목록 아래에 있는 버튼을 클릭하면 됩니다.\n"
				+ "해당하는 곳에 이미 카드가 있을 경우, 맨 위(화면에는 맨 아래)에 놓인 카드와 숫자 또는 기호가 같은 카드만을 놓을 수 있습니다.\n"
				+ "\n"
				+ "숫자가 7인 카드가 맨 위에 있는 곳에는 그 곳의 주인만이 다음 카드를 놓을 수 있습니다.\n"
				+ "숫자가 1인 카드는 7로 보호되지 않은 그 어떤 곳에도 놓을 수 있습니다.\n"
				+ "\n"
				+ "§ 기호를 가진 카드는 곱셈의 의미를 가지고 있으나, 대상 플레이어의 소유한 카드와 낸 사람의  소유한 카드를 전부 교환하는 능력을 가집니다.\n"
				+ "\n"
				+ "단 한 명이라도 보유한 카드가 없거나, 덱에 카드가 다 떨어지면, 게임이 종료되고 점수가 계산됩니다.\n"
				+ "\n"
				+ "맨 아래의 카드(화면에는 맨 위)의 경우, 숫자를 점수에 더합니다.\n"
				+ "그 다음 카드부터, 기호에 해당하는 연산자에 따라 숫자를 계산하여 점수를 계산합니다.\n"
				+ "\n"
				+ "맨 마지막에, 카드를 가장 적게 보유한 플레이어는 5점을 추가로 얻습니다.\n"
				+ "\n"
				+ "\n"
				+ "Made by HJOW\n"
				+ "hujinone22@naver.com\n"
				+ "http://hujinone11.oranc.co.kr";
		
		menu_file = "파일";
		build_warn = "한 번 건설된 구역은 전력 부족으로만 파괴되며, 운영자가 직접 파괴할 수는 없습니다.\n"
				+ "구역 건설 시 유의하십시오 !";
		
		description = new String[33];
		description[0] = " 이(가) 카드를 1장 가져갑니다.";
		description[1] = " 이(가) 다음 장소에 카드를 1장 냅니다 : ";
		description[2] = "의 놓인 카드 슬롯";
		description[3] = "Calc 플레이가 준비되었습니다.";
		description[4] = "의 턴 시작";
		description[5] = "의 턴 종료";
		description[6] = "숫자 7 카드에 의해 보호된 곳입니다. 다른 곳에 놓으십시오.";
		description[7] = " 카드와 숫자 혹은 기호가 같은 카드를 선택하십시오.";
		description[8] = "카드를 선택하지 않아 놓을 수 없었습니다.";
		description[9] = "설정 파일의 버전이 달라 불러올 수 없습니다.";
		description[10] = "보유한 카드가 1장인 플레이어 : ";
		description[11] = "게임이 시작되었습니다.";
		description[12] = "공지사항을 더 이상 보이지 않게 설정할까요?";
		description[13] = "의 암호를 입력하십시오.";
		description[14] = "암호가 틀렸습니다.";
		description[15] = "이(가) 선택되었습니다.";
		description[16] = "베팅된 금액 : ";
		description[17] = "얼마나 베팅하시겠습니까? 현재 베팅 설정된 금액 : ";
		description[18] = "인증 게임을 하여야 인증 코드가 나옵니다.";
		description[19] = "점수 인증 코드를 이 곳에 입력해 주세요.";
		description[20] = "다시 입력해 주세요.";
		description[21] = "인증 게임이라도 승리하여야 인증받을 수 있습니다.";
		description[22] = " 차례입니다.";
		description[23] = "";
		description[24] = " 에게 카드를 냅니다. 마지막 놓인 카드 : ";
		description[25] = "덱에서 카드를 가져갑니다. 덱에 있는 카드 수 : ";
		description[26] = "";
		description[27] = " 의 소유한 카드들을 ";
		description[28] = " 의 것과 바꿉니다.";
		description[29] = "게임이 끝났습니다.";
		description[30] = "검증을 시작합니다.";
		description[31] = "트럼프 모드 활성화";
		description[32] = "트럼프 모드 비활성화";
 	}	
	@Override
	public int getType()
	{
		return Language.LANG_KOREAN;
	}	
}
