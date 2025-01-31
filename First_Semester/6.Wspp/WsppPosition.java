import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class WsppPosition{
    public static void main(String[] args) {
        Map<String,ArrayList<Integer>> mass = new LinkedHashMap<>(Map.of());
        ArrayList<Integer> chisla = new ArrayList<Integer>(0);
        mass.put("*",chisla);
        //ArrayList<Integer> defolt = new ArrayList<Integer>();
            try {
                NewScanner reader = new NewScanner(new FileInputStream(args[0]));
                try {
                    int kslov = 1;
                    int kstr = 1;
                    boolean proverka;
                    String line;
                    StringBuilder slovo = new StringBuilder("");
                    StringBuilder perenos = new StringBuilder();
                    char simbol = reader.readInput();
                    while (!reader.end()) {
                        proverka = (simbol == '\''
                                || Character.DASH_PUNCTUATION == Character.getType(simbol)
                                || Character.isLetter(simbol));
                        if(proverka) {
                            slovo.append(simbol);
                        }
                        if(!proverka && slovo.length() > 0) {
                            chisla = new ArrayList<Integer>();
                            line = slovo.toString().toLowerCase();
                            chisla = mass.getOrDefault(line,chisla);
                            if (chisla.size() == 0) {
                                chisla.add(1);
                            } else {
                                chisla.set(0, chisla.get(0) + 1);
                            }
                            chisla.add(kstr);
                            chisla.add(kslov);
                            mass.put(line, chisla);
                            kslov++;
                            slovo.delete(0,slovo.length());
                            //slovo = new StringBuilder("");
                        }
//                        perenos.append(simbol);
//
//                        perenos.append(simbol);
//                        if(perenos.length() == System.lineSeparator().length()*2){
//                            perenos.delete(0,System.lineSeparator().length());
//                        }
                        if(simbol == '\n') {
                            //chisla = mass.getOrDefault("*",defolt);
                            chisla = mass.get("*");
                            chisla.add(kslov);
                            mass.put("*", chisla);
                            kstr++;
                        }
                        simbol = reader.readInput();
                    }
                } finally {
                    reader.close();
                }
            }
            catch(IOException e){
                System.out.println("Error read: " + e.getMessage());
            }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                int k = 0;
                int nstr;
                int nsl;
                for (String s : mass.keySet()) {
                    if (s != "*") {
                        writer.write(s + " "+ mass.get(s).get(0));
                        for (int i = 2; i < mass.get(s).size(); i += 2) {
                            nstr = mass.get(s).get(i - 1);
                            nsl = mass.get("*").get(nstr-1) - mass.get(s).get(i);
                            writer.write(" " + nstr + ":" + nsl);
                        }
                        writer.write("\n");
                        k++;
                    }
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