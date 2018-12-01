package content;

import javax.sound.sampled.Clip;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Funeral - Funeral scene for when you lose.
 * @author Joel
 *
 */
public class Funeral extends AbstractSprite
{
  private TransformableContent content;
  private Clip music;
  private int ticksPassed, ticksToWait;
  
  /**
   * Funeral - Constructor for a Funeral
   * @param x X position
   * @param y Y position
   * @param content Content image
   * @param music Music for funeral
   * @param ticksToWait Ticks to wait before starting funeral
   */
  public Funeral(int x, int y, TransformableContent content, Clip music, int ticksToWait)
  {
    this.content = content;
    if (this.content == null)
    {
      ContentFactory contentFactory = new ContentFactory();
      this.content = contentFactory.createContent("");
    }
    
    this.music = music;
    
    ticksPassed = 0;
    this.ticksToWait = ticksToWait;
    
    this.x = x;
    this.y = y;
    this.setLocation(x, y);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(int arg0)
  {
    ticksPassed++;
    if(ticksToWait == ticksPassed)
    {
      music.loop(1);
      this.setVisible(true);
    }
  }
}
