import  java.util.*;
public class True {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int n = scan.nextInt();
        int d = b-a;
        System.out.println((n-b+d-1)/d+(n-a+d-1)/d);
    }
}