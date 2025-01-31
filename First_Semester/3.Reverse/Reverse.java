import java.util.Arrays;
public class Reverse {
    public static void main(String[] args) {
        NewScan scanner = new NewScan(System.in);
        int [][] mass = new int[5][5];
        int i = 0; int j;
        while(scanner.hasNextLine()){
            j = 1;
            NewScan sc = new NewScan(scanner.nextLine());
            while(sc.hasNextInt()){
                if(j + 1 > mass[i].length){
                    mass[i] = lm(mass[i]);
                }
                mass[i][j] = sc.nextInt();
                j++;
            }
            if(i + 1 == mass.length){
                mass = ls(mass);
            }
            mass[i][0] = j;
            i++;
            sc.close();
        }
        for(int i1 = i-1; i1 >= 0; i1--){
            for(int j1 = mass[i1][0] - 1; j1 > 0; j1--) {
                System.out.print(mass[i1][j1] + " ");
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
}