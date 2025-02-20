package utils;

public interface InventoryIterator<T> {
    boolean hasNext();
    T next();
}