package main_classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import setting.Setting;
import tracking.TrackStorage;
import ai_algorithm.VirtualBlock;
import lang.Language;


public class Block extends JPanel implements Serializable
{
	private static final long serialVersionUID = 6020372871083134274L;
	private BigInteger point = new BigInteger("0"), virtual_point = new BigInteger("0");
	private CalcWindow window;
	private Block selfthis;
	private String name;
	private boolean ai = false;
	private int ai_difficulty = 1;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel centerLeft;
	private JPanel centerRight;
	private JPanel downLeft;
	private JPanel downRight;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel ownLabel;
	private JLabel paidLabel;
	private JList ownList;
	private JList paidList;
	private Vector<StandardCard> owns;
	private Vector<StandardCard> paids;
	private JScrollPane ownScroll;
	private JScrollPane paidScroll;
	private JButton pay;
	private boolean turn = false;
	private boolean showAll = false;
	private JTextField pointField;
	private JTextField lastCard;
	private JPanel centerBottom;
	private JLabel pointLabel;
	private boolean replay_play = false;
	private JLabel cardCountLabel;
	private JTextField cardCountField;
	private boolean network_guest = false;
	private boolean network_turn = false;
	private JCheckBox nowTurn;
	private JLabel isAI;
	private Setting set;
	private String card_separator;
	private JPanel[] downLeftPns;
	private String[] ownArray;
	private String[] paidArray;
	private String[] empty;
	
	public Block()
	{
		this(null);
	}
	public Block(CalcWindow window)
	{
		owns = new Vector<StandardCard>();
		paids = new Vector<StandardCard>();
		init(window);
	}
	public void init(CalcWindow window)
	{
		TrackStorage.addTrack(this.getClass().getName(), "init("+ window +") started", false);
		selfthis = this;
		this.window = window;
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder());
		if(name == null) name = "";
		if(window != null) set = window.setting.clone();
		else set = new Setting();
		card_separator = new String(set.getCard_separator());
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();		
		
		centerPanel.setLayout(new GridLayout(1, 2));
		downPanel.setLayout(new GridLayout(1, 2));
		upPanel.setLayout(new FlowLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(downPanel, BorderLayout.SOUTH);
		this.add(upPanel, BorderLayout.NORTH);
		
		centerLeft = new JPanel();
		centerRight = new JPanel();
		centerPanel.add(centerLeft);
		centerPanel.add(centerRight);
		centerLeft.setLayout(new BorderLayout());
		centerRight.setLayout(new BorderLayout());
		downLeft = new JPanel();
		downRight = new JPanel();
		downPanel.add(downLeft);
		downPanel.add(downRight);		
		downRight.setLayout(new FlowLayout());
		downLeftPns = new JPanel[2];
		downLeft.setLayout(new GridLayout(downLeftPns.length, 1));
		for(int i=0; i<downLeftPns.length; i++)
		{
			downLeftPns[i] = new JPanel();
			//downLeftPns[i].setLayout(new FlowLayout());
			downLeft.add(downLeftPns[i]);
		}
		
		isAI = new JLabel();
		nameLabel = new JLabel();
		if(window != null) nameLabel.setText(window.lang.getText(Language.NAME) + " ");
		if(CalcWindow.usingFont != null) nameLabel.setFont(CalcWindow.usingFont);
		nameField = new JTextField(10);
		if(CalcWindow.usingFont != null) nameField.setFont(CalcWindow.usingFont);
		nameField.setEditable(false);
		nowTurn = new JCheckBox();
		nowTurn.setEnabled(false);
		
		upPanel.add(isAI);
		upPanel.add(nameLabel);
		upPanel.add(nameField);
		upPanel.add(nowTurn);
		
		ownLabel = new JLabel();
		if(window != null) ownLabel.setText(window.lang.getText(Language.OWNS));
		if(CalcWindow.usingFont != null) ownLabel.setFont(CalcWindow.usingFont);
		paidLabel = new JLabel();		
		if(window != null) paidLabel.setText(window.lang.getText(Language.PAIDS));
		if(CalcWindow.usingFont != null) paidLabel.setFont(CalcWindow.usingFont);
		ownList = new JList();
		paidList = new JList();
		lastCard = new JTextField(10);
		if(CalcWindow.usingFont != null)
		{
			ownList.setFont(CalcWindow.usingFont);
			paidList.setFont(CalcWindow.usingFont);
			lastCard.setFont(CalcWindow.usingFont);
		}
		lastCard.setEditable(false);
		ownList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paidList.setEnabled(false);
		ownScroll = new JScrollPane(ownList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		paidScroll = new JScrollPane(paidList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		centerLeft.add(ownLabel, BorderLayout.NORTH);
		centerLeft.add(ownScroll, BorderLayout.CENTER);
		centerRight.add(paidLabel, BorderLayout.NORTH);
		centerRight.add(paidScroll, BorderLayout.CENTER);
		centerBottom = new JPanel();
		centerBottom.setLayout(new BorderLayout());
		centerRight.add(centerBottom, BorderLayout.SOUTH);
		centerBottom.add(lastCard, BorderLayout.CENTER);
		
		pointLabel = new JLabel();
		if(window != null) pointLabel.setText(window.lang.getText(Language.POINT));
		if(CalcWindow.usingFont != null) pointLabel.setFont(CalcWindow.usingFont);
		pointField = new JTextField(10);
		pointField.setEditable(false);
		if(CalcWindow.usingFont != null) pointField.setFont(CalcWindow.usingFont);
		cardCountLabel = new JLabel();
		if(window != null) cardCountLabel.setText(window.lang.getText(Language.CARD) + " " + window.lang.getText(Language.COUNT));
		if(CalcWindow.usingFont != null) cardCountLabel.setFont(CalcWindow.usingFont);
		cardCountField = new JTextField(3);
		cardCountField.setEditable(false);
		if(CalcWindow.usingFont != null) cardCountField.setFont(CalcWindow.usingFont);
		for(int i=0; i<downLeftPns.length; i++)
		{
			downLeftPns[i].setLayout(new BorderLayout());
		}
		pointField.setBorder(new EtchedBorder());
		cardCountField.setBorder(new EtchedBorder());
		downLeftPns[0].add(pointLabel, BorderLayout.WEST);
		downLeftPns[0].add(pointField, BorderLayout.CENTER);
		downLeftPns[1].add(cardCountLabel, BorderLayout.WEST);
		downLeftPns[1].add(cardCountField, BorderLayout.CENTER);
		/*
		downLeftPns[0].add(pointLabel);
		downLeftPns[0].add(pointField);
		downLeftPns[1].add(cardCountLabel);
		downLeftPns[1].add(cardCountField);
		*/
		
		pay = new JButton();
		if(window != null) pay.setText(window.lang.getText(Language.PAY));
		if(CalcWindow.usingFont != null) pay.setFont(CalcWindow.usingFont);
		downRight.add(pay);
	}
	public void theme_refresh()
	{
		TrackStorage.addTrack(this.getClass().getName(), "theme_refresh() started", false);
		if(window.setting.isUse_color())
		{
			pay.setForeground(window.selected_fore);
			if(window.selected_button != null)
			{
				pay.setBackground(window.selected_button);
			}
			if(turn)
			{				
				this.setBackground(window.selected_back);
				SwingUtilities.invokeLater(new Runnable()
				{				
					
					@Override
					public void run()
					{						
						upPanel.setBackground(window.selected_back);
						centerLeft.setBackground(window.selected_back);
						centerRight.setBackground(window.selected_back);
						downLeft.setBackground(window.selected_back);
						downRight.setBackground(window.selected_back);
						nameLabel.setForeground(window.selected_fore);
						nameField.setBackground(window.selected_inside_back);
						nameField.setForeground(window.selected_fore);
						ownLabel.setForeground(window.selected_fore);
						paidLabel.setForeground(window.selected_fore);
						ownList.setBackground(window.selected_inside_back);
						paidList.setBackground(window.selected_inside_back);
						ownList.setForeground(window.selected_fore);
						paidList.setForeground(window.selected_fore);
						lastCard.setBackground(window.selected_inside_back);
						lastCard.setForeground(window.selected_fore);
						nowTurn.setBackground(window.selected_back);
						nowTurn.setForeground(window.selected_fore);
						isAI.setBackground(window.selected_back);
						isAI.setForeground(window.selected_fore);
						pointLabel.setForeground(window.selected_fore);
						cardCountLabel.setForeground(window.selected_fore);
						pointField.setForeground(window.selected_fore);
						pointField.setBackground(window.selected_inside_back);
						cardCountField.setForeground(window.selected_fore);
						cardCountField.setBackground(window.selected_inside_back);
						for(int i=0; i<downLeftPns.length; i++)
						{
							downLeftPns[i].setBackground(window.selected_back);
						}
					}
				});
				
			}
			else
			{
				this.setBackground(window.unselected_back);				
				SwingUtilities.invokeLater(new Runnable()
				{				
					@Override
					public void run()
					{
						upPanel.setBackground(window.unselected_back);
						centerLeft.setBackground(window.unselected_back);
						centerRight.setBackground(window.unselected_back);
						downLeft.setBackground(window.unselected_back);
						downRight.setBackground(window.unselected_back);
						nameLabel.setForeground(window.unselected_fore);
						nameField.setBackground(window.unselected_inside_back);
						nameField.setForeground(window.unselected_fore);
						ownLabel.setForeground(window.unselected_fore);
						paidLabel.setForeground(window.unselected_fore);
						ownList.setBackground(window.unselected_inside_back);
						paidList.setBackground(window.unselected_inside_back);
						ownList.setForeground(window.unselected_fore);
						paidList.setForeground(window.unselected_fore);
						lastCard.setBackground(window.unselected_inside_back);
						lastCard.setForeground(window.unselected_fore);
						nowTurn.setBackground(window.unselected_back);
						nowTurn.setForeground(window.unselected_fore);
						isAI.setBackground(window.unselected_back);
						isAI.setForeground(window.unselected_fore);
						pointLabel.setForeground(window.unselected_fore);
						cardCountLabel.setForeground(window.unselected_fore);
						pointField.setForeground(window.unselected_fore);
						pointField.setBackground(window.unselected_inside_back);
						cardCountField.setForeground(window.unselected_fore);
						cardCountField.setBackground(window.unselected_inside_back);
						for(int i=0; i<downLeftPns.length; i++)
						{
							downLeftPns[i].setBackground(window.unselected_back);
						}
					}
				});
				
			}
		}
		if(window.setting.isCenter_tab())
		{
			window.centerSecondThemeSet(this);
		}
	}
	public synchronized void thisTurn()
	{
		TrackStorage.addTrack(this.getClass().getName(), "thisTurn() started", false);
		if(window.printDescriptDetail) 
		{
			System.out.println(name + window.lang.getText(Language.DESCRIPTIONS + 4));
		}
		if(! replay_play)
		{
			pay.setEnabled(true);
			ownList.setEnabled(true);
		}
		turn = true;
		
		nowTurn.setSelected(true);
		refresh();
		if(window.setting.isCenter_tab())
		{
			String[] ownArray = new String[owns.size()];			
			StandardCard target;
			for(int i=0; i<ownArray.length; i++)
			{
				target = owns.get(i);
				ownArray[i] = target.toBasicString();
			}
			
			if(paids.size() >= 1) window.centerSecondSet(this, paids.get(paids.size() - 1).toBasicString(), ownArray);
			else window.centerSecondSet(this, "", ownArray);
		}
	}
	public synchronized void turnFinish()
	{
		TrackStorage.addTrack(this.getClass().getName(), "turnFinish() started", false);
		if(window.printDescriptDetail && window.active) 
		{
			window.message(name + window.lang.getText(Language.DESCRIPTIONS + 5));
		}
		ownList.setEnabled(false);		
		turn = false;
		nowTurn.setSelected(false);
		if(window.active) refresh();
	}
	public void calculate(boolean virtual, int bonus)
	{
		TrackStorage.addTrack(this.getClass().getName(), "calculate(" + virtual + ", " + bonus + ") started", false);
		int cardNum = 0;
		char cardOp = set.getOp_plus();
		if(virtual)
		{
			virtual_point = new BigInteger("0");
			for(int j=0; j<paids.size(); j++)
			{
				cardOp = paids.get(j).getOp();
				cardNum = paids.get(j).getNum();
				if(j == 0)
				{
					virtual_point = virtual_point.add(new BigInteger(String.valueOf(cardNum)));
					//virtual_point += cardNum;
				}
				else
				{
					if(cardOp == set.getOp_plus() || cardOp == set.getOp_spade())
					{
						virtual_point = virtual_point.add(new BigInteger(String.valueOf(cardNum)));
						//virtual_point += cardNum;
					}
					else if(cardOp == set.getOp_minus() || cardOp == set.getOp_clover())
					{
						virtual_point = virtual_point.subtract(new BigInteger(String.valueOf(cardNum)));
						//virtual_point -= cardNum;
					}
					else if(cardOp == set.getOp_multiply() || cardOp == set.getOp_diamond())
					{
						virtual_point = virtual_point.multiply(new BigInteger(String.valueOf(cardNum)));
						//virtual_point *= cardNum;
					}				
					else if(cardOp == set.getOp_divide())
					{
						virtual_point = virtual_point.divide(new BigInteger(String.valueOf(cardNum)));
						//virtual_point /= cardNum;
					}
					else if(cardOp == set.getOp_power())
					{
						for(int k=0; k<cardNum; k++)
						{
							virtual_point = virtual_point.multiply(virtual_point);
							//virtual_point *= virtual_point;
						}
					}
					else if(cardOp == set.getOp_change())
					{
						virtual_point = virtual_point.multiply(new BigInteger(String.valueOf(cardNum)));
						//virtual_point *= cardNum;
					}
				}
			}
			virtual_point = virtual_point.add(new BigInteger(String.valueOf(bonus)));
			//virtual_point += bonus;
		}
		else
		{					
			point = new BigInteger("0");
			for(int j=0; j<paids.size(); j++)
			{
				cardOp = paids.get(j).getOp();
				cardNum = paids.get(j).getNum();			
				if(j == 0)
				{
					point = point.add(new BigInteger(String.valueOf(cardNum)));
					//point += cardNum;
				}
				else
				{
					if(cardOp == set.getOp_plus() || cardOp == set.getOp_spade())
					{
						point = point.add(new BigInteger(String.valueOf(cardNum)));
						//point += cardNum;
					}
					else if(cardOp == set.getOp_minus() || cardOp == set.getOp_clover())
					{
						point = point.subtract(new BigInteger(String.valueOf(cardNum)));
						//point -= cardNum;
					}
					else if(cardOp == set.getOp_multiply() || cardOp == set.getOp_diamond())
					{
						point = point.multiply(new BigInteger(String.valueOf(cardNum)));
					}		
					else if(cardOp == set.getOp_divide())
					{
						point = point.divide(new BigInteger(String.valueOf(cardNum)));
						//point /= cardNum;
					}
					else if(cardOp == set.getOp_power())
					{
						for(int k=0; k<cardNum; k++)
						{
							point = point.multiply(point);
							//point *= point;
						}
					}
					else if(cardOp == set.getOp_change())
					{
						point = point.multiply(new BigInteger(String.valueOf(cardNum)));
						//point *= cardNum;
					}
				}
			}
			point = point.add(new BigInteger(String.valueOf(bonus)));
			
			//point += bonus;
		}
		TrackStorage.addTrack(this.getClass().getName(), "calculate(" + virtual + ", " + bonus + ") finished", false);
	}
	public void refresh()
	{
		TrackStorage.addTrack(this.getClass().getName(), "refresh() started", false);
		// Important !!
		
		//String[] ownArray = (String[]) owns.toArray();
		//String[] paidArray = (String[]) paids.toArray();
		empty = new String[1];
		if(window != null) empty[0] = window.lang.getText(Language.SEALED);
		else empty[0] = "";
		ownArray = new String[owns.size()];
		paidArray = new String[paids.size()];
		StandardCard target;
		for(int i=0; i<ownArray.length; i++)
		{
			target = owns.get(i);
			ownArray[i] = target.toBasicString(set.getCard_separator());
		}
		for(int i=0; i<paidArray.length; i++)
		{
			target = paids.get(i);
			paidArray[i] = target.toBasicString(set.getCard_separator());
		}		
		TrackStorage.addTrack(this.getClass().getName(), "refresh() token complete", false);
		if(network_guest)
		{
			if(network_turn)
			{
				if(turn)
				{
					
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							ownList.setListData(ownArray);
							pay.setEnabled(true);
							
						}			
					});
				}
				else
				{
					
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							ownList.setListData(empty);
							pay.setEnabled(false);
							
						}			
					});
					
				}
			}
			else
			{
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						ownList.setListData(empty);
						pay.setEnabled(false);
						
					}			
				});
			}
		}
		else
		{				
			if(ai && (! replay_play))
			{
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						ownList.setListData(empty);
						
					}			
				});
			}
			else
			{
				if((! turn) && (! replay_play))
				{
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							ownList.setListData(empty);
							
						}			
					});
				}
				else
				{
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							ownList.setListData(ownArray);
							
						}			
					});
				}				
			}	
			if(replay_play) pay.setEnabled(false);
		}
		if(showAll)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					ownList.setListData(ownArray);
					
				}			
			});
		}
		TrackStorage.addTrack(this.getClass().getName(), "refresh() own refreshed", false);
		calculate(false, 0);
		TrackStorage.addTrack(this.getClass().getName(), "refresh() calculated", false);
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				paidList.setListData(paidArray);
				pointField.setText(String.valueOf(point));
				cardCountField.setText(String.valueOf(owns.size()));
				nowTurn.setSelected(turn);
			}			
		});		
		TrackStorage.addTrack(this.getClass().getName(), "refresh() paid refreshed", false);
		if(window != null)
		{
			if(ai)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						isAI.setText("[" + window.lang.getText(Language.AI) + "]");
					}			
				});
			}
			else
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						isAI.setText("[" + window.lang.getText(Language.PLAYER) + "]");
					}			
				});
			}
			
			if(window.setting.isCenter_tab() && turn)
			{
				if(paids.size() >= 1)
				{
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							window.centerSecondSet(selfthis, paids.get(paids.size() - 1).toBasicString(), ownArray);
						}			
					});
				}
				else
				{
					SwingUtilities.invokeLater(new Runnable()
					{
						@Override
						public void run()
						{
							window.centerSecondSet(selfthis, "", ownArray);
						}			
					});
				}
			}
		}
		if(name != null)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					nameField.setText(name);
				}			
			});
		}
		TrackStorage.addTrack(this.getClass().getName(), "refresh() almostFinished", false);
		if(paids.size() >= 1)
		{
			StandardCard last_target = paids.get(paids.size() - 1);
			TrackStorage.addTrack(this.getClass().getName(), "refresh() last refreshing : getting paids", false);
			TrackStorage.addTrack(this.getClass().getName(), "refresh() last refreshing : get token", false);			
			lastCard.setText(last_target.toBasicString(set.getCard_separator()));			
		}
		TrackStorage.addTrack(this.getClass().getName(), "refresh() finished", false);
	}
	public VirtualBlock toVirtualBlock()
	{
		TrackStorage.addTrack(this.getClass().getName(), "toVirtualBlock() started", false);
		VirtualBlock newOne = new VirtualBlock(set);
		for(int i=0; i<owns.size(); i++)
		{
			newOne.getOwns().add(owns.get(i));
		}
		for(int i=0; i<paids.size(); i++)
		{
			newOne.getPaids().add(paids.get(i));
		}
		newOne.calculate(false);
		newOne.setName(new String(this.name));
		newOne.setAi(this.ai);
		return newOne;
	}
	public void setBlock(VirtualBlock data)
	{			
		setBlock(data, null);
	}
	public void setBlock(VirtualBlock data, CalcWindow window)
	{
		TrackStorage.addTrack(this.getClass().getName(), "setBlock(" + data + ", " + window + ") started", false);
		this.removeAll();
		init(null);
		owns.clear();
		paids.clear();
		for(int i=0; i<data.getOwns().size(); i++)
		{
			owns.add(data.getOwns().get(i));
		}
		for(int i=0; i<data.getPaids().size(); i++)
		{
			paids.add(data.getPaids().get(i));
		}
		this.name = new String(data.getName());
		this.ai = data.isAi();
		calculate(false, 0);
		refresh();
	}
	public void setWindow(CalcWindow window)
	{
		this.window = window;
	}
	public void nullWindow()
	{
		this.window = null;
		this.selfthis = null;
	}
	public BigInteger getPoint()
	{
		return point;
	}
	public void setPoint(BigInteger point)
	{
		this.point = point;
	}
	public BigInteger getVirtual_point()
	{
		return virtual_point;
	}
	public void setVirtual_point(BigInteger virtual_point)
	{
		this.virtual_point = virtual_point;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean isAi()
	{
		return ai;
	}
	public void setAi(boolean ai)
	{
		this.ai = ai;
	}
	public int getAi_difficulty()
	{
		return ai_difficulty;
	}
	public void setAi_difficulty(int ai_difficulty)
	{
		this.ai_difficulty = ai_difficulty;
	}
	public JPanel getCenterPanel()
	{
		return centerPanel;
	}
	public void setCenterPanel(JPanel centerPanel)
	{
		this.centerPanel = centerPanel;
	}
	public JPanel getUpPanel()
	{
		return upPanel;
	}
	public void setUpPanel(JPanel upPanel)
	{
		this.upPanel = upPanel;
	}
	public JPanel getDownPanel()
	{
		return downPanel;
	}
	public void setDownPanel(JPanel downPanel)
	{
		this.downPanel = downPanel;
	}
	public JPanel getCenterLeft()
	{
		return centerLeft;
	}
	public void setCenterLeft(JPanel centerLeft)
	{
		this.centerLeft = centerLeft;
	}
	public JPanel getCenterRight()
	{
		return centerRight;
	}
	public void setCenterRight(JPanel centerRight)
	{
		this.centerRight = centerRight;
	}
	public JPanel getDownLeft()
	{
		return downLeft;
	}
	public void setDownLeft(JPanel downLeft)
	{
		this.downLeft = downLeft;
	}
	public JPanel getDownRight()
	{
		return downRight;
	}
	public void setDownRight(JPanel downRight)
	{
		this.downRight = downRight;
	}
	public JLabel getNameLabel()
	{
		return nameLabel;
	}
	public void setNameLabel(JLabel nameLabel)
	{
		this.nameLabel = nameLabel;
	}
	public JTextField getNameField()
	{
		return nameField;
	}
	public void setNameField(JTextField nameField)
	{
		this.nameField = nameField;
	}
	public JLabel getOwnLabel()
	{
		return ownLabel;
	}
	public void setOwnLabel(JLabel ownLabel)
	{
		this.ownLabel = ownLabel;
	}
	public JLabel getPaidLabel()
	{
		return paidLabel;
	}
	public void setPaidLabel(JLabel paidLabel)
	{
		this.paidLabel = paidLabel;
	}
	public JList getOwnList()
	{
		return ownList;
	}
	public void setOwnList(JList ownList)
	{
		this.ownList = ownList;
	}
	public JList getPaidList()
	{
		return paidList;
	}
	public void setPaidList(JList paidList)
	{
		this.paidList = paidList;
	}
	public Vector<StandardCard> getOwns()
	{
		return owns;
	}
	public void setOwns(Vector<StandardCard> owns)
	{
		this.owns = owns;
	}
	public Vector<StandardCard> getPaids()
	{
		return paids;
	}
	public void setPaids(Vector<StandardCard> paids)
	{
		this.paids = paids;
	}
	public JScrollPane getOwnScroll()
	{
		return ownScroll;
	}
	public void setOwnScroll(JScrollPane ownScroll)
	{
		this.ownScroll = ownScroll;
	}
	public JScrollPane getPaidScroll()
	{
		return paidScroll;
	}
	public void setPaidScroll(JScrollPane paidScroll)
	{
		this.paidScroll = paidScroll;
	}
	public JButton getPay()
	{
		return pay;
	}
	public void setPay(JButton pay)
	{
		this.pay = pay;
	}
	public boolean isTurn()
	{
		return turn;
	}
	public void setTurn(boolean turn)
	{
		this.turn = turn;
	}
	public JTextField getPointField()
	{
		return pointField;
	}
	public void setPointField(JTextField pointField)
	{
		this.pointField = pointField;
	}
	public JTextField getLastCard()
	{
		return lastCard;
	}
	public void setLastCard(JTextField lastCard)
	{
		this.lastCard = lastCard;
	}
	public JPanel getCenterBottom()
	{
		return centerBottom;
	}
	public void setCenterBottom(JPanel centerBottom)
	{
		this.centerBottom = centerBottom;
	}
	public JLabel getPointLabel()
	{
		return pointLabel;
	}
	public void setPointLabel(JLabel pointLabel)
	{
		this.pointLabel = pointLabel;
	}
	public boolean isReplay_play()
	{
		return replay_play;
	}
	public void setReplay_play(boolean replay_play)
	{
		this.replay_play = replay_play;
	}
	public JLabel getCardCountLabel()
	{
		return cardCountLabel;
	}
	public void setCardCountLabel(JLabel cardCountLabel)
	{
		this.cardCountLabel = cardCountLabel;
	}
	public JTextField getCardCountField()
	{
		return cardCountField;
	}
	public void setCardCountField(JTextField cardCountField)
	{
		this.cardCountField = cardCountField;
	}
	public boolean isNetwork_guest()
	{
		return network_guest;
	}
	public void setNetwork_guest(boolean network_guest)
	{
		this.network_guest = network_guest;
	}
	public boolean isNetwork_turn()
	{
		return network_turn;
	}
	public void setNetwork_turn(boolean network_turn)
	{
		this.network_turn = network_turn;
	}
	public JCheckBox getNowTurn()
	{
		return nowTurn;
	}
	public void setNowTurn(JCheckBox nowTurn)
	{
		this.nowTurn = nowTurn;
	}
	public JLabel getIsAI()
	{
		return isAI;
	}
	public void setIsAI(JLabel isAI)
	{
		this.isAI = isAI;
	}
	public Setting getSet()
	{
		return set;
	}
	public void setSet(Setting set)
	{
		this.set = set;
	}
	public String getCard_separator()
	{
		return card_separator;
	}
	public void setCard_separator(String card_separator)
	{
		this.card_separator = card_separator;
	}
	public JPanel[] getDownLeftPns()
	{
		return downLeftPns;
	}
	public void setDownLeftPns(JPanel[] downLeftPns)
	{
		this.downLeftPns = downLeftPns;
	}
	public Block getSelfthis()
	{
		return selfthis;
	}
	public void setSelfthis(Block selfthis)
	{
		this.selfthis = selfthis;
	}
	public String[] getOwnArray()
	{
		return ownArray;
	}
	public void setOwnArray(String[] ownArray)
	{
		this.ownArray = ownArray;
	}
	public String[] getPaidArray()
	{
		return paidArray;
	}
	public void setPaidArray(String[] paidArray)
	{
		this.paidArray = paidArray;
	}
	public String[] getEmpty()
	{
		return empty;
	}
	public void setEmpty(String[] empty)
	{
		this.empty = empty;
	}
	public CalcWindow getWindow()
	{
		return window;
	}
	public void payEnable(boolean s)
	{
		pay.setEnabled(s);			
	}
	public boolean isShowAll()
	{
		return showAll;
	}
	public void setShowAll(boolean showAll)
	{
		this.showAll = showAll;
	}
}
