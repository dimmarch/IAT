package ru.dmarchuk.IAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainWindow extends JFrame {
    private Main m;

    public MainWindow(Main m) {

        super("Simple Example");
        this.m = m;
        this.setBounds(100, 100, 1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        Container container = this.getContentPane();
        container.setBackground(Color.BLACK);
        addKeyListener(new MyKeyListener());
        setFocusable(true);

    }

    public void setSlide(Slide s) {
        setContentPane(s);
        setVisible(true);
    }


    public void create() {


        this.setVisible(true);
        //setFocusable(true);
    }

    public void pressKey(char key) {
        m.pressKey(key);
    }


    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pressKey(e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }



}

