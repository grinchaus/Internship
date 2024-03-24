import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    JPanel winCont;
    public static void main(String[] args) {
        JFrame wind = new JFrame("tic-tac-toe");
        wind.setSize(700,400);
        wind.setResizable(false);
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setLayout(null);

        JPanel window = new JPanel();
        window.setLayout(null);
        window.setBackground((new Color(255, 230, 254)));
        wind.setContentPane(window);


        JLabel naz = new JLabel("tic-tac-toe");
        naz.setBounds(260 ,20,200,100);
        naz.setFont(new Font("Akina",Font.CENTER_BASELINE,30));
        window.add(naz);

        JLabel vib = new JLabel("Выберете режим игры");
        vib.setBounds(165 ,60,350,100);
        vib.setFont(new Font("Akina",Font.CENTER_BASELINE,30));
        window.add(vib);

        JButton bot = new JButton("1 vs PC");
        bot.setBounds(150,160,150,50);
        bot.setBackground(new Color(255, 111, 119));
        bot.setFont(new Font("Anonymous Pro",Font.LAYOUT_NO_START_CONTEXT,20));
        /*ВАЖНО-Убирает границы*/
        bot.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        bot.setFocusPainted(false);
        window.add(bot);

        JButton friend = new JButton("1 vs 1");
        friend.setBounds(362,160,150,50);
        friend.setBackground(new Color(110, 112, 255));
        friend.setFont(new Font("Anonymous Pro",Font.LAYOUT_NO_START_CONTEXT,20));
        /*ВАЖНО-Убирает границы*/
        friend.setBorderPainted(false);
        /*ВАЖНО-Убирает фокус с кнопки*/
        friend.setFocusPainted(false);
        window.add(friend);

        friend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wind.setVisible(false);
                Friend friend  = new Friend();
                friend.newGame();
            }
        });




        wind.setVisible(true);
    }
}
