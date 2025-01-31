import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Display {
    static Font my = new Font("Anonymous Pro",Font.LAYOUT_NO_START_CONTEXT,25);
    public static void create() {
        JFrame wind = new JFrame("Калькулятор");
        wind.setSize(411, 665);
        wind.setResizable(false);
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setResizable(false);

        JPanel window = new JPanel();
        window.setLayout(null);
        window.setBackground((new Color(250, 248, 224)));
        wind.setContentPane(window);

        JLabel filed = new JLabel("0");
        filed.setFont(new Font("Akina",Font.CENTER_BASELINE,55));
        filed.setBounds(0,90,390,100);
        filed.setHorizontalAlignment(JTextField.RIGHT);
        window.add(filed);

        JLabel filedup = new JLabel("");
        filedup.setFont(new Font("Akina",Font.CENTER_BASELINE,25));
        filedup.setBounds(0,35,385,100);
        filedup.setForeground(Color.LIGHT_GRAY);
        filedup.setHorizontalAlignment(JTextField.RIGHT);
        window.add(filedup);

        JButton bpr = new JButton("%");    bpr.addActionListener( new Arifm(bpr,filed));  bpr.addActionListener( new Strocka(bpr,filedup));
        bpr.setBounds(5,235,95,62);
        bpr.setBackground(new Color(255, 233, 255));
        bpr.setFont(my);

        JButton bce = new JButton("CE");      bce.addActionListener( new Strocka(bce,filed));
        bce.setBounds(105,235,95,62);
        bce.setBackground(new Color(255, 233, 255));
        bce.setFont(my);

        JButton bc = new JButton("C");     bc.addActionListener( new Strocka(bc,filedup)); bc.addActionListener( new Strocka(bc,filed));
        bc.setBounds(205,235,95,62);
        bc.setBackground(new Color(255, 233, 255));
        bc.setFont(my);

        JButton bback = new JButton("⌫");
        bback.setBounds(305,235,95,62);
        bback.setBackground(new Color(255, 233, 255));
        bback.setFont(my);

        JButton bre = new JButton("1/x");       bre.addActionListener( new Arifm(bre,filed));  bre.addActionListener( new Strocka(bre,filedup));
        bre.setBounds(5,302,95,62);
        bre.setBackground(new Color(255, 233, 255));
        bre.setFont(my);

        JButton bkv = new JButton("x²");       bkv.addActionListener( new Arifm(bkv,filed));  bkv.addActionListener( new Strocka(bkv,filedup));
        bkv.setBounds(105,302,95,62);
        bkv.setBackground(new Color(255, 233, 255));
        bkv.setFont(my);

        JButton bk = new JButton("√");     bk.addActionListener( new Arifm(bk,filed));  bk.addActionListener( new Strocka(bk,filedup));
        bk.setBounds(205,302,95,62);
        bk.setBackground(new Color(255, 233, 255));
        bk.setFont(my);

        JButton bde = new JButton("÷");    bde.addActionListener( new Arifm(bde,filed));  bde.addActionListener( new Strocka(bde,filedup));
        bde.setBounds(305,302,95,62);
        bde.setBackground(new Color(255, 233, 255));
        bde.setFont(my);

        JButton b1 = new JButton("1");    b1.addActionListener( new Strocka(b1,filed));  b1.addActionListener( new Strocka(b1,filedup));
        b1.setBounds(5,503,95,62);
        b1.setBackground(Color.WHITE);
        b1.setFont(my);

        JButton b2 = new JButton("2");    b2.addActionListener( new Strocka(b2,filed));  b2.addActionListener( new Strocka(b2,filedup));
        b2.setBounds(105,503,95,62);
        b2.setBackground(Color.WHITE);
        b2.setFont(my);

        JButton b3 = new JButton("3");    b3.addActionListener( new Strocka(b3,filed));  b3.addActionListener( new Strocka(b3,filedup));
        b3.setBounds(205,503,95,62);
        b3.setBackground(Color.WHITE);
        b3.setFont(my);

        JButton bpl= new JButton("+");     bpl.addActionListener( new Arifm(bpl,filed));  bpl.addActionListener( new Strocka(bpl,filedup));
        bpl.setBounds(305,503,95,62);
        bpl.setBackground(new Color(255, 233, 255));
        bpl.setFont(my);

        JButton b4 = new JButton("4");    b4.addActionListener( new Strocka(b4,filed));  b4.addActionListener( new Strocka(b4,filedup));
        b4.setBounds(5,436,95,62);
        b4.setBackground(Color.WHITE);
        b4.setFont(my);

        JButton b5 = new JButton("5");    b5.addActionListener( new Strocka(b5,filed));  b5.addActionListener( new Strocka(b5,filedup));
        b5.setBounds(105,436,95,62);
        b5.setBackground(Color.WHITE);
        b5.setFont(my);

        JButton b6 = new JButton("6");    b6.addActionListener( new Strocka(b6,filed));  b6.addActionListener( new Strocka(b6,filedup));
        b6.setBounds(205,436,95,62);
        b6.setBackground(Color.WHITE);
        b6.setFont(my);

        JButton bmin=new JButton("-");     bmin.addActionListener( new Arifm(bmin,filed));  bmin.addActionListener( new Strocka(bmin,filedup));
        bmin.setBounds(305,436,95,62);
        bmin.setBackground(new Color(255, 233, 255));
        bmin.setFont(my);

        JButton b7 = new JButton("7");    b7.addActionListener( new Strocka(b7,filed));  b7.addActionListener( new Strocka(b7,filedup));
        b7.setBounds(5,369,95,62);
        b7.setBackground(Color.WHITE);
        b7.setFont(my);

        JButton b8 = new JButton("8");    b8.addActionListener( new Strocka(b8,filed));  b8.addActionListener( new Strocka(b8,filedup));
        b8.setBounds(105,369,95,62);
        b8.setBackground(Color.WHITE);
        b8.setFont(my);

        JButton b9 = new JButton("9");    b9.addActionListener( new Strocka(b9,filed));  b9.addActionListener( new Strocka(b9,filedup));
        b9.setBounds(205,369,95,62);
        b9.setBackground(Color.WHITE);
        b9.setFont(my);

        JButton bum = new JButton("*");    bum.addActionListener( new Arifm(bum,filed));  bum.addActionListener( new Strocka(bum,filedup));
        bum.setBounds(305,369,95,62);
        bum.setBackground(new Color(255, 233, 255));
        bum.setFont(my);

        JButton bpm = new JButton("±");       bpm.addActionListener( new Arifm(bpm,filed));  bpm.addActionListener( new Strocka(bpm,filedup));
        bpm.setBounds(5,570,95,62);
        bpm.setBackground(Color.WHITE);
        bpm.setFont(my);

        JButton b0 = new JButton("0");    b0.addActionListener( new Strocka(b0,filed)); b0.addActionListener( new Strocka(b0,filedup));
        b0.setBounds(105,570,95,62);
        b0.setBackground(Color.WHITE);
        b0.setFont(my);

        JButton bz = new JButton(",");  bz.addActionListener( new Strocka(bz,filedup));
        bz.setBounds(205,570,95,62);
        bz.setBackground(Color.WHITE);
        bz.setFont(my);

        bz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!filed.getText().contains(",")) {
                    filed.setText(filed.getText() + bz.getText());
                }
            }
        });
        bback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filed.setText(filed.getText().substring(0, filed.getText().length() - 1));
                filedup.setText(filedup.getText().substring(0, filedup.getText().length() - 1));
            }
        });

        JButton br = new JButton("=");     br.addActionListener( new Arifm(br,filed));  br.addActionListener( new Strocka(br,filedup));
        br.setBounds(305,570,95,62);
        br.setBackground(new Color(253, 127, 254));
        br.setFont(my);
        br.setForeground(Color.white);

        b1.setBorderPainted(false);
        b2.setBorderPainted(false);
        b3.setBorderPainted(false);
        b4.setBorderPainted(false);
        b5.setBorderPainted(false);
        b6.setBorderPainted(false);
        b7.setBorderPainted(false);
        b8.setBorderPainted(false);
        b9.setBorderPainted(false);
        b0.setBorderPainted(false);
        br.setBorderPainted(false);
        bpm.setBorderPainted(false);
        bz.setBorderPainted(false);
        bum.setBorderPainted(false);
        bpl.setBorderPainted(false);
        bmin.setBorderPainted(false);
        bde.setBorderPainted(false);
        bk.setBorderPainted(false);
        bkv.setBorderPainted(false);
        bre.setBorderPainted(false);
        bpr.setBorderPainted(false);
        bc.setBorderPainted(false);
        bce.setBorderPainted(false);
        bback.setBorderPainted(false);

        window.add(bback);
        window.add(bce);
        window.add(bpr);
        window.add(bc);
        window.add(bre);
        window.add(bk);
        window.add(bkv);
        window.add(bde);
        window.add(b7);
        window.add(b8);
        window.add(b9);
        window.add(b4);
        window.add(b5);
        window.add(b6);
        window.add(b1);
        window.add(b2);
        window.add(b3);
        window.add(bpm);
        window.add(b0);
        window.add(bz);
        window.add(bpl);
        window.add(bmin);
        window.add(br);
        window.add(bum);

        ImageIcon icon = new ImageIcon("src/g0.png");
        wind.setIconImage(icon.getImage());

        wind.setVisible(true);
    }
}
