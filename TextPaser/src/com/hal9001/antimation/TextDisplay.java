package com.hal9001.antimation;

import java.awt.Color;
import java.util.HashMap;

/**
 * A simple class that displays text on speech
 * It won't do anything complex... yet
 * @author SoraZodia
 */
public class TextDisplay {
	
	//String variable
	private static EZText message;
	private static HashMap<String, EZText> texts = new HashMap<String, EZText>();
	
	/**
	 * Get the text ready for later use
	 * @param String
	 * @param X coord
	 * @param Y coord
	 */
	public static void addText(String str, int x, int y, int size){
		message = EZ.addText(x, y, str, Color.WHITE, size);
		texts.put(str, message);
	}
	
	/**
	 * Show the text
	 */
	public static void showText(String str){
		texts.get(str).pullToFront();
		texts.get(str).show();
	}
	
	/**
	 * Hides the text
	 */
	public static void hideText(String str){
		texts.get(str).hide();
	}

}
