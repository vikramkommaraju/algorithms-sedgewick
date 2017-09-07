package algorithms.sedgewick.graphs.api;

/**
 * API for a directed edge in a Weighted Digraph
 *
 * @author Vikram Kommaraju
 */
public interface DirectedEdge {
	
	/**
	 * W	eight of this edge
	 */
	double weight();
	
	/**
	 * Vertex this edge points from
	 */
	int from();
	
	/**
	 * Vertex this edge points to
	 */
	int to();

}
