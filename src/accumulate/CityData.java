package accumulate;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import main_classes.CalcWindow;
import setting.Lint;

public class CityData implements Serializable
{
	private static final long serialVersionUID = -8723768529779992521L;
	private Vector<City> cities = null;
	private Integer ver_main = null;
	private Integer ver_sub1 = null;
	private Integer ver_sub2 = null;
	public CityData()
	{
		ver_main = new Integer(CalcWindow.version_main);
		ver_sub1 = new Integer(CalcWindow.version_sub_1);
		ver_sub2 = new Integer(CalcWindow.version_sub_2);
		cities = new Vector<City>();
	}
	
	public Integer getVer_main()
	{
		return ver_main;
	}
	public void setVer_main(Integer ver_main)
	{
		this.ver_main = ver_main;
	}
	public Integer getVer_sub1()
	{
		return ver_sub1;
	}
	public void setVer_sub1(Integer ver_sub1)
	{
		this.ver_sub1 = ver_sub1;
	}
	public Integer getVer_sub2()
	{
		return ver_sub2;
	}
	public void setVer_sub2(Integer ver_sub2)
	{
		this.ver_sub2 = ver_sub2;
	}

	public Vector<City> getCities()
	{
		return cities;
	}

	public void setCities(Vector<City> cities)
	{
		this.cities = cities;
	}
	
	public BigInteger newId()
	{
		BigInteger result = Lint.big(0);
		if(cities.size() <= 0) return result;
		else
		{
			for(int i=0; i<cities.size(); i++)
			{
				if(cities.get(i).getId().compareTo(result) == 0)
				{
					result = result.add(Lint.big(1));
					i = 0;
				}
			}
			return result;
		}
	}
}
