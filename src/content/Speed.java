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
	private int speed;
	private int speedChangeDelay;
	private int speedChangeDelayCounter;
	
	private boolean stopping;
	
	/**
	 * .
	 */
	public Speed()
	{
		this.speed = 0;
		this.speedChangeDelay = 10;
		this.speedChangeDelayCounter = 0;
		
		this.stopping = false;
	}

	/**
	 * .
	 * @return -
	 */
	public int getSpeed()
	{
		return speed;
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
		if(speed > Background.BG_WIDTH)
		{
			speed = Background.BG_WIDTH;
		}
	}
	
	public void stop()
	{
	  stopping = true;
	}

	@Override
	protected TransformableContent getContent() 
	{
		return null;
	}

	@Override
	public void handleTick(int arg0) 
	{
		//TODO: psuedo random.
		
		//modify speed.
		if(speedChangeDelayCounter >= speedChangeDelay)
		{
		  if(!stopping)
		    incrementSpeed();
		  else if(speed > 3)
		    modifySpeed(-3);
		  else
		    modifySpeed(-this.getSpeed());
			speedChangeDelayCounter = 0;
		}
		speedChangeDelayCounter++;		
	}
	
}
