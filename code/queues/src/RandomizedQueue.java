/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int usedSize;
    private int ptr;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        usedSize = 0;
        ptr = 0; // point to the next available position
    }

    // if ptr == nodes.length, arrange the nodes
    private void arrange() {
        if (usedSize == 0) {
            return;
        }
        Item[] newItems = (Item[]) new Object[findN(ptr)];
        int j = 0;
        for (int i = 0; i < ptr; i++) {
            if (items[i] != null) {
                newItems[j] = items[i];
                j++;
            }
        }
        items = newItems;
        ptr = usedSize;
    }

    private int findN(int n) {
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16; // 整型一般是 32 位，上面我是假设 8 位。
        return (n + 1) >> 1;
    }


    // it means after the arrange the ptr still equals to the nodes.length, which means it need to resize
    private void expand() {
        Item[] newItems = (Item[]) new Object[items.length * 2];
        int j = 0;
        for (int i = 0; i < ptr; i++) {
            if (items[i] != null) {
                newItems[j] = items[i];
                j++;
            }
        }
        items = newItems;
    }

    // it means after the arrange the total size is less than the nodes.length / 4, which means it need to shrink
    private void shrink() {
        Item[] newItems = (Item[]) new Object[items.length / 2];
        int j = 0;
        for (int i = 0; i < ptr; i++) {
            if (items[i] != null) {
                newItems[j] = items[i];
                j++;
            }
        }
        items = newItems;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return usedSize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return usedSize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (ptr == items.length) {
            arrange();
            if (ptr == items.length) {
                expand();
            }
        }
        items[ptr] = item;
        usedSize++;
        ptr++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(ptr);
        int indexToDequeue = findClosedUsedIndex(randomIndex);
        Item item = items[indexToDequeue];
        items[indexToDequeue] = null;
        usedSize--;
        if ((usedSize <= items.length / 4) && usedSize > 0) {
            arrange();
            if ((usedSize <= items.length / 4) && usedSize > 0) {
                shrink();
            }
        }
        return item;
    }

    private int findClosedUsedIndex(int begin) {
        if (usedSize == 0) {
            return -1;
        }
        int index = begin;
        for (int i = 0; i < ptr; i++) {
            if (items[index] != null) {
                return index;
            }
            index = (index + 1) % ptr;
        }
        return -1;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(ptr);
        int indexTodequeue = findClosedUsedIndex(randomIndex);
        return items[indexTodequeue];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private int[] randomIndex;

        public RandomizedQueueIterator() {
            current = 0;
            randomIndex = new int[usedSize];
            int j = 0;
            for (int i = 0; i < ptr; i++) {
                if (items[i] != null) {
                    randomIndex[j] = i;
                    j++;
                }
            }
            StdRandom.shuffle(randomIndex);
        }

        public boolean hasNext() {
            return current < randomIndex.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return items[randomIndex[current++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);
        rq.enqueue(10);
        rq.enqueue(11);
        rq.enqueue(12);
        rq.enqueue(13);
        rq.enqueue(14);
        rq.enqueue(15);
        rq.enqueue(16);
        rq.enqueue(17);
        rq.enqueue(18);
        rq.enqueue(19);
        rq.enqueue(20);
        rq.enqueue(21);
        rq.enqueue(22);
        rq.enqueue(23);
        System.out.println(rq.size());
        System.out.println(rq.isEmpty());
        System.out.println(rq.sample());
        for (int i : rq) {
            System.out.println(i);
        }

    }
}
