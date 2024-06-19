package lang;

public class UserDefinedKor extends Korean
{
	private static final long serialVersionUID = 3990689272386317090L;
	private String languagePackage = "";
	private Long authority_code = new Long(0);
	public UserDefinedKor()
	{
		super();
	}
	public UserDefinedKor(String contents)
	{
		super();
		languagePackage = contents;
	}
	@Override
	public int getType()
	{
		return LANG_USER_DEFINED;
	}
	@Override
	public String getText(int index)
	{
		String result = null;
		if(languagePackage != null)
		{
			String[] lines = languagePackage.split("\n");
			String[] tokened = null;
			int flag = -1;
			StringBuffer multiline = new StringBuffer("");
			boolean existed = false;
			for(int i=0; i<lines.length; i++)
			{
				try
				{
					if(lines[i].startsWith("#")) continue;
					tokened = lines[i].split("=");
					flag = Integer.parseInt(tokened[0].trim());
					
					if(index == flag)
					{
						multiline = multiline.append(tokened[1].trim());
						multiline = multiline.append("\n");
						existed = true;
					}
				} 
				catch (Exception e)
				{
					
				}
			}
			if(existed)
			{
				result = new String(multiline);		
			}
			else
			{
				return super.getText(index);
			}
		}
		
		return result;
	}
	public String getLanguagePackage()
	{
		return languagePackage;
	}
	public void setLanguagePackage(String languagePackage)
	{
		this.languagePackage = languagePackage;
	}
	public static Language convert(String str)
	{
		return new UserDefinedKor(str);
	}
	public static String convert(Language lang)
	{
		int[] list = lang.getList();
		Language sample = null;
		String loc;
		try
		{
			loc = System.getProperty("user.language");
			if(loc.equalsIgnoreCase("ko") || loc.equalsIgnoreCase("kr") || loc.equalsIgnoreCase("kor") || loc.equalsIgnoreCase("korean"))
			{
				sample = new Korean();
			}
			else if(loc.equalsIgnoreCase("en") || loc.equalsIgnoreCase("eng") || loc.equalsIgnoreCase("english"))
			{
				sample = new English();
			}
			else
			{
				sample = new English();
			}
		} 
		catch (Exception e1)
		{
			
		}
		String result = "# Language" + String.format("%n");
		String[] splitTarget = null;
		String target = null;
		for(int i=0; i<list.length; i++)
		{			
			try
			{
				target = lang.getText(list[i]);
				if(target == null) target = sample.getText(list[i]);
				if(target == null) target = "NONE";
				//System.out.println(i + ", " + target);
				splitTarget = target.split("\n");
				for(int j=0; j<splitTarget.length; j++)
				{
					try
					{
						result = result + String.valueOf(list[i]) + "=" + splitTarget[j] + String.format("%n");
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			} 
			catch (Exception e)
			{				
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Language clone()
	{
		UserDefinedKor newOne = new UserDefinedKor(new String(this.languagePackage));
		newOne.setAuthority_code(new Long(this.authority_code.longValue()));
		return newOne;
	}
	public Long getAuthority_code()
	{
		return authority_code;
	}
	public void setAuthority_code(Long authority_code)
	{
		this.authority_code = authority_code;
	}
}
