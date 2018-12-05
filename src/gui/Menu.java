package gui;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import io.ResourceFinder;
import visual.Visualization;
import visual.VisualizationView;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;

/**
 * Main menu for In the Abstract game.
 * @author Joel
 */
public class Menu extends JPanel
{

  private static final long serialVersionUID = 1L;
  private final String backgroundName = "menu_background.gif";
  private final String startButtonName = "menu_start_button.gif";
  private final String exitButtonName = "menu_exit_button.gif";
  
  private JButton start, exit;
  private ImageIcon startImage, exitImage;
  private Content background;
  
  /**
   * Constructor for a Menu.
   * 
   * @param width Window width
   * @param height Window height
   * @param al ActionListener to send button actions to
   */
  public Menu(int width, int height, ActionListener al)
  {
    setBounds(0, 0, width, height);
    setLayout(null);
    
    ResourceFinder rf = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory cf = new ContentFactory(rf);
    
    startImage = new ImageIcon(rf.findURL(startButtonName));
    exitImage = new ImageIcon(rf.findURL(exitButtonName));
    
    start = new JButton(startImage);
    start.setLocation((width/2)-100, (height/4)-60);
    start.setSize(200, 50);
    start.addActionListener(al);
    start.setActionCommand("start");
    add(start);
    
    exit = new JButton(exitImage);
    exit.setLocation((width/2)-75, (height/4));
    exit.setSize(150, 35);
    exit.addActionListener(al);
    exit.setActionCommand("exit");
    add(exit);
    
    Visualization vis = new Visualization();
    VisualizationView view = vis.getView();
    view.setBounds(0,0,width,height);
    view.setSize(width, height);
    vis.toBack(background);
    
    background = cf.createContent(backgroundName, 4);
    background.setLocation(0, 0);
    vis.add(background);
    
    add(view);
  }
}
