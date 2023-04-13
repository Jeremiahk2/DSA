package edu.ncsu.csc316.dsa.graph;

/**
 * The Graph abstract data type represents a collection of vertices, edges, and
 * the relationships between vertices and edges.
 * 
 * The Graph interface is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @param <V> the type of vertices in the graph
 * @param <E> the type of edges in the graph
 */
public interface Graph<V, E> {

    /**
     * Returns true if the graph is directed; otherwise, return false if the graph
     * is undirected
     * 
     * @return true if the graph is directed; otherwise, return false if the graph
     *         is undirected
     */
    boolean isDirected();

    /**
     * Returns the number of vertices in the graph
     * 
     * @return the number of vertices in the graph
     */
    int numVertices();

    /**
     * Returns an {@link Iterable} collection of vertices in the graph
     * 
     * @return an {@link Iterable} collection of vertices in the graph
     */
    Iterable<Vertex<V>> vertices();

    /**
     * Returns the number of edges in the graph
     * 
     * @return the number of edges in the graph
     */
    int numEdges();

    /**
     * Returns an {@link Iterable} collection of edges in the graph
     * 
     * @return an {@link Iterable} collection of edges in the graph
     */
    Iterable<Edge<E>> edges();

    /**
     * Returns the edge that exists between vertex1 and vertex2; if no edge exists
     * from vertex1 to vertex2, return null
     * 
     * @param vertex1 an endpoint vertex; for a directed graph, the source vertex
     * @param vertex2 an endpoint vertex; for a directed graph, the destination
     *                vertex
     * @return the edge that exists between vertex1 and vertex2
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);

    /**
     * Returns an array that represents the two endpoint vertices of the given edge
     * 
     * @param edge the edge for which to retrieve endpoint vertices
     * @return an array that represents the two endpoint vertices of the given edge
     */
    Vertex<V>[] endVertices(Edge<E> edge);

    /**
     * Given an edge and one of the edge's endpoint vertices, returns the remaining
     * endpoint vertex
     * 
     * @param vertex an endpoint vertex of the given edge
     * @param edge   the edge for which to determine endpoint vertices
     * @return the remaining endpoint vertex for the given edge
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);

    /**
     * Returns the outdegree of the given vertex -- in other words, the number of
     * edges that are 'leaving' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the outdegree
     * @return the outdegree of the given vertex
     */
    int outDegree(Vertex<V> vertex);

    /**
     * Returns the indegree of the given vertex -- in other words, the number of
     * edges that are 'entering' the given vertex
     * 
     * @param vertex the vertex for which to retrieve the indegree
     * @return the indegree of the given vertex
     */
    int inDegree(Vertex<V> vertex);

    /**
     * Returns an {@link Iterable} collection of outgoing edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve outgoing edges
     * @return an {@link Iterable} collection of outgoing edges for a given vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);

    /**
     * Returns an {@link Iterable} collection of incoming edges for a given vertex
     * 
     * @param vertex the vertex for which to retrieve incoming edges
     * @return an {@link Iterable} collection of incomoing edges for a given vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);

    /**
     * Adds a new vertex to the graph, and returns a reference to the vertex that
     * was created
     * 
     * @param vertexData the data to store in the new vertex
     * @return a reference to the newly created vertex
     */
    Vertex<V> insertVertex(V vertexData);

    /**
     * Adds a new edge to the graph, and returns a reference to the edge that was
     * created
     * 
     * @param v1       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the source vertex
     * @param v2       for an undirected graph, one of the endpoint vertices; for a
     *                 directed graph, the destination vertex
     * @param edgeData the data to store in the new edge
     * @return a reference to the newly created edge
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);

    /**
     * Removes the given vertex from the graph. Also removes all of the vertex's
     * incident (incoming and outgoing) edges from the graph.
     * 
     * @param vertex the vertex to remove from the graph
     */
    void removeVertex(Vertex<V> vertex);

    /**
     * Removes the given edge from the graph.
     * 
     * @param edge the edge to remove from the graph
     */
    void removeEdge(Edge<E> edge);

    /**
     * Represents an Edge in a Graph
     * 
     * @author Dr. King
     *
     * @param <E> the type of data in the edge
     */
    interface Edge<E> {

        /**
         * Returns the element stored in the edge
         * 
         * @return the element stored in the edge
         */
        E getElement();
    }

    /**
     * Represents a Vertex in a Graph
     * 
     * @author Dr. King
     *
     * @param <V> the type of data in the vertex
     */
    interface Vertex<V> {

        /**
         * Returns the element stored in the vertex
         * 
         * @return the element stored in the vertex
         */
        V getElement();
    }
}