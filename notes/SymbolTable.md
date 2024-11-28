```JAVA 
public class ST<Key, Value> {
    ST() // create a symbol table
    void put(Key key, Value val) // put key-value pair into the table
    Value get(Key key) // value paired with key
    void delete(Key key) // remove key (and its value) from table
    boolean contains(Key key) // is there a value paired with key?
    boolean isEmpty() // is the table empty?
    int size() // number of key-value pairs
    Iterable<Key> keys() // all the keys in the table
}
```

values are not null.
get() returns null if key not present.
put() overwrites old value with new value.

# equal

* reflexive: x.equals(x) is always true
* symmetric: if x.equals(y) is true, then y.equals(x) is true
* transitive: if x.equals(y) and y.equals(z) are true, then x.equals(z) is true
* consistent: multiple invocations of x.equals(y) consistently return true or consistently return false
* x.equals(null) is always false

```java
public boolean equals(Object x) {
    if (this == x) return true;
    if (x == null) return false;
    if (this.getClass() != x.getClass()) return false;
    Item that = (Item) x;
    return this.key.equals(that.key) && this.val.equals(that.val);
}
```