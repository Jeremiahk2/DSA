package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyListGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyListGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyListGraph vertices each maintain
 * lists of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected adjacency list graph
     */    
    public AdjacencyListGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency list graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyListGraph(boolean directed) {
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
    
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
        
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);       
        ALEdge edge = new ALEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        // Remember to set the edge's positions in the outgoingEdges 
        //    and incomingEdges lists for the appropriate vertices
        origin.outgoing.addLast(edge);
        destination.incoming.addLast(edge);
        return edge;
    }

    @Override
    public void removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
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
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        edgeList.remove(e.getPosition());
        origin.outgoing.remove(e.getPosition());
        dest.incoming.remove(e.getPosition());
    }

    /**
     * Represents a vertex in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALVertex extends GraphVertex {

        /** A positional list of outgoing edges */
        private PositionalList<Edge<E>> outgoing;

        /** A positional list of incoming edges */
        private PositionalList<Edge<E>> incoming;

        /**
         * Creates a new adjacency list vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if (isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a positional list of outgoingEdges from the vertex. For an undirected
         * graph, returns the same as getIncoming()
         * 
         * @return a positional list of outgoing edges from the vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a positional list of incomingEdges to the vertex. For an undirected
         * graph, returns the same as getOutgoing()
         * 
         * @return a positional list of incoming edges to the vertex
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Represents an edge in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALEdge extends GraphEdge {

        /** The position of the edge in a vertex's outgoing edge list */
        private Position<Edge<E>> outgoingListPosition;

        /** The position of the edge in a vertex's incoming edge list */
        private Position<Edge<E>> incomingListPosition;

        /**
         * Creates a new adjacency list edge
         * 
         * @param element the data to store in the edge
         * @param v1      an endpoint vertex
         * @param v2      an endpoint vertex
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }

        /**
         * Returns the position of the edge in the associated vertex's outgoing edge
         * list
         * 
         * @return the position of the edge in the associated vertex's outgoing edge
         *         list
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's outgoing edge list
         * 
         * @param outgoingListPosition the position of the edge in the associated
         *                             vertex's outgoing edge list
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }

        /**
         * Returns the position of the edge in the associated vertex's incoming edge
         * list
         * 
         * @return the position of the edge in the associated vertex's incoming edge
         *         list
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's incoming edge list
         * 
         * @param incomingListPosition the position of the edge in the associated
         *                             vertex's incoming edge list
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency list vertex
     * 
     * @return an adjacency list vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency list
     *                                  vertex
     */
    private ALVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex) v;
    }

    /**
     * Safely casts an Edge to an adjacency list edge
     * 
     * @return an adjacency list edge representation of the given Edge
     * @throws IllegalArgumentException if the edge is not a valid adjacency list
     *                                  edge
     */
    protected ALEdge validate(Edge<E> e) {
        if (!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge) e;
    }
}