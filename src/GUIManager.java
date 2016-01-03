import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GUIManager {

    String title = "Morse Code";
    String defaultMorse = "Morse: ";
    String defaultRoman = "Roman: ";

    JFrame frame = new JFrame(title);
    JTextArea morseTxt = new JTextArea(defaultMorse);
    JTextArea romanTxt = new JTextArea(defaultRoman);

    JPanel panel = new JPanel();

    public GUIManager(KeyListener inputter){
        frame.setSize(300, 300);

        frame.addKeyListener(inputter);
        morseTxt.addKeyListener(inputter);

        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setSize(300, 300);
        frame.add(panel);

        morseTxt.setLineWrap(true);
        romanTxt.setLineWrap(true);
        morseTxt.setEditable(false);
        romanTxt.setEditable(false);
        morseTxt.setBounds(0, 0, 300, 150);
        romanTxt.setBounds(0, 150, 300, 150);
        morseTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        romanTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        morseTxt.setText("Morse: ");
        romanTxt.setText("Roman: ");
        panel.add(morseTxt);
        panel.add(romanTxt);

        frame.setVisible(true);
    }

    public void appendMorse(String toAppend){
        morseTxt.append(toAppend);
    }

    public void resetTxtAreas(){
        morseTxt.setText(defaultMorse);
        romanTxt.setText(defaultRoman);
    }
}
