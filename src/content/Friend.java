package content;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * .
 * @author Carl
 *
 */
public class Friend extends AbstractSprite
{
	
	private TransformableContent content;

	/**
	 * .
	 * @param xVal - 
	 * @param yVal - 
	 * @param content - 
	 */
	public Friend(int xVal, int yVal, TransformableContent content)
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
	
	@Override
	protected TransformableContent getContent() 
	{
		return content;
	}

	@Override
	public void handleTick(int arg0) 
	{
		//When bernstein jumps off the train needs to speed away (with the people in it)
		this.setLocation(x,y);
		
	}

}
