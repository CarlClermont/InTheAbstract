package content;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;

/**
 * .
 * @author Carl
 * An abstract sprite to be added to the same stage (and metronome) as everything else.
 */
public class Speed extends AbstractSprite
{
  public static final int MAX_SPEED = 100;
  public static final int SPEED_CHANGE_DELAY = 10;
  
	private int speed;
	private int speedChangeDelayCounter;
	
	private boolean stopping;
	
	/**
	 * Default constructor for Speed.
	 */
	public Speed()
	{
		this.speed = 0;
		this.speedChangeDelayCounter = 0;
		
		this.stopping = false;
	}

	/**
	 * Gets the current speed in MPH.
	 * @return Speed in MPH
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * Increments the speed by 1.
	 */
	public void incrementSpeed()
	{
		modifySpeed(1);
	}
	
	
	/**
	 * Modifies the speed by the modifier. Speed will always be within [0, MAX_SPEED].
	 * @param modifier - the number to increase the speed by (can be negative)
	 */
	public void modifySpeed(int modifier)
	{
		speed += modifier;
		
		if (speed < 0)
		{
			speed = 0;
		}
		
		else if (speed > MAX_SPEED)
		{
			speed = MAX_SPEED;
		}
	}
	
	/**
	 * Begins gradual stop of speed.
	 */
	public void stop()
	{
	  stopping = true;
	}
	
	/**
	 * Instantly stops speed.
	 */
	public void instantStop()
	{
	  stopping = true;
	  speed = 0;
	}

	@Override
	protected TransformableContent getContent() 
	{
		return null;
	}

	/**
	 * Increases speed up to 100 MPH while not stopping. If stopping, gradually slows speed down.
	 * Velocity changes occur 1/10 ticks.
	 * TODO: psuedo random.
	 */
	@Override
	public void handleTick(int millis) 
	{
	  /* Trains CAN go faster than 100 MPH, but the BG becomes painful to look at. */ 
		
		// Modify speed 1/10 ticks (500ms interval).
		if (speedChangeDelayCounter >= SPEED_CHANGE_DELAY)
		{
		  if (!stopping)
		  {
		    // Increment speed up to 100
		    if (speed < MAX_SPEED)
		    {
	        incrementSpeed();
		    }
		  }
		  
		  // Train is stopping; Make a smooth stop.
		  else if (speed > 3)
		  {
		    modifySpeed(-3);
		  }
		  
		  // Complete stop
		  else
		  {
		    modifySpeed(-this.getSpeed());
		  }
		  
		  // Reset interval
			speedChangeDelayCounter = 0;
		}
		
		speedChangeDelayCounter++;		
	}
	
}
