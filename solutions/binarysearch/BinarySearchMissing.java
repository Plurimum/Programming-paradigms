package binarysearch;

public class BinarySearchMissing {
    // Pre : args.length > 0 && args[0], ..., args[n] are integers && args[1]..args[n] are sorted by non-growth
    // Post : if args has elements output is i : args[i + 1] == x
    // else output is -i - 1
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        final int size = args.length - 1;
        int[] a = new int[size];
        for (int i = 0; i < size; i++)
            a[i] = Integer.parseInt(args[i + 1]);
        int result;
        System.out.println((result = recursiveBinarySearch(a, x)) == iterativeBinarySearch(a, x) ? result : "Error");
    }

    // Pre : i = 0..(size - 1) && a[i + 1] <= a[i]
    // Post : Result = i && a[i - 1] > x >= a[i]
    // T(n) = T(n / 2) + C + T(n / 2) + C = 2 * C + T(n / 4) + T(n / 4) + 2 * C = ... = 2 * log2(n) * C + 1
    static int recursiveBinarySearch(int a[], int x){
        return recursiveBinarySearch(a, x, 0, a.length);
    }
    // INV: a[0]..a[l - 1] > key >= a[r]..a[size - 1]
    static int recursiveBinarySearch(int a[], int key, int l, int r) {
        if (l < r) {
            // INV && l < r
            int m = (l + r) >> 1;
            // l <= m < r
            if (a[m] > key) {
                // INV && key < a[m]
                // a[0]..a[m] > key >= a[r]...a[size - 1]
                // range' = a[m + 1]..a[r] => range'.length = r.length / 2
                return recursiveBinarySearch(a, key, m + 1, r);
            } else {
                // INV && key >= a[m]
                // a[0]...a[l - 1] >= a[m] > a[m]...a[size - 1]
                // range' = a[l]..a[m] => range'.length = r.length / 2
                return recursiveBinarySearch(a, key, l, m);
            }
        } else
            // Post: INV && l >= r
            // a[l - 1] > x >= a[r] && l - 1 >= r - 1
            // a[r - 1] > x >= a[r]
            // Result = r
            // r < size && a[r] == x => Result = r
            // r >= size || a[r] != x => Result = -r - 1
            return (r < a.length) && (key == a[r]) ? r : -r - 1;
    }
    // Pre : i = 0...(size - 1) && a[i + 1] <= a[i]
    // Post : Result = i && a[i - 1] > x >= a[i]
    // T(n) = T(n / 2) + C + T(n / 2) + C = 2 * C + T(n / 4) + T(n / 4) + 2 * C = ... = 2 * log2(n) * C + 1
    static int iterativeBinarySearch(int a[], int key) {
        int l = 0, r = a.length;
        // INV: a[0]...a[l - 1] > key >= a[r]....a[size - 1]
        while (l < r) {
            // INV && l < r
            int m = (l + r) >> 1;
            // l <= m < r
            if (a[m] <= key) {
                // INV && key >= a[m]
                r = m;
                // range' = a[l]..a[m] => range'.length = range / 2
                // a[0]...a[l - 1] >= a[m] > a[r]...a[size]
            } else {
                // INV && key < a[m]
                l = m + 1;
                // range' = a[m + 1]..a[r] => range'.length = range / 2
                // a[-1]...a[m] > a[m] >= a[r]...a[size]
            }
        }
        // POST: INV && l >= r
        // a[l - 1] > x >= a[r] && l - 1 >= r - 1
        // a[r - 1] > x >= a[r]
        // Result = r
        // r < size && a[r] == x => Result = r
        // r >= size || a[r] != x => Result = -r - 1
        return (r < a.length) && (a[r] == key) ? r : (-r - 1);
    }
}