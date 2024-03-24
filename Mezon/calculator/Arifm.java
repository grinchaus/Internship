//import jdk.nashorn.internal.ir.IfNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arifm implements ActionListener {
    JButton btn;
    JLabel str;
    static double ch1=0;
    static String op;
    static double res;
    static int res1;
    static String p;

    public Arifm(JButton b, JLabel lab) {
        this.btn = b;
        this.str = lab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String str1 = str.getText();
        str1 = str1.replace(",", ".");
        double ch = Double.parseDouble(str1);
        switch (btn.getText()){
            case "*":
            case "+":
            case "-":
            case "÷":
                op = btn.getText();
                ch1 = ch;
                str.setText("0");break;

            case "=":

                switch (op){
                    case "+":
                        res = ch1+ch;break;
                    case "-":
                        res = ch1-ch;break;
                    case "*":
                        res = ch1*ch;break;
                    case "÷":
                        res = ch1/ch;
                }
                p = Double.toString(res);
                p = p.replace(".", ",");
                if (p=="Infinity"||p=="NaN") {
                    str.setText("ошибка");
                }
                else {
                    str.setText(p);
                }
                break;
            case "√": res = Math.sqrt(ch);
            p = Double.toString(res);
            p = p.replace(".", ",");
            str.setText(p);break;
            case "x²": res = ch*ch;
            p = Double.toString(res);
            p = p.replace(".", ",");
            str.setText(p);break;
            case "1/x": res = 1/ch;
            p = Double.toString(res);
            p = p.replace(".", ",");
            str.setText(p);break;
            case "±":if (ch==0) res=0;else res = ch * -1;
            p = Double.toString(res);
            p = p.replace(".", ",");
            str.setText(p);break;
        }
    }
}