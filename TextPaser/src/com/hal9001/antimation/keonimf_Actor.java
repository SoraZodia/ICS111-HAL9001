package com.hal9001.antimation;

import java.io.FileReader;
import java.util.Scanner;

import com.hal9001.main.IPaser;

public class keonimf_Actor implements IPaser{
	private EZImage actorPicture;
	private int x, y, startx, starty;
	private int destx, desty;
	private long starttime;
	private long duration;
	private boolean interpolation;
	private int movex, movey, mdur;
	private String control;
	private double ang, ang2;
	private double scale;
	private boolean foreground;
	private boolean showing;
	String Script;
	Scanner fileScanner;

	public keonimf_Actor(String filename, String script, int stx, int sty)throws java.io.IOException{
		Script = script;
		x=stx; y=sty;
		fileScanner = new Scanner(new FileReader("keonimf_resources/"+Script));
		actorPicture = EZ.addImage("keonimf_resources/"+filename, stx, sty);
		interpolation = false;
		}
 	
	
	public void rotBy(int posx, int posy, long dur) { 
		destx = posx; desty = posy; duration = dur*1000;
		starttime = System.currentTimeMillis();
		startx=x; starty=y;
		interpolation = true;
	}
	
	
	public void setScale(double size) {
		scale = size;
		actorPicture.scaleTo(scale);
	}
	
	
	public void setDestination(int posx, int posy, long dur){
		destx = posx; desty = posy; duration = dur*1000;
		starttime = System.currentTimeMillis();
		startx=x; starty=y;
		interpolation = true;
	}
	
	@Override
	public void go(){	
		control = "";
		
		if(interpolation == false) {
			if (fileScanner.hasNext()) {
				control = fileScanner.next();
				switch (control) {
				case "control":
					movex = fileScanner.nextInt();
					movey = fileScanner.nextInt();
					ang = fileScanner.nextInt();
					scale = fileScanner.nextDouble();
					mdur = fileScanner.nextInt();
					showing = fileScanner.nextBoolean();
					setDestination(movex, movey, mdur);
					rotBy(movex, movey, mdur);
					setScale(scale);
					
					if(showing){
						actorPicture.show();
					}
					else if(!showing){
						actorPicture.hide();
					}
					break;	
					
					}

			}
		}
		
		
		if (interpolation == true) {
			float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) duration;
			
			x = (int) (startx + ((float) (destx - startx) *  normTime));
			y = (int) (starty + ((float) (desty - starty) *  normTime));
			
			
			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				x = destx; y = desty;
			}
		
		}
		
		actorPicture.translateTo(x,y);	
		actorPicture.rotateBy(ang);
	}





	
}
