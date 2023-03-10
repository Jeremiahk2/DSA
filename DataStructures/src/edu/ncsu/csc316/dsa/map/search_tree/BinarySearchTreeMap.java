package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.AbstractOrderedMap;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * The BinarySearchTreeMap is implemented as a linked data structure to support
 * efficient Tree and Map abstract data type behaviors.
 * 
 * Because of single-inheritance, we can extend only a single superclass: {$link
 * AbstractOrderedMap}. To overcome the limitation of single-inheritance, we
 * create a {@link BalanceableBinaryTree} class to handle rotation and relinking
 * logic. This allows us to adapt our implementation to delegate to the
 * {@link BalanceableBinaryTree} instead of extending {@link LinkedBinaryTree}.
 * 
 * BinarySearchTreeMap uses sentinel leaves. Every leaf node should have 2
 * sentinel children.
 * 
 * The BinarySearchTreeMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <K> the type of keys stored in the binary search tree
 * @param <V> the type of values associated with keys in the binary search tree
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    // The BalanceableBinaryTree class is an inner class below
	/**
	 * The BalanceableBinarySearchTree that will keep track of the data in the BinarySearchTreeMap
	 */
    private BalanceableBinaryTree<K, V> tree;

    /**
     * Constructs a new binary search tree map that uses natural ordering of keys
     * when performing comparisons
     */
    public BinarySearchTreeMap() {
        this(null);
    }

    /**
     * Constructs a new binary search tree map that uses a provided
     * {@link Comparator} when performing comparisons of keys within the tree
     * @param compare the comparator to use in the BinarySearchTreeMap
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }

    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2
        return (tree.size() - 1) / 2;
    }

    /**
     * To preserve the property of having all sentinel leaves, expandLeaf converts a
     * sentinel leaf into a position with an entry, then adds 2 new sentinel
     * children to the position
     * 
     * @param p     the position in the tree to update to store the provided entry
     * @param entry the entry to store in the provided position of the tree
     */
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // This method is used to add dummy/sentinel left and right children as leaves

        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry
        tree.set(p, entry);

        // Then add new dummy/sentinel children
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    /**
     * Performs a traversal down a single path of the tree to locate and return the
     * position with the provided key. If no position in the tree contains the
     * provided key, then return a reference to the sentinel node where the downward
     * path traversal stopped
     * 
     * @param p   the position that represents the root of the subtree being
     *            searched
     * @param key the target key to locate within the subtree
     * @return the position that contains the provided key, or the target key is not
     *         contained within the tree then return the sentinel position at which
     *         the search terminated
     */
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // This helper method traces a path down the tree to locate the position
        // that contains an entry with the given key.
        // Think of "lookUp" as returning the last position visited when tracing
        // a path down the tree to find the given key

        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        // Create the new map entry
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);

        // Get the last node visited when looking for the key
        Position<Entry<K, V>> p = lookUp(root(), key);

        // If the last node visited is a dummy/sentinel node
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnAccess(p);
            return original;
        }
    }

    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key
        Position<Entry<K, V>> p = lookUp(root(), key);

        // If p is a dummy/sentinel node
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move the reference p to the replacement node in the right subtree
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a
            // child)...
            Position<Entry<K, V>> leaf = isLeaf(left(p)) ? left(p) : right(p);
            // ... then get its sibling (will be another sentinel or an actual entry node)
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your LinkedBinaryTree remove method)
            remove(leaf);
            // Remove the NODE (this is your LinkedBinaryTree remove method)
            // which will "promote" the sib node to replace p
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use
            actionOnDelete(sib);
            return original;
        }
    }

    /**
     * Locates and returns the position in the tree that stores the inorder
     * successor of the key in p. In other words, find the position that contains
     * the minimum key in the subtree rooted at p.
     * 
     * @param p the position that represents the root of the subtree being searched
     * @return the position in the tree that stores the inorder successor of the key
     *         in p
     */
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> current = p;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (Position<Entry<K, V>> n : tree.inOrder()) {
            collection.add(n.getElement());
        }
        return collection;
    }

//    @Override
//    public String toString() {
//        return tree.toString();
//    }

    /**
     * A method hook that is executed whenever a tree position is accessed
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnAccess(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * A method hook that is executed whenever a tree position is inserted into the
     * tree
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * A method hook that is executed whenever a tree position is removed from the
     * tree
     * 
     * @param p the position p that should be acted upon
     */
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        // Do nothing for BST
    }

    /**
     * The BalanceableBinaryTree is implemented as a linked data structure to
     * support efficient Tree abstract data type behaviors.
     * 
     * We create a {@link BalanceableBinaryTree} class to handle rotation and
     * relinking logic for binary trees. All behaviors delegate to
     * {@link LinkedBinaryTree}.
     * 
     * The BalanceableBinaryTree class is based on the implementation developed for
     * use with the textbook:
     *
     * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
     * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
     * 
     * @author Dr. King
     *
     * @param <K> the type of keys stored in the binary tree
     * @param <V> the type of values associated with keys in the binary tree
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        /**
         * Relink two positions to create a parent-child relationship
         * 
         * @param parent        the position that will become the parent of the child
         * @param child         the position that will be come a child of the parent
         * @param makeLeftChild indicates whether the child should be a left child
         *                      (true) or not (false)
         */
        private void relink(BinaryTreeNode<Entry<K, V>> parent, BinaryTreeNode<Entry<K, V>> child,
                boolean makeLeftChild) {
            child.setParent(parent);
            if (makeLeftChild) {
            	parent.setLeft(child);
            }
            else {
            	parent.setRight(child);
            }
        }

        /**
         * Performs a single rotation of a position, p, around it's parent. If
         * necessary, the grandparent must be updated to now refer to p as its child; p
         * must be updated to indicate its parent is now its child
         * 
         * @param p the position to rotate around its parent
         */
        public void rotate(Position<Entry<K, V>> p) {
        	//Track the three nodes involved in the rotation
        	BinaryTreeNode<Entry<K, V>> node = validate(p);
        	BinaryTreeNode<Entry<K, V>> parent = node.getParent();
        	BinaryTreeNode<Entry<K, V>> grandparent = parent.getParent();
           
            //Check whether the node is a single rotation (no grandparent exists)
            if (grandparent == null) {
            	//Rotate the node to be the new root
            	setRoot(node);
            	node.setParent(null);
            }
            else {
            	//Otherwise, link the node as a child of the grandparent
            	if (grandparent.getLeft().equals(parent)) {
            		relink(grandparent, node, true);
            	}
            	else {
            		relink(grandparent, node, false);
            	}
            }
            //Regardless of whether a grandparent exists,
            //relink the parent and node and transfer node's subtree
            if (parent.getLeft().equals(node)) {
            	relink(parent, node.getRight(), true);
            	relink(node, parent, false);
            }
            else {
            	relink(parent, node.getLeft(), false);
            	relink(node, parent, true);
            }
            
            
        }

        /**
         * Performs a trinode restructuring and returns the position at its final,
         * rotated position.
         * 
         * @param x the position that represents x in a trinode restructuring of x, its
         *          parent y, and its grandparent z
         * @return the position at its final, rotated position
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
            //Track the three nodes involved in the restructuring.
        	BinaryTreeNode<Entry<K, V>> node = validate(x);
        	BinaryTreeNode<Entry<K, V>> parent = node.getParent();
        	BinaryTreeNode<Entry<K, V>> grandparent = parent.getParent();
        	
        	if (parent.getLeft().equals(node) && grandparent.getLeft().equals(parent) || 
        		 parent.getRight().equals(node) && grandparent.getRight().equals(parent)) {
        		rotate(parent);
        		return parent;
        	}
        	else {
        		//rotate the node around the parent twice
        		rotate(node);
        		rotate(node);
        		return node;
        	}
        }

        @Override
        protected BinaryTreeNode<Entry<K, V>> createNode(Entry<K, V> element, BinaryTreeNode<Entry<K, V>> parent,
                BinaryTreeNode<Entry<K, V>> left, BinaryTreeNode<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        /**
         * A BSTNode is a binary search tree node. A binary search tree node extends
         * {@link BinaryTreeNode} to saved an additional piece of information that is
         * necessary for some search trees: In an AVL tree, this extra information
         * represents the height of the node; In a red-black tree, this extra
         * information represents the color of the node
         * 
         * 
         * @author Dr. King
         * @author Jeremiah Knizley
         *
         * @param <E> the element stored in the binary search tree node
         */
        protected static class BSTNode<E> extends BinaryTreeNode<E> {
        	/**
        	 * The property of the node (usually height)
        	 */
            private int property;

            /**
             * Constructs a binary search tree node with the provided element
             * 
             * @param element the element to store in the binary search tree node
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }

            /**
             * Sets the property of the binary search tree node.
             * 
             * @param property the property information (height, color, etc.) of the binary
             *                 search tree node
             */
            public void setProperty(int property) {
                this.property = property;
            }

            /**
             * Returns the propert of the binary search tree node.
             * 
             * @return the property information (height, color, etc.) of the binary search
             *         tree node
             */
            public int getProperty() {
                return property;
            }
        }

        /**
         * Returns the property of a given position. The position must first be cast to
         * a {@link BSTNode}.
         * 
         * @param p the position for which to retrieve the property information
         * @return the property information for the provided position in the tree
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }

        /**
         * Sets the property of a given position. The position must first be cast to a
         * {@link BSTNode}.
         * 
         * @param p     the position for which to set the property information
         * @param value the value to set for the property information for the provided
         *              position in the tree
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

        /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which
    // extends your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the root of the binary search tree
     * 
     * @return the root of the binary search tree
     */
    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    /**
     * Returns the parent of position p
     * 
     * @param p the position for which to return the parent
     * @return the parent of position p
     */
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    /**
     * Returns true if position p is an internal node
     * 
     * @param p the position for which to check
     * @return true if the position is an internal node; otherwise, return false
     */
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    /**
     * Updates the given tree position to contain a new entry, then returns the
     * original/overwritten entry
     * 
     * @param p     the position to be updated
     * @param entry the new entry to be stored in the position
     * @return the original entry that was overwritten
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }

    /**
     * Returns true if the tree position p is a leaf node
     * 
     * @param p the position to check
     * @return true if the position is a leaf node; otherwise, return false
     */
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }

    /**
     * Returns true if the tree position p is the root of the tree
     * 
     * @param p the position to check
     * @return true if the position is the root node; otherwise, return false
     */
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    /**
     * Returns the left child of tree position p
     * 
     * @param p the position for which to return the left child
     * @return the left child of the position p
     */
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    /**
     * Returns the right child of tree position p
     * 
     * @param p the position for which to return the right child
     * @return the right child of the position p
     */
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    /**
     * Returns the sibling of position p
     * 
     * @param p the position p for which to return the sibling
     * @return the sibling of the position p, or null if p has no sibling
     */
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    /**
     * Rotates the given position up 1 level in the tree
     * {@see BalanceableBinaryTree#rotate}
     * 
     * @param p the position to rotate
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * Performs a trinode restructuring where the given position, p, is the
     * grandchild {@see BalanceableBinaryTree#restructure}
     * 
     * @param x the position at which to begin the trinode restructuring rotations
     * @return the position at the root of the balanced subtree
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * Returns the property of the given position
     * {@see BalanceableBinaryTree#getProperty}
     * 
     * @param p the position for which to retrieve the property
     * @return the property of the given position
     */
    protected int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }

    /**
     * Sets the property of the given position to have the given value
     * {@see BalanceableBinaryTree#setProperty}
     * 
     * @param p     the position for which to set the property
     * @param value the property value to be set
     */
    protected void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }
}