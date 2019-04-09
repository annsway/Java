package guimodule;

import processing.core.PApplet;
import processing.core.PImage;


public class SunChangingColor extends PApplet {
	
	PImage img; 
	
	public void setup() //only run once
	{
		size(400, 400); //set canvas size
		background(255); //set canvas color
		stroke(0);	//set pen color
		img = loadImage("imageUrl", "jpg");
		img.resize(0, height);
		image(img, 0, 0);
		
	}

	public void draw()
	{
		int[] color = sunColorSec(second()); //built-in method using system clock; 
		//it returns the seconds elapsed since the last minute according to your system's clock
		fill(color[0], color[1], color[2]);
		ellipse(width/4, height/5, width/4, height/5); //draw sun

	}
	
	public int[] sunColorSec(float seconds)
	{
		int[] rgb = new int[3]; // Create an array with 3 color elements 
		
		//Scale the brightness of the yellow based on the seconds. 
		//30 seconds is black. 0 seconds is bright yellow.
		
		float diffFrom30 = Math.abs(30 - seconds);
		
		float ratio = diffFrom30/30; // how far are we from 30
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
		
	}
}
