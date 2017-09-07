package algorithms.sedgewick.graphs.mst;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.sedgewick.graphs.api.UndirectedEdge;
import algorithms.sedgewick.graphs.api.MinimumSpanningTree;
import algorithms.sedgewick.graphs.api.WeightedGraph;

/**
 * Implementation of {@link MinimumSpanningTree} API using Lazy Prim's Algorithm
 *
 * @author Vikram Kommaraju
 */
public class LazyPrimMST implements MinimumSpanningTree {

	private boolean marked[]; // MST Vertices
	private PriorityQueue<UndirectedEdge> pQueue;
	private Queue<UndirectedEdge> mstEdges; // MST edges

	LazyPrimMST(WeightedGraph g) {
		marked = new boolean[g.V()];
		pQueue = new PriorityQueue<UndirectedEdge>();
		mstEdges = new ArrayDeque<UndirectedEdge>();

		visit(g, 0); // Assume G is connected. So you can start with any vertex

		while (!pQueue.isEmpty()) {

			// Get the lowest weight edge in the pQueue
			UndirectedEdge e = pQueue.remove();
			int v = e.either();
			int w = e.other(v);

			// If both the vertices of that edge are marked, it is ineligible.
			if (marked[v] && marked[w]) {
				continue;
			}

			// If one of the vertices is unvisited, add the edge to MST
			mstEdges.add(e);

			// Add the unmarked vertex to the tree
			if (!marked[v]) {
				visit(g, v);
			}

			if (!marked[w]) {
				visit(g, w);
			}
		}
	}

	private void visit(WeightedGraph g, int v) {
		// Mark v and add to pQueue all edges from v to unmarked vertices
		marked[v] = true;
		for (UndirectedEdge e : g.adj(v)) {
			if (!marked[e.other(v)]) {
				pQueue.add(e);
			}
		}
	}

	public Iterable<UndirectedEdge> edges() {
		return mstEdges;
	}

	public double weight() {
		double totalWeight = 0;
		for (UndirectedEdge e : mstEdges) {
			totalWeight += e.weight();
		}
		return totalWeight;
	}

	public static void main(String[] args) throws Exception {
		WeightedGraph g = EdgeWeightedGraph.createSmallGraph();
		System.out.println(g);

		MinimumSpanningTree mst = new LazyPrimMST(g);
		System.out.println(mst.edges());
		System.out.println(mst.weight());
	}

}
