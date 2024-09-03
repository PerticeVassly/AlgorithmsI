```
int mid = (low + high) / 2;//int mid = (low + high) >> 1;(in JDK 5 source code)
```

here if low and high is very big may cause the overflow bug(mid turns to negative)

a correct version is in JDK6

```
int mid = low + (high - low) / 2; //int mid = (low + high) >>> 1;(in JDK 6 source code)
```



