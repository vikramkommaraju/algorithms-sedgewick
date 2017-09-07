package algorithms.sedgewick.graphs.api;

/**
 * API definition for a weighted edge
 *
 * @author Vikram Kommaraju
 */
public interface UndirectedEdge extends Comparable<UndirectedEdge> {
	
	/**
	 * Returns the weight of this edge
	 */
	double weight();
	
	/**
	 * Returns either of this edge's vertices
	 */
	int either();
	
	/**
	 * Returns the other vertex of this edge
	 */
	int other(int v);
}
