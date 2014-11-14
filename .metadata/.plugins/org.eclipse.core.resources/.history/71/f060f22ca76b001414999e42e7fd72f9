package com.sorazodia.antimation;

import java.awt.Color;

/**
 * A simple class that displays text on speech
 * It won't do anything complex... yet
 * @author SoraZodia
 */
public class TextDisplay {
	
	//String variable
	private static EZText message;
	
	/**
	 * Get the text ready for later use
	 * @param String
	 * @param X coord
	 * @param Y coord
	 */
	public static void addText(String str, int x, int y, int size){
		message = EZ.addText(x, y, str, Color.WHITE, size);
	}
	
	/**
	 * Show the text
	 */
	public static void showText(){
		message.show();
	}
	
	/**
	 * Hides the text
	 */
	public static void hideText(){
		message.hide();
	}

}
