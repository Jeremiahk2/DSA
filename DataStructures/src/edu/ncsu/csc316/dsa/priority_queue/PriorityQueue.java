package edu.ncsu.csc316.dsa.priority_queue;

/**
 * The Priority Queue abstract data type represents a collection of entries,
 * where each entry consists of a 'key' that is mapped to a 'value'. The 'key'
 * represents and entry's priority. The Priority Queue ADT does allow duplicate
 * keys to exist.
 * 
 * The Priority Queue interface is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <K> the type of keys stored in the priority queue
 * @param <V> the type of values that are associated with keys in the priority
 *            queue
 */
public interface PriorityQueue<K, V> {

    /**
     * Inserts a new entry into the priority queue with the given key (priority) and
     * value
     * 
     * @param key   the key (priority) of the inserted entry
     * @param value the value of the inserted entry
     * @return a reference to the priority queue entry that was inserted
     */
    Entry<K, V> insert(K key, V value);

    /**
     * Returns (but does not remove) the entry with the lowest priority in the
     * priority queue.
     * 
     * @return the entry with the lowest priority in the priority queue
     */
    Entry<K, V> min();

    /**
     * Removes and returns the entry with the lowest priority in the priority queue.
     * 
     * @return the entry with the lowest priority in the priority queue
     */
    Entry<K, V> deleteMin();

    /**
     * Returns the number of entries stored within the priority queue
     * 
     * @return the number of entries stored within the priority queue
     */
    int size();

    /**
     * Returns true if there are no entries stored in the priority queue
     * 
     * @return true if there are no entries stored within the priority queue;
     *         otherwise, return false
     */
    boolean isEmpty();

    /**
     * The Entry abstract data type defines behaviors for retrieving key and value
     * data that are stored within the priority queue entry
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the priority queue entry
     * @param <V> the type of value stored in the priority queue entry
     */
    interface Entry<K, V> {
        /**
         * Return the key of the entry
         * 
         * @return the key of the entry
         */
        K getKey();

        /**
         * Return the value of the entry
         * 
         * @return the value of the entry
         */
        V getValue();
    }
}