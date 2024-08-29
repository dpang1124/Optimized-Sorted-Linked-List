package cse250.pa1.tests;
import cse250.pa1.LinkedListNode;
import cse250.pa1.SortedList;

import org.junit.Test;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SortedListTests {

    private static final Random SeededRandom = new Random(250);

    @Test
    public  void testfinal(){
        var thing1 = new SortedList<Integer>();
        thing1.insert(-1);
        thing1.insert(-1);
        thing1.insert(3);
        thing1.insert(5);
        thing1.insert(3);
        thing1.insert(4);
        thing1.insert(18);
        thing1.insert(18);

         assertEquals(thing1.headNode.count, 2);
         assertEquals(thing1.lastNode.count, 2);


         assertEquals(thing1.get(0), Integer.valueOf(-1));
        assertEquals(thing1.get(3), Integer.valueOf(3));
        assertEquals(thing1.get(7), Integer.valueOf(18));

        var store = thing1.lastNode;
        thing1.removeAll(thing1.lastNode);
        assertEquals(store.count, 0I);

    }

    @Test

    public void testone(){
        //create the list
        var thing1 = new SortedList<Integer>();

        thing1.insert(-1);
        thing1.insert(3);
        thing1.insert(5);
        thing1.insert(3);
        thing1.insert(4);
        thing1.insert(18);

       /* var start = thing1.headNode;
        System.out.println("length: " + thing1.length);
        while(true){
            System.out.println(start.value + ", ");
            if(start == thing1.lastNode){
                break;
            }
            start = start.next.get();
        }
        */
        //nice lil print function above just in case i need it

        assertEquals(thing1.headNode.value, Integer.valueOf(-1));
        assertEquals(thing1.lastNode.value, Integer.valueOf(18));

        //defining search functions
        assertEquals(thing1.headNode.value, Integer.valueOf(-1));
        assertEquals(thing1.lastNode.value, Integer.valueOf(18));
        assertEquals(thing1.length, 6);

        assertEquals(thing1.findRefBefore(2).get(), thing1.headNode);
        assertEquals(thing1.findRefBefore(4).get(), thing1.headNode.next.get().next.get());
        //assertEquals(thing1.findRefBefore(5, thing1.headNode.next.get().next.get()), thing1.headNode.next.get().next.get().next.get());
        //assertEquals(thing1.findRefBefore(-3).get(), Optional.empty().get());

        //defining indexing functions
        assertEquals(thing1.getRef(4), thing1.headNode.next.get().next.get().next.get());
        assertEquals(thing1.getRef(0), thing1.headNode);
        assertEquals(thing1.get(0), thing1.headNode.value);
        assertEquals(thing1.get(3), thing1.headNode.next.get().next.get().value);



        assertThrows(IndexOutOfBoundsException.class, () -> {thing1.getRef(-3);});

        //removal functions
        var store1 = thing1.headNode.next.get();
        thing1.remove(thing1.headNode); //removes -1

        assertEquals(thing1.headNode, thing1.getRef(0));
        assertEquals(thing1.headNode, store1);

        var store2 = thing1.headNode.next.get();
        thing1.removeN(store2, 1); //removes 4
        assertEquals(thing1.length, 4);



        var store3 = thing1.headNode.next.get();
        thing1.removeAll(thing1.headNode); //removes all 3s
        assertEquals(thing1.headNode, thing1.headNode);
        assertEquals(thing1.headNode, store3);





}

@Test
public void testextra(){
        var thing1 = new SortedList<Integer>();

        assertEquals(thing1.headNode, null);
        thing1.insert(3);

        assertEquals(thing1.headNode.value, Integer.valueOf(3));
        assertEquals(thing1.lastNode.value, Integer.valueOf(3));
        thing1.remove(thing1.headNode);
    assertEquals(thing1.headNode, null);
    assertEquals(thing1.lastNode, null);
}
@Test
public void testfour(){
        var thing1 = new SortedList<Integer>();
        thing1.insert(0);
    thing1.insert(3);
    thing1.insert(3);
    thing1.insert(6);
    thing1.insert(7);
    thing1.insert(21);
    thing1.insert(21);
    thing1.insert(21);

    var hint1 = thing1.headNode.next.get().next.get();
    assertEquals(thing1.findRefBefore(4, hint1), thing1.headNode.next);
    assertEquals(thing1.findRefBefore(6, hint1), thing1.headNode.next.get().next);
    assertEquals(thing1.findRefBefore(21, hint1), Optional.of(thing1.lastNode));

    thing1.remove(thing1.lastNode);
    thing1.removeAll(thing1.lastNode);
    assertEquals(thing1.lastNode.value, Integer.valueOf(7));
    thing1.remove(thing1.lastNode);
    assertEquals(thing1.lastNode.value, Integer.valueOf(6));


}
@Test
public void testthree(){
        var list = new SortedList<Integer>();
        list.insert(3);
        list.insert(0);
        list.insert(0);
        list.insert(1);
        list.insert(-3);
        list.insert(10);
        list.insert(10);
        //-3, 0, 1, 3, 10
          var start = list.headNode;
        System.out.println("length: " + list.length);
        while(true){
            System.out.println(start.value + ", ");
            if(start == list.lastNode){
                break;
            }
            start = start.next.get();
        }


    assertEquals(list.get(0), Integer.valueOf(-3));
    assertEquals(list.get(1), Integer.valueOf(0));
    assertEquals(list.get(4), Integer.valueOf(3));

    //hint functions
    var hint1 = list.headNode.next.get();
    assertEquals(list.findRefBefore(4, hint1), hint1.next.get().next);
    assertEquals(list.findRef(3, hint1), list.headNode.next.get().next.get().next);
    assertEquals(list.findRef(3, list.headNode), list.headNode.next.get().next.get().next);
    /*
      start = list.headNode;
        System.out.println("length: " + list.length);
        while(true){
            System.out.println(start.value + ", ");
            if(start == list.lastNode){
                break;
            }
            start = start.next.get();
        }*/

    var store2 = list.lastNode.prev;
    list.removeAll(list.lastNode);
    assertEquals(list.lastNode, store2.get());
    list.removeN(list.headNode, 0);
    assertEquals(list.headNode.value, Integer.valueOf(-3));
    list.remove(list.headNode.next.get());
    assertEquals(list.headNode.next.get().value, Integer.valueOf(0));

    assertEquals(list.lastNode.next, Optional.empty());
    assertEquals(list.headNode.prev, Optional.empty());

    }


@Test
public void testtwo(){
    var thing1 = new SortedList<Integer>();
    thing1.insert(3);
    thing1.insert(3);
    thing1.insert(3);
    thing1.insert(4);
    thing1.insert(5);
    thing1.insert(7);
    thing1.insert(0);


    //0-3-4-5-7
    assertEquals(thing1.headNode.next.get().count, 3);
    thing1.removeN(thing1.headNode.next.get(), 2);
    assertEquals(thing1.headNode.next.get().count, 1);

    thing1.removeAll(thing1.headNode.next.get());

    assertEquals(thing1.headNode.next.get(), thing1.headNode.next.get());
    assertEquals(thing1.headNode.next.get().prev.get(), thing1.headNode);
    assertEquals(thing1.lastNode.value, Integer.valueOf(7));
    assertEquals(thing1.lastNode.prev.get(), thing1.headNode.next.get().next.get());

    thing1.removeAll(thing1.lastNode);
    assertEquals(thing1.lastNode, thing1.lastNode);
    assertEquals(thing1.lastNode.value, Integer.valueOf(5));



}


    public void testInsertReverseOrderElementsInOrder() {
        SortedList<Integer> list = new SortedList<>();

        for (int i = 9; i >= 0; i--) {
            list.insert(i);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    @Test
    public void testInsertInOrderElementsInOrder() {
        SortedList<Integer> list = new SortedList<>();

        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    @Test
    public void testInsertRandomOrderElementsInOrder() {
        SortedList<Integer> list = new SortedList<>();

        List<Integer> numbers = shuffledRange(0, 30);
        for (Integer num : numbers) {
            list.insert(num);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }

        List<Integer> randomIndexes = shuffledRange(0, 30).subList(0, 10);
        for (Integer i : randomIndexes) {
            assertEquals(Integer.valueOf(i), list.get(i));
        }
    }

    @Test
    public void testInsertInOrderElementsWithHints() {
        SortedList<Integer> list = new SortedList<>();

        LinkedListNode<Integer> tail = list.insert(0);
        for (int i = 1; i < 100; i++) {
            tail = list.insert(i, tail);
        }

        int index = 0;
        for (Integer elem : list) {
            assertEquals(Integer.valueOf(index), elem);
            index++;
        }
    }

    private List<Integer> shuffledRange(int start, int end) {
        List<Integer> numbers = IntStream.range(start, end).boxed().collect(Collectors.toList());
        java.util.Collections.shuffle(numbers, SeededRandom);
        return numbers;
    }
}