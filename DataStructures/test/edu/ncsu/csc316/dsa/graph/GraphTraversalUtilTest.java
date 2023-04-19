package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for GraphTraversalUtil
 * @author Jeremiah Knizley
 *
 */
public class GraphTraversalUtilTest {

	
	/**
	 * Undirected graph for testing purposes
	 */
	private Graph<String, Integer> undirectedGraph;
	/**
	 * Directed graph for testing purposes
	 */
	private Graph<String, Integer> directedGraph;

	
	/**
	 * Create a new instance of an edge list graph before each test case executes
	 */ 
	@Before
	public void setUp() {
		undirectedGraph = new EdgeListGraph<String, Integer>();
		directedGraph = new EdgeListGraph<String, Integer>(true);
	}
	
	/**
	 * Tests depthFirstSearch functionality
	 */
	@Test
	public void testDepthFirstSearchUndirected() {
		Vertex<String> v1 = undirectedGraph.insertVertex("One");
		Vertex<String> v2 = undirectedGraph.insertVertex("Two");
		Vertex<String> v3 = undirectedGraph.insertVertex("Three");
		Vertex<String> v4 = undirectedGraph.insertVertex("Four");
		Vertex<String> v5 = undirectedGraph.insertVertex("Five");
		Vertex<String> v6 = undirectedGraph.insertVertex("Six");
		Vertex<String> v7 = undirectedGraph.insertVertex("Seven");
		Vertex<String> v8 = undirectedGraph.insertVertex("Eight");
		Vertex<String> v9 = undirectedGraph.insertVertex("Nine");
		Vertex<String> v10 = undirectedGraph.insertVertex("Ten");
		Vertex<String> v11 = undirectedGraph.insertVertex("Eleven");
		
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v2, v3, 10);
		undirectedGraph.insertEdge(v1, v3, 20);
		
		undirectedGraph.insertEdge(v2, v11, 25);
		
		undirectedGraph.insertEdge(v11, v9, 30);
		undirectedGraph.insertEdge(v11, v10, 35);
		
		undirectedGraph.insertEdge(v9, v4, 45);
		undirectedGraph.insertEdge(v9, v6, 50);
		undirectedGraph.insertEdge(v9, v10, 40);
		undirectedGraph.insertEdge(v4, v5, 70);
		undirectedGraph.insertEdge(v4, v6, 65);
		undirectedGraph.insertEdge(v4, v10, 55);
		
		undirectedGraph.insertEdge(v6, v7, 75);
		undirectedGraph.insertEdge(v6, v8, 85);
		undirectedGraph.insertEdge(v7, v8, 80);
		undirectedGraph.insertEdge(v10, v6, 60);
		
		Map<Vertex<String>, Edge<Integer>> traversal = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
		
		Iterable<Entry<Vertex<String>, Edge<Integer>>> entryset = traversal.entrySet();
		Iterator<Entry<Vertex<String>, Edge<Integer>>> it = entryset.iterator();
		
		assertEquals((Integer)60, it.next().getValue().getElement());
		assertEquals((Integer)80, it.next().getValue().getElement());
		assertEquals((Integer)75, it.next().getValue().getElement());
		assertEquals((Integer)65, it.next().getValue().getElement());
		assertEquals((Integer)70, it.next().getValue().getElement());
		assertEquals((Integer)45, it.next().getValue().getElement());
		assertEquals((Integer)30, it.next().getValue().getElement());
		assertEquals((Integer)25, it.next().getValue().getElement());
		assertEquals((Integer)10, it.next().getValue().getElement());
		assertEquals((Integer)5, it.next().getValue().getElement());
		assertFalse(it.hasNext());
	}
	/**
	 * Tests depthFirstSearch functionality
	 */
	@Test
	public void testDepthFirstSearchDirected() {
		Vertex<String> bos = directedGraph.insertVertex("One");
		Vertex<String> ord = directedGraph.insertVertex("Two");
		Vertex<String> dfw = directedGraph.insertVertex("Three");
		Vertex<String> lax = directedGraph.insertVertex("Four");
		Vertex<String> jfk = directedGraph.insertVertex("Five");
		Vertex<String> sfo = directedGraph.insertVertex("Six");
		Vertex<String> mia = directedGraph.insertVertex("Seven");
		
		directedGraph.insertEdge(bos, jfk, 5);
		directedGraph.insertEdge(bos, mia, 10);
		directedGraph.insertEdge(bos, sfo, 15);
		directedGraph.insertEdge(ord, dfw, 20);
		directedGraph.insertEdge(ord, mia, 25);
		directedGraph.insertEdge(dfw, lax, 30);
		directedGraph.insertEdge(dfw, ord, 35);
		directedGraph.insertEdge(dfw, sfo, 40);
		directedGraph.insertEdge(jfk, bos, 45);
		directedGraph.insertEdge(jfk, dfw, 50);
		directedGraph.insertEdge(jfk, sfo, 55);
		directedGraph.insertEdge(lax, ord, 60);
		directedGraph.insertEdge(mia, dfw, 65);
		directedGraph.insertEdge(mia, lax, 70);
		
		Map<Vertex<String>, Edge<Integer>> traversal = GraphTraversalUtil.depthFirstSearch(directedGraph, bos);
		
		Iterable<Entry<Vertex<String>, Edge<Integer>>> entryset = traversal.entrySet();
		Iterator<Entry<Vertex<String>, Edge<Integer>>> it = entryset.iterator();
		
		assertEquals((Integer)40, it.next().getValue().getElement());
		assertEquals((Integer)25, it.next().getValue().getElement());
		assertEquals((Integer)60, it.next().getValue().getElement());
		assertEquals((Integer)30, it.next().getValue().getElement());
		assertEquals((Integer)50, it.next().getValue().getElement());
		assertEquals((Integer)5, it.next().getValue().getElement());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests breadthFirstSearch functionality
	 */
	@Test
	public void testBreadthFirstSearchUndirected() {
		Vertex<String> v1 = undirectedGraph.insertVertex("One");
		Vertex<String> v2 = undirectedGraph.insertVertex("Two");
		Vertex<String> v3 = undirectedGraph.insertVertex("Three");
		Vertex<String> v4 = undirectedGraph.insertVertex("Four");
		Vertex<String> v5 = undirectedGraph.insertVertex("Five");
		Vertex<String> v6 = undirectedGraph.insertVertex("Six");
		Vertex<String> v7 = undirectedGraph.insertVertex("Seven");
		Vertex<String> v8 = undirectedGraph.insertVertex("Eight");
		Vertex<String> v9 = undirectedGraph.insertVertex("Nine");
		Vertex<String> v10 = undirectedGraph.insertVertex("Ten");
		Vertex<String> v11 = undirectedGraph.insertVertex("Eleven");
		
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v2, v3, 10);
		undirectedGraph.insertEdge(v1, v3, 20);
		
		undirectedGraph.insertEdge(v2, v11, 25);
		
		undirectedGraph.insertEdge(v11, v9, 30);
		undirectedGraph.insertEdge(v11, v10, 35);
		
		undirectedGraph.insertEdge(v9, v4, 45);
		undirectedGraph.insertEdge(v9, v6, 50);
		undirectedGraph.insertEdge(v9, v10, 40);
		undirectedGraph.insertEdge(v4, v5, 70);
		undirectedGraph.insertEdge(v4, v6, 65);
		undirectedGraph.insertEdge(v4, v10, 55);
		
		undirectedGraph.insertEdge(v6, v7, 75);
		undirectedGraph.insertEdge(v6, v8, 85);
		undirectedGraph.insertEdge(v7, v8, 80);
		undirectedGraph.insertEdge(v10, v6, 60);
		
		Map<Vertex<String>, Edge<Integer>> traversal = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
		
		Iterable<Entry<Vertex<String>, Edge<Integer>>> entryset = traversal.entrySet();
		Iterator<Entry<Vertex<String>, Edge<Integer>>> it = entryset.iterator();
		
		assertEquals((Integer)85, it.next().getValue().getElement());
		assertEquals((Integer)75, it.next().getValue().getElement());
		assertEquals((Integer)70, it.next().getValue().getElement());
		assertEquals((Integer)50, it.next().getValue().getElement());
		assertEquals((Integer)45, it.next().getValue().getElement());
		assertEquals((Integer)35, it.next().getValue().getElement());
		assertEquals((Integer)30, it.next().getValue().getElement());
		assertEquals((Integer)25, it.next().getValue().getElement());
		assertEquals((Integer)20, it.next().getValue().getElement());
		assertEquals((Integer)5, it.next().getValue().getElement());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Tests breadthFirstSearch functionality
	 */
	@Test
	public void testBreadthFirstSearchDirected() {
		Vertex<String> bos = directedGraph.insertVertex("One");
		Vertex<String> ord = directedGraph.insertVertex("Two");
		Vertex<String> dfw = directedGraph.insertVertex("Three");
		Vertex<String> lax = directedGraph.insertVertex("Four");
		Vertex<String> jfk = directedGraph.insertVertex("Five");
		Vertex<String> sfo = directedGraph.insertVertex("Six");
		Vertex<String> mia = directedGraph.insertVertex("Seven");
		
		directedGraph.insertEdge(bos, jfk, 5);
		directedGraph.insertEdge(bos, mia, 10);
		directedGraph.insertEdge(bos, sfo, 15);
		directedGraph.insertEdge(ord, dfw, 20);
		directedGraph.insertEdge(ord, mia, 25);
		directedGraph.insertEdge(dfw, lax, 30);
		directedGraph.insertEdge(dfw, ord, 35);
		directedGraph.insertEdge(dfw, sfo, 40);
		directedGraph.insertEdge(jfk, bos, 45);
		directedGraph.insertEdge(jfk, dfw, 50);
		directedGraph.insertEdge(jfk, sfo, 55);
		directedGraph.insertEdge(lax, ord, 60);
		directedGraph.insertEdge(mia, dfw, 65);
		directedGraph.insertEdge(mia, lax, 70);
		
		
		Map<Vertex<String>, Edge<Integer>> traversal = GraphTraversalUtil.breadthFirstSearch(directedGraph, bos);
		
		Iterable<Entry<Vertex<String>, Edge<Integer>>> entryset = traversal.entrySet();
		Iterator<Entry<Vertex<String>, Edge<Integer>>> it = entryset.iterator();
		
		assertEquals((Integer)35, it.next().getValue().getElement());
		assertEquals((Integer)70, it.next().getValue().getElement());
		assertEquals((Integer)50, it.next().getValue().getElement());
		assertEquals((Integer)15, it.next().getValue().getElement());
		assertEquals((Integer)10, it.next().getValue().getElement());
		assertEquals((Integer)5, it.next().getValue().getElement());
		
		
	}

}
