import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class Wspp{
    public static void main(String[] args) {
        Map<String,ArrayList<Integer>> mass = new LinkedHashMap<>(Map.of());
        ArrayList<Integer> chisla;
            try {
                NewScan reader = new NewScan(new FileInputStream(args[0]));
            try {
                String currentLine;
                int start;
                int nomer = 1;
                boolean flag;
                boolean mf;
                String line;
                while (reader.hasNextLine()) {
                    currentLine = reader.nextLine();
                    currentLine += " ";
                    start = 0;
                    flag = false;
                    for (int i = 0; i < currentLine.length(); i++) {
                        chisla = new ArrayList<Integer>();
                        mf = false;
                        if ((currentLine.charAt(i) == '\'' || Character.DASH_PUNCTUATION == Character.getType(currentLine.charAt(i)) || Character.isLetter(currentLine.charAt(i))) && !flag) {
                            start = i;
                            flag = true;
                        }
                        if (((currentLine.charAt(i) != '\'' && Character.DASH_PUNCTUATION != Character.getType(currentLine.charAt(i)) && !Character.isLetter(currentLine.charAt(i))) && flag == true)) {
                            flag = false;
                            line = currentLine.substring(start, i).toLowerCase();
                            mass.put(line,mass.get(line));
                            if(mass.get(line) == null){
                                chisla.add(1);
                                chisla.add(nomer);
                                mass.put(line,chisla);
                            }
                            else{
                                chisla = mass.get(line);
                                chisla.set(0,chisla.get(0)+1);
                                chisla.add(nomer);
                                mass.put(line,chisla);
                            }
                            nomer++;
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
                for (String s : mass.keySet()) {
                    writer.write(s);
                    for (int i : mass.get(s)) {
                        writer.write(" "+i);
                    }
                    writer.write("\n");
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
}