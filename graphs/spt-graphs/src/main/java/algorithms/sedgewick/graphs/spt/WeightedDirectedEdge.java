package algorithms.sedgewick.graphs.spt;

import algorithms.sedgewick.graphs.api.DirectedEdge;

/**
 * Implmentation of the {@link DirectedEdge} API
 *
 * @author Vikram Kommaraju
 */
public class WeightedDirectedEdge implements DirectedEdge {

	private final int v;
	private final int w;
	private final double weight;
	
	WeightedDirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}
	
	public String toString() {
		return String.format("[ %d-->%d %.2f ]", v, w, weight);
	}

}
