import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.Timer;

public class Cards extends JButton implements MouseListener {
    ImageIcon face, back;
    static int k = 0;
    static int k1 = 0;
    static boolean ft = false;
    static Icon card1;
    static Icon card2;
    static Cards thisd;
    static Cards thisd1;
    static int sch;


    public Cards(ImageIcon face, ImageIcon back) {
        this.face = face;
        this.back = back;
        this.setIcon(back);
        this.addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (this.getIcon().equals(back)) {
            this.setIcon(this.face);
            k++;
        } else {
            this.setIcon(this.back);
        }
        if (!ft) {
            card1 = this.getIcon();
            ft = true;
            thisd = this;
        } else {
            card2 = this.getIcon();
            ft = false;
            thisd1 = this;
        }
        if (k == 2) {
            k1++;
            if (card1 == card2) {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        thisd.setIcon(null);
                        thisd1.setIcon(null);
                    }
                };
                Timer timer = new Timer("Timer");
                timer.schedule(task, 650);
                sch++;
            } else {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        thisd.setIcon(back);
                        thisd1.setIcon(back);
                    }
                };
                Timer timer = new Timer("Timer");
                timer.schedule(task, 650);
            }
            k = 0;
        }
        if (sch == 1) {
            String name = JOptionPane.showInputDialog(null, new String[]{"Количество ходов " + k1, "Ваше имя"}, "Завершение игры", JOptionPane.WARNING_MESSAGE);

            try {
                FileWriter raiting = new FileWriter("raiting.txt", true);
                sort(name, k1);
                raiting.write("\n");
                raiting.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void sort(String names, int kol) {
        BufferedReader rait = null;
        try {
            rait = new BufferedReader(new FileReader("raiting.txt"));
            int k = 0;
            String[] mass = new String[10+1];
            int pos = 0, z = 0;
            while (k < 10) {
                mass[k] = rait.readLine();
                if (Integer.valueOf(mass[k].split(" ")[1]) > kol && z != 2) {
                    pos=k;
                    mass[k+1] = mass[k];
                    mass[k]=names+" "+kol;
                    k++;
                    z = 2;
                }
                k++;
            }
            System.out.println(pos);
            rait.close();
            for (int i = 10; i >= pos; i--) {
                mass[i] = mass[i - 1];
            }
            mass[pos] = names + " " + kol;
            FileWriter raiting = new FileWriter("raiting.txt");
            for (int i = 0; i < 10; i++) {

                raiting.write("\n"+mass[i]);

            }
            raiting.close();

            System.out.println(Arrays.asList(mass));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}