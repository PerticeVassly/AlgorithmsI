```
public interface Comparator<key> {
    int compare(Key v, Key w)
}
```

Required property must be a total order

# how to use

Create Comparator object

Passs a second argument to Arrays.sort().

Arrays.sort(a, String.CASE_INSTANCE_OERDER)

it can decouples the definition of the datatype from the definition of the data type from the definition of what it means to compare two objects of that type

1. use ObjectInstead of Comparable

2. add Comparator to the fucntion for strategy

```java
public static void sort(Object[] a, Comparator comparator) {
    int N = a.length;
    for (int i = 0 ; i < N; i ++) {
        for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]) ;j--) {
            exch(a, j, j-1);
        }
    }
}

private static boolean less(Comparator c, Object v, Object w) {
    return c.compare(v,w) < 0>
}

private static void exch(Object[] a, int i, int j) {
    Object swap = a[i] ;
    a[i] = a[j];
    a[j] = swap;
}
```

example

```java
public class student {
    public static final Comparator<Student> BY_NAME = new ByName();

    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }
}
```