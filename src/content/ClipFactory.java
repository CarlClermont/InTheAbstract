package content;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.ResourceFinder;

/**
 * I didn't see any clip factory in his code. so here we go.
 * @author Carl
 *
 */
public class ClipFactory 
{

	private ResourceFinder rf;
	
	/**
	 * creates a clip factory.
	 * @param rf - to help get the files.
	 */
	public ClipFactory(ResourceFinder rf)
	{
		this.rf = rf;
	}
	
	/**
	 * gets a open clip.
	 * @param fileName - the name of the audio file.
	 * @return - an open clip
	 */
	public Clip getClip(String fileName)
	{
		//clip factory.
		Clip clip = null;
		InputStream is;
		BufferedInputStream  bis;
		AudioInputStream     stream = null;
		is     = rf.findInputStream(fileName);
		bis = new BufferedInputStream(is);          

		try 
		{
			stream = AudioSystem.getAudioInputStream(bis);
		} catch (UnsupportedAudioFileException | IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) 
		{
			e.printStackTrace();
		}    
			
		if(clip != null)
		{
			try 
			{
				//NOTE: not sure if im supposed to open these here.
				clip.open(stream);
			} catch (LineUnavailableException | IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return clip;
	}
	
	
}
