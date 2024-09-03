/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node next;
        private Node pre;

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.pre = null;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Item getItem() {
            return item;
        }
    }

    private int size;
    private Node greatHead;
    private Node greatTail;

    // construct an empty deque
    public Deque() {
        greatHead = new Node(null);
        greatTail = new Node(null);
        greatHead.next = greatTail;
        greatTail.pre = greatHead;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        newNode.setNext(greatHead.next);
        newNode.setPre(greatHead);
        greatHead.next.setPre(newNode);
        greatHead.setNext(newNode);
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        newNode.setNext(greatTail);
        newNode.setPre(greatTail.pre);
        greatTail.pre.setNext(newNode);
        greatTail.setPre(newNode);
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node toRemove = greatHead.next;
        greatHead.setNext(toRemove.next);
        toRemove.next.setPre(greatHead);
        size--;
        return toRemove.getItem();
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node toRemove = greatTail.pre;
        greatTail.setPre(toRemove.pre);
        toRemove.pre.setNext(greatTail);
        size--;
        return toRemove.getItem();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = greatHead.next;

        public boolean hasNext() {
            return current != greatTail;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.getItem();
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeFirst();
        deque.addLast(2);
        deque.removeLast();
        deque.addFirst(3);
        deque.addLast(4);
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        for (int i : deque) {
            System.out.println(i); // 3 4
        }
    }

}