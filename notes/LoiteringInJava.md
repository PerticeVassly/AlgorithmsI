```
public Item pop() {
  Item item = a[--N];
  a[N] = null;
  ...
  return
}
```

在Java中垃圾回收策略是回收所有无法被访问的对象，如果不指定a[N]被弹出的元素是null，则垃圾回收器不会将其回收。
