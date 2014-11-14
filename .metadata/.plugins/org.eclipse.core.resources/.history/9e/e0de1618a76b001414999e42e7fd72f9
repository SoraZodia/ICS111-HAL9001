package com.sorazodia.main;

import java.awt.Color;
import java.io.IOException;

import com.sorazodia.antimation.EZ;
import com.sorazodia.filereader.edmundgh_FileParser;

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
	public static void main(String args[]) throws IOException{
		init();
		
		//ARRAYS FOR THE WIN
		edmundgh_FileParser actors[] = 
			{
				new edmundgh_FileParser("creeper.txt", "creeper.png", "explosion.wav", EZ.getWindowWidth()/2, 650),
				new edmundgh_FileParser("witch.txt", "witch.png", "squish.wav", EZ.getWindowWidth()/2, 650),
				new edmundgh_FileParser("player.txt", "player.png", "cheehoo.wav",EZ.getWindowWidth()/2, 650)
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
		EZ.initialize(1280,720);
		EZ.setBackgroundColor(Color.WHITE);
	}
	
	public static void stop(){
		running = false;
	}
	
}
