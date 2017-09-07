package algorithms.sedgewick.graphs.mst;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.sedgewick.graphs.api.Edge;
import algorithms.sedgewick.graphs.api.MinimumSpanningTree;
import algorithms.sedgewick.graphs.api.WeightedGraph;
import algorithms.sedgewick.utils.UF;

/**
 * Implemenation of the {@link MinimumSpanningTree} API using Kruskal's
 * algorithm
 *
 * @author Vikram Kommaraju
 */
public class KruskalMST implements MinimumSpanningTree {

	private Queue<Edge> mstEdges;
	private double totalWeight;

	public KruskalMST(WeightedGraph g) {
		mstEdges = new ArrayDeque<Edge>();
		UF uf = new UF(g.V());

		// Add all edges to PQ
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(g.E());
		for (Edge e : g.edges()) {
			pQueue.add(e);
		}

		while (!pQueue.isEmpty() && mstEdges.size() < g.V() - 1) {
			Edge e = pQueue.remove();
			int v = e.either();
			int w = e.other(v);

			// Add edge to MST if v and w are not connected to the same tree
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				mstEdges.add(e);
				totalWeight += e.weight();
			}
		}
	}

	public Iterable<Edge> edges() {
		return mstEdges;
	}

	public double weight() {
		return totalWeight;
	}

	public static void main(String[] args) throws Exception {
		EdgeWeightedGraph g = EdgeWeightedGraph.createSmallGraph();
		System.out.println(g);

		MinimumSpanningTree mst = new KruskalMST(g);
		System.out.println(mst.edges());
		System.out.println(mst.weight());
	}

}
