import java.util.Scanner;
public class Bad_Treap {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i = -710*25000; i <= n; i+=710){
            System.out.println(i - 710 * 25000);
        }
    }
}
