package edu.ncsu.csc316.dsa.map;

/**
 * The Map abstract data type represents a collection of entries, where each
 * entry consists of a 'key' that is mapped to a 'value'. The Map ADT does not
 * allow duplicate keys to exist within the map.
 * 
 * All Maps must be {@link Iterable} to allow clients to iterate using for-each
 * loops.
 * 
 * The Map interface is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public interface Map<K, V> extends Iterable<K> {

	/**
	 * Returns an Iterable collection of all of the entries stored within the map
	 * 
	 * @return an Iterable collection of all of the entries stored within the map
	 */
	Iterable<Entry<K, V>> entrySet();

	/**
	 * Returns the value that is associated with the given key
	 * 
	 * @param key the key of the entry for which to locate the associated value
	 * @return the value that is associated with the given key
	 */
	V get(K key);

	/**
	 * Returns true if the map contains no entries, otherwise returns false
	 * 
	 * @return true if the map contains no entries
	 */
	boolean isEmpty();

	/**
	 * Adds a new entry into the map that associates the provided key with the
	 * provided value. If an entry with the provided key already exists within the
	 * Map, the value associated with the existing key is replaced with the newly
	 * provided value
	 * 
	 * @param key   the key of the entry
	 * @param value the value of the entry
	 * @return null if an entry with the provided key did not already exist;
	 *         otherwise, return the original value that was replaced with the newly
	 *         provided value
	 */
	V put(K key, V value);

	/**
	 * Removes the entry with the provided key from the map and returns the value of
	 * that entry
	 * 
	 * @param key the key of the entry to remove from the map
	 * @return the value of the entry that was removed from the map, or null if an
	 *         entry with the provided key did not exist within the map
	 */
	V remove(K key);

	/**
	 * The number of entries stored in the map
	 * 
	 * @return the number of entries stored in the map
	 */
	int size();

	/**
	 * Returns an iterable collection of the values of all of the entries stored
	 * within the map
	 * 
	 * @return an iterable collection of the values of all of the entries stored
	 *         within the map
	 */
	Iterable<V> values();

	/**
	 * The Entry abstract data type defines behaviors for retrieving key and value
	 * data that are stored within the entry
	 * 
	 * @author Dr. King
	 *
	 * @param <K> the type of key stored in the entry
	 * @param <V> the type of value stored in the entry
	 */
	interface Entry<K, V> extends Comparable<Entry<K, V>> {
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