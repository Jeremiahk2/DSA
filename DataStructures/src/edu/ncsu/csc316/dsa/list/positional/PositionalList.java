package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional List abstract data type defines behaviors that operate based
 * on positions instead of indexes (like the index-based List abstract data
 * type).
 *
 * All Positional Lists must be {@link Iterable} over Positions of the list to
 * allow clients to iterate using for-each loops. In addition, all Positional
 * Lists must be {@link Iterable} over elements within the list.
 * 
 * The PositionalList interface is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public interface PositionalList<E> extends Iterable<E> {

	/**
	 * Add a new element into a new position that should be added immediately after
	 * the specified position, p
	 * 
	 * @param p       the position that should be located before the new position
	 *                that will be created
	 * @param element the element to be added to the list
	 * @return a reference to the Position that was created to store the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	Position<E> addAfter(Position<E> p, E element);

	/**
	 * Add a new element into a new position that should be added immediately before
	 * the specified position, p
	 * 
	 * @param p       the position that should be located after the new position
	 *                that will be created
	 * @param element the element to be added to the list
	 * @return a reference to the Position that was created to store the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	Position<E> addBefore(Position<E> p, E element);

	/**
	 * Add a new element into a new position at the front of the list
	 * 
	 * @param element the element to be added to the front of the list
	 * @return a reference to the Position that was created at the front of the list
	 *         to store the new element
	 */
	Position<E> addFirst(E element);

	/**
	 * Add a new element into a new position at the end of the list
	 * 
	 * @param element the element to be added to the end of the list
	 * @return a reference to the Position that was created at the end of the list
	 *         to store the new element
	 */
	Position<E> addLast(E element);

	/**
	 * Returns a reference to the Position that is located in the list immediately
	 * after the given position, p. Return null if p is at the end of the list.
	 * 
	 * @param p the position for which to retrieve the position located after
	 * @return a reference to the Position that is located in the list immediately
	 *         after the given position, p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	Position<E> after(Position<E> p);

	/**
	 * Returns a reference to the Position that is located in the list immediately
	 * before the given position, p. Return null if p is at the front of the list.
	 * 
	 * @param p the position for which to retrieve the position located before
	 * @return a reference to the Position that is located in the list immediately
	 *         before the given position, p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	Position<E> before(Position<E> p);

	/**
	 * Returns a reference to the first Position in the list
	 * 
	 * @return a reference to the first position in the list
	 */
	Position<E> first();

	/**
	 * Returns true if the list is empty, otherwise return false. Return null if the
	 * list is empty.
	 * 
	 * @return true if the list is empty, otherwise return false
	 */
	boolean isEmpty();

	/**
	 * Returns a reference to the last/final Position in the list. Return null if
	 * the list is empty.
	 * 
	 * @return a reference to the last position in the list
	 */
	Position<E> last();

	/**
	 * Returns a new Iterable collection of list Positions
	 * 
	 * @return a new Iterable collection of list positions
	 */
	Iterable<Position<E>> positions();

	/**
	 * Removes the position p from the list and returns the element stored at p.
	 * 
	 * @param p the position to remove from the list
	 * @return the element stored at p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	E remove(Position<E> p);

	/**
	 * Updates the element in a given position, p, to a new element.
	 * 
	 * @param p       the position in which to update the element
	 * @param element the new element that will overwrite the existing element
	 * @return the original element that was replaced by the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	E set(Position<E> p, E element);

	/**
	 * The number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	int size();
}