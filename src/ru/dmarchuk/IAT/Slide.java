package ru.dmarchuk.IAT;


import javax.swing.*;
import java.awt.*;


public abstract class Slide extends JPanel {
    private TestLoader tl;

    public Slide(TestLoader testLoader, Color bg, Color tc) {
        super();
        this.tl = testLoader;
        setBackground(bg);
        setForeground(tc);


    }

    public void pressKey(char key) {
        System.out.println(key + " ");
    }


    public String getOutFolder() {
        return tl.getOutFolder();
    }

    public void changeSlide() {
        tl.changeSlide();
    }

}
