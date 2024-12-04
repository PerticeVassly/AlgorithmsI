1. Represent 2-3 tree as BSTs

Use "inetrnal" left-leaning links as "glue" for 3-nodes

A BST such that:

* No node has two red links connected to it 

every path from root to null link has the same number of the black links

Red links lean left

there is 1-1 correspondence between 2-3 trees and red-black BSTs


```java
public Val get(Key key) {
  Node x = root;
  while (x !+ null)
  {
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x = x.left;
    else if (cmp > 0) x = x.right;
    else return x.val;
  }
  return null;
}

private static final Boolean RED = true;
private static final Boolean BLACK = false;

private class Node {
  Key key;
  Val val;
  Node left, right;
  Boolean color;
}

private boolean isRed(Node x) {
  if (x == null) return false;
  return x.color == RED;
}

private Node rotateLeft(Node h) {
  Node x = h.right;
  h.right = x.left;
  x.left = h;
  x.color = h.color;
  h.color = RED;
  return x;
}

private Node rotateRight(Node h) {
  Node x = h.left;
  h.left = x.right;
  x.right = h;
  x.color = h.color;
  h.color = RED;
  return x;
}

private void flipColors(Node h) {
  h.color = RED;
  h.left.color = BLACK;
  h.right.color = BLACK;
}
```

same code handles all cases.

Right child red, left child black: rotate left

Left child, left-left grandchild red: rotate right

Both children red: flip colors

```java
private Node put(Node h, Key key, Val val) {
  if (h == null) return new Node(key, val, RED);
  int cmp = key.compareTo(h.key);
  if (cmp < 0) h.left = put(h.left, key, val);
  else if (cmp > 0) h.right = put(h.right, key, val);
  else h.val = val;

  if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
  if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
  if (isRed(h.left) && isRed(h.right)) flipColors(h);

  return h;
}
```


proposition: height of red-black BST with N nodes is at most 2 log N