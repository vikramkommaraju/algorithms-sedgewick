package algorithms.sedgewick.graphs.api;

import java.util.List;

/**
 * Abstract class defining APIs to perform search related operations on a given {@link Graph}
 * and a source vertex or multiple source vertices
 * 
 * @author Vikram Kommaraju
 *
 */
public abstract class MultipleSourceGraphSearch {
	
	protected Graph<Integer> g;
	protected int singleSource;
	protected List<Integer> multipleSources;

	protected MultipleSourceGraphSearch(Graph<Integer> g, int singleSource) {
		this.g= g;
		this.singleSource = singleSource;
	}
	
	protected MultipleSourceGraphSearch(Graph<Integer> g, List<Integer> multipleSources) {
		this.g= g;
		this.multipleSources = multipleSources;
	}
	
	/**
	 * Returns true if vertex v is connected to any of the source vertices
	 */
	public abstract boolean marked(int v);

}
