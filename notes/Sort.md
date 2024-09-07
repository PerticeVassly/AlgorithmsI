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

# Shell Sort

Proposition: A g-sorted array remains g-sorted after h-sorting it.

proof https://oi-wiki.org/basic/shell-sort/(自己甄别)


```java
public static void sort(Comparable[] a) {
    int N = a.length;

    int h = 1;
    while (h < N / 3) h = 3 * h + 1;

    while (h >= 1) {
        for (int i = h; i < N ; i++){
            for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                exchange(a, j, j-h)
            }
        }
        h = h / 3;
    }

}
```

proposition The worst-case number of compares used by shellsort with the 3x+1 increments is O(N^(3/2))

Property: Number of compares used by shellsort with the 3x+1 increments is at most by a small multiple of N times the # of increments used

# Merge Sort

Divide the array into two halves

Recursively sort each half

Merge two halves

```java
private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

  assert isSorted(a, lo, mid);
  assert isSorted(a, mid+1, hi);

  // copy
  for (int k = lo ; i <= hi; i ++) {
    aux[k] = a[k];
  }

  //merge
  int i = lo, j = mid + 1;
  for (int k = lo; k <= hi; k++) {
    if (i > mid) {
      a[k] = aux[j++];
    } else if (j > hi) {
      a[k] = aux[i++];
    } else if(less(aux[i], aux[j]) {
      a[k] = aux[i++];
    } else {
      a[k] = aux[j++];
    }
  }

  assert isSorted(a, lo, hi);
}

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
  if (hi <= low) {
    return;
  }

  int mid = lo + (hi - lo) / 2;
  sort(a, aux, lo, mid);
  sort(a, aux, mid + 1, hi);
  merge(a, aux, lo, mid, hi);
}

public static void sort(Comparable[] a) {
  aux = new Comparable[a.length];
  sort(a, aux, 0, a.length - 1);
}
```
MergeSort suse at most NlgN compares and 6NlogN array access to sort any array fo size N


but merge sort sometimes can be inefficient for some small arrays and so you can add a judgement 

```java
if (hi <= lo + CUTOFF - 1) {
  Insertion.sort(a, lo ,hi);
  return
} //cutoff is 7
```

```java
if (!less(a[mid +1], a[mid])) {
  return
}
```

here you need two array but you always first copy the array a which is actually unneeded


```java
private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

  assert isSorted(a, lo, mid);
  assert isSorted(a, mid+1, hi);

  //merge
  int i = lo, j = mid + 1;
  for (int k = lo; k <= hi; k++) {
    if (i > mid) {
      aux[k] = a[j++];
    } else if (j > hi) {
      aux[k] = a[i++];
    } else if(less(a[i], a[j]) {
      aux[k] = a[i++];
    } else {
      aux[k] = a[j++];
    }
  }

  assert isSorted(a, lo, hi);
}

// the result sortinto aux
private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
  if (hi <= low) {
    return;
  }

  int mid = lo + (hi - lo) / 2;
  sort(aux, a, lo, mid);
  sort(aux, a, mid + 1, hi);
  merge(a, aux, lo, mid, hi);
}
```

# Bottom-up mergesort

```java

private static void merge(Comparable[] a, int lo, int mid, int hi) {
  ...
}

public static void sort(Comparable[] a) {
  int N = a.length;
  aux = new Comparable[N];
  for (int sz = 1; sz < N; sz +=sz) {
    for(int lo = 0; lo < N - sz; lo += sz + sz) {
      merge(a, lo, lo + sz - 1; Math.min(lo + sz + sz - 1, N -1))
    }
  }
}
```











