package algorithms.sedgewick.graphs.api;

/**
 * Methods to find and extract negative cycles in a {@link Digraph} if such a
 * cycle exists
 *
 * @author Vikram Kommaraju
 */
public interface NegativeCycle {

	/**
	 * @return true if there is a negative cycle in the {@link Digraph}
	 */
	public boolean hasNegativeCycle();
	
	/**
	 * @return negative cycle in the {@link Digraph} if it exists
	 */
	public Iterable<DirectedEdge> negativeCycle();
	
}
