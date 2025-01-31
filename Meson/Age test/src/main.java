import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {

    static int i = 1;
    static int k1 = 0;
    static int k2 = 0;
    static int k3 = 0;
    static int k4 = 0;
    static String[] arr = new String[10];
    static String r;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader("vopros"));
        JFrame window = new JFrame("Тест на ваш психологический возраст");
        window.setSize(750, 500);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);

        //кнопочка СТАРТ И НАСТРЙОКИ

        JButton knopNastr = new JButton("<html> <h2><font color=black>Настройки ");
        knopNastr.setBounds(10, 10, 130, 35);
        knopNastr.setBackground(Color.pink);
        window.add(knopNastr);

        JButton knopStart = new JButton("<html> <h2><font color=black>Старт");
        knopStart.setBounds(310, 210, 120, 35);
        knopStart.setBackground(Color.pink);
        window.add(knopStart);

        //окно настроек

        JFrame Nastr = new JFrame("Настройки");
        Nastr.setSize(750, 500);
        Nastr.setResizable(false);
        Nastr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Nastr.setLocationRelativeTo(null);
        Nastr.setLayout(null);

        //кнопка вернуться

        JButton back = new JButton("<html> <h2><font color=black>Вернуться");
        back.setBounds(10, 10, 130, 35);
        back.setBackground(Color.pink);
        Nastr.add(back);

        JLabel naz = new JLabel("Настройки");
        naz.setBounds(283, 40, 600, 35);
        naz.setFont(new Font("Arial", Font.BOLD, 25));
        naz.setForeground(Color.pink);
        Nastr.add(naz);

        //размер окна

        JButton razm = new JButton("<html> <h2><font color=black>Размер окна");
        razm.setBounds(10, 200, 170, 35);
        razm.setBackground(Color.pink);
        Nastr.add(razm);

        ButtonGroup grrazm = new ButtonGroup();

        JRadioButton razmer1 = new JRadioButton("750:500");
        razmer1.setBounds(440, 150, 600, 75);
        razmer1.setFont(new Font("Arial", Font.BOLD, 25));
        razmer1.setForeground(Color.pink);
        razmer1.setSelected(true);
        Nastr.add(razmer1);
        grrazm.add(razmer1);
        razmer1.setActionCommand("1");
        razmer1.setVisible(false);

        JRadioButton razmer2 = new JRadioButton("750:550");
        razmer2.setBounds(440, 200, 600, 75);
        razmer2.setFont(new Font("Arial", Font.BOLD, 25));
        razmer2.setForeground(Color.pink);
        Nastr.add(razmer2);
        grrazm.add(razmer2);
        razmer2.setActionCommand("2");
        razmer2.setVisible(false);

        JRadioButton razmer3 = new JRadioButton("800:500");
        razmer3.setBounds(440, 250, 600, 75);
        razmer3.setFont(new Font("Arial", Font.BOLD, 25));
        razmer3.setForeground(Color.pink);
        Nastr.add(razmer3);
        grrazm.add(razmer3);
        razmer3.setActionCommand("3");
        razmer3.setVisible(false);

        JButton sohrr = new JButton("<html> <h2><font color=black>Сохранить");
        sohrr.setBounds(440, 350, 170, 35);
        sohrr.setBackground(Color.pink);
        Nastr.add(sohrr);
        sohrr.setVisible(false);

        //цвет шрифта

        JButton color = new JButton("<html> <h2><font color=black>Цвета");
        color.setBounds(10, 290, 170, 35);
        color.setBackground(Color.pink);
        Nastr.add(color);

        ButtonGroup grcolor = new ButtonGroup();

        JRadioButton color1 = new JRadioButton("Мягкие оттенки");
        color1.setBounds(440, 175, 600, 75);
        color1.setFont(new Font("Arial", Font.BOLD, 25));
        color1.setForeground(Color.pink);
        color1.setSelected(true);
        Nastr.add(color1);
        grcolor.add(color1);
        color1.setActionCommand("1");
        color1.setVisible(false);

        JRadioButton color2 = new JRadioButton("Чёрно-белый");
        color2.setBounds(440, 225, 600, 75);
        color2.setFont(new Font("Arial", Font.BOLD, 25));
        color2.setForeground(Color.pink);
        Nastr.add(color2);
        grcolor.add(color2);
        color2.setActionCommand("2");
        color2.setVisible(false);

        JButton sohrc = new JButton("<html> <h2><font color=black>Сохранить");
        sohrc.setBounds(440, 350, 170, 35);
        sohrc.setBackground(Color.pink);
        Nastr.add(sohrc);
        sohrc.setVisible(false);



        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color1.setVisible(true);
                color2.setVisible(true);
                sohrc.setVisible(true);
                razmer1.setVisible(false);
                razmer2.setVisible(false);
                razmer3.setVisible(false);
                sohrr.setVisible(false);
            }
        });

        razm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                razmer1.setVisible(true);
                razmer2.setVisible(true);
                razmer3.setVisible(true);
                sohrr.setVisible(true);
                color1.setVisible(false);
                color2.setVisible(false);
                sohrc.setVisible(false);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                razmer1.setVisible(false);
                razmer2.setVisible(false);
                razmer3.setVisible(false);
                sohrr.setVisible(false);
                color1.setVisible(false);
                color2.setVisible(false);
                sohrc.setVisible(false);
            }
        });

        //название ТЕСТА

        JLabel Titl = new JLabel("Тест на ваш психологический возраст");
        Titl.setBounds(145, 40, 600, 35);
        Titl.setFont(new Font("Arial", Font.BOLD, 25));
        Titl.setForeground(Color.pink);
        window.add(Titl);

        // окна вопросов

        JFrame vopros = new JFrame("Тест на ваш психологический возраст");
        vopros.setSize(750, 500);
        vopros.setResizable(false);
        vopros.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        vopros.setLocationRelativeTo(null);
        vopros.setLayout(null);

        // Вопрос и его номер

        JLabel voprosTitl = new JLabel("Вопрос № 1");
        voprosTitl.setBounds(285, 40, 600, 35);
        voprosTitl.setFont(new Font("Arial", Font.BOLD, 25));
        voprosTitl.setForeground(Color.pink);
        vopros.add(voprosTitl);

        //Сами вопросы

        JLabel Quest = new JLabel();
        Quest.setBounds(75, 57, 600, 100);
        Quest.setFont(new Font("Arial", Font.BOLD, 25));
        Quest.setForeground(Color.pink);
        Quest.setText(file.readLine());
        vopros.add(Quest);

        //картинки

        JLabel kartinki = new JLabel();
        kartinki.setBounds(430, 30, 500, 500);
        //kartinki.setBackground(Color.pink);                      (задний фон картиночки)
        kartinki.setIcon(new ImageIcon(file.readLine()));
        vopros.add(kartinki);

        // создание группы для вариантов ответа

        ButtonGroup group = new ButtonGroup();

        //варианты ответов, их 4

        JRadioButton otvet1 = new JRadioButton();
        otvet1.setBounds(75, 175, 275, 75);
        otvet1.setFont(new Font("Arial", Font.BOLD, 25));
        otvet1.setForeground(Color.pink);
        otvet1.setText(file.readLine());
        otvet1.setSelected(true);
        vopros.add(otvet1);
        group.add(otvet1);
        otvet1.setActionCommand("1");

        JRadioButton otvet2 = new JRadioButton();
        otvet2.setBounds(75, 225, 275, 75);
        otvet2.setFont(new Font("Arial", Font.BOLD, 25));
        otvet2.setForeground(Color.pink);
        otvet2.setText(file.readLine());
        vopros.add(otvet2);
        group.add(otvet2);
        otvet2.setActionCommand("2");

        JRadioButton otvet3 = new JRadioButton();
        otvet3.setBounds(75, 275, 275, 75);
        otvet3.setFont(new Font("Arial", Font.BOLD, 25));
        otvet3.setForeground(Color.pink);
        otvet3.setText(file.readLine());
        vopros.add(otvet3);
        group.add(otvet3);
        otvet3.setActionCommand("3");

        JRadioButton otvet4 = new JRadioButton();
        otvet4.setBounds(75, 325, 275, 75);
        otvet4.setFont(new Font("Arial", Font.BOLD, 25));
        otvet4.setForeground(Color.pink);
        otvet4.setText(file.readLine());
        vopros.add(otvet4);
        group.add(otvet4);
        otvet4.setActionCommand("4");

        //кнопка ответ

        JButton knopOtvet = new JButton("<html> <h2><font color=black>Ответить");
        knopOtvet.setBounds(310, 400, 120, 35);
        knopOtvet.setBackground(Color.pink);
        vopros.add(knopOtvet);

        //Вывод

        JLabel Itogo = new JLabel();
        Itogo.setBounds(259, 100, 600, 35);
        Itogo.setFont(new Font("Arial", Font.BOLD, 25));
        Itogo.setForeground(Color.pink);
        vopros.add(Itogo);
        Itogo.setVisible(false);

        //смена значений вопросов, картинок и ответов

        knopOtvet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Преобразование ответов в массив

                arr[i-1] = group.getSelection().getActionCommand();
                Integer str = Integer.valueOf(arr[i-1]);
                if (str==1)
                    k1++;
                if (str==2)
                    k2++;
                if (str==3)
                    k3++;
                if (str==4)
                    k4++;

                i = i + 1;
                if (i <= 10) {
                    voprosTitl.setText("Вопрос № " + i);
                    try {

                        Quest.setText(file.readLine());
                        kartinki.setIcon(new ImageIcon(file.readLine()));
                        otvet1.setText(file.readLine());
                        otvet2.setText(file.readLine());
                        otvet3.setText(file.readLine());
                        otvet4.setText(file.readLine());
                        //otvet1.setEnabled(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    voprosTitl.setBounds(242, 40, 600, 35);
                    voprosTitl.setText("Вы завершили тест");
                    knopOtvet.setVisible(false);

                    //Выыод

                    Itogo.setVisible(true);

                        if (k1 + k2 > k4 + k3) {
                            if (k1 + k3 > k4 + k2) {
                                w = (int) (Math.random() * 30 + 10);
                                Itogo.setText("Вам в районе " + w);
                                try {
                                    kartinki.setIcon(new ImageIcon(file.readLine()));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                kartinki.setBounds(235, 60, 500, 500);
                            }
                            else{
                                w = (int) (Math.random() * 40 + 10);
                                Itogo.setText("Вам в районе " + w);
                                try {
                                    kartinki.setIcon(new ImageIcon(file.readLine()));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                kartinki.setBounds(235, 60, 500, 500);
                            }
                        }
                        else {
                            if (k4 + k2 > k1 + k3) {
                                w = (int) (Math.random() * 10 + 10);
                                Itogo.setText("Вам примерно " + w);
                                try {
                                    kartinki.setIcon(new ImageIcon(file.readLine()));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                kartinki.setBounds(235, 60, 500, 500);
                            }
                            else{
                                w = (int) (Math.random() * 20 + 10);
                                Itogo.setText("Вам примерно " + w);
                                try {
                                    kartinki.setIcon(new ImageIcon(file.readLine()));
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                kartinki.setBounds(235, 60, 500, 500);
                            }
                        }

                    Quest.setVisible(false);
                    otvet1.setVisible(false);
                    otvet2.setVisible(false);
                    otvet3.setVisible(false);
                    otvet4.setVisible(false);
                }
            }
        });

        //смена окон настроек на обротно(главная стр)

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nastr.setVisible(false);
                window.setVisible(true);
            }
        });

        //смена окон с главного в настройки

        knopNastr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                Nastr.setVisible(true);
            }
        });

        //смена окон с главной на тест

        knopStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                vopros.setVisible(true);
            }
        });

        sohrr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r = grrazm.getSelection().getActionCommand();
                Integer str = Integer.valueOf(r);
                if(str == 1) {
                    window.setSize(750, 500);
                    Nastr.setSize(750, 500);
                    vopros.setSize(750, 500);
                    Quest.setBounds(75, 57, 600, 100);
                }
                if(str == 2) {
                    window.setSize(750, 550);
                    Nastr.setSize(750, 550);
                    vopros.setSize(750, 550);
                }
                if(str == 3) {
                    window.setSize(800, 500);
                    Nastr.setSize(800, 500);
                    vopros.setSize(800, 500);
                    Quest.setBounds(75, 57, 800, 100);
                }

            }
        });

        sohrc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r = grcolor.getSelection().getActionCommand();
                Integer str = Integer.valueOf(r);
                if (str==1){
                    Itogo.setForeground(Color.pink);
                    knopOtvet.setBackground(Color.pink);
                    otvet4.setForeground(Color.pink);
                    otvet3.setForeground(Color.pink);
                    otvet2.setForeground(Color.pink);
                    otvet1.setForeground(Color.pink);
                    Quest.setForeground(Color.pink);
                    voprosTitl.setForeground(Color.pink);
                    Titl.setForeground(Color.pink);
                    sohrc.setBackground(Color.pink);
                    sohrr.setBackground(Color.pink);
                    color1.setForeground(Color.pink);
                    color2.setForeground(Color.pink);
                    razmer3.setForeground(Color.pink);
                    razmer2.setForeground(Color.pink);
                    razmer1.setForeground(Color.pink);
                    color.setBackground(Color.pink);
                    razm.setBackground(Color.pink);
                    naz.setForeground(Color.pink);
                    back.setBackground(Color.pink);
                    knopNastr.setBackground(Color.pink);
                    knopStart.setBackground(Color.pink);
                }
                if (str==2){
                    Itogo.setForeground(Color.black);
                    knopOtvet.setBackground(Color.white);
                    otvet4.setForeground(Color.black);
                    otvet3.setForeground(Color.black);
                    otvet2.setForeground(Color.black);
                    otvet1.setForeground(Color.black);
                    Quest.setForeground(Color.black);
                    voprosTitl.setForeground(Color.black);
                    Titl.setForeground(Color.black);
                    sohrc.setBackground(Color.white);
                    sohrr.setBackground(Color.white);
                    color1.setForeground(Color.black);
                    color2.setForeground(Color.black);
                    razmer3.setForeground(Color.black);
                    razmer2.setForeground(Color.black);
                    razmer1.setForeground(Color.black);
                    color.setBackground(Color.white);
                    razm.setBackground(Color.white);
                    naz.setForeground(Color.black);
                    back.setBackground(Color.white);
                    knopNastr.setBackground(Color.white);
                    knopStart.setBackground(Color.white);
                }
            }
        });
        ImageIcon icon = new ImageIcon("img/g0.png");
        window.setIconImage(icon.getImage());
        vopros.setIconImage(icon.getImage());
        Nastr.setIconImage(icon.getImage());
        window.setVisible(true);
    }
}
