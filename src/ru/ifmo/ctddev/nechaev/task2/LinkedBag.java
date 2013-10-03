package ru.ifmo.ctddev.nechaev.task2;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 20.03.13
 * Time: 17:32
 */

public class LinkedBag<T> extends AbstractBag<T> {
    public LinkedBag() {
        super(new LinkedHashMap<Integer, Entry<T>> ());
    }

    public LinkedBag(Collection<? extends T> c) {
        super(new LinkedHashMap<Integer, Entry<T>> ());
        addAll(c);
    }

    public Iterator<T> iterator() {
        return new LinkedBagIterator();
    }

    class LinkedBagIterator implements Iterator<T> {
        private Iterator<Map.Entry<Integer, Entry<T>>> it;
        private Entry<T> curEntry;
        private int goodRevision = revision;


        public LinkedBagIterator() {
            it = map.entrySet().iterator();
        }

        public T next() {
            if (revision != goodRevision)
                throw new ConcurrentModificationException();

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            curEntry = it.next().getValue();

            return curEntry.data;
        }

        public boolean hasNext() {
            if (revision != goodRevision) {
                throw new ConcurrentModificationException();
            }
            return it.hasNext();
        }

        public void remove() {
            if (revision != goodRevision) {
                throw new ConcurrentModificationException();
            }
            it.remove();

            Set<Integer> set = idMap.get(curEntry.data);
            set.remove(curEntry.id);
            if (set.isEmpty()) {
                idMap.remove(curEntry.data);
            }

            revision++;
            size--;

            goodRevision = revision;
        }
    }

}
