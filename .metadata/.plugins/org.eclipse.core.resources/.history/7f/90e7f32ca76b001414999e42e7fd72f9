package com.sorazodia.antimation;

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
	
	public SoundEffect(){
	}
	
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
	 * Set the currect sound to play
	 * @param name
	 */
	public static void setPlay(String name){
		sound = EZ.addSound(name);
	}
	
	/**
	 * Play the currect sound
	 */
	public static void play(){
		sound.play();
	}
	
	/**
	 * Loop the currect sound
	 */
	public static void loop(){
		sound.loop();
	}
	
	/**
	 * Stop the sound
	 */
	public static void stop(){
		sound.stop();
	}

}
