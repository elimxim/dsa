# Ring Buffer

Given class `RingBuffer`:

```java
public class RingBuffer {
    private final Object[] data;

    public RingBuffer(int size) {
        data = new Object[size];
    }

    public void put(Object o) {
    }

    public Object get() {
    }
}
```

**Requirements**

- Ring buffer, i.e. space consumption does not increase up to a certain value (buffer size).
- FIFO method, which means "first in, first out", i.e. the first data recorded will be the first data received.
- Throw exceptions in corner cases: no free space (on put) or data (on get) e.g.
- Handling `null`-values will be a plus
- `size()` method implementation will be a plus
- Thread-safe implementation will be a plus

**Limitations**

- Changing method signatures is prohibited

Operating principle:

| command                    | buffer       |
|----------------------------|--------------|
| new buffer `RingBuffer(3)` | [ ][ ][ ]    |
| invoke `get()`             | -> exception | 
| invoke `put(1)`            | \[1][ ][ ]   |
| invoke `put(2)`            | \[1]\[2][ ]  |
| invoke `put(3)`            | \[1]\[2]\[3] |
| invoke `put(4)`            | -> exception |
| invoke `get()`             | [ ]\[2]\[3]  |
| invoke `put(4)`            | \[4]\[2]\[3] |
| invoke `get()`             | \[4][ ]\[3]  |
| invoke `get()`             | \[4][ ][ ]   |

## Task description

Implement `RingBuffer` class to meet requirements using **TDD** method.

Time: 1h 30m  
Procedure:
- Implement the FIFO `RingBuffer`
- Implement the reception of `null`-values
- Implement the `size()` method
- Implement a thread-safe version