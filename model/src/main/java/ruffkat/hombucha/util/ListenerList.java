package ruffkat.hombucha.util;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EventListener;

public class ListenerList<T extends EventListener>
        implements Serializable {
    private transient T[] listeners;

    public ListenerList(Class<T> type) {
        this((T[]) Array.newInstance(type, 0));
    }

    public ListenerList(T[] listeners) {
        this.listeners = listeners;
    }

    public T[] listeners() {
        return listeners;
    }

    public int count() {
        return listeners.length;
    }

    public synchronized boolean isEmpty() {
        return listeners.length == 0;
    }

    public synchronized void clear() {
        listeners = newArray(0);
    }

    public synchronized void add(T listener) {
        if (listener == null) {
            return;
        }
        if (listeners == null) {
            listeners = newArray(1);
            listeners[0] = listener;
        } else {
            T[] tmp = Arrays.copyOf(listeners, listeners.length + 1);
            tmp[listeners.length] = listener;
            listeners = tmp;
        }
    }

    public synchronized void remove(T listener) {
        if (listener == null) {
            return;
        }
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i].equals(listener)) {
                T[] tmp = newArray(listeners.length - 1);
                System.arraycopy(listeners, 0, tmp, 0, i);
                if (i < tmp.length) {
                    System.arraycopy(listeners, i + 1, tmp, i, tmp.length - i);
                }
                listeners = tmp;
                break;
            }

        }
    }

    private T[] newArray(int size) {
        return (T[]) Array.newInstance(listeners.getClass().getComponentType(), size);
    }
}
