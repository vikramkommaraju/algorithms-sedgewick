package algorithms.sedgewick.graphs.api;

/**
 * 
 * Interface that defines a Graph
 * 
 * @author Vikram Kommaraju
 */
public interface Graph<T> {

	/**
	 * This method should return the number of vertices in the Graph
	 */
	public int V();
	
	/**
	 * This method should return the number of edges in the Graph
	 */
	public int E();
	
	/**
	 * This method should insert an edge between v and w
	 * 
	 * @param vertex v
	 * @param vertex w
	 */
	public void addEdge(T v, T w);
	
	
	/**
	 * This method should return the adjacency list of a given vertex
	 */
	public Iterable<T> adj(T v);
	
}