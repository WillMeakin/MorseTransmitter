import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inputter implements KeyListener{

	int sendKey = KeyEvent.VK_ENTER;
	int clearKey = KeyEvent.VK_BACK_SPACE;

	//Timing - TODO: Farnsworth speed for playback. Also playback...
	int wpm = 13;	//TODO: Make this user settable.
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

		if (!keyBeingHeld) {
			if (key.getKeyCode() == sendKey) {
				audioManager.play();
				beginPress = System.currentTimeMillis();
				if (beginPress - endPress > spaceTime && !atBeginning) {
					gui.appendMorse(" / ");
					gui.appendRoman(' ');
					lastLetter = "";
				} else if (beginPress - endPress > dashTime && !atBeginning) {
					gui.appendMorse(" ");
					lastLetter = "";
				}
				atBeginning = false;
			} else if (key.getKeyCode() == clearKey) {
				gui.resetTxtAreas();
				atBeginning = true;
				lastLetter = "";
			}
			keyBeingHeld = true;
		}
	}

	public void keyReleased(KeyEvent key){
		endPress = System.currentTimeMillis();
		audioManager.stop();

		if (key.getKeyCode() == sendKey)
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
