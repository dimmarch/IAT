package ru.dmarchuk.IAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class StartWindow extends JFrame {

    private final Main m;
    private final Color bg = Color.BLACK;
    private final Color tc = Color.WHITE;
    private JButton button;
    private JLabel lLastName;
    private JLabel lFirstname;
    private JLabel lPatronym;
    private JLabel lSex;
    private JLabel lAge;
    private JLabel lTestType;
    private JTextField tfLastName;
    private JTextField tfFirstname;
    private JTextField tfPatronym;
    private JTextField tfAge;
    private JRadioButton rbMan;
    private JRadioButton rbWoman;
    private ButtonGroup bgSex;
    private JComboBox cbTestType;
    private String outFolder;


    public StartWindow(Main mn) {

        super("IAT");
        this.m = mn;
        this.setBounds(200, 200, 400, 220);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        container.setLayout(new GridLayout(8, 2, 2, 2));
        container.setBackground(bg);

        initFields();
        initLabels();
        initTestChooser();

        addComponents(container);

        this.setContentPane(container);
        this.pack();
    }

    private void initTestChooser() {
        cbTestType = new JComboBox(m.getTestNames());

    }

    private void initLabels() {
        lLastName = new JLabel("  Фамилия:");
        lFirstname = new JLabel("  Имя:");
        lPatronym = new JLabel("  Отчество:");
        lSex = new JLabel("  Пол:");
        lAge = new JLabel("  Возраст:");
        lTestType = new JLabel("  Выберите тест:");

        ArrayList<JComponent> labels = new ArrayList<JComponent>();
        labels.add(lLastName);
        labels.add(lFirstname);
        labels.add(lPatronym);
        labels.add(lSex);
        labels.add(lAge);
        labels.add(lTestType);

        for (JComponent j : labels) {
            j.setBackground(bg);
            j.setForeground(tc);
        }
    }

    private void initFields() {
        tfLastName = new JTextField("", 15);
        tfFirstname = new JTextField("", 15);
        tfPatronym = new JTextField("", 15);
        tfAge = new JTextField("", 3);

        rbMan = new JRadioButton("Мужской");
        rbWoman = new JRadioButton("Женский");
        bgSex = new ButtonGroup();
        bgSex.add(rbMan);
        bgSex.add(rbWoman);
        rbMan.setSelected(true);

        rbMan.setForeground(tc);
        rbMan.setBackground(bg);
        rbWoman.setForeground(tc);
        rbWoman.setBackground(bg);

        button = new JButton("Далее");
        button.setBackground(bg);
        button.setForeground(Color.GRAY);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                outFolder = "output" + File.separator + tfLastName.getText() + tfFirstname.getText() + System.currentTimeMillis() + File.separator;
                String path = outFolder + "personalData" + ".csv";
                File f = new File(path);
                f.getParentFile().mkdirs();

                try {
                    Writer fw = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(f), "Cp1251"));
                    fw.write("sep=,\n");
                    fw.write(getPersonData() + "\n");
                    fw.flush();
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                m.start();
            }
        });

    }

    private void addComponents(Container c) {
        c.add(lLastName);
        c.add(tfLastName);
        c.add(lFirstname);
        c.add(tfFirstname);
        c.add(lPatronym);
        c.add(tfPatronym);
        c.add(lSex);
        c.add(new JLabel());
        c.add(rbMan);
        c.add(rbWoman);
        c.add(lAge);
        c.add(tfAge);
        c.add(lTestType);
        c.add(cbTestType);
        c.add(new JLabel());
        c.add(button);
    }

    public int getTestNumber() {
        return cbTestType.getSelectedIndex();
    }

    public String getName() {
        return lFirstname.getText();
    }

    public void run() {
        this.setVisible(true);
    }

    public String getPersonData() {
        String s;
        s = tfLastName.getText() + ", " + tfFirstname.getText() + ", " + tfPatronym.getText() + ", Возраст:" +
                tfAge.getText() + ", Пол: ";
        if (rbWoman.isSelected())
            s += "Ж, ";
        else
            s += "М, ";
        s += " Тест: " + (getTestNumber() + 1) + "; ";
        return s;
    }

    public String getOutFolder() {
        return outFolder;
    }
}
