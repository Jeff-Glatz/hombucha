package ruffkat.swing.module;

public interface Module<T> {
    void initialize() throws Exception;
    T getService();
    void release();
}
