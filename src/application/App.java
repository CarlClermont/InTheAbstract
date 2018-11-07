package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import app.JApplication;

/**
 * App for In The Abstract.
 * @author Carl Clermont, Joel Spiers, Paul Barnhill
 * 
 */
public class App extends JApplication implements ActionListener
{

	/**
	 * .
	 * @param width - 
	 * @param height - 
	 */
	public App(int width, int height)
	{
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * .
	 * @param args -
	 * @param width - 
	 * @param height - 
	 */
	public App(String[] args, int width, int height) 
	{
		super(args, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String eventName = e.getActionCommand();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() 
	{
		//Sets up contentPane.
		JPanel contentPane = (JPanel)getContentPane();
		//Sets up hard code layout. 
		contentPane.setLayout(null);
		// TODO Auto-generated method stub
		
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
