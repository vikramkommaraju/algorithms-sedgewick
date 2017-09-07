package algorithms.sedgewick.graphs.mst;

import java.util.ArrayDeque;
import java.util.Queue;

import algorithms.sedgewick.graphs.api.UndirectedEdge;
import algorithms.sedgewick.graphs.api.MinimumSpanningTree;
import algorithms.sedgewick.graphs.api.WeightedGraph;
import algorithms.sedgewick.utils.IndexMinPQ;

/**
 * Eager implementation of the {@link MinimumSpanningTree} API
 *
 * @author Vikram Kommaraju
 */
public class PrimMST implements MinimumSpanningTree {

	private boolean[] marked;
	private UndirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pQueue;

	public PrimMST(WeightedGraph g) {
		marked = new boolean[g.V()];
		edgeTo = new UndirectedEdge[g.V()];
		distTo = new double[g.V()];
		pQueue = new IndexMinPQ<Double>(g.V());

		for (int i = 0; i < g.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}

		distTo[0] = 0.0;
		pQueue.insert(0, 0.0);
		while (!pQueue.isEmpty()) {
			int v = pQueue.delMin();
			visit(g, v);
		}

	}

	private void visit(WeightedGraph g, int v) {
		marked[v] = true;
		for (UndirectedEdge e : g.adj(v)) {
			int w = e.other(v);

			if (marked[w]) {
				continue;
			}
			if (e.weight() < distTo[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();

				if (pQueue.contains(w)) {
					pQueue.changeKey(w, distTo[w]);
				} else {
					pQueue.insert(w, distTo[w]);
				}
			}
		}
	}

	public Iterable<UndirectedEdge> edges() {
		Queue<UndirectedEdge> mstEdges = new ArrayDeque<UndirectedEdge>();
		for (int v = 0; v < edgeTo.length; v++) {
			UndirectedEdge e = edgeTo[v];
			if (e != null) { // We need this check because edgeTo[0] is null since 0 is itself the root
				mstEdges.add(e);
			}
		}
		return mstEdges;
	}

	public double weight() {
		double totalWeight = 0.0;
		for (UndirectedEdge e : edges()) {
			totalWeight += e.weight();
		}
		return totalWeight;
	}

	public static void main(String[] args) throws Exception {
		WeightedGraph g = EdgeWeightedGraph.createSmallGraph();
		System.out.println(g);

		MinimumSpanningTree mst = new PrimMST(g);
		System.out.println(mst.edges());
		System.out.println(mst.weight());
	}
}
