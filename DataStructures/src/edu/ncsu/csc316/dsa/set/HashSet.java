package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The HashSet is implemented as a linear probing hash table data structure to
 * support efficient set abstract data type behaviors.
 * 
 * Using the linear probing hash table ensures expected runtime of O(1) for
 * {@link Set#add}, {@link Set#remove}, and {@link Set#contains}; O(n) expected
 * runtime for {@link Set#addAll}, {@link Set#removeAll}, and
 * {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size} and
 * {@link Set#isEmpty}.
 * 
 * The HashSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the type of elements stored in the set
 */
public class HashSet<E> extends AbstractSet<E> {
    // Since our hash map uses linear probing, the entries are not ordered.
    // As a result, we do not restrict our hash set to use Comparable elements.
    // This also gives you an option if you need a set to manage elements
    // that are *NOT* Comparable (versus a TreeSet)
    
	/**
	 * Map to be used to keep track of our sets
	 */
    private Map<E, E> map;

    /**
     * Constructs a new HashSet
     */
    public HashSet() {
        // This constructor will use our "production version" of our hash map
        // meaning random values for alpha and beta will be used
        this(false);
    }

    /**
     * Constructs a new HashSet to be used ONLY when testing the data structure
     * @param isTesting flag to say whether or not the object should becreated with testing in mind (not random)
     */
    public HashSet(boolean isTesting) {
        // If isTesting is true, this constructor will use our "development version" of
        // our hash map
        // meaning alpha=1, beta=1, and prime=7
        map = new LinearProbingHashMap<E, E>(isTesting);
    }

    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public void add(E value) {
        map.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return map.get(value) != null;
    }

    @Override
    public E remove(E value) {
        return map.remove(value);
    }

    @Override
    public int size() {
        return map.size();
    }
}