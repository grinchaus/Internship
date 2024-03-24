import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Treatment implements ActionListener {
    int n;
    int hod[];
    JButton btn[];
    int j;
    static int k = 0;
    static String ch;
    static String pc;

    public Treatment(int n, int hod[], JButton btn[], int j, String ch, String pc) {
        this.n = n;
        this.hod = hod;
        this.btn = btn;
        this.j = j;
        this.ch = ch;
        this.pc = pc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (hod[n] == 0) {
            hod[n] = 2;
            btn[n].setText(ch);
            int random;
            do {
                random = (int) (Math.random() * j * j);
                k++;
            }
            while (hod[random] != 0);
           // hod[random] = 1;
          //  btn[random].setText(pc);
        }
        if (win(n) == true) {
            System.out.println("gg");
        }
    }

    //   0  1  2    //   0  1  2  3    //   0  1  2  3  4
    //   3  4  5    //   4  5  6  7    //   5  6  7  8  9
    //   6  7  8    //   8  9  10 11   //   10 11 12 13 14
                    //   12 13 14 15   //   15 16 17 18 19
                                       //   20 21 22 23 24

    private boolean win(int kl) {
            if (kl == 0 && (proverka(kl, j, 1) || proverka(kl, j * j, j) || proverka(kl, j * j, j + 1))) {
                return true;
            }
            if (kl == j - 1 && (proverka(kl, -1, -1) || proverka(kl, j * j, j) || proverka(kl, j * (j - 1) + 1, j - 1))) {
                return true;
            }
            if (kl == j * (j - 1) && (proverka(kl, j * j, 1) || proverka(kl, -1, -j) || proverka(kl, j - 2, -j + 1))) {
                return true;
            }
            if (kl == j * j - 1 && (proverka(kl, -1, -1) || proverka(kl, -1, -j) || proverka(kl, 0, -j - 1))) {
                return true;
            }
            for (int i = 0;i<j*j;i++){

                if(kl==i&&( proverka(kl,kl+j,1))){
                    return true;
                }
            }

        return false;
    }

    private boolean proverka(int i, int end, int step) {
        int k = 0;
        if (i < end) {
            for (int g = i; g < end; g = g + step) {
             //   if (hod[i] == hod[g]) k++;
            }
        }
        if (i > end) {
            for (int g = i; g > end; g = g + step) {
              //  if (hod[i] == hod[g]) k++;
            }
        }
        for (int r =0;r<j*j;r=r+j) {
            if (i == r)
                for (int g = i; g < end; g = g + step) {
                    if (hod[i] == hod[g]) k++;
                }
        }

        if (k == j)
            return true;
        else
            return false;
    }
}