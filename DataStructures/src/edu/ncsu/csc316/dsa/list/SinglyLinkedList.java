package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** A reference to the dummy/sentinel node at the front of the list **/
	private LinkedListNode<E> front;

	/** A reference to the last/final node in the list **/
	private LinkedListNode<E> tail;

	/** The number of elements stored in the list **/
	private int size;

	/**
	 * Constructs an empty singly-linked list
	 */     
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	/**
	 * Adds an element to the list at the specified index
	 * @param index the index in the list where the element should be added
	 * @param element the element to be added to the list
	 */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		if (index == size) {
			addLast(element);
		}
		else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			LinkedListNode<E> newNode = new LinkedListNode<E>(element, current.getNext());
			current.setNext(newNode);
			size++;
		}
	}

	/**
	 * returns the element at the index
	 * @param index the place in the list at which to retrieve the element
	 * @return E the element in the list to be retrieved
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.getElement();
	}

	/**
	 * Removes the element at the index from the list and returns it
	 * @param index the location of the element in the list that will be removed
	 * @return the element that was removed from the list
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E oldData = current.next.getElement();
		current.setNext(current.getNext().getNext());
		if (index == size - 1) {
			tail = current;
		}
		size--;
		return oldData;
	}

	/**
	 * replaces the element at the specified index with the parameterized element
	 * @param index the index at which to set the element
	 * @param element the element that the index in the list will be set to
	 * @return the element that was replaced with the parameter element
	 */
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E oldData = current.getElement();
		current.setElement(element);
		return oldData;
	}

	/**
	 * returns the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
	 * runtime.
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty");
		}
		return tail.getElement();
	}

	/**
	 * {@inheritDoc}
	 * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
	 */    
	@Override
	public void addLast(E element) {
		LinkedListNode<E> lastNode = new LinkedListNode<E>(element);
		if (isEmpty()) {
			front.setNext(lastNode);
			tail = lastNode;
			size++;
		}
		else {
			tail.setNext(lastNode);
			tail = tail.getNext();
			size++;
		}
	}

	/**
	 * returns a new iterator that starts at the beginning of the list
	 * @return the new iterator for this list
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	private class ElementIterator implements Iterator<E> {
		/**
		 * Keep track of the next node that will be processed
		 */
		private LinkedListNode<E> current;

		/** 
		 * Keep track of the node that was processed on the last call to 'next'
		 */
		private LinkedListNode<E> previous;

		/**
		 * Construct a new element iterator where the cursor is initialized 
		 * to the beginning of the list.
		 */
		public ElementIterator() {
			previous = front;
			current = previous.next;
		}

		/**
		 * returns the status of whether or not the iterator has a next element
		 * @return true if the iterator has a next element, false if not
		 */
		@Override
		public boolean hasNext() {
			return previous.next != null;
		}

		/**
		 * returns the next element in the iterator.
		 * @return the next element in the iterator
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current = previous.next;
			previous.next = current;
			previous = current;
			return previous.getElement();
		}

		/**
		 * remove is not supported by this implementation of Iterator
		 * @throws UnsupportedOperationException because remove is not supported
		 */
		@Override    
		public void remove() {
			throw new UnsupportedOperationException(
					"This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
		}
	}

	/**
	 * This class contains nodes that are apart of a LinkedList. 
	 * These are singly linked nodes, only containing their data and a reference to the next node after this one
	 * @author Jeremiah Knizley
	 *
	 * @param <E> the type of data to be stored in the nodes
	 */
	private static class LinkedListNode<E> {
		/** The data stored in the node */
		private E data;
		/** the next node after this one */
		private LinkedListNode<E> next;

		/**
		 * creates a new LinkedListNode 
		 * @param data  the data to be stored in the node
		 */
		public LinkedListNode(E data) {
			this.data = data;
		}
		/**
		 * creates a new LinkedListNode using data and a next reference
		 * @param data the data in the node
		 * @param next the next node after this one
		 */
		public LinkedListNode(E data, LinkedListNode<E> next) {
			this.data = data;
			this.next = next;
		}
		/**
		 * returns the next node after this one
		 * @return the node after this one
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}
		/**
		 * sets the next node to the parameter
		 * @param next the node to be set as next
		 */
		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}
		/**
		 * sets the data of this node to element
		 * @param element the new data for this node
		 */
		public void setElement(E element) {
			this.data = element;
		}
		/**
		 * returns the data stored in this node
		 * @return the data stored in this node
		 */
		public E getElement() {
			return data;
		}
	}

}