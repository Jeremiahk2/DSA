package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** A dummy/sentinel node representing at the front of the list **/
	private PositionalNode<E> front;

	/** A dummy/sentinel node representing at the end/tail of the list **/
	private PositionalNode<E> tail;

	/** The number of elements in the list **/
	private int size;

	/**
	 * Constructs an empty positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Adds the element between next and prev
	 * @param element the element to be added
	 * @param next the next element after the element to be added
	 * @param prev the previous element before the element to be added
	 * @return the position of the newly added element
	 */
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> newNode = new PositionalNode<>(element);
		newNode.setNext(next);
		newNode.setPrevious(prev);
		prev.setNext(newNode);
		next.setPrevious(newNode);
		size++;
		return newNode;
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		return addBetween(element, validate(p).getNext(), validate(p));
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		return addBetween(element, validate(p), validate(p).getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		Position<E> after = validate(p).getNext();
		if (after == tail) {
			return null;
		}
		return after;
	}

	@Override
	public Position<E> before(Position<E> p) {
		Position<E> before = validate(p).getPrevious();
		if (before == front) {
			return null;
		}
		return before;
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getPrevious();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		E old = p.getElement();
		PositionalNode<E> oldNode = validate(p);
		oldNode.getPrevious().setNext(oldNode.getNext());
		oldNode.getNext().setPrevious(oldNode.getPrevious());
		size--;
		return old;
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E old = node.getElement();
		node.setElement(element);
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
	 * PositionalNode are nodes that contain data, a reference to the next element after this one,
	 * and the previous element before this one. Used for Position based lists
	 * @author Jeremiah Knizley
	 *
	 * @param <E> the type of data to be stored in the list
	 */
	private static class PositionalNode<E> implements Position<E> {

		/** the data in the node */
		private E element;
		/** the next node after this one */
		private PositionalNode<E> next;
		/** the previous node before this one */
		private PositionalNode<E> previous;

		/**
		 * Constructor for PositionalNodes. Creates them with value as the element. next and previous are set to null
		 * @param value the element to be stored in then ode
		 */
		public PositionalNode(E value) {
			this(value, null);
		}

		/**
		 * Additional constructor for PositionalNodes. Creates them with value as the element, next is set to next, and previous is set to null.
		 * @param value the value to be stored in element
		 * @param next the next position to be stored in next
		 */
		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		/**
		 * Additional constructor for PositionalNodes. Creates them with value as the element, next is set to next, and previous is set to null.
		 * @param value the value to be stored in element
		 * @param next the next position to be stored in next
		 * @param prev the previous position to be stored in previous
		 */
		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		/**
		 * sets previous to prev
		 * @param prev the new position that is will be set to previous
		 */
		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		/**
		 * returns the previous element
		 * @return the previous element
		 */
		public PositionalNode<E> getPrevious() {
			return previous;
		}

		/**
		 * sets the next variable to a new node
		 * @param next the position to be set as next
		 */
		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		/**
		 * returns the next positional node after this one
		 * @return the next positional node after this one
		 */
		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		/**
		 * sets the element with the parameter element
		 * @param element the element to be set as the new data in the node
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * An iterator that iterates through and returns Positions
	 * @author Jeremiah Knizley
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * The current Position in the list (the one that will be returned on a call to next())
		 */
		private Position<E> current;
		/**
		 * controls whether or not the remove operation can be performed. Set to true on a call to next, and false on a call to remove
		 */
		private boolean removeOK;

		/**
		 * creates a PositionIterator at the beginning of the list
		 */
		public PositionIterator() {
			current = front.next;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
			if (isEmpty()) {
				return false;
			}
			return current != null;
		}

		@Override
		public Position<E> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Position<E> old = current;
			current = after(current);
			removeOK = true;
			return old;
		}

		@Override
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			if (current == null) {
				PositionalLinkedList.this.remove(tail.previous);
				removeOK = false;
			}
			else {
				PositionalLinkedList.this.remove(before(current));	
				removeOK = false;
			}
		}
	}

	/**
	 * An iterator that iterates through elements rather than positions/nodes. 
	 * @author Jeremiah Knizley
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** a position iterator that will be paired with this element iterator */
		private Iterator<Position<E>> it;

		/**
		 * creates a new ElementIterator (initializes "it" field) 
		 * The position iterator will be delegated to in order to retrieve elements.
		 */
		public ElementIterator() {
			it = new PositionIterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			it.remove();
		}
	}
	/**
	 * Allows for the list to be iterable, and returns a new PositionIterator
	 * @author Jeremiah Knizley
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}



}