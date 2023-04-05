package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type. Specifically, this abstraction allows the underlying data
 * structure to store entries in a sorted order based on the keys to help
 * improve the efficiency of lookUp behaviors.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractOrderedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	/** the Comparator for this ordered map. Compares keys (K) */
    private Comparator<K> compare;

    /**
     * Constructs a new AbstractOrderedMap using a custom comparator
     * 
     * @param compare the custom Comparator to use when comparing keys
     */
    public AbstractOrderedMap(Comparator<K> compare) {
        if (compare == null) {
            this.compare = new NaturalOrder();
        } else {
            this.compare = compare;
        }
    }

    /**
     * Compare keys to determine their relative ordering. Return a negative number
     * to indicate key1 should appear before key2. Return a positive number to
     * indicate key1 should appear after key2. Return 0 to indicate key1 and key2
     * are considered equal.
     * 
     * @param key1 a key to compare
     * @param key2 a key to compare
     * @return a number to indicate the relative ordering of key1 and key2: a
     *         negative number indicates key1 should appear before key2; a positive
     *         number indicates key1 should appear after key2; 0 indicates the two
     *         keys are considered equal
     */
    public int compare(K key1, K key2) {
        return compare.compare(key1, key2);
    }

    /**
     * Delegates to the {@link Comparable#compareTo} implementation that defines the
     * natural ordering of the keys
     * 
     * @author Dr. King
     *
     */
    private class NaturalOrder implements Comparator<K> {
        public int compare(K first, K second) {
            return ((Comparable<K>) first).compareTo(second);
        }
    }
}