package ru.ifmo.collections;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents sorted set of unique values.
 * create() returns a SortedSet instance with natural ordering. (i.e. from smallest to largest in case of integer numbers)
 * from() is used to create a SortedSet instance with given Comparator.
 * Instances of a class can be created using only these two methods above.
 * <p>
 * This class should not be abstract and should be capable of adding/removing single/multiple elements.
 * It has two more methods: getSorted() and getReversed() which return an array of set contents in forward and reverse order respectively.
 * <p>
 * NB! This class must have only map(s) as an internal data structure(s).
 *
 * @param <T> set contents type
 */
public final class SortedSet<T> extends AbstractSet<T> {
    private final Map<T, Integer> contents;

    public static <T> SortedSet<T> create() {
        return from(null);
    }

    public static <T> SortedSet<T> from(Comparator<T> comparator) {
        return new SortedSet<>(comparator);
    }

    private SortedSet(Comparator<T> comparator) {
        contents = new TreeMap<>(comparator);
    }

    public List<T> getSorted() {
        return new ArrayList<>(contents.keySet());
    }

    public List<T> getReversed() {
        List<T> list = getSorted();
        list.sort(Collections.reverseOrder());
        return list;
    }

    @Override
    public boolean add(T tempItem) {
        boolean isInContents = contents.containsKey(tempItem);
        if (!isInContents) {
            contents.put(tempItem, 1);
        }
        return isInContents;
    }

    @Override
    public boolean addAll(Collection<? extends T> collectionOfItems) {
        boolean hasChanged = false;
        for (var item : collectionOfItems) {
            if (add(item)) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    @Override
    public boolean remove(Object objectToRemove) {
        boolean isInContents = contents.containsKey(objectToRemove);
        if (isInContents) {
            contents.remove(objectToRemove);
        }
        return isInContents;
    }

    @Override
    public boolean removeAll(Collection<?> collectionOfItems) {
        boolean hasChanged = false;
        for (var item : collectionOfItems) {
            if (remove(item)) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public boolean contains(Object objectToFind) {
        return contents.containsKey(objectToFind);
    }

    @Override
    public boolean containsAll(Collection<?> collectionOfItems) {
        return contents.keySet().containsAll(collectionOfItems);
    }

    @Override
    public Iterator<T> iterator() {
        return contents.keySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        contents.keySet().forEach(action);
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return contents.keySet().toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return contents.keySet().removeIf(filter);
    }

    @Override
    public Spliterator<T> spliterator() {
        return contents.keySet().spliterator();
    }

    @Override
    public Stream<T> stream() {
        return contents.keySet().stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return contents.keySet().parallelStream();
    }

    @Override
    public int size() {
        return contents.size();
    }
}