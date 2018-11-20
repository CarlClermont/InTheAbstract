package content;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl Clermont
 * @author Paul Barnhill
 * @version 2018-11-11
 * For the random speed it needs to be created elsewhere since the two backgrounds will
 * have different implementations so they need to be sent the same value in modifySpeed().
 */
public class Background extends AbstractSprite
{
	public static final int BG_WIDTH = 2400; //size of background image in pixels
	public static final int BG_HEIGHT = 600; //size of background image in pixels
	
	// Used for seamless transition
	private Background sibling;
	
	private Speed speed;
	private int speedDivider;
	private TransformableContent content;
	
	/**
	 * constructor.
	 * @param xVal - sets the starting x value.
	 * @param yVal - sets the starting y value.
	 * @param content - the background as a TC.
	 * @param speed - the rate of motion. 
	 * @param speedDivider - if the background needs to be slowed. should be 1 for full speed.
	 */
	public Background(int xVal, int yVal, TransformableContent content, Speed speed, int speedDivider)
	{
		super();
		this.speed = speed;
		this.speedDivider = speedDivider;
		x = xVal;
		y = yVal;
		
		this.content = content;
		if (this.content == null)
		{
			ContentFactory contentFactory = new ContentFactory();
			this.content = contentFactory.createContent("");
		}

		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	/**
	 * Sets the background pair so they can know attributes of the other background.
	 * @param sibling - the other instance of a background.
	 */
	public void setSibling(Background sibling)
	{
	  if (sibling == this)
	  {
	    throw new IllegalArgumentException("Sibling cannot be the same background!");
	  }
	  
	  this.sibling = sibling;
	}
	
	@Override
	public TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int millis) 
	{
	  //System.out.println("BG - millis: " + millis);
	  int currSpeed = getCurrentSpeed();
		x -= currSpeed;
		
		// Background has gone off the left side, put it directly behind its sibling.
		if (x <= (-1*BG_WIDTH))
		{
		  System.out.println("x before fix: " + x);
			
		  if (sibling != null)
		  {
		    x = sibling.getHorizontalPos() + BG_WIDTH - currSpeed;
		  }
		  else
		  {
		    x = BG_WIDTH - currSpeed;
		  }
		  
			System.out.println("x after fix: " + x);
			System.out.println("\tspeed: " + currSpeed + " MPH");
			System.out.println("\tmillis: " + millis + "\n");
		}	
		
		// Background will always start from the top, so Y is 0.
		setLocation(x, 0);
	}
	
	/**
	 * Gets the current x-coordinate.
	 * @return horizontal position
	 */
	public double getHorizontalPos()
	{
	  return x;
	}
	
	/**
	 * Returns the current speed the background is moving.
	 * @return the normal speed divided by a number > 0. if 1 then it is the normal speed.
	 */
	private int getCurrentSpeed()
	{
		int currentSpeed = speed.getSpeed();
		if(speedDivider > 0)
		{
			currentSpeed /= speedDivider;
		}
		return currentSpeed;
	}
	
	
	
	
	
	
	
}
