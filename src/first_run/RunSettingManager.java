package first_run;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import main_classes.Openable;


public class RunSettingManager extends Frame implements ActionListener, WindowListener, Openable
{
	private static final long serialVersionUID = 8020721345591087297L;
	private boolean independence = true;
	private Panel mainPanel;
	private Panel downPanel;
	private Panel centerPanel;
	private Panel[] pns;
	private Label[] labels;
	private MenuBar menubar;
	private Menu menu_file;
	private MenuItem menu_file_exit;
	private MenuItem menu_file_save;
	private TextField urlField;
	private TextField pathField;
	private Checkbox always_download_check;
	private SettingForRun set, target;
	public RunSettingManager(boolean indepencence)
	{
		this.independence = indepencence;
		init();
	}
	private void init()
	{
		this.addWindowListener(this);
		this.setSize(350, 150);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2 - this.getWidth()/2), (int)(screenSize.getHeight()/2 - this.getHeight()/2));
		mainPanel = new Panel();
		this.setLayout(new BorderLayout());
		this.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		downPanel = new Panel();
		centerPanel = new Panel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		menubar = new MenuBar();
		this.setMenuBar(menubar);
		menu_file = new Menu("파일");
		menubar.add(menu_file);
		menu_file_save = new MenuItem("저장");
		menu_file_exit = new MenuItem("종료");
		menu_file_save.addActionListener(this);
		menu_file_exit.addActionListener(this);
		menu_file.add(menu_file_save);
		menu_file.add(menu_file_exit);
		
		pns = new Panel[3];
		labels = new Label[pns.length];
		centerPanel.setLayout(new GridLayout(pns.length, 1));
		for(int i=0; i<pns.length; i++)
		{
			pns[i] = new Panel();
			labels[i] = new Label();
			pns[i].setLayout(new FlowLayout());
			pns[i].add(labels[i]);
			
			centerPanel.add(pns[i]);
		}
		labels[0].setText("기본 URL");
		labels[1].setText("파일 경로");
		labels[2].setText("");
		
		urlField = new TextField(25);
		pathField = new TextField(25);
		pns[0].add(urlField);
		pns[1].add(pathField);
		
		always_download_check = new Checkbox("항상 다운로드");
		pns[2].add(always_download_check);
		
		set = new SettingForRun();
		target = new SettingForRun();
		
		urlField.setText(set.getBasic_url());
		pathField.setText(set.getDefault_path());
	}
	
	private void save() throws Exception
	{
		target.setBasic_url(urlField.getText());
		target.setDefault_path(pathField.getText());
		target.setExec_path(target.getDefault_path() + "calc_game.jar");	
		target.setAlways_download(always_download_check.getState());
		target.refresh_authorize();
		String path = set.getDefault_path();
		FileDialog fileDialog = new FileDialog(this, "Save", FileDialog.SAVE);
		fileDialog.setVisible(true);
		path = fileDialog.getDirectory() + fileDialog.getFile();
		if(fileDialog.getFile() == null || fileDialog.getFile().equals(""))
		{
			
		}
		else
		{
			if(! path.endsWith(".crst"))
			{
				path += ".crst";
			}
			save_file(target, path);
			System.out.println("성공");
		}
	}
	private void save_file(SettingForRun tg, String path) throws Exception
	{
		tg.refresh_authorize();
		File save_target = new File(set.getDefault_path());
		if(! save_target.exists()) save_target.mkdir();
		FileOutputStream newOut = new FileOutputStream(path);
		ObjectOutputStream obout = new ObjectOutputStream(newOut);
		obout.writeObject(tg);
		obout.close();
		newOut.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_file_exit)
		{
			exit();
		}
		else if(ob == menu_file_save)
		{
			try
			{
				save();				
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
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
