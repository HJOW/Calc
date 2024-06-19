package main_classes;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import lang.Language;
import setting.Setting;

public class User_Block extends JPanel
{
	private static final long serialVersionUID = -6082027698555535017L;
	private JLabel nameLabel;
	private JTextField nameField;
	public JButton selectButton;
	public JButton removeButton;
	private JLabel creditLabel;
	private JTextField creditField;
	private Setting sets;
	public boolean selected = false;
	public JButton betButton;

	public User_Block()
	{
		super();
	}
	public User_Block(String name, Setting sets)
	{
		super();
		this.setLayout(new FlowLayout());
		this.setBorder(new EtchedBorder());
		this.setBackground(sets.getSelected_back());
		this.sets = sets.clone();
		nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		nameField = new JTextField(8);
		creditLabel = new JLabel(sets.getLang().getText(Language.CREDIT));
		creditField = new JTextField(10);
		selectButton = new JButton(sets.getLang().getText(Language.SELECT));
		removeButton = new JButton("-");
		removeButton.setForeground(sets.getSelected_fore());
		betButton = new JButton(sets.getLang().getText(Language.BET));
		betButton.setForeground(sets.getSelected_fore());
		nameLabel.setForeground(sets.getUnselected_fore());
		creditLabel.setForeground(sets.getUnselected_fore());
		nameField.setBackground(sets.getUnselected_inside_back());
		nameField.setForeground(sets.getUnselected_fore());
		creditField.setBackground(sets.getUnselected_inside_back());
		creditField.setForeground(sets.getUnselected_fore());
		nameField.setEditable(false);
		creditField.setEditable(false);
		if(CalcWindow.usingFont != null)
		{
			nameLabel.setFont(CalcWindow.usingFont);
			nameField.setFont(CalcWindow.usingFont);
			creditLabel.setFont(CalcWindow.usingFont);
			creditField.setFont(CalcWindow.usingFont);
			selectButton.setFont(CalcWindow.usingFont);
			removeButton.setFont(CalcWindow.usingFont);
			betButton.setFont(CalcWindow.usingFont);
		}
		this.add(nameLabel);
		this.add(nameField);
		this.add(creditLabel);
		this.add(creditField);
		this.add(betButton);
		this.add(selectButton);
		this.add(removeButton);
	}
	public void setSelected(boolean sel)
	{
		if(sel)
		{
			nameLabel.setForeground(sets.getUnselected_fore());
			creditLabel.setForeground(sets.getUnselected_fore());
			nameField.setBackground(sets.getUnselected_inside_back());
			nameField.setForeground(sets.getUnselected_fore());
			creditField.setBackground(sets.getUnselected_inside_back());
			creditField.setForeground(sets.getUnselected_fore());
			selectButton.setEnabled(false);
		}
		else
		{
			nameLabel.setForeground(sets.getSelected_fore());
			creditLabel.setForeground(sets.getSelected_fore());
			nameField.setBackground(sets.getSelected_inside_back());
			nameField.setForeground(sets.getSelected_fore());
			creditField.setBackground(sets.getSelected_inside_back());
			creditField.setForeground(sets.getSelected_fore());
			selectButton.setEnabled(true);
		}
		selected = sel;
	}
	public void setName(String name)
	{
		nameField.setText(name);
	}
	public void setCredit(long credit)
	{
		creditField.setText(String.valueOf(credit));
	}
	public void setUser(User user)
	{
		setName(user.getName());
		setCredit(user.getAccumulated_point());
	}
}
