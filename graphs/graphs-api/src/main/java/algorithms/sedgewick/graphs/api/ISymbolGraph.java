package algorithms.sedgewick.graphs.api;

/**
 * Interface that defines a SymbolGraph
 * 
 * @author Vikram Kommaraju
 */
public interface ISymbolGraph {

	/**
	 * Returns true if key is a vertex in the {@link Graph}
	 */
	public boolean contains(String key);
	
	/**
	 * Returns the index associated with key
	 */
	public int index(String key);
	
	/**
	 * Returns the key associated with the vertex
	 */
	public String name(int index);
	
	/**
	 * Returns the underlying {@link Graph}
	 */
	public Graph G();
}