package application;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;

/**
 * .
 * @author Carl
 * An abstract sprite to be added to the same stage as everything else.
 */
public class Speed extends AbstractSprite
{
	private int speed;
	private int speedChangeDelay;
	private int speedChangeDelayCounter;
	
	/**
	 * .
	 */
	public Speed()
	{
		this.speed = 0;
		this.speedChangeDelay = 10;
		this.speedChangeDelayCounter = 0;
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

	@Override
	protected TransformableContent getContent() 
	{
		return null;
	}

	@Override
	public void handleTick(int arg0) 
	{
		//TODO: psudo random.
		
		//modify speed.
		if(speedChangeDelayCounter >= speedChangeDelay)
		{
			incrementSpeed();
			speedChangeDelayCounter = 0;
		}
		speedChangeDelayCounter++;
		// TODO Auto-generated method stub
		
	}
	
}
