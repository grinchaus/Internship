package search;

public class BinarySearch {

    //Pred: l` >= 0 && r` <= mass.length && mass - sorted in non ascending order
    //Post: R = i && a[i] <= x && i - min
    public static int binarySearchRecursion(int l, int r, int[] mass, int x) {
        //Pred: 0 <= l` <= r` <= mass.length
        if (r - l <= 1) {
            //Pred: R == r` - 1 && r` - l` <= 1
            return r - 1;
        } else {
            //Pred: r` - l` > 1
            int m = (r + l) / 2;
            //Post: m` == l` + (r` - l`) / 2 && r` - l` > 1
            //Pred: 0 <= m` <= mass.length
            if (mass[m] <= x) {
                // 0 <= m` <= mass.length && mass[m`] > x
                r = m;
                //Post: l` == m`
                return binarySearchRecursion(l, r, mass, x);
            } else {
                // 0 <= m` <= mass.length && mass[m`] <= x
                l = m;
                //Post: r` == m`
                return binarySearchRecursion(l, r, mass, x);
            }
        }
        //Post: We will never visit here
    }

    //Pred: mass - sorted in non ascending order
    //Post: R = i && a[i] <= x && i - min
    public static int binarySearchIteration(int[] mass, int x) {
        //Pred: r > ∀i && r - min && mass[i] - exists
        int r = mass.length;
        //Post: mass.length > ∀i && mass[mass.length] - not exists ⇒ r == mass.length
        //Pred: l <= ∀i && mass[i] - exists && l - max
        int l = 0;
        //Post: 0 <= ∀i && mass[0] - exists ⇒ l == 0
        //I: 0 <= l` <= m` <= r` <= mass.length && mass[l`] >= mass[m`] >= mass[r`]
        while (r - l > 1) {
            //Pred: I && r` - l` > 1
            int m = l + (r - l) / 2;
            //Post: m` = l` + (r` - l`) / 2 && I && r` - l` > 1
            //Pred: I && r` - l` > 1
            if (mass[m] <= x) {
                //Pred: I && r` - l` > 1 && mass[m`] > x
                r = m;
                //Post: l` == m`
            } else {
                //Pred: I && r` - l` > 1 && mass[m`] <= x
                l = m;
                //Post: r` == m`;
            }
            //Post: r` == r` || r` == m` && l` == l` || l` == m`
        }
        //I && r` - l` <= 1 && R == r` - 1
        return r - 1;
    }

    //Pred: String[] args && args.length > 0 && int x = (int) args[0] &&
    // && int[] mass = (int) args[0:] && 0 <= i <= args.length && ∀ args[i] - correct
    //Post: R = i && a[i] <= x && i - min
    public static void main(String[] args) {
        //Pred: true
        int x = Integer.parseInt(args[0]);
        //Post: x == (int) args[0]
        //Pred: true
        int[] mass = new int[args.length];
        //Post: mass.length == args.length
        //Pred: true
        mass[0] = Integer.MAX_VALUE;
        //Post: mass[0] = Integer.MAX_VALUE
        //Pred: true
        int i = 1;
        //Post: i == 1
        //I: i >= 1 && mass[i]` = (int) args[i]`
        while (i < args.length) {
            //Pred: i` < args.length
            mass[i] = Integer.parseInt(args[i]);
            //Post: I &&  mass[i`] == (int) args[i`] && i` < args.length
            //Pred: i + 1 < args.length
            i = i + 1;
            //Post: i` == i + 1 && i` < args.length
        }
        //Post: I &&  mass == (int) args && i >= args.length
        //Pred: l == 0 && r == mass.length
        System.out.println(binarySearchRecursion(0, mass.length, mass, x));
//        System.out.println(BinarySearchIteration(mass, x));
        //Post: print R
    }
}