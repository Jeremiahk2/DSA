package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;

/**
 * The AVLTreeMap is implemented as a linked data structure to support efficient
 * Tree and Map abstract data type behaviors.
 * 
 * In an AVL tree, the height-balance property requires the heights of a
 * position's children to differ by no more than 1. Otherwise, restructuring is
 * performed to rebalance the tree.
 * 
 * The properties of an AVL tree ensure O(logn) height, and O(logn)
 * worst-case performance for {@see Map#put}, {@see Map#get}, and
 * {@see Map#remove}.
 *
 * AVLTreeMap uses sentinel leaves. Every leaf node should have 2 sentinel
 * children.
 * 
 * The AVLTreeMap class is based on the implementation developed for use with
 * the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <K> the type of keys stored in the AVL tree
 * @param <V> the type of values associated with keys in the AVL tree
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

    /**
     * Constructs a new AVL tree map that uses natural ordering of keys when
     * performing comparisons
     */
    public AVLTreeMap() {
        super(null);
    }

    /**
     * Constructs a new AVL tree map that uses a provided {@link Comparator} when
     * performing comparisons of keys within the tree
     * @param compare the comparator to use in the AVL tree
     */
    public AVLTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * Checks the height-balance property at each level of the tree up to the root.
     * If the height-balance property is violated, then a restructuring is
     * performed.
     * 
     * @param p the position at which to check the height-balance property
     */
    private void rebalance(Position<Entry<K, V>> p) {
        //Track the old height of the node p
    	int oldHeight = 0;
    	int newHeight;
    	Position<Entry<K, V>> localPosition = p;
    	do {
    		oldHeight = getProperty(localPosition);
    		if (!isBalanced(localPosition)) {
    			//Find the child with the "taller" height"
    			Position<Entry<K, V>> child = tallerChild(localPosition);
    			//Find the grandchild with the taller height
    			Position<Entry<K, V>> grandchild = tallerChild(child);
    			//Perform trinode restructuring at the grindchild
    			//save the root of the restructured subtree as x
    			Position<Entry<K, V>> x = restructure(grandchild);
    			recomputeHeight(left(x));
    			recomputeHeight(right(x));
    		}
    		recomputeHeight(localPosition);
    		newHeight = getProperty(localPosition);
    		//Move up to the parent
    		localPosition = parent(localPosition);
    		
    	} while (oldHeight != newHeight && localPosition != null);
    	
    }

    /**
     * Returns the taller child (the child of p with the greater height) of the
     * given position p. If the given position is the root, return the left child.
     * If both children have the same height, return the child that is aligned with
     * the parent's orientation (i.e., if the parent is a left child of the
     * grandparent, then return the left child of the parent).
     * 
     * @param p the position for which to return the taller child.
     * @return the taller child of p
     */
    private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        if (getProperty(left(p)) > getProperty(right(p))) {
        	return left(p);
        }
        if (getProperty(left(p)) < getProperty(right(p))) {
        	return right(p);
        }
        if (isRoot(p)) {
        	return left(p);
        }
        if (left(parent(p)).equals(p)) {
        	return left(p);
        }
        else {
        	return right(p);
        }
    }

    /**
     * Returns true if the heights of the children of p differ by no more than 1
     * 
     * @param p the position at which to check the height-balance property
     * @return true if the heights of p's children differ by no more than 1;
     *         otherwise, return false
     */
    private boolean isBalanced(Position<Entry<K, V>> p) {
        return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
    }

    /**
     * Recalculates the height of a position, p, when restructuring
     * 
     * @param p the position for which to recalculate the height
     */
    private void recomputeHeight(Position<Entry<K, V>> p) {
        int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
        setProperty(p, h);
    }

    /**
     * {@inheritDoc} For an AVL tree, we must trace a path up the tree toward the
     * root to determine if the newly added position results in a violation of the
     * height-balance property
     */
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        rebalance(node);
    }

    /**
     * {@inheritDoc} For an AVL tree, we must trace a path all the way to the root
     * of the tree to determine if the removed position results in a violation of
     * the height-balance property.
     */
    protected void actionOnDelete(Position<Entry<K, V>> node) {
        if (!isRoot(node)) {
            rebalance(parent(node));
        }
    }
}