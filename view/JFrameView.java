package view;

import javax.swing.JFrame;

public class JFrameView extends JFrame{
	private int screenX;
	private int screenY;
	private String screenTitle;
	public JFrame frame = new JFrame();
	
	public void setupFrame(int SCREENX, int SCREENY, String Title)
	{
		screenX = SCREENX;
		screenY = SCREENY;
		screenTitle = Title;
	}
	public void buildFrame()
	{
		frame.setSize(screenX, screenY);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle(screenTitle);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
