package ru.ifmo.ctddev.nechaev.task2;/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 21.03.13
 * Time: 11:02
 */

public class Entry<T> {
    public final T data;
    public final Integer id;

    Entry(T data, Integer id) {
        this.data = data;
        this.id = id;
    }

    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof Entry))
            return false;
        Entry entry = (Entry) other;
        return data.equals(entry.data) && id.equals(entry.id);
    }

    public int hashCode() {
        return data.hashCode() * id.hashCode();
    }
}
