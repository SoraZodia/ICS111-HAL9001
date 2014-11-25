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
				
				new edmundgh_FileParser("effects.txt", "background.jpg", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleExtank.txt", "ShuttleExtank.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleLiTank1.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)+140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleLiTank2.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)-140, (EZ.getWindowHeight()/2)+100),
			    new edmundgh_FileParser("shuttleShip.txt", "ShuttleShip.png", EZ.getWindowWidth()/2, (EZ.getWindowHeight()/2)+60),
				
				new shaunnk_Animator("SLIDE_CHANGE.txt", "slidechange-pic.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				//new shaunnk_Animator("BLACKHOLE.txt", "BlackHole.png", EZ.getWindowWidth()/2, -EZ.getWindowHeight()),
			    
				new lorink_TestAnim("text1-pic.png", "text1.txt"),
				new lorink_TestAnim("text2-pic.png", "text2.txt"),
				new lorink_TestAnim("text3-pic.png", "text3.txt"),
				new lorink_TestAnim("text4-pic.png", "text4.txt"),
				new lorink_TestAnim("text5-pic.png", "text5.txt"),
				new lorink_TestAnim("text6-pic.png", "text6.txt"),
				new lorink_TestAnim("text7-pic.png", "text7.txt"),
				new lorink_TestAnim("text8-pic.png", "text8.txt"),
				new lorink_TestAnim("spaceshuttlenew-pic.png", "lshuttle.txt"),
				new lorink_TestAnim("shuttledoor-pic.png", "lshuttledoor.txt"),
				new lorink_TestAnim("shuttlenodoor-pic.png", "lnodoorshuttle.txt"),
				new lorink_TestAnim("astronautnew-pic.png", "lmajortom.txt"),
				new lorink_TestAnim("tinshipnew-pic.png", "ltinship.txt"),				
				new lorink_TestAnim("text9-pic.png", "text9.txt"),
				new lorink_TestAnim("text10-pic.png", "text10.txt"),
				new lorink_TestAnim("text11-pic.png", "text11.txt"),
				new lorink_TestAnim("text12-pic.png", "text12.txt"),
				
				new keonimf_Actor("ShuttleShip.png","animator.txt", 635,600),
				new keonimf_Actor("backofspaceship-pic.png","animator2.txt", 649,550),
				new keonimf_Actor("explosion.png","animator3.txt", 649, 700),
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
