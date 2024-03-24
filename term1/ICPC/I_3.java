import java.util.Scanner;

public class Ideal_Pyramid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x,y,arr;
        int xl = Integer.MAX_VALUE;
        int yl = Integer.MAX_VALUE;
        int xr = 0;
        int yr = 0;
        for(int i = 0; i < n; i++) {
            x = scan.nextInt();
            y = scan.nextInt();
            arr = scan.nextInt();
            if (x - arr < xl) {
                xl = x - arr;
            }
            if (x + arr > xr){
                xr = x + arr;
            }
            if (y - arr < yl){
                yl = y-arr;
            }
            if (y + arr > yr){
                yr = y + arr;
            }
        }
        int hotv,xotv,yotv;
        double x1 = (xr - xl) / 2;
        double y1 = (yr - yl) / 2;
        if (x1 > y1){
            hotv = (int) Math.ceil(x1);
        }
        else {
            hotv = (int) Math.ceil(y1);
        }
        xotv = (xl + xr) / 2;
        yotv = (yl + yr) / 2;
        System.out.println(xotv + " " + yotv + " " + hotv);
    }
}
