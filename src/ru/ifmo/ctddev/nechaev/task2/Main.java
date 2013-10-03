package ru.ifmo.ctddev.nechaev.task2;
/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 20.03.13
 * Time: 21:50
 */

import java.util.HashMap;
import java.util.Iterator;

import static java.util.Arrays.asList;

public class Main {

  public static void main(String[] arg) {
      AbstractBag<Integer> linkedBag = new LinkedBag<Integer>();
      AbstractBag<Integer> bag = new Bag<Integer>();

      linkedBag.addAll(asList(1, 2, 3, null, 6, 5, 2, 4, null));
      bag.addAll(asList(1, 2, 3, null, 6, 5, 2, 4, null));

      System.out.println(linkedBag.toString());
      System.out.println(bag.toString());

      linkedBag.remove(2);
      bag.remove(2);
      linkedBag.remove(2);
      bag.remove(2);

      Iterator<Integer> itLinkedBag = linkedBag.iterator();
      Iterator<Integer> itBag = bag.iterator();

      while (itLinkedBag.hasNext()) {
          System.out.print(itLinkedBag.next() + " ");
      }
      System.out.println();
      while (itBag.hasNext()) {
          System.out.print(itBag.next() + " ");
          itBag.remove();
      }

      ArrayList<Integer> q;
  }
  public class ArrayList<E> {

    }
}
