package ru.dmarchuk.IAT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class TestSlide extends Slide {
    private String leftText;
    private String rightText;
    private String folder;
    private String[] images;
    private int[] rightAnswer;
    private int nOfCat;
    private JLabel cross;
    private JLabel center;
    private int current;
    private Queue<Long>[] times;
    private long startTime;
    private boolean firstSpace;
    private Color bg;
    private int nConfig;


    public TestSlide(TestLoader testLoader, Color bg, Color tc, String folder, int configN, String greeting) {
        super(testLoader, bg, tc);
        this.bg = bg;
        nConfig = configN;
        this.folder = folder;
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        readConfig(configN);
        current = -1;
        firstSpace = true;
        setLayout(new BorderLayout(5, 5));


        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 3, 5, 5));
        top.setBackground(bg);


        JLabel l = new JLabel(leftText);
        l.setForeground(tc);
        if (configN == 2)
            l.setForeground(Color.GREEN);

        top.add(l);

        cross = new JLabel("<html><b><font size = 50>X</font> </b> </html>");
        cross.setForeground(bg);
        cross.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(cross);

        l = new JLabel(rightText);
        l.setForeground(tc);
        if (configN == 2)
            l.setForeground(Color.GREEN);
        l.setHorizontalAlignment(SwingConstants.RIGHT);
        top.add(l);

        add(top, BorderLayout.PAGE_START);

        center = new JLabel(greeting);
        center.setForeground(tc);
        center.setHorizontalAlignment(SwingConstants.CENTER);

        add(center, BorderLayout.CENTER);


    }

    private void readConfig(int n) {
        String s = folder + "config" + n + ".txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(s));
            String l;
            String[] array;
            l = br.readLine();
            l = l.replace('\ufeff', ' ').trim();

            nOfCat = Integer.valueOf(l);
            if (nOfCat == 1) {
                leftText = "<html> <font size = 6>" + br.readLine() + "</font></html>";
                rightText = "<html> <font size = 6>" + br.readLine() + "</font></html>";
            } else if (nOfCat == 2) {
                l = br.readLine();
                array = l.split(";");
                leftText = "<html> <font size = 6 >" + array[0] + " <br>" +
                        " или </font><br><font size = 6 color = green>" + array[1] + "</font> </html>";
                l = br.readLine();
                array = l.split(";");
                rightText = "<html> <font size = 6 >" + array[0] + " <br>" +
                        " или </font><br><font size = 6 color = green>" + array[1] + "</font> </html>";
            }

            Queue<String> qs = new LinkedList<String>();
            Queue<Integer> qi = new LinkedList<Integer>();
            l = br.readLine();
            while (l != null && !l.isEmpty()) {
                array = l.split(" ");
                if (array[1].charAt(0) == 'l')
                    qi.add(0);
                else
                    qi.add(1);
                qs.add(array[0]);
                l = br.readLine();
            }

            rightAnswer = new int[qi.size()];
            int j = 0;
            for (Integer next : qi) {
                rightAnswer[j] = next;
//                System.out.println(" " + next);
                j++;
            }

            images = new String[qs.size()];
            j = 0;
            for (String next : qs) {
                images[j] = next;
//                System.out.println(next + " ");
                j++;
            }


            times = new Queue[qs.size()];

            for (int i = 0; i < qs.size(); i++) {
                times[i] = new LinkedList<Long>();
            }

            startTime = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void pressKey(char key) {

        if (firstSpace) {
            if (key == ' ') {
                loadNextStimulus();
                firstSpace = false;
            }
        } else {
            if (key == ' ') {
                if (noMoreImg()) {
                    endSlide();
                }

            } else if (key == 'e' || key == 'E' || key == 'у' || key == 'У')
                choose(0);
            else if (key == 'i' || key == 'I' || key == 'ш' || key == 'Ш')
                choose(1);
        }

    }

    private boolean noMoreImg() {
        return (current == images.length - 1);
    }

    private void choose(int i) {

        if (i == rightAnswer[current]) {
            times[current].add(System.currentTimeMillis() - startTime);
            cross.setForeground(bg);
            if (noMoreImg()) {
                endSlide();
            } else
                loadNextStimulus();
        } else {
            times[current].add(System.currentTimeMillis() - startTime);
            cross.setForeground(Color.RED);
        }


    }

    private void endSlide() {
        String outFolder = getOutFolder();

        String path = outFolder + "out" + nConfig + ".csv";
        File f = new File(path);
        try {
            // FileWriter fw = new FileWriter(f);
            Writer fw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f), "Cp1251"));

            fw.write("sep=,\n");

            for (int i = 0; i < times.length; i++) {
                fw.write((rightAnswer[i] == 0) ? "l" : "r");
                for (Long t : times[i])
                    fw.write(", " + t);// / 1000 + "." + t % 1000 );
                fw.write("\n");
            }

            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSlide();
    }

    private void loadNextStimulus() {
        current++;
        startTime = System.currentTimeMillis();
        if (isImage()) {
            center.setText(null);
            String img = folder + "images" + File.separator + images[current];
            ImageIcon image = loadAndResizeImage(img);
            center.setIcon(image);
        } else {
            center.setIcon(null);
            center.setText("<html> <font size = 10>" + images[current] + "</font> </html>");
        }
    }

    private ImageIcon loadAndResizeImage(String img) {
        File file = new File(img);
        try {
            BufferedImage image = ImageIO.read(file);
            int w, h;
            w = Math.min(image.getWidth(), 800);
            h = Math.min(image.getHeight(), 500);
            BufferedImage scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = scaledImg.createGraphics();
            g.drawImage(image, 0, 0, w, h, null);
            g.dispose();
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            System.out.println("\n" + img + "\n");
            e.printStackTrace();
        }
        return null;
    }

    private boolean isImage() {
        return images[current].contains(".");
    }


}
