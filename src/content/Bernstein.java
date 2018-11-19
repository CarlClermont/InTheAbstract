package content;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Handles the actions for Bernstein.
 * @author Carl
 *
 */
public class Bernstein extends AbstractSprite
{

	private TransformableContent content;
	private int jumpSpeed = 5;
	private int gravity = 1;
	private int groundHeight = 400;
	private boolean jumped = false;
	private boolean jumping = false;
	private boolean survived;
	private boolean done = false;

	/**
	 * .
	 * @param xVal - 
	 * @param yVal - 
	 * @param content - 
	 */
	public Bernstein(int xVal, int yVal, TransformableContent content)
	{
		super();
		super.x = xVal;
		super.y = yVal;
		
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
	
	/**
	 * 
	 * @param speed - the speed to jump off.
	 * @param survived - weather or not he rolls or slides
	 */
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
	  if(!done && jumped)
	  {
	    if (jumping)
	    {
	      if (y < groundHeight)
	      {
	        //Falling
	        y -= jumpSpeed;
	        jumpSpeed -= gravity;
	      }
	      
	      else
	      {
	        //Hit ground
	        jumping = false;
	      }
	    }
	    
	    
	    else if (survived)
	    {
	      // Roll
	      if(rotationX >= (11 * Math.PI)/6)
	      {
	        //Finished rolling
	        done = true;
	      }
	      
	      rotationX += Math.PI / 6;
	      this.setRotation(rotationX);
	    }
	    
	    else
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
