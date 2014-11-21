package com.hal9001.antimation;

import java.io.FileReader;
import java.util.Scanner;

import com.hal9001.main.IPaser;

public class shaunnk_Animator implements IPaser{

	EZSound song;
	EZImage pic;
	Scanner scan;
	String instruction;

	private double x, y, startx, starty, destx, desty;
	private double angle, startAngle, endAngle;
	private double size, startSize, endSize;
	private long starttime, duration;
	private boolean interpolation;

	public shaunnk_Animator(String textfile, String image, int x, int y)
			throws java.io.IOException {

		scan = new Scanner(new FileReader("shaunnk_resources/"+textfile));

		pic = EZ.addImage("shaunnk_resources/"+image, x, y);

		//song = EZ.addSound("shaunnk_resources/"+songfile);

		//song.play(); 

		interpolation = false;

	}

	public void move(double posx, double posy, long dur) {
		interpolation = true;
		startx = pic.getXCenter();
		starty = pic.getYCenter();
		destx = posx;
		desty = posy;
		duration = dur;
		starttime = System.currentTimeMillis();

	}

	public void rotate(double angle, long dur) {
		interpolation = true;
		startAngle = pic.getRotation();
		endAngle = angle;
		duration = dur;
		starttime = System.currentTimeMillis();

	}

	public void scale(double size, long dur) {
		interpolation = true;
		startSize = pic.getScale();
		endSize = size;
		duration = dur;
		starttime = System.currentTimeMillis();

	}

	public void go(){
		// interpolation = true;
		if (interpolation) {
			// moveto
			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;
			x = (int) (startx + ((float) (destx - startx) * normTime));
			y = (int) (starty + ((float) (desty - starty) * normTime));
			// rotate
			angle = (double) (startAngle + ((float) (endAngle - startAngle) * normTime));

			size = (double) (startSize + ((double) (endSize - startSize) * normTime));

			// interpolation logic
			if ((System.currentTimeMillis() - starttime) >= duration) {

				interpolation = false;
				x = destx;
				y = desty;
				angle = endAngle;
				size = endSize;
			}
			// split
			pic.translateTo(x, y); // moveto
			pic.rotateTo(angle); // rotate
			pic.scaleTo(size);

		} else if (scan.hasNext()) {

			instruction = scan.next();

			switch (instruction) {

			case "COMMAND":

				// interpolation = false;
				x = scan.nextDouble();
				y = scan.nextDouble();
				angle = scan.nextDouble();
				size = scan.nextDouble();
				duration = scan.nextInt();
				move(x, y, duration);
				rotate(angle, duration);
				scale(size, duration);

				break;

			default:

				System.out.println("Invalid Token." + instruction);
				// interpolation = true;

				break;
			}

		}

	}
}

/*
 * public Animator(String textfile, String songfile, String image, int x, int y)
 * throws java.io.IOException {
 * 
 * scan = new Scanner(new FileReader(textfile));
 * 
 * pic = EZ.addImage(image, x, y);
 * 
 * song = EZ.addSound(songfile);
 * 
 * song.play();
 * 
 * interpolation = false;
 * 
 * }
 * 
 * public void move(int posx, int posy, long dur) { interpolation = true; startx
 * = pic.getXCenter(); starty = pic.getYCenter(); destx = posx; desty = posy;
 * duration = dur; starttime = System.currentTimeMillis();
 * 
 * 
 * 
 * }
 * 
 * public void rotate(double angle, long dur) { interpolation = true; startAngle
 * = pic.getRotation(); endAngle = angle; duration = dur; starttime =
 * System.currentTimeMillis();
 * 
 * }
 * 
 * 
 * public void scale(double size, long dur) { interpolation = true; startSize =
 * pic.getScale(); endSize = size; duration = dur; starttime =
 * System.currentTimeMillis();
 * 
 * 
 * }
 * 
 * 
 * public void go() throws java.io.IOException { //interpolation = true; if
 * (interpolation) { //moveto float normTime = (float)
 * (System.currentTimeMillis() - starttime) / (float) duration; x = (int)
 * (startx + ((float) (destx - startx) * normTime)); y = (int) (starty +
 * ((float) (desty - starty) * normTime)); //rotate angle = (double) (startAngle
 * + ((float) (endAngle - startAngle) * normTime));
 * 
 * size = (double) (startSize + ((double) (endSize - startSize) *normTime));
 * 
 * //interpolation logic if ((System.currentTimeMillis() - starttime) >=
 * duration) {
 * 
 * interpolation = false; x = destx; y = desty; angle = endAngle; size =
 * endSize; } //split pic.translateTo(x, y); //moveto pic.rotateTo(angle);
 * //rotate pic.scaleTo(size);
 * 
 * } else if(scan.hasNext()) {
 * 
 * instruction = scan.next();
 * 
 * switch (instruction) {
 * 
 * case "MOVE":
 * 
 * //interpolation = false; x = scan.nextInt(); y = scan.nextInt(); duration =
 * scan.nextInt(); move(x, y, duration);
 * 
 * break; case "ROTATE":
 * 
 * //interpolation = false; angle = scan.nextInt(); duration = scan.nextInt();
 * rotate(angle, duration);
 * 
 * break; case "SCALE":
 * 
 * //interpolation = false; size = scan.nextInt(); duration = scan.nextInt();
 * scale(size, duration); break;
 * 
 * default:
 * 
 * System.out.println("Invalid Token." + instruction); //interpolation = true;
 * 
 * break; }
 * 
 * 
 * 
 * }
 * 
 * 
 * } }
 */