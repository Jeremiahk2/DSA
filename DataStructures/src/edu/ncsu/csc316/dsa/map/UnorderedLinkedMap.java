package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** the positional list that contains our entries */
    private PositionalList<Entry<K, V>> list;
    /**
     * constructor for unordered linked maps
     * Initializes list
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    /**
     * Finds a specific entry with the given key in the list
     * @param key the key to look for
     * @return Position the position in the list with the given key
     */
    private Position<Entry<K, V>> lookUp(K key) {
    	Position<Entry<K, V>> current = list.first();
    	Position<Entry<K, V>> value = null;
        for (int i = 0; i < list.size(); i++) {
        	if (current.getElement().getKey().equals(key)) {
        		value = current;
        	}
        	current = list.after(current);
        }
        return value;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        }
        else {
        	moveToFront(p);
        	return p.getElement().getValue();
        }
    }
    /**
     * Moves the given position to the front of the list
     * @param position the position to be moved
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        list.remove(position);
        list.addFirst(position.getElement());
    }

    
	@Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        V oldValue = null;
        if (p == null) {
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return null;
        }
        else {
        	oldValue = p.getElement().getValue();
        	list.remove(p);
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return oldValue;
        }
    }
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       }
       return list.remove(p).getValue();
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}