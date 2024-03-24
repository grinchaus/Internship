import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friend {
    JFrame wind;

    public Friend() {
        wind = new JFrame("tic-tac-toe");
        wind.setSize(700, 400);
        wind.setResizable(false);
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setLayout(null);

        JPanel window = new JPanel();
        window.setLayout(null);
        window.setBackground((new Color(255, 230, 254)));
        wind.setContentPane(window);

        JLabel naz = new JLabel("Размер поля");
        naz.setBounds(260, 20, 200, 100);
        naz.setFont(new Font("Akina", Font.CENTER_BASELINE, 30));
        window.add(naz);

        JButton game3 = new JButton("3x3");
        game3.setBounds(190, 160, 80, 50);
        game3.setBackground(new Color(255, 111, 119));
        game3.setFont(new Font("Anonymous Pro", Font.LAYOUT_NO_START_CONTEXT, 20));
        /*ВАЖНО-Убирает границы*/
        game3.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        game3.setFocusPainted(false);
        window.add(game3);

        JButton game4 = new JButton("4x4");
        game4.setBounds(310, 160, 80, 50);
        game4.setBackground(new Color(255, 111, 119));
        game4.setFont(new Font("Anonymous Pro", Font.LAYOUT_NO_START_CONTEXT, 20));
        /*ВАЖНО-Убирает границы*/
        game4.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        game4.setFocusPainted(false);
        window.add(game4);

        JButton game5 = new JButton("5x5");
        game5.setBounds(430, 160, 80, 50);
        game5.setBackground(new Color(255, 111, 119));
        game5.setFont(new Font("Anonymous Pro", Font.LAYOUT_NO_START_CONTEXT, 20));
        /*ВАЖНО-Убирает границы*/
        game5.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        game5.setFocusPainted(false);
        window.add(game5);


        game3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wind.setVisible(false);
            }
        });
        game4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wind.setVisible(false);
            }
        });
        game5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wind.setVisible(false);
            }
        });
        game3.addActionListener(new Game(3));
        game4.addActionListener(new Game(4));
        game5.addActionListener(new Game(5));
        wind.setVisible(false);
    }

    void newGame() {
        wind.setVisible(true);
    }
}
