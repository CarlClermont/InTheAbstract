package content;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

public class Bernstein extends AbstractSprite
{

	private TransformableContent content;
	int jumpHeight = 200;
	int jumpSpeed = 5;
	int groundHeight = 450;
	boolean jumping = false;
	boolean falling = false;

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
	
	public void jump()
	{
	  jumping = true;
	}

	@Override
	protected TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
	  if(jumping && !falling)
	  {
	    y -= jumpSpeed;
	    if(y <= jumpHeight)
	    {
	      falling = true;
	    }
	  }
	  else if(jumping && falling)
	  {
	    y += jumpSpeed;
	    if(y >= groundHeight)
	    {
	      jumping = false;
	    }
	  }
	  
		this.setLocation(x,y);
	}
}
