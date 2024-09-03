# SelectionSort


```java

public static void sort(Comparable[] a) {
    int N = a.length;
    for (int i = 0 ; i < N; i++) {
        int min = i;
        for (int j = i + 1; i < N; j++) {
            if (less(a[j], a[min]) {
                min = j;
            }
        }
        ech(a[i],a[min]);
    }
}
```

$\frac{N^2}{2}$ compares and $N$ exchange

无论输入是否有序，均需要此时间以及交换次数



# Insertion sort

```java
public static void sort(Comparable[] a) {
    int N = a.length;
    for (int i = 0 ; i < N; i++) {
        for(int j = i, j > 0 j --){
            if(less(a[j],a[j-1])){
                ech(a,j,j-1);
            }
            else break;
        } // j can't be 0
    }
}
```

use $\frac{N^2}{4}$ compares and $\frac{N^2}{4}$ exchanges on average

best case: N-1 compare and 0 exchanges

worst case: 0 + 1 + 2 + ... + N - 1 compares ~ $\frac{N^2}{4}$ and 

same times of exchange

Def: An inversion is a pair of keys that are out of order

Def: An array is partially sorted if the number of inversios is <= cN

For partially-sorted arrays. insertion sort runs in linear time. because echange means change the order of two key so the exchange time is actually the num of inversion, and the number of compare is equal to the number of exchange