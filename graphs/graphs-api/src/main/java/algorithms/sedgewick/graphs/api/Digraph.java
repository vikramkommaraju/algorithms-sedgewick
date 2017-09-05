package algorithms.sedgewick.graphs.api;

/**
 * Interface that defines API for a Directed Graph
 *
 * @author Vikram Kommaraju
 */
public interface Digraph<T> extends Graph<T> {

	/**
	 * Returns the reverse of this Digraph
	 */
	Digraph reverse();
}
