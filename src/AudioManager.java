import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioManager {
	private final File toneFile;
	private Clip tone;

    public AudioManager(String audioFile){
		toneFile = new File(audioFile);
        try{
            tone = AudioSystem.getClip();
            tone.open(AudioSystem.getAudioInputStream(toneFile.getAbsoluteFile()));
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }

	public void play(){
		tone.start();
		tone.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop(){
		tone.stop();
		tone.setFramePosition(0);
	}
}
