package com.hal9001.antimation;
import java.io.FileReader;
import java.util.Scanner;

import com.hal9001.main.IPaser;

public class lorink_TestAnim implements IPaser{
		
		private EZImage Actor;
		private int x, y, startx, starty, angle, startangle;
		private int destx, desty, destangle;
		private float destscale, startscale, scalea, comscale;
		private long starttime;
		private long duration;
		private boolean interpolation;
		String Script;
		String command;
		Scanner reader;
		private int comx, comy, comdur, comangle;
		private boolean visible;
		private boolean layer;
		
		public lorink_TestAnim(String picturename, String filename) throws java.io.IOException{
			Actor = EZ.addImage("lorink_resources/"+picturename, x, y);
			scalea = 1; destscale = 1; startscale = 1;
			Script = filename;
			interpolation = false;
			reader = new Scanner(new FileReader("lorink_resources/"+Script));
		}
		
		public void getCommand(){
			if(interpolation == false){
				if(reader.hasNextLine()){
					if(reader.hasNext()){
						command = reader.next();
					}

					switch(command){
					case "control":
						comx = reader.nextInt();
						comy = reader.nextInt();
						comangle = reader.nextInt();
						comscale = reader.nextFloat();
						comdur = reader.nextInt();
						visible = reader.nextBoolean();
						layer = reader.nextBoolean();
						
						break;
					}
				}
			}
		}
		
		public void Control(int posx, int posy, int angle1, float scale1, long dur, boolean showing, boolean foreground){
			destx = posx; desty = posy;
			destangle = angle1;
			destscale = scale1;
			
			duration = dur*1000;
			
			starttime = System.currentTimeMillis();
			
			startx=x; starty=y;
			startangle = angle;
			startscale = scalea;
			
			if(showing){
				Actor.show();
			}
			else if(!showing){
				Actor.hide();
			}
			
			if(foreground){
				Actor.pullForwardOneLayer();
			}
			else if(!foreground){
				Actor.pushBackOneLayer();
			}
			
			interpolation = true;
		}

		public boolean moving() {
			if (interpolation = true) {
				float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) duration;
				
				x = (int) (startx + ((float) (destx - startx) *  normTime));
				y = (int) (starty + ((float) (desty - starty) *  normTime));
				angle = (int) (startangle + ((float) (destangle - startangle) *  normTime));
				scalea = (float) (startscale + ((float) (destscale - startscale) * normTime));
			}
			
			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				x = destx; y = desty;
				startx = x; starty = y;
				angle = destangle;
				startangle = angle;
				scalea = (float)destscale;
				startscale = scalea;
			}
			return interpolation;
		}
		
		@Override
		public void go(){
			command = "";
			
			getCommand();
			
			switch(command){
			case "control":
				Control(comx, comy, comangle, comscale, comdur, visible, layer);
				break;
			}
			
			if(interpolation == true){moving();}
			
			Actor.translateTo(x,y);
			Actor.rotateTo(angle);
			Actor.scaleTo(scalea);	
		}
}