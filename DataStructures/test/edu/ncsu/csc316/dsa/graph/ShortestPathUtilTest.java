package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Class for testing the shortestPathUtil class
 * @author Jeremiah Knizley
 *
 */
public class ShortestPathUtilTest {
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
	}
	
	
	/**
	 * Tests Dijkstra's algorithm
	 */
	@Test
	public void testDijkstra() {
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
		
		Map<Vertex<String>, Integer> traversal = ShortestPathUtil.dijkstra(directedGraph, v0);
		
		Iterable<Entry<Vertex<String>, Integer>> entryset = traversal.entrySet();
		Iterator<Entry<Vertex<String>, Integer>> it = entryset.iterator();
		
		assertEquals(v2, it.next().getKey());
		assertEquals(v3, it.next().getKey());
		assertEquals(v1, it.next().getKey());
		assertEquals(v4, it.next().getKey());
		assertEquals(v5, it.next().getKey());
		assertEquals(v0, it.next().getKey());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests Dijkstra's algorithm
	 */
	@Test
	public void testShortestPath() {
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
		
		Map<Vertex<String>, Integer> traversal = ShortestPathUtil.dijkstra(directedGraph, v0);
		
		Map<Vertex<String>, Edge<Highway>> tree = ShortestPathUtil.shortestPathTree(directedGraph, v0, traversal);
		
		Iterable<Entry<Vertex<String>, Edge<Highway>>> entryset = tree.entrySet();
		Iterator<Entry<Vertex<String>, Edge<Highway>>> it = entryset.iterator();
		
		assertEquals("HighwayOne", it.next().getValue().getElement().getName());
		assertEquals("HighwayThree", it.next().getValue().getElement().getName());
		assertEquals("HighwayFour", it.next().getValue().getElement().getName());
		assertEquals("HighwayTwo", it.next().getValue().getElement().getName());
		assertEquals("HighwaySeven", it.next().getValue().getElement().getName());
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
