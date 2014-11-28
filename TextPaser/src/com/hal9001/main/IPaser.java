package com.hal9001.main;

/**
 * This is just to make things a little easier, basely, implementing makes it so Java will 
 * know that all of our classes are using these methods. 
 * 
 * @author Edmund
 */
public interface IPaser {

	/**
	 * This will be called by AntimationEngine to tell the parsers to read their files
	 * and activate the files' commands
	 */
	public void go();
	
}
