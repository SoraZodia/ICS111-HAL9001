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
		
		//ARRAYS FOR THE WIN
		IPaser actors[] = 
			{
				new edmundgh_FileParser("effects.txt", "LaunchSite.jpg", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleExtank.txt", "ShuttleExtank.png", EZ.getWindowWidth()/2, EZ.getWindowHeight()/2),
				new edmundgh_FileParser("shuttleLiTank1.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)+140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleLiTank2.txt", "ShuttleLiTank.png", (EZ.getWindowWidth()/2)-140, (EZ.getWindowHeight()/2)+100),
				new edmundgh_FileParser("shuttleShip.txt", "ShuttleShip.png", EZ.getWindowWidth()/2, (EZ.getWindowHeight()/2)+60),
				new keonimf_Actor("hwpup.png","animator.txt", 950,256),
				new keonimf_Actor("bat.png","animator2.txt", 1800,500),
				new keonimf_Actor("ghostpv1.png","animator3.txt", 10,50),
				new lorink_TestAnim("Patrick.png", "control.txt"),
				new lorink_TestAnim("Patrick.png", "control2.txt"),
				new lorink_TestAnim("Patrick.png", "control3.txt"),
				new shaunnk_Animator("HANK.txt", "King of the Hill.wav", "Hank_Hill.png", 375, 310),
				new shaunnk_Animator("BILL.txt", "King of the Hill.wav", "Bill_Dauterive.png", 760, 310),
				new shaunnk_Animator("DALE.txt", "King of the Hill.wav", "Dale_Gribble.png", 115, 310),
				new shaunnk_Animator("JEFF.txt", "King of the Hill.wav", "Jeff_Boomhauer.png", 225, 310),
				new shaunnk_Animator("PROPANE.txt", "King of the Hill.wav", "propane_tank.png", 1000, 200),
				new shaunnk_Animator("TEXAS.txt", "King of the Hill.wav", "Texas_converted.png", 1500, 250),
				new codywade_Actor("pizza.png","animator3.txt"),
				new codywade_Actor("patrick.gif","animator2.txt"),
				new codywade_Actor("earth.gif","animator.txt")
			};	
		
		//trying to see if this works

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
		EZ.initialize(1280,960);
		EZ.setBackgroundColor(Color.WHITE);
	}
	
	public static void stop(){
		running = false;
	}
	
}
