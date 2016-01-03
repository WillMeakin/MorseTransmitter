import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inputter implements KeyListener{

	int sendKey = KeyEvent.VK_ENTER;

	//Timing - TODO: Farnsworth speed for playback.
	int wpm = 10;
	int dotTime = 1200/wpm;	//Milliseconds
	int dashTime = 3*dotTime;
	int spaceTime = 7*dotTime;
	int errMargin = dotTime/2;

	long beginPress = -1;
	long endPress = -1;

	boolean keyBeingHeld = false;	//Ensures key pressed only once on hold.
	boolean atBeginning = true;		//Prevents leading space (" / ")

	String lastLetter = "";

	GUIManager gui = new GUIManager(this);
	AudioManager audioManager = new AudioManager("550.wav");
	MorseTranslator translator = new MorseTranslator();

	public Inputter(){
	}

	public void keyPressed(KeyEvent key){

		if (key.getKeyCode() == sendKey && !keyBeingHeld){
			audioManager.play();
			beginPress = System.currentTimeMillis();
			keyBeingHeld = true;
			if (beginPress - endPress > spaceTime && !atBeginning){
				gui.appendMorse(" / ");
				gui.appendRoman(' ');
				lastLetter = "";
			}
			else if (beginPress - endPress > dashTime && !atBeginning){
				gui.appendMorse(" ");
				lastLetter = "";
			}
			atBeginning = false;
		}else if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			gui.resetTxtAreas();
			atBeginning = true;
			lastLetter = "";
		}
	}

	public void keyReleased(KeyEvent key){
		endPress = System.currentTimeMillis();
		audioManager.stop();

		if (key.getKeyCode() == KeyEvent.VK_ENTER)
			if (endPress - beginPress <= dotTime + errMargin) {
				gui.appendMorse(".");
				lastLetter += '.';
				updateRomanGUI();
			}
			else {
				gui.appendMorse("-");
				lastLetter += '-';
				updateRomanGUI();
			}
		keyBeingHeld = false;
		System.out.println(lastLetter);

	}

	private void updateRomanGUI(){
		if (lastLetter.length() == 1)
			gui.appendRoman(translator.toRoman(lastLetter));
		else
			gui.updateRoman(translator.toRoman(lastLetter));
	}

	public void keyTyped(KeyEvent key) {
	}
}
