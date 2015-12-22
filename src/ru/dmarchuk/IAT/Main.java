package ru.dmarchuk.IAT;


import java.awt.*;
import java.util.List;

public class Main {
    private final Color bg = Color.BLACK;
    private final Color tc = Color.WHITE;
    private StartWindow sw;
    private MainWindow mw;
    private TestLoader tl;

    public Main() {
        tl = new TestLoader(this, bg, tc);
        sw = new StartWindow(this);
        mw = new MainWindow(this);

    }

    public static void main(String[] args) {
        Main m = new Main();

        m.sw.run();


    }

    public String[] getTestNames() {
        return tl.getTestNames();
    }

    public void start() {
        tl.loadSlides(sw.getTestNumber());
        mw.create();

        next();
    }

    public void pressKey(char key) {
        tl.presKey(key);
    }

    public void next() {
        if (tl.hasSlides()) {
            mw.setSlide(tl.getNextSlide());
//            System.out.println(sw.getName());
        } else {
            mw.dispose();
        }
    }

    public String getPersonData() {
        return sw.getPersonData();
    }

    public String getOutFolder() {
        return sw.getOutFolder();
    }
}
