import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GUIManager {

    private final String title = "Morse Code";
    private final  String defaultMorse = "Morse: ";
    private final String defaultRoman = "Roman: ";

    private final JFrame frame = new JFrame(title);
    private final JTextArea morseTxtArea = new JTextArea(defaultMorse);
    private final JTextArea romanTxtArea = new JTextArea(defaultRoman);

    private final JPanel panel = new JPanel();

    public GUIManager(KeyListener inputter){
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        frame.addKeyListener(inputter);
        morseTxtArea.addKeyListener(inputter);

        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);  //TODO: a real damn layout
        panel.setSize(300, 300);
        frame.add(panel);

        morseTxtArea.setLineWrap(true);
        romanTxtArea.setLineWrap(true);
        morseTxtArea.setEditable(false);
        romanTxtArea.setEditable(false);
        romanTxtArea.setFocusable(false);
        morseTxtArea.setBounds(0, 0, 300, 150);
        romanTxtArea.setBounds(0, 150, 300, 150);
        morseTxtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        romanTxtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        morseTxtArea.setText("Morse: ");
        romanTxtArea.setText("Roman: ");
        panel.add(morseTxtArea);
        panel.add(romanTxtArea);

        frame.setVisible(true);
    }

    public void appendMorse(String morseString){
        morseTxtArea.append(morseString);
    }

    public void appendRoman(char romanChar){
        romanTxtArea.append(String.valueOf(romanChar));
    }

    public void updateRoman(char romanChar){
        romanTxtArea.replaceRange(String.valueOf(romanChar), romanTxtArea.getText().length()-1, romanTxtArea.getText().length());
    }

    public void resetTxtAreas(){
        morseTxtArea.setText(defaultMorse);
        romanTxtArea.setText(defaultRoman);
    }
}
