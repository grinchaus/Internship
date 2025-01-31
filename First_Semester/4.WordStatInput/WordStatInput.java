import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class WordStatInput{
    public static void main(String[] args) {
        //String[] mass = new String[4];
        //int[] massind = new int[mass.length + 1];
        Map<String,Integer> mass1 = new LinkedHashMap<>(Map.of());
        //System.err.println(mass1.put("-",mass1.get("-")));
        try {
            NewScan reader = new NewScan(new FileInputStream(args[0]));
            try {
                String currentLine;
                int start;
                boolean flag;
                boolean mf;
                String line;
                while (reader.hasNextLine()) {
                    currentLine = reader.nextLine();
                    currentLine += " ";
                    start = 0;
                    flag = false;
                    for (int i = 0; i < currentLine.length(); i++) {
                        mf = false;
                        if ((currentLine.charAt(i) == '\'' || Character.DASH_PUNCTUATION == Character.getType(currentLine.charAt(i)) || Character.isLetter(currentLine.charAt(i))) && !flag) {
                            start = i;
                            flag = true;
                        }
                        if (((currentLine.charAt(i) != '\'' && Character.DASH_PUNCTUATION != Character.getType(currentLine.charAt(i)) && !Character.isLetter(currentLine.charAt(i))) && flag == true)) {
                            flag = false;
                            line = currentLine.substring(start, i).toLowerCase();
                            if(mass1.put(line,mass1.get(line)) == null){
                                mass1.put(line,1);
                            }
                            else{
                                mass1.put(line,mass1.get(line)+1);
                            }
                        }
                    }
                }
                reader.close();
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                for (String s : mass1.keySet()) {
                    //System.err.println(s + " " + mass1.get(s) + "\n");
                    writer.write(s + " " + mass1.get(s) + "\n");
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
        return nm;
    }
    public static int[] lint(int [] mass){
        int [] nm = new int[mass.length * 2];
        nm = Arrays.copyOf(mass,mass.length * 2);
        return nm;
    }
}