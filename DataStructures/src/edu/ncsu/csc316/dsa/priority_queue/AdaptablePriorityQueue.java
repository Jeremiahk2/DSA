package edu.ncsu.csc316.dsa.priority_queue;

/**
 * The Adaptable Priority Queue abstract data type extends the behaviors of the
 * Priority Queue abstract data type to allow removing arbitrary entries,
 * updating an arbitrary entry's key (priority), and updating an arbitrary
 * entry's value.
 * 
 * The Adaptable Priority Queue interface is based on the implementation
 * developed for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <K> the type of keys stored in the adaptable priority queue
 * @param <V> the type of values that are associated with keys in the adaptable
 *            priority queue
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

    /**
     * Removes the provided entry
     * 
     * @param entry the entry to remove
     */
    void remove(Entry<K, V> entry);

    /**
     * Replaces the key (priority) of the provided entry
     * 
     * @param entry the entry for which to update the key (priority)
     * @param key   the new key (priority) of the entry
     */
    void replaceKey(Entry<K, V> entry, K key);

    /**
     * Replaces the value of the provided entry
     * 
     * @param entry the entry for which to update the value
     * @param value the new value of the entry
     */
    void replaceValue(Entry<K, V> entry, V value);
}