# knuth shuffle

in iteration i pick integer r between 0 and i uniformly at random

Swap a[i] and a[r]

linear time

public static void shuffle (Object[] a) {
    int N = a.length;
    for (int i = 0 ; i < N; I++) {
        int r = StdRandom.uniform(i + 1);
        exch(a, i , r);
    }
}