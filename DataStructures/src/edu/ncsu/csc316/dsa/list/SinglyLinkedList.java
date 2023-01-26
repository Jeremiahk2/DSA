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
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E>{

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

	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.getElement();
	}

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
	     * Keep track of whether it's ok to remove an element (based on whether
	     * next() has been called immediately before remove())
	     */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        previous = front;
	        current = previous.next;
	        removeOK = false;
	    }

	    @Override
	    public boolean hasNext() {
	        if (previous.next != null) {
	        	return true;
	        }
	        return false;
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) {
	        	throw new NoSuchElementException();
	        }
	        current = previous.next;
	        removeOK = true;
	        previous.next = current;
	        previous = current;
	        //current = previous.next;
	        return previous.getElement();
	    }
	     
	    @Override    
	    public void remove() {
	        throw new UnsupportedOperationException(
	            "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
	    }
	}
    
	private static class LinkedListNode<E> {
        /** The data stored in the node */
        private E data;
        /** the next node after this one */
        private LinkedListNode<E> next;
        
        public LinkedListNode(E data) {
        	this.data = data;
        }
        public LinkedListNode(E data, LinkedListNode<E> next) {
        	this.data = data;
        	this.next = next;
        }
        
        public LinkedListNode<E> getNext() {
        	return next;
        }
        public void setNext(LinkedListNode<E> next) {
        	this.next = next;
        }
        public void setElement(E element) {
        	this.data = element;
        }
        public E getElement() {
        	return data;
        }
    }
    
}