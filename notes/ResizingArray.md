
```
public void push(String item) {
  if (N == s.length) resize(2*s.length);
  s[N++] = item;
}

private void resize(int capacity) {
  String copy = new String[capacity];
  for (int i = 0 ; i < N; i++) {
    copy[i] = s[i];
  }
  s = copy;
}
```

对于pop的情况，并不能简单的也是为1/2的时候缩短，因为对于一些push pop push pop..(thrashing)的情况，array会不断的resizing和shrink，造成大量的性能损失

因此对于shrink，只有当1/4的情况下，才会进行shrink

```
public String pop() { 
  String item = s[--N];
  s[N] = null;
  if (N > 0 && N = s.length / 4) resize(s.length / 2);
  return item;
}
```
