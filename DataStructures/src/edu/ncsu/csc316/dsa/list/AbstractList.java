package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {

	/**
	 * adds the element to the front of the list
	 * @param element the element to be added to the beginning of the list
	 */
	@Override
	public void addFirst(E element) {
		add(0, element);
	}

	/**
	 * adds the element to the end of the list
	 * @param element the element be added to the end of the list
	 */
	@Override
	public void addLast(E element) {
		add(size(), element);
	}

	/**
	 * Checks whether the provided index is a legal index based on the current state
	 * of the list. This check should be performed when accessing any specific
	 * indexes within the list.
	 * 
	 * @param index the index for which to check whether it is valid/legal in the
	 *              current list or not
	 * @throws IndexOutOfBoundsException if the index is not valid for the current size of the list
	 */
	protected void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * Checks whether the provided index is a legal index based on the current state
	 * of the list. This check should be performed when adding elements at specific
	 * indexes within the list.
	 * 
	 * @param index the index for which to check whether it is valid/legal in the
	 *              current list or not
	 * @throws IndexOutOfBoundsException if the index is not valid for the current size of the list
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * returns the first element in the list
	 * @return the first element in the list
	 */
	@Override
	public E first() {
		return get(0);
	}

	/**
	 * returns whether or not the list is empty or not
	 * @return true if the list is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * returns the last element in the list
	 * @return the last element in the list
	 */
	@Override
	public E last() {
		return get(size() - 1);
	}

	/**
	 * removes the first element in the list
	 * @return the element that was removed from the list
	 */
	@Override
	public E removeFirst() {
		return remove(0);
	}

	/**
	 * removes the element at the end of the list
	 * @return the element that was removed from the list
	 */
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}