package com.hal9001.antimation;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Class that FileParser uses to translate words into action
 * @author SoraZodia
 */
public class Image {
	
	//Them variable
	private EZImage image;
	private static EZImage background;
	private static HashMap<String, EZImage> backgroundList = new HashMap<String, EZImage>();
	private float startX, startY;
	private float destX, destY;
	private long startTime;
	private float duration;
	private boolean interpolation;
	private float newDegree, oldDegree;
	private float oldSize, newSize;
	private SoundEffect sound;
	private boolean hasSound = false;
	
	//Totally not a Constructor 
	public Image(String imageName, String soundName, int x, int y){
		image = EZ.addImage(imageName, x, y);
		startX = x;
		startY = y;
		oldDegree = 0;
		oldSize = 1;
		sound = new SoundEffect(soundName, image);
		hasSound = true;
		interpolation = false;
	}
	
	public Image(String imageName, int x, int y){
		image = EZ.addImage(imageName, x, y);
		startX = x;
		startY = y;
		oldDegree = 0;
		oldSize = 1;
		hasSound = false;
		interpolation = false;
	}
	
	/**
	 * Sets the background
	 * @param fileName
	 */
	public static void addBackground(String fileName){
		background = EZ.addImage(fileName, EZ.getWindowWidth()/2, EZ.getWindowHeight()/2);
	    backgroundList.put(fileName, background);
	    backgroundList.get(fileName).pushToBack();
	}
	
	/**
	 * For multiple background, this is used to set them so some/all/only one of them shows
	 */
	public static void layerUpBackground(String name, short layer){
		for(short x = 0; x < layer; x++){
			backgroundList.get(name).pullForwardOneLayer();
		}
	}
	
	/**
	 * For multiple background, this is used to set them so some/all/only one of them shows
	 */
	public static void layerDownBackground(String name, short layer){
		for(short x = 0; x < layer; x++){
			backgroundList.get(name).pushBackOneLayer();
		}
	}
	
	/**
	 * Hides the background
	 */
	public static void hideBackground(String name){
	    backgroundList.get(name).hide();
	}
	
	/**
	 * Show the background
	 */
	public static void showBackground(String name){
		backgroundList.get(name).show();
	}
	
	/**
	 * Resize the background. Important note: Giving it a value of 4 will
	 * be enough be almost fill a 1280x720 screen, use this as a guide to
	 * determine how big you want the image 
	 * @param scale
	 */
	public static void resizeBackground(String name, double scale){
		backgroundList.get(name).scaleTo(scale);
	}
	
	/**
	 * Pull the image up x number of times
	 */
	public void layerUpImage(short layer){
		for(short x = 0; x < layer; x++){
		image.pullForwardOneLayer();
		}
	}
	
	/**
	 * Push the image down x number of times
	 */
	public void layerDownImage(short layer){
		for(short x = 0; x < layer; x++){
		image.pushBackOneLayer();
		}
	}
	
	
	/**
	 * Set the translate, spin, and resize value of the image as well as
	 * the time range it should finish everything in
	 * @param x
	 * @param y
	 * @param degree
	 * @param size
	 * @param dur
	 */
	public void setInterpolAll(float x, float y, float degree, float size, float dur){
		
		startX = image.getWorldXCenter();
		startY = image.getWorldYCenter();
		destX = x;
		destY = y;
		
		newSize = size;
		oldSize = (float) image.getScale();
		
		newDegree = degree;
		oldDegree = (float) image.getRotation();
		
		duration = covertToNanoTime((long)dur);
		startTime = System.nanoTime();
		
		interpolation = true;
	}
	
	/**
	 * Hides the image until show() is called
	 */
	public void hide(){
		image.hide();
	}
	
	/**
	 * Shows the image, generally you will not need this unless hide() was called
	 */
	public void show(){
		image.show();
	}
	
	/**
	 * Resize, spin, and translate the image all at the same time
	 * @param x
	 * @param y
	 * @param degree
	 * @param size
	 */
	private void interAll(float x, float y, float degree, float size){

		if (interpolation == true) {

			float sizeTran = getInter(size, oldSize, newSize, duration);
			float degreeTran = getInter(degree, oldDegree, newDegree, duration);
			float xTran = getInter(x, startX, destX, duration);
			float yTran = getInter(y, startY, destY, duration);

			if ((System.nanoTime() - startTime) >= duration) {
				interpolation = false;
				newSize = size;
				newDegree = degree;
				destX = x; destY = y;
			}
			image.scaleTo(sizeTran);
			image.rotateTo(degreeTran);
			image.translateTo(xTran, yTran);
		}

	}
	
	/**
	 * Determine the time limit as well as the location which the image needs to be
	 * @author The Best Hula Dancer in Hawaii
	 * @param intput
	 * @param old 
	 * @param notOld
	 * @param duration
	 * @return notOld
	 */
	private float getInter(float intput, float old, float notOld, float duration){
		float normTime = 0;
		if(duration != 0){//Safely net
		normTime = (float) (System.nanoTime() - startTime)/ duration;
		}
		notOld = (old + ((float) (intput - old) *  normTime));
		return notOld;
	}
	
	/**
	 * Checks if the image is moving
	 * @return interpolation
	 */
	public boolean getMove(){
		return interpolation;
	}
	
	/**
	 * Converts seconds to nanosecond 
	 * @param second
	 * @return nanoTime
	 */
	private float covertToNanoTime(long second){
		second = TimeUnit.NANOSECONDS.convert(second,TimeUnit.SECONDS);
		return (float)second;
	}
	
	/**
	 * Makes the image dances
	 */
	public void go(){
		 if(hasSound) sound.doClickSound();
		 interAll(destX, destY, newDegree, newSize);
	}

}
