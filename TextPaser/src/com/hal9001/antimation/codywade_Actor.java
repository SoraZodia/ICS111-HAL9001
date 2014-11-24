package com.hal9001.antimation;

import java.io.FileReader;
import java.util.Scanner;

import com.hal9001.main.IPaser;

public class codywade_Actor implements IPaser{
	private EZImage actorPicture;
	private int x, y, startx, starty;
	private int destx, desty;
	private long starttime;
	private long duration;
	private boolean interpolation;
	private int mx, my, mdur;
	private String command;
	private double ang;
	private double a1S;
	String Script;
	Scanner fileScanner;

	public codywade_Actor(String filename, String script)throws java.io.IOException{
		Script = script;
		x=-1000;y=-1000; 
		fileScanner = new Scanner(new FileReader("codywade_resources/"+Script));
		actorPicture = EZ.addImage("codywade_resources/"+filename, x, y);
		interpolation = false;
		}
 	
	public void setRotation(Double angle) {
		ang = angle;
		actorPicture.rotateBy(ang);
	}
	
	public void setScale(double size) {
		a1S = size;
		actorPicture.scaleBy(size);
	}
	
	
	public void setDestination(int posx, int posy, long dur){
		destx = posx; desty = posy; duration = dur*1000;
		starttime = System.currentTimeMillis();
		startx=x; starty=y;
		interpolation = true;
	}
	
	public boolean moving() {
		if (interpolation == true) {
			float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) duration;
			
			x = (int) (startx + ((float) (destx - startx) *  normTime));
			y = (int) (starty + ((float) (desty - starty) *  normTime));
			
			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				x = destx; y = desty;
			}
				
		}
		return interpolation;
	}

	public void go(){	
		command = "";
		
		if(interpolation == false) {
			if (fileScanner.hasNext()) {
				command = fileScanner.next();
				
				switch (command) {
				case "control":
					mx = fileScanner.nextInt();
					my = fileScanner.nextInt();
					ang = fileScanner.nextInt();
					a1S = fileScanner.nextDouble();
					mdur = fileScanner.nextInt();
					break;	
					}
				}
			}
		
		
		switch (command) {
		case "control":
			setDestination(mx, my, mdur);
			setRotation(ang);
			setScale(a1S);
			break;
			}
		moving();
		actorPicture.translateTo(x,y);	
	
	}	






}
