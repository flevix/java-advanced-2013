package ru.ifmo.ctddev.nechaev.task2;

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 21.03.13
 * Time: 11:01
 */

public abstract class AbstractBag<T> extends AbstractCollection<T> {

    protected int size;
    protected final HashMap<Integer, Entry<T>> map;
    protected final HashMap<T, HashSet<Integer>> idMap;
    protected int lastId;
    protected int revision;

    public AbstractBag(HashMap<Integer, Entry<T>> map) {
        this.map = map;
        this.idMap = new HashMap();
        this.lastId = -1;
    }

    public abstract Iterator<T> iterator();

    public boolean add(T element) {
        Entry<T> currentEntry = new Entry(element, lastId + 1);

        HashSet<Integer> curSet = idMap.get(currentEntry.data);
        if (curSet == null) {
            curSet = new HashSet();
            idMap.put(currentEntry.data, curSet);
        }
        curSet.add(currentEntry.id);

        map.put(currentEntry.id, currentEntry);

        lastId = currentEntry.id;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (contains(o)) {
            int curId = idMap.get(o).iterator().next();
            Entry<T> curEntry = map.get(curId);

            revision++;
            HashSet<Integer> curSet = idMap.get(curEntry.data);
            if (curSet.size() == 1) {
                idMap.remove(curEntry.data);
            } else {
                curSet.remove(curEntry.id);
            }

            map.remove(curEntry.id);

            size--;

            return true;
        } else {
            return false;
        }
    }

    public boolean contains(Object element) {
        return idMap.get(element) != null && idMap.get(element).size() > 0;
    }

    public void clear() {
        revision++;

        map.clear();
        idMap.clear();
        lastId = -1;
        size = 0;
    }

    public int size() {
        return size;
    }
}
