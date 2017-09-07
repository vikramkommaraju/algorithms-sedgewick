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
	 * Add an {@link UndirectedEdge} e to the Graph
	 */
	void addEdge(UndirectedEdge e);

	/**
	 * Edges incident on vertex v
	 */
	Iterable<UndirectedEdge> adj(int v);

	/**
	 * All edges of this Graph
	 */
	Iterable<UndirectedEdge> edges();
}
