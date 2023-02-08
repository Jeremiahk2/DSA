package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * MapEntry implements the Entry abstract data type.
	 * 
	 * @author Dr. King
	 *
	 * @param <K> the type of key stored in the entry
	 * @param <V> the type of value stored in the entry
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		/** the key of this entry */
		private K key;
		/** the value stored in this entry */
		private V value;

		/**
		 * Constructs a MapEntry with a provided key and a provided value
		 * 
		 * @param key   the key to store in the entry
		 * @param value the value to store in the entry
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Set the key of the entry to the provided key
		 * 
		 * @param key the key to store in the entry
		 */
		private void setKey(K key) {
			this.key = key;
		}

		/**
		 * Set the value of the entry to the provided value
		 * 
		 * @param value the value to store in the entry
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>)this.key).compareTo(o.getKey());
		}       
	}

	/**
	 * EntryCollection implements the {@link Iterable} interface to allow traversing
	 * through the entries stored in the map. EntryCollection does not allow removal
	 * operations
	 * 
	 * @author Dr. King
	 * @author jeremiah Knizley
	 *
	 */
	protected class EntryCollection implements Iterable<Entry<K, V>> {

		/** the list of entries in the collection */
		private List<Entry<K, V>> list;

		/** constructor for entry collection, simply initializes list */
		public EntryCollection() {
			list = new SinglyLinkedList<Entry<K, V>>();
		}

		/** 
		 * adds the entry to the list (at the end)
		 * @param entry the entry to be added
		 */
		public void add(Entry<K, V> entry) {
			list.addLast(entry);
		}

		/** 
		 * creates and returns an EntryCollectionIterator
		 * @return a new EntryCollectionIterator
		 */
		public Iterator<Entry<K, V>> iterator() {
			return new EntryCollectionIterator();
		}

		/**
		 * Class for iterating through an EntryCollection, uses standard iterator methods (except remove is unsupported)
		 * @author Jeremiah Knizley
		 *
		 */
		private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

			/** the iterator object over entries */
			private Iterator<Entry<K, V>> it;

			/** creates a new iterator  and puts it in it */
			public EntryCollectionIterator() {
				it = list.iterator();
			}

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Entry<K, V> next() {
				return it.next();
			}

			/**
			 * overridden to remove functionality
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException("The remove operation is not supported yet.");
			}
		}
	}       

	/**
	 * KeyIterator implements the {@link Iterator} interface to allow traversing
	 * through the keys stored in the map
	 * 
	 * @author Dr. King
	 * @author Jeremiah Knizley
	 *
	 */
	protected class KeyIterator implements Iterator<K> {

		/** the Entry iterator to be used */
		private Iterator<Entry<K, V>> it;
		/**
		 * constructs a new iterator
		 */
		public KeyIterator() {
			it = entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public K next() {
			return it.next().getKey();
		}
		/**
		 * overridden to remove functionality
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * ValueIterator implements the {@link Iterator} interface to allow traversing
	 * through the values stored in the map
	 * 
	 * @author Dr. King
	 * @author Jeremiah Knizley
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
		/** an iterator to be used in this iterator */
		private Iterator<Entry<K, V>> it;
		/** constructs it */
		public ValueIterator() {
			it = entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return it.next().getValue();
		}
		/**
		 * overridden to remove functionality
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}
	private class ValueIterable implements Iterable<V> {
		
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<K> iterator() {
		return new KeyIterator();
	}

	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

}