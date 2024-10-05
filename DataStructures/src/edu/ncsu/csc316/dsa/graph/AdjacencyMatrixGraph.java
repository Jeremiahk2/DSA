package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends AbstractGraph<V, E> {
	
	/**
	 * Matrix representing edges in the graph
	 */
    private GraphEdge[][] matrix;
    /**
     * The list of vertices in the graph
     */
    private PositionalList<Vertex<V>> vertexList;
    /**
     * The list of Edges in the graph
     */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * An int that is used to decode vertices in the matrix
     */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
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
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        MatrixVertex v1 = validate(vertex1);
        MatrixVertex v2 = validate(vertex2);
        return matrix[v1.getIndex()][v2.getIndex()];
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        GraphEdge e = validate(edge);
        return e.getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        GraphVertex v = validate(vertex);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == v) {
            return ends[1];
        }
        if (ends[1] == v) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (GraphEdge e : matrix[v.getIndex()]) {
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
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
        MatrixVertex v = new MatrixVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        growArray();
        return v;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        MatrixVertex origin = validate(vertex1);
        MatrixVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        matrix[origin.getIndex()][destination.getIndex()] = e;
        if (!isDirected()) {
            matrix[destination.getIndex()][origin.getIndex()] = e;
        }
        return e;
    }


	@Override
    public void removeVertex(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        vertexList.remove(v.getPosition());
        for (Edge<E> e : outgoingEdges(v)) {
            removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        int index = v.getIndex();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == index || j == index) {
                	matrix[i][j] = null;
                }
            }
        }
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
        Vertex<V>[] ends = e.getEndpoints();
        int originIndex = validate(ends[0]).getIndex();
        int destIndex = validate(ends[1]).getIndex();
        matrix[originIndex][destIndex] = null;
        
    }

    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @param v the vertex to be validated
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
    
    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
//         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
}