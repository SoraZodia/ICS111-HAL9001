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
		
		//ARRAY AND PLOYMORPHISM FOR THE WIN :D
		IPaser actors[] = 
			{
				new edmundgh_FileParser("effects.txt", "background.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleExtank.txt", "ShuttleExtank.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleLiTank1.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)+140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleLiTank2.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)-140, (EZ.getWindowHeight()/2)+100),
			    new edmundgh_FileParser("shuttleShip.txt", "ShuttleShip.png", EZ.getWindowWidth()/2, (EZ.getWindowHeight()/2)+60),
			    new edmundgh_FileParser("gate.txt", "gateLaunch.png", 815, 364),
				
				new shaunnk_Animator("STARS1.txt", "Stars1.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new shaunnk_Animator("STARS2.txt", "Stars1.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new shaunnk_Animator("BLACKHOLE.txt", "BlackHole.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),

				new codywade_Actor("mars-pic.png","mars.txt"),
				new codywade_Actor("meteor.png","meteor.txt"),
				new codywade_Actor("earth-pic.png","earth.txt"),

				new shaunnk_Animator("ATMOSPHERE1.txt", "EarthAtmosphere.jpg", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new shaunnk_Animator("LAUNCHSITE.txt", "LaunchSite.JPG", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new shaunnk_Animator("ALIEN.txt", "Alien.png", EZ.getWindowWidth()/2, -EZ.getWindowHeight()),
				
				new lorink_TestAnim("smoke1-pic.png", "smoke1.txt"),
				new lorink_TestAnim("smoke2-pic.png", "smoke2.txt"),
				
				new keonimf_Actor("explosion.png","animator3.txt", 649, 700),
				new keonimf_Actor("backofspaceship-pic.png","animator2.txt", 649,550),
				new keonimf_Actor("spaceshuttlenew-pic.png","animator.txt", 635,600),
				new keonimf_Actor("aliencraft2.png","animator4.txt", 0,0),
				new keonimf_Actor("alienray.png", "alienray.txt", 647, 365),
				
				new shaunnk_Animator("SLIDE_CHANGE.txt", "RedSky.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				
				new lorink_TestAnim("asteroid1-pic.png", "asteroid1.txt"),
				new lorink_TestAnim("asteroid2-pic.png", "asteroid2.txt"),
				new lorink_TestAnim("asteroid1-pic.png", "asteroid4.txt"),
				new lorink_TestAnim("asteroid2-pic.png", "asteroid3.txt"),
				new lorink_TestAnim("text1-pic.png", "text1.txt"),
				new lorink_TestAnim("text2-pic.png", "text2.txt"),
				new lorink_TestAnim("text3-pic.png", "text3.txt"),
				new lorink_TestAnim("text4-pic.png", "text4.txt"),
				new lorink_TestAnim("text5-pic.png", "text5.txt"),
				new lorink_TestAnim("text6-pic.png", "text6.txt"),
				new lorink_TestAnim("text7-pic.png", "text7.txt"),
				new lorink_TestAnim("text8-pic.png", "text8.txt"),
				new lorink_TestAnim("text9-pic.png", "text9.txt"),
				new lorink_TestAnim("text10-pic.png", "text10.txt"),
				new lorink_TestAnim("text11-pic.png", "text11.txt"),
				new lorink_TestAnim("text12-pic.png", "text12.txt"),
				new lorink_TestAnim("text13-pic.png", "text13.txt"),
				new lorink_TestAnim("text14-pic.png", "text14.txt"),
				new lorink_TestAnim("text15-pic.png", "text15.txt"),
				new lorink_TestAnim("text16-pic.png", "text16.txt"),
				new lorink_TestAnim("text17-pic.png", "text17.txt"),
				new lorink_TestAnim("text18-pic.png", "text18.txt"),
				new lorink_TestAnim("text19-pic.png", "text19.txt"),
				new lorink_TestAnim("text20-pic.png", "text20.txt"),
				new lorink_TestAnim("text21-pic.png", "text21.txt"),
				new lorink_TestAnim("text22-pic.png", "text22.txt"),
				new lorink_TestAnim("text23-pic.png", "text23.txt"),
				new lorink_TestAnim("redled-pic.png", "redled1.txt"),
				new lorink_TestAnim("redled-pic.png", "redled2.txt"),
				new lorink_TestAnim("redled-pic.png", "redled3.txt"),
				new lorink_TestAnim("spaceshuttlenew-pic.png", "lshuttle.txt"),
				new lorink_TestAnim("shuttledoor-pic.png", "lshuttledoor.txt"),
				new lorink_TestAnim("shuttlenodoor-pic.png", "lnodoorshuttle.txt"),
				new lorink_TestAnim("astronautnew-pic.png", "lmajortom.txt"),
				new lorink_TestAnim("tinshipnew-pic.png", "ltinship.txt"),	
				new lorink_TestAnim("tinshipnodoor-pic.png", "ltinshipnodoor.txt"),
				new lorink_TestAnim("tinshipdoor-pic.png", "ltinshipdoor.txt"),
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
