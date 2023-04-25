package view;

import java.awt.*;

import javax.swing.*;

public class MainView extends JFrameView{
	public static JPanel header = new JPanel();
	public static JLabel headText = new JLabel("KRACK Attack - MitM Exploit");
	public static JPanel targetNetworkPanel = new JPanel();
	public static JLabel targetNetworkText = new JLabel("Target Network: ");
	public static JTextArea targetNetworkInput = new JTextArea();
	public static JPanel targetMACPanel = new JPanel();
	public static JLabel targetMACText = new JLabel("Target MAC: ");
	public static JTextArea targetMACInput = new JTextArea();
	public static JPanel runPanel = new JPanel();
	public static JButton run = new JButton("Run Attack");
	public static JButton exit = new JButton("Exit");
	public static void main(String[] args) 
	{
		//frame and controller initialization
		JFrameView window = new JFrameView();
		controller.controllerMain MC = new controller.controllerMain(window);
		window.setupFrame(300, 180, "KRACK Attack");
		window.frame.setLayout(new GridLayout(4,1));
		//header
		header.add(headText, BorderLayout.CENTER);
		header.add(new JSeparator());
		window.frame.add(header);
		
		//target network panel
		targetNetworkPanel.add(targetNetworkText);
		targetNetworkInput.setPreferredSize(new Dimension(100,25));
		targetNetworkPanel.add(targetNetworkInput);
		window.frame.add(targetNetworkPanel);
		//target MAC panel
		targetMACPanel.add(targetMACText);
		targetMACInput.setPreferredSize(new Dimension(100,25));
		targetMACPanel.add(targetMACInput);
		window.frame.add(targetMACPanel);
		//run button
		run.addActionListener(MC);
		exit.addActionListener(MC);
		runPanel.add(run);
		runPanel.add(exit);
		window.frame.add(runPanel);
		window.buildFrame();
	}

}
