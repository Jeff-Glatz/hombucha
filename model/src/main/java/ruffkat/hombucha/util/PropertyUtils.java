package ruffkat.hombucha.util;

public final class PropertyUtils {
    public static boolean changed(Object a, Object b) {
        return !(a != null ? !a.equals(b) : b != null);
    }
}
