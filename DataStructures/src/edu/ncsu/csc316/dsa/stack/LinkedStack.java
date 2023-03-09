package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

	@Override
	public void push(E element) {
		list.addLast(element);
	}

	@Override
	public E pop() {
		if (list.isEmpty() ) {
			throw new EmptyStackException();
		}
		return list.removeLast();
	}

	@Override
	public E top() {
		if (list.isEmpty() ) {
			throw new EmptyStackException();
		}
		return list.get(list.size() - 1);
	}

	@Override
	public int size() {
		return list.size();
	}
}