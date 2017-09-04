package algorithms.sedgewick.graphs.api;

/**
 * Abstract class defining APIs to perform search related operations on a given {@link Graph}
 * and a source vertex
 * 
 * @author Vikram Kommaraju
 *
 */
public abstract class Search {

	protected Graph g;
	protected int source;
	
	/**
	 * Must pass in a reference to a {@link Graph} and a source vertex
	 */
	protected Search(Graph g, int source) {
		this.g = g;
		this.source = source;
	}
	
	/**
	 * Returns true if vertex v is connected to source vertex
	 */
	public abstract boolean marked(int v);
	
	/**
	 * Returns the number of vertices conncted to source vertex
	 */
	public abstract int count();
}