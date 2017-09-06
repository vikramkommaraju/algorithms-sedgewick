package algorithms.sedgewick.graphs.mst;

import algorithms.sedgewick.graphs.api.Edge;

/**
 * Implementation of {@link Edge} API
 *
 * @author Vikram Kommaraju
 */
public class WeightedEdge implements Edge {

	private final int v;
	private final int w;
	private final double weight;

	public WeightedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public double weight() {
		return this.weight;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if (vertex == v) {
			return w;
		} else if (vertex == w) {
			return v;
		} else {
			throw new RuntimeException("Inconsistent edge");
		}
	}

	public int compareTo(Edge that) {
		if (this.weight() < that.weight()) {
			return -1;
		} else if (this.weight() > that.weight()) {
			return 1;
		} else {
			return 0;
		}
	}

	public String toString() {
		return String.format("[ %d-->%d %.2f ]", v, w, weight);
	}

}
