package com.hal9001.main;

import java.awt.Color;
import java.io.IOException;

import com.hal9001.antimation.EZ;
import com.hal9001.filereader.edmundgh_FileParser;

/**
 * Main Class
 * @author SoraZodia
 *
 */
public class AntimationEngine {
	
	private static boolean running = true;
	
	/**
	 * Where the magic is regulated
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws java.io.IOException{
		init();
		
		//ARRAYS FOR THE WIN
		IPaser actors[] = 
			{
				new edmundgh_FileParser("effects.txt", "LaunchSite.jpg", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
		    };	

		while(running){
			for(int x = 0; x < actors.length; x++){
				actors[x].read();
			}
			for(int x = 0; x < actors.length; x++){
				actors[x].go();
			}
			EZ.refreshScreen();
		}
		System.exit(0);
	}
	
	/**
	 * Sets up EZ
	 */
	public static void init(){
		EZ.initialize(1280,960);
		EZ.setBackgroundColor(Color.WHITE);
	}
	
	public static void stop(){
		running = false;
	}
	
}
