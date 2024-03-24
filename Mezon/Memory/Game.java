import javax.swing.*;
import java.awt.*;


public class Game {

    JFrame game;
    JPanel winCont;
    ImageIcon[] cardImg;

    public Game() {
        game = new JFrame("Memory");
        game.setSize(700, 450);
        game.setResizable(false);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setLayout(null);

        ImageIcon icon = new ImageIcon("src/g0.png");
        game.setIconImage(icon.getImage());

        winCont = new JPanel();
        //winCont.setLayout(null);
        winCont.setLayout(new BoxLayout(winCont, BoxLayout.PAGE_AXIS));
        game.setContentPane(winCont);

        cardImg = new ImageIcon[13];

        for (int i = 0; i < 13; i++) {
            cardImg[i] = new ImageIcon("src/p/f" + i + ".png");
        }

        // создаём таблицу для пикч

        int[] cardT = new int[24];

        for (int i = 0; i < 12; i++) {
            cardT[i * 2] = i;
            cardT[i * 2 + 1] = i;
        }

        for (int i = 0; i < 24; i++) {
            int d = (int) (Math.random() * 24);
            int s = cardT[d];
            cardT[d] = cardT[i];
            cardT[i] = s;

        }

        JPanel tabl = new JPanel(new GridLayout(4, 6));
        game.setContentPane(tabl);
        for (int i = 0; i < 24; i++) {
            Cards pik = new Cards(cardImg[cardT[i]], cardImg[12]);
            pik.setBackground(new Color(240, 240, 240));
            pik.setBorderPainted(false);
            pik.setFocusPainted(false);
            tabl.add(pik);
        }
    }

    void newGame() {
        game.setVisible(true);
    }
}
