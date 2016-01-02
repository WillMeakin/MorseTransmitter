//TODO: Separate into classes: Inputter (KeyListener), GUIManager, SoundManager(Tone), Translator.
//TODO: Rename to MorseTrainer, move to GitHub.

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Inputter implements KeyListener{

	JFrame frame = new JFrame("Morse Code");
	JTextArea morseLbl = new JTextArea();
	JTextArea transLbl = new JTextArea();

	JPanel panel = new JPanel();

	int sendKey = KeyEvent.VK_ENTER;

	int wpm = 10;
	int dotTime = 1200/wpm;	//Milliseconds
	int dashTime = 3*dotTime;
	int spaceTime = 7*dotTime;
	int errMargin = dotTime/2;

	long beginPress = -1;
	long endPress = -1;

	boolean atBeginning = true;

	File toneFile = new File("550.wav");
	Clip tone;

	public Inputter(){
		frame.setSize(300, 300);

		frame.addKeyListener(this);
		morseLbl.addKeyListener(this);
		transLbl.addKeyListener(this);

		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setSize(300, 300);
		frame.add(panel);

		morseLbl.setLineWrap(true);
		transLbl.setLineWrap(true);
		morseLbl.setEditable(false);
		transLbl.setEditable(false);
		morseLbl.setBounds(0, 0, 300, 150);
		transLbl.setBounds(0, 150, 300, 150);
		morseLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		transLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		morseLbl.setText("Morse: ");
		transLbl.setText("Roman: ");
		panel.add(morseLbl);
		panel.add(transLbl);

		frame.setVisible(true);

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

	public void keyPressed(KeyEvent key){

		if (key.getKeyCode() == sendKey && beginPress == -1){
			tone.start();
			tone.loop(Clip.LOOP_CONTINUOUSLY);
			beginPress = System.currentTimeMillis();
			if (beginPress - endPress > spaceTime && !atBeginning){
				morseLbl.append(" / ");
			}
			else if (beginPress - endPress > dashTime){
				morseLbl.append(" ");
				//TODO: Translate
			}
			atBeginning = false;
		}else if (key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			morseLbl.setText("Morse: ");
			transLbl.setText("Roman: ");
			atBeginning = true;
		}
	}

	public void keyReleased(KeyEvent key){
		endPress = System.currentTimeMillis();
		tone.stop();

		tone.setFramePosition(0);

		if (key.getKeyCode() == KeyEvent.VK_ENTER)
			if (endPress - beginPress <= dotTime + errMargin)
				morseLbl.append(".");
			else
				morseLbl.append("-");

		beginPress = -1;
	}

	public void keyTyped(KeyEvent key){
	}

	public static void main(String[] args){

		System.out.println("Use Enter/Return for input:");

		new Inputter();


	}
}
