package content;

import javax.sound.sampled.Clip;

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
	//Note survive sound should be clip _2 and death should be _5 in my opinion - Carl
	
	private TransformableContent content;
	private int jumpSpeed = 5;
	private int gravity = 1;
	private int groundHeight = 400;
	private boolean jumped = false;
	private boolean jumping = false;
	private boolean survived;
	private boolean done = false;
	private Clip jumpSound;
	private Clip surviveSound; 
	private Clip deathSound;

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
		
		//Visual Content
		this.content = content;
		if (this.content == null)
		{
			ContentFactory contentFactory = new ContentFactory();
			this.content = contentFactory.createContent("");
		}
		this.setScale(0.5); //may need changing.
		
		//Auditory Content
		
		
		//
		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	/**
	 * sounds are not needed for a berstein to exist. So here is a seperate constructor.
	 * @param xVal - 
	 * @param yVal - 
	 * @param content -
	 * @param jumpSound - 
	 * @param surviveSound - 
	 * @param deathSound - 
	 */
	public Bernstein(int xVal, int yVal, TransformableContent content, 
			Clip jumpSound, Clip surviveSound, Clip deathSound)
	{
		this(xVal, yVal, content);
		this.jumpSound = jumpSound;
		this.surviveSound = surviveSound;
		this.deathSound = deathSound;
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
	  this.jumpSpeed = speed;
	  jumpSound.start();
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
	        if(survived)
	        {
	        	surviveSound.start();
	        	
	        }
	        else
	        {
	        	deathSound.start();
	        }
	        
	      }
	    }
	    
	    
	    else if (survived)
	    {
	      // Roll
	      if(rotationX >= (11 * Math.PI)/6)
	      {
	        //Finished rolling
	        done = true;
	        jumpSound.close();
	        surviveSound.close();
	        deathSound.close();
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
