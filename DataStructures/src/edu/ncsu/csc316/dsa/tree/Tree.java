package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Tree abstract data type represents a hierarchical collection of elements,
 * where each. The Tree ADT does not restrict the number of children that each
 * node may have.
 * 
 * All Trees must be {@link Iterable} to allow clients to iterate using for-each
 * loops.
 * 
 * The Tree interface is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <E> the type of elements stored in the tree
 */
public interface Tree<E> {

    /**
     * Returns a reference to the root position of the tree
     * 
     * @return the root position of the tree
     */
    Position<E> root();

    /**
     * Returns a reference to the new root position of the tree that is created to
     * store the given element
     * 
     * @param element the element to add to the tree
     * @return the root position that is created to store the element
     * @throws IllegalArgumentException if the tree already has an existing root
     *                                  position
     */
    Position<E> addRoot(E element);

    /**
     * Returns a reference to the parent of the provided position, p
     * 
     * @param p a position for which to retrieve the position's parent
     * @return the parent of the provided position, p
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    Position<E> parent(Position<E> p);

    /**
     * Returns an {@link Iterable} collection of positions that represent children
     * of the given position
     * 
     * @param p a position for which to retrieve the position's children
     * @return a collection of positions that represent children of the given
     *         position, p
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    Iterable<Position<E>> children(Position<E> p);

    /**
     * Returns the number of children of the provided position
     * 
     * @param p a position for which to retrieve the number of children of the
     *          position
     * @return the number of children of the provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    int numChildren(Position<E> p);

    /**
     * Returns true if the provided position has at least 1 child
     * 
     * @param p a position for which to determine if the position is an internal
     *          position of the tree
     * @return true if the provided position has at least 1 child, otherwise return
     *         false
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    boolean isInternal(Position<E> p);

    /**
     * Returns true if the provided position has no children
     * 
     * @param p a position for which to determine if the position is a leaf position
     *          of the tree
     * @return true if the provided position has no childred, otherwise return false
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    boolean isLeaf(Position<E> p);

    /**
     * Returns true if the provided position is the root of the tree
     * 
     * @param p a position for which to determine if the position is the root of the
     *          tree
     * @return true if the provided position is the root of the tree, otherwise
     *         return false
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    boolean isRoot(Position<E> p);

    /**
     * Removes the provided position from the tree. Returns the element that was
     * stored in the position that is removed.
     * 
     * @param p the position for which to remove from the tree
     * @return the element that was stored in the position that is removed
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     * @throws IllegalArgumentException if removal of the position is not allowed
     */
    E remove(Position<E> p);

    /**
     * Updates the given position to have the provided element.
     * 
     * @param p       the position for which to update the element stored at that
     *                position
     * @param element the new element that should replace the existing element in
     *                the provided position
     * @return the original element that was replaced by the new element at the
     *         provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    E set(Position<E> p, E element);

    /**
     * Return the number of elements stored in the tree
     * 
     * @return the number of elements stored in the tree
     */
    int size();

    /**
     * Returns true if the tree contains no elements, otherwise return false
     * 
     * @return true if the tree contains no elements, otherwise return false
     */
    boolean isEmpty();

    /**
     * Returns an {@link Iterable} collection of positions that represent the
     * preorder traversal of positions within the tree
     * 
     * @return a collection of positions that represent the tree's preorder
     *         traversal
     */
    Iterable<Position<E>> preOrder();

    /**
     * Returns an {@link Iterable} collection of positions that represent the
     * postorder traversal of positions within the tree
     * 
     * @return a collection of positions that represent the tree's postorder
     *         traversal
     */
    Iterable<Position<E>> postOrder();

    /**
     * Returns an {@link Iterable} collection of positions that represent the
     * levelorder traversal of positions within the tree
     * 
     * @return a collection of positions that represent the tree's levelorder
     *         traversal
     */
    Iterable<Position<E>> levelOrder();
}