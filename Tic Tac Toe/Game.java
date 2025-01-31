import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    int n;
    static int hod[];
    static String ch = "o";
    static String pc = "x";

    public Game(int n) {
        this.n = n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame gam = new JFrame("tic-tac-toe");
        gam.setSize(450, 400);
        gam.setResizable(false);
        gam.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gam.setLocationRelativeTo(null);
        gam.setLayout(null);

        JPanel window = new JPanel();
        window.setLayout(new BorderLayout());
        window.setBackground(new Color(215, 250, 255));
        gam.setContentPane(window);


        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(n, n));
        window.add(pan, "Center");

        JLabel naz = new JLabel("Ваш ход");
        naz.setBounds(100, 100, 100, 100);
        naz.setFont(new Font("Akina", Font.CENTER_BASELINE, 30));
        window.add(naz, "North");


        int rand = (int) (Math.random() * 2);
        int random = (int) (Math.random() * n * n);
        int hod[] = new int[n * n];
        if (rand == 0) {
            ch = "x";
            pc = "o";
        }

        JButton[] btn = new JButton[n * n];
        for (int i = 0; i < n * n; i++) {
            btn[i] = new JButton();
            btn[i].setBackground(new Color(161, 238, 240));
        /*ВАЖНО-Убирает фокус с кнопки*/
            btn[i].setFocusPainted(false);
            btn[i].addActionListener(new Treatment(i, hod, btn, n,ch,pc));
            pan.add(btn[i]);
        }

        if (rand == 0) {
            hod[random] = 1;
            btn[random].setText(pc);
            naz.setText("Пк сходил, ваш ход");
        }

        gam.setVisible(true);
    }
}
