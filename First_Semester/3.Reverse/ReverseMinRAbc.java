import java.util.Arrays;
public class ReverseMinRAbc {
    static String goodChars = "abcdefghij";
    public static void main(String[] args) {
        NewScan scanner = new NewScan(System.in);
        int [][] mass = new int[5][5];
        int i = 0; int j;
        int mn;
        int mx=1;
        while(scanner.hasNextLine()){
            j = 1;
            NewScan sc = new NewScan(scanner.nextLine());
            while(sc.hasNext()){
                if(j + 1 > mass[i].length){
                    mass[i] = lm(mass[i]);
                }
                mass[i][j] = toDigit(sc.next());
                j++;
            }
            if(i + 1 == mass.length){
                mass = ls(mass);
            }
            mass[i][0] = j;
            i++;
            sc.close();
        }
        for(int i1 = 0; i1 <i; i1++){
            mn = Integer.MAX_VALUE;
            for(int j1 = 1; j1 < mass[i1][0]; j1++) {
                if (mass[i1][j1] < mn){
                    mn = mass[i1][j1];
                    System.out.print(toLetter(mass[i1][j1]) + " ");
                } else{
                    mass[i1][j1] = mn;
                    System.out.print(toLetter(mn) + " ");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
    public static int[][] ls(int [][] mass){
        int [][] nm = new int[mass.length*2][4];
        for(int i = 0; i < mass.length; i++){
            nm[i]=Arrays.copyOf(mass[i],mass[i].length);
        }
        return nm;
    }
    public static int[] lm(int [] mass){
        int [] nm = new int[mass.length*2];
        System.arraycopy(mass, 0, nm, 0, mass.length);
        return nm;
    }
    public static int toDigit(String str){
        StringBuilder line = new StringBuilder("");
        for(int i = 0;i < str.length();i++){
            if(i == 0 && str.charAt(0) == '-'){
                line.append("-");
                i++;
            }
            if (goodChars.indexOf(str.charAt(i)) >= 0) {
                line.append(goodChars.indexOf(str.charAt(i)));
            }
        }
        return Integer.parseInt(line.toString());
    }
    public static String toLetter(int digit){
        StringBuilder line = new StringBuilder("");
        String str=Integer.toString(digit);
        for(int i = 0; i < str.length(); i++){
            if(i == 0 && digit < 0){
                line.append("-");
                i++;
            }
            for(int j = 0; j < goodChars.length(); j++) {
                if(Integer.parseInt(String.valueOf(str.charAt(i))) == j){
                    line.append(goodChars.charAt(j));
                    break;
                }
            }
        }
        return line.toString();
    }
}