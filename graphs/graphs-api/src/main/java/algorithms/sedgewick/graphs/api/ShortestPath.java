package algorithms.sedgewick.graphs.api;

/**
 * This class defines APIs for finding shortest paths from a given source vertex
 * to any conected vertex in an {@link WeightedDigraph}
 *
 * @author Vikram Kommaraju
 */
public abstract class ShortestPath {

	protected WeightedDigraph g;
	protected int source;

	public ShortestPath(WeightedDigraph g, int source) {
		this.g = g;
		this.source = source;
	}

	/**
	 * Distance from source to vertex v. INFINITY if no path from source to v
	 */
	abstract double distTo(int v);

	/**
	 * Is there a path from source to v
	 */
	abstract boolean hasPathTo(int v);

	/**
	 * Path from source to v. Null if none
	 */
	abstract Iterable<DirectedEdge> path(int v);

}