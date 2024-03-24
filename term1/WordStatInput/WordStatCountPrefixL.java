//import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;
public class WordStatCountPrefixL{
    public static void main(String[] args){
        String [] mass = new String[4];
        int [] massind = new int[mass.length+1];
        try {
            NewScan reader = new NewScan(new FileInputStream(args[0])); // StandardCharset.UTF_8
            try {
                String currentLine;
                int start;
                boolean flag;
                boolean mf;
                String line;
                String linelow;
                while (reader.hasNextLine()) {
                    currentLine = reader.nextLine();
                    currentLine+=" ";
                    start = 0;
                    flag = false;
                    for (int i = 0; i < currentLine.length(); i++) {
                        mf = false;
                        if ((currentLine.charAt(i) == '\'' || Character.DASH_PUNCTUATION == Character.getType(currentLine.charAt(i)) || Character.isLetter(currentLine.charAt(i))) && !flag) {
                            start = i;
                            flag = true;
                        }
                        if (((currentLine.charAt(i) != '\'' && Character.DASH_PUNCTUATION != Character.getType(currentLine.charAt(i)) && !Character.isLetter(currentLine.charAt(i))) && flag == true)){
                            line = currentLine.substring(start, i + 1).toLowerCase();
                            linelow = currentLine.substring(start, i).toLowerCase();
                            flag = false;
                            if (linelow.length() >= 3) {
                                for (int j = 0; j < massind[0]; j++) {
                                    if (line.substring(0, 3).equals(mass[j])) {
                                        massind[j + 1] += 1;
                                        mf = true;
                                    }
                                }
                                if (!mf) {
                                    if (mass.length < massind[0] + 1) {
                                        mass = lstr(mass);
                                        massind = lint(massind);
                                    }
                                    mass[massind[0]] = linelow.substring(0, 3);
                                    massind[0] += 1;
                                    massind[massind[0]] += 1;
                                }
                            }
                        }
                    }
                }
                int copint;
                String copstr;
                for (int i = 0; i < massind[0]; i++) {
                    for (int j = 0; j < massind[0] - 1; j++) {
                        if (massind[j + 1] > massind[j + 2]) {
                            copint = massind[j + 1];
                            massind[j + 1] = massind[j + 2];
                            massind[j + 2] = copint;
                            copstr = mass[j];
                            mass[j] = mass[j + 1];
                            mass[j + 1] = copstr;
                        }
                    }
                }
            }
            finally {
                reader.close();
            }
        }
        catch (IOException e) {
            System.out.println("Error read: " + e.getMessage());
        }
        /*
        finally {
            try {
                reader.close();
            }
            catch (IOException e){
                System.out.println("Error close read: " + e.getMessage());
            }
        }
         */
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                for (int i = 0; i < massind[0]; i++) {
                    writer.write(mass[i] + " " + massind[i + 1] + "\n");
                }
            }
            finally {
                writer.close();
            }
        }
        catch (IOException e){
            System.out.println("Error write: " + e.getMessage());
        }
    }
    public static String[] lstr(String [] mass){
        String [] nm = new String[mass.length * 2];
        nm = Arrays.copyOf(mass,mass.length * 2);
        //System.arraycopy(mass, 0, nm, 0, mass.length); // Arrays.copyOf
        return nm;
    }
    public static int[] lint(int [] mass){
        int [] nm = new int[mass.length * 2];
        nm = Arrays.copyOf(mass,mass.length * 2);
        return nm;
    }
}