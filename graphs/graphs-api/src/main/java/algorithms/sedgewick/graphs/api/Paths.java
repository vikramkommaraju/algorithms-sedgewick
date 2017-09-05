package algorithms.sedgewick.graphs.api;

/**
 * This class has APIs for finding if a path exists from a vertex to a given
 * source and also to find the path from source to that vertex
 * 
 * @author Vikram Kommaraju
 *
 */
public abstract class Paths {
	
	protected Graph<Integer> g;
	protected int source;
	
	public Paths(Graph<Integer> g, int source) {
		this.g = g;
		this.source = source;
	}
	
	/**
	 * Returns true if there a path from source to vertex v
	 */
	public abstract boolean hasPathTo(int v);
	
	/**
	 * Returns a path from source to v if it exists. Null otherwise
	 */
	public abstract Iterable<Integer> pathTo(int v);

}