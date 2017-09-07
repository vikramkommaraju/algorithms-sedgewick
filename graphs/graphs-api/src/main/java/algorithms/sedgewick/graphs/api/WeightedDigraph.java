package algorithms.sedgewick.graphs.api;

/**
 * API definition for a weighted {@link Digraph}
 *
 * @author Vikram Kommaraju
 */
public interface WeightedDigraph {
	
	/**
	 * Number of vertices
	 */
	int V();
	
	/**
	 * Number of edges
	 */
	int E();
	
	/**
	 * Add directed edge e to this Digraph
	 */
	void addEdge(DirectedEdge e);
	
	/**
	 * Edges pointing from vertex v
	 */
	Iterable<DirectedEdge> adj(int v);
	
	/**
	 * All edges in this Digraph
	 */
	Iterable<DirectedEdge> edges();

}
