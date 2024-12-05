Range search: find all keys between k1 and k2

Range count: number of keys between k1 and k2

```java
public int size(Key lo, key hi) {
  if (contains(hi)) return rank(hi) - rank(lo) + 1;
  else return rank(hi) - rank(lo);
}
```

# sweep line algorithm

takes proportional to N logN + R to find all R intersections among N orthogonal line segments

put x-co

# kd-trees

## 2-d trees

Typical case R + logN

Worst case R + sqrt(N)

# interval search trees

```java
public class IntervalST<Key extends Comparable<Key>, value>

IntervalST() // create an empty interval search tree

void put(Key lo, Key hi, Value val) // insert an interval

Value get(Key lo, Key hi) // all intervals that intersect the given interval

void delete(Key lo, Key hi) // delete the given interval

Iterable<Value> search(Key lo, Key hi) // all intervals in the tree that intersect the given interval
```

to insert an interval
* insert into BST using lo endpoint as the key
* update max in each node on search path

to search for any one interval that intersects a given intervalf

* if interval in node intersects query interval, return it

* else if left subtree is null, go right

* else if max endpoint in left subtree is less than lo endpoint of query interval, go right

* else go left

```java
Node x = root;
while (x != null) {
  if (x.interval.intersects(lo, hi)) return x.interval;
  else if (x.left == null) x = x.right;
  else if (x.left.max < lo) x = x.right;
  else x = x.left;
}
```

# Rectangle intersection

```java


```