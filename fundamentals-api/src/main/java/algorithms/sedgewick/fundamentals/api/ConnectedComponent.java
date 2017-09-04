package algorithms.sedgewick.fundamentals.api;

/**
 * Interface to define APIs for finding if two components/sites are connected
 * to each other and to find the total number of connected components
 * 
 * @author Vikram Kommaraju
 *
 */
public interface ConnectedComponent {

	/**
	 * Returns true if v and w are connected
	 */
	public boolean connected(int v, int w);
	
	/**
	 * Returns the number of connected components
	 */
	public int count();
	
	/**
	 * Component identifier of v. Value is between 0 and count()-1
	 */
	public int id(int v);
}