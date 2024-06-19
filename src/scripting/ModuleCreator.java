package scripting;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import lang.Language;
import main_classes.Openable;
import setting.Setting;

public class ModuleCreator extends JFrame implements Openable, ActionListener, ItemListener, WindowListener, ListSelectionListener
{
	private static final long serialVersionUID = 8390815520988059477L;
	private JPanel mainPanel;
	public ModulePackage target;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton save;
	private JButton close;
	private JPanel centerLeft;
	private JPanel centerRight;
	private JList moduleList;
	private JScrollPane moduleScroll;
	private JTextArea descriptionPanel;
	private JScrollPane descriptionScroll;
	private JPanel moduleControlPanel;
	private JButton addModule;
	private JButton removeModule;
	private int selectedIndex = 0;
	private boolean selected = false;
	private JFileChooser cfd;
	public Setting sets;
	private SingleModuleCreator moduleCreator;
	public ScriptModule single_target;

	public ModuleCreator(Setting sets)
	{
		this.setSize(500, 400);
		this.setLocation((int)(sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.addWindowListener(this);
		this.setTitle(sets.getLang().getText(Language.MODULE) + " " + sets.getLang().getText(Language.EDIT));
		mainPanel = new JPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);		
		mainPanel.setLayout(new BorderLayout());
		
		this.sets = sets.clone();
		
		upPanel = new JPanel();
		downPanel = new JPanel();
		centerPanel = new JPanel();
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		centerPanel.setLayout(new GridLayout(1, 2));
		upPanel.setLayout(new FlowLayout());
		downPanel.setLayout(new FlowLayout());
		
		nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		nameField = new JTextField(10);
		upPanel.add(nameLabel);
		upPanel.add(nameField);
		
		save = new JButton(sets.getLang().getText(Language.SAVE));
		close = new JButton(sets.getLang().getText(Language.EXIT));
		save.addActionListener(this);
		close.addActionListener(this);
		downPanel.add(save);
		downPanel.add(close);
		
		centerLeft = new JPanel();
		centerRight = new JPanel();
		centerPanel.add(centerLeft);
		centerPanel.add(centerRight);
		
		centerLeft.setLayout(new BorderLayout());
		centerRight.setLayout(new BorderLayout());
		
		moduleList = new JList();
		moduleScroll = new JScrollPane(moduleList);
		moduleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centerLeft.add(moduleScroll, BorderLayout.CENTER);
		moduleControlPanel = new JPanel();
		centerLeft.add(moduleControlPanel, BorderLayout.SOUTH);
		moduleControlPanel.setLayout(new FlowLayout());
		addModule = new JButton(sets.getLang().getText(Language.ADD));
		removeModule = new JButton(sets.getLang().getText(Language.REMOVE));
		addModule.addActionListener(this);
		removeModule.addActionListener(this);
		moduleControlPanel.add(addModule);
		moduleControlPanel.add(removeModule);
		moduleList.addListSelectionListener(this);
		
		descriptionPanel = new JTextArea();
		descriptionPanel.setLineWrap(true);
		descriptionScroll = new JScrollPane(descriptionPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerRight.add(descriptionScroll, BorderLayout.CENTER);
		
		moduleCreator = new SingleModuleCreator(this);
		
		target = new ModulePackage();
		refresh();
	}
	public void refresh()
	{
		selected = false;
		String[] listData = new String[target.getModules().size()];
		for(int i=0; i<listData.length; i++)
		{
			listData[i] = new String(target.getModules().get(i).getName());
		}
		moduleList.setListData(listData);
	}
	public void newCancel()
	{
		
	}
	public void newOk()
	{
		target.getModules().add(single_target);
		refresh();
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == save)
		{
			FileFilter ff1 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".cpack")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Module Package (.cpack)";
				}
			};
			FileFilter ff2 = new FileFilter()
			{
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".xml")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Module Package XML (.xml)";
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
				String newFilePath = newFile.getAbsolutePath();
				FileOutputStream fout = null;
				try
				{
					fout = new FileOutputStream(newFile);
					if(newFilePath.endsWith(".xml") || newFilePath.endsWith(".XML"))
					{
						XMLEncoder encoder = new XMLEncoder(fout);
						encoder.writeObject(target);
						encoder.close();
					}
					else
					{
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(target);
						oout.close();
					}
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
				}
				finally
				{
					try
					{
						fout.close();
					} 
					catch (IOException e1)
					{
						
					}
				}
			}
		}
		else if(ob == close)
		{
			exit();
		}
		else if(ob == addModule)
		{
			single_target = null;
			moduleCreator.open();
		}
		else if(ob == removeModule)
		{
			if(selected)
			{
				target.getModules().remove(selectedIndex);
				refresh();
			}
		}
	}
	@Override
	public void open()
	{
		this.setVisible(true);
		
	}

	@Override
	public void exit()
	{
		System.exit(0);
		
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
	public void itemStateChanged(ItemEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{		
		selectedIndex = moduleList.getSelectedIndex();
		selected = true;
	}
}
class SingleModuleCreator extends JDialog implements ActionListener, WindowListener, Openable
{
	private static final long serialVersionUID = 1047101297466745002L;
	private ModuleCreator upper;
	private JPanel mainPanel;
	private JDialog selectionDialog;
	private JPanel selectionDialog_mainPanel;
	private JPanel selectionDialog_centerPanel;
	private JPanel selectionDialog_downPanel;
	private ButtonGroup selectionDialog_group;
	private JRadioButton selectionDialog_block;
	private JRadioButton selectionDialog_card;
	private JButton selectionDialog_ok;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JTextField nameField;
	private JButton cancel;
	private JButton ok;
	private JScrollPane centerScroll;
	private JPanel centerContentPanel;
	private JTextArea descriptionPanel;
	private JScrollPane descriptionScroll;
	private JTextArea scriptPanel;
	private JScrollPane scriptScroll;
	private JTextArea turnScriptPanel;
	private JScrollPane turnScriptScroll;
	private JTextArea actScriptPanel;
	private JScrollPane actScriptScroll;
	private JPanel controlPanel;
	private JCheckBox useActScriptCheck;
	private JCheckBox actAfterDefaultCheck;

	public SingleModuleCreator(ModuleCreator upper)
	{
		super(upper, true);
		this.setSize(500, 400);
		this.setLocation((int)(upper.sets.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(upper.sets.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.addWindowListener(this);
		this.upper = upper;
		this.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new FlowLayout());
		downPanel.setLayout(new FlowLayout());
		
		nameField = new JTextField(10);
		upPanel.add(nameField);
		
		ok = new JButton(upper.sets.getLang().getText(Language.OK));
		cancel = new JButton(upper.sets.getLang().getText(Language.CANCEL));
		ok.addActionListener(this);
		cancel.addActionListener(this);
		downPanel.add(ok);
		downPanel.add(cancel);
		
				
		selectionDialog = new JDialog(this, true);
		selectionDialog.setSize(300, 150);
		selectionDialog.setLocation((int)(upper.sets.getScreenSize().getWidth()/2 - selectionDialog.getWidth()/2), (int)(upper.sets.getScreenSize().getHeight()/2 - selectionDialog.getHeight()/2));
		selectionDialog_mainPanel = new JPanel();
		selectionDialog.getContentPane().setLayout(new BorderLayout());
		selectionDialog.getContentPane().add(selectionDialog_mainPanel, BorderLayout.CENTER);
		selectionDialog_mainPanel.setLayout(new BorderLayout());
		selectionDialog_centerPanel = new JPanel();
		selectionDialog_downPanel = new JPanel();		
		selectionDialog_mainPanel.add(selectionDialog_centerPanel, BorderLayout.CENTER);
		selectionDialog_mainPanel.add(selectionDialog_downPanel, BorderLayout.SOUTH);
		selectionDialog_centerPanel.setLayout(new FlowLayout());
		selectionDialog_downPanel.setLayout(new FlowLayout());
		selectionDialog_group = new ButtonGroup();
		selectionDialog_block = new JRadioButton(upper.sets.getLang().getText(Language.BLOCK));
		selectionDialog_card = new JRadioButton(upper.sets.getLang().getText(Language.CARD));
		selectionDialog_group.add(selectionDialog_block);
		selectionDialog_group.add(selectionDialog_card);
		selectionDialog_ok = new JButton(upper.sets.getLang().getText(Language.OK));
		selectionDialog_ok.addActionListener(this);
		selectionDialog_downPanel.add(selectionDialog_ok);
		selectionDialog_block.setSelected(true);
		selectionDialog_centerPanel.add(selectionDialog_block);
		selectionDialog_centerPanel.add(selectionDialog_card);
	}

	public void init()
	{
		int grids = 2;
		if(upper.single_target instanceof BlockModule)
		{
			grids = 5;
		}
		
		centerPanel.setLayout(new BorderLayout());
		centerContentPanel = new JPanel();
		centerContentPanel.setLayout(new GridLayout(1, grids));
		centerScroll = new JScrollPane(centerContentPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		centerPanel.add(centerScroll, BorderLayout.CENTER);
		
		// 1
		descriptionPanel = new JTextArea();
		descriptionPanel.setLineWrap(true);
		descriptionPanel.setText("// Input description here");
		descriptionScroll = new JScrollPane(descriptionPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerContentPanel.add(descriptionScroll);
		
		// 2
		scriptPanel = new JTextArea();
		scriptPanel.setLineWrap(true);
		scriptPanel.setText("// Input script here");
		scriptScroll = new JScrollPane(scriptPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerContentPanel.add(scriptScroll);
		
		if(upper.single_target instanceof BlockModule)
		{
			// 3
			controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());
			useActScriptCheck = new JCheckBox(upper.sets.getLang().getText(Language.ACTIVE) + " " + upper.sets.getLang().getText(Language.SCRIPT));
			actAfterDefaultCheck = new JCheckBox(upper.sets.getLang().getText(Language.BASIC_EDITION) + " " + upper.sets.getLang().getText(Language.ACTIVE));
			controlPanel.add(useActScriptCheck);
			controlPanel.add(actAfterDefaultCheck);
			centerContentPanel.add(controlPanel);
			
			// 4
			turnScriptPanel = new JTextArea();
			turnScriptPanel.setLineWrap(true);
			turnScriptPanel.setText("// Input script here, run automatically each turn");
			turnScriptScroll = new JScrollPane(turnScriptPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			centerContentPanel.add(turnScriptScroll);
			
			// 5
			actScriptPanel = new JTextArea();
			actScriptPanel.setLineWrap(true);
			actScriptPanel.setText("// Input script here, run automatically when the player press \"pay\" button.");
			actScriptScroll = new JScrollPane(actScriptPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			centerContentPanel.add(actScriptScroll);
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == selectionDialog_ok)
		{
			if(selectionDialog_block.isSelected()) upper.single_target = new BlockModule(); 
			else if(selectionDialog_block.isSelected()) upper.single_target = new SpecialCardModule();
			init();
			selectionDialog.setVisible(false);
		}
		else if(ob == ok)
		{
			upper.single_target.setName(nameField.getText());
			upper.single_target.setDescription(descriptionPanel.getText());
			upper.single_target.setScripts(scriptPanel.getText());
			if(upper.single_target instanceof BlockModule)
			{
				BlockModule target = (BlockModule) upper.single_target;
				target.setActAfterDefault(new Boolean(actAfterDefaultCheck.isSelected()));
				target.setUseActScript(new Boolean(useActScriptCheck.isSelected()));
				target.setTurnScript(turnScriptPanel.getText());
				target.setActScript(actScriptPanel.getText());
			}
			long code = 0;
			code = (long) Math.round(Math.random() * Integer.MAX_VALUE);
			upper.single_target.setAuthorized(new Long(code));
			upper.newOk();
			this.setVisible(false);
		}
		else if(ob == cancel)
		{
			upper.newCancel();
			this.setVisible(false);
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
		upper.newCancel();
		this.setVisible(false);
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
		selectionDialog.setVisible(true);
		this.setVisible(true);
		
		
	}

	@Override
	public void exit()
	{
		upper.newCancel();
		this.setVisible(false);
		
	}
}
