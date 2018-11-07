package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.dynamic.described.RuleBasedSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl
 * For the random speed it needs to be created elsewhere since the two backgrounds will
 * have different implementations so they need to be sent the same value in modifySpeed().
 */
public abstract class Background extends RuleBasedSprite
{
	private final int BG_WIDTH = 2400; //size of background image in pixels
	private final int BG_HEIGHT = 600; //size of background image in pixels
	
	private double                    x, y;
	protected TransformableContent[] contents;
	private int speed;
	
	
	/**
	 * constructor.
	 * @param xVal - sets the starting x value.
	 */
	public Background(int xVal)
	{
		super();
		//start at speed 0 and speed up later.
		contents = new TransformableContent[2];
		speed = 0;
		y = 0;
		x = xVal;
				
		//get two copies of the background 
		//(so that when the first finishes moving across we have another ready).
		ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
		ContentFactory contentFactory = new ContentFactory();
		
		BufferedImage bufferedImage;
		Content c;
		try 
		{
			InputStream    is;
			is = rf.findInputStream("Background lowres.png");
			bufferedImage = ImageIO.read(is);
			contents[0] = contentFactory.createContent(bufferedImage);
			contents[1] = contentFactory.createContent(bufferedImage);
			is.close();
		}
		catch (IOException e)
		{
			
		}
		
	}
	
	/**
	 * increments the speed by 1.
	 */
	public void incrementSpeed()
	{
		modifySpeed(10);
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
	
	@Override
	public TransformableContent getContent() 
	{
		return contents[0];
	}

	@Override
	public void handleTick(int arg0) 
	{
		incrementSpeed();
		//TODO: RANDOM SPEED.
		x = x - speed;
		setLocation(x, 0);
	}
	
	
	
	
	
	
	
}
