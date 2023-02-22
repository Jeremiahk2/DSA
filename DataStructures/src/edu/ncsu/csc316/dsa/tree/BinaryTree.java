package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Binary Tree abstract data type represents a hierarchical collection of
 * elements, where each. The Binary Tree ADT restricts each node in the tree to
 * have either 0, 1, or 2 children.
 * 
 * All Binary Trees must be {@link Iterable} to allow clients to iterate using
 * for-each loops.
 * 
 * The Binary Tree interface is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <E> the type of elements stored in the binary tree
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns a reference to the new tree position that is created to store the
     * given element. The newly created position is added as the left child of the
     * provided position.
     * 
     * @param p       the position for which to add a new left child
     * @param element the element to add to the tree in the newly created position
     * @return the position that is created as the left child of the provided
     *         position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     * @throws IllegalArgumentException if the provided position already has an
     *                                  existing left child
     */
    Position<E> addLeft(Position<E> p, E element);

    /**
     * Returns a reference to the new tree position that is created to store the
     * given element. The newly created position is added as the right child of the
     * provided position.
     * 
     * @param p       the position for which to add the new right child
     * @param element the element to add to the tree in the newly created position
     * @return the position that is created as the right child of the provided
     *         position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     * @throws IllegalArgumentException if the provided position already has an
     *                                  existing right child
     */
    Position<E> addRight(Position<E> p, E element);

    /**
     * Returns an {@link Iterable} collection of positions that represent the
     * inorder traversal of positions within the tree
     * 
     * @return a collection of positions that represent the tree's inorder traversal
     */
    Iterable<Position<E>> inOrder();

    /**
     * Returns the position that is the left child of the provided position, p
     * 
     * @param p the position for which to return the left child of the position
     * @return the position that is the left child of the provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    Position<E> left(Position<E> p);

    /**
     * Returns the position that is the right child of the provided position, p
     * 
     * @param p the position for which to return the right child of the position
     * @return the position that is the right child of the provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    Position<E> right(Position<E> p);

    /**
     * Returns the position that is the sibling of the provided position, p
     * 
     * @param p the position for which to return the sibling of the position
     * @return the position that is the sibling of the provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    Position<E> sibling(Position<E> p);
}