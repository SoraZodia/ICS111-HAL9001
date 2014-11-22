package com.hal9001.main;

import java.awt.Color;
import java.io.IOException;

import com.hal9001.antimation.EZ;
import com.hal9001.antimation.codywade_Actor;
import com.hal9001.antimation.keonimf_Actor;
import com.hal9001.antimation.lorink_TestAnim;
import com.hal9001.antimation.shaunnk_Animator;
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
		
		//ARRAY AND PLOYMORPHISM FOR THE WIN
		IPaser actors[] = 
			{
				//shaun's actors
				/*new shaunnk_Animator("REDSKY.txt", "RedSky.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new shaunnk_Animator("ATMOSPHERE.txt", "EarthAtmosphere.png", EZ.getWindowWidth()/2, -EZ.getWindowHeight()/2),
				new shaunnk_Animator("STARS1.txt", "Stars1.png", EZ.getWindowWidth()/2, -EZ.getWindowHeight()),
				
				//cody's actors
				new codywade_Actor("earth-pic.png","animator3.txt"),
				new codywade_Actor("venus-pic.png","animator2.txt"),
				new codywade_Actor("mars-pic.png","animator.txt"),
				
				//keoni's actors
				//new keonimf_Actor("hwpup.png","animator.txt", 950,256),
				//new keonimf_Actor("bat.png","animator2.txt", 1800,500),
				new keonimf_Actor("Moon.png","animator3.txt", 10,50),
				
				//lorin's actors
				new lorink_TestAnim("asteroid1-pic.png", "control.txt"),
				new lorink_TestAnim("asteroid2-pic.png", "control2.txt"),
				new lorink_TestAnim("saturn-pic.png", "control3.txt"),
			    
				//edmund's actors
				new edmundgh_FileParser("shuttleExtank.txt", "ShuttleExtank.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleLiTank1.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)+140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleLiTank2.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)-140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleShip.txt", "ShuttleShip.png", EZ.getWindowWidth()/2, (EZ.getWindowHeight()/2)+60),
				new edmundgh_FileParser("effects.txt", "LaunchSite.jpg", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2), */
				
				new lorink_TestAnim("slidechange-pic.png", "control.txt")
			};	

		while(running){
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
		EZ.initialize(1280,735);
		EZ.setBackgroundColor(Color.BLACK);
	}
	
	public static void stop(){
		running = false;
	}
	
}
