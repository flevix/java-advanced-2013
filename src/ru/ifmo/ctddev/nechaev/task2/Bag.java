package ru.ifmo.ctddev.nechaev.task2;


import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 20.03.13
 * Time: 17:32
 */

public class Bag<T> extends AbstractBag<T> {
    public Bag() {
        super(new HashMap<Integer, Entry<T>>());
    }

    public Bag(Collection<? extends T> c) {
        super(new HashMap<Integer, Entry<T>> ());
        addAll(c);
    }

    public Iterator<T> iterator() {
        return new BagIterator();
    }

    class BagIterator implements Iterator<T> {
        private Iterator<Map.Entry<T, HashSet<Integer>>> setIterator;
        private Iterator<Integer> intIterator;
        private Map.Entry<T, HashSet<Integer>> curMapEntry;
        private Entry<T> curEntry;
        private int goodRevision = revision;

        public BagIterator() {
            setIterator = idMap.entrySet().iterator();
        }

        public T next() {
            if (revision != goodRevision)
                throw new ConcurrentModificationException();

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (intIterator == null || !intIterator.hasNext()) {
                curMapEntry = setIterator.next();
                intIterator = curMapEntry.getValue().iterator();
            }

            Integer id = intIterator.next();

            curEntry = map.get(id);

            return curEntry.data;
        }

        public boolean hasNext() {
            if (revision != goodRevision)
                throw new ConcurrentModificationException();

            if (intIterator == null || !intIterator.hasNext()) {
                return setIterator.hasNext();
            } else {
                return true;
            }
        }

        public void remove() {
            if (revision != goodRevision)
                throw new ConcurrentModificationException();

            intIterator.remove();
            if (curMapEntry.getValue().isEmpty()) { // Поддерживаем пустоту вспомогательных списков
                setIterator.remove();
            }

            map.remove(curEntry.id);

            revision++;
            size--;

            goodRevision = revision;
        }
    }
}
