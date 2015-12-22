package ru.dmarchuk.IAT;


import javax.swing.*;
import java.awt.*;

public class TextSlide extends Slide {
    private String[] text;


    public TextSlide(TestLoader testLoader, Color bg, Color tc, String[] lines) {
        super(testLoader, bg, tc);
        setLayout(new GridLayout(lines.length, 1, 5, 5));

        text = new String[lines.length];


        for (int i = 0; i < lines.length; i++) {
            String s = lines[i];
            text[i] = s;
            JLabel label = new JLabel(s, SwingConstants.CENTER);
            label.setBackground(bg);
            label.setForeground(tc);
            add(label);

        }


    }

    @Override
    public void pressKey(char key) {
        if (key == ' ')
            changeSlide();
    }


}
