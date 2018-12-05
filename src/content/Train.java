package content;

import javax.sound.sampled.Clip;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Train sprite.
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
	private Clip trainConstantSound;
	private Clip trainChooChooSound;

	/**
	 * Base constructor for a train sprite.
	 * @param xVal - X position
	 * @param yVal - Y position
	 * @param content - Train image
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
	
	/**
	 * Constructor for if you have sounds for a train sprite.
   * @param xVal - X position
   * @param yVal - Y position
   * @param content - Train image
	 * @param trainConstantSound - Train chugga chugga
	 * @param trainChooChooSound - Train choo choo
	 */
	public Train(int xVal, int yVal, TransformableContent content, Clip trainConstantSound,
	    Clip trainChooChooSound)
	{
		this(xVal, yVal, content);
		this.trainConstantSound = trainConstantSound;
		this.trainChooChooSound = trainChooChooSound;
		trainConstantSound.loop(Clip.LOOP_CONTINUOUSLY);
		trainChooChooSound.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	
	/**
	 * sets the speed of the train to the input speed.
	 * @param speed - Speed to use
	 */
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
					break;
				case 1:
					x -= 1;
					break;
				default:
					x = originalX;
					y = originalY;
					positionNumber = 0;
					break;
			}
		}
			
		positionNumber++;
		setLocation(x,y);

		if(x > 600)
		{
			trainConstantSound.stop();
			trainChooChooSound.stop();
			trainConstantSound.close();
			trainChooChooSound.close();
			//stop rendering me.
		}
	}

}
