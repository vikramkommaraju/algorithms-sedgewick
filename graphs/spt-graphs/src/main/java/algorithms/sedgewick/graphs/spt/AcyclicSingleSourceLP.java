package algorithms.sedgewick.graphs.spt;

import java.util.ArrayList;

import algorithms.sedgewick.graphs.api.DirectedEdge;
import algorithms.sedgewick.graphs.api.ShortestPath;
import algorithms.sedgewick.graphs.api.WeightedDigraph;

/**
 * Reverse implementation {@link AcyclicSingleSourceSP}. It computes the longest
 * path from a given source vertex to any connected target vertex
 *
 * @author Vikram Kommaraju
 */
public class AcyclicSingleSourceLP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;

	public AcyclicSingleSourceLP(WeightedDigraph g, int source) {
		edgeTo = new DirectedEdge[g.V()];
		distTo = new double[g.V()];
		for(int i=0; i<distTo.length; i++) {
			distTo[i] = Double.NEGATIVE_INFINITY;
		}
		distTo[source] = 0.0;
		
		DirectedDFSOrder dfs = new DirectedDFSOrder(g, source);
		// reverse port gets the topological order
		for(int v : dfs.reversePost()) {
			relax(g, v);
		}	
	}

	private void relax(WeightedDigraph g, int v) {

		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();

			if (distTo[w] < distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> path(int v) {
		ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.add(0, e);
		}
		return path;
	}

	public static void main(String[] args) throws Exception {
		WeightedDigraph g = EdgeWeightedDigraph.createSmallDAG();
		System.out.println(g);

		int source = 5;
		int sink = 2;
		AcyclicSingleSourceLP lpt = new AcyclicSingleSourceLP(g, source);
		System.out.println(lpt.distTo(sink));
		System.out.println(lpt.path(sink));
	}

}
