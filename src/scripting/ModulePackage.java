package scripting;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class ModulePackage implements Serializable
{
	private static final long serialVersionUID = -5504041955273587726L;
	private String name = "";
	private String description = "";
	private Long authorize;
	private Vector<ScriptModule> modules;
	public ModulePackage()
	{
		modules = new Vector<ScriptModule>();
		authorize = new Long(0);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Vector<ScriptModule> getModules()
	{
		return modules;
	}
	public void setModules(Vector<ScriptModule> modules)
	{
		this.modules = modules;
	}
	public static ModulePackage load(File path) throws Exception
	{
		String fileName = path.getAbsolutePath();
		ModulePackage gets = null;
		FileInputStream fin = new FileInputStream(path);
		ObjectInputStream oin = null;
		XMLDecoder decoder = null;
		if(fileName.endsWith(".cpack") || fileName.endsWith(".CPACK"))
		{
			try
			{
				oin = new ObjectInputStream(fin);
				gets = (ModulePackage) oin.readObject();
				oin.close();
			} 
			catch (Exception e)
			{
				try
				{
					oin.close();
				} 
				catch (Exception e1){}
				try
				{
					fin.close();
				}
				catch (Exception e1){}
				throw e;
			}
		}
		else
		{
			try
			{
				decoder = new XMLDecoder(fin);
				gets = (ModulePackage) decoder.readObject();
				decoder.close();
			} 
			catch (Exception e)
			{
				try
				{
					decoder.close();
				}
				catch(Exception e1) { }
				try
				{
					fin.close();
				}
				catch(Exception e1) { }
				throw e;
			}
		}
		fin.close();
		return gets;
	}
	public static void save(ModulePackage packs, File path, boolean xml) throws Exception
	{
		FileOutputStream fout = new FileOutputStream(path);
		if(xml)
		{
			XMLEncoder encoder = new XMLEncoder(fout);
			encoder.writeObject(packs);
			encoder.close();
		}
		else
		{
			ObjectOutputStream objectOutput = new ObjectOutputStream(fout);
			objectOutput.writeObject(packs);
			objectOutput.close();
		}
		fout.close();
	}
	public Long getAuthorize()
	{
		return authorize;
	}
	public void setAuthorize(Long authorize)
	{
		this.authorize = authorize;
	}
	public ModulePackage clone()
	{
		ModulePackage newOne = new ModulePackage();
		newOne.setName(new String(name));
		newOne.setDescription(new String(description));
		newOne.authorize = new Long(authorize.longValue());
		newOne.modules = new Vector<ScriptModule>();
		for(int i=0; i<modules.size(); i++)
		{
			newOne.modules.add(modules.get(i).clone());
		}
		return newOne;
	}
}
