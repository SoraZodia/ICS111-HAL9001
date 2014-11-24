package com.hal9001.filereader;

import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hal9001.antimation.Image;
import com.hal9001.antimation.SoundEffect;
import com.hal9001.antimation.TextDisplay;
import com.hal9001.main.AntimationEngine;
import com.hal9001.main.IPaser;

/**
 * Class that reads the file and send the info into the Image class
 * @author SoraZodia
 *
 */
public class edmundgh_FileParser implements IPaser{ //Yes Eclipse, I know the convention for class names <_<
  public Scanner reader;
  private Image image;
  private float x;
  private float y;
  private float time;
  private float degree;
  private float size = 1;
  private short push;
  private boolean play = true;
  private static String soundName;
  private static String backgroundName;
  private static String text;
  private String name;
  
  //Constructor 
  public edmundgh_FileParser(String fileName, String imageName, String soundName, int x, int y) throws java.io.IOException{
	  reader = new Scanner(new FileReader("edmundgh_resources/"+fileName));
	  image = new Image("edmundgh_resources/"+imageName, "edmundgh_resources/"+soundName, x, y);
	  name = fileName;
	  this.x = x;
	  this.y = y;
  }
  
  public edmundgh_FileParser(String fileName, String imageName, int x, int y) throws java.io.IOException{
	  reader = new Scanner(new FileReader("edmundgh_resources/"+fileName));
	  image = new Image("edmundgh_resources/"+imageName, x, y);
	  name = fileName;
	  this.x = x;
	  this.y = y;
  }

  /**
   * Translate the command into Java code
   */
  private void read(){ 
	  if(reader.hasNext() && !image.getMove()){
		  String command = reader.next();
		  switch(command.toLowerCase()){
		  case "control":
			  x = reader.nextFloat();
			  y = reader.nextFloat();
			  degree = reader.nextFloat();
			  size = reader.nextFloat();
			  time = reader.nextFloat();
			  image.setInterpolAll(x, y, degree, size, time);
			  System.out.printf("[%s] Image to X:%s, Y:%s, Degree:%s, Size:%s in %s secord \n", name,x,y,degree,size,time);
			  break;

		  case "move":
			  x = reader.nextFloat();
			  y = reader.nextFloat();
			  time = reader.nextFloat();
			  image.setInterpolAll(x, y, degree, size, time);
			  System.out.printf("[%s] Moving to X:%s Y:%s in %s secord \n", name,x,y,time);
			  break;

		  case "rotate":
			  degree = reader.nextFloat();
			  time = reader.nextFloat();
			  image.setInterpolAll(x, y, degree, size, time);
			  System.out.printf("[%s] Rotating to %s degree in %s secord \n", name,degree,time);
			  break;

		  case "resize":
			  size = reader.nextFloat();
			  time = reader.nextFloat();
			  image.setInterpolAll(x, y, degree, size, time);
			  System.out.printf("[%s] Resizing to x%s pixel in %s secord \n", name,size,time);
			  break;

		  case "hide":
			  image.hide();
			  System.out.printf("[%s] Calling command: %s image \n", name, command);
			  break;

		  case "show":
			  image.show();
			  System.out.printf("[%s] Calling command: %s image \n", name, command);
			  break;

		  case "addsound":
			  soundName = reader.next();
			  SoundEffect.setPlay(soundName);
			  System.out.printf("[%s] Calling command: %s, %s \n", name, command, soundName);
			  break;

		  case "loopsound":
			  soundName = reader.next();
			  SoundEffect.loop(soundName);
			  System.out.printf("[%s] Calling command: %s, %s \n", name, command, soundName);
			  break;

		  case "stopsound":
			  soundName = reader.next();
			  SoundEffect.stop(soundName);
			  System.out.printf("[%s] Calling command: %s, %s \n", name, command, soundName);
			  break;

		  case "playsound":
			  soundName = reader.next();
			  SoundEffect.play(soundName);
			  System.out.printf("[%s] Calling command: %s, %s \n", name, command, soundName);
			  break;

		  case "layerdown":
			  push = reader.nextShort();
			  image.layerDownImage(push);
			  System.out.printf("[%s] Pushing down %s layer \n", name, push);
			  break;

		  case "layerup":
			  push = reader.nextShort();
			  image.layerUpImage(push);
			  System.out.printf("[%s] Pushing up %s layer \n", name, push);
			  break;

		  case "background":
			  backgroundName = reader.next();
			  Image.addBackground(backgroundName);
			  System.out.printf("[%s] Adding background: %s \n", name, backgroundName);
			  break;

		  case "layerdownbackground":
			  backgroundName = reader.next();
			  push = reader.nextShort();
			  Image.layerDownBackground(backgroundName, push);
			  System.out.printf("[%s] Pushing background %s down %s layer \n", name, backgroundName, push);
			  break;

		  case "layerupbackground":
			  backgroundName = reader.next();
			  push = reader.nextShort();
			  Image.layerUpBackground(backgroundName, push);
			  System.out.printf("[%s] Pushing background %s up %s layer \n", name, backgroundName, push);
			  break;

		  case "hidebackground":
			  backgroundName = reader.next();
			  Image.hideBackground(backgroundName);
			  System.out.printf("[%s] Hiding background: %s \n", name, backgroundName);
			  break;

		  case "showbackground":
			  backgroundName = reader.next();
			  Image.showBackground(backgroundName);
			  System.out.printf("[%s] Showing background: %s \n", name, backgroundName);
			  break;

		  case "resizebackground":
			  backgroundName = reader.next();
			  double scale = reader.nextDouble();
			  Image.resizeBackground(backgroundName, scale);
			  System.out.printf("[%s] Rescaling background %s to %s\n", name, backgroundName, scale);
			  break;

		  case "addtext":
			  x = reader.nextFloat();
			  y = reader.nextFloat();
			  int size = reader.nextInt();
			  text = reader.nextLine();
			  TextDisplay.addText(text, (int)x, (int)y, size);
			  System.out.printf("[%s] Adding text -%s-, sized %s, at X: %s and Y: %s \n", name, text, size, x, y);
			  break;

		  case "hidetext":
			  text = reader.nextLine();
			  TextDisplay.hideText(text);
			  System.out.printf("[%s] Hiding text -%s- \n", name, text);
			  break;

		  case "showtext":
			  text = reader.nextLine();
			  TextDisplay.showText(text);
			  System.out.printf("[%s] Showing text -%s- \n", name, text);
			  break;

		  case "exit":
			  AntimationEngine.stop();
			  System.out.printf("[%s Log] Closing \n", name);
			  //System.out.println("[System] Command called to exit program");
			  //System.exit(0);
			  break;

			  // Cause the parser to skip the line // is found in
		  case "//":
			  reader.nextLine();
			  break;

		  default:
			  System.out.printf("[%s] ERROR: Unable To Parse Command, Double Check Your Code \n", name);
			  break;
		  }
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
	  read();
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
