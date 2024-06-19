package main_classes;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel
{
	private static final long serialVersionUID = 5825199007917087472L;
	private float transparency_opacity = 0.2f;
	public TransparentPanel()
	{
		super();
		setOpaque(false);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency_opacity));
	}
	public float getTransparency_opacity()
	{
		return transparency_opacity;
	}
	public void setTransparency_opacity(float transparency_opacity)
	{
		this.transparency_opacity = transparency_opacity;
	}	
}
