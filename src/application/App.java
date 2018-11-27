package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import app.JApplication;
import content.Background;
import content.BackgroundPair;
import content.Bernstein;
import content.ClipFactory;
import content.Friend;
import content.Speed;
import content.Train;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.described.Stage;
import visual.statik.sampled.ContentFactory;

/**
 * App for In The Abstract.
 * @author Carl Clermont, Joel Spiers, Paul Barnhill
 * 
 */
public class App extends JApplication implements KeyListener
{
	//for some reason .png doesn't keep the alpha channel when I save it so,
	//I use .gif if it needs alpha.
	private final String backgroundName = "Background lowres.gif";
	private final String cloudsName = "clouds.gif";
	private final String trainName = "train_1.gif";
	private final String happyFriendName = "person_0.gif";
	private final String sadFriendName = "person_1.gif";
	private final String normalBernsteinName = "bernstein_0.gif";
	private final String happyBernsteinName = "bernstein_1.gif";
	private final String bowersName = "bowers_0.gif";
	private final String nortonName = "norton_0.gif";
	private final String spragueName = "sprague_0.gif";
	private final String jumpSoundName = "jump_grunt_0.aiff";
	private final String surviveSoundName = "pain_grunt_2.aiff";
	private final String deathSoundName = "pain_grunt_4.aiff";
	private final int trainLeaveSpeed = 10;
	private final int safeJumpSpeed = 25;
	private final int friendOneX = 105;
	private final int friendOneY = 280;
	private final int friendTwoX = 245;
	private final int friendTwoY = 280;
	private final int bernsteinX = 175;
	private final int bernsteinY = 280;
	private final int conductorX = 338;
	private final int conductorY = 285;
	
	private JPanel contentPane;
	private Stage stage;
	private Speed speed;
	private Bernstein bernstein;
	private Train train;
	private Friend friend1, friend2, conductor;
	
	
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
		contentPane = (JPanel)getContentPane();
		
		//Sets up hard code layout. 
		contentPane.setLayout(null);
		
		//So we have a method that resets to the very beginning of the game.
		//easier than finding every variable of existing objects and reseting them.
		startGame();
		
		//Note: tombstone gifs use 'Informal Roman' font.
	}
	
	/**
	 * Starts or resets the game.
	 * NOTE: May need to be changed to public, depending on how we reset. If a main menu object
	 * is used to reset then it will need to be.
	 */
	private void startGame()
	{
		contentPane.removeAll(); //So it can reset.
		
		speed = new Speed();
		ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
		ContentFactory contentFactory = new ContentFactory(rf);
		ClipFactory clipFactory = new ClipFactory(rf);
		
		//TODO: Main Menu
		
		// Setup train moving game (50ms between ticks)
		stage = new Stage(50);
		VisualizationView stageView = stage.getView();
		stageView.setBounds(0, 0, width, height);
		
		BackgroundPair bgPair = new BackgroundPair(0, 0,
		    contentFactory.createContent(backgroundName, 4),
		    contentFactory.createContent(backgroundName, 4), speed, 1);
		Background bgA = bgPair.getBackgroundOne();
		Background bgB = bgPair.getBackgroundTwo();
		
		BackgroundPair cloudPair = new BackgroundPair(0, 0,
			    contentFactory.createContent(cloudsName, 4),
			    contentFactory.createContent(cloudsName, 4), speed, 10);
		Background cloudsA = cloudPair.getBackgroundOne();
		Background cloudsB = cloudPair.getBackgroundTwo();
		
		//X positions for Bernstein and friends in carts: 105, 175, 245
		friend1 = new Friend(friendOneX, friendOneY, contentFactory.createContent(happyFriendName, 4)); 
		friend2 = new Friend(friendTwoX, friendTwoY, contentFactory.createContent(happyFriendName, 4)); 
		conductor = new Friend(conductorX, conductorY, 
				contentFactory.createContent(happyFriendName, 4));
		
		bernstein = new Bernstein(bernsteinX, bernsteinY, 
				contentFactory.createContent(normalBernsteinName, 4),
				clipFactory.getClip(jumpSoundName), clipFactory.getClip(surviveSoundName), 
				clipFactory.getClip(deathSoundName));
		
		train = new Train(100, 265, contentFactory.createContent(trainName, 4));
		
		stage.add(cloudsA);
		stage.add(cloudsB);
		stage.add(bgA);
		stage.add(bgB);
		stage.add(friend1);
		stage.add(friend2);
		stage.add(conductor);
		stage.add(bernstein);
		stage.add(train);
		stage.add(speed);
		stage.addKeyListener(this);
		
		contentPane.add(stageView);
		stage.start();
	}
	
	
	/*-KEY-LISTENERS--------------------------------------------------------------------------------*/
	@Override
	public void keyPressed(KeyEvent e){}

	@Override
	public void keyReleased(KeyEvent e){}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == ' ')
		{
			System.out.println(speed.getSpeed());
			boolean survived = speed.getSpeed() <= safeJumpSpeed;
	      
			speed.stop();
	      
			bernstein.jump(15, survived);
	      
			train.setSpeed(trainLeaveSpeed);
			friend1.setSpeed(trainLeaveSpeed);
			friend2.setSpeed(trainLeaveSpeed);
			conductor.setSpeed(trainLeaveSpeed);
	      
			//Render Bernstein in front of the train
			stage.remove(bernstein);
			stage.add(bernstein);
		}
		//R to reset. TODO: CHANGE TO A BUTTON THAT CAN ONLY BE PRESSED BEFORE OR AFTER GAME
		else if(e.getKeyChar() == 'r')
		{
			startGame();
		}
	}
	
	
	/*-MAIN-----------------------------------------------------------------------------------------*/
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
