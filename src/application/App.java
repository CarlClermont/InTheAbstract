package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import app.JApplication;
import content.Background;
import content.Bernstein;
import content.Friend;
import content.Speed;
import content.Train;
import event.MetronomeListener;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.described.Stage;
import visual.statik.sampled.ContentFactory;

/**
 * App for In The Abstract.
 * @author Carl Clermont, Joel Spiers, Paul Barnhill
 * 
 */
public class App extends JApplication implements ActionListener, MetronomeListener
{
	//for some reason .png doesn't keep the alpha channel when I save it so,
	//I use .gif if it needs alpha.
	private final String backgroundName = "Background lowres.png";
	private final String trainName = "train_1.gif";
	private final String happyFriendName = "person_0.gif";
	private final String sadFriendName = "person_1.gif";
	private final String normalBernsteinName = "bernstein_0.gif";
	private final String happyBernsteinName = "bernstein_1.gif";
	private final String bowersName = "bowers_0.gif";
	private final String nortonName = "norton_0.gif";
	private final String spragueName = "sprague_0.gif";
	
	
	
	
	/**
	 * constructor.
	 * @param width - of the window
	 * @param height - of the window
	 */
	public App(int width, int height)
	{
		super(width, height);
	}
	
	/**
	 * constructor.
	 * @param args - input
	 * @param width - of the window
	 * @param height - of the window
	 */
	public App(String[] args, int width, int height) 
	{
		super(args, width, height);
	}

	@Override
	public void init() 
	{
		//Sets up contentPane.
		JPanel contentPane = (JPanel)getContentPane();
		//Sets up hard code layout. 
		contentPane.setLayout(null);
		Speed speed = new Speed();
		ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
		ContentFactory contentFactory = new ContentFactory(rf);
		
		//TODO: Setup first jump on scene
		
		//TODO: Setup train moving game
		Stage stage = new Stage(50);
		VisualizationView stageView = stage.getView();
		stageView.setBounds(0, 0, width, height);
		Background bgA = new Background(0, 0, 
				contentFactory.createContent(backgroundName, 4), speed);
		Background bgB = new Background(Background.BG_WIDTH, 0, 
				contentFactory.createContent(backgroundName, 4), speed);
		//X positions for Bernstein and friends in carts: 105, 175, 245
		Friend friend1 = new Friend(105, 280, contentFactory.createContent(happyFriendName, 4)); 
		Friend friend2 = new Friend(245, 280, contentFactory.createContent(happyFriendName, 4)); 
		Friend conductor = new Friend(338, 285, contentFactory.createContent(happyFriendName, 4));
		Bernstein bernstein = new Bernstein(175, 280, 
				contentFactory.createContent(normalBernsteinName, 4));
		Train train = new Train(100, 265, contentFactory.createContent(trainName, 4));
		stage.add(bgA);
		stage.add(bgB);
		stage.add(friend1);
		stage.add(friend2);
		stage.add(conductor);
		stage.add(bernstein);
		stage.add(train);
		stage.add(speed);
		
		contentPane.add(stageView);
		stage.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String eventName = e.getActionCommand();

		//TODO: Setup Jump event
		
	}
	
	@Override
	public void handleTick(int arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/*----------------------------------------------------------------------------------------------*/
	/**
	 * main - .
	 * @param args - 
	 */
	public static void main(String[] args)
	{
	  JApplication app = new App(args, 600, 600);
	  invokeInEventDispatchThread(app);
	}


	
}
