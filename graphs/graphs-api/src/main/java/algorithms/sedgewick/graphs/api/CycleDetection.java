package algorithms.sedgewick.graphs.api;

/**
 * API to detect if a cycle is present in a {@link Graph} or {@link Digraph}
 *
 * @author Vikram Kommaraju
 */
public interface CycleDetection {

	/**
	 * @return true if there exists a cycle
	 */
	public boolean hasCycle();
	
	/**
	 * @return the cycle in the graph. Null otherwise
	 */
	public Iterable cycle();
}
