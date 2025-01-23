---
title: 12.HashTable
date: 2025-01-23 12:00:00
tags: dataStructure
categories: AlgorithmI
---

ref: https://www.coursera.org/learn/algorithms-part1/home/module/12

# Java's hash code conventions

all java classes inherit a method hashCode(), which returns a 32-bit int

**Requirement:** if x.equals(y), then x.hashCode() == y.hashCode()

**High desirable:** if !x.equals(y), then x.hashCode() != y.hashCode()

**Default implementation:** return a memory address of x

# Java library implementations

```java
public final  class Integer {
    private final int value;
    public int hashCode() {
        return value;
    }
}

public final clas Boolean {
  private final boolean value;

  public int hashCode() {
    if (value) return 1231;
    else return 1237;
  }
}

public final class Double {
  priavte final double value;

  public int hashCode() {
    long bits = Double.doubleToLongBits(value);
    return (int) (bits ^ (bits >>> 32));
  }
}

// Horner's method
public final class String {
  priavte final char[] s;
  priavte int hash = 0;

  public int hashCode() {
    int h = hash;
    if (h != 0) return h;
    for (int i = 0; i < s.length; i++) {
      hash = s[i] + (31 * hash);
    }
    hash = h;
    return hash;
  }
}
// cache the hash code and return it when called
```

# HashCode design

Combine each significant field using $31x + y$ rule

If field is a primitive type, use wrapper type `hashCode()`

if field is null, return `0`

if field is a reference type, use `hashCode()`

if field is an array, apply to each entry

# Uniform hashing assumption

Bins and balls: Throw balls uniformly at random into $M$ bins

Birthday problem: Expect a collision after ~$\sqrt{\pi M / 2}$

Coupon collector: Expect to see every bin has >= 1 ball after ~$M lnM$

Load balancing: Each bin has $\theta (\log N / \log \log N)$ balls

# Separate chaining

use an array of $M < N$ linked lists

```java
public class SeparateChainingHashST<Key, Value> {
  private int M = 97;
  private Node[] st = new Node[M];

  priate static class Node {
    private Object key;
    private Object val;
    private Node next;
    ...
  }

  public Value get(Key key) {
    int i = hash(key);
    for (Node x = st[i]; x != null; x = x.next) {
      if (key.equals(x.key)) return (Value) x.val;
    }
    return null;
  }

  public void put(Key key, Value val) {
    int i = hash(key);
    for (Node x = st[i]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        x.val = val;
        return;
      }
    }
    st[i] = new Node(key, val, st[i]);
  }
}

```

Proposition. Under uniform hashing assumption, prob. that the number of
keys in a list is within a constant factor of $N / M$ is extremely close to 1.

Typical choice: $M ~ N / 5$ => constant-time ops

# linear probing

When a new key collides, find next empty slot, and put it there

Note: Array size M must be greater than number of key-value pairs $N$

```java
public class LinearProbingHashST<Key, Value> {
  private int M = 30001;
  private Value[] vals = (Value[]) new Object[M];
  private Key[] keys = (Key[]) new Object[M];

  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  public void put(Key key, Value val) {
    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (key.equals(keys[i])) {
        vals[i] = val;
        return;
      }
    }
    keys[i] = key;
    vals[i] = val;
  }

  public Value get(Key key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (key.equals(keys[i])) return vals[i];
    }
    return null;
  }
}
```

New Keys likely to hash into the middle of big clusters

## Knuth's parking problem

What is the mean displacement of a car?

With M/2 cars, mean displacement is ~$3/2$

With M cars, mean displacement is ~$\sqrt{(\pi M / 8)}$

Under uniform hashing assumption, the average # of probes
in a linear probing hash table of size M that contains $N = α M$ keys is:

Search hit: ~$\frac{1}{2}(1 + \frac{1}{1 - α})$

Search miss: ~$\frac{1}{2}(1 + \frac{1}{(1 - α)^2})$

so typical choice is $α = N / M ~ 1/2$

then prob. of search hit is $3/2$, and prob. of search miss is $5/2$


