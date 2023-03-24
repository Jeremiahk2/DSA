package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * A skeletal implementation of the hash table data structure for the Map
 * abstract data type. This class provides implementation for common methods
 * that can be implemented the same no matter what specific type of concrete
 * hash table data structure is used to implement the map abstract data type.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the hash table
 * @param <V> the type of values that are associated with keys in the hash table
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /**
     * The default initial capacity of the hash table array
     */
    protected static final int DEFAULT_CAPACITY = 17;
    //////////////////////////////////////////////////////////////////
    // From our discussion in class, the expected number of probes
    // for separate chaining remains relatively small no matter
    // what the load factor may be. However, for linear probing, to
    // reduce the chance of having large clusters, we will resize
    // when the load factor reaches 0.5
    //////////////////////////////////////////////////////////////////
    /**
     * The maximum load factor to allow before resizing the hash table
     */
    private static final double MAX_LOAD_FACTOR = 0.5;

    /**
     * A default prime number to use in hash function calculations
     */
    protected static final int DEFAULT_PRIME = 109345121;

    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
    private long alpha;
    private long beta;

    // The prime number to use for compression strategy
    private int prime;

    /**
     * Initializes the abstract hash map state with values to use when calculating
     * hash functions. You can use the isTesting flag (set to true) to.
     * 
     * In TESTING MODE only, alpha=1, beta=1, and prime=7 to help ensure a
     * repeatable sequence of prime numbers and hash functions
     * 
     * @param capacity  the initial capacity of the hash table array
     * @param isTesting if true, controls the testing environment with a predictable
     *                  sequence of random numbers when testing
     */
    public AbstractHashMap(int capacity, boolean isTesting) {
        if (isTesting) {
            alpha = 1;
            beta = 1;
            prime = 7;
        } else {
            Random rand = new Random();
            alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
            beta = rand.nextInt(DEFAULT_PRIME);
            prime = DEFAULT_PRIME;
        }
        createTable(capacity);
    }

    /**
     * Returns the hash function for a given key using multiply-and-divide
     * compression
     * 
     * @param key the key for which to calculate the hash function
     * @return the compressed hash function for the provided key
     */
    private int compress(K key) {
        return (int) ((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
    }

    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if ((double) size() / capacity() > MAX_LOAD_FACTOR) {
            resize(2 * capacity() + 1);
        }
        return ret;
    }

    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }

    /**
     * Resizes the hash table array to have a new, larger capacity
     * 
     * @param newCapacity the capacity of the new, larger hash table array
     */
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for (Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for (Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the current capacity of the current hash table array
     * 
     * @return the capacity of the current hash table array
     */
    protected abstract int capacity();

    /**
     * Creates a new hash table array with the given capacity
     * 
     * @param capacity the initial capacity for a new hash table array instance
     */
    protected abstract void createTable(int capacity);

    /**
     * Returns the value associated with the given key in the bucket with the given
     * hash function
     * 
     * @param hash the index of the bucket in which to inspect
     * @param key  the target key
     * @return the value associated with the given target key
     */
    protected abstract V bucketGet(int hash, K key);

    /**
     * Adds or updates an entry with the given key and value to the hash table array
     * in the bucket that has the provided hash
     * 
     * @param hash  the index of the bucket in which to add or update the entry
     * @param key   the key for the entry being added or updated
     * @param value the value for the entry being added or updated
     * @return the original value of the entry that was updated, or null if an entry
     *         with the target key did not already exist in the hash table bucket
     */
    protected abstract V bucketPut(int hash, K key, V value);

    /**
     * Returns the value associated with the entry with the key in the bucket with
     * the given hash function
     * 
     * @param hash the index of the bucket in which to remove the entry
     * @param key  the key for the targeted entry being removed from the hash table
     * @return the value associated with the entry that was removed from the hash
     *         table
     */
    protected abstract V bucketRemove(int hash, K key);
}