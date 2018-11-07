package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import app.JApplication;
import io.ResourceFinder;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;

/**
 * App for In The Abstract.
 * @author Carl Clermont, Joel Spiers, Paul Barnhill
 * 
 */
public class App extends JApplication implements ActionListener
{

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
	public void actionPerformed(ActionEvent e) 
	{
		String eventName = e.getActionCommand();

		//TODO: Setup Jump event
		
	}

	@Override
	public void init() 
	{
		//Sets up contentPane.
		JPanel contentPane = (JPanel)getContentPane();
		//Sets up hard code layout. 
		contentPane.setLayout(null);
		
		//TODO: Setup first jump on scene
		
		//TODO: Setup train moving game
	}

	
	/*----------------------------------------------------------------------------------------------*/
	/**
	 * main - .
	 * @param args - 
	 */
	public static void main(String[] args)
	{
	  JApplication app = new App(args, 600, 400);
	  invokeInEventDispatchThread(app);
	}
	
}
