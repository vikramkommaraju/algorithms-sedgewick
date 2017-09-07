package algorithms.sedgewick.graphs.api;

/**
 * API definition for finding a Mininum Spanning Tree of a {@link WeightedGraph}
 *
 * @author Vikram Kommaraju
 */
public interface MinimumSpanningTree {

	/**
	 * Returns all edges of the MST
	 */
	Iterable<UndirectedEdge> edges();
	
	/**
	 * Returns the total weight of the MST
	 */
	double weight();
}
