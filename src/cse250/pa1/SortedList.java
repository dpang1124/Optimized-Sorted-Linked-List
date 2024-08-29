package cse250.pa1;

import cse250.pa1.LinkedListNode;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.Optional;

/**
 * A linked list that stores its elements in sorted order.
 * <p>
 * When an element is inserted into the list (or updated), it is positioned such that
 * <tt>node.next</tt> is the next greater value being stored in the list and <tt>node.prev</tt> is
 * the next lesser value being stored in the list. Duplicate values are stored in a single list
 * node, with the node also holding a count of the number of elements with that value.
 * <p>
 * SortedList also has "hinted" variants of several methods, where the caller may provide a
 * reference to a node that is close to the search term in the sorted list.  If this is actually the
 * case, the runtime of these methods, which is normally linear <i>in the size of the list</i> will
 * drop to linear in the number of records between the hint and the search term.
 *
 * @param <T> The type of value stored in the list
 */
public class SortedList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Reference to the first node in the list, or null if list is empty
     */
    public LinkedListNode<T> headNode;
    /**
     * Reference to the last node in the list, or null if list is empty
     */
    public LinkedListNode<T> lastNode;
    /**
     * Number of elements in the list (which may be different from number of nodes)
     */
    public int length;

    /**
     * Creates an empty SortedList
     */
    public SortedList() {
        this.headNode = null;
        this.lastNode = null;
        this.length = 0;
    }

    /**
     * Find a reference to the node holding elem, or the node that would precede it
     *
     * @param elem the element to find
     * @return the node containing the greatest element less than or equal to <tt>elem</tt>
     * <p>
     * If the list contains <tt>elem</tt>, this function should return the list node containing it
     * <p>
     * If the list does not contain <tt>elem</tt>, this function should return a reference to the
     * node that would precede <tt>elem</tt> if it were in the list, or <tt>Optional.empty()</tt>
     * if
     * <tt>elem</tt> is smaller than the smallest element in the list.
     * <p>
     * This function should run in O(length)
     */
    public Optional<LinkedListNode<T>> findRefBefore(T elem) {

        if(elem.compareTo(headNode.value)<0){
            return Optional.empty();
        }

        if(elem.compareTo(lastNode.value)>0 || elem.compareTo(lastNode.value)==0){
            return Optional.of(lastNode);
        }

        var start = headNode;
        while(elem.compareTo(start.value)>=0){
            start = start.next.get();
        }
        return start.prev;
    }

    /**
     * Find a reference to the node holding elem, or the node that would precede it
     *
     * @param elem the element to find
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return the node containing the greatest element less than or equal to <tt>elem</tt>
     * <p>
     * If the list contains <tt>elem</tt>, this function should return the list node containing it
     * <p>
     * If the list does not contain <tt>elem</tt>, this function should return a reference to the
     * node that would precede <tt>elem</tt> if it were in the list, or <tt>Optional.empty()</tt> if
     * <tt>elem</tt> is smaller than the smallest element in the list.
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public Optional<LinkedListNode<T>> findRefBefore(T elem, LinkedListNode<T> hint) {
        if(elem.compareTo(headNode.value)<0){
            return Optional.empty();
        }

        if(elem.compareTo(lastNode.value)>0 || elem.compareTo(lastNode.value)==0){
            return Optional.of(lastNode);
        }

        var start = hint;
        if(hint.value.compareTo(elem)>0){
            while(elem.compareTo(start.value)<0){
                start = start.prev.get();
            }
            return Optional.of(start);
        }
        else {
            while (elem.compareTo(start.value) >= 0) {
                start = start.next.get();
            }
        }
        return start.prev;
    }

    /**
     * Find a reference to the specified element
     *
     * @param elem the element to find
     * @return <tt>Optional.of(node)</tt> where <tt>node</tt> contains <tt>elem</tt>, or
     * <tt>Optional.empty()</tt> if <tt>elem</tt> is not in the list
     * <p>
     * This function should run in O(length)
     */
    public Optional<LinkedListNode<T>> findRef(T elem) {
        if(lastNode.value.compareTo(elem)==0){
            return Optional.of(lastNode);
        }
        var start = headNode;
        while(start!=lastNode){
            if(start.value.compareTo(elem)==0){
                return Optional.of(start);
            }
            start = start.next.get();
        }
        return Optional.empty();
    }

    /**
     * Find a reference to the specified element
     *
     * @param elem the element to find
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return <tt>Optional.of(node)</tt> where <tt>node</tt> contains <tt>elem</tt>, or
     * <tt>Optional.empty()</tt> if <tt>elem</tt> is not in the list
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public Optional<LinkedListNode<T>> findRef(T elem, LinkedListNode<T> hint) {
        if(lastNode.value.compareTo(elem)==0){
            return Optional.of(lastNode);
        }
        if(headNode.value.compareTo(elem)==0){
            return Optional.of(headNode);
        }
        var start = hint;
        if(start.value.compareTo(elem)>0){
            while(start != headNode){
                if(start.value.compareTo(elem)==0){
                    return Optional.of(start);
                }
                start = start.prev.get();
            }
        }
        else {
            while (start != lastNode) {
                if (start.value.compareTo(elem) == 0) {
                    return Optional.of(start);
                }
                start = start.next.get();
            }
        }
        return Optional.empty();
    }

    /**
     * Return the node holding the element at the specified index
     *
     * @param idx the index to look up
     * @return the node holding the element at position <tt>idk</tt>
     * @throws IndexOutOfBoundsException if <tt>idx</tt> < 0 or <tt>idx</tt> >= length
     *
     * Note that <tt>idx</tt> refers to the index of an element, not of a node.
     *
     * If the list changes, references to nodes who's values are unchanged should remain valid, even
     * if their index changes.
     *
     * This function should run in O(idx)
     */
    public LinkedListNode<T> getRef(int idx) throws IndexOutOfBoundsException {
        if(idx>=length || idx < 0){
            throw new IndexOutOfBoundsException();
        }
        var start = headNode;
        var count = 1;
        for(int i=0; i<idx; i++){
            if(count == start.count){
                count = 1;
                start = start.next.get();
            }
            else {
                count++;
            }
        }
        return start;
    }

    /**
     * Return the value at the specified index
     *
     * @param idx the index to look up
     * @return the value currently at index <tt>idx</tt>
     * @throws IndexOutOfBoundsException if <tt>idx</tt> < 0 or <tt>idx</tt> >= length
     *
     * Note that <tt>idx</tt> refers to the index of an element, not of a node.
     *
     * This function should run in O(idx)
     */
    public T get(int idx) throws IndexOutOfBoundsException {
        return getRef(idx).value;
    }

    /**
     * Insert a new value into the list
     *
     * @param elem the value to insert
     * @return a reference to the node holding the inserted value
     * <p>
     * The value should be placed so that the list remains in sorted order. After the insertion, the
     * insertion points <tt>next</tt> field should be a reference holding the next largest element,
     * and the <tt>prev</tt> field should be a reference to the next smallest element.
     * <p>
     * If <tt>elem</tt> is already in the list, the existing node should just have its
     * <tt>count</tt> field updated instead of creating a new node.
     * <p>
     * This function should run in O(length)
     */
    public LinkedListNode<T> insert(T elem) {
        if(length==0){
            var thing = new LinkedListNode<T>(elem, 1);
            headNode = thing;
            lastNode = thing;
            length += 1;
            return thing;
        }
       if(elem.compareTo(headNode.value)<0){
           var x = new LinkedListNode<T>(elem, 1);
           x.next = Optional.of(headNode);
           x.prev = Optional.empty();
           headNode.prev = Optional.of(x);
           headNode = x;
           length += 1;
           return x;
        }
        if(elem.compareTo(lastNode.value)>0){
            var y = new LinkedListNode<T>(elem, 1);
            y.prev = Optional.of(lastNode);
            y.next = Optional.empty();
            lastNode.next = Optional.of(y);
            lastNode = y;
            length += 1;
            return y;
        }
        var return1 = new LinkedListNode<T>(elem, 1);
        var start = headNode;
        while(true){
            if(elem.compareTo(start.value)==0){
                start.count += 1;
                length += 1;
                return start;
            }
            if(start == lastNode){
                break;
            }
            else if(elem.compareTo(start.value)>0 && elem.compareTo(start.next.get().value)<0){
                return1.next = start.next;
                return1.prev = Optional.of(start);
                return1.prev.get().next = Optional.of(return1);
                return1.next.get().prev = Optional.of(return1);
                length += 1;
                return start;
            }
            start = start.next.get();
        }

        return return1;
    }

    /**
     * Insert a new value into the list
     *
     * @param elem the value to insert
     * @param hint a reference to a node "close" to <tt>elem</tt>
     * @return a reference to the node holding the inserted value
     * <p>
     * The value should be placed so that the list remains in sorted order. After the insertion, the
     * insertion points <tt>next</tt> field should be a reference holding the next largest element,
     * and the <tt>prev</tt> field should be a reference to the next smallest element.
     * <p>
     * If <tt>elem</tt> is already in the list, the existing node should just have its
     * <tt>count</tt> field updated instead of creating a new node.
     * <p>
     * If <tt>hint</tt> is at position i, and <tt>elem</tt> would be at position j, then this
     * function should run in O(|i-j|)
     */
    public LinkedListNode<T> insert(T elem, LinkedListNode<T> hint) {
        if(length==0){
            var thing = new LinkedListNode<T>(elem, 1);
            headNode = thing;
            lastNode = thing;
            length += 1;
            return thing;
        }
        if(elem.compareTo(headNode.value)<0){
            var x = new LinkedListNode<T>(elem, 1);
            x.next = Optional.of(headNode);
            headNode.prev = Optional.of(x);
            headNode = x;
            length += 1;
            return x;
        }
        if(elem.compareTo(lastNode.value)>0){
            var y = new LinkedListNode<T>(elem, 1);
            y.prev = Optional.of(lastNode);
            lastNode.next = Optional.of(y);
            lastNode = y;
            length += 1;
            return y;
        }
        var return1 = new LinkedListNode<T>(elem, 1);
        var start = hint;
        while(true){
            if(elem.compareTo(start.value)==0){
                start.count += 1;
                length += 1;
                return start;
            }
            if(start == lastNode){
                break;
            }
            else if(elem.compareTo(start.value)>0 && elem.compareTo(start.next.get().value)<0){
                return1.next = start.next;
                return1.prev = Optional.of(start);
                start.next.get().prev = Optional.of(return1);
                start.next = Optional.of(return1);
                length += 1;
                return start;
            }
            start = start.next.get();
        }

        return return1;
    }

    /**
     * Remove one instance of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @return the removed value
     * <p>
     * If the list only contains a single instance of the value being removed, then the node should
     * be removed from the list as well. If the list contains multiple instances of the value, the
     * node itself should remain in the list.
     * <p>
     * This function should run in O(1)
     */
    public T remove(LinkedListNode<T> ref) {
        var value = ref.value;
        if(length==1){
            headNode = null;
            lastNode = null;
            ref.count-=1;
            length-=1;
            return value;
        }
        if(ref.count==1) {
            if(ref==headNode){
                    headNode = ref.next.get();
                    headNode.prev = Optional.empty();
                    ref.next = Optional.empty();

                    length -= 1;
                ref.count-=1;
                    return value;
                }
            if(ref==lastNode){
                lastNode = ref.prev.get();
                lastNode.next = Optional.empty();
                ref.prev = Optional.empty();
                length-=1;
                ref.count-=1;
                return value;
            }
            ref.prev.get().next = ref.next;
            ref.next.get().prev = ref.prev;
            ref.next = Optional.empty();
            ref.prev = Optional.empty();
            length-=1;
            ref.count-=1;
            return value;
        }
        else{
            ref.count = ref.count-1;
            length-=1;
            return value;
        }

    }

    /**
     * Remove n instances of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @param n the number of instances of the value to remove
     * @return the removed value
     * @throws IllegalArgumentException if <tt>n</tt> > <tt>ref.count</tt>
     *
     * If the node contains more than <tt>n</tt> instances of the value, then the node itself should
     * remain in the list.
     *
     * This function should run in O(1)
     */
    public T removeN(LinkedListNode<T> ref, int n) throws IllegalArgumentException {

        if(ref.count < n){
            throw new IllegalArgumentException();
        }
        var thing1 = ref.value;
        for(int i=0; i<n; i++){
            remove(ref);
        }
        return thing1;
    }

    /**
     * Remove all instances of the value held by <tt>ref</tt> from the list
     *
     * @param ref the node holding the value to remove (must be part of the list)
     * @return the removed value
     * <p>
     * This function should run in O(1)
     */
    public T removeAll(LinkedListNode<T> ref) {
        var thing1 = ref.value;
        var counter = ref.count;
        for(int i=0; i<counter; i++) {

            remove(ref);
        }
        return thing1;
    }

    /**
     * Modifies a single value presently in the list
     *
     * @param ref  a reference to the node with the value to be updated
     * @param elem the new value
     * @return a reference to the updated node
     * <p>
     * This code is provided for you.
     * <p>
     * If i is the position of ref before the update and j is the position of ref after the update,
     * then this function should run in O(|i-j|)
     */
    public LinkedListNode<T> update(LinkedListNode<T> ref, T elem) {
        LinkedListNode<T> ret = insert(elem, ref);
        remove(ref);
        return ret;
    }

    /**
     * Modifies a single value presently in the list
     *
     * @param idx  the index of the element to be updated
     * @param elem the new value
     * @return a reference to the updated node
     * <p>
     * This code is provided for you.
     * <p>
     * If i is the position of ref before the update and j is the position of ref after the update,
     * then this function should run in O(|i-j|)
     */
    public void update(int idx, T elem) {
        update(getRef(idx), elem);
    }

    /**
     * Return an iterator over the elements of the collection
     *
     * @return an iterator over the elements of the collection
     * <p>
     * The iterator should return elements in sorted order from least to greatest (according to the
     * types <tt>compareTo</tt> method).
     * <p>
     * The iterator's <tt>next</tt> and <tt>hasNext</tt> methods should both run in O(1)
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private LinkedListNode<T> current = headNode;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T ret = current.value;
                count++;
                if (count >= current.count) {
                    current = current.next.orElse(null);
                    count = 0;
                }
                return ret;
            }
        };
    }
}