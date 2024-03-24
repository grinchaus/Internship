import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Strocka implements ActionListener {
    JButton btn;
    JLabel lab;
    static String p;

    public Strocka(JButton b, JLabel filed) {
        this.btn = b;
        this.lab = filed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p = lab.getText();

        if (btn.getText().equals("0") || btn.getText().equals("1") || btn.getText().equals("2") || btn.getText().equals("3") || btn.getText().equals("4") || btn.getText().equals("5") || btn.getText().equals("6") || btn.getText().equals("7") || btn.getText().equals("8") || btn.getText().equals("9")) {
            if (lab.getText().equals("0")) {
                lab.setText(btn.getText());
                p = btn.getText();
            } else {
                lab.setText(lab.getText() + btn.getText());
                p = btn.getText();
            }
        } else {
            if (!lab.getText().contains("+") && !lab.getText().contains("-") && !lab.getText().contains("*") && !lab.getText().contains("÷")) {
                if (btn.getText().equals("+"))
                    lab.setText(lab.getText() + " + ");
                if (btn.getText().equals("-"))
                    lab.setText(lab.getText() + " - ");
                if (btn.getText().equals("÷"))
                    lab.setText(lab.getText() + " ÷ ");
                if (btn.getText().equals("*"))
                    lab.setText(lab.getText() + " * ");
            }

            if (btn.getText().equals("√"))
                lab.setText("√(" + lab.getText() + ")");
            if (btn.getText().equals("1/x"))
                lab.setText("1/(" + lab.getText() + ")");
            if (btn.getText().equals("x²"))
                lab.setText("(" + lab.getText() + ")²");
            if (!lab.getText().contains("="))
                if (btn.getText().equals("=")) {
                    lab.setText(lab.getText() + " = ");
                    /*lab.setText(p.setText().length()-1);
                    if (btn.getText().equals("0") || btn.getText().equals("1") || btn.getText().equals("2") || btn.getText().equals("3") || btn.getText().equals("4") || btn.getText().equals("5") || btn.getText().equals("6") || btn.getText().equals("7") || btn.getText().equals("8") || btn.getText().equals("9")) {
                        lab.setText(btn.getText());
                    }*/
                }
            if (btn.getText().equals("CE"))
                lab.setText("0");
            if (btn.getText().equals("C"))
                lab.setText("0");
        }
    }
}


