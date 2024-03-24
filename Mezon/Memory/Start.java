import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    public static void create() {
        JFrame window = new JFrame("Memory");
        window.setSize(700,400);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);

        JLabel naz = new JLabel("Memory");
        naz.setBounds(285 ,30,200,100);
        naz.setFont(new Font("Akina",Font.CENTER_BASELINE,30));
        window.add(naz);

        JLabel nazd = new JLabel("Задача: найти одинаковые картинки");
        nazd.setBounds(167 ,85,400,100);
        nazd.setFont(new Font("Akina",Font.CENTER_BASELINE,20));
        window.add(nazd);

        JButton kstart = new JButton("Начать игру");
        kstart.setBounds(242,210,190,70);
        kstart.setBackground(new Color(244, 101, 255));
        kstart.setFont(new Font("Anonymous Pro",Font.LAYOUT_NO_START_CONTEXT,25));
        /*ВАЖНО-Убирает границы*/
        kstart.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        kstart.setFocusPainted(false);
        window.add(kstart);




        ImageIcon icon = new ImageIcon("g0.png");
        window.setIconImage(icon.getImage());


        kstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                Game kstart  = new Game();
                kstart.newGame();
            }
        });


        window.setVisible(true);
    }
}
