package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import io.ResourceFinder;
import visual.statik.sampled.CompositeContent;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl
 *
 */
public class Background extends CompositeContent
{
	private Content bg1;
	private Content bg2;
	private int speed;
	
	
	/**
	 * constructor.
	 */
	public Background()
	{
		ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
		ContentFactory contentFactory = new ContentFactory();
		
		BufferedImage bufferedImage;
		Content c;
		try 
		{
			InputStream    is;
			is = rf.findInputStream("Background lowres.png");
			bufferedImage = ImageIO.read(is);
			bg1 = contentFactory.createContent(bufferedImage);
			bg2 = contentFactory.createContent(bufferedImage);
			is.close();
		}
		catch (IOException e)
		{
			
		}
		
		//start at speed 0 and speed up later.
		speed = 0;
	}
	
	/**
	 * increments the speed by 1.
	 */
	public void incrementSpeed()
	{
		modifySpeed(1);
	}
	
	/**
	 * Modifies the speed by the modifier.
	 * @param modifier - the number to increase the speed by (can be negative)
	 */
	public void modifySpeed(int modifier)
	{
		speed += modifier;
		if(speed > 0)
		{
			speed = 0;
		}
	}
	
	
	
	
	
}
