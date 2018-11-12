package content;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl
 *
 */
public class Train extends AbstractSprite
{
	private TransformableContent content;
	private int positionNumber;
	private int originalX;
	private int originalY;
	private int speed;

	/**
	 * .
	 * @param xVal - 
	 * @param yVal - 
	 * @param content - 
	 */
	public Train(int xVal, int yVal, TransformableContent content)
	{
		super();
		x = xVal;
		y = yVal;
		originalX = xVal;
		originalY = yVal;
		positionNumber = 0;
		speed = 0;
		
		this.content = content;
		if (this.content == null)
		{
			ContentFactory contentFactory = new ContentFactory();
			this.content = contentFactory.createContent("");
		}
		
		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	public void setSpeed(int speed)
	{
	  this.speed = speed;
	}
	
	@Override
	protected TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
		//When bernstein jumps off the train needs to speed away (with the people in it)
	  if(speed > 0)
	  {
	    x += speed;
	  }
	  
	  else
	  {
	    switch (positionNumber)
	    {
	      case 0:
	        x += 1;
	        //				y += 3;
	        break;
	        
	      case 1:
	        x -= 1;
	        //				y -= 3;
	        break;
	        
	        //			case 2:
	        //				x -= 1;
	        //				y -= 3;
	        //				break;
	        //			case 3:
	        //				x += 1;
	        //				y += 3;
	        //				break;
	        
	      default:
	        x = originalX;
	        y = originalY;
	        positionNumber = 0;
	        break;
	    }
	  }
		
		positionNumber++;
		setLocation(x,y);
	}

}
