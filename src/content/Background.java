package content;

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
	
	private Speed speed;
	private TransformableContent content;
	
	/**
	 * constructor.
	 * @param xVal - sets the starting x value.
	 * @param yVal - sets the starting y value.
	 * @param content - the background as a TC.
	 * @param speed - the rate of motion. 
	 */
	public Background(int xVal, int yVal, TransformableContent content, Speed speed)
	{
		super();
		this.speed = speed;
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
	
	@Override
	public TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
		x = x - speed.getSpeed();
		if (x <= (-1*BG_WIDTH))
		{
			x = BG_WIDTH;
			x = x - speed.getSpeed();
		}
		
		setLocation(x, 0);
	}
	
	
	
	
	
	
	
}
