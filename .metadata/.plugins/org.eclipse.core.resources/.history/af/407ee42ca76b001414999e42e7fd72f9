package com.sorazodia.filereader;

import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sorazodia.antimation.Image;
import com.sorazodia.antimation.SoundEffect;
import com.sorazodia.antimation.TextDisplay;

/**
 * Class that reads the file and send the info into the Image class
 * @author SoraZodia
 *
 */
public class edmundgh_FileParser { //Yes Eclipse, I know the convention for class names <_<
  public Scanner reader;
  private Image image;
  private float x;
  private float y;
  private float time;
  private float degree;
  private float size;
  private short push;
  private boolean play = true;
  private static String soundName;
  private static String backgroundName;
  private static String text;
  private String name;
  
  //Constructor 
  public edmundgh_FileParser(String fileName, String imageName, String soundName, int x, int y) throws java.io.IOException{
	  reader = new Scanner(new FileReader(fileName));
	  image = new Image(imageName, soundName, x, y);
	  name = fileName;
  }

  /**
   * Reads the file and send to Image to interpret
   */
  public void read(){
	  if(reader.hasNext() && !image.getMove()){
		  String command = reader.next();
		  try{
			  parse(command);
		  }
		  catch(InputMismatchException error){
			  parseError(command);
		  }
	  }

  }
  
  /**
   * Translate the command into Java code
   */
  private void parse(String command){
	  switch(command.toLowerCase()){
	  case "control":
		  x = reader.nextFloat();
		  y = reader.nextFloat();
		  degree = reader.nextFloat();
		  size = reader.nextFloat();
		  time = reader.nextFloat();
		  image.setInterpolAll(x, y, degree, size, time);
		  System.out.printf("[%s Log] Image to X:%s, Y:%s, Degree:%s, Size:%s in %s secord \n", name,x,y,degree,size,time);
		  break;

	  case "move":
		  x = reader.nextFloat();
		  y = reader.nextFloat();
		  time = reader.nextFloat();
		  image.setInterpolAll(x, y, degree, size, time);
		  System.out.printf("[%s Log] Moving to X:%s Y:%s in %s secord \n", name,x,y,time);
		  break;

	  case "rotate":
		  degree = reader.nextFloat();
		  time = reader.nextFloat();
		  image.setInterpolAll(x, y, degree, size, time);
		  System.out.printf("[%s Log] Rotating to %s degree in %s secord \n", name,degree,time);
		  break;

	  case "resize":
		  size = reader.nextFloat();
		  time = reader.nextFloat();
		  image.setInterpolAll(x, y, degree, size, time);
		  System.out.printf("[%s Log] Resizing to x%s pixel in %s secord \n", name,size,time);
		  break;

	  case "hide":
		  image.hide();
		  System.out.printf("[%s Log] Calling command: %s image \n", name, command);
		  break;

	  case "show":
		  image.show();
		  System.out.printf("[%s Log] Calling command: %s image \n", name, command);
		  break;

	  case "addsound":
		  soundName = reader.next();
		  SoundEffect.setPlay(soundName);
		  System.out.printf("[%s Log] Calling command: %s, %s \n", name, command, soundName);
		  break;

	  case "loopsound":
		  SoundEffect.loop();
		  System.out.printf("[%s Log] Calling command: %s, %s \n", name, command, soundName);
		  break;

	  case "stopsound":
		  SoundEffect.stop();
		  System.out.printf("[%s Log] Calling command: %s, %s \n", name, command, soundName);
		  break;

	  case "playsound":
		  SoundEffect.play();
		  System.out.printf("[%s Log] Calling command: %s, %s \n", name, command, soundName);
		  break;

		  //Just a filler command to help with the different actor timing, 
		  //It can be used as a delay
	  case "wait":
		  System.out.printf("[%s Log] Calling command: %s \n", name, command);
		  break;

	  case "background":
		  backgroundName = reader.next();
		  Image.addBackground(backgroundName);
		  System.out.printf("[%s Log] Adding background: %s \n", name, backgroundName);
		  break;

	  case "layerdownbackground":
		  push = reader.nextShort();
		  Image.layerDownBackground(push);
		  System.out.printf("[%s Log] Pushing background %s down %s layer \n", name, backgroundName, push);
		  break;

	  case "layerupbackground":
		  push = reader.nextShort();
		  Image.layerUpBackground(push);
		  System.out.printf("[%s Log] Pushing background %s up %s layer \n", name, backgroundName, push);
		  break;

	  case "hidebackground":
		  Image.hideBackground();
		  System.out.printf("[%s Log] Hiding background: %s \n", name, backgroundName);
		  break;

	  case "showbackground":
		  Image.showBackground();
		  System.out.printf("[%s Log] Showing background: %s \n", name, backgroundName);
		  break;

	  case "resizebackground":
		  double scale = reader.nextDouble();
		  Image.resizeBackground(scale);
		  System.out.printf("[%s Log] Rescaling background %s to %s\n", name, backgroundName, scale);
		  break;

	  case "addtext":
		  x = reader.nextFloat();
		  y = reader.nextFloat();
		  int size = reader.nextInt();
		  text = reader.nextLine();
		  TextDisplay.addText(text, (int)x, (int)y, size);
		  System.out.printf("[%s Log] Adding text -%s-, sized %s, at X: %s and Y: %s \n", name, text, size, x, y);
		  break;

	  case "hidetext":
		  TextDisplay.hideText();
		  System.out.printf("[%s Log] Hiding text -%s- \n", name, text);
		  break;

	  case "showtext":
		  TextDisplay.showText();
		  System.out.printf("[%s Log] Showing text -%s- \n", name, text);
		  break;

	  case "exit":
		  //AntimationEngine.stop();
		  //System.out.printf("[%s Log] Closing \n", name);
		  System.out.println("[System] Command called to exit program");
		  System.exit(0);
		  break;

		  // Cause the parser to skip the line // is found in
	  case "//":
		  reader.nextLine();
		  break;

	  default:
		  System.out.printf("[%s Log] ERROR: Unable To Parse Command, Double Check Your Code \n", name);
		  break;
	  }
  }
  
  /**
   * If user entered an incomplete command, this is to be called to inform them
   * of the proper usage. This is only able to detect parameters mirror not 
   * spelling errors, commands such as "showtext" 
   * will not work due to their lack of parameters.
   * @param command
   */
  private void parseError(String command){
	  switch(command.toLowerCase()){
	  case "control":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command,"control <target X position> <target Y position> <the degree to turn> <number which the picture's pixel will be muiltple/divdled by> <how fast/slow it will take (in secords)>");
		  break;

	  case "move":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "move <target X position> <target Y position> <how fast/slow it will take (in secords)>");
		  break;

	  case "rotate":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command,"rotate <the degree to turn> <how fast/slow it will take (in secords)>");
		  break;

	  case "resize":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command,"resize <number which the picture's pixel will be muiltple/divdled by> <how fast/slow it will take (in secords)>");
		  break;

	  case "addsound":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "addsound <FULL file name of sound, including its extension(.png, .jpg, etc.)>");
		  break;

	  case "background":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "background <FULL file name of background, including its extension(.png, .jpg, etc.)>");
		  break;
		  
	  case "layerdownbackground":
		  push = reader.nextShort();
		  Image.layerDownBackground(push);
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "layerdownbackground <number of layer to be push back by>");
		  break;

	  case "layerupbackground":
		  push = reader.nextShort();
		  Image.layerUpBackground(push);
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "layerupbackground <number of layer to be pull up by>");
		  break;
		  
	  case "resizebackground":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "resizebackground <FULL name of background, including its extension(.png, .jpg, etc.)> <number which the picture's pixel will be muiltple/divdled by>");
		  break;

	  case "addtext":
		  System.out.printf("[Parameter Error] %s need to be entered as \n %s \n", command, "addtext <message> <text size> <X position> <Y position>"); //If you're wondering, I did this %s thing to make copy paste easier :P
		  break;

	  default:
		  System.out.printf("[General Error] ERROR: Unable To Parse Command, Double Check Your Code \n");
		  break;
	  }
  }
  
  /**
   * Checks if FileReader has any more lines
   * @return play
   */
  public boolean getPlay(){
	  return play;
  }
  
  /**
   * Makes the images dance
   */
  public void go(){
	  image.go();
  }
  
  /**
   * Checks if the image is in reading or moving mode
   * @return image.getMove()
   */
  public boolean isMoving(){
	  return image.getMove();
  }
 
}
