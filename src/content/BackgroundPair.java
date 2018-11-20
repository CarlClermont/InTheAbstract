package content;

import visual.statik.TransformableContent;

/**
 * We need two instances of background which are "siblings" (always next to each other and
 * moving at the same speed), so they're encapsulated into a single collection.
 * 
 * @author Paul Barnhill
 * @version 2018-11-11
 */
public class BackgroundPair
{
  private Background a;
  private Background b;
  
  private TransformableContent contentA;
  private TransformableContent contentB;
  
  public BackgroundPair(int xVal, int yVal, TransformableContent contentA, 
      TransformableContent contentB, Speed speed, int speedDivider)
  {
    this.a = new Background(xVal, yVal, contentA, speed, speedDivider);
    this.b = new Background(xVal + Background.BG_WIDTH, yVal, contentB, speed, speedDivider);
    
    a.setSibling(b);
    b.setSibling(a);
  }
  
  public Background getBackgroundOne()
  {
    return a;
  }
  
  public Background getBackgroundTwo()
  {
    return b;
  }
}
