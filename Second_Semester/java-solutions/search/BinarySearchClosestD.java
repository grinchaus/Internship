package search;

public class BinarySearchClosestD {
    //Let immutable(n): for i = 0..n: mass[i]` == mass[i] && x` == x

    //Pred: 0 < r < mass.length
    //Post: (R == mass[r] && Math.abs(mass[r] - x) < Math.abs(mass[r - 1] - x) ||
    // || R == mass[r - 1] && Math.abs(mass[r] - x) >= Math.abs(mass[r - 1] - x) && immutable(n)
    private static int leastDifferent(int r, int[] mass, int x) {
        //Pred == P1: Math.abs(mass[r] - x) < Math.abs(mass[r - 1] - x) ||
        // || Math.abs(mass[r] - x) >= Math.abs(mass[r - 1] - x)
        if(r < mass.length){
            //Pred: P1 && r < mass.length
            // ↓ if (cond) { ... }
            if (r - 1 > 0 && Math.abs(mass[r] - x) >= Math.abs(mass[r - 1] - x)) {
                //Pred: P1 && r < mass.length && cond
                return mass[r - 1];
                //Post: R == mass[r - 1]
            }else{
                //Pred: P1 && r < mass.length && !cond
                return mass[r];
                //Post: R == mass[r]
            }
            //Post: R == mass[r] || R == mass[r - 1]
        }
        else {
            //Pred: P1 && r >= mass.length
            return mass[r - 1];
            //Post: R == mass[r - 1]
        }
        //Post: R == mass[r] || R == mass[r - 1]
    }

    //Pred: l` >= 0 && r` <= mass.length && for i=0..n-1: mass[i] >= mass[i+1]
    //Post: (R == mass[r] && Math.abs(mass[r] - x) < Math.abs(mass[r - 1] - x) ||
    // || R == mass[r - 1] && Math.abs(mass[r] - x) >= Math.abs(mass[r - 1] - x) &&
    // && immutable(n) && r` == r && l` == l
    public static int binarySearchRecursion(int l, int r, int[] mass, int x) {
        //Pred: 0 <= l` <= r` <= mass.length
        if (r - l <= 1) {
            //Pred: r` - l` <= 1 && 0 < r` < mass.length
            return leastDifferent(r, mass, x);
            //Post: R == leastDifferent(r`, mass, x)
        } else {
            //Pred: r` - l` > 1
            int m = l + (r - l) / 2;
            //Post: m` == l` + (r` - l`) / 2 && r` - l` > 1 && l` <= m` <= r`
            //Pred: 0 <= m` <= mass.length
            if (mass[m] > x) {
                //Pred: 0 <= m` <= mass.length && mass[m`] > x
                l = m;
                //Post: l` == m`
            } else {
                //Pred: 0 <= m` <= mass.length && mass[m`] <= x
                r = m;
                //Post: r` == m`
            }
            //Post: r` == r` && l` == m` || l` == l` && r` == m`
            //Pred: 0 <= l` <= r` <= mass.length
            return binarySearchRecursion(l, r, mass, x);
            //Post: Run a binary search on a smaller interval
        }
        //Post: The binary search worked correctly (10/10)
    }

    //Pred: for i=0..n-1: mass[i] >= mass[i+1]
    //Post: (R == mass[r] && Math.abs(mass[r] - x) < Math.abs(mass[r - 1] - x) ||
    // || R == mass[r - 1] && Math.abs(mass[r] - x) >= Math.abs(mass[r - 1] - x) && immutable(n)
    public static int binarySearchIteration(int[] mass, int x) {
        //Pred: l <= ∀i && mass[i] - exists && l - max
        int l = 0;
        //Post: 0 <= ∀i && mass[0] - exists ⇒ l == 0
        //Pred: r > ∀i && r - min && mass[i] - exists
        int r = mass.length;
        //Post: mass.length > ∀i && mass[mass.length] - not exists ⇒ r == mass.length
        //I: 0 <= l` <= m` <= r` <= mass.length && mass[l`] >= mass[m`] >= mass[r`]
        while (r - l > 1) {
            //Pred: I && r` - l` > 1
            int m = l + (r - l) / 2;
            //Post: m` = l` + (r` - l`) / 2 && I && r` - l` > 1
            //Pred: I && r` - l` > 1
            if (mass[m] > x) {
                //Pred: I && r` - l` > 1 && mass[m`] > x
                l = m;
                //Post: l` == m`
            } else {
                //Pred: I && r` - l` > 1 && mass[m`] <= x
                r = m;
                //Post: r` == m`;
            }
            //Post: r` == r` && l` == m` || l` == l` && r` == m`
        }
        //Post: I && r` - l` <= 1
        //Pred: 0 < r` < mass.length
        return leastDifferent(r, mass, x);
        //Post: R == leastDifferent(r`, mass, x)
    }

    //Pred == P`: String[] args && args.length > 0 && int x = (int) args[0] &&
    // && int[] mass = (int) args[0:] && 0 <= i <= args.length && ∀ args[i] - correct
    //Post: R == y && y - the nearest number to x && y in mass[] && immutable(n)
    public static void main(String[] args) {
        //Pred: P`
        int x = Integer.parseInt(args[0]);
        //Post: x == (int) args[0]
        //Pred: P`
        int[] mass = new int[args.length];
        //Post: mass.length == args.length
        //Pred: Integer.MAX_VALUE >= ∀ mass[i]
        mass[0] = Integer.MAX_VALUE;
        //Post: mass[0] = Integer.MAX_VALUE && mass[0] >= mass[1]
        //Pred: true
        int i = 1;
        int sum = 0;
        //Post: sum = 0 && i == 1
        //I: i >= 1 && mass[i]` = (int) args[i]`
        while (i < args.length) {
            //Pred: I && i` < args.length
            mass[i] = Integer.parseInt(args[i]);
            //Post: I && mass[i`] == (int) args[i`] && i` < args.length
            //Pred: I && mass[i`] - correct && sum + mass[i`] <= Integer.MAX_VALUE
            sum = sum + mass[i];
            //Past: I && sum` = sum + mass[i] && sum` <= Integer.MAX_VALUE
            //Pred: i + 1 < args.length
            i = i + 1;
            //Post: i` == i + 1 && i` < args.length
        }
        //Post: I &&  mass == (int) args && i >= args.length
        //Pred: true
        if (sum % 2 == 0) {
            //Pred: sum % 2 == 0 && l(first argument) == 0 && r(second argument) == mass.length
            System.out.println(binarySearchRecursion(0, mass.length, mass, x));
            //Post: R == binarySearchRecursion(0, mass.length, mass, x)
        } else {
            //Pred: sum % 2 == 1
            System.out.println(binarySearchIteration(mass, x));
            //Post: R == binarySearchIteration(mass, x)
        }
        //Post: print R
    }
}