package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;
//import edu.ncsu.csc316.dsa.map.AbstractMap.EntryCollection;
//import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/**
	 * The table to be used for hashing
	 */
    private TableEntry<K, V>[] table;
    /**
     * the size of table
     */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isDeleted()) {
				// Each bucket contains a map, so include
				// all entries in the entrySet for the map
				// at the current bucket
				collection.add(table[i]);
			}
		}
		return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int bucket = findBucket(hash, key);
    	if (bucket < 0) {
    		return null;
    	}
    	return table[bucket].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int bucket = findBucket(hash, key);
        if (bucket < 0) {
        	table[bucket * -1 - 1] = new TableEntry<K, V>(key, value);
        	size++;
        	return null;
        }
        V old = table[bucket].getValue();
        table[bucket].setValue(value);
        return old;
    }

    /**
     * Finds the index of the bucket that contains the entry (if it exists)
     * or if the entry is not in the map, -(a+1) where a is the index where the
     * entry should be inserted.
     * @param index The index into the hash table
     * @param key the key of the entry to locate
     * @return the index of the bucket that contains the entry, or -(a+1)
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
        	if (isAvailable(j)) {
        		if (avail == -1) {
        			avail = j;
        		}
        		if (table[j] == null) {
        			return 0 - (avail + 1);
        		}
        	}
        	else if (table[j].getKey().equals(key)) {
        		return j;
        	}
        	j = (j + 1) % table.length;
        } while (j != index);
        return 0 - (avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
        int bucket = findBucket(hash, key);
        if (bucket < 0) {
        	return null;
        }
        else {
        	size--;
        	V old = table[bucket].getValue();
        	table[bucket].setDeleted(true);
        	return old;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	
    	/**
    	 * Determines whether or not the current entry has been deleted
    	 */
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}