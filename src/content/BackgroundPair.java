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
  
  /**
   * A class to represent a pair of backgrounds used to create the rolling background effect.
   * 
   * @param xVal - x value for pair
   * @param yVal - y value for pair
   * @param contentA - Background 1
   * @param contentB - Background 2
   * @param speed - Speed object to control speed of the rolling background
   * @param speedDivider - Offset needed to fix white line issue
   */
  public BackgroundPair(int xVal, int yVal, TransformableContent contentA, 
      TransformableContent contentB, Speed speed, int speedDivider)
  {
    this.a = new Background(xVal, yVal, contentA, speed, speedDivider);
    this.b = new Background(xVal + Background.BG_WIDTH, yVal, contentB, speed, speedDivider);
    
    a.setSibling(b);
    b.setSibling(a);
  }
  
  /**
   * Getter for background 1.
   * 
   * @return Background 1
   */
  public Background getBackgroundOne()
  {
    return a;
  }
  
  /**
   * Getter for background 2.
   * 
   * @return Background 2
   */
  public Background getBackgroundTwo()
  {
    return b;
  }
}
