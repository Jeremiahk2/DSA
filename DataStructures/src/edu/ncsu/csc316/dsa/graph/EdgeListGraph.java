package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An EdgeListGraph is an implementation of the {@link Graph} abstract data type.
 * EdgeListGraph maintains a list of vertices in the graph and a list of edges in the
 * graph.
 * 
 * The EdgeListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {

    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected edge list graph
     */
    public EdgeListGraph() {
        this(false);
    }

    /**
     * Creates a new edge list graph
     * @param directed if true, the graph is directed; if false, the graph is undirected
     */
    public EdgeListGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }

    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[0] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[1] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[1] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[0] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        GraphVertex origin = validate(vertex1);
        GraphVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        return e;
    }

    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        for (Edge<E> e : outgoingEdges(v)) {
            removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        vertexList.remove(v.getPosition());
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
    }
}