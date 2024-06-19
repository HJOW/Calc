package main_classes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import lang.Language;
import network.OnlineContentURLList;
import setting.Key;
import setting.KeyBlock;
import setting.Setting;

public class Creator extends JFrame implements WindowListener, ActionListener, Openable
{
	private static final long serialVersionUID = 2716636340187283151L;
	public boolean independence = false;
	private Dimension screenSize;
	private Setting set;
	private JPanel mainPanel;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel centerAIPanel;
	private JPanel centerSetPanel;
	private JScrollPane centerAIScroll;
	private JScrollPane centerSetScroll;
	private JPanel[] centerAIPns;
	private JPanel[] centerSetPns;
	public int ais = 0, sets = 0, ultimate_keys = 0;
	private JTextField[] centerAIField;
	private JTextField[] centerSetField;
	private JLabel centerAILabel;
	private JLabel centerSetLabel;
	private JButton ai_add;
	private JButton set_add;
	private JButton close;
	private JSpinner ai_field;
	private JSpinner set_field;
	private JButton save;
	private FileFilter ff1;
	private JFileChooser cfd;
	private OnlineContentURLList target;
	private JTextField nameField;
	private JButton load;
	private JPanel centerKeyPanel;
	private JScrollPane centerKeyScroll;
	private JPanel[] centerKeyPns;
	private JTextField[] centerKeyField;
	private JLabel centerKeyLabel;
	private JSpinner key_field;
	private JButton key_add;
	private FileFilter ff2;
	
	public Creator(boolean independence, Setting set)
	{
		this.independence = independence;
		this.set = set.clone();
		init();
	}
	public void init()
	{
		this.setSize(680, 600);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		this.addWindowListener(this);
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		this.setTitle("URL Creator");
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();		
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		center_init();
		
		upPanel.setLayout(new FlowLayout());
		nameField = new JTextField(10);
		upPanel.add(nameField);
		
		downPanel.setLayout(new FlowLayout());
		close = new JButton(set.getLang().getText(Language.CLOSE));
		close.addActionListener(this);		
		save = new JButton(set.getLang().getText(Language.SAVE));
		save.addActionListener(this);
		load = new JButton(set.getLang().getText(Language.LOAD));
		load.addActionListener(this);
		downPanel.add(save);
		downPanel.add(load);
		downPanel.add(close);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == ai_add)
		{
			ais = (Integer) ai_field.getValue();
			this.setVisible(false);
			center_init();
			this.setVisible(true);
		}
		else if(ob == set_add)
		{
			sets = (Integer) set_field.getValue();
			this.setVisible(false);
			center_init();
			this.setVisible(true);
		}
		else if(ob == key_add)
		{
			ultimate_keys = (Integer) key_field.getValue();
			this.setVisible(false);
			center_init();
			this.setVisible(true);
		}
		else if(ob == close)
		{
			exit();
		}
		else if(ob == save)
		{
			save_ready();
			ff1 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".curl")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "URL Data (.curl)";
				}
			};	
			ff2 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".curt")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "URL Text (.curt)";
				}
			};	
			
			if(cfd == null) 
			{
				cfd = new JFileChooser();
				cfd.setFileFilter(ff1);
				cfd.addChoosableFileFilter(ff2);
			}
			int cfds = cfd.showSaveDialog(this);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd.getSelectedFile();
				String newFileName = newFile.getAbsolutePath();
				if(newFileName.endsWith(".curl"))
						url_save(newFile);
				else if(newFileName.endsWith(".curt"))
				{
					url_save_text(newFile);
				}
				else
				{
					newFileName = newFileName + ".curl";
					newFile = new File(newFileName);
					url_save(newFile);
				}
			}
		}
		else if(ob == load)
		{
			ff1 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".curl")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "URL Data (.curl)";
				}
			};			
			
			if(cfd == null) 
			{
				cfd = new JFileChooser();
				cfd.setFileFilter(ff1);
			}
			int cfds = cfd.showOpenDialog(this);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd.getSelectedFile();
				String newFileName = newFile.getAbsolutePath();
				if(newFileName.endsWith(".curl"))
						url_load(newFile);
				else
				{
					newFileName = newFileName + ".curl";
					newFile = new File(newFileName);
					url_load(newFile);
				}
			}
		}
	}
	private void save_ready()
	{
		target = new OnlineContentURLList();
		for(int i=0; i<ais; i++)
		{
			target.getAi_contents().add(centerAIField[i].getText());
		}
		for(int i=0; i<sets; i++)
		{
			target.getSetting_contents().add(centerSetField[i].getText());
		}
		Key newKey;
		StringTokenizer stokens;
		for(int i=0; i<ultimate_keys; i++)
		{
			newKey = new Key();
			stokens = new StringTokenizer(centerKeyField[i].getText(), "-");
			String getTokens = "";
			try
			{
				for(int j=0; j<newKey.getChars().length; j++)
				{
					getTokens = stokens.nextToken();
					newKey.getChars()[j] = new KeyBlock(getTokens);
				}
			} 
			catch(ArrayIndexOutOfBoundsException e)
			{
				
			}
			catch(NoSuchElementException e)
			{
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			target.getUltimate_keys().add(newKey);
		}
		target.setName(nameField.getText());
			
	}
	public void url_load(File path)
	{
		try
		{
			FileInputStream fin = new FileInputStream(path);
			XMLDecoder decoder = new XMLDecoder(fin);
			target = (OnlineContentURLList) decoder.readObject();
			decoder.close();
			fin.close();
			ais = target.getAi_contents().size();
			sets = target.getSetting_contents().size();
			ultimate_keys = target.getUltimate_keys().size();
			this.setVisible(false);
			center_init();
			for(int i=0; i<ais; i++)
			{
				centerAIField[i].setText(target.getAi_contents().get(i));
			}
			for(int i=0; i<sets; i++)
			{
				centerSetField[i].setText(target.getSetting_contents().get(i));
			}
			String temps = "";
			for(int i=0; i<ultimate_keys; i++)
			{
				temps = "";
				for(int j=0; j<target.getUltimate_keys().get(i).getChars().length; j++)
				{
					temps += target.getUltimate_keys().get(i).getChars()[j];
					if(j < target.getUltimate_keys().get(i).getChars().length - 1) temps += "-";
				}
				centerKeyField[i].setText(temps);
			}
			this.setVisible(true);
			
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void url_save(File path)
	{
		try
		{
			FileOutputStream fin = new FileOutputStream(path);
			XMLEncoder encoder = new XMLEncoder(fin);
			encoder.writeObject(target);
			encoder.close();
			fin.close();
		} 
		catch (Exception e)
		{			
			e.printStackTrace();
		}
	}
	public void url_save_text(File path)
	{
		String saves = "";
		String strs = "";
	
		saves = saves + "0|" + target.getName() + "\n";
		saves = saves + "10|" + String.valueOf(target.getVer_main().intValue()) + "\n";
		saves = saves + "11|" + String.valueOf(target.getVer_sub1().intValue()) + "\n";
		saves = saves + "12|" + String.valueOf(target.getVer_sub2().intValue()) + "\n";
		for(int i=0; i<target.getAi_contents().size(); i++)
		{
			saves = saves + "20|" + target.getAi_contents().get(i) + "\n";
		}
		for(int i=0; i<target.getSetting_contents().size(); i++)
		{
			saves = saves + "30|" + target.getSetting_contents().get(i) + "\n";
		}
		for(int i=0; i<target.getUltimate_keys().size(); i++)
		{
			strs = "";
			for(int j=0; j<target.getUltimate_keys().get(i).getChars().length; j++)
			{
				strs = strs + new String(target.getUltimate_keys().get(i).getChars()[j].blocks);
				if(j < target.getUltimate_keys().get(i).getChars().length - 1) strs = strs + "-";
			}
			
			saves = saves + "40|" + OnlineContentURLList.crypt(strs) + "\n";
		}
		for(int i=0; i<target.getAbandoned_keys().size(); i++)
		{
			strs = "";
			for(int j=0; j<target.getAbandoned_keys().get(i).getChars().length; j++)
			{
				strs = strs + new String(target.getAbandoned_keys().get(i).getChars()[i].blocks);
				if(j < target.getAbandoned_keys().get(i).getChars().length - 1) strs = strs + "-";
			}
			
			saves = saves + "41|" + OnlineContentURLList.crypt(strs) + "\n";
		}
		FileOutputStream fin = null;
		OutputStreamWriter wrt = null;
		BufferedWriter bfw = null;
		try
		{
			fin = new FileOutputStream(path);
			wrt = new OutputStreamWriter(fin);
			bfw = new BufferedWriter(wrt);
			bfw.write(saves);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			bfw.close();
			wrt.close();
			fin.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public void center_init()
	{
		centerPanel.removeAll();
		centerAIPns = new JPanel[ais + 1];
		centerSetPns = new JPanel[sets + 1];
		centerKeyPns = new JPanel[ultimate_keys + 1];
		centerPanel.setLayout(new GridLayout(3, 1));
		centerAIPanel = new JPanel();
		centerSetPanel = new JPanel();
		centerKeyPanel = new JPanel();
		centerAIScroll = new JScrollPane(centerAIPanel);
		centerSetScroll = new JScrollPane(centerSetPanel);
		centerKeyScroll = new JScrollPane(centerKeyPanel);
		centerPanel.add(centerAIScroll);
		centerPanel.add(centerSetScroll);
		centerPanel.add(centerKeyScroll);
		centerAIField = new JTextField[centerAIPns.length];
		centerSetField = new JTextField[centerSetPns.length];
		centerKeyField = new JTextField[centerKeyPns.length];
		centerAIPanel.setLayout(new GridLayout(centerAIPns.length, 1));
		centerSetPanel.setLayout(new GridLayout(centerSetPns.length, 1));
		centerKeyPanel.setLayout(new GridLayout(centerKeyPns.length, 1));
		centerAIPns[0] = new JPanel();
		centerSetPns[0] = new JPanel();
		centerKeyPns[0] = new JPanel();
		centerAILabel = new JLabel("AI URL List");
		centerSetLabel = new JLabel("Setting URL List");
		centerKeyLabel = new JLabel("Ultimate Key List");
		ai_field = new JSpinner();
		set_field = new JSpinner();
		key_field = new JSpinner();
		ai_field.setValue(ais);
		set_field.setValue(sets);
		key_field.setValue(ultimate_keys);
		ai_add = new JButton(set.getLang().getText(Language.ACCEPT));
		ai_add.addActionListener(this);
		set_add = new JButton(set.getLang().getText(Language.ACCEPT));
		set_add.addActionListener(this);		
		key_add = new JButton(set.getLang().getText(Language.ACCEPT));
		key_add.addActionListener(this);
		centerAIPns[0].setLayout(new FlowLayout());
		centerSetPns[0].setLayout(new FlowLayout());
		centerKeyPns[0].setLayout(new FlowLayout());
		centerAIPns[0].add(centerAILabel);
		centerSetPns[0].add(centerSetLabel);
		centerKeyPns[0].add(centerKeyLabel);
		centerAIPns[0].add(ai_field);
		centerSetPns[0].add(set_field);
		centerKeyPns[0].add(key_field);
		centerAIPns[0].add(ai_add);
		centerSetPns[0].add(set_add);
		centerKeyPns[0].add(key_add);
		centerAIPanel.add(centerAIPns[0]);
		centerSetPanel.add(centerSetPns[0]);
		centerKeyPanel.add(centerKeyPns[0]);
		for(int i=1; i<centerAIPns.length; i++)
		{
			centerAIPns[i] = new JPanel();
			centerAIField[i-1] = new JTextField();
			centerAIPns[i].setLayout(new BorderLayout());
			centerAIPns[i].add(centerAIField[i-1]);
			centerAIPanel.add(centerAIPns[i]);
		}
		for(int i=1; i<centerSetPns.length; i++)
		{
			centerSetPns[i] = new JPanel();
			centerSetField[i-1] = new JTextField();
			centerSetPns[i].setLayout(new BorderLayout());
			centerSetPns[i].add(centerSetField[i-1]);
			centerSetPanel.add(centerSetPns[i]);
		}
		for(int i=1; i<centerKeyPns.length; i++)
		{
			centerKeyPns[i] = new JPanel();
			centerKeyField[i-1] = new JTextField();
			centerKeyPns[i].setLayout(new BorderLayout());
			centerKeyPns[i].add(centerKeyField[i-1]);
			centerKeyPanel.add(centerKeyPns[i]);
		}
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		exit();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void open()
	{
		this.setVisible(true);
	}


	@Override
	public void exit()
	{
		if(independence) System.exit(0);
		else this.setVisible(false);
	}
}
