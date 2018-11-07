package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import app.JApplication;
import event.MetronomeListener;
import visual.VisualizationView;
import visual.dynamic.described.Stage;

/**
 * App for In The Abstract.
 * @author Carl Clermont, Joel Spiers, Paul Barnhill
 * 
 */
public class App extends JApplication implements ActionListener, MetronomeListener
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
	public void init() 
	{
		//Sets up contentPane.
		JPanel contentPane = (JPanel)getContentPane();
		//Sets up hard code layout. 
		contentPane.setLayout(null);
		
		//TODO: Setup first jump on scene
		
		//TODO: Setup train moving game
		Stage stage = new Stage(50);
		VisualizationView stageView = stage.getView();
		stageView.setBounds(0, 0, width, height);
		Background bgA = new Background(0, 0);
		Background bgB = new Background(Background.BG_WIDTH, 0);
		stage.add(bgA);
		stage.add(bgB);
		
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
		System.out.println("HI");
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
