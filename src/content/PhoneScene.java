package content;

import javax.sound.sampled.Clip;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Success scene.
 * 
 * @author Joel
 */
public class PhoneScene extends AbstractSprite
{
  private final String scene0name = "success_part0.jpg";
  private final String scene1name = "success_part1.jpg";
  private final String scene2name = "success_part2.jpg";
  private final String scene3name = "success_part3.jpg";
  private final String scene4name = "success_part4.jpg";
  private final String scene5name = "success_part5.jpg";
  private final String scene6name = "success_part6.jpg";
  private final String ringName = "ring.aiff";
  private final String clickName = "click.aiff";
  
  private TransformableContent content;
  private ContentFactory contentFactory;
  private ClipFactory clipFactory;
  private Clip ring, click;
  private int ticksPassed, ticksToWait, ticksBetweenScenes;
 
  /**
   * Constructor for success scene.
   * 
   * @param contentFactory ContentFactory to use
   * @param clipFactory ClipFactory to use
   * @param ticksToWait ticks to wait before showing scene
   */
  public PhoneScene(ContentFactory contentFactory, ClipFactory clipFactory, int ticksToWait)
  {
    x = 0;
    y = 0;
    this.setLocation(x, y);
    
    this.clipFactory = clipFactory;
    this.contentFactory = contentFactory;
    
    content = contentFactory.createContent(scene0name);
    ring = clipFactory.getClip(ringName);
    click = clipFactory.getClip(clickName);
    
    this.ticksToWait = ticksToWait;
    ticksBetweenScenes = 50;
    ticksPassed = 0;
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
    if(ticksToWait > ticksPassed)
    {
      return;
    }
    else if(ticksToWait == ticksPassed)
    {
      this.setVisible(true);
    }
    System.out.println((ticksPassed-ticksToWait)/ticksBetweenScenes);
      
    switch((ticksPassed-ticksToWait)/ticksBetweenScenes)
    {
      case 1:   content = contentFactory.createContent(scene1name);
                break;
                
      case 2:   content = contentFactory.createContent(scene2name);
                ring.loop(1);
                break;
                  
      case 3:   content = contentFactory.createContent(scene3name);
                ring.stop();
                break;

      case 4:   content = contentFactory.createContent(scene4name);
                break;

      case 5:   content = contentFactory.createContent(scene5name);
                break;
      
      case 6:   content = contentFactory.createContent(scene6name);
                break;
                
      case 7:   content = contentFactory.createContent(scene1name);
                click.loop(0);
                break;
      
      default:  break;
    }
  }
}  

