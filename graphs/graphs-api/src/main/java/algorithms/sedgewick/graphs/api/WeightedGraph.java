package algorithms.sedgewick.graphs.api;

/**
 * API definition for a weighted {@link Graph}
 *
 * @author Vikram Kommaraju
 */
public interface WeightedGraph {

	/**
	 * Returns the number of vertices in the Graph
	 */
	int V();

	/**
	 * Returns the number of edges in the Graph
	 */
	int E();

	/**
	 * Add an {@link Edge} e to the Graph
	 */
	void addEdge(Edge e);

	/**
	 * Edges incident on vertex v
	 */
	Iterable<Edge> adj(int v);

	/**
	 * All edges of this Graph
	 */
	Iterable<Edge> edges();
}
