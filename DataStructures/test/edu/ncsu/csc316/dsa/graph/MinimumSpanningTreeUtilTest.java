package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * @author Jeremiah Knizley
 *
 */
public class MinimumSpanningTreeUtilTest {
	
	/**
	 * Undirected graph for testing purposes
	 */
	private Graph<String, Highway> undirectedGraph;
	/**
	 * Directed graph for testing purposes
	 */
	private Graph<String, Highway> directedGraph;

	
	/**
	 * Create a new instance of an edge list graph before each test case executes
	 */ 
	@Before
	public void setUp() {
		directedGraph = new EdgeListGraph<String, Highway>(true);
		undirectedGraph = new EdgeListGraph<String, Highway>();
	}
	/**
	 * Tests Prim-Jarnik's algorithm
	 */
	@Test
	public void primTest() {
		Vertex<String> v0 = directedGraph.insertVertex("Zero");
		Vertex<String> v1 = directedGraph.insertVertex("One");
		Vertex<String> v2 = directedGraph.insertVertex("Two");
		Vertex<String> v3 = directedGraph.insertVertex("Three");
		Vertex<String> v4 = directedGraph.insertVertex("Four");
		Vertex<String> v5 = directedGraph.insertVertex("Five");
		
		directedGraph.insertEdge(v0, v4, new Highway("HighwayZero", 6));
		directedGraph.insertEdge(v0, v5, new Highway("HighwayOne", 3));
		directedGraph.insertEdge(v5, v3, new Highway("HighwayTwo", 3));
		directedGraph.insertEdge(v5, v4, new Highway("HighwayThree", 1));
		directedGraph.insertEdge(v4, v1, new Highway("HighwayFour", 1));
		directedGraph.insertEdge(v4, v3, new Highway("HighwayFive", 3));
		directedGraph.insertEdge(v3, v1, new Highway("HighwaySix", 1));
		directedGraph.insertEdge(v3, v2, new Highway("HighwaySeven", 4));
		directedGraph.insertEdge(v3, v4, new Highway("HighwayEight", 1));
		directedGraph.insertEdge(v1, v2, new Highway("HighwayNine", 6));
		
		PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.primJarnik(directedGraph);
		
		Iterator<Edge<Highway>> it = tree.iterator();
		
		assertEquals("HighwayOne", it.next().getElement().getName());
		assertEquals("HighwayThree", it.next().getElement().getName());
		assertEquals("HighwayFour", it.next().getElement().getName());
		assertEquals("HighwayTwo", it.next().getElement().getName());
		assertEquals("HighwaySeven", it.next().getElement().getName());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests Prim-Jarnik's algorithm
	 */
	@Test
	public void kruskalTest() {
		Vertex<String> a = undirectedGraph.insertVertex("a");
		Vertex<String> b = undirectedGraph.insertVertex("b");
		Vertex<String> c = undirectedGraph.insertVertex("c");
		Vertex<String> d = undirectedGraph.insertVertex("d");
		Vertex<String> e = undirectedGraph.insertVertex("e");
		Vertex<String> f = undirectedGraph.insertVertex("f");
		
		undirectedGraph.insertEdge(a, b, new Highway("HighwayZero", 3));
		undirectedGraph.insertEdge(a, e, new Highway("HighwayOne", 7));
		undirectedGraph.insertEdge(d, f, new Highway("HighwayEight", 5)); //This needs to be here because it would process this first because they have equal weights.
		undirectedGraph.insertEdge(b, c, new Highway("HighwayTwo", 5));
		undirectedGraph.insertEdge(b, e, new Highway("HighwayThree", 5));
		undirectedGraph.insertEdge(e, c, new Highway("HighwayFour", 4));
		undirectedGraph.insertEdge(e, d, new Highway("HighwayFive", 8));
		undirectedGraph.insertEdge(e, f, new Highway("HighwaySix", 6));
		undirectedGraph.insertEdge(c, d, new Highway("HighwaySeven", 9));
		
		PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
		
		Iterator<Edge<Highway>> it = tree.iterator();
		
		assertEquals("HighwayZero", it.next().getElement().getName());
		assertEquals("HighwayFour", it.next().getElement().getName());
		assertEquals("HighwayTwo", it.next().getElement().getName());
		assertEquals("HighwayEight", it.next().getElement().getName());
		assertEquals("HighwaySix", it.next().getElement().getName());
		assertFalse(it.hasNext());
	}

	
	
	/**
	 * example class for MST implementations
	 * @author Jeremiah Knizley
	 *
	 */
	public class Highway implements Weighted {
		/**
		 * The name of the highway
		 */
        private String name;
        /**
         * The length of the highway
         */
        private int length;
        
        /**
         * Constructor for highway (basic)
         * @param n the name of the highway
         * @param l the length of the highway
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }
        /**
         * Set the name of the highway
         * @param name the new name of the highway
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * Return the name of the highway
         * @return the name of the highway
         */
        public String getName() {
        	return name;
        }
        /**
         * returns the length of the highway
         * @return the length of the highway
         */
        public int getLength() {
            return length;
        }
        /**
         * Sets the length of the highway
         * @param length the new length of the highway
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
}
