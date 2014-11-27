package com.hal9001.antimation;

import java.util.HashMap;

/**
 * Class that handles what sounds to play,
 * it's  still incomplete. Need to find a way to add a timer and queue sounds.
 * @author SoraZodia
 */
public class SoundEffect {

	//Static variables
	private static EZSound sound;	
	private EZSound objSound;
	private EZImage image;
	private int clickX, clickY;
	private boolean clicked = false;
	private static HashMap<String, EZSound> soundList = new HashMap<>();
	
	public SoundEffect(String file, EZImage pic){
		objSound = EZ.addSound(file);
		image = pic;
	}
	
	/**
	 * Plays a sound if the mouse is clicked on the image
	 */
	public void doClickSound(){
		clicked = false;

		if(EZInteraction.getXMouseClick() != -1) {
			clickX = EZInteraction.getXMouseClick();
			clicked = true;
		}

		if(EZInteraction.getYMouseClick() != -1) {
			clickY = EZInteraction.getYMouseClick();
			clicked = true;
		}

		if(image.isPointInElement(clickX, clickY) && clicked){
			objSound.play();
		}

	}
	
	/**
	 * Set the currect sound to play. It will be looking at edmundgh_resources
	 * for the song files.
	 * @param name
	 */
	public static void setPlay(String name){
		sound = EZ.addSound(name);
		soundList.put(name, sound);
	}
	
	/**
	 * Play the currect sound
	 */
	public static void play(String name){
		soundList.get(name).play();
	}
	
	/**
	 * Loop the currect sound
	 */
	public static void loop(String name){
		soundList.get(name).loop();
	}
	
	/**
	 * Stop the sound
	 */
	public static void stop(String name){
		soundList.get(name).stop();
	}

}
