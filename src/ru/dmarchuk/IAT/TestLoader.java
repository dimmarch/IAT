package ru.dmarchuk.IAT;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class TestLoader {
    private Queue<Slide> slides;
    private Color bg;
    private Color tc;
    private Iterator<Slide> currentSlideIt;
    private Slide curSlide;
    private Main m;
    private int testNumber;
    private String testsFile = "resources" + File.separator + "tests.txt";
    private String[] folderNames;
    private String folderName;

    public TestLoader(Main m, Color bg, Color tc) {
        this.m = m;
        this.bg = bg;
        this.tc = tc;

        slides = new LinkedList<Slide>();
    }

    public void loadSlides(int testN) {
        folderName = "resources" + File.separator + folderNames[testN] + File.separator;
        testNumber = testN;
        initSlides();

        currentSlideIt = slides.iterator();
    }

    private void initSlides() {

        String[] lines = {"<html>  Вы выполните IAT, в котором Вы сортируете слова и картины по категориям как можно быстрее.<br>" +
                " Вы должны быть готовы закончить задачи меньше чем 10 минут. Когда Вы заканчиваете, Вы получите <br>" +
                " ваши результаты, так же как дополнительную информацию о тесте и сравнение результатов.</html>", "" +
                "Нажмите \"Пробел\" для продолжения"};
        Slide s = new TextSlide(this, bg, tc, lines);
        slides.add(s);

        lines = new String[4];
        lines[0] = "<html>  В следующем задании Вам дадут слова или изображения, которые надо будет разделить на группы.<br> " +
                "Разделить нужно как можно быстрее, делая как можно меньше ошибок. Если Вы будете действовать <br>" +
                "слишком медленно или делать слишком много ошибок, результаты будет невозможно интерпретировать.<br>" +
                "Эта часть исследования займет около 5 минут. Вот список категорий и предметов, относящихся к ним.</html>";
        lines[1] = "<html><i><b>Хорошо:</b></i> Радость, Любовь, Мир, Замечательный, Удовольствие, Великолепно, Смех, Счастливый<br>" +
                "<i><b>Плохо:</b></i> Несчастье, Ужас, Кошмар, Противно, Зло, Жуть, Отказ, Вред <br> ";
        if (testNumber == 0)
            lines[1] += "<br><i><b>Сепарация:</b></i> Изображения, связанные с отделением от матери <br>" +
                    "<i><b>Симбиоз:</b></i> Изображения, связанные с матерью </html> ";
        else
            lines[1] += "<br><i><b>Есть лишний вес:</b></i> Изображения людей с лишним весом <br><br>" +
                    "<i><b>Нет лишнего веса:</b></i> Изображения людей с отсутствием лишнего веса </html> ";

        lines[2] = "<html> <font size=5>Запомнить</font> <br>" +
                "<UL> <LI> Держите пальцы на клавишах E и I, чтобы быстро реагировать.\n" +
                "<LI> Две надписи сверху указывают на слова или изображения, соответствующие каждой клавише.\n" +
                "<LI> Каждое слово или изображение соответствует какой-то категории. Обычно ее легко определить.\n" +
                "<LI> Этот тест не дает результатов, если проходить его медленно -- отвечайте как можно быстрее.\n" +
                "<LI> Из-за скорости будет несколько ошибок. Это нормально.\n" +
                "<LI> Старайтесь не отвлекаться. </UL> </html>";
//        lines[3] = "<html> </html>";
        lines[3] = "Нажмите \"Пробел\" для продолжения";
        s = new TextSlide(this, bg, tc, lines);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> Поместите ваши средние или указательные пальцы на клавиши <font color=green>E</font> и <font color=green>I</font> вашей клавиатуры.<br>" +
                "Слова или изображения, представляющие категории, появятся один за другим в середине <br> " +
                "экрана. Когда объект принадлежит категории слева, нажмите клавишу <font color=green>E</font>; Когда объект  <br>" +
                "принадлежит категории справа – нажмите клавишу <font color=green>I</font>. Объекты принадлежат только одной  <br>" +
                "категории. Если Вы сделаете ошибку, появится <font color=red>X</font>. Исправьте ошибку, нажав другую клавишу. <br>" +
                "<br><br>" +
                "Это задание на сортировку с ограниченным временем. ВЫПОЛНЯЙТЕ ТАК БЫСТРО, КАК ТОЛЬКО<br>" +
                "МОЖЕТЕ. Несколько ошибок в процессе выполнения допустимы. Если Вы будете выполнять <br>" +
                "задания слишком медленно или сделаете слишком много ошибок, это приведет к невозможности<br>" +
                "интерпретации результата. Выполнение задания займет около 5 минут. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 1, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Посмотрите – категории вверху изменились.</font> Объекты для сортировки изменились.<br>" +
                " Правила остаются теми же самыми. Когда объект принадлежит к левой категории, нажимайте <br> " +
                "клавишу <font color=green>E</font>; когда объект принадлежит к правой категории, нажимайте клавишу <font color=green>I</font>. Объекты принадлежат <br>" +
                "только к одной категории. <font color=red>X</font> появляется, если Вы сделали ошибку – исправьте ошибку, нажав на <br>" +
                "другую кнопку. ДЕЙСТВУЙТЕ ТАК БЫСТРО, КАК ТОЛЬКО МОЖЕТЕ.  <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 2, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Сверху Вы можете увидеть, что четыре категории, ранее отдельные, <br> теперь объединены. </font> <br> <br>" +
                "Помните, каждый объект принадлежит только к одной группе. Например, если категории <i>цветок</i> и <br>" +
                "<i>добро</i> появляются в разных сторонах наверху – то картинки или слова, означающие  <i>цветок</i> должны <br>" +
                "быть отнесены в категорию <i>цветок</i>, а не в категорию <i>добро</i>. <font color = green>Зеленые </font> и Белые ярлыки и объекты могут <br>" +
                "помочь определить соответствующую категорию. Используйте клавиши  <font color=green>E</font> и <font color=green>I</font>, чтобы распределить объекты <br>" +
                "по четырем категориям слева и справа, и исправляйте ошибки при помощи нажатия на другую клавишу. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 3, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Выполните сортировку по тем же четырем категориям снова.  </font> <br> <br>" +
                "Помните: следует выполнять задание так быстро, как вы можете.Несколько ошибок в процессе<br>" +
                " выполнения допустимы. <br> <br>" +
                "<font color = green>Зеленые </font> и Белые ярлыки и объекты могут помочь определить соответствующую категорию.<br>" +
                "Используйте клавиши  <font color=green>E</font> и <font color=green>I</font>, чтобы распределить объекты по четырем категориям слева и справа, <br>" +
                "и исправляйте ошибки при помощи нажатия на другую клавишу. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 4, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Заметьте: выше есть только 2 категории, и они изменили положения. </font> <br> <br>" +
                "Категория, бывшая ранее правой, теперь слева и наоборот: та, что была слева, теперь справа. <br>" +
                "Попрактикуйтесь в новом расположении. Используйте клавиши <font color=green>E</font> и <font color=green>I</font>, чтобы распределить объекты <br>" +
                "по категориям <font color=green>слева</font> и <font color=green>справа</font>, и исправляйте ошибки при помощи нажатия на другую клавишу. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 5, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Обратите внимание, сверху  сейчас возникли 4 категории в новой <br> конфигурации.</font> <br> <br>" +
                "Помните, каждый объект принадлежит только к одной группе.<br> <br>" +
                "<font color = green>Зеленые </font> и Белые ярлыки и объекты могут помочь определить соответствующую категорию.<br>" +
                "Используйте клавиши  <font color=green>E</font> и <font color=green>I</font>, чтобы распределить объекты по четырем категориям слева и справа, <br>" +
                "и исправляйте ошибки при помощи нажатия на другую клавишу. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 6, lines[0]);
        slides.add(s);

        lines = new String[1];
        lines[0] = "<html> <font Size = +1>Выполните сортировку по тем же четырем категориям снова.  </font> <br> <br>" +
                "Помните: следует выполнять задание так быстро, как вы можете.Несколько ошибок в процессе<br>" +
                " выполнения допустимы. <br> <br>" +
                "<font color = green>Зеленые </font> и Белые ярлыки и объекты могут помочь определить соответствующую категорию.<br>" +
                "Используйте клавиши  <font color=green>E</font> и <font color=green>I</font>, чтобы распределить объекты по четырем категориям слева и справа, <br>" +
                "и исправляйте ошибки при помощи нажатия на другую клавишу. <br>" +
                "<br><br>" +
                "Нажмите \"Пробел\" чтобы начать тест </html>";

        s = new TestSlide(this, bg, tc, folderName, 7, lines[0]);
        slides.add(s);


        lines = new String[3];
        lines[0] = "<html> <font size = +5> Тест закончен. </font> </html>";
        lines[1] = "<html><font size = +1>Спасибо за прохождение.</font></html>";
        lines[2] = "<html>Нажмите \"Пробел\" для выхода.</html>";


        s = new TextSlide(this, bg, tc, lines);
        slides.add(s);

    }

    public Slide getNextSlide() {
        Slide s;

        s = currentSlideIt.next();
        curSlide = s;
        return s;
    }

    public String[] getTestNames() {
        List<String> testNames = new ArrayList<String>();
        List<String> lFolderNames = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(testsFile));
            String s;
            String[] array;
            Integer counter = 0;
            while ((s = br.readLine()) != null) {
                array = s.split(",");
                testNames.add((counter + 1) + ". " + array[1]);
                lFolderNames.add(array[0]);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] result = new String[testNames.size()];
        folderNames = new String[testNames.size()];

        testNames.toArray(result);
        lFolderNames.toArray(folderNames);

        return result;
    }


    public void changeSlide() {
        m.next();
    }

    public boolean hasSlides() {
        return currentSlideIt.hasNext();
    }

    public void presKey(char key) {
        curSlide.pressKey(key);
    }

    public String getOutFolder() {
        return m.getOutFolder();
    }
}
