package utils;

import java.util.List;

public class ListInventoryIterator<T> implements InventoryIterator<T> {
    private final List<T> items;
    private int position = 0;

    public ListInventoryIterator(List<T> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public T next() {
        return items.get(position++);
    }
}