package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl
 * For the random speed it needs to be created elsewhere since the two backgrounds will
 * have different implementations so they need to be sent the same value in modifySpeed().
 */
public class Background extends AbstractSprite
{
	public static final int BG_WIDTH = 2400; //size of background image in pixels
	public static final int BG_HEIGHT = 600; //size of background image in pixels
	private final int speedChangeDelay = 10; //how many ticks till the speed increases.
	
	private double                    x, y;
	private int speed;
	private int speedChangeDelayCounter;
	private TransformableContent content;
	
	/**
	 * constructor.
	 * @param xVal - sets the starting x value.
	 * @param yVal - sets the starting y value.
	 */
	public Background(int xVal, int yVal)
	{
		super();
		//start at speed 0 and speed up later.
		speed = 10;
		//The amount of ticks needed to change the increase in speed.
		speedChangeDelayCounter = 0;
		x = xVal;
		y = yVal;
				
		ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
		ContentFactory contentFactory = new ContentFactory();
		BufferedImage bufferedImage;
		try 
		{
			InputStream    is;
			is = rf.findInputStream("Background lowres.png");
			bufferedImage = ImageIO.read(is);
			content = contentFactory.createContent(bufferedImage);
			is.close();
		}
		catch (IOException e)
		{
			
		}
		this.setLocation(x,y);
		this.setVisible(true);
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
		if(speed < 0)
		{
			speed = 0;
		}
		if(speed > BG_WIDTH)
		{
			speed = BG_WIDTH;
		}
	}
	
	@Override
	public TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
		if(speedChangeDelayCounter >= speedChangeDelay)
		{
			incrementSpeed();
			speedChangeDelayCounter = 0;
		}
		//TODO: RANDOM SPEED.
		x = x - speed;
		if (x <= (-1*BG_WIDTH))
		{
			x = BG_WIDTH;
			x = x - speed;
		}
		speedChangeDelayCounter++;
		
		setLocation(x, 0);
	}
	
	
	
	
	
	
	
}
