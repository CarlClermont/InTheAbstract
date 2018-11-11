package content;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

public class Bernstein extends AbstractSprite
{

	private TransformableContent content;
	int jumpSpeed = 5;
	int gravity = 1;
	int groundHeight = 400;
	boolean jumped = false;
	boolean jumping = false;
	boolean survived;
	boolean done = false;

	/**
	 * .
	 * @param xVal - 
	 * @param yVal - 
	 * @param content - 
	 */
	public Bernstein(int xVal, int yVal, TransformableContent content)
	{
		super();
		x = xVal;
		y = yVal;
		
		this.content = content;
		if (this.content == null)
		{
			ContentFactory contentFactory = new ContentFactory();
			this.content = contentFactory.createContent("");
		}
		this.setScale(0.5); //may need changing.
		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	public void jump(int speed, boolean survived)
	{
	  jumped = true;
	  jumping = true;
	  jumpSpeed = speed;
	  
	  this.survived = survived;
	}

	@Override
	protected TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
	  if(jumped && !done)
	  {
	    if(jumping && y < groundHeight)
	    {
	      //Falling
	      y -= jumpSpeed;
	      jumpSpeed -= gravity;
	    }
	    else if(jumping && y >= groundHeight)
	    {
	      //Hit ground
	      jumping = false;
	    }
	    else if(survived)
	    {
	      //Roll
	      if(rotationX >= (11 * Math.PI)/6)
	      {
	        //Finished rolling
	        done = true;
	      }
	      rotationX += Math.PI / 6;
	      this.setRotation(rotationX);
	    }
	    else if(!survived)
	    {
	      //Fall
        if(rotationX >= (2 * Math.PI)/6)
        {
          //Finished rolling
          done = true;
        }
        rotationX += Math.PI / 6;
        this.setRotation(rotationX);
	    }
	  }
	  
		this.setLocation(x,y);
	}
}
