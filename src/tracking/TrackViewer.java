package tracking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import lang.Language;
import main_classes.Openable;
import setting.Setting;

public class TrackViewer extends JFrame implements Openable, WindowListener, ActionListener
{
	private static final long serialVersionUID = 2829695621109878862L;
	private Setting set;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel controlPanel;
	private TrackStorage target;
	private JButton button_exit;
	private JButton button_load;
	private FileFilter ff1;
	private JFileChooser cfd2;
	private JPanel contentPanel;
	private JScrollPane contentScroll;
	private JPanel insidePanel;
	private BlockPanel[] pns;
	private JPanel upPanel;
	private JLabel osLabel;
	private JTextField versionField;
	private FileFilter ff2;
	
	public TrackViewer(Setting set)
	{
		this.set = set.clone();
		this.addWindowListener(this);
		this.setSize(set.getWidth(), set.getHeight());
		this.setLocation((int)(this.set.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(this.set.getScreenSize().getHeight()/2 - this.getHeight()/2));
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle(set.getLang().getText(Language.TRACKING) + " " + set.getLang().getText(Language.VIEW));
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentScroll = new JScrollPane(contentPanel);
		centerPanel.add(contentScroll);
		insidePanel = new JPanel();
		downPanel.setLayout(new BorderLayout());
		controlPanel = new JPanel();
		downPanel.add(controlPanel, BorderLayout.CENTER);
		controlPanel.setLayout(new FlowLayout());
		button_exit = new JButton(set.getLang().getText(Language.EXIT));
		button_exit.addActionListener(this);
		button_load = new JButton(set.getLang().getText(Language.LOAD));
		button_load.addActionListener(this);		
		controlPanel.add(button_load);
		controlPanel.add(button_exit);
		upPanel.setLayout(new FlowLayout());
		osLabel = new JLabel("OS : " + System.getProperty("os.name"));
		upPanel.add(osLabel);
		versionField = new JTextField(8);
		versionField.setEditable(false);
		upPanel.add(versionField);
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
	public void load_xml(File file)
	{
		try
		{
			FileInputStream inputs = new FileInputStream(file);
			XMLDecoder decoder = new XMLDecoder(inputs);
			target = (TrackStorage) decoder.readObject();
			decoder.close();
			inputs.close();
			JOptionPane.showMessageDialog(this, set.getLang().getText(Language.LOAD) + " " + set.getLang().getText(Language.COMPLETE));
			center_refresh();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		}
		
	}
	public void load_file(File file)
	{
		try
		{
			FileInputStream inputs = new FileInputStream(file);
			ObjectInputStream decoder = new ObjectInputStream(inputs);
			target = (TrackStorage) decoder.readObject();
			decoder.close();
			inputs.close();
			JOptionPane.showMessageDialog(this, set.getLang().getText(Language.LOAD) + " " + set.getLang().getText(Language.COMPLETE));
			center_refresh();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		}
		
	}
	public void center_refresh()
	{
		this.setVisible(false);
		contentPanel.removeAll();
		insidePanel.removeAll();
		insidePanel = new JPanel();
		insidePanel.setLayout(new GridLayout(target.getBlocks().size(), 1));
		pns = new BlockPanel[target.getBlocks().size()];
		for(int i=0; i<pns.length; i++)
		{
			pns[i] = new BlockPanel(target.getBlocks().get(i));
			insidePanel.add(pns[i]);
		}
		
		contentPanel.add(insidePanel);
		versionField.setText("v" + String.valueOf(target.getVer_main()) + "." + String.valueOf(target.getVer_sub1()) + "" + String.valueOf(target.getVer_sub2()) + "" + String.valueOf(target.getVer_test()));
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == button_exit)
		{
			exit();
		}
		else if(ob == button_load)
		{
			ff1 = new FileFilter()
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
					return "Saved Tracking XML (.xml)";
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
						if(file1.getAbsolutePath().endsWith(".ctr")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Saved Tracking File (.ctr)";
				}
			};
			if(cfd2 == null) 
			{
				cfd2 = new JFileChooser(set.getDefault_path());
				cfd2.setFileFilter(ff1);
				cfd2.addChoosableFileFilter(ff2);
			}
			int cfds = cfd2.showOpenDialog(this);
			if(cfds == JFileChooser.APPROVE_OPTION)
			{
				File newFile = cfd2.getSelectedFile();
				String newFileName = newFile.getAbsolutePath();
				try
				{
					if(newFileName.endsWith(".xml"))
						load_xml(newFile);
					else if(newFileName.endsWith(".ctr"))
					{
						load_file(newFile);
					}
					else
					{
						newFileName = newFileName + ".ctr";
						newFile = new File(newFileName);
						load_file(newFile);
					}
				} 
				catch (Exception e1)
				{
					if(set.isError_printDetail()) e1.printStackTrace();
					TrackStorage.addTrack(this.getClass().getName(), e1);
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}	
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
		Object ob = e.getSource();
		if(ob == this)
		{
			exit();
		}
		
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
}
class BlockPanel extends JPanel
{
	private static final long serialVersionUID = 401716435983456087L;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JLabel title;
	private JCheckBox isError;
	private JTextArea stringPanel;
	private JScrollPane stringScroll;
	private JTextField lineNumbers;

	public BlockPanel(TrackBlock block)
	{
		this.setBorder(new EtchedBorder());
		this.setLayout(new BorderLayout());
		upPanel = new JPanel();
		centerPanel = new JPanel();
		this.add(upPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		upPanel.setLayout(new FlowLayout());
		title = new JLabel(block.getClassName());
		upPanel.add(title);
		isError = new JCheckBox("Error");
		isError.setSelected(block.isProblem());
		isError.setEnabled(false);
		upPanel.add(isError);
		lineNumbers = new JTextField(10);
		lineNumbers.setEditable(false);
		lineNumbers.setText(String.valueOf(block.getLine_number()));
		upPanel.add(lineNumbers);
		centerPanel.setLayout(new BorderLayout());
		stringPanel = new JTextArea();
		stringPanel.setLineWrap(true);
		stringPanel.setEditable(false);
		stringPanel.setText(block.getMessage());
		stringScroll = new JScrollPane(stringPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerPanel.add(stringScroll, BorderLayout.CENTER);
	}
}